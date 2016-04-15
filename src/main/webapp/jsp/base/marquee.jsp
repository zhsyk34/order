<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/commons.jsp"%>
<html>
<head>
<title><s:text name="index-marquee" /></title>
<link rel="stylesheet" href="css/util/layout.css">
<link rel="stylesheet" href="js/lib/zhsy/css/modal.css">
<link rel="stylesheet" href="js/lib/zhsy/css/page.css">
<link rel="stylesheet" href="js/lib/zhsy/css/slider.css">
<link rel="stylesheet" href="js/lib/spectrum/spectrum.css">
<link rel="stylesheet" href="css/module/marquee.css">
</head>
<body>
	<header>
		<h3>
			<s:text name="index-marquee" />
		</h3>
		<nav>
			<div>
				<div class="inline group">
					<label class="addon" for="search-title"><s:text name="title" /></label><input id="search-title" class="text">
				</div>
				<div class="inline group">
					<label class="addon" for="search-content"><s:text name="content" /></label><input id="search-content" class="text">
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

	<!-- data -->
	<div class="main">
		<table class="table table-hover">
			<thead>
				<tr>
					<th width=""><input id="check-parent" type="checkbox"></th>
					<th width=""><s:text name="index" /></th>
					<th width=""><s:text name="title" /></th>
					<th width=""><s:text name="content" /></th>
					<th width=""><s:text name="marquee-direction" /></th>
					<th width=""><s:text name="marquee-speed" /></th>
					<th width=""><s:text name="marquee-font" /></th>
					<th width=""><s:text name="marquee-size" /></th>
					<th width=""><s:text name="marquee-color" /></th>
					<th width=""><s:text name="marquee-background" /></th>
					<th width=""><s:text name="edit" /></th>
				</tr>
			</thead>
			<tbody id="data"></tbody>
		</table>
	</div>

	<div id="editor">
		<table class="table table-hover table-edit">
			<tr>
				<th><s:text name="preview" /></th>
				<td>
					<div id="wrap">
						<div id="preview"></div>
					</div>
				</td>
			</tr>
			<tr>
				<th width="20%"><s:text name="title" /><input id="id" type="hidden"></th>
				<td width="80%"><input class="text" id="title"></td>
			</tr>
			<tr>
				<th><s:text name="content" /></th>
				<td><textarea id="content" rows="3" cols="60" class="text"></textarea></td>
			</tr>
			<tr>
				<th><s:text name="marquee-direction" /></th>
				<td id="direction"><label><input type="radio" name="dir" value="left" checked> <s:text name="marquee-left" /></label> <label><input type="radio" name="dir" value="right"> <s:text name="marquee-right" /></label></td>
			</tr>
			<tr>
				<th><s:text name="marquee-speed" /></th>
				<td><div id="speed"></div></td>
			</tr>
			<tr>
				<th><s:text name="marquee-font" /></th>
				<td><select id="font" class="text">
						<option value="标楷体" selected>标楷体</option>
						<option value="华康俪特圆">华康俪特圆</option>
						<option value="华康细圆体">华康细圆体</option>
						<option value="华康细圆体">华康粗圆体</option>
						<option value="华康中黑体">华康中黑体</option>
						<option value="华康粗黑体">华康粗黑体</option>
						<option value="DFHaiBaoW12-B5">华康海报粗体</option>
						<option value="DFHaiBaoW9-B5">华康海报细体</option>
						<option value="DFFangYuan Std W7">华康方圆体</option>
						<option value="华康正颜楷体W7">华康正颜楷体</option>
						<option value="华康勘亭流">华康勘亭流</option>
						<option value="文鼎粗圆">文鼎粗圆</option>
				</select></td>
			</tr>
			<tr>
				<th><s:text name="marquee-size" /></th>
				<td><div id="size"></div></td>
			</tr>
			<tr>
				<th><s:text name="marquee-color" /></th>
				<td><input id="color"></td>
			</tr>
			<tr>
				<th><s:text name="marquee-background" /></th>
				<td><input id="background"></td>
			</tr>
		</table>
	</div>

	<!-- page -->
	<footer>
		<div id="page"></div>
	</footer>

	<script src="js/lib/require.js" data-main="module/base/marquee"></script>
</body>
</html>

