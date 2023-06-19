"use strict";

// Captcha Google
var recaptchaCallback = function recaptchaCallback(response) {
	$('.captcha').removeClass('invalido');
	$('.captcha').addClass('valido');
};

var validarPaso1 = function validarPaso1() {
	var captchaValido = grecaptcha.getResponse() ? true : false;

	var serieValido = validarFormatoSerie() ? true : false;
	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Captcha' : [ captchaValido, $('#paso1').find('.captcha') ],

		},	
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso1').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso1').submit();
		}
	});
};

function report() {

	document.getElementById('form-report').submit();

}

function reportAprobado(){
	
	$('#form-aprobado').submit();
}

var validarPaso2 = function validarPaso2() {

	var celularValido= true;
	var codigoValido= true;
	
	//Validaci�n celular
	var celular= $("#celular").val();
	if(celular.length!=9){
		celularValido=false;
	}
	
	if(isNaN(celular)){
		celularValido=false;
	}
	
	//Validacion prefijo celular
	var listacel= $('#lista_prefijos_celulares').val();
	var prefijocel= ", " + celular.substr(0, 2) + ",";
	if(listacel.indexOf(prefijocel)==-1){
		celularValido=false;
	}
	
	var digito_anterior=celular[1];
	var iguales=true;
	var digito=9;
	//Validando celular no repita los n�meros
	for (var i=2;i<celular.length;i++) { 
		digito=celular[i];
		if(digito!=digito_anterior){
			iguales=false;
			break;
		}
		digito_anterior=digito;
	}
	if(iguales==true){
		celularValido=false;
	}
	
	//Validaci�n c�digo
	var codigo= $("#codigosms").val();	
	if(codigo.length!=6){
		codigoValido=false;
	}
	
	if(isNaN(codigo)){
		codigoValido=false;
	}
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Celular V�lido':  [celularValido, $('#paso2').find('.celular')],
			'C�digo V�lido':  [codigoValido, $('#paso2').find('.codigosms')],
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

var validarPaso3 = function validarPaso3(){
	
	validarFormulario('paso3', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			

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
	
}

var validarPaso3_2 = function validarPaso3_2() {

	var email= $('#email').val();
	$('#emailDescarga').val(email);

	validarFormulario('paso3', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso3_2').submit();
		}
	});
};


var validarPaso3_3 = function validarPaso3_3(){
	
	$('#correo').val($('#email').val());
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso3_3').submit();
		}
	});
	
	
}

 

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