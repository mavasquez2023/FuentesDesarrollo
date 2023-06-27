<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<table border="1" width="100%" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
	<tbody>
		<tr>
			<td>
				<table width="100%" style="background-color: #f8f8f8;">
					<tbody>								
						<tr><td colspan="5" style="height : 4px;"></td></tr>

							<!-- Oficina -->						
							<tr>
								<td style="width : 10px;"></td>
								<td style="width : 5px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="width : 130px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.oficina" />:
								</td>												
								<td style="width : 300px; text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="oficina" property="nombre" scope="request" filter="true" ignore="true" />
								</td>												
								<td></td>																							
							</tr>
							
							<!-- Sucursal -->						
							<tr>
								<td></td>
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.sucursal" />:
								</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="sucursal" property="nombre" scope="request" filter="true" ignore="true" />
								</td>												
								<td></td>																							
							</tr>
						
							<!-- Periodo -->						
							<tr>
								<td style=""></td>
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.periodoLiquidacion" />:
								</td>												
								<td style="text-align : left;  vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="periodoLiquidacion" property="formattedPeriodo" scope="request" filter="true" ignore="true" />
								</td>												
								<td></td>																							
							</tr>

							<!-- Fecha -->						
							<tr>
								<td style=""></td>
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.fechaLiquidacion" />:
								</td>												
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="fechaLiquidacion" property="fecha" scope="request" filter="true" formatKey="global.fecha" ignore="true" />
								</td>												
								<td></td>																							
							</tr>

							<!-- Concepto -->						
							<tr>
								<td style=""></td>
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.concepto" />:
								</td>												
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="concepto" property="glosa" scope="request" filter="true" ignore="true" />
								</td>												
								<td></td>																							
							</tr>

							<!-- Periodo Remuneración-->						
							<tr>
								<td style=""></td>
								<td style="text-align : left; vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 9px; font-weight: normal; color: #000000">&#149;</td>												
								<td style="text-align : left; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:message key="global.text.periodoRemuneracion" />:
								</td>												
								<td style="text-align : left;  vertical-align: top; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold; color: #000000">
									<bean:write name="periodoRemuneracion" property="formattedPeriodo" scope="request" filter="true" ignore="true" />
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