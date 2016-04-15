define([ "i18n" ], function() {
	var map = {};
	if ($.isEmptyObject(map)) {
		$.i18n.properties({
			name : "message",
			path : "recources/",
			language : navigator.language || navigator.userLanguage,
			mode : "map",
			cache : true,
			callback : function() {
				map = $.i18n.map;
			}
		});
	}
	$.message = function(key) {
		return map[key];
	}
});
