<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-material" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="css/module/material.css">
</head>
<body>
	<!-- upload and search -->
	<header>
		<h3>
			<s:text name="index-material" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-name"><s:text name="name" /></label><input id="search-name" class="text">
				</div>
				<div class="inline group">
					<label class="addon"><s:text name="type" /></label>
					<div class="text" id="search-type">
						<label><input type="radio" name="search-type" value="" checked> <s:text name="all" /></label> <label><input type="radio" name="search-type" value="image"> <s:text name="picture" /></label> <label> <input type="radio" name="search-type" value="video"> <s:text name="video" /></label>
					</div>
				</div>
				<div class="inline">
					<button class="btn btn-primary btn-small" id="find">
						<s:text name="find" />
					</button>
				</div>
				<div class="inline">
					<button class="btn btn-success btn-small" id="add">
						<s:text name="upload" />
					</button>
				</div>
			</div>
			<div>
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
			</div>
		</nav>
	</header>

	<div class="main">
		<div id="data"></div>
	</div>

	<footer>
		<div id="page"></div>
	</footer>

	<!-- upload dialog -->
	<div id="upload">
		<form method="post" enctype="multipart/form-data" action="json/Material_save">
			<table class="table table-hover table-edit">
				<thead>
					<tr>
						<th width="40%"><s:text name="material-file" /></th>
						<th width="35%"><s:text name="material-name" /></th>
						<th width="25%">
							<button class="btn btn-primary btn-small" id="append" type="button">
								<s:text name="create" />
							</button>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><label class="btn btn-default btn-small file"><s:text name="material-browse" /><input type="file" name="upload"></label><span class="path-info inline"></span></td>
						<td><input name="nameList" class="text"></td>
						<th>
							<button class="btn btn-info btn-small clean" type="button">
								<s:text name="reset" />
							</button>
						</th>
					</tr>
				</tbody>
			</table>
		</form>
	</div>

	<div id="preview">
		<img>
		<object type="application/x-shockwave-flash" data="tool/vcastr/vcastr3.swf">
			<param name="movie" value="tool/vcastr/vcastr3.swf">
			<param name="wmode" value="opaque">
			<param name="allowFullScreen" value="true">
			<param name="FlashVars" id="flash">
		</object>
	</div>

	<div id="editor">
		<table class="table table-hover table-edit">
			<thead>
				<tr>
					<th><s:text name="name" /></th>
					<td><input type="hidden" id="id"><input class="text" id="name"></td>
				</tr>
			</thead>
		</table>
	</div>

	<script src="js/lib/require.js" data-main="module/base/material"></script>
</body>
</html>
