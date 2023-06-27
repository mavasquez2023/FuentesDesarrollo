<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/SucursalesEditar" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="sucursalesFicha" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<html:hidden property="rutEmpresaFmt"><nested:write property="rutEmpresaFmt"/></html:hidden>
<html:hidden property="nombreEmpresa"><nested:write property="nombreEmpresa"/></html:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
      	<td valign="top">
      	<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr> 
          		<td>
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
			            <td>
			            	<nested:write property="nombreEmpresa"/>
			            </td>
		          	</tr>
		          	<tr> 
			            <td><strong>Sucursal:</strong></td>
			            <td>
							<html:select property="opcSucursal" styleClass="campos" onchange="javascript:bCancel=true;submit();">
								<html:optionsCollection property="sucursales" />
							</html:select>
						</td>
						<td colspan="2">&nbsp;</td>
		          	</tr>
		          	<tr> 
		            	<td colspan="4">&nbsp;</td>
		         	</tr>
		        </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Sucursal</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="15%" height="17" class="barra_tablas">C&oacute;digo</td>
                <td height="17" width="35%" class="textos_formularios">
					<nested:write property="codigo"/>
                </td>
                <td height="17" width="15%" class="barra_tablas"><strong>Nombre:</strong></td>
                <td height="17" width="35%" class="textos_formularios">
					<nested:write property="nombre"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas">Direcci&oacute;n</td>
                <td height="17" class="textos_formularios">
					<nested:write property="direccion"/>&nbsp;
                </td>
                <td height="17" class="barra_tablas"><strong>Nº</strong></td>
                <td height="17" class="textos_formularios">
					<nested:write property="numero"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td width="9%" height="17" class="barra_tablas">Departamento</td>
                <td width="18%" class="textos_formularios">
					<nested:write property="dpto"/>&nbsp;
                </td>
                <td height="17" class="barra_tablas">Regi&oacute;n</td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreRegion"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas">Ciudad</td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreCiudad"/>&nbsp;
                </td>
                <td height="17" class="barra_tablas">Comuna</td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreComuna"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas">Tel&eacute;fono</td>
                <td height="17" class="textos_formularios">
                 (<nested:write property="codigoFono" />)
					<nested:write property="fono"/>&nbsp;
                </td>
                <td height="17" class="barra_tablas"><strong>Celular</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:notEqual property="celular" value="0">
						<nested:write property="celular"/>
					</nested:notEqual>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barra_tablas">Fax:</td>
                <td height="17" class="textos_formularios">
                <nested:present property="codigoFax">
                 (<nested:write property="codigoFax" />)
					<nested:write property="fax"/>
					</nested:present>&nbsp;
                </td>
                <td height="17" class="barra_tablas">e-mail</td>
                <td width="27%" height="17" class="textos_formularios">
					<nested:write property="email"/>&nbsp;
                </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
	<nested:equal property="puedeEditar" value="true">
		<tr align="center">
			<td valign="top"><br />
				<html:submit property="operacion" styleClass="btn_grande" value="${editar}"/>
			</td>
		</tr>
	</nested:equal>
</table>
</html:form>
