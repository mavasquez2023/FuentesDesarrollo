<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="comun/tld.jsp"%>
<html>
<head>
<title>La Araucana C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
<body>
<fieldset class="form-fieldset">
	<div align="center">
		<br>
		<h1>Sistema de pago para deudores afiliados y no afiliados.&nbsp;&nbsp;</h1>
		<br> <br>

		<p class="texto">
			<c:if test="${not empty  param.ms}">
				<bean:message key="${param.ms}" />
			</c:if>
			
			<c:if test="${not empty mensaje}">
				<c:out value="${mensaje}"></c:out>
			</c:if>

		</p>
		<br> <input type="button" class="boton" value="Volver"
			onclick="window.history.back()"> <br> <br> <br>
	</div>
	<br>
	<br>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
</fieldset>
</body>
</html>
