<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<jsp:include flush="true" page="comun/inports.jsp"></jsp:include>
</head>
<jsp:include flush="true" page="comun/header.jsp"></jsp:include>
<body>
	<div align="center">
		<br>
		<h1>PAGO DE CRÉDITO EN LÍNEA.</h1>
		<br> <br> <br>
		<p class="texto">Por favor, ingrese su rut y clave de acceso</p>
		<form method="post" action="j_security_check" id="loginForm">
			<table>
				<tbody>
					<tr>
						<td><label for="username">Rut:</label>
						</td>
						<td><input type="text" name="username" id="uname"> <input type="hidden" name="j_username" id="name">
						</td>
						<td><font class="textofino">&nbsp;&nbsp;(incluir gui&oacute;n y d&iacute;gito verificador)</font>
						</td>
					</tr>
					<tr>
						<td><label for="pswd">Contrase&ntilde;a:</label>
						</td>
						<td><input type="password" name="j_password" id="pswd"></td>
						<td></td>
					</tr>
				</tbody>
			</table>
			<input type="submit" name="action" id="submiter" class="boton" value="Aceptar"> <br /> <br />
			<div id="mensajesError"></div>
		</form>
		<br> <br>
		<div class="textofino">
			¿Olvidó su clave? La puede recuperar &nbsp;<a href="<%=request.getContextPath()%>/olvidoClave.jsp">aquí</a>
		</div>
		<div class="textofino">
			Si no tiene clave la puede solicitar &nbsp;<a href="<%=request.getContextPath()%>/solicitaClave.jsp">aquí</a>
		</div>
	</div> 
	<br>
	<br>
	<jsp:include flush="true" page="comun/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {

		if (window.opener) {
			window.opener.location.reload();
			window.close();
		}

		$("#loginForm").validate({
			rules : {
				username : {
					required : true,
					rut : true
				},
				j_password : {
					required : true
				}
			},
			errorPlacement : function(error, element) {
				error.appendTo("#mensajesError");
			},
			messages : {
				"username" : {
					required : "* El Rut es requerido."
				},
				"j_password" : {
					required : "* La contraseña es requerida."
				},
			}
		}); //fin jquery validate
		$("#uname").Rut({
			format_on : 'keyup'
		});

		$("#submiter").click(function() {
			var value = $("#uname").val();
			value = value.split(".").join("");
			$("#name").val(value);
		});
	}); //fin document ready
</script>
</html>
