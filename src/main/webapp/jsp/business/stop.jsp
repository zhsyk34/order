<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-stop" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/activity.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-stop" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-kitchen"><s:text name="activity-kitchen" /></label><input id="search-kitchen" class="text">
				</div>
				<div class="inline group">
					<label class="addon" for="search-food"><s:text name="activity-food" /></label><input id="search-food" class="text">
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="activity-status" /></label>
					<div class="text" id="search-used">
						<label><input type="radio" name="search-used" value="" checked> <s:text name="all" /></label><label><input type="radio" name="search-used" value="0"> <s:text name="activity-notdone" /></label><label><input type="radio" name="search-used" value="1"> <s:text name="activity-already" /></label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">
						<s:text name="find" />
					</button>
				</div>
			</div>
			<div>
				<ul class="inline">
					<li class="inline">
						<button class="btn btn-success btn-small" id="used-all">
							<s:text name="activity-used-all" />
						</button>
					</li>
					<li class="inline">
						<button class="btn btn-warning btn-small" id="unused-all">
							<s:text name="activity-unused-all" />
						</button>
					</li>
					<li class="inline">
						<button class="btn btn-primary btn-small" id="update-all">
							<s:text name="activity-setup-all" />
						</button>
					</li>
					<li class="inline">
						<button class="btn btn-danger btn-small" id="revoke-all">
							<s:text name="activity-revoke-all" />
						</button>
					</li>
				</ul>
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
			</div>
		</nav>
	</header>

	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="5%"><input id="check-parent" type="checkbox"></th>
					<th width="5%"><s:text name="index" /></th>
					<th width="10%"><s:text name="activity-kitchen" /></th>
					<th width="10%"><s:text name="activity-location" /></th>
					<th width="10%"><s:text name="activity-food" /></th>
					<th width="20%"><s:text name="activity-time" /></th>
					<th width="10%"><s:text name="activity-count" /></th>
					<th width="10%"><s:text name="activity-used" /></th>
					<th width="10%"><s:text name="setup" /></th>
					<th width="10%"><s:text name="revoke" /></th>
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
				<th><s:text name="activity-stop-pattern" /></th>
				<td id="pattern"><label><input type="radio" name="pattern" value="date" checked> <s:text name="activity-date" /></label> <label><input type="radio" name="pattern" value="count"> <s:text name="activity-count" /></label></td>
			</tr>
			<tr class="pattern-date">
				<th><s:text name="activity-begin" /></th>
				<td><input class="text" id="begin"></td>
			</tr>
			<tr class="pattern-date">
				<th><s:text name="activity-end" /></th>
				<td><input class="text" id="end"></td>
			</tr>
			<tr class="pattern-count">
				<th><s:text name="activity-stop-count" /></th>
				<td><input class="text" id="count"></td>
			</tr>
			<tr>
				<th><s:text name="activity-isused" /></th>
				<td id="used"><label><input type="radio" name="used" value="1" checked> <s:text name="yes" /></label> <label><input type="radio" name="used" value="0"> <s:text name="no" /></label></td>
			</tr>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/business/activity"></script>
</body>
</html>