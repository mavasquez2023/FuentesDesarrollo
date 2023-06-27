<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/ListaCodigosFicha" styleId="formulario">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="codigosFicha" />
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
		<td valign="top">
						<table class="textos-formularios3">
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td width="30%">Grupo de Convenios:</td>
								<td>
									<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:submit();">
										<nested:optionsCollection property="gruposConvenio" />
									</nested:select>
								</td>
							</tr>
							<tr> 
								<td colspan="2">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de C&oacute;digos de Mapeo</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<c:set var="grupoConvenio"><nested:write property="opcGrupoConvenio"/></c:set>
						<c:set var="url"><c:url value='/ListaCodigosFicha.do'/>?grupoConvenio=${grupoConvenio}&imprimir=&accion=mapeo&subAccion=imprimir</c:set>
						<html:button property="operacion" styleClass="btn_grande" value="${imprimir}" onclick="javascript:abrirDocImpresion('${url}');"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table cellpadding="0" cellspacing="0" class="tabla-datos">
				<tr>
					<td width="20" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					<td width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Concepto</td>
					<td width="320" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Entidad</td>
					<td width="100" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">C&oacute;digo</td>
					<td width="70" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('afp', 'img1');">
								<img id="img1" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>&nbsp;AFP</strong>
					</td>
					<td width="70" class="textos_formularios" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<html:link title="Editar" action="/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=AFP"><img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="afp" style="display:none">
					<td colspan="5">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listaAFP" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
   									<c:otherwise>
   										<c:set var="estilo" value="textos_formularios"/>
   									</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('isapre', 'img2');">
								<img id="img2" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos-formcolor2" align="left">
						<strong>ISAPRE</strong>
					</td>
					<td width="70" class="textos-formcolor2" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<html:link title="Editar" action="/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=ISAPRE"><img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="isapre" style="display:none">
					<td colspan="5">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listaISAPRE" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos_formularios"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos-formcolor2"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
		                  	</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('apv', 'img3');">
								<img id="img3" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>APV</strong>
					</td>
					<td width="70" class="textos_formularios" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<html:link title="Editar" action="/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=APV"><img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="apv" style="display:none">
					<td colspan="5" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listaAPV" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos_formularios"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('mvto', 'img4');">
								<img id="img4" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos-formcolor2" align="left">
						<strong>Movimiento Personal</strong>
					</td>
					<td width="70" class="textos-formcolor2" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<html:link title="Editar" action="/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=MVTO"><img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="mvto" style="display:none">
					<td colspan="5" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listMvtoPer" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos_formularios"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos-formcolor2"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('mvtoAF', 'img40');">
								<img id="img40" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>Movimiento Personal Afiliaci&oacute;n Voluntaria</strong>
					</td>
					<td width="70" class="textos_formularios" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<a href="<c:url value='/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=MVTOAF' />">
									<img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
								</a>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="mvtoAF" style="display:none">
					<td colspan="5" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listMvtoPerAF" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos_formularios"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('genero', 'img5');">
								<img id="img5" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos-formcolor2" align="left">
						<strong>G&eacute;nero</strong>
					</td>
					<td width="70" class="textos-formcolor2" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<a href="<c:url value='/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=GENERO' />">
									<img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
								</a>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="genero" style="display:none">
					<td colspan="5" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listaGenero" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos_formularios"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos-formcolor2"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:;" onclick="swapAll('tramo', 'img6');">
								<img id="img6" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							</a>
						</div>
					</td>
					<td colspan="3" width="500" nowrap="nowrap" class="textos_formularios" align="left">
						<strong>Tramo Asignaci&oacute;n Familiar</strong>
					</td>
					<td width="70" class="textos_formularios" align="center">
						<div align="center">
							<nested:equal property="puedeEditar" value="true">
								<a href="<c:url value='/EdicionCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&subSubAccion=codigosEditar&grupoConvenio=${grupoConvenio}&tipoEdicion=TRAMO' />">
									<img src="<c:url value="/img/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
								</a>
							</nested:equal>&nbsp;
						</div>
					</td>
				</tr>
				<tr id="tramo" style="display:none">
					<td colspan="5" align="right">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:iterate id="fila" property="listaTramo" indexId="nFila">
								<c:choose>
				   		    		<c:when test="${nFila % 2 == 0}">
				   		    			<c:set var="estilo" value="textos-formcolor2"/>
				   		    		</c:when>
	   								<c:otherwise>
	   									<c:set var="estilo" value="textos_formularios"/>
	   								</c:otherwise>
								</c:choose>
								<tr>
									<td class="${estilo}" width="20">
										&nbsp;
									</td>
									<td class="${estilo}" width="80">
										&nbsp;
									</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="320">
		                    			&nbsp;<nested:write property="nombre" />
		                    		</td>
		                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="100">
		                    			<nested:write property="idCodigo" />&nbsp;
		                    		</td>
		                         	<td align="center" valign="middle" class="${estilo}" width="70">
		                         		&nbsp;
		                         	</td>
		                  		</tr>
							</nested:iterate>
						</table>
					</td>
				</tr>
			</table>
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
// End --> 
</script>