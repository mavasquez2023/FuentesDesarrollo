<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<html:form action="/ListarEmpresas" styleId="formulario" onsubmit="return onFormSubmit(this);">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaLista" />
<html:hidden property="operacion" styleId="operacion" value="" />

<bean:define id="procesoOculto" ><%=request.getAttribute("procesoOculto")%></bean:define>
<bean:define id="convenioOculto"><%=request.getAttribute("convenioOculto")%></bean:define>
<c:set var="FLG_Busqueda" scope="page"><%=request.getAttribute("FLG_Busqueda")%></c:set>


<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr valign="bottom"> 
    	<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
			<strong>B&uacute;squeda de Empresa</strong>
		</td>
	</tr>
	<tr>
		<td>
			<table class="textos-formularios3">
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td>RUT Empresa:</td>
				    <td><html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="12" size="25" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
				    <td>Raz&oacute;n Social: </td>
				    <td><html:text property="razonSocial" styleId="razonSocial" styleClass="campos"/></td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
			    	<td>Grupo de Convenios:</td>
				    <td>
						<html:select property="opcGrupoConvenio" styleClass="campos">
							<option value="0">...</option>
							<html:optionsCollection property="gruposConvenio"/>
						</html:select>
					</td>
					<td>Tipo Proceso: </td>
					<td>
				    	<html:select property="tipoProceso" styleId="tipoProceso" size="1" styleClass="campos">
			        		<option value="0">...</option>
				        	<logic:iterate id="tipoNomina" name="tiposDeNominas">
				        		<bean:define id="nomina" name="tipoNomina" type="cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO"/>
			        			<option value="<bean:write name='nomina' property='idTipoNomina' />">
			        				<bean:write name="nomina" property="descripcion"/>
			        			</option>
			        		</logic:iterate>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
			    	<td colspan="2" align="right">
			    		<html:submit property="operacion" styleClass="btn-destacado" value="${buscar}"/>
			    	</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
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
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr valign="bottom"> 
              		<td width="30%" height="30" bgcolor="#FFFFFF" class="titulo">
              			<strong>Lista de Empresas</strong>
              		</td>
              		<td width="35%" align="right" bgcolor="#FFFFFF">
						<c:set var="url"><c:url value='/ListarEmpresas.do'/>?imprimir=&accion=admin&subAccion=imprimir</c:set>
              			<html:button property="operacion" styleClass="btn_grande" value="${imprimir}" onclick="javascript:abrirDocImpresion('${url}');" />
						<nested:equal property="esAdminEmpresa" value="true">
						  &nbsp;&nbsp;&nbsp;<html:button property="operacion" styleClass="btn_grande" value="${crearEmpresa}" onclick="javascript:creaEmpresa(this);"/>
						</nested:equal>
	              	</td>
				</tr>
	         </table>
        </td>
	</tr>
	<tr align="center"> 
		<td height="33" valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" bgcolor="#FFFFFF">
						<table width="590" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" class="tabla-datos">
							<tr class="subtitulos_tablas">
								<td align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT</td>
                                <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
                               		Raz&oacute;n Social
                               	</td>
                                <td align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
                                	Estado
                                </td>
                                <td colspan="4" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">
                                	<div align="center">
                                		Acci&oacute;n
                                	</div>
                                </td>
							</tr>
							<nested:notEmpty property="consulta">
								<nested:iterate id="filaConsulta" property="consulta" indexId="nFila">
									<c:choose>
							   		    <c:when test="${nFila % 2 == 0}">
							   		    	<c:set var="estilo" value="textos_formularios"/>
							   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
							   		    </c:when>
				   						<c:otherwise>
				   							<c:set var="estilo" value="textos-formcolor2"/>
				   							<c:set var="estilo2" value="tablaOscuroBordes"/>
				   						</c:otherwise>
									</c:choose>
		                            <tr>                 
		                                <td width="15%" height="20" align="left" valign="middle" nowrap="nowrap" class="${estilo}">
	                                		<nested:write property="idEmpresaFmt" />
		                                </td>
		                                <td width="49%" class="${estilo}">
		                                	<nested:write property="razonSocial" />
		                                </td>
		                                <td width="16%" nowrap="nowrap" class="${estilo}">
		                                	<nested:notEqual property="habilitada" value="0">
		                                		Habilitada
		                                	</nested:notEqual>
		                                	<nested:equal property="habilitada" value="0">
		                                		Deshabilitada
		                                	</nested:equal>
		                                </td>
		                                <td align="center" width="5%" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
			                                	<a href="<c:url value="/DetalleEmpresa.do?accion=admin&subAccion=empresas&subSubAccion=empresaDetalle&rutEmpresa=${filaConsulta.idEmpresa}" />" title="Ver detalle" class="links">
			                                		<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0" />
			                                	</a>
		                                	</div>
										</td>
		                                <td align="center" width="5%" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
			                                	<a href="<c:url value='/SucursalesLista.do?accion=admin&subAccion=empresas&subSubAccion=sucursalesLista&rutEmpresa=${filaConsulta.idEmpresa}'/>">
			                                		<img alt="Listar sucursales" title="Listar sucursales" src="<c:url value="/img/btnListaSucursal.gif" />" width="14" height="13" border="0" />
			                                	</a>
			                                </div>
										</td>
		                                <td align="center" width="5%" align="center" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
			                                	<a href="<c:url value="/ListarConvenios.do?accion=admin&subAccion=empresas&subSubAccion=conveniosLista&rutEmpresa=${filaConsulta.idEmpresa}" />">
			                                		<img alt="Listar convenios" title="Listar convenios" src="<c:url value="/img/btnListaConvenio.gif" />" width="14" height="13" border="0" />
			                                	</a>
			                                </div>
										</td>
		                                <td align="center" width="5%" align="center" nowrap="nowrap" class="${estilo}">
		                                	<div align="center">
												<nested:equal property="editable" value="true">
				                                	<a href="<c:url value="/DetalleEmpresa.do?accion=admin&subAccion=empresas&subSubAccion=empresaEditar&rutEmpresa=${filaConsulta.idEmpresa}" />">
				                                		<img src="<c:url value="/img/ico_hojap.gif" />" alt="Modificar Empresa" title="Modificar Empresa" width="14" height="13" border="0"/>
				                                	</a>
				                                </nested:equal>
												<nested:notEqual property="editable" value="true">
			                                		<img src="<c:url value="/img/ico_hojap_no.gif" />" width="14" height="13" border="0"/>
				                                </nested:notEqual>
											</div>				                                
		                                </td>
		                            </tr>
	                            </nested:iterate>
							</nested:notEmpty>
							<nested:empty property="consulta">
								<tr>
									<td align="left" nowrap="nowrap" class="textos_formularios" colspan="6">
										<c:choose>
		             						<c:when test="${FLG_Busqueda == '0'}">No hay empresas administradas por el usuario.</c:when>
											<c:otherwise>No existen resultados para el criterio de b&uacute;squeda ingresado.</c:otherwise>
		             					</c:choose>
									</td>
								</tr>
							</nested:empty>
						</table>
					</td>
				</tr>
				<bean:size id="nPags" name="ListarEmpresasActionForm" property="numeroFilas"/>
				<c:if test="${nPags > 1}">
					<tr> 
						<td align="center" valign="middle" class="numeracion">
							<logic:iterate id="paginacion" name="ListarEmpresasActionForm" property="numeroFilas">
								${paginacion}
							</logic:iterate>
						</td>
					</tr>
				</c:if>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript">
<!--
	function validaFormulario(f)
	{
		var sMsje = "";
		if (validaReq(document.getElementById("rutEmpresa")) &&
			!validaRut(document.getElementById("rutEmpresa").value))
			sMsje += "* Formato de rut inválido para la empresa a buscar\n";
		if (validaReq(document.getElementById("razonSocial")) &&
			!validaChrs(document.getElementById("razonSocial").value))
			sMsje += "* Caracteres inválidos en la razón social de la empresa a buscar\n";
		else if (document.getElementById("razonSocial").value.length > 0 &&
				 document.getElementById("razonSocial").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en la razón social de la empresa a buscar\n";

		if (sMsje == "") {
			if (validaReq(document.getElementById("rutEmpresa")) &&
				!validaDV(document.getElementById("rutEmpresa").value))
				sMsje += "* Dígito verificador inválido para el rut la empresa a buscar\n";
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}

		return true;
	}


	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		formu.action = "ListarEmpresas.do?accion=admin&subAccion=empresas&subSubAccion=empresaLista&paginaNumero="+paginacion;
		formu.submit();
	}

	function creaEmpresa(obj) 
	{
		document.getElementById("operacion").value = obj.value;
		document.getElementById("formulario").submit();
	}
	
	function seleccionCombos() {
		var comboProceso  = document.getElementById('tipoProceso');
		var comboConvenio = document.getElementById('opcGrupoConvenio');
		var proceso       = '${procesoOculto}';
		var convenio      = '${convenioOculto}';
		var i = 0;

		if (proceso != 'null') {
			for (i = 0; i < comboProceso.length; i++) {
				if (comboProceso[i].value   == proceso) {
         			comboProceso[i].selected = true;
         			break;
         		}
			}
		}

		if (convenio != 'null') {
			for (i = 0; i < comboConvenio.length; i++) {
				if (comboConvenio[i].value   == convenio){
         			comboConvenio[i].selected = true;
         			break;
         		}
			}
		}
	}

	seleccionCombos();
// -->
</script>
