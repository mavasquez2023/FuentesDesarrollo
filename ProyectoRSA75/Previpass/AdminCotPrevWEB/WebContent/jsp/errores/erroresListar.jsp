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
	<TITLE>Clasificaci&oacute;n de Errores</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	
	<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript">
$(document).ready(function() {
    $("#botonEnviar").click(function() {
		var descripcion = $("#descripcion").attr("value");
		if (descripcion == ""){
			alert("Debe ingresar una descripcion a buscar");
			return false;
		}else{
			window.location = "/AdminCotPrevWEB/ErroresListar.do?_descripcion="+descripcion+"&_cmd=search";
		}
	});
});
</script>
<script language="JavaScript">
    function conMayusculas(field) {
            field.value = field.value.toUpperCase()
}
</script>
</head>
<body>
<html:form action="/ErroresListar" styleId="formulario">

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
		<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="0" style="font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 10px; color: #333333; background-color: #e1ebed;">	         	
				<tr> 
	           		<td><strong>Descripci&oacute;n:</strong></td>
	           		<td><input type="text" id="descripcion" maxlength="30" size="45" class="campos" onkeyup="conMayusculas(this);"/></td>
	         	</tr>
	         	<tr> 
				    <td align="center" colspan="2"><input type="button" id="botonEnviar" class="btn3" value="Buscar"/></td>    	
				</tr>
	         	<tr> 
	           		<td height="4" colspan="2" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Clasificaci&oacute;n de Errores</strong></td>
				</tr>
			</table>
		</td>
	</tr>
		
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr>
					<td width="350" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripci&oacute;n</td>
					<td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Tipo Causa</td>
					<td width="60" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Corregible</td>
					<td width="80" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr>
					<td colspan="9">
						<table border="0" cellpadding="0" cellspacing="0" width="100%">
							<nested:notEmpty property="listaErrores">
								<nested:iterate id="fila" property="listaErrores" indexId="nFila">
									<c:choose>
					   		    		<c:when test="${nFila % 2 == 0}">
					   		    			<c:set var="estilo" value="textos-formcolor2"/>
					   		    		</c:when>
	   									<c:otherwise>
	   										<c:set var="estilo" value="textos_formularios"/>
	   									</c:otherwise>
									</c:choose>
									<tr>
										<nested:notEqual property="id" value="0">
										<nested:define property="id" id="id" />
				                    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}" width="350">
				                    			&nbsp;<nested:write property="descripcion" />
				                    		</td>
				                    		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}" width="60">
				                    			<nested:equal property="error" value="0">
				                    				&nbsp;Error	
				                    			</nested:equal>
				                    			<nested:equal property="error" value="1">
				                    				&nbsp;Aviso	
				                    			</nested:equal>
				                    			<nested:equal property="error" value="2">
				                    				&nbsp;No Validar	
				                    			</nested:equal>
				                    		</td>
				                    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}" width="60">
				                    			<nested:equal property="corregible" value="1">
				                    				&nbsp;SI	
				                    			</nested:equal>
				                    			<nested:equal property="corregible" value="0">
				                    				&nbsp;NO	
				                    			</nested:equal>
				                    		</td>
				                       		<td width="80" class="${estilo}" align="center">
												<div align="center">
													<a href="<c:url value='/ErroresEditar.do?id=${id}&tipoEdicion=ACTUALIZA' />">
															<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
													</a>
												</div>
											</td>
										</nested:notEqual>
			                  		</tr>
								</nested:iterate>
							</nested:notEmpty>
							<nested:empty property="listaErrores">
								<tr>
									<td class=textos_formularios colspan="8">
										No hay Errores definidos para este &iacute;tem
									</td>
								</tr>
							</nested:empty>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
	
	<td>
	<!-- Insertando Paginacion -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<bean:size id="nPags" name="ErroresListarActionForm" property="numeroFilas"/>
					<c:if test="${nPags > 1}">
					<logic:notEmpty name="_cmd"><input id="busqueda" type="hidden" value="<c:out value='${_cmd}'/>"></logic:notEmpty>
					<logic:empty name="_cmd"><input id="busqueda" type="hidden" value="0"></logic:empty>
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr> 
									<td align="center" valign="middle" class="numeracion">
										<logic:iterate id="paginacion" name="ErroresListarActionForm" property="numeroFilas">
											${paginacion}
										</logic:iterate>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</c:if>
				</tr>
			</table>
	</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
function addError(){
	formu = document.getElementById("formulario");	
	formu.action = 'ErroresEditar.do?tipoEdicion=NUEVO';
	formu.submit();
}										

function delConfirmar(url,txt){
	
	if (confirm('¿Esta seguro de que desea eliminar \n'+txt+'?')){
		document.location = url;
	} 
} 
function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		var cmd = document.getElementById("busqueda").value;
		if (cmd == '0')
			formu.action = "ErroresListar.do?paginaNumero=" + paginacion;
		else
			formu.action = "ErroresListar.do?paginaNumero=" + paginacion+"&_cmd=search";
		formu.submit();
	}
// -->
</script>
</body>
</html:html>

