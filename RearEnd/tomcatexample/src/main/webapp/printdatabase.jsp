<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

	<%
	Class.forName("com.mysql.jdbc.Driver");//动态加载mysql驱动

	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false", "root",
			"123456");

	Statement stmt = conn.createStatement();
	stmt.executeUpdate("USE items");

	ResultSet RS_result = stmt.executeQuery("select* from totalgoods ");
	out.print("[");
	RS_result.next();
	while (true) {
	%>
	{"itemimage" : "<%=RS_result.getString("itemimage")%>" ,"itemid" : "<%=RS_result.getInt("itemid")%>" , "itemname" : "<%=RS_result.getString("itemname")%>",
	"itemprice" : "<%=RS_result.getFloat("itemprice")%>" , "itemdesc" : "<%=RS_result.getString("itemdesc")%>","itemaddress" : "<%=RS_result.getString("itemaddress")%>" }
	<%
	if (RS_result.next()) {
	%>,
	<%
	} else {
	break;
	}

	}
	out.print("]");
	%>
