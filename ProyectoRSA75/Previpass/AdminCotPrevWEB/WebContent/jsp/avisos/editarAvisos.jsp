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
<html:form action="/EditarAvisos" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="usuarios" />
<nested:hidden property="idAvisos" styleId="idAvisos"/>
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
                            	<td height="30" bgcolor="#FFFFFF" class="titulo">
                            		<strong>
    	                        			Edici&oacute;n de Avisos
                            		</strong>
                            	</td>
                          	</tr>
                      		<tr>
								<td>&nbsp;</td>
							</tr>
                        </table>
                        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
                          	<tr> 
                            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
                          	</tr>
                          	<tr align="left"> 
	                            <td class="barratablas"><strong>Titulo *</strong></td>
	                            <td class="textos_formularios" colspan="3">
                            		<html:textarea property="titulo" styleId="titulo" styleClass="campos" rows="6" cols="90" onblur="javascript:cuentaTxtArea(this, 300);javascript:soloAviso(this, '.-&gt;&lt;&lsquo;&rsquo;&quot=/;: ');" onkeyup="javascript:cuentaTxtArea(this, 300);javascript:soloAviso(this, '.-&gt;&lt;&lsquo;&rsquo;&quot=/;: ');"></html:textarea>
	                            </td>
                          	</tr>
                          	<tr align="left">
	                            <td width="25%" class="barratablas"><strong>Estado</strong></td>
	                            <td width="25%" class="textos_formularios">
			                    	<html:select property="estado" styleClass="campos">
			                    		<html:option value="1">Habilitado</html:option>
			                    		<html:option value="0">Deshabilitado</html:option>
			                    	</html:select>
	                            </td>
	                            <td width="25%" class="barratablas"><strong>Link</strong></td>
	                            <td width="25%" class="textos_formularios">
	                            	<html:text property="link" styleId="link" maxlength="100" size="45" styleClass="campos" />
	                            </td>
                          	</tr>
                          	<tr align="left">
	                            <td class="barratablas"><strong>Ancho *</strong></td>
	                            <td class="textos_formularios">
	                            	<html:text property="ancho" styleId="ancho" maxlength="3" size="5" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
	                            <td class="barratablas"><strong>Alto *</strong></td>
	                            <td class="textos_formularios">
	                            	<html:text property="alto" styleId="alto" maxlength="3" size="5" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
	                            </td>
                          	</tr>
                          	<tr align="left">
	                            <td class="barratablas"><strong>Contenido *</strong></td>
	                            <td class="textos_formularios" colspan="3">
	                            	<html:textarea property="contenido" styleId="contenido" rows="10" cols="90" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 600);javascript:soloAviso(this, '.-&gt;&lt;&lsquo;&rsquo;&quot=/;: ');" onkeyup="javascript:cuentaTxtArea(this, 600);javascript:soloAviso(this, '.-&gt;&lt;&lsquo;&rsquo;&quot=/;: ');"/>
	                            </td>
                          	</tr>                          	                         	
                        </table>
                        </td>
                    </tr>
				</table>
				</td>
			</tr>
			<tr align="center">
             <td height="41" valign="top" align="right"><br/>
             	<html:submit property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:bBuscar=false;" />
             	<input type="button" name="vv" value="Cancelar" class="btn3" onclick="javascript:volver();">
             </td>
           </tr>
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
		if(!validaReq(document.getElementById("titulo")))
			sMsje += "* Debe ingresar el Titulo\n";
		if (!validaReq(document.getElementById("ancho")))
			sMsje += "* Debe ingresar el ancho\n";
		if (!validaReq(document.getElementById("alto")))
			sMsje += "* Debe ingresar el alto\n";
		if (!validaReq(document.getElementById("contenido")))
			sMsje += "* Debe ingresar el contenido\n";

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}

		return true;
	}
	
	function volver() 
	{
		frm = document.forms['EdicionAvisosActionForm'];
		frm.action = 'ListarAvisos.do';
		frm.submit();	
	}
	function foco()
	{
		document.getElementById('titulo').focus();
	}
// End --> 
</script>
</body>
</html:html>
