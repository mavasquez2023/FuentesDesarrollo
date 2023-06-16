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
	<fieldset class="form-fieldset">
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Asignar permisos de la aplicación</h1>
			<br> <br>
			<div class="textofino">
				<b>Nota:</b>&nbsp;En esta opción usted puede cambiar los roles o perfiles para un usuario de la aplicación.
				<html:errors />
			</div><br>
			<form action="otorgaPermiso.do?op=busca" method="post" id="otorgaPermisosForm">
			<div class="field li">
				<label>Ingrese rut del usuario</label>
				<input class="inputCorto" type="text" name="id" id="id" maxlength="13">
				<html:errors property="rut" />
			</div>
			<div class="field">
				<input class="boton" type="submit" value="Buscar">
			</div>
			
			</form>
			<br> <br>
			<c:if test="${not empty tipo}">
				<div class="${tipo}">
					<strong>Mensaje:</strong>&nbsp; ${mensaje}
				</div>
			</c:if>
			<c:if test="${not empty id}">
				<div>
					<form action="otorgaPermiso.do?op=update" method="post">
						<div class="centraTabla">
							<table>
								<tr>
									<td><b>Id:&nbsp;</b>
									</td>
									<td>${id}<input type="hidden" name="id" value="${id}"></td>
								</tr>
								<tr>
									<td><b>Nombre:&nbsp;</b>
									</td>
									<td>${nombre}</td>
								</tr>
								<tr>
									<td><b>Email:&nbsp;</b>
									</td>
									<td>${mail}</td>
								</tr>
								<tr>
									<td><b>Rol admin:&nbsp;</b></td>
									<c:if test="${not empty admin}">
										<td><input type="checkbox" name="roles" checked="checked" value="${admin}">
										</td>
									</c:if>
									<c:if test="${empty admin}">
										<td><input type="checkbox" name="roles" value="admin">
										</td>
									</c:if>
								</tr>
								<tr>
									<td><b>Rol deudor:&nbsp;</b></td>
									<c:if test="${not empty deudor}">
										<td><input type="checkbox" name="roles" checked="checked" value="${deudor}">
										</td>
									</c:if>
									<c:if test="${empty deudor}">
										<td><input type="checkbox" name="roles" value="deudor">
										</td>
									</c:if>
								</tr>
								<tr>
									<td><b>Rol ejecutivo:&nbsp;</b></td>
									<c:if test="${not empty ejecutivo}">
										<td><input type="checkbox" name="roles" checked="checked" value="${ejecutivo}">
										</td>
									</c:if>
									<c:if test="${empty ejecutivo}">
										<td><input type="checkbox" name="roles" value="ejecutivo">
										</td>
									</c:if>
								</tr>
								<tr>
									<td></td>
									<td><input class="boton" type="submit" value="Ok">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</div>
			</c:if>
		</div>
		<br>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>

<script type="text/javascript">

$(document).ready(function () {
        $("#otorgaPermisosForm").validate({
            rules: {
                id: {required: true, rut:true}
            }
        }); //fin jquery validate
        $("#id").Rut({
			format_on : 'keyup'
		}); 
    }); //fin document ready

</script>
</html>