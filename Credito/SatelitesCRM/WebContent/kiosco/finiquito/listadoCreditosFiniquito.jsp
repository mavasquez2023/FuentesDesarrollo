<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="../../cssKiosco/prepago.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado Saldo Capital por Finiquito</title>
</head>
<body>
	<div id="content">
	<div class="bloque-explorer">
		<div id="detalleFiniquitoCont">
			<p class="titulo texto-centrado">Certificado Saldo deuda Capital s&oacute;lo para Finiquito</p>
			<div id="detalleCertificado">
			<div class="field">
				<label><b>Afiliado:</b></label>
				<label>${certificadoFiniquito.rut}</label>
				<label>${certificadoFiniquito.nombreCompleto}</label>
			</div>
			
			<div class="field">
				<label><b>Empresa:</b></label>
				<label>${certificadoFiniquito.rutEmpresa}</label>
				<label>${certificadoFiniquito.nombreEmpresa}</label>
			</div>
			
			<div class="field">
				<label><b>Fecha finiquito:</b></label>
				<label>${fechaFiniquito}</label>
			</div>
			
			<div class="field">
				<label><b>Fecha solicitud:</b></label>
				<label>${fechaSolicitud}</label>
			</div>
			<div class="tablaDetCredito">
						<table id="tabla-creditos">
							<c:forEach items="${listaCreditoFinFoliosVO.salidaList}"  var="listaCreditos">
							<c:if test="${not empty listaCreditos.detalle}">
								<c:if test="${listaCreditos.isCabecera()}">
									<tr>
										<th class="cabecera">${listaCreditos.detalle}
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio1}">
												${listaCreditos.valorFolio1}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio2}">
												${listaCreditos.valorFolio2}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio3}">
												${listaCreditos.valorFolio3}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio4}">
												${listaCreditos.valorFolio4}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio5}">
												${listaCreditos.valorFolio5}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio6}">
												${listaCreditos.valorFolio6}
											</c:if>
										</th>
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
		</div>
			<form id="genCertForm" name="genCertForm" action="generarCertificadoFiniquito.do" method="post" target="iframeLoad">
				<input type="hidden" name="origen" value="kiosco">
			</form>
		</div>
	</div>
	</div>
<jsp:include page="../../comunKiosco/botoneraFull.jsp" flush="true" />
<script>
$(document).ready(function(){
	configureScroll("iframeLoad", "content", "imprimeOff", "cargando");
});
</script>
</body>
</html>