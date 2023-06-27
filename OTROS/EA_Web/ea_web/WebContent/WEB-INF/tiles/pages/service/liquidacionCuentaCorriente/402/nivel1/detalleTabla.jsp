<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.706.nivel1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.402.nivel1.table.header.periodo" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.402.nivel1.table.header.saldoAnterior" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.402.nivel1.table.header.abonoLiquidacion" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.402.nivel1.table.header.saldoDespuesLiquidacion" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class="smallprompt">
									<bean:define id="parametros" name="detalle" property="params" />
									<bean:define id="dtoPeriodo" name="detalle" property="periodoRemuneracion" />
										<html:link action="/liquidacion/402N2" name="parametros">
											<bean:write name="dtoPeriodo" property="formattedPeriodo" scope="page" />
										</html:link>														
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="saldoAnterior" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="abono" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.402.nivel1.table.label.totales" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalSaldoAnterior" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalAbono" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalSaldoDespues" filter="true" formatKey="global.monto" ignore="true" />									
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