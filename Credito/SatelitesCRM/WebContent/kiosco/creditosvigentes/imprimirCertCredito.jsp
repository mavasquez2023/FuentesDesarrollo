<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cssKiosco/kiosco.css">
</head>
<body>
	<c:set var="listaCreditosVigentes" scope="request" value="${datos}">
	</c:set>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" />
			</div>
			<h1>CERTIFICADO DE CRÉDITOS VIGENTES</h1>
		</div>
		<div class="content">
			<c:if test="${empty datos or datos.get(0)==null}">
				<p>
					Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> no registra créditos vigentes.
				</p>
			</c:if>
			<c:if test="${datos.get(0)!=null}">
				<div id="datosAfiliado">
					<p>
						Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> registra los siguientes créditos vigentes:
					</p>
				</div>
				<table>
					<tr>
						<th>Folio</th>
						<th>Monto Solicitado</th>
						<th>Fecha Otorgamiento</th>
						<th>Cuota a Descuento</th>
						<th>Plazo</th>
						<th>Gastos de cobranza</th>
						<th>Rol Asociado</th>
					</tr>
					<c:forEach items="${listaCreditosVigentes}" var="credito">
						<tr>
							<td class="folio">${credito.folio}</td>
							<td><c:choose>
									<c:when test="${credito.tipoMoneda == 'UF'}">
									UF <fmt:formatNumber maxFractionDigits="5" value="${credito.montoSolicitado}" />
									</c:when>
									<c:otherwise>
							$<fmt:formatNumber maxFractionDigits="0" value="${credito.montoSolicitado}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${credito.fechaOtorgamiento}</td>
							<td><c:choose>
									<c:when test="${credito.tipoMoneda == 'UF'}">
									UF <fmt:formatNumber maxFractionDigits="5" value="${credito.montoCuota}" />
									</c:when>
									<c:otherwise>
									$<fmt:formatNumber maxFractionDigits="0" value="${credito.montoCuota}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td><fmt:formatNumber maxFractionDigits="0" value="${credito.plazo}" /></td>
							<td><c:if test="${not empty credito.gastosCobranza}">
								$<fmt:formatNumber maxFractionDigits="0" value="${credito.gastosCobranza}" />
								</c:if>
							</td>
							<td>${credito.rolAsociado}</td>
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