$(document).ready(
		function() {
			$('.halamanlogin').fadeIn('slow');

			$('.datePicker').datepicker({
				format : "dd-mm-yyyy",
				todayBtn : true,
				autoclose : true,
				language : "id",
				todayHighlight : true
			});

			$('#formCari').submit(function(e) {
				e.preventDefault();
			});

			$('#stringCari').on('keypress', function(e) {
				if (e.which === 13) {
					$('#btnCari').click();
				}
			});

			$('#btnReset').click(function() {
				$('#stringCari').val('');
			});

			$.validator.addMethod("valueNotEquals", function(value, element,
					arg) {
				return arg != value;
			}, "Value must not equal arg.");

			$.validator.addMethod("greaterThan",
					function(value, element, param) {
						var $min = $(param);

						if (this.settings.onfocusout) {
							$min.off(".validate-greaterThan").on(
									"blur.validate-greaterThan", function() {
										$(element).valid();
									});
						}

						return parseInt(value) > parseInt($min.val());
					}, "Max must be greater than min");

			$.validator.addMethod("lessThan", function(value, element, param) {
				var $min = $(param);
				if (this.settings.onfocusout) {
					$min.off(".validate-lessThan").on("blur.validate-lessThan",
							function() {
								$(element).valid();
							});
				}
				return parseInt(value) < parseInt($min.val());
			}, "Max must be less than min");

			$.validator.addMethod("greaterEqualThan", function(value, element,
					param) {
				var $min = $(param);
				if (this.settings.onfocusout) {
					$min.off(".validate-greaterEqualThan").on(
							"blur.validate-greaterEqualThan", function() {
								$(element).valid();
							});
				}
				return parseInt(value) >= parseInt($min.val());
			}, "Max must be greater or equal than min");

			$.validator.addMethod("lessEqualThan", function(value, element,
					param) {
				var $min = $(param);
				if (this.settings.onfocusout) {
					$min.off(".validate-lessEqualThan").on(
							"blur.validate-lessEqualThan", function() {
								$(element).valid();
							});
				}
				return parseInt(value) <= parseInt($min.val());
			}, "Max must be less or equal than min");
			
			
		});

$.postJSON = function(url, data, callback, error) {
	return jQuery.ajax({
		'headers' : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		'type' : 'POST',
		'url' : url,
		'data' : JSON.stringify(data),
		'dataType' : 'json',
		'success' : callback,
		'error' : error
	});
};

$.postAjaxAsyncFalse = function(url, data, callback, error) {
	return jQuery.ajax({
		'headers' : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		'type' : 'POST',
		'async' : false,
		'url' : url,
		'data' : JSON.stringify(data),
		'dataType' : 'json',
		'success' : callback,
		'error' : error
	});
};

$.getAjax = function(url, data, callback, error) {
	return jQuery.ajax({
		'type' : 'GET',
		'url' : url,
		'data' : data,
		'success' : callback,
		'error' : error
	});
};

function setAutoComplete(e, url) {
	$(e).autocomplete({
		serviceUrl : url
	// onSelect: function (suggestion) {
	// alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
	// }
	});
}

function setMaskingUang(e) {
	$(e).maskMoney({
		thousands : '.',
		precision : 0,
		allowZero : true,
		selectAllOnFocus : true
	});
}

function tanggalHariIni() {
	var d = new Date();

	var month = d.getMonth() + 1;
	var day = d.getDate();

	var output = (day < 10 ? '0' : '') + day + '-' + (month < 10 ? '0' : '')
			+ month + '-' + d.getFullYear();

	return output;
}

function tambahTitik(nStr) {
    nStr += '';
    var x = nStr.split('.');
    var x1 = x[0];
    var x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + '.' + '$2');
    }
    return x1 + x2;
}
