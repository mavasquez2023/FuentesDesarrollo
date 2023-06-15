<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/web/includes/top.jsp"%>
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
  
<html:form action='/prepareSimulacionCreditoWeb'>

<html:hidden property="campo"/>
<html:hidden property="ingresosLiquidos"/>
<html:hidden property="montoSolicitado"/>
<html:hidden property="cantidadCuotas"/>
<html:hidden property="fechaNacimiento"/>
<html:hidden property="fechaIngresoEmpresa"/>
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
		<td class="certificado">
			<bean:message key="label.simulacion.credito.ingreso.seguro.texto"/>:
			<br>
			<br>
		</td>
	</tr>
	<tr>
		<td align="center">
			<table border="0" cellpadding="0" cellspacing="0" width="60%">
				<tr>
					<td class="certificado">
						<html:img page="/images/simulacionCredito/seguroDesgravamen.gif" border="0"/>
					</td>
					<td class="certificado">
						<html:img page="/images/simulacionCredito/opcionSi.gif" border="0"/>
						<html:radio property="seguroDesgravamen" value="1"/>
					</td>
					<td class="certificado">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>													
					<td class="certificado">
						<html:img page="/images/simulacionCredito/opcionNo.gif" border="0"/>
						<html:radio property="seguroDesgravamen" value="0"/>
					</td>
				</tr>
				<logic:present name="seguroDeVida">
					<tr>
						<td class="certificado" colspan='4'>
							<br>
						</td>
					</tr>
					<tr>							
						<td class="certificado">
							<html:img page="/images/simulacionCredito/seguroVida.gif" border="0"/>
						</td>
						<td class="certificado">
							<html:img page="/images/simulacionCredito/opcionSi.gif" border="0"/>
							<html:radio property="seguroVida" value="1"/>
						</td>
						<td class="certificado">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>													
						<td class="certificado">
							<html:img page="/images/simulacionCredito/opcionNo.gif" border="0"/>
							<html:radio property="seguroVida" value="0"/>
						</td>					
					</tr>
				</logic:present>
				<logic:present name="seguroDeCesantia">
					<tr>
						<td class="certificado" colspan='4'>
							<br>
						</td>
					</tr>				
					<tr>
						<td class="certificado">
							<html:img page="/images/simulacionCredito/seguroCesantia.gif" border="0"/>
						</td>
						<td class="certificado">
							<html:img page="/images/simulacionCredito/opcionSi.gif" border="0"/>
							<html:radio property="seguroCesantia" value="1"/>
						</td>
						<td class="certificado">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						</td>						
						<td class="certificado">
							<html:img page="/images/simulacionCredito/opcionNo.gif" border="0"/>
							<html:radio property="seguroCesantia" value="0"/>
						</td>						
					</tr>
				</logic:present>				
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<br>
			<br>
			
			<table>
				<tr>
					<td class="texto" colspan='2' align='center'>
						<html:link styleClass="subopcion" href='<%="javascript:seleccion(" + Constants.DATOS_SC_INGRESOS_LIQUIDOS + ")" %>'>
						<html:img page="/images/simulacionCredito/volverIngresoSC.gif" border="0"/>
						</html:link>					
					</td>
					<td>
						&nbsp;
						&nbsp;
						&nbsp;												
						&nbsp;
						&nbsp;						
					</td>					
					<td class="texto" colspan='2' align='center'>
						<html:link styleClass="subopcion" href="javascript:seleccion('creditos')">
						<html:img page="/images/simulacionCredito/continuarSC.gif" border="0"/>
						</html:link>					
					</td>
				</tr>			
			</table>
			
			<br>
			<br>
		</td>
	</tr>
</table>

<br>
<br>
<br>

</font>
</html:form>
<br>

<%@ include file = "/web/includes/footer.jsp"%>
