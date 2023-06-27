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
<html:form action="/ListaEntidadesRegImp" styleId="formulario">

<input type="hidden" id="accionInterna" name="accionInterna"/>
<nested:define id="origenAfp" property="origenAfp" />
<input type="hidden" id="origenAfp" name="origenAfp" value="${origenAfp}"/>
<nested:define id="origen" property="origen" />
<input type="hidden" id="origen" name="origen" value="${origen}"/>
<nested:define id="idExCaja" property="idExCaja" />
<input type="hidden" id="idExCaja" name="idExCaja" value="<nested:write property="idExCaja" />" />
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
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Entidad Regimen Impositivo</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<input type="button" class="btn3" name="volver" value="Volver" onclick="javascript:volverOrigen('${origen}','${origenAfp}');"/>&nbsp;
						<html:button property="operacion" styleClass="btn3" value="Crear Entidad" onclick="javascript:addEntidadRegImp('${origen}','${origenAfp}','${idExCaja}');"/>
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
					<td width="70%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Tasa</td>
					<td width="7%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr> 
					<td width="20" align="center" valign="middle" class="textos_formularios">
						<div align="center">
							&nbsp;
						</div>
					</td>
					<td colspan="4" width="590" class="textos_formularios" align="left">
						<strong>&nbsp;Regimen Impositivo</strong>
					</td>

				</tr>
				<tr id="salud">
					<td colspan="9">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:notEmpty property="listaEntidadRegimenImpositivo">
								<nested:iterate id="fila" property="listaEntidadRegimenImpositivo" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<nested:define id="nombre" property="nombre" />
									<nested:define id="idCodigo" property="idCodigo" />
									<tr>
										<td class="${estilo}" width="1%">
											&nbsp;
										</td>
										<td class="${estilo}" width="10%">
											&nbsp;
										</td>
			                    		<td align="right" valign="middle" class="${estilo}" width="40%">
			                    			&nbsp;<nested:write property="nombre" />
			                    			<input type="hidden" name="nombreAux" id="nombreAux_${idCodigo}" value="<nested:write property="nombre" />">
			                    		</td>
			                    		<td align="right" valign="middle" class="${estilo}" width="10%">
			                    			<nested:write property="tasaPension" />&nbsp;
			                    		</td>
										<td width="7%" class="${estilo}" align="center">
											<div align="center">
												<html:link title="Editar" action="/EdicionEntidadesRegImp.do?tipoEdicion=REGIMEN&codigoEntidad=${idCodigo}&idEntidadExCajaSeleccionada=${idExCaja}&origen=${origen}&origenAfp=${origenAfp}"><img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" /></html:link>
												<a href="javascript:delConfirmar('${idExCaja}', '${idCodigo}', '${origen}', '${origenAfp}');">
													<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
												</a>
											</div>
										</td>											
									</tr>
								</nested:iterate>
								</nested:notEmpty>
								<nested:empty property="listaEntidadRegimenImpositivo">
									<tr>
										<td class=textos_formularios colspan="5">
											No hay c&oacute;digos definidos para este &iacute;tem
										</td>
									</tr>
								</nested:empty>
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

function addEntidadRegImp(origen1, origen2, idEntidad){
	url = "<c:url value='/EdicionEntidadesRegImp.do?tipoEdicion=NUEVO&origen="+origen1+"&origenAfp="+origen2+"&idEntidadExCajaSeleccionada=" + idEntidad+"' />";
	document.location = url;
}										

function delConfirmar(idEntidad, idCodigo,  origen, origen2) {
	var url = '';
	var nombreEntidad = document.getElementById('nombreAux_' + idCodigo);
	if (nombreEntidad != null) {
		nombreEntidad = trim(nombreEntidad.value);
	}
	url = "<c:url value='/EdicionEntidadesRegImp.do?idEntidadExCajaSeleccionada=" + idEntidad + 
				"&codigoEntidad="+idCodigo+"&accionInterna=DEL_ENTIDAD&tipoEdicion=REGIMEN&origen=" + origen + "&origenAfp="+origen2+"' />";
		
			
	if (url.value != '') {
		if (confirm("¿Está seguro de que desea eliminar la Entidad " + nombreEntidad + "?")){
			document.location = url;
		} 
	}
} 

 
function volverOrigen(origen, origen2){
//http://localhost:9080/AdminCotPrevWEB/EdicionEntidadesExCaja.do?tipoEdicion=EXCAJA&idEntPagadora=80537000&idEntidad=12&origen=61533000
	formu = document.getElementById("formulario");	
	var idExCaja = document.getElementById('idExCaja').value;
	formu.action = 'EdicionEntidadesExCaja.do?tipoEdicion=EXCAJA&idEntPagadora='+origen+'&idEntidad='+idExCaja+'&origen='+origen2;
	formu.submit();
}
// -->
</script>
</body>
</html:html>
