package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deletegood
 */
@WebServlet("/deletegood")
public class deletegood extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deletegood() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String getgoodid = request.getParameter("goodsid");
		System.out.println(getgoodid);
		if (getgoodid == null) {
		} else {
			PreparedStatement pstmt = null;
			Statement stmt = null;
			ResultSet RS_result=null;
			try {
				PrintWriter out = response.getWriter();
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false&useUnicode=true", "root", "123456");
				
				stmt = conn.createStatement();
				String sql = "DELETE FROM totalgoods WHERE itemid="+ getgoodid;

				stmt.executeUpdate("USE items");
				stmt.executeUpdate(sql);
				out.print("删除成功");
				
				
				

			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (pstmt != null) {
						pstmt.close(); // 关闭 ResultSet 对象
						pstmt = null;
					}
					if (stmt != null) {
						stmt.close(); // 关闭 Statement 对象
						stmt = null;
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
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
