<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.smallheader {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
	font-weight: bold;
}

.labelNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
	font-weight: bold;
	text-decoration: none;
}

.textNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
}

.normalBold {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
	font-weight: bold;
}

.normalSmall {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
}

.numberNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
	text-align: right;
	text-decoration: none;
}

.numberBold {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
	font-weight: bold;
	text-align: right;
	text-decoration: none;
}
</style>

<logic:notEmpty name="resumen" scope="request">
	<table border="1" cellspacing="0" cellpadding="0" width="" style="background-color: #ffffff; border-color: #a9a9a9; text-align: center;">	
		<tbody>
			<tr>
				<td>
					<table style="background-color: #f8f8f8;">
						<tbody>					
							<tr><td></td></tr>
							<tr>
								<td style="width: 100%;">
									<table border="0" width="100%;">
										<tr>
											<td>
												<table style="width: 100%;" border="0">
												<tr>
													<td class="labelNormal" style="width: 40mm; text-align: left;">
														<bean:message key="tablaDesarrollo.label.fechaOtorgamiento" />:
													</td>
													<td style="width: 2mm;"></td>
													<td class="textNormal" style="width: 30mm;">
														<bean:write name="resumen" property="fechaOtorgamiento.fecha" scope="request" filter="true" formatKey="global.fecha" ignore="true" />
													</td>
													<td></td>
												</tr>		
												</table>	
											</td>
										</tr>
									</table>
								</td>
							</tr>	
							<tr><td></td></tr>
							<tr>
								<td style="width: 100%;">
									<table border="0" width="100%;">
										<tr>
											<td style="width: 100%;">
												<table style="width: 100%;" border="0">
													<tr>
														<td class="labelNormal" style="width: 50%;">Valor Cuota:</td>
														<td style="width: 2%;"></td>
														<td class="textNormal" style="width: 48%;">
															<bean:write name="resumen" property="valorCuota" scope="request" filter="true" formatKey="global.monto" ignore="true" />
														</td>
														<td></td>
													</tr>	
												</table>			
											</td>
										</tr>
									</table>
								</td>
							</tr>							
							<tr><td></td></tr>
							<tr>
								<td style="width: 100%;">
									<table border="0" width="100%;">
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0">
												<tr>
													<td class="labelNormal" style="width: 30mm;">Monto Solicitado</td>
													<td style="width: 2mm;"></td>
													<td style="width: 2mm;"></td>
													<td class="numberNormal" style="width: 20mm;">
														<bean:write name="resumen" property="montoSolicitado" scope="request" filter="true" formatKey="global.monto" ignore="true" />
													</td>
													<td></td>
												</tr>		
												<tr>
													<td class="normalSmall" colspan="5" style="color: blue; font-size: 8px; width: 5mm;">(+)</td>
													<td style="height: 2mm;"></td>							
												</tr>	
												<tr>
													<td class="labelNormal" style="text-align: right;">
														<bean:message key="tablaDesarrollo.label.seguroDesgravamen" />:
													</td>
													<td></td>
													<td></td>
													<td class="numberNormal">
														<logic:equal name="resumen" property="montoSeguroDesgravamen" scope="request" value="0">
															No
														</logic:equal>
														<logic:greaterThan name="resumen" property="montoSeguroDesgravamen" scope="request" value="0">
															S&iacute;
														</logic:greaterThan>
													</td>
													<td></td>
												</tr>		
												<tr>
													<td class="labelNormal" style="text-align: right;">
														<bean:message key="tablaDesarrollo.label.seguroCesantia" />:
													</td>
													<td></td>
													<td></td>
													<td class="numberNormal">
														<logic:equal name="resumen" property="montoSeguroCesantia" scope="request" value="0">
															No
														</logic:equal>
														<logic:greaterThan name="resumen" property="montoSeguroCesantia" scope="request" value="0">
															S&iacute;
														</logic:greaterThan>
													</td>
													<td></td>
												</tr>		
												<tr>
													<td class="labelNormal" style="text-align: right;">
														<bean:message key="tablaDesarrollo.label.seguroVida" />:
													</td>
													<td></td>
													<td></td>
													<td class="numberNormal">
														<logic:equal name="resumen" property="montoSeguroVida" scope="request" value="0">
															No
														</logic:equal>
														<logic:greaterThan name="resumen" property="montoSeguroVida" scope="request" value="0">
															S&iacute;
														</logic:greaterThan>
													</td>
													<td></td>
												</tr>		
												<tr>
													<td class="labelNormal">
														<bean:message key="tablaDesarrollo.label.impuestoLeyTimbre" />:
													</td>
													<td></td>
													<td>:</td>
													<td class="numberNormal">
														<logic:equal name="resumen" property="impuesto" scope="request" value="0">
															No
														</logic:equal>
														<logic:greaterThan name="resumen" property="impuesto" scope="request" value="0">
															S&iacute;
														</logic:greaterThan>
													</td>
													<td></td>
												</tr>		
												<tr>
													<td colspan="4" style="height : 4px;"><html:img page="/img/box_top.gif" style="width: 100%; height : 4px; text-align : center; border : 0px;" />
													</td>
													<td></td>							
												</tr>		
												<tr>
													<td class="labelNormal">
														<bean:message key="tablaDesarrollo.label.totalCredito" />:
													</td>
													<td></td>
													<td></td>
													<td class="numberBold">
														<bean:write name="resumen" property="montoTotal" scope="request" filter="true" formatKey="global.monto" ignore="true" />
													</td>
													<td></td>
												</tr>		
												</table>			
											</td>
										</tr>
									</table>
								</td>
							</tr>	
						
							<tr><td></td></tr>
			
						</tbody>
					</table>
				</td>
			</tr>
			
		</tbody>
	</table>														
</logic:notEmpty>
