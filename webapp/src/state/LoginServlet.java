package state;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import db.userBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher;
		
		//ユーザー情報を処理するJavaBeanをつくる
		userBean ub = new userBean();
		//リクエストされたパラメーターを取得する
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//emailが一致するユーザを取得する
		ArrayList<userBean> list = ub.getUserRecordByEmail(email, password);
		
		//結果のページをディスパッチする
		if (!list.isEmpty()) {
			dispatcher = request.getRequestDispatcher("home.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("signin.html");
		}
		
		//ubのリストをセット
		request.setAttribute("userBeanList", list);
		//JSPの呼び出し
		dispatcher.forward(request, response);
	}

}
