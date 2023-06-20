<%@ include file="layout/headerJsp.jsp"%>
<html>
<head>
	<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/CollapsibleLists.js"></script>
	
	<link href="<%=request.getContextPath()%>/css/tree.css" rel="stylesheet" type="text/css"> 
	
	<title>Administració de menú por usuarios</title>
	<style type="text/css">
	
	
	
		#form_adm_UsuarioMenu table td{
		vertical-align: top;
		}
		#menuOpcionesUsuairo{
			text-align: left;		
		}
	</style>
</head>
<body>
	<div class="container">
			<table align="center" width="750px;">
				<tr>
					<td style="width:1000px;;" align="center"><IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg" width="1000px;" height="81px;">
					</td>
				</tr>
				<tr>
					<td>
						<table class="tablaSinBordes" align="center" width="1000px;">
							<tr>
								<td>
									<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 985px;" align="center">
										<table border="0" align="center" style="width: 985px;">
										<tr>
											<td>
												<h2 class="ui-widget-header ui-widget-content ui-corner-all">
													<center>Administraci&oacute;n de men&uacute; por usuarios</center>
												</h2>
											</td>
										</tr>
										</table>
										<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 970px;" align="center">
										<table border="0" width="970">
											<tbody>
												<tr>
													<td>
														<div id="contenedor-tabla_UsuarioMenu"><!-- start, contenedor tablas usuarios y menus -->
														<form action="" name="form_adm_UsuarioMenu" id="form_adm_UsuarioMenu">
														<input type="hidden" id="op" name="op">
														<table border="0" width="960">	
															<tbody>
																<tr>
																	<td>
																		<div class="MarcoFormulario ui-widget-content ui-corner-all" style="min-height:400px; width: 400px;" align="center">
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
																												<th> </th>
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
																		<div id="menuOpcionesUsuairo" class="MarcoFormulario ui-widget-content ui-corner-all" style="min-height:400px; width: 540px; z-index:1;">
																			<table border="0" width="100%" >
																				<tbody>
																					<tr>
																						<td>
																							<h5><center>Lista de Men&uacute;</center></h5>
																						</td>
																					</tr>
																					<tr>
																						<td>
																							<ul id="tree" class="collapsibleList tree"  style="min-height:350px; height: 350px; overflow-y: scroll;">
																								<c:forEach items="${arbolMenu}" var="menuUsuario">
																									${menuUsuario}
																								</c:forEach>
																							</ul>
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
														</div><!-- end, contenedor tablas usuarios y menus -->
													</td>
												</tr>
												<tr>
													<td>
														
														<table border="0" width="962">
															<tbody>
																<tr>
																	<td style="width: 500px; text-align: right;" width="500px" height="55px;">
																		<center><label id="msgAccion_admMxU"> </label></center>
																	</td>
																	<td style="width: 266px; text-align: right; " width="266px" height="55px;">
																		<div id="loadGif" class="loadGif" >
																			<img src='<%=request.getContextPath() %>/img/load_50px.GIF' id="imgLoad" name="imgLoad">																		
																		</div>
																	</td>
																	<td width="181">
																		<input type="button" id="btn_Agregar" value="Actualizar Menu" class="boton" onclick="javascrip:asignar_UsuarioMenu()" />
																		 
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>
												<tr>
													<td>
														<div id="contenedor-tabla_menuPorUsuario">
															<form action="" name="form_adm_MenuPorUsuario" id="form_adm_MenuPorUsuario">
																<input type="hidden" id="op" name="op">
																<input type="hidden" id="rut_user" name="rut_user">
																<input type="hidden" id="concat" name="concat">
															</form>	
															<center><label id="msgAccion_admMxU"> </label></center>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<table border="0" width="100%">
											<tr>
												<td align="right">
													<input type="button" class="boton" id="btnVolver" value="Volver" onclick="javascript:goBackMenu('${nodoPadre}');">
												</td>
											</tr>
										</table>	
										</div>	
									</div>
								</td>
							</tr>
							<tr>
								<td>
								
								</td>
							</tr>
						</table>
					</td>
				</tr>	
			</table>
		</div>
<script>

$(document).ready(function(){
// 	$('#tree').checktree(true);
	CollapsibleLists.apply();

	//Deshabilita los checkbox de los botones volver
	$(".volver").attr("disabled","disabled");
	/**
	 * <ul id="tree">
	 *   <li><label><input type="checkbox" />Item1</label></li>
	 *   <li>
	 *     <label><input type="checkbox" />ItemWithSubitems</label>
	 *     <ul>
	 *       <li><label><input type="checkbox" />Subitem1</label></li>
	 *     </ul>
	 *   </li>
	 * </ul>
	 */
	$("#tree input[type='checkbox'].padre").on("click", function(event){
		checkHijoVolver($(this));
	});
	 
	 function checkHijoVolver(elem){
		 var padre = elem;
			var check = (padre[0].checked==true)?true:false;
			var hijos = padre.parent().find("input.hijo");
			
			for(var x=0; x<hijos.length;x++){
				hijos[x].checked=check;
			}
	 }
	 
	 
	$("#tree input[type='checkbox'].hijo").on("click", function(event){
		var foundParent = false;
		var elem = $(this);
		while(foundParent==false){
			if(elem.find("input.padre:first").length>0){
				var padre = elem.find("input.padre")[0];
				padre.checked=true;
				
				if($(padre).parent().find("input.volver:last").length>0){
					$(padre).parent().find("input.volver:last")[0].checked=true;
				}
				
				var clases = $(padre).attr('class').split(" ");
				
				for ( var i = 0; i < clases.length; i++) {
					
					foundParent=true;
					if(clases[i]=="hijo"){
						foundParent=false;
						i = clases.length;
						elem = elem.parent().parent();
					}
				}
				
			}else{
				elem = elem.parent().parent().parent();
			}
			
		}		
		
	});

});

</script>	
</body>
</html>