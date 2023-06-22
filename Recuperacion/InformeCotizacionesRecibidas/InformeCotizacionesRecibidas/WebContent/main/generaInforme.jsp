<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME COTIZACIONES PAGADAS</title>
</head>
<body>
	<p class="titulo">INFORME COTIZACIONES PAGADAS POR EL EMPLEADOR</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">Caja de Compensación de Asignación Familiar La Araucana, informa que el empleador ${salida.param.razonSocial}, 
				Rut Nº ${salida.param.rutEmpresa}-${salida.param.dvEmpresa}, registra las siguientes cotizaciones previsionales, durante los últimos ${meses} meses:
				</p>
				<p class="certificadoLeft">
					RUT		: <b>${salida.param.rutEmpresa}-${salida.param.dvEmpresa}</b><br>
					OFICINA	: <b>${salida.param.nombreOficina}</b><br>
					SUCURSAL: <b>${salida.param.nombreSucursal}</b> <br>
					
				<br>
				</p>
				<p>
				
				
				<table align="center" class="tabla-creditos">
					<tr>
						<th>PERIODO</th>
						<th>COTIZACION<br>PREVISIONAL CCAF ($)</th>
						<th>ASIGNACION<br>FAMILIAR ($)</th>
						<th>SALDO A FAVOR<br>CCAF ($)</th>
						<th>SALDO A FAVOR<br>EMPLEADOR ($)</th>
					</tr>
					
					<c:forEach var="lista" varStatus="vs" items="${salida.pagos}">
						
							<tr>
								<td class="certificadoLeft">${lista.periodo}</td>
								<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${lista.cotizacion}" />
								</td>
								<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${lista.asfam}" />
								</td>
								<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${lista.sfi}" />
								</td>
								<td class="certificadoRight"><fmt:formatNumber
										maxFractionDigits="0" value="${lista.sfe}" />
								</td>
							</tr>

					</c:forEach>
					
				</table>
				</p>
				<br> <br>
				<p class="certificadoLeft">
				
					Se extiende el presente certificado a solicitud de ${salida.param.razonSocial} 
					para los fines que estime pertinentes.
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
				<input class="boton" id="volver" type="button" value="Volver"  onclick="history.back();" />
				<input type="hidden" name="periodo" id="periodo" value="${salida.param.periodo }"/>
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="${salida.param.rutEmpresa }"/>
				<input type="hidden" name="oficina" id="periodo" value="${salida.param.oficina }"/>
				<input type="hidden" name="sucursal" id="periodo" value="${salida.param.sucursal }"/>
				<input class="boton" id="generar" type="submit" value="Generar Certificado"  />
			</form>
			
			<!--  form action="<%=request.getContextPath() %>/certificados/afiliacion/cargaCertificado.do" target="_blank"-->
			</form>
			</div>
			</blockquote>
		</logic:equal>
		<logic:equal name="error" value="-1">
		
			No se han encontrado datos para la consulta realizada.
			
		</logic:equal>
		<logic:equal name="error" value="1">
		
			No es posible generar informe en este momento.
		
		</logic:equal>

		<logic:equal name="error" value="2">
		
			RUT No válido.
			<br>
			<br>
			<input type="button" class="button" value="volver" onclick="history.back()"/>
		</logic:equal>
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