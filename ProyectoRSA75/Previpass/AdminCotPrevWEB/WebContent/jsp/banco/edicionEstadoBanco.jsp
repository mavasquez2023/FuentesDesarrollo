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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/EdicionEstadoBanco" styleId="formulario">
	<input type="hidden" id="accionInterna" name="accionInterna" />
	
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<html:errors />
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
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Modificar estados de bancos</strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
					<tr>
						<td width="200" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Banco</td>
						<td width="100" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					</tr>
					<nested:notEmpty property="lista">
						<nested:iterate id="fila" property="lista" indexId="nFila">
						<nested:define id="idBanco" property="idBanco" />
						<input type="hidden" name="idBanco${nFila}" id="idBanco${nFila}" value="<nested:write property="idBanco" />" />
						<c:choose>
	   			    		<c:when test="${nFila % 2 == 0}">
	   			    			<c:set var="estilo" value="textos-formcolor2"/>
	   		    			</c:when>
							<c:otherwise>
								<c:set var="estilo" value="textos_formularios"/>
							</c:otherwise>
						</c:choose>
							<tr>
								<td class="${estilo}" width="200">
									&nbsp;<nested:write property="nombre" />
								</td>
								<td class="${estilo}" width="100">
									<nested:equal property="estado" value="1">
										<input type="radio" id="estado${nFila}" name="estado${nFila}" value="1" checked/>ON
										<input type="radio" id="estado${nFila}" name="estado${nFila}" value="0" />OFF
									</nested:equal>
									<nested:equal property="estado" value="0">
										<input type="radio" id="estado${nFila}" name="estado${nFila}" value="1" />ON
										<input type="radio" id="estado${nFila}" name="estado${nFila}" value="0" checked/>OFF
									</nested:equal>
								</td>
							</tr>
						</nested:iterate>
						<input type="hidden" name="totalFila" id="totalFila" value="${nFila}" />
					</nested:notEmpty>
					<nested:empty property="lista">
						<tr>
							<td class=textos_formularios colspan="8">
								No hay bancos definidos.
							</td>
						</tr>
					</nested:empty>
				</table>
			</td>
		</tr>
		<tr>
  <td height="4" bgcolor="#85b4be" colspan="4"/>
</tr>
<tr>
  <td  colspan="4">&nbsp;</td>
  
</tr>
<tr>
	<td colspan="4" align="right" >
	<nested:notEmpty property="lista">
		<html:button property="operacion" styleClass="btn3" value="Aceptar" onclick="javascript:guardarEstado();"/>
	</nested:notEmpty>
		<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelarEstado();"/>
	</td>
</tr>
</table>
</html:form>
</body>
<script>
<!--
function guardarEstado()
{
	formu=document.getElementById('formulario');
	formu.accionInterna.value = 'GUARDAR';
	formu.submit();
}

function cancelarEstado()
{
	formu=document.getElementById('formulario');
	formu.accionInterna.value = 'CANCELAR';
	formu.submit();
}
// End --> 
</script>
</html:html>

