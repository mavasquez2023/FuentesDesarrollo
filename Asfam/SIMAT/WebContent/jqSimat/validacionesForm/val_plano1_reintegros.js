//funciones que validan formularios de jsp tabla_plano1 reintegros

/*
		mes_informacion   	
   		codigo_entidad
	  	mes_nomina
	  	run_beneficiario
	  	nombre_beneficiario
	  	tipo_subsidio
	  	nro_licencia
	  	rut_empleador
	  	nombre_empleador
	  	origen_reintegro	  	
	  	monto_total_a_reintegrar
	  	monto_recuperado
	  	monto_recuperado_acum
	  	total_saldo_a_reintegrar
   	*/

function validarReintegros(tipoForm){
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
   	
   	//mes_nomina, pueder ser nulo. en formato fecha periodo "aaaamm"
   	
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
	
	campo="tipo_subsidio";
	if(valNull($("#"+tipoForm).find("#"+campo).val())){
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
	
	campo="origen_reintegro";
	if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
	marcarCampo(tipoForm,campo,key);
	
	campo="monto_total_a_reintegrar";
   	if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);
   	
   	campo="monto_recuperado";
	if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);
   	
   	campo="monto_recuperado_acum";
	if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);
   	
   	campo="total_saldo_a_reintegrar";
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
//
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

        

