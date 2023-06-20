<%@ include file="layout/headerJsp.jsp"%>

<html>
<head>
<jsp:include flush="true" page="layout/header.jsp"></jsp:include>
<title>SILMSIL</title>

	<%
		String cantidadPaginas="";
		String paginaActual="";
	
		Paginacion_VO pag;
		HttpSession sesion = request.getSession();
		pag=(Paginacion_VO) sesion.getAttribute("pagInfo");
		if(pag!=null){
			cantidadPaginas=String.valueOf(pag.getCantidadPaginas());
			paginaActual=String.valueOf(pag.getPaginaActual());
		}
		String login = (String)sesion.getAttribute("login"); 
	%>
</head>
<body>
	<div class="container">
	
		<div class="menu-container">
			
			<!-- Formulario página principal -->
			<form action="<%=request.getContextPath() %>/asignando.do" id="formCargar" name="formCargar" 
				type="cl.laaraucana.silmsil.forms.ProcesarForm" method="post">
			<!-- Campos Hidden para uso struts -->	
			<input type="hidden" name="metodo" id="metodo" value="asignar" />	
			
			<!-- Campos Hidden para uso en interfaz gráfica -->
			<c:set var="now" value="<%=new java.util.Date()%>" />
			<input type="hidden" id="hd_Date" name="hd_Date" value="<fmt:formatDate pattern="yyyyMM" value="${now}" />" />
			<input type="hidden" id="hd_Ahno" name="hd_Ahno" value="<fmt:formatDate pattern="yyyy" value="${now}" />" />
			<input type="hidden" id="hd_mesActual" name="hd_mesActual" 
				value="<fmt:formatDate pattern="MM" value="${now}" />" />
			<input type="hidden" id="hd_Asignar" name="hd_Asignar" value="" />
			<input type="hidden" id="keyProcesoPeriodo" name="keyProcesoPeriodo" value="" />
			
			<% String fecha =  (String)request.getAttribute("selAhno");%>
			<input type="hidden" id="periodo_selectd" name= "periodo_selectd" value="${selAhno}" />				
			<table align="center" width="950px;">
				<tr>
					<td style="width:967px;"><IMG border="0" src="<%=request.getContextPath() %>/img/banner.jpg" width="967px;" height="81px;">
					</td>
				</tr>
				<tr>
					<td style="width:967px;"><table border="0">
							<tbody>
								<tr>
									<td class="text13n" style="width: 367px" width="367">
										<label id="txt_Usuario"><%out.println("Login : "+login); %></label>
									</td>
									<td style="width: 540px" width="540"></td>
									<td  style="width: 115px; text-align: right;">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
										<input type="button" class="boton" name="btn_Salir" id="btn_Salir" value="Cerrar" onclick="javascript:goOut()"/>
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
									<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 950px;">
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
											<table class="tablaSinBordes" align="center" style="width: 950px;">
											</table>											
											<!-- Tercer Fieldset, rodea campo de año de proceso -->
											<div class="MarcoFormulario ui-widget-content ui-corner-all" style="width: 400px;" align="left">
												<table border="0" align="center" style="width: 370px;">
													<tbody>
														<tr>
															<td></td>
															<td class="text13n">A&ntilde;o de proceso :</td>
															<td width="218" style="width: 218px">&nbsp; 
																
																<select name="selAnno" id="selAnno" class="selPeriodo">
														            
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
															<input type="button" class="boton" name="btn_Procesar" id="btn_Procesar" onclick="javascript:asignarValor(1)" value="Procesar" style="width: 58px" />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Mant" id="btn_Mant" 
																onclick="javascript:checkMesMantencion()" value="Mantenci&oacute;n" />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Log" id="btn_Log" onclick="javascript:logError()" value="Log de error" style="width: 72px" />
														</td>
														<td align="center">
															<input type="button" class="boton" name="btn_Refrescar" id="btn_Refrescar" onclick="javascript:refrescar(4)" value="Refrescar Estado" style="width: 108px" /></td>
														<td align="center">
															<input type="button" class="boton" name="btn_Generar" id="btn_Generar" onclick="javascript:asignarValor(5)" value="Generar y Descargar" style="width: 121px" /></td>
														<td></td>
													</tr>
												</tbody>
											</table>
											
											<br>
												<table border="0"  style="width: 930px;">
													<tbody>
														<tr>
															<td width="79" style="width: 79px"></td>
															<td width="751" style="width: 751px" align="center">${msj}</td>
															<td width="90"></td>
														</tr>
													</tbody>
												</table>
												<br>
												
												<table border="0" align="center" style="width: 930px;">
												<tbody>
													<tr>
														<td>
															<div class="datagrid">
															<table border="1" style=" border-width: 1px; border-color: #4682B4; border-collapse: collapse;">
																<!-- Cabecera tabla -->
																<thead>
																	<tr>
																		<th rowspan="2" style="width: 83px; height: 38px;" align="center" height="38" width="83">
																			Trimestre
																		</th>
																		<th rowspan="2" style="width: 70px" align="center" height="38" width="117">
																			Mes
																		</th>
																		<th colspan="2" style="height: 20px; width: 62px" align="center">
																			Proceso
																		</th>
																		<th colspan="2" style="width: 400px" align="center">
																			Estado
																		</th>
																	</tr>
																	<tr>
																		<th align="center" style="width: 70px" width="70">
																			SIL
																		</th>
																		<th align="center" style="width: 45px" width="254">
																			LM
																		</th>
																		<th align="center" style="width: 303px" width="303">
																			SIL
																		</th>
																		<th align="center" width="321">
																			LM
																		</th>
																	</tr>
																</thead>
																<!-- Cuerpo tabla -->
																<tbody>
																	<c:forEach items="${listaTabla}" var="parent">
																	
																	<tr>
																		<td rowspan="4" style="width: 80px; border-width: 1px; border-color: #4682B4; border-collapse: collapse; border-bottom: #4682B4;" align="center" width="80">
																			<input class="radioCHK" type="radio" id="rd_Trim" name="rd_Trim" value="${parent.trimestre}" />
																		</td>
																	</tr>
																	<c:forEach items="${parent.listaMeses}" var="child">
																	<tr>
																		<td style="width: 75px; border-width: 1px; border-color: #4682B4; border-collapse: collapse;" align="center" width="75">
																			<label id="lbMes">${child.mes}</label>
																			</td>
																		<td height="20" style="height: 20px; width: 72px; border-width: 1px; border-color: #4682B4; border-collapse: collapse;" align="center" width="72">
																			<input type="checkbox" id="${child.procSIL}" name="${child.procSIL}" value="${child.procSIL}" /></td>
																		<td height="20" style="width: 68px; border-width: 1px; border-color: #4682B4; border-collapse: collapse;" align="center" width="68">
																			<input type="checkbox" id="${child.procLM}" name="${child.procLM}" value="${child.procLM}" />
																		</td>
																	<c:forEach items="${child.lsEstadoSIL}" var="childSIL">
																		<td style=" width: 371px; border-width: 1px; border-color: #4682B4; border-collapse: collapse;" align="center" width="371">
																			${childSIL.texto}
																			<c:if test="${childSIL.boton == 'btnCorregir'}">
																				<br><input type="button" id="btn_Corregir" name="btn_Corregir" onclick="javascript:cargarListadoLogUnico('${childSIL.proceso_periodo}')" class="boton" value="Corregir"/>
																			</c:if>	
																			<c:if test="${childSIL.boton == 'btnDescargar'}">
																				<br><input type="button" id="btn_Descargar" name="btn_Descargar" onclick="javascript:estadoDescargarUno('${childSIL.proceso_periodo}')" class="boton" value="Descargar"/>
																			</c:if>
																		</td>
																	</c:forEach>
																	
																	<c:forEach items="${child.lsEstadoLM}" var="childLM">
																		<td height="20" style="width: 400px; border-width: 1px; border-color: #4682B4; border-collapse: collapse;" align="center" width="253"> ${childLM.texto}<br>
																		<c:if test="${childLM.boton == 'btnCorregir'}">
																			<br><input type="button" id="btn_Corregir" name="btn_Corregir" onclick="javascript:cargarListadoLogUnico('${childLM.proceso_periodo}')"  class="boton" value="Corregir"/>
																		</c:if>	
																		<c:if test="${childLM.boton == 'btnDescargar'}">
																			<br><input type="button" id="btn_Descargar" name="btn_Descargar" 
																				onclick="javascript:estadoDescargarUno('${childLM.proceso_periodo}')" class="boton"
																				value="Descargar"/>
																		</c:if>
																		</td>
																	</c:forEach>
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
														<!--START: version de la aplicacion  -->
														<td style="text-align: right; font-size: 12px;">(v1.4)</td>
														<!--END: version de la aplicacion  -->
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
	
	<!-- Mantenedor LM-->
	<div id="dialog_mantenedor_LM" title="Mantenedor LM">
		<div id="info" class="bordeLimite">
				Usted esta trabajando en la Mantenci&oacute;n Licencias M&eacute;dicas para el per&iacute;odo: <label class="lbl_info_periodo" id="periodoInfoMantenedor_LM">AAAA-MM</label>
		</div>	
		<div id="filtro" class="bordeLimite">
			<p>B&uacute;squeda (Sin puntos con gui&oacute;n)</p>
			<form action="" id="form_filtro_LM" name="form_filtro_LM">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="fecproceso" name="fecproceso">
				<input type="hidden" id="correlativ" name="correlativ">
				<div class="campoFiltro">
					<label class="lbl_nombre" >Rut </label>
					<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
					<label class="lbl_Error" id="ruttrabaj_error"></label>
				</div>
				<div class="campoFiltro">
					<label class="lbl_nombre" >N° Licencia</label>
					<input class="txt_campo" id="folio" name="folio">
					<label class="lbl_Error" id="folio_error"></label>
				</div>
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Buscar" onclick="javascript:buscar('LM')">
				</div>
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListado('LM')">
				</div>
				<label id="lbl_msgBusqueda"></label>
			</form>
		</div>
		
		<div id="filtro" class="bordeLimite">
			Ingreso nuevo Registro
			<br>
			<br>
			<br>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>				
					<input type="button" class="boton" id="btnIngresar" value="Ingresar" onclick="javascript:openInsertar('LM')">
			</div>				
		</div>
		
		<div id="data" class="bordeLimite">
			<form id="formLoadMant_LM">
				<div class="loadMenuMantenedor" id="loadMenuTabla_LM">
					<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
				</div>
			</form>
			<form action="">
			<div id="contenedor-tabla_LM">
				<div class="datagrid">
				<table class="tablaSinBordes">
					<thead>
						<tr>
							<th>N°</th>
							<th>N° Licencia</th>
			   				<th>Rut Trabajador</th>
			   				<th>Fecha inicio reposo</th>
			   				<th>Fecha t&eacute;rmino reposo</th>
							<th>Opciones</th>
						</tr>
					</thead>	                         
			 		<tbody>
			 			<!--Empty y msg-->
						<!-- Iterate-->
						<c:forEach items="${lmList}" var="lmList">
							<tr>
								<td>${lmList.correlativ}</td>                             		
								<td class="var_f">${lmList.folio}</td>
								<td class="var_r">${lmList.ruttrabaj}</td> 
								<td>${lmList.fecinirepo}</td>
								<td>${lmList.fecterrepo}</td>
								<td>
									<input type="button" class="boton up_LM"  value="Editar"/>												
									<input type="button" class="boton del_LM"  value="Eliminar"/>
								</td> 
							</tr>
						</c:forEach>
					<!--END ITERATE--->
					</tbody>
				</table>
				</div>
			</div>
			</form>
		</div>
	</div>
	<!-- Fin sesión Pop Up Mantenedor LM-->
	
	<!-- Mantenedor SIL-->
	<div id="dialog_mantenedor_SIL" title="Mantenedor SIL">
		<div id="info" class="bordeLimite">
				Usted esta trabajando en la Mantenci&oacute;n Licencias M&eacute;dicas para el per&iacute;odo: <label class="lbl_info_periodo" id="periodoInfoMantenedor_SIL">AAAA-MM</label>
		</div>	
		<div id="filtro" class="bordeLimite">
			<p>B&uacute;squeda (Sin puntos con gui&oacute;n)</p>
			<form action="" id="form_filtro_SIL" name="form_filtro_SIL">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="perpag" name="perpag">
				<div class="campoFiltro">
					<label class="lbl_nombre" >Rut </label>
					<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
					<label class="lbl_Error" id="ruttrabaj_error"></label>
				</div>
				<div class="campoFiltro">
					<label class="lbl_nombre" >N° Licencia</label>
					<input class="txt_campo" id="nrofol" name="nrofol">
					<label class="lbl_Error" id="nrofol_error"></label>
				</div>
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Buscar" onclick="javascript:buscar('SIL')">
				</div>
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListado('SIL')">
				</div>
				<label id="lbl_msgBusqueda"></label>
			</form>
		</div>
		
		<div id="filtro" class="bordeLimite">
			Ingreso nuevo Registro
			<br>
			<br>
			<br>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>				
					<input type="button" class="boton" id="btnIngresar" value="Ingresar" onclick="javascript:openInsertar('SIL')">
			</div>				
		</div>
		
		<div id="data" class="bordeLimite">
			<form id="formLoadMant_SIL">
				<div class="loadMenuMantenedor" id="loadMenuTabla_SIL">
					<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
				</div>
			</form>	
			<form action="">
			<div id="contenedor-tabla_SIL">
				<div class="datagrid">
				<table class="tablaSinBordes">
				<thead>
					<tr>
						<th>N°</th>
		   				<th>Rut Trabajador</th>
		   				<th>N° Licencia</th>   				
		   				<th>Folio de pago PAGFOL</th>
		   				<th>Fecha t&eacute;rmino reposo</th>
						<th>Opciones</th>
					</tr>
				</thead>	                         
		 		<tbody>
		 			<!--Empty y msg-->
					<!-- Iterate-->
					<c:forEach items="${silList}" var="silList">
						<tr>
							<td>${lmList.correlativ}</td>
							<td class="var_r">${silList.ruttrabaj}</td>
							<td class="var_f">${silList.nrofolio}</td>
							<td class="var_pf">${silList.pagfol}</td>
							<td class="var_ftr">${silList.lichasfec}</td>
							<td>
								<input type="button" class="boton up_SIL"  value="Editar"/>												
								<input type="button" class="boton del_SIL"  value="Eliminar"/>
							</td> 
						</tr>
					</c:forEach>
				<!--END ITERATE--->
				</tbody>
			</table>
				</div>
			</div>
			</form>
		</div>
	</div>
	<!-- Fin sesión Pop Up Mantenedor SIL-->
	
	
	<!-- Log Errores LM-->
	<div id="dialog_logError_LM" title="Log de errores LM">
		<div id="info" class="bordeLimite">
			Usted esta trabajando en la Correci&oacute;n de Errores de Licencias M&eacute;dicas para el per&iacute;odo: 
			<label class="lbl_info_periodo" id="periodoInfo">AAAA-MM</label>
		</div>	
		<div id="filtroLog" class="bordeLimite">
			<p>Búsqueda (Sin puntos con guión)</p>
			<form action="" id="form_filtro_Log_LM" name="form_filtro_Log_LM">
				<!-- Campos hidden -->
				<input type="hidden" id="op" name="op" value="" />
				<input type="hidden" id="fecproceso" name="fecproceso" />
				
				<div class="campoFiltro">
					<label class="lbl_nombre" >Rut </label>
					<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
					<label class="lbl_Error" id="ruttrabaj_error"></label>
				</div>
				<div class="campoFiltro">
					<label class="lbl_nombre" >N° Licencia</label>
					<input class="txt_campo" id="folio" name="folio">
					<label class="lbl_Error" id="folio_error"></label>
				</div>	
				<div class="campoFiltro">
					<label class="lbl_nombre">Agrupar Errores</label>
					<input type="checkbox" id="chk_agrupar_LM" name="chk_agrupar_LM" value="" />
				</div>
			</form>		
			<div class="campoLogs">
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Buscar" onclick="javascript:buscarLog('LM')">
				</div>
				<div class="campoFiltro">
						<label class="lbl_nombre" ></label>
						<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListadoLog('LM')">
				</div>	
			</div>	
		</div>
	
		<div id="data" class="bordeLimite">
			<form id="formLoadLog_LM">
				<div class="classLoading" id="loadMenuTablaLog_LM">
					<center>
						<img src='<%=request.getContextPath() %>/img/Loading.gif'  id="imgLoad" name="imgLoad" />
						<br>Espere un momento...<br>
					</center>
				</div>
			</form>
			<form action="">
				<div id="contenedor-tablaLog_LM">
					<div class="datagrid">
						<table class="tablaSinBordes">
							<thead>
								<tr>
									<th>N°</th>
									<th>N° Licencia</th>
					   				<th>Rut Trabajador</th>
					   				<th>Emisi&oacute;n</th>
					   				<th>Descripci&oacute;n Error</th>
									<th>Opci&oacute;n</th>
								</tr>
							</thead>	                         
					 		<tbody>
					 			<!--Empty y msg-->
								<!-- Iterate-->
							<!--END ITERATE--->
							</tbody>
						</table>
					</div>
				</div>
			</form>
		</div>
	</div>		
	
	<div id="dialog_formL_LM" title="Formulario LM">		
		<form action="" name="form_logError_LM" id="form_logError_LM">
			<input type="hidden" id="op" name="op">
			<input type="hidden" id="fecproceso" name="fecproceso">
		<div id="contenedor-form">		
		</div>	
		</form>
		<div id="loadMenu" class="loadMenu">
			<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
		</div>
	</div>
	
	
	<!-- Fin sesión Pop Up Log Errores SIL-->

	<!-- Log Errores SIL-->
	<div id="dialog_logError_SIL" title="Log de errores SIL">
		<div id="info" class="bordeLimite">
			Usted esta trabajando en la Correci&oacute;n de Errores de Licencias M&eacute;dicas para el per&iacute;odo: <label class="lbl_info_periodo_log" id="periodoInfo_Log">AAAA-MM</label>
		</div>	
		<div id="filtroLog" class="bordeLimite">
			<p>B&uacute;squeda (Sin puntos con gui&oacute;n)</p>
			<form action="" id="form_filtro_Log_SIL" name="form_filtro_Log_SIL">
				<!-- Campos hidden -->
				<input type="hidden" id="op" name="op" value="" />
				<input type="hidden" id="perpag" name="perpag" />
				
				<div class="campoFiltro">
					<label class="lbl_nombre" >Rut </label>
					<input class="txt_campo" id="ruttrabaj" name="ruttrabaj">
					<label class="lbl_Error" id="ruttrabaj_error"></label>
				</div>
				<div class="campoFiltro">
					<label class="lbl_nombre" >N° Licencia</label>
					<input class="txt_campo" id="nrofol" name="nrofol">
					<label class="lbl_Error" id="folio_error"></label>
				</div>	
				<div class="campoFiltro">
					<label class="lbl_nombre">Agrupar Errores</label>
					<input type="checkbox" id="chk_agrupar_SIL" name="chk_agrupar_SIL" value="" />
				</div>
			</form>
			
		<div class="campoLogs">
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="Buscar" onclick="javascript:buscarLog('SIL')">
			</div>
			<div class="campoFiltro">
					<label class="lbl_nombre" ></label>
					<input type="button" class="boton" value="Cargar listado completo" onclick="javascript:cargarListadoLog('SIL')">
			</div>	
		</div>	
		</div>
	
		<div id="data" class="bordeLimite">
			<form id="formLoadLog_SIL">
				<div class="classLoading" id="loadMenuTablaLog_SIL">
					<center>
						<img src='<%=request.getContextPath() %>/img/Loading.gif'  id="imgLoad" name="imgLoad" />
						<br>Espere un momento...<br>
					</center>
				</div>
			</form>
			<form action="">
			<div id="contenedor-tablaLog_SIL">
				<div class="datagrid">
				<table class="tablaSinBordes">
					<thead>
						<tr>
							<th>N°</th>
							<th>N° Licencia</th>
			   				<th>Rut Trabajador</th>
			   				<th>Emisi&oacute;n</th>
			   				<th>Descripci&oacute;n Error</th>
							<th>Opci&oacute;n</th>
						</tr>
					</thead>	                         
			 		<tbody>
			 			<!--Empty y msg-->
						<!-- Iterate-->
						<c:forEach items="${silList}" var="silList">
							<tr>
								<td>${silList.correlab}</td>                             		
								<td class="var_rut">${silList.ruttrabaj}</td>
								<td class="var_nrofol">${silList.nrofol}</td>
								<td>${silList.fecemi}</td>
								<td>${silList.descripcion}</td>
								<td>
									<input type="button" class="boton up_LM"  value="Corregir Error"/>	
								</td> 
							</tr>
						</c:forEach>
					<!--END ITERATE--->
					</tbody>
				</table>
				</div>
			</div>
			</form>
		</div>
		</div>
	
		 <div id="dialog_formL_SIL" title="Formulario SIL">		
			<form action="" name="form_filtro_Log_SIL" id="form_filtro_Log_SIL">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="fecproceso" name="fecproceso">
				<div id="contenedor-form">		
				</div>	
			</form>
			<div id="loadMenu" class="loadMenu">
				<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
			</div>
		</div>
	
	
		<!-- START: comentado por duplicidad
		<div id="dialog_form_LogSIL" title="Formulario Log Errores SIL">		
			<div id="contenedor-form_SIL">
				<form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
					<input type="hidden" id="op" name="op">
					<input type="hidden" id="fecproceso" name="fecproceso">
					<input type="hidden" id="folio" name="folio">
					<input type="hidden" id="ruttrabaj" name="ruttrabaj">
				</form>
			</div>
		</div>
		END: comentado por duplicidad-->
			
	<!-- Fin sesión Pop Up Log Errores SIL-->
	
	<!-- Comunes -->
	<div id="dialog_form_SIL" title="Formulario SIL">		
		<div id="contenedor-form_SIL">
			<form action="" name="form_mantenedor_SIL" id="form_mantenedor_SIL">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="perpag" name="perpag">	
				<input type="hidden" id="nrofol" name="nrofol">
				<input type="hidden" id="ruttrabaj" name="ruttrabaj">
				<input type="hidden" id="lichasfec" name="lichasfec">
				<input type="hidden" id="pagfol" name="pagfol">
				<input type="hidden" id="correlab" name="correlab">
				<input type="hidden" id="correlativ" name="correlativ">
			</form>
		</div>
		<div id="loadMenu" class="loadMenu">
			<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
		</div>
	</div>
	
	<div id="dialog_form_LM" title="Formulario Log Errores LM">		
		<div id="contenedor-form_LM">
			<form action="" name="form_mantenedor_LM" id="form_mantenedor_LM">
				<input type="hidden" id="op" name="op">
				<input type="hidden" id="fecproceso" name="fecproceso">
				<input type="hidden" id="fecterrepo" name="fecterrepo">
				<input type="hidden" id="folio" name="folio">
				<input type="hidden" id="ruttrabaj" name="ruttrabaj">
				<input type="hidden" id="correlab" name="correlab">
				<input type="hidden" id="correlativ" name="correlativ">
			</form>
		</div>
		<div id="loadMenu" class="loadMenu">
			<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
		</div>
	</div>	
	<!-- Fin comunes -->
	
	
	<!-- START: dialog consulta estado procesos-->	
	<div id="dialog_form_Ejecucion" title="Estado de procesos seleccionados">		
		<div id="contenedor-form_Ejecucion">
			<div id="loadMenu" class="loadMenu">
				<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
			</div>	
		</div>
	</div>
	<!-- END: dialog consulta estado procesos-->
	
	<!-- START: dialog descargas-->	
	<div id="dialog_form_Descarga" title="Generacion de archivos">		
		<div id="contenedor-form_Descarga">
			<div id="loadMenu" class="loadMenu">
				<center><img src='<%=request.getContextPath() %>/img/Loading.gif' id="imgLoad" name="imgLoad"><br>Espere un momento...<br></center>
			</div>
		</div>
	</div>
	<!-- END: dialog consulta estado procesos-->
	
	<!-- Cerrar sesión -->
	<div id="closeSesionContent">
		<form action="" id="closeSesionForm">
		</form>
	</div>	
	<!-- FIN: Cerrar sesión -->
	<script>
		var myselect = document.getElementById("selAnno"), year = new Date().getFullYear();
		var gen = function(max){
				do{
					if($('#periodo_selectd').val()== year ){
						myselect.add(new Option(year--,max--, true, true),null);
					}else{
						myselect.add(new Option(year--,max--),null);
					}
					
				}while(year>2013);
			}(year);
	</script>
</body>
</html>