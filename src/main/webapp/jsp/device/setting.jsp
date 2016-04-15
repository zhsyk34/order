<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/remote.css">
<title><s:text name="index-setting" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-setting" />
		</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%"><s:text name="setting-terminalNo" /></th>
					<th width="10%"><s:text name="setting-invoice" /></th>
					<th width="10%"><s:text name="setting-teamViewer" /></th>
					<th width="20%"><s:text name="setting-boots" /></th>
					<th width="20%"><s:text name="setting-shuts" /></th>
					<th width="10%"><s:text name="setting-shut" /></th>
					<th width="10%"><s:text name="edit" /></th>
					<th width="10%"><s:text name="setting-seat" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<!-- seats -->
	<div id="seat">
		<table class="table table-edit table-hover">
			<tr>
				<th width="15%"><s:text name="setting-terminalNo" /></th>
				<td width="85%"><span id="seat-terminal"></span> <label><input type="checkbox" id="seat-parent"> <s:text name="all" /></label></td>
			</tr>
			<tr>
				<th><s:text name="setting-seat" /></th>
				<td><ul></ul></td>
			</tr>
		</table>
	</div>

	<div id="editor">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="20%"><s:text name="setting-terminalNo" /><input id="id" type="hidden"></th>
					<td width="80%"><span id="terminalNo"></span></td>
				</tr>
				<tr>
					<th><s:text name="setting-invoice" /></th>
					<td>
						<div id="invoice">
							<label><input type="radio" name="invoice" value="0"> <s:text name="no" /></label> <label><input type="radio" name="invoice" value="1"> <s:text name="yes" /></label>
						</div>
					</td>
				</tr>
				<tr>
					<th><s:text name="setting-teamViewer" /></th>
					<td><input class="text" id="teamViewer"></td>
				</tr>
				<tr>
					<th><s:text name="setting-times" /></th>
					<td>
						<table class="table times">
							<thead>
								<tr>
									<th width="30%"><s:text name="index" /></th>
									<th width="35%"><s:text name="setting-boots" /></th>
									<th width="35%"><s:text name="setting-shuts" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:text name="setting-timeone" /></td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
								<tr>
									<td><s:text name="setting-timetwo" /></td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
								<tr>
									<td><s:text name="setting-timethree" /></td>
									<td><input class="text boots" readonly></td>
									<td><input class="text shuts" readonly></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<th><s:text name="setting-shut" /></th>
					<td>
						<div id="shut">
							<label><input type="radio" name="shut" value="0"> <s:text name="no" /></label> <label><input type="radio" name="shut" value="1"> <s:text name="yes" /></label>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/device/setting"></script>
</body>
</html>
