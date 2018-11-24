package design;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DBSelectServlet
 */
@WebServlet("/DBSelectServlet")
public class DBSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DBSelectServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher;

		//  ユーザ情報を処理する JavaBean をつくる
		studentBean sb = new studentBean();

		//  データベースで SELECT を実行して結果を受け取る
		// 結果は Java のリスト型で返される（studentBean のリストになっている）　
		ArrayList<studentBean> list = sb.getUserRecords();		
		
		// 結果のページをディスパッチする
		 if ( ! list.isEmpty() ) {
		     dispatcher = request.getRequestDispatcher("studentDB-result.jsp");
		 } else {
		     dispatcher = request.getRequestDispatcher("studentDB-failed.jsp");
		 }
		 
		 // 配列型リスト（ArrayList）のインスタンスを request に詰めて
		 // 次に遷移する JSP に渡す
		 // JSP側 ではキーワード "studentBeanList" をつかってインスタンスを取り出す
		 //  二重引用符の中の単語はクラス名である必要はなく、自分で自由に決めてよい
		 request.setAttribute("studentBeanList", list);

		 // 最後に JSP へ処理を遷移させる
		 dispatcher.forward(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
