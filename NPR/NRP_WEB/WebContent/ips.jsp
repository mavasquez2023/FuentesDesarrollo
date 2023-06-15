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
<title>Gestor de NRP - La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<style>
#content_liv {
	width: 800px;
	height: 600px;
	background-color: #FFF;
}

.menu {
	width: 300px;
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

.descripcion_etapa {
	margin-left: 10px;
}

.titulo_menu {
	color: #005D8B;
	font-weight: bold;
	height: 30px;
}

.titulo_proceso {
	width: 100%;
	color: #005D8B;
}

.menu_proceso {
	padding: 2px;
	border: 1px solid #DDD;
	margin: 2px;
	font-size: 14px;
	padding-bottom: 10px;
	padding-top: 5px;
	padding-left: 8px;
}

.opciones_proceso {
	padding-top: 10px;
	padding-left: 10px;
}

.estado_menu {
	font-size: 13px;
	height: 20px;
}

.hide {
	display: none;
}

.cursor {
	cursor: hand;
	cursor: pointer;
}

.estado_actual {
	font-weight: bold;
	color: #00F;
	font-size: 12px;
}

.content_titulo_proceso {
	height: 40px;
	color: #005D8B;
	font-weight: bold;
}

.content_titulo_etapa_proceso {
	height: 30px;
}

.titulo_detalle_proceso {
	color: #005D8B;
	font-weight: bold;
}

.data_detalle_proceso {
	color: #005D8B;
}

.clase_bloqueo {
	color: #F00;
}

.loading {
	width: 400px;
	text-align: center;
	color: #005D8B;
}

.loading img {
	padding-top: 100px;
	padding-bottom: 20px;
	width: 50px;
}

.texto_celda{
	text-align:right;
	padding-right:10px;
}
</style>
</head>
<body class="corp">


	<div id="content" class="container_12">
		<div class="grid_12">
			<img src="images/cabecera_claves.jpg" />
			<%@include file="menu.jsp"%>
			<h1>Cuadratura IPS</h1>
			<div class='loading' style="display:none"> <img src="img/loading.gif" ></img> <br/><font class='font_loading'>Cargando por favor espere...</font></div>
			
			<div id="content_liv" style="display:none">

				<br/><br/>
				<div id="main_content">
					<table class="table table-bordered tabla_con_header">
						<thead class="header_tabla">
							<tr>
								<th width="500px" style="font-size:14px;">Puntos Cuadratura</th>
								<th width="100px" style="font-size:14px;">Registros</th>
								<th width="100px" style="font-size:14px;">Monto</th>
							</tr>
						</thead>
						<tbody id="body_formatos">

							<tr>
								<th style="font-size:12px;padding-left: 5px;">N&oacute;mina mes anterior sin agrupar (NRP15IPS)</th>
								<td  class="texto_celda" id="registros_mes_anterior_sin_agrupar"></td>
								<td  class="texto_celda" id="monto_mes_anterior_sin_agrupar"></td>
							</tr>
							
							<tr>
								<th style="font-size:12px;padding-left: 5px;">N&oacute;mina mes anterior Agrupada</th>
								<td  class="texto_celda" id="registros_mes_anterior_agrupada"></td>
								<td  class="texto_celda" id="monto_mes_anterior_agrupada"></td>
							</tr>
							
							<tr>
								<th style="font-size:12px;padding-left: 5px;">Cantidad total registros SAP del mes</th>
								<td  class="texto_celda" id="registros_cantidad_registros_sap_mes"></td>
								<td  class="texto_celda" id="monto_cantidad_registros_sap_mes"></td>
							</tr>
							
							<tr>
								<td style="padding-left: 15px;">1.- Terminados Normal</td>
								<td  class="texto_celda" id="registros_terminados_normal"></td>
								<td  class="texto_celda" id="monto_terminados_normal"></td>
							</tr>
							
							<tr>
								<td style="padding-left: 15px;">2.- Terminados Anticipado (3)</td>
								<td  class="texto_celda" id="registros_terminado_anticipado"></td>
								<td  class="texto_celda" id="monto_terminado_anticipado"></td>
							</tr>
							
							<tr>
								<td style="padding-left: 15px;">3.- Modificados (2)</td>
								<td  class="texto_celda" id="registros_modificados"></td>
								<td  class="texto_celda" id="monto_modificados"></td>
							</tr>
							
							<tr>
								<td style="padding-left: 15px;">4.- Se mantienen igual</td>
								<td  class="texto_celda" id="registros_se_mantiene_igual"></td>
								<td  class="texto_celda" id="monto_se_mantiene_igual"></td>
							</tr>
							
							<tr>
								<td style="padding-left: 15px;">5.- Creados (1)</td>
								<td  class="texto_celda" id="registros_creados"></td>
								<td  class="texto_celda" id="monto_creados"></td>
							</tr>
							
							<tr>
								<th style="font-size:12px;padding-left: 5px;">Total Procesos ( 3.- + 4- + 5.- )</th>
								<td  class="texto_celda" id="registros_total_proceso"></td>
								<td  class="texto_celda" id="monto_total_proceso"></td>
							</tr>
							
							<tr>
								<th style="font-size:12px;padding-left: 5px;">Total Novedades ( (1) + (2) + (3) )</th>
								<td  class="texto_celda" id="registros_total_novedades"></td>
								<td  class="texto_celda" id="monto_total_novedades"></td>
							</tr>
							
							<tr>
								<th style="font-size:12px;padding-left: 5px;">Total Novedades ( (1) + (2) + (3) ) Agrupados Por Rut + Num. Inscripci&oacute;n ( n&oacute;mina final )</th>
								<td  class="texto_celda" id="registros_agrupadas"></td>
								<td  class="texto_celda" id="monto_agrupadas"></td>
							</tr>
							
							
						</tbody>
					</table>
				</div>

			</div>


		</div>
	</div>



	<script>
		var url_obtener_datos_cuadratura = "/NRP/request?formato_salida=json&id_req=getDataCuadraturaIPS&token="
				+ mi_token + "&rand=" + Math.random();

		function mostrarCargando() {
			$(".loading").show();
			$("#content_liv").hide();
		}
		function ocultarCargando() {
			$(".loading").hide();
			$("#content_liv").show();
		}

		$("#menu_opciones").addClass("active");
		mostrarCargando();
		setTimeout(function() {
			url = url_obtener_datos_cuadratura;
			$.getJSON(url, function(data) {
				console.log(data.item)
				$("#registros_mes_anterior_sin_agrupar").html( formatText("number",data.item["r_mes_anterior_sin_agrupar".toUpperCase()]) );
				$("#monto_mes_anterior_sin_agrupar").html( formatText("monto",data.item["m_mes_anterior_sin_agrupar".toUpperCase()]) );
				
				$("#registros_mes_anterior_agrupada").html( formatText("number",data.item["r_mes_anterior_agrupada".toUpperCase()]) );
				$("#monto_mes_anterior_agrupada").html( formatText("monto",data.item["m_mes_anterior_agrupada".toUpperCase()]) );
		 		
				$("#registros_cantidad_registros_sap_mes").html( formatText("number",data.item["r_cantidad_registros_sap_mes".toUpperCase()]) );
				$("#monto_cantidad_registros_sap_mes").html( formatText("monto",data.item["m_cantidad_registros_sap_mes".toUpperCase()]) );

				$("#registros_terminados_normal").html( formatText("number",data.item["r_terminados_normal".toUpperCase()]) );
				$("#monto_terminados_normal").html( formatText("monto",data.item["m_terminados_normal".toUpperCase()]) );

				$("#registros_terminado_anticipado").html( formatText("number",data.item["r_terminados_anticipados".toUpperCase()]) );
				$("#monto_terminado_anticipado").html( formatText("monto",data.item["m_terminados_anticipados".toUpperCase()]) );

				$("#registros_modificados").html( formatText("number",data.item["r_modificados".toUpperCase()]) );
				$("#monto_modificados").html( formatText("monto",data.item["m_modificados".toUpperCase()]) );

				$("#registros_se_mantiene_igual").html( formatText("number",data.item["r_iguales".toUpperCase()]) );
				$("#monto_se_mantiene_igual").html( formatText("monto",data.item["m_iguales".toUpperCase()]) );

				$("#registros_creados").html( formatText("number",data.item["r_creados".toUpperCase()]) );
				$("#monto_creados").html( formatText("monto",data.item["m_creados".toUpperCase()]) );

				$("#registros_total_proceso").html( formatText("number",data.item["r_total_proceso".toUpperCase()]) );
				$("#monto_total_proceso").html( formatText("monto",data.item["m_total_proceso".toUpperCase()]) );

				$("#registros_total_novedades").html( formatText("number",data.item["r_total_novedades".toUpperCase()]) );
				$("#monto_total_novedades").html( formatText("monto",data.item["m_total_novedades".toUpperCase()]) );

				$("#registros_agrupadas").html( formatText("number",data.item["r_agrupadas".toUpperCase()]) );
				$("#monto_agrupadas").html( formatText("monto",data.item["m_agrupadas".toUpperCase()]) );

				ocultarCargando();
			});
		}, 1000);
		
		function formatNumber(num) {
			var salida = "";
			if(parseInt(num)>=0){
				console.log("num["+num+"]")
				//1234567 1.234.567
				while(num.length>0){
					var largo = 3;
					if(num.length < 3){
						largo = num.length;
					}
					var from = num.length - largo;
					var section = num.substr(from);
					console.log("section["+section+"]")
					if(salida.length>0){
						salida = "." + salida ;
					}
					salida = section + salida;
					num = num.substr(0,from);
				}
			}
			else{
				salida = "0";
			}
			return salida;
		}
		function formatText(type,value){
			if(type == "number"){
				value = formatNumber(value+"");

				console.log("number..."+ value)
			}
			else if(type == "monto"){
				value = "$"+formatNumber(value+"");
			}
			return value;
		}
	</script>

</body>
</html>
