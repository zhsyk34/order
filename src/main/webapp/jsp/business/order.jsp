<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/order.css">
<title><s:text name="index-order" /></title>
</head>
<body>
	<header>
		<h3>
			<s:text name="index-order" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-orderNo"><s:text name="order-number" /></label><input class="text" id="search-orderNo">
				</div>
				<div class="inline group">
					<label class="addon" for="search-shop"><s:text name="order-shop" /></label> <input class="text" id="search-shop">
				</div>
				<div class="inline group">
					<label class="addon" for="search-kitchen"><s:text name="order-kitchen" /></label> <input class="text" id="search-kitchen">
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">
						<s:text name="find" />
					</button>
				</div>
			</div>
			<div>
				<div class="inline group">
					<label class="addon"><s:text name="order-from" /></label>
					<div class="text">
						<label><input type="radio" name="from" value="" checked> <s:text name="all" /></label> 
						<label><input type="radio" name="from" value="1"> <s:text name="order-terminal" /></label> 
						<label><input type="radio" name="from" value="0"> <s:text name="order-backend" /></label>
					</div>
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="order-status" /></label>
					<div class="text">
						<label><input type="radio" name="status" value="all" checked> <s:text name="all" /></label> <label><input type="radio" name="status" value="normal"> <s:text name="order-normal" /></label> <label><input type="radio" name="status" value="nullify"> <s:text name="order-nullify" /></label>
					</div>
				</div>
			</div>
		</nav>
	</header>

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="6%"><s:text name="index" /></th>
					<th width="10%"><s:text name="order-number" /></th>
					<th width="10%"><s:text name="order-shop" /></th>
					<th width="10%"><s:text name="order-kitchen" /></th>
					<th width="8%"><s:text name="order-total" /></th>
					<th width="8%"><s:text name="order-income" /></th>
					<th width="8%"><s:text name="order-expense" /></th>
					<th width="15%"><s:text name="order-time" /></th>
					<th width="10%"><s:text name="order-detail" /></th>
					<th width="15%"><s:text name="edit" /></th>
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
		<nav>
			<div>
				<div class="inline group">
					<label class="addon"><s:text name="order-number" /></label>
					<div class="text" id="orderNo"></div>
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="order-shop" /></label>
					<div class="text" id="shop"></div>
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="order-kitchen" /></label>
					<div class="text" id="kitchen"></div>
				</div>
			</div>

			<div class="money">
				<div class="inline group">
					<label class="addon" for="total"><s:text name="order-total" /></label> <input class="text" id="total">
				</div>
				<div class="inline group">
					<label class="addon" for="income"><s:text name="order-income" /></label> <input class="text" id="income">
				</div>
				<div class="inline group">
					<label class="addon" for="expense"><s:text name="order-expense" /></label> <input class="text" id="expense">
				</div>
				<button class="btn btn-success btn-small" id="add">添加</button>
			</div>
		</nav>

		<div class="main">
			<table class="table table-edit table-hover">
				<thead>
					<tr class="detail">
						<th><s:text name="order-detail" /></th>
						<td>
							<div class="inline group">
								<label class="addon"><s:text name="order-food" /></label> <input class="text food">
							</div> <!-- <button class="btn btn-small btn-primary">查询</button>
							-->

							<div class="inline group">
								<label class="addon"><s:text name="order-taste" /></label> <input class="text taste">
							</div> <!-- 
							<button class="btn btn-small btn-primary">选择</button>
							 -->

							<div class="inline group">
								<label class="addon"><s:text name="order-price" /></label><input class="text price">
							</div>
							<div class="inline group">
								<label class="addon"><s:text name="order-count" /></label><input class="text count">
							</div>
							<button class="btn btn-small btn-danger cancel">
								<s:text name="del" />
							</button>
						</td>
					</tr>
				</thead>
				<tbody id="details"></tbody>
			</table>
		</div>
	</div>

	<!-- food -->
	<!-- 
	<div id="food-dialog">
		<div class="inline dialog-nav">
			<div class="inline group">
				<label class="addon" for="food-name">名称</label><input id="food-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-food">查询</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="food-parent" type="checkbox"></th>
						<th width="">序号</th>
						<th width="">名称</th>
						<th width="">价格</th>
						<th width="">图片</th>
						<th width="">调味</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="food-page"></div>
		</div>
	</div>
 -->

	<script src="js/lib/require.js" data-main="module/business/order"></script>
</body>
</html>
