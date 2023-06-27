<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>

<% 
cl.araucana.common.ui.UserInformation userinformation = 
  new cl.araucana.common.ui.UserInformation("15314239", "8123");
session.setAttribute("application.userinformation", userinformation);
%>
<% 
   request.getRequestDispatcher( "/manageEncargados.do" ).forward( request, response );
%>