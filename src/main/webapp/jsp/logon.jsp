<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/logon.css">
<title><s:text name="logon-title" /></title>
</head>
<body>
	<div id="container">
		<form>
			<input id="name" placeholder="<s:text name='logon-name' />"> <input id="password" type="password" placeholder="<s:text name='logon-password' />">
			<button id="logon" class="btn btn-primary" type="button">
				<s:text name="logon-submit" />
			</button>
		</form>
	</div>
	<script src="js/lib/require.js" data-main="module/logon"></script>
</body>
</html>

