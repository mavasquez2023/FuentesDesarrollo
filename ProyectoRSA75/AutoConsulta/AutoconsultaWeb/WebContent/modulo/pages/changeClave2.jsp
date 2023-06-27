<%@ page import = "java.io.*" %>
 
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Cambio de Clave Personal";
   forceExit = true; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<%  // Captura a que action debe reenviar
	String volverA= (String)session.getAttribute("volverA");
	String from=request.getParameter("from")==null ? "" :request.getParameter("from");
	
	String accion= (String)session.getAttribute("destino");
	System.out.println("  action debe reenviar (accion) --> " + accion);
	
%>

<html:form action='<%= "/" + volverA %>'>
<html:hidden property="paso" value="2"/>
<html:hidden property="user" value='<%= request.getParameter("user") %>'/>
<html:hidden property="newClave" value='<%= request.getParameter("newClave") %>'/>
<input type="hidden" name="from" value='<%= from %>'/>
<input type=hidden name="accion" value="<%=accion%>" />

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	
    <tr>
		<td class="texto" align='center'>
			<font size='4'><bean:message key="login.change.clave.reNew"/></font><br>  
			<html:password property="reNewClave" readonly="true" styleClass="bigfield" size='15' maxlength='4'/>

		
<%  // Captura el Mensaje de Error
    String message=(String)session.getAttribute("security.message");
%>

<font color=red>

<% if (message!=null) { %>
<script>
document.<%= !from.equalsIgnoreCase("opc") ? "LoginForm" : "ChangeClaveForm" %>.reNewClave.value="";
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
			<%	String field = "reNewClave"; %>
			<%	String fieldUser = "user"; %>
			<%@ include file = "/modulo/includes/botoneraClave.jsp"%>
		</td>
    </tr>
    
  

</table>
</html:form>




<%@ include file = "/modulo/includes/footer.jsp"%>


