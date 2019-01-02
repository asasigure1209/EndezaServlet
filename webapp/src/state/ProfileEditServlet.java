package state;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import db.profileBean;
import db.userBean;

/**
 * Servlet implementation class ProfileEditServlet
 */
@WebServlet("/ProfileEditServlet")
@MultipartConfig(location="/tmp")
public class ProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String)session.getAttribute("user");
		
		if (userId != null) {
			userBean ub = new userBean();
			ub.getUserById(userId);
			
			if (ub.getId() != null) {
				profileBean pb = new profileBean();
				pb.getProfileByProfileId(ub.getProfile());
				RequestDispatcher dispatcher = request.getRequestDispatcher("profileEdit.jsp");
				request.setAttribute("userBean", ub);
				request.setAttribute("profileBean", pb);
				
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("TimeLineServlet");
			}
		} else {
			response.sendRedirect("TimeLineServlet");
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
						String name = request.getParameter("username");
						String bio = request.getParameter("bio");
						Part part = request.getPart("file");
						String fileName = this.getFileName(part);

						if (!(fileName.endsWith(".png") || fileName.endsWith(".jpeg"))) {
							throw new NullPointerException();
						}
						
						UUID uuid = UUID.randomUUID();
						String fileId = uuid.toString();
						
						userBean user = list.get(0);
						
						//profile変更
						user.setName(name);
						user.updateUserName();
						profileBean pb = new profileBean();
						pb.getProfileByProfileId(user.getProfile());
						pb.setBio(bio);
						pb.setImage(fileId);
						
						part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + fileId);
						pb.updateRecord();
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
