package servlet;

import java.io.File;



import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import daoimplement.UserDaoimplement;
import items.Item;
import java.net.URLDecoder.*;

/**
 * Servlet implementation class Wxappconn
 * 
 */
//濠电偞鍨堕幐鎼侇敄閸℃稑钃熼柣鏂挎憸閳绘棃鏌ｉ幋鐐嗘垿寮抽崼銉﹀仭婵炲棙鐟х粔鍓佺磼鏉堛劎绠栫紒鍌涘浮閺佹劙宕卞Ο纰辨捶xml闂備礁鎼�氼喗鎱ㄩ幘顔藉剭闁绘顕уΛ姗�鏌涢妷顖炴妞ゆ劧鎷�
@WebServlet("/Wxappconn")
public class Wxappconn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private UserDaoimplement userDaoimplement = new UserDaoimplement();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Wxappconn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

//返回图片路径
//		DiskFileItemFactory factory1 = new DiskFileItemFactory();
//		// 获取文件需要上传到的路径
//		String path = request.getRealPath("/upload");
//		File file = new File(path);
//		if (!file.exists()) {
//			file.mkdirs();
//		}
//		factory1.setRepository(new File(path));
//		// 设置 缓存的大小
//		factory1.setSizeThreshold(1024 * 1024);
//		// 文件上传处理
//		ServletFileUpload upload1 = new ServletFileUpload(factory1);
//		try {
//			// 可以上传多个文件
//			List<FileItem> list = (List<FileItem>) upload1.parseRequest(new ServletRequestContext(request));
//			for (FileItem item : list) {
//				// 获取属性名字
//				String name = item.getFieldName();
//				// 如果获取的 表单信息是普通的 文本 信息
//				if (item.isFormField()) {
//					// 获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的
//					String value = item.getString();
//					request.setAttribute(name, value);
//				} else {
//					// 获取路径名
//					String value = item.getName();
//					// 索引到最后一个反斜杠
//					int start = value.lastIndexOf("\\");
//					// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
//					String filename = value.substring(start + 1);
//					request.setAttribute(name, filename);
//					// 写到磁盘上
//					item.write(new File(path, filename));// 第三方提供的
//					System.out.println("上传成功：" + filename);
//					response.getWriter().print(filename);// 将路径返回给客户端
//				}
//			}
//
//		} catch (Exception e) {
//			System.out.println("上传失败");
//			response.getWriter().print("上传失败：" + e.getMessage());
//		}

		String getname = request.getParameter("goodsname");
		String gettype = request.getParameter("defaulttype");
		String getdesc = request.getParameter("goodsdesc");
		String getId = request.getParameter("goodsId");
		String getprice = request.getParameter("goodsprice");
		String getimage = request.getParameter("goodsimage");
		String getaddress = request.getParameter("address");
		// String getname = new
		// String(request.getParameter("goodsname").getBytes("gbk"), "UTF-8");
		// String getdesc = new
		// String(request.getParameter("goodsdesc").getBytes("gbk"), "UTF-8");
		if (getname == null) {
		} else {
			getname = java.net.URLDecoder.decode(getname, "UTF-8");
			getdesc = java.net.URLDecoder.decode(getdesc, "UTF-8");
			gettype = java.net.URLDecoder.decode(gettype, "UTF-8");
			getaddress=java.net.URLDecoder.decode(getaddress, "UTF-8");
			System.out.println(getname);
			System.out.println(getprice);
			System.out.println(getdesc);
			System.out.println(gettype);
			System.out.println(getimage);

			// String str = new String(getname.getBytes("ISO8859_1"), "gbk");

			// System.out.println(System.getProperty("file.encoding"));//鏌ョ湅缂栫爜鏍煎紡

			Statement stmt = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false&useUnicode=true", "root", "123456");
				/*
				 * 闂佽崵濮崇粈浣割焽閳ユ緞娑㈠醇閺囩偞鐎梺缁橆殔閻楀棛绮婇敃鍌氱閻庢稒蓱閹牏绱掓潏銊х疄妤犵偛绉烽ˇ鑸点亜椤喗瀚� SQL
				 * 闂佽崵濮村ù鍌炲储閼硅埇浜归柨鐕傛嫹
				 */
				stmt = conn.createStatement();
				// stmt.executeUpdate("CREATE DATABASE student"); //
				// 闂備礁鎲＄敮妤冪矙閹寸姷纾介柟鎹愵嚙閺嬩線鏌ｅΔ锟介悧鍡欑矈閿曞倸绠归弶鍫㈠亾鐎氾拷
				stmt.executeUpdate("USE items"); // 濠电偠鎻紞鍥箯閿燂拷 items
													// 闂備胶鎳撻悺銊╁礉閹烘梹瀚婚柣鏃�鐪规禍褰掓煙閹冩毐妞ゃ倕鎳橀弻鈩冩媴閸濆嫷鏆悗瑙勬尫閻掞箓骞嗛弮鍫熸櫢闁跨噦鎷�

				pstmt = conn.prepareStatement("INSERT INTO totalgoods VALUES(?,?,?,?,?,?,?)");
				pstmt.setString(1, getimage);
				pstmt.setString(2, gettype);
				pstmt.setString(3, getId);
				pstmt.setString(4, getname);
				pstmt.setString(5, getprice);
				pstmt.setString(6, getdesc);
				pstmt.setString(7, getaddress);
				pstmt.executeUpdate();

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

	// userDaoimplement.saveDao(new Item(getname,getprice,getdesc));

	// PrintWriter writer = response.getWriter();

	// writer.print("Hello Servlet");
	// response.getWriter().append("Served at: ").append(request.getContextPath());

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
