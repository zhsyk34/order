<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-style" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-style" />
		</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-name"><s:text name="name" /></label><input id="search-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">
					<s:text name="find" />
				</button>
			</div>
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
					<th width="15%"><s:text name="index" /></th>
					<th width="35%"><s:text name="name" /></th>
					<th width="40%"><s:text name="edit" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<footer>
		<div id="page"></div>
	</footer>

	<div id="editor">
		<table class="table table-hover table-edit">
			<tr>
				<th><s:text name="name" /><input type="hidden" id="id"></th>
				<td><input class="text" id="name"></td>
			</tr>
		</table>
	</div>
	
	<script src="js/lib/require.js" data-main="module/base/style"></script>
</body>
</html>

