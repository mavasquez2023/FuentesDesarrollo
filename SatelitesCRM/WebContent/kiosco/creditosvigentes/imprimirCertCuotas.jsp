<%@ include file="../../comun/headerJsp.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cssKiosco/kiosco.css">
</head>
<body>
	<c:set var="listaCuotas" scope="request" value="${datos}">
	</c:set>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" />
			</div>
			<h1>CERTIFICADO DE CUOTAS VIGENTES</h1>
		</div>
		<div class="content">
			<c:if test="${not empty datos}">
				<div id="datosAfiliado">
					Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> registra las siguientes cuotas del crédito ${hash['folio_contrato']}
					<p>Folio contrato: ${hash['folio_contrato']}</p>
					<p>
						Monto Solicitado:
						<fmt:formatNumber maxFractionDigits="0" value="${hash['montoSolicitado']}" />
					</p>
					<p>Fecha otorgamiento: ${hash['fechaOtorgamiento']}</p>
					<p>Cuotas Pendientes: ${hash['cuotasPendientes']}</p>
				</div>
				<table>
					<tr>
						<th>Nc</th>
						<th>Vcto.</th>
						<th>Cuota</th>
						<th>Cuota a Descuento</th>
						<th>Estado cuota</th>
					</tr>
					<c:forEach items="${listaCuotas}" var="lista">
						<tr>
							<td>${lista.nCuota}</td>
							<td>${lista.fecVencimiento}</td>
							<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}" />
							</td>
							<td><c:choose>
									<c:when test="${lista.tipoMoneda == 'UF'}">
									UF <fmt:formatNumber maxFractionDigits="5" value="${lista.monto}" />
									</c:when>
									<c:otherwise>
									$<fmt:formatNumber maxFractionDigits="0" value="${lista.monto}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${lista.estCuota}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="footer">
			<p>Se extiende el presente certificado a petición del interesado para los fines que estime convenientes, sin ulterior responsabilidad de La Araucana C.C.A.F.</p>
			<span>Santiago, ${hash['fechaCreacion']}.</span>
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/${hash['firma']}" alt="firma">
			</div>
			<div class="left">
				<span>Código validación: ${hash['codValidacion']}, ingrese a www.laaraucana.cl </span>
			</div>
		</div>
	</div>
	<jsp:include page="../../comunKiosco/printScript.jsp" flush="true" />
</body>
</html>