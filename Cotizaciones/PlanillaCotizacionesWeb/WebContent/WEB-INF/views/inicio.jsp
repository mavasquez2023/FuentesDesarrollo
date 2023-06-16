<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Comprobante Aporte Asfam PDF -  La Araucana</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
</head>
<body>

	<div class="container">
		<h2>Comprobante Aporte Asfam PDF</h2>
		<br>

		<!--form action="datos.do" method="post">
			<div class="row">
				<div class="col-6 col-md-4">
					<div class="form-group">
						<label for="rutEmpresa">Rut empresa (lista de Ruts sin dígito separados por coma)</label> <input type="text"
							class="form-control" id="rutEmpresa" name="rutEmpresa"
							placeholder="Rut empresa">
					</div>
					
					<div class="form-group">
						<label for="periodo">Periodo (aaaamm)</label> <input type="text"
							class="form-control" id="periodo" name="periodo"
							placeholder="Periodo">
					</div>
				
					<div class="form-group">
						<label for="periodo">Código Oficina</label> <input type="text"
							class="form-control" id="codigoOficina" name="codigoOficina"
							placeholder="codigoOficina">
					</div>
					
					<div class="form-group">
						<label for="periodo">Código Sucursal</label> <input type="text"
							class="form-control" id="codigoSucursal" name="codigoSucursal"
							placeholder="codigoSucursal">
					</div>
					<br>
					<button type="submit" class="btn btn-primary">Buscar</button>
				</div>
			</div>
			<br>
			<br>
		</form -->
		<c:if test="${mensaje != null }">

			<h3>${mensaje} </h3>
		</c:if>
		<c:if test="${cabeceras.size() > 0 }">
			<table class="table">
				<thead>
					<tr>
						<th>Periodo</th>
						<th>Rut Empresa</th>
						<th>Razón social</th>
						<th>Código oficina</th>
						<th>Sucursal</th>
						<th>Descargar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cabeceras}" var="cab">
						<tr>
							<td>${cab.periodo}</td>
							<td>${cab.rutEmpresa}</td>
							<td>${cab.razonsocial}</td>
							<td>${cab.codofi}</td>
							<td>${cab.sucursal}</td>
							<td><a
								href="<c:url
							value='/reporte.do?rut=${cab.rutEmpresa}&per=${cab.periodo}&codofi=${cab.codofi}&suc=${cab.sucursal}'/>">descargar</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
		crossorigin="anonymous"></script>
</body>
</html>