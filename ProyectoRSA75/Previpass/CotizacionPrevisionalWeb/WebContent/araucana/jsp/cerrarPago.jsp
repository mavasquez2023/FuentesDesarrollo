<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title>abrirPago</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="GENERATOR" content="Rational Application Developer">
<script language="Javascript">
function volver()
{
	if (window.opener)
    	window.opener.location='<%=request.getContextPath()%>/ListarNominas.do?accion=inicio&subAccion=procesos&limpiaPath=';
    window.close();
 }
</script>


</head>
<body onload="volver();">
</body>
</html>
