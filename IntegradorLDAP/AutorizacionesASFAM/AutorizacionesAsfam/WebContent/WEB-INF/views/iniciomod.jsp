<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modificaciones Asfam PDF -  La Araucana</title>
<link type="text/css" href="<c:url value='/css/certificado.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui-1.9.2.custom.js"></script>
<script type="text/javascript">
	function lock(boton, reg) {
		boton.disabled="disabled";
		boton.style.background= "#E7CB28";
		$('#loading').show();
		setTimeout("document.getElementById('reg" + reg + "').disabled=false;", 3100);
		setTimeout("document.getElementById('reg" + reg + "').style.background='#296FB0';", 3100);
		setTimeout("$('#loading').hide()", 3000);
	}
</script>
</head>
<body>

	<div class="container">
		<h2>Modificaciones Asfam PDF</h2>
		<br>

		<form action="datos.do" method="post">
			<div class="row">
				<div class="col-6 col-md-4">
					<div class="form-group">
						<label for="carpeta">Seleccione Tipo de Movimiento)</label> 
							<select class="form-control" id="tipomov" name="tipomov" >
									<option selected value='AUT'>Autorizaciones Asfam</option>
									<option value='MOD' selected="selected">Nómina Modificaciones Asfam</option>
								</select>
					</div>
					<div class="form-group">
						<label for="rutEmpresa">Rut empresa (lista de Ruts sin dígito separados por coma)</label> <input type="text"
							class="form-control" id="rutEmpresa" name="rutEmpresa" value="${rutEmpresa }"
							placeholder="Rut empresa">
					</div>
					
					<div class="form-group">
						<label for="periodo">Periodo (aaaamm)</label> <input type="text"
							class="form-control" id="periodo" name="periodo" value="${periodo }" 
							placeholder="Periodo">
					</div>
				
					<br>
					<button type="submit" class="btn btn-primary">Buscar</button>
				</div>
			</div>
			<br>
			<br>
		</form >
		<c:if test="${mensaje != null }">

			<h3>${mensaje} </h3>
		</c:if>
		<c:if test="${cabeceras.size() > 0 }">
			<table class="table" style="width: 70%">
				<thead>
					<tr>
						<th>Periodo</th>
						<th>Rut Empresa</th>
						<th>Razón social</th>
						<th>Oficina</th>
						<th>Sucursal</th>
						<th>Descargar</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${cabeceras}" var="cab" varStatus="numreg">
						<tr>
							<td>${cab.periodo}</td>
							<td>${cab.rutEmpresa}-${cab.dvEmpresa}</td>
							<td>${cab.razonSocial}</td>
							<td>${cab.oficina}</td>
							<td>${cab.sucursal}</td>
							<td><a
								href="<c:url
							value='/reporteMOD.do?rutemp=${cab.rutEmpresa}&periodo=${cab.periodo}&oficina=${cab.oficina}&sucursal=${cab.sucursal}'/>"><button id="reg${numreg.count }" type="button" class="boton" onclick="lock(this, '${numreg.count}');">descargar</button></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div id="loading" style="position:fixed; top:22%; display:none; left:35%;  z-index: auto" >
			<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
		</div>
</body>
</html>