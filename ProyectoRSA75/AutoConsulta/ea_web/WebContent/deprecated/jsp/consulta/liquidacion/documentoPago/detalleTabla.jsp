<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="../../../../theme/Master.css" rel="stylesheet" type="text/css" />
<title>detalleTabla.jsp</title>
</head>
<body>

	<logic:notEmpty name="detalle" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="documentoPago.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr>
								<td class="smallprompt" style="width : 25%;">
									<bean:message key="documentoPago.table.label.banco" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:write name="detalle" property="banco" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:message key="documentoPago.table.label.cuentaCorriente" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:write name="detalle" property="cuentaCorriente" scope="request" filter="true" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.fechaEmision" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="fechaEmision" scope="request" filter="true" formatKey="global.date" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.folio" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="folio" scope="request" filter="true" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.numeroCheque" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="numeroCheque" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.monto" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="monto" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.estadoCheque" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="estadoCheque" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.fechaCobro" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="fechaCobro" scope="request" filter="true" formatKey="global.date" ignore="true" />
								</td>
							</tr>
							
						</tbody>
					</table>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</tbody>
	</table>
	</logic:notEmpty>															

</body>
</html>
