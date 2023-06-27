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
<body onLoad="poneFoco('password')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleAdministrador" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<logic:equal parameter="subSubAccion" value="usuarioEditar">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioEditar"/>
</logic:equal>
<logic:equal parameter="subSubAccion" value="usuarioCrear">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioCrear"/>
</logic:equal>
<html:hidden property="combosLugaresClick" styleId="combosLugaresClick" value="false" />
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
         <logic:equal name="DetalleAdministradorActionForm" property="mostrarDatos" value="SI">
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
    	                        			Edici&oacute;n de Administrador
    	                        		</logic:equal>
	                            		<logic:equal parameter="subSubAccion" value="usuarioCrear">
    	                        			Creaci&oacute;n de Administrador
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
	                            <td width="9%" class="barratablas"><strong>RUT:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
	                            	<logic:equal parameter="subSubAccion" value="usuarioEditar">
	                            		<nested:write property="idUsuarioFmt" />
	                            	</logic:equal>
	                            	<logic:equal parameter="subSubAccion" value="usuarioCrear">
	                            		<nested:write property="idUsuarioFmt" />
	                            	</logic:equal>
	                            </td>
	                            <td class="barratablas"><strong>Estado:</strong></td>
	                            <td class="textos_formularios">
			                    	<nested:equal property="opcHabilitado" value="1">
			                    		Habilitado
			                    	</nested:equal>
			                    	<nested:equal property="opcHabilitado" value="0">
			                    		Deshabilitado
			                    	</nested:equal>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Nombre:</strong></td>
	                            <td width="20%" class="textos_formularios">
	                            	<nested:write property="nombre" />
	                            </td>
	                            <td width="13%" class="barratablas"><strong>Apellido Paterno:</strong></td>
	                            <td width="24%" class="textos_formularios">
	                            	<nested:write property="apPat" />
	                            </td>
	                        </tr>    
							<tr align="left"> 
	                            <td width="8%" class="barratablas"><strong>Apellido Materno:</strong></td>
	                            <td width="26%" class="textos_formularios">
	                            	<nested:write property="apMat" />
	                            </td>
	                            <td class="barratablas"><strong>e-mail:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="email" />
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Tel&eacute;fono:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="fono" />
	                            </td>
	                            <td class="barratablas"><strong>Celular:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="celular" />
	                            </td>
	                         </tr>
	                         <tr align="left"> 
	                            <td class="barratablas"><strong>Fax:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="fax" />
	                            </td>
	                            <td class="barratablas"><strong>Direcci&oacute;n:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="direccion" />
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>N&ordm;:</strong></td>
	                            <td class="textos_formularios"><strong>
	                            	<nested:write property="numero" />
	                            </strong></td>
	                            <td class="barratablas"><strong>Depto:</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:write property="dpto" />
	                            </td>
                          	</tr>
                          	<tr align="left"> 
                            	<td class="barratablas"><strong>Regi&oacute;n:</strong></td>
                            	<td class="textos_formularios"> 
									<nested:write property="nombreRegion" />
                              	</td>
                            	<td class="barratablas"><strong>Ciudad:</strong></td>
                            	<td class="textos_formularios">
									<nested:write property="nombreCiudad" />
                              	</td>
                          	</tr>
                          	<tr align="left"> 
                          		<td class="barratablas"><strong>Comuna:</strong></td>
                            	<td colspan="3" class="textos_formularios">
									<nested:write property="nombreComuna" />
                              	</td>
                          	</tr>
                          	<logic:equal parameter="subSubAccion" value="usuarioEditar">
                          	<tr align="left">
	                            <td class="barratablas">Password *</td>
	                            <td class="textos_formularios">
		                            <nested:password property="password" styleId="password" maxlength="4" size="35" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
	                            </td>
	                            <td class="barratablas">Confirme Password *</td>
	                            <td class="textos_formularios">
		                            <nested:password property="confPassword" styleId="confPassword" maxlength="4" size="35" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
	                            </td>
                          	</tr>
                           </logic:equal>
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
             	<html:submit property="operacion" styleClass="btn2" value="Guardar Password" onclick="javascript:bBuscar=false;"/>
             </td>
           </tr>
           </logic:equal>
           <logic:equal name="DetalleAdministradorActionForm" property="mostrarPermiso" value="SI">
            <tr align="center"> 
                <td height="41" valign="top">
		    		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
				       	<tr> 
				          	<td width="30%"><strong>RUT Administrador:</strong></td>
				          	<td width="30%">
				          	  <c:choose>
				          	    <c:when  test="${empty DetalleAdministradorActionForm.idEncargadoFmt}">
				          	      ${idUsuarioNuevoFmt}
				          	    </c:when>
				          	    <c:otherwise>${DetalleAdministradorActionForm.idEncargadoFmt}</c:otherwise>
				          	  </c:choose>
				          	</td>
				            <td><strong>Nombre:</strong></td>
				          	<td>${DetalleAdministradorActionForm.nombre}</td>
				       	</tr>
				      	<tr> 
				         	<td><strong>Apellidos:</strong></td>
				         	<td colspan="3">${DetalleAdministradorActionForm.apellidos}</td>
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
				<logic:equal name="DetalleAdministradorActionForm" property="administrador" value="SI">
	                <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
	                    <tr> 
	                      	<td width="100%" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos de administrador sobre empresas</a></strong></td>
	                    </tr>
	                </table>
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
							<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"> <%@ include file="/html/comun/flecha.jspf"%> RUT</td>
							<td width="70%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
							<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Administrador</td>
						</tr>
						<nested:notEmpty property="consultaPermAdminEmp">
							<nested:iterate id="filaEmpresa" property="consultaPermAdminEmp" indexId="nFila">
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
										<nested:hidden property="idEmpresa"/>
										<nested:hidden property="idEmpresaFmt"/>
										<nested:write property="idEmpresaFmt"/>
									</td>
									<td class="${estilo}">
										<nested:hidden property="razonSocial"/>
										<nested:write property="razonSocial"/>
									</td>
									<td class="${estilo}">
										<div align="center">
											<nested:checkbox property="esAdmin"/>
										</div>
									</td>
								</tr>
							</nested:iterate>
						</nested:notEmpty>
						<nested:empty property="consultaPermAdminEmp">
							<tr>
								<td height="20" class="textos_formularios" colspan="5">
									El usuario no es administrador de ninguna empresa
								</td>
							</tr>
						</nested:empty>
						<tr>
							<td>
								&nbsp;
							</td>
						</tr>
						<tr>
							<td colspan="5" width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">
								Empresas en que las que el usuario no es administrador
							</td>

		                </tr>
					  	<tr> 
					    	<td align="left" valign="top" colspan="5">
						        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
									<tr> 
						           		<td><strong>RUT Empresa:</strong></td>
						           		<td><html:text property="idEmpresaAdmin" styleId="idEmpresaAdmin" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloRut(this);limpiaGrupo();" onkeyup="javascript:soloRut(this);limpiaGrupo();"/></td>
						           		<td><strong>Raz&oacute;n Social:</strong></td>
						           		<td><html:text property="nombreEmpresaAdmin" styleId="nombreEmpresaAdmin" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');limpiaGrupo();" onkeyup="javascript:soloAlfanumerico(this, '');limpiaGrupo();"/></td>
						         	</tr>
						         	<tr> 
						           		<td><strong>Codigo Grupo Convenio:</strong></td>
						           		<td><html:text property="idGrConvenio" styleId="idGrConvenio" maxlength="13" size="13" styleClass="campos" onblur="javascript:soloNumero(this, '');limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');limpiaEmpresa();"/></td>
						           		<td><strong>Nombre Grupo Convenio:</strong></td>
						           		<td><html:text property="nombreGrConvenio" styleId="nombreGrConvenio" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');limpiaEmpresa();" onkeyup="javascript:soloAlfanumerico(this, '');limpiaEmpresa();"/></td>
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
	
						<nested:notEmpty property="consultaPermAdminEmpOtros">
							<nested:iterate id="filaEmpresa" property="consultaPermAdminEmpOtros" indexId="nFila">
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
										<nested:hidden property="idEmpresa"/>
										<nested:hidden property="idEmpresaFmt"/>
										<nested:write property="idEmpresaFmt"/>
									</td>
									<td class="${estilo}">
										<nested:hidden property="razonSocial"/>
										<nested:write property="razonSocial"/>
									</td>
									<td class="${estilo}">
										<div align="center">
											<nested:checkbox property="esAdmin"/>
										</div>
									</td>
								</tr>
							</nested:iterate>
						</nested:notEmpty>
						<nested:empty property="consultaPermAdminEmpOtros">
							<tr>
								<td height="20" class="textos_formularios" colspan="5">
									No hay empresas bajo los criterios especificados en los que el usuario no sea administrador.
								</td>
							</tr>
						</nested:empty>
	              </table>
           </logic:equal>
           <tr align="center">
             <td height="41" valign="top"><br />
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
			<html:hidden property="celular" styleId="celular" />
			<html:hidden property="fax" styleId="fax" />
			<html:hidden property="direccion" styleId="direccion" />
			<html:hidden property="numero" styleId="numero" />
			<html:hidden property="dpto" styleId="dpto" />
			<html:hidden property="opcComuna" styleId="opcComuna" />
			<html:hidden property="adminSistemaAraucana" styleId="adminSistemaAraucana" />
			<html:hidden property="opcHabilitado" styleId="opcHabilitado" />
			<html:hidden property="opcRegion" styleId="opcRegion" />
			<html:hidden property="opcCiudad" styleId="opcCiudad" />
        </logic:equal>
      </table></td>
  </tr>
  <tr> 
    <td width="170">&nbsp;</td>
  </tr>
</table>
</html:form>
<script language="javaScript">
<!-- 
	var subSubAction = document.getElementById("subSubAccion").value;

	function limpiaEmpresa() 
	{
		document.getElementById("idEmpresaAdmin").value = "";
		document.getElementById("nombreEmpresaAdmin").value = "";
	}
	function limpiaGrupo() 
	{
		document.getElementById("idGrConvenio").value = "";
		document.getElementById("nombreGrConvenio").value = "";
	}

	var bBuscar = false;

	function validaCajasBusqueda(f) 
	{
		var sMsje = "";
		if(!validaReq(document.getElementById("idGrConvenio")) &&  !validaReq(document.getElementById("nombreGrConvenio")) && !validaReq(document.getElementById("idEmpresaAdmin")) && !validaReq(document.getElementById("nombreEmpresaAdmin"))){
			sMsje = "* Debe ingresar datos de Empresa o Grupo de Convenio para realizar la busqueda";
		}
		if (validaReq(document.getElementById("idEmpresaAdmin")) || validaReq(document.getElementById("nombreEmpresaAdmin"))){
			if(validaReq(document.getElementById("idEmpresaAdmin"))){
				if(!validaRut(document.getElementById("idEmpresaAdmin").value)){
					sMsje = "* El formato del rut de la empresa es incorrecto";
				}else{
					if(!validaDV(document.getElementById("idEmpresaAdmin").value)){
						sMsje = "* Digito verificador del rut de la empresa incorrecto";
					}
				}
			}else{
				if(!validaReq(document.getElementById("nombreEmpresaAdmin"))){
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

	function validaFormulario(f) 
	{
		if (bBuscar)
			return validaCajasBusqueda(f);

		var sMsje = "";
		var validar = true;
		
		if(document.getElementById("password") && !validaReq(document.getElementById("password")))
				sMsje += "* Debe ingresar la password\n";
		if(document.getElementById("confPassword") && !validaReq(document.getElementById("confPassword")))
				sMsje += "* Debe ingresar la confirmacion de la password\n";			
		if(document.getElementById("confPassword") && document.getElementById("password").value != document.getElementById("confPassword").value)
				sMsje += "* Verifique que las password sean iguales";
		
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}
// End --> 
</script>
</body>
</html:html>
