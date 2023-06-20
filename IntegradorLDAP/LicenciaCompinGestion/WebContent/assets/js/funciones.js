"use strict";

var validarPaso1 = function validarPaso1() {
	
	validarFormulario('paso1', {
		
		texto : 'Debes ',
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

	
	validarFormulario('paso2', {
		texto : 'Debes ingresar ',
		otrosCampos : {

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

var validarPaso2DN = function validarPaso2DN() {

	
	validarFormulario('paso2DN', {
		texto : 'Debes ingresar ',
		otrosCampos : {

		// 'Aceptar condiciones': [_check, $('#terminos').parent()]
		},
		exito : function exito() {
			fln.preloader(1);
			/*
			 * Ingresar metodo para procesar el formulario aquí Puede ser:
			 * $('#paso2').submit(); O un AJAX, depende de como lo integrarán;
			 */
			$('#paso2DN').submit();
		}
	});
};

var validarPaso3 = function validarPaso3() {

	
	validarFormulario('paso3', {
		texto : 'Debes ingresar ',
		otrosCampos : {

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


