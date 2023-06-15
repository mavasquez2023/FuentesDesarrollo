<%@ include file="../../comun/headerJsp.jsp"%>
<html:html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="../../cssKiosco/certificado.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
</head>
<body>
	<div class="titulo">Certificado de estado de créditos</div>
	<br>
	<br>
	<!-- Ingreso de rut y filtros -->
	<html:form action="/ingresar_filtro" styleClass="form">
		<div id="ingreso-datos">
			Rut:
			<html:text property="rut"></html:text>
			Estado:
			<html:select property="estado">
				<html:option value="-1">Seleccione un estado</html:option>
				<html:option value="1">Vigente</html:option>
				<html:option value="2">Moroso Normal</html:option>
				<html:option value="3">Moroso en estado Judicial</html:option>
				<html:option value="4">Moroso por Quiebra</html:option>
				<html:option value="5">Moroso incobrable</html:option>
				<html:option value="6">Moroso Castigada</html:option>
				<html:option value="7">Cancelado con seguro de desgravamen</html:option>
				<html:option value="8">Crédito Nulo</html:option>
				<html:option value="9">Crédito cancelado</html:option>
			</html:select>
			<input type="submit" value="Obtener créditos" class="boton" />
		</div>
	</html:form>
	<!-- / Ingreso de rut y filtros -->
	
	<!-- Tabla lista de créditos -->
	<logic:notEmpty name="creditos" >
		La lista de créditos no esta vacia
	</logic:notEmpty>
	<c:if test="${!empty creditos}" >
		<html:form action="/certificado_estado_creditos">
			<div id="tabla-creditos">
				<table>
					<thead>
						<tr>
							<th><input type="checkbox" name="checkall" value=""></th>
							<th>Folio</th>
							<th>Monto Sol.</th>
							<th>F. Otorg.</th>
							<th>F. Cancel.</th>
							<th>Cuota a Descuento</th>
							<th>Cant. Cuotas</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${creditos}" var="listadoCreditos">
							<tr>
								<td><input type="checkbox" name="folio[]" value='${listadoCreditos.folio}'></td>
								<td><bean:write name="listadoCreditos" property="folio" /></td>
								<td><bean:write name="listadoCreditos" format="$#0.0##" property="mtoSolicitado" /></td>
								<td><bean:write name="listadoCreditos" property="fOtorgamiento" /></td>
								<td><bean:write name="listadoCreditos" property="fCancelacion" /></td>
								<td><bean:write name="listadoCreditos" property="mtoCuota" /></td>
								<td><bean:write name="listadoCreditos" property="cantCuotas" /></td>
								<td align="left"><bean:write name="listadoCreditos" property="estado" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<br>
				<div align="right">
					<input type="submit" value="Imprimir" class="boton" />
				</div>
			</div>
		</html:form>
	</c:if>
	<!-- / Tabla lista de créditos -->
	<html:errors property="folio" />
	<html:errors property="rut" />
</body>
</html:html>