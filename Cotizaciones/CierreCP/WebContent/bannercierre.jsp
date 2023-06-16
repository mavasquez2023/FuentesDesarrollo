
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
<TITLE>Banner Cierre CP</TITLE>
<script>
var path= "<%=request.getContextPath()%>";

function Home(){
	window.location= "";
}
function cerrarSesion(){
		top.location=path + "/Logout.do";
}
function Correos(){
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=no, resizable=no, width=550, height=300, top=50, left=100";
	pagina = path + "/correos.jsp";
	window.open(pagina,"",opciones);
return true;
}
</script>
</HEAD>
<BODY topmargin="0">
<table width="778" border="0" cellpadding="0" cellspacing="0">
	<tr align="left" valign="top">
		<td colspan="2"><img src="images/logo.jpg" width="778" height="95" border="0"
			usemap="#Map" /></td>
	</tr>
	<tr valign="middle">
		<td height="20" align="left" valign="middle"
			class="titulos_formularios" width="49%"><font color="#999999"><font
			color="#666666"><strong>&nbsp;&nbsp;&nbsp;&nbsp;</strong>Cierre CP Empresas e Independientes</font></font></td>
		<td height="25" align="right" valign="bottom"
			class="titulos_formularios" width="51%">
		<table height="18" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="18" align="right" valign="middle"
					class="botonera_ppalactivada">&nbsp;</td>
				<!--   
				<td align="right" valign="middle" nowrap="nowrap"
					class="botonera_ppalactivada"><a href="#" onclick="Home();"><img
					src="images/ico_home.gif" width="11" height="10" hspace="2"
					border="0" alt="Home" /></a>Home</td>
				<td width="10" align="right" valign="middle"
					class="botonera_ppalactivada">&nbsp;</td>
				-->
				<td align="right" valign="middle" class="botonera_ppalactivada"><a
					href="#" onclick="Correos();return false;"><img
					src="images/ico_contact.gif" width="11" height="10" hspace="2"
					border="0" alt="Configuración Correos" />Config</a></td>
				<td width="10" align="right" valign="middle"
					class="botonera_ppalactivada">&nbsp;</td>
				<td align="right" valign="middle" nowrap="nowrap"
					class="botonera_ppalactivada"><a href="#" onclick="cerrarSesion();"><img
					src="images/ico_cerrar.gif" width="9" height="9" hspace="2"
					border="0" alt="Salir" />Salir</a></td>
				<td width="10" align="right" valign="middle" nowrap="nowrap"
					class="botonera_ppalactivada">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr valign="middle">
		<td height="22" colspan="2" align="left" valign="middle"
			class="titulos_formularios">
		<div align="center"><img src="images/sombra.jpg" width="739"
			height="7" /></div>
		</td>
	</tr>
</table>
</BODY>
</html>
