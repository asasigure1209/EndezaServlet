package state;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.userBean;
import db.postBean;

/**
 * Servlet implementation class TimeLineServlet
 */
@WebServlet("/TimeLineServlet")
public class TimeLineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeLineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		
		//sessionに有効なuserIdが存在するかチェック
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user");
		System.out.println((String)session.getAttribute("user"));
		
		userBean ub = new userBean();
		ArrayList<userBean> list = ub.getUserRecordById(userId);
		 
		if (!list.isEmpty()) {
			//TLに投稿一覧
			postBean pb = new postBean();
			ArrayList<postBean> postList = pb.getAllPostRecords();
			
			dispatcher = request.getRequestDispatcher("timeLine.jsp");
			//ubのリストをセット
			request.setAttribute("userBeanList", list);
			request.setAttribute("postBeanList", postList);
			//call JSP
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("signin.html");
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
