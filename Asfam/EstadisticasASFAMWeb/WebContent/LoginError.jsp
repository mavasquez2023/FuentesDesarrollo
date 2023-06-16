<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="description" content="Estadisticas ASFAM Web 2.0" />
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/secure/resources/css/estilos_interna.css" />" />
	<link rel="stylesheet" type="text/css" href="<c:url value="/secure/resources/css/grid_960.css" />" />
	
	<title>Estadisticas ASFAM Web 2.0</title>
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

</head>
<body>
<%-- 	<c:set var="estadoGenerada">Generada</c:set> --%>
<%-- 	<c:set var="estadoNoGenerada">No generada</c:set> --%>
<%-- 	<c:set var="estadoEnProceso">En Proceso</c:set> --%>
<%-- 	<c:set var="urlForm"><c:url value="/secure/generar_estadisitcas" /></c:set> --%>
	<form:form action="#" >
		<div id="caja_registro">
			<table>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
			</table>
			<table width="1020" border="0">
				<tr>
					<td align="right">
						&nbsp;&nbsp;&nbsp; 
						<a href="#" onclick="window.close()"><b>Volver</b></a>
					</td>
				</tr>
				<tr>
					<td width="100%" height="25">
						<table border="0">
							<tr>
								<td><strong>ERROR</strong></td>
							</tr>
						</table>
					</td>
				</tr>
				
			</table>
			<div id="tabla_reload">
				<table width="1020" align="center" cellpadding="0" cellspacing="1" border="0">
					<tr>
						<td height="40" width="50" valign="middle" bgcolor="#7BAEBD" class="texto" style="color: #fff; text-align: center"><strong>Error, Tiene problemas con su credenciales. Contacte a su administrador de sistemas</strong></td>
					</tr>
				</table>
				
			</div>
		</div>
	</form:form>
</body>
</html>