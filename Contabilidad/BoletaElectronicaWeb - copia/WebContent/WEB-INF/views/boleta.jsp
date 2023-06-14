<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<body>

	<jsp:include page="./comun/header.jsp" flush="true" />
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<form action="consulta.do" method="post">

		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF;">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
					Boletas Pendientes</td>
			</tr>

		</table>
		<br>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 25%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
					Buscar por folio</td>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

					<input type="text" name="FOLIO" id="folio"
					value="" class="form-control input-sm" />



				</td>
			</tr>
			<tr>
				<td class="bienvenida" colspan="2"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">

					<input type="submit" value="buscar" id="buscar"
					class="btn btn-primary" />



				</td>
			</tr>
		</table>
<br><br>
		<table align="center" class="tabla-creditos">
			<thead>
				<tr>
					<th class="certificadoLeft">Folio</th>
					<th class="certificadoLeft">Rut. Empresa</th>
					<th class="certificadoLeft">Razón social</th>
					<th class="certificadoLeft">Emitir</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boletasSinEmitir}" var="ori">
					<tr>
						<td class="certificadoLeft">${ori.FOLIO}</td>
						<td class="certificadoLeft">${ori.RUTREC}</td>
						<td class="certificadoLeft">${ori.NOMREC}</td>
						<td class="certificadoLeft"><a
							href="<c:url value='/emitir.do?id=${ori.FOLIO}'/>">Emitir</a></td>
					</tr>
				</c:forEach>
			</tbody>

		</table>


	</form>

</body>
</html>