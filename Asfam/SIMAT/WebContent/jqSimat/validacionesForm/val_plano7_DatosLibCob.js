//funciones que validan formularios de jsp tabla plano7_DatosLibCob

/*
mes_informacion
codigo_entidad
nro_licencia
run_beneficiario
nombre_beneficiario
edad
sexo
fecha_emision_licencia
fecha_inicio_reposo
fecha_termino_reposo
nro_dias_licencia
num_dias_licencia_autorizados
run_menor_enfermo
nombre_menor_enfermo
fecha_nac_menor_enfermo
cod_tipo_licencia
cod_comuna_beneficiario
run_profesional
nombre_profesional
registro_colegio_profesional
codigo_diagnostico
rut_empleador
nombre_empleador
calidad_trabajador
*/

	function validarDatosLibCob(tipoForm){
		var key=true;
		var keyAux=true;
	
	//validar campos
	
		campo="mes_informacion";
		if(valFormaDateAM($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="codigo_entidad";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="nro_licencia";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="run_beneficiario";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="nombre_beneficiario";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="edad";
		if(valEdad($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="sexo";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="fecha_emision_licencia";
		if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="fecha_inicio_reposo";
		if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="fecha_termino_reposo";
		if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="nro_dias_licencia";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="num_dias_licencia_autorizados";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   			
	   //run_menor_enfermo:puede ser vacio, tipo string, requiere validacion formato rut.
	   	campo="run_menor_enfermo";
	   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
		  }else{
		  	if(valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
		   	}else{
		   		key=true;
		   	}
		  }
		  marcarCampo(tipoForm,campo,key);
	   	
	   	//: puede ser vacio, tipo string, no requiere validacion
		campo="nombre_menor_enfermo";
	   	
	   //fecha_nac_menor_enfermo: puede ser vacio, requiere validacion formato date iso
		campo="fecha_nac_menor_enfermo";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
		  }else{
		  	if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
		   	}else{
		   		key=true;
		   	}
		  }
		  marcarCampo(tipoForm,campo,key);
		
		campo="cod_tipo_licencia";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="cod_comuna_beneficiario";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="run_profesional";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="nombre_profesional";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="registro_colegio_profesional";
		/*
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		*/
		campo="codigo_diagnostico";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="rut_empleador";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="nombre_empleador";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="calidad_trabajador";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
	    return keyAux;
	}


//evaluaciones genericas-----------------------------------------------------------------------------------

	function valNull(valor){
	//retorna true si el valor contiene null o vacio
		var key=false;
	
		if(valor=="" || valor==null){
			key=true;
	   	}	
		return key;
	}
	
	function valNumerico(valor){
	//retorna true si el valor contiene letras.
		var key=false;
		if (!/^([0-9])*$/.test(valor)){
			key=true;
	   	}
		return key;
	}
	
	function valEdad(valor){
//retorna true si el valor contiene letras o es menor que quince o mayor a 89
	var key=false;
	
	if (!/^([1][5-9]|[2-8][0-9])$/.test(valor)){
		key=true;
   	}
	return key;
}
	
	function valLetras(valor){
	
		var key=true;
		if (!/^[a-zA-Z]*$/.test(valor)){
			key=false;
	   	}
		return key;
	}
	
	function valFormaDateAM(valor){
	//retorna true si el valor contiene letras o contiene un mes no valido
		var key=false;
		
		if (!/^\d{4}(0[1-9]|1[012])$/.test(valor)){
			key=true;
	   	}
		return key;
	}
	
	function valFormaDateISO(valor){
	//retorna true si el valor(en formato) no es fecha ISO (aaaa-MM-dd)
	var key=true;
	
	if (/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/.test(valor)){
			key=false;
	   	}
	return key;
	}
	
function valFormatoRut(valor){
	//retorna false si el valor no tiene formato rut, 999999999-9 (y no menores a 1 millon "1000000-k")
	//o si su digito verificador no corresponde.
	var aux=true;	
	if (/^\b\d{7,9}\-[K|k|0-9]$/.test(valor) && RutMod11(valor)){
		aux=false;
   	}	
	return aux;
}//end valFormatoRut
	
	
	
	function marcarCampo(tipoForm,campo,key){
		if(key){
			$("#"+tipoForm).find("#"+campo).css({'background-color' : 'white'});
			$("#"+tipoForm).find("#"+campo+"Marca").text("");
		}else{
			$("#"+tipoForm).find("#"+campo+"Marca").text("*");
			$("#"+tipoForm).find("#"+campo).val("");		
			$("#"+tipoForm).find("#"+campo).css({'background-color' : '#fef1ec'});
		}
		return true;
	}

        

