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
<body onLoad="poneFoco('rutEmpresa')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/AsigPermisosEncargado" styleId="formulario" onsubmit="return onFormSubmit(this)">
<input type="hidden" name="accion" id="accion" value="admin"/>
<input type="hidden" name="subAccion" id="subAccion" value="usuarios"/>
<input type="hidden" name="subSubAccion" id="subSubAccion" value="asigPermEncargado"/>
<input type="hidden" name="idEncargado" id="idEncargado" value="${AsigPermEncargadoActionForm.idEncargado}"/>


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
    		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
		       	<tr> 
		          	<td width="30%"><strong>RUT Encargado:</strong></td>
		          	<td width="30%">${AsigPermEncargadoActionForm.idEncargadoFmt}</td>
		            <td><strong>Nombre:</strong></td>
		          	<td>${AsigPermEncargadoActionForm.nombre}</td>
		       	</tr>
		      	<tr> 
		         	<td><strong>Apellidos:</strong></td>
		         	<td colspan="3">${AsigPermEncargadoActionForm.apellidos}</td>
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
						<tr>
			        		<bean:size id="numEmp" name="grupo" property="empresas"/>
							<td align="center" valign="middle" width="20" class="${estilo}">
								<c:if test="${numEmp >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-gr-${grupo.idGrupo}', 'tr-gr-img${grupo.idGrupo}');"><img id="tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
								<c:if test="${numEmp < 1}">&nbsp;</c:if>
							</td>
				         	<td width="120" class="${estilo}">${grupo.nombre}<nested:hidden property="idGrupo" /></td>
		   		    		<td colspan="6" width="370" class="${estilo}">&nbsp;</td>
		   		    		<td class="${estilo}" align="center"><nested:checkbox property="permLector" /></td>
		   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
						</tr>
						<tr id="tr-gr-${grupo.idGrupo}" style="display:none">
							<td colspan="12">
								<table cellpadding="0" cellspacing="0" border="0">
									<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
										<tr>
											<bean:size id="numConv" name="empresa" property="convenios"/>
											<td width="120" class="${estilo}">&nbsp;</td>
											<td width="15" align="center" valign="middle" class="${estilo}">
												<c:if test="${numConv >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
												<c:if test="${numConv < 1}">&nbsp;</c:if>
											</td>
								         	<td width="100" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
								         	<td width="135" nowrap="nowrap" class="${estilo}">${empresa.razonSocial}<nested:hidden property="idEmpresa" /></td>
			   		    					<td width="165" class="${estilo}">&nbsp;</td>
						   		    		<td width="25" align="center" class="${estilo}"><nested:checkbox property="permLector" /></td>
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
							   		    					<td width="20" align="center" class="${estilo}"><nested:checkbox property="convenioLector" /></td>
							   		    					<td width="15" class="${estilo}">
							   		    						<nested:hidden property="sucursalLector" styleId="idSuc-${empresa.idEmpresa}-${perm.idConvenio}" />
																<html:link action="/PermisosRolLector" target="_blank" onclick="javascript:modalWin(this.href, this.target, 600, 500); return false;" styleClass="links">
																	<html:param name="rutEmpresa">${empresa.idEmpresa}</html:param>
																	<html:param name="rutEmpresaFmt">${empresa.rut}</html:param>
																	<html:param name="nombreEmpresa">${empresa.razonSocial}</html:param>
																	<html:param name="idLectorEmpresa">${AsigPermEncargadoActionForm.idEncargado}</html:param>
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
		<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Convenios sin acceso</td>
	</tr>
	<tr> 
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
	           		<td><strong>RUT Empresa:</strong></td>
	           		<td><html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloRut(this);javascript:limpiaGrupo();" onkeyup="javascript:soloRut(this);javascript:limpiaGrupo();"/></td>
	           		<td><strong>Nombre:</strong></td>
	           		<td><html:text property="razonSocial" styleId="razonSocial" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaGrupo();"/></td>
	         	</tr>
	         	<tr> 
	           		<td><strong>Codigo Grupo Convenio:</strong></td>
	           		<td><html:text property="idGrupo" styleId="idGrupo" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloNumero(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');javascript:limpiaEmpresa();"/></td>
	           		<td><strong>Nombre grupo Convenio:</strong></td>
	           		<td><html:text property="nombreGrupo" styleId="nombreGrupo" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();" onkeyup="javascript:soloAlfanumerico(this, '');javascript:limpiaEmpresa();"/></td>
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
        </td>
       <tr>
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
					<tr>
		        		<bean:size id="numEmp" name="grupo" property="empresas"/>
						<td align="center" valign="middle" width="20" class="${estilo}">
							<c:if test="${numEmp >= 1}"><a style="cursor:pointer" onclick="swapAll('new-tr-gr-${grupo.idGrupo}', 'new-tr-gr-img${grupo.idGrupo}');"><img id="new-tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
							<c:if test="${numEmp < 1}">&nbsp;</c:if>
						</td>
			         	<td width="120" class="${estilo}">${grupo.nombre}<nested:hidden property="idGrupo" /></td>
	   		    		<td colspan="6" class="${estilo}">&nbsp;</td>
	   		    		<td class="${estilo}" nowrap="nowrap" ><nested:checkbox property="permLector" /></td>
	   		    		<td colspan="3" class="${estilo}">&nbsp;</td>
					</tr>
					<tr id="new-tr-gr-${grupo.idGrupo}" style="display:none">
						<td colspan="12">
							<table cellpadding="0" cellspacing="0" border="0" align="right">
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
					   		    		<td width="25" nowrap="nowrap"  class="${estilo}"><nested:checkbox property="permLector" /></td>
		   		    					<td width="30" class="${estilo}">&nbsp;</td>
					   		    	</tr>												   		    		
									<tr id="new-tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
										<td colspan="7">
											<table cellpadding="0" cellspacing="0" border="0" align="right">
												<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
													<tr>
														<td width="360" class="${estilo}">&nbsp;</td>
						   		    					<td width="78" class="${estilo}"><div align="right">${perm.idConvenio}</div><nested:hidden property="idConvenio" /></td>
						   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="2"/></div></td>
						   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="1"/></div></td>
						   		    					<td width="34" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="0"/></div></td>
						   		    					<td width="40" class="${estilo}">&nbsp;</td>
						   		    					<td width="20" class="${estilo}" nowrap="nowrap" ><nested:checkbox property="convenioLector" /></td>
						   		    					<td width="15" class="${estilo}">
										   		    		<nested:hidden property="sucursalLector" styleId="idSuc-${empresa.idEmpresa}-${perm.idConvenio}" />
															<html:link action="/PermisosRolLector" target="_blank" onclick="javascript:modalWin(this.href, this.target, 600, 500); return false;" styleClass="links">
																<html:param name="rutEmpresa">${empresa.idEmpresa}</html:param>
																<html:param name="rutEmpresaFmt">${empresa.rut}</html:param>
																<html:param name="nombreEmpresa">${empresa.razonSocial}</html:param>
																<html:param name="idLectorEmpresa">${AsigPermEncargadoActionForm.idEncargado}</html:param>
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
	<tr align="center">
    	<td height="41" valign="top" ><br />
        	<html:submit property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:bBuscar=false;"/>
            <html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
		</td>
	</tr>
  <tr> 
    <td>&nbsp;</td>
  </tr>
</table>
</html:form>
<script language="javaScript">
<!-- 
	var bBuscar = false;
	function limpiaEmpresa() 
	{
		document.getElementById("rutEmpresa").value = "";
		document.getElementById("razonSocial").value = "";
	}

	function limpiaGrupo() 
	{
		document.getElementById("idGrupo").value = "";
		document.getElementById("nombreGrupo").value = "";
	}
	
	function validaCajasBusqueda(f) 
	{
		var sMsje = "";
		if(!validaReq(document.getElementById("idGrupo")) &&  !validaReq(document.getElementById("nombreGrupo")) && !validaReq(document.getElementById("rutEmpresa")) && !validaReq(document.getElementById("razonSocial"))){
			sMsje = "* Debe ingresar datos de Empresa o Grupo de Convenio para realizar la busqueda";
		}
		if (validaReq(document.getElementById("rutEmpresa")) || validaReq(document.getElementById("razonSocial"))){
			if(validaReq(document.getElementById("rutEmpresa"))){
				if(!validaRut(document.getElementById("rutEmpresa").value)){
					sMsje = "* El formato del rut de la empresa es incorrecto";
				}else{
					if(!validaDV(document.getElementById("rutEmpresa").value)){
						sMsje = "* Digito verificador del rut de la empresa incorrecto";
					}
				}
			}else{
				if(!validaReq(document.getElementById("razonSocial"))){
					sMsje = "* Caracteres invalidos para el nombre de la empresa";
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
			+ "status=no,linemenubar=no,resizable=no,modal=yes");
	}

	function validaFormulario(f) 
	{
		if (bBuscar)
			return validaCajasBusqueda(f);
		var inputs = document.getElementsByTagName("input");
	    for (var i = 0; i < inputs.length; i++) 
	    {
	    	if (inputs[i].type == "radio" && inputs[i].checked == true && inputs[i].value > 0)
				return true;
	    	if (inputs[i].type == "checkbox" && inputs[i].checked == true && inputs[i].name.lastIndexOf("todos") == -1)
				return true;
			if (inputs[i].type == "hidden" && inputs[i].id.lastIndexOf("idSuc-") >= 0 && trim(inputs[i].value) != "")
				return true;
		}
		alert("Para este usuario, debe asignarle al menos un permiso de Encargado de Convenios ó Lector de Planillas.\n\n");
		return false;
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
// End --> 
</script>
</body>
</html:html>
