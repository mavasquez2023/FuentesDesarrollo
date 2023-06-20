
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, viewport-fit=cover" />
<meta http-equiv="X-UA-Compatible" content="ie=edge" />
<meta name="format-detection" content="telephone=no" />
<title>Test - Servicios La Araucana</title>
<link rel="stylesheet" href="assets/css/fln.css" />
<link rel="stylesheet" href="fonts/fln-icons.css" />
<link rel="stylesheet" href="assets/css/estilos.css" />

</head>
<body>	
	<div class="container">
		<div class="row">
			<div class="col xs12 lg3 text-align-center-xs">
			
				<jsp:include page="menuServices.jsp" flush="true" />

				<div class="separador--big"></div>
			</div>
			<div class="col xs12 lg9">
				<form class="form" id="init" action="ftp.do" method="POST" enctype="multipart/form-data">
					<h3>FTP - Servicios</h3>
					<table>
					<tr>
						<td>Server FTP:</td>
						<td><input type="text" name="server" value="${server }" /></td>
					</tr>
					<tr>
						<td>Puerto FTP:</td>
						<td><input type="text" name="puerto" value="${puerto }" /></td>
					</tr>
					<tr>
						<td>Usuario FTP:</td>
						<td><input type="text" name="usuario" value="${usuario }" /></td>
					</tr>
					<tr>
						<td>Clave FTP:</td>
						<td><input type="text" name="clave" value="${clave }" /></td>
					</tr>
					<tr>
						<td>Carpeta FTP:</td>
						<td><input type="text" name="carpeta" value="${carpeta }" /></td>
					</tr>
					<tr>
						<td>Timeout (mseg):</td>
						<td><input type="text" name="timeout" value="${timeout }" /></td>
					</tr>
					<tr>
						<td>Archivo a subir:</td>
						<td><input type="file" name="archivo" value="" /></td>
					</tr>
					</table>
					 <br/>
					
					<input type="submit" name="aceptar" value="PUT Archivo" />
					<br/>

					<c:if test="${resultados!=null }">
					<p align="left"><h5  style="margin-left: 450px">Resultados</h5></p>
					<table style="margin-left: 15%;  border-collapse: collapse" border="1">
					<thead>
									<tr>

										<th>Server</th>
										<th>Puerto</th>
										<th>Usuario</th>
										<th>Clave</th>
										<th>Carpeta</th>
										<th>Timeout</th>
										<th>Resultado</th>
										<th>Hora</th>
										<th>Observación</th>
										
									</tr>
					</thead>
					<tbody>
					<c:forEach items="${resultados}" var="rtdo">
										<tr>
											<td>${rtdo.server}</td>
											<td>${rtdo.puerto}</td>
											<td>${rtdo.usuario}</td>
											<td>${rtdo.clave}</td>
											<td>${rtdo.carpeta}</td>
											<td>${rtdo.timeout}</td>
											<td>${rtdo.estado}</td>
											<td>${rtdo.hora}</td>
											<td>${rtdo.observaciones}</td>
										</tr>

					</c:forEach>
					</tbody>
					</table>
					</c:if>
				</form>
			</div>

		</div>
	</div>
			
				
				
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>
<script>
	$(document).ready(function() {
			$("#resultado").show();
    		setTimeout('$("#resultado").hide()', 6000);
		});
	</script>
</body>
</html>