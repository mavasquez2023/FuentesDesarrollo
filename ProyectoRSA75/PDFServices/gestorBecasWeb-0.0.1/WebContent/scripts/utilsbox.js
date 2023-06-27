/** Utilioraios de despliegue & otros */

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
		$.blockUI({ message: 'Ejecutando. Momento por favor', css: { left: '43%', top: '45%' } } );
		if ( !isOldFashionBrowser() )
			show ('loading');
	}
}

function showLoadingInternet() {
	// if ( !isOldFashionBrowser() && isWaitingLogo ) {
	if ( isWaitingLogo ) {
		$.blockUI({ message: 'Ejecutando. Momento por favor', css: { left: '38%', top: '45%' } } );
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
	$.blockUI({  message:$("#modalErrorBox"),css:{backgroundColor: '#FFA07A', width:'36%',top:'30%',left:'32%'}}); 	       
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

function getComboList( lista, id, seleccion ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id=" + id;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetComboListAjax.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					// todo ok ... creamos el combo
					var total = $(xml).find("return").find("totalItems").text();
					var opciones = new Array( total );

					var c = document.getElementById( lista );
					c.options.length = 0;

					var i=0;
					$(xml).find("return").find("items").find("item").each(
					  function() {
					  	var value = $(this).find("value").text();
					  	var label = $(this).find("label").text();
					  	c.options[i] = new Option(label, value);
					  	if ( value == seleccion )
						  	c.options[i].selected = true;
						i++;
					});		

					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
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
function getComboListInforme( cmd, fechaDesde, fechaHasta, lista, idBeca) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + cmd + "&fechaDesde=" + fechaDesde+ "&fechaHasta=" + fechaHasta+ "&idBeca="+idBeca;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetInformesAjax.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					// todo ok ... creamos el combo
					var total = $(xml).find("return").find("totalItems").text();
					var opciones = new Array( total );

					var c = document.getElementById( lista );
					c.options.length = 0;

					var i=0;
					$(xml).find("return").find("items").find("item").each(
					  function() {
					  	var value = $(this).find("value").text();
					  	var label = $(this).find("label").text();
					  	c.options[i] = new Option(label, value);
					  	if ( value == '' )
						  	c.options[i].selected = true;
						i++;
					});		

					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function getCheckList( lista, id, seleccion ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id=" + id;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
					$('#cursoid').text("");
					$('#idMaxCurso').val("0");
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var value = $(this).find("value").text();
						  var label = $(this).find("label").text();
						  
						  $('#idMaxCurso').val(value);
						  
						  
						  $('#cursoid').append('<input id="curso'+value+'" name="curso'+value+'" value="'+value+'" type="checkbox"></input>') 
						  .append("<strong>"+label+"</strong></br>");
						 
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }


function getCheckBecaList( lista, id, seleccion ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id=" + id;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
					$('#becaid').text("");
					$('#idMaxBeca').val("0");
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var value = $(this).find("value").text();
						  var label = $(this).find("label").text();
						  
						  $('#idMaxBeca').val(value);
						  
						  $('#becaid').append('<input id="beca'+value+'" name="beca'+value+'" value="'+value+'" type="checkbox"></input>') 						  
						  .append(label+"</br>");
						  	
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }
function getEvaluacionNivelEdu( lista, id, seleccion ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id=" + id;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
       				$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var value = $(this).find("value").text();
						  var label = $(this).find("label").text();
						  
						  
						  if(value == 'nota'){
							  if(label == 'true'){
								  $("#notaBeneficioDesde").val('');
								  $("#notaBeneficioHasta").val('');
								  $("#trNota").css("display","");
								  $("#puntajeOnota").text('Nota');
							  }else{
								  $("#notaBeneficioDesde").val("0");
								  $("#notaBeneficioHasta").val("0");
								  $("#trNota").css("display","none");
							  }
						  }

						  if(value == 'porcentaje'){
							  if(label == 'true'){
								  $("#porcentajeBeneficioDesde").val('');
								  $("#porcentajeBeneficioHasta").val('');
								  $("#trPorcentaje").css("display","");
							  }else{
								  $("#porcentajeBeneficioDesde").val("0");
								  $("#porcentajeBeneficioHasta").val("0");
								  $("#trPorcentaje").css("display","none");
							  }
						  }
								  
						  if(value == 'puntaje'){
							if(label == 'true'){
								$("#puntajeBeneficioDesde").val('');
								$("#puntajeBeneficioHasta").val('');
								$("#trPuntaje").css("display","");
								$("#puntajeOnota").text('Puntaje');
								
							}else{
								$("#puntajeBeneficioDesde").val('0');
								$("#puntajeBeneficioHasta").val('0');
								$("#trPuntaje").css("display","none");
							  }
						  }
							  
						
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function getTipoEntrega( lista, id, seleccion ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&idTipoBeca=" + id;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
       				$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var value = $(this).find("value").text();
						  var label = $(this).find("label").text();
						  
						  
						  if(value == 'inmediata'){
							  if(label == 'true'){
								  $("#divPlazoEntrega").css("display","none");
							  }else{
								  $("#divPlazoEntrega").css("display","");
							  }
						  }

					  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function agregaBeneficioList( lista, id, nombreBenefico, nDesde, nHasta, poDesde, poHasta, puDesde, puHasta, monto,incentivo,nivelEducacional) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id=" + id+ "&nombreBeneficio=" + nombreBenefico+ "&nDesde=" + nDesde+ "&nHasta=" + nHasta+ "&poDesde=" + poDesde+ 
					"&poHasta=" + poHasta+ "&puDesde=" + puDesde+ "&puHasta=" + puHasta+ "&monto=" + monto+ "&incentivo=" + incentivo+ "&nivelEducacional=" + nivelEducacional;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
				var msn = $(xml).find("return").find("msn").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
					$('#detNombreBeneficioId').text("");
					$('#detDesdeBeneficioId').text("");
					$('#detHastaBeneficioId').text("");
					$('#detMontoBeneficioId').text("");
					$('#detEliminaBeneficioId').text("");
					$('#idMaxDetBeneficio').val("0");
					
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var id = $(this).find("id").text();
						  var nombre = $(this).find("nombre").text();
						  var desde = $(this).find("desde").text();
						  var hasta = $(this).find("hasta").text();
						  var monto = $(this).find("monto").text();
						  
						  var largo = $(this).find("largo").text();
						  
						  $('#idMaxDetBeneficio').val(largo);
						  
						  $('#detNombreBeneficioId').append('<a id="muestraBeneficio" href="#" class="links" onClick="muestraBeneficio('+id+');">'+nombre+'</a></br>');
						  
						  $('#detDesdeBeneficioId').append('<a>'+desde+'</a></br>');
						  
						  $('#detHastaBeneficioId').append('<a>'+hasta+'</a></br>');
						  
						  $('#detMontoBeneficioId').append('<a>'+monto+'</a></br>');
						  
						  $('#detEliminaBeneficioId').append('<a title="Elimina Beneficio" href="#"><img onclick="javascript:eliminaBenefico('+id+')" src="/gestorBecasWeb/images/icono_basurero.gif"></a></br>');
						  
						  });
					
					if(msn != ""){
						  alert(msn);
					  }
						
					agregaAreaConceptoList('agregaAreaConceptoList','','');
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function agregaAreaConceptoList( lista,segmeto,isMarcado) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista+"&segmeto=" + segmeto+"&isMarcado=" + isMarcado;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
					$('#detBeneficioAC').text("");
					$('#detSegmentoAC').text("");
					$('#detAreaAC').text("");
					$('#detConceptoAC').text("");
					var contador =0;
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var idAreaConcepto = $(this).find("idAreaConcepto").text();
						  var beneficio = $(this).find("beneficio").text();
						  var segmento = $(this).find("segmento").text();
						  var area = $(this).find("area").text();
						  var concepto = $(this).find("concepto").text();
						  
						  var largo = $(this).find("largo").text();
						  
						  $('#idMaxDetBeneficioAC').val(largo);
						  
						  $('#detBeneficioAC').append('<a href="#" id="muestraAreaConcepto" class="links" onClick="javascript:muestraAreaConcepto('+idAreaConcepto+')">'+beneficio+'</a> </br>');							  
						  
						  $('#detSegmentoAC').append('<a>'+segmento+'</a></br>');
						  
						  $('#detAreaAC').append('<a>'+area+'</a><input type="hidden"  name="hArea'+contador+'" id="hArea'+contador+'" value="'+area+'"/></br>');
						  
						  $('#detConceptoAC').append('<a>'+concepto+'</a><input type="hidden"  name="hConcepto'+contador+'" id="hConcepto'+contador+'" value="'+concepto+'"/></br>');
						  	
						  contador =	contador+1;
						  	
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function muestraAreaConceptoById( lista,idAreaConcepto) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista+"&idAreaConcepto=" + idAreaConcepto;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					
					$('#labelBeneficio').text("");
					$('#labelSegmento').text("");
					
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var area = $(this).find("area").text();
						  var concepto = $(this).find("concepto").text();
						  var labelBeneficio = $(this).find("labelBeneficio").text();
						  var labelSegmento = $(this).find("labelSegmento").text();
						  var idAreaConcepto = $(this).find("idAreaConcepto").text();
						  
						  					 
						  $('#labelBeneficio').append('<a>'+labelBeneficio+'</a></br>');
						  
						  $('#labelSegmento').append('<a>'+labelSegmento+'</a></br>');
						  
						  $('#area').val(area);
						  
						  $('#concepto').val(concepto);
						  
						  $('#idAreaConcepto').val(idAreaConcepto);
						  	
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function registrarAreaConcepto( lista,idAreaConcepto,area,concepto) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista+"&idAreaConcepto=" + idAreaConcepto+"&area=" + area+"&concepto=" + concepto;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					

					$('#detBeneficioAC').text("");
					$('#detSegmentoAC').text("");
					$('#detAreaAC').text("");
					$('#detConceptoAC').text("");
					var contador =0;
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var idAreaConcepto = $(this).find("idAreaConcepto").text();
						  var beneficio = $(this).find("beneficio").text();
						  var segmento = $(this).find("segmento").text();
						  var area = $(this).find("area").text();
						  var concepto = $(this).find("concepto").text();
						  
						  var largo = $(this).find("largo").text();
						  
						  $('#idMaxDetBeneficioAC').val(largo);
						  
						  $('#detBeneficioAC').append('<a href="#" class="links" id="muestraAreaConcepto" onClick="javascript:muestraAreaConcepto('+idAreaConcepto+')">'+beneficio+'</a> </br>');							  
						  
						  $('#detSegmentoAC').append('<a>'+segmento+'</a></br>');
						  
						  $('#detAreaAC').append('<a>'+area+'</a><input type="hidden"  name="hArea'+contador+'" id="hArea'+contador+'" value="'+area+'"/></br>');
						  
						  $('#detConceptoAC').append('<a>'+concepto+'</a><input type="hidden"  name="hConcepto'+contador+'" id="hConcepto'+contador+'" value="'+concepto+'"/></br>');
						  	
						  contador =	contador+1;
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }


function cargaDatosBeneficio( lista, id,nivelEducacional,opcTipoBeca) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + lista + "&id="+id+ "&nivelEducacional="+nivelEducacional+ "&opcTipoBeca="+opcTipoBeca;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetConfiguracionAjaxAction.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					$(xml).find("return").find("items").find("item").each(
					  function() {
						  
						  var id = $(this).find("id").text();
						  
						  var notaDesde = $(this).find("notaDesde").text();
						  var notaHasta = $(this).find("notaHasta").text();
						  
						  var porcentajeDesde = $(this).find("porcentajeDesde").text();
						  var porcentajeHasta = $(this).find("porcentajeHasta").text();
						  
						  var puntajeDesde = $(this).find("puntajeDesde").text();
						  var puntajeHasta = $(this).find("puntajeHasta").text();
						  
						  var monto = $(this).find("monto").text();
						  var incentivo = $(this).find("incentivo").text();
						  
							$('#opcBeneficio').val(id);
							
							$('#notaBeneficioDesde').val(notaDesde);
							$('#notaBeneficioHasta').val(notaHasta);
							
							$('#porcentajeBeneficioDesde').val(porcentajeDesde);
							$('#porcentajeBeneficioHasta').val(porcentajeHasta);
							
							$('#puntajeBeneficioDesde').val(puntajeDesde);
							$('#puntajeBeneficioHasta').val(puntajeHasta);
							
							$('#montoBeneficio').val(monto);
							$('#incentivo').val(incentivo);
							
						  });		
					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }

function getTexto( texto, id, seleccion, key ) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + texto + "&id=" + id + "&key=" +key;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetTextoAjax.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
					// todo ok ... creamos el combo
					var total = $(xml).find("return").find("totalItems").text();
					var opciones = new Array( total );

					var c = document.getElementById( texto );

					var i=0;
					var textoFinal="";
					$(xml).find("return").find("items").find("item").each(
					  function() {
					  	var value = $(this).find("value").text();
					  	textoFinal += value + "<br/>";
					});	
					$("#"+texto).empty();
					$("#"+texto).prepend(textoFinal);

					hide ("ajaxErrorBox");
					hideLoading();
       			}
       	   }
		});			
	 });
 }
function buscaSegmentoByRut(cmd,rut) {

	showLoading();

	$(document).ready(function() {
		var data = "_cmd=" + cmd+"&rut=" + rut;
        $.ajax({
           type: "post",
           url: jsContextRoot + "/commons/GetTextoAjax.do?",
           data: data,
           success: function (xml) {
				var error = $(xml).find("return").find("error").text();
       			if (error == 'true')  
       				despliegaErroresAjax (xml);
       			else {
       			 
       				$("#tituloSegmento").css("display","none");
       				$("#botonSegmento").css("display","none"); 
       				$("#botonBusqueda").css("display","");
       				
       				      				
       				$('#divSegmentos').text("");
       			 
       			 	if($(xml).find("return").find("items").find("item").length == 1) {
	       			 	$(xml).find("return").find("items").find("item").each(
						  function() {
						
							  var value = $(this).find("value").text();
							  var label = $(this).find("label").text();
							  
							  $("#tituloSegmento").css("display","");
							  $("#botonSegmento").css("display","");
							  $("#botonBusqueda").css("display","none");
							  $('#divSegmentos').append('<input type="radio" name="segmento" id="'+value+'" value="'+value+'" checked/><a>'+label+'<a/></br>');							  
						  });
						  hide ("ajaxErrorBox");
						  hideLoading();
						  document.forms["BusquedaPersonaForm"].submit(); 
       			 	} 
       			 	else {
					$(xml).find("return").find("items").find("item").each(
					  function() {
					
						  var value = $(this).find("value").text();
						  var label = $(this).find("label").text();
						  
						  $("#tituloSegmento").css("display","");
						  $("#botonSegmento").css("display","");
						  $("#botonBusqueda").css("display","none");
						  $('#divSegmentos').append('<input type="radio" name="segmento" id="'+value+'" value="'+value+'"/><a>'+label+'<a/></br>');							  
					  });	
					  hide ("ajaxErrorBox");
					  hideLoading();	
					}
					
       			}
       	   }
		});			
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
