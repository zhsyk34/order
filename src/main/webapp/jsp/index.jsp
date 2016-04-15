<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/lib/init.css">
<link rel="stylesheet" href="css/module/index.css">
<title><s:text name="index-title" /></title>
</head>
<body>
	<div id="container">
		<nav id="nav">
			<div id="logo"></div>
			<div id="welcome">
				<s:text name="index-welcome" />
				<span><s:property value="#session.user.name" /></span>
				<s:text name="index-logon" />
			</div>
			<div class="accordion">
				<p><s:text name="index-base" /></p>
				<ul>
					<li><a href="jsp/base/food.jsp"><s:text name="index-food" /></a></li>
					<li><a href="jsp/base/taste.jsp"><s:text name="index-taste" /></a></li>
					<li><a href="jsp/base/style.jsp"><s:text name="index-style" /></a></li>
					<li><a href="jsp/base/type.jsp"><s:text name="index-type" /></a></li>
					<li><a href="jsp/base/material.jsp"><s:text name="index-material" /></a></li>
					<li><a href="jsp/base/marquee.jsp"><s:text name="index-marquee" /></a></li>
					<li><a href="jsp/base/template.jsp"><s:text name="index-template" /></a></li>
					<li><a href="jsp/base/seat.jsp"><s:text name="index-seat" /></a></li>
				</ul>
				<p><s:text name="index-business" /></p>
				<ul>
					<li><a href="jsp/business/rule.jsp"><s:text name="index-rule" /></a></li>
					<li><a href="jsp/business/order.jsp"><s:text name="index-order" /></a></li>
					<li><a href="jsp/business/sell.jsp"><s:text name="index-sell" /></a></li>
					<li><a href="jsp/business/refund.jsp"><s:text name="index-refund" /></a></li>
					<li><a href="jsp/business/stop.jsp"><s:text name="index-stop" /></a></li>
					<li><a href="jsp/business/gift.jsp"><s:text name="index-gift" /></a></li>
					<li><a href="jsp/business/discount.jsp"><s:text name="index-discount" /></a></li>
				</ul>
				<p><s:text name="index-device" /></p>
				<ul>
					<li><a href="jsp/device/terminal.jsp"><s:text name="index-terminal" /></a></li>
					<li><a href="jsp/device/setting.jsp"><s:text name="index-setting" /></a></li>
					<li><a href="jsp/device/list.jsp"><s:text name="index-list" /></a></li>
					<li><a href="jsp/device/monitor.jsp"><s:text name="index-monitor" /></a></li>
					<li><a href="jsp/device/record.jsp"><s:text name="index-record" /></a></li>
					<li><a href="jsp/device/remote.jsp"><s:text name="index-remote" /></a></li>
				</ul>
				<p><s:text name="index-system" /></p>
				<ul>
					<li><a href="jsp/system/config.jsp"><s:text name="index-config" /></a></li>
					<li><a href="jsp/system/cashbox.jsp"><s:text name="index-cashbox" /></a></li>
					<li><a href="jsp/system/payset.jsp"><s:text name="index-payset" /></a></li>
					<li><a href="jsp/system/user.jsp"><s:text name="index-user" /></a></li>
					<li><a href="jsp/system/password.jsp"><s:text name="index-password" /></a></li>
					<li id="logout"><s:text name="index-logout" /></li>
				</ul>
			</div>
		</nav>
		<div id="content">
			<iframe src="jsp/device/list.jsp"></iframe>
		</div>
	</div>

	<div id="footer">
		<div id="version">
			<span><s:text name="index-version" /></span>
			<s:property value="#application.version" />
		</div>
		<div id="serverId">
			<span><s:text name="index-serverid" /></span>
			<s:property value="#application.serverid" />
		</div>
		<span id="date"></span>
	</div>

	<script src="js/lib/require.js" data-main="module/index"></script>
</body>
</html>

