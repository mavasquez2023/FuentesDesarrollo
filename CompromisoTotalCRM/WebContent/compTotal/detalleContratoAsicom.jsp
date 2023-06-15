<!DOCTYPE html>
<%@page	language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
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
		
		<h2 class="subTitulo pullLeft"><b>RUT Afiliado:</b> <bean:write name="rut"/> </h2>
		<h3 class="subTitulo pullLeft"><b>ID Contrato:</b> <bean:write name="idContrato"/></h3>
		<h3 class="fechaEmision margenBottom"><b>Fecha Emisión: </b><bean:write name="fechaEmision" format="dd-MM-yyy"/></h3>
		
	<div class="containerTablas">
	
		<table>
			<caption>Detalle de Contrato Hipotecario</caption>
			<tbody>
				<tr>
					<th>N° de cuota</th>
					<th>Fecha de vencimiento</th>
					<th>Monto capital amortizado</th>
					<th>Monto seguros</th>
					<th>Monto interés</th>
					<th>Cuota morosa</th>
				</tr>
<logic:equal name="error" value="0">
				<logic:iterate id="detalleAsicom" name="lista">
					<tr>
						<td><bean:write name="detalleAsicom" property="numero_cuota" /></td>
						<td><bean:write name="detalleAsicom" property="fecha_vencimiento" format="dd-MM-yyy"/></td>
						<td><bean:write name="detalleAsicom" property="monto_capital_amortizado" /></td>
						<td><bean:write name="detalleAsicom" property="monto_seguros" /></td>
						<td><bean:write name="detalleAsicom" property="monto_interes" /></td>
						<td><bean:write name="detalleAsicom" property="cuota_morosa" /></td>

					</tr>
				</logic:iterate>
					</logic:equal>
				<logic:equal name="error" value="1">
				<tr>
			<td class="notice_message" colspan="10"><bean:message
					key="error.sin.cuotas" /></td>
		</tr>
				
				</logic:equal>
			</tbody>
		</table>
	
		<div class="pullRight"><input class="boton" type="button" value="Imprimir" onClick="window.print()"/> <input class="boton" type="button" value="Volver" onclick="window.history.back()"/></div>
	</div>
	</div>
	
</body>
</html:html>