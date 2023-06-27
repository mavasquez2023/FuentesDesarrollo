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
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleConvenio" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosFicha" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td valign="top">
      	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			<tr> 
				<td width="15%"><strong>RUT:</strong></td>
				<td width="20%">
					
						<nested:write property="rutEmpresaFmt"/>
					
				</td>
				<td><strong>Empresa:</strong></td>
				<td><nested:write property="nombreEmpresa" /></td>
			</tr>
          	<tr> 
	            <td><strong>Convenio:</strong></td>
	            <td>
	            	<nested:select property="opcConvenio" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="convenios"/>
	            	</nested:select>
				</td>
	            <td colspan="2">&nbsp;</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
         	</tr>
        </table>
      	<table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle Convenio</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:write property="codigoConvenio" format="00"/>
                </td>
                <td width="25%" height="17" class="barratablas"><strong>Descripci&oacute;n:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:write property="nombreConvenio"/>
                </td>
              </tr>
              <tr>
                <td height="17" class="barratablas"><strong>Estado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:equal value="0" property="opcHabilitado">
                		Deshabilitado
                	</nested:equal>
                	<nested:equal value="1" property="opcHabilitado">
                		Habilitado
                	</nested:equal>
                </td>
                <td height="17" class="barratablas"><strong>Grupo de Convenios:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreGrupoConvenio"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Creado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="fechaCreado"/>
                </td>
                <td width="25%" height="17" class="barratablas">&Uacute;ltimo uso:</td>
                <td height="17" class="textos_formularios">
                	<nested:write property="fechaUltAcceso"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreCaja"/>
                </td>
                <td height="17" class="barratablas">
                	<strong>
                		C&aacute;lculo de Mov. Personal
                	</strong>
                </td>
                <td height="17" class="textos_formularios">
                	<nested:notEqual property="opcCalculoMovPersonal" value="0">
                		S&iacute;
                	</nested:notEqual>
                	<nested:equal property="opcCalculoMovPersonal" value="0">
                		No
                	</nested:equal>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>N&ordm; N&oacute;minas:</strong></td>
                <td height="17" class="textos_formularios">
                	<div align="right">
	                	<nested:write property="numNominas"/>
                	</div>
                </td>
                <td height="17" class="barratablas"><strong>N&ordm; Cotizaciones:</strong></td>
                <td height="17" class="textos_formularios">
                	<div align="right">
	                	<nested:write property="numCotiz"/>
	                </div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Actividad Econ&oacute;mica</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreActividadEconomica"/>
                </td>
                <td height="17" class="barratablas">&nbsp;</td>
                <td height="17" class="textos_formularios">&nbsp;</td>
              </tr>
              <%--><tr> 
                <td height="17" class="barratablas" colspan="4"><strong>MUTUAL</strong></td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Nº Adherente:</strong></td>
                <td height="17" class="textos_formularios">452</td>
                <td height="17" class="barratablas"><strong>Tasa Adicional:</strong></td>
                <td height="17" class="textos_formularios">0,45</td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Calculo Individual:</strong></td>
                <td height="17" class="textos_formularios">no</td>
                <td height="17" class="barratablas">&nbsp;</td>
                <td height="17" class="textos_formularios">&nbsp;</td>
              </tr><--%>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de Archivos de N&oacute;minas</strong></td>
              </tr>
            </table>
            <bean:define id="idGrupoConvenio"><nested:write property="opcGrupoConvenio"/></bean:define>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barratablas"><strong>Remuneraciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionR"/></td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=R&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Gratificaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionG"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=G&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Reliquidaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionA"/></td>
                <td  height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=A&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Dep&oacute;sitos Convenidos e Indemnizaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionD"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=D&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de C&oacute;digos</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barratablas"><strong>Mapeo:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreMapeoCodigo"/>
                </td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/ListaCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/iconos/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            <br />
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
             	<tr> 
               		<td width="590" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre Convenios</a></strong></td>
             	</tr>
           	</table>
           	<table width="590" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	         <tr>
	           <td colspan="2" width="140" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> Grupo Convenio</td>
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
	         <nested:notEmpty property="permisos">
		         <nested:iterate id="user" property="permisos" indexId="nFilaUser" type="cl.araucana.adminCpe.presentation.struts.javaBeans.Usuario">
		         	<c:choose>
						<c:when test="${nFilaUser % 2 == 0}">
							<c:set var="estilo" value="textos_formularios" />
						</c:when>
						<c:otherwise>
							<c:set var="estilo" value="textos-formcolor2" />
						</c:otherwise>
					</c:choose>
					<tr>
						<td colspan="2" width="140" class="${estilo}">${user.rutFormat}</td>
						<td colspan="3" width="215" class="${estilo}">${user.nombre} ${user.apellidos}</td>
						<td colspan="6" class="${estilo}">&nbsp;</td>
					</tr>
		         	<nested:iterate id="grupo" property="asignaciones" indexId="nFila" type="cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio">
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
																		<td width="20" class="${estilo}">&nbsp;</td>
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
				</nested:iterate>
			</nested:notEmpty>
			<nested:empty property="permisos">
				<tr>
					<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="11">
						Usuario no tiene permisos sobre Convenios
					</td>
				</tr>
			</nested:empty>
		</table>
            </td>
        </tr>
      </table></td>
  </tr>
  <tr align="center">
    <td valign="top"><br />
    	<html:submit property="operacion" value="Editar" styleClass="btn3"/>
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
	    if ( obj.style.display=='' ) {
			obj.style.display='none';
			img.src = '<c:url value="/img/ico_mas.gif" />';
		} else {
			obj.style.display='';
			img.src = '<c:url value="/img/ico_menos.gif" />';
		}
	}
// -->
</script>
</body>
</html:html>
