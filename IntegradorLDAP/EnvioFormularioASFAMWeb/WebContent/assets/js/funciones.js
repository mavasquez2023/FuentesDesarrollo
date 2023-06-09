"use strict";

// Captcha Google
var recaptchaCallback = function recaptchaCallback(response) {
	$('.captcha').removeClass('invalido');
	$('.captcha').addClass('valido');
};

var validarPaso1_2 = function validarPaso1_2() {

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
			$('#documentacion').submit();
		}
	});

};

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

var validarPaso2 = function validarPaso2() {
	var telefonoValido= false;
	//Validaci�n telefono
	var telefono= $("#telefono").val();
	if(telefono.length==9){
		telefonoValido=true;
	}
	
	//Validaci�n Oficina
	var sucursalSelect = $("#sucursal option:selected").text().length > 2
			&& $("#sucursal option:selected").val() > 0 ? true : false;

	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Tel�fono V�lido':  [telefonoValido,
			      			      	$('#paso2').find('.telefono')],
			'Sucursal' : [ sucursalSelect, $('#paso2').find('.sucursal') ]

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

$('#solicitud').click(function() {

	$('#file_error1').html('');

});

$('#solicitud').change(
		function() {

			var index = $('#solicitud').val().lastIndexOf('\\');
			var len = $('#solicitud').val().length;

			$('#file_error1').html(
					'<p style="font-size:0.8em">'
							+ $('#solicitud').val().substring(index + 1, len)
							+ '</p>');
		});

var validarPaso3 = function validarPaso3() {

	// var _check = false;

	// if ($('#terminos').is(':checked')) {
	// _check = true;
	// }

	var imgVal = $('#solicitud').val();
	if (imgVal == '') {
		$('#file_error1')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar su solicitud de asignaci\u00F3n</p>");
		return false;
	}

	var imgVal = $('#certificado').val();
	if (imgVal == '') {
		$('#file_errorC')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar certificado de respaldo</p>");
		return false;
	}

	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
		// 'Aceptar condiciones' : [ _check, $('#terminos').parent() ],

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso3').submit();
		}
	});
};

$('#certificado').click(function() {

	$('#file_errorC').html('');

});

$('#certificado').change(
		function() {

			var index = $('#certificado').val().lastIndexOf('\\');
			var len = $('#certificado').val().length;

			$('#file_errorC').html(
					'<p style="font-size:0.8em">'
							+ $('#certificado').val().substring(index + 1, len)
							+ '</p>');
		});

var validarPaso4 = function validarPaso4() {

	var imgVal = $('#certificado').val();
	if (imgVal == '') {
		$('#file_errorC')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar certificado de respaldo</p>");
		return false;
	}

	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
		// 'Aceptar condiciones' : [ _check, $('#terminos').parent() ],

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso4').submit();
		}
	});
};
var validarPaso5 = function validarPaso5() {

	validarFormulario('paso5', {
		texto : 'Debe ingresar ',
		otrosCampos : {

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso5').submit();
		}
	});
};
var volverPaso2 = function volverPaso2() {

	$('#back-form').submit();

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