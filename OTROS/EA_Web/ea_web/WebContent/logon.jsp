<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />

<jsp:include page="/WEB-INF/meta_application.jsp" />

<link href="/sce/css/Master.css" rel="stylesheet" type="text/css" />

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
.noframe-framing-table {background-color: #767776; background-image: none; border-right: 1px solid #000000;	border-bottom: 1px solid #000000; border-top: 1px solid #000000; border-left: 1px solid #000000;}
.column-head { padding-left: .35em; font-family: Arial, Helvetica, sans-serif; font-size: 77.0%; font-weight: bold; text-align: left; color: #FFFFFF; background-color: #8691C7; background-image: none;}
.table-text { font-family: Arial, Helvetica, sans-serif; font-size: 12px; background-color: #F7F7F7; background-image: none;}
.login-button-section {padding-left: 0px; font-family: Arial, Helvetica, sans-serif; font-size:10px; font-weight: normal; color: #000000; background-color: #CCCCCC; background-image: none;}
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}

/* FORMS */
FIELDSET {border: 0px}
INPUT {font-family: sans-serif; font-size: 95.0%}
FORM {margin-bottom: 0px; margin-top: 0px; padding-top: 0px; padding-bottom: 0px;}
</style>

</head>
<body bgcolor="#FFFFFF">
	<center>
    <img src="img/EA.jpg">
	</center>

	<table border="0" width="800" cellpadding="0" cellspacing="0">
		<tbody>
		
			<!-- Header -->
			<tr>
				<td colspan="3">

<table width="520" border="0" align="center" cellpadding="1" cellspacing="1">
	<tr>
		<td>
			<br>
			<br>
			<form action="j_security_check" method="post">
			<table border=0 cellpadding=0 cellspacing=0 width="100%" height="100%" align="center">
				<tr> 
					<td class="login">
					<table class="noframe-framing-table" border=0 cellpadding="5" cellspacing="0" width="400" summary="Login Table" align="center">
						<tbody>
							<tr>
								<th colspan="2" class="column-head" scope="rowgroup">Por favor, ingrese su Rut y clave de acceso&nbsp;&nbsp;</th>
							</tr>
							<tr>
								<td valign=top colspan="2" class="table-text" nowrap>&nbsp;</td>
							</tr>
							<tr>
								<td valign=top width="20%" class="table-text" nowrap>
									<label	for="name">Rut:</label>
								</td>
								<td valign=top class="table-text">
									<input type="text" name="j_username" class="short" id="name">
									<font size="1">&nbsp;&nbsp;(incluir guión y dígito verificador)</font>
								</td>
							</tr>
							<tr>
								<td valign=top width="20%" class="table-text" nowrap>
									<label	for="pswd">Contrase&ntilde;a:</label>
								</td>
								<td valign=top class="table-text">
									<input type="password" name="j_password" class="short" id="pswd">
								</td>
							</tr>
							<tr>
								<td valign=top colspan="2" class="login-button-section" nowrap align="center">
									<input type="submit" name="action" class="buttons" value='Aceptar'>
								</td>
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
	document.forms[0].j_username.focus();
</script>

</body>
</html>

