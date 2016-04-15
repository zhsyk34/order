(function($) {
	$.fn.drag = function(options, param) {
		if (typeof options == "string") {
			return $.fn.drag.methods[options](this, param);
		}

		return this.each(function() {
			var state = $.data(this, "drag");
			if (state) {
				$.extend(state.options, options || {});
			} else {
				$.data(this, "drag", {
					options : $.extend({}, $.fn.drag.defaults, options || {})
				});
			}
			listener(this);
		});
	};

	function listener(target) {
		var options = $(target).drag("options");
		if (options.disabled) {
			$(this).css("cursor", "");
			return;
		}

		$(target).off(".drag").on("mouseleave.drag", function() {
			$(this).css("cursor", "");
		});

		$(target).on("mousedown.drag", function(original) {
			if (options.onBefore.call(target) == false) {
				return;
			}

			var offset = $(this).offset();
			options.offset = offset;
			var proxy = null;

			if (options.proxy) {
				proxy = $(target).clone().insertAfter(target);
				proxy.css("position", "absolute");
				proxy.offset(offset);
			}

			$(document).on("mouseup.drag", function(e) {
				$(this).off(".drag");

				if (options.revert) {
					$(target).offset({
						left : offset.left,
						top : offset.top
					});
				}
				if (proxy) {
					proxy.remove();
				}
				options.onStop.call(target, e);
			});

			$(document).on("mousemove.drag", function(current) {
				if (options.onMove.call(target, current) == false) {
					return;
				}
				var left = offset.left + current.clientX - original.clientX, top = offset.top + current.clientY - original.clientY;
				// 限制范围
				var container = options.container ? (typeof options.container == "string" ? $(options.container) : options.container) : null;
				if (container && container.length > 0) {
					var edge = options.edge;
					top = Math.max(top, container.offset().top - edge);
					top = Math.min(top, container.offset().top + container.outerHeight() - $(target).outerHeight() + edge);
					left = Math.max(left, container.offset().left - edge);
					left = Math.min(left, container.offset().left + container.outerWidth() - $(target).outerWidth() + edge);
				}

				$(target).offset({
					left : options.axis == "y" ? null : left,
					top : options.axis == "x" ? null : top
				});
			});
		});
	}

	$.fn.drag.defaults = {
		proxy : false,// 是否使用代理对象
		revert : false,// 还原位置
		cursor : "move",
		// deltaX : null,
		// deltaY : null,
		// handle : null,
		// delay : 100,
		container : null,// 活动容器

		disabled : false,
		edge : 0,// 容器的拓展边界
		axis : null, // 限制移动方向:x||y

		onBefore : function() {
		},
		onMove : function(e) {
		},
		onStop : function(e) {
		}
	};

	$.fn.drag.methods = {
		options : function(target) {
			return $.data(target[0], "drag").options;
		},
		proxy : function(target) {
			return $.data(target[0], "drag").proxy;
		},
		enable : function(target) {
			return $(target).each(function() {
				$(this).drag({
					disabled : false
				});
			});
		},
		disable : function(target) {
			return $(target).each(function() {
				$(this).drag({
					disabled : true
				});
			});
		},
		revert : function(target) {
			return $(target).each(function() {
				$(this).offset($(this).drag("options").offset || {});
			});
		}
	};
})(jQuery);
