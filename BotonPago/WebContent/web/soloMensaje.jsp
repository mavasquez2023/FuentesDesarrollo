<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<body>
	<div class="contenedor">
		<div class="${tipo}">
			<b>${titulo}:</b>&nbsp;${mensaje}
			<html:errors />
		</div>
		<br> <br> <br>
	</div>
</body>
</html>