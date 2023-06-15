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


#table_header th{
padding-left:10px;
padding-right:10px;
}

.tabla_con_borde{
border: 1px solid #CCC;
}
.tabla_con_borde tr{
border: 1px solid #CCC;
}
.tabla_con_borde th{
text-align:center;
border-left:1px solid #CCC;
}
.tabla_con_borde td{
text-align:center;
border-left:1px solid #CCC;
}

.estado_configuracion{
font-size:14px;
padding-left:20px;
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
				
						<button type='button' class='btn btn-default btn-sm' style="margin-left:10px;"  onclick="volverAConfiguracion()">
							<span class='glyphicon glyphicon-arrow-left'></span> Volver a Configuraciones
						</button> 
					<div id="div_configuracion_header">
						<h3>Header <font id="estado_error_header" style="display:none" class="estado_configuracion">Deshabilitado</font></h3>
						
						<table id="table_header" class="tabla_con_borde">
							<tr>
								<th width="150px">Columna</th>
								<th width="80px">Largo</th>
								<th width="100px">Alineaci&oacute;n</th>
								<th width="80px">Relleno</th>
								<th width="140px">Data Ejemplo</th>
							</tr>

						</table>
					</div>

					<div id="div_configuracion_detalle">
						<h3>Detalle <font id="estado_error_detalle" style="display:none" class="estado_configuracion">Deshabilitado</font></h3>
						<table id="table_detalle" class="tabla_con_borde">
							<tr>
								<th width="150px">Columna</th>
								<th width="80px">Largo</th>
								<th width="100px">Alineaci&oacute;n</th>
								<th width="80px">Relleno</th>
								<th width="140px">Data Ejemplo</th>
							</tr>

						</table>
					</div>
		
					<div id="div_configuracion_footer">
						<h3>Footer <font id="estado_error_footer" style="display:none" class="estado_configuracion">Deshabilitado</font></h3>
						<table id="table_footer" class="tabla_con_borde">
							<tr>
								<th width="150px">Columna</th>
								<th width="80px">Largo</th>
								<th width="100px">Alineaci&oacute;n</th>
								<th width="80px">Relleno</th>
								<th width="140px">Data Ejemplo</th>
							</tr>

						</table>
					</div>
		
				</div>
			</div>
		</div>
	</div>
</div>

</body>
</html>

<script>

var id_reporte = '<%=request.getParameter("r")%>';

$("#id_reporte").html(id_reporte);
var url_configuracion_nomina = "/NRP/request?formato_salida=json&id_req=obtenerConfiguracionNomina&formato_completo=formato_completo_nominas&uri_configuracion="+id_reporte;

var ultima_columna_abierta = "";
var separador = "";

separador = ";";


var dataConfiguracion = new Array();



ejecutarAJAX( url_configuracion_nomina,null, function( data ) {
	dataConfiguracion = data.item.columnas;

	var divs = new Array();
	divs["header"]=false;
	divs["detalle"]=false;
	divs["footer"]=false;
	for(var i=0; i < data.item.columnas.length ; i++){
		var configuracion = data.item.columnas[i];
		cargarDataColumnas(configuracion);
		/*for(var j =0; j<configuracion.data.length; j++){

			if(configuracion.data[j].configurada){
				obtenerDataPreparada(configuracion.data[j])
				var tr = "<tr>"+
						"		<td style='text-align:left; padding-left:10px'>"+configuracion.data[j].header+"</td>"+
						"		<td>"+configuracion.data[j].largo+"</td>"+
						"		<td>"+configuracion.data[j].alineamiento+"</td>"+
						"		<td>'"+configuracion.data[j].relleno+"'</td>"+
						"		<td>"+configuracion.data[j].ejemplo_salida+"</td>"+
						"	</tr>";

				$("#table_"+configuracion.id).append(tr);	
			}
		}*/
		if(configuracion.ejecutar=="true"){
			divs[configuracion.id] = true;
		}
		
		else if(configuracion.ejecutar=="false"){
			$("#table_"+configuracion.id).hide();
			$("#estado_error_"+configuracion.id).show();
		}
	}


	
});


var seleccionados = new Array();
function cargarDataColumnas(configuracion){
	seleccionados["header"] = new Array();
	seleccionados["detalle"] = new Array();
	seleccionados["footer"] = new Array();
	for(var j =0; j<configuracion.data.length; j++){
		if(configuracion.data[j].configurada){
			console.log("add to "+ configuracion.id);
			seleccionados[configuracion.id].push(configuracion.data[j]);
			console.log("-> ["+configuracion.id+"]"+configuracion.data[j].header);
			obtenerDataPreparada(configuracion.data[j])
			/*var tr = "<tr>"+
					"		<td style='text-align:left; padding-left:10px'>"+configuracion.data[j].header+"</td>"+
					"		<td>"+configuracion.data[j].largo+"</td>"+
					"		<td>"+configuracion.data[j].alineamiento+"</td>"+
					"		<td>'"+configuracion.data[j].relleno+"'</td>"+
					"		<td>"+configuracion.data[j].ejemplo_salida+"</td>"+
					"	</tr>";

			$("#table_"+configuracion.id).append(tr);*/	
		}
	}
	if(seleccionados["header"].length > 0){
		console.log("header: "+seleccionados["header"]);
		cargarDataSeccion("header");
	}

	if(seleccionados["detalle"].length > 0){
		console.log("detalle: "+seleccionados["detalle"]);
		cargarDataSeccion("detalle");
	}

	if(seleccionados["footer"].length > 0){
		console.log("footer: " +seleccionados["footer"]);
		cargarDataSeccion("footer");
	}

}

function ordenarColumnasSeccion(seccion){
	var data = seleccionados[seccion];
	var seleccionadosAux = new Array();
	var aOrdenar = new Array();
	if(data != null){
		for(var i=0; i< data.length; i++){
			aOrdenar.push(getLetraPorIndice(data[i].posicion )+"::"+data[i].nombre);
		}


		console.log("array -> " + aOrdenar);
		aOrdenar.sort();
		console.log("array -> " + aOrdenar);
		for(var i=0; i< aOrdenar.length; i++){
			var partes = aOrdenar[i].split("::");
			console.log(partes[0] +" -> "+partes[1])
			seleccionadosAux.push(getDataFromArray(seleccionados[seccion],"nombre",partes[1]));
		}
		console.log("seleccionados -> "+ seleccionadosAux)

		seleccionados[seccion] = seleccionadosAux;
		
	}


	
	
}

function getDataFromArray(array, key, valor){

	for(var i=0; i< array.length; i++){
		if(array[i][key]==valor){
			return array[i];
		}
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



function cargarDataSeccion(seccion){
	ordenarColumnasSeccion(seccion);
	var data = seleccionados[seccion];

	for(var i=0; i<data.length; i++){
		var tr = "<tr>"+
					"		<td style='text-align:left; padding-left:10px'>"+data[i].header+"</td>"+
					"		<td>"+data[i].largo+"</td>"+
					"		<td>"+data[i].alineamiento+"</td>"+
					"		<td>'"+data[i].relleno+"'</td>"+
					"		<td>"+data[i].ejemplo_salida+"</td>"+
					"	</tr>";

			$("#table_"+seccion).append(tr);	
	}
}


function obtenerDataPreparada(data){
	var out = data.data_ej;

	for(var i=out.length; i< data.largo ; i++){
		if(data.alineamiento == "derecha"){
			out = data.relleno +out+"";
		}
		else{
			out = out + data.relleno +"";
		}
	
	}


	data.ejemplo_salida = out;
}


function volverAConfiguracion(){
	location.href = "/NRP/configuracionEntidad.jsp?ce="+'<%=request.getParameter("ce")%>';
}

$("#menu_configurador").addClass("active");

</script>
