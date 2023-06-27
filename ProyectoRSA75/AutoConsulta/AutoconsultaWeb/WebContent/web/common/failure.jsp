<%@ page isErrorPage="true" %>
<%@ page import = "java.io.*" %> 
    
<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%" >
<tr>
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>

<!-- Begin de la pagina particular -->
<%  // Captura el Mensaje de Error
    String code=(String)request.getAttribute("error.code");
    String msg=(String)request.getAttribute("error.message");
    String info=(String)request.getAttribute("error.info");
    String details=(String)request.getAttribute("error.details");

    // Por prioridad: Exception JSP
    if (exception!=null) { 
	code=null;
	msg=exception.getMessage(); 

	// Recupera Details
        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter( sw, true );
        exception.printStackTrace( writer );
        details = sw.toString();
    }

    // Para ventana de Detalles Error
    request.getSession(true).setAttribute("error.details",details);
%>

<div class="titulo">
<bean:message key="errors.title"/>
</div>
<div align='center'>
<br>
<div class="textobold"><bean:message key="errors.message"/></div>
<br>
<div class="texto">
<font color=red>
<% if (code!=null) { // Caso a través de message-resource %>
<bean:message key='<%= code %>'/>
<% } else if (msg!=null) { // viene el texto de mensaje %>
<%= msg %>
<% } else { // no viene mensaje %>
<bean:message key='errors.system'/>
<% }  %>
</font>
<br>
<font size=1 color='black'><%= info==null ? "" : info %></font>

</div>
</div>

<script>

<!-- Ventana de detalles Tecnicos -->
function showErrorDetail(){
  window.open('<%= "/"+fullappname+"/ErrorDetails.do" %>','errorDetail','resizable=yes,status=yes,toolbar=no,location=no,menubar=no,scrollbars=yes,height=300,width=500');
}
</script>

<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/web/includes/footer.jsp"%>



