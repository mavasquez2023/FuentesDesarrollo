<%@ include file="layout/headerJsp.jsp"%>

<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<title>Aplicaci&oacute;n Men&uacute;</title>
	</head>
	<body>
		<div class="container">
			<form action="<%=request.getContextPath() %>/j_security_check" 
				name="formLogin" id="formLogin" method="post" class="form">
				<table align="center" width="750px;">
					<tr>
						<td style="width:750px;" align="center"><IMG border="0"
								src="<%=request.getContextPath() %>/img/banner.jpg"
								width="750px;" height="81px;">
						</td>
					</tr>
					<tr>
						<td>
							<table class="tablaSinBordes" align="center" width="750px;">
								<tr>
									<td>
										<div class="MarcoFormulario ui-widget-content ui-corner-all"
											style="width: 735px;" align="center">
											<table border="0" align="center" style="width: 735px;">
												<tr>
													<td>
														<h2 class="ui-widget-header ui-widget-content ui-corner-all">
															<center>Login</center>
														</h2>
													</td>
												</tr>
											</table>
											<div class="MarcoFormulario ui-widget-content ui-corner-all"
												style="width: 720px;" align="center">
												<table border="0" align="center" style="width: 720px;">
													<tbody>
														<tr>
															<td width="210" style="width: 210px"></td>
															<td class="text13n" width="108" style="width: 108px">Usuario</td>
															<td width="281">
																<input type="text" id="claveInicial" name="j_username" size="24"/>
															</td>
															<td width="107"></td>
														</tr>
														<tr>
															<td width="210"></td>
															<td class="text13n" width="108">Password</td>
															<td width="281" style="width: 281px">
																<input type="password" id="claveNueva" name="j_password" size="24"/>
															</td>
															<td width="107"></td>
														</tr>
														<tr>
															<td width="210"></td>
															<td class="text13n" width="108"></td>
															<td width="281"></td>
															<td width="107">
																<input type="button" class="boton" id="btn_Aceptar" name="btn_Aceptar" 
																	value="Aceptar" onclick="javascript:validarLogin()"/>
															</td>
														</tr>
													</tbody>
												</table>
											</div>	
											<div style="text-align: right;">V(1.4)</div>
										</div>
									</td>
								</tr>
								
							</table>
						</td>
						
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>