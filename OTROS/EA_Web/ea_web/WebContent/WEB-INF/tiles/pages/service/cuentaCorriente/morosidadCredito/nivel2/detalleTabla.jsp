<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<logic:notEmpty name="detalles" scope="request">
		<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
			<tbody>
				<tr style="height : 18px;">
					<td class=smallfontreverse >
						<b>
							<bean:message key="morosidadCreditoN2.table.header" />&nbsp;
							<bean:message key="global.text.unidadMonetaria" />
						</b>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
							<tbody>
								<tr style="background-color : #cccccc;">
									<td class="smalltableheader" style="width : 22%;"><bean:message key="morosidadCreditoN2.table.header.deudor" /></td>
									<td class="smalltableheader" style="width : 22%;"><bean:message key="morosidadCreditoN2.table.header.folio" /></td>
									<td class="smalltableheader" style="width : 11%;"><bean:message key="morosidadCreditoN2.table.header.cuotaNumero" /></td>
									<td class="smalltableheader" style="width : 15%;"><bean:message key="morosidadCreditoN2.table.header.saldo" /></td>
									<td class="smalltableheader" style="width : 15%;"><bean:message key="morosidadCreditoN2.table.header.gravamenes" /></td>
									<td class="smalltableheader" style="width : 15%;"><bean:message key="morosidadCreditoN2.table.header.total" /></td>
								</tr>
								<logic:iterate id="detalle" name="detalles" scope="request">						
								<tr>
									<td class="smallprompt">
										<bean:define id="parametros" name="detalle" property="params"></bean:define>
											<html:link page="/deudaCreditoN2.do" name="parametros">
												<bean:write name="detalle" property="rut.formattedRut" filter="true" ignore="true" />-
												<bean:write name="detalle" property="rut.dv" filter="true" ignore="true" />
											</html:link>																
									</td>
									<td class=smallprompt style="vertical-align : top;">
										<bean:write name="detalle" property="codigoOficinaProceso" ignore="true" />-
										<bean:write name="detalle" property="folio" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align: top;">
										<bean:write name="detalle" property="cuota" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="deuda" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="total" filter="true" formatKey="global.monto" ignore="true" />
									</td>
								</tr>
								</logic:iterate>
								
								<tr>
									<td colspan="6" style="height : 1px; background-color : #006666;"></td>
								</tr>


							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</logic:notEmpty>
