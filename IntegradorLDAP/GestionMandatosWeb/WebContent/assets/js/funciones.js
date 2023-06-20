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
	      'N˙mero de Serie v·lido': [serieValido, $('#paso1').find('.serie')]
	    },
	    exito: function exito() {
	    	fln.preloader(1);
	      /*
	        Ingresar metodo para procesar el formulario aqu√≠
	        Puede ser:
	        $('#paso1').submit();
	        O un AJAX, depende de como lo integrar√°n;
	      */
	    	$('#paso1').submit();
	    }
	  });
	};

	var validarRechazo = function validarRechazo() {

	  validarFormulario('subirRechazo', {
	    texto: 'Debe ingresar ',
	    otrosCampos: {
	    },
	    exito: function exito() {
	    	fln.preloader(1);
	      /*
	        Ingresar metodo para procesar el formulario aqu√≠
	        Puede ser:
	        $('#paso1').submit();
	        O un AJAX, depende de como lo integrar√°n;
	      */
	    	$('#subirRechazo').submit();
	    }
	  });
	};
	
var validarPaso1 = function validarPaso1() {
	 var captchaValido = grecaptcha.getResponse() ? true : false;
	 var serieValido=true;
		if($('#serie').val()!=""){
			serieValido= validarFormatoSerie() ? true : false; 
		}
	validarFormulario('paso1', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			 'N˙mero de Serie v·lido': [serieValido, $('#paso1').find('.serie')],
			 'Captcha': [captchaValido, $('#paso1').find('.captcha')]

		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aqu√≠ Puede ser:
			 * $('#paso1').submit(); O un AJAX, depende de como lo integrar√°n;
			 */
			fln.preloader(1);
			$('#paso1').submit();
		}
	});
};


function report(){

	document.getElementById('form-report').submit();
	
}
 

	 

var validarPaso2 = function validarPaso2() {
	
	var _emailValidos = false;
	var telefonoValido= true;
	var celularValido= true;
	var cuentaValida= false;
	
	//Validacion emails
	if ($('#email').val() === $('#confirmar-email').val()
			&& $('#email').val().length && $('#confirmar-email').val().length) {
		_emailValidos = true;
	}
	//ValidaciÛn telefono
	var telefono= $("#telefono").val();
	if(telefono!= ""){
		if(telefono.length!=9){
			telefonoValido=false;
		}

		if(isNaN(telefono)){
			telefonoValido=false;
		}

		//Validacion prefijo telefono
		var listatel= $('#lista_prefijos_telefonos').val();
		var prefijotel= ", " + telefono.substr(0, 3) + ",";
		if(listatel.indexOf(prefijotel)==-1){
			telefonoValido=false;
		}

		//Validando telefono no repita los n˘meros
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
	
	//ValidaciÛn celular
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
	
	digito_anterior=celular[1];
	iguales=true;
	//Validando celular no repita los n˘meros
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
	
	//ValidaciÛn Banco
	var bancoSelect = $("#banco").val()!="" ? true: false;
	
	//ValidaciÛn Tipo Cuenta
	var tipoCuentaSelect = $("#tipo-cuenta").val()!="" ? true : false;
	if(tipoCuentaSelect){
		if($('#tipo-cuenta').val()=='3' && $('#banco').val()!='12'){
			tipoCuentaSelect= false;
		}
	}
	
	//ValidaciÛn Cuenta
	var cuenta = $("#numero_cuenta").val();
	var cuentaValida = /^[0-9]{6,17}/.test(cuenta);
	var posicion = $('#rut').val().indexOf("-");
	var rut= $('#rut').val().substr(0, posicion);
	var rutdv= rut + $('#rut').val().substr(posicion+1);
	
	if(cuentaValida){
		if(cuenta.length<6 || cuenta.length>17 ){
			cuentaValida=false;
		}else if((cuenta==rut || cuenta== rutdv) && $('#tipo-cuenta').val()!='3'){
			cuentaValida=false;
		}else if(cuenta!= rut && $('#tipo-cuenta').val()=='3' && $('#banco').val()=='12'){
			cuentaValida=false;
		}
	}
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Celular V·lido':  [celularValido,
			      	$('#paso2').find('.celular')],
			'TelÈfono Fijo V·lido':  [telefonoValido,
			      	$('#paso2').find('.telefono')],
			'Coincidir Emails' : [ _emailValidos,
					$('#paso2 .grupo-emails input') ],
			'Banco': [ bancoSelect,
				$('#paso2').find('.banco')],
			'Tipo Cuenta V·lido': [ tipoCuentaSelect,
					$('#paso2').find('.tipo-cuenta')],
			'N˙mero Cuenta V·lido': [cuentaValida,
			        $('#paso2').find('.numero_cuenta')]
			 
		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			/*
			 * Ingresar metodo para procesar el formulario aqu√≠ Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrar√°n;
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
			 * Ingresar metodo para procesar el formulario aqu√≠ Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrar√°n;
			 */
			fln.preloader(1);
			$('#paso2').submit();
		}
	});
};

function setCuentaRut(){
	if($('#banco').val()=='12' && $('#tipo-cuenta').val()=='3'){
		var posicion = $('#rut').val().indexOf("-");
		$('#numero_cuenta').val($('#rut').val().substr(0, posicion));
		$('#numero_cuenta').focus();
		$('#continuar').focus();
	}
}

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
			 * Ingresar metodo para procesar el formulario aqu√≠ Puede ser:
			 * $('#recuperarClavePaso1').submit(); O un AJAX, depende de como lo
			 * integrar√°n;
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
			 * Ingresar metodo para procesar el formulario aqu√≠ Puede ser:
			 * $('#recuperarClavePaso2').submit(); O un AJAX, depende de como lo
			 * integrar√°n;
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
				_pattern = /^[9]{1}[0-9]{8,9}/;
				_max = 9;
			} else if (_dataPrefijoTipo === 'fijo') {
				_pattern = /^[2-7]{1}[0-9]{8,9}/;
				_max = 9;
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