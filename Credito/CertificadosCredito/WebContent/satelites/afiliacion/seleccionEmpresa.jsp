<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true" />
<title>Certificado Afiliacion</title>
<link rel="stylesheet" href="../../css/certificado.css">
</head>
<body>
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
						<html:link action="/detalleCertificado.do?rutEmpresa=${emp.rutEmpresa}&numero=${loop.index}&uc=${uc}&rol=${rol }">
							Seleccionar
						</html:link>
					</td>
				</tr>
			</c:forEach>
		</table>
</body>
</html>