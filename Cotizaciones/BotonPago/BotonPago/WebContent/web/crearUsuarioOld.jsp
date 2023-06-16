<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Crear usuario</h1>
			<br> <br>
			<div class="centraTabla">
				<c:if test="${not empty tipo}">
					<div class="${tipo}">
						<strong>Mensaje:</strong>${mensaje}
					</div>
				</c:if>
				<form id="formulario" action="crearUser.do" method="post">
					<table class="formV">
						<tbody>
							<tr>
								<td><label for="nombre">Id:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="id"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Nombre:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="nombre"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Apellido paterno:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="apellidoPaterno"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Apellido materno:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="apellidoMaterno"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Email:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="email"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Fono:&nbsp;</label></td>
								<td><input class="inputCorto" type="text" name="fono"></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="sexo">Sexo:&nbsp;</label></td>
								<td><select name="sexo" class="inputCorto">
										<option value="F">Femenino</option>
										<option value="M">Masculino</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<td><label for="nombre">Perfil:&nbsp;</label></td>
								<td><select multiple="multiple" name="perfil" class="inputCorto">
										<option value="admin">Administrador</option>
										<option value="deudor">Deudor</option>
										<option value="ejecutivo">Ejecutivo</option>
								</select></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td><input id="boton" class="boton" type="submit" name="ingresar" value="Ingeresar"></td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div align="right"></div>
		</div>
		<br>
	</div>
		<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
</body>
</html>