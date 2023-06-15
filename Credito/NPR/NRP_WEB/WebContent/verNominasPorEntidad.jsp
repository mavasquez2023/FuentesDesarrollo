<%@include file="header_session.jsp"%>
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

	<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/comun.js?<%=Math.random()%>"></script>


	<style>
#content_liv {
	width: 1000px;
	height: 600px;
	background-color: #FFF;
}

.menu {
	width: 180px;
	height: 600px;
	background-color: #FFF;
	padding-left: 5px;
	float: left;
	color: #005D8B;
}

#main_content {
	float: left;
	margin-left: 20px;
}

#table_header th {
	padding-left: 10px;
	padding-right: 10px;
}

.tabla_con_borde {
	border: 1px solid #CCC;
}

.tabla_con_borde tr {
	border: 1px solid #CCC;
}

.tabla_con_borde th {
	text-align: center;
	border-left: 1px solid #CCC;
}

.tabla_con_borde td {
	text-align: center;
	border-left: 1px solid #CCC;
}

.estado_configuracion {
	font-size: 14px;
	padding-left: 20px;
}
</style>
</head>
<body class="corp">


	<div id="content" class="container_12">
		<div class="grid_12">
			<img src="images/cabecera_claves.jpg" />
			<%@include file="menu.jsp"%>
			<div id="content_liv">
				<div id="main_content">
					<div id="configurador">
						<h2>Buscador de N&oacute;minas</h2>
						<div class="form-group">
							<label for="periodo">Ingrese Periodo</label> <input type="text"
								class="form-control" id="periodo" placeholder="Ingrese Periodo"
								value="">
						</div>
						<div class="form-group">
							<label for="codigo_entidad">Ingrese C&oacute;digo Entidad</label>
							<input type="text" class="form-control" id="codigo_entidad"
								placeholder="Ingrese C&oacute;digo Entidad" value="">
						</div>

						<button type='button' class='btn btn-default btn-sm'
							style="margin-left: 10px; margin-top: 10px"
							onclick="volverAConfiguracion()">
							<span class='glyphicon glyphicon-arrow-left'></span> Volver
						</button>

						<button type="button" class="btn btn-success btn-sm"
							style="margin-top: 10px" id="btn_buscar_info" onclick="buscar()">
							<span class="glyphicon glyphicon-search"></span> Buscar
							Resultados
						</button>

						<button type="button" class="btn btn-info btn-sm"
							style="margin-top: 10px;" id="btn_generar_nomina">
							<span class="glyphicon glyphicon-play"></span> Generar
							N&oacute;mina
						</button>

						<button type='button' class='btn btn-warning btn-sm'
							style="margin-top: 10px" onclick="eliminarNominas()">
							<span class='glyphicon glyphicon-remove'></span> Eliminar
							Resultados
						</button>
						<h4>N&oacute;minas Encontradas</h4>
						<div id="archivos"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

<script>

var url_configuracion_nomina = "/NRP/request?formato_salida=json&id_req=obtenerNominasPorPeriodoEntidad&periodo=";
var url_eliminar_nomina = "/NRP/request?formato_salida=json&id_req=limpiarCarpetaGeneracion&";
var url_generar_nominas = "/NRP/request?formato_salida=json&id_req=generarNominas&token="+mi_token+"&codigo_entidad=";

function eliminarNominas(){
	
	if($("#codigo_entidad").val().length < 3 ){
		cargarMensajeInformativo("C&oacute;digo entidad no v&aacute;lido",2000, "warning");
		$("#codigo_entidad").val("");
		return;
	}
	if($("#periodo").val().length  != 6 ){
		cargarMensajeInformativo("Per&iacute;odo no v&aacute;lido",2000, "warning");
		$("#periodo").val("");
		return;
	}

	
	var url = url_eliminar_nomina+"periodo=" +$("#periodo").val().trim()+"&codigo_entidad="+$("#codigo_entidad").val().trim();
	ejecutarAJAX(url, null, function( url_eliminar_nomina ) {
		buscar();
	});
	
}

function buscar(){

var url = url_configuracion_nomina +$("#periodo").val().trim()+"&codigo_entidad="+$("#codigo_entidad").val().trim();

if($("#codigo_entidad").val().length < 3 ){
	cargarMensajeInformativo("C&oacute;digo entidad no v&aacute;lido",2000, "warning");
	$("#codigo_entidad").val("");
	return;
}


	if($("#periodo").val().length  != 6 ){
		cargarMensajeInformativo("Per&iacute;odo no v&aacute;lido",2000, "warning");
		$("#periodo").val("");
		return;
	}


mostrarCargando("#buscando nominas");
ejecutarAJAX(url, null, function( data ) {
	$("#archivos").html("");
	$("#archivos").append("<table ><tr><td width='200px'>Nombre</td><td  width='200px'>Tama&ntilde;o</td><td  width='200px'>Fecha Generaci&oacute;n</td></tr>");
	for(var i=0; i< data.item.data.length ; i++ ){
		//$("#archivos").append('<font> -  ['+data.item.data[i].length+' bytes]</font><br/>');


	$("#archivos").append('<tr><td width="200px" style="padding-right:10px"><a href="javascript:generarNomina(\''+$("#periodo").val()+'\',\''+$("#codigo_entidad").val()+'\',\''+data.item.data[i].archivo+'\')">'+data.item.data[i].archivo+'</a></td><td  width="200px"  style="padding-right:10px">'+data.item.data[i].length+' bytes</td><td  width="200px"style="padding-right:10px" >'+data.item.data[i].fecha+'</td></tr>');
	}
	if(data.item.data.length == 0){
		
		$("#archivos").append("<tr><td colspan='3' align='center'>  Sin Resultados  </td></tr>");
	}

	$("#archivos").append("</table>");
	ocultarCargando("#buscando nominas");
});

}

$("#btn_generar_nomina").click(function() {
	mostrarCargando("#generar nomina");

		setTimeout( function (){
			url = url_generar_nominas;
			url = url + $("#codigo_entidad").val() +"&cantidad_hebras=1";
			$.getJSON( url, function( data ) {
				
				ocultarCargando();
				var tiempo = 2000;
				try{
					if(!data.item.status)
						tiempo = 5000;
						cargarMensajeInformativo(data.item.descripcion, tiempo, '');
					} 
				catch( e){
					cargarMensajeInformativo("Ha Ocurrido un error ", tiempo, '');
				}

			});
		}, 1000);
});

function generarNomina(periodo,entidad,archivo){

	var params = "codigo_entidad:"+$("#codigo_entidad").val().trim()+";;";
console.log("url: /NRP/Planillas?req=descargar_nomina&periodo="+periodo+"&entidad="+entidad+"&archivo="+archivo); 
window.open("/NRP/Planillas?req=descargar_nomina&periodo="+periodo+"&entidad="+entidad+"&archivo="+archivo);


}


var ce = '<%=request.getParameter("ce")%>';
var periodo = '<%=cl.liv.comun.utiles.UtilesComunes.reemplazarVariables("sys.YearMonth")%>';

if(ce != null){
	
	$("#periodo").val(periodo);
	if(ce != "null"){
		$("#codigo_entidad").val(ce);
		setTimeout(function(){buscar();},1000);
	}


}



function volverAConfiguracion(){
	location.href = "/NRP/configuracionEntidad.jsp?ce="+'<%=request.getParameter("ce")%>';
}

	$("#menu_ver_nominas").addClass("active");
</script>
