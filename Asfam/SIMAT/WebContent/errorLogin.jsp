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
	<script src="jqSimat/menuOpen/ajustesMenu.js"></script>

</head>
<body>
	<div id="wrapper" name="wrapper">	
		<div id="header" name="header">
			<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900px" height="100px">
		</div>
		<div id="workSpace" name="workSpace">
			<center>		
		        <h2>Error</h2>            
		        <hr>
		    </center>	
			<div id="simulacion">
				<fieldset class="form-fieldset">
					<div id="errorBox" name="errorBox" style=" display:inline-block; width: 900px; height: 100px;">
						<p>Ha ocurrido un error:</p>
						<center>
							<p>	
								No se ha podido validar de manera correcta las credenciales del usuario. Por favor inicie sesión nuevamente.
							</p>
						</center>
					</div>
					<div style="display:inline-block;  text-align: right; min-width:700px; width: 850px; height:50px;" >
						<a onclick="window.location.href='<%=request.getServletContext().getInitParameter("urlMenuDinamico") %>'"><input type="button" class="boton" value="Ir a Menú dinámico"></a>
						<input type="button" class="boton" name="btn_Salir" id="btn_Salir" value="Salir" onclick="javascript:closeSesion()"/>
					</div>
				</fieldset>
			</div>
		</div>		
	</div>
</body>
</html>