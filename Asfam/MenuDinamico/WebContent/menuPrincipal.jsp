<%@ include file="layout/headerJsp.jsp"%>

<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<title>Aplicaci&oacute;n Men&uacute;</title>
	</head>
	<body>
		<%
			HttpSession sesion = request.getSession();
			String user =  (String)sesion.getAttribute("login");
			String name = (String)sesion.getAttribute("userIBM");
		 %>
		<div class="container">
			<table align="center">
				<tr>
					<td style="width:1000px;" width="1000px;">
					<IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg" width="1000px;" height="81px;">
					</td>
				</tr>
				<tr>
					<td width="999" style="width: 999px">
						<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 990px; padding-right: 0;" align="center">
							<table border="0" width="990">
								<tbody>
									<tr>
										<td style="width: 523px" class="text13n" width="523">
											Usuario : <%out.println(name + " - " + user); %>
										</td>
										<td style="width: 48px"></td>
										<td style="width: 329px" width="329"></td>
										<td style="width: 45px" width="146">
											<input type="button" class="boton" value="Salir" onclick="javascript:closeSesion()" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td width="999">
						<table border="0" width="993">
						<tbody>
							<tr>
								<td style="width: 298px" width="298"></td>
								<td width="696">
									<div class="MarcoFormulario ui-widget-content ui-corner-all" 
										style="width: 450px;" align="center">
										<table class="tablaSinBordes" align="center" width="450px;">
											<tr>
												<td>
													<h2 class="ui-widget-header ui-widget-content ui-corner-all">
														<center>Men&uacute; </center>
													</h2>
												</td>
											</tr>
											<c:choose>
												<c:when test="${empty lista}">
													<tr>
														<td style="background-color: #B0C4DE; color: white; 
															font-weight: bolder;">
															<center>${msgListNull}</center>
														</td>
													</tr>
												</c:when>
												<c:otherwise>
													<form action="menuPrincipal.do" id="formMenuUno" name="formMenuUno" 
														method="post">
														<!-- Campos hidden -->
														<input type="hidden" id="op" name="op"/>
														<input type="hidden" id="path" name="path" value="<%= request.getContextPath()%>/router.do?URL="/>
														<input type="hidden" id="codMenu" name="codMenu" />
														<input type="hidden" id="flgHoja" name="flgHoja" />
														<input type="hidden" id="url" name="url" />
														<input type="hidden" id="nodoPadre" name="nodoPadre" />
														
														<c:forEach items="${lista}" var="list">
															<tr>
																<td>
																	<center>
																		<input type="button" class="boton" value="${list.etiqueta}" style="width: 80%;"
																			onclick="javascript:goMenu('${list.codMenu}','${list.linkInterno}','${list.flgHoja}','${list.seguridad}','${list.enlace}','${list.nodoPadre}')"/>
																	</center>	
																</td>
															</tr>
														</c:forEach>
													</form>
												</c:otherwise>
											</c:choose>
										</table>
									</div>
								</td>
								<td></td>
							</tr>
						</tbody>
					</table>
					
					</td>
				</tr>
			</table>
		</div>
	
	<div id="closeSesionContent">
		<form action="closeSesion.do" id="closeSesionForm">
		</form>
	</div>	
		
	</body>
</html>