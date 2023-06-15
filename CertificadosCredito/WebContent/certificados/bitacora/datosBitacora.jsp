<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<title>Detalle de Certificados por RUT</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
	<logic:notEqual value="null" name="bitacora	">
		<p class="titulo">Detalle de Certificados por RUT</p>
		
		<table>
			<tr>
				<th>N°Certificado</th>
				<th>Folio</th>
				<th>Tipo Certificado</th>
				<th>Rut Deudor</th>
				<th>Nombre Deudor</th>
				<th>Fecha Emisión</th>
				<th>Hora Emisión</th>
				<th>Total a Pagar</th>
				<th>Rut Ejecutivo</th>
				<th>Nombre Ejecutivo</th>
			</tr>
			<c:forEach items="${consultaBitacora}"  var="bitacora">
			<tr>
				
					<td><font size="-2">${bitacora.idCertificado}</font></td>
					<td><font size="-2">${bitacora.folio}</font></td>
					<td><font size="-2">${bitacora.tipoCertificado}</font></td>
					<td><font size="-2">${bitacora.rutDeudor}</font></td>
					<td><font size="-2">${bitacora.nombreDeudor}</font></td>
					<td><font size="-2">${bitacora.fechaCreacion}</font></td>
					<td><font size="-2">${bitacora.horaCreacion}</font></td>
					<td><font size="-2">$<fmt:formatNumber maxFractionDigits="0"
							value="${bitacora.totalPagar}" /></font></td>
					<td><font size="-2">${bitacora.rutUsuario}</font></td>
					<td><font size="-2">${bitacora.nombreUsuario}</font></td>
				
			</tr>
			</c:forEach>
		</table>
		
	</logic:notEqual>
</body>

</html>