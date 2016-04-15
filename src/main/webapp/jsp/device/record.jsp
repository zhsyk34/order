<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/monitor.css">
<title><s:text name="index-record" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-record" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="begin"><s:text name="record-begin" /></label> <input class="text" id="begin" readonly>
				</div>
				<div class="inline group">
					<label class="addon" for="end"><s:text name="record-end" /></label> <input class="text" id="end" readonly>
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon" for="terminalNo"><s:text name="record-terminalNo" /></label><input class="text" id="terminalNo">
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="record-status" /></label>
					<div class="text" id="online">
						<label><input type="radio" name="online" value="" checked> <s:text name="all" /></label> <label><input type="radio" name="online" value="true"> <s:text name="record-online" /></label> <label><input type="radio" name="online" value="false"> <s:text name="record-offline" /></label>
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
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%"><s:text name="record-terminalNo" /></th>
					<th width="20%"><s:text name="record-location" /></th>
					<th width="20%"><s:text name="record-host" /></th>
					<th width="20%"><s:text name="record-time" /></th>
					<th width="20%"><s:text name="record-status" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/record"></script>
</body>
</html>
