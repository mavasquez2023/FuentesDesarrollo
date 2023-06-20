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
</head>
<body>
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />
	<form action="init.do" method="get">


		<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Rendición nóminas.</td>

		</tr>
		</table>
		<br />
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 50%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Código Nómina</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					<input type="text" name="nomina" id="nomina"
					value="${nomina.nomina}" class="form-control input-sm" disabled="disabled"/>

				</td>
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Estado Nómina</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					<input type="text" name="nomina" id="nomina"
					value="${nomina.estado}" class="form-control input-sm" disabled="disabled"/>


				</td>
			</tr>
			
			<tr>
				<td class="bienvenida" colspan="2"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

					<input type="submit" value="Volver" id="volver"
					class="btn btn-primary" />



				</td>
			</tr>
		</table>
	</form>
	<br />
	<br />

	
	<div style="height: 300px; overflow-y: scroll;">
		<table align="center" class="tabla-creditos">
			<thead>
				<tr>
					<th class="certificadoLeft">código de nómina</th>
					<th class="certificadoLeft">Rut Afiliado</th>
					<th class="certificadoLeft">Nombre afiliado</th>
					<th class="certificadoLeft">Monto</th>
					<th class="certificadoLeft">Estado detalle</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${detalleCabecera}" var="det">
					<tr>
						<td class="certificadoLeft">${det.CODNOM}</td>
						<td class="certificadoLeft">${det.RUTAFI}</td>
						<td class="certificadoLeft">${det.NOMBRE}</td>
						<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${det.MONTO}" /></td>
						<td class="certificadoLeft">${det.ESTDET}</td>

					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
	<br />
	<br />

</body>
</html>