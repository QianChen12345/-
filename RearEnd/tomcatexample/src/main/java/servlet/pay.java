package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import until.Util;

/**
 * Servlet implementation class pay
 */
@WebServlet("/pay")
public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public pay() {
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
		String getgoodsid = request.getParameter("goodsid");
		String getprice = request.getParameter("price");
		String appid = request.getParameter("appid");
		System.out.println(getaccount);
		System.out.println(getgoodsid);
		System.out.println(getprice);

		SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Util util = new Util();
		
		String nowtime = time.format(new Date()).replaceAll("[[\\s-:punct:]]", "");
		String ordernum = nowtime + util.getnum();
		String randomnum = util.getnum();
		String prepay_id = "wx" + nowtime + util.getnum();
		String secret = util.MD5(appid + util.getnum());
		
		util.connectdata(ordernum, getaccount, getprice, getgoodsid);
		
		
		 request.setAttribute("nowtime",nowtime);
		 request.setAttribute("randomnum",randomnum);
		 request.setAttribute("prepay_id",prepay_id);
		 request.setAttribute("secret",secret);
		 request.setAttribute("ordernum",ordernum);
		request.getRequestDispatcher("pay.jsp").forward(request,response);
		
	}
//		System.out.println(time.format(new Date()).replaceAll("[[\\s-:punct:]]","")+util.getnum());
	

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
