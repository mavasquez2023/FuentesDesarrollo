<%@ page import="cl.azurian.sce.Constantes" %> 
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
<%@ page import="cl.azurian.sce.dto.servicios.SubCrc414"%>
<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO"%>
<%@ page import="cl.araucana.autoconsulta.vo.AfiliadoVO"%>
<script>       
function seleccion(opcion){
 
	document.PARAMForm.campo.value = opcion; 
	document.PARAMForm.submit();
 
}  
</script> 
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr> 
<!-- menu de opciones -->
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%>
</td>
<td width='1%'>&nbsp;
</td>
<td valign='top'> 

<table border="0" cellpadding="0" cellspacing="0">
<br>
	<tr>
		<td valign="top">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		<tr>
          			<td width="97%" class="title15">Simulador de Cr&eacute;dito
          			</td>
        		</tr> 
      		</table>
     	</td> 
	</tr> 
    <tr>
    	<td valign="top">
    		<table width="100%" border="0" cellspacing="0" cellpadding="0">
        		<tr valign="top">
            		<td width="78%">
            			<table width="100%" border="0" cellspacing="0" cellpadding="0">
                			<tr>
                  				<td valign="top" background="images/divider_blue.gif"><img src="images/block.gif" width="3" height="1">
                  				</td>
                			</tr>
                			<!-- Modificacion realizada para mensaje de Deuda InterCaja -->
			    			<!-- 29/09/2009 Neoris Argentina -->
			    			<logic:present name="montoDeudaIntercaja">
			        			<tr>
			        				<td class="c11azul" nowrap="nowrap">
			        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.1"/>
			        					<bean:write name="cajaDeudaIntercaja"/>
			        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.2"/>
			        					<bean:write name="montoDeudaIntercaja"/>
			        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.3"/>
			        				</td>
			        			</tr>
			    			</logic:present>
			    			<!-- Fin Modificacion -->
                			<tr>
                  				<td valign="top"><br>
                  					<table width="100%" border="0" cellspacing="0" cellpadding="0">
                      					<tr> 
					                      <%
					                      cl.araucana.autoconsulta.vo.UsuarioVO usuario = (cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario");
			  								
					                      String urlPdf = "";
					                      if(usuario.isEsAfiliadoActivo())
					                      	 urlPdf = "/"+fullappname+"/PDF/SolicitudesAfiliados.pdf";
					                      else
					                      	 urlPdf = "/"+fullappname+"/PDF/SolicitudesPensionados.pdf";
					                      
					                      String logoPdfPath = "/"+fullappname+"/images/pdf.jpg";
					                      %>
	                      					<td width="100%"><a href="<%=urlPdf%>" target="_blank">Descargar Solicitud de Crédito</a><img src="<%=logoPdfPath%>" border="0"/>
					                      	</td>
										</tr>
										<tr>
					                        <td width="100%"><img src="images/arrow2.gif" width="13" height="14">
	                					        <b>Resultado Simulaci&oacute;n</b>
											</td>
	                      				</tr>
	                    			</table>
	                    		</td>
							</tr>																						
							<tr>							
								<td align="center">
									<logic:present name="results">
										<table width="100%" cellspacing="0" cellpadding="0">
											<logic:equal name="results" property="numeroCuotas" value="84">
												<tr valign="top">
													<td colspan='2' class="c11rojo" align="center" nowrap>												
														<bean:message key="label.simulacion.credito.mensaje84Cuotas"/>												
													</td>
												</tr>
											</logic:equal>
										</table>
										<br>	
										<!-- Identificacion de quien esta simulando-->
										<table>
																		
										<tr valign="top">
											<td colspan='2' class="c11azul" align="center">
												<table width="100%" border="0" cellspacing="0" cellpadding="0">
													<tr>														
														<td class="c11azul" align="center">
															<table width="100%" border="1" cellspacing="0" cellpadding="0">
																<tr>
																	<td colspan="4" class="celeste" align="left">
																		<bean:message key="label.simulacion.credito.identificacion"/>
																	</td>								
																</tr>	
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.identificacion.nombre"/>	
																	</td>
																	<td class="c11azul" align="left" nowrap>
																		<%String nombreCompleto = "";
																			if(usuario.isEsEmpresa()){
																				nombreCompleto = ((cl.araucana.autoconsulta.vo.AfiliadoVO)session.getAttribute("datosAfiliado")).getFullNombre();%>
																				<%=nombreCompleto%>
																			<%}else{%>
																					<bean:write name="nombre"/>
																			<%}%>
																	</td>
																</tr>
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.identificacion.rut"/>	
																	</td>
																	<td class="c11azul" align="left" nowrap>
																		<%String rutCompleto = "";
																			if(usuario.isEsEmpresa()){
																				rutCompleto = ((cl.araucana.autoconsulta.vo.AfiliadoVO)session.getAttribute("datosAfiliado")).getFullRut();%>
																				<%=rutCompleto%>
																			<%}else{%>
																					<bean:write name="rut"/>
																			<%}%>
																	</td>
																</tr>
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.identificacion.tipo"/>	
																	</td>
																	<td class="c11azul" align="left" nowrap>
																		<%																		
																		String tipoAfiliado ="";
																		if(session.getAttribute("afiliado")!=null &&
																			session.getAttribute("afiliado").equals("yes"))
																			tipoAfiliado="Afiliado";
																		else
																			tipoAfiliado="Pensionado";
																		%>
																		<%=tipoAfiliado%>
																	</td>
																</tr>
																<%if(session.getAttribute("afiliado")!=null &&
																			session.getAttribute("afiliado").equals("yes")){%>
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.identificacion.rutempresa"/>	
																	</td>
																	<td class="c11azul" align="left" nowrap>
																		<%
																			String fullRutEmpresa= ""+((SubCrc414)session.getAttribute("perfil")).getRutEmpresa() +
																									"-"+
																									((SubCrc414)session.getAttribute("perfil")).getDvEmpresa();
																		%>
																		<%=fullRutEmpresa%>
																	</td>
																</tr>
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.identificacion.nombreempresa"/>	
																	</td>
																	<td class="c11azul" align="left" nowrap>
																		<bean:write name="perfil" property="nombreEmpresa"/>
																	</td>
																</tr>
																<%}%>
															</table>
														</td>
													</tr>
												</table>				
											</td>	
										</tr>
										<!-- Resultado Simulación -->
										<!--<table cellspacing="0" cellpadding="0">-->
													
											<tr valign="top">
												<td align="center">
													<table width="100%" border="1" cellspacing="0" cellpadding="0">
														<logic:present name="results">
															<tr>
																<td colspan="2" class="celeste" align="left">
																	<bean:message key="label.simulacion.credito.resultado"/>
																</td>								
															</tr>							
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.montoOtorgado"/>	
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="montoOtorgado" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.valorCuota"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="valorCuotaADescuento" formatKey="format.money"/>
																</td>
																</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.numeroCuotas"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write	name="results" property="numeroCuotas" />
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.tasa"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="tasa" formatKey="format.float3"  locale="RegSettingSrv"/> %
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.descuentoCreditosAnteriores"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="descuentoCreditosAnteriores" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.liquidoPago"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<logic:greaterEqual name="results" property="liquidoPago" value="0">
																		<bean:write name="results" property="liquidoPago" formatKey="format.money"/>
																	</logic:greaterEqual>
																	<logic:lessThan name="results" property="liquidoPago" value="0">
																		<bean:message key="label.simulacion.credito.resultado.montoSolicitadoMenorSaldoAdeudado"/>
																	</logic:lessThan>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.servicioTeleasistencia"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																		<bean:write name="results" property="servicioTeleasistencia" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.gastosNotariales"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																		<bean:write name="results" property="gastosNotariales" formatKey="format.money"/>
																</td>
															</tr>															
															<tr>
																<td class="celeste" colspan="2" nowrap align="left">
																	<bean:message key="label.simulacion.credito.resultado.detalleMontoOtorgado"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.montoSolicitado"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="montoSolicitado" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.impuesto"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="impuestosLTE" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.seguroDesgravamen"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																	<bean:write name="results" property="seguroDesgravamen" formatKey="format.money"/>
																</td>
															</tr>
															<tr>
																<td class="c11azul" nowrap>
																	<bean:message key="label.simulacion.credito.resultado.seguroVida"/>
																</td>
																<td class="c11azul" align="right" nowrap>
																<bean:write name="results" property="seguroVida" formatKey="format.money"/>
																</td>
															</tr>
															<logic:present name="afiliado">
																<tr>
																	<td class="c11azul" nowrap>
																		<bean:message key="label.simulacion.credito.resultado.seguroCesantia"/>
																	</td>
																	<td class="c11azul" align="right" nowrap>
																		<bean:write name="results" property="seguroCesantia" formatKey="format.money"/>
																	</td>
																</tr>
															</logic:present>
														</logic:present>
													</table>
												</td>
												<td class="c11azul" align="center">
													<table border="1" cellspacing="0" cellpadding="0">
														<tr>
															<td colspan="5" class="celeste" align="left">
																<bean:message key="label.simulacion.credito.resultado.otrasOpciones"/>
															</td>							
														</tr>						
														<tr>
															<td class="c11azul" align="center" nowrap>
																<bean:message key="label.simulacion.credito.resultado.cuota"/>
															</td>
															<td class="c11azul" align="center" nowrap>
																<bean:message key="label.simulacion.credito.resultado.tasa"/>
															</td>
															<td class="c11azul" align="center">
																<bean:message key="label.simulacion.credito.resultado.valorCuota"/>
															</td>
															<td class="c11azul" align="center" nowrap>
																<bean:message key="label.simulacion.credito.resultado.inicio"/>
															</td>
															<td class="c11azul" align="center" nowrap>
																<bean:message key="label.simulacion.credito.resultado.termino"/>
															</td>
														</tr>
		
														<!-- Otras Opciones de Cuotas -->
														<bean:define name="results" property="montoMaxCuota" id="varMontoMaxCuota" />
														<bean:define name="results" property="maxCuotas" id="varCantMaxCuotas" />
									
														<logic:present name="results" property="otrasOpcionesCuotas">
															<logic:greaterThan name="varCantMaxCuotas" value="0">
																<logic:iterate name="results" property="otrasOpcionesCuotas" id="OpcionesCuotaData">
																	<logic:lessEqual name="OpcionesCuotaData" property="cuotas" value="<%=varCantMaxCuotas.toString()%>">
																	<!--***** Si es <> a las cuotas solicitadas ***-->
																		<tr>
																		<!--***** Si es mayor o igual Excede el % Permitido ***-->
																			<logic:greaterThan name="OpcionesCuotaData"
																			property="valorCuota"
																			value="<%=varMontoMaxCuota.toString()%>">
																				<td class="c11azul" align="center" nowrap>
																					<bean:write	name="OpcionesCuotaData" property="cuotas"/>
																				</td>
																				<td class="c11azul" align="right" nowrap>
																					<bean:write name="OpcionesCuotaData" property="tasa" formatKey="format.float3" locale="RegSettingSrv"/>
																				</td>
																				<td class="c11azul" align="right" nowrap>
																					<bean:write name="OpcionesCuotaData" property="valorCuota" formatKey="format.money"/>
																				</td>
																				<td class="c11azul" align="center" colspan=2 nowrap="nowrap">
																					 <bean:message key="label.simulacion.credito.resultado.cuotaExcede"/> <%=(int) (Constantes.PORCENTAJE_MAX_DE_CUOTA_SOBRE_RENTA * 100)%>% de su renta 
																				</td>
																			</logic:greaterThan>
		
																			<!-- **** Si es menor que No Excede el % Permitido ****-->
																			<logic:lessEqual name="OpcionesCuotaData"
																			property="valorCuota"
																			value='<%=varMontoMaxCuota.toString()%>'>
																				<td class="c11azul" align="center" nowrap>
																					 <bean:write name="OpcionesCuotaData" property="cuotas"/>
																				</td>
																				<td class="c11azul" align="right" nowrap>
																					 <bean:write name="OpcionesCuotaData" property="tasa" formatKey="format.float3" locale="RegSettingSrv"/>
																				</td>
																				<td class="c11azul" align="right" nowrap>
																					<bean:write name="OpcionesCuotaData" property="valorCuota" formatKey="format.money"/>
																				</td>
																				<td class="c11azul" align="center">
																					 <bean:write name="results" property="fechaPrimerVencimiento" formatKey="format.date.corta"/>
																				</td>
																				<td class="c11azul" align="center">
																					 <bean:write name="OpcionesCuotaData" property="vencimiento" formatKey="format.date.corta"/>
																				</td>
																			</logic:lessEqual>
																		</tr>
																	</logic:lessEqual>
																</logic:iterate>
															</logic:greaterThan>
															<logic:equal name="varCantMaxCuotas" value="0">
																<tr>
																	<td class="c11azul" colspan="5" align="center">
																		<bean:message key="label.simulacion.credito.resultado.sinOpcionesCuotas"/>
																	</td>
																</tr>
															</logic:equal>
														</logic:present>
														<logic:notPresent name="results" property="otrasOpcionesCuotas">
															<tr>
																<td class="c11azul" colspan="5" align="center">
																	<bean:message key="label.simulacion.credito.resultado.sinOpcionesCuotas"/>
																</td>
															</tr>
														</logic:notPresent>
													</table>
												</td>
											</tr>
	
											<!-- Opciones de Endeudamiento -->
											<logic:present name="afiliado">
												<tr>
													<td colspan='2' class="c11azul" align="center">
														<table width="100%" border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td class="c11azul" align="center">
																	<table width="100%" border="1" cellspacing="0" cellpadding="0">
																		<tr>
																			<td colspan="4" class="celeste" align="left">
																				<bean:message key="label.simulacion.credito.resultado.opcionesEndeudamiento"/>
																			</td>
																		</tr>										
																		<tr>
																			<td class="c11azul" colspan="4">
																				<bean:message key="label.simulacion.credito.resultado.endeudamientoMaximo"/>
																			</td>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				&nbsp;
																			</td>
																			<td class="c11azul" align="center">
																				<bean:message key="label.simulacion.credito.resultado.sinAval"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul" align="center">
																					<bean:message key="label.simulacion.credito.resultado.con1Aval"/>
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul" align="center">
																					<bean:message key="label.simulacion.credito.resultado.con2Avales"/>
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.capacidadMaximaEndeudamiento"/>
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="montoEndeudamiento" formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul" align="right">												
																						<bean:write name="results" property="montoEndeudamientoConAval" formatKey="format.money"/>
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul" align="right">	
																					<bean:write name="results" property="montoEndeudamientoConAval2" formatKey="format.money"/>
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.endeudamientoDisponible"/>
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="montoEndeudamientoDisponible" formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul" align="right">
																					<bean:write name="results" property="montoEndeudamientoDisponibleConAval" formatKey="format.money"/>
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul" align="right">
																						<bean:write name="results" property="montoEndeudamientoDisponibleConAval2" formatKey="format.money"/>
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.deudaVigente"/>:
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="sumaMontoCreditoVigente" formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.cuotaMaxima.comprometer"/><br>
																				&nbsp;(<%=(int) (Constantes.PORCENTAJE_MAX_DE_CUOTA_SOBRE_RENTA * 100)%>%
																				<bean:message key="label.simulacion.credito.resultado.cuotaMaxima.remuneracionLiquida"/>)
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="montoMaxCuota" formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.cuota.comprometidaCreditosVigentes"/>
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="sumaCuotaCreditoVigente" 	formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">												
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																		</tr>
																		<tr>
																			<td class="c11azul">
																				<bean:message key="label.simulacion.credito.resultado.cuota.cuotaDisponible"/>
																			</td>
																			<td class="c11azul" align="right">
																				<bean:write name="results" property="cuotaDisponible" formatKey="format.money"/>
																			</td>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																			<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">												
																				<td class="c11azul">
																					&nbsp;
																				</td>
																			</logic:greaterThan>
																		</tr>
																	</table>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</logic:present> 				 
										</table>
									</logic:present>						
									<logic:present name="results">
										<br>
										<table border="0" cellpadding="0" cellspacing="0" width="80%">
											<tr>
												<td class="c11azul">
													<bean:message key="label.simulacion.credito.resultado.pie.pagina"/>
												</td>
											</tr>
										</table>
									</logic:present>									
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>