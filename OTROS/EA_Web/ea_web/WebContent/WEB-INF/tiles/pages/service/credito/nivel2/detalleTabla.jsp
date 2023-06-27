<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="detalles" scope="request">											
	<table style="width :100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="deudaCreditoN2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 10%;"><bean:message key="deudaCreditoN2.table.header.cuotaNro" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.fechaVencimiento" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.saldoCuota" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.gravamenes" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.subtotal" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.acumulado" /></td>
								<td class=smallprompt style="font-weight : bold; text-align : center; width : 15%;"><bean:message key="deudaCreditoN2.table.header.estado" /></td>
							</tr>
							<logic:iterate id="detalle" name="detalles" scope="request">
								<tr>
									<bean:define id="dtoFecha" name="detalle" property="fechaVencimiento" />								
									<td class="smallprompt"><bean:write name="detalle" property="numero" /></td>
									<td class="smallprompt"><bean:write name="dtoFecha" property="fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" /></td>
									<td class="smallcurrency"><bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" /></td>
									<td class="smallcurrency"><bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" /></td>
									<td class="smallcurrency"><bean:write name="detalle" property="subtotal" filter="true" formatKey="global.monto" ignore="true" /></td>
									<td class="smallcurrency"><bean:write name="detalle" property="acumulado" filter="true" formatKey="global.monto" ignore="true" /></td>
									<td class="smallprompt"><bean:write name="detalle" property="estado" /></td>
								</tr>
							</logic:iterate>
							<tr>
								<td colspan="7" style="height : 1px; background-color : #006666;"></td>
							</tr>
							<tr>
								<td colspan="7">
									<bean:write name="pageBar" scope="request" filter="false" />
								</td>
							</tr>							
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</logic:notEmpty>															
