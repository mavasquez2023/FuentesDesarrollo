<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="lista" scope="request">										
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
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.706.nivel1.table.header.periodo" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.706.nivel1.table.header.cotizacionCalculada" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.706.nivel1.table.header.asignacionFamiliarAceptada" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.706.nivel1.table.header.gravamenes" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacion.706.nivel1.table.header.saldo" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="lista" scope="request">							
							<tr>
								<td class="smallprompt">
									<html:link action="/liquidacion/706N2" name="detalle" property="params" scope="page">
										<bean:write name="detalle" property="periodoRemuneracion.formattedPeriodo" scope="page" />
									</html:link>														
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="5" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt">
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:message key="liquidacion.706.nivel1.table.label.totalPagar" />
								</td>
								<td class="smallcurrency">
									<bean:write name="total" filter="true" formatKey="global.monto" ignore="true" />
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
</body>
</html>
