<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
 
<body>
	<script src="assets/js/polyfill.js"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="assets/js/funciones.js"></script>

	<jsp:include page="./comun/header.jsp" flush="true" />
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<form action="consultaDeta.do" method="post">

		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF;">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
					Boletas emitidas</td>
			</tr>

		</table>
		<br>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 30%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Buscar por folio</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					<input type="text" name="FOLIO" id="folio" value=""
					class="form-control input-sm" />



				</td>
			</tr>
				<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Fecha inicio</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					 <input id="fecha1" name="fechaInicio" type="text" maxlength="10" class="form-control input-sm" autocomplete="off" />



				</td>
			</tr>
				<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Fecha fin</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					 <input id="fecha2" name="fechaFin" type="text" maxlength="10" class="form-control input-sm" autocomplete="off" />



				</td>
			</tr>
			<tr>
				<td class="bienvenida" colspan="2"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: right;">

					<input type="submit" value="buscar" id="buscar"
					class="btn btn-primary" />

					

				</td>
			</tr>
		</table>
		
		<c:if test='${numeroBoleta!= null && numeroBoleta!=""}'>
			<script>
				avisar("<div class='container'><div class='alerta alerta--exito' style='margin-left: 21%;margin-right: 50%'>Se ha emitido correctamente la boleta ${numeroBoleta}</div></div>", 5000);
			</script>
		</c:if>

		<div class="container"></div>

		<br> <br>

		<table align="center" class="tabla-creditos">
			<thead>
				<tr>
					<th class="certificadoLeft">Número de boleta</th>
					<th class="certificadoLeft">Folio</th>
					<th class="certificadoLeft">Rut. Empresa</th>
					<th class="certificadoLeft">Razón social</th>
					<th class="certificadoLeft">Descargar boleta</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boletasEmitidas}" var="ori">
					<tr>
						<td class="certificadoLeft">${ori.NUMBOL}</td>
						<td class="certificadoLeft">${ori.FOLIO}</td>
						<td class="certificadoLeft">${ori.RUTREC}</td>
						<td class="certificadoLeft">${ori.NOMREC}</td>
						<td class="certificadoLeft"><a href="${ori.urlDte}"
							target="_BLANK"><img
								src="<c:url value='/resources/img/descarga.png'/>" width="15px"
								height="15px" title="Descargar" alt="Descargar"></a></td>

					</tr>
				</c:forEach>
			</tbody>

		</table>


	</form>
 
 <script type="text/javascript">
		$(document).ready(
				function() {
				 				 

					var currentTime = new Date();
					//var inicioMes = new Date(currentTime.getFullYear(), currentTime.getMonth(), 1);
					// 10 days before next month
					var finMes = new Date(currentTime.getFullYear(),
							currentTime.getMonth() + 1, 0);
					// one day before next month
					//var endDateFrom = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 3); // 3rd of next month
					//var endDateTo = new Date(currentTime.getFullYear(), currentTime.getMonth() +1, 10); // 10th of next month
					$(function() {
						$("#fecha1").datepicker({
							maxDate : finMes
						});
						
					}); //fin del datepicker
					
					$(function() {
						$("#fecha2").datepicker({
							maxDate : finMes
						});
						
					}); //fin del datepicker
					
					
				}); // fin del document ready
				
	</script>
</body>
</html>