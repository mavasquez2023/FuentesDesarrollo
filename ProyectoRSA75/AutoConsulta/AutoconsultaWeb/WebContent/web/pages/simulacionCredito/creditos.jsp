<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/web/includes/top.jsp"%>
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
<%@ page import="cl.azurian.sce.Constantes" %>
  
<script> 
function seleccion(opcion){  

	document.PARAMForm.campo.value = opcion;
	document.PARAMForm.submit();

}

</script> 
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<!-- menu de opciones --> 
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td><!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>  
<!-- Begin de la pagina particular -->
<table border="0" cellpadding="0" cellspacing="0" width="80%">
<html:form action='/prepareSimulacionCreditoWeb'> 
<html:hidden property="campo"/>
<html:hidden property="ingresosLiquidos"/>
<html:hidden property="montoSolicitado"/>
<html:hidden property="cantidadCuotas"/>
<html:hidden property="fechaNacimiento"/>
<html:hidden property="fechaIngresoEmpresa"/>
<html:hidden property="seguroDesgravamen"/>
<html:hidden property="seguroVida"/> 
<html:hidden property="seguroCesantia"/>
<html:hidden property="rut"/>
<html:hidden property="oficina"/>
<html:hidden property="servicioTeleasistencia"/>
<html:hidden property="tipoRenta"/>
<tr><td >&nbsp;</td></tr>
  <tr>
    <td valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="97%" class="title15">Simulador de Cr&eacute;dito</td>
        </tr>
      </table></td> 
  </tr> 
    <tr>
      <td valign="top"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr valign="top">
            <td width="78%"> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td valign="top" background="images/divider_blue.gif"><img src="images/block.gif" width="3" height="1"></td>
                </tr>
                <tr>
                  <td valign="top"><br> <table width="100%" border="0" cellspacing="0" cellpadding="0">
                      <tr>
                        <td width="3%"><img src="images/arrow2.gif" width="13" height="14"></td>
                        <td width="97%" class="c13az"><b>Cr&eacute;ditos vigentes</b></td>
                      </tr>
                    </table></td>
                </tr>          
	<tr>
		<td class="c11">
			<bean:message key="label.simulacion.credito.credito.texto"/>
			<br>
			<br>
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
		<td align="center">
			<logic:present name="creditoRepactar">
			<!-- Creditos Vigentes -->
			
			<br>
			
			<table border='1' width='100%' cellspacing='0' cellpadding='2'>
				<!-- Encabezados -->			
				<tr>
					<td colspan="7" align="center" class="celeste">
						<bean:message key="label.simulacion.credito.credito.creditosVigentes"/>
					</td>
				</tr>				
				<tr>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.folio"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.vencimiento"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.montoVigente"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.cuota"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.estado"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.montoDescontar"/>
					</td>
					<td class="c11azul" align="center">
						<bean:message key="label.simulacion.credito.credito.descuentaCredito"/>
					</td>
				</tr>
				
				<!-- Datos -->
				
				<logic:present name="creditoRepactar">
					<logic:iterate name="creditoRepactar" id="creditoRepactarData">
						<logic:equal  name="creditoRepactarData" property="tipo"  value="<%=String.valueOf(Constantes.TIPO_DEUDOR)%>"> 
							<logic:lessEqual name="creditoRepactarData" property="estado" value="<%=String.valueOf(Constantes.ESTADO_CREDITO_VIGENTE)%>">					
							<tr valign="middle">
								<td class="c11azul" align="right" nowrap>
									<bean:write name="creditoRepactarData" property="oficina"/>
									-
									<bean:write name="creditoRepactarData" property="folio"/>
								</td>
								<td class="c11azul" align="center" nowrap>
									<bean:write name="creditoRepactarData" property="vencimiento"
									formatKey="format.date.corta"/>
								</td>
								<td class="c11azul" align="right" nowrap>
									<bean:write name="creditoRepactarData" property="montoRepactar"	formatKey="format.money"/>
								</td>
								<td class="c11azul" align="right" nowrap>
									<bean:write name="creditoRepactarData" property="montoCuota" formatKey="format.money"/>
								</td>
								<td class="c11azul" align="center" nowrap>
									<bean:define id="estadoId"
									name="creditoRepactarData" property="estado" type="Integer" /><%=Constantes.DESCRIPCION_ESTADO_VIGENTE%>
								</td>
								<td class="c11azul" align="right" nowrap>
									<bean:write name="creditoRepactarData" property="descuentoSinMora" formatKey="format.money"/>
								</td>
								<td class="c11azul" align="center">
									<html:multibox property="marcado" style="{font-size: 30px;}">
										<bean:write name="creditoRepactarData" property="oficina"/>-<bean:write	name="creditoRepactarData" property="folio"/>
									</html:multibox>
								</td>
							</tr>
							</logic:lessEqual>
						</logic:equal>				
					</logic:iterate>
				</logic:present>
				
				<logic:notPresent name="creditoRepactar">
					<tr valign="middle">
						<td class="c11azul" colspan="8" align="center">
							<b><bean:message key="label.simulacion.credito.credito.sinCreditosVigentes"/></b>
						</td>
					</tr>
				</logic:notPresent>				
			</table>
			</logic:present>
		</td>
	</tr>
	<tr>
		<td align="center">
			<br>
			<br>
			
			<table>
				<tr>
					<!--<td class="texto" colspan='2' align='center'>
						<html:link styleClass="subopcion" href='<%="javascript:seleccion(" + Constants.DATOS_SC_INGRESOS_LIQUIDOS + ")" %>'>
						<html:img page="/images/simulacionCredito/volverIngresoSC.gif" border="0"/>
						</html:link>					
					</td>-->
					<td>
						<input name="volver" type="button" class="boton" id="volver" onClick="javascript:seleccion('<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>');" value="&lt;&lt; Volver a Simular"></td>															
					<td>
						&nbsp;
						&nbsp;
						&nbsp;												
						&nbsp;
						&nbsp;
					</td>	
					<td>
						<input name="continuar" type="button" class="boton" id="continuar" onClick="javascript:seleccion('resultado');" value="Simular &gt;&gt;">
					</td>					
				 <tr>
				
				</tr>	
															
					<!--<td class="texto" colspan='2' align='center'>
						<html:link styleClass="subopcion" href="javascript:seleccion('resultado')">
						<html:img page="/images/simulacionCredito/continuarSC.gif" border="0"/>
						</html:link>					
					</td>-->
				</tr>			
			</table>
			
			<br>
			<br>
		</td>
	</tr>	
</table>

</td>
</tr>
</html:form>
</table>
<br>
<br>
<br>



<br>

<%@ include file = "/web/includes/footer.jsp"%>		
