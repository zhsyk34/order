<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title><s:text name="index-list" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-list" />
		</h3>
	</header>

	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%"><s:text name="list-terminalNo" /></th>
					<th width="20%"><s:text name="list-location" /></th>
					<th width="25%"><s:text name="list-time" /></th>
					<th width="20%"><s:text name="list-status" /></th>
					<!-- <th width="15%">终端画面</th> -->
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/list"></script>
</body>
</html>
