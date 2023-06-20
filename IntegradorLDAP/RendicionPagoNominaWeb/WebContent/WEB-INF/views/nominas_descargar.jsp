<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./comun/header.jsp" flush="true" />
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
	<script>
	
	function RendicionxFecha(){
		$('#rendicionFecha').submit();
	}
	function RendicionxNomina(){
		$('#rendicionNomina').submit();
	}
	</script>
</head>
<body>
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<br />

	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Rendición nóminas.</td>

		</tr>
	</table>

	<br />
<form action="processFecha.do" method="post" id="rendicionFecha">
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">Ingresa Fecha Desde:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;"><input name="fechaDesde" id="fechaDesde" value="dd/mm/aaaa"/ onclick="this.value=''"> </td>
		</tr>
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">Ingresa Fecha Hasta:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;"><input name="fechaHasta" id="fechaHasta" value="dd/mm/aaaa"/ onclick="this.value=''"> </td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;"><a
				href="#" onclick="RendicionxFecha();"><input
					style="cursor: pointer" type="button" class="btn btn-primary"
					value="&nbsp;Bajar por Fecha&nbsp;" /></a></td>
		</tr>
	</table>
</form>
<form action="processNomina.do" method="post" id="rendicionNomina">
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">Ingresa Número Nómina:</td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;"><input name="numNomina" id="numNomina" /> </td>
			<td class="bienvenida"
				style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;"><a
				href="#" onclick="RendicionxNomina();"><input
					style="cursor: pointer" type="button" class="btn btn-primary"
					value="Bajar por Número" /></a></td>
		</tr>
	</table>
</form>



</body>
</html>