<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
		<%-- <div class="menu">
				<jsp:include flush="true" page="/web/includes/opciones.jsp"></jsp:include> 
		</div> --%>
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<h1>Recuperación</h1>
			<br> <br>
			<div id="datosAfiliado">
				<span><b>RUT: </b>${rut} 12.3456.789-0 </span><span><b> Nombre:</b>${nombre} Juan Perez</span>
			</div>
			<br> <br> <br>
			<table id="rounded-corner">
				<caption>Creditos con problemas en la recuperación</caption>
				<thead>
					<tr>
						<th><input type="checkbox" name="selectAll" value="all" title="seleccionar todos"></th>
						<th>Rut</th>
						<th>Nombre</th>
						<th>Folio</th>
						<th>Monto pago</th>
						<th>Fecha pago</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Rhiannon Haley</td>
						<td>012-968536521</td>
						<td>$ 65465</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Deanna Faulkner</td>
						<td>410-56854852</td>
						<td>$ 45854</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Winifred T. Fischer</td>
						<td>125-365895862</td>
						<td>$ 875827</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Gwendolyn Gordon</td>
						<td>856-145875896</td>
						<td>$ 786525</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Steel Collins</td>
						<td>454-456789852</td>
						<td>$ 45472</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Walker U. Griffin</td>
						<td>784-4582145852</td>
						<td>$ 8567</td>
						<td>01-02-2014</td>
					</tr>
					<tr>
						<td><input type="checkbox" name="selectAll" value="all"></td>
						<td>123456789-0</td>
						<td>Faith G. Terrell</td>
						<td>654-145458758</td>
						<td>$ 785788</td>
						<td>01-02-2014</td>
					</tr>
				</tbody>
			</table>
			<br>
			<div align="right">
				<input type="submit" class="boton" value="Recuperar">
			</div>
		</div>
	</div>
		<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>
	</fieldset>
</body>
</html>
