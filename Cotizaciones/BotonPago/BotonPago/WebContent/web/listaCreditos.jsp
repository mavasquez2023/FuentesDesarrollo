<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
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
				<span><b>RUT: </b> ${rut} </span>&nbsp;&nbsp;<span><b>Nombre:</b> ${nombre} </span>
			</div>
			<br>
			<c:if test="${!empty aviso}">
				<div class="info">
					<b>Aviso:</b>&nbsp;Estimado cliente; usted ha realizado exitosamente un pago, podrá visualizar el descuento dentro de 24 horas, si desea visualizar o imprimir comprobante de pago,  ingrese a la opción "Historial de Pagos"
					<html:errors />
				</div>
			</c:if>
			<br>
			<div class="textofino">
				<b>Nota:</b>&nbsp;Estimado(a), los pagos efectuados tienen un tiempo de verificación aproximadamente de 24 horas, por lo tanto no es posible efectuar más de un pago para un crédito durante el mismo día.
				<html:errors />
			</div>
			<br>
			<form action="<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnLinea.do" target="pagoLinea" method="post" name="form1" id="form1">
				<input type="hidden" name="op" value="select">
				<table id="rounded-corner">
					<thead>
						<tr>
							<th></th>
							<th>Producto</th>
							<th>Operación</th>
							<th>Fecha Colocación</th>
							<th>Cuotas Pagadas/ Total</th>
							<th>Total Deuda</th>
							<th>Total a Pagar</th>
							<th>Pagos del día</th>
							<th>Pago Parcial</th>
<!-- 							<th>Pago en Caja</th> -->
							<th>Pago Total</th>
						</tr>
					</thead>
					<tbody>
						<logic:equal name="code" value="succes">
							<c:forEach var="credito" items="${listaCreditos}">
								<tr>
									<td><c:choose>
											<c:when test="${credito.montoPagadoDia eq null}">
												<input type="checkbox" name="creditos" value="${credito.operacion}">
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="" disabled="disabled">
											</c:otherwise>
										</c:choose></td>
									<td>${credito.producto}</td>
									<td>${credito.operacion}</td>
									<td>${credito.fechaColocacion}</td>
									<td>${credito.cuotasPagadas}/${credito.cuotasTotales}</td>
									<td><fmt:formatNumber maxFractionDigits="0">${credito.totalDeuda}</fmt:formatNumber>
									</td>
									<td><fmt:formatNumber maxFractionDigits="0">${credito.totalPagar}</fmt:formatNumber>
									</td>
									<td><fmt:formatNumber maxFractionDigits="0">${credito.montoPagadoDia}</fmt:formatNumber>
									</td>
									<td><a href="<c:out value="${pageContext.request.contextPath}"/>/web/listaCuotas.do?folio=${credito.operacion}&tipoCredito=${credito.producto}" title="Detalle del Crédito, Permite realizar pagos parciales"><img
											alt="icon detalle" src="<c:out value="${pageContext.request.contextPath}"/>/img/detalle.png" />
											</a><br>detalle
									</td>
<!-- 									<td> 
										<c:forEach var="item" items="${permisos}">
											<c:if test="${item eq 'pagoEnCaja'}">
												<c:choose>
													<c:when test="${credito.montoPagadoDia eq null}">
														<a href="<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnCaja.do?op=directo&folio=${credito.operacion}" target="pagoCupon" title="Pago en Caja, Permite impresión de comprobante para pago por caja"><img
															alt="icon pago caja" src="<c:out value="${pageContext.request.contextPath}"/>/img/doc.png" /> </a>
														<br>cupón
												</c:when>
													<c:otherwise>
														<a title="Pago en Caja, Permite impresión de comprobante para pago por caja">
<%-- 														<img alt="icon pago linea" src="<c:out value="${pageContext.request.contextPath}"/>/img/docds.png"</img>  --%>
														<img alt="icon pago linea" src="<%=request.getContextPath()%>/img/docds.png" />
														</a>

														<br>cupón
												    </c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
									</td>-->
									<td><c:forEach var="item" items="${permisos}">
											<c:if test="${item eq 'pagoEnLinea'}">
												<c:choose>
													<c:when test="${credito.montoPagadoDia eq null}">
														<a onclick="abrir('<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnLinea.do?op=directo&folio=${credito.operacion}');" title="Pago en Línea, Permite pagar en línea el total de la deuda"> 
<%-- 														<img alt="icon pago linea" src="<c:out value="${pageContext.request.contextPath}"/>/img/pagar.png"</img>  --%>
														<img alt="icon pago linea" src="<%=request.getContextPath()%>/img/pagar.png" />
														</a>
														<br>pagar
												    </c:when>
													<c:otherwise>
														<a title="Pago en Línea, Permite pagar en línea el total de la deuda">
<%-- 														<img alt="icon pago linea" src="<c:out value="${pageContext.request.contextPath}"/>/img/pagards.png"</img>  --%>
														<img alt="icon pago linea" src="<%=request.getContextPath()%>/img/pagards.png" />
														</a>
														<br>pagar
												    </c:otherwise>
												</c:choose>
											</c:if>
										</c:forEach>
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
								<td colspan="11"><bean:message key="message.tabla.vacia" />
								</td>
							</tr>
						</logic:equal>
					</tbody>
				</table>
				<br>
				<div align="right">
					<c:forEach var="item" items="${permisos}">
						<c:if test="${item eq 'pagoEnLinea'}">
							<input type="button" onclick='sendme();' value="Pagar Seleccionados" class="boton" />
							<%-- <input type="button" onclick="abrir('<c:out value="${pageContext.request.contextPath}"/>/web/pagoEnLinea.do?op=todos');" class="boton" value="Pagar Todos"> --%>
						</c:if>
					</c:forEach>
				</div>
			</form>
		</div>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
	<c:if test="${!empty listaCreditos}">
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
	</c:if>
</body>
</html>
