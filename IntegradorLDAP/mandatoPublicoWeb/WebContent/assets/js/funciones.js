"use strict";

//Captcha Google
var recaptchaCallback = function recaptchaCallback(response) {
	$('.captcha').removeClass('invalido');
	$('.captcha').addClass('valido');
};

var validarPasoEjecutivo = function validarPasoEjecutivo() {
		var serieValido=true;
		if($('#serie').val()!=""){
			serieValido= validarFormatoSerie() ? true : false; 
		}

	  validarFormulario('paso1', {
	    texto: 'Debe ingresar ',
	    otrosCampos: {
	      'N�mero de Serie v�lido': [serieValido, $('#paso1').find('.serie')]
	    },
	    exito: function exito() {
	    	fln.preloader(1);
	      /*
	        Ingresar metodo para procesar el formulario aquí
	        Puede ser:
	        $('#paso1').submit();
	        O un AJAX, depende de como lo integrarán;
	      */
	    	$('#paso1').submit();
	    }
	  });
	};
	
var validarPaso1 = function validarPaso1() {
	
	
	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			
			

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


function report(){

	document.getElementById('form-report').submit();
	
}
 

	 

var validarPaso2 = function validarPaso2() {
	
	var _emailValidos = false;

	if ($('#email').val() === $('#confirmar-email').val()
			&& $('#email').val().length && $('#confirmar-email').val().length) {
		_emailValidos = true;
	}
	var bancoSelect = $("#banco").val()!="" ? true: false;	
	var tipoCuentaSelect = $("#tipo-cuenta").val()!="" ? true : false;
	var cuenta_numerico = !isNaN($("#numero_cuenta").val());
	if(cuenta_numerico){
		if($("#numero_cuenta").val().length<5 || $("#numero_cuenta").val().length>17 ){
			cuenta_numerico=false;
		}
	}
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Coincidir Emails' : [ _emailValidos,
					$('#paso2 .grupo-emails input') ],
			'Banco': [ bancoSelect,
				$('#paso2').find('.banco')],
			'Tipo cuenta': [ tipoCuentaSelect,
					$('#paso2').find('.tipo-cuenta')],
			'N�mero Cuenta V�lido': [cuenta_numerico,
			        $('#paso2').find('.numero_cuenta')]
			 
		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			fln.preloader(1);
			$('#paso2').submit();
		}
	});
};

var validarPaso2_1 = function validarPaso2() {
	 
	var _check = false;

	if ($('#terminos').is(':checked')) {
		_check = true;
	}
 

	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			
		'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			fln.preloader(1);
			$('#paso2').submit();
		}
	});
};

function deleteMandato(id){
	fln.preloader(1);
	$('#id_mandato').val(id);
	$('#listado').submit();
}
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