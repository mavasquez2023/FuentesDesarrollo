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
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body>
<html:form action="/ListaEntidadesExCaja" styleId="formulario">

<input type="hidden" id="accionInterna" name="accionInterna"/>
<nested:define id="origen" property="origen" />
<input type="hidden" id="origen" name="origen" value="${origen}"/>
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
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Entidad Ex-Caja</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<input type="button" class="btn3" name="volver" value="Volver" onclick="javascript:volverOrigen('${origen}');"/>&nbsp;
						<html:button property="operacion" styleClass="btn3" value="Crear Entidad" onclick="javascript:addEntidad('${origen}');"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="3%" align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					<td width="10%" align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">Entidad</td>
					<td width="25%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					<td width="45%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Descripci&oacute;n</td>
					<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Tasa</td>					 
					<td width="7%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" class="textos_formularios">
						&nbsp;
					</td>
					<td colspan="7" width="590" class="textos_formularios" align="left">
						<strong>&nbsp;Ex-Caja</strong>
					</td>
				</tr>
				<nested:notEmpty property="listaEntidadExCaja">
					<nested:iterate id="fila" property="listaEntidadExCaja" indexId="nFila">
						<c:choose>
		   		    		<c:when test="${nFila % 2 == 0}">
		   		    			<c:set var="estilo" value="textos-formcolor2"/>
		   		    		</c:when>
							<c:otherwise>
								<c:set var="estilo" value="textos_formularios"/>
							</c:otherwise>
						</c:choose>
						<nested:define id="nombre" property="nombre" />
						<nested:define id="idEntPagadora" property="idEntPagadora" />
						<nested:define id="idFoliacion" property="idFoliacion" />
						<nested:define id="idEntidad" property="idEntidad" />
						<tr>
							<nested:notEqual property="idFoliacion" value="0">
								<td class="${estilo}" width="13%" colspan="2">&nbsp;</td>
	                    		<td class="${estilo}" width="25%">
	                    			<nested:write property="nombre" />&nbsp;
	                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
	                    		</td>
	                       		<td valign="middle" class="${estilo}" width="45%">
	                    			<nested:write property="descripcion" />&nbsp;
	                    		</td>
	                    		<nested:notEmpty property="nombre">
		                       		<td class="${estilo}" width="10%">
		                    			<nested:notEqual property="descripcion" value="">	
	                    					<div align="right"><nested:write property="tasaPension" /></div>
	                    				</nested:notEqual>
		                    		</td>
									<td width="7%" class="${estilo}">
										<div align="center">
											<html:link title="Editar" action="/EdicionEntidadesExCaja.do?tipoEdicion=EXCAJA&idEntPagadora=${idEntPagadora}&idEntidad=${idEntidad}&origen=${origen}"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
											<a href="javascript:delConfirmar('${idEntPagadora}', '${idEntidad}', '${origen}');">
												<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
											</a>
										</div>
									</td>
								</nested:notEmpty>
								<nested:empty property="nombre">
		                   			<td valign="middle" class="${estilo}" width="10%"><div align="right"><nested:write property="tasaPension" /></div>&nbsp;</td>
									<td width="7%" class="${estilo}">&nbsp;</td>
								</nested:empty>
							</nested:notEqual>
							<nested:equal property="idFoliacion" value="0">
								<td class="${estilo}" width="13%" colspan="2">&nbsp;</td>
	                    		<td class="${estilo}" width="25%"><nested:write property="nombre" />&nbsp;
	                    			<input type="hidden" name="nombreAux" id="nombreAux_${idEntPagadora}" value="<nested:write property="nombre" />">
	                    		</td>
	                    		<td class="${estilo}" width="45%">&nbsp;</td>
	                       		<td class="${estilo}" width="10%">&nbsp;</td>
								<td width="7%" class="${estilo}" align="center">
									<div align="center">
										<html:link title="Editar" action="/EdicionEntidadesExCaja.do?tipoEdicion=EXCAJA&idEntPagadora=${idEntPagadora}&idEntidad=${idEntidad}&origen=${origen}"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
										<a href="javascript:delConfirmar('${idEntPagadora}', '${idEntidad}', '${origen}');">
											<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
										</a>
									</div>
								</td>
							</nested:equal>
                  		</tr>
					</nested:iterate>
					</nested:notEmpty>
					<nested:empty property="listaEntidadExCaja">
						<tr>
							<td class=textos_formularios colspan="6">
								No hay c&oacute;digos definidos para este &iacute;tem
							</td>
						</tr>
					</nested:empty>
			</table>
		</td>
	</tr>
</table>
<br />
<br />
</html:form>
<script language="javaScript"> 
<!-- 

function addEntidad()
{
	formu = document.getElementById("formulario");	
	formu.action = 'EdicionEntidadesExCaja.do?tipoEdicion=NUEVO';
	formu.submit();
}

function delConfirmar(id, entidad,  origen) 
{
	var url = '';
	var nombreEntidad = document.getElementById('nombreAux_' + id);
	if (nombreEntidad != null) 
		nombreEntidad = trim(nombreEntidad.value);

	if (entidad == 'EXCAJA')
		url = "<c:url value='/EdicionEntidadesExCaja.do?idEntPagadora=" + id + "&idEntidad="+entidad+"&accionInterna=DEL_ENTIDAD&tipoEdicion=EXCAJA&origen=" + origen + "' />";
	else  
		url = "<c:url value='/EdicionEntidadesExCaja.do?idEntPagadora=" + id + "&idEntidad="+entidad+"&accionInterna=DEL_ENTIDAD&tipoEdicion=EXCAJA&origen=" + origen  + "' />";
		
	if (url.value != '') 
	{
		if (confirm("¿Está seguro de que desea eliminar la Entidad " + nombreEntidad + " y sus folios?"))
		{
			document.location = url;
		} 
	}
} 

function volverOrigen(id)
{
	formu = document.getElementById("formulario");	
	formu.action = 'EdicionEntidadesAfp.do?tipoEdicion=AFP&idEntPagadora='+id;
	formu.submit();
}
// -->
</script>
</body>
</html:html>
