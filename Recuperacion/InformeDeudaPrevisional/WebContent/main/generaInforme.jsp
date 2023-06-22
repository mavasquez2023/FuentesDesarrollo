<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME DEUDA PREVISIONAL VIGENTE</title>
</head>

<body>
	<p class="titulo">INFORME DEUDA PREVISIONAL VIGENTE</p>
	<div class="certificadoAfiliacion">
		<c:if test="${error==0 || error==-1}">
			<blockquote>
				<p class="certificadoLeft">Caja de Compensación de Asignación Familiar La Araucana, informa que el empleador ${salida.param.razonSocial}, 
				Rut Nº ${salida.param.rutEmpresa}-${salida.param.dvEmpresa}, a la fecha registra  la siguiente deuda morosa:
				</p>
				<p class="certificadoLeft">
					RUT		: <b>${salida.param.rutEmpresa}-${salida.param.dvEmpresa}</b><br>
					OFICINA	: <b>${salida.param.nombreOficina}</b><br>
					SUCURSAL: <b>${salida.param.nombreSucursal}</b> <br>
					
				<br>
				</p>
				<p>
				<c:if test="${error==-1}">
					<h3><b>No Presenta Deuda.</b></h3>
				</c:if>
				
				<table align="center" class="tabla-creditos">
					<tr>
						<th>CONCEPTO</th>
						<th>MONTO $</th>
					</tr>
					<c:set var="total" value="0"></c:set>
					<c:forEach var="lista" varStatus="vs" items="${salida.deuda}">
						<c:if test="${lista.monto!=0}">
							<c:set var="total" value="${lista.monto + total}"></c:set>
							<tr>
								<td class="certificadoLeft">${lista.concepto}</td>
								<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${lista.monto}" />
								</td>
							</tr>
						</c:if>
					</c:forEach>
					<tr>
						<td class="certificadoLeft">&nbsp;</td>
						<td class="certificadoLeft">&nbsp;</td>
					</tr>
					<tr>
						<td class="certificadoLeft">TOTAL</td>
						<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${total}" /></td>
					</tr>

				</table>
				
				</p>
				<br> <br>
				<p class="certificadoLeft">
					<c:if test="${error==0}">
					El monto nominal no incluye gravámenes, estos se actualizan diariamente, 
					de acuerdo a Ley 17322 enviada por Superintendencia de Seguridad Social.<br>
					Por lo anterior, existirá una variación de este informe versus el monto a la fecha efectiva del pago. <br>
					<br>
					</c:if>
					Se extiende el presente certificado a solicitud del empleador ${nombreEmpresa} 
					para los fines que estime pertinentes.
					<br> <br> El presente certificado tendrá una vigencia de 15 días desde la fecha de emisión.
					<br> <br>
				</p>
				<p class="certificadoLeft">
					<br><br>
					Santiago, ${fechaEmision}.
				</p>
				<br>
				<hr>
				<br>
			<div class="certificadoRight">
			<form target="blank" action="informe.do" method="POST">
				<input type="hidden" name="accion" value="imprimirReporte">
				<input type="hidden" name="total" value="${total}" />
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="${salida.param.rutEmpresa }"/>
				<input type="hidden" name="oficina" id="periodo" value="${salida.param.oficina }"/>
				<input type="hidden" name="sucursal" id="periodo" value="${salida.param.sucursal }"/>
				<input class="boton" id="volver" type="button" value="Volver"  onclick="history.back();" />
				<input class="boton" id="generar" type="submit" value="Generar Certificado"  />
			</form>
			
			<!--  form action="<%=request.getContextPath() %>/certificados/afiliacion/cargaCertificado.do" target="_blank"-->
			</form>
			</div>
			</blockquote>
		</c:if>
	
		<c:if test="${error==1}">
		
			No es posible generar informe en este momento.
		
		</c:if>

		<c:if test="${error==2}">
		
			RUT No válido.
			<br>
			<br>
			<input type="button" class="button" value="volver" onclick="history.back()"/>
		</c:if>
	</div>


	<!-- 	<script>
		$(document).ready(function() {
			$("#generar").click(function() {
				$("#generar").attr("disabled", "disabled");

			});
		});
	</script> -->
</body>
</html>