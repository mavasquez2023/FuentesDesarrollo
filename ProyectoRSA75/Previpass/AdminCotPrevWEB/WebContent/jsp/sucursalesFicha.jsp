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
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
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
          		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
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
		            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
		         	</tr>
		        </table>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Sucursal</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="12%" height="17" class="barratablas">C&oacute;digo</td>
                <td height="17" width="48%" class="textos_formularios">
					<nested:write property="codigo"/>
                </td>
                <td height="17" width="12%" class="barratablas"><strong>Nombre</strong></td>
                <td height="17" width="48%" class="textos_formularios">
					<nested:write property="nombre"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas">Direcci&oacute;n</td>
                <td height="17" class="textos_formularios">
					<nested:write property="direccion"/>&nbsp;
                </td>
                <td height="17" class="barratablas"><strong>Nº</strong></td>
                <td height="17" class="textos_formularios">
					<nested:write property="numero"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td width="9%" height="17" class="barratablas">Departamento</td>
                <td width="18%" class="textos_formularios">
					<nested:write property="dpto"/>&nbsp;
                </td>
                <td height="17" class="barratablas">Regi&oacute;n</td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreRegion"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Ciudad</strong></td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreCiudad"/>&nbsp;
                </td>
                <td height="17" class="barratablas">Comuna</td>
                <td height="17" class="textos_formularios">
					<nested:write property="nombreComuna"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas">Tel&eacute;fono</td>
                <td height="17" class="textos_formularios">
                (<nested:write property="codigoFono" />)
					<nested:write property="fono"/>&nbsp;
                </td>
                <td height="17" class="barratablas"><strong>Celular</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:notEqual property="celular" value="0">
						<nested:write property="celular"/>
					</nested:notEqual>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas">Fax:</td>
                <td height="17" class="textos_formularios">
					<nested:present
						property="codigoFax" >
					(<nested:write
						property="codigoFax" />)
						</nested:present>
					<nested:write property="fax"/>&nbsp;
                </td>
                <td height="17" class="barratablas">e-mail:</td>
                <td width="27%" height="17" class="textos_formularios">
					<nested:write property="email"/>&nbsp;
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="6" bgcolor="#85b4be"></td>
              </tr>
            </table></td>
        </tr>
      </table></td>
  </tr>
  <tr align="center">
    <td valign="top"><br />
    	<html:submit property="operacion" styleClass="btn3" value="Editar"/>
    </td>
  </tr>
</table>
</html:form>
</body>
</html:html>
