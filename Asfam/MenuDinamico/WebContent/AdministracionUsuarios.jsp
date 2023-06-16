
<%@ include file="layout/headerJsp.jsp"%>

<html>
<head>
<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
<title>Administraci&oacute;n de Usuario</title>

</head>

<body>
<div class="container">
	<table align="center" width="750px;">
		<tr>
			<td style="width:1000px;;" align="center">
				<IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg" width="1000px;" height="81px;">
			</td>
		</tr>
		<tr>
			<td>
				<table class="tablaSinBordes" align="center" width="1000px;">
					<tr>
						<td>
							<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 985px;" align="center">
								<table border="0" align="center" style="width: 985px;" width="985px">
									<tr>
										<td>
											<h2 class="ui-widget-header ui-widget-content ui-corner-all">
												<center>Administraci&oacute;n de Usuarios</center>
											</h2>
										</td>
									</tr>
								</table>
								<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 980px;" align="center">
								<form action="" id="form_filtro_Usuario" name="form_filtro_Usuario">
									<input type="hidden" id="op" name="op">
									<table border="0" width="963">
										<tbody>
											<tr>
												<td height="22" style="height: 22px" width="39"></td>
												<td height="22" width="453">
													<table border="0">
														<tbody>
															<tr>
																<td style="width: 161px" width="37">
																</td>
																<td style="width: 338px" width="619">
																	<label class="lbl_nombre text13n">Rut Usuario</label> 
																	<input class="txt_campo" id=rut_user name="rut_user" size="62"> 
																	<label style="width: 10px; height: 20px" id="rut_user_filtro_error"></label>
																</td>
																<td style="width: 67px" width="316">
																</td>
															</tr>
															<tr>
																<td style="width: 37px" width="37">
																</td>
																<td align="right" width="619">
																	<input type="button" class="boton" value="Buscar" onclick="javascript:buscar_Usuario()" />
																</td>
																<td style="width: 75px" width="316">
																	<input type="button" class="boton" value="Cargar" onclick="javascript:cargarListado_Usuarios()" />
																</td>
															</tr>
															<tr>
																<td style="width: 101px" width="37"></td>
																<td width="619">
																	<label id="lbl_msgBusqueda"></label>
																</td>
																<td width="316"></td>
															</tr>
														</tbody>
													</table>
												</td>
												<td height="22" width="444">
													 <label class="lbl_nombre"></label>
													 <input type="button" class="boton" id="btnIngresar" value="Ingresar" onclick="javascript:openInsertar_Usuario()" />
													&nbsp; &nbsp;
														 <input type="button" class="boton" id="btnVolver" value="Volver" onclick="javascript:goBackMenu('${nodoPadre}')">	
												</td>
												<td height="22" width="42">
												</td>
											</tr>
										</tbody>
									</table>
								</form>
							</div>	
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div id="data" class="bordeLimite">
								<form action="administracionUsuario.do?op=goAdminstracionUsuario" id="formMantUsuarios" 
									type="cl.laaraucana.menudinamico.forms.Usuario_Form" name="formMantUsuarios" 
									method="post">
									<div id="contenedor-tabla_Usuario">
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
																<td colspan="6" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
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
									</div>
								</form>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>	
	</table>
</div>


<!-- START: dialog form. -->

<div id="dialog_form_Usuario" title="">		
	<div id="contenedor-form_Usuario">
			 
		<form action="" id="form_Mentenedor_Usuario" name="form_Mentenedor_Usuario">
			<div id="loadGif" class="loadGif">
				<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
			</div>
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="id_user" name="id_user" value="">
			
		</form>
	</div>
</div>

<div id="dialogoFormularioUsuario" title="Atención" style="display: none;">
	<p>
		<span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"> </span>
		Se eliminara el usuario seleccionado
		¿Realmente desea continuar?
	</p>
</div>

<!-- END: dialog form -->

</body>
</html>