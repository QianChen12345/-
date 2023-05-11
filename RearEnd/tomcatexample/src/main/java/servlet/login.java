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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String getaccount = request.getParameter("account");
		String getpassword = request.getParameter("password");
		if (getaccount == null) {
		} else {
			getaccount = java.net.URLDecoder.decode(getaccount, "UTF-8");
			System.out.println(getaccount);
			System.out.println(getpassword);
		}
		PreparedStatement pstmt = null;
		Statement stmt = null;

		try {
			PrintWriter out = response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false&useUnicode=true", "root", "123456");

			stmt = conn.createStatement();
			String sql = "SELECT COUNT(*) FROM USER WHERE account=" + getaccount + "&&" + "password=" + getpassword;

			stmt.executeUpdate("USE items");
			ResultSet i = stmt.executeQuery(sql);
			i.next();
			System.out.println(i.getInt("count(*)"));

			if (i.getInt("count(*)") == 1) {

				out.print("登录成功");
			} else {
				out.print("登录失败");
			}

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
