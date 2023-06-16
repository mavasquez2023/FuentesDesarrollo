<%@ include file="layout/headerJsp.jsp"%>

<html>
	<head>
		<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
		<title>Aplicaci&oacute;n Men&uacute;</title>
	</head>
	<body>
	<%String mensaje = (String)request.getAttribute("mensaje");%>
		<div class="container">
				<table align="center" width="750px;">
					<tr>
						<td style="width:750px;" align="center">
							<IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg" width="750px;" height="81px;">
						</td>
					</tr>
					<tr>
						<td>
							<table class="tablaSinBordes" align="center" width="750px;">
								<tr>
									<td style="width: 750px;" align="center">
										<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 750px;">
											<table align="center" width="750px;">
												<tr><!-- Título -->
													<td width="750px;">
														<h2 class="ui-widget-header ui-widget-content ui-corner-all">
															
														</h2>
													</td>
												</tr>
												<tr>
													<td width="750px;">
														Ha ocurrido un error inesperado: 
														<%out.println("El usuario y/o password no son válidos.");%>
													</td>												
												</tr>
												<tr>
													<td width="750px;" style="text-align: right;">
														
														<a href="login.jsp">
															<input type="button" class="boton" value="Volver" onclick="javascript:goBack()"/>
														</a>																				
													</td>												
												</tr>
											</table>
										</div>
									</td>
								</tr>
							</table>	
						</td>
					</tr>
				</table>
		</div>
		
		<div id="closeSesionContent">
			<form action="closeSesion.do" id="closeSesionForm">
			</form>
		</div>	
		
		<script type="text/javascript">
		
			document.onkeydown = function(evt) {
			    evt = evt || window.event;
			    if (evt.keyCode == 8 || evt.keyCode == 13) {
			        return false;
			    }
			};
		</script>
	</body>
	
	
	
</html>