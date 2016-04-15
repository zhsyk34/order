(function($) {
	$.fn.slider = function(options, param) {
		if (typeof options == "string") {
			return $.fn.slider.methods[options](this, param);
		}

		return this.each(function() {
			var state = $.data(this, "slider");
			if (state) {
				$.extend(state.options, options || {});
			} else {
				$.data(this, "slider", {
					options : $.extend({}, $.fn.slider.defaults, options || {})
				});
			}
			init(this);
		});
	};

	function init(target) {
		var options = $(target).slider("options");

		var slider = $("<div class='slider'></div>");
		var completed = $("<div class='slider-completed'></div>").appendTo(slider);
		var handle = $("<div class='slider-handle'></div>").appendTo(slider);
		var tip = $("<span class='slider-tip'></span>").appendTo(slider).hide();

		$(target).empty().append(slider);

		// size and position
		$(slider).css({
			width : options.width,
			height : options.height
		});
		handle.css({
			width : options.handleWidth,
			height : options.handleHeight,
			marginLeft : -options.handleWidth / 2,
			marginTop : -options.handleHeight / 2
		});
		tip.css({
			marginTop : (options.height - options.handleHeight) / 2 - tip.height() - 5// css
		});

		// value and position
		locate(slider.width() * options.value / 100);

		if (options.disabled) {
			slider.addClass("slider-disabled");
			return;
		}

		// listen
		handle.off(".slider").on("mouseleave.slider", function() {
			$(this).css("cursor", "");
		});

		handle.on("mousedown.slider", function(original) {
			$(this).css("cursor", "pointer");
			if (options.onStart.call(handle, original) == false) {
				return;
			}
			var position = $(this).position();

			$(document).on("mouseup.slider", function(e) {
				$(this).off(".slider");
				options.onStop.call(handle, e);
			});

			$(document).on("mousemove.slider", function(current) {
				if (options.onDrag.call(handle, current) == false) {
					return;
				}
				var left = position.left + current.pageX - original.pageX;
				locate(left);
			});
		});

		slider.on("mousedown.slider", function(e) {
			var left = e.pageX - $(this).offset().left;
			locate(left);
			options.onStop.call(handle, e);
		});

		function locate(left) {
			var width = slider.width();
			var min = options.min, max = options.max;

			left = Math.min(Math.max(left, min * width / 100), max * width / 100);
			var value = Math.min(Math.max(Math.round(left * 100 / width), min), max);
			options.value = value;

			completed.width(value + "%");
			handle.css("left", left);
			tip.css("left", left);
			if (options.tip) {
				tip.text(value).show();
			}
		}
	}

	$.fn.slider.defaults = {
		width : 200,
		height : 10,
		handleWidth : 10,
		handleHeight : 20,
		mode : null,// TODO
		reversed : false,// TODO max-min
		tip : true,
		disabled : false,
		range : false,// TODO
		value : 0,
		min : 0,
		max : 100,
		step : 1,// TODO
		formatter : function(value) {
			return value;
		},
		converter : {
			position : function(value, size) {
				var options = $(this).slider("options");
				return (value - options.min) / (options.max - options.min) * size;
			},
			value : function(position, size) {
				var options = $(this).slider("options");
				return options.min + (options.max - options.min) * (position / size);
			}
		},
		onStart : function() {
		},
		onDrag : function() {
		},
		onStop : function() {
		}
	};

	$.fn.slider.methods = {
		options : function(target) {
			return $.data(target[0], "slider").options;
		},
		enable : function(target) {
			return $(target).each(function() {
				$(this).slider({
					disabled : false
				});
			});
		},
		disable : function(target) {
			return $(target).each(function() {
				$(this).slider({
					disabled : true
				});
			});
		},
		value : function(target, param) {
			if (param && (typeof param == "number")) {
				$(target).each(function() {
					$(this).slider({
						value : param
					});
				});
				return;
			}
			return this.options(target).value;
		}
	};
})(jQuery);
