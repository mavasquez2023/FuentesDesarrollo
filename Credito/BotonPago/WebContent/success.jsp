<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="comun/tld.jsp"%>
<html>
<head>
<title>Error-LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
<body>
	<div align="center">
		<br>
		<h1>Usuario registrado correctamente&nbsp;&nbsp;</h1>
		<br> <br>
			<p class="texto">
				El usuario ha sido registrado correctamente. Su clave inicial será enviada al correo electrónico indicado.                            
			</p>
		<br><input type="button" class="boton" name="volver" value="Iniciar sesión" onclick="window.location.href='<%=request.getContextPath()%>/login.jsp'"><br>
		<br> <br>
	</div>
</body>
</html>
