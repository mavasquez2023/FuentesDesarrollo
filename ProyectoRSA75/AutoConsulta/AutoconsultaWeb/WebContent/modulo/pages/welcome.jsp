<%@ include file = "/modulo/includes/env.jsp"%>
<% showVolverMenu = false; %>
<% title = "Menú Principal"; %>
<%@ include file = "/modulo/includes/header.jsp"%>
<%@ include file = "/modulo/includes/top.jsp"%> 
  
<% 
    boolean hasCertificados=false;    
%>                

<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
<%
	if(opcion.getTipo()==1) hasCertificados=true;
%>
</logic:iterate> 
<br>
<table border="0" cellpadding="0" cellspacing="0" width="80%">

	<tr> 
		<td nowrap align='center' colspan='<%= hasCertificados ? "2" : "1" %>'>
			<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
			<% if(opcion.getTipo()==2) { // Simulador %>
					<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
					<html:img page='<%= "/images/opciones/"+opcion.getClave()+".gif" %>' border="0"/>   
					</html:link>
					<br>
			<%}%>
			</logic:iterate>
			<br><br>
		</td>
	</tr>

	<tr>
		<td nowrap class='textobold' align='center'><font size='3'><bean:message key='opcion.grupo.consultas'/></font><br><br><br></td>
		<% if (hasCertificados) { %><td nowrap class='textobold' align='center'><font size='3'><bean:message key='opcion.grupo.certificados'/></font><br><br><br></td><% } %>
	</tr>
	<tr>
		<td nowrap align="left">  

	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
	<% if(opcion.getTipo()==0) { // Consultas %>
			<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
			<html:img page='<%= "/images/opciones/"+opcion.getClave()+".gif" %>' border="0"/>   
			</html:link>
			<br>
	<%}%>
	</logic:iterate>
	
		</td>

    <% if (hasCertificados) { %>    

		<td nowrap align="left">

	<logic:iterate id="opcion" name="servicios" type="cl.araucana.autoconsulta.common.Servicio">
	<% if(opcion.getTipo()==1) { // Certificados %>
			<html:link styleClass="subopcion" page='<%= opcion.getAction() %>'>
			<html:img page='<%= "/images/opciones/"+opcion.getClave()+".gif" %>' border="0"/>
			</html:link>
			<br>
	<%}%>
	</logic:iterate>
	
		</td>
	<% } %>
	
	</tr>
	
    <% if (session.getAttribute("encargadoEmpresa")==null) { %>
	<tr>
		<td nowrap align='center' colspan='<%= hasCertificados ? "2" : "1" %>'>
			<html:link styleClass="subopcion" page="/changeClave.do?from=opc">
			<html:img page='/images/opciones/cambioClaveSecreta.gif' border="0"/>
			</html:link>
		</td>
	</tr>
	<% } %>	
</table>


<%@ include file = "/modulo/includes/footer.jsp"%>


