<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- Le styles -->
<link href="dist/css/bootstrap.css" rel="stylesheet">
<link href="css/estilo.css" rel="stylesheet">

<link href="css/redmond/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script type="text/javascript" src="js/ie.js"></script>
<script type="text/javascript" src="js/comun.js"></script>
<script type="text/javascript" src="js/cargar_datos.js"></script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/corev2.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style>

label {
	display: inline-block;
	width: 5em;
}

fieldset div {
	margin-bottom: 2em;
}

fieldset .help {
	display: inline-block;
}

.ui-tooltip {
	width: 210px;
}
</style>
</head>
<body> 
<form  id="sucursales" action="VentanaServlet" name="sucursales" method="post">
		
		<table align="center" class="tabla_formulario" cellspacing="2" style="width: 100%">
		<tr><td style="width: 150px">Rut Encargado:</td><td>${encargado.rutEncargado }</td></tr>
		<tr><td>Nombre:</td><td>${encargado.nombreEncargado }</td></tr>
		</table>
		<br>
		<table align="center" style="width: 100%">
		<tr><td colspan="3">
			<div id="informacion" class="informacion"></div>
			<div id="informacion_error" class="informacion"></div>
			<div id="informacion_warning" class="informacion"></div>
		</td></tr>
		</table>
		<table align="center" class="tabla_formulario" cellspacing="2" style="width: 70%">
		
		<tr class="tr_formulario"><td class="titulo_campo" align="center" colspan="3">Lista de Oficinas y Sucursales de esta Empresa</td>
		<tr class="tr_formulario"><td class="titulo_campo" align="center" style="width: 50px">Todas <input type="checkbox" name="allSucursal" id="allSucursal" value="All" <c:if test='${encargado.allOffice== "SI"}'>checked="checked"</c:if> onclick="selectAll(sucursales.codSucursal, this.checked)"/></td><td class="titulo_campo" align="center" >Oficina</td><td class="titulo_campo_check" align="center">Sucursal</td></tr>

					<c:forEach var="sucursal" varStatus="vs" items="${encargado.sucursales}">
						<tr class="tr_formulario" ><td class="data_campo" style="width: 50px; background-color: #ffffff"><input type="checkbox" name="codSucursal" id="codSucursal" value="${sucursal.oficina}:${sucursal.sucursal}" <c:if test='${sucursal.estado== "1"}'>checked="checked"</c:if> onclick="unSelect(sucursales.allSucursal, this.checked)"/></td><td class="data_campo" style="background-color: #ffffff">${sucursal.nombreOficina}</td><td class="data_campo" style="background-color: #ffffff">${sucursal.nombreSucursal}</td></tr>
					</c:forEach>
						
						<tr class="tr_formulario">
						<td class="data_campo_check" colspan="3"><br><input type="submit" class="titulo_campo" style="height: 30px" name="guardar" value="Guardar" /></td></tr>
						</table>
	<br>
	<input type="hidden" name="action" value="GUARSUC"/>
</form>
<script type="text/javascript">
	<c:if test='${update_ok=="1"}'>
			mostrarInfo("Operación realizada exitosamente!");
	</c:if>
	<c:if test='${update_ok=="0"}'>
			mostrarInfoError("ERROR, operación no realizada.");
	</c:if>
</script>
</body>
</html>