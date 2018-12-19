package img;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageFileServlet
 */
@WebServlet("/ImageFileServlet")
public class ImageFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dir = getServletContext().getRealPath("/WEB-INF/uploaded");
		String fname = request.getParameter("name");
		
		int iData = 0;
		
		//ServletのOutputStream取得
		ServletOutputStream out = response.getOutputStream();
		
		//画像ファイルをBufferedInputStreamを使用して読み取る
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(dir + "/" + fname));
		
		while((iData = in.read()) != -1) {
			out.write(iData);
		}
		
		in.close();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
