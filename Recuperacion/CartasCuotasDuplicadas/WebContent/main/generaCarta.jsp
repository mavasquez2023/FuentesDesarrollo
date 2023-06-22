<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>Carta Pagos en Exceso</title>
<link rel="stylesheet" href="../css/certificado.css">
</head>
<body>
	<p class="titulo">Carta Pagos en Exceso Cuotas de Crédito</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">
					A JEFE PERSONAL O REPRESENTANTE LEGAL <br>
					<br>
				</p>
				<p class="certificadoLeft">
					EMPRESA	: <b>${nombreEmpresa}</b><br>
					RUT		: <b>${rutEmpresa}</b><br>
					SUCURSAL: <b>${sucursal}</b> <br>
					OFICINA	: <b>${oficina}</b><br>
				<br>
				</p>
				<p class="certificadoLeft">
					MOTIVO
					<br><br>
					Agradecemos a Usted, informar a los trabajadores individualizados en esta nómina, que podrán
					cobrar en cualquiera de nuestras oficinas, presentando su cédula de identidad, valores pagados en
					excesos por conceptos de cuotas de crédito. <br>
					<br>
				</p>
				<p>
					<table align="center" width="70%">
					<tr>
						<th>RUT</th>
						<th>NOMBRE</th>
						<th>MONTO $</th>
					</tr>
					<logic:iterate id="lista" name="trabajadores">
					 <c:if test="${lista.rutAfiliado!=0}">
					<tr>
						<td>${lista.rutAfiliado}-${lista.dvRutAfiliado}</td>
						<td class="certificadoLeft">${lista.nombreAfiliado}</td>
						<td class="certificadoRight"><fmt:formatNumber maxFractionDigits="0" value="${lista.montoCuota}" /></td>
					</tr>
					</c:if>
				</logic:iterate>
				</table>
				</p>
				<p class="certificadoLeft">
					<br><br>
				Si al momento de la recepción de esta carta, los trabajadores ya realizaron el cobro, favor dejar sin efecto esta comunicación.
				</p>
				<p class="certificadoLeft">
					<br><br>
					Fecha Generación Información : ${fechaCreacion}<br>
					Fecha Emisión Carta : ${fechaEmision}
				</p>
				<br>
				<hr>
				<br>
			<div class="certificadoRight">
			<form target="blank" action="detalleCuotas.do" method="POST">
				<input type="hidden" name="accion" value="imprimirReporte">
				<input class="boton" id="generar" type="submit" value="Generar Carta"  />
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
		
			No es posible generar certificado en este momento.
		
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