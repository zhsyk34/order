<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title><s:text name="index-monitor" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-monitor" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="terminalNo"><s:text name="monitor-terminalNo" /></label><input class="text" id="terminalNo">
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="monitor-status" /></label>
					<div class="text" id="online">
						<label><input type="radio" name="online" value="" checked> <s:text name="all" /></label> <label><input type="radio" name="online" value="true"> <s:text name="monitor-online" /></label> <label><input type="radio" name="online" value="false"> <s:text name="monitor-offline" /></label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">
						<s:text name="find" />
					</button>
				</div>
			</div>
		</nav>
	</header>
	<!-- data -->
	<div class="main">
		<div id="data"></div>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/monitor"></script>
</body>
</html>
