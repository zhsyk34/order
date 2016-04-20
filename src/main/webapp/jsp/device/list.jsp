<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
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
					<th width="15%"><s:text name="list-cash" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>
	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<div id="show">
		<table class="table table-edit table-hover">
			<thead>
				<tr>
					<th width="30%"><s:text name="list-machine" /></th>
					<th width="35%"><s:text name="list-face" /></th>
					<th width="35%"><s:text name="list-count" /></th>
				</tr>
			</thead>
			<tbody>
				<!-- nd100:tw -->
				<tr class="nd100 tw">
					<td><s:text name="list-vomit" /></td>
					<td>100</td>
					<td id="nd100tw100"></td>
				</tr>
				<!-- nd100:cn -->
				<tr class="nd100 cn">
					<td><s:text name="list-vomit" /></td>
					<td>10</td>
					<td id="nd100cn10"></td>
				</tr>
				<!-- nv9:tw -->
				<tr class="nv9 tw">
					<td rowspan="3"><s:text name="list-swallow" /></td>
					<td>100</td>
					<td id="nv9tw100"></td>
				</tr>
				<tr class="nv9 tw">
					<td>500</td>
					<td id="nv9tw500"></td>
				</tr>
				<tr class="nv9 tw">
					<td>1000</td>
					<td id="nv9tw1000"></td>
				</tr>
				<!-- nv9:cn -->
				<tr class="nv9 cn">
					<td rowspan="6"><s:text name="list-swallow" /></td>
					<td>1</td>
					<td id="nv9cn1"></td>
				</tr>
				<tr class="nv9 cn">
					<td>5</td>
					<td id="nv9cn5"></td>
				</tr>
				<tr class="nv9 cn">
					<td>10</td>
					<td id="nv9cn10"></td>
				</tr>
				<tr class="nv9 cn">
					<td>20</td>
					<td id="nv9cn20"></td>
				</tr>
				<tr class="nv9 cn">
					<td>50</td>
					<td id="nv9cn50"></td>
				</tr>
				<tr class="nv9 cn">
					<td>100</td>
					<td id="nv9cn100"></td>
				</tr>
				<!-- hopper:tw -->
				<tr class="hopper tw">
					<td rowspan="4"><s:text name="list-coin" /></td>
					<td>1</td>
					<td id="hoppertw1"></td>
				</tr>
				<tr class="hopper tw">
					<td>5</td>
					<td id="hoppertw5"></td>
				</tr>
				<tr class="hopper tw">
					<td>10</td>
					<td id="hoppertw10"></td>
				</tr>
				<tr class="hopper tw">
					<td>50</td>
					<td id="hoppertw50"></td>
				</tr>
				<!-- hopper:cn -->
				<tr class="hopper cn">
					<td rowspan="3"><s:text name="list-coin" /></td>
					<td>0.1</td>
					<td id="hoppercn01"></td>
				</tr>
				<tr class="hopper cn">
					<td>0.5</td>
					<td id="hoppercn05"></td>
				</tr>
				<tr class="hopper cn">
					<td>1</td>
					<td id="hoppercn1"></td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/device/list"></script>
</body>
</html>
