package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class postBean {

	private String id=null, user, text, image, createdAt;
	
	public postBean() {
		
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		if (this.id == null) {
			UUID u1 = UUID.randomUUID();
			this.id = u1.toString();
		}
		
		return this.id;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
	public String getCreatedAt() {
		return this.createdAt;
	}
	
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
