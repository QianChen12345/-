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
 * Servlet implementation class goodschoose
 */
@WebServlet("/goodschoose")
public class goodschoose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public goodschoose() {
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

		request.getRequestDispatcher("goodschoose.jsp").forward(request, response);

//		Statement stmt = null;
//		PreparedStatement pstmt = null;

//		try {
//
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false&useUnicode=true", "root", "123456");
//
//			stmt = conn.createStatement();
//
//			stmt.executeUpdate("USE items");
//
//			ResultSet RS_result = stmt.executeQuery("select* from totalgoods ");
//			PrintWriter out = response.getWriter();
//			out.print("[");
//			RS_result.next();
//			while (true) {
//				out.print("{");
//				out.print("itemimage" + ":" + RS_result.getString("itemimage") + "itemid" + ":"
//						+ RS_result.getString("itemid") + "itemname" + ":" + RS_result.getString("itemname")
//						+ "itemdesc" + ":" + RS_result.getString("itemdesc") + "itemprice" + ":"
//						+ RS_result.getString("itemprice"));
//				out.print("}");
//				if (RS_result.next()) {
//					out.print(",");
//				} else {
//					break;
//				}
//			}
//
//			out.print("]");

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
