<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<title>Certificado Créditos Cancelados</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
	<logic:notEqual value="null" name="credito">
		<p class="titulo">Certificado Créditos Cancelados</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>
		</div>

		<table>
			<tr>
				<th></th>
				<th>Folio</th>
				<th>Monto Solicitado</th>
				<th>Fecha Otorgamiento</th>
				<th>Fecha Cancelacion</th>
				<th>Monto Cuota</th>
				<th>Plazo</th>
			</tr>
			<tr>
				<td>
				<a href="seleccionCredito/detalleCredito.do?folio_contrato=${credito.folio}&accion=cargarDetalle">Detalle</a>
				</td>
				<td class="folio">${credito.folio}</td>
				<td>
				<c:choose>
					<c:when test="${credito.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="0"
								value="${credito.montoSolicitado}" />
						</c:when>
						<c:otherwise>
					$<fmt:formatNumber maxFractionDigits="0"
								value="${credito.montoSolicitado}" />
					</c:otherwise>
				</c:choose>				
				</td>
				<td>${credito.fechaOtorgamiento}</td>
				<td>${credito.fechaCancelacion}</td>
				<td>
					<c:choose>
							<c:when test="${credito.tipoMoneda == 'UF'}">
						UF <fmt:formatNumber maxFractionDigits="5"
									value="${credito.montoCuota}" />
							</c:when>
							<c:otherwise>
						$<fmt:formatNumber maxFractionDigits="0"
									value="${credito.montoCuota}" />
							</c:otherwise>
					</c:choose>
				</td>
				<td>${credito.plazo}</td>
			</tr>
		</table>
		<div class="botones">
			<form target="blank" action="seleccionCredito.do" method="POST">
				<input type="hidden" name="accion" value="imprimirReporte">
				<input id="volver" type="button" value="Volver"	onClick="history.back()" / class="boton"> 
				<input type="submit" value="Generar Certificado" id="imprimir" class="boton" />
			</form>
		</div>

	</logic:notEqual>
</body>

</html>