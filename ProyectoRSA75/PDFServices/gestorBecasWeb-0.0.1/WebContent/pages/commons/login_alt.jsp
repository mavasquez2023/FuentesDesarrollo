<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<TITLE>Gestor de Becas e Incentivos</TITLE>
<script src="scripts/jquery.tools.full.min.js" language="javascript"
	type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="css/estilo.css">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="user-scalable=no, width=480">
</head>
<body class="login  ">
	<div id="contenido">
		<div id="cabecera" class="clearfix">
			<center>
				<img src="images/header_2017.jpg" />
			</center>
		</div>
		<div id="formulario_ingreso">
			<div id="contenedor_mensaje"></div>
			<br>
			<script language="javascript" src="includes/funciones.js"></script>
			<form action="j_security_check" method="post" name="frmlogin">
				<input type="hidden" name="accion" value="login">
				<h3>Ingreso a Gestor de Becas e Incentivos</h3>
				<div class="bloque clearfix">
					<label for="username">RUT Usuario</label> <input
						placeholder="Ingrese su RUT" type="text"
						class="text solo_rut_guion " name="j_username"
						value="" maxlength="12">
						<br>
					<label for="username">Contraseña</label> <input
						placeholder="Ingrese su contraseña" type="password"
						class="text" name="j_password" id="pswd"
						value="">
				</div>
				<div class="bloque clearfix">
					<!--  <a href="#" onclick="validarSubmit(); return false;" class="boton"><input	type="submit">Ingresar</a> -->
					 <a href="#" onclick="" class="boton"><input	type="submit">Ingresar</a>
				</div>
			</form>
		</div>
	</div>
	
	<div id="footer_img">
		<center >
			<img src="images/footer_2017.jpg" />
		</center>
	</div>
</body>
</html>