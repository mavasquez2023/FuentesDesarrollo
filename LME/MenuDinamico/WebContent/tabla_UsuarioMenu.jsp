<%@ include file="layout/headerJsp.jsp"%>
	
	 <form action="" name="form_adm_UsuarioMenu" id="form_adm_UsuarioMenu">
		<input type="hidden" id="op" name="op">		
		<table border="0" width="960">	
		<tbody>
			<tr>
				<td>
					<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 400px;" align="center">
						<table border="0" width="100%" >
							<tbody>
								<tr>
									<td>
										<h5><center>Lista de Usuarios</center></h5>
									</td>
								</tr>
								<tr>
									<td>
										<div id="contenedor-tabla">
											<div class="datagrid" style="min-height:350px; height: 350px; overflow-y: scroll;">
												<table class="tablaSinBordes">
													<thead>
														<tr>
															<th>    </th>
															<th>Rut Usuario</th>
															<th>Nombre Usuario</th>
														</tr>
													</thead>
													<tbody>
														<!--Empty y msg-->
														<!-- Iterate lista de usuarios existentes en el sistema. -->
														<c:choose>
															<c:when test="${empty admUserList}">
																<tr>
																	<td colspan="6" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
																		<center>${msgListNull}</center>
																	</td>
																</tr>
															</c:when>
															<c:otherwise>
																<!-- Iterate lista de usuarios existentes en el sistema. -->
																<c:forEach items="${admUserList}" var="admUserList">	
																	<tr>
																		<td>
																			<input class="radioCHK" type="radio" id="rd_User" name="rd_User" value="${admUserList.rut_user}" onclick='javascrip:cargar_MenuPorUsuario("${admUserList.rut_user}")'/>
																		</td>
																		<td>${admUserList.rut_user}</td>
																		<td>${admUserList.nombre_user}</td>
																	</tr>
																</c:forEach>
															</c:otherwise>
														</c:choose>
														<!--END ITERATE--->
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
				<td>
					<div class="MarcoFormulario ui-widget-content ui-corner-all" style="min-height:400px; width: 540px;" align="center">
						<table border="0" width="100%" >
							<tbody>
								<tr>
									<td>
										<h5><center>Lista de Men&uacute;</center></h5>
									</td>
								</tr>
								<tr>
									<td>
										<div id="contenedor-tabla">
											<div class="datagrid" style="min-height:350px; height: 350px; overflow-y: scroll;">
												<table class="tablaSinBordes" >
													<thead>
														<tr>
															<th>    </th>
															<th>Id. &iacute;tem men&uacute;</th>
															<th>Etiqueta</th>
														</tr>
													</thead>
													<tbody>
														<!--Empty y msg-->
														<!-- Iterate lista de ítems de menú existentes en el sistema. -->
														<c:choose>
															<c:when test="${empty admMenuList}">
																<tr>
																	<td colspan="5" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
																		<center>${msgListNull}</center>
																	</td>
																</tr>
															</c:when>
															<c:otherwise>
																<!-- Iterate lista de ítems de menú existentes en el sistema. -->
																<c:forEach items="${admMenuList}" var="admMenuList">
																	<tr>
																		<td>
																			<input type="checkbox" id="${admMenuList.codMenu}" name="chk_Menu" value="${admMenuList.codMenu}" />
																		</td>
																		<td>${admMenuList.codMenu}</td>
																		<td>${admMenuList.etiqueta}</td>
																	</tr>
																</c:forEach>
															</c:otherwise>
														 </c:choose>
														<!--END ITERATE--->
													</tbody>
												</table>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
		
    </form>
		
	
	
	