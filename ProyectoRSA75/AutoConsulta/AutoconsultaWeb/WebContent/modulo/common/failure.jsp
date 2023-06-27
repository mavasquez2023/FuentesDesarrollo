<%@ page isErrorPage="true" %>
<%@ page import = "java.io.*" %> 
 
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% forceExit = true; %> 
<% title = "Hubo un Error"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
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


<br>
<br>
<div class="textobold"><font size='3'><bean:message key="errors.message"/></font></div>
<br>
<div class="texto">
<font color=red size='3'>
<% if (code!=null) { // Caso a través de message-resource %>
<bean:message key='<%= code %>'/>
<% } else if (msg!=null) { // viene el texto de mensaje %>
<%= msg %>
<% } else { // no viene mensaje %>
<bean:message key='errors.system'/>
<% }  %>
</font>
<br>
<font size=2 color='black'><%= info==null && false ? "" : info %></font>

</div>
</div>


<%@ include file = "/modulo/includes/footer.jsp"%>



