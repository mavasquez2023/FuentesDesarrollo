<%@ include file="layout/headerJsp.jsp"%>
    	<div id="loadGif" class="loadGif">
			<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
		</div>
    	<div class="datagrid">    	
			<table class="tablaSinBordes">
				<thead>
					<tr>
						<th>Rut Usuario</th>
						<th>Nombre Usuario</th>
						<th>Apellido Paterno</th>
						<th>Apellido Materno</th>
						<th>Correo Electr&oacute;nico</th>
						<th>Opciones</th>
					</tr>
				</thead>
				<tbody>
					<!--Empty y msg-->
					<c:choose>
						<c:when test="${empty userList}">
							<tr>
								<td colspan="6" style="background-color: #B0C4DE; color: white; 
									font-weight: bolder;">
									<center>${msgListNull}</center>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- Iterate lista de usuarios existentes en el sistema. -->
							<c:forEach items="${userList}" var="userList">	
								<tr>
									<td>${userList.rut_user}</td>
									<td>${userList.nombre_user}</td>
									<td>${userList.ape_paterno}</td>
									<td>${userList.ape_materno}</td>
									<td>${userList.email_user}</td>
									<td>
										<input type="button" class="boton update_usuario" onclick="javascript:openActualizar_Usuario('${userList.id_user}')" value="Editar" /> 
										<input type="button" class="boton delete_usuario" onclick="javascript:openEliminar_Usuario('${userList.id_user}')" value="Eliminar" />
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<!--END ITERATE--->
				</tbody>
			</table>
		</div>
	
	
	