<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>
<html:form action="/ConsultaNominas" styleId="formulario" >
<input type="hidden" name="tipoNominaB" id="tipoNominaB"/>
<input type="hidden" name="empresaB" id="empresaB"/>
<input type="hidden" name="tipoNominaAux" value="<%=request.getAttribute("tipoNominaRespaldo")%>"/>
<%--input type="hidden" name="estadoB" id="estadoB" value="3"/> <!-- POR PAGAR --%>
<html:hidden styleId="accion"    property="accion"    name="accion"    value="cargas" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="cargaNomina" />

<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="" />
<bean:define id="bloqueoPagoLinea"><%=request.getAttribute("bloqueoPagoLinea")%></bean:define>
<bean:define id="bloqueoEdicionNom"><%=request.getAttribute("bloqueoEdicionNom")%></bean:define>
<bean:define id="bloqueoPagoCaja"><%=request.getAttribute("bloqueoPagoCaja")%></bean:define>
<nested:define id="hayPagables" property="hayPagables"/>
<c:set var="estilo" value="textos-formcolor2"/>

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
<logic:equal name="ConsultaNominasForm" property="mostrarValidar" value="true">
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
								<tr>
									<td align="left"><p>Tipo de N&oacute;mina:</p></td>
									<td align="center">
										<div align="left">
											<html:select property="tipoNomina" styleId="tipoNomina" size="1" styleClass="campos">
												<html:optionsCollection property="tiposNomina" />
											</html:select>
										</div>
									</td>
								</tr>
								<tr>
									<td align="left">Empresa:</td>
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

	<html:errors/>
	<br>
</logic:equal>


<logic:notEmpty name="ConsultaNominasForm" property="nominas">

	<bean:define id="imgsrcs" name="ConsultaNominasForm" property="imgsrcs" />
	<bean:define id="tipoNomina"><nested:write property="tipoNomina" /><nested:empty property="tipoNomina">R</nested:empty></bean:define>
	<div class="div-datos">
		Tipo N&oacute;mina :&nbsp;
		<logic:equal name="tipoNomina" value="R">REMUNERACI&Oacute;N</logic:equal>
		<logic:equal name="tipoNomina" value="A">RELIQUIDACI&Oacute;N</logic:equal>
		<logic:equal name="tipoNomina" value="G">GRATIFICACI&Oacute;N</logic:equal>
		<logic:equal name="tipoNomina" value="D">DEP&Oacute;SITO CONVENIDO</logic:equal>
		<c:choose>
			<c:when test="${flgNominasEnProgreso == 1}">
				<logic:equal name="ConsultaNominasForm" property="mostrarValidar" value="true">
					<img id="imgActualizar" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Actualizar" style="cursor: pointer;">
				</logic:equal>
				<logic:notEqual name="ConsultaNominasForm" property="mostrarValidar" value="true">
					<img id="imgActualizarAction" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Actualizar" style="cursor: pointer;">
				</logic:notEqual>
				<span id="spanActualizar"></span>
			</c:when>
		</c:choose>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
	  <tr>
	    <td align="center" bgcolor="#FFFFFF">
	    <c:set var="contadorNominas"value="0"/>
	    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	        <tr class="subtitulos_tablas" align="center" valign="middle">
	          <%--td bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td--%>
	          <td bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Rut</td>
	          <td width="170" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
		      <td width="30" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
	          <logic:equal name="ConsultaNominasForm" property="mostrarTotal" value="true">
		          <td width="65" bordercolor="#FFFFFF" class="barra_tablas">Total $ </td>
	          </logic:equal>
	          <td width="84" bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
	          <td width="150" bordercolor="#FFFFFF" class="barra_tablas" colspan="10">Acci&oacute;n</td>
	        </tr>
	        <logic:iterate id="nomina" name="ConsultaNominasForm" property="nominas" type="cl.araucana.cp.distribuidor.hibernate.beans.NominaVO" indexId="count">
	          <c:choose>
	   		    <c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/><c:set var="estilo2" value="tablaClaroBordes"/></c:when>
	   			<c:otherwise><c:set var="estilo" value="textos-formcolor2"/><c:set var="estilo2" value="tablaOscuroBordes"/></c:otherwise>
	   		  </c:choose>
	          <tr valign="middle">
	           	<%--td align="center" class="${estilo}" valign="middle">
		          	<logic:equal name="nomina" property="mostrarChkBox" value="true">
		          		<c:if test="${bloqueoPagoCaja == 0 || bloqueoPagoLinea == 0}" >
		          			<c:set var="contadorNominas" value="${contadorNominas+1}"/>
							<html:multibox property="chkBoxes" value="${nomina.rutEmpresa};${nomina.idConvenio}" />
						</c:if>
		            </logic:equal>&nbsp;
	           	</td--%>
	            <td height="20" style="text-align:right" class="${estilo}" nowrap="nowrap"><bean:write name="nomina" property="idformateado" /></td>
	            <td class="${estilo}">${nomina.empresa.razonSocial}</td>
		        <td class="${estilo}" nowrap  style="text-align:right">${nomina.idConvenio}</td>
				<logic:equal name="ConsultaNominasForm" property="mostrarTotal" value="true">
		            <td class="${estilo}" style="text-align:right">
		            	<logic:empty name="nomina" property="monto">---</logic:empty>
		            	<logic:notEmpty name="nomina" property="monto">
	    	        		<bean:write name="nomina" format="$ ###,###,###" property="montoNum" />
		            	</logic:notEmpty>
		            </td>
	            </logic:equal>
	            <td class="${estilo}">${nomina.estado.descripcion}</td>
	            <td class="${estilo}" style="text-align:center">
	            	<logic:empty name="nomina" property="accion1">&nbsp;</logic:empty>
	            	<logic:notEmpty name="nomina" property="accion1">
	            		<c:if test="${bloqueoEdicionNom == 0}" >
							<%--html:link href="sender.do" onclick="javascript:return validaSender(this);"--%>
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
	            	<logic:empty name="nomina" property="accion9">&nbsp;</logic:empty>
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
	            	<logic:empty name="nomina" property="accion3">&nbsp;</logic:empty>
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
	
	            <%-- A DIFERENCIA DE resumen.jsp ACÁ EL "ACCION3" SE USA PARA LISTAR LOS AVISOS DE CARGAS FAMILIARES --%>
	            <td class="${estilo}" style="text-align:center">
		           	<logic:empty name="nomina" property="accion3">&nbsp;</logic:empty>
		           	<logic:notEmpty name="nomina" property="accion3">
	 	           		<c:if test="${bloqueoEdicionNom == 0}" >
							<html:link target="_blank" href="ListarAvisoCargas.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1">
							                                 
		            			<img align="middle" alt="Listado Avisos" title="Listado Avisos" border="0" src="<c:url value="/img/alert.png" />" />
							</html:link>
						</c:if>
		            	<c:if test="${bloqueoEdicionNom == 1}" >
							<html:link target="_blank" href="ListarAvisos.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1">
		            			<img align="middle" alt="Listado Avisos" title="Listado Avisos" border="0" src="<c:url value="/img/alert.png" />" />
							</html:link>
						</c:if>
					</logic:notEmpty>
	            </td>
	
	            <%-- A DIFERENCIA DE resumen.jsp ACÁ EL "ACCION10" SE USA PARA INDICAR LAS NÓMINAS CON LAS VALIDACIONES CORRECTAS --%>
	            <td class="${estilo}" style="text-align:center">
	            	<logic:empty name="nomina" property="accion10">&nbsp;</logic:empty>
	            	<logic:notEmpty name="nomina" property="accion10">
            			<img align="middle" alt="Sin errores" title="Sin avisos" border="0" src="<c:url value='/img/ok-icon.png' />" />
					</logic:notEmpty>
	            </td>
	            <td class="${estilo}" style="text-align:center; vertical-align: middle">
	            	<logic:notEmpty name="nomina" property="accion4">
	            		<c:if test="${bloqueoEdicionNom == 0}" >
							<html:link href="ListarCotizantes.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar">
		            			<img align="middle" alt="Editar n&oacute;mina" title="Editar n&oacute;mina" border="0" src="<c:url value="${imgsrcs[3]}" />" />
							</html:link>
						</c:if>
						<c:if test="${bloqueoEdicionNom == 1}" >
							<html:link href="ListarCotizantes.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaEditar" onclick="alert('\n El plazo válido para editar Nominas ha finalizado')">
		            			<img align="middle" alt="Editar n&oacute;mina" title="Editar n&oacute;mina" border="0" src="<c:url value="${imgsrcs[3]}" />" />
							</html:link>
						</c:if>
	            	</logic:notEmpty>
	            	<logic:notEmpty name="nomina" property="accion8">
						<html:link href="ListarCotizantes.do?idConvenio=${nomina.idConvenio}&idEmpresa=${nomina.rutEmpresa}&tipoNomina=${tipoNomina}&accion=inicio&subAccion=trabajadores&subSubAccion=nominaVer">
	            			<img align="middle" alt="Ver n&oacute;mina" title="Ver n&oacute;mina" border="0" src="<c:url value="${imgsrcs[7]}" />" />
						</html:link>
	            	</logic:notEmpty>&nbsp;
	            </td>
	            <td class="${estilo}" style="text-align:center">
	            	<logic:empty name="nomina" property="accion5">&nbsp;</logic:empty>
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
	            	<logic:empty name="nomina" property="accion6">&nbsp;</logic:empty>
	            	<logic:notEmpty name="nomina" property="accion6">
						<html:link href="DetalleComprobante.do?idCodigoBarras=${nomina.idCodigoBarras}&accion=inicio&subAccion=procesos&subSubAccion=comprobanteFicha">
	            			<img align="middle" alt="Detalle Comprobante" title="Detalle Comprobante" border="0" src="<c:url value="${imgsrcs[5]}" />" />
						</html:link>
	            	</logic:notEmpty>
	            </td>
	            <td class="${estilo}" style="text-align:center">
	            	<logic:empty name="nomina" property="accion7">&nbsp;</logic:empty>
	            	<logic:notEmpty name="nomina" property="accion7">
	            		<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 0}" >
							<html:link href="PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&codigoBarra=${nomina.idCodigoBarras}">
		            			<img align="middle" alt="Pagar" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
							</html:link>
						</c:if>
	            		<c:if test="${bloqueoPagoCaja == 1 && bloqueoPagoLinea == 0}" >
							<html:link href="PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&codigoBarra=${nomina.idCodigoBarras}" onclick="alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado, sólo podrá pagar en Línea.')">
		            			<img align="middle" alt="Pagar" title="Pagar" border="0" src="<c:url value="${imgsrcs[6]}" />" />
							</html:link>
						</c:if>
	            		<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 1}" >
							<html:link href="PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&codigoBarra=${nomina.idCodigoBarras}" onclick="alert('\n El plazo válido para pagar Comprobantes en Línea ha finalizado, sólo podrá pagar por Caja.')">
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
	            	<logic:empty name="nomina" property="accion2">&nbsp;</logic:empty>
	            	<logic:notEmpty name="nomina" property="accion2">
						<html:link href="PagarComprobantes.do?accion=inicio&subAccion=procesos&subSubAccion=pagarNomina&operacion=PDF&codigoBarra=${nomina.idCodigoBarras}">
	            			<img align="middle" alt="Ver Comprobante Pagado" title="Ver Comprobante Pagado" border="0" src="<c:url value="${imgsrcs[1]}" />" />
						</html:link>
	            	</logic:notEmpty>
	            </td>
	          </tr>
	        </logic:iterate>
	      </table>
	      </td>
		</tr>
		<%--bean:size id="nPags" name="ConsultaNominasForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
	  		<tr>
	  			<td>
	  			<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center" valign="middle" class="numeracion">
							<logic:iterate id="paginacion" name="ConsultaNominasForm" property="numeroFilas">
								${paginacion}
							</logic:iterate>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</c:if--%>
	</table>
<%-->c:if test="${contadorNominas > 0}">
	<logic:equal name="ConsultaNominasForm" property="mostrarValidar" value="true">
		<br><center><INPUT id="btnValidar" class="btn3" onclick="javascript: validarNominas();" value="Validar" type="button"></center>
	</logic:equal>
</c:if--%>
</logic:notEmpty>
</html:form>
<script language="javaScript">
	<logic:equal name="ConsultaNominasForm" property="mostrarValidar" value="true">
	buscar();
	</logic:equal>

	function validaFormulario(f) {
		if (!validaChkBoxes()) {
			alert("Debe seleccionar al menos una Nómina");
			return false;
		} else {
			return true;
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

	function buscar()
	{
		document.getElementById('empresaB').value = document.getElementById('empresa').value;
		document.getElementById('tipoNominaB').value = document.getElementById('tipoNomina').value;
	}

	function retornaEnlace(paginacion)
	{
		var tipoNomina = '${tipoNomina}';
		formu = document.getElementById("formulario");
		formu.action = "ConsultaNominas.do?accion=cargas&subAccion=cfValidacion&limpiaPath=0&paginaNumero="+paginacion+"&tipoNomina="+tipoNomina;
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


	function validarNominas() {
		if (validaFormulario(document.getElementById('formulario'))) {
			var ruta = '<%=request.getContextPath()%>';
			var codigos = '';
			var chkBoxes = document.getElementsByName("chkBoxes");
			for (var i = 0; i < chkBoxes.length; i++)
				if (chkBoxes[i].checked)
					codigos=codigos+'&codigos='+chkBoxes[i].value;

			ruta = 'ValidarNominasCargas.do?accion=cargas&subAccion=cfValidacion&tipoNomina=${tipoNomina}'+codigos;
			formu = document.getElementById("formulario");
			formu.action = ruta;
			formu.submit();
		}
	}

	$(document).ready(function() {
		$('#imgActualizar').click(function() {
	    	$('#imgActualizar').attr('src', '<c:url value="/img/loading_chico.gif" />');
	    	$('#imgActualizar').attr('title', 'Actualizando...');
	    	$('#spanActualizar').html("<i>Actualizando...</i>");
	    	window.location.href = window.location.href;
		});
		
		
		$('#imgActualizarAction').click(function() {
	    	$('#imgActualizarAction').attr('src', '<c:url value="/img/loading_chico.gif" />');
	    	$('#imgActualizarAction').attr('title', 'Actualizando...');
	    	$('#spanActualizar').html("<i>Actualizando...</i>");
	    	//var url = jsContextRoot + "CompruebaNominasCargass.do";
	    	var url = "CompruebaNominasCargas.do";
			$('#formulario').attr("action",url); 
			$('#formulario').submit();
		});		
	});
</script>