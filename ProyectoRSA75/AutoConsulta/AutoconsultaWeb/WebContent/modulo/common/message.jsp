<%@ page isErrorPage="true" %>
<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Información"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
<%  // Captura el Mensaje
    String msg=(String)request.getAttribute("message");
    String info=(String)request.getAttribute("info");
%>

<br><br>
<div align='center' class="texto">
<br>
<font color=red size='3'><%= msg==null ? "" : msg %></font>
<br>
<font size=2 color='black'><%= info==null ? "" : info %></font>
</div>



<%@ include file = "/modulo/includes/footer.jsp"%>