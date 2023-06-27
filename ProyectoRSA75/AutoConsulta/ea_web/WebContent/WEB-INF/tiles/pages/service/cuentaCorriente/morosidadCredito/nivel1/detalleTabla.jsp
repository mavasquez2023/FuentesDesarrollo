<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>


	<logic:notEmpty name="detalles" scope="request">
		<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
			<tbody>
				<tr style="height : 18px;">
					<td class=smallfontreverse >
						<b>
							<bean:message key="morosidadCreditoN1.table.header" />&nbsp;
							<bean:message key="global.text.unidadMonetaria" />
						</b>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
							<tbody>
								<tr style="background-color : #cccccc;">
									<td class="smalltableheader" style="width : 20%;"><bean:message key="label.morosidadCreditoN1.periodo" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="label.morosidadCreditoN1.deuda" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="label.morosidadCreditoN1.gravamenes" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="label.morosidadCreditoN1.total" /></td>
								</tr>
								<logic:iterate id="detalle" name="detalles" scope="request">						
								<tr>
									<td class="smallprompt" style="width: 25%">
											<html:link action="/ctaCte/morosidadCredito/N2" name="detalle" property="params" scope="page">
												<bean:write name="detalle" property="periodo.formattedPeriodo" scope="page" filter="true" ignore="true" />
											</html:link>																
									</td>
									<td class=smallcurrency style="width: 25%; vertical-align : top;">
										<bean:write name="detalle" property="deuda" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="width: 25%; vertical-align: top;">
										<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="width: 25%; vertical-align : top;">
										<bean:write name="detalle" property="total" filter="true" formatKey="global.monto" ignore="true" />
									</td>
								</tr>
								</logic:iterate>
								
								<tr>
									<td colspan="5" style="height : 1px; background-color : #006666;"></td>
								</tr>


							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</logic:notEmpty>