package state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import db.postBean;
import db.userBean;

/**
 * Servlet implementation class PostServlet
 */
@WebServlet("/PostServlet")
@MultipartConfig(location="/tmp")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher;
		
		//sessionに有効なuserIdが存在するかチェック
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user");
		
		userBean ub = new userBean();
		ArrayList<userBean> list = ub.getUserRecordById(userId);
		 
		if (!list.isEmpty()) {
			//TLに投稿一覧
			postBean pb = new postBean();
			ArrayList<postBean> postList = pb.getAllPostRecords();
			
			dispatcher = request.getRequestDispatcher("post.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("signin.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ユーザ情報を取得する
		//sessionにuserIdが存在するか
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user");
		userBean ub = new userBean();
		ArrayList<userBean> list = ub.getUserRecordById(userId);
		
		if (!list.isEmpty()) {
			try {
				//リクエストされたパラメータを取得する
				request.setCharacterEncoding("utf-8");
				String text = request.getParameter("text");
				Part part = request.getPart("file");
				String name = this.getFileName(part);
				if (!(name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".jpg"))) {
					throw new NullPointerException();
				}
				
				UUID uuid = UUID.randomUUID();
				String fileName = uuid.toString();
				
				userBean user = list.get(0);
				
				//投稿
				postBean pb = new postBean();
				pb.setUser(user.getId());
				pb.setText(text);
				pb.setImage(fileName);
				
				part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + fileName);
				System.out.println(getServletContext().getRealPath("/WEB-INF/uploaded") + "/");
				pb.insertRecord();
			} catch(Exception e) {
				//Error
			}
			
			response.sendRedirect("TimeLineServlet");
		} else {
			response.sendRedirect("signin.html");
		}
	}
	
	private String getFileName(Part part) {
		String name = null;
		for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
			if (dispotion.trim().startsWith("filename")) {
				name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"",  "").trim();
				name = name.substring(name.lastIndexOf("\\")+ 1);
				break;
			}
		}
		return name;
	}

}
