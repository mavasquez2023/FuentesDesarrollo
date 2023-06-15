<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="../comun/tld.jsp"%>
<html>
<head>
<title>Error - La Araucana C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
<body>
	<div align="center">
		<br>
		<h1>Error 500</h1>
		<br> <br>
			<p class="texto">
				Su solicitud no pudo ser procesada, intente nuevamente
			</p>
		<br> <input type="button" class="boton" value="Volver" onclick="window.history.back()"> <br>
		<br> <br>
	</div>
	<br>
	<br>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
</body>
</html>
