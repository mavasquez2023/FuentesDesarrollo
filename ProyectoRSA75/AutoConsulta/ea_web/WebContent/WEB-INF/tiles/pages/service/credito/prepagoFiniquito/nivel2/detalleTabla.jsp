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
						<bean:message key="prepagoFiniquitoN2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.saldo" arg0="" arg1=""/>									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="saldo" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.gravamenes" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="gravamenes" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>						
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.subtotal" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="subtotal" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>						
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.menos" />									
								</td>
								<td class="smallcurrency">
								</td>
							</tr>						
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.devolucionInteres" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoInteres" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>						
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.seguroVida" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoSeguroVida" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>						
							<tr>
								<td class="smallprompt">
									<bean:message key="prepagoFiniquitoN2.table.label.totalDescuento" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="totalDescuento" scope="request" filter="true" formatKey="global.monto" ignore="true" />
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
