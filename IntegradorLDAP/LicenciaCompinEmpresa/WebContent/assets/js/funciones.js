"use strict";

var validarPaso1 = function validarPaso1(opcion) {
	
	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {			 

		},
		exito : function exito() {
			fln.preloader(1);
			$('#opcion').val(opcion);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso1').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso1').submit();
		}
	});
};

var validarPaso2 = function validarPaso2() {
var telefonoValido= true;
	
	//Validaci�n telefono
	var telefono= $("#telefono").val();
	if(telefono!= ""){
		if(telefono.length!=9){
			telefonoValido=false;
		}
		
		if(isNaN(telefono)){
			telefonoValido=false;
		}

		//Validando telefono no repita los n�meros
		var digito;
		var digito_anterior=telefono[0];
		var iguales=true;

		for (var i=1;i<telefono.length;i++) { 
			digito=telefono[i];
			if(digito!=digito_anterior){
				iguales=false;
				break;
			}
			digito_anterior=digito;
		}
		if(iguales==true){
			telefonoValido=false;
		}
	}
	
	var comunaSelect = $("#comuna option:selected").text().length > 2
			&& $("#comuna option:selected").val() > 0 ? true : false;
	var regionSelect = $("#region option:selected").text().length > 2 ? true
			: false;
	var folioparam= $('#folioLicencia').val();

	var _folio= (/^[0-9]{5,9}[-]{0,1}[0-9kK]{0,1}$/.test(folioparam ))?true:false;
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Celular V�lido':  [telefonoValido,
		      			      	$('#paso3').find('.telefono')],
			'Comuna' : [ comunaSelect, $('#paso1').find('.comuna') ],
			'Region' : [ regionSelect, $('#paso1').find('.region') ],
			'Folio V�lido': [_folio, $('#folioLicencia')]

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

var validarPaso2DocP = function validarPaso2DocP() {
	var folioparam= $('#folioLicencia').val();

	var _folio= (/^[0-9]{5,9}[-]{0,1}[0-9kK]{0,1}$/.test(folioparam ))?true:false;

	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {

			'Folio V�lido': [_folio, $('#folioLicencia')]
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

var validarPaso2DocP_2 = function validarPaso2DocP_2(folio) {
	fln.preloader(1);
	$('#folio').val(folio);
	$('#paso2').submit();
};

function volverPaso2() {
	$('#paso2').submit();
}

$('#medica').click(function() {

	$('#file_error1').html('');

});

$('#medica').change(
		function() {

			var index = $('#medica').val().lastIndexOf('\\');
			var len = $('#medica').val().length;

			$('#file_error1').html(
					'<p style="font-size:0.8em">'
							+ $('#medica').val().substring(index + 1, len)
							+ '</p>');
		});


$('#zonac').click(function() {

	$('#file_errorC').html('');

});

$('#zonac').change(
		function() {

			var index = $('#zonac').val().lastIndexOf('\\');
			var len = $('#zonac').val().length;

			$('#file_errorC').html(
					'<p style="font-size:0.8em">'
							+ $('#zonac').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta1').click(function() {

	$('#file_error2').html('');

});

$('#renta1').change(
		function() {

			var index = $('#renta1').val().lastIndexOf('\\');
			var len = $('#renta1').val().length;

			$('#file_error2').html(
					'<p style="font-size:0.8em">'
							+ $('#renta1').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta2').click(function() {

	$('#file_error3').html('');

});

$('#renta2').change(
		function() {

			var index = $('#renta2').val().lastIndexOf('\\');
			var len = $('#renta2').val().length;

			$('#file_error3').html(
					'<p style="font-size:0.8em">'
							+ $('#renta2').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta3').click(function() {

	$('#file_error4').html('');

});

$('#renta3').change(
		function() {

			var index = $('#renta3').val().lastIndexOf('\\');
			var len = $('#renta3').val().length;

			$('#file_error4').html(
					'<p style="font-size:0.8em">'
							+ $('#renta3').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta4').click(function() {

	$('#file_error5').html('');

});

$('#renta4').change(
		function() {

			var index = $('#renta4').val().lastIndexOf('\\');
			var len = $('#renta4').val().length;

			$('#file_error5').html(
					'<p style="font-size:0.8em">'
							+ $('#renta4').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta5').click(function() {

	$('#file_error6').html('');

});

$('#renta5').change(
		function() {

			var index = $('#renta5').val().lastIndexOf('\\');
			var len = $('#renta5').val().length;

			$('#file_error6').html(
					'<p style="font-size:0.8em">'
							+ $('#renta5').val().substring(index + 1, len)
							+ '</p>');
		});

$('#renta6').click(function() {

	$('#file_error7').html('');

});

$('#renta6').change(
		function() {

			var index = $('#renta6').val().lastIndexOf('\\');
			var len = $('#renta6').val().length;

			$('#file_error7').html(
					'<p style="font-size:0.8em">'
							+ $('#renta6').val().substring(index + 1, len)
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
	
	var imgVal = $('#documento').val();
	if (imgVal == '') {
		$('#file_error1')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar documento</p>");
		return false;
	}
	var sucursalSelect = $("#sucursal option:selected").text().length > 2
	&& $("#sucursal option:selected").val() > 0 ? true : false;
	
	var telefonoValido= true;
	
	//Validaci�n telefono
	var telefono= $("#telefono").val();
	if(telefono!= ""){
		if(telefono.length!=9){
			telefonoValido=false;
		}
		
		if(isNaN(telefono)){
			telefonoValido=false;
		}

		//Validando telefono no repita los n�meros
		var digito;
		var digito_anterior=telefono[0];
		var iguales=true;

		for (var i=1;i<telefono.length;i++) { 
			digito=telefono[i];
			if(digito!=digito_anterior){
				iguales=false;
				break;
			}
			digito_anterior=digito;
		}
		if(iguales==true){
			telefonoValido=false;
		}
	}
	
	validarFormulario('paso3', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Celular V�lido':  [telefonoValido,
			      			      	$('#paso3').find('.telefono')],
			'Oficina' : [ sucursalSelect, $('#paso3').find('.sucursal') ]
		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
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

var validarPaso3 = function validarPaso3() {

	var _checked = false;

	
	if ($('#maternal').is(':checked')) {
		_checked = true;
	}

	
	
	var imgVal = $('#medica').val();
	if (imgVal == '') {
		$('#file_error1')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar tu licencia</p>");
		return false;
	}
	
	var imgVal = $('#zonac').val();
	if (imgVal == '') {
		$('#file_errorC')
				.html(
						"<p style='color:red; font-size:0.8em'>Debes adjuntar Autorizacion trabajador</p>");
		return false;
	}
	
	/*
	var imgVal = $('#renta1').val();
	if (imgVal == '') {
		$('#file_error2')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 1</p>");
		return false;
	}
	var imgVal = $('#renta2').val();
	if (imgVal == '') {
		$('#file_error3')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 2</p>");
		return false;
	}
	var imgVal = $('#renta3').val();
	if (imgVal == '') {
		$('#file_error4')
				.html(
						"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 3</p>");
		return false;
	}

	if (_checked) {

		var imgVal = $('#renta4').val();
		if (imgVal == '') {
			$('#file_error5')
					.html(
							"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 4</p>");
			return false;
		}
		var imgVal = $('#renta5').val();
		if (imgVal == '') {
			$('#file_error6')
					.html(
							"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 5</p>");
			return false;
		}
		var imgVal = $('#renta6').val();
		if (imgVal == '') {
			$('#file_error7')
					.html(
							"<p style='color:red; font-size:0.8em'>Debe adjuntar su liquidaci\u00F3n 6</p>");
			return false;
		}
		
	}*/
	validarFormulario('paso3', {
		texto : 'Debe ingresar ',
		otrosCampos : {

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

$('#adicional').change(
		function() {

			var index = $('#adicional').val().lastIndexOf('\\');
			var len = $('#adicional').val().length;

			$('#file_errorA').html(
					'<p style="font-size:0.8em">'
							+ $('#adicional').val().substring(index + 1, len)
							+ '</p>');
		});

var validarPaso4 = function validarPaso4() {
	
	validarFormulario('paso4', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			 

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


	validarFormulario('recuperarClavePaso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
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

//validarSerie();

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

//validarTelefono();