package state;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.profileBean;
import db.userBean;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		//ユーザー情報を処理するJavaBeanを作る
		userBean ub = new userBean();
		profileBean pb = new profileBean();
		//リクエストされたパラメータ取得
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		//userを登録
		ub.setEmail(email);
		ub.setName(name);
		ub.setPassword(password);
		pb.setUser(ub.getId());
		
		if (ub.setUserRecord() && pb.setProfileRecord() && bindProfile(ub, pb)) {
			HttpSession session = request.getSession();
			session.setAttribute("user", ub.getId());
			
			response.sendRedirect("welcome.html");
		} else {
			response.sendRedirect("signup.html");
		}
	}

	private boolean bindProfile(userBean ub, profileBean pb)
	{
		ub.setProfile(pb.getId());
		return ub.updateProfile();
	}
}
