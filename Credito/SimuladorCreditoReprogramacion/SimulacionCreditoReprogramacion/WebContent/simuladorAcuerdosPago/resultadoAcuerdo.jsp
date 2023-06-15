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
	<jsp:include flush="true" page="../layout/banner_acuerdos.jsp"></jsp:include>
	<!-- Resultado de la simulacion -->
	<div align="center">
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
					<label class="span4-10">RUT:</label>
					<label>${resultado.paramEntrada.rutAfiliado}</label>

				</div>
				
				<div class="field li">
					<label class="span4-10">Nombre:</label>
					<label>${resultado.paramEntrada.nombreAfiliado}</label>

				</div>
				
				<div class="field li">
					<label class="span4-10">Tipo de Cliente:</label>
					<label>${resultado.paramEntrada.tipoAfiliado} </label>

				</div>
				<div class="field li">
					<label class="span4-10">Contrato:</label>
					<label>${resultado.paramEntrada.contrato}</label>

				</div>
				<div class="field li">
					<label class="span4-10">Capital Adeudado:</label>
					<label>
						<fmt:formatNumber currencySymbol="$"
						value="${resultado.capitalAdeudado}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Cuotas por Pagar:</label> 
					<label>${resultado.cuotasxPagar} cuotas</label>
				</div>
				<div class="field li">
					<label class="span4-10">Tasa de Interes Mensual:</label> 
					<label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.tasaInteresMensual}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Monto Abono Inmediato:</label>
					<label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.paramEntrada.montoAbono}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">% Condonación Capital:</label> 
					<label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.paramEntrada.porcentajeCapital}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Capital Comprometido a Pagar:</label> 
					<label> <fmt:formatNumber currencySymbol="$"
							value="${resultado.capitalComprometido}" type="currency" /> 
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Capital a condonar al finalizar los pagos comprometidos</label> 
					<label> <fmt:formatNumber
							currencySymbol="$"
							value="${resultado.capitalCondonado}" type="currency" />
					</label>
				</div>
				
				<div class="blue">
				<div class="field li">
					<label class="span4-10">El Valor Cuota Mensual Total:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.montoCuota}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">CAE % (Carga Anual Equivalente):</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.cae}" type="number" />% </label>
				</div>
				<div class="field li">
					<label class="span4-10">CTC - Costo Total del Crédito:</label> <label>
						<fmt:formatNumber currencySymbol="$"
							value="${resultado.costoTotal}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Fecha de primer vencimiento:</label> <label>
						<fmt:formatDate pattern="dd/MM/yyyy"
							value="${resultado.fechaPrimerVencimiento}" /> </label>
				</div>
				<div class="field li">
					<label class="span4-10">Fecha de simulación:</label> <label>
						<fmt:formatDate pattern="dd/MM/yyyy ' a las ' HH:mm"
							value="${fechaEmision}" /> </label>
				</div>
			</div>
			<div class="hr"></div>
			<div id="noprint" class="right">
				<form target="blank" action="pdfAcuerdo.do" method="POST">
					<input type="hidden" name="accion" value="imprimirReporte">
					<!-- onclick="javascript:window.location.href='/SimulacionCreditoReprogramacion/simuladorReprogramacion.do';" -->
					<input type="button" value="<< Volver a simular" onclick="javascript:history.back();" class="boton" /> 
						<input class="boton" id="generar" type="submit" value="Descargar PDF">
				</form>
				
				 
        
			</div>
		</div>
		<div class="padding-m" align="left">
			<p>Notas: </p>
			<p> -  La presente sólo tendrá el carácter de simulación no vinculante o meramente referencial, hasta la aprobación de la evaluación de riesgo comercial correspondiente.</p>
		</div>
		</div>
	</fieldset>
	</div>
	


	<script type="text/javascript">
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