
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">

<TITLE>Menu Cierre CP</TITLE>
<style>
a, td{
	font-family: Verdana, Arial, Helvetica, sans-serif;
	text-decoration:none;
	font-size: 11px;
	font-weight:bold;
	color:#dddddd;
	background-color: #99AAAA;
	height: 35px;
	width: 150px;	
}
a:hover{
	color:#000000;
	background-color: #CCDDDD;
}
a:active{
	color:#000000;
	background-color: #DDEEEE;
}

</style>
<script language="JavaScript 1.2" type="text/javascript">
var path= "<%=request.getContextPath()%>";
var serverport= "<%=request.getServerName() + ":" + request.getServerPort()%>";
var admin= "<%=session.getAttribute("admin")%>";

function init(){
	if (admin=="null"){
		top.location= path  + "/Inicio";
	}
}

function Redirect(action){
	//window.location= path + action;
	//top.PRINCIPAL.window.location= "http://" + serverport + action;
	top.PRINCIPAL.window.location= path + action;
}
function Popup(action){
	var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=no, width=550, height=500, top=50, left=100";
	pagina = path + action;
	window.open(pagina,"",opciones);
}
</script>
</HEAD>
<BODY topmargin="0" onload="init();">
<table border=0 cellspacing="5" cellpadding="1">
	<tr>
		<td><a onclick="Redirect('/VerBoletas.do');return false;" href="#"> 
			<img border="0" src="icons/actn015.gif" alt="Boletas PAC"
			height="16" width="16">&nbsp;Boletas PAC</a></td>
	</tr>
	<tr>
		<td><a onclick="Redirect('/GenerarPropuesta.do');return false;" href="#"> 
			<img border="0" src="icons/money.png" alt="Generar Propuesta Pago"
			height="16" width="16">&nbsp;Propuesta Pago</a></td>
	</tr>
	<tr>
		<td><a onclick="Redirect('/VerPropuesta.do');return false;" href="#"> 
			<img border="0" src="icons/actn040.gif" alt="Gestión Entidades"
			height="21" width="21">&nbsp;Gestión Entidades</a></td>
	</tr>
	<tr>
		<td><a onclick="Redirect('/VerResumen.do');return false;" href="#"> 
			<img border="0" src="icons/update-data.png" alt="Gestión Empresas"
			height="16" width="16">&nbsp;Gestión Empresas</a></td>
	</tr>
	<tr>
		<td><a onclick="Popup('/VerProcesosActivos.do');return false;" href="#"> 
			<img border="0" src="icons/actn037.gif" alt="Ver Procesos Activos"
			height="16" width="16">&nbsp;Procesos Activos</a></td>
	</tr>
</table>
</BODY>
</html>
