//[validacion login].----------------------------------------
	//funciones 

function validarLogin(){
	var	tipoForm="formLogin";
	var name= $("#"+tipoForm).find("input[name='nombre']").val();
    var pass= $("#"+tipoForm).find("input[name='clave']").val();
    var key=true;
    var keyAux=true;
    
    $("#formLogin").find("#claveMarca").text("");
    $("#formLogin").find("#nombreMarca").text("");    
    colorFondo("formLogin","nombre",true);
    colorFondo("formLogin","clave",true);
    $("#msgLogin").remove();
	
	campo="nombre";
	if(valNull($("#"+tipoForm).find("#"+campo).val()) || valFormatoRut($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);
   		
   	campo="clave";
	if(valNull($("#"+tipoForm).find("#"+campo).val())){
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);

    if(keyAux){
		document["formLogin"].submit();
    }     
}//end validarLogin

function colorFondo(tipoForm,campo,key){
	if(key){
		$("#"+tipoForm).find("#"+campo).css({'background-color' : 'white'});
	}else{
		$("#"+tipoForm).find("#"+campo).css({'background-color' : '#fef1ec'});
	}
}

function limpiarLogin(){
$("#msgLogin").remove();
	colorFondo("formLogin","clave",true);
	colorFondo("formLogin","nombre",true);
	$("#formLogin").find("input[name='nombre']").val("");
    $("#formLogin").find("input[name='clave']").val("");
	$("#formLogin").find("#claveMarca").text("");
    $("#formLogin").find("#nombreMarca").text("");
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

function valNull(valor){
//retorna true si el valor contiene null o vacio
	var key=false;
	if(valor=="" || valor==null){
		key=true;
   	}	
	return key;
}//end valNull

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

//[on key enter press].-------------------------------------------------------------------------------------------------------------
$(document).ready(function(){
	$("#formLogin").find("input[name='nombre']").keypress(
		function(e) {
			if (e.which == 13) {
				$("#formLogin").find("input[name='clave']").focus();
				
			}
		}
	)
	$("#formLogin").find("input[name='clave']").keypress(
		function(e) {
			if (e.which == 13) {
				validarLogin();
			}
		}
	)
	
	
});















