<%@ include file="/html/comun/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%

String sistema = (String)request.getAttribute("sistema");
String xml = (String)request.getAttribute("xml");
String vector = (String)request.getAttribute("vector");
String urlSLP = (String)request.getAttribute("urlSPL");
%>
<html>
<head>
<title>sendXml</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="JavaScript">
function sendInformacion()
{
	formu = document.forms['mediosPagoForm'];
	formu.submit();
}
</script>
</head>
<body onload="document.forms[0].submit();">
<form name="mediosPagoForm" method="post" action="<%= urlSLP %>">
<input type="hidden" name="sistema" value="<%=sistema %>">
<input type="hidden" name="xml" value="<%=xml %>">
<input type="hidden" name="vector" value="<%=vector %>">
</form>
 
</body>
</html>

