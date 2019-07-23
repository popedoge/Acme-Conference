var phoneinput = document.getElementById("phone-input");
var cc = document.getElementById("cc");
var form = document.getElementById("regForm");
var regex = new RegExp("^(\\+\\d{1,3})?\\s?(\\(\\d{1,3}\\))?\\d{4,}$");
var regexpn = new RegExp("^\\d{4,}$");

if (form.addEventListener) {
	form.addEventListener("submit", function(evt) {
		validatePhone(evt);
	}, true);
} else {
	form.attachEvent('onsubmit', function(evt) {
		validatePhone(evt);
	});
}

function validatePhone(evt) {
	if (checkPhone()) {
		return true;
	} else {
		evt.preventDefault();
		return false;
	}
}

function checkPhone() {
	var res = regex.test(phoneinput.value);
	if (!res) {
		if (confirm('Are you sure you want to save with this phone format?')) {

			res = true;
		}
	} else {
		if (regexpn.test(phoneinput.value)) {
			cc.value = true;
		}
	}
	return res;
}
