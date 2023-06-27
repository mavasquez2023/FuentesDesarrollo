<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="declaracionCotizacionSinPagoN2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 30%;" rowspan="2"><bean:message key="declaracionCotizacionSinPagoN2.table.header.cotizacion" /></td>
								<td class="smalltableheader" style="width : 30%;" colspan="2"><bean:message key="declaracionCotizacionSinPagoN2.table.header.trabajador" /></td>
								<td class="smalltableheader" style="width : 20%;" rowspan="2"><bean:message key="declaracionCotizacionSinPagoN2.table.header.remuneracion" /></td>
								<td class="smalltableheader" style="width : 20%;" rowspan="2"><bean:message key="declaracionCotizacionSinPagoN2.table.header.monto" /></td>
							</tr>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN2.table.header.hombre" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN2.table.header.mujer" /></td>
							</tr>
							
							<tr>
								<td class="smallprompt">
									<bean:message key="declaracionCotizacionSinPagoN2.table.label.afiliadoFonosa" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorHombreFonasa" filter="true" ignore="true" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorMujerFonasa" filter="true" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="remuneracionImponibleFonasa" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="monto06Fonasa" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="declaracionCotizacionSinPagoN2.table.label.afiliadoIsapre" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorHombreIsapre" filter="true" ignore="true" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorMujerIsapre" filter="true" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="remuneracionImponibleIsapre" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="declaracionCotizacionSinPagoN2.table.label.total" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorHombreTotal" filter="true" ignore="true" />
								</td>
								<td class=smallprompt style="text-align: right; vertical-align : top;">
									<bean:write name="detalle" property="numeroTrabajadorMujerTotal" filter="true" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="remuneracionImponibleTotal" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
								</td>
							</tr>

							<tr>
								<td class="smallprompt" colspan="4">
									<bean:message key="declaracionCotizacionSinPagoN2.table.label.asignacionFamiliarCompensada" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="montoAsignacionFamiliarCompensada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt" colspan="5">
									&nbsp;
								</td>
							</tr>

							<tr>
								<td class="smallprompt" colspan="4">
									<bean:message key="declaracionCotizacionSinPagoN2.table.label.saldoCaja" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
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