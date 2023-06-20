<%@ include file="layout/headerJsp.jsp"%>
<html>
<head>
<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
<title>SILMSIL</title>
</head>
<body>
	<div class="container">	
		<div class="menu-container">
			<table align="center" width="950px;">
				<tr>
					<td style="width:967px;"><IMG border="0"
						src="<%=request.getContextPath() %>/img/banner.jpg"
						width="967px;" height="81px;">
					</td>
				</tr>				
				<tr>
					<td style="width: 930px;">
						<!-- Primer Fieldset -->
						<table class="tablaSinBordes" align="center" width="950px;">
							<tr>
								<td style="width: 950px;" align="center">
									<div class="MarcoFormulario ui-widget-content ui-corner-all"
										style="width: 950px;">
										<table align="center" width="950px;">
											<tr><!-- Título -->
												<td width="950px;">
													<h2 class="ui-widget-header ui-widget-content ui-corner-all">SILMSIL</h2>
												</td>
											</tr>
											<tr>
												<td width="950px;">
													Ha ocurrido un error inesperado: Los datos ingresados de 
													usuario y/o password no son correctos.
												</td>												
											</tr>
											<tr>
												<td width="950px;" >
													<input type="button" class="boton" value="Cerrar"
														onclick="javascript:cerrar()">
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
	</div>
</body>
</html>