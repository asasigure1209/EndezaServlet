package design;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class userBean {

	private String id, email, name, profile;
	
	public userBean() {
		;
	}
	
	// setter and getter
	
	//userId
	public void setId(String i) {
		id = i;
	}
	
	public String getId() {
		return id;
	}
	
	public void setEmail(String e) {
		email = e;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public void setProfile(String p) {
		profile = p;
	}
	
	public String getProfile() {
		return profile;
	}
	
	//DBへの追加
	public boolean insertRecord() {
		try {
			//DBのコネクションを取得
			Connection con = DBManager.getUserConnection();
			
			//間接的にSQL文を実行させる
			String sql = "INSERT INTO user (id, email, name, profile) VALUES(?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, email);
			ps.setString(3, name);
			ps.setString(4, profile);
			//sqlを実行する
			int count = ps.executeUpdate();
			//conectionを閉じる
			ps.close();
			con.close();
			//正しく実行されたかどうか確認する
			if (count > 0)
				return true;
			else
				return false;
		} catch (Exception e) {
			return false;
		}
	}
}
