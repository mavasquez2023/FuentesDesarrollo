<%@ include file="/html/comun/taglibs.jsp"%>
<html:form action="/SucursalesLista" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="sucursalesLista" />
<bean:parameter id="rutEmpresaBean" name="rutEmpresa"/>
<html:hidden property="rutEmpresa" styleId="rutEmpresa"><%=rutEmpresaBean%></html:hidden>
<html:hidden property="operacion" styleId="operacion" value="" />
<html:hidden property="idEmpresaBorrar" styleId="idEmpresaBorrar" value="" />
<html:hidden property="idSucursalBorrar" styleId="idSucursalBorrar" value="" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors />
		</td>
	</tr>
	<tr>
		<td>
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
							<tr>
								<td width="15%"><strong>RUT:</strong></td>
								<td width="20%">
									<nested:write property="rutEmpresaFmt"/>
								</td>
								<td><strong>Nombre:</strong></td>
								<td>
									<nested:write property="nombreEmpresa"/>
								</td>
							</tr>
							<tr>
								<td height="4" colspan="4" bgcolor="#85b4be"></td>
							</tr>
						</table>
					</td>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1">
								<tr valign="bottom">
									<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de Direcciones</strong></td>
									<td align="right" bgcolor="#FFFFFF">
										<c:set var="url"><c:url value='/SucursalesLista.do'/>?rutEmpresa=<nested:write property="rutEmpresa"/>&imprimir=&accion=admin&subAccion=imprimir</c:set>
									    <html:button property="operacion" styleClass="btn3" value="${imprimir}" onclick="javascript:abrirDocImpresion('${url}');"/>
										<nested:equal property="puedeCrear" value="true">
											&nbsp;&nbsp;&nbsp;<html:button property="operacion" styleClass="btn3" value="${crearDireccion}" onclick="javascript:creaSucursal(this);"/>
										</nested:equal>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1">
								<tr>
									<td align="center" bgcolor="#FFFFFF">
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
											<tr>
												<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />Tipo de Direcci&oacute;n</td>
												<td width="70%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n</td>
												<td width="15%" colspan="3" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
											</tr>
											<nested:iterate id="filaSuc" property="consulta" indexId="nFila">
												<c:choose>
													<c:when test="${nFila % 2 == 0}">
														<c:set var="estilo" value="textos_formularios" />
													</c:when>
													<c:otherwise>
														<c:set var="estilo" value="textos-formcolor2" />
													</c:otherwise>
												</c:choose>
												<tr>
													<td align="center" valign="middle" nowrap="nowrap" class="${estilo}">
														<center>
															<nested:write property="idSucursal"/>
														</center>
													</td>
													<td nowrap="nowrap" class="${estilo}" align="left">
														<nested:write property="nombre"/>
													</td>
													<td align="center" nowrap="nowrap" class="${estilo}">
														<center>
															<html:link title="Ver detalle" action="/SucursalesEditar.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesFicha&rutEmpresa=${filaSuc.idEmpresa}&idSucursal=${filaSuc.idSucursal}" styleClass="links">
																<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
															</html:link>
														</center>
													</td>
													<td align="center" nowrap="nowrap" class="${estilo}">
														<center>
															<nested:equal property="editable" value="true">
																<a href="<c:url value='/SucursalesEditar.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesEditar&rutEmpresa=${filaSuc.idEmpresa}&idSucursal=${filaSuc.idSucursal}'/>">
																	<img src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0" alt="Editar" title="Editar" />
																</a>
															</nested:equal>
															<nested:notEqual property="editable" value="true">
																<img src="<c:url value="/img/ico_hojap_no.gif" />" width="14" height="13" border="0"/>
															</nested:notEqual>
														</center>
													</td>
													<td align="center" nowrap="nowrap" class="${estilo}">
														<center>
															<%-- No se puede borrar la casa matriz --%>
															<nested:equal property="eliminable" value="true">
																<nested:notEqual property="esCasaMatriz" value="true">
																	<a href="javascript:borraSucursal(${filaSuc.idEmpresa}, '${filaSuc.idSucursal}');">
																		<img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar" />
																	</a>
																</nested:notEqual>
																<nested:equal property="esCasaMatriz" value="true">																	
																	<a href="javascript:alert('Esta Direcci&oacute;n no se puede eliminar, ya que debe tener una.');">
																		<img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar" />
																	</a>
																</nested:equal>
															</nested:equal>
															<nested:notEqual property="eliminable" value="true">
																&nbsp;
															</nested:notEqual>
														</center>
													</td>
												</tr>
											</nested:iterate>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<!-- paginacion -->
		<bean:size id="nPags" name="SucursalActionForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr> 
						<td align="center" valign="middle" class="numeracion">
							<logic:iterate id="paginacion" name="SucursalActionForm" property="numeroFilas">
								${paginacion}
							</logic:iterate>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</c:if>
	</table>
</html:form>
<script language="javaScript">
	
	function borraSucursal(idEmpresa, idSucursal) {
		
		if (!confirm("Está seguro que desea borrar la sucursal código: " + idSucursal))
			return;
			
		document.getElementById("operacion").value = "borrar";
		document.getElementById("idEmpresaBorrar").value = idEmpresa;
		document.getElementById("idSucursalBorrar").value = idSucursal;
		document.getElementById("formulario").submit();
	}
	
	function creaSucursal(obj) {
		
		document.getElementById("operacion").value = obj.value;
		document.getElementById("formulario").submit();
	}
	
	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		formu.action = "SucursalesLista.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesLista&paginaNumero="+paginacion;
		formu.submit();
	}
</script>