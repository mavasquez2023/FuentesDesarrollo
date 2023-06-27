<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<logic:notEmpty name="detalle" scope="request">										
	<table style="width : 460; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="ctaCte.402.nivel1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 10%;"><bean:message key="ctaCte.402.nivel1.table.header.item" /></td>
								<td class="smalltableheader" style="width : 70%;"><bean:message key="ctaCte.402.nivel1.table.header.concepto" /></td>
								<td class="smalltableheader" style="width : 30%;"><bean:message key="ctaCte.402.nivel1.table.header.monto" /></td>
							</tr>

							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										1
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.1" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoEmitido" scope="request" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										2
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.2" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoCompensado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										3
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.3" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoAceptado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										4
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.4" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoInformado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										5
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.5" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoNoPagado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										6
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.6" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoDiferenciaInconsistencia" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										7
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.7" />
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoAutorizado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										8
									</span>
								</td>
								<td class="smallprompt">
									<html:link action="/ctaCte/402N2" name="params" scope="request" >
										<bean:message key="ctaCte.402.nivel1.table.label.8" />
									</html:link>
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoMayorValor" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							<tr>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										9
									</span>
								</td>
								<td class="smallprompt">
									<bean:message key="ctaCte.402.nivel1.table.label.9" />
								</td>
								<td class="smallcurrency" style=" text-align: right; vertical-align: top;">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoDiferenciaCobrar" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>

							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
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