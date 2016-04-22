<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-template" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/template.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-template" />
		</h3>
		<nav>
			<div class="inline group">
				<label class="addon" for="search-name"><s:text name="name" /></label><input id="search-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find">
					<s:text name="find" />
				</button>
			</div>
			<div class="inline">
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
		<table class="table table-hover">
			<thead>
				<tr>
					<th width="10%"><input id="check-parent" type="checkbox"></th>
					<th width="15%"><s:text name="index" /></th>
					<th width="30%"><s:text name="name" /></th>
					<th width="15%"><s:text name="type" /></th>
					<th width="30%"><s:text name="edit" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<div id="editor">
		<table class="table table-hover table-edit">
			<tbody>
				<tr>
					<th width="20%"><s:text name="type" /></th>
					<td id="type"><label><input type="radio" name="type" value="s02">S02</label> <label><input type="radio" name="type" value="e01">E01</label></td>
				</tr>
				<tr>
					<th><s:text name="content" /></th>
					<td id="content"><label><input type="radio" name="content" value="number" checked> <s:text name="template-number" /></label> <label><input type="radio" name="content" value="video"> <s:text name="video" /></label> <label><input type="radio" name="content" value="picture"> <s:text name="picture" /></label></td>
				</tr>
				<tr>
					<th><s:text name="name" /></th>
					<td><input type="hidden" id="id"><input class="text" id="name"></td>
				</tr>
				<tr class="layout">
					<th><s:text name="template-layout" /></th>
					<td>
						<div class="inline">
							<input class="text" value="25" id="rowcount" placeholder="<s:text name="template-rowcount" />">
						</div>
						<div class="inline">
							<input class="text" value="3" id="colcount" placeholder="<s:text name="template-colcount" />">
						</div>
					</td>
				</tr>
				<!-- logo -->
				<tr id="logo">
					<th>Logo
						<button class="btn btn-primary">
							<s:text name="select" />
						</button>
					</th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- food -->
				<tr id="foodList">
					<th><s:text name="template-food" />
						<button class="btn btn-primary">
							<s:text name="select" />
						</button></th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- number -->
				<tr id="number">
					<th><s:text name="template-number" />
						<button class="btn btn-primary">
							<s:text name="select" />
						</button></th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- video -->
				<tr id="videoList">
					<th><s:text name="video" />
						<button class="btn btn-primary">
							<s:text name="select" />
						</button></th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<!-- 图片相关 -->
				<tr id="pictureList" class="picture">
					<th><s:text name="picture" />
						<button class="btn btn-primary">
							<s:text name="select" />
						</button></th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
				<tr class="picture">
					<th><s:text name="template-interlude" /></th>
					<td><input class="text" id="interlude"></td>
				</tr>
				<tr class="picture">
					<th><s:text name="template-effect" /></th>
					<td><select class="text" id="effect">
							<option value="RANDOM" selected="selected">随机</option>
							<option value="ALPHA">渐变</option>
							<option value="CIRCLE">圆形</option>
							<option value="MOVE">移动</option>
							<option value="BLINDS">百叶窗</option>
					</select></td>
				</tr>
				<!-- 跑马灯 -->
				<tr id="marqueeList">
					<th><s:text name="template-marquee" />
						<button class="btn btn-primary">
							<s:text name="select" />
						</button></th>
					<td>
						<div class="thumbnail"></div>
					</td>
				</tr>
			</tbody>
		</table>
	</div>

	<!-- material -->
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
						<th width="" id="material-parent"></th>
						<th width=""><s:text name="index" /></th>
						<th width=""><s:text name="name" /></th>
						<th width=""><s:text name="picture" /></th>
						<!-- <th width=""><s:text name="preview" /></th> -->
					</tr>
				</thead>
				<tbody id="material-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="material-page"></div>
		</div>
	</div>

	<!-- food -->
	<div id="food-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="food-name"><s:text name="name" /></label><input id="food-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-food">
					<s:text name="find" />
				</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="food-parent" type="checkbox"></th>
						<th width=""><s:text name="index" /></th>
						<th width=""><s:text name="name" /></th>
						<th width=""><s:text name="price" /></th>
						<th width=""><s:text name="picture" /></th>
						<th width=""><s:text name="template-taste" /></th>
					</tr>
				</thead>
				<tbody id="food-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="food-page"></div>
		</div>
	</div>

	<!-- marquee -->
	<div id="marquee-dialog">
		<div class="dialog-nav">
			<div class="inline group">
				<label class="addon" for="marquee-name"><s:text name="name" /></label> <input id="marquee-name" class="text">
			</div>
			<div class="inline">
				<button class="btn btn-primary btn-small" id="find-marquee">
					<s:text name="find" />
				</button>
			</div>
		</div>
		<div class="dialog-main">
			<table class="table table-hover">
				<thead>
					<tr>
						<th width=""><input id="marquee-parent" type="checkbox"></th>
						<th width=""><s:text name="index" /></th>
						<th width=""><s:text name="title" /></th>
						<th width=""><s:text name="content" /></th>
					</tr>
				</thead>
				<tbody id="marquee-data"></tbody>
			</table>
		</div>
		<div class="dialog-page">
			<div id="marquee-page"></div>
		</div>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/base/template"></script>
</body>
</html>

