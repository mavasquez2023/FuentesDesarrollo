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
%>
<br><br>
<div class="titulo">
<%=request.getAttribute("error.code") %>: <%=request.getAttribute("error.message") %> 
</div>
<div align='center'>
<br>
<div class="textobold"><%=request.getAttribute("error.info") %></div>
<br>
<div class="texto">
<br>

</div>
</div>
<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>