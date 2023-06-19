"use strict";


var validarPaso1 = function validarPaso1(opcion) {
	$('#opcion').val(opcion);
	if(opcion=='email'){
		validarFormulario('paso1', {
			texto : 'Debes ingresar ',
			otrosCampos : {
			
			},
			exito : function exito() {
				fln.preloader(1);
				/*
				 * Ingresar metodo para procesar el formulario aquÃ­ Puede ser:
				 * $('#paso1').submit(); O un AJAX, depende de como lo integrarÃ¡n;
				 */
				$('#paso1').submit();
			}
		});
	}else if(opcion=='descargar'){
		setTimeout("window.location='/AporteFamiliarWeb/finalizar.do'", 4000);
		$('#paso1').submit();
	}
	

};


var validarPaso2 = function validarPaso2() {
	
	if($('#rut').val()!=''){
		fln.preloader(1);
		$('#paso2').submit();
	}
	
};


var validarFecha = function validarFecha(fechaParam){
			
		var error=false;

	      // La longitud de la fecha debe tener exactamente 10 caracteres
	      if ( fechaParam.length !== 10 ){
	    	  return false;
	      }
	      // Primero verifica el patron
	      if ( !/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(fechaParam) ){
	    	  return false;
	      }
	      // Mediante el delimitador "/" separa dia, mes y año
	      var fecha = fechaParam.split("/");
	      var day = parseInt(fecha[0]);
	      var month = parseInt(fecha[1]);
	      var year = parseInt(fecha[2]);

	      // Verifica que dia, mes, año, solo sean numeros
	      error = ( isNaN(day) || isNaN(month) || isNaN(year) );
	      if(error){
	    	  return false;
	      }

	      // Lista de dias en los meses, por defecto no es año bisiesto
	      var ListofDays = [31,28,31,30,31,30,31,31,30,31,30,31];
	      if ( month === 1 || month > 2 )
	         if ( day > ListofDays[month-1] || day < 0 || ListofDays[month-1] === undefined )
	        	 return false;

	      // Detecta si es año bisiesto y asigna a febrero 29 dias
	      if ( month === 2 ) {
	         var lyear = ( (!(year % 4) && year % 100) || !(year % 400) );
	         if ( lyear === false && day >= 29 )
	        	 return false;
	         if ( lyear === true && day > 29 )
	        	 return false;
	      }


	      return true;
	
};

function validarMayor18Menor65(date){
    var x=new Date();
    var mayor18 = new Date(x.getFullYear()-18, x.getMonth(), x.getDate());
    var menor65 = new Date(x.getFullYear()-65, x.getMonth(), x.getDate());

    var fecha = date.split("/");
    x.setFullYear(fecha[2],fecha[1]-1,fecha[0]);
    
    if (x >= mayor18 || x<menor65)
      return false;
    else
      return true;
}

function validarRut(valor) {
	
	valor=valor.replace(/\D/g, '');
	if(valor.indexOf('-')==-1){
		valor= valor.substr(0, valor.length - 1) + '-' + valor.substr(valor.length - 1);
	}
	if (valor.length <6){
		return false;
	}
	var estructuraRut = /^0*(\d{1,3}(\.?\d{3})*)\-([\dkK])$/;
	if (valor.match(estructuraRut) == null) {
		return false;
	} 

	var tmp = valor.split('-');
	var digv = tmp[1];
	var rut = tmp[0];
	if (digv == 'K') {
		digv = 'k';
	}
	var digesto = dv(rut);
	if (digesto == digv) {
		return true;
	} else {
		return false;

	}
}

function dv(T) {
	var M = 0, S = 1;
	for (; T; T = Math.floor(T / 10)) {
		S = (S + T % 10 * (9 - M++ % 6)) % 11;
	}
	return S ? S - 1 : 'k';
}


