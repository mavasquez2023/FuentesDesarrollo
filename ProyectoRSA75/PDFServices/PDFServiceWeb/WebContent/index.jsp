<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>

<HEAD>

<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*"
%>

</HEAD>

<BODY>
	<%
		String URL = request.getRequestURL().toString();
		String URI = request.getRequestURI();
		int index = URL.indexOf(URI);
				
		if (index > 0) {
			String startPageURL =
				  URL.substring(0, index)
				+ request.getContextPath() + "/LoadConfUser.do"; 
				
			response.sendRedirect(startPageURL);				
		}
	%>
</BODY>
</HTML>
