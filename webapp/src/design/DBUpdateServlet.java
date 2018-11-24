package design;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBUpdateServlet
 */
@WebServlet("/DBUpdateServlet")
public class DBUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;

		//  ユーザ情報を処理する JavaBean をつくる
		userBean sb = new userBean();
		
		// 文字コードを UTF-8 として扱う
		request.setCharacterEncoding("UTF-8");
		
		 // フォームの入力データを文字列としてサーブレットで受け取る
		 //  ※日本語入力の場合だけ、文字コードをUTF-8 に変換する
		 //   （送られてきたパラメータが ISO8859-1 で一旦デコードされているので元に戻す）
		 String id = request.getParameter("id");
		 id = new String( id.getBytes("ISO8859-1"), "UTF-8");
		 String name = request.getParameter("name");
		 name = new String( name.getBytes("ISO8859-1"), "UTF-8");
		 String email = request.getParameter("email");
		 email = new String( email.getBytes("ISO8859-1"), "UTF-8");
		 String profile = null;
		
		 // セッターメソッドで、ブラウザから得たデータを JavaBean インスタンスにセットする
		 //   （引数にあたえると同時に型変換も行っている）
		 sb.setId(id);
		 sb.setName(name);
		 sb.setEmail(email);
		 sb.setProfile(profile);

		 //  データベースへの INSERT 処理の実行
		 if ( sb.insertRecord() ) {
		     dispatcher = request.getRequestDispatcher("userDB-success.jsp");
		 } else {
		     dispatcher = request.getRequestDispatcher("userDB-failed.jsp");
		 }
		 
		 // studentBean のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBean" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("userBean", sb);

		 // 最後に JSP へ処理を遷移させる
		 dispatcher.forward(request,response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
