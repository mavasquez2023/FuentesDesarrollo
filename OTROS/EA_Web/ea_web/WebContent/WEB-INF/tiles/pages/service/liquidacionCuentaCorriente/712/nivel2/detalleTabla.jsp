<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="cotizacion" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.712.nivel2.table.header" />&nbsp;
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
								<td class="smalltableheader" style="width : 70%;"><bean:message key="liquidacion.712.nivel2.table.header.detalle" /></td>
								<td class="smalltableheader" style="width : 30%;"><bean:message key="liquidacion.712.nivel2.table.header.monto" /></td>
							</tr>
							
							<tr>
								<td class="smallprompt">
									1
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.1" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoAproteCotizacion" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									2
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.2" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoAsignacionFamiliarCompensada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									3
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.3" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="saldoFavorEmpresa" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">									
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.descuentos" />
								</td>
								<td class="smallcurrency">
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									4
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.4" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoNominaCredito" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									5
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.5" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoNominaLeasing" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									6
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.6" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoOtros" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									7
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.7" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoTotalDescuento" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
				
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.8" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoTotalCompensado" filter="true" formatKey="global.monto" ignore="true" />
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
	<p />
	
<logic:notEmpty name="movimientos" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width: 70%"><bean:message key="liquidacion.704.nivel2.table.header.abonosAnteriores" /></td>
								<td class="smalltableheader"><bean:message key="liquidacion.704.nivel2.table.header.monto" /></td>
							</tr>

							<logic:iterate id="movimiento" name="movimientos" scope="request">							
							<tr>
								<td class="smallprompt">
									<bean:write name="movimiento" property="fechaLiquidacion.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="movimiento" property="monto" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="2" style="height : 1px; background-color : #006666;"></td>
							</tr>							

						</tbody>
					</table>
				</td>
			</tr>

		</tbody>
	</table>
	</logic:notEmpty>	

	<p />
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.704.nivel2.table.label.total" />
								</td>
								<td class="smallcurrency">
									<bean:write name="desarrolloAbono" property="abonosAnteriores" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.704.nivel2.table.label.saldoPendiente" />
								</td>
								<td class="smallcurrency">
									<bean:write name="desarrolloAbono" property="saldo" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

		</tbody>
	</table>