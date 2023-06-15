<%@ include file="../../comun/headerJsp.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/cssKiosco/kiosco.css">
</head>
<body>
	<c:set var="listaCreditosCancelados" scope="request" value="${datos}"></c:set>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" alt="logo">
			</div>
			<h1>CERTIFICADO DE CR�DITOS CANCELADOS</h1>
		</div>
		<div class="content">
			<br>
			<c:if test="${datos.get(0)==null}">
				<div id="datosAfiliado">
					<p>
						Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> no registra cr�ditos cancelados.
					</p>
				</div>
			</c:if>
			<c:if test="${datos.get(0)!=null}">
				<div id="datosAfiliado">
					<p>
						Se certifica que <b>${hash['nombreCompleto']}</b>, Rut <b>${hash['rut']}</b> registra los siguientes cr�ditos cancelados:
					</p>
				</div>
				<table>
					<tr>
						<th>Folio</th>
						<th>Monto Solicitado</th>
						<th>Fecha Otorgamiento</th>
						<th>Fecha Cancelaci�n</th>
						<th>Monto Cuota</th>
						<th>Plazo</th>
					</tr>
					<c:forEach items="${listaCreditosCancelados}" var="lista">
						<tr>
							<td class="folio">${lista.folio}</td>
							<td><c:choose>
									<c:when test="${lista.tipoMoneda == 'UF'}">
									UF <fmt:formatNumber maxFractionDigits="0" value="${lista.montoSolicitado}" />
									</c:when>
									<c:otherwise>
									$ <fmt:formatNumber maxFractionDigits="0" value="${lista.montoSolicitado}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${lista.fechaOtorgamiento}</td>
							<td>${lista.fechaCancelacion}</td>
							<td><c:choose>
									<c:when test="${lista.tipoMoneda == 'UF'}">
									UF <fmt:formatNumber maxFractionDigits="0" value="${lista.montoCuota}" />
									</c:when>
									<c:otherwise>
									$ <fmt:formatNumber maxFractionDigits="0" value="${lista.montoCuota}" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>${lista.plazo}</td>
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