<!DOCTYPE html>
<%@page	language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<title>Presupuesto COSAL</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../comun/header.jsp"%>
</head>
<body>
	<div class="logo">
		<img width="210px" alt="logo" src="../img/logo_reducido.jpg">
	</div>
	<div class="contenedor">

		<h1 class="titulo">Presupuesto COSAL</h1>

		<h2 class="subTitulo pullLeft">
			<b>RUT Afiliado:</b>
			<bean:write name="rut" />
		</h2>
		<h3 class="subTitulo pullLeft">
			<b>ID Contrato:</b>
			<bean:write name="idContrato" />
		</h3>
		<h3 class="fechaEmision margenBottom">
			<b>Fecha Emisión: </b>
			<bean:write name="fechaEmision" format="dd-MM-yyy" />
		</h3>

		<div class="containerTablas">

			<table>
				<caption>Números de Presupuesto COSAL</caption>
				<tbody>
					<tr>
						<th>N° de Presupuesto</th>
						<th>Monto del Presupuesto</th>
					</tr>
					<logic:equal name="error" value="ok">
						<logic:iterate id="detalleBanking" name="detalle">
							<tr>
								<td><bean:write name="detalleBanking" property="numero" />
								</td>
								<td>
<%-- 								<fmt:formatNumber maxFractionDigits="0"> --%>
										<bean:write name="detalleBanking" property="monto" />
<%-- 								</fmt:formatNumber> --%>
								</td>
								
							</tr>
						</logic:iterate>
					</logic:equal>
					<logic:equal name="error" value="vacio">
						<tr>
							<td colspan="2">
								<div class="notice_message">
									<bean:message key="error.sin.cuotas" />
								</div>
							</td>
						</tr>

					</logic:equal>
					<logic:equal name="error" value="error">
						<tr>
							<td colspan="2">
								<div class="error_message">
									<bean:write name="mensaje" />
								</div>
							</td>
						</tr>

					</logic:equal>
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