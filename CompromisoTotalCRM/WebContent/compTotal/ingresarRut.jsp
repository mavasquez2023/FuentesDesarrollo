<!DOCTYPE html>
<%@page	language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>
<% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%>
<%@ include file="../comun/tld.jsp"%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/js/script.js"></script>
		<link type="text/css" href="<%=request.getContextPath() %>/css/estilos.css" rel="stylesheet"/>
		<link type="text/css" href="<%=request.getContextPath() %>/css/integracionCRM.css" rel="stylesheet"/>
		<title>Compromiso Total - La Araucana</title>
		<meta charset="UTF-8">
	</head>
	<body>
		<form name="formulario" id="formulario">
			<strong>Ingrese rut afiliado:</strong> <input type="text" id="rut" name="rut" placeholder="1234567-8" >
			<input type="submit" id="consultar" value="Consultar" class="boton" >
			<!-- <font size="2">&nbsp;&nbsp;(incluir guión y dígito verificador)</font> -->
			<span id="rutError" class="error">
			</span>
		</form>
	<div class="contieneIframe">
			<iframe src="${url}" id="mainIframe" name="mainIframe" width="100%;" height="100%;" frameborder="0">
			</iframe>
	</div>
	<script type="text/javascript">
 	$(document).ready(function() {
		$("#formulario").submit(function() {
			var rut = $("#rut").val();
			if(isBlank(rut)){
				$("#rutError").empty();
				$("#rutError").append("Ingrese un rut");
			}else{
				$("#rutError").empty();
				if(esrut(rut)){
					var url = '<%=request.getContextPath() %>/compTotal/listadoContratos.do?rut='+$("#rut").val();
					$('#mainIframe').attr('src', url);
				}else{
					$("#rutError").append("Ingrese un rut válido");
				}
			}
			return false;
		});
	});
	</script>
		
	</body>
</html>