<%@ include file="/comun/tld.jsp"%>
<table class="tabla-principal">
	<tr>
		<th>Año tabla</th>
		<th>Código producto</th>
		<th>Cuota desde</th>
		<th>Cuota hasta</th>
		<th>% Condonación</th>
		<th>fecha cambio</th>
		<th>Hora cambio</th>
		<th>Usuario</th>
		<th>Acci&oacute;n</th>
	</tr>
	<c:forEach items="${sinat04List}" var="sinat04">
		<tr>
			<td class="anopro">${sinat04.anopro}</td>
			<td class="codpro">${sinat04.codpro}</td>
			<td class="nrodes">${sinat04.nrodes}</td>
			<td class="nrohas">${sinat04.nrohas}</td>
			<td>${sinat04.porcen}</td>
			<td>${sinat04.fecsis}</td>
			<td>${sinat04.horsis}</td>
			<td>${sinat04.iduser}</td>
			<td>
				<input type="image" class="editar" src="<%=request.getContextPath() %>/img/file_edit.png" title="editar condonación de gastos de cobranza"/>
				<input type="image" class="eliminar" src="<%=request.getContextPath() %>/img/file_delete.png" title="eliminar condonación de gastos de cobranza"/>
				<!-- <a href="javascript:void(0)" >Eliminar</a> / 
				<a href="javascript:void(0)" class="editar">Modificar</a>  -->
			</td>
		</tr>
	</c:forEach>
</table>