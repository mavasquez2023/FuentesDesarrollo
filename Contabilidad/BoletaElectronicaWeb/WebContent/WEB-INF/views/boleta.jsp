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

	<form action="consulta.do" method="post">
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF;">
			<tr>
				<td class="bienvenida"
					style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
					Boletas pendientes
				</td>
			</tr>
		</table>
		<br>
		<table align="center" class="tabla-creditos"
			style="border: 0 px; border-color: #FFFFFF; width: 25%">
			<tr>
				<td class="bienvenida"
					style="border: 0 px;width:150px;font-size: 17px; border-color: #FFFFFF; text-align: left;">
					Ingresar folio</td>
				<td class="bienvenida"
					style="border: 0 px;width:200px; font-size: 17px; border-color: #FFFFFF; text-align: left;">
					<input type="text" name="FOLIO" id="folio"
					value="" class="form-control input-sm" />
				</td>
				<td class="bienvenida" colspan="2"
					style="border: 0 px; font-size: 17px; border-color: #FFFFFF; text-align: right;">
					<input type="submit" value="buscar" id="buscar" 
					class="btn btn-primary" />
				</td>
			</tr>
		</table>
		<br><br>
		<c:if test='${not empty mensajeError}'>
			<script>
				avisar("<div class='container'><div class='alerta alerta-error' style='margin-left: 21%;margin-right: 50%'>${mensajeError}</div></div>", 5000);
			</script>
		</c:if>
		<div class="container">
			<div style="margin-left: 21%;margin-right: 50%">${mensaje}</div>
		</div>
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
						<td class="certificadoLeft">
							<a href="<c:url value='/emitir.do?id=${ori.FOLIO}'/>"><input type="button" value="Emitir" id="buscar" class="btn btn-primary"/></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>