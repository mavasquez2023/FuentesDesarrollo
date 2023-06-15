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
<script src="js/comun.js?<%=Math.random()%>"></script>
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
color:#333;

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

.data_detalle_proceso{
font-size:12px;
}
</style>

</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="images/cabecera_claves.jpg" />
	<%@include file="menu.jsp" %>

    <h1>Gestor de NRP</h1>
    
	<div id="content_liv">
		
	
		<div class="menu">
			<div class="titulo_menu">Procesos</div>
		</div>		


		<div id="main_content"> 
			<div class='loading' style="display:none"> <img src="img/loading.gif" ></img> <br/><font class='font_loading'>Cargando por favor espere...</font></div>
			<div class="content_titulo_proceso descripcion_etapa"  style="display:none"> Detalle Proceso </div>
			<div class="descripcion_etapa_0 descripcion_etapa" style="display:none">

				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
							<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td>
											<font class="data_detalle_proceso">Apertura de Per&iacute;odo</font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
		<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td>
		<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_0"></font> 
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
		<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td>
		<font class="data_detalle_proceso" id="ejecutor_etapa_0"></font> 
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
		<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td>
		<font class="data_detalle_proceso" id="estado_etapa_0"></font> 
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
		
						</td>
						<td>
		
						</td>
					</tr>
				</table>
				
				
			</div>

			<div class="descripcion_etapa_1 descripcion_etapa"  style="display:none">


				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td>
	<font class="data_detalle_proceso">Certificar existencia de data a cargar</font> 				
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
	<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td>
	<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_1"></font> 				
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td>
	<font class="data_detalle_proceso" id="ejecutor_etapa_1"></font> 				
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td>
		<font class="data_detalle_proceso" id="estado_etapa_1"></font> 			
						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;Opciones Disponibles &nbsp;</font> 
						</td>
						<td>
					
 <button id="btn_1_ejecutar" type="button" class="btn btn-success btn-sm">
					  <span class="glyphicon glyphicon-play"></span> Ejecutar Proceso
					</button> 

						</td>
					</tr>

					<tr class="content_titulo_etapa_proceso">
						<td valign="top"><br/>
	<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Resultado del Proceso &nbsp;</font> 
						</td>
						<td>
						<font class="data_detalle_proceso" id="detalle_etapa_1"></font> 
						</td>
					</tr>

				</table>


				
				

			</div>
	
			<div class="descripcion_etapa_2 descripcion_etapa"  style="display:none">

				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso">Consolidaci&oacute;n de data a Cargar</font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_2"></font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="ejecutor_etapa_2"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="estado_etapa_2"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;Opciones Disponibles &nbsp;</font> 
						</td>
						<td> 		
		<button class="status_2_OK btn btn-warning btn-sm" id="btn_2_rollback" type="button" style="display:none">
						<span class="glyphicon glyphicon-trash"></span> Rollback
					</button>		 
					
					<button id="btn_2_ejecutar" type="button" class="btn btn-success btn-sm" style="display:none">
					  <span class="glyphicon glyphicon-play"></span> Ejecutar Proceso
					</button> 
					
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td valign="top" colspan="2">
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Resultado del Proceso &nbsp;</font> 

		<font class="data_detalle_proceso" id="detalle_etapa_2"></font> 
						</td>
					</tr>
					
				</table>
			
			</div>
	

			<div class="descripcion_etapa_3 descripcion_etapa"  style="display:none">

				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso">Generar N&oacute;minas NRP</font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_3"></font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="ejecutor_etapa_3"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="estado_etapa_3"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;Opciones Disponibles &nbsp;</font> 
						</td>
						<td> 		
					
					<button id="btn_3_ejecutar" type="button" class="btn btn-success btn-sm" style="display:none">
					  <span class="glyphicon glyphicon-play"></span> Ejecutar Proceso
					</button> 
					
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td valign="top" colspan="2">
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Resultado del Proceso &nbsp;</font> 

		<font class="data_detalle_proceso" id="detalle_etapa_3"></font> 
						</td>
					</tr>
					
				</table>
			
			</div>


<div class="descripcion_etapa_4 descripcion_etapa"  style="display:none">

				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso">Disponibilizar N&oacute;minas</font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_4"></font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="ejecutor_etapa_4"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="estado_etapa_4"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;Opciones Disponibles &nbsp;</font> 
						</td>
						<td> 		
					
					<button id="btn_4_ejecutar" type="button" class="btn btn-success btn-sm" style="display:none">
					  <span class="glyphicon glyphicon-play"></span> Ejecutar Proceso 
					</button> 
					
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td valign="top" colspan="2">
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Resultado del Proceso &nbsp;</font> 

		<font class="data_detalle_proceso" id="detalle_etapa_4"></font> 
						</td>
					</tr>
					
				</table>
			
			</div>
	


<div class="descripcion_etapa_5 descripcion_etapa"  style="display:none">

				<table class="data_detalle_proceso">
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso">Enviar a SAP </font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
						</td>
						<td> 		
		<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_5"></font> 
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="ejecutor_etapa_5"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
						</td>
						<td> 		
	<font class="data_detalle_proceso" id="estado_etapa_5"></font> 	
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td>
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-cog" ></span>&nbsp;&nbsp;Opciones Disponibles &nbsp;</font> 
						</td>
						<td> 		
					
					<button id="btn_5_ejecutar" type="button" class="btn btn-success btn-sm" style="display:none">
					  <span class="glyphicon glyphicon-play"></span> Ejecutar Proceso
					</button> 
					
						</td>
					</tr>
					<tr class="content_titulo_etapa_proceso">
						<td valign="top" colspan="2">
<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Resultado del Proceso &nbsp;</font> 

		<font class="data_detalle_proceso" id="detalle_etapa_5" style="width:400px"></font> 
						</td>
					</tr>
					
				</table>
			


			<!--
			<div class="descripcion_etapa_3 descripcion_etapa" style="display:none">
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Etapa: &nbsp;</font> 
					<font class="data_detalle_proceso">Enviar N&oacute;mina a SAP</font> 
				</div>
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
					<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_3"></font> 
				</div>
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
					<font class="data_detalle_proceso" id="ejecutor_etapa_3"></font> 

				</div>
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-bullhorn" ></span>&nbsp;&nbsp;Estado Proceso: &nbsp;</font> 
					<font class="data_detalle_proceso" id="estado_etapa_3"></font> 

				</div>
				
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Descripci&oacute;n Proceso: &nbsp;</font> 
					<font class="data_detalle_proceso" id="descripcion_etapa_3"></font> 

				</div>
				
			</div>
			-->

		
			
	

		</div>


			<div class="descripcion_etapa_blocked descripcion_etapa"  style="display:none">
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Estado: &nbsp;</font> 
					<font class="data_detalle_proceso"  id="descripcion_etapa_blocked"></font> 
				</div>
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-calendar" ></span>&nbsp;&nbsp;Fecha Ejecuci&oacute;n: &nbsp;</font> 
					<font class="data_detalle_proceso" id="fecha_ejecucion_etapa_blocked"></font> 
				</div>
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-user" ></span>&nbsp;&nbsp;Usuario Ejecutor: &nbsp;</font> 
					<font class="data_detalle_proceso" id="ejecutor_etapa_blocked"></font> 

				</div>
				
				
				<div class="content_titulo_etapa_proceso"> 
					<font class="titulo_detalle_proceso"><span class="glyphicon glyphicon-list-alt" ></span>&nbsp;&nbsp;Detalle Proceso &nbsp;</font> 
					<font class="data_detalle_proceso" id="detalle_etapa_blocked"></font> 

				</div>

			</div>





	</div>


</div>
</div>



<script>
var status_actual = 0;
var estados = new Array();
var periodo_seleccionado = "";

var url_proceso = "/NRP/request?formato_salida=json&id_req=obtenerEstadoWorklow&periodo=";
var url_detalle_etapa = "/NRP/request?formato_salida=json&id_req=obtenerDetalleEtapaProceso";
var url_validar_archivo_SAP = "/NRP/request?formato_salida=json&id_req=validaArchivosCargados&email=luisibacache@gmail.com";
var url_cargar_archivo_SAP = "/NRP/request?formato_salida=json&id_req=ejecutarProcesoConsolidacion&email=luisibacache@gmail.com";
var url_rollback_carga= "/NRP/request?formato_salida=json&id_req=rollbackProcesoCarga&email=luisibacache@gmail.com";
var url_arbol_procesos = "/NRP/request?formato_salida=json&id_req=obtenerArbolDeProcesos";
var url_estado_avance= "/NRP/request?formato_salida=json&id_req=obtenerEstadoAvance&email=luisibacache@gmail.com&periodo=201804";
var url_generar_nominas = "/NRP/request?formato_salida=json&id_req=generarNominas&codigo_entidad=";
var url_enviar_nominas = "/NRP/request?formato_salida=json&id_req=enviarNominas&codigo_entidad=";
var url_envio_sap = "/NRP/request?formato_salida=json&id_req=enviarNominaSAP";


function agregarProceso(idProceso, tituloProceso, estado, abierto, bloqueo){

	var iconFolder='glyphicon glyphicon-folder-open';
	var iconBloqueo='<span class="glyphicon glyphicon-time></span>';
	var displayOpciones = '';
	if(!abierto){
		iconFolder='glyphicon glyphicon-folder-close';
		displayOpciones ='display:none';
	}
	var div_bloqueo = "";

	if(bloqueo){

		div_bloqueo = '			<div class="estado_menu_ estado_menu_2 cursor clase_bloqueo" id="opcion_estado_'+idProceso+'_bloqueo" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-time" >			'+
		'				Proceso Bloqueado 					'+	
		'				</span>								'+
		'			</div>		';
	}


	var html = '<div id="proceso_'+idProceso+'" class="menu_proceso">							'+
		'		<span class="glyphicon '+iconFolder+' titulo_proceso cursor"  onclick="abrirArbolProceso(\''+idProceso+'\')">			'+
		'		'+tituloProceso+'									'+
		'		</span>										'+
		'		<div id="opciones_proceso_'+idProceso+'" class=" opciones_proceso '+displayOpciones+'" style="'+displayOpciones+'">				'+
		'			<div class="estado_menu estado_menu_0 cursor" id="opcion_estado_'+idProceso+'_0" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-folder-open" >			'+
		'				Apertura de Per&iacute;odo					'+
		'				</span>								'+
		'			</div>									'+
		'			<div class="estado_menu estado_menu_1 cursor" id="opcion_estado_'+idProceso+'_1" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-repeat" >			'+
		'				Certificar existencia de data a cargar		'+
		'				</span>								'+
		'			</div>									'+
		'			<div class="estado_menu estado_menu_2 cursor" id="opcion_estado_'+idProceso+'_2" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-play" >			'+
		'				Consolidaci&oacute;n de data a Cargar		'+	
		'				</span>								'+
		'			</div>									'+
		'			<div class="estado_menu estado_menu_3 cursor" id="opcion_estado_'+idProceso+'_3" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-file" >			'+
		'				Generar N&oacute;minas NRP		'+	
		'				</span>								'+
		'			</div>									'+

		'			<div class="estado_menu estado_menu_4 cursor" id="opcion_estado_'+idProceso+'_4" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-envelope" >			'+
		'				Disponibilizar N&oacute;minas		'+	
		'				</span>								'+
		'			</div>									'+

		'			<div class="estado_menu estado_menu_5 cursor" id="opcion_estado_'+idProceso+'_5" title="Ver Detalle" onclick="verDetalleProceso(this)">	'+
		'				<span class="glyphicon glyphicon-cloud-upload" >			'+
		'				Enviar N&oacute;mina a SAP		'+	
		'				</span>								'+
		'			</div>									'+

					div_bloqueo +
		'		</div>										'+

		'	</div>											';

	$(".menu").append(html);
	
	procesos[idProceso] = bloqueo;
}

function abrirArbolProceso(idProceso){
	var id = '#opciones_proceso_'+idProceso;
	var estado = $(id).is(":visible"); 

	
	$(".estado_menu").hide()
	$(".opciones_proceso").hide();

	if(!estado){
		$(id).show();
		cargarEstadoProceso(idProceso, function(){  });	
	 
	}
}


function cargarEstadoProceso(idProceso, funcion){
	estados = new Array();
	url = url_proceso + idProceso;
		ejecutarAJAX(url, null, function( data ) {

			if(data.item[0]!=null){
				var id_menu = "opcion_estado_"+idProceso+"_0";
				$("#"+id_menu).show();

				var id_menu_1 = "opcion_estado_"+idProceso+"_1";
				$("#"+id_menu_1).show();
				status_actual = 1;				
				estados[0]= true;
				
			}
			if(data.item[1]!=null){
				var id_menu = "opcion_estado_"+idProceso+"_2";
				$("#"+id_menu).show();
				estados[1]= data.item[1].status;
				if(data.item[1].status == "true"){
					var id_menu = "opcion_estado_"+idProceso+"_2";
					$("#"+id_menu).show();
					status_actual = 2;
				}
				else{
					var id_menu = "opcion_estado_"+idProceso+"_2";
					$("#"+id_menu).hide();
					status_actual = 1;
				}
	
			
			}
						
			if(data.item[2]!=null){
				console.log("estado 2-> "+data.item[2].status);
				estados[2]= data.item[2].status;
				if(data.item[2].status == "true"){
					var id_menu = "opcion_estado_"+idProceso+"_3";
					$("#"+id_menu).show();					
					status_actual = 3;
				}		
				else{
					status_actual = 2;
				}	
				
			}
			if(data.item[3]!=null){
				console.log("estado 2-> "+data.item[3].status);				
				estados[3]= data.item[3].status;
				if(data.item[3].status == "true"){
					var id_menu = "opcion_estado_"+idProceso+"_4";
					$("#"+id_menu).show();			
					status_actual = 4;
				}		
				else{
					status_actual = 3;
				}	
				
			}
			if(data.item[4]!=null){
				console.log("estado 2-> "+data.item[4].status);				
				estados[4]= data.item[4].status;
				if(data.item[4].status == "true"){
					var id_menu = "opcion_estado_"+idProceso+"_5";
					$("#"+id_menu).show();			
					status_actual = 5;
				}		
				else{
					status_actual = 4;
				}	
				
			}
			if(data.item[5]!=null){
				console.log("estado 2-> "+data.item[4].status);				
				estados[5]= data.item[5].status;
				status_actual = 5;
				if(data.item[5].status == "false"){
					$("#detalle_etapa_5").html(data.item[5].error);
				}		
				else{
					$("#detalle_etapa_5").html(data.item[5].success);
				}
				
			}

			if(data.item["blocked"]!= null){
				funcion();
			}
			manejarBotones();
		});

}

function verDetalleProceso(obj){

	var periodo = obj.id.split("_")[2];
	var estado = obj.id.split("_")[3];	
	cargarProceso(periodo , estado);

	

}

var procesos = new Array();

function limpiarCampos(){

	$("#fecha_ejecucion_etapa_0").html("");
	$("#ejecutor_etapa_0").html("");
	$("#estado_etapa_0").html("");

	$("#fecha_ejecucion_etapa_1").html("");
	$("#ejecutor_etapa_1").html("");
	$("#detalle_etapa_1").html("");
	$("#estado_etapa_1").html("");

	$("#fecha_ejecucion_etapa_2").html("");
	$("#ejecutor_etapa_2").html("");
	$("#detalle_etapa_2").html("");
	$("#estado_etapa_2").html("");
	
	$("#descripcion_etapa_blocked").html("");
	$("#fecha_ejecucion_etapa_blocked").html("");
	$("#ejecutor_etapa_blocked").html(""); 
	$("#detalle_etapa_blocked").html("");


}

var contadorBloqueo = 0;

function verDetalleBloqueo(idProceso){

	url = url_proceso + idProceso;
	ejecutarAJAX(url, null, function( data ) {

		if(data == null ) return;

		if(contadorBloqueo > 0 && !$(".descripcion_etapa_blocked").is(":visible")){
			return;
		}
		contadorBloqueo++;
			
		
		$("#descripcion_etapa_blocked").html(data.item["blocked"].descripcion);
		$("#fecha_ejecucion_etapa_blocked").html(data.item["blocked"].fecha_creacion);
		$("#ejecutor_etapa_blocked").html(data.item["blocked"].usuario); 
		try{	
		var html = "<br/><br/><div style='padding-left:40px'>";	
			if(data.item["blocked"].procesos != null ){
				for(var i=0; i< data.item["blocked"].procesos.length; i++){
					var proceso = data.item["blocked"].procesos[i];
					html = html + "<font>"+proceso.codigo+":</font><font>&nbsp;&nbsp;"+proceso.estado+"</font><br/>";	
				}
			}
		html = html + "</div>";
		$("#detalle_etapa_blocked").html(html);
		}catch(e){alert(e);}

		$(".descripcion_etapa_blocked").show();
		$(".content_titulo_proceso").show();

		
		var reintentar_en = parseInt(data.item["blocked"].reintentar_en);
		if(reintentar_en > 0){
			setTimeout(function(){ verDetalleBloqueo(idProceso);},reintentar_en );
		}
		else{
			alert("proceso terminado");
			setTimeout( function(){
				
				cargarArbolMenu(function(){ 
					cargarProceso(periodo_seleccionado,data.item["blocked"].status_blocked); 
					abrirArbolProceso(periodo_seleccionado);
					});
				
			}, 5000  );
		}
	});


}


function cargarProceso(idProceso, estado){

	limpiarCampos();

	if(estado == "bloqueo"){
		$(".descripcion_etapa").hide();
		$(".content_titulo_proceso").hide();
		contadorBloqueo = 0;
		verDetalleBloqueo(idProceso);
		return;
	}


	ocultarCargando();
	periodo_seleccionado = idProceso;
	var url = url_detalle_etapa +"&periodo="+idProceso+"&etapa="+estado;
	$(".descripcion_etapa").hide();
	$(".content_titulo_proceso").hide();
	ejecutarAJAX(url, null, function( data ) {
		if(data.item.contenido_archivo == "archivo no encontrado"){
			$(".descripcion_etapa_"+estado).show();
			$(".content_titulo_proceso").show();
		}
		else{
			if(estado == 0 ){
				
				$("#fecha_ejecucion_etapa_0").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_0").html(data.item.contenido_archivo.usuario);
				var status = "Ejecutada";
				$("#estado_etapa_0").html(status);

				$(".descripcion_etapa_0").show();
				$(".content_titulo_proceso").show();

				manejarBotones();				
			}
			else if(estado == 1 ){
			
				$("#fecha_ejecucion_etapa_1").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_1").html(data.item.contenido_archivo.usuario);
				var status = "<font>Ejecutada Exitosamente &nbsp;&nbsp;&nbsp;</font><span class='glyphicon glyphicon-ok' ></span>";
				
				if(data.item.contenido_archivo.status == "false"){
					status = "<font color='#F00'>Ejecutada Con Errores &nbsp;&nbsp;&nbsp;</font><span class='glyphicon glyphicon-flag' ></span>";

				}
				$("#estado_etapa_1").html(status);

				var procesos = "";


				var cantidadProcesos = parseInt(data.item.contenido_archivo.cantidad_procesos);

				var html = "<br/><div >";

				html = html + "<font style='font-weight:bold;' > Cantidad de Procesos: "+ cantidadProcesos +"</font><br/>";
				try{
					for(var i=0; i< cantidadProcesos; i++){
						color = "#666";
						texto = " -> Listo para ejecutar ";
						if( data.item.contenido_archivo.procesos[i].estado == false){
							color ="#F00";
							texto = " -> Informaci&oacute;n no encontrada";
						}
						html = html+ "- <font style='font-weight:bold;' color='"+color+"'> "+ data.item.contenido_archivo.procesos[i].codigo+"</font> &nbsp; "+texto+" <br/>";
				}
				}catch(e){}
				html = html + "</div>";
				$("#detalle_etapa_1").html(html);


				$(".descripcion_etapa_1").show();
				$(".content_titulo_proceso").show();
				manejarBotones();
			}		

			else if(estado == 2 ){
			
				$("#fecha_ejecucion_etapa_2").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_2").html(data.item.contenido_archivo.usuario);
				var status = "<font>Ejecutada Exitosamente &nbsp;&nbsp;&nbsp;</font><span class='glyphicon glyphicon-ok' ></span>";
				if(data.item.contenido_archivo.status == "false"){
					status = "<font color='#F00'>Ejecutada Con Errores &nbsp;&nbsp;&nbsp;</font><span class='glyphicon glyphicon-flag' ></span>";
				}
				$("#estado_etapa_2").html(status);

				var procesos = "";


				var cantidadProcesos = parseInt(data.item.contenido_archivo.cantidad_procesos);

				var html = "<div>";
				var tabla_estados = "<font style='font-weight:bold;' > Procesos Ejecutados </font> </br><table style='border:1px solid #CCC;font-size:11px'><tr style='background-color:#4F81BD;color:#FFF;height:22px;'><td style='width:100px;text-align:center'>Proceso</td><td style='width:180px;text-align:center'>Estado</td></tr>";
				var status_proceso = "";
				for(var i=0; i< data.item.contenido_archivo.procesos.length; i++){
					
					if(data.item.contenido_archivo.procesos[i].estado == true){
						status_proceso = "<strong>OK</strong>";
					}
					else{
						status_proceso = "<strong><font color='red'>ERROR</font></strong>";
					}
					
					tabla_estados = tabla_estados +"<tr style='border-bottom:1px solid #CCC'>"+
					"<td width='140px' style='padding-left:5px;border-right:1px solid #CCC'>"+data.item.contenido_archivo.procesos[i].codigo+"</td>"+
					"<td width='140px' align='center'>"+status_proceso+"</td>"+
				"</tr>"
					
					
				}
				tabla_estados = tabla_estados + "</table>";
				
				html = html + "<br/><br/>"+tabla_estados+"<br/><br/><font style='font-weight:bold;' > Resultado Proceso </font>";
				
				try{
					for(var i=0; i< data.item.contenido_archivo.procesos.length; i++){
						color = "";
						if( data.item.contenido_archivo.procesos[i].estado == false){
							color ="#F00";
						}
						
						
						
						if(data.item.contenido_archivo.procesos[i].detalle != null ){

						var balance = data.item.contenido_archivo.procesos[i].detalle.monto_inicial - data.item.contenido_archivo.procesos[i].detalle.monto_cargados - data.item.contenido_archivo.procesos[i].detalle.monto_no_cargados - data.item.contenido_archivo.procesos[i].detalle.total_parciales;

						var color_balance = "";
						if(balance == 0){
							color_balance = ";background-color:#337822; color:#FFF";
							balance = "<font style='font-weight:bold;'>"+formatearMonto(balance)+"</font>";
						}
						else{
							color_balance = ";background-color:#F00; color:#FFF";
							balance = "<font style='font-weight:bold;'>"+formatearMonto(balance)+"</font>";
						}

						try{				
						html = html +	"<table style='border:1px solid #CCC;font-size:14px'>"+
									"<tr style='background-color:#4F81BD;color:#FFF;height:22px;'>"+
										"<td width='140px' style='padding-left:5px;'>"+data.item.contenido_archivo.procesos[i].codigo+"</td>"+
										"<td width='140px' align='center'>Registros</td>"+
										"<td width='140px' align='center'>Montos</td>"+
									"</tr>"+
									"<tr  style='border-bottom:1px solid #CCC;height:22px;'>"+
										"<td style='padding-left:5px;'>Iniciales</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearNumero(data.item.contenido_archivo.procesos[i].detalle.registros_iniciales)+"</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearMonto(data.item.contenido_archivo.procesos[i].detalle.monto_inicial)+"</td>"+
									"</tr>"+

									"<tr style='border-bottom:1px solid #CCC;height:22px;'>"+
										"<td style='padding-left:5px;'>Cargados</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearNumero(data.item.contenido_archivo.procesos[i].detalle.registros_cargados)+"</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearMonto(data.item.contenido_archivo.procesos[i].detalle.monto_cargados)+"</td>"+
									"</tr>"+
									"<tr style='border-bottom:1px solid #CCC;height:22px;'>"+
										"<td style='padding-left:5px;'>Parciales</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearNumero(data.item.contenido_archivo.procesos[i].detalle.registros_parciales)+"</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearMonto(data.item.contenido_archivo.procesos[i].detalle.total_parciales)+"</td>"+
									"</tr>"+
									"<tr style='border-bottom:1px solid #CCC;height:22px;'>"+
										"<td style='padding-left:5px;'>No Cargados</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearNumero(data.item.contenido_archivo.procesos[i].detalle.registros_no_cargados)+"</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearMonto(data.item.contenido_archivo.procesos[i].detalle.monto_no_cargados)+"</td>"+
									"</tr>"+
"</tr>"+
									"<tr  style='border-bottom:1px solid #CCC;height:22px;'>"+
										"<td style='padding-left:5px;'>Cuotones</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearNumero(data.item.contenido_archivo.procesos[i].detalle.registros_cuotones)+"</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC'>"+formatearMonto(data.item.contenido_archivo.procesos[i].detalle.monto_cuotones)+"</td>"+
									"</tr>"+
"<tr style='height:22px;'>"+
										"<td style='padding-left:5px;background-color:#4F81BD;color:#FFF' colspan='2'>Diferencias</td>"+
										"<td style='text-align:right;padding-right:8px;border-left:1px solid #CCC;"+color_balance+"'>"+balance+"</td>"+
									"</tr>"+

								"</table><br/>";
						}catch(e){}
						}
					}
				}catch(e){alert(e);}
				html = html + "</div>";
				$("#detalle_etapa_2").html(html);


				$(".descripcion_etapa_2").show();
				$(".content_titulo_proceso").show();
				manejarBotones();

			}
			else if(estado == 3 ){
				
				$("#fecha_ejecucion_etapa_3").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_3").html(data.item.contenido_archivo.usuario);
				var status = "Ejecutada";
				$("#estado_etapa_3").html(status);

				$(".descripcion_etapa_3").show();
				$(".content_titulo_proceso").show();

				manejarBotones();
			}

			
			else if(estado == 4 ){
				
				$("#fecha_ejecucion_etapa_4").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_4").html(data.item.contenido_archivo.usuario);
				var status = "Ejecutada";
				$("#estado_etapa_4").html(status);

				$(".descripcion_etapa_4").show();
				$(".content_titulo_proceso").show();
				manejarBotones();
			}

			else if(estado == 5 ){
				
				$("#fecha_ejecucion_etapa_5").html(data.item.contenido_archivo.fecha);
				$("#ejecutor_etapa_5").html(data.item.contenido_archivo.usuario);
				var status = "Ejecutada";
				$("#estado_etapa_5").html(status);

				$(".descripcion_etapa_5").show();
				$(".content_titulo_proceso").show();
				manejarBotones();
			}

		
		}
	});
}

function manejarBotones(){
	$("#btn_1_ejecutar").hide();
	$("#btn_2_ejecutar").hide();
	$("#btn_2_rollback").hide();
	$("#btn_3_ejecutar").hide();
	$("#btn_4_ejecutar").hide();
	$("#btn_5_ejecutar").hide();

	for(var i=0; i< estados.length; i++){
		console.log("estado["+i+"] "+ estados[i])
	}

	try{
		if(estados[5] != null && estados[5] == "false" ){	
			console.log("en envio error ");			
			$("#btn_5_ejecutar").show();	
		}
		else if(estados[5] != null && estados[5] ){	
			console.log("en envio ok ");		
			$("#btn_5_ejecutar").hide();		
			$("#btn_4_ejecutar").hide();		
			$("#btn_3_ejecutar").hide();		
			$("#btn_2_ejecutar").hide();
		}
		else if(estados[4] != null && estados[4]== false){
			console.log("en disponibilizacion error ");
			$("#btn_4_ejecutar").show();		
			$("#btn_3_ejecutar").hide();		
			$("#btn_2_ejecutar").hide();
		}
		else if(estados[4] != null && estados[4]){
			console.log("en disponibilizacion ok ");			
			$("#btn_5_ejecutar").show();
			$("#btn_4_ejecutar").hide();
		}
		else if(estados[3] != null && estados[3]){
			console.log("en generacion");
			$("#btn_3_ejecutar").show();
			$("#btn_4_ejecutar").show();
		}
		else if(estados[2] != null && estados[2]){
			console.log("en consolidacion");
			$("#btn_3_ejecutar").show();
			$("#btn_2_rollback").show();
		}
		else if(estados[1] != null && estados[1]){
			console.log("en certificacion");
			$("#btn_1_ejecutar").show();
			$("#btn_2_ejecutar").show();
		}
		else if(estados[0] != null && estados[0]){
			console.log("apertura ok");
			$("#btn_1_ejecutar").show();
		}

	}
	catch(e){}

	/*

	if(status_actual == 1){
		$("#btn_1_ejecutar").show();
	}
	else if(status_actual == 2 ){
		if(estados[2] == true){
			$("#btn_2_rollback").show();
		}
		else{
			$("#btn_2_ejecutar").show();			
			$("#btn_1_ejecutar").show();
		}
	}
	else if(status_actual == 3 ){
			$("#btn_3_ejecutar").show();
			$("#btn_2_rollback").show();			
	}

	for(var i=0; i< estados.length; i++){
		console.log("estado["+i+"] "+ estados[i])
	}
	*/

}


function mostrarCargando(){
	$(".loading").show();
	$(".descripcion_etapa").hide();
}
function ocultarCargando(){
	$(".loading").hide();
}

function validarProcesoBloqueado(){
	idProceso = periodo_seleccionado;
	try{
		if(procesos[idProceso]){ 
		
		alert("Proceso bloqueado");
		return true;
		}
	} catch(e){}
	return false;

}

$("#btn_1_ejecutar").click(function() {
		if(validarProcesoBloqueado()) return;
		mostrarCargando();

		setTimeout( function (){
			url = url_validar_archivo_SAP +"&periodo="+periodo_seleccionado;
			ejecutarAJAX(url, null, function( data ) {
				cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,1); abrirArbolProceso(periodo_seleccionado);});

			});
		}, 1000);
});
$("#btn_2_ejecutar").click(function() {
		if(validarProcesoBloqueado()) return;
		mostrarCargando();
		setTimeout( function (){
			$("#btn_2_ejecutar").hide()
			url = url_cargar_archivo_SAP +"&periodo="+periodo_seleccionado;
			ejecutarAJAX(url, null, function( data ) {});
				setTimeout( function(){

					cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,"bloqueo"); abrirArbolProceso(periodo_seleccionado); ocultarCargando();});

				}, 3000);
		}, 1000);

});
$("#btn_2_rollback").click(function() {
		
		if(validarProcesoBloqueado()) return;
		mostrarCargando();
		setTimeout( function (){
			$("#btn_2_rollback").hide();
			url = url_rollback_carga +"&periodo="+periodo_seleccionado;
			ejecutarAJAX(url, null, function( data ) {});
				setTimeout( function(){

					cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,"2"); abrirArbolProceso(periodo_seleccionado); ocultarCargando();});

				}, 3000);
		}, 1000);

});

$("#btn_3_ejecutar").click(function() {
		if(validarProcesoBloqueado()) return;
		mostrarCargando();
		setTimeout( function (){
			$("#btn_3_ejecutar").hide()
			url = url_generar_nominas;
			ejecutarAJAX(url, null, function( data ) {});
				setTimeout( function(){

					cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,"bloqueo"); abrirArbolProceso(periodo_seleccionado); ocultarCargando();});

				}, 1000);
		}, 1000);

});


$("#btn_4_ejecutar").click(function() {
		if(validarProcesoBloqueado()) return;
		mostrarCargando();
		setTimeout( function (){
			$("#btn_3_ejecutar").hide()
			url = url_enviar_nominas;
			ejecutarAJAX(url, null, function( data ) {});
				setTimeout( function(){

					cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,"bloqueo"); abrirArbolProceso(periodo_seleccionado); ocultarCargando();});

				}, 1000);
		}, 1000);

});

$("#btn_5_ejecutar").click(function() {
		if(validarProcesoBloqueado()) return;
		mostrarCargando();

		setTimeout( function (){
			url = url_envio_sap;
			ejecutarAJAX( url,null, function( data ) {
				ocultarCargando();
				cargarArbolMenu(function(){ cargarProceso(periodo_seleccionado,"bloqueo"); abrirArbolProceso(periodo_seleccionado); ocultarCargando();});

			});
		}, 1000);

});
function cargarArbolMenu(funcion){
	$(".menu_proceso").remove();
	ejecutarAJAX(url_arbol_procesos, null , function( data ) {
		procesos = new Array();
		for(var i=0; i< data.item.procesos.procesos.length; i++){
			var ultimoProceso = -1;
			var abrir = false;
			var bloqueado = false;
			if(i == -1){
				abrir = true;		
			}
			try{
				if(data.item.procesos.procesos[i].bloqueado == true){
					bloqueado = true;			
				}
			}catch(e){}
			try{
				var ultimo_archivo = data.item.procesos.procesos[i].ultimo_archivo.split(".txt")[0];
				ultimoProceso = parseInt(ultimo_archivo);
			}catch(e){}
			agregarProceso(data.item.procesos.procesos[i].periodo,data.item.procesos.procesos[i].periodo,ultimoProceso,abrir, bloqueado);
		}

	if(funcion != null){
		funcion();
	}
});
}

cargarArbolMenu();


function formatearNumero(numero){
	numero = numero +"";
	var numeroFormateado = "";
	var contador = 0;
	for(var i=numero.length; i>=0; i--){		
		numeroFormateado = numero.charAt(i) + numeroFormateado;
		if(contador > 0 && contador % 3 == 0 && contador < numero.length ){
			numeroFormateado = "."+numeroFormateado;
		}		
		contador++;
	}
	return numeroFormateado;
}

function formatearMonto(monto){
	return "$"+formatearNumero(monto);
}


window.history.forward();
function noBack()
{
    window.history.forward();
}


</script>

</body>
</html>
