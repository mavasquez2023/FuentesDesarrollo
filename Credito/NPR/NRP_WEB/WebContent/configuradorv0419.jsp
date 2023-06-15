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
<title>Gestor de Nóminas - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="js/comun.js"></script>
<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/comun.js?<%=Math.random()%>"></script>


<style>

#content_liv{
width:1000px;
height:600px;
background-color:#FFF;
}

.menu{
width:180px;
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
font-size:10px;
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





#columnas_configuradas{
width:370px;
border: 1px solid #CCC;
}

#columnas_disponibles{
width:370px;
border: 1px solid #CCC;
}

.div_columna_titulo{
width:348px;
height:30px;
padding-bottom:10px;
margin-top:5px;
margin-bottom:10px;
margin-left:11px;
padding-top:5px;
padding-left:5px;
background-color:#FFF;
font-size:15px;
color:#333;
}
.div_columna{
width:328px;
border: 1px solid #005D8B;
padding-bottom:10px;
margin-top:5px;
margin-bottom:5px;
margin-left:10px;
background-color:#FFF;
border-bottom-left-radius:5px;
border-bottom-right-radius:5px;
}

.titulo_columna{
width:100%;
height:38px;
border-bottom: 1px solid #CCC;
background-color:#005D8B;
padding-left:10px;
padding-top:5px;
color:white;
font-weight:bold;
font-size:12px;
}

.data_columna{
width:100%;
height:15px;
padding-left:10px;
padding-top:5px;
font-size:10px;
}
.titulo_data_columna{
font-weight:bold;
}
.font_titulo_columna{
	
}
.font_data_columna{
	
}
.columna_seleccionada{
border:1px solid #227A05;
background-color:#D0EBC7;
}
.data_ej_seleccionada{
	font-weight:bold;
	color:#000;
	font-size:14px;
}
#ejemplo_salida{
	color:#666;
	font-size:13px;
	padding-left:10px;
}

.data_ejemplo{
	padding-left:30px;
}

.container_conf{
margin-top:20px;
border-top:2px solid #DDD;
padding-top:5px;

}

.container_columnas_configurador{
margin-top:10px;
width:750px;
}

.content_columnas_configurador{
width:350px;
border: 1px solid #CCC;

}

.link_ver_mas_menos{
float:right;
padding-right:10px;
}

.opcion_habilitar_deshabilitar{
float:right;
padding-right:10px;
}
.clickeable{

cursor:hand;
cursor:pointer;
}

.cols_conf_titulo{
	padding-top:3px;
	padding-left:20px;
	width:100%;
	background-color:#DDD;
	height:23px;

}

.cols_disp_titulo{
	padding-top:3px;
	padding-left:20px;
	width:100%;
	background-color:#DDD;
	height:25px;
	
}
.sin_columnas{
	text-align:center;
	padding-top:8px;
}

.texto_reporte{
font-size:18px;
margin-left:10px;
color:#666;
font-weight:bold;
}
</style>

</head>
<body class="corp">


<div id="content" class="container_12">
	<div class="grid_12">
		<img src="images/cabecera_claves.jpg" />
		<%@include file="menu.jsp" %>
		<div id="content_liv">

			<div id="main_content" > 
				<div id="configurador">
					<h2>Configurador Nómina: <font id="id_reporte" class="texto_reporte"></font></h2>
					<div id="configurable">
						<div id="div_opciones"> <h4>Opciones</h4>
							<button type='button' class='btn btn-default btn-sm' style="margin-left:10px;"  onclick="volverAConfiguracion()">
								<span class='glyphicon glyphicon-arrow-left'></span> Volver a Configuraciones
							</button> 
							<button type='button' class='btn btn-success btn-sm' style="margin-left:10px;" title='Guardar Cambios' onclick="guardarCambios()">
								<span class='glyphicon glyphicon-floppy-saved'></span> Guardar Cambios
							</button> 

						</div>
						<div id="div_seleccion_seccion">
							<h4>Secciones</h4>
							<div style="margin-left:10px;">
								<font onclick="javascript:verMas('header')" id="link_header_ver_mas" class="clickeable" > 
									<span class="label label-default"><span class="glyphicon glyphicon-menu-down"></span> Header </span>
								</font>
								<font onclick="javascript:verMenos('header')" id="link_header_ver_menos" class="clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-menu-up"></span> Header </span>
								</font>
								&nbsp;

								<font onclick="javascript:verMas('detalle')" id="link_detalle_ver_mas" class="clickeable" > 
									<span class="label label-default"><span class="glyphicon glyphicon-menu-down"></span> Detalle </span>
								</font>
								<font onclick="javascript:verMenos('detalle')" id="link_detalle_ver_menos" class="clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-menu-up"></span> Detalle </span>
								</font>
								&nbsp;

								<font onclick="javascript:verMas('footer')" id="link_footer_ver_mas" class="clickeable" > 
									<span class="label label-default"><span class="glyphicon glyphicon-menu-down"></span> Footer </span>
								</font>
								<font onclick="javascript:verMenos('footer')" id="link_footer_ver_menos" class="clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-menu-up"></span> Footer </span>
								</font>
							</div>
						</div>

						<!-- inicio seccion configurador -->
						<div id="div_conf_header" class="container_conf" style="display:none">
							<h4>Configuraci&oacute;n Secci&oacute;n Header</h4>
							<div style="margin-left:10px">
								<font onclick="javascript:deshabilitar('header')"  class="conf_header_habilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-warning"><span class="glyphicon glyphicon-remove"></span> Deshabilitar</span>
								</font>

								<font  onclick="javascript:habilitar('header')" class="conf_header_deshabilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-ok"></span> Habilitar</span>
								</font>
								<br/>
								<font>Estado:</font>
								<font id="estado_conf_header_habilitado"  class="conf_header_habilitado"> 
									<span class="label label-success">Habilitado</span>
								</font>

								<font id="estado_conf_header_deshabilitado"  class="conf_header_deshabilitado"> 
									<span class="label label-warning">Deshabilitado</span>
								</font>
								<br>
								<font id="estado_conf_header_data_ejemplo"  class="conf_header_habilitado"> 
									<font>Ejemplo Registro:</font><br/>
									<font id="data_ejemplo_header" class="data_ejemplo"></font>
								</font>

								<div id="div_content_header" class="conf_header_habilitado container_columnas_configurador" >
									<table width="100%">
										<tr>
											<td  valign="top">
												<div id="header_cols_conf" style='float:left' class="content_columnas_configurador"> 
													<div class='cols_conf_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Configuradas</div>
													<div id="header_cols_conf_content" ></div>
													<div id="header_cols_conf_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
											<td valign="top">
												<div id="header_cols_disp" style='float:right'  class="content_columnas_configurador">
													<div class='cols_disp_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Disponibles</div>
													<div id="header_cols_disp_content" ></div>									
													<div id="header_cols_disp_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
										</tr>
									</table>		
								</div>
							</div>
						</div>
						<!-- fin seccion configurador -->



						<!-- inicio seccion configurador -->
						<div id="div_conf_detalle" class="container_conf" style="display:none">
							<h4>Configuraci&oacute;n Secci&oacute;n Detalle</h4>
							<div style="margin-left:10px">
								<font onclick="javascript:deshabilitar('detalle')"  class="conf_detalle_habilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-warning"><span class="glyphicon glyphicon-remove"></span> Deshabilitar</span>
								</font>

								<font  onclick="javascript:habilitar('detalle')" class="conf_detalle_deshabilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-ok"></span> Habilitar</span>
								</font>
								<br/>
								<font>Estado:</font>
								<font id="estado_conf_detalle_habilitado"  class="conf_detalle_habilitado"> 
									<span class="label label-success">Habilitado</span>
								</font>

								<font id="estado_conf_detalle_deshabilitado"  class="conf_detalle_deshabilitado"> 
									<span class="label label-warning">Deshabilitado</span>
								</font>
								<br>
								<font id="estado_conf_detalle_data_ejemplo"  class="conf_detalle_habilitado"> 
									<font>Ejemplo Registro:</font><br/>
									<font id="data_ejemplo_detalle" class="data_ejemplo"></font>
								</font>
								<div id="div_content_detalle" class="conf_detalle_habilitado container_columnas_configurador" >
									<table width="100%">
										<tr>
											<td  valign="top">
												<div id="detalle_cols_conf" style='float:left' class="content_columnas_configurador"> 
													<div class='cols_conf_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Configuradas</div>
													<div id="detalle_cols_conf_content" ></div>
													<div id="detalle_cols_conf_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
											<td valign="top">
												<div id="detalle_cols_disp" style='float:right'  class="content_columnas_configurador">
													<div class='cols_disp_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Disponibles</div>
													<div id="detalle_cols_disp_content" ></div>									
													<div id="detalle_cols_disp_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
										</tr>
									</table>		
								</div>
							</div>
						</div>
						<!-- fin seccion configurador -->



											<!-- inicio seccion configurador -->
						<div id="div_conf_footer" class="container_conf" style="display:none">
							<h4>Configuraci&oacute;n Secci&oacute;n Footer</h4>
							<div style="margin-left:10px">
								<font onclick="javascript:deshabilitar('footer')"  class="conf_footer_habilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-warning"><span class="glyphicon glyphicon-remove"></span> Deshabilitar</span>
								</font>

								<font  onclick="javascript:habilitar('footer')" class="conf_footer_deshabilitado opcion_habilitar_deshabilitar clickeable" > 
									<span class="label label-success"><span class="glyphicon glyphicon-ok"></span> Habilitar</span>
								</font>
								<br/>
								<font>Estado:</font>
								<font id="estado_conf_footer_habilitado"  class="conf_footer_habilitado"> 
									<span class="label label-success">Habilitado</span>
								</font>

								<font id="estado_conf_footer_deshabilitado"  class="conf_footer_deshabilitado"> 
									<span class="label label-warning">Deshabilitado</span>
								</font>
								<br>
								<font id="estado_conf_footer_data_ejemplo"  class="conf_footer_habilitado"> 
									<font>Ejemplo Registro:</font><br/>
									<font id="data_ejemplo_footer" class="data_ejemplo"></font>
								</font>
								<div id="div_content_footer" class="conf_footer_habilitado container_columnas_configurador" >
									<table width="100%">
										<tr>
											<td valign="top">
												<div id="footer_cols_conf" style='float:left' class="content_columnas_configurador"> 
													<div class='cols_conf_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Configuradas</div>
													<div id="footer_cols_conf_content" ></div>
													<div id="footer_cols_conf_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
											<td valign="top">
												<div id="footer_cols_disp" style='float:right'  class="content_columnas_configurador">
													<div class='cols_disp_titulo' > <span class='glyphicon glyphicon-list-alt'></span>&nbsp;&nbsp; Columnas Disponibles</div>
													<div id="footer_cols_disp_content" ></div>									
													<div id="footer_cols_disp_sin_columnas" class='sin_columnas' > <span class='glyphicon glyphicon-info-sign'></span> Sin Columnas</div>
												</div>
											</td>
										</tr>
									</table>		
								</div>
							</div>
						</div>
						<!-- fin seccion configurador -->
					<div>
				</div>

			</div>

					<div id="no_configurable" style="display:none">
						Este Formato no es configurable, cont&aacute;ctese con el administrador.
					</div>		
		</div>
	</div>
</div>

<!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Agregar Columna Constante</h4>
        </div>
        <div class="modal-body">
          
			<div class="form-group">
				<label for="col_const_nombre">Identificador</label>
				<input type="text" class="form-control" id="col_const_nombre" placeholder="Nombre" value="data_constante_1" readonly>
			</div>
			<div class="form-group">
				<label for="col_const_header">Nombre</label>
				<input type="text" class="form-control" id="col_const_header" placeholder="Ingrese Nombre Dato" value="" >
			</div>
			
			<div class="form-group">
				<label for="col_const_texto">Texto</label>
				<input type="text" class="form-control" id="col_const_texto" placeholder="Ingrese texto constante" value="" >
			</div>

        </div>
        <div class="modal-footer">
			
          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModal()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 


          	<button type='button' class='btn btn-success btn-sm' title='Guardar Cambios' onclick="agregarColumnaConstante()">
				<span class='glyphicon glyphicon-plus'></span> Agregar Columna
			</button> 

        </div>
      </div>
      
    </div>
  </div>
 


 <div class="modal fade" id="pantallaVariableSistema" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"></button>
          <h4 class="modal-title">Agregar Variable de Sistema</h4>
        </div>
        <div class="modal-body">
          
			
			<div class="form-group">
				<label for="col_variable_nombre">Seleccione Variable </label>
				<select id="col_variable_select" onchange="asignarVariable()"><option>Seleccione</option></select>
			</div>

			<div class="form-group">
				<label for="col_variable_nombre">Identificador</label>
				<input type="text" class="form-control" id="col_variable_nombre" placeholder="Nombre" value="data_variable_1" readonly>
			</div>
			<div class="form-group">
				<label for="col_variable_header">Nombre</label>
				<input type="text" class="form-control" id="col_variable_header" placeholder="Ingrese Nombre Dato" value="" >
			</div>	
			<div class="form-group">
				<label for="col_variable_texto">Variable Seleccionada</label>
				<input type="text" class="form-control" id="col_variable_texto" placeholder="Ingrese texto constante" value="" readonly>
			</div>	
			<div class="form-group">
				<label for="col_variable_ejemplo">Ejemplo Valor</label>
				<input type="text" class="form-control" id="col_variable_ejemplo" placeholder="Ingrese texto constante" value="" readonly>
			</div>

        </div>
        <div class="modal-footer">
		
          	<button type='button' class='btn btn-default btn-sm' title='Guardar Cambios' onclick="ocultarModalVariable()">
				<span class='glyphicon glyphicon-back'></span> Cancelar
			</button> 


          	<button type='button' class='btn btn-success btn-sm' title='Guardar Cambios' onclick="agregarColumnaDataVariable()">
				<span class='glyphicon glyphicon-plus'></span> Agregar Columna
			</button> 

        </div>
      </div>
      
    </div>
  </div>
  

</body>
</html>

<script>

var id_reporte = '<%=request.getParameter("r")%>';
var url_configuracion_nomina = "/NRP/request?formato_salida=json&id_req=obtenerConfiguracionNomina&formato_completo=formato_completo_nominas&uri_configuracion="+id_reporte;

$("#id_reporte").html(id_reporte);

var ultima_columna_abierta = "";
var separador = "";

separador = ";";


var dataConfiguracion = new Array();

setTimeout( function(){
		mostrarCargando("#configurador");
		init();
	}, 1000);
function init(){
	ejecutarAJAX( url_configuracion_nomina,null,  function( data ) {
	dataConfiguracion = data.item.columnas;
	for(var i=0; i < data.item.columnas.length ; i++){
		var configuracion = data.item.columnas[i];
		

		data.item.columnas[i].posicion_inicial = data.item.columnas[i].posicion; 
		if(configuracion.ejecutar=="true"){
			estado_conf[configuracion.id]="habilitado";
			validarEstadoConfDiv(configuracion.id);
			verMas(configuracion.id);
		}
		else{
			estado_conf[configuracion.id]="deshabilitado";
			validarEstadoConfDiv(configuracion.id);
		}

		procesarColumnas(configuracion.id,true);
		
	}
	ocultarCargando("#configurador")
});
}

function procesarColumnas(div, agregar){

	$("#"+div+"_cols_conf_content .div_columna").remove();
	$("#"+div+"_cols_conf_content .btn_add_constante").remove();
	if(agregar){
		$("#"+div+"_cols_disp_content .div_columna").remove();
		seleccionados[div] = new Array();
		for(var i=0; i< dataConfiguracion.length; i++){

			if(dataConfiguracion[i].id == div){
				var columnas = dataConfiguracion[i].data;

				for(var j=0; j< columnas.length; j++){
						agregarColumna(div, columnas[j])
				}
			}
		}
	}

var btn = "<div style='width:100%;text-align:right;padding-right:10px;padding-top:5px' class='btn_add_constante'><button type='button' class='btn btn-success btn-sm ' title='Agregar Columna' style='padding-right:10px;'  onclick='cargarFomularioConstante(\""+div+"\");'><span class='glyphicon glyphicon-plus'></span>&nbsp;Agregar Constante</button></div><div style='width:100%;text-align:right;padding-right:10px;padding-top:5px' class='btn_add_constante'><button type='button' class='btn btn-info btn-sm ' title='Agregar Columna' style='padding-right:10px;'  onclick='cargarFomularioVariableSistema(\""+div+"\");'><span class='glyphicon glyphicon-plus'></span>&nbsp;Agregar Variable Sistema</button></div>";
	$("#"+div+"_cols_conf_content").append(btn);
	var id_container = div+"_cols_conf_content";
	ordenarPorPosicion(div); 
	for(var i=0; i< seleccionados[div].length; i++){
		seleccionados[div][i].posicion = ( i + 1 );
		putDivColumna(div, seleccionados[div][i], id_container);

	}
	
}

function getLetraPorIndice(indice){
	return fill(indice+"",4,"derecha","0")
}

function fill(texto, largo, alineacion, relleno){
	for(var i=texto.length; i < largo; i++){
		if(alineacion =="derecha"){
			texto = relleno + texto;
		}
		else{
			texto = texto + relleno;
		}
	}
	return texto;
}

function ordenarPorPosicion(div){
	var aOrdenar = new Array();
	var seleccionadosAux = new Array();
	if(seleccionados[div] != null && seleccionados[div].length > 1){
		for(var i=0; i< seleccionados[div].length; i++){
			aOrdenar.push(getLetraPorIndice(seleccionados[div][i].posicion)+"::"+seleccionados[div][i].nombre)
			
		}
		
		console.log("array -> " + aOrdenar);
		aOrdenar.sort();
		console.log("array -> " + aOrdenar);
		for(var i=0; i< aOrdenar.length; i++){
			var partes = aOrdenar[i].split("::");
			console.log(partes[0] +" -> "+partes[1])
			seleccionadosAux.push(getDataFromArray(seleccionados[div],"nombre",partes[1]));
		}
		console.log("seleccionados -> "+ seleccionadosAux)

	}
	seleccionados[div] = seleccionadosAux;
	
	
			
}

function getDataFromArray(array, key, valor){

	for(var i=0; i< array.length; i++){
		if(array[i][key]==valor){
			return array[i];
		}
	}
}


function esSeleccionado(div, nombre){
	if(seleccionados[div] != null && seleccionados[div].length > 0){
		for(var i=0; i< seleccionados[div].length; i++){
			if( seleccionados[div][i].nombre == nombre){
				return true;
			}
		}
	}
	return false;
}


function getSeleccionado(div, nombre){
	if(seleccionados[div] != null && seleccionados[div].length > 0){
		for(var i=0; i< seleccionados[div].length; i++){
			if( seleccionados[div][i].nombre == nombre){
				return seleccionados[div][i];
			}
		}
	}
	return false;
}





function obtenerColumnasPorDiv(div){
	for(var i=0; i< dataConfiguracion.length; i++){
		if(dataConfiguracion[i].id == div){
			var columnas = dataConfiguracion[i].data;
			return columnas
		}
	}

}

var posiciones = new Array();
posiciones["header"] = 0;
posiciones["detalle"] = 0;
posiciones["footer"] = 0;

var seleccionados = new Array();
seleccionados["header"] = new Array();
seleccionados["detalle"] = new Array();
seleccionados["footer"] = new Array();

function agregarColumna(div, data){
	var id_container = div + "_cols_";
	if(data.configurada){
		id_container = id_container+"conf_content";
		$("#"+div+"_cols_conf_sin_columnas").hide();
		seleccionados[div].push(  data );
	}
	else{
		id_container = id_container+"disp_content";
		$("#"+div+"_cols_disp_sin_columnas").hide();
		putDivColumna(div,data, id_container);
	}
	
}


function putDivColumna(div, data, id_container){

	var opciones_configurada = "opciones_"+div+"_"+data.nombre+"_configurada";
	var opciones_disponible = "opciones_"+div+"_"+data.nombre+"_disponible";

	var dataPreparada = obtenerDataPreparada(data);


	var unique_key = div+"_"+data.nombre;
	var posicion = "";
	if(data.configurada){
		posicion = data.posicion;
	}

	unique_key = unique_key.replace(".","");

	var _div = "<div id='col_"+div+"_"+data.nombre+"' class='div_columna' onmouseover='seleccionarColumna(\""+div+"\",\""+data.nombre+"\")' onmouseout='deseleccionarColumna(\""+div+"\",\""+data.nombre+"\")'> "+
							
							"<div class='titulo_columna'>"+
									"<button type='button' class='btn btn-success btn-sm "+opciones_disponible+"' title='Agregar Columna' style='display:none'  onclick='addColumna(\""+div+"\",\""+data.nombre+"\")'>"+
									"	<span class='glyphicon glyphicon-arrow-left'></span>"+
									"</button>&nbsp; "+
								"<font ></font>&nbsp<font>"+  data.header  +"</font>"+
								"<div style='float:right;padding-right:3px'>"+
									"<button type='button' class='btn btn-success btn-sm "+opciones_configurada+"' title='Subir Columna' style='display:none' onclick='upColumna("+posicion+",\""+div+"\",\""+data.nombre+"\")'>"+
									"	<span class='glyphicon glyphicon-circle-arrow-up'></span>"+
									"</button> "+
									"<button type='button' class='btn btn-warning btn-sm "+opciones_configurada+"' title='Bajar Columna' style='display:none' onclick='downColumna("+posicion+",\""+div+"\",\""+data.nombre+"\")'>"+
									"	<span class='glyphicon glyphicon-circle-arrow-down'></span>"+
									"</button> "+
									"<button type='button' class='btn btn-danger btn-sm "+opciones_configurada+"' title='Eliminar Columna' style='display:none'  onclick='removeColumna(\""+div+"\",\""+data.nombre+"\")'>"+
									"	<span class='glyphicon glyphicon-arrow-right'></span>"+
									"</button>"+
								"</div>"+
							"</div>"+

							"<div class='data_columna'>"+
								"<font class='titulo_data_columna'>Posici&oacute;n: </font>&nbsp<font>"+posicion+"</font>"+
								"<div style='float:right;padding-right:30px'>"+
									"<font class='titulo_data_columna'>Largo: </font>&nbsp<font><input type='text' id='largo_"+unique_key+"' value='"+data.largo+"' class='spinner' size='2' style='text-align:center' onkeyPress='validaTecla(event)' onchange='cambiarLargo(\""+div+"\",\""+data.nombre+"\")'/></font>"+
									"<font class='data_columna ver_mas_"+unique_key+"' style='text-center;'><a href=\"javascript:verDetalleColumna('"+div+"','"+data.nombre+"')\"> Ver M&aacute;s</a></font>"+
								"</div>"+
							"</div>"+

							"<div class='data_columna data_adicional_"+unique_key+"' style='display:none'>"+
								"<font class='titulo_data_columna'>Alineamiento Texto: </font>&nbsp<font><select id='alineamiento_"+unique_key+"' onchange='cambiarAlineamiento(\""+div+"\",\""+data.nombre+"\")'><option value='derecha'>Derecha</option><option value='izquierda'>Izquierda</option></select></font>"+
								"<div style='float:right;padding-right:30px'>"+
									"<font class='titulo_data_columna'>Relleno: </font>&nbsp<font><select id='relleno_"+unique_key+"' onchange='cambiarRelleno(\""+div+"\",\""+data.nombre+"\")'><option value='0'>0</option><option value='blanco'>Blanco</option></select></font>"+
								"</div>"+
							"</div>"+

							"<div class='data_columna data_adicional_"+unique_key+"' style='display:none'>"+
								"<font class='titulo_data_columna'>Ejemplo: </font>&nbsp<font>"+  data.data_ej  +"</font>"+
							"</div>"+
							"<div class='data_columna data_adicional_"+unique_key+"' style='display:none'>"+
								"<font class='titulo_data_columna'>Ejemplo Salida: </font>&nbsp<font id='data_ejemplo_col_"+unique_key+"'>["+   data.ejemplo_salida  +"]</font>"+
									"<font class='data_columna ver_menos_"+unique_key+"' style='text-center;'><a href=\"javascript:ocultarDetalleColumna('"+div+"','"+data.nombre+"')\"> Ver Menos</a></font>"+
							"</div>"+

						"</div>";


	$("#"+id_container).append(_div);


	$("#alineamiento_"+unique_key).val(data.alineamiento.toLowerCase());


	if(data.relleno.toLowerCase().trim().length == 0){
		$("#relleno_"+unique_key).val("blanco");
	}
	if(data.relleno.toLowerCase().trim() == "0"){
		$("#relleno_"+unique_key).val("0");
	}

	if(ultima_columna_abierta == data.nombre){
		verDetalleColumna(data.nombre);
	}

	if(data.configurada)
		$("#data_ejemplo_"+div ).append("<font class='data_ejemplo_txt_"+unique_key+"'>"+data.ejemplo_salida+"</font> ");

	if(data.configurada){
		$("."+opciones_configurada).show();
	}
	else{
		$("."+opciones_disponible).show();
	}

}


function verDetalleColumna(div, nombre){
	var unique_key = div+"_"+nombre;
	unique_key = unique_key.replace(".","");

	$(".data_adicional_"+unique_key).show();
	$(".ver_mas_"+unique_key).hide();
	ultima_columna_abierta = nombre;
}
function ocultarDetalleColumna(div, nombre){
	var unique_key = div+"_"+nombre;
	unique_key = unique_key.replace(".","");

	$(".data_adicional_"+unique_key).hide();
	$(".ver_mas_"+unique_key).show();
}

function removeColumna(div, nombre){
	marcarColumnaSeleccionada(div, nombre);
	var columnas = obtenerColumnasPorDiv(div);
	for(var i=0; i< columnas.length; i++){
		if(columnas[i].nombre == nombre ){
			columnas[i].configurada = false;
			i=columnas.length;
		}
	}
	
	procesarColumnas(div,true);
}

function addColumna(div, nombre){
	marcarColumnaSeleccionada(div, nombre);
	$("#data_ejemplo_"+div).html("");
	var columnas = obtenerColumnasPorDiv(div);
	for(var i=0; i< columnas.length; i++){
		if(columnas[i].nombre == nombre ){
			columnas[i].configurada = true;
			i=columnas.length;
		}
	}

	procesarColumnas(div,true);

}


function upColumna(posicion, div, nombre){
	marcarColumnaSeleccionada(div, nombre);
	if(posicion>1){
		$("#data_ejemplo_"+div).html("");
		var posicionActual = posicion -1 ;
		var nuevaPosicion = posicion-2;
		var item = seleccionados[div][nuevaPosicion];	

		seleccionados[div][nuevaPosicion]=seleccionados[div][posicionActual];
		seleccionados[div][posicionActual]=item;

		seleccionados[div][nuevaPosicion].posicion=nuevaPosicion + 1;
		seleccionados[div][posicionActual].posicion=posicionActual + 1;

		procesarColumnas(div,false);
	}
}


function downColumna(posicion, div, nombre){

	marcarColumnaSeleccionada(div, nombre);
	if(posicion< seleccionados[div].length){
		$("#data_ejemplo_"+div).html("");
		var posicionActual = posicion -1 ;
		var nuevaPosicion = posicion;
		var item = seleccionados[div][nuevaPosicion];	

		seleccionados[div][nuevaPosicion]=seleccionados[div][posicionActual];
		seleccionados[div][posicionActual]=item;

		seleccionados[div][nuevaPosicion].posicion=nuevaPosicion + 1;
		seleccionados[div][posicionActual].posicion=posicionActual + 1;
		procesarColumnas(div,false);
	}
}



function seleccionarColumna(div, nombre){
	var unique_key = div+"_"+nombre;
	unique_key = unique_key.replace(".","");
	$(".data_ejemplo_txt_"+unique_key).addClass("data_ej_seleccionada");
}

function deseleccionarColumna(div, nombre){
	var unique_key = div+"_"+nombre;
	unique_key = unique_key.replace(".","");
	$(".data_ejemplo_txt_"+unique_key).removeClass("data_ej_seleccionada");
}



var ultima_columna_abierta = "";
var estado_conf = new Array();
estado_conf["header"]="deshabilitado";
estado_conf["detalle"]="deshabilitado";
estado_conf["footer"]="deshabilitado";

function validarEstadoConfDiv(div){
	if(estado_conf[div] == "habilitado"){
		$(".conf_"+div+"_habilitado").show();
		$(".conf_"+div+"_deshabilitado").hide();
	}
	else {
		$(".conf_"+div+"_habilitado").hide();
		$(".conf_"+div+"_deshabilitado").show();
	}

	if($("#div_content_"+div).is(":visible")){
		$("#link_"+div+"_ver_mas").hide();
		$("#link_"+div+"_ver_menos").show();
	}
	else {
		$("#link_"+div+"_ver_mas").show();
		$("#link_"+div+"_ver_menos").hide();
	}
}

function verMas(div){
	verMenos("header");
	verMenos("detalle");
	verMenos("footer");	
	$("#div_conf_"+div).show();
	$("#div_content_"+div).show();
	$("#link_"+div+"_ver_mas").hide();
	$("#link_"+div+"_ver_menos").show();
}

function verMenos(div){
	$("#div_conf_"+div).hide();
	$("#div_content_"+div).hide();
	$("#link_"+div+"_ver_mas").show();
	$("#link_"+div+"_ver_menos").hide();
}

function deshabilitar(div){
	estado_conf[div] = "habilitado";
	validarEstadoConfDiv(div);
	
}

function deshabilitar(div){
	estado_conf[div] = "desahabilitado";
	validarEstadoConfDiv(div);
}

function habilitar(div){
	estado_conf[div] = "habilitado";
	validarEstadoConfDiv(div);
}



function obtenerDataPreparada(data){
	var out = data.data_ej;

	for(var i=out.length; i< data.largo ; i++){
		if(data.alineamiento.toLowerCase() == "derecha"){
			out = data.relleno +out+"";
		}
		else{
			out = out + data.relleno +"";
		}
	
	}

	data.ejemplo_salida = out;
}





function validaTecla(evt){
	 var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
}
function cambiarLargo(div,nombre){
	if(nombre+"".indexOf("data_constante")==0)
		return;

	var columnas = obtenerColumnasPorDiv(div);
	var indice = 0;
	if(esSeleccionado(div, nombre)){
		var obJColumna = getSeleccionado(div,nombre);
		obJColumna.largo = $("#largo_"+div+"_"+nombre).val();	
		var data_preparada = obtenerDataPreparada(obJColumna);
		
		$("#data_ejemplo_col_"+div+"_"+nombre).html("["+obJColumna.ejemplo_salida+"]");
		$(".data_ejemplo_txt_"+div+"_"+nombre).html(obJColumna.ejemplo_salida);
		return;
	}
	for(var i=0; i<columnas.length; i++){
		if(columnas[i].nombre = nombre){
			columnas[i].largo = $("#largo_"+div+"_"+nombre).val();	
			obtenerDataPreparada(columnas[i]);
			indice = i;
			i = columnas.length;
		}
	}

	procesarColumnas(div,true);
}

function cambiarAlineamiento(div, nombre){
	if(nombre+"".indexOf("data_constante")==0)
		return;

	if(esSeleccionado(div, nombre)){
		var obJColumna = getSeleccionado(div,nombre);
		if( $( "#alineamiento_"+div+"_"+nombre +" option:selected" ).text() == "Izquierda"){
			obJColumna.alineamiento = "Izquierda";		
		}
		else{
			obJColumna.alineamiento = "Derecha";		
		}
		var data_preparada = obtenerDataPreparada(obJColumna);
		$("#data_ejemplo_col_"+div+"_"+nombre).html("["+obJColumna.ejemplo_salida+"]");
		$(".data_ejemplo_txt_"+div+"_"+nombre).html(obJColumna.ejemplo_salida);
		return;
	}

	var columnas = obtenerColumnasPorDiv(div);
	for(var i=0; i<columnas.length; i++){
		if(columnas[i].nombre == nombre){
			if( $( "#alineamiento_"+div+"_"+nombre +" option:selected" ).text() == "Izquierda"){
				columnas[i].alineamiento = "Izquierda";		
				obtenerDataPreparada(columnas[i]);
			}
			else{
				columnas[i].alineamiento = "Derecha";		
				obtenerDataPreparada(columnas[i]);
			}
			i = columnas.length;
		}
	}
	procesarColumnas(div,true);
}

function cambiarRelleno(div, nombre){
	if(nombre+"".indexOf("data_constante")==0)
		return;	

	if(esSeleccionado(div, nombre)){
		var obJColumna = getSeleccionado(div,nombre);
		if( $( "#relleno_"+div+"_"+nombre +" option:selected" ).text() == "0"){
				obJColumna.relleno = "0";		
			}
			else{
				obJColumna.relleno = " ";		
			}
		obtenerDataPreparada(obJColumna);
		var data_preparada = obtenerDataPreparada(obJColumna);
		
		$("#data_ejemplo_col_"+div+"_"+nombre).html("["+obJColumna.ejemplo_salida+"]");
		$(".data_ejemplo_txt_"+div+"_"+nombre).html(obJColumna.ejemplo_salida);
		return;
	}


	var columnas = obtenerColumnasPorDiv(div);
	for(var i=0; i<columnas.length; i++){
		if(columnas[i].nombre == nombre){
			if( $( "#relleno_"+div+"_"+nombre +" option:selected" ).text() == "0"){
				columnas[i].relleno = "0";		
				obtenerDataPreparada(columnas[i]);
			}
			else{
				columnas[i].relleno = " ";		
				obtenerDataPreparada(columnas[i]);
			}
			i = columnas.length;
		}
	}
	procesarColumnas(div,true);
}




var columnaSeleccionada = "";
function marcarColumnaSeleccionada(div, nombre){
	columnaSeleccionada = "col_"+div+"_"+nombre; 
	aplicarMarcaSeleccionada();
}

function aplicarMarcaSeleccionada(){
	try{
		
		setTimeout( function(){ 		$("#"+columnaSeleccionada).addClass("columna_seleccionada"); }, 500);
		setTimeout( function(){ 		$("#"+columnaSeleccionada).removeClass("columna_seleccionada"); }, 4000);
	}catch(e){}	
}






function getJSONSeleccionados(div){
	var dataJSON = "";
	var dataColumnasConfiguradas = seleccionados[div];
	for(var i = 0; i < dataColumnasConfiguradas.length; i++){	
		var item = '{'+
				'"nombre":"'+ dataColumnasConfiguradas[i].nombre +'",' +
				'"header":"'+ dataColumnasConfiguradas[i].header +'",' +
				'"alineamiento":"'+ dataColumnasConfiguradas[i].alineamiento +'",' +
				'"relleno":"'+ dataColumnasConfiguradas[i].relleno +'",' +
				'"largo":"'+ dataColumnasConfiguradas[i].largo +'",' +
				'"posicion":"'+ dataColumnasConfiguradas[i].posicion +'",' +
				'"data_ej":"'+ dataColumnasConfiguradas[i].data_ej +'",' +
				'"valor_default":"'+ dataColumnasConfiguradas[i].valor_default +'"' +

			'}';

		dataJSON = dataJSON +","+ item;
	}


	dataJSON = '{"seccion":"'+div+'","habilitado":"'+estado_conf[div]+'","data":['+dataJSON.substr(1)+']  }';
	return dataJSON;

}

function guardarCambios(){

var dataJSON = '{ "formato":"'+id_reporte+'", "secciones":[ '+getJSONSeleccionados("header")+','+getJSONSeleccionados("detalle")+','+getJSONSeleccionados("footer")+' ] }';

console.log(dataJSON);
mostrarCargando("#guardando configuracion");
ejecutarAJAX("/NRP/request?formato_salida=json&id_req=guardarCambiosNomina&id_nomina=formato_estandar", { data_cfg :  dataJSON },
 	function (data) {
    ocultarCargando("#guardando configuracion");
cargarMensajeInformativo('Se han guardado los cambios',3000,'success');
		
  }
);

}


function agregarColumnaConstante(){
	if($("#col_const_texto").val().trim().length == 0 || $("#col_const_header").val().trim().length == 0){
		alert("ingrese los campos requeridos");
		return;
	}

	var item = new Array();
	item.alineamiento="derecha";
	item.posicion=seleccionados[div_llamador_constante].length;
	item.valor_default=$("#col_const_texto").val();
	item.relleno="";
	item.header="Const: "+ $("#col_const_header").val();
	item.largo=$("#col_const_texto").val().length;
	item.nombre=$("#col_const_nombre").val();
	item.data_ej=$("#col_const_texto").val();
	item.configurada=true;
	agregarColumna(div_llamador_constante,item);
	procesarColumnas(div_llamador_constante,false);
	$('#myModal').modal('toggle');
}

function ocultarModal(){
	$('#myModal').modal('toggle');
}

var div_llamador_constante = "";
function cargarFomularioConstante(div){
	div_llamador_constante = div;
	$("#col_const_nombre").val("data_constante_"+parseInt(Math.random()*10000));
	$("#col_const_header").val("");
	$("#col_const_texto").val("");
	$('#myModal').modal();
}
function cargarFomularioVariableSistema(div){
	div_llamador_constante = div;
	$("#col_variable_nombre").val("data_variable_"+parseInt(Math.random()*10000));
	$("#col_variable_texto").val("");
	$("#pantallaVariableSistema").modal();
	
}

function asignarVariable(){
	$("#col_variable_texto").val($( "#col_variable_select" ).val().split(":")[0]);
	$("#col_variable_ejemplo").val($( "#col_variable_select" ).val().split(":")[1]);


}
function ocultarModalVariable(){
	$('#pantallaVariableSistema').modal('toggle');
}

function agregarColumnaDataVariable(){

	if($("#col_variable_header").val().trim().length == 0 || $("#col_variable_texto").val().trim().length == 0 ){
		alert("Ingrese los datos requeridos");
		return ;
	}

	var item = new Array();
	item.alineamiento="derecha";
	item.posicion=seleccionados[div_llamador_constante].length;
	item.valor_default=$("#col_variable_texto").val();
	item.relleno="";
	item.header="Var: "+$("#col_variable_header").val();
	item.largo=$("#col_variable_ejemplo").val().length;
	item.nombre=$("#col_variable_texto").val();
	item.data_ej=$("#col_variable_ejemplo").val();
	item.configurada=true;
	agregarColumna(div_llamador_constante,item);
	procesarColumnas(div_llamador_constante,false);
	$('#pantallaVariableSistema').modal('toggle');

}

function cargarVariablesSistema(){
cargarSelectGenerico("/NRP/request?formato_salida=json&id_req=obtenerVariablesSistema","col_variable_select");
}


cargarVariablesSistema();
function descargarArchivo(){
window.open("http://localhost:8080/NRP/Planillas?req=exportar_txt&tipo=txt&reporte="+id_reporte+"&params=");
}

function volverAConfiguracion(){
	location.href = "/NRP/configuracionEntidad.jsp?ce="+'<%=request.getParameter("ce")%>';
}


$("#menu_configurador").addClass("active");
</script>
