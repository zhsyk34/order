define([ "jquery" ], function($) {
	return {
		isEmpty : function(str) {
			return str == null || typeof str == undefined || ($.trim(str)).length == 0;
		},
		equals : function(str1, str2) {// 比较字符串
			return str1 == str2;
		},
		equalsIgnoreCase : function(str1, str2) {// 忽略大小写比较字符串
			return (str1 ? str1.toLowerCase() : str1) == (str2 ? str2.toLowerCase() : str2);
		},
		isNumber : function(number) {
			return !isNaN(number);
		},
		isInteger : function(input) {
			return /^[+-]?\d+$/.test(input);
		},
		isNatural : function(input) {// 自然数
			return /^[+]?[1-9]\d*$/.test(input);
		},
		isPositive : function(input, strict) {// 正数
			return !isNaN(input) && (strict ? input > 0 : input >= 0);
		}
	}
});