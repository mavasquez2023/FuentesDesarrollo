<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>
<html:form action="/ListarNominas" styleId="formulario" >
<input type="hidden" name="tipoNominaB" id="tipoNominaB"/>
<input type="hidden" name="empresaB" id="empresaB"/>
<input type="hidden" name="estadoB" id="estadoB"/>
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="procesos" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="" />
<bean:define id="bloqueoPagoLinea"><%=request.getAttribute("bloqueoPagoLinea")%></bean:define>
<bean:define id="bloqueoEdicionNom"><%=request.getAttribute("bloqueoEdicionNom")%></bean:define>
<bean:define id="bloqueoPagoCaja"><%=request.getAttribute("bloqueoPagoCaja")%></bean:define>
<nested:define id="hayPagables" property="hayPagables"/>
<c:set var="estilo" value="textos-formcolor2"/>
<c:set var="flgNominasEnProgreso" scope="page"><%=request.getAttribute("flgNominasEnProgreso")%></c:set>
<style> 
.div-datos {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px;
	color: #333333;
	padding: 4px;
	background-position: top;
}
</style>
<html:messages id="msg" message="true">
	<div class="msgExito">${msg}</div><br />
</html:messages>
<table width="100%" border="0" cellpadding="1" cellspacing="5" class="textos-formularios3">
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="titulos_formularios">
				<tr>
					<td colspan="2">
						<a href="javascript:;" onclick="swapAll('filtros', 'img');">
							<img id="img" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							B&uacute;squeda Avanzada
						</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				<tr id="filtros" style="display:none">
					<td colspan="2">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" class="titulos_formularios">
							<tr style="display:none;">
								<td align="left"><p>Tipo de N&oacute;mina:</p></td>
								<td align="center">
									<div align="left">
										<!--<html:select property="tipoNomina" styleId="tipoNomina" size="1" styleClass="campos">
											<html:optionsCollection property="tiposNomina" />
										</html:select> -->
										<select name="tipoNomina" id="tipoNomina">
											<option value="R" selected="selected">R</option>
										</select>
									</div>
								</td>
							</tr>
							<tr>
								<td align="left">Estados:</td>
								<td align="left">
									<html:select property="estado" styleId="estado" styleClass="campos">
										<html:option value="0">Todos</html:option>
										<html:optionsCollection property="estados" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left">Independiente:</td>
								<td align="left">
									<html:select property="empresa" styleId="empresa" styleClass="campos">
										<html:option value="0">Todas</html:option>
										<html:optionsCollection property="empresas" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td align="center" colspan="2">
									<html:submit styleClass="btn-destacado" value="${filtro}" onclick="javascript:buscar();" />
								</td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>

<br>

<table width="100%" border="0" cellpadding="1" cellspacing="5" class="textos-formularios3">
	<tr>
		<td>
		    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="titulos_formularios">
			    <tr>
			    	<td colspan="2">Opciones de Pago</td>
			    </tr>
			    <tr>
			    	<td colspan="2">&nbsp;</td>
			    </tr>
			    <tr>
			        <td align="center">
			        	<html:button property="operacion" value="${pagarSeleccionados}" styleClass="btn-destacado" onclick="javascript:sBtn='${pagarSeleccionados}'; javascript:pagarSeleccionados('');" />
			        </td>
				    <td align="center">
				    	<html:button property="operacion" value="${pagarTodos}" styleClass="btn-destacado" onclick="javascript:sBtn='${pagarTodos}'; javascript:pagarTodos('');" />
				    </td>
			    </tr>
			    <tr>
			    	<td colspan="2">&nbsp;</td>
			    </tr>	       
		    </table>
		</td>
	</tr>
</table>

<br />
<table width="100%">
	<tr>
		<td align="right">
			<html:button styleClass="btn-destacado" property="btnLinkCrearEmpresa" value="Crear Independiente" onclick="window.location.href='DetalleEmpresa.do?subSubAccion=empresaCrear&accion=admin&subAccion=empresas'"></html:button>
		</td>
	</tr>
</table>

<html:errors/>
<br>
<logic:notEmpty name="NominasForm" property="nominas">

<bean:define id="imgsrcs" name="NominasForm" property="imgsrcs" />
<bean:define id="tipoNomina"><nested:write property="tipoNomina" /><nested:empty property="tipoNomina">R</nested:empty></bean:define>
<div class="div-datos" style="display:none">
	Tipo N&oacute;mina :&nbsp;
	<logic:equal name="tipoNomina" value="R">REMUNERACI&Oacute;N</logic:equal>
	<logic:equal name="tipoNomina" value="A">RELIQUIDACI&Oacute;N</logic:equal>
	<logic:equal name="tipoNomina" value="G">GRATIFICACI&Oacute;N</logic:equal>
	<logic:equal name="tipoNomina" value="D">DEP&Oacute;SITO CONVENIDO</logic:equal>
	<c:choose>
		<c:when test="${flgNominasEnProgreso == 1}">
			<img id="imgActualizar" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Actualizar" style="cursor: pointer;">
			<span id="spanActualizar"></span>
		</c:when>
	</c:choose>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	<tr>
		<td align="center" bgcolor="#FFFFFF">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				<tr class="subtitulos_tablas" align="center" valign="middle">
					<td bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
					<td bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Rut</td>
					<td width="170" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					<td width="30" bordercolor="#FFFFFF" class="barra_tablas" style="display:none">Convenio</td>
					<logic:equal name="NominasForm" property="mostrarTotal" value="true">
						<td width="65" bordercolor="#FFFFFF" class="barra_tablas">Total $</td>
					</logic:equal>
					<td width="84" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
					<td width="150" bordercolor="#FFFFFF" class="barra_tablas" colspan="10">Acci&oacute;n</td>
				</tr>
				<logic:iterate id="nomina" name="NominasForm" property="nominas" type="cl.araucana.cp.distribuidor.hibernate.beans.NominaVO" indexId="count">
					<c:choose>
						<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/><c:set var="estilo2" value="tablaClaroBordes"/></c:when>
						<c:otherwise><c:set var="estilo" value="textos-formcolor2"/><c:set var="estilo2" value="tablaOscuroBordes"/></c:otherwise>
					</c:choose>
					<tr valign="middle">
						<td align="center" class="${estilo}" valign="middle">
							<logic:equal name="nomina" property="mostrarChkBox" value="true">
								<c:if test="${bloqueoPagoCaja == 0 || bloqueoPagoLinea == 0}" >
									<html:multibox property="chkBoxes" value="${nomina.idCodigoBarras}" />
								</c:if>
							</logic:equal>
							&nbsp;
						</td>
						<td height="20" style="text-align:right" class="${estilo}" nowrap="nowrap"><bean:write name="nomina" property="idformateado" /></td>
						<td class="${estilo}">${nomina.empresa.razonSocial}</td>
						<td class="${estilo}" nowrap style="display:none">${nomina.idConvenio}</td>
						<logic:equal name="NominasForm" property="mostrarTotal" value="true">
							<td class="${estilo}" style="text-align:right">
								<logic:empty name="nomina" property="monto">
									---
								</logic:empty>
								<logic:notEmpty name="nomina" property="monto">
									<bean:write name="nomina" format="$ ###,###,###" property="montoNum" />
								</logic:notEmpty>
							</td>
						</logic:equal>
						<td class="${estilo}">${nomina.estado.descripcion}</td>
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion1">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion1">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link href="sender.do" onclick="javascript:return validaUnzippedAdaptedSender(this);">
										<img align="middle" alt="Enviar n&oacute;mina" title="Enviar n&oacute;mina" border="0" src="<c:url value="${imgsrcs[0]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link onclick="alert('\n El plazo válido para enviar Nominas ha finalizado')" href="javascript:;">
										<img align="middle" alt="Fuera de plazo para enviar n&oacute;mina" title="Fuera de plazo para enviar n&oacute;mina" border="0" src="<c:url value="${imgsrcs[0]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion9">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion9">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link href="CrearNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaCrear&limpiaPath=">
										<img align="middle" alt="Crear n&oacute;mina en l&iacute;nea" title="Crear n&oacute;mina en l&iacute;nea" border="0" src="<c:url value="${imgsrcs[8]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link href="CrearNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaCrear&limpiaPath=" onclick="alert('\n El plazo válido para crear Nóminas en línea ha finalizado')">
										<img align="middle" alt="Crear n&oacute;mina en l&iacute;nea" title="Crear n&oacute;mina en l&iacute;nea" border="0" src="<c:url value="${imgsrcs[8]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion3">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion3">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link href="ListarCotizantes.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar">
										<img align="middle" alt="Corregir n&oacute;mina" title="Corregir n&oacute;mina" border="0" src="<c:url value="${imgsrcs[2]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link href="ListarCotizantes.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar" onclick="alert('\n El plazo válido para corregir Nominas ha finalizado')">
										<img align="middle" alt="Corregir n&oacute;mina" title="Corregir n&oacute;mina" border="0" src="<c:url value="${imgsrcs[2]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion3">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion3">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link target="_blank" href="ListarErroresNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=0">
										<img align="middle" alt="Listado Errores" title="Listado Errores" border="0" src="<c:url value="/img/alert-error.png" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link target="_blank" href="ListarErroresNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=0">
										<img align="middle" alt="Listado Errores" title="Listado Errores" border="0" src="<c:url value="/img/alert-error.png" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						<!-- nuevo -->
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion10">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion10">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link target="_blank" href="InformacionNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}">
										<img align="middle" alt="Informaci&oacute;n N&oacute;mina" title="Informacion Nomina" border="0" src="<c:url value="${imgsrcs[9]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link target="_blank" href="InformacionNomina.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}">
										<img align="middle" alt="Informaci&oacute;n N&oacute;mina" title="Informacion Nomina" border="0" src="<c:url value="${imgsrcs[9]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						 <td class="${estilo}" style="text-align:center; vertical-align: middle">
							<logic:notEmpty name="nomina" property="accion4">
								<c:if test="${bloqueoEdicionNom == 0}" >
											<html:link href="EditarCotizacion.do?idConvenio=${nomina.idConvenio}&rutEmpresa=${nomina.rutEmpresa}&idCotizante=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorEditar&nombre=${nomina.empresa.razonSocial}">
									  
										<img align="middle" alt="Editar n&oacute;mina" title="Editar Remuneraci&oacute;n" border="0" src="<c:url value="${imgsrcs[3]}" />" />
									</html:link>
								</c:if>
									<c:if test="${bloqueoEdicionNom == 1}" >
											<html:link action="/EditarCotizacion?rutEmpresa=${nomina.rutEmpresa}&idCotizante=${nomina.rutEmpresa}&idConvenio=${nomina.idConvenio}&idCotizante=${nomina.rutEmpresa}&tipoProceso=${tipoNomina}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorEditar" onclick="alert('\n El plazo válido para editar Nominas ha finalizado')">
										<img align="middle" alt="Editar n&oacute;mina" title="Editar Remuneraci&oacute;n" border="0" src="<c:url value="${imgsrcs[3]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
							<logic:notEmpty name="nomina" property="accion8">

										<html:link href="EditarCotizacion.do?rutEmpresa=${nomina.rutEmpresa}&idConvenio=${nomina.idConvenio}&idCotizante=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&operacion=mod&accion=inicio&subAccion=trabajadores&subSubAccion=trabajadorVer">
									<img align="middle" alt="Ver n&oacute;mina" title="Ver n&oacute;mina" border="0" src="<c:url value="${imgsrcs[7]}" />" />
								</html:link>
							</logic:notEmpty>
							&nbsp;
						</td>
						
					 	<!--<td class="${estilo}" style="text-align:center" style="display:none">
							<logic:empty name="nomina" property="accion5">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion5">
								<c:if test="${bloqueoEdicionNom == 0}" >
									<html:link href="EditarComprobante.do?accion=inicio&subAccion=procesos&subSubAccion=comprobanteEditar&codigoBarra=${nomina.idCodigoBarras}">
										<img align="middle" alt="Editar comprobante" title="Editar comprobante" border="0" src="<c:url value="${imgsrcs[4]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoEdicionNom == 1}" >
									<html:link href="EditarComprobante.do?accion=inicio&subAccion=procesos&subSubAccion=comprobanteEditar&codigoBarra=${nomina.idCodigoBarras}" onclick="alert('\n El plazo válido para editar comprobantes ha finalizado')">
										<img align="middle" alt="Editar comprobante" title="Editar comprobante" border="0" src="<c:url value="${imgsrcs[4]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>  
						
							<td class="${estilo}" style="text-align:center">
								<logic:empty name="nomina" property="accion6">
									&nbsp;
								</logic:empty>
								<logic:notEmpty name="nomina" property="accion6">
									<html:link href="DetalleComprobante.do?idCodigoBarras=${nomina.idCodigoBarras}&accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha">
										<img align="middle" alt="Detalle Comprobante" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
									</html:link>
								</logic:notEmpty>
							</td>
						-->
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion7">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion7">
								<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 0}" >
									<html:link href="DetalleComprobante.do?idCodigoBarras=${nomina.idCodigoBarras}&accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha">
										<img align="middle" alt="Pagar" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoPagoCaja == 1 && bloqueoPagoLinea == 0}" >
									<html:link href="DetalleComprobante.do?idCodigoBarras=${nomina.idCodigoBarras}&accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha" onclick="alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado, sólo podrá pagar en Línea.')">
										<img align="middle" alt="Pagar" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 1}" >
									 <html:link href="DetalleComprobante.do?idCodigoBarras=${nomina.idCodigoBarras}&accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha" onclick="alert('\n El plazo válido para pagar Comprobantes en Línea ha finalizado, sólo podrá pagar por Caja.')"> 
										<img align="middle" alt="Pagar" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
									</html:link>
								</c:if>
								<c:if test="${bloqueoPagoCaja == 1 && bloqueoPagoLinea == 1}" >
									<html:link onclick="alert('\n El plazo válido para pagar Comprobantes ha finalizado')" href="#">
										<img align="middle" alt="Fuera de plazo para pagar Comprobantes" title="Fuera de plazo para pagar Comprobantes" border="0" src="<c:url value="${imgsrcs[6]}" />" />
									</html:link>
								</c:if>
							</logic:notEmpty>
						</td>
						<td class="${estilo}" style="text-align:center">
							<logic:empty name="nomina" property="accion2">
								&nbsp;
							</logic:empty>
							<logic:notEmpty name="nomina" property="accion2">
								<html:link target="_blank" href="PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&operacion=PDF&codigoBarra=${nomina.idCodigoBarras}">
									<img align="middle" alt="Ver Comprobante Pagado" title="Ver Comprobante Pagado" border="0" src="<c:url value="${imgsrcs[1]}" />" />
								</html:link>
							</logic:notEmpty>
						</td>
					</tr>
				</logic:iterate>
			</table>
		</td>
	</tr>
	<bean:size id="nPags" name="NominasForm" property="numeroFilas"/>
	<c:if test="${nPags > 1}">
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center" valign="middle" class="numeracion">
							<logic:iterate id="paginacion" name="NominasForm" property="numeroFilas">
								${paginacion}
							</logic:iterate>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</c:if>
</table>
</logic:notEmpty>
</html:form>
<script language="javaScript">

	var sBtn = '';
	buscar();

	function validaFormulario(f) 
	{
		if ((${bloqueoPagoCaja} == 1) && (${bloqueoPagoLinea} == 1)) 
		{
			alert('\n El plazo válido para pagar Comprobantes ha finalizado');
			return false;
		} else 
		{
			if (sBtn == '${pagarSeleccionados}')
				if (validaChkBoxes()) 
				{
					if (${bloqueoPagoCaja} == 1)
						alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado, sólo podrá pagar en Línea.');
					else if (${bloqueoPagoLinea} == 1)
						alert('\n El plazo válido para pagar Comprobantes en Línea ha finalizado, sólo podrá pagar por Caja.');
					return true;
				} else 
				{
					alert("Debe seleccionar al menos un Comprobante");
					return false;				
				}
			else if (sBtn == '${pagarTodos}') 
			{
				if (hayComprobantePagable()) 
				{
					if (${bloqueoPagoCaja} == 1)
						alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado, sólo podrá pagar en Línea.');
					else if (${bloqueoPagoLinea} == 1)
						alert('\n El plazo válido para pagar Comprobantes en Línea ha finalizado, sólo podrá pagar por Caja.');
					return true;
				} else 
				{
					alert('\n No hay ningún comprobante Por Pagar.');
					return false;
				}
			} else
				return false;
		}
	}

	function validaChkBoxes() 
	{
		var chkBoxes = document.getElementsByName("chkBoxes");
		for (var i = 0; i < chkBoxes.length; i++) 
			if (chkBoxes[i].checked) 
				return true;
		return false;
	}
	
	function hayComprobantePagable() 
	{
		return ${hayPagables} == 1;
	}

function pagarSeleccionados(valor)
{
	if(validaFormulario(document.getElementById('formulario')))
	{
		var ruta='<%=request.getContextPath()%>';
		var codigos='';
		var chkBoxes = document.getElementsByName("chkBoxes");
			for (var i = 0; i < chkBoxes.length; i++) 
				if (chkBoxes[i].checked)
					codigos=codigos+'&codigos='+chkBoxes[i].value;

		ruta='PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&operacion=PAGAR+SELECCIONADOS'+codigos;
		formu = document.getElementById("formulario");
		formu.action = ruta;
		formu.submit();
	}
}

function pagarTodos(valor)
{
	if(validaFormulario(document.getElementById('formulario')))
	{
		var empresa=document.getElementById('empresaB').value;
		var tipo='';
		var estado='';
		if(empresa=='')
		{//no a hecho clic en buscar 
			empresa=document.getElementById('empresa').value;
			tipo=document.getElementById('tipoNomina').value;
			estado=document.getElementById('estado').value;
		} else
		{//clic en buscar
			tipo=document.getElementById('tipoNominaB').value;
			estado=document.getElementById('estadoB').value;
		}
		var ruta='PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&operacion=PAGAR+TODOS&empresa='+empresa+'&tipoNomina='+tipo+'&estado='+estado;
        formu = document.getElementById("formulario");
		formu.action = ruta;
		formu.submit();
	}
}

function buscar()
{
	document.getElementById('empresaB').value = document.getElementById('empresa').value;
	document.getElementById('tipoNominaB').value = document.getElementById('tipoNomina').value;
	document.getElementById('estadoB').value = document.getElementById('estado').value;
}

function retornaEnlace(paginacion)
{
	var tipoNomina = '${tipoNomina}';
	formu = document.getElementById("formulario");
	formu.action = "ListarNominas.do?accion=inicio&subAccion=procesos&limpiaPath=0&paginaNumero="+paginacion+"&tipoNomina="+tipoNomina;
	formu.submit();
}

function swapAll(id, imgId)
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display == '' )
    {
		obj.style.display='none';
		img.src   = '<c:url value="/img/ico_mas.gif" />';
		img.alt   = "Expandir";
		img.title = "Expandir";
	} else
	{
		obj.style.display = '';
		img.src   = '<c:url value="/img/ico_menos.gif" />';
		img.alt   = "Contraer";
		img.title = "Contraer";
	}
}


$(document).ready(function() {
	$('#imgActualizar').click(function() {
    	$('#imgActualizar').attr('src', '<c:url value="/img/loading_chico.gif" />');
    	$('#imgActualizar').attr('title', 'Actualizando...');
    	$('#spanActualizar').html("<i>Actualizando...</i>");
    	window.location.href = window.location.href;
	});
});

</script>