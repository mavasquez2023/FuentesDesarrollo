<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="cl.araucana.core.util.http.Utils"
%>

<%
	response.sendRedirect(Utils.getPageURL(request, "/web/Welcome.do"));
%>
