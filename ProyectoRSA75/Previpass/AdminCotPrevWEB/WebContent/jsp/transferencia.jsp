<%@ include file="/html/comun/taglibs.jsp" %>
<%@ page import="java.util.List"; %>
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
<body onLoad="poneFoco('rutInicio')">
<script type="text/javascript">
<!--
	if ('${TransferenciaActionForm.volver}' == 'OK')
	{
	<%   List lista = (List)request.getSession().getAttribute("listaPath");
		 if (lista != null)
		 {
			String enlace = (String)lista.get(0);
			enlace = enlace.substring(enlace.indexOf("\"") + 1, enlace.indexOf("\"", enlace.indexOf("\"") + 1));
			 %>
			window.location.href = '<%= enlace %>';
		<% } %>
	}
	var bCancel = false;

	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/TransferirPermisos" styleId="formulario" onsubmit="return onFormSubmit(this);" method="GET">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="transferencia" />
<html:hidden property="volver"/>
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
	        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
	           		<td><strong>Rut Usuario Origen:</strong></td>
	           		<td><html:text property="rutInicio" styleId="rutInicio" maxlength="13" size="18" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
	           		<td><strong>Rut Usuario Destino:</strong></td>
	           		<td><html:text property="rutDestino" styleId="rutDestino" maxlength="13" size="18" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
	           	</tr>

	         	<tr> 
	           		<td colspan="3">&nbsp;</td>
	           		<td align="right">
	           			<html:submit property="operacion" styleClass="btn3" value="Buscar"/>
	           		</td>
	         	</tr>
	         	<tr> 
	           		<td height="4" colspan="5" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
	<logic:notEmpty name="TransferenciaActionForm" property="usuarioInicio">
		<tr> 
			<td align="left" valign="top">
	        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
	            	<tr valign="bottom"> 
	              		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Usuarios a Transferir</strong></td>
					</tr>
		         </table>
	        </td>
		</tr>
		<tr align="center"> 
			<td height="33" valign="top">
                <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
                   	<tr> 
                     	<td width="100%" class="barratablas" colspan="4"><strong>Usuario desde donde transferir permisos</strong></td>
                   	</tr>
                    <tr> 
	                    <td width="10%" height="17" class="barratablas">RUT:</td>
	                    <td height="17" class="textos_formularios">${TransferenciaActionForm.usuarioInicio.rutFormat}
	                    	<html:hidden property="rutInicioTrans" name="TransferenciaActionForm" value="${TransferenciaActionForm.usuarioInicio.rut}"/>
	                    </td>
	                    <td height="17" class="barratablas">Nombre:</td>
	                    <td height="17" class="textos_formularios">${TransferenciaActionForm.usuarioInicio.nombre}</td>	                    
					</tr>
                    <tr> 
	                    <td width="10%" height="17" class="barratablas">Apellidos:</td>
	                    <td height="17" class="textos_formularios" colspan="3">${TransferenciaActionForm.usuarioInicio.apellidos}</td> 
					</tr>
                   	<tr> 
                     	<td width="100%" class="barratablas" colspan="4"><strong>Usuario hacia donde transferir permisos</strong></td>
                   	</tr>
                    <tr> 
	                    <td width="10%" height="17" class="barratablas">RUT:</td>
	                    <td height="17" class="textos_formularios">${TransferenciaActionForm.usuarioDestino.rutFormat}
	                    	<html:hidden property="rutDestinoTrans" name="TransferenciaActionForm" value="${TransferenciaActionForm.usuarioDestino.rut}"/></td>
	                    <td height="17" class="barratablas">Nombre:</td>
	                    <td height="17" class="textos_formularios">${TransferenciaActionForm.usuarioDestino.nombre}</td>	                    
					</tr>
                    <tr> 
	                    <td width="10%" height="17" class="barratablas">Apellidos:</td>
	                    <td height="17" class="textos_formularios" colspan="3">${TransferenciaActionForm.usuarioDestino.apellidos}</td> 
					</tr>
				</table>	           			
			</td>
		</tr>
		<tr>
			<td align="center"><br /><html:submit property="operacion" styleClass="btn3" value="Transferir"/><br /></td>
		</tr>
		<tr>
			<td>
               		<table width="100%" border="0" cellpadding="0" cellspacing="1">
                   		<tr valign="bottom"> 
                     		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Permisos a Transferir</strong></td>
                   		</tr>
                 	</table>
                  	<br />
                  	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
                    	<tr> 
                      		<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre Convenios en Sucursales</a></strong></td>
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
					         <nested:notEmpty property="permisosEncLector">
						         <nested:iterate id="grupo" property="permisosEncLector" indexId="nFila" type="cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio">
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
							</nested:notEmpty>
							<nested:empty property="permisosEncLector">
								<tr>
									<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="11">
										Usuario no tiene permisos sobre Convenios
									</td>
								</tr>
							</nested:empty>
						</table>
						<br />
		                <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
		                    <tr> 
		                      	<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos de administrador sobre empresas</a></strong></td>
		                    </tr>
		                </table>
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
							<tr>
								<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
								<td width="85%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
							</tr>
				        	 <nested:notEmpty property="permisosAdmin">
								<nested:iterate id="filaEmpresa" property="permisosAdmin" indexId="nFila">
									<c:choose>
							   		    <c:when test="${nFila % 2 == 0}">
							   		    	<c:set var="estilo" value="textos_formularios"/>
							   		    </c:when>
				   						<c:otherwise>
				   							<c:set var="estilo" value="textos-formcolor2"/>
				   						</c:otherwise>
									</c:choose>
									<tr>
										<td class="${estilo}">
											<nested:hidden property="idEmpresaFmt" write="true"/>
										</td>
										<td class="${estilo}">
											<nested:hidden property="razonSocial" write="true"/>
										</td>
									</tr>
								</nested:iterate>
							</nested:notEmpty>
							<nested:empty property="permisosAdmin">
		                    	<tr>
		                    		<td class="textos_formularios" colspan="2">
		                    			Usuario no tiene empresas administradas
		                    		</td>
		                    	</tr>
							</nested:empty>
		              </table>
			</td>
		</tr>
	</logic:notEmpty>
</table>
</html:form>
<script language="javaScript"> 
<!--
	function validaFormulario(f) 
	{
		var sMsje = "";
		if (!validaReq(document.getElementById("rutInicio")))
			sMsje += "* Debe ingresar el RUT del usuario de Origen\n";
		else if (!validaRut(document.getElementById("rutInicio").value))
			sMsje += "* RUT del usuario de Origen inválido\n";
		else if (!validaDV(document.getElementById("rutInicio").value))
			sMsje += "* El dígito verificador del usuario de Origen es incorrecto.\n";
		if (!validaReq(document.getElementById("rutDestino")))
			sMsje += "* Debe ingresar el RUT del usuario de Destino\n";
		else if (!validaRut(document.getElementById("rutDestino").value))
			sMsje += "* RUT del usuario de Destino inválido\n";
		else if (!validaDV(document.getElementById("rutDestino").value))
			sMsje += "* El dígito verificador del usuario de Destino es incorrecto.\n";

		if (sMsje == "" && document.getElementById("rutDestino").value == document.getElementById("rutInicio").value)
			sMsje += "* Los RUT deben ser distintos.\n";
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
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
// -->
</script>
</body>
</html:html>
