<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-food" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/food.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-food" />
		</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-name"><s:text name="name" /></label><input id="search-name" class="text">
			</div>
			<div class="inline group">
				<label class="addon" for="search-type"><s:text name="type" /></label> <select class="text" id="search-type"></select>
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">
					<s:text name="find" />
				</button>
				<button class="btn btn-success btn-small" id="add">
					<s:text name="create" />
				</button>
			</div>
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
				<li class="inline">
					<button class="btn btn-danger btn-small" id="del-all">
						<s:text name="delete-all" />
					</button>
				</li>
			</ul>
		</nav>
	</header>

	<div class="main">
		<div id="data"></div>
	</div>

	<footer>
		<div id="page"></div>
	</footer>

	<div id="material-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="material-name"><s:text name="name" /></label><input id="material-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-material">
					<s:text name="find" />
				</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width="20%"><s:text name="select" /></th>
						<th width="45%"><s:text name="name" /></th>
						<th width="35%"><s:text name="picture" /></th>
					</tr>
				</thead>
				<tbody id="material-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="material-page"></div>
		</div>
	</div>

	<!-- editor dialog -->
	<div id="editor">
		<table class="table">
			<tbody>
				<tr>
					<td id="show">
						<div class="info">
							<div class="wrap">
								<img src="" alt="">
								<div class="desc">
									<label class="name"></label> <label class="price"></label>
								</div>
							</div>
						</div>
					</td>
					<td>
						<table class="table table-edit table-hover">
							<tbody>
								<tr>
									<th width="15%"><s:text name="name" /></th>
									<td><input id="id" type="hidden"><input class="text" id="name"></td>
								</tr>
								<tr>
									<th><s:text name="food-abbreviation" /></th>
									<td><input class="text" id="abbreviation"></td>
								</tr>
								<tr>
									<th><s:text name="food-nickname" /></th>
									<td><input class="text" id="nickname"></td>
								</tr>
								<tr>
									<th><s:text name="type" /></th>
									<td><select class="text" id="typeId">
									</select></td>
								</tr>
								<tr>
									<th><s:text name="food-price" /></th>
									<td><input class="text" id="price"></td>
								</tr>
								<tr>
									<th><s:text name="picture" /><input type="hidden" id="materialId"></th>
									<td>
										<button class="btn btn-primary" id="load-material">
											<s:text name="select" />
										</button>
									</td>
								</tr>
								<tr>
									<th><s:text name="food-introduction" /></th>
									<td><textarea class="text" id="introduction" rows="5" cols="30"></textarea></td>
								</tr>
								<tr>
									<th><s:text name="food-taste" /></th>
									<td><div id="taste"></div></td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/base/food"></script>
</body>
</html>
