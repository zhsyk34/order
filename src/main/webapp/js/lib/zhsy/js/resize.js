(function($) {
	$.fn.resize = function(options, param) {
		if (typeof options == "string") {
			return $.fn.resize.methods[options](this, param);
		}

		return this.each(function() {
			var state = $.data(this, "resize");
			if (state) {
				$.extend(state.options, options || {});
			} else {
				state = $.data(this, "resize", {
					options : $.extend({}, $.fn.resize.defaults, options || {})
				});
			}
			listen(this);
		});
	};

	function listen(target) {
		var options = $.data(target, "resize").options;
		if (options.disabled) {
			return;
		}
		$(target).off(".resize");

		$(target).on("mousemove.resize", function(e) {
			if (options.resizing) {
				return;
			}
			var dir = getDirection(e);
			$(this).css("cursor", dir ? dir + "-resize" : "");
		});

		$(target).on("mouseleave.resize", function(e) {
			$(this).css("cursor", "");
		});

		$(target).on("mousedown.resize", function(original) {

			$(document).on("mouseup.resize", function(e) {
				options.resizing = false;
				options.onStop.call(this, e);
				$("body").css("cursor", "");
				$(this).off(".resize");
				return false;
			});

			$(document).on("mousedown.resize", function(e) {
				options.resizing = true;
				options.onStart.call(this, e);
				return false;
			});

			var dir = getDirection(original);
			$("body").css("cursor", dir ? dir + "-resize" : "");
			var data = {
				left : parseFloat($(target).css("left")) || 0,
				top : parseFloat($(target).css("top")) || 0,
				width : $(target).outerWidth(),
				height : $(target).outerHeight()
			};

			$(document).on("mousemove.resize", function(current) {
				var result = {
					left : null,
					top : null,
					width : null,
					height : null
				};

				if (dir.indexOf("e") != -1) {
					result.width = Math.min(Math.max(data.width + current.pageX - original.pageX, options.minWidth), options.maxWidth);
				}

				if (dir.indexOf("w") != -1) {
					result.width = Math.min(Math.max(data.width + original.pageX - current.pageX, options.minWidth), options.maxWidth);
					result.left = data.left + data.width - result.width;
				}

				if (dir.indexOf("s") != -1) {
					result.height = Math.min(Math.max(data.height + current.pageY - original.pageY, options.minHeight), options.maxHeight);
				}
				if (dir.indexOf("n") != -1) {
					result.height = Math.min(Math.max(data.height + original.pageY - current.pageY, options.minHeight), options.maxHeight);
					result.top = data.top + data.height - result.height;
				}

				result.top && $(target).css("top", result.top);
				result.left && $(target).css("left", result.left);
				result.width && $(target).outerWidth(result.width);
				result.height && $(target).outerHeight(result.height);

			});
		});
	}

	function getDirection(e, target) {
		var dir = "";

		var target = $(e.target);
		var options = $.data(target[0], "resize").options;

		var offset = target.offset();
		var width = target.outerWidth();
		var height = target.outerHeight();
		var edge = options.edge;

		if (e.pageY > offset.top && e.pageY < offset.top + edge) {
			dir += "n";
		} else if (e.pageY < offset.top + height && e.pageY > offset.top + height - edge) {
			dir += "s";
		}
		if (e.pageX > offset.left && e.pageX < offset.left + edge) {
			dir += "w";
		} else if (e.pageX < offset.left + width && e.pageX > offset.left + width - edge) {
			dir += "e";
		}

		var handles = options.handles.split(",");
		for (var i = 0; i < handles.length; i++) {
			var handle = handles[i].replace(/(^\s*)|(\s*$)/g, "");
			if (handle == "all" || handle == dir) {
				return dir;
			}
		}
		return "";
	}

	$.fn.resize.methods = {
		options : function(target) {
			return $.data(target[0], "resize").options;
		},
		enable : function(target) {
			return $(target).each(function() {
				$(this).resize({
					disabled : false
				});
			});
		},
		disable : function(target) {
			return $(target).each(function() {
				$(this).resize({
					disabled : true
				});
			});
		}
	};

	$.fn.resize.defaults = {
		disabled : false,
		handles : "n, e, s, w, ne, se, sw, nw, all",
		minWidth : 10,
		minHeight : 10,
		maxWidth : 10000,// $(document).width(),
		maxHeight : 10000,// $(document).height(),
		edge : 15,
		resizing : false,
		onStart : function(e) {
		},
		onResize : function(e) {
		},
		onStop : function(e) {
		}
	};

})(jQuery);