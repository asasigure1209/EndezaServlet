package design;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBDeleteServlet
 */
@WebServlet("/DBDeleteServlet")
public class DBDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBDeleteServlet() {
        super();
     }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;

		//  ユーザ情報を処理する JavaBean をつくる
		studentBean sb = new studentBean();
		
		 // フォームの入力データを文字列としてサーブレットで受け取る
		 String id = request.getParameter("id");
		
		 // セッターメソッドでデータを JavaBean に入れる
		 //   （引数にあたえるときに型変換も行っている）
		 sb.setId( Integer.parseInt(id) );

		 //  データベースからの削除の実行
		 if ( sb.deleteRecord() ) {
		     dispatcher = request.getRequestDispatcher("studentDB-success.jsp");
		 } else {
		     dispatcher = request.getRequestDispatcher("studentDB-failed.jsp");
		 }
		 
		 // studentBean のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBean" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("studentBean", sb);

		 // 最後に JSP へ処理を遷移させる
		 dispatcher.forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
