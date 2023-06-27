<%@ include file="/html/comun/taglibs.jsp" %>
<style type="text/css">

.textos_formularios_user 
{
	color: #333333;
	font: normal;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	background-color: #F5FAF9;
	border-top-width: 1px;
	border-top-style: solid;
	border-top-color: #FFFFFF;
	height: 22px;
	text-align: left;
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #A6C4BF;
}

.textos-formcolor2_user 
{
	color: #333333;
	background-color: #D1E6E7;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	font-style: normal;
	border-top: 1px solid #FFFFFF;
	border-bottom: 1px solid #6B929C;
	height: 22px;
	font-weight: normal;
	font-variant: normal;
	text-align: left;
}
</style>
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
			<html:errors />
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
        <table width="590" border="0" cellpadding="0" cellspacing="0">
         	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
          		<td width="590" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                <table border="0" cellpadding="0" cellspacing="0">
                	<tr> 
                     	<td>
                        <table width="590" border="0" cellpadding="0" cellspacing="1">
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
                        <table border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
                          	<tr align="left"> 
	                            <td width="9%" class="barra_tablas"><strong>RUT *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
	                            	<logic:equal parameter="subSubAccion" value="usuarioEditar">
	                            		<nested:text property="idUsuarioFmt" styleId="idUsuarioFmt" maxlength="13" styleClass="campos" readonly="true"/>
	                            	</logic:equal>
	                            	<logic:equal parameter="subSubAccion" value="usuarioCrear">
	                            		<nested:text property="idUsuarioFmt" styleId="idUsuarioFmt" maxlength="13" styleClass="campos" readonly="false" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
	                            	</logic:equal>
	                            </td>
	                            <td class="barra_tablas"><strong>Estado *</strong></td>
	                            <td class="textos_formularios">
			                    	<nested:select property="opcHabilitado" styleId="opcHabilitado" styleClass="campos">
			                    		<html:option value="1">Habilitado</html:option>
			                    		<html:option value="0">Deshabilitado</html:option>
			                    	</nested:select>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barra_tablas"><strong>Nombre *</strong></td>
	                            <td width="20%" class="textos_formularios">
	                            	<nested:text property="nombre" styleId="nombre" maxlength="30" size="33" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
	                            <td width="13%" class="barra_tablas"><strong>Apellido Paterno *</strong></td>
	                            <td width="24%" class="textos_formularios">
	                            	<nested:text property="apPat" styleId="apPat" maxlength="20" size="33" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
                          	 	<td width="8%" class="barra_tablas"><strong>Apellido Materno *</strong></td>
	                            <td width="26%" class="textos_formularios">
	                            	<nested:text property="apMat" styleId="apMat" maxlength="20" size="33" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
	                            </td>
	                            <td class="barra_tablas"><strong>e-mail *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="email" styleId="email" maxlength="100" size="35" styleClass="campos" onblur="javascript:soloEmail(this);" onkeyup="javascript:soloEmail(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barra_tablas"><strong>Tel&eacute;fono *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="codigoFono" styleId="codigoFono" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            	<nested:text property="fono" styleId="fono" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
	                            <td class="barra_tablas"><strong>Celular</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="celular" styleId="celular" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barra_tablas"><strong>Fax</strong></td>
	                            <td class="textos_formularios">
					               	<nested:text property="codigoFax" styleId="codigoFax" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            	<nested:text property="fax" styleId="fax" maxlength="9" size="15" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
	                            <td class="barra_tablas"><strong>Direcci&oacute;n *</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:textarea property="direccion" styleId="direccion" onblur="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" rows="2" cols="25" styleClass="campos"/>
	                            </td>
                          	</tr>
                          	<tr align="left">
	                            <td class="barra_tablas"><strong>N&ordm; *</strong></td>
	                            <td class="textos_formularios"><strong>
	                            	<nested:text property="numero" styleId="numero" maxlength="6" size="8" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
	                            </strong></td>
	                            <td class="barra_tablas"><strong>Depto</strong></td>
	                            <td class="textos_formularios">
	                            	<nested:text property="dpto" styleId="dpto" maxlength="6" size="8" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left"> 
                            	<td class="barra_tablas"><strong>Regi&oacute;n *</strong></td>

                            	<td class="textos_formularios"> 
									<html:select property="opcRegion" styleId="opcRegion" styleClass="campos" onchange="javascript:combosChange(this);">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="regiones" />
									</html:select>
                              	</td>
                            	<td class="barra_tablas"><strong>Ciudad *</strong></td>
                            	<td class="textos_formularios">
									<html:select property="opcCiudad" styleId="opcCiudad" styleClass="campos" onchange="javascript:combosChange(this);">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="ciudades" />
									</html:select>
                              	</td>
                          	</tr>
                           <tr align="left"> 
                            	<td class="barra_tablas"><strong>Comuna *</strong></td>
                            	<td class="textos_formularios">
									<html:select property="opcComuna" styleId="opcComuna" styleClass="campos">
										<html:option value="-1" >-Seleccione-</html:option>
										<html:optionsCollection property="comunas" />
									</html:select>
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
            <tr> 
                <td width="590" valign="top">
					<br />             	
           		<table width="590" border="0" cellpadding="0" cellspacing="1">
               		<tr valign="bottom"> 
                 		<td width="590" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Permisos</strong></td>
               		</tr>
             	</table>
                <table width="590" border="0" cellpadding="1" cellspacing="1" class="tabla-datos2">
                    <tr> 
                      	<td width="590" class="titulos_formularios"><strong><a name="ancla" class="titulos_formularios">Permisos sobre convenios</a></strong></td>
                    </tr>
                </table>
                <table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
			         <tr>
			           <td width="115" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Grupo Convenio</td>
			           <td width="100" align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
			           <td width="91" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
			           <td width="70" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
			           <td colspan="3" width="114" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Convenios</td>
			           <td colspan="4" width="100" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Planillas</td>
			         </tr>
			         <tr>
			           <td colspan="4" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Editor<br /><input type="checkbox" id="todosEditor" name="todosEditor" onclick="javascript:cambiaCheck('2')" value="2"></td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Lector<br /><input type="checkbox" id="todosLector" name="todosLector" onclick="javascript:cambiaCheck('1')" value="1"></td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nada<br /><input type="checkbox" id="todosNada" name="todosNada" onclick="javascript:cambiaCheck('0')" value="0"></td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Gr</td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Em</td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Cn</td>
			           <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Sc</td>
			         </tr>
				         <nested:notEmpty property="permisos">
					         <nested:iterate id="grupo" property="permisos" indexId="nFila" type="cl.araucana.cp.distribuidor.presentation.beans.GrupoConvenio">
					         	<c:choose>
									<c:when test="${nFila % 2 == 0}">
										<c:set var="estilo" value="textos_formularios_user" />
									</c:when>
									<c:otherwise>
										<c:set var="estilo" value="textos-formcolor2_user" />
									</c:otherwise>
								</c:choose>
								<tr>
					        		<bean:size id="numEmp" name="grupo" property="empresas"/>
									<td colspan="3" align="center" width="306" valign="middle" class="${estilo}">
										<c:if test="${numEmp >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-gr-${grupo.idGrupo}', 'tr-gr-img${grupo.idGrupo}');"><img id="tr-gr-img${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
										<c:if test="${numEmp < 1}">&nbsp;</c:if>
										${grupo.nombre}<nested:hidden property="idGrupo" />
									</td>
				   		    		<td colspan="4" width="184" class="${estilo}">&nbsp;</td>
				   		    		<td width="25" class="${estilo}"><div align="center"><nested:checkbox property="permLector" title="Todas las Empresas del Grupo de Convenio Autorizadas" /></div></td>
				   		    		<td width="75" colspan="3" class="${estilo}">&nbsp;</td>
								</tr>
								<tr id="tr-gr-${grupo.idGrupo}" style="display:none">
									<td colspan="11">
										<table width="590" cellpadding="0" cellspacing="0" border="0">
										<nested:iterate id="empresa" property="empresas" indexId="nFilaEmp" type="cl.araucana.cp.distribuidor.presentation.beans.Empresa">
											<bean:size id="numConv" name="empresa" property="convenios"/>
											<tr>
												<td width="115" class="${estilo}">&nbsp;</td>
									         	<td width="100" class="${estilo}">
									         		<div align="right">
														<c:if test="${numConv >= 1}"><a style="cursor:pointer" onclick="swapAll('tr-em-${empresa.idEmpresa}-${grupo.idGrupo}', 'tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}');"><img id="tr-em-img${empresa.idEmpresa}-${grupo.idGrupo}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></c:if>
														<c:if test="${numConv < 1}">&nbsp;</c:if>${empresa.rut}&nbsp;
													</div>
												</td>
									         	<td width="161" class="${estilo}">${empresa.razonSocial}<nested:hidden property="idEmpresa" /></td>
				   		    					<td width="141" class="${estilo}">&nbsp;</td>
							   		    		<td width="25" class="${estilo}"><div align="center"><nested:checkbox property="permLector" title="Todos los Convenios de la Empresa Autorizados"/></div></td>
				   		    					<td width="48" class="${estilo}">&nbsp;</td>
							   		    	</tr>
											<tr id="tr-em-${empresa.idEmpresa}-${grupo.idGrupo}" style="display:none">
												<td colspan="6" width="590">
													<table width="590" cellpadding="0" cellspacing="0" border="0">
													<nested:iterate id="perm" property="convenios" indexId="nFilaPerm" type="cl.araucana.cp.distribuidor.presentation.beans.PermEncargadoLector">
														<tr>
			   		    									<td width="306" class="${estilo}">&nbsp;</td>
															<td width="70" class="${estilo}"><div align="right">${perm.idConvenio}&nbsp;</div><nested:hidden property="idConvenio" /></td>
							   		    					<td width="38" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="2" title="Enviar nóminas y editar información."/></div></td>
							   		    					<td width="38" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="1" title="Solo visualizar información."/></div></td>
							   		    					<td width="38" class="${estilo}"><div align="center"><nested:radio property="idNivel" value="0" title="Sin acceso a cotizaciones."/></div></td>
							   		    					<td width="50" class="${estilo}">&nbsp;</td>
							   		    					<td width="25" class="${estilo}"><div align="center"><nested:checkbox property="convenioLector" title="Solo este Convenio Autorizado"/></div></td>
							   		    					<td width="25" class="${estilo}">
							   		    						<nested:hidden property="sucursalLector" styleId="idSuc-${empresa.idEmpresa}-${perm.idConvenio}" />
							   		    						<div align="center">
																	<html:link action="/PermisosRolLector" target="_blank" onclick="javascript:modalWin(this.href, this.target, 620, 500); return false;" styleClass="links">
																		<html:param name="rutEmpresa">${empresa.idEmpresa}</html:param>
																		<html:param name="rutEmpresaFmt">${empresa.rut}</html:param>
																		<html:param name="nombreEmpresa">${empresa.razonSocial}</html:param>
																		<html:param name="idLectorEmpresa">${DetalleUsuariosActionForm.idUsuario}</html:param>
																		<html:param name="idConvenio">${perm.idConvenio}</html:param>
																		<html:param name="idSucHidden">idSuc-${empresa.idEmpresa}-${perm.idConvenio}</html:param>
																		<html:param name="tipo">convenio</html:param>
																		<html:param name="acc">per_</html:param>
																		<c:choose>
																		<c:when test="${perm.sucursalLector!=''}">
																		<img alt="Ver Permisos en Sucursales" title="Ver Permisos en Sucursales" src="<c:url value="/img/btnListaSucursalAut.gif" />" width="14" height="13" border="0" />
																		</c:when>
																		<c:otherwise>
																		<img alt="Ver Permisos en Sucursales" title="Ver Permisos en Sucursales" src="<c:url value="/img/btnListaSucursal.gif" />" width="14" height="13" border="0" />
																		</c:otherwise>
																		</c:choose>
																	</html:link>
																</div>
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
								<td align="left" valign="top" nowrap="nowrap" class="textos_formularios" colspan="11">
									Encargado no tiene permisos sobre Convenios
								</td>
							</tr>
						</nested:empty>
					</table>
                </td>
              </tr>
           <tr align="center">
             <td height="41" valign="top"><br />
             	<html:submit property="operacion" styleClass="btn_grande" value="${guardar}"/>
             	<html:cancel property="operacion" styleClass="btn_grande" value="${cancelar}"/>
             </td>
           </tr>
      </table>
      </td>
  </tr>
  <tr> 
    <td width="590">&nbsp;</td>
  </tr>
</table>
</html:form>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>

<script language="javaScript"> 
<!-- 
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
	
	function validaFormulario(f) 
	{
		var sMsje = "";

		if (!validaReq(document.getElementById("idUsuarioFmt")))
			sMsje += "* Debe ingresar el identificador del encargado\n";
		if (!validaReq(document.getElementById("nombre")))
			sMsje += "* Debe ingresar el nombre del encargado\n";
		if (!validaReq(document.getElementById("apPat")))
			sMsje += "* Debe ingresar el apellido paterno del encargado\n";
		if (!validaReq(document.getElementById("apMat")))
			sMsje += "* Debe ingresar el apellido materno del encargado\n";
		if (!validaReq(document.getElementById("email")))
			sMsje += "* Debe ingresar el email del encargado\n";
		if (!validaReq(document.getElementById("direccion")))
			sMsje += "* Debe ingresar la dirección del encargado\n";
		if (!validaReq(document.getElementById("numero")))
			sMsje += "* Debe ingresar el número de la dirección del encargado\n";
		if (document.getElementById("opcComuna").value == "-1")
			sMsje += "* Debe seleccionar la comuna del encargado\n";
		
		if (sMsje == "") 
		{
			//Formato Rut
			if (!validaRut(document.getElementById("idUsuarioFmt").value))
				sMsje += "* Formato de rut inválido en el RUT del encargado.\n";
		
			//Campos de texto
			if (!validaNombres(document.getElementById("nombre").value))
				sMsje += "* Caracteres inválidos en el nombre del encargado.\n";
			if (!validaNombres(document.getElementById("apPat").value))
				sMsje += "* Caracteres inválidos en el apellido paterno del encargado.\n";
			if (!validaNombres(document.getElementById("apMat").value))
				sMsje += "* Caracteres inválidos en apellido materno del encargado.\n";
			if (validaReq(document.getElementById("direccion")) && !validaDireccion(document.getElementById("direccion").value))
				sMsje += "* Caracteres inválidos en la dirección del encargado.\n";
			if (validaReq(document.getElementById("dpto")) && !validaDescripcion(document.getElementById("dpto").value))
				sMsje += "* Caracteres inválidos en el departamento del encargado.\n";

			if (!validaReq(document.getElementById("fono")))
				sMsje += "* Debe ingresar el teléfono del encargado\n";
			else if (document.getElementById("fono").value.length < 6)
				sMsje += "* Número de teléfono del encargado inválido\n";
			if (document.getElementById("codigoFono").value.length < 1) 
				sMsje += "* Debe ingresar un código de area para el telefono del encargado.\n";

			if ((document.getElementById("codigoFax").value.length<1)&&
			(document.getElementById("fax").value.length>0))
				sMsje += "* Debe ingresar un código de area para el Fax del encargado.\n";
			
			if ((document.getElementById("codigoFax").value.length>0)&&
			(document.getElementById("fax").value.length<6))
			 	sMsje += "* No es un número válido el fax del encargado.\n";
				
			//Emails
			if (!validaMail(document.getElementById("email").value))
				sMsje += "* Formato de e-mail inválido en e-mail del encargado.\n";
				
			//Enteros
			if (validaReq(document.getElementById("celular")) && !validaNumero(document.getElementById("celular").value))
				sMsje += "* Número invalido para el celular del encargado.\n";
			else if (document.getElementById("celular").value != '' && document.getElementById("celular").value.length < 8)
				sMsje += "* El celular del encargado es inválido.\n";
				
			if (sMsje == "") 
			{
				if (!validaDV(document.getElementById("idUsuarioFmt").value))
					sMsje += "* El dígito verificador del RUT del encargado es incorrecto.\n";
			}
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		
		var inputs = document.getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) 
		{	    
	    	if ((inputs[i].type == "radio" && inputs[i].checked == true && inputs[i].value > 0) || 
		    	(inputs[i].type == "checkbox" && inputs[i].checked == true && inputs[i].value == "on"))
				return true;
		}
		alert("Para este encargado, debe asignarle al menos un permiso de Encargado Lector/Editor de Convenios.\n\n");
		return false;

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

	function cambiaCheck(valor)
	{
		var inputs = document.forms[0].getElementsByTagName("input");
		for (var i = 0; i < inputs.length; i++) 
		{
	        if ((inputs[i].type == undefined) || (inputs[i].type != "radio") || (inputs[i].value != valor))
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

	function modalWin(url, target, width, height) 
	{
		window.open(url, target, "height=" + height + ",width=" + width + ",toolbar=no,directories=no,"
			+ "status=no,linemenubar=no,resizable=no,modal=yes,scrollbars=yes");
	}

	function foco()
	{
		foco =  document.getElementById('opcionfoco').value;
		if(foco == 'c')
			 document.getElementById('idUsuarioFmt').focus();
		else
			 document.getElementById('nombre').focus();
	}
	foco();
// End --> 
</script>