define([ "jquery" ], function() {
	return {
		list : function(target) {
			// 全选
			$("#check-all").on("click", function() {
				target.find(":checkbox").prop("checked", true);
			});
			// 全不选
			$("#check-cancel").on("click", function() {
				target.find(":checkbox").prop("checked", false);
			});
			// 反选
			$("#check-inverse").on("click", function() {
				target.find(":checkbox").each(function() {
					var isCheckd = $(this).prop("checked");
					$(this).prop("checked", !isCheckd);
				});
			});
		},
		table : function(target) {// for-table
			// 总选
			$("#check-parent").on("click", function() {
				target.find("td :checkbox").prop("checked", $(this).prop("checked"));
			});
			// 全选
			$("#check-all").on("click", function() {
				target.find(":checkbox").prop("checked", true);
			});
			// 全不选
			$("#check-cancel").on("click", function() {
				target.find(":checkbox").prop("checked", false);
			});
			// 反选
			$("#check-inverse").on("click", function() {
				var flag = true;
				target.find("td :checkbox").each(function() {
					var isCheckd = $(this).prop("checked");
					$(this).prop("checked", !isCheckd);
					flag = flag && (!isCheckd);
				});

				$("#check-parent").prop("checked", flag);
			});
			// 单选
			target.on("click", "td :checkbox", function() {
				var flag = true;
				target.find("td :checkbox").each(function() {
					flag = flag && $(this).prop("checked");
				});
				$("#check-parent").prop("checked", flag);
			});

			// 便捷点击
			$("tr").on("click", function(e) {

			});
		},
		general : function(parent, childs) {
			function selectParent() {
				var flag = true;
				$(childs).each(function() {
					flag = flag && $(this).prop("checked");
				});
				$(parent).prop("checked", flag);
			}
			selectParent();
			// 总选
			$(parent).on("click", function() {
				$(childs).prop("checked", $(this).prop("checked"));
			});
			// 单选
			$(childs).on("click", function() {
				selectParent();
			});
		}
	}
});
