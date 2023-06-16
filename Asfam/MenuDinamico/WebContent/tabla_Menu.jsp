<%@ include file="layout/headerJsp.jsp"%>
<link href="<%=request.getContextPath()%>/css/tree.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/CollapsibleLists.js"></script>
<div id="loadGif" class="loadGif">
	<center>
		<img src='<%=request.getContextPath()%>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br>
	</center>
</div>
	<!--Empty y msg-->
	<!-- Iterate lista de ítems de menú existentes en el sistema. -->
	<c:choose>
		<c:when test="${empty arbolMenu}">
			<tr>
				<td colspan="5" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
					<center>${msgListNull}</center></td>
			</tr>
		</c:when>
		<c:otherwise>
			<!-- Iterate lista de ítems de menú existentes en el sistema. -->
			<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 99%; height:500px; overflow: scroll; z-index:1;">
												
			<table border="0" width="100%">
				<tbody>
					<tr>
						<td>
							<h4>
								<center>Lista de Men&uacute;</center>
							</h4>
						</td>
					</tr>
					<tr>
						<td>
							<ul id="tree" class="collapsibleList tree">
								<c:set var="count" value="0" />
								<c:forEach items="${arbolMenu}" var="menuUsuario">
									<c:set var="count" value="${count+1}" />		
												${menuUsuario}
											</c:forEach>
							</ul></td>
					</tr>
				</tbody>
			</table>
			</div>
			<%-- 	
							<c:forEach items="${menuList}" var="menuList">
								<tr>
									<td>${menuList.codMenu}</td>
									<td>${menuList.nivel}</td>
									<td>${menuList.etiqueta}</td>
									<td style='min-width: 300px;'>
										<c:set var="count" value="${count+1}"/>				
										<div id="url_jstl_${count}" class="textoJSTL" style=' font-size: 12px;'>
											${menuList.enlace}
										</div>
									</td>
									<td  style="min-width: 130px;">
										<input type="button" class="boton update_item" onclick="javascript:openActualizar_Menu('${menuList.codMenu}')" value="Editar" /> 
										<input type="button" class="boton delete_item" onclick="javascript:openEliminar_Menu('${menuList.codMenu}')" value="Eliminar" />
									</td>
								</tr>
							</c:forEach> --%>
			<input type="hidden" id="cantRegis" value="${count}">
		</c:otherwise>
	</c:choose>
	<!--END ITERATE--->

<script type="text/javascript">
	//Limitar tamaño texto	
	$(document).ready(function() {
		ajusteStringMenu();
	});
	$(document).ready(function() {
		CollapsibleLists.apply();
	});
</script>