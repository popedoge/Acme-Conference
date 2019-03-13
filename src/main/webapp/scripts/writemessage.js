function checkuser(userstring) {
	$.ajax({
		url : 'messaging/fetch.do',
		type : 'GET',
		data : {
			username : userstring
		},
		complete : function(response) {
			var id = response.responseText;
			if (id != 0) {
				$('#confirmed').append(
						'<div id="' + id + '"onlick="remove(this)" style="border:2px solid #cccccc;width:200px;margin:2px 2px 2px 2px"><span>' + search.value
								+ '</span><i  class="fa fa-times" aria-hidden="true"></i></div>');
				confirmedrecipients.push(id);
				search.value = '';
				updaterecipients();
				var newrecipient = document.getElementById(id);
				newrecipient.addEventListener("click", function(e) {
					newrecipient.remove();
					removeid(id);
					updaterecipients();
				});
			}
		}
	});
}
var confirmedrecipients = new Array();
var search = document.getElementById("recipientsearch");
var addButton = document.getElementById("add-button");
addButton.addEventListener("click", function(e) {
	validate();
});

function updaterecipients() {
	var field = document.getElementById("recipientsfield");
	field.value = confirmedrecipients.toString();
}

function removeid(num) {
	var index = confirmedrecipients.indexOf(num);
	if (index > -1) {
		confirmedrecipients.splice(index, 1);
	}
}

function validate() {
	var text = search.value;
	checkuser(text);
}
