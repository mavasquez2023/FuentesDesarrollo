<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><%@page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="../comun/tld.jsp"%>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="../comun/header.jsp"%>
</head>
<body>
	<center>
		<div>
			<div class="error_message">
				<strong>Ha ocurrido un problema en el sistema:</strong>
				<bean:write name="msg" />
			</div>
		</div>
	</center>
</body>
</html:html>