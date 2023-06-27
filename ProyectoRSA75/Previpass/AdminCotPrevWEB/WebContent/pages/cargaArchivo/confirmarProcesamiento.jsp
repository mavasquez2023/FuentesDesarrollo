<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="poneFoco('rut')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<script language="javascript" type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script language="javascript">

	// pone al día el Formulario 
	$(document).ready(function() {
		
		$("#volver").click(function(){
			window.location="/AdminCotPrevWEB/InitCargaArchivo.do";			
		});
		}
	);
	
</script>

<logic:equal name="archivoContingencia" property="tieneDatosIncorrectos" value="true">
	<div class="msgExito"><p><bean:message key="message.datos.erroneos"/></p><br/></div>
</logic:equal>
<logic:equal name="archivoContingencia" property="borraDatos" value="true">
	<div class="msgExito"><p><bean:message key="message.datos.borrarCache"/></p><br/></div>
</logic:equal>

<br>
<form action="/AdminCotPrevWEB/ProcesaArchivo.do">
<br/>
<c:set var="estilo" value="textos-formcolor2"/>
<logic:equal name="archivoContingencia" property="tieneDatosIncorrectos" value="true">
	<table width="590" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
		<tr> 
			<td width="25%" bordercolor="#CCCCCC" class="barra_tablas"><strong><bean:message key="label.archivo.noOK.rut"/></strong></td>	
			<td width="25%" bordercolor="#CCCCCC" class="barra_tablas"><strong><bean:message key="label.archivo.noOK.dv"/></strong></td>
			<td width="25%" bordercolor="#CCCCCC" class="barra_tablas"><strong><bean:message key="label.archivo.noOK.codigo"/></strong></td>
			<td width="25%" bordercolor="#CCCCCC" class="barra_tablas"><strong><bean:message key="label.archivo.noOK.error"/></strong></td>
		</tr>
		<logic:iterate id="datos" name="archivoContingencia" property="datosIncorrectos">
		<c:choose>
			<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/><c:set var="estilo2" value="tablaClaroBordes"/></c:when>
			<c:otherwise><c:set var="estilo" value="textos-formcolor2"/><c:set var="estilo2" value="tablaOscuroBordes"/></c:otherwise>
		</c:choose>
		<tr>
			<td class="${estilo}" ><bean:write name="datos" property="rutAfiliado"/></td>
			<td class="${estilo}" ><bean:write name="datos" property="dvAfiliado"/></td>
			<td class="${estilo}" ><bean:write name="datos" property="codigoRespuestaServicio"/></td>
			<td class="${estilo}" ><bean:write name="datos" property="error"/></td>
		</tr>
		</logic:iterate>
	</table>
</logic:equal>
<br>
<table width="590" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	<tr> 
		<td colspan="3" class="botonera" align="center">
			<html:submit property="" styleClass="btn3"><bean:message key="button.cargar"/></html:submit>
			<html:button property="" styleClass="btn3" styleId="volver"><bean:message key="button.cancelar"/></html:button>
		</td>
	</tr>
</table>
</form>