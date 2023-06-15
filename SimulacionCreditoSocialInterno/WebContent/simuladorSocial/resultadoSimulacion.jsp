<%@ include file="../layout/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../layout/header.jsp"></jsp:include>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/autoNumeric.js"></script>
<title></title>
</head>
<body>
	<!-- Resultado de la simulacion -->
	<p id="titulo" class="titulo">Simulador de Crédito Social Interno</p>
	<fieldset class="form-fieldset padding-m">
		<div class="container">
			<div class="grey">
				<%-- <div class="field li">
						<label class="span-3-5">Tipo de producto:</label>
						<label>
							<label>${resultado.tipoProductoStr}</label>
						</label>
					</div> --%>
				<div class="field li">
					<label class="span6-10">El Monto Solicitado es:</label>
					<fmt:formatNumber currencySymbol="$"
						value="${resultado.montoSolicitado}" type="currency" />

				</div>
				<div class="field li">
					<label class="span6-10">El Plazo es:</label> <label>${resultado.cuotas}
						meses</label>
				</div>
				<div class="field li">
					<label class="span6-10">Tasa de Interes Mensual:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.creaCotizacion.tasaIntMensual}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span6-10">Tasa de Interes Anual:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.creaCotizacion.tasaIntAnual}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span6-10">Valor Mensual Seguro de
						Desgravamen:</label> <label> <fmt:formatNumber currencySymbol="$"
							value="${resultado.creaCotizacion.segDesgravamen}"
							type="currency" /> </label>
				</div>
				<div class="field li">
					<label class="span6-10">Valor Mensual Seguro
						cesant&iacute;a:</label> <label> <fmt:formatNumber
							currencySymbol="$"
							value="${resultado.creaCotizacion.segCesantia}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span6-10">Impuesto Ley de Estampilla:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.creaCotizacion.lte}" type="currency" /> </label>
				</div>
				<div class="field li">
					<label class="span6-10">Gastos Notariales:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.creaCotizacion.gastosNotariales}"
							type="currency" /> </label>
				</div>
			</div>
			<div class="blue">
				<div class="field li">
					<label class="span6-10">El Valor Cuota Mensual Total:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.querySimulation.montoCuota}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span6-10">CAE % (Carga Anual Equivalente):</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.querySimulation.cae}" type="number" />% </label>
				</div>
				<div class="field li">
					<label class="span6-10">CTC - Costo Total del Crédito:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.querySimulation.costoTotal}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span6-10">Fecha de primer vencimiento:</label> <label>
						<fmt:formatDate pattern="dd/MM/yyyy"
							value="${resultado.fechaPrimerVencimiento}" /> </label>
				</div>
				<div class="field li">
					<label class="span6-10">Fecha de simulación:</label> <label>
						<fmt:formatDate pattern="dd/MM/yyyy ' a las ' HH:mm"
							value="${fechaEmision}" /> </label>
				</div>
			</div>
			<div class="hr"></div>
			<div id="noprint" class="right">
				<form action=""
					method="post">
					<input type="button" value="<< Volver a simular"
						onclick="javascript:window.location.href='/SimulacionCreditoSocialInterno/simuladorSocial.do';"
						class="boton" /> <input class="boton" type="button"
						value="Imprimir" onclick="doPrint()">
				</form>
				
				 
        
			</div>
		</div>
	</fieldset>
	
	<div class="padding-m">
		<p>Esta simulación es interna y no formal. La tasa de interés no es la tasa pizarra.</p>
	</div>


	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSolicitar").click(function() {
				$("#tipoProductoHd").val("${resultado.tipoProducto}");
				$("#montoHd").val("${resultado.montoSolicitado}");
				return true;
			});

		});
		function doPrint() {
			document.all.item("noprint").style.visibility = 'hidden';
			$("#titulo").css("text-align", "center");
			window.print();
			document.all.item("noprint").style.visibility = 'visible';
			$("#titulo").css("text-align", "left");
		}
	</script>

	<!-- / Resultado de la simulacion -->
</body>
</html>