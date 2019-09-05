$(document).ready(function() {
	$("tr").each(function(index) {
		var aux, sum;

		if (index > 0) {

			aux = this.cells[1]

			sum = $(aux).find(".hidden-date")[0].textContent.trim();

			const date = new Date(sum.substring(0, 4), sum.substring(5, 6) - 1, sum.substring(6, 8));
			const now = new Date();

			const diffInMillis = now.getTime() - date.getTime();
			const difference = diffInMillis / (1000 * 60 * 60 * 24);
			console.log(now);
			console.log(date);
			console.log(difference);

			if (difference < 30) {
				$(this).css('background-color', 'MediumSlateBlue');
			} else if (difference > 30 && difference < 61) {
				$(this).css('background-color', 'Coral');
			} else {
				$(this).css('background-color', 'DimGrey');
			}
		}

	});
});
