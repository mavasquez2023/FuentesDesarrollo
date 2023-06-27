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
	<TITLE>CMapeo Tesoreria</TITLE>
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
		var tipoSeccion = $("#selTipoSeccion").attr("value");
		var tipoNomina = $("#selTipoNomina").attr("value");
		if (tipoSeccion == 0 && tipoNomina == 0){
			alert("Debe seleccionar al menos una entidad de busqueda");
			return false;
		}
		window.location = "/AdminCotPrevWEB/MapeoTesoreriaListar.do?_tipoNomina="+tipoNomina+"&_tipoSeccion="+tipoSeccion+"&_cmd=search";
	});
});
</script>
</head>
<body>
<html:form action="/MapeoTesoreriaListar" styleId="formulario">

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
	           		<td><strong>Tipo Nomina:</strong></td>
	           		<td>
	           			<select name="selTipoNomina" id="selTipoNomina" class="campos">
					  		<option value="0">Seleccionar</option>
						  	<logic:iterate id="tipoNomina" name="listaTipoNomina" type="cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO">		  		
						  		<option value="<bean:write name='tipoNomina' property='idTipoNomina'/>"><bean:write name="tipoNomina" property="descripcion"/></option>
						  	</logic:iterate>
					  	</select>
					</td>
				</tr>	
				<tr>	
					<td><strong>Tipo Seccion:</strong></td>
					<td> 	
						<select name="selTipoSeccion" id="selTipoSeccion" class="campos">
					  		<option value="0">Seleccionar</option>
							 <logic:iterate id="tipoSeccion" name="listaTipoSeccion" type="cl.araucana.cp.distribuidor.hibernate.beans.TipoSeccionVO">
							  	<option value="<bean:write name='tipoSeccion' property='id'/>"><bean:write name="tipoSeccion" property="descripcion"/></option>
							  </logic:iterate>
						  </select>
					</td>
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
		<td valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Mapeo Tesorer&iacute;a</strong></td>
					<td align="right" bgcolor="#FFFFFF">
						<html:button property="operacion" styleClass="btn3" value="Crear Mapeo" onclick="javascript:addMapeo();"/>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr class="subtitulos_tablas" align="center" valign="middle">
					<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">N&oacute;mina</td>
					<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Tipo Secci&oacute;n</td>
					<td width="20%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Tipo Detalle</td>
					<td width="25%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Concepto</td>
					<td width="5%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Monto</td>
					<td width="10%" align="center" valign="middle"  bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
				</tr>
				<tr valign="top" align="center">
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
							<nested:notEmpty property="idNomina"><nested:define id="idNomina" property="idNomina"/></nested:notEmpty>
							<nested:notEmpty property="idTipoSeccion"><nested:define id="idTipoSeccion" property="idTipoSeccion"/></nested:notEmpty>
							<nested:notEmpty property="idTipoDetalle"><nested:define id="idTipoDetalle" property="idTipoDetalle"/></nested:notEmpty>
							<nested:notEmpty property="idConcepto"><nested:define id="idConcepto" property="idConcepto"/></nested:notEmpty>
							<nested:notEmpty property="valorMonto"><nested:define id="valorMonto" property="valorMonto"/></nested:notEmpty>
							<tr>
	                    		<td align="center" valign="middle"  class="${estilo}" width="20%">
	                    			&nbsp;<nested:write property="nomina" />
		                    	</td>
		                    	<td align="center" valign="middle"  class="${estilo}" width="20%">
	                    			&nbsp;<nested:write property="tipoSeccion" />
		                    	</td>
		                    	<td align="center" valign="middle"  class="${estilo}" width="20%">
	                    			&nbsp;<nested:write property="tipoDetalle" />
		                    	</td>
		                    	<td align="center" valign="middle"  class="${estilo}" width="25%">
	                    			&nbsp;<nested:write property="concepto" />
		                    	</td>
		                    	<td align="center" valign="middle"  class="${estilo}" width="5%">
	                    			&nbsp;<nested:write property="monto" />
		                    	</td>
		                    	<td align="center" class="${estilo}" width="10%">
									<div align="center">
										<a href="<c:url value='/MapeoTesoreriaEditar.do?idNominaSeleccionado=${idNomina}&idTipoSeccionSeleccionado=${idTipoSeccion}&idTipoDetalleSeleccionado=${idTipoDetalle}&idConceptoSeleccionado=${idConcepto}&valorMontoSeleccionado=${valorMonto}&accion=EDIT' />">
												<img src="<c:url value="/img/iconos/ico_hojap.gif" />" width="14" alt="Editar" title="Editar" height="13" border="0" />
										</a>
										<a href="javascript:delConfirmar('<c:url value='/MapeoTesoreriaEditar.do?idNominaSeleccionado=${idNomina}&idTipoSeccionSeleccionado=${idTipoSeccion}&idTipoDetalleSeleccionado=${idTipoDetalle}&idConceptoSeleccionado=${idConcepto}&valorMontoSeleccionado=${valorMonto}&accionInterna=DEL_MAPEO' />','<nested:write property="nomina" />','<nested:write property="concepto" />');">
											<img src="<c:url value="/img/iconos/icono_basurero.gif" />" width="16" alt="Eliminar" title="Eliminar" height="16" border="0" />
										</a>
									</div>
								</td>
	                  		</tr>
						</nested:iterate>
					</nested:notEmpty>
					<nested:empty property="lista">
						<tr>
							<td class=textos_formularios colspan="6">
								No hay datos definidos para este &iacute;tem
							</td>
						</tr>
					</nested:empty>
				</table>
			</td>
		</tr>
		<bean:size id="nPags" name="MapeoTesoreriaListarActionForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
		<logic:notEmpty name="_cmd"><input id="busqueda" type="hidden" value="<c:out value='${_cmd}'/>"></logic:notEmpty>
		<logic:empty name="_cmd"><input id="busqueda" type="hidden" value="0"></logic:empty>
			<tr> 
				<td align="center" valign="middle" class="numeracion">
					<logic:iterate id="paginacion" name="MapeoTesoreriaListarActionForm" property="numeroFilas">
						${paginacion}
					</logic:iterate>
				</td>
			</tr>
		</c:if>
	</table>
</html:form>

<script language="javaScript"> 
<!-- 
function addMapeo()
{
	formu = document.getElementById("formulario");	
	formu.action = 'MapeoTesoreriaEditar.do';
	formu.submit();
}										

function delConfirmar(url,txt,cpto)
{
	if (confirm("¿Esta seguro de que desea eliminar \n"+txt+" \n"+cpto+"?"))
		document.location = url;
} 
function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		var cmd = document.getElementById("busqueda").value;
		if (cmd == '0')
			formu.action = "MapeoTesoreriaListar.do?paginaNumero=" + paginacion;
		else
			formu.action = "MapeoTesoreriaListar.do?paginaNumero=" + paginacion+"&_cmd=search";
		formu.submit();
	}
// -->
</script>
</body>
</html:html>

