
//funciones que validan formularios sobre tipo de datos (int / String)

//formuser:
//rut_user
//nombre_user
//ape_paterno
//ape_materno
//email_user

function validarForm_User(tipoForm){	
	var campo="";
	var key=true;
	var keyAux=true;	
	
	campo="rut_user";
   	if(isRut($("#"+tipoForm).find("#"+campo).val())){
   		//es rut.
   		key=true;
   		msg="";		
   	}else{
   		key=false;
		keyAux=false;
		msg=msgRut();
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="nombre_user";
   	if(isNotNull($("#"+tipoForm).find("#"+campo).val())){
   		key=true;
   		msg="";		
   	}else{
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ape_paterno";
   	if(isNotNull($("#"+tipoForm).find("#"+campo).val())){
   		key=true;
   		msg="";		
   	}else{
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	
   	campo="ape_materno";
   	if(isNotNull($("#"+tipoForm).find("#"+campo).val())){
   		key=true;
   		msg="";		
   	}else{
   		key=false;
		keyAux=false;
		msg=msgNull();
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   
   	campo="email_user";
   	if(isE_Mail($("#"+tipoForm).find("#"+campo).val())){
   		key=true;
   		msg="";		
   	}else{
   		key=false;
		keyAux=false;
		msg=msgEmail();
   	}
   	marcarCampo(tipoForm,campo,key,msg);
   	   	
   	
   	return keyAux;
}//end validarForm


//[START: evaluaciones por formulario]* * * * * *

//[END: evaluaciones por formulario]* * * * * *


//[evaluaciones genericas]* * * * * *

function isNotNull(valor){
	var key=false;
	
	if(valor=="" || valor==null){
		key=false;
   	}else{
   		key=true;
   	}	
	return key;
}

function isNumerico(valor){
	var key=false;
	if (/^([0-9])*$/.test(valor)){
		key=true;
   	}
	return key;
}

function isText(valor){
	var key=false;
	if (/^[a-zA-Z]*$/.test(valor)){
		key=true;
   	}
	return key;
}

function isRut(valor){
	var aux=false;	
	if (/^\b\d{7,9}\-[K|k|0-9]$/.test(valor) && RutMod11(valor)){
		aux=true;
   	}	
	return aux;
}//end valFormatoRut

function isE_Mail(valor){
	var aux=false;	
	filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (filter.test(valor)) {
		aux=true;
   	}	
	return aux;
}//end valFormatoRut

//[START: funciones de mensajes]---------------------------

function msgNull(){
	return "Debe ingresar un valor";
}

function msgRut(){
	return "Debe ingresar un rut valido";
}

function msgNumerico(){
	return "Debe ingresar un valor numerico";
}

function msgLetras(){
	return "Debe ingresar un valor sin numeros";
}

function msgFechas(){
	return "Debe ingresar una fecha valida";
}
function msgEmail(){
	return "Debe ingresar un Email valido";
}

//[END: funciones de mensajes]---------------------------


function marcarCampo(tipoForm,campo,key,msg){
	if(key){
		$("#"+tipoForm).find("#"+campo).css({'background-color' : 'white'});
		$("#"+tipoForm).find("#"+campo+"_error").text("");
	}else{
		$("#"+tipoForm).find("#"+campo+"_error").text("( *) "+msg);
		$("#"+tipoForm).find("#"+campo+"_error").css({'color' : '#cd0a0a'});
		//$("#"+tipoForm).find("#"+campo).val("");		
		//$("#"+tipoForm).find("#"+campo).css({'background-color' : '#fef1ec'});
		//$("#"+tipoForm).find("#"+campo).css({'background-color' : '#daa6a9'});
	}
	return true;
}

//Otras validaciones
function esVacio(valor){	
	alert(valor);
	if(valor=="" || valor==null){
		alert("Debe ingresar un valor");
   	}
}

function esNumerico(valor){
	alert(valor);
	if (/^([0-9])*$/.test(valor)){
		alert("Debe ingresar un valor numerico");
   	}else if(valor=="" || valor==null){
   		alert("Debe ingresar un valor");
   	}
}
