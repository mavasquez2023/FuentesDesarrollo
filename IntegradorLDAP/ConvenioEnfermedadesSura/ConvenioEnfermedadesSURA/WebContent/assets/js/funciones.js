"use strict";

var validarPaso1 = function validarPaso1(opcion) {
		
	validarFormulario('paso1', {
		
		texto : 'Debes ingresar ',
		otrosCampos : {
		},
		exito : function exito() {
			fln.preloader(1);
			$('#paso1').submit();
		}
	});
};


function submitPaso3() {
	$('#paso3').submit();
}


var validarDatosContacto = function validarDatosContacto() {
	var _emailValidos = false;
	var telefonoValido= true;
	var celularValido= true;
	var i= 0;
	
	//Validacion emails
	if($('#email').val()!= $('#email_old').val()){
		if ($('#email').val() === $('#confirmar-email').val()
			&& $('#email').val().length && $('#confirmar-email').val().length) {
			_emailValidos = true;
		}
	}else{
		_emailValidos = true;
	}
	//Validación telefono
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

		//Validando telefono no repita los nùmeros
		var digito;
		var digito_anterior=telefono[0];
		var iguales=true;
	
	for (i=1;i<telefono.length;i++) { 
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
	
	//Validación celular
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
	//Validando celular no repita los nùmeros
	for (i=2;i<celular.length;i++) { 
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
	
	validarFormulario('paso2', {
		texto : 'Debe ingresar ',
		otrosCampos : {
			'Celular Válido':  [celularValido,
				$('#paso2').find('.celular')],
			'Teléfono Fijo Válido':  [telefonoValido,
				$('#paso2').find('.telefono')],
			'Coincidir Emails' : [ _emailValidos,
				$('#paso2 .grupo-emails input') ],

		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquÃ­ Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarÃ¡n;
			 */
			$('#paso2').submit();
		}
	});
};


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