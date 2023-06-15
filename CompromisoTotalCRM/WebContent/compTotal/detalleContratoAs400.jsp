<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
	response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
	response.setDateHeader("Expires", 0); //prevents caching at the proxy server
%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<title>Carga Financiera</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../comun/header.jsp"%>
</head>
<body>
	<div class="logo">
		<img width="210px" alt="logo" src="../img/logo_reducido.jpg">
	</div>
	<div class="contenedor">

		<h1 class="titulo">Carga Financiera</h1>

		<h2 class="subTitulo pullLeft">
			<b>RUT Afiliado:</b> ${rut}
		</h2>
		<h3 class="subTitulo pullLeft">
			<b>Folio Credito:</b> ${folioCredito}
		</h3>
		<h3 class="fechaEmision margenBottom">
			<b>Fecha Emisión: </b>
			<bean:write name="fechaEmision" format="dd-MM-yyy" />
		</h3>

		<div class="containerTablas">

			<table>
				<caption>Detalle de Contrato</caption>
				<tbody>
					<tr>
						<th>N° de cuota</th>
						<th>Fecha de vencimiento</th>
						<th>Monto Capital</th>
						<th>Monto Interés</th>
						<th>Monto Gravamen</th>
						<th>Monto Seguro</th>
						<th>Monto Pagado/Abonado</th>
						<th>Monto a Dcto</th>
						<th>Estado de cuota</th>
					</tr>
					<logic:equal name="error" value="ok">
						<logic:iterate id="detalleAs" name="detalle">
							<tr>
								<td><bean:write name="detalleAs" property="numeroCuota" />
								</td>
								<td><bean:write name="detalleAs"
										property="fechaVencimiento" format="dd-MM-yyyy" /></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoCapitalAmortizado" />
									</fmt:formatNumber></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoInteres" />
									</fmt:formatNumber></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoGravamen" />
									</fmt:formatNumber></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoSeguros" />
									</fmt:formatNumber></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoAbonado" />
									</fmt:formatNumber></td>
								<td><fmt:formatNumber maxFractionDigits="0">
										<bean:write name="detalleAs" property="montoDcto" />
									</fmt:formatNumber></td>
								<td><bean:write name="detalleAs" property="estadoCuota" />
								</td>
							</tr>
						</logic:iterate>
					</logic:equal>
					<logic:equal name="error" value="vacio">
					<tr>
							<td colspan="10">
								<div class="notice_message">
										<bean:message key="error.sin.cuotas" />
								</div></td>
						</tr>

					</logic:equal>
					<%-- 	
					<logic:equal name="error" value="error">
						<tr>
							<td colspan="10">
								<div class="error_message">
									<bean:write name="mensaje" />
								</div>
							</td>
						</tr>

					</logic:equal> --%>
				</tbody>
			</table>

			<div class="pullRight">
				<input class="boton" type="button" value="Imprimir"
					onClick="window.print()" /> <input class="boton" type="button"
					value="Volver" onclick="window.history.back()" />
			</div>
		</div>
	</div>

</body>
</html:html>