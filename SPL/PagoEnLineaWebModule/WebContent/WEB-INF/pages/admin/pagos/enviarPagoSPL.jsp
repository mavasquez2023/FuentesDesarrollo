<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%
String xml = (String)request.getAttribute("xml");
String contexto = (String)request.getAttribute("contexto");
String trx = (String)request.getAttribute("trx");
String estado = (String)request.getAttribute("estado");
%>
<html>
<head>
<title>sendXml</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--</script>-->
</head>
<body onload="document.forms[0].submit();">
<form name="frmNotificacion" action="../<%=contexto%>/NotificacionPago.do" method="post">
	  <input type="HIDDEN" name="TX" value="<%= xml %>">
	  <input type="HIDDEN" name="xml" value="<%= xml %>">
	  <input type="HIDDEN" name="trx" value="<%= trx %>">
      <input type="HIDDEN" name="estado" value="<%= estado %>">
</form>
</body>
</html>

