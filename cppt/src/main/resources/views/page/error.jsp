<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<title>Exception</title>
</head>
<body>
	<%
		Exception e = (Exception) request.getAttribute("ex");
	%>
	<H2>
		未知错误:
		<%=e.getClass().getSimpleName()%></H2>
	<hr />
	<P>错误描述：</P>
	<%=e.getMessage()%>
	<P>错误信息：</P>
	<%
		e.printStackTrace(new java.io.PrintWriter(out));
	%>
</body>
</html>
