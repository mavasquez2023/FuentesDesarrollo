<%@ include file="/html/comun/taglibs.jsp" %>
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
<html:form action="/DetalleUsuarios" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="usuarioFicha" />
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
              	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                	<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
                  	<table width="100%" border="0" cellpadding="0" cellspacing="0">
                    	<tr> 
                      		<td>
                      		<table width="100%" border="0" cellpadding="0" cellspacing="1">
                          		<tr valign="bottom"> 
                            		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Cambiar Password Encargado</strong></td>
                          		</tr>
                        	</table>
	                        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
	                            <tr> 
	                            <td width="10%" height="17" class="barratablas">RUT:</td>
	                            <nested:hidden property="idUsuario"><nested:write property="idUsuario"/></nested:hidden>
	                            <td class="textos_formularios"><nested:write property="idUsuarioFmt"/></td>
	                            <td class="barratablas">Estado:</td>
	                            <td class="textos_formularios"><nested:write property="nombreHabilitado"/></td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Nombre:</td>
	                            <td class="textos_formularios">
	                            	<nested:write property="nombre"/> <nested:write property="apPat"/> <nested:write property="apMat"/>
	                            </td>
	                            <td class="barratablas">Email:</td>
	                            <td class="textos_formularios"><nested:write property="email" />&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Tel&eacute;fono:</td>
	                            <td width="27%"class="textos_formularios"><nested:write property="fono"/>&nbsp;</td>
	                            <td class="barratablas">Fax:</td>
	                            <td class="textos_formularios"><nested:write property="fax" />&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td width="9%" height="17" class="barratablas">Celular:</td>
	                            <td width="23%" class="textos_formularios">
	                            	<nested:notEqual property="celular" value="0">
	                            		<nested:write property="celular" />
	                            	</nested:notEqual>&nbsp;
	                            </td>
	                            <td class="barratablas">Direcci&oacute;n:</td>
	                            <td class="textos_formularios"><nested:write property="direccion" />&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">N&ordm;:</td>
	                            <td class="textos_formularios"><nested:write property="numero" />&nbsp;</td>
	                            <td class="barratablas">Depto:</td>
	                            <td class="textos_formularios"><nested:write property="dpto" />&nbsp;</td>
	                          </tr>
	                          <tr>
	                            <td height="17" class="barratablas">Ciudad:</td>
	                            <td class="textos_formularios"><nested:write property="nombreCiudad" /></td>
	                            <td class="barratablas">Regi&oacute;n:</td>
	                            <td class="textos_formularios"><nested:write property="nombreRegion" /></td>
	                          </tr>
	                          <tr>
	                            <td height="17" class="barratablas">Comuna:</td>
	                            <td class="textos_formularios"><nested:write property="nombreComuna" /></td>
	                            <td class="barratablas">&nbsp;</td>
	                            <td class="textos_formularios">&nbsp;</td>
	                          </tr>
	                          <tr> 
	                            <td height="17" class="barratablas">Password:</td>
	                            <td height="17" class="textos_formularios">
	                            	<nested:password property="password" styleId="password" maxlength="4" size="20" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"></nested:password>
	                            </td>
	                            <td height="17" class="barratablas">Confirme Password:</td>
	                            <td height="17" class="textos_formularios">
									<nested:password property="confPassword" styleId="confPassword" maxlength="4" size="20" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"></nested:password>
	                            </td>
	                          </tr>
	                          <tr>
									<td height="4" colspan="4" bgcolor="#85b4be"></td>
								</tr>
	                        </table>
	                        </td>
                    	</tr>
                  	</table>
                  	</td>
              	</tr>
              	<tr>
                	<td height="41" valign="top" align="center"><br />
                		<html:submit property="operacion" styleClass="btn2" value="Guardar Password" onclick="javascript:bBuscar=false;"/>
                	</td>
              	</tr>
            </table>
            </td>
  		</tr>
  		<tr> 
    		<td width="170">&nbsp;</td>
  		</tr>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
var bBuscar=false;

function validaFormulario(f)
{
	var sMsje = "";
	if(!validaReq(document.getElementById("password")))
		sMsje = "* Debe ingresar la password\n";
	if(!validaReq(document.getElementById("confPassword")))
		sMsje += "* Debe ingresar la confirmacion de la password\n";
	
	if(sMsje == "")
	{
		if(document.getElementById("password").value == document.getElementById("confPassword").value)
			return true;
		else
			sMsje = "* Verifique que las password sean iguales";
	}
	
	if (sMsje != "") 
	{
		alert("Errores de validación:\n\n" + sMsje);
		return false;
	}
}											
// End --> 
</script>
</body>
</html:html>
