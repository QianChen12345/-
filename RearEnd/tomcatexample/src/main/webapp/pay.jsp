<%@ page language="java" import="java.sql.* "
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
String nowtime =(String)request.getAttribute("nowtime");
String randomnum =(String)request.getAttribute("randomnum");
String prepay_id =(String)request.getAttribute("prepay_id");
String secret =(String)request.getAttribute("secret");
String ordernum =(String)request.getAttribute("ordernum");
System.out.println(nowtime);
System.out.println(randomnum);
System.out.println(prepay_id);
System.out.println(secret);
System.out.println(ordernum);
out.print("[");
%>
{"nowtime" : "<%=nowtime%>" ,"randomnum" : "<%=randomnum%>","prepay_id" : "prepay_id=<%=prepay_id%>"
, "secret" : "<%=secret%>", "ordernum" : "<%=ordernum%>"}
<%out.print("]");%>





