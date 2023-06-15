<%@ include file="layout/headerJsp.jsp" %>
<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<link href="css/estilos.css" rel="stylesheet" type="text/css" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<meta name="Author" content="J-Factory Solutions" />
		<meta http-equiv="Content-Style-Type" content="text/css" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<title>Pago de Crédito en Línea - La Araucana</title>
	</head>
	<body>
	<div id="contenedor">
	<jsp:include flush="true" page="comun/header.jsp"></jsp:include>
	<fieldset class="form-fieldset">
	<div class="content">
		<div class="content-header">
			<p class="titulo">Servicios en línea</p>
			<div class="mensaje-gris">
				Estimado usuario: Su clave ha sido cambiada exitosamente
			</div>
		</div>
		<div id="content-principal">
			<div id="content-left">
				<div class="caja">
					<div id="caja-left">
						<bean:message key="cambio.clave.exito"/>				
						<br><br>
							<input type="button" onclick="window.location='<%=request.getContextPath() %>/logout.do'" class="boton" value="Continuar" >
					</div>	
					<div id="caja-right">
					</div>
				</div>
			</div>
			<div id="content-right">
				<div class="caja">
					<a href="http://www.laaraucana.cl/irj/go/km/docs/documents/corpsite/personas/inicio/LinksCabecera/Contactenos">
						<img src="img/sobre.gif" align="left" id="imgContacto" >Consultas <br> sugerencias o reclamos
					</a>
					<br>
					<div class="linea-puntos" ></div>
					<img src="img/fono_contacto.gif" >
				</div>
			</div>
		</div>
		<br><br>
		<hr>
		<script type="text/javascript" src="js/jquery-validate.min.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
	</div>
	</fieldset>
	</div>
	</body>
</html>