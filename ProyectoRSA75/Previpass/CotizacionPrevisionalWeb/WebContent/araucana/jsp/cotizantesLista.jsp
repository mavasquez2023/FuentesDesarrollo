<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/ListarCotizantes" styleId="formuConsulta">
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="trabajadores" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="nominaEditar" />
<html:hidden styleId="numTrabTotal" property="numTrabTotal"/>
<c:set var="estilo" value="textos-formcolor2"/>
<c:set var="count" value="0"/>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	  	<td width="100%" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	   	
	    <table width="100%" border="0" cellpadding="0" cellspacing="1">
	    	<tr valign="bottom"> 
	        	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>B&uacute;squeda de N&oacute;minas</strong></td>
	        </tr>
	    </table>
		
	  	<table class="textos-formularios3">
	       	<tr>
	       		<td colspan="4">&nbsp;</td>
	       	</tr>
	       	<tr> 
	          	<td width="15%"><strong>Rut:</strong></td>
	          	<td width="20%">
					<html:select property="rutEmpresa" styleClass="campos" styleId="comboEmpresas" onchange="javascript:cargaConvenios('${CotizantesListarActionForm.convenio}');">
						<html:optionsCollection property="empresas"/>
					</html:select>
				</td>
	            <td><strong>Raz&oacute;n Social:</strong></td>
	          	<td>
	          		<div id="nombreEmp">${CotizantesListarActionForm.razonSocial}</div>
					<logic:iterate id="emp" property="objEmpresas" name="CotizantesListarActionForm">
						<input type="hidden" value="${emp.razonSocial}" id="nomEmp#${emp.idEmpresa}" />
						<logic:iterate id="conv" property="convenios" name="emp" indexId="countConv">
							<input type="hidden" value="${conv.idConvenio}" id="idConvEmp#${emp.idEmpresa}#${countConv}" />
							<input type="hidden" value="${conv.descripcion}" id="descConvEmp#${emp.idEmpresa}#${countConv}" />
						</logic:iterate>
					</logic:iterate>
	          	</td>
	       	</tr>
	      	<tr> 
	         	<td><strong>Convenio:</strong></td>
	         	<td>
					<html:select property="convenio" styleClass="campos" styleId="comboConvenios" />
				</td>
	         	<td><strong>Tipo de N&oacute;mina:</strong></td>
	         	<td>
					<html:select property="tipoProceso" styleClass="campos">
						<html:optionsCollection property="tiposProcesos" label="descripcion" value="idTipoNomina" />
					</html:select>
	           	</td>
	      	</tr>
	      	<tr>
	      		<td colspan="3">&nbsp;</td>
	      		<td align="center"><html:submit property="operacion" value="${filtro}" styleClass="btn-destacado"/></td>
	      	</tr>
	      	<tr> 
	       		<td colspan="4">&nbsp;</td>
	     	</tr>
	    </table>
	    <br />
		
		<html:errors/>
	    <html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
		<br>
	   	
	   	<logic:present name="CotizantesListarActionForm" property="trabajadores">
	   	
	    <table width="100%" border="0" cellpadding="0" cellspacing="1">
	    	<tr valign="bottom"> 
	        	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>B&uacute;squeda de Trabajadores por N&oacute;mina</strong></td>
	        </tr>
	    </table>
		
	    <table class="textos-formularios3">
	    	<tr>
	       		<td colspan="5">&nbsp;</td>
	       	</tr>
			<tr> 
	        	<td><strong>Rut Trabajador:</strong></td>
	        	<td><html:text style="text-align: left;" property="rutTrab" styleId="rutTrab" styleClass="campos" maxlength="12" size="25" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
	        	<td rowspan="2"><input type="submit" class="btn-destacado" name="operacion" value="${buscar}" onclick="return validaFormulario();" /></td>
	      	</tr>
	      	<c:choose>
	      	<c:when test="${CotizantesListarActionForm.tipoProceso=='A'}">
	      	<tr> 
	        	<td><strong>Periodo:</strong></td>
	        	<td><html:text style="text-align: left;" property="periodo" styleId="periodo" styleClass="campos" maxlength="6" size="25" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/></td>
	      	</tr>
	      	</c:when>
	      	<c:otherwise>
	      		<html:hidden property="periodo" styleId="periodo"/>
	      	</c:otherwise>
	      	</c:choose>
		</table>
	    <br />
	    <table width="100%" border="0" cellpadding="0" cellspacing="1">
	    	<tr valign="bottom"> 
	        	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Edici&oacute;n N&oacute;mina</strong></td>
	        	<c:choose>
					<c:when test="${CotizantesListarActionForm.editNomina == 0}">
						<td>&nbsp;</td>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${CotizantesListarActionForm.tipoProceso == 'R'}">
					        	<td align="right" bgcolor="#FFFFFF"><input type="submit" class="btn_xgrande" name="operacion" value="${nuevoTrabajadorR}" /></td>
					        	<td align="right" bgcolor="#FFFFFF"><input type="submit" class="btn_xgrande"    name="operacion" value="${nuevoTrabajadorF}" /></td>
							</c:when>
							<c:otherwise>
		    					<td align="right" bgcolor="#FFFFFF"><input type="submit" class="btn_grande" name="operacion" value="${nuevoTrabajador}" /></td>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
	        </tr>
	    </table>
	    </logic:present>
		</td>
	</tr>
	<logic:present name="CotizantesListarActionForm" property="trabajadores">
	<tr align="center"> 
	  	<td valign="top">
		<table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	    	<tr class="subtitulos_tablas" align="center" valign="middle">
	        	<td width="86" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Rut</td>
	            <td width="106" bordercolor="#FFFFFF" class="barra_tablas">Nombres</td>
	            <td width="106" bordercolor="#FFFFFF" class="barra_tablas">Apellidos</td>
	            <c:if test="${CotizantesListarActionForm.tipoProceso != 'A'}">
	            	<td width="106" bordercolor="#FFFFFF" class="barra_tablas">Sucursal</td>
	            </c:if>
	            <c:if test="${CotizantesListarActionForm.tipoProceso == 'A'}">
	            	<td width="76" bordercolor="#FFFFFF" class="barra_tablas">Sucursal</td>
	            	<td width="30" bordercolor="#FFFFFF" class="barra_tablas">Periodo</td>
	            </c:if>
	            <td colspan="4" bordercolor="#FFFFFF" class="barra_tablas" style="text-align:center">Acci&oacute;n</td>
			</tr>
	   		<logic:present name="CotizantesListarActionForm" property="trabPendientes">
				<logic:iterate id="cotizante" name="CotizantesListarActionForm" property="trabPendientes">
		          	<c:choose>
		   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
		   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
		   		  	</c:choose>
					<c:set var="count" value="${count + 1}"/>
			        <tr valign="top" align="center">
			        	<td height="20" style="text-align:right" nowrap="nowrap" class="${estilo}">${cotizante.rut}&nbsp;</td>
				       	<td align="left" class="${estilo}">${cotizante.nombre}&nbsp;</td>
				       	<td align="left" class="${estilo}">${cotizante.apellidos}&nbsp;</td>
				       	<td class="${estilo}">${cotizante.sucursal}&nbsp;</td>
				       	<c:if test="${CotizantesListarActionForm.tipoProceso == 'A'}">
				       		<td class="${estilo}">${cotizante.periodo}&nbsp;</td>
				       	</c:if>
				       	<td class="${estilo}" width="12">
				       		<c:choose>
				       			<c:when test="${cotizante.flgVoluntario == true}"><span class="mensaje_aviso" title="Trabajador Voluntario">V</span></c:when>
				       			<c:otherwise>&nbsp;</c:otherwise>
				       		</c:choose>
				       	</td>
				       	<td class="${estilo}" valign="middle">
				       		<div class="textos_resaltados" align="center" title="Trabajador con errores en su Cotizaci&oacute;n">
				       			<c:choose>
					   		    	<c:when test="${cotizante.nivelError == 0}"><div class="mensaje_error">Error</div></c:when>
					   		    	<c:when test="${cotizante.nivelError == 1}"><div class="mensaje_aviso">Aviso</div></c:when>
					   				<c:otherwise>&nbsp;</c:otherwise>
					   		  	</c:choose>
				       		</div>
				       	</td>
				       	<td class="${estilo}" valign="middle">
				   		    <div align="center">
					       	<c:choose>
				   		    	<c:when test="${CotizantesListarActionForm.editNomina == 0}">
			   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizPend=${cotizante.idCotizPendiente}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizante.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorVer">
			   		    				<img src="<c:url value="/img/lupa.gif" />" alt="Ver" width="14" height="13" border="0" title="Ver" />
			   		    			</html:link>
				   		    	</c:when>
				   		    	<c:otherwise>
			   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizPend=${cotizante.idCotizPendiente}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizante.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorEditar">
			   		    				<img src="<c:url value="/img/ico_hojap.gif" />" alt="Editar" width="14" height="13" border="0" title="Editar" />
			   		    			</html:link>
				   		    	</c:otherwise>
				   		  	</c:choose>
				   		  	</div>
				   		</td>
				       	<td class="${estilo}" valign="middle">
				   		    <div align="center">
				   		    <c:if test="${CotizantesListarActionForm.editNomina == 1}">
				   		    	<html:link href="#" onclick="javascript:eliminar(this, 'rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizPend=${cotizante.idCotizPendiente}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizanteAviso.periodo}&operacion=del&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar','${cotizante.rut}','${cotizante.nombre} ${cotizante.apellidos}');"><img src="<c:url value="/img/icono_basurero.gif" />" alt="Eliminar" width="16" height="16" border="0" title="Eliminar" /></html:link>
				   		    </c:if>&nbsp;
				   		  	</div>
						</td>
					</tr>
				</logic:iterate>
			</logic:present>
			<logic:iterate id="cotizanteAviso" name="CotizantesListarActionForm" property="trabAvisos">
	         	<c:choose>
	   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
	   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
	   		  	</c:choose>
				<c:set var="count" value="${count + 1}"/>
		        <tr valign="top" align="center">
		        	<td height="20" style="text-align:right" nowrap="nowrap" class="${estilo}">${cotizanteAviso.rut}&nbsp;</td>
			       	<td align="left" class="${estilo}">${cotizanteAviso.nombre}&nbsp;</td>
			       	<td align="left" class="${estilo}">${cotizanteAviso.apellidos}&nbsp;</td>
			       	<td class="${estilo}">${cotizanteAviso.sucursal}&nbsp;</td>
			       	<c:if test="${CotizantesListarActionForm.tipoProceso == 'A'}">
			       		<td class="${estilo}">${cotizanteAviso.periodo}&nbsp;</td>
			       	</c:if>
			       	<td class="${estilo}" width="12">
			       		<c:choose>
			       				<c:when test="${cotizanteAviso.flgVoluntario == true}"><span class="mensaje_aviso" title="Trabajador Voluntario">V</span></c:when>
			       				<c:otherwise>&nbsp;</c:otherwise>
		       			</c:choose>
			       	</td>
			       	<td class="${estilo}" valign="middle">
				       		<div class="textos_resaltados" align="center" title="Trabajador con errores en su Cotizaci&oacute;n">
				       			<c:choose>
					   		    	<c:when test="${cotizanteAviso.nivelError == 0}"><div class="mensaje_error">Error</div></c:when>
					   		    	<c:when test="${cotizanteAviso.nivelError == 1}"><div class="mensaje_aviso">Aviso</div></c:when>
					   				<c:otherwise>&nbsp;</c:otherwise>
					   		  	</c:choose>
				       		</div>
				       	</td>
			       	<td class="${estilo}" valign="middle">
			   		    <div align="center">
				       	<c:choose>
			   		    	<c:when test="${CotizantesListarActionForm.editNomina == 0}">
		   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizanteAviso.rutEmpresa}&idConvenio=${cotizanteAviso.idConvenio}&idCotizante=${cotizanteAviso.idCotizante}&tipoProceso=${cotizanteAviso.tipoProceso}&periodo=${cotizanteAviso.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorVer">
		   		    				<img src="<c:url value="/img/lupa.gif" />" alt="Ver" width="14" height="13" border="0" title="Ver" />
		   		    			</html:link>
			   		    	</c:when>
			   		    	<c:otherwise>
		   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizanteAviso.rutEmpresa}&idConvenio=${cotizanteAviso.idConvenio}&idCotizante=${cotizanteAviso.idCotizante}&tipoProceso=${cotizanteAviso.tipoProceso}&periodo=${cotizanteAviso.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorEditar">
		   		    				<img src="<c:url value="/img/ico_hojap.gif" />" alt="Editar" width="14" height="13" border="0" title="Editar" />
		   		    			</html:link>
			   		    	</c:otherwise>
			   		  	</c:choose>
			   		  	</div>
			   		</td>
			       	<td class="${estilo}" valign="middle">
			   		    <div align="center">
			   		    <c:if test="${CotizantesListarActionForm.editNomina == 1}">
			   		    	<html:link href="#" onclick="javascript:eliminar(this, 'rutEmpresa=${cotizanteAviso.rutEmpresa}&idConvenio=${cotizanteAviso.idConvenio}&idCotizante=${cotizanteAviso.idCotizante}&tipoProceso=${cotizanteAviso.tipoProceso}&periodo=${cotizanteAviso.periodo}&operacion=del&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar','${cotizanteAviso.rut}','${cotizanteAviso.nombre} ${cotizanteAviso.apellidos}');"><img src="<c:url value="/img/icono_basurero.gif" />" alt="Eliminar" width="16" height="16" border="0" title="Eliminar" /></html:link>
			   		    </c:if>&nbsp;
			   		  	</div>
				   	</td>
				</tr>
			</logic:iterate>
			<logic:iterate id="cotizante" name="CotizantesListarActionForm" property="trabajadores">
	         	<c:choose>
	   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
	   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
	   		  	</c:choose>
				<c:set var="count" value="${count + 1}"/>
		        <tr valign="top" align="center">
		        	<td height="20" style="text-align:right" nowrap="nowrap" class="${estilo}">${cotizante.rut}&nbsp;</td>
			       	<td align="left" class="${estilo}">${cotizante.nombre}&nbsp;</td>
			       	<td align="left" class="${estilo}">${cotizante.apellidos}&nbsp;</td>
			       	<td class="${estilo}">${cotizante.sucursal}&nbsp;</td>
			       	<c:if test="${CotizantesListarActionForm.tipoProceso == 'A'}">
			       		<td class="${estilo}">${cotizante.periodo}&nbsp;</td>
			       	</c:if>
			       	<td class="${estilo}" width="12">
			       		<c:choose>
			       			<c:when test="${cotizante.flgVoluntario == true}"><span class="mensaje_aviso" title="Trabajador Voluntario">V</span></c:when>
			       			<c:otherwise>&nbsp;</c:otherwise>
			       		</c:choose>
			       	</td>
			       	<td class="${estilo}">&nbsp;</td>
			       	<td class="${estilo}" valign="middle">
			   		    <div align="center">
				       	<c:choose>
			   		    	<c:when test="${CotizantesListarActionForm.editNomina == 0}">
		   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizante=${cotizante.idCotizante}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizante.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorVer">
		   		    				<img src="<c:url value="/img/lupa.gif" />" alt="Ver" width="14" height="13" border="0" title="Ver" />
		   		    			</html:link>
			   		    	</c:when>
			   		    	<c:otherwise>
		   		    			<html:link action="/EditarCotizacion?rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizante=${cotizante.idCotizante}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizante.periodo}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorEditar">
		   		    				<img src="<c:url value="/img/ico_hojap.gif" />" alt="Editar" width="14" height="13" border="0" title="Editar" />
		   		    			</html:link>
			   		    	</c:otherwise>
			   		  	</c:choose>
			   		  	</div>
			   		</td>
			       	<td class="${estilo}" valign="middle">
			   		    <div align="center">
			   		    <c:if test="${CotizantesListarActionForm.editNomina == 1}">
			   		    	<html:link href="#" onclick="javascript:eliminar(this, 'rutEmpresa=${cotizante.rutEmpresa}&idConvenio=${cotizante.idConvenio}&idCotizante=${cotizante.idCotizante}&tipoProceso=${cotizante.tipoProceso}&periodo=${cotizante.periodo}&operacion=del&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar','${cotizante.rut}','${cotizante.nombre} ${cotizante.apellidos}');"><img src="<c:url value="/img/icono_basurero.gif" />" alt="Eliminar" width="16" height="16" border="0" title="Eliminar" /></html:link>
			   		    </c:if>&nbsp;
			   		  	</div>
				   	</td>
				</tr>
			</logic:iterate>
		</table>
	    </td>
	</tr>
	<bean:size id="nPags" name="CotizantesListarActionForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
			<tr> 
				<td align="center" valign="middle" class="numeracion">
					<logic:iterate id="paginacion" name="CotizantesListarActionForm" property="numeroFilas">
						${paginacion}
					</logic:iterate>
				</td>
			</tr>
		</c:if>
	</logic:present>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
cargaConvenios('${CotizantesListarActionForm.convenio}');

function retornaEnlace(paginacion)
{
	formu = document.getElementById("formuConsulta");
	formu.action = "ListarCotizantes.do?idConvenio=${CotizantesListarActionForm.convenio}&idEmpresa=${CotizantesListarActionForm.rutEmpresa}&tipoNomina=${CotizantesListarActionForm.tipoProceso}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar&paginaNumero=" + paginacion;
	formu.submit();
}

function validaFormulario()
{
//si va a buscar, valida rut
	if (document.getElementById("rutTrab").value == '' || !validaRut(document.getElementById("rutTrab").value))
	{
		alert("El rut ingresado para la búsqueda no es válido");
		return false;
	}
	if(document.getElementById("tipoProceso").value == 'A' && document.getElementById("periodo").value.length!=6)
	{
		alert("El período ingresado para la búsqueda no es válido");
		return false;
	}
	return true;
}
function eliminar(link, params,rut,nom)
{
	var flag = false;
	var trabajador="("+rut+" / "+ nom+")";
	if (confirm("¿Está seguro que desea eliminar al trabajador " + trabajador + "?"))
	{
	    flag = true;
	    
		// BUG_ELIMINA_NOMINA
		if (document.getElementById("numTrabTotal").value == 1) {
		    if (!confirm("Si elimina trabajador "+trabajador+", la nómina quedará vacía,\ny se eliminará automáticamente. \n\n ¿Está seguro que desea continuar?"))
			    flag = false;
		}
	}
	if (flag)
		link.href = document.forms[0].action + "?" + params;
	else
		link.href = "#";
}

function foco()
{
	if (document.getElementById('rutTrab') && document.getElementById('rutTrab') != null)
		document.getElementById('rutTrab').focus();
}

foco();
//-->
</script>