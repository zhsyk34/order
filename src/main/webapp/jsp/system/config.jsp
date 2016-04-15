<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-config" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-config" />
		</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-edit table-hover">
			<tbody>
				<tr>
					<th width="16%"><s:text name="config-take" /></th>
					<td><label><input type="radio" name="takeShow" value="0"> <s:text name="config-hide" /></label> <label><input type="radio" name="takeShow" value="1"> <s:text name="config-show" /></label> <select class="text" id="takeAway">
							<option value="0"><s:text name="config-inner" /></option>
							<option value="1"><s:text name="config-outer" /></option>
					</select></td>
				</tr>
				<tr>
					<th><s:text name="config-seat" /></th>
					<td><label><input type="radio" name="seat" value="0"> <s:text name="config-hide" /></label> <label><input type="radio" name="seat" value="1"> <s:text name="config-show" /></label></td>
				</tr>
				<tr>
					<th><s:text name="config-type" /></th>
					<td><label><input type="radio" name="foodType" value="0"> <s:text name="config-hide" /></label> <label><input type="radio" name="foodType" value="1"> <s:text name="config-show" /></label></td>
				</tr>
				<tr>
					<th><s:text name="config-paper" /></th>
					<td><label><input type="checkbox" id="shopOrder"> <s:text name="config-shop" /></label> <label><input type="checkbox" id="kitchenOrder"> <s:text name="config-kitchen" /></label></td>
				</tr>
				<tr class="pay">
					<th><s:text name="config-payset" /></th>
					<td><label><input type="checkbox" id="cash"> <s:text name="config-cash" /></label> <label><input type="checkbox" id="creditcard"> <s:text name="config-creditcard" /></label> <label><input type="checkbox" id="wechat"> <s:text name="config-wechat" /></label> <label><input type="checkbox" id="alipay"> <s:text name="config-alipay" /></label> <label><input type="checkbox" id="member"> <s:text name="config-member" /></label> <label><input type="checkbox" id="metrocard"> <s:text name="config-metrocard" /></label> <label><input type="checkbox" id="easycard"> <s:text name="config-easycard" /></label></td>
				</tr>
				<tr>
					<th><s:text name="config-accessory" /></th>
					<td>
						<div>
							<label><input type="radio" name="accessory" value="0"> <s:text name="config-disable" /></label> <label><input type="radio" name="accessory" value="1"> <s:text name="config-enable" /></label>
						</div>
						<div>
							<div class="group accessory">
								<label class="addon" for="accessoryName"><s:text name="name" /></label><input class="text" id="accessoryName">
							</div>
						</div>
						<div>
							<div class="group accessory">
								<label class="addon" for="accessoryPercent"><s:text name="config-percent" /></label><input class="text" id="accessoryPercent">
							</div>
						</div>
					</td>
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

	<script src="js/lib/require.js" data-main="module/system/config"></script>
</body>
</html>

