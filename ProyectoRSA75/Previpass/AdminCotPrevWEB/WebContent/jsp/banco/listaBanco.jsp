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
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/ListaBanco" styleId="formulario">

<input type="hidden" id="accionInterna" name="accionInterna"/>

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
		<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Bancos</strong></td>
					<td align="right">
						<html:button property="operacion" styleClass="btn3" value="Crear Banco" onclick="javascript:addBanco();"/>
						<html:button property="operacion" styleClass="btn3" value="Modificar Estados" onclick="javascript:editEstado();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="20%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> RUT</td>
					<td width="50%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Cod. SPL</td>
					<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					<td width="10%" align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Acciones</td>
				</tr>
				<nested:notEmpty property="lista">
					<nested:iterate id="fila" property="lista" indexId="nFila">
					<c:choose>
   			    		<c:when test="${nFila % 2 == 0}">
   			    			<c:set var="estilo" value="textos-formcolor2"/>
   		    			</c:when>
						<c:otherwise>
							<c:set var="estilo" value="textos_formularios"/>
						</c:otherwise>
					</c:choose>
					<nested:define id="idBanco" property="idBanco" />
						<tr>
							<td class="${estilo}">
								&nbsp;<nested:write property="rutBanco" />
							</td>
							<td class="${estilo}">
								&nbsp;<nested:write property="nombre" />
								<input type="hidden" name="nombreAux" id="nombreAux_${idBanco}" value="<nested:write property="rutBanco" />">
							</td>
							<td class="${estilo}">
								<nested:equal property="codSpl" value="-1">
									Sin SPL
								</nested:equal>
								<nested:notEqual property="codSpl" value="-1">
									<nested:write property="codSpl" />
								</nested:notEqual>&nbsp;
							</td>
							<td class="${estilo}">
								<nested:equal property="estado" value="1">
									ON
								</nested:equal>
								<nested:equal property="estado" value="0">
									OFF
								</nested:equal>
							</td>
							<td class="${estilo}" align="center">
								<div align="center">
									<a href="<c:url value='/EdicionBanco.do?idBanco=${idBanco}&tipoEdicion=ACTUALIZA' />">
										<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
									</a>
									<a href="javascript:delConfirmar('${idBanco}');">
										<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
									</a>
								</div>
							</td>
						</tr>
					</nested:iterate>
				</nested:notEmpty>
				<nested:empty property="lista">
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
</html:form>
<script language="javaScript"> 
<!--
function addBanco(){
	formu = document.getElementById("formulario");	
	formu.action = 'EdicionBanco.do?tipoEdicion=NUEVO&id=-1';
	formu.submit();
}										
function editEstado(){
	formu = document.getElementById("formulario");	
	formu.action = 'EdicionEstadoBanco.do?tipoEdicion=ACTUALIZA';
	formu.submit();
}

function delConfirmar(id) {
	var url = '';
	var nombre = document.getElementById('nombreAux_' + id);
	if (nombre != null) {
		nombre = trim(nombre.value);
	}
	
	url = "<c:url value='/EdicionBanco.do?idBanco=" + id + "&accionInterna=DEL_BANCO' />";
	if (confirm("¿Está seguro que desea eliminar el Banco " + nombre + " ?")){
		document.location = url;
	} 
} 
// -->
</script>
</body>
</html:html>

