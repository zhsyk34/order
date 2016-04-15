Array.prototype.indexOf = function(value) {
	for (var i = 0, len = this.length; i < len; i++) {
		if (this[i] === value) {
			return i;
		}
	}
	return -1;
}

Array.prototype.lastIndexOf = function(value) {
	var len = this.length;
	while (len--) {
		if (this[len] === value) {
			return len;
		}
	}
	return -1;
}

Array.prototype.contains = function(value) {
	return this.indexOf(value) > -1;
}

Array.prototype.sortNumber = function(desc) {
	if (this.length) {
		this.sort(function(x, y) {
			if (desc && desc == "desc") {
				return y - x;
			}
			return x - y;
		});
	}
}

Array.prototype.remove = function(target) {
	if (target instanceof Array) {
		for (var i = 0, len = target.length; i < len; i++) {
			this.remove(target[i]);
		}
	} else {
		var index = this.indexOf(target);
		index > -1 && (this.splice(index, 1));
	}
	return this;
}

// unique-1
Array.prototype.unique = function() {
	var hash = {}, len = this.length, result = [];
	for (var i = 0; i < len; i++) {
		if (!hash[this[i]]) {
			result.push(this[i]);
			hash[this[i]] = true;
		}
	}
	return result;
}
// unique-2 数字元素排序后...
Array.prototype.unique2 = function() {
	this.sortNumber();
	var result = [ this[0] ];
	for (var i = 1; i < this.length; i++) {
		if (this[i] > result[result.length - 1]) {
			result.push(this[i]);
		}
	}
	return result;
}

Date.prototype.getMonthDate = function() {
	var year = this.getFullYear();
	var month = this.getMonth() + 1;
	month > 12 && year++;

	return new Date(year, month % 12, 1).spacing(-1, "day").getDate();
}

Date.prototype.spacing = function(count) {
	var unit = arguments[1] || "second";

	var date = new Date();
	var time = this.getTime();

	switch (unit) {
	case "second":
		date.setTime(time + count * 1000);
		break;
	case "minute":
		date.setTime(time + count * 1000 * 60);
		break;
	case "hour":
		date.setTime(time + count * 1000 * 60 * 60);
		break;
	case "day":
		date.setTime(time + count * 1000 * 60 * 60 * 24);
		break;
	case "week":
		date.setTime(time + count * 1000 * 60 * 60 * 24 * 7);
		break;
	}
	return date;
}

Date.prototype.format = function(pattern) {
	// pattern like "yyyy-MM-dd HH:mm:ss"
	var info = {
		"y+" : this.getFullYear(),
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"H+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds()
	};
	for ( var key in info) {
		var r = eval("/" + key + "/").exec(pattern);
		r && (pattern = pattern.replace(r[0], ("0" + info[key]).slice(-r[0].length)));
	}
	return pattern;
}

function randomColor() {
	return "#" + ("00000" + (Math.random() * 0x1000000 << 0).toString(16)).slice(-6);
}

$.toMap = function(target) {
	var id = arguments[1] || "id";
	// var override = !!arguments[2];
	var map = {};
	if (target instanceof Array) {
		for (var i = 0, len = target.length; i < len; i++) {
			var key = target[i][id];
			key == null || (map[key] == null && (map[key] = target[i]));
		}
	} else if (target instanceof Object) {
		var key = target[id];
		key == null || (map[key] = target);
	}
	return map;
}

$.extract = function(target) {
	var id = arguments[1] || "id";
	var unique = !!arguments[2];
	var result = [];

	if (target instanceof Array) {
		var hash = {};
		for (var i = 0, len = target.length; i < len; i++) {
			var obj = target[i];
			obj[id] == null || result.push(obj[id]);
		}
		unique && (result = result.unique());
	} else if (target instanceof Object) {
		target[id] == null || result.push(target[id]);
	}
	return result;
}