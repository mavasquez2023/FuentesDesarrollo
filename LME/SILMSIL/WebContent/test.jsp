<%@ include file="layout/headerJsp.jsp" %>

<html>
<head>
<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
<title>SILMSIL</title>
</head>
<body>
	<div class="container">
	
		<div class="menu-container">
			
			<!-- Formulario página principal -->
			<form action="/cargar.do" id="formCargar" name="formCargar">			
			<table align="center" width="950px;">
				<tr>
					<td style="width:967px;"><IMG border="0"
						src="<%=request.getContextPath() %>/img/banner.jpg"
						width="967px;" height="81px;">
					</td>
				</tr>
				<tr>
					<td style="width:967px;"><table border="0">
							<tbody>
								<tr>
									<td class="text13n" style="width: 367px" width="367">
										Login:
										<!--<input type="text" name="txt_Usuario" id="txt_Usuario" 
											value='<%=session.getAttribute("User")%>' 
											disabled="true" />-->
									</td>
									<td style="width: 540px" width="540"></td>
									<td  style="width: 115px" width="79">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										<a onclick="javascript:goOut()" class="text13n">Salir</a>
									</td>
								</tr>
							</tbody>
						</table></td>
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
											<tr>
												<!-- Título -->
												<td width="950px;">
													<h2 class="ui-widget-header ui-widget-content ui-corner-all">SILMSIL</h2>
												</td>
											</tr>
										</table>
										
										<br>
										<!-- Segundo Fieldset -->
										<div class="MarcoFormulario ui-widget-content ui-corner-all">
											<table class="tablaSinBordes" align="center"
												style="width: 950px;"></table>
											
											<!-- Tercer Fieldset, rodea campo de año de proceso -->
											<div class="MarcoFormulario ui-widget-content ui-corner-all" 
												style="width: 400px;" align="left">
												<table border="0" align="center" style="width: 370px;">
													<tbody>
														<tr>
															<td></td>
															<td class="text13n">A&ntilde;o de proceso :</td>
															<td width="218" style="width: 218px">&nbsp; 
																<select name="selAnno" id="selAnno" class="selPeriodo">
														            <option value="vacio" selected="selected"> - - - - - - - - - - - </option>
																	<option value="2013">2013</option>
																	<option value="2014">2014</option>			
																	<option value="2015">2015</option>			
																	<option value="2016">2016</option>	
																	<option value="2017">2017</option>			
																	<option value="2018">2018</option>			
																	<option value="2019">2019</option>
																	<option value="2020">2020</option>			
																	<option value="2021">2021</option>			
																	<option value="2022">2022</option>		
																</select>
															</td>
															<td width="4"></td>
														</tr>
													</tbody>
												</table>
											</div>
											
											<br>												
											<table border="0" align="center" style="width: 930px;">
												<tbody>
													<tr>
														<td></td>
														<td align="center">
															<input type="button" class="boton" name="btn_Procesar"
															id="btn_Procesar" onclick="javascript:goProcesar()"
															value="Procesar" style="width: 58px" />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Mant" id="btn_Mant" 
																onclick="javascript:goMantencion()" value="Mantenci&oacute;n"  />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Log"
															id="btn_Log" onclick="javascript:goLog()" value="Log"
															style="width: 72px" />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Refrescar"
															id="btn_Refrescar" onclick="javascript:goRefrescar()"
															value="Refrescar Estado" style="width: 108px" /></td>
														<td align="center">
															<input type="button" class="boton" name="btn_Generar"
															id="btn_Generar" onclick="javascript:goGenerar()"
															value="Generar y Descargar" style="width: 121px" /></td>
														<td></td>
													</tr>
												</tbody>
											</table>
											
											<br>		
											<table border="0" align="center" style="width: 930px;">
												<tbody>
													<tr>
														<td>
															<div class="datagrid">
															<table border="0">
																<!-- Cabecera tabla -->
																<thead>
																	<tr>
																		<th rowspan="2" width="129" style="width: 129px" align="center">Trimestre</th>
																		<th rowspan="2" width="180" style="width: 180px" align="center">Mes</th>
																		<th colspan="2" height="20" style="height: 20px; width: 202px" align="center" width="202">Proceso</th>
																		<th colspan="2" height="20" style="width: 400px" align="center" width="400">Estado</th>
																	</tr>
																	<tr>
																		<th height="20" align="center">SIL</th>
																		<th height="20" align="center">LM</th>
																		<th height="20" align="center">SIL</th>
																		<th height="20" align="center">LM</th>
																	</tr>
																</thead>
																<!-- Cuerpo tabla -->
																<tbody>
																	<c:forEach items="${listaTabla}" var="parent">
																	<tr>
																		<td rowspan="4" width="129" style="width: 129px" align="center">
																			<input type="radio" id="rd_Trim" name="rd_Trim" value="${parent.trimestre}"/>
																		</td>
																	</tr>
																	<c:forEach items="${listaTabla.listaMeses}" var="child">
																	<tr>
																		<td width="180" style="width: 180px" align="center">${child.mes}</td>
																		<td height="20" style="height: 20px; width: 202px" align="center" width="202">
																			<input type="checkbox" id="chk_SIL" name="chk_SIL" value="${child.procSIL}"/>
																		</td>
																		<td height="20" style="width: 400px" align="center" width="400">
																			<input type="checkbox" id="chk_LM" name="chk_LM" value="${child.procLM}"/>
																		</td>
																		<td height="20" style="height: 20px; width: 202px" align="center" width="202">
																			${child.estSIL}
																		</td>
																		<td height="20" style="width: 400px" align="center" width="400">
																			${child.estLM}
																		</td>
																	</tr>
																</c:forEach>
																</c:forEach>
																</tbody>
															</table>
															</div>
														</td>
													</tr>
													<tr>
														<td>
															
														</td>
													</tr>
													<tr>
														<td></td>
													</tr>
													<tr>
														<td></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			</form>
		</div>
	</div>
</body>
</html>