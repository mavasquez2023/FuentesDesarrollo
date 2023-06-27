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

<html:form action="/liquidacionN1" method="POST">	

<table border="1" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
	<tbody>
		<tr>
			<td>
				<table style="background-color: #f8f8f8;">
					<tbody>								
						<tr><td colspan="5" style="height : 4px;"></td></tr>

							<!-- Opción Oficinas -->						
							<logic:notEmpty name="oficinas" scope="request">								
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 130px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.oficina" />:
								</td>												
								<td>
									<html:select property="codigoOficina" style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal color: #000000"
										onchange="document.forms[0].idChanged.value='OFICINA'; 
												  document.forms[0].submit(); 
												  return false;">
									<html:options collection="oficinas" property="idValue" labelProperty="displayValue" />
									</html:select>				
								</td>												
								<td></td>																							
							</tr>
							</logic:notEmpty>
							
							<!-- Opción Sucursal -->
							<logic:notEmpty name="sucursales" scope="request">
							<tr>
								<logic:notEmpty name="sucursales" scope="request">												
								<td></td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.sucursal" />:
								</td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<html:select property="codigoSucursal" style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal color: #000000"
										onchange="document.forms[0].idChanged.value='SUCURSAL';
												  document.forms[0].submit(); 
												  return false;">
									<html:options collection="sucursales" property="idValue" labelProperty="displayValue" />
									</html:select>			
								</td>
								</logic:notEmpty>
								<td></td>																							
							</tr>
							</logic:notEmpty>
		
							<!-- Opción Periodo Liquidacion-->
							<logic:notEmpty name="periodosLiquidacion" scope="request">
							<tr>
								<td style=""></td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.periodoLiquidacion" />:
								</td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<html:select property="periodoLiquidacion" style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal color: #000000"
										onchange="document.forms[0].idChanged.value='PERIODO';
												  document.forms[0].submit(); 
												  return false;">
									<html:options collection="periodosLiquidacion" property="idValue" labelProperty="displayValue" />
									</html:select>			
								</td>
								<td></td>																							
							</tr>
							</logic:notEmpty>

							<!-- Opción Fecha Liquidacion -->
							<logic:notEmpty name="fechasLiquidacion" scope="request">
							<tr>
								<td style=""></td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="liquidacionN1.label.fechaLiquidacion" />:
								</td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<html:select property="fechaLiquidacion" style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal color: #000000"
										onchange="document.forms[0].idChanged.value='FECHA';
												  document.forms[0].submit(); 
												  return false;">
									<html:options collection="fechasLiquidacion" property="idValue" labelProperty="displayValue" />
									</html:select>			
								</td>
								<td></td>																							
							</tr>
							</logic:notEmpty>
						
						<tr><td colspan="5"></td></tr>
					</tbody>
				</table>	
			</td>
		</tr>
	</tbody>
</table>														

<html:hidden property="idChanged" />
<html:hidden property="rutEmpresa" />											
</html:form>															

</body>
</html>
