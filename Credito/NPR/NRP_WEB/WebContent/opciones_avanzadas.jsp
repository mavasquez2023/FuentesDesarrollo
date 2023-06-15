<%@include file="header_session.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<title>Gestor de NRP - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style>

#content_liv{
width:800px;
height:600px;
background-color:#FFF;
}

.menu{
width:300px;
height:600px;
background-color:#FFF;
padding-left: 5px;

float:left;
color:#005D8B;
}

#main_content{
float:left;
margin-left:20px;
}

.descripcion_etapa{
margin-left:10px;
}

.titulo_menu{
color:#005D8B;
font-weight:bold;
height:30px;
}

.titulo_proceso{
width:100%;
color:#005D8B;
}

.menu_proceso{
padding:2px;
border: 1px solid #DDD;
margin:2px;
font-size:14px;
padding-bottom:10px;
padding-top: 5px;
padding-left:8px;
}

.opciones_proceso{
padding-top:10px;
padding-left:10px;
}

.estado_menu{
font-size:13px;
height:20px;
}

.hide{
display:none;
}

.cursor{
cursor:hand;
cursor:pointer;
}
.estado_actual{
font-weight:bold;
color:#00F;
font-size:12px;
}


.content_titulo_proceso{
height:40px;
color:#005D8B;
font-weight:bold;
}
.content_titulo_etapa_proceso{
height:30px;
}
.titulo_detalle_proceso{
color:#005D8B;
font-weight:bold;
}
.data_detalle_proceso{
color:#005D8B;

}
.clase_bloqueo{
color:#F00;
}

.loading{
width:400px;
text-align:center;
color:#005D8B;
}
.loading img{
padding-top:100px;
padding-bottom:20px;
width:50px;
}


</style>

</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="images/cabecera_claves.jpg" />
		<%@include file="menu.jsp" %>
    <h1> Opciones &Uacute;tiles</h1>
    
	<div id="content_liv">
		

		<div id="main_content"> 
			<div class='loading' style="display:none"> <img src="img/loading.gif" ></img> <br/><font class='font_loading'>Cargando por favor espere...</font></div>
			<div class="content_titulo_proceso descripcion_etapa" > * No todas estas opciones estar&aacute;n disponibles en la versi&oacute;n, pues ser&aacute; automatizadas. Se despliegan s&oacute;lo para ayudar a las pruebas y validaci&oacute;n de la Aplicaci&oacute;n </div>
			
			<br/><br/>
			<div class="content_titulo_proceso descripcion_etapa" > Crear Estructura periodo Actual ( bloquea antiguos ) &nbsp;
				<button id="btn_ejecutar_creacion" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Ejecutar Ahora
				</button> 
			
			</div>

			
			<br/>
			<div class="content_titulo_proceso descripcion_etapa" > Limpiar Proceso Actual &nbsp;
				<button id="btn_ejecutar_limpiar" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Ejecutar Ahora
				</button> 
			
			</div>


			<br/>
			<div class="content_titulo_proceso descripcion_etapa" > Desbloquear Proceso Actual&nbsp;
				<button id="btn_ejecutar_desbloquear" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Ejecutar Ahora
				</button> 
			
			</div>

			
			<br/>
			<div class="content_titulo_proceso descripcion_etapa" > Eliminar Proceso &nbsp;
				<br/>
				 &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; Per&iacute;odo <input type="text"  id="periodo_eliminar" value="" placeholder="EJ:201803" size="8"/> &nbsp;
				<button id="btn_ejecutar_eliminar" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Ejecutar Ahora
				</button> 
			
			</div>



			<br/>
			<div class="content_titulo_proceso descripcion_etapa" > Enviar Archivo NRP a SAP &nbsp;
				<button id="btn_envio_sap" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Enviar Ahora
				</button> 
			
			</div>

			<div class="content_titulo_proceso descripcion_etapa" > Generar Nominas Por Entidad &nbsp;
				<br/>
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; C&oacute;digo Entidad <input type="text"  id="codigo_entidad" value="" placeholder="EJ:HOL001" size="10"/> &nbsp; <input type="text"  id="cantidad_hebras" value="1"  size="1"/>
				<button id="btn_eliminar_nominas" type="button" class="btn btn-danger btn-sm">
				  <span class="glyphicon glyphicon-remove"></span> eliminar
				</button> 
				&nbsp;
				<button id="btn_generar_nominas" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Generar
				</button> 
			
			</div>

<br/>
	<div class="content_titulo_proceso descripcion_etapa" > Enviar Nominas Por Entidad &nbsp;
				<br/>
				&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; C&oacute;digo Entidad <input type="text"  id="codigo_entidad_enviar" value="" placeholder="EJ:HOL001" size="10"/> &nbsp; 
				<button id="btn_enviar_nominas" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Enviar
				</button> 
			
			</div>

<br/>

			<div class="content_titulo_proceso descripcion_etapa" > Limpiar N&oacute;minas extranet &nbsp;

				<button id="btn_limpiar_extranet" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Limpiar
				</button> 
			
			</div>

			<div class="content_titulo_proceso descripcion_etapa" > Limpiar Configuraciones &nbsp;

				<button id="btn_limpiar_configuraciones" type="button" class="btn btn-success btn-sm">
				  <span class="glyphicon glyphicon-play"></span> Limpiar Configuraciones
				</button> 
			
			</div>



		</div>

	</div>


</div>
</div>



<script>


var url_creacion = "/NRP/request?formato_salida=json&id_req=crearEstructura&token="+mi_token+"&rand="+Math.random();
var url_limpieza = "/NRP/request?formato_salida=json&id_req=limpiarEstructuraProceso&token="+mi_token+"&rand="+Math.random();
var url_desbloquear = "/NRP/request?formato_salida=json&id_req=desbloquearProceso&token="+mi_token+"&rand="+Math.random();
var url_eliminar = "/NRP/request?formato_salida=json&id_req=eliminarProceso&token="+mi_token+"&rand="+Math.random()+"&periodo=";
var url_envio_sap = "/NRP/request?formato_salida=json&id_req=enviarNominaSAP&token="+mi_token+"&rand="+Math.random();
var url_generar_nominas = "/NRP/request?formato_salida=json&id_req=generarNominas&token="+mi_token+"&rand="+Math.random()+"&codigo_entidad=";
var url_enviar_nominas = "/NRP/request?formato_salida=json&id_req=enviarNominas&token="+mi_token+"&rand="+Math.random()+"&codigo_entidad=";
var url_eliminar_nominas = "/NRP/request?formato_salida=json&id_req=eliminarNominas&token="+mi_token+"&rand="+Math.random()+"&codigo_entidad=";
var url_limpiar_extranet = "/NRP/request?formato_salida=json&id_req=eliminarArchivosExtranet&token="+mi_token+"&rand="+Math.random();
var url_limpiar_configuraciones = "/NRP/request?formato_salida=json&id_req=refrescarConfiguraciones&token="+mi_token+"&rand="+Math.random();


function mostrarCargando(){
	$(".loading").show();
	$(".content_titulo_proceso").hide();
}
function ocultarCargando(){
	$(".loading").hide();
	$(".content_titulo_proceso").show();
}

$("#btn_ejecutar_creacion").click(function() {
		mostrarCargando();

		setTimeout( function (){
			$.getJSON( url_creacion, function( data ) {				
				ocultarCargando();
			});
		}, 1000);
});

$("#btn_ejecutar_limpiar").click(function() {
		mostrarCargando();

		setTimeout( function (){
			$.getJSON( url_limpieza, function( data ) {
				ocultarCargando();
			});
		}, 1000);
});

$("#btn_ejecutar_desbloquear").click(function() {
		mostrarCargando();

		setTimeout( function (){
			$.getJSON( url_desbloquear, function( data ) {
				ocultarCargando();
			});
		}, 1000);
});

$("#btn_ejecutar_eliminar").click(function() {
		mostrarCargando();

		setTimeout( function (){
			var url = url_eliminar+$("#periodo_eliminar").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();
			});
		}, 1000);
});

$("#btn_envio_sap").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_envio_sap;
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});

$("#btn_generar_nominas").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_generar_nominas;
			url = url + $("#codigo_entidad").val() +"&cantidad_hebras="+$("#cantidad_hebras").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});


$("#btn_enviar_nominas").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_enviar_nominas;
			url = url + $("#codigo_entidad_enviar").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});


$("#btn_eliminar_nominas").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_eliminar_nominas;
			url = url + $("#codigo_entidad").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});

$("#btn_limpiar_extranet").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_limpiar_extranet;
			url = url + $("#codigo_entidad").val();
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});

$("#btn_limpiar_configuraciones").click(function() {
		mostrarCargando();

		setTimeout( function (){
			url = url_limpiar_configuraciones;
			$.getJSON( url, function( data ) {
				ocultarCargando();

			});
		}, 1000);
});



$("#menu_opciones").addClass("active");

</script>

</body>
</html>
