var scrolled=0;
var maxHeight=0;
var idContent="";
var idImprimeOff="";
var idImprimeOn="";
var iframe;
var intervalo;
var certCargado=0;

function doBajar() {
    var scrol_pos = $("#"+idContent).scrollTop();
    $("#"+idContent).scrollTop(scrol_pos - 100);
}

function doSubir() {
    var scrol_pos = $("#"+idContent).scrollTop();
    $("#"+idContent).scrollTop(scrol_pos + 100);
}

function imprimir(){
	if($("#btnImprimir").attr("disabled")!="disabled"){
		$("#"+idImprimeOn).css("display","");
		$("#"+idImprimeOff).css("display","none");
		$("#genCertForm").submit();
	}
/*	if(certCargado==1){
		certCargado=1;
	}else{
		$("#genCertForm").submit();
		certCargado=1;
	}*/
//	vbPrintPage(document.getElementById('iframeLoad').contentWindow);
//	var wnd = document.getElementById('iframeLoad').contentWindow;
//	wnd.focus(); // <- very important string!!!!
//	wnd.document.execCommand('Print', true, 0);
}

function configureScroll(idIframe, idContenedor, idCargandoOff, idCargandoOn){
	iframe = document.getElementById(idIframe);
	idContent = idContenedor;
	idImprimeOff = idCargandoOff;
	idImprimeOn = idCargandoOn;
	
	maxHeight = document.getElementById(idContenedor).scrollHeight;
}
function finalizado() {
	$("#" + idImprimeOn).css("display","none");
	$("#" + idImprimeOff).css("display","");
}
/*
 
 function finalizado(){
	var estadoIframe;
	if(iframe.readyState!=undefined){
		metodo de obtener el estado de carga en IE
		estadoIframe = iframe.readyState;
	}else{
		metodo en navegadores modernos.
		estadoIframe = iframe.contentWindow.document.readyState;
	}
	if(estadoIframe==="complete"){
		$("#"+idImprimeOn).hide();
		$("#"+idImprimeOff).show();
		certCargado=1;
		window.clearInterval(intervalo);
		vbPrintPage(document.getElementById('iframeLoad').contentWindow);
	}
}
 
 function enviarForm(formulario){
	$("#"+idImprimeOff).hide();
	$("#"+idImprimeOn).show();
	
	$.get(document.getElementById(formulario).getAttribute("action"), $("#"+formulario).serialize(), function(data){
		var json = $.parseJSON(data);
		console.log(json);
		
		$("#nom_cert").append(json.nombreCompleto);
		$("#rut_cert").append(json.rut);
		$("#fecha_cert").append(json.fechaCreacion);
		$("#validacion_cert span").append(json.codValidacion);
		$("#firma_cert").append("<img src='/SatelitesCRM/GetImages?nombreImg="+json.firma+"'>");
		$(".print_logo").append("<img src='/SatelitesCRM/GetImages?nombreImg=logo_reducido.jpg'>");
		
		certCargado=1;
		
	});
}*/