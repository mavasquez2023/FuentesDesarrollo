<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<jsp:include page="/WEB-INF/meta_application.jsp" />

<!--  
<link href="/sce/css/Master.css" rel="stylesheet" type="text/css" /> 
-->

<title>La Araucana C.C.A.F. - Cuenta Corriente Empresa</title>

<style>
.salir {
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px; 
	font-weight: bold; 
	color: yellow;
}
</style>

<style type="text/css">
.nnoframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.ncolumn-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.ntable-text { font-family: Arial, Helvetica, sans-serif; font-size: 12px; background-color: #F7F7F7; background-image: none;}
.ntlogin-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size:10px; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.nnota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
UNKNOWN {
	
}
body {
	padding-top: 0px;
	margin-top: 0px;
	background-image: url(/ea/edocs/images/fondopag.jpg);
	font-size: 13px;
	font-family: Arial, Helvetica;
}
#mensaje {
	padding-top: 5px;
	margin: 0px;
	padding-right: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	height: auto;
	background-color: #f1f1f1;
	font-family: Arial, Helvetica;
	font-size: 13px;
}

.text {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.button {
	FONT-SIZE: 13px;
	COLOR: #FFFFFF;
	FONT-STYLE: normal;
	FONT-FAMILY: arial;
	background-image: url(/ea/edocs/images/fondobot.jpg);
	margin-right: 5px;
	margin-left: 5px;
	margin-bottom: 15px;
	border-top-color: 03557b;
	border-right-color: 03557b;
	border-bottom-color: 007bb7;
	border-left-color: 007bb7;
	font-weight: bold;
	height: 25px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}
.menu {
	border: 1px solid #ABABAB;
	width: 238px;
}

h1 {
	font-family: Arial, Helvetica;
	font-size: 25px;
	font-weight: bold;
	color: #999999;
	margin-left: 10px;
}
#tabla {
	border: 1px solid #ABABAB;
	width: 730px;
}


.blackText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
#tabla th {
	background-image: url(/ea/edocs/images/fondo_top_tabla.jpg);
	height: 24px;
	padding-top: 3px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
}

.blackText14 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackText16 {
	FONT-WEIGHT: bold;
	FONT-SIZE: 16px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP6 {
	FONT-SIZE: 16px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: Arial, Helvetica
}
.blueTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText5 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.redTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.redBackGris {
	FONT-STYLE: normal;
	padding-top: 10px;
	padding-right: 10px;
	padding-bottom: 10px;
	padding-left: 5px;
}
.optionText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.optionBlueText {
	FONT-SIZE: 16px;
	COLOR: #005d8b;
	FONT-STYLE: normal;
	FONT-FAMILY: arial;
	margin: 5px;
	text-align: left;
	font-weight: bold;
	padding: 5px;
	display: block;
}
.optionBlueTextP2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: Arial, Helvetica
}
#tabla td {
	height: 25px;
	padding-top: 5px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
	font-family: Arial, Helvetica;
}
a:link {
	font-family: Arial, Helvetica;
	font-size: 13px;
	color: #333;
}
#tabla th {
	height: 25px;
	padding-top: 5px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	font-family: Arial, Helvetica;
}
.hr  {
	clear: both;
	height: 2px;
	background-repeat: repeat-x;
	background-position: 0px 0px;
	border:none !important;
	padding: 0px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
	margin-left: 0px;
	background-image: url(/ea/edocs/images/corp_linea_puntos.gif);
}

</style>

</head>
<body bgcolor="#FFFFFF">
	<center>
    <img src="edocs/images/EPC.jpg">
	</center>

	<table border="0" width="800" cellpadding="0" cellspacing="0">
		<tbody>
		
			<!-- Header -->
			<tr>
				<td colspan="3">

<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr>
		<td>
			<h1><strong>Empresas Adherentes</strong></h1>
			<br>
			<form action="j_security_check" method="post">
			<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" align="center">
				<tr> 
					<td class="login">
							<table class="noframe-framing-table" border=0 cellpadding="5"
								cellspacing="0" width="475" summary="Login Table" align="center">
								<tbody>
									<tr>
										<th colspan="2" class="login-button-section" scope="rowgroup">Por
										favor, ingrese su Rut y clave de acceso&nbsp;&nbsp;</th>
									</tr>
									<tr>
										<td valign=top colspan="2" class="table-text" nowrap>&nbsp;</td>
									</tr>
									<tr>
										<td valign=top width="25%" class="table-text" nowrap><label
											for="name">Rut:</label></td>
										<td valign=top class="table-text" width="367"><input
											type="text" name="j_username" class="short" id="name">
										<font size="1">&nbsp;&nbsp;(incluir guión y dígito
										verificador)</font></td>
									</tr>
									<tr>
										<td valign=top width="25%" class="table-text" nowrap><label
											for="pswd">Contrase&ntilde;a:</label></td>
										<td valign=top class="table-text" width="367"><input
											type="password" name="j_password" class="short" id="pswd">
										</td>
									</tr>
									<tr>
										<td valign=top class="table-text" colspan="2" nowrap
											align="center"><input type="submit" name="action"
											class="buttons" value='Aceptar'></td>
									</tr>
								</tbody>
							</table>
							</td>
				</tr>
			</table>			
			</form>
			</td>
		</tr>
	</table>
	
	<br>
	<br>
	<br>
	<br>
	
	<!-- NO MOSTRAR LINKS PARA OBTENER CLAVES.
	<table border="0" width="50%" align="center">
	  <tr>
	    <td width="100%">
			<p> &nbsp;&nbsp;&nbsp;&nbsp;Si a&uacute;n no tiene su clave o
				contrase&ntilde;a para poder
			    acceder a nuestros servicios en l&iacute;nea, por favor
			    solic&iacute;tela aqu&iacute;:
			</p>
			
			<ul>
				<li><a href="http://www.laaraucana.cl/?seccion=1012">Clave para Afiliados y Pensionados</a>
				<li><a href="http://www.laaraucana.cl/?seccion=1013">Clave para Empresas</a>
			</ul>
	    </td>
	  </tr>
	</table>
	-->

<script>
	document.forms[0].username.focus();
</script>

</body>
</html>

