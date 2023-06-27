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
	<TITLE>Entidades</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/entidad.js'/>"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body>
<html:form action="/ListaEntidadesFicha" styleId="formulario">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="entidadesFicha" />
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
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de Entidades</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<html:button property="operacion" styleClass="btn3" value="Crear Entidad" onclick="javascript:addEntidad();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr class="subtitulos_tablas">
					<td width="3%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Entidad</td>
					<td width="40%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%>Nombre</td>
					<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Folio</td>
					<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Inicio</td>
					<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fin</td>
					<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Actual</td>
					<td colspan="2" width="7%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acciones</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('salud', 'img22');">
								<img id="img22" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7" class="textos_formularios" align="left">
						<strong>Salud</strong>
					</td>
					<td width="25%" class="textos_formularios">&nbsp;</td>
				</tr>
				<tr id="salud" style="display:none">
					<td colspan="9">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadSalud">
								<nested:iterate id="fila" property="listaEntidadSalud" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
							
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<html:link title="Editar" action="/EdicionEntidadesSalud.do?tipoEdicion=SALUD&idEntPagadora=${idEntPagadora}&accionInterna=LOAD"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>														
														<a href="javascript:delConfirmar('SALUD','${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
												<div align="center">
													<html:link title="Editar" action="/EdicionEntidadesSalud.do?tipoEdicion=SALUD&idEntPagadora=${idEntPagadora}&accionInterna=LOAD"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
													<a href="javascript:delConfirmar('SALUD', '${idEntPagadora}');">
														<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
													</a>
												</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
		                  	</nested:notEmpty>
								<nested:empty property="listaEntidadSalud">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Salud
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('sil', 'img2');">
								<img id="img2" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7" class="textos-formcolor2" align="left">
						<strong>SIL</strong>
					</td>
					<td width="25%" class="textos-formcolor2">&nbsp;</td>
				</tr>
				<tr id="sil" style="display:none">
					<td colspan="9">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadSil">
							<nested:iterate id="fila" property="listaEntidadSil" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
							
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
											<div align="center">
												<html:link title="Editar" action="/EdicionEntidadesSil.do?tipoEdicion=SIL&idEntPagadora=${idEntPagadora}&accionInterna=LOAD"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
												<a href="javascript:delConfirmar('SIL', '${idEntPagadora}');">
													<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
												</a>
											</div>
										</td>
									</nested:notEmpty>
									<nested:empty property="nombre">
			                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:write property="folioActual" />&nbsp;
			                    		</td>
									</nested:empty>
								</nested:notEqual>
								<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesSil.do?tipoEdicion=SIL&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('SIL', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
		                  	</nested:notEmpty>
								<nested:empty property="listaEntidadSil">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para SIL
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('afp', 'img3');">
								<img id="img3" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7"   class="textos_formularios" align="left">
						<strong>AFP</strong>
					</td>
					<td width="25%" class="textos_formularios">&nbsp;</td>
				</tr>
				<tr id="afp" style="display:none">
					<td colspan="9" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadFondoPension">
							<nested:iterate id="fila" property="listaEntidadFondoPension" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		</td>
										<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesAfp.do?tipoEdicion=AFP&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('AFP', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
												<div align="center">
							                		<html:link action="/EdicionEntidadesAfp.do?tipoEdicion=AFP&idEntPagadora=${idEntPagadora}&accionInterna=LOAD" title="Editar">
							                			<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
							                		</html:link>
							                		<a href="javascript:delConfirmar('AFP', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
												</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
								<nested:empty property="listaEntidadFondoPension">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Fondos de Pensiones
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('apv', 'img4');">
								<img id="img4" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7"   class="textos-formcolor2" align="left">
						<strong>APV</strong>
					</td>
					<td width="25%" class="textos-formcolor2">&nbsp;</td>
				</tr>
				<tr id="apv" style="display:none">
					<td colspan="9" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadApv">
							<nested:iterate id="fila" property="listaEntidadApv" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		</td>
										<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesApv.do?tipoEdicion=APV&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('APV', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesApv.do?tipoEdicion=APV&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('APV' , '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
								<nested:empty property="listaEntidadApv">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Ahorro Previsional Voluntario
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('ccaf', 'img40');">
								<img id="img40" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7"   class="textos_formularios" align="left">
						<strong>CCAF</strong>
					</td>
					<td width="25%" class="textos_formularios">&nbsp;</td>
				</tr>
				<tr id="ccaf" style="display:none">
					<td colspan="9" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadCcaf">
							<nested:iterate id="fila" property="listaEntidadCcaf" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		</td>
										<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesCcaf.do?tipoEdicion=CCAF&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('CCAF', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesCcaf.do?tipoEdicion=CCAF&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('CCAF' , '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
								<nested:empty property="listaEntidadCcaf">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Cajas de Compensaci&oacute;n
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('afc', 'img5');">
								<img id="img5" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7"   class="textos-formcolor2" align="left">
						<strong>AFC</strong>
					</td>
					<td width="25%" class="textos-formcolor2">&nbsp;</td>
				</tr>
				<tr id="afc" style="display:none">
					<td colspan="9" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadAfc">
							<nested:iterate id="fila" property="listaEntidadAfc" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		</td>
										<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesAfc.do?tipoEdicion=AFC&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('AFC', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="10%">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
												<div align="center">
														<a href="<c:url value='/EdicionEntidadesAfc.do?tipoEdicion=AFC&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('AFC', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
								<nested:empty property="listaEntidadAfc">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Fondos de Seguros de cesa&iacute;a
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle"  class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('mutual', 'img7');">
								<img id="img7" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="7" width="680"  class="textos_formularios" align="left">
						<strong>Mutual</strong>
					</td>
					<td width="25%" class="textos_formularios">&nbsp;</td>
				</tr>
				<tr id="mutual" style="display:none">
					<td colspan="9" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
						<nested:notEmpty property="listaEntidadMutual">
							<nested:iterate id="fila" property="listaEntidadMutual" indexId="nFila">
							<nested:define id="nombre" property="nombre" />
							<nested:define id="idEntPagadora" property="idEntPagadora" />
							<nested:define id="idFoliacion" property="idFoliacion" />
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
								<nested:notEqual property="idFoliacion" value="0">
									<td class="${estilo}" width="1%">
										&nbsp;
									</td>
									<td class="${estilo}" width="10%">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="40%">
		                    			&nbsp;<nested:write property="nombre" />
		                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="idFoliacion" value="0">	
		                    				<nested:write property="idFoliacion" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioInicial" value="0">	
		                    				<nested:write property="folioInicial" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<td align="right" valign="middle"  class="${estilo}" width="10%">
		                    			<nested:notEqual property="folioFinal" value="0">	
		                    				<nested:write property="folioFinal" />&nbsp;
		                    			</nested:notEqual>
		                    		</td>
		                    		<nested:notEmpty property="nombre">
			                       		<td align="right" valign="middle"  class="${estilo}" width="10%">
			                    			<nested:notEqual property="folioActual" value="0">	
		                    					<nested:write property="folioActual" />&nbsp;
		                    				</nested:notEqual>
			                    		</td>
										<td width="7%" class="${estilo}" align="center" nowrap="nowrap">
													<div align="center">
														<a href="<c:url value='/EdicionEntidadesMutual.do?tipoEdicion=MUTUAL&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('MUTUAL', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
														</a>
													</div>
												</td>
											</nested:notEmpty>
											<nested:empty property="nombre">
					                       		<td colspan="2" align="right" valign="middle"  class="${estilo}" width="10%">
					                    			<nested:write property="folioActual" />&nbsp;
					                    		</td>
											</nested:empty>
										</nested:notEqual>
										<nested:equal property="idFoliacion" value="0">
										<td class="${estilo}" width="1%">
												&nbsp;
											</td>
											<td class="${estilo}" width="80">
												&nbsp;
											</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="40%">&nbsp;<nested:write property="nombre" />
				                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
				                    		</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                    		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
				                       		<td align="right" valign="middle"  class="${estilo}" width="10%">&nbsp;</td>
											<td width="7%" class="${estilo}" align="center" nowrap="nowrap" nowrap="nowrap">
												<div align="center">
														<a href="<c:url value='/EdicionEntidadesMutual.do?tipoEdicion=MUTUAL&idEntPagadora=${idEntPagadora}&accionInterna=LOAD' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
														</a>
														<a href="javascript:delConfirmar('MUTUAL', '${idEntPagadora}');">
															<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"  />
														</a>
													</div>
											</td>
										</nested:equal>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
								<nested:empty property="listaEntidadMutual">
									<tr>
										<td class=textos_formularios colspan="8">
											No hay entidades definidas para Mutuales
										</td>
									</tr>
								</nested:empty>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script type="text/javascript">
<!--
function swapAll(id, imgId) 
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
		img.alt = "Expandir";
		img.title = "Expandir";
	} else		
	{
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
		img.alt = "Contraer";
		img.title = "Contraer";
	}
}	
function delConfirmar_old(url){
	if (confirm("¿Está seguro de que desea eliminar la Entidad y sus folios?")){
		document.location = url;
	} 
}

function delConfirmar(entidad, id) {
	var url = '';
	var nombreEntidad = document.getElementById('nombreAux_' + id);
	if (nombreEntidad != null) {
		nombreEntidad = trim(nombreEntidad.value);
	}
	
	if (entidad == 'SALUD') {
		url = "<c:url value='/EdicionEntidadesSalud.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
		
	} else if (entidad == 'SIL') {
		url = "<c:url value='/EdicionEntidadesSil.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	} else if (entidad == 'AFP') {
		url = "<c:url value='/EdicionEntidadesAfp.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	} else if (entidad == 'APV') {
		url = "<c:url value='/EdicionEntidadesApv.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	} else if (entidad == 'CCAF') {
		url = "<c:url value='/EdicionEntidadesCcaf.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	} else if (entidad == 'AFC') {
		url = "<c:url value='/EdicionEntidadesAfc.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	} else if (entidad == 'MUTUAL') {
		url = "<c:url value='/EdicionEntidadesMutual.do?tipoEdicion=SALUD&idEntPagadora=" + id + "&accionInterna=DEL_ENTIDAD' />";
	
	}
		
	if (url.value != '') {
		if (confirm("¿Está seguro de que desea eliminar la Entidad " + nombreEntidad + " y sus folios?")){
			document.location = url;
		} 
	}
}
// -->
</script>

</body>
</html:html>


