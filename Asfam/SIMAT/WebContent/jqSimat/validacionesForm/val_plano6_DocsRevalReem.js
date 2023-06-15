//funciones que validan formularios de jsp tabla plano6_DocsRevalReem

/*
mes_informacion
codigo_entidad
tiposubsidio
mod_pago_original
codigo_banco_original
serie_documento_original
num_documento_original
fecha_emision_documento_original
monto_documento_original
estado_documento_original
modo_pago_nuevo
codigo_banco_nuevo
serie_documento_nuevo
num_documento_nuevo
fecha_emision_documento_nuevo
monto_documento_nuevo
*/

	function validarDocsRevalReem(tipoForm){
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
	   	
		campo="tiposubsidio";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);

		campo="mod_pago_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="codigo_banco_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="serie_documento_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="num_documento_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="fecha_emision_documento_original";
		if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="monto_documento_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="estado_documento_original";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="modo_pago_nuevo";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="codigo_banco_nuevo";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="serie_documento_nuevo";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="num_documento_nuevo";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="fecha_emision_documento_nuevo";
		if(valFormaDateISO($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
		
		campo="monto_documento_nuevo";
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

        

