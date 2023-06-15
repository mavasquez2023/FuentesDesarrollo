<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true" />
<link rel="stylesheet" href="../../css/prepago.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado Prepago</title>
</head>
<body>
	<div>
		<c:if test="${liquidacion!='1' }">
		<p class="titulo texto-centrado">Deuda Ley 20.720 Renegociación SIR</p>
		<div class="field">
			<label><b>Fecha de Admisibilidad:</b> ${certificadoDeuda.fechaAdmisibilidad}</label>
		</div>
		</c:if>
		<c:if test="${liquidacion=='1' }">
		<p class="titulo texto-centrado">Deuda Ley 20.720 Liquidación</p>
		</c:if>
		<div class="field">
			<label><b>RUT:</b> ${certificadoDeuda.rut}</label>
			<label><b>Nombre:</b> ${certificadoDeuda.nombreCompleto}</label>
		</div>
		<div id="detallePrepagoCont">

				<form name="frm" action="generarCertificadoDeuda.do" method="post" target="_blank">
					<div class="tablaDetCredito">
						<table id="tabla-creditos">
							<c:set var="numcred" value="0"></c:set>
							<c:forEach items="${listaCreditoPrepagoFoliosVO.salidaList}"  var="listaCreditos">
								<c:if test="${not empty listaCreditos.detalle}">
									<c:if test="${listaCreditos.isCabecera()}">
										<tr>
											<th class="cabecera">${listaCreditos.detalle}
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio1}">
													<c:set var="numcred" value="1"></c:set>
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio1}" checked>
													<br>${listaCreditos.valorFolio1}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio2}">
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio2}">
													<br>${listaCreditos.valorFolio2}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio3}">
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio3}">
													<br>${listaCreditos.valorFolio3}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio4}">
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio4}">
													<br>${listaCreditos.valorFolio4}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio5}">
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio5}">
													<br>${listaCreditos.valorFolio5}
												</c:if>
											</th>
											<th>
												<c:if test="${not empty listaCreditos.valorFolio6}">
													<input type="radio" name="prepago" value="${listaCreditos.valorFolio6}">
													<br>${listaCreditos.valorFolio6}
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
									
						<p>&nbsp;</p>
					</div>
					<div class="ContBtnGenerarPrepago">
						<c:if test="${numcred==1}">
							<input type="submit" value="Generar certificado" class="boton" name="sub" id="botonasd">
						</c:if> 
						<input type="button" class="boton" value="<< Volver" onclick="history.back()"/>
					</div>
				</form>
			</div>

	</div>
	
</body>
</html>