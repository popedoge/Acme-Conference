$(document).ready(function() {
	$("tr").each(function(index) {
		var aux, sum;

		if (index > 0) {

			aux = this.cells[2].textContent;
			sum = aux[4] + aux[5] + aux[9] + aux[10] + aux[2] + aux[3];

			var formatDate = moment(sum, "MMDDYY");

			console.log(formatDate);
			var currentDate = moment();
			console.log(currentDate);

			var difference = moment.duration(currentDate.diff(formatDate)).asMonths();
			console.log(difference);
			if (difference < 1) {
				$(this).css('background-color', 'MediumSlateBlue');
			} else if (difference > 1 && difference < 2) {
				$(this).css('background-color', 'Coral');
			} else {
				$(this).css('background-color', 'DimGrey');
			}
		}

	});
});
