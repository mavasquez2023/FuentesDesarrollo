<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	String msg="";
	msg=(String)request.getAttribute("errorLogin");
	if(msg==null){
		msg="";
	}
	
 %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8, IE=9, IE=10" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIMAT</title>

	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css">
	<link rel="stylesheet" type="text/css" href="css/simular.css">

	<link rel="stylesheet" href='css/main.css' type="text/css" />
	<link rel="stylesheet" href='css/screen.css' type="text/css" />
	<link rel="stylesheet" href='css/interior.css' type="text/css" />
	
	
	<link rel="stylesheet" href='cssSimat/estructura.css' type="text/css" />
	<link rel="stylesheet" href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css">
	
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	<script src="jqSimat/validacionesForm/val_login.js"></script>
	<script src="jqSimat/validacionesForm/validarut.js"></script>

</head>
<body>
	<div id="wrapper" name="wrapper">	
		<div id="header" name="header">
			<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
		</div>
		<div id="workSpace" name="workSpace">
			<center>		
		        <h2>Login </h2>            
		        <hr>
		    </center>	
			<div id="simulacion">
				<fieldset class="form-fieldset">
						<%
							if(msg!=""){
							out.println("<div align='center' id='msgLogin' name='msgLogin'>");									
							out.println(msg);									
							out.println("</div>");									
							}						
						%>
					
					<div id="loginBox1" name="loginBox1" >
					</div>
					<div id="loginBox" name="loginBox" >
						<form action="direcLogin.do?metodo=validarUsuario" name="formLogin" id="formLogin" method="POST" class="form" >							
							<div class="campoForm">
								<label class="labelLogin" >Rut Usuario: </label>
								<input class="inputForm" type="text" id="nombre" name="nombre" title="rut con guion y sin puntos"/>
								<label class="labelError" id="nombreMarca"></label>
							</div>								 
							<div class="campoForm">
								<label class="labelLogin">Clave: </label>
								<input class="inputForm" type="password" id="clave" name="clave"/>
								<label class="labelError" id="claveMarca"></label>
							</div>
						</form> 
					</div>
					<div id="loginBox2" name="loginBox2" >
					</div>
					<div id="loginBoxBotones" name="loginBoxBotones">
						<input class="boton" type="button" value='login' onclick="javascript:validarLogin()"/>
						<!--<input class="boton" type="button" value='login' onclick="javascript:Rut()"/>-->
						<input TYPE="button" value="limpiar" class="boton" onclick="javascript:limpiarLogin()"/>												
					</div >
					
				</fieldset>
				<fieldset>
					<div align="right">
						v_2.4.4
					</div >
				</fieldset>
			</div>
		</div>		
	</div>
</body>
</html>