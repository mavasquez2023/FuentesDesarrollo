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
	<TITLE>Concepto de Tesoreria</TITLE>
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
<html:form action="/ConceptoTesoListar" styleId="formulario">

<input type="hidden" id="accionInterna" name="accionInterna"/>

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
		<td valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Concepto Tesorer&iacute;a</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<html:button property="operacion" styleClass="btn3" value="Nuevo Concepto" onclick="javascript:addConcepto();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Id Concepto</td>
					<td width="60%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Descripci&oacute;n</td>
					<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acciones</td>
				</tr>
				<tr>
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
							<nested:notEmpty property="id"><nested:define id="id" property="id"/></nested:notEmpty>
							<nested:notEmpty property="descripcion"><nested:define id="descripcion" property="descripcion"/></nested:notEmpty>
							<tr>
	                    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}" width="20%">
	                    			&nbsp;<nested:write property="id" />
		                    	</td>
		                    	<td align="center" valign="middle" nowrap="nowrap" class="${estilo}" width="60%">
	                    			&nbsp;<nested:write property="descripcion" />
		                    	</td>
		                    	<td align="center" class="${estilo}" width="20%">
									<div align="center">
										<a href="<c:url value='/ConceptoTesoEditar.do?idSeleccionado=${id}&descripcionSeleccionado=${descripcion}&accion=EDIT' />">
												<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
										</a>
										<a href="javascript:delConfirmar('<c:url value='/ConceptoTesoEditar.do?idSeleccionado=${id}&descripcionSeleccionado=${descripcion}&accionInterna=DEL_CONCEPTO' />', '${id}');">
											<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
										</a>
									</div>
								</td>
	                  		</tr>
						</nested:iterate>
					</nested:notEmpty>
					<nested:empty property="lista">
						<tr>
							<td class=textos_formularios colspan="3">
								No hay datos definidos para este &iacute;tem
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

function addConcepto(){
	formu = document.getElementById("formulario");	
	formu.action = 'ConceptoTesoEditar.do?accionInterna=NUEVO';
	formu.submit();
}										

function delConfirmar(url, id){
	if (confirm("¿Esta seguro de que desea eliminar el concepto \n"+id+"?")){
		document.location = url;
	} 
}
// -->
</script>
</body>
</html:html>

