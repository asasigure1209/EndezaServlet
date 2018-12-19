package state;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.postBean;
import db.userBean;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user");
		//一致するuserを取得
		userBean ub = new userBean();
		ArrayList<userBean> list = ub.getUserRecordById(userId);
		
		if (!list.isEmpty()) {
			//投稿一覧を取得
			postBean pb = new postBean();
			ArrayList<postBean> postList = pb.getPostRecordsByUserId(list.get(0).getId());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			//ubとpbをセット
			request.setAttribute("userBean",list.get(0));
			request.setAttribute("postBeanList", postList);
			
			//call JSP
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("TimeLineServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
