<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacionN1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 60%;" colspan="2"><bean:message key="liquidacionN1.table.header.concepto" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacionN1.table.header.deber" /></td>
								<td class="smalltableheader" style="width : 20%;"><bean:message key="liquidacionN1.table.header.haber" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class=smallprompt colspan="2" onmouseover="onto('i'+<bean:write name="detalle" property="concepto.codigo" scope="page" />);" onmouseout="out('i'+<bean:write name="detalle" property="concepto.codigo" scope="page" />);">
									<bean:define id="cod" name="detalle" property="concepto.codigo" scope="page" />
									<logic:equal name="detalle" property="isDefault" value="Y" scope="page">
										<logic:equal name="detalle" property="concepto.tipo" value="I" scope="page">
											<html:link action="/liquidacion/N2/abonosASaldoAduedado" name="detalle" property="params" scope="page">
												<bean:write name="detalle" property="concepto.glosa" />
											</html:link>
										</logic:equal>
										<logic:equal name="detalle" property="concepto.tipo" value="E" scope="page">
											<html:link action="/liquidacion/N2/pagosSaldoAFavorEmpresa" name="detalle" property="params" scope="page">
												<bean:write name="detalle" property="concepto.glosa" />
											</html:link>
										</logic:equal>
									</logic:equal>
									<logic:equal name="detalle" property="isDefault" value="N" scope="page">
										<html:link action='<%=(new java.lang.String("liquidacion/")) + cod.toString() + (new java.lang.String("N1"))%>' name="detalle" property="params" scope="page">
											<bean:write name="detalle" property="concepto.glosa" />
										</html:link>
									</logic:equal>
									<logic:empty name="detalle" property="params" scope="page">
										<bean:write name="detalle" property="concepto.glosa" />
									</logic:empty>
								</td>
								<td class=smallcurrency>
									<logic:equal name="detalle" property="concepto.tipo" value="I">
										<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
									</logic:equal>							
								</td>
								<td class=smallcurrency>
									<logic:equal name="detalle" property="concepto.tipo" value="E">
										<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
									</logic:equal>							
								</td>
							</tr>
							</logic:iterate>
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>
	
	
							<!-- Subtotales -->																
							<tr>
								<td style="width : 30%;"></td>
								<td class=smallprompt><bean:message key="liquidacionN1.label.subtotales" /></td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="subtotalCobrar" scope="request" value="0">
										<bean:write name="resumen" property="subtotalCobrar" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="subtotalPagar" scope="request" value="0">
										<bean:write name="resumen" property="subtotalPagar" scope="request" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
							</tr>
	
							<!-- Gravámenes -->
							<tr>
								<td style="width : 30%;"></td>
								<td class=smallprompt>
									<logic:notEqual name="resumen" property="gravamenes" scope="request" value="0">
										<html:link action="/liquidacion/gravamenes" name="paramsGravamenes" scope="request" styleClass="smallPrompt" >
											<bean:message key="liquidacionN1.label.gravamenes" />
										</html:link>
									</logic:notEqual>		
									<logic:equal name="resumen" property="gravamenes" scope="request" value="0">
										<bean:message key="liquidacionN1.label.gravamenes" />
									</logic:equal>		
								</td>
								<td class=smallcurrency><bean:write name="resumen" property="gravamenes" scope="request" filter="true" formatKey="global.monto" ignore="true" /></td>
								<td class=smallcurrency></td>
							</tr>
	
							<!-- Totales -->
							<tr>
								<td style="width : 30%;"></td>
								<td class=smallprompt><bean:message key="liquidacionN1.label.totales" /></td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="totalCobrar" value="0"> 
										<bean:write name="resumen" property="totalCobrar" filter="ture" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="subtotalPagar" scope="request" value="0"> 
										<bean:write name="resumen" property="subtotalPagar" scope="request" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
							</tr>												
	
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>
	
							<!-- Monto a Pago -->
							<tr>
								<td style="width : 30%;"></td>
								<td class=smallprompt><bean:message key="liquidacionN1.label.montoPago" /></td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="saldoCobrar" scope="request" value="0"> 
										<bean:write name="resumen" property="saldoCobrar" scope="request" filter="ture" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class=smallcurrency>
									<logic:notEqual name="resumen" property="saldoPagar" scope="request" value="0"> 
										<bean:write name="resumen" property="saldoPagar" scope="request" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
							</tr>												
	
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>
							
							<tr>
								<td>&nbsp;
								</td>
							</tr>
							<logic:notEqual name="resumen" property="saldoPagar" scope="request" value="0">
							<tr>
								<td>
									<html:link page="/documentoPago.do" name="paramsDocumentoPago" scope="request" styleClass="smallprompt">
										<bean:message key="liquidacionN1.label.documentoPago" />
									</html:link>
								</td>
							</tr>
							</logic:notEqual>
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