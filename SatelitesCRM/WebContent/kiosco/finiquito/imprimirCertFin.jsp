<%@ include file="../../comun/headerJsp.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cssKiosco/kiosco.css">
</head>
<body>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" />
			</div>
			<h1>Certificado Saldo deuda Capital sólo para Finiquito</h1>
		</div>
		<div class="content">
			<div>
				<p>La Araucana CCAF certifica, que el Sr.(a) ${hash['nombre']} RUT: ${hash['rut']} en su calidad de tipo: Trabajador de la empresa ${hash['nombreEmpresa']}, registra el (los) siguiente(s)
					crédito(s)</p>
			</div>
			<br>
			<table class="finiquito">
				<c:forEach items="${hash['listaCreditoPrepagoFolios']}" var="listaCreditos">
					<c:if test="${not empty listaCreditos.detalle}">
						<c:if test="${listaCreditos.isCabecera()}">
							<tr>
								<th class="cabecera">${listaCreditos.detalle}</th>
								<th><c:if test="${not empty listaCreditos.valorFolio1}">
												${listaCreditos.valorFolio1}
										</c:if></th>
								<th><c:if test="${not empty listaCreditos.valorFolio2}">
												${listaCreditos.valorFolio2}
											</c:if></th>
								<th><c:if test="${not empty listaCreditos.valorFolio3}">
												${listaCreditos.valorFolio3}
											</c:if></th>
								<th><c:if test="${not empty listaCreditos.valorFolio4}">
												${listaCreditos.valorFolio4}
											</c:if></th>
								<th><c:if test="${not empty listaCreditos.valorFolio5}">
												${listaCreditos.valorFolio5}
											</c:if></th>
								<th><c:if test="${not empty listaCreditos.valorFolio6}">
												${listaCreditos.valorFolio6}
											</c:if></th>
							</tr>
						</c:if>
						<c:if test="${not listaCreditos.isCabecera()}">
							<tr>
								<td class="cabecera">${listaCreditos.detalle}</td>
								<td>${listaCreditos.valorFolio1}</td>
								<td>${listaCreditos.valorFolio2}</td>
								<td>${listaCreditos.valorFolio3}</td>
								<td>${listaCreditos.valorFolio4}</td>
								<td>${listaCreditos.valorFolio5}</td>
								<td>${listaCreditos.valorFolio6}</td>
							</tr>
						</c:if>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<div class="footer">
			<p>IMPORTANTE: Este certificado es válido sólo si el finiquito tiene lugar en la fecha indicada por la empresa. Nota: La cancelación de los descuentos por finiquitos, no deben ser incluidos en
				el pago normal de la nómina de los créditos, sino de forma directa, adjuntando a éste documento la copia del finiquito. Se extiende el presente certificado, a petición del interesado para los
				fines que estime convenientes sin ulterior responsabilidad de La Araucana CCAF.</p>
			<span>Santiago, ${hash['fechaCreacion']}.</span>
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/firma_afiliacion.gif" alt="firma">
			</div>
			<div class="left">
				<span>Código validación: ${hash['codValidacion']}, ingrese a www.laaraucana.cl </span>
			</div>
		</div>
	</div>
	<jsp:include page="../../comunKiosco/printScript.jsp" flush="true" />
</body>
</html>