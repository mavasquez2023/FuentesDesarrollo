<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<title>Carga Financiera</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../comun/header.jsp"%>


</head>
<body>
	<div class="logo">
		<img width="210px" alt="logo" src="../img/logo_reducido.jpg">
	</div>
	<div class="contenedor">

		<h1 class="titulo">Carga Financiera</h1>
		<h2 class="subTitulo margenBottom">
			<b>RUT Afiliado: </b>
			<bean:write name="rut" />
		</h2>

		<logic:equal name="error" value="rutNoValido">
			<div class="error">
				<b>Rut no valido.</b>
			</div>
			<script>
				$(document).ready(
						function() {

							$(".loading").parent().remove();
							$("#tablaCreditos").append(
									"<tr><td colspan="17"></td></tr>");
							$("#tablaAsicom").append(
									"<tr><td colspan="11"></td></tr>");
							$("#tablaIntercaja").append(
									"<tr><td colspan="3"></td></tr>");
						});
			</script>
		</logic:equal>

		<div class="fechaEmision margenBottom">
			<b>Fecha Emisión: </b>
			<bean:write name="fechaEmision" format="dd-MM-yyy" />
		</div>

		<div class="containerTablas">

			<table id="tablaCreditos">
				<caption>1. Créditos de consumo.</caption>
				<thead>
					<tr>
						<th>Origen</th>
						<th>ID Contrato</th>
						<th>Línea comercial</th>
						<th>Tasa de interés</th>
						<th>Monto adeudado</th>
						<th>Monto cuota</th>
						<th>Estado del crédito</th>
						<th>Repactación</th>
						<th>Reprogramación</th>
						<th>Titular</th>
						<th>Rol pagador</th>
						<th>Monto solicitado</th>
						<th>Tipo de afiliado</th>
						<th>Número anexo</th>
						<th>Número inscripción</th>
						<th>Rut empresa</th>
						<th>Plazo</th>
						<th>Fecha de otorgamiento</th>
						<th class="aDetalle">Detalle Cuotas</th>
						<th>N° Presupuesto</th>
					</tr>
				</thead>
				<tbody>
					<tr id="trCreditos">
						<td colspan="20"><div id="loadingCreditos" class="loading"></div>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
			<!-- 
			<table id="tablaAsicom">
				<caption>2. Créditos Hipotecarios.</caption>
				<thead>
					<tr>
						<th>Oficina originadora del crédito</th>
						<th>Folio de crédito</th>
						<th>Tasa de interés</th>
						<th>Monto adeudado</th>
						<th>Monto cuota</th>
						<th>Estado del crédito</th>
						<th>Indicador de reprogramación</th>
						<th>Rol asociado y relación</th>
						<th>Rol pagador</th>
						<th>Plazo</th>
						<th class="aDetalle">Detalle Cuotas</th>
					</tr>
				</thead>
				<tbody>
					<tr id="trAsicom">
						<td colspan="11"><div id="loadingAsicom" class="loading"></div>
						</td>
					</tr>
				</tbody>
			</table>
			<br>
			 -->
			<table id="tablaIntercaja">
				<caption>2. Créditos con otras CCAF.</caption>
				<thead>
					<tr>
						<th>Periodo</th>
						<th>Caja origen</th>
						<th>Caja destino</th>
						<th>Numero pagare</th>
						<th>Identificación empresa</th>
						<th>Identificación deudor</th>
						<th>Nombre deudor</th>
						<th>Identificación aval</th>
						<th>Sujeto descuento</th>
						<th>Monto oferta</th>
						<th>Monto cuota deudor</th>
					</tr>
				</thead>
				<tbody>
					<tr id="trIntercaja">
						<td colspan="11"><div id="loadingIntercaja" class="loading"></div>
						</td>
					</tr>
				</tbody>
			</table>

			<br>
			<div class="pullRight">

				<input class="boton" type="button" value="Imprimir"
					onClick="window.print()" />
			</div>
		</div>
	</div>

	<logic:equal name="error" value="rutValido">
		<script src="../js/spin.min.js"></script>
		<script src="../js/core.js"></script>
		<script>
			$(document).ready(function() {

				var target;
				var spinnerBank;
				var spinerAsicom;
				var spinerIntercaja;

				var opts = {
					lines : 11, // The number of lines to draw
					length : 8, // The length of each line
					width : 7, // The line thickness
					radius : 11, // The radius of the inner circle
					corners : 1, // Corner roundness (0..1)
					rotate : 0, // The rotation offset
					direction : 1, // 1: clockwise, -1: counterclockwise
					color : '#0044A3', // #rgb or #rrggbb
					speed : 2, // Rounds per second
					trail : 60, // Afterglow percentage
					shadow : false, // Whether to render a shadow
					hwaccel : false, // Whether to use hardware acceleration
					className : 'spinner', // The CSS class to assign to the spinner
					zIndex : 2e9, // The z-index (defaults to 2000000000)
					top : 'auto', // Top position relative to parent in px
					left : 'auto' // Left position relative to parent in px
				};

				jQuery.ajax({
					type : "POST",
					url : '../compTotal/getContratos.do?',
					data : {
						rut : "<bean:write name="rut"/>"
					},
					beforeSend : function() {
						target = document.getElementById('loadingCreditos');
						spinnerBank = new Spinner(opts).spin(target);

					},
					success : function(data) {

						$("#trCreditos").remove();
						$("#loadingCreditos").remove();
						spinnerBank.stop();
						$("#tablaCreditos tbody").append(data);
					}
				});
/*
				jQuery.ajax({
					type : "POST",
					url : '../compTotal/getContratoAsicom.do?',
					data : {
						rut : "<bean:write name="rut"/>"
					},
					beforeSend : function() {
						target = document.getElementById('loadingAsicom');
						spinerAsicom = new Spinner(opts).spin(target);
					},
					success : function(data) {
						$("#trAsicom").remove();
						$("#loadingAsicom").remove();
						spinerAsicom.stop();
						$("#tablaAsicom tbody").append(data);
					}
				});
*/
				jQuery.ajax({
					type : "POST",
					url : '../compTotal/getContratoIntercaja.do?',
					data : {
						rut : "<bean:write name="rut"/>"
					},
					beforeSend : function() {
						target = document.getElementById('loadingIntercaja');
						spinerIntercaja = new Spinner(opts).spin(target);
					},
					success : function(data) {
						$("#trIntercaja").remove();
						$("#loadingIntercaja").remove();
						spinerIntercaja.stop();
						$("#tablaIntercaja tbody").append(data);
					}
				});

			});
		</script>

	</logic:equal>

</body>
</html:html>