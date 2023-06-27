<%@ page import = "java.io.*" %>
 
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Cambio de Clave Personal";
   forceExit = true; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<%  // Captura a que action debe reenviar
	String volverA= (String)session.getAttribute("volverA");
	String from=request.getParameter("from")==null ? "" :request.getParameter("from");

%>

<html:form action='<%= "/" + volverA %>'>
<html:hidden property="paso" value="1"/>

<input type="hidden" name="from" value='<%= from %>'/>
<html:hidden property="user" value='<%= request.getParameter("user") %>'/>

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	
    <tr>
		<td class="texto" align='center'>
			<font size='4'><bean:message key="login.change.clave.new"/></font><br>  
			<html:password property="newClave" readonly="true" styleClass="bigfield" size='15' maxlength='4'/>
			
<%  // Captura el Mensaje de Error
    String message=(String)session.getAttribute("security.message");
%>

<font color=red>
<logic:present name="forzarCambio">
<br><bean:message key="login.change.clave.inicial.text"/><br>
</logic:present>

<% if (message!=null) { %>
<script>
document.<%= !from.equalsIgnoreCase("opc") ? "LoginForm" : "ChangeClaveForm" %>.newClave.value="";
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
			<%	String form = !from.equalsIgnoreCase("opc") ? "LoginForm" : "ChangeClaveForm"; %>
			<%	String field = "newClave"; %>
			<%	String fieldUser = "user"; %>
			<%@ include file = "/modulo/includes/botoneraClave.jsp"%>
		</td>
    </tr>
    
  

</table>
</html:form>




<%@ include file = "/modulo/includes/footer.jsp"%>


