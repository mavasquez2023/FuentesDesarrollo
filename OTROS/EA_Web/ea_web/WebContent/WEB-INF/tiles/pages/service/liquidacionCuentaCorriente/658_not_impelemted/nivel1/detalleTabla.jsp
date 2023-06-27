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
<link href="../../../../../theme/Master.css" rel="stylesheet" type="text/css" />
<title>detalleTabla.jsp</title>
</head>
<body>

	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.708.nivel1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 50%;" colspan="2"><bean:message key="liquidacion.708.nivel1.table.header.afiliado" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.708.nivel1.table.header.folio" /></td>
								<td class="smalltableheader" style="width : 10%;"><bean:message key="liquidacion.708.nivel1.table.header.cuota" /></td>
								<td class="smalltableheader" style="width : 20;"><bean:message key="liquidacion.708.nivel1.table.header.monto" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class="smallprompt" style="width : 15%;">
									<bean:write name="detalle" property="afiliado.rut.formattedRut" scope="page" />-
									<bean:write name="detalle" property="afiliado.rut.dv" scope="page" />
									
								</td>
								<td class="smallprompt" style="width : 35%;">
									<bean:write name="detalle" property="afiliado.apellidoPaterno" scope="page" />&nbsp;
									<bean:write name="detalle" property="afiliado.apellidoMaterno" scope="page" />&nbsp;
									<bean:write name="detalle" property="afiliado.nombre" scope="page" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="codigoOficinaProceso" filter="true" ignore="true" />-
									<bean:write name="detalle" property="folio" filter="true" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:write name="detalle" property="numeroCuota" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="5" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt" colspan="4">
									<bean:message key="liquidacion.708.nivel1.table.label.total" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="total" filter="true" formatKey="global.monto" ignore="true" />									
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
