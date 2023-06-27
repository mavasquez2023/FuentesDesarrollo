<%@ include file="/html/comun/taglibs.jsp" %>
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
                            		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle de Encargado</strong></td>
                          		</tr>
                        	</table>
	                        <table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	                            <tr> 
	                            <td width="10%" height="17" class="barra_tablas">RUT:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:hidden property="idUsuario" />
	                            	<nested:write property="idUsuarioFmt"/>
	                            </td>
	                            <td height="17" class="barra_tablas">Estado:</td>
	                            <td height="17" class="textos_formularios">
	                            	<bean:write name="DetalleUsuariosActionForm" property="nombreHabilitado"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barra_tablas">Nombre:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:write property="nombre"/> <bean:write name="DetalleUsuariosActionForm" property="apPat"/> <bean:write name="DetalleUsuariosActionForm" property="apMat"/>
	                            </td>
	                              <td height="17" class="barra_tablas">e-mail:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<bean:write name="DetalleUsuariosActionForm" property="email"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barra_tablas">Tel&eacute;fono:</td>
	                            <td width="27%" height="17" class="textos_formularios">
	                            	&nbsp;(<bean:write name="DetalleUsuariosActionForm" property="codigoFono"/>)<bean:write name="DetalleUsuariosActionForm" property="fono"/>
	                            </td>
	                            <td width="9%" height="17" class="barra_tablas">Celular:</td>
	                            <td width="23%" height="17" class="textos_formularios">
	                            	<logic:notEqual name="DetalleUsuariosActionForm" property="celular" value="0">
	                            		<bean:write name="DetalleUsuariosActionForm" property="celular"/>
	                            	</logic:notEqual>&nbsp;
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barra_tablas">Fax:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<nested:present property="codigoFax">(<bean:write name="DetalleUsuariosActionForm" property="codigoFax"/>)</nested:present><bean:write name="DetalleUsuariosActionForm" property="fax"/>
	                            </td>
	                            <td height="17" class="barra_tablas">Direcci&oacute;n:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<bean:write name="DetalleUsuariosActionForm" property="direccion"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barra_tablas">N&ordm;:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<bean:write name="DetalleUsuariosActionForm" property="numero"/>
	                            </td>
	                            <td height="17" class="barra_tablas">Depto:</td>
	                            <td height="17" class="textos_formularios">
	                            	&nbsp;<bean:write name="DetalleUsuariosActionForm" property="dpto"/>
	                            </td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barra_tablas">Regi&oacute;n:</td>
	                            <td height="17" class="textos_formularios">
	                            	<bean:write name="DetalleUsuariosActionForm" property="nombreRegion"/>
	                            </td>
	                            <td height="17" class="barra_tablas">Ciudad:</td>
	                            <td height="17" class="textos_formularios">
	                            	<bean:write name="DetalleUsuariosActionForm" property="nombreCiudad"/>
	                            </td>
	                          </tr>
	                           <tr> 
	                            <td height="17" class="barra_tablas">Comuna:</td>
	                            <td height="17" class="textos_formularios">
	                            	<bean:write name="DetalleUsuariosActionForm" property="nombreComuna"/>
	                            </td>
	                            <td class="barra_tablas">&nbsp;</td>
	                            <td class="textos_formularios">&nbsp;</td>
	                          </tr>
	                        </table>
	                        </td>
                    	</tr>
                  	</table>
                  	</td>
              	</tr>
              	<tr align="center"> 
                	<td height="41" valign="top">
                	<br />
               		<table width="100%" border="0" cellpadding="0" cellspacing="1">
                   		<tr valign="bottom"> 
                     		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Permisos</strong></td>
                   		</tr>
                 	</table>
                  	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
                    	<tr> 
                      		<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre Convenios</a></strong></td>
                    	</tr>
                  	</table>
                  	<table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
				         <tr>
				           <td colspan="2" width="140" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Grupo Convenio</td>
				           <td colspan="2" width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
				           <td width="125" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
				           <td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
				           <td width="75" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenios</td>
				           <td colspan="4" width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Planillas</td>
				         </tr>
				         <tr>
				           <td colspan="7" bordercolor="#FFFFFF" class="barra_tablas">&nbsp;</td>
				           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Gr</td>
				           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Em</td>
				           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cn</td>
				           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Sc</td>
				         </tr>
				         <nested:notEmpty property="permisos" name="DetalleUsuariosActionForm">
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
										<td align="center" valign="middle" nowrap="nowrap" class="${estilo}">
											<c:if test="${numEmp > 1}"><a style="cursor:pointer" onclick="swapAll('tr-gr-${grupo.idGrupo}', 'tr-gr-img${grupo.idGrupo}');"><img id="tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
											<c:if test="${numEmp <= 1}">&nbsp;</c:if>
										</td>
							         	<td width="120" class="${estilo}">${grupo.nombre}</td>
							         	<c:if test="${numEmp > 1}">
						   		    		<td colspan="5" class="${estilo}">&nbsp;</td>
						   		    		<td class="${estilo}">&nbsp;<c:if test="${grupo.permLector}">S&iacute;</c:if></td>
						   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
						   		    	</c:if>
							         	<c:if test="${numEmp == 1}">
						   					<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
												<bean:size id="numConv" name="empresa" property="convenios"/>
												<td align="center" valign="middle" nowrap="nowrap" class="${estilo}">
													<c:if test="${numConv > 1}"><a style="cursor:pointer" onclick="swapAll('tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>														
													<c:if test="${numConv <= 1}">&nbsp;</c:if>
												</td>
									         	<td nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
									         	<td width="125" class="${estilo}">${empresa.razonSocial}</td>
									         	<c:if test="${numConv > 1}">
					   		    					<td colspan="3" class="${estilo}">&nbsp;</td>
								   		    		<td class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
								   		    		<td colspan="2" class="${estilo}">&nbsp;</td>
								   		    		</tr>
													<tr id="tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
														<td colspan="11" class="${estilo}">
															<table width="590" cellpadding="0" cellspacing="0" border="0" align="right">
																<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
																	<tr>
																		<td width="20" class="${estilo}">&nbsp;</td>
																		<td width="120" class="${estilo}">&nbsp;</td>
																		<td width="20"class="${estilo}">&nbsp;</td>
															         	<td width="90" class="${estilo}">&nbsp;</td>
															         	<td width="125" class="${estilo}">&nbsp;</td>
										   		    					<td width="60" class="${estilo}"><div align="right">${perm.idConvenio}</div></td>
										   		    					<td width="75" class="${estilo}"><div align="right">&nbsp;${perm.nombreNivel}</div></td>							   		    											   		    					
										   		    					<td width="20" class="${estilo}">&nbsp;</td>
										   		    					<td width="20" class="${estilo}">&nbsp;</td>
										   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${perm.convenioLector}">S&iacute;</c:if></td>
										   		    					<td width="20" class="${estilo}">&nbsp;${perm.sucursalLector}</td>
																	</tr>
															    </nested:iterate>
															</table>
														</td>
												</c:if>
												<c:if test="${numConv == 1}">
						   							<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
						   		    					<td class="${estilo}"><div align="right">${perm.idConvenio}</div></td>
						   		    					<td width="75" class="${estilo}"><div align="right">&nbsp;${perm.nombreNivel}</div></td>							   		    					
						   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${grupo.permLector}">S&iacute;</c:if></td>
						   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
						   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${perm.convenioLector}">S&iacute;</c:if></td>
						   		    					<td width="20" class="${estilo}">&nbsp;${perm.sucursalLector}</td>
					   		    					</nested:iterate>
					   		    				</c:if>
												<c:if test="${numConv == 0}">
					   		    					<td width="60" class="${estilo}">&nbsp;</td>
					   		    					<td width="75" class="${estilo}">&nbsp;</td>							   		    											   		    					
					   		    					<td width="20" class="${estilo}">&nbsp;</td>
					   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
					   		    					<td width="20" class="${estilo}">&nbsp;</td>
					   		    					<td width="20" class="${estilo}">&nbsp;</td>
												</c:if>
									         </nested:iterate>
						   		    	</c:if>
							         	<c:if test="${numEmp == 0}">
						   		    		<td colspan="5" class="${estilo}">&nbsp;</td>
						   		    		<td class="${estilo}">&nbsp;<c:if test="${grupo.permLector}">S&iacute;</c:if></td>
						   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
						   		    	</c:if>
									</tr>
									<c:if test="${numEmp > 1}">
										<tr id="tr-gr-${grupo.idGrupo}" style="display:none">
											<td colspan="11" class="${estilo}">
												<table width="590" cellpadding="0" cellspacing="0" border="0" align="right">
													<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
														<tr>
															<bean:size id="numConv" name="empresa" property="convenios"/>
															<td width="20" class="${estilo}">&nbsp;</td>
															<td width="120" class="${estilo}">&nbsp;</td>
															<td width="20" align="center" valign="middle" nowrap="nowrap" class="${estilo}">
																<c:if test="${numConv > 1}"><a style="cursor:pointer" onclick="swapAll('tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>														
																<c:if test="${numConv <= 1}">&nbsp;</c:if>
															</td>
												         	<td width="90" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
												         	<td width="125" class="${estilo}">${empresa.razonSocial}</td>
															<c:if test="${numConv > 1}">
								   		    					<td width="60" class="${estilo}">&nbsp;</td>
								   		    					<td width="75" class="${estilo}">&nbsp;</td>
								   		    					<td width="20" class="${estilo}">&nbsp;</td>
											   		    		<td width="20" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
								   		    					<td width="20" class="${estilo}">&nbsp;</td>
								   		    					<td width="20" class="${estilo}">&nbsp;</td>
											   		    		</tr>												   		    		
																<tr id="tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
																	<td colspan="11" class="${estilo}">
																		<table width="590" cellpadding="0" cellspacing="0" border="0" align="right">
																			<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
																				<tr>
																					<td width="20" class="${estilo}">&nbsp;</td>
																					<td width="120" class="${estilo}">&nbsp;</td>
																					<td width="20"class="${estilo}">&nbsp;</td>
																		         	<td width="90" class="${estilo}">&nbsp;</td>
																		         	<td width="125" class="${estilo}">&nbsp;</td>
													   		    					<td width="60" class="${estilo}"><div align="right">${perm.idConvenio}</div></td>
													   		    					<td width="75" class="${estilo}"><div align="right">&nbsp;${perm.nombreNivel}</div></td>
													   		    					<td width="20" class="${estilo}">&nbsp;</td>
													   		    					<td width="20" class="${estilo}">&nbsp;</td>
													   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${perm.convenioLector}">S&iacute;</c:if></td>
													   		    					<td width="20" class="${estilo}">&nbsp;${perm.sucursalLector}</td>
																				</tr>
																		    </nested:iterate>
																		</table>
																	</td>
															</c:if>
															<c:if test="${numConv == 1}">
									   							<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">						         
									   		    					<td width="60" class="${estilo}"><div align="right">${perm.idConvenio}</div></td>
									   		    					<td width="75" class="${estilo}"><div align="right">&nbsp;${perm.nombreNivel}</div></td>							   		    											   		    					
									   		    					<td width="20" class="${estilo}">&nbsp;</td>
									   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
									   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${perm.convenioLector}">S&iacute;</c:if></td>
									   		    					<td width="20" class="${estilo}">&nbsp;${perm.sucursalLector}</td>
								   		    					</nested:iterate>
															</c:if>
															<c:if test="${numConv == 0}">
									   		    					<td width="60" class="${estilo}">&nbsp;</td>
									   		    					<td width="75" class="${estilo}">&nbsp;</td>							   		    											   		    					
									   		    					<td width="20" class="${estilo}">&nbsp;</td>
									   		    					<td width="20" class="${estilo}">&nbsp;<c:if test="${empresa.permLector}">S&iacute;</c:if></td>
									   		    					<td width="20" class="${estilo}">&nbsp;</td>
									   		    					<td width="20" class="${estilo}">&nbsp;</td>
															</c:if>
														</tr>
												    </nested:iterate>
												</table>
											</td>
										</tr>
									</c:if>
								</nested:iterate>
							</nested:notEmpty>
						<nested:empty property="permisos" name="DetalleUsuariosActionForm">
							<tr>
								<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="11">
									Encargado no tiene permisos definidos o encargado autenticado no tiene privilegios
								</td>
							</tr>
						</nested:empty>
					</table>
                  	<br />
                	</td>
              	</tr>
              	<tr>
                	<td height="41" valign="top" align="center"><br />
                		<html:submit property="operacion" styleClass="btn_grande" value="${editar}" />
                	</td>
              	</tr>
            </table>
            </td>
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
