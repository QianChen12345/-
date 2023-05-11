<%@ page language="java" import="java.sql.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String getid = request.getParameter("goodsid");
System.out.println(getid);
Class.forName("com.mysql.jdbc.Driver");//动态加载mysql驱动
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/?serverTimezone=UTC&useSSL=false", "root",
		"123456");
Statement stmt = conn.createStatement();
stmt.executeUpdate("USE items");
String sql = "SELECT COUNT(*) FROM totalgoods WHERE itemid=" + getid;
ResultSet i = stmt.executeQuery(sql);
i.next();
System.out.println(i.getInt("count(*)"));
if (getid == null || getid.equals("undefined") || i.getInt("count(*)") == 0) {
	out.print("no");
} else {
	sql = "SELECT* FROM totalgoods WHERE itemid=" + getid;
	i = stmt.executeQuery(sql);

	i.next();
	System.out.println(i.getString("itemid"));
	out.print("[");
	while (true) {
%>
{"itemimage" : "<%=i.getString("itemimage")%>" ,"itemtype" : "<%=i.getString("itemtype")%>","itemid" : "<%=i.getString("itemid")%>"
, "itemname" : "<%=i.getString("itemname")%>", "itemprice" : "<%=i.getFloat("itemprice")%>"
, "itemdesc" : "<%=i.getString("itemdesc")%>","itemaddress" : "<%=i.getString("itemaddress")%>" }
<%
if (i.next()) {
%>,

<%
} else {
break;
}

}
out.print("]");
%>

<%
}
%>






