package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;

public class profileBean {
	private String id=null, bio, user, image;
	
	public profileBean() {
		
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getBio() {
		return this.bio;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return this.image;
	}
	
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
}
