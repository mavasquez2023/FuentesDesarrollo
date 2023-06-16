<%@ include file="layout/headerJsp.jsp"%>


<html>
<head>
	<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
 	<link href="<%=request.getContextPath()%>/css/tree.css" rel="stylesheet" type="text/css"> 
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/CollapsibleLists.js"></script>
	<title>Administraci&oacute;n &iacute;tem de men&uacute;</title>
</head>
<body>
	<div class="container">
		<table align="center" width="750px;">
			<tr>
				<td style="width: 1000px;" align="center"><IMG border="0" src="<%=request.getContextPath()%>/img/banner.jpg" width="1000px;" height="81px;">
				</td>
			</tr>
			<tr>
				<td>
					<table class="tablaSinBordes" align="center" width="1000px;">
						<tr>
							<td>
								<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 985px;" align="center">
									<table border="0" align="center" style="width: 985px;" width="1009">
										<tr>
											<td>
												<h2 class="ui-widget-header ui-widget-content ui-corner-all">
													<center>Administraci&oacute;n de &Iacute;tems de Men&uacute;</center>
												</h2>
											</td>
										</tr>
									</table>
									<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 970px;" align="center">
										<form action="" id="form_filtro_Menu" name="form_filtro_Menu">
											<input type="hidden" id="op" name="op">
											<table border="0" width="963">
												<tbody>
													<tr>
														<td height="22" width="453">
															<table border="0">
																<tbody>
																	<tr>
																		<td></td>
																		<td style="width: 304px" width="369" align="center">
																			<label class="lbl_nombre text13n">Etiqueta men&uacute;</label> 
																			<input class="txt_id_item" id="etiqueta" name="etiqueta" size="17" /> 
																			<label style="width: 10px; height: 20px" id="etiqueta_filtro_error" ></label>
																		</td>
																		<td style="width: 67px" width="33"></td>
																	</tr>
																	<tr>
																		<td style="width: 34px; height: 21px" height="21" width="34"></td>
																		<td align="right" height="21" width="369"><input type="button" class="boton" value="Buscar" onclick="javascript:buscar_Menu()">
																		</td>
																		<td style="width: 75px" width="33" height="21"><input type="button" class="boton" value="Cargar" onclick="javascript:cargarListado_Menu()">
																		</td>
																	</tr>
																	<tr>
																		<td style="width: 101px" height="22" width="34"></td>
																		<td height="22" width="369"><label id="lbl_msgBusqueda"></label>
																		</td>
																		<td></td>
																	</tr>
																</tbody>
															</table>
														</td>
														<td height="22" width="444"><label class="lbl_nombre"></label> <input type="button" class="boton" id="btnIngresar" value="Ingresar" onclick="javascript:openInsertar_Menu()">
															&nbsp; &nbsp; <input type="button" class="boton" id="btnVolver" value="Volver" onclick="javascript:goBackMenu('${nodoPadre}')">
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
								<!--<div id="data" class="bordeLimite">-->
								<form action="administracionMenu.do?op=goAdminstracionMenu" id="formMantAdmItems" type="cl.laaraucana.menudinamico.forms.Menu_Form" name="formMantAdmItems" method="post">
									<div id="contenedor-tabla_Menu">
										<div id="loadGif" class="loadGif">
											<center>
												<img src='<%=request.getContextPath()%>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br>
											</center>
										</div>
									
											<c:choose>
												<c:when test="${empty arbolMenu}">
													<tr>
														<td colspan="5" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
															<center>${msgListNull}</center>
														</td>
													</tr>
												</c:when>
												<c:otherwise>
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
																		<c:forEach items="${arbolMenu}" var="menuUsuario">
																	${menuUsuario}
																</c:forEach>
																	</ul></td>
															</tr>
														</tbody>
													</table>
													</div>
												</c:otherwise>
											</c:choose>
											<%-- 		 <table class="tablaSinBordes">
												<thead>
													<tr>
														<th>Id. &Iacute;tem men&uacute;</th>
														<th>Orden de despliegue</th>
														<th>Etiqueta</th>
														<th>URL</th>
														<th>Opciones</th>
													</tr>
												</thead>
												<tbody>
													<!--Empty y msg-->
													<!-- Iterate lista de ítems de menú existentes en el sistema. -->
													<c:choose>
														<c:when test="${empty menuList}">
															<tr>
																<td colspan="5" style="background-color: #B0C4DE; color: white; font-weight: bolder;">
																	<center>${msgListNull}</center></td>
															</tr>
														</c:when>
														<c:otherwise>
															<!-- Iterate lista de ítems de menú existentes en el sistema. -->
															<c:set var="count" value="0" />
															<c:forEach items="${menuList}" var="menuList">
																<tr>
																	<td>${menuList.codMenu}</td>
																	<td>${menuList.nivel}</td>
																	<td>${menuList.etiqueta}</td>
																	<td style='min-width: 300px;'><c:set var="count" value="${count+1}" />
																		<div id="url_jstl_${count}" class="textoJSTL" style='font-size: 12px;'>${menuList.enlace}</div></td>
																	<td style="min-width: 130px;"><input type="button" class="boton update_item" onclick="javascript:openActualizar_Menu('${menuList.codMenu}')" value="Editar" /> <input
																		type="button" class="boton delete_item" onclick="javascript:openEliminar_Menu('${menuList.codMenu}')" value="Eliminar" /></td>
																</tr>
															</c:forEach>
															<input type="hidden" id="cantRegis" value="${count}">
														</c:otherwise>
													</c:choose>
													<!--END ITERATE--->
												</tbody>
											</table>  --%>
										</div>
								
								</form> <!-- </div> -->
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<!-- START: dialog form. -->
	<div id="dialog_form_Menu" title="">
		<div id="contenedor-form_Menu">
			<form action="" id="form_Mentenedor_Menu" name="form_Mentenedor_Menu">
				<div id="loadGif" class="loadGif">
					<center>
						<img src='<%=request.getContextPath()%>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br>
					</center>
				</div>
				<input type="hidden" id="op" name="op"> <input type="hidden" id="codMenu" name="codMenu" value="">
			</form>
		</div>
	</div>
	<!-- END: dialog form -->
	<script type="text/javascript">
		//Limitar tamaño texto	
		$(document).ready(function() {
			ajusteStringMenu();
		});

		$(document).ready(function() {
			CollapsibleLists.apply();
		});
	</script>
</body>
</html>