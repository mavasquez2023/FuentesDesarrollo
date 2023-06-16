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
			<h1>Historial de pagos</h1>
			<br> <br>
			<div id="datosAfiliado">
				<span><b>RUT: </b> ${rut} </span>&nbsp;&nbsp;<span><b>Nombre:</b> ${nombre} </span>
			</div>
			<br> <br>
			<form action="/BotonPago/web/pagoEnLinea.do" target="pagoLinea" method="post" name="form1" id="form1">
				<input type="hidden" name="op" value="select">
				<table id="rounded-corner">
					<thead>
						<tr>
							<th></th>
							<th>Código de cupón</th>
							<th>Fecha de generación</th>
							<th>Fecha de pago</th>
							<th>Monto cancelado</th>
							<th>Folio del crédito asociado</th>
							<th>Generar</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="code" value="success">
							<c:forEach var="cupon" items="${listaCupones}">
								<tr>
									<td>
									</td>
									<td>${cupon.codBarra}</td>
									<td>${cupon.fechaGen}</td>
									<td>${cupon.fechaPago}</td>
									<td>
										<fmt:formatNumber maxFractionDigits="0" type="currency" currencySymbol="$" value="${cupon.mtoPagar}" />
									</td>
									<td>${cupon.ofiPro} - ${cupon.creFol}</td>
									<td>
										<a href="<%=request.getContextPath() %>/web/generarComprobantePago.do?folio=${cupon.tesFol}" target="cuponPagado" title="Generar comprobante">
											<img alt="icon historial pago" src="<%=request.getContextPath()%>/img/doc.png" /><br>Generar comprobante
										</a>
									</td>
								</tr>
					 	</c:forEach>

						</logic:equal>
						<logic:equal name="code" value="error">
							<tr>
								<td colspan="11"><bean:message key="message.error" /></td>
							</tr>
						</logic:equal>
						<logic:equal name="code" value="vacio">
							<tr>
								<td colspan="11"><bean:message key="message.tabla.pagos.vacia" />
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
				<br>
			</form>
		</div>
	</div>
		<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
	<script type="text/javascript">
		function abrir(url) {
			window.open(url, 'pagoLinea',
					'top=100,left=100,width=760,height=435');
		}

		function sendme() {
			checkCheckBoxes('form1');
		}

		function checkCheckBoxes(form1) {
			if (document.form1.creditos.checked == false) {
				alert('Debes seleccionar por lo menos un crédito.');
				return false;
			} else {
				window.open("", "pagoLinea",
						"top=100,left=100,width=760,height=435");
				var a = window.setTimeout("document.form1.submit();", 100);
				return true;
			}
		}
	</script>
</body>


</html>
