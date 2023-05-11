<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
Class.forName("com.mysql.jdbc.Driver");//动态加载mysql驱动

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false", "root",
		"123456");

Statement stmt = conn.createStatement();
stmt.executeUpdate("USE items");
//System.out.println(session.getAttribute("getactive"));
ResultSet RS_result = stmt.executeQuery("select* from totalgoods WHERE itemtype='电子产品'");
String value = request.getParameter("active");
System.out.println(value);
if ("0".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='电子产品'");

} else if ("1".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='书籍'");

} else if ("2".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='手机'");

} else if ("3".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='化妆品'");

} else if ("4".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='鞋子'");

} else if ("5".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='文具'");

} else if ("6".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='生活用品'");

} else if ("7".equals(value)) {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='首饰'");

}

else if ("8".equals(value)){
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='衣物'");

}
else {
	RS_result = stmt.executeQuery("select* from totalgoods  WHERE itemtype='其他'");

}

out.print("[");
RS_result.next();
while (true) {
%>
{"itemimage" : "<%=RS_result.getString("itemimage")%>" ,"itemid" : "<%=RS_result.getString("itemid")%>"
, "itemname" : "<%=RS_result.getString("itemname")%>", "itemprice" : "<%=RS_result.getFloat("itemprice")%>"
, "itemdesc" : "<%=RS_result.getString("itemdesc")%>"}
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
