<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*"
%>

<%
	String homePage = application.getInitParameter("homePage");
	String loginURL = homePage;
	
	if (homePage == null) {	
		String URL = request.getRequestURL().toString();
		String URI = request.getRequestURI();
		int index = URL.indexOf(URI);
				
		if (index > 0) {
			loginURL =
				  URL.substring(0, index)
				+ request.getContextPath() + "/login.jsp";
		}
	}else{
			response.sendRedirect(loginURL);		
	}
%>