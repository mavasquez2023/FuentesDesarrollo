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
	<jsp:include flush="true" page="../layout/banner.jsp"></jsp:include>
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
					<label>${resultado.paramEntrada.descripcionAfiliado} </label>

				</div>
				<div class="field li">
					<label class="span4-10">Contrato:</label>
					<label>${resultado.paramEntrada.contrato}</label>

				</div>
				<div class="field li">
					<label class="span4-10">Producto:</label>
					<label>${resultado.paramEntrada.productoReprogramacion} </label>

				</div>
				<div class="field li">
					<label class="span4-10">Meses de Gracia:</label>
					<label>${resultado.paramEntrada.mesesGracia} </label>

				</div>
				<div class="field li">
					<label class="span4-10">Monto Adeudado:</label>
					<fmt:formatNumber currencySymbol="$"
						value="${resultado.montoAdeudado}" type="currency" />

				</div>
				<div class="field li">
					<label class="span4-10">El Plazo es:</label> 
					<label>${resultado.paramEntrada.plazo} meses</label>
				</div>
				<div class="field li">
					<label class="span4-10">Tasa de Interes Mensual:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.tasaMensual}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Tasa de Interes Anual:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.tasaAnual}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Valor Mensual Seguro de Desgravamen:</label> 
						<label> <fmt:formatNumber currencySymbol="$"
							value="${resultado.segDesg}"
							type="currency" /> </label>
				</div>
				<div class="field li">
					<label class="span4-10">Valor Mensual Seguro
						Cesant&iacute;a:</label> <label> <fmt:formatNumber
							currencySymbol="$"
							value="${resultado.segCes}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Monto Condonado Interés Moratorio (Gravámenes):</label> 
					<label>
						<fmt:formatNumber currencySymbol="$" 
							value="${resultado.grvCondonado}" type="currency" /> 
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Monto Condonado Gastos de Cobranza y Honorarios:</label> 
					<label> <fmt:formatNumber currencySymbol="$" 
							value="${resultado.gcCondonado}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Monto Condonado Intereses:</label> 
					<label> <fmt:formatNumber currencySymbol="$" 
							value="${resultado.intCondonado}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Monto Abono Inmediato:</label> 
					<label> <fmt:formatNumber currencySymbol="$" 
							value="${resultado.paramEntrada.montoAbono}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Renta del Cliente:</label> 
					<label> <fmt:formatNumber currencySymbol="$" 
							value="${resultado.paramEntrada.renta}" type="currency" />
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Porcentaje Máximo de Endeudamiento:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.porcentajeMaximoEndeudamiento}" type="number" />%
					</label>
				</div>
				<div class="field li">
					<label class="span4-10">Porcentaje de Endeudamiento Simulado:</label> <label>
						<fmt:formatNumber currencySymbol="%"
							value="${resultado.porcentajeEndeudamientoSimulado}" type="number" />%
					</label>
				</div>
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
				<form target="blank" action="pdfReprogramacion.do" method="POST">
					<input type="hidden" name="accion" value="imprimirReporte">
					<!-- onclick="javascript:window.location.href='/SimulacionCreditoReprogramacion/simuladorReprogramacion.do';" -->
					<input type="button" value="<< Volver a simular" onclick="javascript:history.back();" class="boton" /> 
						<input class="boton" id="generar" type="submit" value="Descargar PDF">
				</form>
				 
        
			</div>
		</div>
		<div class="padding-m" align="left">
			<p>Notas: </p>
			<p> -  La tasa de interés no necesariamente es la tasa pizarra.</p>
			<p> -  La presente sólo tendrá el carácter de simulación no vinculante o meramente referencial, hasta la aprobación de la evaluación de riesgo comercial correspondiente.</p>
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