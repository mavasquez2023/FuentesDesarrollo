<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/header.jsp"%>
<% title = "Simulación de Crédito"; %>
<%@ include file = "/web/pages/simulacionCreditoMovil/cabeceraSimulacion.jsp"%> 
<%@ page import="cl.araucana.autoconsulta.common.Constants" %>

   
<html:form action='/simuladorCreditoMovil'>

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
		<td class="c13az">
			<bean:message key="label.simulacion.credito.errores.solicitar"/>
			<br>
			<br>
		</td>
	</tr>
	<tr>	
		<td align="center">
			<logic:iterate id="error" name="erroresSimulacion">
				<font color="red"><bean:write name="error" property="texto"/></font>
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

					<td>
			   			<input name="volver" type="button" class="boton" id="volver" onClick="javascript:seleccion('<%=Constants.DATOS_SC_INGRESOS_LIQUIDOS%>');" value="&lt;&lt; Volver a Simular"></td>															
					<td>
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

<br>

<%@ include file = "/web/includes/footer.jsp"%>		
