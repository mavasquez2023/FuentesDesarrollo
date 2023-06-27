<%@ include file = "/modulo/includes/env.jsp"%> 
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>
 

<html:form action='/prepareSimulacionCredito'>

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
			<bean:message key="label.simulacion.credito.errores.solicitar"/>
			<br>
			<br>
		</td>
	</tr>
	<tr>	
		<td align="center">
			<logic:iterate id="error" name="erroresSimulacion">
				<bean:write name="error" property="texto"/>
				<br>
			</logic:iterate>
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
				</tr>			
			</table>
			
			<br>
			<br>
		</td>
	</tr>
</table>
<br>
<br>

</html:form>

<br>
<br>
<br>

</font>
<br>

<%@ include file = "/modulo/includes/footer.jsp"%>		
