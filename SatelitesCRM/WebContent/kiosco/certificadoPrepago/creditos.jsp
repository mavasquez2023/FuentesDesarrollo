<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="../../cssKiosco/prepago.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado Prepago</title>
<!-- <style>#content{width:990px !important;}</style> -->
</head>
<body>
	<div id="content">
		<p class="titulo texto-centrado">Certificado de Prepago de Crédito (Ley 20.130)</p>
		
		<div class="field">
			<label><b>RUT:</b> ${certificadoPrepago.rut}</label>
			<label><b>Nombre:</b> ${certificadoPrepago.nombreCompleto}</label>
		</div>
		<div id="detalleCertificado">

				<form name="frm" action="generarCertificadoPrepago.do" method="post" target="iframeLoad" name="genCertForm" id="genCertForm">
					<div class="tablaDetCredito">
						<table id="tabla-creditos">
							<c:forEach items="${listaCreditoPrepagoFoliosVO.salidaList}"  var="listaCreditos">
								<c:if test="${not empty listaCreditos.detalle}">
									<c:if test="${listaCreditos.isCabecera()}">
										<tr>
											<th class="cabecera">${listaCreditos.detalle}
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio1}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio1}">
													${listaCreditos.valorFolio1}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio2}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio2}">
													${listaCreditos.valorFolio2}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio3}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio3}">
													${listaCreditos.valorFolio3}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio4}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio4}">
													${listaCreditos.valorFolio4}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio5}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio5}">
													${listaCreditos.valorFolio5}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio6}">
													<input type="checkbox" name="prepago" value="${listaCreditos.valorFolio6}">
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
						<b>NOTA: El usuario debe seleccionar al menos un cr&eacute;dito para poder generar el certificado.</b>
					</div>
					<input type="hidden" name="origen" value="kiosco" />
				</form>
			</div>
	</div>
	<jsp:include page="../../comunKiosco/botoneraSemi.jsp" flush="true" />
	<script type="text/javascript">
		$(document).ready(function(){
			
			$("#btnImprimir").attr("disabled","disabled");
			configureScroll("iframeLoad", "content", "imprimeOff", "cargando");

			$('input[type="checkbox"]').bind('click',function() {
            	var existe = false;
            	$('input[type="checkbox"]').each(function() {
                	if($(this).is(':checked')) {
                    	existe = true;
                    }
                });
                if(existe == true){
                	$("#btnImprimir").removeAttr("disabled");
                }else{
                	$("#btnImprimir").attr("disabled","disabled");
                }
            });
			
		});
 	</script>
</body>
</html>