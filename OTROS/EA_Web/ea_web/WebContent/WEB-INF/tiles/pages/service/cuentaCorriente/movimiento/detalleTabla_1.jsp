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

<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
	<tbody>
		<tr style="height : 18px;">
			<td class=smallfontreverse >
				<b>
					<bean:message key="ctaCteN3.table.header" />
				</b>
			</td>
		</tr>
		<tr>
			<td>
				<table style="border : 0px; border-color : #cccccc;" cellspacing=0 cellpadding=2   width=100%>
					<tbody>
						<tr style="background-color : #cccccc;">
							<td class="smalltableheader" style="width : 20%;">
								<bean:message key="ctaCteN3.table.header.fecha" />
							</td>
							<td class="smalltableheader" style="width : 20%;">
								<bean:message key="ctaCteN3.table.header.descripcion" />
							</td>
							<td class="smalltableheader" style="width : 20%;">
								<logic:equal name="tipoConcepto" scope="request" value="I">
									<bean:message key="ctaCteN3.table.header.monto" />
								</logic:equal>
								<logic:equal name="tipoConcepto" scope="request" value="E">
									<bean:message key="ctaCteN3.table.header.haber" />
								</logic:equal>
							</td>
						</tr>
						<logic:iterate id="detalle" name="detalles" scope="request">
						<tr>
							<td class="smallprompt" >
								<logic:lessEqual name="detalle" property="tipo" value="2">
									<bean:define id="url" name="detalle" property="url" />
										<html:link action='<%=(String)url%>' name="detalle" property="params" scope="page"> 
											<bean:write name="detalle" property="fecha.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
										</html:link>
								</logic:lessEqual>
								<logic:greaterThan name="detalle" property="tipo" value="2">
									<bean:write name="detalle" property="fecha.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
								</logic:greaterThan>
							</td>
							<td class=smallprompt style="vertical-align : top;">
								<bean:write name="detalle" property="glosa" filter="true" formatKey="global.monto" ignore="true" />
							</td>
							<td class=smallcurrency style="vertical-align : top;">
								<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
						</tr>
						</logic:iterate>
						
						<tr>
							<td colspan="3" style="height : 1px; background-color : #006666;"></td>
						</tr>


						<!-- Subtotal, Grávamenes -->
						<tr>
							<td class=smallprompt style="font-weight : bold; width : 20%;">																						
							</td>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.subtotal" />
							</td>
							<td class=smallboldcurrency>
								<bean:write name="resumen" property="totalMonto" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
						</tr>
						<tr>
							<td class=smallprompt style="font-weight : bold; width : 20%;">																						
							</td>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.gravamenes" />
							</td>
							<td class=smallboldcurrency>
								<logic:notEqual name="resumen" property="totalGravamenes" scope="request" value="0">
									<bean:write name="resumen" property="totalGravamenes" filter="true" formatKey="global.monto" ignore="true" />
								</logic:notEqual>
							</td>
						</tr>

						<tr>
							<td colspan="3" style="height : 1px; background-color : #006666;"></td>
						</tr>																				

						<tr>
							<td class=smallprompt style="font-weight : bold; width : 20%;">																						
							</td>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.total" />
							</td>
							<td class=smallboldcurrency>
								<bean:write name="resumen" property="total" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
						</tr>																				
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>


</body>
</html>
