<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="ctaCte.402.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 61%;" colspan="2"><bean:message key="ctaCte.402.nivel2.table.header.afiliado" /></td>
								<td class="smalltableheader" style="width : 13%;"><bean:message key="ctaCte.402.nivel2.table.header.montoCompensado" /></td>
								<td class="smalltableheader" style="width : 13%;"><bean:message key="ctaCte.402.nivel2.table.header.montoAutorizado" /></td>
								<td class="smalltableheader" style="width : 13;"><bean:message key="ctaCte.402.nivel2.table.header.montoDiferencia" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class="smallprompt" style="width : 20%; text-align: right; vertical-align: top;">
									<bean:write name="detalle" property="afiliado.rut.formattedRut" scope="page" />-
									<bean:write name="detalle" property="afiliado.rut.dv" scope="page" />
								</td>
								<td class="smallprompt" style="">
									<bean:write name="detalle" property="afiliado.apellidoPaterno" scope="page" />&nbsp;
									<bean:write name="detalle" property="afiliado.apellidoMaterno" scope="page" />&nbsp;
									<bean:write name="detalle" property="afiliado.nombre" scope="page" />
								</td>
								<td class="smallcurrency" style="vertical-align: top;">
									<bean:write name="detalle" property="montoCompensado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" style="vertical-align: top;">
									<bean:write name="detalle" property="montoAutorizado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" style="vertical-align: top;">
									<bean:write name="detalle" property="montoDiferencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="5" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt" colspan="2">
									<bean:message key="ctaCte.402.nivel2.table.label.totales" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalMontoCompensado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalMontoAutorizado" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="totalMontoDiferencia" filter="true" formatKey="global.monto" ignore="true" />									
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