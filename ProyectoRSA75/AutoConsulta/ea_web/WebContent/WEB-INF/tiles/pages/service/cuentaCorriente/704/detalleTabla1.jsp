<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<table style="width : 430px; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="ctaCteN4.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>														
			</tr>
			<tr>
				<td>
					<table style="border : 0px; border-color : #cccccc;" cellspacing=1 cellpadding=2   width=100%>
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 50%; text-align : left;">&nbsp;
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="ctaCteN4.table.header.empresa" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="ctaCteN4.table.header.caja" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.a" />%
								</td>
								<td class="smallcurrency" >
									<bean:write name="cotizacion" property="cotizacionInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="cotizacion" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
					
							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.b" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="cotizacion" property="asignacionFamiliarInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="cotizacion" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.c" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="gravamenes" value="0">
										<bean:write name="cotizacion" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>																			</td>																				
							</tr>
																									
							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.d" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
								</td>																				
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.deber" />
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="saldoCajaPlanilla" value="0">
										<bean:write name="cotizacion" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="saldoCajaCalculado" value="0">
										<bean:write name="cotizacion" property="saldoCajaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>																				
							</tr>
							<tr>
								<td class="smallprompt" >
									<bean:message key="ctaCteN4.table.label.haber" />
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="saldoEmpresaPlanilla" value="0">
										<bean:write name="cotizacion" property="saldoEmpresaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="saldoEmpresaCalculado" value="0">
										<bean:write name="cotizacion" property="saldoEmpresaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>																				
							</tr>
							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
							</tr>

							<tr>
								<td class="smalltableheader" style="text-align : left;">
									<logic:equal name="ctaCteN4Form" property="codigoConcepto" value="704">
										<bean:message key="ctaCteN4.table.label.diferencia" />
									</logic:equal>
									<logic:equal name="ctaCteN4Form" property="codigoConcepto" value="706">
										<bean:message key="ctaCteN4.table.label.haber" />
									</logic:equal>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="haber" value="0">
										<bean:write name="cotizacion" property="haber" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="cotizacion" property="deber" value="0">
										<bean:write name="cotizacion" property="deber" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>																				
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>