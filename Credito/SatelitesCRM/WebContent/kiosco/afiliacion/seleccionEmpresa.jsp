<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comunKiosco/header.jsp" flush="true" />
<title>Certificado de Afiliación</title>
<link rel="stylesheet" href="../../cssKiosco/certificado.css">
</head>
<body>
<div id="content">
	<p class="titulo">Certificado de Afiliaci&oacute;n</p>
	<strong>Nombre Afiliado:</strong> ${nombreAfiliado}
	
	<br><br>
	<table>
		<tr>
			<th>Rut Empresa</th>
			<th>Raz&oacute;n social</th>
			<th>Acci&oacute;n</th>
		</tr>
		<c:forEach items="${empresasList}" var="emp" varStatus="loop">
			<tr>
				<td>${emp.rutEmpresa}</td>
				<td>${emp.nombreEmpresa}</td>
				<td>
					<html:link action="/detalleCertificado.do?rut=${emp.rutEmpresa}&numero=${loop.index}">
						Seleccionar
					</html:link>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<jsp:include page="../../comunKiosco/botonera.jsp" flush="true" />
</body>
</html>