(function($) {
	$.fn.progress = function(options, param) {
		if (typeof options == "string") {
			return $.fn.progress.methods[options](this, param);
		}

		return this.each(function() {
			var state = $.data(this, "progress");
			if (state) {
				$.extend(state.options, options || {});
			} else {
				$.data(this, "progress", {
					options : $.extend({}, $.fn.progress.defaults, options || {})
				});
			}
			init(this);
		});
	};

	function init(target) {
		var options = $(target).progress("options");

		var progress = $("<div class='progress'></div>");
		var completed = $("<div class='progress-completed'></div>").appendTo(progress);
		var tip = $("<span class='progress-tip'></span>").appendTo(progress).hide();

		$(target).empty().append(progress);

		// size and position
		$(progress).css({
			width : options.width,
			height : options.height
		});

		tip.css({
			marginTop : -options.height - 5
		});

		// value and position
		locate(progress.width() * options.value / 100);

		function locate(left) {
			var width = progress.width();
			var min = options.min, max = options.max;

			left = Math.min(Math.max(left, min * width / 100), max * width / 100);
			var value = Math.min(Math.max(Math.round(left * 100 / width), min), max);
			options.value = value;

			completed.width(value + "%");
			tip.css("left", left);
			if (options.tip) {
				tip.text(value).show();
			}
		}
	}

	$.fn.progress.defaults = {
		width : 200,
		height : 10,
		tip : true,
		value : 0,
		min : 0,
		max : 100,
		formatter : function(value) {
			return value;
		},
		converter : {
			position : function(value, size) {
				var options = $(this).progress("options");
				return (value - options.min) / (options.max - options.min) * size;
			},
			value : function(position, size) {
				var options = $(this).progress("options");
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

	$.fn.progress.methods = {
		options : function(target) {
			return $.data(target[0], "progress").options;
		},
		enable : function(target) {
			return $(target).each(function() {
				$(this).progress({
					disabled : false
				});
			});
		},
		disable : function(target) {
			return $(target).each(function() {
				$(this).progress({
					disabled : true
				});
			});
		},
		value : function(target, param) {
			if (param && (typeof param == "number")) {
				$(target).each(function() {
					$(this).progress({
						value : param
					});
				});
				return;
			}
			return this.options(target).value;
		}
	};
})(jQuery);
