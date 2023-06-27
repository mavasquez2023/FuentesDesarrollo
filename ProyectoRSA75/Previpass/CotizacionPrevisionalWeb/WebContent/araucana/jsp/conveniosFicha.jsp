<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/DetalleConvenio" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosFicha" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<html:hidden property="eliminaNominas" name="eliminaNominas" styleId="eliminaNominas" value="NO" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td valign="top">
      	<table class="textos-formularios3">
      		<tr> 
            	<td colspan="4">&nbsp;</td>
         	</tr>
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
            	<td colspan="4">&nbsp;</td>
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
            <table border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barra_tablas"><strong>C&oacute;digo:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:write property="codigoConvenio" format="00"/>
                </td>
                <td width="25%" height="17" class="barra_tablas"><strong>Descripci&oacute;n:</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:write property="nombreConvenio"/>
                </td>
              </tr>
              <tr>
                <td height="17" class="barra_tablas"><strong>Estado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:equal value="0" property="opcHabilitado">
                		Deshabilitado
                	</nested:equal>
                	<nested:equal value="1" property="opcHabilitado">
                		Habilitado
                	</nested:equal>
                </td>
                <td height="17" class="barra_tablas"><strong>Grupo de Convenios:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreGrupoConvenio"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Creado:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="fechaCreado"/>
                </td>
                <td width="25%" height="17" class="barra_tablas">&Uacute;ltimo uso:</td>
                <td height="17" class="textos_formularios">
                	<nested:write property="fechaUltAcceso"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Caja de Compensaci&oacute;n:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreCaja"/>
                </td>
                <td height="17" class="barra_tablas"><strong>C&aacute;lculo de Mov. Personal</strong></td>
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
                <td height="17" class="barra_tablas"><strong>N&ordm; N&oacute;minas:</strong></td>
                <td height="17" class="textos_formularios">
                	<div align="right">
	                	<nested:write property="numNominas"/>
	                </div>
                </td>
                <td height="17" class="barra_tablas"><strong>N&ordm; Cotizaciones:</strong></td>
                <td height="17" class="textos_formularios">
                	<div align="right">
	                	<nested:write property="numCotiz"/>
	                </div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Actividad Econ&oacute;mica:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreActividadEconomica"/>
                </td>
                <td height="17" class="barra_tablas">&nbsp;</td>
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
            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de Archivos de N&oacute;minas</strong></td>
              </tr>
            </table>
            <bean:define id="idGrupoConvenio"><nested:write property="opcGrupoConvenio"/></bean:define>
            <table border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barra_tablas"><strong>Remuneraciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionR"/></td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=R&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Gratificaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionG"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=G&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Reliquidaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionA"/></td>
                <td  height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=A&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas"><strong>Dep&oacute;sitos Convenidos e Indemnizaciones:</strong></td>
                <td height="17" class="textos_formularios"><nested:write property="descripcionD"/></td>
                <td height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/DetalleMapeoArchivos.do?accion=mapeo&subAccion=archivosLista&subSubAccion=archivosFicha&tipoNomina=D&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
            </table>
            <br />
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo de C&oacute;digos</strong></td>
              </tr>
            </table>
            <table border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
              <tr> 
                <td width="23%" height="17" class="barra_tablas"><strong>Mapeo:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:write property="nombreMapeoCodigo"/>
                </td>
                <td width="13%" height="17" class="textos_formularios">
                	<div align="center">
                		<a href="<c:url value="/ListaCodigosFicha.do?accion=mapeo&subAccion=codigosFicha&grupoConvenio=${idGrupoConvenio}" />" title="Ver detalle" class="links">
                			<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
                		</a>
                	</div>
                </td>
              </tr>
            </table>
            <br />
            <nested:equal property="puedeEditar" value="true">
	            <table width="100%" border="0" cellpadding="0" cellspacing="1">
	              <tr valign="bottom"> 
	                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong><a name="ancla" class="titulo">Accesos al Convenio</a></strong></td>
	              </tr>
	            </table>
	            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
	                	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
	                	<td width="50%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Nombre</td>
	                	<td width="30%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Accesos</td>
	             	</tr>
	             	<nested:notEmpty property="encargadosAccConvs">
		             	<nested:iterate id="filaSuc" property="encargadosAccConvs" indexId="nFilaSuc">
							<tr>
								<td valign="top" nowrap="nowrap" class="textos_formularios"><nested:write property="rutUsuario"/></td>
								<td valign="top" nowrap="nowrap" class="textos_formularios"><nested:write property="nombre"/></td>
								<td valign="top" nowrap="nowrap" class="textos_formularios"><nested:write property="nombrePermiso"/></td>
							</tr>
						</nested:iterate>
					</nested:notEmpty>
					<nested:empty property="encargadosAccConvs">
						<tr>
							<td valign="middle" nowrap="nowrap" class="textos_formularios" colspan="5">
	            				No existen encargados para este Convenio
							</td>
						</tr>
					</nested:empty>
				</table>
			</nested:equal>
            </td>
        </tr>
      </table></td>
  </tr>
  <nested:equal property="puedeEditar" value="true">
	  <tr align="center">
	    <td valign="top"><br />
	    	<html:submit property="operacion" value="${editar}" styleClass="btn_grande"/>
	      </td>
	  </tr>
  </nested:equal>
</table>
</html:form>

