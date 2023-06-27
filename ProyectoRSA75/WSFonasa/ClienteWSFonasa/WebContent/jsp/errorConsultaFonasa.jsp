<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %> 
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<html>
<head>
	<title>Formulario de Consulta Web Service Fonasa</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="GENERATOR" content="Rational Software Architect">
	<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estilo.css" />
	<script language="javascript">
	</script>
</head>
<body>
	<table width="739" border="0" cellspacing="0" cellpadding="0">
		<tr> 
			<td>
				<img src="${pageContext.request.contextPath}/images/logo_araucana.jpg" border="0" />
			</td>
		</tr>
		<tr> 
			<td align="left" valign="middle">
				<img src="${pageContext.request.contextPath}/images/sombra.jpg" width="739" height="7" />
			</td>
		</tr>
   		<tr> 
		    <td align="left" valign="middle">&nbsp;</td>
		</tr>
		<tr> 
		    <td align="left" valign="middle" class="titulos">Resultado Consulta Web Service Fonasa</td>
		</tr>
		<tr> 
		    <td align="left" height="40" valign="top" class="texto">
		    	<table border="0" cellpadding="0" cellspacing="0">
		    	<tr>
		    		<td rowspan="3"><img src="${pageContext.request.contextPath}/images/exclamacion.jpg" width="40" height="40" /></td>
		    		<td>&nbsp;</td>
		    	</tr>
		    	<tr>
		    		<td class="texto">		    	
				    	&nbsp;&nbsp;Ocurrio un error al consultar el <b>Rut</b> ingresado
				    </td>
		    	</tr>
		    	<tr>
		    		<td>&nbsp;</td>
		    	</tr>	
		    	<tr>
		    		<td><a href="formularioConsulta.do"><button class="boton_1">Volver</button></a></td>
		    	</tr>	
		    	</table>    	

		    </td>
		</tr>
	</table>
</body>
</html>
