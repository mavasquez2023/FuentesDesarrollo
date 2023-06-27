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
		<td align="center" class="headerGrilla">
			SIMULACIÓN DE CRÉDITO EN MANTENCIÓN                    	
			<br />
			FAVOR CONTÁCTESE CON NUESTROS EJECUTIVOS AL
			<br />
                        TELÉFONO 600-4228100
                        <br />
                        <br />
                        PARA ACCEDER A OTRO SIMULADOR DE CRÉDITO
                        <br />
			QUE TENEMOS A SU DISPOSICIÓN
			<br />
			POR FAVOR HACER CLIC <a href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/home/simulador/">AQUÍ</a>.
			<br />
			TENGA EN CUENTA QUE AL SER UN SIMULADOR
			<br />
			LOS VALORES SERÁN REFERENCIALES
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
