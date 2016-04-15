<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-refund" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-refund" />
		</h3>
		<nav>
			<table>
				<tr>
					<td width="33%">
						<div class="group">
							<label class="addon" for="authenticode"><s:text name="refund-authenticode" /></label><input class="text" id="authenticode">
						</div>
					</td>
					<td width="33%">
						<div class="group">
							<label class="addon" for="orderNo"><s:text name="refund-orderNo" /></label><input class="text" id="orderNo">
						</div>
					</td>
					<td width="33%">
						<div class="group">
							<label class="addon" for="terminalNo"><s:text name="refund-terminalNo" /></label> <input class="text" id="terminalNo">
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="group">
							<label class="addon" for="reason"><s:text name="refund-reason" /></label> <select class="text" id="reason">
								<option><s:text name="all" /></option>
								<option value="machine"><s:text name="refund-machine" /></option>
								<option value="balance"><s:text name="refund-balance" /></option>
							</select>
						</div>
					</td>
					<td>
						<div class="group">
							<label class="addon" for="type"><s:text name="refund-type" /></label> <select class="text" id="type">
								<option><s:text name="all" /></option>
								<option value="lack"><s:text name="refund-lack" /></option>
								<option value="error"><s:text name="refund-error" /></option>
							</select>
						</div>
					</td>
					<td>
						<div class="group">
							<label class="addon" for="over"><s:text name="refund-over" /></label> <select class="text" id="over">
								<option value=""><s:text name="all" /></option>
								<option value="0"><s:text name="refund-processed" /></option>
								<option value="1"><s:text name="refund-unprocessed" /></option>
							</select>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="inline group">
							<label class="addon" for="begin"><s:text name="refund-begin" /></label> <input class="text" id="begin" readonly>
						</div>
					</td>
					<td>
						<div class="inline group">
							<label class="addon" for="end"><s:text name="refund-end" /></label> <input class="text" id="end" readonly>
						</div>
					</td>
					<td>
						<button class="btn btn-primary btn-small" id="find">
							<s:text name="find" />
						</button>
					</td>
				</tr>
			</table>
		</nav>
	</header>

	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="5%"><s:text name="index" /></th>
					<th width="10%"><s:text name="refund-authenticode" /></th>
					<th width="10%"><s:text name="refund-terminalNo" /></th>
					<th width="10%"><s:text name="refund-orderNo" /></th>
					<th width="10%"><s:text name="refund-reason" /></th>
					<th width="10%"><s:text name="refund-type" /></th>
					<th width="10%"><s:text name="refund-amount" /></th>
					<th width="12%"><s:text name="refund-happentime" /></th>
					<th width="12%"><s:text name="refund-dealtime" /></th>
					<th width="11%"><s:text name="operation" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/business/refund"></script>
</body>
</html>

