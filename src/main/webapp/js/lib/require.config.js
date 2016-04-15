var require = {
	baseUrl : "js",
	shim : {
		"drag" : [ "jquery" ],
		"modal" : [ "jquery" ],
		"page" : [ "jquery", "message" ],
		"progress" : [ "jquery" ],
		"slider" : [ "jquery" ],

		"i18n" : [ "jquery" ],
		"form" : [ "jquery" ],
		"spectrum" : [ "jquery" ],
		"datePicker" : [ "jquery" ],

		"intercept" : [ "jquery" ],
		"tool" : [ "jquery" ],
		"validate" : [ "jquery" ]
	},
	paths : {
		/* lib */
		"jquery" : "lib/jquery",

		"drag" : "lib/zhsy/js/drag",
		"modal" : "lib/zhsy/js/modal",
		"page" : "lib/zhsy/js/page",
		"progress" : "lib/zhsy/js/progress",
		"slider" : "lib/zhsy/js/slider",

		"i18n" : "lib/jquery.i18n",
		"form" : "lib/jquery.form",
		"spectrum" : "lib/spectrum/spectrum",
		"datePicker" : "lib/datePicker/WdatePicker",
		/* util */
		"checkctrl" : "util/checkctrl",
		"crud" : "util/crud",
		"intercept" : "util/intercept",
		"message" : "util/message",
		"tool" : "util/tool",
		"validate" : "util/validate"
	}
};