package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

/**
 * ユーザーデータを設定取得する
 * @author MinamiHitoki
 *
 */
public class userBean {

	/**
	 * ユーザのid,email,name,password
	 */
	private String id=null, email, name, profile="", password;
	
	public userBean() {
		
	}
	
	/**
	 * idを設定する
	 * @param id 設定したいid
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * idを取得する
	 * @return ユーザのid
	 */
	public String getId() {
		if (this.id == null) {
			UUID u1 = UUID.randomUUID();
			this.id = u1.toString();
			return this.id;
		}
		
		return this.id;
	}
	
	/**
	 * emailの設定
	 * @param email 設定したいemail
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * emailの取得
	 * @return ユーザのemail
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * nameの設定
	 * @param name ユーザの名前
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * ユーザの名前を取得する
	 * @return ユーザの名前
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * ユーザのprofileIdを設定します
	 * @param profile 設定したいprofileId
	 */
	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	/**
	 * profileIdを取得する
	 * @return profileのid
	 */
	public String getProfile() {
		return this.profile;
	}
	
	/**
	 * Passwordを設定します
	 * @param password 設定したいパスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * パスワードの取得
	 * @return ユーザのパスワード
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * DBのuserテーブルに自分のインスタンスの情報を登録します
	 * @return 登録成功の可否
	 */
	public boolean insertRecord() {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "INSERT INTO user (id, email, name, profile, password) VALUES(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.id);
			ps.setString(2, this.email);
			ps.setString(3, this.name);
			ps.setString(4, this.profile);
			ps.setString(4, this.password);
			//sqlの実行
			int count = ps.executeUpdate();//
			//close connection
			ps.close();
			con.close();
			//確認
			if (count > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * email,passwordに応じてuserのレコードリストを返します
	 * @param email 取得したいuserのemail
	 * @param password 取得したいuserのpassword
	 * @return emailとpasswordが一致したユーザレコード。一致しない場合はnullを返します
	 */
	public ArrayList<userBean> getUserRecordByEmail(String email, String password) {
		
		ArrayList<userBean> list = new ArrayList<userBean>();
		
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM user WHERE email=? AND password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				userBean tmpUB = new userBean();
				tmpUB.setId(rs.getString("id"));
				tmpUB.setEmail(rs.getString("email"));
				tmpUB.setName(rs.getString("name"));
				tmpUB.setProfile(rs.getString("profile"));
				//userBean	をリストに追加
				list.add(tmpUB);
			}
			rs.close();
			ps.close();
			con.close();
			//最後にuserBeanを返す
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * userIdに応じてDBから取り出したレコードをuserBeanのリストとして返します
	 * @param userId 取得したいユーザのid
	 * @return idが一致したユーザのuserBeanリスト
	 */
	public ArrayList<userBean> getUserRecordById(String userId) {
		ArrayList<userBean> list = new ArrayList<userBean>();
		
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM user WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				userBean tmpUB = new userBean();
				tmpUB.setId(rs.getString("id"));
				tmpUB.setEmail(rs.getString("email"));
				tmpUB.setName(rs.getString("name"));
				tmpUB.setProfile(rs.getString("profile"));
				//userBean	をリストに追加
				list.add(tmpUB);
			}
			rs.close();
			ps.close();
			con.close();
			//最後にuserBeanを返す
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * idに応じてインスタンス自身にDBから取り出したレコードを設定します。
	 * @param userId 取得したいuserId
	 */
	public void getUserById(String userId) {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM user WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				this.setId(rs.getString("id"));
				this.setEmail(rs.getString("email"));
				this.setName(rs.getString("name"));
				this.setProfile(rs.getString("profile"));
				//userBean	をリストに追加
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {

		}
	}
	
	/**
	 * 自身のインスタンスに設定したプロパティをDBのuserテーブルに登録します
	 * @return 登録成功の可否
	 */
	public boolean setUserRecord() {
		try {
			//get DB connection
			Connection con = DBManager.getUserConnection();
			
			//execute Sql
			String sql = "INSERT INTO user (id, email, name, password) VALUES (?,?,?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.getId());
			ps.setString(2, this.getEmail());
			ps.setString(3, this.getName());
			ps.setString(4, this.getPassword());
			//sqlの実行
			int count = ps.executeUpdate();
			//close connection
			ps.close();
			con.close();
			//確認
			if (count > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 自身のインスタンスに設定されたnameでDBのuserテーブルを更新します
	 */
	public void updateUserName() {
		try {
			//get DB connection
			Connection con = DBManager.getUserConnection();
			
			//execute Sql
			String sql = "UPDATE user SET name=? WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.getName());
			ps.setString(2, this.getId());
			//sqlの実行
			ps.executeUpdate();
			//close connection
			ps.close();
			con.close();
		} catch (Exception e) {
			return;
		}
	}
	
	/**
	 * 自身のインスタンスに設定されたprofileIdでDBのuserテーブルを更新します
	 * @return 更新成功の可否
	 */
	public boolean updateProfile() {
		try {
			//get DB connection
			Connection con = DBManager.getUserConnection();
			
			//execute Sql
			String sql = "UPDATE user SET profile=? WHERE id=?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.getProfile());
			ps.setString(2, this.getId());
			//sqlの実行
			ps.executeUpdate();
			//close connection
			ps.close();
			con.close();
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
