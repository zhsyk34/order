<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()	+ request.getContextPath() + "/";
	String dialect = request.getLocale().toString();
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="<%=basePath%>" />
<script src="js/lib/require.config.js"></script>
<script>
	var basePath = "<%=basePath%>";
	var sessionstatus = "<%=session.getAttribute("user")%>";
</script>
<link rel="stylesheet" href="css/lib/init.css">
</head>