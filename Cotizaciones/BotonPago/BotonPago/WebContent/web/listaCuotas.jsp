<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<%-- <div class="menu">
				<jsp:include flush="true" page="/web/includes/opciones.jsp"></jsp:include> 
		</div> --%>
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Gestión de pago</h1>
			<br> <br>
			<div id="datosAfiliado">
				<span><b>RUT: </b>${rut} </span><span><b> Nombre: </b>${nombre}</span>
			</div>
			<br>
			<div>
				<table class="headerTable">
					<tr>
						<td><b>Producto:&nbsp;</b>${infoCredito.producto}</td>
						<td><b>Folio:&nbsp;</b>${infoCredito.operacion}</td>
						<td><b>Cuotas morosas:&nbsp;</b>${infoCredito.cuotasMorosas}</td>
						<td><b>Gastos cobranza:&nbsp;</b> <fmt:formatNumber maxFractionDigits="0">${infoCredito.gastoCobranzaConCond}</fmt:formatNumber></td>
					</tr>
					<tr>
						<td><b>Gravamenes:&nbsp;</b> <fmt:formatNumber maxFractionDigits="0">${infoCredito.sumaGravamenConCond}</fmt:formatNumber></td>
						<td><b>Monto cuota:&nbsp;</b> <fmt:formatNumber maxFractionDigits="0">${infoCredito.montoCuota}</fmt:formatNumber></td>
						<td><b>Total deuda:&nbsp;</b> <fmt:formatNumber maxFractionDigits="0">${infoCredito.totalDeuda}</fmt:formatNumber></td>
						<td><b>Pago mínimo:&nbsp;</b> <fmt:formatNumber maxFractionDigits="0">${infoCredito.pagoMinimo}</fmt:formatNumber></td>
					</tr>
				</table>
			</div>
			<br>
			<div class="msg">
				<c:if test="${not empty tipo}">
					<div class="${tipo}">
						<strong>Mensaje:&nbsp;</strong>${mensaje}
					</div>
				</c:if>
			</div>
			<br>
			<table id="rounded-corner">
				<thead>
					<tr>
						<th>Cuota</th>
						<th>Vencimiento</th>
						<th>Estado</th>
						<!-- 	<th>Deuda</th> -->
						<th>Monto capital</th>
						<th>Seguros</th>
						<th>Intereses</th>
						<th>Monto Abono</th>
						<!-- <th>Gastos de Cobranza</th> -->
						<th>Monto adeudado</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="cuota" items="${listaCuotas}">
						<tr>
							<td>${cuota.ncuota}</td>
							<td>${cuota.fechaVencimiento}</td>
							<td>${cuota.estadoCuota}</td>
							<td><fmt:formatNumber maxFractionDigits="0">${cuota.montoCapital}</fmt:formatNumber>
							</td>
							<td><fmt:formatNumber maxFractionDigits="0">${cuota.montoSeguro}</fmt:formatNumber>
							</td>
							<td><fmt:formatNumber maxFractionDigits="0">${cuota.montoIntereses}</fmt:formatNumber>
							</td>
							<td><fmt:formatNumber maxFractionDigits="0">${cuota.montoAbono}</fmt:formatNumber>
							</td>
							<td><fmt:formatNumber maxFractionDigits="0">${cuota.montoDeuda}</fmt:formatNumber>
							</td>
						</tr>
					</c:forEach>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="6"></td>
						<td class="sumario, textobold">Deuda: $<fmt:formatNumber maxFractionDigits="0">${infoCredito.totalDeuda}</fmt:formatNumber>
						</td>
						<td class="sumario, textobold">
							<form id="formId" method="post">
								Monto a pagar: <input type="text" id="monto" name="monto" <c:if test="${infoCredito.montoPagadoDia ne null}">disabled="disabled"</c:if> size="9" />
							</form>
							<div id="errorPagoMinino"></div>
						</td>
					</tr>
				</tfoot>
			</table>
			<br>
			<div align="right">
				<input type="button" class="boton" name="volver" title="Regresar a la lista de Creditos" value="Volver"
					onclick="window.location.href='<c:out value="${pageContext.request.contextPath}"/>/web/listaCreditos.do'" />
				<c:forEach var="item" items="${permisos}">
					<c:if test="${item eq 'pagoEnCaja'}">
						<c:choose>
							<c:when test="${infoCredito.montoPagadoDia eq null}">
								<input type="button" class="boton" name="pagarcaja" title="Genera cupon de pago en caja" value="Pagar en caja"
									onclick="abrirPDF('<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnCaja.do?op=monto&folio=${infoCredito.operacion}');" />
							</c:when>
							<c:otherwise>
								<input type="button" class="boton" disabled="disabled" name="pagarcaja" title="Genera cupon de pago en caja" value="Pagar en caja" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
				<c:forEach var="item" items="${permisos}">
					<c:if test="${item eq 'pagoEnLinea'}">
						<c:choose>
							<c:when test="${infoCredito.montoPagadoDia eq null}">
								<input type="button" class="boton" name="pagolinea" title="Despliega pago en linea" value="Pagar en linea"
									onclick="abrirM('<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnLinea.do?op=monto&folio=${infoCredito.operacion}');" />
							</c:when>
							<c:otherwise>
								<input type="button" class="boton" disabled="disabled" name="pagolinea" title="Despliega pago en linea" value="Pagar en linea" />
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</div>
			<br>
		</div>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
	<script type="text/javascript">
		function abrirM(url) {
			if ($("#formId").valid() == false) {
				return;
			}
			window.open(url + '&monto='
					+ document.getElementById("monto").value, 'pagoLinea',
					'top=100,left=100,width=760,height=435');

		}
		function abrirPDF(url) {
			if ($("#formId").valid() == false) {
				return;
			}
			window.open(url + '&monto='
					+ document.getElementById("monto").value, 'pagoCupon', '');
		}

		$(document)
				.ready(
						function() {
							$("#formId")
									.validate(
											{
												errorLabelContainer : "#errorPagoMinino",
												rules : {
													monto : {
														required : true,
														mayorIgualQue : parseInt("${infoCredito.pagoMinimo}"),
														menorIgualQue : parseInt("${infoCredito.totalDeuda}")
													}
												},
												messages : {
													monto : {
														//required: "*",
														mayorIgualQue : "Menor al pago mínimo <fmt:formatNumber maxFractionDigits="0" value='${infoCredito.pagoMinimo}' />",
														menorIgualQue : "Mayor al total deuda <fmt:formatNumber maxFractionDigits="0" value='${infoCredito.totalDeuda}' />"
													}
												}
											}); //fin jquery validate

							jQuery(function($) {
								$("#monto").autoNumeric("init", {
									aSep : '',
									aDec : ',',
									mDec : '0',
									vMax : '999999999'
								});
							});
						}); //fin document ready
	</script>
</body>
</html>
