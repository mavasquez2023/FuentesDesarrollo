<%@ page isErrorPage="true" %> 
<%@ page import = "java.io.*" %>
    
<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td width='160' valign='top' ><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>

<!-- Begin de la pagina particular -->

<%  // Captura el Mensaje
    String msg=(String)request.getAttribute("message");
    String info=(String)request.getAttribute("info");
%>

<div class="titulo">
<bean:message key="message.title"/>  
</div>
<div align='center' class="texto">
<br>
<font color=red><%= msg==null ? "" : msg %></font>
<br>
<font size=2 color='black'><%= info==null ? "" : info %></font>
</div>
<!-- End de la pagina particular -->

</td>
</tr>
</table>


<%@ include file = "/web/includes/footer.jsp"%>