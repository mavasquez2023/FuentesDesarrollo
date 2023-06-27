<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="compensacionSaldoAFavor" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.720.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 10%;"></td>
								<td class="smalltableheader" style="width : 60%;"><bean:message key="liquidacion.720.nivel2.table.header.detalle" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="liquidacion.720.nivel2.table.header.parcial" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="liquidacion.720.nivel2.table.header.monto" /></td>
							</tr>
							
							<tr>
								<td class="smallprompt">
									1
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.1" />
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="montoCreditosDescontados" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									2
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.2" />
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="montoLeasingDescontadas" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									3
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.3" />
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="montoOtros" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									4
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.4" />
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="montoCotizacion06" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									5
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.5" />
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="montoAsignacionFamiliarCompensada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency">
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									6
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.6" />
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="saldoFavorPorCotizacion" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>							
							<tr>
								<td class="smallprompt">
									7
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.720.nivel2.table.label.7" />
								</td>
								<td class="smallcurrency">
								</td>
								<td class="smallcurrency">
									<bean:write name="compensacionSaldoAFavor" property="totalSaldoAFavor" filter="true" formatKey="global.monto" ignore="true" />
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