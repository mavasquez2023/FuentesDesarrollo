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
<html:form action="ListarAvisos" styleId="formulario">

<input type="hidden" id="operacion" name="operacion"/>

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="2">
			<html:errors />
		</td>
	</tr>
	<tr>
		<td colspan="2">
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
		<td  colspan="2" valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mantenedor Avisos</strong></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr class="subtitulos_tablas">
                		<td width="65%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">T&iacute;tulo</td>
		               	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
		               	<td width="15%" colspan="5" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
               		</tr>
				<tr>
                    <td height="17" class="textos_formularios">
						&nbsp;<nested:write property="titulo"/>
	                </td>
                    <td height="17" class="textos_formularios">
						&nbsp;
						<c:choose>
							<c:when test="${ListarAvisosActionForm.estado eq 1}">Habilitado</c:when>
							<c:when test="${ListarAvisosActionForm.estado eq 0}">Deshabilitado</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose>
						
	                </td>
					<td align="center" nowrap="nowrap" class="textos_formularios">
						<div align="center">
							<a href="javascript:editar('${ListarAvisosActionForm.idAvisos}');"><img src="<c:url value='/img/iconos/ico_hojap.gif' />" width="14" height="13" border="0" alt="Editar" title="Editar Aviso"/></a>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
<!--
function editar(id)
{
	if (id != '') 
	{
		formu = document.forms['ListarAvisosActionForm'];
		formu.action = 'EditarAvisos.do?accion=admin&subAccion=usuarios&operacion=Editar&idAviso=' + id;
		formu.submit();
	}
}	
function delConfirmar(url){
	if (confirm("¿Está seguro de que desea eliminar el tipo de Asignacion Familiar?")){
		document.location = url;
	} 
}
// -->
</script>
</body>
</html:html>

