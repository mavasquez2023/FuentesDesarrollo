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

	<br />

	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Rendición nóminas.</td>

		</tr>
	</table>

	<form action="consultaCabe.do" method="post">
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 40%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Buscar por nómina</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">

					<input type="text" name="nomina" id="nomina"
					value="${nomina.nomina}" class="form-control input-sm" />
				</td>
			</tr>
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Buscar por estado</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

					<select name="estado" id="estado" class="form-control">
						<option value="">ESTADO</option>
						<c:forEach items="${estadoscab}" var="item">
							<c:if test="${nomina.estado == item.codigo}">
								<option value="${item.codigo}" selected="selected">${item.descripcion}</option>
							</c:if>
							<c:if test="${nomina.estado != item.codigo}">
								<option value="${item.codigo}">${item.descripcion}</option>
							</c:if>
						</c:forEach>
				</select>


				</td>
			</tr>
			<tr>

				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

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
	</form>
	<br />

	<table align="center" class="tabla-creditos">
		<thead>
			<tr>
				<th class="certificadoLeft">Código Nómina</th>
				<th class="certificadoLeft">Fecha de pago</th>
				<th class="certificadoLeft">Convenio</th>
				<th class="certificadoLeft">Monto Informado</th>
				<th class="certificadoLeft">Monto Pagado</th>
				<th class="certificadoLeft">Estado Nómina</th>
				<th class="certificadoLeft">Informados</th>
				<th class="certificadoLeft">Pagados</th>
				<th class="certificadoLeft">Rechazados</th>
				<th class="certificadoLeft">Fecha Proceso</th>


			</tr>
		</thead>
		<tbody>
			<c:forEach items="${nominasCabecera}" var="nom">
				<tr>
					<td class="certificadoLeft"><a href="<c:url value='/detalle.do?numNomina=${nom.CODNOM}' />">${nom.CODNOM}</a></td>
					<td class="certificadoLeft">${nom.FECPAG}</td>
					<td class="certificadoLeft">${nom.IDCONVBAN}</td>
					<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${nom.MONTO}" /></td>
					<td class="certificadoLeft"><fmt:formatNumber type="number"
							maxFractionDigits="0" value="${nom.TOTPAGO}" /></td>
					<td class="certificadoLeft">${nom.ESTPAGO}</td>
					<td class="certificadoLeft">${nom.CANTIDAD}</td>
					<td class="certificadoLeft">${nom.CANTPAGO}</td>
					<td class="certificadoLeft">${nom.CANTRECHA}</td>
					<td class="certificadoLeft">${nom.FECREC}</td>

				</tr>
			</c:forEach>
		</tbody>

	</table>
<br />

</body>
</html>