<%@ include file = "/modulo/includes/env.jsp"%> 
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Simulaci�n de Cr�dito"; %>
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
			SIMULACI�N DE CR�DITO EN MANTENCI�N                    	
			<br />
			FAVOR CONT�CTESE CON NUESTROS EJECUTIVOS AL
			<br />
                        TEL�FONO 600-4228100
                        <br />
                        <br />
                        PARA ACCEDER A OTRO SIMULADOR DE CR�DITO
                        <br />
			QUE TENEMOS A SU DISPOSICI�N
			<br />
			POR FAVOR HACER CLIC <a href="http://portal.laaraucana.cl/wps/wcm/connect/La%20Araucana/araucana/home/simulador/">AQU�</a>.
			<br />
			TENGA EN CUENTA QUE AL SER UN SIMULADOR
			<br />
			LOS VALORES SER�N REFERENCIALES
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
