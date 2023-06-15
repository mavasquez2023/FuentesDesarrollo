<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>

<%@ include file="../../comun/header.jsp"%>
<link rel="stylesheet" href="../../css/finiquito.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado de Prepago Saldo Capital (sólo para finiquito)</title>
</head>
<body>
	<div class="bloque-explorer">
		<div id="detalleFiniquitoCont">
			<p class="titulo texto-centrado">Certificado Saldo deuda Capital s&oacute;lo para Finiquito</p>
			
			<div class="field">
				<label>Afiliado:</label>
				<label>${certificadoFiniquito.rut}</label>
				<label>${certificadoFiniquito.nombreCompleto}</label>
			</div>
			
 			<div class="field">
				<label>Empresa:</label>
				<label>${certificadoFiniquito.rutEmpresa}</label>
				<label>${certificadoFiniquito.nombreEmpresa}</label>
			</div>
			
			<div class="field">
				<label>Fecha finiquito:</label>
				<label>${fechaFiniquito}</label>
			</div>
			
 			<div class="field">
				<label>Fecha solicitud:</label>
				<label>${fechaSolicitud}</label>
			</div>
				
		</div>
		
		<div id="contenedorCreditos">
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

			<div class="ContBtnGenerarFiniquito">
				<form action="generarCertificadoFiniquito.do" method="post" target="blank">
					<input type="submit" value="Generar certificado" class="boton"> 
					<input type="button" class="boton" value="<< Volver" onclick="history.back()"/>
				</form>
			</div>
		</div>
		
	</div>
	
	<script type="text/javascript">
		/*$(document).ready(function(){
			parent.document.getElementById("mainIframe").style.height = document.body.scrollHeight;
		});*/
	</script>
</body>
</html>