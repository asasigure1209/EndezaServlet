// データベース管理用のクラス
// 
// 新規→Javaクラス、で、このようなデータベースアクセス専用の
// クラスを作成します。このクラスは JavaBean から呼び出されて
// 実行されます
//

package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBManager {

	// 重要： 以下の部分を各自の MySQL の設定にあわせて変更する
	// 以下は仮想マシンの MySQL データベースの設定である
	//private static String url = "jdbc:mysql://localhost:3306/db_dev";
	//private static String user = "dev";
	//private static String password = "endeza";
	
	// 本番DB
		private static String url = "jdbc:mysql://192.168.102:3306/db_minami";
		private static String user = "minami";
		private static String password = "south1209";
	
	// サーバマシンの MySQL データベースに切り替える場合には以下のようにする
	// taro は自分のユーザ名にする。班の作品のデータベースを使う場合は taro を tomcat に変更する
	// private static String url = "jdbc:mysql://自分のサーバのIPアドレス:3306/db_taro";
	// private static String user = "taro";
	// private static String password = "自分のパスワード";

	//  データベースへのコネクションを作成して返すメソッド
	public static Connection getUserConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
	
}
