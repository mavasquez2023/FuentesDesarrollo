<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>PreviPass</title>
<link href="css/grid_960.css" rel="stylesheet" type="text/css" />
<link href="css/estilos_interna.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" type="text/javascript"> 
<%ResourceBundle urls = ResourceBundle.getBundle("etc/urls");%>;
function gestionMP() {
		var opciones="toolbar=no,location=no, directories=no, status=yes, menubar=no, scrollbars=yes, resizable=yes, width=770, height=520, top=50, left=50";
		pagina = "cp_movper.jsp";
		window.open(pagina,"",opciones);
}
function appJump(app){
if(app=='CP'){
	//top.location="http://extranet.araucana.cl/planillain.nsf/MiniPortal?OpenFrameset";
	top.location= <%=urls.getString("cp")%>
}else
if(app=='PRE'){
	//top.location="http://201.238.208.213/portal";
	top.location= <%=urls.getString("previpass")%>
}else
if(app=='ENT'){
	//top.location="http://silweb.cp.cl/planillain.nsf/MiniPortal?OpenFrameset";
	top.location= <%=urls.getString("entidades")%>
}else
if(app=='IND'){
	//top.location="http://cotiweb4.araucana.cl/entrada.nsf/Frame Independientes";
	top.location= <%=urls.getString("independientes")%>
}else
if(app=='CRE'){
	//top.location="http://rasp.laaraucana.cl/BotonPago/";
	top.location= <%=urls.getString("pagocredito")%>
}else
if(app=='SOP'){
	//top.location="http://www.previpass.cl/index.php?option=com_content&view=article&id=98:centro-de-ayuda&catid=44:paginas&Itemid=172";
	top.location= <%=urls.getString("soporte")%>
}
}
</script>
<script type="text/javascript">
	document.oncontextmenu = function(){
	return false;
	}
</script>
</head>

<body style="background: url(img/bg.jpg) repeat-x">
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/publico.jsp">
</form>
<div class="container_4" style="margin-top:30px;">
	<div class="grid_4"><img src="img/header.gif" width="957" height="118" /></div>


		<div class="grid_4 fondo">
		<div class="grid_1" align="right" style="margin-top:30px"><img
			src="img/servicios_linea.jpg" width="223" height="468" /></div>
		<div class="caja-1">
		<div class="menu">
			<a href="#" onclick="appJump('CP')"><img src="img/cp_empresas.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/cp_empresas_off.jpg'"
					onmouseout="src='img/cp_empresas.jpg'" 
					/></a>
		</div>
		<div class="menu">
			<a href="#" onclick="appJump('PRE')"><img src="img/previpass.jpg" width="209"
					height="227" border="0" onmouseover="src='img/previpass_off.jpg'"
					onmouseout="src='img/previpass.jpg'" 
					/></a>
		</div>
		<div class="menu">
			<a href="#" onclick="appJump('ENT')"><img src="img/entidades_pagadoras.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/entidades_pagadoras_off.jpg'"
					onmouseout="src='img/entidades_pagadoras.jpg'"
					/></a>
		</div>
		<div class="menu">
			<a href="#" onclick="appJump('CRE')"><img src="img/pago_credito_linea.jpg"
					width="209" height="227" border="0"
					onmouseover="src='img/pago_credito_linea_off.jpg'"
					onmouseout="src='img/pago_credito_linea.jpg'"
					/></a>
		</div>
		<div class="menu">
			<a href="#" onclick="appJump('IND')"><img
			src="img/trabajadores_indep.jpg" width="209" height="227" border="0"
			onmouseover="src='img/trabajadores_indep_off.jpg'"
			onmouseout="src='img/trabajadores_indep.jpg'"
			/></a>
		</div>
		<div class="menu">
			<a href="#" onclick="appJump('SOP')">
			<img src="img/soporte.jpg"
			width="209" height="227" border="0"
			onmouseover="src='img/soporte_off.jpg'"
			onmouseout="src='img/soporte.jpg'" 
			/></a>
		</div>
		</div>
		</div>
	
	<div style="clear:left"></div>
  	<jsp:include page="piedepagina.jsp"/>
</div>
</body>
</html>