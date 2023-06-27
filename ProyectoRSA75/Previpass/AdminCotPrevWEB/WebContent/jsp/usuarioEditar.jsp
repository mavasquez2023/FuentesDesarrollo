<%@ include file="/html/comun/taglibs.jsp" %>
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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="ponerfoco();">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleUsuarios" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<logic:equal parameter="subSubAccion" value="usuarioEditar">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioEditar"/>
</logic:equal>
<logic:equal parameter="subSubAccion" value="usuarioCrear">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioCrear"/>
</logic:equal>
<html:hidden property="combosLugaresClick" styleId="combosLugaresClick" value="false" />
<c:set var="opcioneditcrea" value="c"/>


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
    	<td align="left" valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <logic:equal name="DetalleUsuariosActionForm" property="mostrarDatos" value="SI">
         	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
          		<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                	<tr> 
                     	<td>
                        <table width="100%" border="0" cellpadding="0" cellspacing="1">
                          	<tr valign="bottom">
                            	<td height="30" bgcolor="#FFFFFF" class="titulo">
                            		<strong>
	                            		<logic:equal parameter="subSubAccion" value="usuarioEditar">
    	                        			Edici&oacute;n de Encargado
												<c:set var="opcioneditcrea" value="e"/>
    	                        		</logic:equal>
	                            		<logic:equal parameter="subSubAccion" value="usuarioCrear">
    	                        			Creaci&oacute;n de Encargado 
    	                        		</logic:equal>
                            		</strong>
                            	</td>
                          	</tr>
                        </table>
                        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
                          	<tr> 
                            	<td height="4" colspan="6" bgcolor="#85b4be"></td>
                          	</tr>
                          	<tr align="left"> 
	                            <td width="12%" class="barratablas"><strong>RUT *</strong></td>
	                            <td width="38%" class="textos_formularios">
	                            	<nested:hidden property="idUsuario"><nested:write property="idUsuarioFmt"/></nested:hidden>
	                            	<logic:equal parameter="subSubAccion" value="usuarioEditar">
	                            		<nested:text property="idUsuarioFmt" styleId="idUsuarioFmt" maxlength="12" size="20" styleClass="campos" readonly="true" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
	                            	</logic:equal>
	                            	<logic:equal parameter="subSubAccion" value="usuarioCrear">
	                            		<nested:text property="idUsuarioFmt" styleId="idUsuarioFmt" maxlength="12" size="20" styleClass="campos" readonly="false" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
	                            	</logic:equal>
	                            </td>
	                            <td width="11%" class="barratablas"><strong>Estado *</strong></td>
	                            <td width="39%" class="textos_formularios">
			                    	<nested:select property="opcHabilitado" styleClass="campos">
			                    		<html:option value="1">Habilitado</html:option>
			                    		<html:option value="0">Deshabilitado</html:option>
			                    	</nested:select>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Nombre *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="nombre" styleId="nombre" maxlength="30" size="41" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
	                            <td class="barratablas"><strong>Apellido Paterno *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="apPat" styleId="apPat" maxlength="20" size="42" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left">
	                            <td class="barratablas"><strong>Apellido Materno *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="apMat" styleId="apMat" maxlength="20" size="41" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
								<td class="barratablas"><strong>e-mail *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="email" styleId="email" maxlength="100" size="42" styleClass="campos" onblur="javascript:soloEmail(this);" onkeyup="javascript:soloEmail(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Tel&eacute;fono *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="codigoFono" styleId="codigoFono" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            	<nested:text property="fono" styleId="fono" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
	                            <td class="barratablas"><strong>Celular</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="celular" styleId="celular" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Fax</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="codigoFax" styleId="codigoFax" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            	<nested:text property="fax" styleId="fax" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
	                            <td class="barratablas"><strong>Direcci&oacute;n *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:textarea property="direccion" styleId="direccion" onblur="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" rows="2" cols="25" styleClass="campos"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 	                            
	                            <td class="barratablas"><strong>N&ordm; *</strong></td>
	                            <td class="textos_formularios"><strong>
	                            	<nested:text property="numero" styleId="numero" maxlength="6" size="9" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
	                            </strong></td>
	                            <td class="barratablas"><strong>Depto</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="dpto" styleId="dpto" maxlength="6" size="9" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
                            	<td class="barratablas"><strong>Regi&oacute;n *</strong></td>
                            	<td class="textos_formularios"> 
									<html:select property="opcRegion" styleId="opcRegion" styleClass="campos" onchange="javascript:combosChange(this);">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="regiones" />
									</html:select>
                              	</td>
                            	<td class="barratablas"><strong>Ciudad *</strong></td>
                            	<td class="textos_formularios">
									<html:select property="opcCiudad" styleId="opcCiudad" styleClass="campos" onchange="javascript:combosChange(this);">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="ciudades" />
									</html:select>
                              	</td>
                          	</tr>
                          	<tr align="left"> 
                            	<td class="barratablas"><strong>Comuna *</strong></td>
                            	<td class="textos_formularios">
									<html:select property="opcComuna" styleId="opcComuna" styleClass="campos">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="comunas" />
									</html:select>
                              	</td>
                           
                          	
                          		<td  class="barratablas"><strong>Tipo Encargado*</strong></td>
                            	<td  class="textos_formularios">
		                            	Empresa<nested:checkbox property="tipoAdminEmpresa" styleId="tipoAdminEmpresa" styleClass="campos"/> 
		                            	Independiente<nested:checkbox property="tipoAdminIndependiente" styleId="tipoAdminIndependiente" styleClass="campos"/>  
                              	</td>
                            </tr>                         	
                        </table>
                        </td>
                    </tr>
				</table>
				</td>
			</tr>
			<tr>
				<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
			</tr>
			<tr align="center">
             <td height="41" valign="top"><br />
             	<html:submit property="operacion" styleClass="btn3" value="Siguiente" onclick="javascript:bBuscar=false;" />
             </td>
           </tr>
           </logic:equal>
           <logic:equal name="DetalleUsuariosActionForm" property="mostrarPermiso" value="SI">
            <tr align="center"> 
                <td height="41" valign="top">
		    		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
				       	<tr> 
				          	<td width="30%"><strong>RUT Encargado:</strong></td>
				          	<td width="30%">
				          	  <c:choose>
				          	    <c:when  test="${empty DetalleUsuariosActionForm.idEncargadoFmt}">
				          	      ${idUsuarioNuevoFmt}
				          	    </c:when>
				          	    <c:otherwise>${DetalleUsuariosActionForm.idEncargadoFmt}</c:otherwise>
				          	  </c:choose>
				          	</td>
				            <td><strong>Nombre:</strong></td>
				          	<td>${DetalleUsuariosActionForm.nombre}</td>
				       	</tr>
				      	<tr> 
				         	<td><strong>Apellidos:</strong></td>
				         	<td colspan="3">${DetalleUsuariosActionForm.apellidos}</td>
				      	</tr>
				      	<tr> 
				        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
				     	</tr>
				    </table>
           		<table width="100%" border="0" cellpadding="0" cellspacing="1">
               		<tr valign="bottom"> 
                 		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Permisos</strong></td>
               		</tr>
             	</table>
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre Convenios</a></strong></td>
			     	</tr>
					<tr>
						<td>
	                  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					         <tr>
					           <td colspan="2" width="140" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> Grupo Convenio</td>
					           <td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
					           <td width="125" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
					           <td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
					           <td colspan="3" width="75" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenios</td>
					           <td colspan="4" width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Planillas</td>
					         </tr>
					         <tr>
					           <td colspan="5" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Editor<br /><input type="checkbox" id="todosEditor" name="todosEditor" onclick="javascript:cambiaCheck('2')" value="2"></td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Lector<br /><input type="checkbox" id="todosLector" name="todosLector" onclick="javascript:cambiaCheck('1')" value="1"></td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nada<br /><input type="checkbox" id="todosNada" name="todosNada" onclick="javascript:cambiaCheck('0')" value="0"></td>
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
					   		    		<td colspan="6" width="370" class="${estilo}">&nbsp;</td>
					   		    		<td class="${estilo}" nowrap="nowrap"><nested:checkbox property="permLector" /></td>
					   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
									</tr>
									<tr id="tr-gr-${grupo.idGrupo}" style="display:none">
										<td colspan="12">
											<table cellpadding="0" cellspacing="0" border="0">
												<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
													<tr>
														<bean:size id="numConv" name="empresa" property="convenios"/>
														<td width="120" class="${estilo}">&nbsp;</td>
														<td width="25" align="center" valign="middle" nowrap="nowrap" class="${estilo}">
															<c:if test="${numConv >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
															<c:if test="${numConv < 1}">&nbsp;</c:if>
														</td>
											         	<td width="100" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
											         	<td width="135" class="${estilo}">${empresa.razonSocial}<nested:hidden property="idEmpresa" /></td>
						   		    					<td width="165" class="${estilo}">&nbsp;</td>
									   		    		<td width="25" nowrap="nowrap" class="${estilo}"><nested:checkbox property="permLector" /></td>
						   		    					<td width="30" class="${estilo}">&nbsp;</td>
									   		    	</tr>												   		    		
													<tr id="tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
														<td colspan="7">
															<table cellpadding="0" cellspacing="0" border="0">
																<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
																	<tr>
															         	<td width="360" class="${estilo}">&nbsp;</td>
										   		    					<td width="78" class="${estilo}"><div align="right">${perm.idConvenio}</div><nested:hidden property="idConvenio" /></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="2"/></div></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="1"/></div></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="0"/></div></td>
										   		    					<td width="40" class="${estilo}">&nbsp;</td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}"><nested:checkbox property="convenioLector" /></td>
										   		    					<td width="15" class="${estilo}">
										   		    						<nested:hidden property="sucursalLector" styleId="idSuc-${empresa.idEmpresa}-${perm.idConvenio}" />
																			<html:link action="/PermisosRolLector" target="_blank" onclick="javascript:modalWin(this.href, this.target, 620, 500); return false;" styleClass="links">
																				<html:param name="rutEmpresa">${empresa.idEmpresa}</html:param>
																				<html:param name="rutEmpresaFmt">${empresa.rut}</html:param>
																				<html:param name="nombreEmpresa">${empresa.razonSocial}</html:param>
																				<html:param name="idLectorEmpresa">${DetalleUsuariosActionForm.idUsuario}</html:param>
																				<html:param name="idConvenio">${perm.idConvenio}</html:param>
																				<html:param name="idSucHidden">idSuc-${empresa.idEmpresa}-${perm.idConvenio}</html:param>
																				<html:param name="tipo">convenio</html:param>
																				<html:param name="acc">per_</html:param>
																				<img alt="Ver Permisos en Sucursales" title="Ver Permisos en Sucursales" src="<c:url value="/img/iconos/btnListaSucursal.gif" />" width="14" height="13" border="0" />
																			</html:link>
										   		    					</td>
																	</tr>
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
									<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="12">
										Usuario no tiene permisos sobre Convenios
									</td>
								</tr>
							</nested:empty>
						</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
							Convenios sin acceso
						</td>
	                </tr>
				  	<tr> 
				    	<td align="left" valign="top">
					        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
								<tr> 
									<td><strong>RUT Empresa:</strong></td>
					           		<td><html:text property="idEmpresa" styleId="idEmpresa" maxlength="13" size="17" styleClass="campos" onblur="javascript:soloRut(this);javascript:limpiaGrupo();" onkeyup="javascript:soloRut(this);javascript:limpiaGrupo();"/></td>
					           		<td><strong>Nombre:</strong></td>
					           		<td><html:text property="nombreEmpresa" styleId="nombreEmpresa" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();"/></td>
					         	</tr>
					         	<tr> 
					           		<td><strong>Codigo Grupo Convenio:</strong></td>
					           		<td><html:text property="idGrConvenio" styleId="idGrConvenio" maxlength="9" size="17" styleClass="campos" onblur="javascript:soloNumero(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');javascript:limpiaEmpresa();"/></td>
					           		<td><strong>Nombre grupo Convenio:</strong></td>
					           		<td><html:text property="nombreGrConvenio" styleId="nombreGrConvenio" maxlength="30" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();"/></td>
					         	</tr>
					         	<tr> 
					           		<td colspan="3">&nbsp;</td>
					           		<td align="right">
					           			<html:submit property="operacion" styleClass="btn3" value=" Buscar " onclick="javascript:bBuscar=true;" />
					           		</td>
					         	</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
	                    	<tr> 
	                      		<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre Convenios</a></strong></td>
	                    	</tr>
	                  	</table>
	                  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					         <tr>
					           <td colspan="2" width="140" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> Grupo Convenio</td>
					           <td width="90" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
					           <td width="125" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
					           <td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
					           <td colspan="3" width="75" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenios</td>
					           <td colspan="4" width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Planillas
					          </td>
					         </tr>
					         <tr>
					           <td colspan="5" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Editor<br /><input type="checkbox" id="todosEditorNew" name="todosEditorNew" onclick="javascript:cambiaCheck2('2')" value="2"></td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Lector<br /><input type="checkbox" id="todosLectorNew" name="todosLectorNew" onclick="javascript:cambiaCheck2('1')" value="1"></td>
					           <td width="25" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nada<br /><input type="checkbox" id="todosNadaNew" name="todosNadaNew" onclick="javascript:cambiaCheck2('0')" value="0"></td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Gr</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Em</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cn</td>
					           <td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Sc</td>
							</tr>
					         <nested:notEmpty property="newPermisos">
						         <nested:iterate id="grupo" property="newPermisos" indexId="nFila" type="cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio">
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
											<c:if test="${numEmp >= 1}"><a style="cursor:pointer" onclick="swapAll('new-tr-gr-${grupo.idGrupo}', 'new-tr-gr-img${grupo.idGrupo}');"><img id="new-tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
											<c:if test="${numEmp < 1}">&nbsp;</c:if>
										</td>
							         	<td width="120" class="${estilo}">${grupo.nombre}<nested:hidden property="idGrupo" /></td>
					   		    		<td colspan="6" width="370" class="${estilo}">&nbsp;</td>
					   		    		<td class="${estilo}" nowrap="nowrap"><nested:checkbox property="permLector" /></td>
					   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
									</tr>
									<tr id="new-tr-gr-${grupo.idGrupo}" style="display:none">
										<td colspan="12">
											<table cellpadding="0" cellspacing="0" border="0">
												<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
													<tr>
														<bean:size id="numConv" name="empresa" property="convenios"/>
														<td width="120" class="${estilo}">&nbsp;</td>
														<td width="15" align="center" valign="middle" nowrap="nowrap" class="${estilo}">
															<c:if test="${numConv >= 1}"><a style="cursor:pointer" onclick="swapAll('new-tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'new-tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="new-tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
															<c:if test="${numConv < 1}">&nbsp;</c:if>
														</td>
											         	<td width="100" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
											         	<td width="135" class="${estilo}">${empresa.razonSocial}<nested:hidden property="idEmpresa" /></td>
						   		    					<td width="165" class="${estilo}">&nbsp;</td>
									   		    		<td width="25" nowrap="nowrap" class="${estilo}"><nested:checkbox property="permLector" /></td>
						   		    					<td width="30" class="${estilo}">&nbsp;</td>
									   		    	</tr>												   		    		
													<tr id="new-tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
														<td colspan="7">
															<table cellpadding="0" cellspacing="0" border="0">
																<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
																	<tr>
																		<td width="360" class="${estilo}">&nbsp;</td>
										   		    					<td width="78" class="${estilo}"><div align="right">${perm.idConvenio}</div><nested:hidden property="idConvenio" /></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="2"/></div></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="1"/></div></td>
										   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="0"/></div></td>
										   		    					<td width="40" class="${estilo}">&nbsp;</td>
										   		    					<td width="20" nowrap="nowrap" class="${estilo}"><nested:checkbox property="convenioLector" /></td>
										   		    					<td width="15" class="${estilo}">
										   		    						<nested:hidden property="sucursalLector" styleId="idSuc-${empresa.idEmpresa}-${perm.idConvenio}" />
																			<html:link action="/PermisosRolLector" target="_blank" onclick="javascript:modalWin(this.href, this.target, 620, 500); return false;" styleClass="links">
																				<html:param name="rutEmpresa">${empresa.idEmpresa}</html:param>
																				<html:param name="rutEmpresaFmt">${empresa.rut}</html:param>
																				<html:param name="nombreEmpresa">${empresa.razonSocial}</html:param>
																				<html:param name="idLectorEmpresa">${DetalleUsuariosActionForm.idUsuario}</html:param>
																				<html:param name="idConvenio">${perm.idConvenio}</html:param>
																				<html:param name="idSucHidden">idSuc-${empresa.idEmpresa}-${perm.idConvenio}</html:param>
																				<html:param name="tipo">convenio</html:param>
																				<html:param name="acc">per_</html:param>
																				<img alt="Ver Permisos en Sucursales" title="Ver Permisos en Sucursales" src="<c:url value="/img/iconos/btnListaSucursal.gif" />" width="14" height="13" border="0" />
																			</html:link>
										   		    					</td>
																	</tr>
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
							<nested:empty property="newPermisos">
								<tr>
									<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="12">
										No hay resultados para la b&uacute;squeda
									</td>
								</tr>
							</nested:empty>
						</table>
						</td>
					</tr>
				</table>
		       <tr align="center">
             <td height="41" valign="top"><br />
             	<input type="button" value="Volver" onclick="javascript:history.back(1);" class="btn3" />
             	<html:submit property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:bBuscar=false;"/>
             	<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
             </td>
           </tr>
			<html:hidden property="idUsuario" styleId="idUsuario" />
			<html:hidden property="idUsuarioFmt" styleId="idUsuarioFmt" />
			<html:hidden property="nombre" styleId="nombre" />
			<html:hidden property="apPat" styleId="apPat" />
			<html:hidden property="apMat" styleId="apMat" />
			<html:hidden property="password" styleId="password" />
			<html:hidden property="email" styleId="email" />
			<html:hidden property="fono" styleId="fono" />
			<html:hidden property="codigoFono" styleId="codigoFono" />
			<html:hidden property="celular" styleId="celular" />
			<html:hidden property="fax" styleId="fax" />
			<html:hidden property="codigoFax" styleId="codigoFax" />
			<html:hidden property="direccion" styleId="direccion" />
			<html:hidden property="numero" styleId="numero" />
			<html:hidden property="dpto" styleId="dpto" />
			<html:hidden property="opcComuna" styleId="opcComuna" />
			<html:hidden property="adminSistemaAraucana" styleId="adminSistemaAraucana" />
			<html:hidden property="opcHabilitado" styleId="opcHabilitado" />
			<html:hidden property="opcRegion" styleId="opcRegion" />
			<html:hidden property="opcCiudad" styleId="opcCiudad" />
			
			<html:hidden property="tipoAdminEmpresa" styleId="tipoAdminEmpresa" />
			<html:hidden property="tipoAdminIndependiente" styleId="tipoAdminIndependiente" />
			
        </logic:equal>
      </table></td>
  </tr>
  <tr> 
    <td width="170">&nbsp;</td>
  </tr>
</table>
</html:form>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<script language="javaScript">
<!-- 
	var subSubAction = document.getElementById("subSubAccion").value;
	
	function limpiaEmpresa() 
	{
		document.getElementById("idEmpresa").value = "";
		document.getElementById("nombreEmpresa").value = "";
	}

	function limpiaGrupo() 
	{
		document.getElementById("idGrConvenio").value = "";
		document.getElementById("nombreGrConvenio").value = "";
	}
	
	var bBuscar = false;
	function combosChange(combo) 
	{
		if (combo.id == "opcRegion") 
		{
			document.getElementById("opcCiudad").value = "-1";
			document.getElementById("opcComuna").value = "-1";

			bCancel = true;
			document.getElementById("combosLugaresClick").value = "true";
			document.getElementById("formulario").submit();
		} else if (combo.id == "opcCiudad") 
		{
			document.getElementById("opcComuna").value = "-1";

			bCancel = true;			
			document.getElementById("combosLugaresClick").value = "true";
			document.getElementById("formulario").submit();
		}
	}
	
	function validaCajasBusqueda(f) 
	{
		var sMsje = "";
		if(!validaReq(document.getElementById("idGrConvenio")) &&  !validaReq(document.getElementById("nombreGrConvenio")) && !validaReq(document.getElementById("idEmpresa")) && !validaReq(document.getElementById("nombreEmpresa"))){
			sMsje = "* Debe ingresar datos de Empresa o Grupo de Convenio para realizar la búsqueda";
		}
		if (validaReq(document.getElementById("idEmpresa")) || validaReq(document.getElementById("nombreEmpresa"))){
			if(validaReq(document.getElementById("idEmpresa"))){
				if(!validaRut(document.getElementById("idEmpresa").value)){
					sMsje = "* El formato del rut de la empresa es incorrecto";
				}else{
					if(!validaDV(document.getElementById("idEmpresa").value)){
						sMsje = "* Dígito verificador del rut de la empresa incorrecto";
					}
				}
			}else{
				if(!validaReq(document.getElementById("nombreEmpresa"))){
					sMsje = "* Caracteres inválidos para el nombre de la empresa";
				}
			}
		}
				
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function validaFormulario(f) 
	{
		if (bBuscar)
			return validaCajasBusqueda(f);

		var sMsje = "";
		var validar = true;
		if (!validaReq(document.getElementById("fono")))
			sMsje += "* Debe ingresar el teléfono del usuario\n";
		else if (document.getElementById("fono").value.length < 6)
			sMsje += "* Número de teléfono del usuario inválido\n";
			
		if(!validaReq(document.getElementById("idUsuarioFmt")))
			sMsje += "* Debe ingresar el rut\n";
		else if (!validaRut(document.getElementById("idUsuarioFmt").value))
				sMsje += "* Formato de rut inválido en el RUT del usuario.\n";
		else if(!validaDV(document.getElementById("idUsuarioFmt").value))
					sMsje +="* Verifique el Dígito del RUT\n";
		if (!validaReq(document.getElementById("nombre")))
			sMsje += "* Debe ingresar el nombre de usuario\n";
		else if (!validaNombres(document.getElementById("nombre")))
				sMsje += "* Caracteres inválidos en el nombre del usuario.\n";
		if (!validaReq(document.getElementById("apPat")))
			sMsje += "* Debe ingresar el apellido paterno del usuario\n";
		else if (!validaNombres(document.getElementById("apPat")))
				sMsje += "* Caracteres inválidos en el apellido paterno del usuario.\n";
		if (!validaReq(document.getElementById("apMat")))
			sMsje += "* Debe ingresar el apellido materno del usuario\n";
		else if (!validaNombres(document.getElementById("apMat")))
				sMsje += "* Caracteres inválidos en apellido materno del usuario.\n";
		if (!validaReq(document.getElementById("email")))
			sMsje += "* Debe ingresar el email del usuario\n";
		else if (!validaMail(document.getElementById("email").value))
			sMsje += "* Formato inválido en el email del usuario.\n";

		if (document.getElementById("codigoFono").value.length < 1) 
				sMsje += "* Debe ingresar un código de área para el telefono del usuario.\n";

		if (document.getElementById("celular").value != "" && (!validaUInt(document.getElementById("celular").value) || document.getElementById("celular").value.length < 7))
			sMsje += "* Número inválido para el celular del usuario.\n";

		if ((document.getElementById("codigoFax").value.length<1)&&
			(document.getElementById("fax").value.length>0))
				sMsje += "* Debe ingresar un código de área para el Fax del usuario.\n";
			
			if ((document.getElementById("codigoFax").value.length>0)&&
			(document.getElementById("fax").value.length<6))
			 	sMsje += "* No es un número válido el fax del usuario.\n";

		if (!validaReq(document.getElementById("direccion")))
			sMsje += "* Debe ingresar la dirección del usuario\n";
		else if (!validaDireccion(document.getElementById("direccion")))
				sMsje += "* Caracteres inválidos en la dirección del usuario.\n";

		if (!validaReq(document.getElementById("numero")))
			sMsje += "* Debe ingresar el número de la dirección del usuario\n";
		else if (!validaDireccion(document.getElementById("numero")))
			sMsje += "* Caracteres inválidos en el número del usuario.\n";
		if (document.getElementById("dpto").value != "" && !validaDireccion(document.getElementById("dpto")))
				sMsje += "* Caracteres inválidos en el departamento del usuario.\n";
				
		if (document.getElementById("opcRegion").value == "-1")
			sMsje += "* Debe seleccionar la región del usuario\n";
		if (document.getElementById("opcCiudad").value == "-1")
			sMsje += "* Debe seleccionar la ciudad del usuario\n";
		if (document.getElementById("opcComuna").value == "-1")
			sMsje += "* Debe seleccionar la comuna del usuario\n";
				
		/*if (subSubAction == 'usuarioCrear')
		{
			if(document.getElementById("password") && !validaReq(document.getElementById("password")))
				sMsje += "* Debe ingresar la password\n";
			if(document.getElementById("confPassword") && !validaReq(document.getElementById("confPassword")))
				sMsje += "* Debe ingresar la confirmacion de la password\n";			
			if(document.getElementById("confPassword") && document.getElementById("password").value != document.getElementById("confPassword").value)
				sMsje += "* Verifique que las password sean iguales";
			}*/
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}

		if ('${DetalleUsuariosActionForm.mostrarPermiso}' == 'SI' && document.getElementById("opcHabilitado").value == '1')
		{
			var inputs = document.getElementsByTagName("input");
		    for (var i = 0; i < inputs.length; i++) 
		    {
		    	if (inputs[i].type == "radio" && inputs[i].checked == true && inputs[i].value > 0)
					return true;
		    	if (inputs[i].type == "checkbox" && inputs[i].checked == true && inputs[i].name.lastIndexOf("todos") == -1)
					return true;
				//<input type="hidden" name="permisos[0].empresas[0].convenios[0].sucursalLector" value="EE#" id="idSuc-22666543-1">
		    	if (inputs[i].type == "hidden" && inputs[i].id.lastIndexOf("idSuc-") >= 0 && trim(inputs[i].value) != "")
					return true;
			}
			alert("Para este usuario, debe asignarle al menos un permiso de Encargado de Convenios ó Lector de Planillas.\n\n");
			return false;
		}
		return true;
	}

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

	function modalWin(url, target, width, height) 
	{
		window.open(url, target, "height=" + height + ",width=" + width + ",toolbar=no,directories=no,"
			+ "status=no,linemenubar=no,resizable=no,modal=yes,scrollbars=yes");
	}

	function cambiaCheck(valor)
	{
		var inputs = document.forms[0].getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) 
		{
	        if ((inputs[i].type == undefined) || (inputs[i].type != "radio") || (inputs[i].value != valor) || inputs[i].name.lastIndexOf("new") >= 0)
	        	continue;
	        inputs[i].checked = true;
		}

		if (document.getElementById("todosEditor").value != valor)
			document.getElementById("todosEditor").checked = false;
		if (document.getElementById("todosLector").value != valor)
			document.getElementById("todosLector").checked = false;
		if (document.getElementById("todosNada").value != valor)
			document.getElementById("todosNada").checked = false;
	}

	function cambiaCheck2(valor)
	{
		var inputs = document.forms[0].getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) 
		{
	        if ((inputs[i].type == undefined) || (inputs[i].type != "radio") || (inputs[i].value != valor) || inputs[i].name.lastIndexOf("new") == -1)
	        	continue;
	        inputs[i].checked = true;
		}

		if (document.getElementById("todosEditorNew").value != valor)
			document.getElementById("todosEditorNew").checked = false;
		if (document.getElementById("todosLectorNew").value != valor)
			document.getElementById("todosLectorNew").checked = false;
		if (document.getElementById("todosNadaNew").value != valor)
			document.getElementById("todosNadaNew").checked = false;
	}

	function ponerfoco()
	{
		foco =  document.getElementById('opcionfoco').value;
		if (document.getElementById('idUsuarioFmt').type == 'text')
		{
			if(foco == 'c')
				 document.getElementById('idUsuarioFmt').focus();
			else
				 document.getElementById('nombre').focus();
		}
	}	
// End --> 
</script>
</body>
</html:html>
