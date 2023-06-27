<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="detalle" scope="request">
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.706.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>														
			</tr>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc;" cellspacing=0 cellpadding=2>
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 50%; text-align : left;">&nbsp;
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.empresa" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.caja" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.diferencia" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.a" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="cotizacionInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="diferenciaCotizacion" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
					
							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.b" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="asignacionFamiliarInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="diferenciaAsignacionFamiliar" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.c" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="gravamenes" value="0">
										<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
																									
							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.d" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
							<tr>
								<td class="smallprompt" >
									<span style="margin-left: 10px;">
										<bean:message key="liquidacion.706.nivel2.table.label.saldoDeber" />
									</span>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoCajaPlanilla" value="0">
									<!--
										<bean:write name="detalle" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									-->
									</logic:notEqual></td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoCajaCalculado" value="0">
										<bean:write name="detalle" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
							<tr>
								<td class="smallprompt" >
									<span style="margin-left: 10px;">
										<bean:message key="liquidacion.706.nivel2.table.label.saldoHaber" />
									</span>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoEmpresaPlanilla" value="0">
										<bean:write name="detalle" property="saldoEmpresaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual></td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoEmpresaCalculado" value="0">
										<bean:write name="detalle" property="saldoEmpresaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>

							<tr>
								<td class="smalltableheader" style="text-align : left;">
									<bean:message key="liquidacion.706.nivel2.table.label.saldoFinal" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="saldoPagar" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="saldoCobrar" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
						</tbody>
					</table>
				</td>
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