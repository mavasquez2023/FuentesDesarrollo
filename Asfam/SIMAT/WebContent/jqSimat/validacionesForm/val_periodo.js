//funciones que validan formularios de jsp login

function validarPeriodo(tipoForm){
	keyAux=true;
	key=true;
	
	campo="periodo";
	if(valFormaDateAM($("#"+tipoForm).find("#"+campo).val())){	
		key=false;
		keyAux=false;
   	}else{
   		key=true;
   	}
   	marcarCampo(tipoForm,campo,key);   	
   	return keyAux;   	
}//end validarPeriodo


$(function(){
	$( "#periodo" ).datepicker({dateFormat: 'yymm'});
});
 
       
function cargarPeriodo(){
	var key=false;
	key=validarPeriodo("formPeriodo");
	if(key){			
		document["formPeriodo"].submit();
	}
}//end cargarPeriodo

function valFormaDateAM(valor){
//retorna true si el valor contiene letras o contiene un mes no valido
	var key=false;	
	if (!/^(19|20)([0-9][0-9])(0[1-9]|1[012])$/.test(valor)){
		key=true;
   	}
	return key;
}//end valFormaDateAM


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