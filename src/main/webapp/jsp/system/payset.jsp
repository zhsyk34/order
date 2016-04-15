<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-payset" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-payset" />
		</h3>
	</header>

	<!-- data -->

	<div class="main">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="16%"><s:text name="payset-pattern" /></th>
					<td><select class="text" id="name">
							<option value="wechat" selected><s:text name="payset-wechat" /></option>
							<option value="alipay"><s:text name="payset-alipay" /></option>
					</select></td>
				</tr>
				<tr>
					<th><s:text name="payset-title" /><input id="id" type="hidden"></th>
					<td><input class="text" id="title"></td>
				</tr>
				<tr>
					<th>APPID</th>
					<td><input class="text" id="appId"></td>
				</tr>
				<tr>
					<th>PARTNER</th>
					<td><input class="text" id="partner"></td>
				</tr>
				<tr>
					<th>Private Key</th>
					<td><textarea class="text" id="privateKey" cols="100" rows="8"></textarea></td>
				</tr>
				<tr class="publicKey">
					<th>Public Key</th>
					<td><textarea class="text" id="publicKey" cols="100" rows="3"></textarea></td>
				</tr>
			</tbody>
		</table>

		<footer>
			<button class="btn btn-success" id="sure">
				<s:text name="sure" />
			</button>
			<button class="btn btn-info" id="reset">
				<s:text name="reset" />
			</button>
		</footer>
	</div>

	<script src="js/lib/require.js" data-main="module/system/payset"></script>
</body>
</html>

