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
	<TITLE>Entidades</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/entidad.js'/>"></script>
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body onLoad="foco();">
<html:form action="/EdicionEntidadesAfp" styleId="formulario" onsubmit="return onFormSubmit(this)">
<input type="hidden" id="guardaFolio" name="guardaFolio"/>
<input type="hidden" id="tipoEdicion" name="tipoEdicion" value="<nested:write property="tipoEdicion" />"/>
<input type="hidden" id="accionInterna" name="accionInterna"/>

<input type="hidden" id="idFoliacionNew" name="idFoliacionNew"/>
<input type="hidden" id="idEntPagadoraNew" name="idEntPagadoraNew"/>
<input type="hidden" id="folioActualNew" name="folioActualNew"/>
<input type="hidden" id="folioInicialNew" name="folioInicialNew"/>
<input type="hidden" id="folioFinalNew" name="folioFinalNew"/>
<input type="hidden" id="foliosEnUsoNew" name="foliosEnUsoNew"/>
<input type="hidden" id="largoFolios" name="largoFolios" value="<nested:write property="largoFolios" />" />
<input type="hidden" id="codigoEntidadAntiguo" name="codigoEntidadAntiguo" value="<nested:write property="codigoEntidadAntiguo" />" />
<input type="hidden" id="listaFolios" name="listaFolios" value="<nested:write property="listaFolios" />" />
<input type="hidden" id="folioBorrar" name="folioBorrar" />

<input type="hidden" id="idEntPagadora" name="idEntPagadora" value="<nested:write property="idEntPagadora" />" />

<input type="hidden" id="nuevaEntidad" name="nuevaEntidad" value="<nested:write property="nuevaEntidad" />" />
<input type="hidden" id="idBancoSeleccionado" name="idBancoSeleccionado"/>
<input type="hidden" id="idAfcSeleccionado" name="idAfcSeleccionado"/>
<input type="hidden" id="idEntidadSeleccionada" name="idEntidadSeleccionada"/>
<c:set var="opcioneditcrea" value="c"/>

<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr valign="middle">
	<td>
		<html:errors/>
	</td>
</tr>
<tr valign="middle">
	<td>
		<html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
	</td>
</tr>
<tr>
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<nested:equal property="nuevaEntidad" value="false">
				<tr height="26" valign="middle">
					<td valign="middle" colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Edici&oacute;n de Entidad AFP</strong>
						<c:set var="opcioneditcrea" value="e"/>
					</td>
				</tr>
			</nested:equal>
			<nested:equal property="nuevaEntidad" value="true">
				<tr height="26" valign="middle">
					<td valign="middle" colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Creaci&oacute;n de Entidad</strong>
					</td>
				</tr>
				<tr height="26" valign="middle">
					<td valign="middle" width="110" class="barratablas" >
						<strong>Entidad</strong>
					</td>	
					<td valign="middle" width="150" class="textos_formularios" colspan="3">
						<select id="entidades" name="entidades" onchange="cambiaEntidad();" class="campos">						
							<nested:empty property="tipoEdicionSeleccionada">
			               		<nested:iterate id="filaEntidades" property="tiposEdicion" indexId="nFila">
			               	       		<option value="<nested:write property="nombre"/>"><nested:write property="nombre" /></option>
				               	</nested:iterate>
			               	</nested:empty>
			               	<nested:notEmpty property="tipoEdicionSeleccionada">
			               		<nested:define id="tipoEdicionSeleccionada" property="tipoEdicionSeleccionada" />
			               		<nested:iterate id="filaEntidades" property="tiposEdicion" indexId="nFila">
			               			<nested:equal property="nombre" value="${tipoEdicionSeleccionada}">
			               	       		<option value="<nested:write property="nombre"/>" selected><nested:write property="nombre" /></option>
			               	       	</nested:equal>	
			               	       	<nested:notEqual property="nombre" value="${tipoEdicionSeleccionada}">
			               	       		<option value="<nested:write property="nombre"/>"><nested:write property="nombre" /></option>
			               	       	</nested:notEqual>	
				               	</nested:iterate>
			               	</nested:notEmpty>
						</select>			
					</td>
				</tr>
			</nested:equal>
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Entidad</strong>
				</td>
			</tr>		
			<nested:equal property="nuevaEntidad" value="false">
				<tr>
					<td width="100">
					</td>	
					<td width="150">
					</td>
					<td width="100"></td>
					<td width="150">
						<nested:equal property="idEntPagadora" value="61533000">
							<div align="right">
								<html:button property="operacion" styleClass="btn3" value="Ex-Caja" 
									onclick="javascript:listaExCaja();"/>
							</div>
						</nested:equal>	
					</td>
				</tr>
			</nested:equal>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>RUT Entidad *</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:equal property="nuevaEntidad" value="false">
						<nested:write property="rutEntidad" />
						<input type="hidden" name="rutEntidad" id="rutEntidad" value="<nested:write property="rutEntidad" />"/>
					</nested:equal>
					<nested:notEqual property="nuevaEntidad" value="false">
						<input type="text" name="rutEntidad" id="rutEntidad" value="<nested:write property="rutEntidad" />" class="campos" maxlength="12" size="14" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);" />
						</nested:notEqual>
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>Código Entidad *</strong>
				</td>	
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="codigoEntidad" styleId="codigoEntidad" styleClass="campos" maxlength="5" size="30" onblur="javascript:soloNumero(this, '');"  onkeyup="javascript:soloNumero(this,'');"/>
				</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Nombre *</strong>
				</td>	
				<td valign="middle" colspan="3" width="150" class="textos_formularios">
					<nested:text property="nombreEntidad" styleId="nombreEntidad" styleClass="campos" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);" maxlength="50" size="90"/>
				</td>				
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Tiene Convenio</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:equal property="tieneConvenio" value="true">
						<input type="radio" id="tieneConvenio" name="tieneConvenio" value="1" checked/>S&iacute;
						<input type="radio" id="tieneConvenio" name="tieneConvenio" value="0" />No
					</nested:equal>
					<nested:equal property="tieneConvenio" value="false">
						<input type="radio" id="tieneConvenio" name="tieneConvenio" value="1" />S&iacute;
						<input type="radio" id="tieneConvenio" name="tieneConvenio" value="0" checked/>No
					</nested:equal>
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>Imprime</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:equal property="imprime" value="true">
						<input type="radio" id="imprime" name="imprime" value="1" checked/>S&iacute;
						<input type="radio" id="imprime" name="imprime" value="0" />No
					</nested:equal>
					<nested:equal property="imprime" value="false">
						<input type="radio" id="imprime" name="imprime" value="1" />S&iacute;
						<input type="radio" id="imprime" name="imprime" value="0" checked/>No
					</nested:equal>
				</td>
			</tr>			
			<tr>
				<td>
				</td>
			</tr>
			<tr> 
          		<td height="4" colspan="4" bgcolor="#85b4be">
          		</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Cuenta Corriente para Cheque</strong>
				</td>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Banco</strong>
				</td>
				<nested:define id="idBancoActual" property="idBanco" />
				<td valign="middle" width="150" class="textos_formularios">
					<select id="bancos" name="bancos" class="campos"  onchange="javaScript: limpiaCuenta(this);">						
	               		<nested:iterate id="fila" property="listaBancos" indexId="nFila">
	               			<nested:equal property="idBanco" value="${idBancoActual}">
		               			<option value="<nested:write property="idBanco"/>" selected><nested:write property="nombre" /></option>
	               			</nested:equal>
	               			<nested:notEqual property="idBanco" value="${idBancoActual}">
		               			<option value="<nested:write property="idBanco"/>"><nested:write property="nombre" /></option>
	               			</nested:notEqual>
		               	</nested:iterate>
					</select>			
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>N° de Cuenta *</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="idCtaCte" styleId="idCtaCte" styleClass="campos" maxlength="15" size="30" onblur="javascript:soloCtaCte(this);" onkeyup="javascript:soloCtaCte(this);"/>
				</td>
			</tr>
			<tr> 
          		<td height="4" colspan="4" bgcolor="#85b4be"></td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>AFC</strong>
				</td>
				<nested:define id="idAfc" property="idAfc" />
				<td valign="middle" width="150" class="textos_formularios">
					<select id="afc" name="afc" class="campos">						
	               		<nested:iterate id="fila" property="listaAfc" indexId="nFila">
	               			<nested:equal property="id" value="${idAfc}">
		               			<option value="<nested:write property="id"/>" selected><nested:write property="nombre" /></option>
	               			</nested:equal>
	               			<nested:notEqual property="id" value="${idAfc}">
		               			<option value="<nested:write property="id"/>"><nested:write property="nombre" /></option>
	               			</nested:notEqual>
		               	</nested:iterate>
					</select>			
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong></strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					&nbsp;
				</td>
			</tr>
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Datos Cuenta Corriente para Transferencia</strong>
				</td>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Banco</strong>
				</td>
				<nested:define id="idBancoSplActual" property="idBancoSpl" />
				<td valign="middle" width="150" class="textos_formularios">
					<select id="idBancoSpl" name="idBancoSpl" class="campos"  onchange="javaScript: limpiaCuentaSpl(this);">						
	               		<nested:iterate id="fila" property="listaBancos" indexId="nFila">
	               			<nested:equal property="idBanco" value="${idBancoSplActual}">
		               			<option value="<nested:write property="idBanco"/>" selected><nested:write property="nombre" /></option>
	               			</nested:equal>
	               			<nested:notEqual property="idBanco" value="${idBancoSplActual}">
		               			<option value="<nested:write property="idBanco"/>"><nested:write property="nombre" /></option>
	               			</nested:notEqual>
		               	</nested:iterate>
					</select>			
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>N° de Cuenta *</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="idCtaCteSpl" styleId="idCtaCteSpl" styleClass="campos" maxlength="15" size="30" onblur="javascript:soloCtaCte(this);" onkeyup="javascript:soloCtaCte(this);"/>
				</td>
			</tr>
			<tr> 
          		<td valign="middle" width="130" class="barratablas">
					<strong>Genera Cheque</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:equal property="generaCheque" value="true">
						<input type="radio" id="generaCheque" name="generaCheque" value="1" checked/>S&iacute;
						<input type="radio" id="generaCheque" name="generaCheque" value="0" />No
					</nested:equal>
					<nested:equal property="generaCheque" value="false">
						<input type="radio" id="generaCheque" name="generaCheque" value="1" />S&iacute;
						<input type="radio" id="generaCheque" name="generaCheque" value="0" checked/>No
					</nested:equal>
				</td>
				<td valign="middle" width="130" class="barratablas"><strong>Correo contacto  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="correoContacto" styleId="correoContacto" styleClass="campos" maxlength="100" size="30" onkeyup="javascript:soloEmailMinuscula(this);"/>
				</td>
		<tr/> 					
		<tr> 
          		<td valign="middle" width="130" class="barratablas"><strong>Nombre contacto  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="nombreContacto" styleId="nombreContacto" styleClass="campos" maxlength="100" size="35"  onkeyup="javascript:soloNombresMun(this);"/>
				</td>
				<td valign="middle" width="130" class="barratablas"><strong>Entidad FTP  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="entidadFTP" styleId="entidadFTP" styleClass="campos" maxlength="100" size="30" onkeyup="javascript:soloEmailMinuscula(this);"/>
				</td>
			</tr>
			<tr> 
          		<td valign="middle" width="130" class="barratablas"><strong>Carpeta FTP  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="carpetaFTP" styleId="carpetaFTP" styleClass="campos" maxlength="100" size="35" onkeyup="javascript:soloEmailMinuscula(this);"/>
				</td>
				<td valign="middle" width="130" class="barratablas"><strong>Usuario FTP  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:text property="usuarioFTP" styleId="usuarioFTP" styleClass="campos" maxlength="15" size="30" onkeyup="javascript:soloAlfanumericoMun(this,'Ñ');"/>
				</td>
			</tr>
			<tr> 
        		<td valign="middle" width="130" class="barratablas"><strong>Clave FTP  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
					<nested:password property="claveFTP" styleId="claveFTP" styleClass="campos" maxlength="15" size="35"/>
				</td>
				<td valign="middle" width="130" class="barratablas"><strong>Tipo Archivo Mov. Personal  *</strong></td>
				<td valign="middle" width="150" class="textos_formularios">
						<html:select property="tipoArchMP" styleClass="campos">
							<html:option value="0">Nada</html:option>
							<html:option value="1">AFP</html:option>
							<html:option value="2">IPS</html:option>
							<html:option value="3">AFC</html:option>
						</html:select>
				</td>
			</tr>
			
			<tr height="26" valign="middle">
				<td valign="middle" height="20" class="barratablas" colspan="4">
					<strong>Par&aacute;metros de Tasas</strong>
				</td>
			</tr>		
			<tr height="26" valign="middle">
				<td valign="middle" width="130" class="barratablas">
					<strong>Cotizaci&oacute;n Obligatoria *</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
				<!-- Eliminado tasaNormal-->
					<input type="text" name="cotizacionObligatoria" id="cotizacionObligatoria" value="${EdicionEntidadesAfpActionForm.cotizacionObligatoria}" class="campos" maxlength="5" size="30" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);">
				</td>
				<td valign="middle" width="130" class="barratablas">
					<strong>Comisi&oacute;n *</strong>
				</td>
				<td valign="middle" width="150" class="textos_formularios">
					<!-- Eliminado tasaPensionados-->
					<input type="text" name="comision" id="comision" value="${EdicionEntidadesAfpActionForm.comision}" class="campos" maxlength="5" size="30" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);">
				</td>
			</tr>
				<tr>
					<td valign="middle" width="130" class="barratablas">
						<strong>Sis *</strong>
					</td>
            		<td valign="middle" width="150" class="textos_formularios">
					<!-- Eliminado tasaPensionados-->
						<input type="text" name="sis" id="sis" value="${EdicionEntidadesAfpActionForm.sis}" class="campos" maxlength="5" size="30" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);">					
					</td>
					<td valign="middle" width="130" class="barratablas">
					
					</td>
					<td valign="middle" width="150" class="textos_formularios">
					
					</td>
				</tr>
				<tr height="26" valign="middle">
					<td valign="middle" colspan="4" height="20" bgcolor="#FFFFFF" align="left" class="barratablas">
						<strong>Datos de Folios</strong>						
					</td>
				</tr>
				<tr id="agregarFolio" height="26" valign="middle">
					<td valign="middle" colspan="4" height="20" bgcolor="#FFFFFF" align="right" class="barratablas">
						<html:button property="operacion" styleClass="btn3" value="Nuevo Folio" onclick="javascript:addFolio();"/>
					</td>
				</tr>			
			<tr height="26" valign="middle">
				<td valign="middle" colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<nested:notEmpty property="lista">
						<tr height="26" valign="middle">
							<td valign="middle" width="30%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Estado							
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Inicial							
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Final							
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Actual							
							</td>
							<td valign="middle" width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Acciones							
							</td>
						</tr>
						<nested:iterate id="fila" property="lista" indexId="nFila">
						<nested:define id="idFoliacion" property="idFoliacion" />
						<nested:define id="foliosEnUso" property="foliosEnUso" />
						<nested:define id="idEntPagadora" property="idEntPagadora" />
							<tbody id="<nested:write property="idFoliacion" />Body">
		  			        	<tr height="26" valign="middle">
		                			<td valign="middle" width="30%" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
		                				<input type="hidden" id="idFoliacion${nFila}" name="idFoliacion${nFila}" value="${idFoliacion}"/>
		                				<select id="foliosEnUsos${nFila}" name="foliosEnUsos${nFila}" class="campos">						
						               			<nested:equal property="foliosEnUso" value="1">
							               			<option value="1" selected>ACTIVADO</option>
							               			<option value="0" >DESACTIVADO</option>
						               			</nested:equal>
						               			<nested:notEqual property="foliosEnUso" value="1">
							               			<option value="1" >ACTIVADO</option>
							               			<option value="0" selected>DESACTIVADO</option>
						               			</nested:notEqual>
										</select>
		                			</td>
		                    		<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos"> 
		                      			<nested:text property="folioInicial" styleId="folioInicial${nFila}" styleClass="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
		                    		</td>
		                    		<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos"">
		                    			<nested:text property="folioFinal" styleId="folioFinal${nFila}" styleClass="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
		                    		</td>
									<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos">
		                    			<nested:text property="folioActual" styleId="folioActual${nFila}" styleClass="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
		                    		</td>
									<td valign="middle" width="10%" class="campos" align="center">
										<nested:empty property="nombre">&nbsp;</nested:empty>
											<div align="center">
												<a href="javascript:delFolioTipo('${idFoliacion}','${idEntPagadora}','${nFila}','AFP');">
													<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0"   />
												</a>
											</div>
									</td>
		                		</tr>
	               			</tbody>
	               		</nested:iterate>
	               		</nested:notEmpty>
	               		
	               		<tr id="tituloFolioNuevo" style="display:none"  height="26">
							<td valign="middle" width="30%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Estado						
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Inicial *							
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Final *							
							</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
								Folio Actual *							
							</td>
							<td valign="middle" width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barratablas">
									&nbsp;						
							</td>
						</tr>
						<tr id="folioNuevo" style="display:none"  height="26">
                			<td valign="middle" width="30%" align="center" valign="middle" nowrap="nowrap" class="textos_formularios">
                				<select id="foliosEnUsos-1" name="foliosEnUsos-1" class="campos">						
		               				<option value="-1" selected>-Seleccione-</option>
		               				<option value="1" >ACTIVADO</option>
			               			<option value="0" >DESACTIVADO</option>
				            	</select>
                			</td>
                    		<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos"> 
                      			<input type="text" id="folioInicial-1" name="folioInicial-1" value="" class="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" />
                    		</td>
                    		<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos">
                    			<input type="text" id="folioFinal-1" name="folioFinal-1" value="" class="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" />
                    		</td>
							<td valign="middle" width="20%" align="center" valign="middle" nowrap="nowrap" class="campos">
                    			<input type="text" id="folioActual-1" name="folioActual-1" value="" class="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" />
                    		</td>
							<td valign="middle" width="10%" class="campos" align="center">
								&nbsp;
							</td>
		                </tr>
		                <tr id="folioNuevo2" style="display:none" height="26">
						<td valign="middle" width="50%" colspan="2" > &nbsp; </td>
						<td valign="middle" width="20%" align="center" ><html:button property="operacion" styleClass="btn3" value="Agregar Folio" onclick="javascript:saveFolioTipo('-1','-1','-1','AFP');"/></td>
						<td valign="middle" width="20%" align="center" > <html:button property="operacion" styleClass="btn3" value="Eliminar Folio" onclick="javascript:noSave();"/></td>
						<td valign="middle" width="10%"  >	&nbsp;	</td>
						</tr>
					</table>
				
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
</tr>

<tr>
	<td  >&nbsp;	</td>
</tr>
<tr height="26" valign="middle">
	<td valign="middle" valign="top">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	        <tr align="center">
    	     	<td valign="middle" valign="top" height="20">
    	     		<nested:notEqual property="nuevaEntidad" value="true">
	        	 		<html:button property="operacion" value="Guardar" styleClass="btn4" onclick="irAction('GUARDAR');"/>
   	     			</nested:notEqual>
   	     			<nested:equal property="nuevaEntidad" value="true">
         			   <html:button property="operacion" styleClass="btn3" value="Guardar" onclick="irAction('NEW');"/>
					</nested:equal>
     			    <html:button property="operacion" value="Cancelar" styleClass="btn4" onclick="cancelar();"/>
            	</td>
			</tr>
		</table>
	</td>
</tr>
</table>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<script type="text/javascript">
<!--
	function irAction(accion) {
		frm = document.getElementById('formulario');
		sMsje = '';
		if(actualizaFolios()){
			if (accion == 'GUARDAR') {
				if(document.getElementById("agregarFolio").style.display=='none')
					sMsje=validaIngresoFolio('-1');
				if(sMsje == ''){
					save('AFP');
				}else{
					alert(sMsje);
				}
			} else {
				if(document.getElementById("agregarFolio").style.display=='none')
					sMsje=validaIngresoFolio('-1');
				if(sMsje == ''){
					saveNewEntidad('AFP');			
				}else{
					alert(sMsje);
				}
			}
		}
	}	
	function foco()
	{
		foco =  document.getElementById('opcionfoco').value;
		if(foco == 'c')
			 document.getElementById('entidades').focus();
		else
			 document.getElementById('codigoEntidad').focus();
	}
	function limpiaCuenta(obj){
		if(obj[0].selected == true){
		document.getElementById('idCtaCte').value = '0';
		}
	}
	
	function limpiaCuentaSpl(obj) {
		if(obj[0].selected == true) {
			document.getElementById('idCtaCteSpl').value = '0';
		}
	}

//-->
</script>
</html:form>
</body>
</html:html>
