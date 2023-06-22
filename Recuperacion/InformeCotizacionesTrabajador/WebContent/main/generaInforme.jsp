<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME COTIZACIONES PREVISIONALES</title>
</head>
<body>
	<p class="titulo">INFORME COTIZACIONES PREVISIONALES</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">Señor empleador ${salida.param.nombreEmpresa}, 
				Rut Nº ${salida.param.rutEmpresa}-${salida.param.dvEmpresa}, de acuerdo a nuestros registros, 
				informamos las siguientes cotizaciones presentadas por trabajador que se detalla a continuación:
		
				</p>
				<br><br>
				<p>
				
				<table align="center" class="tabla-creditos" style="width: 85%">
					<tr>
						<th>TRABAJADOR</th>
						<th>RUT</th>
						<th>PERIODOS</th>
					</tr>
					
					<c:forEach var="lista" varStatus="vs" items="${salida.cotizaciones}">
						<c:if test="${vs.index>0}">
							<tr>
								
								<c:if test='${vs.index==1}'>
									<td class="certificadoLeft" rowspan=${rowspan} style="padding-left: 5px">${lista.nombreTrabajador}</td>
									<td class="certificadoLeft" rowspan=${rowspan} style="padding-left: 5px">${lista.rutTrabajador}-${lista.dvTrabajador}</td>
								</c:if>
								<td class="certificadoLeft" style="height:30px; padding-left: 5px">${lista.fechaDesde} a ${lista.fechaHasta}</td>
							</tr>
						</c:if>
					</c:forEach>
					
				</table>
				</p>
				<br> <br>
				<p class="certificadoLeft">
					Se extiende el presente certificado a solicitud de ${salida.param.nombreEmpresa} 
					con fecha ${fechaEmision}, de conformidad a lo establecido en la Ley Nº 19.844, 
					publicada en el Diario Oficial del 11 de Enero de 2003, que modifica el Artículo N° 177 del Código del Trabajo.
					<br> <br>
				</p>
				
				<br>
				<hr>
				<br>
			<div class="certificadoRight">
			<form target="blank" action="informe.do" method="POST">
				<input type="hidden" name="accion" value="imprimirReporte">
				<input type="hidden" name="rutTrabajador" value="${rutTrabajador}">
				<input type="hidden" name="rutEmpresa" value="${rutEmpresa }">
				<input class="boton" id="generar" type="submit" value="Generar Certificado"  />
			</form>
			
			<!--  form action="<%=request.getContextPath() %>/certificados/afiliacion/cargaCertificado.do" target="_blank"-->
			</form>
			</div>
			</blockquote>
		</logic:equal>
		<logic:equal name="error" value="-1">
		
			No se han encontrado datos para la consulta realizada.
			<br>
			<br>
			<input type="button" class="button" value="volver" onclick="history.back()"/>
			
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