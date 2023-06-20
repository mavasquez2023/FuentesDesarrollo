"use strict";



var validarPaso1 = function validarPaso1() {
	
	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso1').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso1').submit();
		}
	});
};

var validarPaso1_2 = function validarPaso1_2() {
	var comunaSelect = $("#comuna option:selected").text().length > 2
	&& $("#comuna option:selected").val() > 0 ? true : false;
	var regionSelect = $("#region option:selected").text().length > 2 ? true
	: false;

	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Comuna' : [ comunaSelect, $('#paso1').find('.comuna') ],
			'Region' : [ regionSelect, $('#paso1').find('.region') ],

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso1').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso1').submit();
		}
	});
};

var validarPaso2 = function validarPaso2() {
	
	var oficinaSelect = true;
	var comunaSelect = true;
	var regionSelect = true;
	if ($('#tipoDespacho1').is(':checked')) {
		oficinaSelect = $("#oficina option:selected").text().length > 2
		&& $("#oficina option:selected").val() > 0 ? true : false;
	}
	if ($('#tipoDespacho2').is(':checked')) {
		if (!$('#repetirDireccion').is(':checked')) {
			comunaSelect = $("#comuna option:selected").text().length > 2
			&& $("#comuna option:selected").val() > 0 ? true : false;
			regionSelect = $("#region option:selected").text().length > 2 ? true
			: false;
		}
	}
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Oficina' : [ oficinaSelect, $('#paso2').find('.oficina') ],
			'Comuna' : [ comunaSelect, $('#paso1').find('.comuna') ],
			'Region' : [ regionSelect, $('#paso1').find('.region') ],

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso2').submit();
		}
	});
};


function volverPaso2() {
	$('#paso2').submit();
}

$('#cedula').click(function() {

	$('#file_error1').html('');

});

$('#cedula').change(
		function() {

			var index = $('#cedula').val().lastIndexOf('\\');
			var len = $('#cedula').val().length;

			$('#file_error1').html(
					'<p style="font-size:0.8em">'
							+ $('#cedula').val().substring(index + 1, len)
							+ '</p>');
		});


$('#contrato').click(function() {

	$('#file_error2').html('');

});

$('#contrato').change(
		function() {

			var index = $('#contrato').val().lastIndexOf('\\');
			var len = $('#contrato').val().length;

			$('#file_error2').html(
					'<p style="font-size:0.8em">'
							+ $('#contrato').val().substring(index + 1, len)
							+ '</p>');
		});

$('#certemp').click(function() {

	$('#file_error3').html('');

});

$('#certemp').change(
		function() {

			var index = $('#certemp').val().lastIndexOf('\\');
			var len = $('#certemp').val().length;

			$('#file_error3').html(
					'<p style="font-size:0.8em">'
							+ $('#certemp').val().substring(index + 1, len)
							+ '</p>');
		});

$('#liqafc').click(function() {

	$('#file_error4').html('');

});

$('#liqafc').change(
		function() {

			var index = $('#liqafc').val().lastIndexOf('\\');
			var len = $('#liqafc').val().length;

			$('#file_error4').html(
					'<p style="font-size:0.8em">'
							+ $('#liqafc').val().substring(index + 1, len)
							+ '</p>');
		});

$('#certafp').click(function() {

	$('#file_error5').html('');

});

$('#certafp').change(
		function() {

			var index = $('#certafp').val().lastIndexOf('\\');
			var len = $('#certafp').val().length;

			$('#file_error5').html(
					'<p style="font-size:0.8em">'
							+ $('#certafp').val().substring(index + 1, len)
							+ '</p>');
		});

$('#documento').click(function() {

	$('#file_error1').html('');

});

$('#documento').change(
		function() {

			var index = $('#documento').val().lastIndexOf('\\');
			var len = $('#documento').val().length;

			$('#file_error1').html(
					'<p style="font-size:0.8em">'
							+ $('#documento').val().substring(index + 1, len)
							+ '</p>');
		});

var validarPaso3DocP = function validarPaso3DocP() {

	var sucursalSelect = $("#sucursal option:selected").text().length > 2
	&& $("#sucursal option:selected").val() > 0 ? true : false;
	
	var imgVal = $('#documento').val();
	if (imgVal == '') {
		$('#file_error1')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar documento</p>");
		return false;
	}
	
	validarFormulario('paso3', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Oficina' : [ sucursalSelect, $('#paso2').find('.comuna') ]

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso3').submit();
		}
	});
};

var validarPaso3 = function validarPaso3() {

	var _check = false;
	var _checked = false;

	if ($('#terminos').is(':checked')) {
		_check = true;
	}
	
	var imgVal = $('#cedula').val();
	if (imgVal == '') {
		$('#file_error1')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar copia C�dula de Identidad</p>");
		return false;
	}
	
	var imgVal = $('#contrato').val();
	if (imgVal == '') {
		$('#file_error2')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar Contrato Trabajo y Anexos</p>");
		return false;
	}
	var imgVal = $('#certemp').val();
	if (imgVal == '') {
		$('#file_error3')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar Certificado Empresa</p>");
		return false;
	}
	var imgVal = $('#liqafc').val();
	if (imgVal == '') {
		$('#file_error4')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar Liquidaci\u00F3n AFC</p>");
		return false;
	}
	var imgVal = $('#certafp').val();
	if (imgVal == '') {
		$('#file_error5')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar Certificado Cotizaciones AFP</p>");
		return false;
	}
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Aceptar condiciones' : [ _check, $('#terminos').parent() ],

		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso3').submit();
		}
	});
};

var validarRecuperarClavePaso1 = function validarRecuperarClavePaso1() {
	// var captchaValido = grecaptcha.getResponse() ? true : false;
	validarFormulario('recuperarClavePaso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {
		/*
		 * 'Captcha': [captchaValido,
		 * $('#recuperarClavePaso1').find('.captcha')]
		 */
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#recuperarClavePaso1').submit(); O un AJAX, depende de como lo
			 * integrarán;
			 */
			$('#recuperarClavePaso1').submit();
		}
	});
};

var validarRecuperarClavePaso2 = function validarRecuperarClavePaso2() {
	var _check = false;

	if ($('#terminos').is(':checked')) {
		_check = true;
	}

	validarFormulario('recuperarClavePaso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Aceptar condiciones' : [ _check, $('#terminos').parent() ]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#recuperarClavePaso2').submit(); O un AJAX, depende de como lo
			 * integrarán;
			 */
			$('#recuperarClavePaso2').submit();
		}
	});
};

function validaLargoSerie() {
	var primer = $("#serie").val().substring(0, 1);
	if (primer == 'A') {
		$('#serie').attr('maxlength', 10);
	} else {
		$("#serie").attr('maxlength', 9);
	}
}

function validarFormatoSerie() {
	var _camp = $("#serie").get(0);
	var _val = $("#serie").val();

	var _alpha = /^[aA]{1}[0-9]{8,9}/.test(_val);
	var _number = /^[0-9]{8,9}/.test(_val);

	if (_alpha || _number) {
		_camp.classList.remove('invalido');
		_camp.classList.add('valido');
		return true;
	} else {
		_camp.classList.add('invalido');
		_camp.classList.remove('valido');
		return false;
	}
}

var validarSerie = function validarSerie() {
	var _inputs = document.querySelectorAll('.tipo_serie');
	if (_inputs.length) {
		var _clearValidar;
		var _a = _inputs;

		var _f = function _f(el, i) {
			clearTimeout(_clearValidar);
			_clearValidar = setTimeout(function() {
				multipleEventsListeners(el, 'blur paste', function(event) {
					var _val = el.value;

					var _alpha = /^[aA]{1}[0-9]{8,9}/.test(_val);

					var _number = /^[0-9]{8,9}/.test(_val);

					if (_alpha || _number) {
						if (_alpha) {
							el.setAttribute('maxlength', 10);
						} else {
							el.setAttribute('maxlength', 9);
						}

						el.classList.remove('invalido');
						el.classList.add('valido');
					} else {
						el.classList.add('invalido');
						el.classList.remove('valido');
					}
				});
			}, 100);
		};

		for (var _i = 0; _i < _a.length; _i++) {
			_f(_a[_i], _i, _a);
		}

		undefined;
	}
};

validarSerie();

var validarTelefono = function validarTelefono() {
	var _inputs = document.querySelectorAll('.tipo_fono');

	if (_inputs.length) {
		var _clearValidar;

		var _a2 = _inputs;

		var _f2 = function _f2(el, i) {
			var _dataPrefijo = el.getAttribute('data-prefijo');

			var _dataPrefijoTipo = el.getAttribute('data-prefijo-tipo');

			var _blur = new Event('blur');

			var _pattern;

			var _max;

			if (_dataPrefijoTipo === 'celular') {
				_pattern = /^[+569]{1}[0-9]{11,12}/;
				_max = 12;
			} else if (_dataPrefijoTipo === 'fijo') {
				_pattern = /^[+56]{1}[0-9]{10,12}/;
				_max = 12;
			}

			if (!el.getAttribute('maxlength')) {
				el.setAttribute('maxlength', _max);
			}

			multipleEventsListeners(el, 'keyup blur paste', function(event) {
				var _val = el.value;

				var _patron = _pattern.test(_val);

				clearTimeout(_clearValidar);
				_clearValidar = setTimeout(function() {
					if (_patron) {
						el.classList.remove('invalido');
						el.classList.add('valido');
					} else {
						el.classList.add('invalido');
						el.classList.remove('valido');
					}
				});
			});
			multipleEventsListeners(el, 'focus paste', function(event) {
				var _val = el.value;

				if (_dataPrefijo && !el.value) {
					el.value = _dataPrefijo;
				} else if (el.value) {
					el.value = _val;
				} else {
					el.value = '';
				}
			});
			el.addEventListener('blur', function(event) {
				var _val = el.value;

				if (_dataPrefijo && el.value === _dataPrefijo) {
					el.value = '';
					setTimeout(function() {
						el.dispatchEvent(_blur);
					}, 10);
				} else {
					el.value = _val;
				}
			});
		};

		for (var _i2 = 0; _i2 < _a2.length; _i2++) {
			_f2(_a2[_i2], _i2, _a2);
		}

		undefined;
	}
};

validarTelefono();