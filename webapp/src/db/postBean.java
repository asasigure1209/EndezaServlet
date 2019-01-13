package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 投稿データを取得設定する
 * @author MinamiHitoki
 *
 */
public class postBean {

	/**
	 * postのid,user,text,image,createdAt
	 */
	private String id=null, user, text, image, createdAt;
	
	public postBean() {
		
	}
	
	/**
	 * postのidを設定します
	 * @param id 設定したいpostのid
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * postのidを取得します
	 * @return postのidを取得します
	 */
	public String getId() {
		if (this.id == null) {
			UUID u1 = UUID.randomUUID();
			this.id = u1.toString();
		}
		
		return this.id;
	}
	
	/**
	 * postのuserのidを設定します
	 * @param user 設定したいuserのid
	 */
	public void setUser(String user) {
		this.user = user;
	}
	
	/**
	 * postのuserのidを取得します
	 * @return postのuserのid
	 */
	public String getUser() {
		return this.user;
	}
	
	/**
	 * postのtextを設定する
	 * @param text 設定したいtext
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * postのtextを取得する
	 * @return postのtext
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * postのimageのidを設定する
	 * @param image 設定したいimageのid
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * postのimageのidを取得する
	 * @return postのimageのid
	 */
	public String getImage() {
		return this.image;
	}
	
	/**
	 * 投稿時間の設定
	 * @param createdAt 設定したい投稿時間
	 */
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	/**
	 * postの投稿時間を取得する
	 * @return postの投稿時間
	 */
	public String getCreatedAt() {
		return this.createdAt;
	}
	
	/**
	 * postをDBのpostテーブルに登録する
	 * @return 登録の成功の可否
	 */
	public boolean insertRecord() {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "INSERT INTO post (id, user, text, image) VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, this.getId());
			ps.setString(2, this.getUser());
			ps.setString(3, this.getText());
			ps.setString(4, this.getImage());
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
	 * 特定のユーザの投稿を取得
	 * @param userId 取得したいユーザのid
	 * @return ユーザの投稿一覧
	 */
	public ArrayList<postBean> getPostRecordsByUserId(String userId) {
		
		ArrayList<postBean> list = new ArrayList<postBean>();
		
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM post WHERE user=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				postBean tmpUB = new postBean();
				tmpUB.setId(rs.getString("id"));
				tmpUB.setUser(rs.getString("user"));
				tmpUB.setText(rs.getString("text"));
				tmpUB.setImage(rs.getString("image"));
				tmpUB.setCreatedAt(rs.getString("created_at"));
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
	 * idの投稿を取得する
	 * @param postId 取得したい投稿のid
	 * @return 投稿一覧
	 */
	public ArrayList<postBean> getPostRecordsById(String postId) {
		ArrayList<postBean> list = new ArrayList<postBean>();
		
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM post WHERE id=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, postId);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				postBean tmpUB = new postBean();
				tmpUB.setId(rs.getString("id"));
				tmpUB.setUser(rs.getString("user"));
				tmpUB.setText(rs.getString("text"));
				tmpUB.setImage(rs.getString("image"));
				tmpUB.setCreatedAt(rs.getString("created_at"));
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
	 * すべての投稿を取得する
	 * @return 投稿一覧
	 */
	public ArrayList<postBean> getAllPostRecords() {
		ArrayList<postBean> list = new ArrayList<postBean>();
		
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQLを実行させる
			String sql = "SELECT * FROM post ORDER BY created_at DESC";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			//結果を格納する
			while( rs.next() ) {
				postBean tmpUB = new postBean();
				tmpUB.setId(rs.getString("id"));
				tmpUB.setUser(rs.getString("user"));
				tmpUB.setText(rs.getString("text"));
				tmpUB.setImage(rs.getString("image"));
				tmpUB.setCreatedAt(rs.getString("created_at"));
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
}
