<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>

	<html:messages id="msg" property="messageSinMovimiento" message="true">
		<span style="font-family: verdana; font-size: 10px; color: #cc3333;">
			<bean:write name="msg" scope="page" />
		</span>
	</html:messages>
	
	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
	
	
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="ctaCteN1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 60%;" colspan="2">
									<bean:message key="ctaCteN1.table.header.concepto" />
								</td>
								<td class="smalltableheader" style="width : 20%;">
									<bean:message key="ctaCteN1.table.header.deber" />
								</td>
								<td class="smalltableheader" style="width : 20%;">
									<bean:message key="ctaCteN1.table.header.haber" />
								</td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">						
							<tr>
								<td class="smallprompt" colspan="2" onmouseover="onto('i'+<bean:write name="detalle" property="concepto.codigo" scope="page" />);" onmouseout="out('i'+<bean:write name="detalle" property="concepto.codigo" scope="page" />);">
									<logic:greaterEqual name="detalle" property="concepto.codigo" value="4">
										<html:link action="/ctaCteN2" name="detalle" property="params">
											<span class="uslnav">
												<bean:write name="detalle" property="concepto.glosa" />
											</span>
										</html:link>
									</logic:greaterEqual>																
									<logic:equal name="detalle" property="concepto.codigo" value="1">
										<logic:notEqual name="detalle" property="montoCobrar" value="0">
										<html:link action="/ctaCte/declaracionCotizacionSinPago/N1" name="detalle" property="params">
											<span class="uslnav">
												<bean:write name="detalle" property="concepto.glosa" />
											</span>
										</html:link>
										</logic:notEqual>
										<logic:equal name="detalle" property="montoCobrar" value="0">
											<bean:write name="detalle" property="concepto.glosa" />
										</logic:equal>
									</logic:equal>																
									<logic:equal name="detalle" property="concepto.codigo" value="2">
										<logic:notEqual name="detalle" property="montoCobrar" value="0">
										<html:link page="/deprecated/uc.html" name="detalle" property="params">
											<span class="uslnav">
												<bean:write name="detalle" property="concepto.glosa" />
											</span>
										</html:link>
										</logic:notEqual>
										<logic:equal name="detalle" property="montoCobrar" value="0">
											<bean:write name="detalle" property="concepto.glosa" />
										</logic:equal>
									</logic:equal>																
									<logic:equal name="detalle" property="concepto.codigo" value="3">
										<logic:notEqual name="detalle" property="montoCobrar" value="0">
										<html:link action="/ctaCte/morosidadCredito/N1" name="detalle" property="params">
											<span class="uslnav">
												<bean:write name="detalle" property="concepto.glosa" />
											</span>
										</html:link>
										</logic:notEqual>
										<logic:equal name="detalle" property="montoCobrar" value="0">
											<bean:write name="detalle" property="concepto.glosa" />
										</logic:equal>
									</logic:equal>
								</td>
								<td class=smallcurrency style="vertical-align : top;">
									<logic:notEqual name="detalle" property="montoCobrar" value="0">
										<bean:write name="detalle" property="montoCobrar" filter="true" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>						
								</td>
								<td class=smallcurrency style="vertical-align: top;">
									<logic:notEqual name="detalle" property="montoPagar" value="0">
										<bean:write name="detalle" property="montoPagar" filter="true" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>							
								</td>
							</tr>
							</logic:iterate>
	
	
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>
	
							<!-- Total -->
							<tr>
								<td class="smallboldprompt" style="width : 60%;" colspan="2">
									<bean:message key="ctaCteN1.table.label.total" />
								</td>
								<td class=smallboldcurrency>
									<bean:write name="resumen" property="totalCobrar" scope="request" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallboldcurrency>
									<bean:write name="resumen" property="totalPagar" scope="request" filter="true" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>	
	
							<!-- Saldo -->
							<tr>
								<td class="smallboldprompt" style="width : 60%;" colspan="2">
									<logic:notEqual name="resumen" property="saldoCobrar" scope="request" value="0">
										<bean:message key="ctaCteN1.table.label.saldoDeber" />
									</logic:notEqual>
									<logic:notEqual name="resumen" property="saldoPagar" scope="request" value="0">
										<bean:message key="ctaCteN1.table.label.saldoHaber" />
									</logic:notEqual>
								</td>
								<td class=smallboldcurrency>
									<logic:notEqual name="resumen" property="saldoCobrar" scope="request" value="0"> 
										<bean:write name="resumen" property="saldoCobrar" scope="request" filter="true" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class=smallboldcurrency>
									<logic:notEqual name="resumen" property="saldoPagar" scope="request" value="0">  
										<bean:write name="resumen" property="saldoPagar" scope="request" filter="true" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
							</tr>																																	
						</tbody>
					</table>
				</td>
			</tr>
	
			<tr>
				<td>&nbsp;</td>
			</tr>
				
			<tr>
				<td>
					<table width="400px;">
						<tr>
							<td class="note" style="text-align:justify; vertical-align: top; color: #336666;">
								Nota: 
							</td>
							<td class="note" style="text-align:justify; color: #336666;">
								<bean:message key="ctaCteN1.nota" />
							</td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>
			
		</tbody>
	</table>
	</logic:notEmpty>
