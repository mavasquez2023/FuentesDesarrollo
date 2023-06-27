<%@ include file = "/modulo/includes/env.jsp"%> 
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
 
<html:form action='/prepareSimulacionCredito'>
<html:hidden property="campo"/>
<html:hidden property="campoAnterior"/>
<html:hidden property="rut"/>

<script>
function seleccion(opcion){

	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();

}
</script> 


<font class="certificado">
<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td align="center">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td>
						<!-- Datos que se deben ingresar -->
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td colspan='3'>
									<%  // Captura el Mensaje de Error
									    String message=(String)session.getAttribute("validation.message");
									%>
									<div align='center'>
									<br>
									<div class="textobold"><font color=red>
									<% if (message!=null) { %>
									<bean:message key='<%= message %>'/>
									<% } %>
									<html:errors/>
								</td>
							</tr>
							<tr>
								<td colspan='3'>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td>
									<logic:present name="afiliado">
										<html:link styleClass="subopcion" href='<%="javascript:seleccion(" + Constants.DATOS_SC_INGRESOS_LIQUIDOS + ")" %>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
											<html:img page="/images/simulacionCredito/remuneracionLiquidaSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
											<html:img page="/images/simulacionCredito/remuneracionLiquida.gif" border="0"/>
										</logic:notEqual>
										</html:link>
									</logic:present>
									<logic:notPresent name="afiliado">
										<html:link styleClass="subopcion" href='<%="javascript:seleccion("+Constants.DATOS_SC_INGRESOS_LIQUIDOS+")"%>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
											<html:img page="/images/simulacionCredito/pensionLiquidaSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
											<html:img page="/images/simulacionCredito/pensionLiquida.gif" border="0"/>
										</logic:notEqual>
										</html:link>
									</logic:notPresent>																									
								</td>
								<logic:equal name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
									<td align="right">
										<html:text tabindex="0" onfocus="true" property="ingresosLiquidos" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_INGRESOS_LIQUIDOS+")"%>' />
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>								
								</logic:equal>
								<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>'>
									<td class="certificado" align="right" nowrap>
										<logic:notEqual name="PARAMForm" property="ingresosLiquidos" value="">
											<html:hidden property="ingresosLiquidos"/>
											<bean:write name="PARAMForm" property="ingresosLiquidos" formatKey="format.money"/>
										</logic:notEqual>
										<logic:equal name="PARAMForm" property="ingresosLiquidos" value="">
											<html:text property="ingresosLiquidos" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_INGRESOS_LIQUIDOS+")"%>' />
										</logic:equal>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</logic:notEqual>
							</tr>
							<tr>
								<td>
									<html:link styleClass="subopcion" href='<%="javascript:seleccion("+Constants.DATOS_SC_MONTO_SOLICITADO+")"%>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_MONTO_SOLICITADO%>'>
											<html:img page="/images/simulacionCredito/montoSolicitadoSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_MONTO_SOLICITADO%>'>
											<html:img page="/images/simulacionCredito/montoSolicitado.gif" border="0"/>
										</logic:notEqual>
									</html:link>
								</td>
								<logic:equal name="columna" value='<%=Constants.DATOS_SC_MONTO_SOLICITADO%>'>
									<td align="right">
										<html:text property="montoSolicitado" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_MONTO_SOLICITADO+")"%>' />
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>								
								</logic:equal>
								<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_MONTO_SOLICITADO%>'>
									<td class="certificado" align="right" nowrap>
										<logic:notEqual name="PARAMForm" property="montoSolicitado" value="">
											<html:hidden property="montoSolicitado"/>
											<bean:write name="PARAMForm" property="montoSolicitado" formatKey="format.money"/>
										</logic:notEqual>
										<logic:equal name="PARAMForm" property="montoSolicitado" value="">
											<html:text property="montoSolicitado" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_MONTO_SOLICITADO+")"%>' />
										</logic:equal>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</logic:notEqual>								
							</tr>				
							<tr>
								<td>
									<html:link styleClass="subopcion" href='<%="javascript:seleccion("+Constants.DATOS_SC_CANTIDAD_CUOTAS+")"%>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_CANTIDAD_CUOTAS%>'>
											<html:img page="/images/simulacionCredito/numeroCuotasSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_CANTIDAD_CUOTAS%>'>
											<html:img page="/images/simulacionCredito/numeroCuotas.gif" border="0"/>
										</logic:notEqual>
									</html:link>
								</td>
								<logic:equal name="columna" value='<%=Constants.DATOS_SC_CANTIDAD_CUOTAS%>'>
									<td align="right">
										<html:text property="cantidadCuotas" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_CANTIDAD_CUOTAS+")"%>' />
									</td>	
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>							
								</logic:equal>
								<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_CANTIDAD_CUOTAS%>'>
									<td class="certificado" align="right" nowrap>
										<logic:notEqual name="PARAMForm" property="cantidadCuotas" value="">
											<html:hidden property="cantidadCuotas"/>
											<bean:write name="PARAMForm" property="cantidadCuotas" formatKey="format.int"/>
										</logic:notEqual>
										<logic:equal name="PARAMForm" property="cantidadCuotas" value="">
											<html:text property="cantidadCuotas" readonly="true" size='10' maxlength="9" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_CANTIDAD_CUOTAS+")"%>' />
										</logic:equal>
									</td>
									<td>
										&nbsp;
									</td>
									<td>
										&nbsp;
									</td>
								</logic:notEqual>
							</tr>
							<tr>
								<td>
									<html:link styleClass="subopcion" href='<%="javascript:seleccion("+Constants.DATOS_SC_FECHA_NACIMIENTO+")"%>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_FECHA_NACIMIENTO%>'>
											<html:img page="/images/simulacionCredito/fechaNacimientoSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_FECHA_NACIMIENTO%>'>
											<html:img page="/images/simulacionCredito/fechaNacimiento.gif" border="0"/>
										</logic:notEqual>
									</html:link>
								</td>
								<logic:equal name="columna" value='<%=Constants.DATOS_SC_FECHA_NACIMIENTO%>'>
									<td align="right">
										<html:text property="fechaNacimiento" readonly="true" size='10' maxlength="8" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_FECHA_NACIMIENTO+")"%>' />
									</td>
									<td>
										&nbsp;
									</td>	
									<td align="right" class="certificado">
										<div class="textobold"><font color=red>
										<bean:message key="label.simulacion.credito.ingreso.formatofecha"/>
									</td>							
								</logic:equal>
								<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_FECHA_NACIMIENTO%>'>
									<td class="certificado" align="right" nowrap>
									<logic:notEqual name="PARAMForm" property="fechaNacimiento" value="">
										<html:hidden property="fechaNacimiento"/>
										<bean:define id="fecNac" name="PARAMForm" property="fechaNacimiento" type='java.lang.String'/>
										<%= fecNac.substring(0,2) + "/" + fecNac.substring(2,4) + "/" + fecNac.substring(4,8)%>
									</logic:notEqual>
									<logic:equal name="PARAMForm" property="fechaNacimiento" value="">
										<html:text property="fechaNacimiento" readonly="true" size='10' maxlength="8" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_FECHA_NACIMIENTO+")"%>' />
									</logic:equal>
									</td>
									<td>
										&nbsp;
									</td>
									<td align="right" class="certificado">
										<div class="textobold"><font color=red>
										<bean:message key="label.simulacion.credito.ingreso.formatofecha"/>
									</td>
								</logic:notEqual>
							</tr>
							<logic:present name="afiliado">
							<tr>
								<td>
									<html:link styleClass="subopcion" href='<%="javascript:seleccion("+Constants.DATOS_SC_FECHA_INGRESO_EMPRESA+")"%>'>
										<logic:equal name="columna" value='<%=Constants.DATOS_SC_FECHA_INGRESO_EMPRESA%>'>
											<html:img page="/images/simulacionCredito/fechaIngresoEmpresaSelected.gif" border="0"/>
										</logic:equal>
										<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_FECHA_INGRESO_EMPRESA%>'>
											<html:img page="/images/simulacionCredito/fechaIngresoEmpresa.gif" border="0"/>
										</logic:notEqual>
									</html:link>
								</td>
								<logic:equal name="columna" value='<%=Constants.DATOS_SC_FECHA_INGRESO_EMPRESA%>'>
									<td align="right">
										<html:text property="fechaIngresoEmpresa" readonly="true" size='10' maxlength="8"/>
									</td>
									<td>
										&nbsp;
									</td>	
									<td align="right" class="certificado">
										<div class="textobold"><font color=red>
										<bean:message key="label.simulacion.credito.ingreso.formatofecha"/>
									</td>							
								</logic:equal>
								<logic:notEqual name="columna" value='<%=Constants.DATOS_SC_FECHA_INGRESO_EMPRESA%>'>
									<td class="certificado" align="right" nowrap>
										<logic:notEqual name="PARAMForm" property="fechaIngresoEmpresa" value="">
											<html:hidden property="fechaIngresoEmpresa"/>
											<bean:define id="fecngEmp" name="PARAMForm" property="fechaIngresoEmpresa" type='java.lang.String'/>
											<%= fecngEmp.substring(0,2) + "/" + fecngEmp.substring(2,4) + "/" + fecngEmp.substring(4,8)%>
										</logic:notEqual>
										<logic:equal name="PARAMForm" property="fechaIngresoEmpresa" value="">
											<html:text property="fechaIngresoEmpresa" readonly="true" size='10' maxlength="8" onclick='<%="javascript:seleccion("+Constants.DATOS_SC_FECHA_INGRESO_EMPRESA+")"%>' />
										</logic:equal>
									</td>
									<td>
										&nbsp;
									</td>
									<td align="right" class="certificado">
										<div class="textobold"><font color=red>
										<bean:message key="label.simulacion.credito.ingreso.formatofecha"/>
									</td>
								</logic:notEqual>
							</tr>
							</logic:present>
						</table>
					</td>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<tr>
								<td class="texto" align='right'>
									<%@ include file = "/modulo/includes/botoneraSimulacion.jsp"%>								
								</td>
							</tr>
						</table>						
					</td>
				</tr>
			</table>				
			
			<br>

			<logic:present name="afiliado">			
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td class="certificado">
							<bean:message key="label.simulacion.credito.ingreso.avisoIngresos.afiliado"/>
					</td>
				</tr>
			</table>
			</logic:present>
			
			<table>
				<tr>
					<td class="texto" colspan='2' align='center'>
						<html:link styleClass="subopcion" href="javascript:seleccion('seguros')">
						<html:img page="/images/simulacionCredito/continuarSC.gif" border="0"/>
						</html:link>					
					</td>
				</tr>			
			</table>
		</td>
	</tr>
</table>
<br>
<br>
<br>

</font>
</html:form>
<br>

<%@ include file = "/modulo/includes/footer.jsp"%>
