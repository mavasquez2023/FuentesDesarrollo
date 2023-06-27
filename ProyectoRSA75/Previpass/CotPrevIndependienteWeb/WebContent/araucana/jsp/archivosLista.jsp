<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/ListaMapeoArchivos" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden name="accion" property="accion" styleId="accion" value="mapeo" />
<html:hidden name="subAccion" property="subAccion" styleId="subAccion" value="archivosLista" />
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
    	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
          	<tr> 
	            <td width="30%"><strong>Grupo de Convenios:</strong></td>
	            <td>
					<nested:select property="opcGrupoConvenio" styleClass="campos" onchange="javascript:submit();">
						<nested:optionsCollection property="gruposConvenio" />
						<nested:define id="opcGrupoConvenio" property="opcGrupoConvenio"/>
					</nested:select>
               	</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="2" bgcolor="#85b4be"></td>
         	</tr>
        </table>
        <br />
        <table width="100%" border="0" cellpadding="0" cellspacing="1">
        	<tr valign="bottom"> 
            	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeos de Archivos</strong></td>
            </tr>
        </table>
        </td>
    </tr>
    <tr align="center"> 
   		<td valign="top" bgcolor="#CCCCCC">
		<table width="100%" border="0" cellpadding="0" cellspacing="1">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
						<tr>
		                 	<td width="30%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Identificador</td>
		                 	<td width="55%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n   </td>
		                 	<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas" colspan="2">Acci&oacute;n</td>
	               		</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
		                 		<div align="left">
	                 				1 Remuneraci&oacute;n
		                 		</div>
		                 	</td>
		                 	<td nowrap="nowrap" class="textos_formularios" align="left"><nested:write property="descripcionR"/></td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios">
                   				<div align="center">
		                 			<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=R&grupoConvenio=${opcGrupoConvenio}" />" title="Ver detalle" class="links">
                   						<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                   					</a>
								</div>
                   			</td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios">
               					<nested:equal property="puedeEditar" value="true">
	                   				<div align="center">
	                   					<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=R&grupoConvenio=${opcGrupoConvenio}" />">
	                   						<img alt="Editar" title="Editar" src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0"/>
	                   					</a>
									</div>
								</nested:equal>
               					<nested:notEqual property="puedeEditar" value="true">
	               					&nbsp;
               					</nested:notEqual>
                   			</td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                 		<div align="left">
	                 				2 Gratificaci&oacute;n
		                 		</div>
		                 	</td>
		                 	<td nowrap="nowrap" class="textos-formcolor2" align="left"><nested:write property="descripcionG"/></td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2">
                   				<div align="center">
		                 			<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=G&grupoConvenio=${opcGrupoConvenio}" />" title="Ver detalle" class="links">
                   						<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                   					</a>
   	               				</div>
                   			</td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2">
               					<nested:equal property="puedeEditar" value="true">
	                   				<div align="center">
	                   					<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=G&grupoConvenio=${opcGrupoConvenio}" />">
	                   						<img alt="Editar" title="Editar" src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0"/>
	                   					</a>
    	               				</div>
	               				</nested:equal>
               					<nested:notEqual property="puedeEditar" value="true">
	               					&nbsp;
               					</nested:notEqual>
                   			</td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
		                 		<div align="left">
	                 				3 Reliquidaci&oacute;n
		                 		</div>
		                 	</td>
		                 	<td nowrap="nowrap" class="textos_formularios" align="left"><nested:write property="descripcionA"/></td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios">
                   				<div align="center">
		                 			<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=A&grupoConvenio=${opcGrupoConvenio}" />" title="Ver detalle" class="links">
	               						<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
	               					</a>
               					</div>
                   			</td>
                   			<td align="center" nowrap="nowrap" class="textos_formularios">
								<nested:equal property="puedeEditar" value="true">
                   					<div align="center">
	                   					<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=A&grupoConvenio=${opcGrupoConvenio}" />">
	                   						<img alt="Editar" title="Editar" src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0"/>
	                   					</a>
	                   				</div>
	               				</nested:equal>
               					<nested:notEqual property="puedeEditar" value="true">
	               					&nbsp;
               					</nested:notEqual>
                   			</td>
               			</tr>
               			<tr> 
		                 	<td align="center" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                 		<div align="left">
	                 				4 Dep&oacute;sito Convenido
		                 		</div>
		                 	</td>
		                 	<td nowrap="nowrap" class="textos-formcolor2" align="left"><nested:write property="descripcionD"/></td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2">
                   				<div align="center">
		                 			<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=D&grupoConvenio=${opcGrupoConvenio}" />" title="Ver detalle" class="links">
                   						<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
	                   				</a>
    	               			</div>
                   			</td>
                   			<td align="center" nowrap="nowrap" class="textos-formcolor2">
               					<nested:equal property="puedeEditar" value="true">
	                   				<div align="center">
	                   					<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosEditar&tipoNomina=D&grupoConvenio=${opcGrupoConvenio}" />">
	                   						<img alt="Editar" title="Editar" src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0"/>
		                   				</a>
	    	               			</div>
	                   			</nested:equal>
               					<nested:notEqual property="puedeEditar" value="true">
	               					&nbsp;
               					</nested:notEqual>
                   			</td>
               			</tr>
					</table>
					</td>
				</tr>
            </table>
		</td>
  	</tr>
</table>
</html:form>