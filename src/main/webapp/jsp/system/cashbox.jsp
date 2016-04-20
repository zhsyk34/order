<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-cashbox" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="css/module/system.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-cashbox" />
		</h3>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-edit table-hover">
			<thead>
				<tr>
					<th width="15%" rowspan="2"><s:text name="cashbox-machine" /></th>
					<th colspan="3"><s:text name="cashbox-configs" /></th>
				</tr>
				<tr>
					<th width="15%"><s:text name="cashbox-face" /></th>
					<th width="35%"><s:text name="cashbox-min" /></th>
					<th width="35%"><s:text name="cashbox-max" /></th>
				</tr>
			</thead>
			<tbody>
				<!-- nd100:tw -->
				<tr class="nd100 tw">
					<th><s:text name="cashbox-vomit" /></th>
					<th>100</th>
					<th><input class="text" id="nd100tw100Min"></th>
					<th><input class="text" value="600" readonly></th>
				</tr>
				<!-- nd100:cn -->
				<tr class="nd100 cn">
					<th><s:text name="cashbox-vomit" /></th>
					<th>10</th>
					<th><input class="text" id="nd100cn10Min"></th>
					<th><input class="text" value="600" readonly></th>
				</tr>
				<!-- nv9:tw -->
				<tr class="nv9 tw">
					<th rowspan="3"><s:text name="cashbox-swallow" /></th>
					<th>100</th>
					<th><input class="text" id="nv9tw100Min" readonly></th>
					<th><input class="text" id="nv9tw100Max"></th>
				</tr>
				<tr class="nv9 tw">
					<th>500</th>
					<th><input class="text" id="nv9tw500Min" readonly></th>
					<th><input class="text" id="nv9tw500Max"></th>
				</tr>
				<tr class="nv9 tw">
					<th>1000</th>
					<th><input class="text" id="nv9tw1000Min" readonly></th>
					<th><input class="text" id="nv9tw1000Max"></th>
				</tr>
				<!-- nv9:cn -->
				<tr class="nv9 cn">
					<th rowspan="6"><s:text name="cashbox-swallow" /></th>
					<th>1</th>
					<th><input class="text" id="nv9cn1Min" readonly></th>
					<th><input class="text" id="nv9cn1Max"></th>
				</tr>
				<tr class="nv9 cn">
					<th>5</th>
					<th><input class="text" id="nv9cn5Min" readonly></th>
					<th><input class="text" id="nv9cn5Max"></th>
				</tr>
				<tr class="nv9 cn">
					<th>10</th>
					<th><input class="text" id="nv9cn10Min" readonly></th>
					<th><input class="text" id="nv9cn10Max"></th>
				</tr>
				<tr class="nv9 cn">
					<th>20</th>
					<th><input class="text" id="nv9cn20Min" readonly></th>
					<th><input class="text" id="nv9cn20Max"></th>
				</tr>
				<tr class="nv9 cn">
					<th>50</th>
					<th><input class="text" id="nv9cn50Min" readonly></th>
					<th><input class="text" id="nv9cn50Max"></th>
				</tr>
				<tr class="nv9 cn">
					<th>100</th>
					<th><input class="text" id="nv9cn100Min" readonly></th>
					<th><input class="text" id="nv9cn100Max"></th>
				</tr>
				<!-- hopper:tw -->
				<tr class="hopper tw">
					<th rowspan="4"><s:text name="cashbox-coin" /></th>
					<th>1</th>
					<th><input class="text" id="hoppertw1Min"></th>
					<th><input class="text" id="hoppertw1Max"></th>
				</tr>
				<tr class="hopper tw">
					<th>5</th>
					<th><input class="text" id="hoppertw5Min"></th>
					<th><input class="text" id="hoppertw5Max"></th>
				</tr>
				<tr class="hopper tw">
					<th>10</th>
					<th><input class="text" id="hoppertw10Min"></th>
					<th><input class="text" id="hoppertw10Max"></th>
				</tr>
				<tr class="hopper tw">
					<th>50</th>
					<th><input class="text" id="hoppertw50Min"></th>
					<th><input class="text" id="hoppertw50Max"></th>
				</tr>
				<!-- hopper:cn -->
				<tr class="hopper cn">
					<th rowspan="3"><s:text name="cashbox-coin" /></th>
					<th>0.1</th>
					<th><input class="text" id="hoppercn01Min"></th>
					<th><input class="text" id="hoppercn01Max"></th>
				</tr>
				<tr class="hopper cn">
					<th>0.5</th>
					<th><input class="text" id="hoppercn05Min"></th>
					<th><input class="text" id="hoppercn05Max"></th>
				</tr>
				<tr class="hopper cn">
					<th>1</th>
					<th><input class="text" id="hoppercn1Min"></th>
					<th><input class="text" id="hoppercn1Max"></th>
				</tr>
			</tbody>
		</table>
	</div>

	<footer>
		<button class="btn btn-success" id="sure">
			<s:text name="sure" />
		</button>
		<button class="btn btn-info" id="reset">
			<s:text name="reset" />
		</button>
	</footer>
	<script src="js/lib/require.js" data-main="module/system/cashbox"></script>
</body>
</html>

