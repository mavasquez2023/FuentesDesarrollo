<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<head>
<title>Error</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta content="tupla version 3.3">
<meta name="GENERATOR" content="Rational Software Architect">
<link rel="stylesheet" type="text/css" href="css/cambiotramo.css" />
<link rel="stylesheet" type="text/css" href="css/main.styles.css" />
</head>
<body> 
<table width="1000px" style="margin-left: 30px;"> 
<tr>
<td height="100" colspan="2">
<img  src="img/BONO.jpg" width="1000px" height="100"">
</td>
</tr>
<tr><td valign="top" width="300px" >
<jsp:include  page="/jsp/menu.jsp">
</jsp:include>
</td><td width="700px" align="center"><font class="text">
Glosa de Error: <c:out value="${mensaje}"></c:out>
</td></tr>
</table>
</body>
</html:html>
 