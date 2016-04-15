<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-rule" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/rule.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-rule" />
		</h3>
		<nav>
			<div class="inline">
				<button class="btn btn-success btn-small" id="add">
					<s:text name="create" />
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
				<li class="inline">
					<button class="btn btn-danger btn-small" id="del-all">
						<s:text name="delete-all" />
					</button>
				</li>
			</ul>
		</nav>
	</header>

	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%"><input id="check-parent" type="checkbox"></th>
					<th width="10%"><s:text name="index" /></th>
					<th width="40%"><s:text name="rule-encode" /></th>
					<th width="10%"><s:text name="rule-status" /></th>
					<th width="30%"><s:text name="edit" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<div id="editor">
		<table class="table table-hover table-edit">
			<tr>
				<th><s:text name="rule-prefix" /><input type="hidden" id="id"></th>
				<td><input class="text" id="prefix"></td>
			</tr>
			<tr>
				<th><s:text name="rule-length" /></th>
				<td><input class="text" id="length"></td>
			</tr>
			<tr>
				<th><s:text name="rule-start" /></th>
				<td><input class="text" id="start"></td>
			</tr>
		</table>
	</div>

	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/business/rule"></script>
</body>
</html>

