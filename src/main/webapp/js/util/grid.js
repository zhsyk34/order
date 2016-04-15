//废弃
define([ 'jquery' ], function($) {
	return {
		load : function() {
		}
	}
});

var method = {
	load : function(target, title, data) {
		function loadRow(data, isTh) {
			var tr = document.createElement('tr');
			for (var i = 0; i < data.length; i++) {
				var td = document.createElement(!!arguments[2] ? 'th' : 'td');
				var node = document.createTextNode(data[i]);
				td.appendChild(node);
				tr.appendChild(td);
			}
			return tr;
		}

		var temp = document.createDocumentFragment();
		for (var i = 0, len = data.length; i < len; i++) {
			if (i === 0) {
				temp.appendChild(loadRow(title, true));
			}
			var row = data[i], rowData = [];
			for (var j = 0; j < title.length; j++) {
				rowData.push(row[title[j]]);
			}
			temp.appendChild(loadRow(rowData));
		}
		target.appendChild(temp);
	},
	init2 : function(target, title, data) {
		var thead = $('<thead></thead>');
		var tbody = $('<tbody></tbody>');

		var headTr = $('<tr></tr>');
		var bodyTr = $('<tr></tr>');
		for (var i = 0, len = title.length; i < len; i++) {
			$('<th></th>').text(title[i]).appendTo(headTr);
			$('<td></td>').appendTo(bodyTr);
		}
		$(target).append(thead.append(headTr));

		for (var i = 0, len = data.length; i < len; i++) {
			var tr = bodyTr.clone();
			var tds = tr.find('td');

			var row = data[i];
			for (j = 0; j < title.length; j++) {
				tds.eq(j).text(row[title[j]]);
			}
			tbody.append(tr);
		}
		$(target).append(tbody);
	},
	init : function(target, title, data) {
		var temp = document.createDocumentFragment();
		for (var i = 0, len = data.length; i < len; i++) {
			if (i === 0) {
				var tr = document.createElement('tr');
				for (var j = 0; j < title.length; j++) {
					var th = document.createElement('th');
					var node = document.createTextNode(title[j]);
					th.appendChild(node);
					tr.appendChild(th);
					temp.appendChild(tr);
				}
			}
			tr = document.createElement('tr');
			var row = data[i];
			for (var j = 0; j < title.length; j++) {
				var td = document.createElement('td');
				node = document.createTextNode(row[title[j]]);
				td.appendChild(node);
				tr.appendChild(td);
				temp.appendChild(tr);
			}
		}
		target.appendChild(temp);
	}
};