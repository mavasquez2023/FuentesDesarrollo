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
	<html:form action="/listarNodo" styleId="formulario">
		<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
		<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="nodo" />
		<c:set var="opcioneditcrea" value="c"/>
		<logic:equal parameter="operacion" value="Modificar">
			<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="Modificar"/>
		</logic:equal>
		<logic:equal parameter="operacion" value="Crear_Nodo">
			<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="Crear_Nodo"/>
		</logic:equal>
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
						                            		<logic:equal property="accion" name="NodoForm"  value="Modificacion">
					    	                        			Edici&oacute;n de Nodo
																<c:set var="opcioneditcrea" value="e"/>
					    	                        		</logic:equal>
						                            		<logic:equal property="accion" name="NodoForm"  value="Crear">
					    	                        			Creaci&oacute;n de Nodo
						    	                        	</logic:equal>
					                            		</strong>
					                            	</td>
					                          	</tr>
					                        </table>
					                        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
					                          	<tr> 
					                            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
					                          	</tr>
					                          	<tr align="left"> 
						                            <td class="barratablas"><strong>Descripci&oacute;n *</strong></td>
						                            <td class="textos_formularios">
						                            	<nested:text property="desc" name="NodoForm" styleId="desc" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '.');" onkeyup="javascript:soloAlfanumerico(this, '.');"/>
						                            </td>
						                             <td class="barratablas"><strong>Admin Port</strong></td>
						                            <td class="textos_formularios" width="185">
						                            	<nested:text property="adminPort" name="NodoForm" styleId="adminPort" maxlength="5" size="8" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
					                          	</tr>
					                          	<tr align="left"> 
					                          		<td class="barratablas"><strong>Host *</strong></td>
						                            <td class="textos_formularios">
						                            	<nested:text property="host" name="NodoForm" styleId="host" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloAlfanumerico(this, './:');" onkeyup="javascript:soloAlfanumerico(this, './:');"/>
						                            </td>						                            
						                           	<td class="barratablas"><strong>Port</strong></td>
						                            <td class="textos_formularios" width="185">
						                            	<nested:text property="port" name="NodoForm" styleId="port" maxlength="5" size="8" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
					                          	</tr>
					                          	<tr>
					                          		<td class="barratablas"><strong>Url *</strong></td>
						                            <td class="textos_formularios" colspan="3">
						                            	<nested:text property="url" name="NodoForm" styleId="url" maxlength="80" size="100" styleClass="campos" onblur="javascript:soloAlfanumerico(this, './:@?&;');" onkeyup="javascript:soloAlfanumerico(this, './:@?&;');"/>
						                            </td>
					                          	</tr>
					                          	<tr align="left"> 
					                          		<td class="barratablas"><strong>Distribuidor *</strong></td>
						                            <td class="textos_formularios">
							                            <nested:select property="distribuidor" styleId="distribuidor" styleClass="campos" >
								                            <html:option value="-1">Seleccione Estado</html:option>
								                    		<html:option value="1">Habilitado</html:option>
								                    		<html:option value="0">Deshabilitado</html:option>
								                    	</nested:select>
						                            </td>
						                            <td width="9%" class="barratablas"><strong>Habilitado *</strong></td>
						                            <td class="textos_formularios" width="185">						                            
								                    	<nested:select property="habiliatado" styleClass="campos" styleId="habiliatado">
								                    		<html:option value="-1">Seleccione Estado</html:option>
								                    		<html:option value="1">Habilitado</html:option>
								                    		<html:option value="0">Deshabilitado</html:option>
								                    	</nested:select>
						                            </td>
					                          	</tr>
					                          	<tr>
					                          		<td class="barratablas"><strong>Initial Context Factory *</strong></td>
						                            <td class="textos_formularios" colspan="3">
						                            	<nested:text property="initial_context_factory" name="NodoForm" styleId="initial_context_factory" maxlength="100" size="100" styleClass="campos"/>
						                            </td>
					                          	</tr>
					                          	<tr>
					                          	<td class="barratablas"><strong>N&uacute;mero Conexiones Disponibles *</strong></td>
						                            <td class="textos_formularios">
						                            	<nested:text property="numConDisponibles" name="NodoForm" styleId="numConDisponibles" maxlength="9" size="30" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
						                            <td width="9%" class="barratablas"><strong>N&uacute;mero de Conexiones Maximas *</strong></td>
						                            <td class="textos_formularios" width="185">
						                            	<nested:text property="numConMax" name="NodoForm" styleId="numConMax" maxlength="9" size="30" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
					                          	</tr>
					                          	<tr align="left"> 
						                            <td class="barratablas"><strong>Uso M&iacute;nimo del Sistema *</strong></td>
						                            <td class="textos_formularios">
						                            	<nested:text property="usoSistMin" name="NodoForm" styleId="usoSistMin" maxlength="9" size="30" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
						                            <td class="barratablas"><strong>Uso M&aacute;ximo del Sistema *</strong></td>
						                            <td class="textos_formularios" width="185">
						                            	<nested:text property="usoSistMax" name="NodoForm" styleId="usoSistMax" maxlength="9" size="30" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						                            </td>
					                          	</tr>
					                          	<nested:hidden property="idNodo" name="NodoForm" />
					                       </table>
										</td>	
									</tr>
									<tr align="center">
						             <td height="41" valign="top"><br />
						             	<html:submit property="operacion" styleClass="btn3" value="Guardar" onclick="return validaFormulario();" />
						             	<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
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

<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<script language="javaScript">
<!--
	function lTrim(sStr)
	{
		while (sStr.charAt(0) == " ")
	      sStr = sStr.substr(1, sStr.length - 1);
	     return sStr;
	}

	function rTrim(sStr)
	{
		while (sStr.charAt(sStr.length - 1) == " ")
	      sStr = sStr.substr(0, sStr.length - 1);
	     return sStr;
	}

	function allTrim(sStr)
	{
	     return rTrim(lTrim(sStr));
	}

	function creaNumero(valor)
	{
		var valor1 = allTrim(valor);
		var tmp = valor1.split('.');
		var numTmp = "";
		for (i = 0; i < tmp.length; i++)
			numTmp += tmp[i];
		return new Number(numTmp);
	}

	function validaFormulario()
	{
		var mensaje ="" ;

		if (document.getElementById('desc') == null || document.getElementById('desc').value.length == 0 || allTrim(document.getElementById('desc').value) == '')
			mensaje += "* Campo Descripción es inválido. \n" 

		if (document.getElementById('host') == null || document.getElementById('host').value.length == 0 || allTrim(document.getElementById('host').value) == '')
			mensaje += "* Campo Host es inválido. \n" ; 

		if (document.getElementById('port') == null || document.getElementById('port').value.length == 0 || allTrim(document.getElementById('port').value) == '')
			mensaje += "* Campo Port es inválido. \n";
		else
		{
			var Port = creaNumero(document.getElementById('port').value);
			if (Port < 0 || Port > 32000)
				mensaje += "* El campo Port solo acepta valores entre 0 y 32.000. \n";
		}

		if (document.getElementById('adminPort') == null || document.getElementById('adminPort').value.length == 0 || allTrim(document.getElementById('adminPort').value) == '')
			mensaje += "* Campo Admin Port es inválido. \n" ;
		else
		{
			var adminPort = creaNumero(document.getElementById('adminPort').value);
			if (adminPort < 0 || adminPort > 32000)
				mensaje += "* El campo adminPort solo acepta valores entre 0 y 32.000. \n";
		}

		if (document.getElementById('url') == null || document.getElementById('url').value.length == 0 || allTrim(document.getElementById('url').value) == '')
			mensaje += "* Campo Url es inválido. \n" ;

		if (document.getElementById("distribuidor").value ==-1)
			mensaje += "* Campo Distribuidor es inválido. \n" ; 

		if (document.getElementById("habiliatado").value ==-1)
			mensaje += "* Campo Habilitado es inválido. \n" ; 

		if (document.getElementById('initial_context_factory') == null || document.getElementById('initial_context_factory').value.length == 0 || allTrim(document.getElementById('initial_context_factory').value) == '')
			mensaje += "* Campo Initial Context Factory es inválido. \n" ; 

		if (!validaReq(document.getElementById("numConDisponibles")))
			mensaje += "* Debe ingresar Número Conexiones Diponibles\n";
		else
		{
			var numero = new Number(document.getElementById("numConDisponibles").value);
			if (numero == NaN || numero <= 0)
				mensaje += "* Campo Número Conexiones Diponibles es inválido.\n";
		}
		if (!validaReq(document.getElementById("numConMax")))
			mensaje += "* Debe ingresar Número de Conexiones Máximas\n";
		else
		{
			var numero = new Number(document.getElementById("numConMax").value);
			if (numero == NaN || numero <= 0)
				mensaje += "* Campo Número Conexiones Máximas es inválido.\n";
		}

		if (!validaReq(document.getElementById("usoSistMin")))
			mensaje += "* Debe ingresar Uso Mínimo del Sistema\n";
		else
		{
			var numero = new Number(document.getElementById("usoSistMin").value);
			if (numero == NaN || numero < 0)
				mensaje += "* Campo Uso Mínimo del Sistema es inválido.\n";
		}

		if (!validaReq(document.getElementById("usoSistMax")))
			mensaje += "* Debe ingresar Uso Máximo del Sistema\n";
		else
		{
			var numero = new Number(document.getElementById("usoSistMax").value);
			if (numero == NaN || numero <= 0)
				mensaje += "* Campo Uso Máximo del Sistema es inválido.\n";
		}

		if (mensaje.length != 0 )
		{
			alert(mensaje);
			return false;
		}
	}
		function foco()
	{
		foco =  document.getElementById('opcionfoco').value;
		if(foco == 'c')
			 document.getElementById('desc').focus();
		else
			 document.getElementById('desc').focus();
	}

// -->
</script>

</body>
</html:html>
