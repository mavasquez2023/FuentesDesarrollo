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
<body onLoad="foco();">
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
												<c:set var="opcioneditcrea" value="e"/>
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
	                            <td width="12%" class="barratablas"><strong>RUT *</strong></td>
	                            <td width="38%" class="textos_formularios">
	                            	<nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
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
	                            <td width="8%" class="barratablas"><strong>Apellido Materno *</strong></td>
	                            <td width="26%" class="textos_formularios">
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
	                            	<nested:textarea property="direccion" styleId="direccion" onblur="javascript:cuentaTxtArea(this, 80);soloDireccion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);soloDireccion(this);" rows="2" cols="25" styleClass="campos"/>
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
                            	<td class="barratablas">
                            		<strong>Admin. Sistema?</strong>
                            	</td>
                            	<td class="textos_formularios" valign="middle">
									<nested:checkbox property="adminSistemaAraucana" styleId="adminSistemaAraucana" styleClass="campos"/>
                              	</td>
                          	</tr>
                          	<tr align="left"> 
                          		<td class="barratablas"><strong>Tipo Administrador*</strong></td>
                            	<td class="textos_formularios">
		                            	Empresa <nested:checkbox property="tipoAdminEmpresa" styleId="tipoAdminEmpresa" styleClass="campos"/>
		                            	Independiente <nested:checkbox property="tipoAdminIndependiente" styleId="tipoAdminIndependiente" styleClass="campos"/>
                              	</td>
								<td class="barratablas">
                            		<strong>Admin. CCAF?</strong>
                            	</td>
                            	<td class="textos_formularios" valign="middle">
									<nested:checkbox property="adminCCAF" styleId="adminCCAF" style="float:left; margin:0 6px 0 0;" styleClass="campos" onclick="showMe('spanCajas', this);"/>
									<span id="spanCajas" style="display:none; float:left; margin:0 6px 0 0;">
										<html:select property="opcCCAF" styleId="opcCCAF" styleClass="campos">
											<html:option value="-1">Todas</html:option>
											<html:optionsCollection property="cajas" />
										</html:select>
									</span>
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
				<c:set var="opcioneditcrea" value="e2"/>
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
											<nested:checkbox property="esAdmin" disabled="true"/>
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
							<td>&nbsp;

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
						           		<td><html:text property="idEmpresaAdmin" styleId="idEmpresaAdmin" maxlength="13" size="17" styleClass="campos" onblur="javascript:soloRut(this);limpiaGrupo();" onkeyup="javascript:soloRut(this);limpiaGrupo();"/></td>
						           		<td><strong>Raz&oacute;n Social:</strong></td>
						           		<td><html:text property="nombreEmpresaAdmin" styleId="nombreEmpresaAdmin" maxlength="50" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');limpiaGrupo();" onkeyup="javascript:soloAlfanumerico(this, '');limpiaGrupo();"/></td>
						         	</tr>
						         	<tr>
						           		<td><strong>Codigo Grupo Convenio:</strong></td>
						           		<td><html:text property="idGrConvenio" styleId="idGrConvenio" maxlength="9" size="17" styleClass="campos" onblur="javascript:soloNumero(this, '');limpiaEmpresa();" onkeyup="javascript:soloNumero(this, '');limpiaEmpresa();"/></td>
						           		<td><strong>Nombre Grupo Convenio:</strong></td>
						           		<td><html:text property="nombreGrConvenio" styleId="nombreGrConvenio" maxlength="30" size="30" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');limpiaEmpresa();" onkeyup="javascript:soloAlfanumerico(this, '');limpiaEmpresa();"/></td>
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
			<html:hidden property="codigoFax" styleId="codigoFax" />
			<html:hidden property="codigoFono" styleId="codigoFono" />
			
			<html:hidden property="tipoAdminEmpresa" styleId="tipoAdminEmpresa" />
			<html:hidden property="tipoAdminIndependiente" styleId="tipoAdminIndependiente" />

			<html:hidden property="adminCCAF" styleId="adminCCAF" />
			<html:hidden property="opcCCAF"   styleId="opcCCAF" />
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
		document.getElementById("idEmpresaAdmin").value = "";
		document.getElementById("nombreEmpresaAdmin").value = "";
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
		if(!validaReq(document.getElementById("idGrConvenio")) &&  !validaReq(document.getElementById("nombreGrConvenio")) && !validaReq(document.getElementById("idEmpresaAdmin")) && !validaReq(document.getElementById("nombreEmpresaAdmin")))
			sMsje = "* Debe ingresar datos de Empresa o Grupo de Convenio para realizar la busqueda";
		if (validaReq(document.getElementById("idEmpresaAdmin")) || validaReq(document.getElementById("nombreEmpresaAdmin")))
		{
			if(validaReq(document.getElementById("idEmpresaAdmin")))
			{
				if(!validaRut(document.getElementById("idEmpresaAdmin").value))
				{
					sMsje = "* El formato del rut de la empresa es incorrecto";
				}else
				{
					if(!validaDV(document.getElementById("idEmpresaAdmin").value)){
						sMsje = "* Digito verificador del rut de la empresa incorrecto";
					}
				}
			}else
			{
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
		if(!validaReq(document.getElementById("idUsuarioFmt")))
			sMsje += "* Debe ingresar el rut\n";
		else if (!validaRut(document.getElementById("idUsuarioFmt").value))
			sMsje += "* Formato de rut inválido en el RUT del administrador.\n";
		else if(!validaDV(document.getElementById("idUsuarioFmt").value))
					sMsje +="* Verifique el Digito del RUT\n";

		if (!validaReq(document.getElementById("nombre")))
			sMsje += "* Debe ingresar el nombre de administrador\n";
		else if (!validaNombres(document.getElementById("nombre")))
			sMsje += "* Caracteres inválidos en el nombre del administrador.\n";
		if (!validaReq(document.getElementById("apPat")))
			sMsje += "* Debe ingresar el apellido paterno del administrador\n";
		else if (!validaNombres(document.getElementById("apPat")))
			sMsje += "* Caracteres inválidos en el apellido paterno del administrador.\n";
		if (!validaReq(document.getElementById("apMat")))
			sMsje += "* Debe ingresar el apellido materno del administrador\n";
		else if (!validaNombres(document.getElementById("apMat")))
			sMsje += "* Caracteres inválidos en apellido materno del administrador.\n";
		if (!validaReq(document.getElementById("email")))
			sMsje += "* Debe ingresar el email del administrador\n";
		else if (!validaMail(document.getElementById("email").value))
			sMsje += "* Formato de email del administrador inválido.\n";


		if (document.getElementById("celular").value != "" && (!validaUInt(document.getElementById("celular").value) || document.getElementById("celular").value.length < 7))
			sMsje += "* Número invalido para el celular del administrador.\n";
		if (!validaReq(document.getElementById("fono")))
			sMsje += "* Debe ingresar el teléfono del administrador\n";
		else if (document.getElementById("fono").value.length < 6)
			sMsje += "* Número de teléfono del administrador inválido\n";
		if (document.getElementById("codigoFono").value.length < 1)
			sMsje += "* Debe ingresar un código de area para el telefono\n";

		if (!document.getElementById("fax").value == "" || !document.getElementById("codigoFax").value=="")
		{
			if (document.getElementById("codigoFax").value.length < 1)
				sMsje += "* Debe ingresar un código de area válido para el fax.\n";

		 	if (document.getElementById("fax").value.length < 6)
				sMsje += "* No es un número válido el fax del administrador.\n";
		}

		if (!validaReq(document.getElementById("direccion")))
			sMsje += "* Debe ingresar la dirección del administrador\n";
		else if (!validaDireccion(document.getElementById("direccion")))
			sMsje += "* Caracteres inválidos en la dirección del administrador.\n";

		if (!validaReq(document.getElementById("numero")))
			sMsje += "* Debe ingresar el número de la dirección del administrador\n";
		else if (!validaDireccion(document.getElementById("numero")))
			sMsje += "* Caracteres inválidos en el número del administrador.\n";
		if (document.getElementById("dpto").value != "" && !validaDireccion(document.getElementById("dpto")))
			sMsje += "* Caracteres inválidos en el departamento del administrador.\n";

		if (document.getElementById("opcRegion").value == "-1")
			sMsje += "* Debe seleccionar la región del administrador\n";
		if (document.getElementById("opcCiudad").value == "-1")
			sMsje += "* Debe seleccionar la ciudad del administrador\n";
		if (document.getElementById("opcComuna").value == "-1")
			sMsje += "* Debe seleccionar la comuna del administrador\n";

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
		if (document.getElementById("adminSistemaAraucana").value != 'true' && document.getElementById("opcHabilitado").value == '1' && '${DetalleAdministradorActionForm.mostrarPermiso}' == 'SI')
		{
			var inputs = document.getElementsByTagName("input");
		    for (var i = 0; i < inputs.length; i++)
		    {
		    	if (inputs[i].type == "checkbox" && inputs[i].checked == true)
					return true;
			}
			/*alert("Para este administrador, debe asignarle al menos una Empresa a Administrar.\n\n");*/
			return true;
		}
		return true;
	}

	function foco()
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

	function showMe (it, box) {
		var vis = (box.checked) ? "block" : "none";
		document.getElementById(it).style.display = vis;
	}

	<logic:equal name="DetalleAdministradorActionForm" property="mostrarDatos" value="SI">
		showMe('spanCajas', document.getElementById('adminCCAF'));
	</logic:equal>
// End -->
</script>
</body>
</html:html>