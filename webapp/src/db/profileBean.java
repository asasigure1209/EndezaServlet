package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

/**
 * プロフィールを設定取得する
 * @author MinamiHitoki
 *
 */
public class profileBean {
	/**
	 * プロフィールのid,bio,user,image
	 */
	private String id=null, bio, user, image;
	
	public profileBean() {
		
	}
	
	/**
	 * profileのidを設定します
	 * @param id 設定したいid
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * profileのidを取得する
	 * @return profileのid
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
	 * profileのBioのidの設定
	 * @param bio 設定したいBioのid
	 */
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	/**
	 * profileのBioのidの取得
	 * @return profileのBioのid
	 */
	public String getBio() {
		return this.bio;
	}
	
	/**
	 * profileのuserId
	 * @param user 設定したいuserId
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * profileのuserIdを取得する
	 * @return profileのuserId
	 */
	public String getUser() {
		return this.user;
	}
	
	/**
	 * profileのimageIdの設定
	 * @param image 設定したいimageId
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * profileのimageIdを取得する
	 * @return profileのimageId
	 */
	public String getImage() {
		return this.image;
	}
	
	/**
	 * profileIdに応じてprofileを自身のインスタンスに格納する
	 * @param profileId 取得したいprofileのid
	 */
	public void getProfileByProfileId(String profileId) {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM profile WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, profileId);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				this.setId(rs.getString("id"));
				this.setBio(rs.getString("bio"));
				this.setUser(rs.getString("user"));
				this.setImage(rs.getString("image"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * profileの更新
	 */
	public void updateRecord() {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "UPDATE profile SET bio=?,image=? WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			System.out.println(this.getBio());
			System.out.println(this.getImage());
			System.out.println(this.getId());
			
			ps.setString(1, this.getBio());
			ps.setString(2, this.getImage());
			ps.setString(3, this.getId());
			ps.executeUpdate();

			ps.close();
			con.close();
		} catch (Exception e) {
			return;
		}
	}
	
	/**
	 * profileの作成
	 * @return 作成の成功の可否
	 */
	public boolean setProfileRecord() {
		try {
			//get DB connection
			Connection con = DBManager.getUserConnection();
			
			//execute Sql
			String sql = "INSERT INTO profile (id, user) VALUES (?,?);";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.getId());
			ps.setString(2, this.getUser());

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
}
