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
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" alt="logo">
			</div>
			<h1>CERTIFICADO DE CUOTAS CANCELADAS</h1>
		</div>
		<div class="content">
			<c:if test="${not empty datos}">
				<p id="datosAfiliado">
					Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> registra las siguientes cuotas del cr�dito ${hash['folio_contrato']}
				</p>
				<p>Folio contrato: ${hash['folio_contrato']}</p>
				<p>
					Monto Solicitado:
					<fmt:formatNumber maxFractionDigits="0" value="${hash['montoSolicitado']}" />
				</p>
				<p>Fecha otorgamiento: ${hash['fechaOtorgamiento']}</p>
				<table>
					<tr>
						<th>Nc</th>
						<th>Vcto.</th>
						<th>Cuota</th>
						<th>Fec. Pago</th>
						<th>Ofi.</th>
						<th>Doc. Pago</th>
						<th>Monto</th>
						<th>Est. al Pago</th>
					</tr>
					<c:forEach items="${listaCuotas}" var="lista">
						<tr>
							<td>${lista.nCuota}</td>
							<td>${lista.fecVencimiento}</td>
							<td><fmt:formatNumber maxFractionDigits="0" value="${lista.nCuota}" />
							</td>
							<td>${lista.fecPago}</td>
							<td>${lista.oficina}</td>
							<td>${lista.docPago}</td>
							<td><c:choose>
									<c:when test="${lista.tipoMoneda == 'UF'}">
								UF <fmt:formatNumber maxFractionDigits="5" value="${lista.monto}" />
									</c:when>
									<c:otherwise>
								$<fmt:formatNumber maxFractionDigits="0" value="${lista.monto}" />
									</c:otherwise>
								</c:choose></td>
							<td>${lista.estPago}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
		</div>
		<div class="footer">
			<p>Se extiende el presente certificado a petici�n del interesado para los fines que estime convenientes, sin ulterior responsabilidad de La Araucana C.C.A.F.</p>
			<span>Santiago, ${hash['fechaCreacion']}.</span>
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/${hash['firma']}" alt="firma">
			</div>
			<br>
			<div class="left">
				<span>C�digo validaci�n: ${hash['codValidacion']}, ingrese a www.laaraucana.cl</span>
			</div>
		</div>
	</div>
	<jsp:include page="../../comunKiosco/printScript.jsp" flush="true" />
</body>
</html>