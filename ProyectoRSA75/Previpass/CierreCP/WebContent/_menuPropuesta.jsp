
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>Menu Cierre CP</TITLE>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";

function Redirect(action){
	//window.location= path + action;
	top.PRINCIPAL.window.location= "http://" + serverport + action;
}

</script>
</HEAD>
<BODY topmargin="0">
<table border=0 cellspacing="5" cellpadding="5">
	<tr valign="middle" bgcolor="#006777">
		<td width="200" colspan=2><a id="Propuesta" style="text-decoration:none; color:#dddddd;"
			onclick="window.location='menucierre.jsp';return false;" href="#"> 
			<img border="0" src="icons/money.png" alt="Propuesta Pago"
			height="16" width="16">Propuesta Pago</a></td>
	</tr>
	<tr valign="middle">
		<td width="20">&nbsp;</td>
		<td width="200" bgcolor="#007777"><a style="text-decoration:none; color:#dddddd;"
			onclick="Redirect('/GestionEntidadesCP/GenerarPropuesta.do');return false;" href="#"> 
			<img border="0" src="icons/actn008.gif" alt="Generar PropuestaPagos"
			height="21" width="21">Generar Propuesta</a>
		</td>
	</tr>
	<tr valign="middle">
		<td width="20">&nbsp;</td>
		<td width="200" bgcolor="#007777"><a style="text-decoration:none; color:#dddddd;"
			onclick="Redirect('/GestionEntidadesCP/VerPropuesta.do');return false;" href="#"> 
			<img border="0" src="icons/actn037.gif" alt="Ver Propuesta de Pago"
			height="21" width="21">Gestión Propuesta</a></td>
	</tr>
	<tr valign="middle">
		<td width="200" colspan=2 bgcolor="#006777"><a style="text-decoration:none; color:#dddddd;"
			onclick="Redirect('/GestionEntidadesCP/VerPropuesta.do');window.location='menucierre.jsp';return false;" href="#"> 
			<img border="0" src="icons/actn037.gif" alt="Ver Propuesta de Pago"
			height="21" width="21">Gestión Entidades</a></td>
	</tr>
	<tr valign="middle">
		<td width="200" colspan=2 bgcolor="#006777"><a style="text-decoration:none; color:#dddddd;"
			onclick="Redirect('/GestionEmpresasCP/VerResumen.do');window.location='menucierre.jsp';return false;" href="#"> 
			<img border="0" src="icons/update-data.png" alt="Cargar Cheques a AS400"
			height="16" width="16">Gestion Empresas</a></td>
	</tr>
	
</table>
</BODY>
</html>
