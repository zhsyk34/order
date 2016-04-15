define([ "jquery", "modal", "page", "message" ], function() {
	return {
		page : function(data, callback, target) {
			var options = {
				onChangePage : function(pageNo, pageSize) {
					typeof callback == "function" && callback();
				}
			};
			if (data) {
				options.pageNo = data.pageNo;
				options.pageSize = data.pageSize;
				options.dataCount = data.count;
			}
			(target || $("#page")).page(options);
		},
		merge : function(url, params, callback) {
			$.ajax({
				url : url,
				async : false,
				traditional : true,
				data : params,
				success : function(data) {
					switch (data.result) {
					case "create":
						$.alert($.message("crud-create"));
						break;
					case "update":
						$.alert($.message("crud-update"));
						break;
					case "enable":
						$.alert($.message("crud-enable"));
						break;
					case "disable":
						$.alert($.message("crud-disable"));
						break;
					case "deal":
						$.alert($.message("crud-deal"));
						break;
					case "revoke":
						$.alert($.message("crud-revoke"));
						break;
					case "error":
						$.alert($.message("crud-error"));
						return;
					}
					typeof callback == "function" && callback();
				}
			});
		},
		del : function(url, ids, callback) {
			typeof ids == "number" && (ids = [ ids ]);
			if (ids.length == 0) {
				$.alert($.message("crud-select"));
				return;
			}
			$.confirm({
				after : function() {
					$.ajax({
						url : url,
						traditional : true,
						async : false,
						data : {
							ids : ids
						},
						success : function(data) {
							switch (data.result) {
							case "relate":
								$.alert($.message("crud-relate"));
								return;
							case "delete":
								$.alert($.message("crud-delete"));
								break;
							case "revoke":
								$.alert($.message("crud-revoke"));
								break;
							}
							$("#page").page({
								pageNo : 1
							});
							typeof callback == "function" && callback();
						}
					});
				}
			});
			$.confirm($.message("crud-delsure"));
		}
	};
});