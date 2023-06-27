<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Sistema de Cotizaci&oacute;n Previsional :: cp.cl</title>
</head>
<body>
<form action="/CotPrevIndependienteWeb/araucana/login/usuarioNoEncontrado.jsp" id="formulario"></form>
<script language="javaScript">
<!-- 
	var http;
	if (window.XMLHttpRequest)
		http = new XMLHttpRequest();
	else if (window.ActiveXObject)
	{
		try 
		{
			http = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e) 
		{
			try 
			{
				http = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http)
	{
		alert("No ha sido posible crear una instancia de XMLHttpRequest");
	}

	logoutApp = function(contexto)
	{
		var url = "/" + contexto + "/ibm_security_logout";
		http.open("POST", url, true);

		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		http.setRequestHeader("Content-length", 0);
		http.setRequestHeader("Connection", "close");
		http.onreadystatechange = function() {}
		http.send('');

		return true;
	}
	if ('<%=request.getSession().getAttribute("cierraTodo") %>' == 'si')
	{
		logoutApp("AdminCotPrevWEB");
		logoutApp("CotPrevIndependienteWeb");
		location.replace("/CotPrevIndependienteWeb/araucana/login/usuarioNoEncontrado.jsp?error=<%= request.getAttribute("error") %>");
	} else
	{
		//if ('<%=request.getSession().getAttribute("cierraAdmin") %>' == 'si' || ('<%=request.getSession().getAttribute("clAdmin") %>' == 'si' &&
		//		confirm("Usted tiene permisos asignados sobre Empresas/Convenios, además de ser Administrador CPE. \n¿Desea entrar en Aplicación Cliente?")))
		//{//deslogea de admin e ingresa a app cliente
		//	logoutApp("AdminCotPrevWEB");
			location.replace("/CotPrevIndependienteWeb/Inicio.do");
		//} else
		//{//deslogea de cliente e ingresa a app admin
		//	logoutApp("CotPrevIndependienteWeb");
		//	location.replace("/AdminCotPrevWEB/");
		//}
	}
	<%  request.getSession().setAttribute("clAdmin", "no");
		request.getSession().setAttribute("cierraAdmin", "no");
		request.getSession().setAttribute("cierraTodo", "no");  %>
//-->
</script>
</body>
</html>
