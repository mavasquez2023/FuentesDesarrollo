<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<table border="1" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
	<tbody>
		<tr>
			<td>
				<table style="background-color: #f8f8f8;">
					<tbody>								
						<tr><td colspan="5" style="height : 4px;"></td></tr>

							<!-- Afiliado -->
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.afiliado" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:define id="rut" name="afiliado" property="rut" scope="request" /> 
									<bean:write name="rut" property="formattedRut" scope="page" />-
									<bean:write name="rut" property="dv" scope="page" /><br />
									<bean:write name="afiliado" property="apellidoPaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="apellidoMaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="nombre" scope="request" />
								</td>
								<td></td>																							
							</tr>
							<!-- Folio -->
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 100px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="deudaCreditoN2.label.folio" />:
								</td>
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="tablaDesarrolloCreditoForm" property="codigoOficinaProceso" ignore="true" />- 
									<bean:write name="tablaDesarrolloCreditoForm" property="folio" ignore="true" /> 
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