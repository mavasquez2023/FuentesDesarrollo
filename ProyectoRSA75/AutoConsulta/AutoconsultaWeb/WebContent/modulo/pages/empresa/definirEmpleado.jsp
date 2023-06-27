<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Definir Empleado"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
 
 
<%  // Captura a que action debe reenviar
	String volverA= (String)session.getAttribute("volverA");
   // Captura el Mensaje de Error 
    String message=(String)session.getAttribute("validation.message");
    session.removeAttribute("validation.message");
 %>

<html:form action='<%= "/" + volverA %>'>
<html:hidden property="validar" value="true"/>

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td class="textobold" align='center' colspan='2'>
			<font size='3'><bean:message key="empleado.text"/></font> <br>
		</td>
    </tr>	
    <tr>
		<td class="texto" align='center'>
			<font size='4'><bean:message key="empleado.rut"/></font><br>  
			<html:text property="rut" readonly="true" styleClass="bigfield" size='11' maxlength='9'/>



<font color=red>
<% if (message!=null) { %>
<script>
document.LoginForm.user.value="";
</script>
<br><font size='3'><bean:message key='<%= message %>'/></font>
<% } %>
<div align='left'>
<html:errors/>
</div>
</font>

		</td>
		<td class="texto" align='center'>
			<br>
			<%	String form = "PARAMForm"; %>
			<%	String field = "rut"; %>
			<%@ include file = "/modulo/includes/botoneraRut.jsp"%>
		</td>
    </tr>




</table>

</html:form>

<%@ include file = "/modulo/includes/footer.jsp"%>


