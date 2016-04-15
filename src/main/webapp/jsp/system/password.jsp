<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-password" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-password" />
		</h3>
	</header>

	<div class="main">
		<table class="table table-hover table-edit">
			<thead>
				<tr>
					<th width="20%"><s:text name="password-name" /><input type="hidden" id="id" value="<s:property value='%{#session.user.id}' />"></th>
					<td width="80%"><s:property value="%{#session.user.name}" /></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><s:text name="password-original" /></th>
					<td><input class="text" type="password" id="original"></td>
				</tr>
				<tr>
					<th><s:text name="password-password" /></th>
					<td><input class="text" type="password" id="password"></td>
				</tr>
				<tr>
					<th><s:text name="password-confirm" /></th>
					<td><input class="text" type="password" id=confirm></td>
				</tr>
			</tbody>
		</table>
	</div>

	<footer>
		<button class="btn btn-success" id="sure">
			<s:text name="sure" />
		</button>
		<button class="btn btn-info" id="reset">
			<s:text name="reset" />
		</button>
	</footer>

	<script src="js/lib/require.js" data-main="module/system/password"></script>
</body>
</html>

