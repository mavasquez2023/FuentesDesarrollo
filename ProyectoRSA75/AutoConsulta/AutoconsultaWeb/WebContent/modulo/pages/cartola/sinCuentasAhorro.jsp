<%@ page isErrorPage="true" %>
<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Información"; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<br><br>
<div align='center' class="texto">
<font size='3'>

<logic:notPresent name="noActiva">
	<bean:message key="label.cartola.sin.cuentas"/>
</logic:notPresent>

<logic:present name="noActiva">
	<bean:message key="label.cartola.sin.cuentas.activas"/>
</logic:present>

</font>

<br>
<br>

	<img src='/AutoconsultaWeb/modulo/images/ahorro/comercialAhorro.jpg' width="600" height="450" border="0">

</div>


<%@ include file = "/modulo/includes/footer.jsp"%>



