<%@ page import="cl.azurian.sce.Constantes" %> 
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
<%@ page import="cl.azurian.sce.dto.servicios.SubCrc414"%>
<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO"%>

<script>
function seleccion(opcion){

	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();

}
   
</script> 

<font class="certificado">
<table border="0" cellpadding="0" cellspacing="0">

	<tr>
		<td align="left">
			<logic:present name="results">			
			<!-- Resultado Simulación -->
<!-- Identificacion de quien esta simulando-->
<table>
<tr valign="top">
<td colspan='2'align="left">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td align="left">
<table width="100%" border="1" cellspacing="0" cellpadding="0">
<tr>
<td colspan="4" class="headerGrilla" align="left">
<b><bean:message key="label.simulacion.credito.identificacion"/></b>
</td>
</tr>	
<tr  class="certificado" >
<td nowrap>
<bean:message key="label.simulacion.credito.identificacion.nombre"/>
</td>
<td align="left" nowrap>
<bean:write name="nombre"/>
</td>
</tr>
<tr  class="certificado" >
<td nowrap>
<bean:message key="label.simulacion.credito.identificacion.rut"/>
</td>
<td align="left" nowrap>
<bean:write name="rut"/>
</td>
</tr>
<tr class="certificado">
<td nowrap>
<bean:message key="label.simulacion.credito.identificacion.tipo"/>
</td>
<td align="left" nowrap>
<%
cl.araucana.autoconsulta.vo.UsuarioVO usuario = (cl.araucana.autoconsulta.vo.UsuarioVO)session.getAttribute("datosUsuario");
String tipoAfiliado ="";
if(usuario.isEsPensionado())
tipoAfiliado="Pensionado";
else
tipoAfiliado="Afiliado";
%>
<%=tipoAfiliado%>
</td>
</tr>
<%if(session.getAttribute("afiliado")!=null &&
session.getAttribute("afiliado").equals("yes")){%>
<tr  class="certificado">
<td nowrap>
<bean:message key="label.simulacion.credito.identificacion.rutempresa"/>
</td>
<td align="left" nowrap>
<%
String fullRutEmpresa= ""+((SubCrc414)session.getAttribute("perfil")).getRutEmpresa() +
"-"+
 ((SubCrc414)session.getAttribute("perfil")).getDvEmpresa();
%>
<%=fullRutEmpresa%>
</td>
</tr>
<tr class="certificado">
<td nowrap>
<bean:message key="label.simulacion.credito.identificacion.nombreempresa"/>
</td>
<td align="left" nowrap>
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
			
			<table>
				<!-- Modificacion realizada para mensaje de Deuda InterCaja -->
    			<!-- 29/09/2009 Neoris Argentina -->
    			<logic:present name="montoDeudaIntercaja">
        			<tr>
        				<td class="certificado" nowrap="nowrap">
        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.1"/>
        					<bean:write name="cajaDeudaIntercaja"/>
        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.2"/>
        					<bean:write name="montoDeudaIntercaja"/>
        					<bean:message key="label.simulacion.credito.mensaje.deudaIntercaja.3"/>
        				</td>
        			</tr>
    			</logic:present>
    			<!-- Fin Modificacion -->
				<tr valign="top">
					<td align="center">
						<table width="100%" border="1" cellspacing="0" cellpadding="0">
							<logic:present name="results">
								<tr>
									<td colspan="2" class="headerGrilla" align="left">
										<b><bean:message key="label.simulacion.credito.resultado"/></b>
									</td>								
								</tr>							
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.montoOtorgado"/>	
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="montoOtorgado" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.valorCuota"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="valorCuota" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.numeroCuotas"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write	name="results" property="numeroCuotas" />
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.tasa"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="tasa" formatKey="format.float3"/> %
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.descuentoCreditosAnteriores"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="descuentoCreditosAnteriores" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.liquidoPago"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<logic:greaterEqual name="results" property="liquidoPago" value="0">
											<bean:write name="results" property="liquidoPago" formatKey="format.money"/>
										</logic:greaterEqual>
										<logic:lessThan name="results" property="liquidoPago" value="0">
											<bean:message key="label.simulacion.credito.resultado.montoSolicitadoMenorSaldoAdeudado"/>
										</logic:lessThan>
									</td>
								</tr>
								<tr>
									<td class="headerGrilla" colspan="2" nowrap align="left">
										<b><bean:message key="label.simulacion.credito.resultado.detalleMontoOtorgado"/></b>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.montoSolicitado"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="montoSolicitado" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.impuesto"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="impuestosLTE" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.seguroDesgravamen"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="seguroDesgravamen" formatKey="format.money"/>
									</td>
								</tr>
								<tr>
									<td class="certificado" nowrap>
										<bean:message key="label.simulacion.credito.resultado.seguroVida"/>
									</td>
									<td class="certificado" align="right" nowrap>
										<bean:write name="results" property="seguroVida" formatKey="format.money"/>
									</td>
								</tr>
								<logic:present name="afiliado">
									<tr>
										<td class="certificado" nowrap>
											<bean:message key="label.simulacion.credito.resultado.seguroCesantia"/>
										</td>
										<td class="certificado" align="right" nowrap>
											<bean:write name="results" property="seguroCesantia" formatKey="format.money"/>
										</td>
									</tr>
								</logic:present>
							</logic:present>
						</table>
						</td>
						<td class="certificado" align="center">
						<table border="1" cellspacing="0" cellpadding="0">
							<tr>
								<td colspan="5" class="headerGrilla" align="left">
									<b><bean:message key="label.simulacion.credito.resultado.otrasOpciones"/></b>
								</td>							
							</tr>						
							<tr>
								<td class="certificado" align="center" nowrap>
									<bean:message key="label.simulacion.credito.resultado.cuota"/>
								</td>
								<td class="certificado" align="center" nowrap>
									<bean:message key="label.simulacion.credito.resultado.tasa"/>
								</td>
								<td class="certificado" align="center">
									<bean:message key="label.simulacion.credito.resultado.valorCuota"/>
								</td>
								<td class="certificado" align="center" nowrap>
									<bean:message key="label.simulacion.credito.resultado.inicio"/>
								</td>
								<td class="certificado" align="center" nowrap>
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
											<td class="certificado" align="center" nowrap>
												<bean:write	name="OpcionesCuotaData" property="cuotas"/>
											</td>
											<td class="certificado" align="right" nowrap>
												<bean:write name="OpcionesCuotaData" property="tasa" formatKey="format.float3"/>
											</td>
											<td class="certificado" align="right" nowrap>
												<bean:write name="OpcionesCuotaData" property="valorCuota" formatKey="format.money"/>
											</td>
											<td class="certificado" align="center" colspan=2 nowrap="nowrap">
												 <bean:message key="label.simulacion.credito.resultado.cuotaExcede"/> <%=(int) (Constantes.PORCENTAJE_MAX_DE_CUOTA_SOBRE_RENTA * 100)%>% de su renta 
											</td>
										</logic:greaterThan>
	
										<!-- **** Si es menor que No Excede el % Permitido ****-->
										<logic:lessEqual name="OpcionesCuotaData"
											property="valorCuota"
											value='<%=varMontoMaxCuota.toString()%>'>
											<td class="certificado" align="center" nowrap>
												 <bean:write name="OpcionesCuotaData" property="cuotas"/>
											</td>
											<td class="certificado" align="right" nowrap>
												 <bean:write name="OpcionesCuotaData" property="tasa" formatKey="format.float3"/>
											</td>
											<td class="certificado" align="right" nowrap>
												 <bean:write name="OpcionesCuotaData" property="valorCuota" formatKey="format.money"/>
											</td>
											<td class="certificado" align="center">
												 <bean:write name="results" property="fechaPrimerVencimiento" formatKey="format.date.corta"/>
											</td>
											<td class="certificado" align="center">
												 <bean:write name="OpcionesCuotaData" property="vencimiento" formatKey="format.date.corta"/>
											</td>
										</logic:lessEqual>
									</tr>
									</logic:lessEqual>
								</logic:iterate>
								</logic:greaterThan>
								<logic:equal name="varCantMaxCuotas" value="0">
									<tr>
										<td class="certificado" colspan="5" align="center">
											<bean:message key="label.simulacion.credito.resultado.sinOpcionesCuotas"/>
										</td>
									</tr>
								</logic:equal>
							</logic:present>
							<logic:notPresent name="results" property="otrasOpcionesCuotas">
								<tr>
									<td class="certificado" colspan="5" align="center">
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
						<td colspan='2' class="certificado" align="center">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td class="certificado" align="center">
										<table width="100%" border="1" cellspacing="0" cellpadding="0">
											<tr>
												<td colspan="4" class="headerGrilla" align="left">
													<b><bean:message key="label.simulacion.credito.resultado.opcionesEndeudamiento"/></b>
												</td>
											</tr>										
											<tr>
												<td class="certificado" colspan="4">
													<bean:message key="label.simulacion.credito.resultado.endeudamientoMaximo"/>
												</td>
											</tr>
											<tr>
												<td class="certificado">
													&nbsp;
												</td>
												<td class="certificado" align="center">
													<bean:message key="label.simulacion.credito.resultado.sinAval"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado" align="center">
														<bean:message key="label.simulacion.credito.resultado.con1Aval"/>
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado" align="center">
														<bean:message key="label.simulacion.credito.resultado.con2Avales"/>
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.capacidadMaximaEndeudamiento"/>
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="montoEndeudamiento" formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado" align="right">												
															<bean:write name="results" property="montoEndeudamientoConAval" formatKey="format.money"/>
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado" align="right">	
														<bean:write name="results" property="montoEndeudamientoConAval2" formatKey="format.money"/>
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.endeudamientoDisponible"/>
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="montoEndeudamientoDisponible" formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado" align="right">
															<bean:write name="results" property="montoEndeudamientoDisponibleConAval" formatKey="format.money"/>
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado" align="right">
															<bean:write name="results" property="montoEndeudamientoDisponibleConAval2" formatKey="format.money"/>
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.deudaVigente"/>:
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="sumaMontoCreditoVigente" formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.cuotaMaxima.comprometer"/><br>
													&nbsp;(<%=(int) (Constantes.PORCENTAJE_MAX_DE_CUOTA_SOBRE_RENTA * 100)%>%
													<bean:message key="label.simulacion.credito.resultado.cuotaMaxima.remuneracionLiquida"/>)
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="montoMaxCuota" formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.cuota.comprometidaCreditosVigentes"/>
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="sumaCuotaCreditoVigente" 	formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">												
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
											</tr>
											<tr>
												<td class="certificado">
													<bean:message key="label.simulacion.credito.resultado.cuota.cuotaDisponible"/>
												</td>
												<td class="certificado" align="right">
													<bean:write name="results" property="cuotaDisponible" formatKey="format.money"/>
												</td>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval" value="0">
													<td class="certificado">
														&nbsp;
													</td>
												</logic:greaterThan>
												<logic:greaterThan name="results" property="montoEndeudamientoConAval2" value="0">												
													<td class="certificado">
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
						<td class="certificado">
							<bean:message key="label.simulacion.credito.resultado.pie.pagina"/>
						</td>
					</tr>
				</table>
			</logic:present>
			
		</td>
	</tr>
</table>


</font>

<br>
