<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="./comun/header.jsp" flush="true" />
<body>
	<jsp:include page="banner.jsp" flush="true" />
	<jsp:include page="menuServices.jsp" flush="true" />

	<form action="loadFile" method="post" enctype="multipart/form-data">


		<table align="center" class="tabla-creditos">

			<tr>
				<th class="certificadoLeft">Archivo CSV</th>
				<td class="certificadoLeft"><input id="file" name="fichero"
					type="file" value="" /></td>
				 
			</tr>
		</table>
		<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr><td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;"><td><tr>
        <tr>
        <td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;">
        <input type="submit"
					style="cursor: pointer" class="boton" value="Subir archivo" /> 
        </td>
        </tr>
		</table>
		<c:if test="${archivo != null}">
			<h5>${archivo}</h5>
		</c:if>

	</form>
	<h3>Errores</h3>
	<br />
	<h3 style="color: red;">${errores}</h3>
	<br />
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;">
				Proceso manual. </td>

		</tr>
	</table>
	<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;"><a
				href="<c:url value='/procesarFTP'/>"><input type="button"
					style="cursor: pointer" class="boton" value="Proceso manual" /> </a></td>
		</tr>
	</table>
	<br />
	<br />

	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;">
				 Datos respuesta de previred. </td>

		</tr>
	</table>
	<table align="center" class="tabla-creditos">
		<thead>
			<tr>
				<th class="certificadoLeft">Nombres</th>
				<th class="certificadoLeft">Apellido Paterno</th>
				<th class="certificadoLeft">Apellido Materno</th>
				<th class="certificadoLeft">Rut Afiliado</th>
				<th class="certificadoLeft">Estado</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${afiliados}" var="afi">
				<tr>
					<td class="certificadoLeft">${afi.NOMBRES}</td>
					<td class="certificadoLeft">${afi.APEPAT}</td>
					<td class="certificadoLeft">${afi.APEMAT}</td>
					<td class="certificadoLeft">${afi.RUTAFI}-${afi.DVAFI}</td>
					<td class="certificadoLeft"><c:if test="${afi.CODREAFI == 0}">Correcto</c:if>
						<c:if test="${afi.CODREAFI != 0}">
							<span style="color: red">Erroneo</span>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
	<br />
	<br />
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;">
				Archivos cargados en el sistema. </td>

		</tr>
	</table>
	<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;"><a
				href="<c:url value='/actualizar'/>"><input
					style="cursor: pointer" type="button" class="boton"
					value="Actualizar" /></a></td>
		</tr>
	</table>

	<table align="center" class="tabla-creditos">
		<thead>
			<tr>
				<th class="certificadoLeft">Nombre</th>
				<th class="certificadoLeft">Fecha</th>
				<th class="certificadoLeft">Estatus</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${erroresPrevired}" var="error">
				<tr>
					<td class="certificadoLeft">${error.NOMBRE}</td>
					<td class="certificadoLeft"><fmt:parseDate
							value="${error.FECHACREACION}" pattern="yyyy-MM-dd HH:mm"
							var="fecha" /> <fmt:formatDate value="${fecha}"
							pattern="dd/MM/yyyy HH:mm" /></td>
					<td class="certificadoLeft"><span style="color: red;">Erroneo</span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br />
	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;">
				Archivo de errores. </td>

		</tr>
	</table>
	<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
		<tr>
			<td class="bienvenida"
				style="border: 0 px;font-size:20px;border-color: #FFFFFF; text-align: left;"><a href="<c:url value='/getError'/>"><input
					type="button" style="cursor: pointer" class="boton"
					value="Actualizar" /></a></td>
		</tr>
	</table>
	<table align="center" class="tabla-creditos">
		<thead>
			<tr>
				<th class="certificadoLeft">Nombre</th>
				<th class="certificadoLeft">Linea Error</th>
				<th class="certificadoLeft">C&oacute;digo error</th>
				<th class="certificadoLeft">Glosa error</th>
				<th class="certificadoLeft">Fecha error</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${erroresResp}" var="error">
				<tr>
					<td class="certificadoLeft">${error.NOMARCH}</td>
					<td class="certificadoLeft">${error.LINERR}</td>
					<td class="certificadoLeft">${error.CODERR}</td>
					<td class="certificadoLeft">${error.GLOSAERR}</td>
					<td class="certificadoLeft"><fmt:parseDate
							value="${error.FECHERR}" pattern="yyyy-MM-dd HH:mm" var="fecha" />
						<fmt:formatDate value="${fecha}" pattern="dd/MM/yyyy HH:mm" /></td>
					<td class="certificadoLeft"><span style="color: red;">Erroneo</span></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>