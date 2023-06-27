<%@ include file = "/tipoMedio.jsp"%>
<%@page import="cl.araucana.core.util.http.Utils" %>
<%
    if (session.getAttribute("internal.huella")==null) {
	   response.sendRedirect(Utils.getPageURL(request, "/modulo2/Welcome.do"));
	} else {
	   System.out.println("user [" +(String)request.getAttribute("user") + "]");
	   session.setAttribute("user",(String)request.getAttribute("user"));
       response.sendRedirect(Utils.getPageURL(request, "/modulo2/loginHuella.do"));
	}
%>
