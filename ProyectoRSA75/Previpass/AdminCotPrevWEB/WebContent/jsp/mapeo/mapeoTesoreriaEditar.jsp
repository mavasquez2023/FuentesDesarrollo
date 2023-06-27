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
	<TITLE>Concepto Tesoreria</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
</head>
<body onLoad="foco();">
<html:form action="/MapeoTesoreriaEditar" styleId="formulario" onsubmit="return onFormSubmit(this)">

<input type="hidden" id="accionInterna" name="accionInterna"/>

<input type="hidden" id="idNominaActual" name="idNominaActual" value="<nested:write property="idNominaActual" />" />
<input type="hidden" id="idTipoSeccionActual" name="idTipoSeccionActual" value="<nested:write property="idTipoSeccionActual" />" />
<input type="hidden" id="idTipoDetalleActual" name="idTipoDetalleActual" value="<nested:write property="idTipoDetalleActual" />" />
<input type="hidden" id="idConceptoActual" name="idConceptoActual" value="<nested:write property="idConceptoActual" />" />
<input type="hidden" id="valorMontoActual" name="valorMontoActual" value="<nested:write property="valorMontoActual" />" />

<input type="hidden" id="accion" name="accion" value="<nested:write property="accion" />" />

<input type="hidden" id="idNominaSeleccionado" name="idNominaSeleccionado" />
<input type="hidden" id="idTipoSeccionSeleccionado" name="idTipoSeccionSeleccionado" />
<input type="hidden" id="idTipoDetalleSeleccionado" name="idTipoDetalleSeleccionado" />
<input type="hidden" id="idConceptoSeleccionado" name="idConceptoSeleccionado" />
<input type="hidden" id="valorMontoSeleccionado" name="valorMontoSeleccionado" />

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
			<tr>
				<td colspan="4" height="30" bgcolor="#FFFFFF" class="titulo">
					<strong>Mapeo Tesorer&iacute;a</strong>
				</td>
			</tr>
			<tr>
				<nested:notEmpty property="idNomina">
					<td width="100" class="textos_formcolor">
						<strong>N&oacute;mina:</strong>
					</td>	
					<nested:define id="idNomina" property="idNomina" />
					<td width="190" class="textos_formularios">
						<select id="nomina" name="nomina" onchange="javascript:cambiaSeccion(this);" class="campos">						
		               		<nested:iterate id="fila" property="listaNomina" indexId="nFila">
		               			<nested:equal property="idTipoNomina" value="${idNomina}">
			               			<option value="<nested:write property="idTipoNomina"/>" selected><nested:write property="descripcion" /></option>
		               			</nested:equal>
		               			<nested:notEqual property="idTipoNomina" value="${idNomina}">
			               			<option value="<nested:write property="idTipoNomina"/>"><nested:write property="descripcion" /></option>
			               		</nested:notEqual>
			               	</nested:iterate>
						</select>			
					</td>
				</nested:notEmpty>
				<nested:notEmpty property="idTipoSeccion">
					<td width="100" class="textos_formcolor">
						<strong>Tipo Secci&oacute;n:</strong>
					</td>	
					<nested:define id="idTipoSeccion" property="idTipoSeccion" />
					<td width="190" class="textos_formularios">

						<select id="seccion" name="seccion" onchange="javascript:cambiaDetalle(this);" class="campos">						
		               		<nested:iterate id="fila" property="listaSeccion" indexId="nFila">
		               			<nested:define id="id" property="id"/>
		               			<c:choose>
			               			<c:when test="${id >= 0 && id < 20 && idNomina == 'R'}">
				               			<nested:equal property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>" selected><nested:write property="descripcion" /></option>
				               			</nested:equal>
				               			<nested:notEqual property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>"><nested:write property="descripcion" /></option>
					               		</nested:notEqual>
					               	</c:when>
					               	<c:when test="${id >= 20 && id < 40 && idNomina == 'A'}">
				               			<nested:equal property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>" selected><nested:write property="descripcion" /></option>
				               			</nested:equal>
				               			<nested:notEqual property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>"><nested:write property="descripcion" /></option>
					               		</nested:notEqual>
					               	</c:when>
					               	<c:when test="${id >= 40 && id < 60 && idNomina == 'G'}">
				               			<nested:equal property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>" selected><nested:write property="descripcion" /></option>
				               			</nested:equal>
				               			<nested:notEqual property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>"><nested:write property="descripcion" /></option>
					               		</nested:notEqual>
					               	</c:when>
					             	<c:when test="${id >= 60 && id < 80 && idNomina == 'D'}">
				               			<nested:equal property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>" selected><nested:write property="descripcion" /></option>
				               			</nested:equal>
				               			<nested:notEqual property="id" value="${idTipoSeccion}">
					               			<option value="<nested:write property="id"/>"><nested:write property="descripcion" /></option>
					               		</nested:notEqual>
					               	</c:when>
					        	</c:choose>
			               	</nested:iterate>
						</select>			
					</td>
				</nested:notEmpty>
			</tr>
			<tr>
				<nested:notEmpty property="idTipoDetalle">
					<td width="100" class="textos_formcolor">
						<strong>Tipo Detalle:</strong>
					</td>	
					<nested:define id="idTipoDetalle" property="idTipoDetalle" />
					<td width="190" class="textos_formularios">
						<select id="detalle" name="detalle" onchange="javascript:cambiaMonto(this);" class="campos">						
		               		<nested:iterate id="fila" property="listaDetalle" indexId="nFila">
		               			<nested:equal property="idDetalleSeccion" value="${idTipoDetalle}">
			               			<option value="<nested:write property="idDetalleSeccion"/>" selected><nested:write property="nombre" /></option>
		               			</nested:equal>
		               			<nested:notEqual property="idDetalleSeccion" value="${idTipoDetalle}">
			               			<option value="<nested:write property="idDetalleSeccion"/>"><nested:write property="nombre" /></option>
			               		</nested:notEqual>
			               	</nested:iterate>
			               	<nested:empty property="listaDetalle">
			               		<option value="-1" selected>-Sin Tipos-</option>
			               	</nested:empty>
						</select>			
					</td>
				</nested:notEmpty>
				<nested:notEmpty property="idConcepto">
					<td width="100" class="textos_formcolor">
						<strong>Concepto:</strong>
					</td>	
					<nested:define id="idConcepto" property="idConcepto" />
					<td width="190" class="textos_formularios">
						<select id="concepto" name="concepto" class="campos">						
		               		<nested:iterate id="fila" property="listaConcepto" indexId="nFila">
		               			<nested:equal property="id" value="${idConcepto}">
			               			<option value="<nested:write property="id"/>" selected><nested:write property="descripcion" /></option>
		               			</nested:equal>
		               			<nested:notEqual property="id" value="${idConcepto}">
			               			<option value="<nested:write property="id"/>"><nested:write property="descripcion" /></option>
			               		</nested:notEqual>
			               	</nested:iterate>
						</select>			
					</td>
				</nested:notEmpty>
			</tr>
			<tr>
				<nested:notEmpty property="valorMonto">
					<td width="100" class="textos_formcolor">
						<strong>Monto:</strong>
					</td>	
					<nested:define id="valorMonto" property="valorMonto" />
					<td width="190" class="textos_formularios" colspan="3">
						<select id="monto" name="monto" class="campos">						
		               		<nested:iterate id="fila" property="listaMonto" indexId="nFila">
		               			<nested:equal property="idMonto" value="${valorMonto}">
			               			<option value="<nested:write property="idMonto"/>" selected><nested:write property="monto" /></option>
		               			</nested:equal>
		               			<nested:notEqual property="idMonto" value="${valorMonto}">
			               			<option value="<nested:write property="idMonto"/>"><nested:write property="monto" /></option>
			               		</nested:notEqual>
			               	</nested:iterate>
			               	<nested:empty property="listaMonto">
			               		<option value="-1" selected>-Sin Montos-</option>
			               	</nested:empty>
						</select>			
					</td>
				</nested:notEmpty>
							
			</tr>
			<tr>
					<td height="4" bgcolor="#85b4be" colspan="4"> </td>
					</tr>
					<tr>
					<td colspan="4"> &nbsp; </td>
					</tr>
			
			<tr>
				<td align="center" colspan="4">
					<div align="center">
						<nested:notEmpty property="listaNomina">
							<nested:notEmpty property="listaSeccion">
								<nested:notEmpty property="listaDetalle">
									<nested:notEmpty property="listaConcepto">
										<nested:notEmpty property="listaMonto">
											<html:button property="operacion" styleClass="btn3" value="Guardar" onclick="javascript:save();"/>
										</nested:notEmpty>
									</nested:notEmpty>
								</nested:notEmpty>
							</nested:notEmpty>
						</nested:notEmpty>
						<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelar();"/>
					</div>
				</td>
       		</tr>
		</table>
	</td>
</tr>
</table>
<script type="text/javascript">
<!--
	function modifica(id)
	{
		document.getElementById('modificar'+id).value = '1';
	}
	function cancelar()
	{
		frm = document.forms['MapeoTesoreriaEditarActionForm'];
		frm.accionInterna.value = 'CANCELAR';
		frm.submit();
	}
	function save()
	{
		frm = document.forms['MapeoTesoreriaEditarActionForm'];
		frm.accionInterna.value = 'GUARDAR';
		
		for(a=0; a<frm.nomina.length; a++){
			if(frm.nomina[a].selected == true)
				document.getElementById("idNominaSeleccionado").value = frm.nomina[a].value;
		}
		for(a=0; a < frm.seccion.length; a++)
		{
			if(frm.seccion[a].selected == true)
				document.getElementById("idTipoSeccionSeleccionado").value = frm.seccion[a].value;
		}
		for(a=0; a<frm.detalle.length; a++)
		{
			if(frm.detalle[a].selected == true)
				document.getElementById("idTipoDetalleSeleccionado").value = frm.detalle[a].value;
		}
		for(a=0; a<frm.concepto.length; a++)
		{
			if(frm.concepto[a].selected == true)
				document.getElementById("idConceptoSeleccionado").value = frm.concepto[a].value;
		}
		 for(a=0; a<frm.monto.length; a++)
		 {
			if(frm.monto[a].selected == true)
				document.getElementById("valorMontoSeleccionado").value = frm.monto[a].value;
		}				
		frm.submit();
	}
	
	function cambiaSeccion(obj)
	{
		frm = document.forms['MapeoTesoreriaEditarActionForm'];
		document.getElementById("idNominaSeleccionado").value = obj.value;
		document.getElementById("idTipoSeccionSeleccionado").value = 1;
		//document.getElementById("idTipoDetalleSeleccionado").value = 1;
		document.getElementById("idConceptoSeleccionado").value = frm.concepto.value;
		document.getElementById("valorMontoSeleccionado").value = frm.monto.value;
		frm.submit();
	}
	
	function cambiaDetalle(obj)
	{
		frm = document.forms['MapeoTesoreriaEditarActionForm'];		
		document.getElementById("idNominaSeleccionado").value = frm.nomina.value;
		document.getElementById("idTipoSeccionSeleccionado").value = obj.value;
		//document.getElementById("idTipoDetalleSeleccionado").value = 1;
		document.getElementById("idConceptoSeleccionado").value = frm.concepto.value;
		document.getElementById("valorMontoSeleccionado").value = frm.monto.value;
		frm.submit();
	}
	
	function cambiaMonto(obj)
	{
		frm = document.forms['MapeoTesoreriaEditarActionForm'];
		document.getElementById("idNominaSeleccionado").value = frm.nomina.value;
		document.getElementById("idTipoSeccionSeleccionado").value = frm.seccion.value
		document.getElementById("idTipoDetalleSeleccionado").value = obj.value;
		document.getElementById("idConceptoSeleccionado").value = frm.concepto.value;
		document.getElementById("valorMontoSeleccionado").value = frm.monto.value;
		frm.submit();
	}
	function foco()
	{
	
			 document.getElementById('nomina').focus();
	}
//-->
</script>
</html:form>
</body>
</html:html>
