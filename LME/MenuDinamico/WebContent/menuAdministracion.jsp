<%@ include file="layout/headerJsp.jsp"%>

<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<title>Aplicaci&oacute;n Men&uacute;</title>
	</head>
	<body>
		<!--<%
			HttpSession sesion = request.getSession();
			String user =  (String)sesion.getAttribute("login");
			String name = (String)sesion.getAttribute("userIBM");
		 %>
		<div class="container">
			<table align="center" width="456px;">
				<tr>
					<td style="width:456px;">
					<IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg"
						width="456px;" height="81px;">
					</td>
				</tr>
				<tr>
					<td width="999" style="width: 999px">
						<div class="MarcoFormulario ui-widget-content ui-corner-all"
							style="width: 450px;" align="center">
							<table border="0" width="990">
								<tbody>
									<tr>
										<td style="width: 523px" class="text13n" width="523">Usuario
											: <%out.println(name + " - " + user); %>
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
					<td>
						<div class="MarcoFormulario ui-widget-content ui-corner-all" 
							style="width: 450px;" align="center">
							<table class="tablaSinBordes" align="center" width="450px;">
								<tr>
									<td>
										<h2 class="ui-widget-header ui-widget-content ui-corner-all">
											<center>Men&uacute; Administraci&oacute;n</center>
										</h2>
									</td>
								</tr>
								<c:choose>
									<c:when test="${empty menuUserList}">
										<tr>
											<td style="background-color: #B0C4DE; color: white; 
												font-weight: bolder;">
												<center>${msgListNull}</center>
											</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${menuUserList}" var="menuUserList">
											<c:if test="${menuUserList.nivel==1}">
												<tr>
													<td>
														<center>
															<form action=""	id="formMenuAdm" method="post" > 
																<input type="button" class="boton" value="${menuUserList.etiqueta}" 
																	onclick="goMenuAdministracion()"/>
																<input type="hidden" id="hdn_menu" name="hdn_menu"
																	value="${menuUserList.etiqueta}" />
															</form>
														</center>
													</td>
												</tr>
											</c:if>	
										</c:forEach>	
									</c:otherwise>
								</c:choose>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
		
		<div id="closeSesionContent">
			<form action="closeSesion.do" id="closeSesionForm">
			</form>
		</div>	
		-->
	</body>
</html>