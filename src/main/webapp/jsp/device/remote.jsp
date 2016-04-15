<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="js/lib/zhsy/css/progress.css">
<link rel="stylesheet" href="css/module/remote.css">
<title><s:text name="index-remote" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-remote" />
		</h3>
		<nav>
			<div class="inline" id="remote">
				<button class="btn btn-success btn-small" id="correct">
					<s:text name="remote-correct" />
				</button>
				<!--  -->
				<button class="btn btn-primary btn-small" id="boot">
					<s:text name="remote-boot" />
				</button>
				<button class="btn btn-danger btn-small" id="shut">
					<s:text name="remote-shut" />
				</button>
				<!--  -->
				<button class="btn btn-primary btn-small" id="open">
					<s:text name="remote-open" />
				</button>
				<button class="btn btn-danger btn-small" id="close">
					<s:text name="remote-close" />
				</button>
			</div>
			<ul class="inline" id="check-ctrl">
				<li class="inline">
					<button class="btn btn-info btn-small" id="check-all">
						<s:text name="check-all" />
					</button>
				</li>
				<li class="inline">
					<button class="btn btn-info btn-small" id="check-inverse">
						<s:text name="check-inverse" />
					</button>
				</li>
				<li class="inline">
					<button class="btn btn-info btn-small" id="check-cancel">
						<s:text name="check-cancel" />
					</button>
				</li>
			</ul>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="%"><input id="check-parent" type="checkbox"></th>
					<th width="%"><s:text name="remote-terminalNo" /></th>
					<th width="%"><s:text name="remote-location" /></th>
					<th width="%"><s:text name="remote-type" /></th>
					<th width="%"><s:text name="remote-template-manage" /></th>

				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<div id="template">
		<div class="inline dialog-nav">
			<div class="inline group">
				<label class="addon" for="template-name"><s:text name="name" /></label><input id="template-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-template">
					<s:text name="find" />
				</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="30%"><s:text name="remote-template" /></th>
						<th width="30%"><s:text name="remote-template-status" /></th>
						<th width="20%"><s:text name="remote-template-used" /></th>
						<th width="20%"><s:text name="operation" /></th>
					</tr>
				</thead>
				<tbody id="template-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="template-page"></div>
		</div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/device/remote"></script>
</body>
</html>
