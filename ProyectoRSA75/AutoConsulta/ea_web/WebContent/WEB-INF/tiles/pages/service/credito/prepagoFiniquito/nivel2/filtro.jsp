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
<title>filtro.jsp</title>
</head>
<body>

<html:form action="/prepagoFiniquitoN2" method="POST">
	<table border="1" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
		<tbody>
			<tr>
				<td>
					<table style="background-color: #f8f8f8;">
						<tbody>								
							<tr><td colspan="5" style="height : 4px;"></td></tr>
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.afiliado" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="afiliado" property="rut" scope="request" />-
									<bean:write name="afiliado" property="dv" scope="request" />:&nbsp;
									<bean:write name="afiliado" property="apellidoPaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="apellidoMaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="nombre" scope="request" />
								</td>
								<td></td>																							
							</tr>

							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="prepagoFiniquitoN2.label.folio" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="prepagoFiniquitoN2Form" property="codigoOficinaProceso" scope="request" ignore="true" />- 
									<bean:write name="prepagoFiniquitoN2Form" property="folio" scope="request" ignore="true" /> 
								</td>
								<td></td>																							
							</tr>

							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="prepagoFiniquitoN2.label.fechaFiniquito" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="prepagoFiniquitoN2Form" property="fechaFiniquito" scope="request" filter="true" ignore="true" />
								</td>
								<td></td>																							
							</tr>

							<tr><td colspan="5"></td></tr>
						</tbody>
					</table>	
				</td>
			</tr>
		</tbody>
	</table>														
</html:form>

</body>
</html>
