<%@ include file="/html/comun/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
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
			<html:errors/>
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
								<td width="20%"><nested:write property="rutEmpresaFmt"/></td>
								<td><strong>Empresa:</strong></td>
								<td><nested:write property="nombreEmpresa"/></td>
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
									<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de Sucursales</strong></td>
									<td align="right" bgcolor="#FFFFFF">
										<html:button property="operacion" styleClass="btn3" value="Crear Sucursal" onclick="javascript:creaSucursal();"/>&nbsp;&nbsp;&nbsp;
									    <c:set var="url"><c:url value='/SucursalesLista.do'/>?rutEmpresa=<%=rutEmpresaBean%>&operacion=imprimir&accion=admin&subAccion=imprimir</c:set>
									    <html:button property="operacion" styleClass="btn3" value="${imprimir}" onclick="javascript:imprime('${url}');"/>
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
												<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%>  C&oacute;digo</td>
												<td width="70%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Nombre</td>
												<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas" colspan="3">Acci&oacute;n</td>
											</tr>
											<nested:iterate id="filaSuc" property="consultaSucursales" indexId="nFila">
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
															<html:link title="Ver detalle" action="/SucursalesEditar.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesFicha&rutEmpresa=${filaSuc.idEmpresa}&idSucursal=${filaSuc.idSucursal}"  styleClass="links">
																<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0" />
															</html:link>
														</center>
													</td>
													<td align="center" nowrap="nowrap" class="${estilo}">
														<center>
															<html:link action="/SucursalesEditar.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesEditar&rutEmpresa=${filaSuc.idEmpresa}&idSucursal=${filaSuc.idSucursal}">
																<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" height="13" border="0" alt="Editar" title="Editar" />
															</html:link>
														</center>
													</td>
													<td align="center" nowrap="nowrap" class="${estilo}">
														<center>
															<%-- emite aviso si es casa matriz --%>
															<nested:notEqual property="esCasaMatriz" value="true">
																<a href="javascript:borraSucursal(${filaSuc.idEmpresa}, '${filaSuc.idSucursal}');">
																	<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar"/>
																</a>
															</nested:notEqual>
															<nested:equal property="esCasaMatriz" value="true">
																<a href="javascript:alert('Esta Sucursal no se puede eliminar, ya que corresponde a la Casa Matriz.');">
																	<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" height="16" border="0" alt="Borrar" title="Borrar"/>
																</a>
															</nested:equal>
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
		<!-- Insertando Paginacion -->
		<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
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
		</td>
		</tr>
	</table>
</html:form>
<script language="javaScript">
<!--
	function borraSucursal(idEmpresa, idSucursal)
	{
		if (!confirm("Está seguro que desea borrar la sucursal código: " + idSucursal))
			return;

		document.getElementById("operacion").value = "borrar";
		document.getElementById("idEmpresaBorrar").value = idEmpresa;
		document.getElementById("idSucursalBorrar").value = idSucursal;
		document.getElementById("formulario").submit();
	}

	function creaSucursal() 
	{
		document.getElementById("operacion").value = "Crear Sucursal";
		document.getElementById("formulario").submit();
	}
	
	function imprime(url) 
	{
		target = "_blank";
		window.open(url, target, "height=600,width=650,toolbar=no,directories=no,"
			+ "scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
	
	}
	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		formu.action = "SucursalesLista.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesLista&rutEmpresa=${SucursalActionForm.rutEmpresa}&paginaNumero=" + paginacion;
		formu.submit();
	}
// -->
</script>
</body>
</html:html>
