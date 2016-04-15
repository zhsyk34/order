<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<title><s:text name="index-terminal" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-terminal" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-terminalNo"><s:text name="terminal-number" /></label><input class="text" id="search-terminalNo">
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="terminal-type" /></label>
					<div class="text" id="search-type">
						<label><input type="radio" name="search-type" value="" checked> <s:text name="all" /></label> <label><input type="radio" name="search-type" value="shop"> <s:text name="terminal-shop" /></label> <label><input type="radio" name="search-type" value="kitchen"> <s:text name="terminal-kitchen" /></label>
					</div>
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
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="20%"><s:text name="terminal-number" /></th>
					<th width="15%"><s:text name="terminal-type" /></th>
					<th width="20%"><s:text name="terminal-location" /></th>
					<th width="15%"><s:text name="terminal-version" /></th>
					<!-- <th width="30%">编辑</th> -->
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<div id="editor">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="25%"><s:text name="terminal-number" /></th>
					<td width="75%"><input id="id" type="hidden"><input class="text" id="terminalNo"></td>
				</tr>
				<tr>
					<th><s:text name="terminal-type" /></th>
					<td>
						<div id="type">
							<label><input type="radio" name="type" value="shop"> <s:text name="terminal-shop" /></label> <label><input type="radio" name="type" value="kitchen"> <s:text name="terminal-kitchen" /></label>
						</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="terminal-location" /></th>
					<td><input class="text" id="location"></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/device/terminal"></script>
</body>
</html>
