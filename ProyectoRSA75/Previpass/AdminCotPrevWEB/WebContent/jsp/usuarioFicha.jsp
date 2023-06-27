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
<html:form action="/DetalleUsuarios" styleId="formulario">
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
                            		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle Encargado</strong></td>
                          		</tr>
                        	</table>
	                        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
	                            <tr> 
	                            <td width="10%" height="17" class="barratablas">   RUT:</td>
	                            <nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="idUsuarioFmt"/>
	                            </td>
	                            <td height="17" class="barratablas">Estado:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombreHabilitado"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Nombre:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombre"/> <nested:write property="apPat"/> <nested:write property="apMat"/>
	                            </td>
	                            <td height="17" class="barratablas">Email:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:write property="email"/>
	                            </td>

	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Tel&eacute;fono:</td>
	                            <td width="27%" height="17" class="textos_formularios">
	                            	&nbsp;(<nested:write property="codigoFono"/>)<nested:write property="fono"/>
	                            </td>
	                            <td width="9%" height="17" class="barratablas">Celular:</td>
	                            <td width="23%" height="17" class="textos_formularios">
	                            	<nested:notEqual property="celular" value="0">
	                            		<nested:write property="celular"/>
	                            	</nested:notEqual>&nbsp;
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Fax:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:present property="codigoFax">(<nested:write property="codigoFax"/>)</nested:present><nested:write property="fax"/>
	                            </td>
	                            <td height="17" class="barratablas">Direcci&oacute;n:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:write property="direccion"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">N&ordm;:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:write property="numero"/>
	                            </td>
	                            <td height="17" class="barratablas">Depto:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:write property="dpto"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Regi&oacute;n:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombreRegion"/>
	                            </td>
	                            <td height="17" class="barratablas">Ciudad:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombreCiudad"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Comuna:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombreComuna"/>
	                            </td>
	                            <td height="17" class="barratablas">Tipo Encargado Independiente:</td>
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
	                          <tr> 
	                          	<td height="17" class="barratablas">Tipo Encargado Empresa:</td>
	                            
	                            <td height="17" class="textos_formularios" colspan="3">
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
				<logic:equal name="DetalleUsuariosActionForm" property="encargado" value="SI">
              	<tr align="center"> 
                	<td height="41" valign="top">
                	<br />
                		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					         <tr>
					           <td colspan="2" width="140" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> Grupo Convenio</td>
					           <td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
					           <td width="125" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
					           <td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
					           <td width="75" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenios</td>
					           <td colspan="4" width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Planillas</td>
					         </tr>
					         <tr>
					           <td colspan="6" align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Gr</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Em</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cn</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Sc</td>
					         </tr>
					         <nested:notEmpty property="permisos">
						         <nested:iterate id="grupo" property="permisos" indexId="nFila" type="cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio">
						         	<c:choose>
										<c:when test="${nFila % 2 == 0}">
											<c:set var="estilo" value="textos_formularios" />
										</c:when>
										<c:otherwise>
											<c:set var="estilo" value="textos-formcolor2" />
										</c:otherwise>
									</c:choose>
									<tr class="${estilo}">
						        		<bean:size id="numEmp" name="grupo" property="empresas"/>
										<td align="center" width="20" valign="middle" nowrap="nowrap" class="${estilo}">
											<c:if test="${numEmp >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-gr-${grupo.idGrupo}', 'tr-gr-img${grupo.idGrupo}');" ><img id="tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
											<c:if test="${numEmp < 1}">&nbsp;</c:if>
										</td>
							         	<td width="120" class="${estilo}">${grupo.nombre}<nested:hidden property="idGrupo" /></td>
					   		    		<td width="450" colspan="8" class="${estilo}">&nbsp;</td>
									</tr>
									<tr id="tr-gr-${grupo.idGrupo}" style="display:none">
										<td colspan="10">
											<table cellpadding="0" cellspacing="0" border="0">
												<c:set var="rut" value="0" />
												<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
													<bean:size id="numConv" name="empresa" property="convenios"/>
													<tr>
														<td colspan="3">
															<table cellpadding="0" cellspacing="0" border="0">
																<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
																	<tr>
															         	<td width="160" class="${estilo}">&nbsp;</td>
															         	<c:if test="${rut != empresa.rut}">
																         	<td width="90" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
																         	<td width="125" nowrap="nowrap" class="${estilo}">${empresa.razonSocial}</td>
															         	</c:if>
																		<c:if test="${rut == empresa.rut}">
																			<td width="90" nowrap="nowrap" class="${estilo}">&nbsp;</td>
																         	<td width="125" nowrap="nowrap" class="${estilo}">&nbsp;</td>
																		</c:if>
										   		    					<td width="60" nowrap="nowrap" class="${estilo}"><div align="right">${perm.idConvenio}</div><nested:hidden property="idConvenio" /></td>
										   		    					<td width="75" nowrap="nowrap" class="${estilo}"><div align="right">&nbsp;${perm.nombreNivel}</div></td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}">&nbsp;<c:if test="${grupo.permLector}"></c:if></td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}">&nbsp;<c:if test="${perm.convenioLector}">S&iacute;</c:if></td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}">&nbsp;${perm.sucursalLector}</td>
																	</tr>
																	<c:set var="rut" value="${empresa.rut}" />
															    </nested:iterate>
															</table>
														</td>
													</tr>
											    </nested:iterate>
											</table>
										</td>
									</tr>
								</nested:iterate>
							</nested:notEmpty>
							<nested:empty property="permisos">
								<tr>
									<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="10">
										Usuario no tiene permisos sobre Convenios
									</td>
								</tr>
							</nested:empty>
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
    		<td>&nbsp;</td>
  		</tr>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
function swapAll(id, imgId) 
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
	} else		
	{
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
	}
}												
// End --> 
</script>
</body>
</html:html>
