
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
				<form class="form" id="mail" action="mail.do" method="POST" enctype="multipart/form-data">
					<h3>MAIL - Servicios</h3>
					<table>
					<tr>
						<td>Mail Session:</td>
						<td><input type="text" name="mailsession" value="${mailsession }" /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" name="email" value="${email }" /></td>
					</tr>
					<tr>
						<td>Subject:</td>
						<td><input type="text" name="subject" value="${subject }" /></td>
					</tr>
					<tr>
						<td>Body:</td>
						<td><input type="text" name="body" value="${body }" size="60"/></td>
					</tr>
					
					</table>
					 <br/>
					
					<input type="submit" name="aceptar" value="Enviar" />
					<br/>
					
					<c:if test="${resultados!=null }">
					<table border="0">
					<tr><td>&nbsp;</td></tr>
					<tr><td><b>Resultado:&nbsp;</b></td>
						<td>${resultados}</td>
					</tr>
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