<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<title>Certificado Créditos Vigentes</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/cssKiosco/certificado.css">
</head>
<body>
	<logic:notEqual value="null" name="credito">
	<div id="content">
		<p class="titulo">Certificado Créditos Vigentes</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>
		</div>
		<table>
			<tr>
				<th></th>
				<th>Folio</th>
				<th>Monto Solicitado</th>
				<th>Fecha Otorgamiento</th>
				<th>Cuota a Descuento</th>
				<th>Plazo</th>
				<th>Gastos de Cobranza</th>
				<th>Rol Asociado</th>
			</tr>
			<tr>
				<td>
				<a href="<%=request.getContextPath() %>/kiosco/creditosvigentes/listadoCreditos/seleccionCredito/detalleCredito.do?folio_contrato=${credito.folio}&fecha_otorgamiento=${credito.fechaOtorgamiento}&accion=cargarDetalle">Detalle</a>
				</td>
				<td class="folio">${credito.folio}</td>
				<td>
					<c:choose>
						<c:when test="${credito.tipoMoneda == 'UF'}">
					UF <fmt:formatNumber maxFractionDigits="5"
								value="${credito.montoSolicitado}" />
						</c:when>
						<c:otherwise>
					$<fmt:formatNumber maxFractionDigits="0"
								value="${credito.montoSolicitado}" />
						</c:otherwise>
					</c:choose>
				</td>
				<td>${credito.fechaOtorgamiento}</td>
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
				<td><fmt:formatNumber maxFractionDigits="0" value="${credito.plazo}" /></td>
				<td>
					<c:if test="${not empty credito.gastosCobranza}">
						$<fmt:formatNumber maxFractionDigits="0" value="${credito.gastosCobranza}" />
					</c:if>
				</td>
				<td>${credito.rolAsociado}</td>
			</tr>
		</table>
		<br>
		<div class="botones">
			<form id="genCertForm" name="genCertForm" target="iframeLoad" action="seleccionCredito.do" method="POST">
				<input type="hidden" name="origen" value="kiosco">
				<input type="hidden" name="accion" value="imprimirReporte">
			</form>
		</div>
		</div>
		<jsp:include page="../../comunKiosco/botoneraFull.jsp" flush="true" />
		<script>
			$(document).ready(function(){
				configureScroll("iframeLoad", "content", "imprimeOff", "cargando");
			});
		</script>
	</logic:notEqual>
	
	<logic:equal value="1" name="codError">
		<h2>Problemas en el sistema</h2>
		<div class="ui-widget">
			<div style="margin-top: 20px; padding: 0 .7em;" class="ui-state-highlight ui-corner-all"> 
				<p><span style="float: left; margin-right: .3em;" class="ui-icon ui-icon-info"></span>
					<strong>Ha ocurrido un problema en el sistema:</strong> No existe crédito. 
				</p>
			</div>
			<div class="botones">
				<input id="volver" type="button" value="Volver"	onClick="history.back()" / class="boton">
			</div>
		</div>
	</logic:equal>
</body>
</html>