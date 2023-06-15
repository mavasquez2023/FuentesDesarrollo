<%@ include file="../../comun/headerJsp.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="../../cssKiosco/kiosco.css">
</head>
<body>
	<div class="pagina">
		<div class="header">
			<div class="right">
				<img src="<%=request.getContextPath()%>/img/logo_reducido.jpg" alt="logo">
			</div>
			<h1>Certificado de Prepago de Crédito (Ley 20.130)</h1>
		</div>
		<div class="content">
		<div><p>La Araucana CCAF certifica, que el Sr.(a) ${hash['nombre']} RUT:${hash['rut']} en su calidad de tipo: Trabajador de la empresa ANEXO MINISTERIO DEL INTERIOR, registra el (los) siguiente(s) crédito(s)</p>
		</div>
		<br>
		<table class="finiquito">
						<c:forEach items="${datos}"  var="listaCreditos">
							<c:if test="${not empty listaCreditos.detalle}">
								<c:if test="${listaCreditos.isCabecera()}">
									<tr>
										<th class="cabecera">${listaCreditos.detalle}
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio1}">
												<br>${listaCreditos.valorFolio1}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio2}">
												<br>${listaCreditos.valorFolio2}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio3}">
												<br>${listaCreditos.valorFolio3}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio4}">
												<br>${listaCreditos.valorFolio4}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio5}">
												<br>${listaCreditos.valorFolio5}
											</c:if>
										</th>
										<th>
											<c:if test="${not empty listaCreditos.valorFolio6}">
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
			<p>Los valores señalados a continuación, son válidos hasta la última fecha de pago indicada. Para fechas posteriores, se deberá solicitar la actualización de este certificado.</p>
			<div class="prepago-grid">
					<table class="table-wid table-wid__left">
					<caption>Para contratos como trabajador / Independiente</caption>
						<tr>
							<th>Fecha de Pago</th>
							<th>Total a pagar</th>
						</tr>
						<c:forEach items="${hash['fechasFuturasPagoAfi']}" var="trabajador" >
							<tr>
								<td>${trabajador.fechaDePago}</td>
								<td>$ ${trabajador.totalAPagar}</td>
							</tr>
						</c:forEach>
					</table>
							
					<table class="table-wid table-wid__right">
						<caption>Para contratos como pensionado</caption>
						<tr>
							<th>Fecha de Pago</th>
							<th>Total a pagar</th>
						</tr>
						<c:forEach items="${hash['fechasFuturasPagoPen']}" var="pensionado" >
							<tr>
								<td>${pensionado.fechaDePago}</td>
								<td>$ ${pensionado.totalAPagar}</td>
							</tr>
						</c:forEach>
					</table>
			</div>
		</div>
		<div class="footer">
  			<p>Los valores señalados precedentemente se encuentran exentos de Impuesto de Ley de Timbres y estampillas en caso de prepagar con otra operación de crédito en esta u otra institución financiera.</p>
  			<p>IMPORTANTE: El prepago no considera las cuotas que ya han sido informadas a su empleador o entidad pagadora y se encuentran en proceso de cobro.</p>
  			<p>Las que se considerarán canceladas una vez que La Araucana CCAF, reciba el pago por parte de dichas entidades. El monto obtenido Créditos de Educación Superior, considera el valor UF del día de emisión del certificado, y no UF futura.</p>
  			<p>Se extiende el presente Certificado que tiene validez hasta la fecha señalada en última línea del cuadro de valores, a petición del interesado para los fines que estime convenientes sin ulterior responsabilidad de La Araucana CCAF.</p>
  			<span>Santiago, 21 de abril de 2015.</span>
  			<div class="right"><img src="<%=request.getContextPath()%>/img/firma_afiliacion.gif" alt="firma"></div>
			<br>
			<div class="left">
				<span>Código validación: ${hash['codValidacion']}, ingrese a www.laaraucana.cl</span>
			</div>
		</div>
	</div>
		<jsp:include page="../../comunKiosco/printScript.jsp" flush="true" />
</body>
</html>
