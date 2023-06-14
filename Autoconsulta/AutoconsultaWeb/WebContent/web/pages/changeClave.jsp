<%@ include file = "/web/includes/env.jsp" %>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
   
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<logic:notPresent name="forzarCambio">
<td width='160' valign='top' bgcolor="#003360"><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
</logic:notPresent>
<td width='1%'>&nbsp;</td>
<td valign='top'>  
 
<!-- Begin de la pagina particular -->

<%  
	// Captura el Mensaje de Error
    String message=(String)session.getAttribute("security.message");
    System.out.println("  Mensaje de Error (message) --> " + message);
    // Captura a que action debe reenviar
	String volverA= (String)session.getAttribute("volverA");
	System.out.println("  action debe reenviar (volverA) --> " + volverA);

	String accion= (String)session.getAttribute("destino");
	System.out.println("  action debe reenviar (accion) --> " + accion); 
	
%>
  
<div align='center'>
<br>
<div class="textobold"><font color=red>
<% if (message!=null) { %>
<bean:message key='<%= message %>'/>
<% } %>
</font></div>
<br>
<div class="texto">
<br>
<logic:notPresent name="forzarCambio">
     <div class="textobold"><bean:message key="login.change.clave.text"/></div>
</logic:notPresent>
<logic:present name="forzarCambio">
   	<div class="textobold"><bean:message key="login.change.clave.inicial.text"/></div>
</logic:present>
<br>
<html:form action='<%= "/" + volverA %>'>
<input type=hidden name="accion" value="<%=accion%>" />
<table border="0" cellpadding="2" cellspacing="2" width="50%">
	<tr>
		<td class="textobold"><bean:message key="login.rut"/>:</td>
		<td class="texto"><bean:write name="datosUsuario" property="fullRut"/></td>
	</tr>
	<tr>
		<td class="textobold"><bean:message key="login.change.clave.new"/>:</td>
		<td class="texto"><html:password property="newClave" styleClass="field" size='4' maxlength='4'/></td>
	</tr>
	<tr>
		<td class="textobold"><bean:message key="login.change.clave.reNew"/>:</td>
		<td class="texto"><html:password property="reNewClave" styleClass="field" size='4' maxlength='4'/></td>
	</tr>
	<tr>
		<td class="texto" colspan='2' align='center'>
			<html:image page='/images/aceptar.gif'/>
		</td>
	</tr> 
</table>
</html:form>

</div>
</div>


<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>