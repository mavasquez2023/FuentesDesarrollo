<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<html:form action="/ctaCteN1.do" method="POST">	

<table border="1" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
	<tbody>
		<tr>
			<td>
				<table style="background-color: #f8f8f8;">
					<tbody>								
						<tr><td colspan="5" style="height : 4px;"></td></tr>
						
							<!-- Opción Oficina -->
							<logic:notEmpty name="oficinas" scope="request">								
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
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
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.sucursal" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
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