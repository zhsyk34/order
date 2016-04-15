<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/order.css">
<title><s:text name="index-sell" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-sell" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="shop"><s:text name="sell-shop" /></label> <input class="text" id="shop">
				</div>
				<div class="inline group">
					<label class="addon" for="kitchen"><s:text name="sell-kitchen" /></label> <input class="text" id="kitchen">
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">
						<s:text name="find" />
					</button>
				</div>
				<div class="inline">
					<!-- <button class="btn btn-success btn-small" id="export">导出</button> -->
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon" for="begin"><s:text name="sell-begin" /></label> <input class="text" id="begin" readonly>
				</div>
				<div class="inline group">
					<label class="addon" for="end"><s:text name="sell-end" /></label> <input class="text" id="end" readonly>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="yesterday">
						<s:text name="sell-yesterday" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="today">
						<s:text name="sell-today" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-info btn-small" id="preweek">
						<s:text name="sell-preweek" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-info btn-small" id="week">
						<s:text name="sell-week" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-warning btn-small" id="premonth">
						<s:text name="sell-premonth" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-warning btn-small" id="month">
						<s:text name="sell-month" />
					</button>
				</div>
			</div>
		</nav>
	</header>

	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%" rowspan="2"><s:text name="sell-shop" /></th>
					<th width="10%" rowspan="2"><s:text name="sell-kitchen" /></th>
					<th width="50%" colspan="4"><s:text name="sell-content" /></th>
					<th width="15%" rowspan="2"><s:text name="sell-amount" /></th>
					<!-- <th width="15%" rowspan="2"><s:text name="view" /></th> -->
				</tr>
				<tr>
					<th width="10%"><s:text name="index" /></th>
					<th width="20%"><s:text name="sell-food" /></th>
					<th width="10%"><s:text name="sell-count" /></th>
					<th width="10%"><s:text name="sell-total" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/business/sell"></script>
</body>
</html>
