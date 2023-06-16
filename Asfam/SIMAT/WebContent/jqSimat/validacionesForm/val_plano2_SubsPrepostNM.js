//funciones que validan formularios de jsp tabla_plano2_subsPrepostNM

/*

mes_informacion
codigo_entidad
agencia_entidad
rut_empleador
nombre_empleador
run_beneficiario
nombre_beneficiario
nro_licencia
codigo_diagnostico
vinculo_beneficiario_menor
actividad_laboral_trabajador
cod_comuna_beneficiario
nro_resolucion
extension_postnatal
nro_nacimientos
num_dias_licencia_autorizados
fecha_inicio_reposo
fecha_termino_reposo
num_dias_sub_pagadados
tipo_de_pago
monto_sub_dfl44_1978
monto_sub_pagado
tipo_emision
mes_nomina
mod_pago
serie_documento
num_documento
fecha_emision_documento
monto_documento
codigo_banco
cta_cte
causal_emision
num_dias_cotiz_pagados
monto_renum_imponible
monto_fp
monto_salud
monto_salud_ad
monto_desahucio
monto_cotiz_fu
monto_cotiz_sc
entidad_previsional
subsidio_iniciado
indicador_convenio
*/

	function validarSubsPrepostNM(tipoForm){
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
	   	
	   	campo="agencia_entidad";
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
	   	
	   	campo="nro_licencia";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="codigo_diagnostico";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="vinculo_beneficiario_menor";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="actividad_laboral_trabajador";
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
	  
	  //nro_resolucion: puede ser vacio, string, no necesita validacion tipo dato.
	  
	   	
	  //extension_postnatal: puede ser vacio, es numerico.
	   	campo="extension_postnatal";			   	
	   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
	  	}else{
	  		//si no esta vacio, requiere evaluacion de solo numero
	  		if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
	   		}else{
	   			key=true;
	   		}
	  	}
	  	marcarCampo(tipoForm,campo,key);
	   		   	
	   	campo="nro_nacimientos";
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
	   	
	   	campo="num_dias_sub_pagadados";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="tipo_de_pago";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_sub_dfl44_1978";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="monto_sub_pagado";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	//tipo_emision: puede ser vacio, string, no necesita validacion tipo dato
	  	
	//mes_nomina, puede ser nulo, string tipo fecha periodo.
		campo="mes_nomina";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
	  	}else{
	  		if(valFormaDateAM($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
	   		}else{
	   			key=true;
	   		}
	  	}
	  	marcarCampo(tipoForm,campo,key);
	   	
	 //mod_pago: puede ser vacio, necesita evaluacion tipo dato numerico
	   	campo="mod_pago";
	   	if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
	  	}else{
	  		if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
	   		}else{
	   			key=true;
	   		}
	  	}
	  	marcarCampo(tipoForm,campo,key);
	   	
	   //serie_documento: puede ser vacio, String , no necesita validacion.
	   	campo="serie_documento";
	   
	   //num_documento: puede ser vacio, String , no necesita validacion.
	   	campo="num_documento";
	   	
	   //fecha_emision_documento: puede ser vacio, requiere validacio formato fecha.
		campo="fecha_emision_documento";
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
	  
	  //monto_documento: puede ser vacio, vlidacion tipo numerico
	   	campo="monto_documento";
		if(valNull($("#"+tipoForm).find("#"+campo).val())){
			key=true;		
	  	}else{
	  		if(valNumerico($("#"+tipoForm).find("#"+campo).val())){
				key=false;
				keyAux=false;
	   		}else{
	   			key=true;
	   		}
	  	}
	  	marcarCampo(tipoForm,campo,key);
	   
	   //puede ser vacio, no requiere validacion tipo string.
	   	campo="codigo_banco";
	   	
	   //cta_cte: puede ser vacio, no requiere validacion tipo string.
	   	campo="cta_cte";
		
	   	
	   	campo="causal_emision";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="num_dias_cotiz_pagados";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_renum_imponible";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_fp";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_salud";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_salud_ad";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="monto_desahucio";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_cotiz_fu";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="monto_cotiz_sc";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
	   	campo="entidad_previsional";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);
	   	
		campo="subsidio_iniciado";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}
	   	marcarCampo(tipoForm,campo,key);   	
	   	
	   	campo="indicador_convenio";
		if(valNull($("#"+tipoForm).find("#"+campo).val()) || valNumerico($("#"+tipoForm).find("#"+campo).val())){
			key=false;
			keyAux=false;
	   	}else{
	   		key=true;
	   	}	   	
	   	marcarCampo(tipoForm,campo,key);
	   	
	    return keyAux;
	}

//evaluaciones genericas---------------------------------------------------------------------------------------------------------

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

        

