<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
	
	
	<link href="jqSimat/jqBlueUI/css/redmond/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="jqSimat/jqBlueUI/js/jquery-1.8.3.js"></script>
	<script src="jqSimat/jqBlueUI/js/jquery-ui-1.9.2.custom.js"></script>
	<script src="jqSimat/calendar/ajustesCalendario.js"></script>
	<script src="jqSimat/botones/ajustesBotones.js"></script>
	
	<script src="jqSimat/validacionesForm/val_periodo.js"></script>

</head>

<body >
	<div id="wrapper" name="wrapper">	
		<div id="header" name="header">
			<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg" width="900" height="100">
		</div>
		<div id="workSpace" name="workSpace">
			<center>		
		        <h2>Seleccione Per&iacute;odo</h2>            
		        <hr>
		    </center>	
			<div id="simulacion">
				<center>
					<fieldset class="form-fieldset">
						<div id="periodoBox" name="periodoBox" >
							<form action="cargarPeriodo.do" name="formPeriodo" id="formPeriodo" method="post" class="form" type="cl.laaraucana.simat.forms.PeriodoForm" onKeypress="if(event.keyCode == 13) event.returnValue = false;">
						
							    <input type="hidden" name="metodo" id="metodo" value="getEstadoPeriodo"/>					    
								<div class="campoForm">
									<label class="labelFormPeriodo">Per&iacute;odo</label>
									<input class="inputForm" maxlength="6" type="text" name="periodo" id="periodo" />
									<label class="labelError" id="periodoMarca"></label>
								</div>
							</form>
						</div>
						<input type="button" class="boton" value="aceptar" id="botonPeriodo" name="botonPeriodo" onclick="javascript:cargarPeriodo()"/>
					</fieldset>
					<fieldset class="form-fieldset">
						<div name="vm" id="vm" align="right">
				    		<input class="boton" type="button" value='ir Menu' onclick="javascript:volverMenu()"/>
						</div>						
					</fieldset>
					<fieldset>
						<div align="right">
							v_2.4.4
						</div >
					</fieldset>
				</center>
			</div>
		</div>
		<div id="LoadMenu_dialog" title='Validando Periodo'>
			<div id="loadMenu" name="loadMenu" >
			<center><img src='./imgSimat/Loading.gif'><br>Espere un momento...<br></center>
			</div>			
		</div>		
	</div>
	
</body>
</html>