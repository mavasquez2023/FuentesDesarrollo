var isWaitingLogo = true;

var isInternetMode = false;

function notImplemented() {
    alert("funcionalidad aun en desarrollo");
}

function popUp( url ) {
	var form = document.getElementById('popUpForm');
	form.action = url;
	form.submit();
	hideLoading();
}

/** despliegue gradual */
function flash( id ) {
	hide ( id );
	desplegar ( id );
}

function desplegar( id ){
	var ref = "#" + id;
	var o = document.getElementById( id );
	if (o)
		$(ref).fadeIn(isOldFashionBrowser() ? 0 : 3000);
}

function ocultar( id ){
	var ref = "#" + id;
	var o = document.getElementById( id );
	if (o)
		$(ref).fadeOut(isOldFashionBrowser() ? 0 : 3000);
}

function desplegarTemporalmente( id, timeOut ) {
	desplegar( id );
	setTimeout("ocultar('"+id+"')", 1000 * timeOut );	
}

/** despliege inmediato */

function show( id ){
	var ref = "#" + id;
	var o = document.getElementById( id );
	if (o)
		$(ref).show();
}

function hide( id ){
	var ref = "#" + id;
	var o = document.getElementById( id );
	if (o)
		$(ref).hide();
}

function setClassName( id, theClass ){
	var ref = "#" + id;
	var o = document.getElementById( id );
	if (o) 
		o.className=theClass;
}


/** utiltarios */

function cleanNumbers() {
	$('input.integer').each(
	  function() {
		  var value = $(this).attr('value');
		  var newValue = cleanNumberValue(value);
		  $(this).attr('value', newValue);
	});
	$('input.decimal').each(
	  function() {
		  var value = $(this).attr('value');
		  var newValue = cleanNumberValue(value);
		  $(this).attr('value', newValue);
	});

	$('input.bigdecimal').each(
	  function() {
		  var value = $(this).attr('value');
		  var newValue = cleanNumberValue(value);
		  $(this).attr('value', newValue);
	});
	
}

function cleanNumberValue( value ) {
	// saca los puntos
	while (value.toString().indexOf(".") != -1) 
		value = value.toString().replace(".","");
	// cambia las comas
	while (value.toString().indexOf(",") != -1) 
		value = value.toString().replace(",",".");
	return value;
}

function volverInicio() {
	var form = document.getElementById('initialBack');
	form.submit();			
}	

// me indica si es un explorador antiguo o no
function isOldFashionBrowser() {
	if ( $.browser.msie )
		if ( $.browser.version.substring(0,1) == '6' )
			return true;
	else
		return false;
	
}
function activaTab( tabNumber ) {

	for (var i=1; i <= 5; i++ ) {
		hide( "tab_info_" + i );
		setClassName( "tab_" + i, "" );
	}

	show( "tab_info_" + tabNumber );
	setClassName( "tab_" + tabNumber, "tab_selected" );

}

function hideMensajeria( ) {
	hide ("zona_mensajes");
	hide ("ajaxErrorBox");
	hide ("mensajeBox");
	hide ("alertaBox");
	hide ("errorBox");
	hide ("validationBox");
	hide ("modalErrorBox");	
	hideLoading();
}

function hideLoading() {
	$.unblockUI();
	hide ('loading');
}

function showLoading() {
	// if ( !isOldFashionBrowser() && isWaitingLogo ) {
	if ( isWaitingLogo ) {
		if ( !isOldFashionBrowser() )
			show ('loading');
	}
}

function showLoadingInternet() {
	// if ( !isOldFashionBrowser() && isWaitingLogo ) {
	if ( isWaitingLogo ) {
		if ( !isOldFashionBrowser() )
			show ('loading');
	}
}


function cleanNull( data ) {
	if ( data == 'null' )
		return '';
	return data;
}


/** ajax */
function despliegaErroresAjax( xml ) {
	hideMensajeria();
	
	var mensajes = "";
	$(xml).find("return").find("messages").find("message").each(
	  function() {
			mensajes = mensajes + "<li>" + $(this).text(); + "</li>"
	});
	var cuadro = document.getElementById( "ajaxErrorMessages" );
	cuadro.innerHTML = mensajes;
	
	show( "zona_mensajes" );
	desplegar( "ajaxErrorBox" );
	
	// Inputs numericos
	$('input.integer').each(function(){
	    $(this).autoNumeric();
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val()));
	});				
	$('input.decimal').each(function(){
	    $(this).autoNumeric({mDec: 2});
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 2}));
	});		
	$('input.bigdecimal').each(function(){
	    $(this).autoNumeric({mDec: 4});
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 4}));
	});	;
	
}

/** errores modal */
function despliegaErroresModal( xml ) {
	hideMensajeria();	
	var mensajes = "<br/>";
	$(xml).find("return").find("messages").find("message").each(
	  function() {
			mensajes = mensajes + "<li>" + $(this).text(); + "</li>"
	});
	var cuadro = document.getElementById( "modalErrorMessages" );
	cuadro.innerHTML = mensajes;
	$.blockUI({  message:$("#modalErrorBox"),css:{backgroundColor: '#E0E0E0', width:'36%',top:'30%',left:'32%'}}); 	       
	$('#botonModalErrorBox').livequery('click', function(){$.unblockUI();});
	
	// Inputs numericos
	$('input.integer').each(function(){
	    $(this).autoNumeric();
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val()));
	});				
	$('input.decimal').each(function(){
	    $(this).autoNumeric({mDec: 2});
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 2}));
	});		
	$('input.bigdecimal').each(function(){
	    $(this).autoNumeric({mDec: 4});
	    if ($(this).attr('id')) 
	        $(this).val($.fn.autoNumeric.Format($(this).attr('id'), $(this).val(), {mDec: 4}));
	});		
}

function bloquearBoton(boton1,boton2) {
	var boton=$('#'+boton2).css("display");
	if (boton=="none") {
    	$('#'+boton2).show();
    	$('#'+boton1).hide();
    }	
 }


function ajaxBlock(urlAction,data) {	
 $.ajax({type: "post",
        url: jsContextRoot + urlAction+".do?",
        data: data,
        success: function (xml) {
  			var error = $(xml).find("return").find("error").text();  			
  	       	if (error == 'true')  {  	       		
  	       	 	despliegaErroresModal( xml );
     		}else {
     			$("#div-body").unblock();
     			hideMensajeria();
     			location.reload();			  	
	       	}
        }
	});	 
}

function blockFormulario(divFormulario,poswidth,posTop,posLeft,tipoMedida){
	$('#div-body').block({  message:$("#"+divFormulario),centerY: false,centerX: false,css:{width: poswidth+tipoMedida,top:posTop+tipoMedida,left: posLeft+tipoMedida}}); 
} 

function bloquearBoton(boton1,boton2) {
	var boton=$('#'+boton2).css("display");
	if (boton=="none") {
    	$('#'+boton2).show();
    	$('#'+boton1).hide();
    }	
 }


function ajaxBlock(urlAction,data) {	
 $.ajax({type: "post",
        url: jsContextRoot + urlAction+".do?",
        data: data,
        success: function (xml) {
  			var error = $(xml).find("return").find("error").text();  			
  	       	if (error == 'true')  {  	       		
  	       	 	despliegaErroresModal( xml );
     		}else {
     			$("#div-body").unblock();
     			hideMensajeria();
     			location.reload();			  	
	       	}
        }
	});	 
}

function blockFormulario(divFormulario,poswidth,posTop,posLeft,tipoMedida){
	$('#div-body').block({  message:$("#"+divFormulario),centerY: false,centerX: false,css:{width: poswidth+tipoMedida,top:posTop+tipoMedida,left: posLeft+tipoMedida}}); 
} 
