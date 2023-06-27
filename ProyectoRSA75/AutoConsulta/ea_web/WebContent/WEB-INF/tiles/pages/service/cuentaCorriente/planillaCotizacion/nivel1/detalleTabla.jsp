<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="declaracionCotizacionSinPagoN1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN1.table.header.periodo" /></td>
								<td class="smalltableheader" style="width : 40%;"><bean:message key="declaracionCotizacionSinPagoN1.table.header.codigoBarra" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN1.table.header.cotizacion" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN1.table.header.asignacionFamiliar" /></td>
								<td class="smalltableheader" style="width : 15%;"><bean:message key="declaracionCotizacionSinPagoN1.table.header.saldoCaja" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">						
							<tr>
								<td class="smallprompt">
									<html:link action="/ctaCte/declaracionCotizacionSinPago/N2" name="detalle" property="params" scope="page">
										<bean:write name="detalle" property="periodo.formattedPeriodo" />
									</html:link>															
								</td>
								<td class=smallprompt style="vertical-align : top;">
									<bean:write name="detalle" property="codigoBarra" filter="true" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align : top;">
									<bean:write name="detalle" property="montoCotizacion" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="montoAsignacionFamiliar" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<bean:write name="detalle" property="saldo" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

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