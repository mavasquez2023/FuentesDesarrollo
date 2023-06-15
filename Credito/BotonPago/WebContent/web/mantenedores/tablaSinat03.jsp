<%@ include file="/comun/tld.jsp"%>
<table class="tabla-principal">
	<tr>
		<th>Código tabla</th>
		<th>% Condonación</th>
		<th>Fecha cambio</th>
		<th>Hora cambio</th>
		<th>Usuario</th>
		<th>Acci&oacute;n</th>
	</tr>
	<c:forEach items="${sinat03List}" var="sinat03">
		<tr>
			<td class="elCodigo">${sinat03.tipcod}</td>
			<td>${sinat03.porcen}</td>
			<td>${sinat03.fecsis}</td>
			<td>${sinat03.horsis}</td>
			<td>${sinat03.iduser}</td>
			<td>
				<input type="image" class="editar" src="<%=request.getContextPath() %>/img/file_edit.png" title="editar condonación gravamenes"/>
				<input type="image" class="eliminar" src="<%=request.getContextPath() %>/img/file_delete.png" title="eliminar condonación gravamenes"/>
				<!-- <a href="javascript:void(0)" >Eliminar</a> / 
				<a href="javascript:void(0)" class="editar">Modificar</a>  -->
			</td>
		</tr>
	</c:forEach>
</table>