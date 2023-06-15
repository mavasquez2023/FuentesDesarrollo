


var formatear_rut = function(rut) {
	if ($.trim(rut).length > 1) {
		var digito = rut.substring(rut.length - 1);
		var entero = rut.substring(0, rut.length - 1);
		return SepararMiles(entero) + "-" + digito;
	}
	return rut;
}
var valida_texto_numerico = function(event) {
	if ((event.which >= 48 && event.which <= 57) || event.which != 45
			|| event.which != 46 || event.which != 8)
		return true;
	return false;
}
function replaceAll(str, find, replace) {
  return str.replace(new RegExp(find, 'g'), replace);
}

function validarRutIngresado(campo, rut) {
	rut= rut.replace(/\./g, "");
		if(rut.length>0){
			var partes = rut.split("-");
			var digv = partes[1];
			var rut = partes[0];
			if (digv == 'K') {
				digv = 'k';
			}
			var digesto = dv(rut);
			if (digesto == digv) {
				return true;
			} else {
				//mostrarInfoError("El Rut ingresado no es v&aacute;lido");
				return false;
			}
		}
}

function dv(T) {
	var M = 0, S = 1;
	for (; T; T = Math.floor(T / 10)) {
		S = (S + T % 10 * (9 - M++ % 6)) % 11;
	}
	return S ? S - 1 : 'k';
}

function SepararMiles(numero) {
	var num = numero.replace(/\./g, "");
	if (!isNaN(num)) {
		num = num.toString().split("").reverse().join("").replace(
				/(?=\d*\.?)(\d{3})/g, "$1.");
		num = num.split("").reverse().join("").replace(/^[\.]/, "");
		numero = num;
	} else {
		num = num.replace(/[^\d\.]*/g, "");
		num = num.toString().split("").reverse().join("").replace(
				/(?=\d*\.?)(\d{3})/g, "$1.");
		num = num.split("").reverse().join("").replace(/^[\.]/, "");
		numero = num;

	}
	return numero;
}

function sysout(texto) {
	// if(texto.indexOf("info")>-1)
	//console.log(texto);
}

var mensajeInformativoAgregado = false;
var modalOculto = false;
function cargarMensajeInformativo(mensaje, tiempo, tipo){

	var icono = "";
	var icono_tipo = "";
	if(tipo != null){
		if(tipo == "warning"){
			icono_tipo = "glyphicon-exclamation-sign";
		}
		else if(tipo == "success"){
			icono_tipo = "glyphicon glyphicon-ok-sign";
		}
		else if(tipo == "danger"){
			icono_tipo = "glyphicon glyphicon-remove-sign";
		}

		icono = '<span class="glyphicon '+icono_tipo+'"></span> &nbsp;';
	}

	var div = '<div class="modal fade" id="modalInformativo" role="dialog">'+
    		'<div class="modal-dialog">'+
    			'<div class="modal-content">'+
					'<div class="modal-header">'+
					  '<button type="button" class="close" data-dismiss="modal"></button>'+
					  '<h4 class="modal-title">Mensaje Informativo</h4>'+
					'</div>'+
					'<div class="modal-body" style="padding-left:20px" id="div_content_informativo">&nbsp; '+icono +' '+mensaje+
					'</div>'+
					'<div class="modal-footer">'+

					'<button type="button" class="btn btn-default btn-sm"  onclick="ocultarModalInformativo()">'+
					'<span class="glyphicon glyphicon-remove"></span> Ocultar'+
					'</button> '+
					'</div>'+
				  '</div>'+      
				'</div>'+
			  '</div>';
	if(mensajeInformativoAgregado == false){
		mensajeInformativoAgregado = true;
		console.log("agregando div...");
		$("body").append(div);
	}
	else{
		console.log("mostrando div...");
		$("#div_content_informativo").html(mensaje);
	}
	$("#modalInformativo").modal();
	modalOculto = false;
	if(tiempo > 0 ){
		setTimeout( function (){
			if($('#modalInformativo').hasClass('in')){
				ocultarModalInformativo();
			}
		}, tiempo );
	}
}

function ocultarModalInformativo(){
	modalOculto = true;
	$("#modalInformativo").modal('toggle');
}


function respuestaAJAXValida(data){
	alert("procesando respuesta 1" );

	cargarMensajeInformativo("Sesi&oacute;n no v&aacute;lida",-1);
}


function ejecutarAJAX(url, parametros, funcion){
	url = url +"&token="+mi_token;
	//console.log("ejecutando ajax...["+url+"]")
	$.ajax({
	  type: 'POST',
	  data :  parametros,
	  url: url,
	  dataType: 'json',
	  success: function (data) {
		funcion(data);
	  }
	});
}


function cargarSelectGenerico(url, idSelect){

ejecutarAJAX( url,null,
	function (data) {
	$( "#"+idSelect).html("");
	for(var i =0; i< data.item.data.length; i++){
		$( "#"+idSelect).append('<option value="'+data.item.data[i].valor+'">'+data.item.data[i].texto+'</option>');
	}
  
});


}

var modal_cargando = null;
setTimeout( function(){
var div = '<div class="modal fade modal_cargando" id="modal_cargando" role="dialog" >'+
				 '<div style="width:100%;text-align:center;padding-top:300px;color:#FFF;font-weight:bold;font-size:20px;">'+
					'<img src="img/loading.gif" width="60px"></img> <br/><br/> Cargando, por favor espere...'+
				 '</div>'+
			  '</div>';
	
	
$("body").append(div);

modal_cargando = $('#modal_cargando');
}, 1000 );

function mostrarCargando(tag){		
	
	console.log("mostrarCargando ["+tag+"]")
	modal_cargando.modal();
}

function ocultarCargando(tag){
	console.log("ocultarCargando ["+tag+"]")
	modal_cargando.modal('toggle');
}


function agregarConfirm(texto, funcionSI, funcionNO){

var div = '<div class="modal fade"  role="dialog" id="mi_modal_confirm">'+
  '<div class="modal-dialog">'+
  '  <div class="modal-content">'+
  '    <div class="modal-header">'+
  '      <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
  '      <h4 class="modal-title" id="myModalLabel">'+texto+'</h4>'+
  '    </div>'+
  '    <div class="modal-footer">'+
  '      <button id="btn_confirm_no" type="button" class="btn btn-primary" id="modal-btn-no">No</button>'+
  '      <button id="btn_confirm_si" type="button" class="btn btn-default" id="modal-btn-si">Si</button>'+
  '    </div>'+
  '  </div>'+
  '</div>'+
'</div>';

$("body").append(div);

$("#btn_confirm_si").click(function() {
	funcionSI();	
	ocultarConfirm();
});

$("#btn_confirm_no").click(function() {
	funcionNO();	
	ocultarConfirm();
});

$('#mi_modal_confirm').modal({
		backdrop: 'static',
		keyboard: false
	})


}


function ocultarConfirm(){
	setTimeout(
		function (){
			$('#mi_modal_confirm').modal('toggle');
			setTimeout(function(){ $('#mi_modal_confirm').remove(); },500 );
		},100 );
	
}

window.history.forward();
