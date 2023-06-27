<%@ include file="/html/comun/taglibs.jsp" %>
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
<html:form action="/DetalleAdministrador" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioFicha" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="left" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
					<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="1">
										<tr valign="bottom">
											<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle del Administrador</strong></td>
										</tr>
									</table>
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
										<tr>
											<td width="10%" height="17" class="barratablas">RUT:</td>
											<nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
											<td height="17" class="textos_formularios"><nested:write property="idUsuarioFmt"/></td>
											<td height="17" class="barratablas">Estado:</td>
											<td height="17" class="textos_formularios"><nested:write property="nombreHabilitado"/></td>
										</tr>
										<tr>
											<td height="17" class="barratablas">Nombre:</td>
											<td height="17" class="textos_formularios"><nested:write property="nombre"/> <nested:write property="apPat"/> <nested:write property="apMat"/></td>
											<td height="17" class="barratablas">Email:</td>
											<td height="17" class="textos_formularios">&nbsp;<nested:write property="email"/></td>
										</tr>
										<tr>
											<td height="17" class="barratablas">Tel&eacute;fono:</td>
											<td width="27%" height="17" class="textos_formularios">&nbsp;(<nested:write property="codigoFono"/>)<nested:write property="fono"/></td>
											<td width="9%" height="17" class="barratablas">Celular:</td>
											<td width="23%" height="17" class="textos_formularios"><nested:notEqual property="celular" value="0"><nested:write property="celular"/></nested:notEqual>&nbsp;</td>
										</tr>
										<tr>
											<td height="17" class="barratablas">Fax:</td>
											<td height="17" class="textos_formularios">&nbsp;<nested:present property="codigoFax">(<nested:write property="codigoFax"/>)</nested:present><nested:write property="fax"/></td>
											<td height="17" class="barratablas">Direcci&oacute;n:</td>
											<td height="17" class="textos_formularios">&nbsp;<nested:write property="direccion"/></td>
										</tr>
										<tr>
											<td height="17" class="barratablas">N&ordm;:</td>
											<td height="17" class="textos_formularios">&nbsp;<nested:write property="numero"/></td>
											<td height="17" class="barratablas">Depto:</td>
											<td height="17" class="textos_formularios">&nbsp;<nested:write property="dpto"/></td>
										</tr>
										<tr>
											<td height="17" class="barratablas">Regi&oacute;n:</td>
											<td height="17" class="textos_formularios"><nested:write property="nombreRegion"/></td>
											<td height="17" class="barratablas">Ciudad:</td>
											<td height="17" class="textos_formularios"><nested:write property="nombreCiudad"/></td>
										</tr>
										<tr>
											<td height="17" class="barratablas">Comuna:</td>
											<td height="17" class="textos_formularios"><nested:write property="nombreComuna"/></td>
											<td height="17" class="barratablas">Admin. Araucana?:</td>
											<td height="17" class="textos_formularios">
												<nested:hidden property="adminSistemaAraucana"><nested:write property="adminSistemaAraucana"/></nested:hidden>
												<nested:equal property="adminSistemaAraucana" value="true">
													S&iacute;
												</nested:equal>
												<nested:notEqual property="adminSistemaAraucana" value="true">
													No
												</nested:notEqual>
											</td>
										</tr>
              <tr> 
	                          	<td height="17" class="barratablas">Tipo Administrador Empresa:</td>
	                            
	                            <td height="17" class="textos_formularios">
	                                	<nested:hidden property="tipoAdminEmpresa">
	                            		<nested:write property="tipoAdminEmpresa"/>
	                            		</nested:hidden>                            
	                            	<nested:equal property="tipoAdminEmpresa" value="true">
	                            		Si
	                            	</nested:equal>
	                            	<nested:notEqual property="tipoAdminEmpresa" value="true">
	                            		No
	                            	</nested:notEqual>
	                            </td>
	                            <td height="17" class="barratablas">Tipo Administrador Independiente:</td>
	                            <td height="17" class="textos_formularios">
		                            <nested:hidden property="tipoAdminIndependiente">
	                            		<nested:write property="tipoAdminIndependiente"/>
	                            		</nested:hidden>                            
	                            	<nested:equal property="tipoAdminIndependiente" value="true">
	                            		Si
	                            	</nested:equal>
	                            	<nested:notEqual property="tipoAdminIndependiente" value="true">
	                            		No
	                            	</nested:notEqual>
	                            </td>
	                          </tr>
										<tr align="left">
											<td class="barratablas">&nbsp;</td>
											<td class="textos_formularios">&nbsp;</td>
											<td class="barratablas"><strong>Admin. CCAF?</strong></td>
											<td class="textos_formularios" valign="middle">
												<nested:hidden property="adminCCAF"><nested:write property="adminCCAF"/></nested:hidden>
												<nested:equal property="adminCCAF" value="true">
													S&iacute;&nbsp;(<nested:write property="nombreCCAF"/>)
												</nested:equal>
												<nested:notEqual property="adminCCAF" value="true">
													No
												</nested:notEqual>
											</td>
										</tr>
										<tr>
											<td height="4" colspan="6" bgcolor="#85b4be"></td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<logic:equal name="DetalleAdministradorActionForm" property="administrador" value="SI">
					<tr>
						<td><br /></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
								<tr>
									<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos de administrador sobre empresas</a></strong></td>
								</tr>
							</table>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								<tr>
									<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> RUT</td>
									<td width="85%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
								</tr>
								<nested:equal property="admin" value="true">
									<nested:iterate id="filaEmpresa" property="consultaPermAdminEmp" indexId="nFila">
										<c:choose>
											<c:when test="${nFila % 2 == 0}">
												<c:set var="estilo" value="textos_formularios"/>
											</c:when>
											<c:otherwise>
												<c:set var="estilo" value="textos-formcolor2"/>
											</c:otherwise>
										</c:choose>
										<nested:equal property="esAdmin" value="true">
											<tr>
												<td class="${estilo}">
													<nested:hidden property="idEmpresaFmt"/>
													<nested:write property="idEmpresaFmt"/>
												</td>
												<td class="${estilo}">
													<nested:hidden property="razonSocial"/>
													<nested:write property="razonSocial"/>
												</td>
											</tr>
										</nested:equal>
									</nested:iterate>
								</nested:equal>
								<nested:notEqual property="admin" value="true">
									<tr>
										<td class="textos_formularios" colspan="2">
											Usuario no tiene empresas administradas
										</td>
									</tr>
								</nested:notEqual>
							</table>
						</td>
					</tr>
				</logic:equal>
				<tr>
					<td height="41" valign="top" align="center"><br />
						<html:submit property="operacion" styleClass="btn3" value="Editar" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="170">&nbsp;</td>
	</tr>
</table>
</html:form>
</body>
</html:html>
