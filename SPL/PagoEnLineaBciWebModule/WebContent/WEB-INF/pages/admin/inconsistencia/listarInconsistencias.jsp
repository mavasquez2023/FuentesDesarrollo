<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page import="cl.araucana.spl.util.ResourceHelper"%>
<%@page import="com.bh.paginacion.HttpPageParameters"%>

<%@ page import="cl.araucana.spl.base.Constants" %>

<% ResourceHelper resources = ResourceHelper.getInstance(); %>


<script type="text/javascript" src="<c:url value="/js/calendar/calendar.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendar/calendar-setup.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendar/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/FormCheq.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-1.2.3.min.js" />"></script>
<script language="javaScript">
<!--
function limpiar(f) {
	var now = '<fmt:formatDate value="${dateNow}" pattern="${datepattern}" />';
	f.buscar.value = '';
	f.numeroPago.value = '';
	f.numeroFolio.value = '';
	f.fechaPagoDesde.value = now;
	f.fechaPagoHasta.value = now;
	f.fechaContableDesde.value = now;
	f.fechaContableHasta.value = now;

	// setValueSelect(f.estado, '<%= Constants.ESTADO_PAGO_INCONSISTENTE %>');
	f.estado.selectedIndex = 0;

	f.banco.selectedIndex = 0;
	f.sistema.selectedIndex = 0;
	
}
function buscarPagos(f) {
	if (!checkField(f['numeroPago'], isNumber, true, 'Verifique número de transacción')
		|| !checkField(f['numeroFolio'], isNumber, true, 'Verifique número de folio')) {
		return false;
	}
	if (getValueRadio(f['tipoFecha']) == 'P' && !validarRangoFechas(f['fechaPagoDesde'], f['fechaPagoHasta'])) {
		alert('Verifique rango de fechas');
		return false;
	}
	if (getValueRadio(f['tipoFecha']) == 'C' && !validarRangoFechas(f['fechaContableDesde'], f['fechaContableHasta'])) {
		alert('Verifique rango de fechas');
		return false;
	}
	f.buscar.value = '1';
	f.submit();
}

function validarRangoFechas(campoDesde, campoHasta) {
	var desde = parseFecha(campoDesde.value);
	var hasta = parseFecha(campoHasta.value);
	return fechaMenorIgual(desde, hasta);
}

function cambioFechaPagoContable(f) {
	var tipoFecha = getValueRadio(f['tipoFecha']);
	if (tipoFecha == 'C') {
		// contable
		$('#idSeccionFechaPago').hide();
		$('#idSeccionFechaContable').show();
	} else {
		// pago
		$('#idSeccionFechaPago').show();
		$('#idSeccionFechaContable').hide();
	}
}

$(document).ready(function() {
	$('a.pago').toggle(
		function() {
			var n = this.id.substring(1);
			$('#D'+n).load('verDetallePago.do?pago='+n).show();
		}, function() {
			var n = this.id.substring(1);
			$('#D'+n).hide();
		}
	);

	$('a.bitacora').click(function() {
		var n = this.id.substring(1);
		winopen('verBitacora.do?pago='+n, 'Bitacora', 500, 400);
		return false;
	});
	
	cambioFechaPagoContable(document.forms['inconsistenciasForm']);
	
	setupCalendar('idFechaPagoDesde', '${jscalendarpattern}', 'idImgFechaPagoDesde');
	setupCalendar('idFechaPagoHasta', '${jscalendarpattern}', 'idImgFechaPagoHasta');
	setupCalendar('idFechaContableDesde', '${jscalendarpattern}', 'idImgFechaContableDesde');
	setupCalendar('idFechaContableHasta', '${jscalendarpattern}', 'idImgFechaContableHasta');
	
	$('a.modInconsistencia').click(function() {
		var n = this.id.substring(1);
		winopen('modificarInconsistencia/frm.do?idPago='+n, 'Inconsistencia', 500, 400);
		return false;
	});	
	
	$('#botonbuscarpagos').click(function() {
		buscarPagos(this.form);
		return false;
	});
	document.location.href  = '#h';
});

//-->
</script>

<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Administraci&oacute;n de Inconsistencias</strong> </strong></td>             
            </tr>
          </table></td>
      </tr>
    </table>
  </td>
</tr>

<html:form action="/admin/listarInconsistencias" styleId="idFormInconsistencias">
	<html:hidden property="buscar" />

<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr> 
              <td align="left" class="textos-formularios" bgcolor="#FFFFFF">
              


            
   <table width="490" height="266" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td width="17" height="20"><html:img  page="/images/id_01.gif" width="17" height="24" /></td>

          <td width="454" class="bordes">&nbsp;</td>
          <td width="17" align="left"><html:img page="/images/id_03.gif" width="17" height="24" /></td>
        </tr>
        <tr> 
          <td height="216" class="bordesizq">&nbsp;</td>
          <td class="araucana"><table width="494" height="216" cellpadding="0" cellspacing="0">
                <tr> 
                  <td width="107" height="30" class="campo"><p>N&ordm; de transacci&oacute;n</p></td>
                  <td width="129">
                  	<html:text property="numeroPago" size="10" />
                  </td>

                  <td class="campo">N&ordm; de folio</td>
                  <td colspan="3"><html:text property="numeroFolio" size="10" /></td>
                </tr>
                <tr> 
                  <td colspan="4">&nbsp;</td>
                </tr>

                <tr> 
                  <td height="22" class="campo">Fecha</td>
                  <td colspan="3" class="campo">
                  	<html:radio property="tipoFecha" value="P" onclick="cambioFechaPagoContable(this.form)" />&nbsp;Pago
	                  &nbsp;&nbsp;&nbsp;
	                <html:radio property="tipoFecha" value="C" onclick="cambioFechaPagoContable(this.form)" />&nbsp;Contable
                  </td>
                </tr>

                <tr id="idSeccionFechaPago"> 
                  <td height="22" class="campo">Desde</td>
                  <td>
                  	<html:text property="fechaPagoDesde" size="10" readonly="true" styleId="idFechaPagoDesde" />
                  	&nbsp;<html:img page="/images/calendario.gif" title="Editar Fecha" border="0" styleId="idImgFechaPagoDesde" styleClass="pointer" />
                  </td>
                  <td class="campo">Hasta</td>
                  <td>
                  	<html:text property="fechaPagoHasta" size="10" readonly="true" styleId="idFechaPagoHasta" />
                  	&nbsp;<html:img page="/images/calendario.gif" title="Editar Fecha" border="0" styleId="idImgFechaPagoHasta" styleClass="pointer" />
                  </td>
                </tr>
                <tr id="idSeccionFechaContable" style="display: none">
                  <td height="22" class="campo">Desde</td>
                  <td>
                  	<html:text property="fechaContableDesde" size="10" readonly="true" styleId="idFechaContableDesde" />
                  	&nbsp;<html:img page="/images/calendario.gif" title="Editar Fecha" border="0" styleId="idImgFechaContableDesde" styleClass="pointer" />
                  </td>
                  <td class="campo">Hasta</td>
                  <td>
                  	<html:text property="fechaContableHasta" size="10" readonly="true" styleId="idFechaContableHasta" />
                  	&nbsp;<html:img page="/images/calendario.gif" title="Editar Fecha" border="0" styleId="idImgFechaContableHasta" styleClass="pointer" />
                  </td>
                </tr>


                <tr> 
                  <td class="textonegro">&nbsp;</td>
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr> 
                  <td height="5" class="campo">Estado</td>
                  <td colspan="3">
                  	<html:select property="estado" size="1" styleClass="textonegrointerior">
                  		<html:option value="">Seleccione</html:option>
                  		<html:optionsCollection property="estados" label="descripcion" value="id"  />
                  	</html:select>
                  </td>
                </tr>
                <tr> 
                  <td height="4">&nbsp;</td>
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr> 
                  <td height="27" class="campo">Banco</td>
                  <td>
                  	<html:select property="banco" size="1" styleClass="textonegrointerior">
                  		<html:optionsCollection property="bancos" label="descripcion" value="id"  />
                  	</html:select>
                   </td>
                  <td class="campo">Sistema origen</td>
                  <td>
                  	<html:select property="sistema" size="1" styleClass="textonegrointerior">
                  		<html:option value="">Seleccione</html:option>
                  		<html:optionsCollection property="sistemas" label="descripcion" value="id"  />
                  	</html:select>
                   </td>
                </tr>
                <tr><td colspan="4">&nbsp;</td></tr>
                <tr> 
                  <td height="27" class="campo">Transacciones a Considerar</td>
                  <td colspan="3">
                  	<html:select property="trxConsiderar" size="1" styleClass="textonegrointerior">
                  		<html:option value="todas">&nbsp;&nbsp;&nbsp;Todas</html:option>
                  		<html:option value="terminadas">&nbsp;&nbsp;&nbsp;Sólo operaciones terminadas</html:option>
                  	</html:select>
                   </td>
                </tr>
                <tr> 
                  <td height="18" colspan="4">&nbsp;</td>
                </tr>
              </table>

          <td class="bordesder">&nbsp;</td>
        </tr>
        <tr> 
          <td height="12" valign="top"><html:img page="/images/id_14.gif"  width="17" height="24" /></td>
          <td valign="top" class="bordeabajo">&nbsp;</td>
          <td align="left" valign="top"><html:img  page="/images/id_16.gif" width="17" height="24" /></td>
        </tr>
      </table>

        <table width="318" height="41" cellpadding="0" cellspacing="0"  align="center">
          <tr bgcolor="#FFFFFF">
          		<td>
	     		   	<span class="titulos_formularios"><strong>
			  			<input type="button" class="btn2" onclick="limpiar(this.form);" value="Limpiar" />
	            	</strong></span>
          		</td>
     		   <td height="25" align="right" >
     		   	<span class="titulos_formularios"><strong>
		  			<input type="submit" class="btn2" id="botonbuscarpagos" value="Buscar pagos" />
            	</strong></span>
		  		</td>
          </tr>
        </table>


<c:if test="${not empty inconsistenciasForm.buscar}">

		<table width="100%" align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
		    <td width="700" align="center" valign="top" bgcolor="#FFFFFF">
		   	  <table width="460"  align="center" cellpadding="0" cellspacing="0"  border="0">
		   	  <tr>
		   	    <td class="titulos_formularios">Resumen</td>
		      </tr>
		      <tr>
		        <td>
		          <table width="97%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" border="0">
		           <tr class="subtitulos_tablas">
				     <td width="60%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Detalle de la Transacci&oacute;n</td>
				     <td width="20%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Cantidad</td>
				     <td width="20%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Monto</td>             
		           </tr>
		           <tr>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="left">${inconsistenciasForm.resumen.medioPago.codigo}</td>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="right">${inconsistenciasForm.resumen.cantidadBanco}</td>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="right">$ <fmt:formatNumber value="${inconsistenciasForm.resumen.montoBanco}" /></td>
		           </tr>
		           <tr>
		             <td valign="middle" nowrap="nowrap" class="textos-formcolor2" align="left">Registros Pago en Linea</td>
		             <td valign="middle" nowrap="nowrap" class="textos-formcolor2" align="right">${inconsistenciasForm.resumen.cantidadPago}</td>
		             <td valign="middle" nowrap="nowrap" class="textos-formcolor2" align="right">$ <fmt:formatNumber value="${inconsistenciasForm.resumen.montoPago}" /></td>             
		           </tr>    
		           <tr>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="left">Descuadre</td>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="right">${inconsistenciasForm.resumen.cantidadDescuadre}</td>
		             <td valign="middle" nowrap="nowrap" class="textos_formularios" align="right">$ <fmt:formatNumber value="${inconsistenciasForm.resumen.montoDescuadre}" /></td> 
		           </tr>                  
		          </table>
		        </td>
		      </tr>
		      </table>
		    </td>     
		</tr>
		<tr>
		  <td><br/><br/></td>
		</tr>
		</table>

		<table width="700"  align="center" cellpadding="0" cellspacing="0">
		   	  <tr>
		   	    <td class="titulos_formularios">Transacciones</td>
		      </tr>
		      <tr>
		        <td>
		        <table width="100%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" border="0">
				   <tr class="subtitulos_tablas" valign="middle" nowrap="nowrap" align="center">
				   	<td class="barra_tablas" width="5%"><a name="h" id="h">N&ordm;</a></td>
				   	<td class="barra_tablas">Banco</td>
				   	<td class="barra_tablas">Sistema</td>
				   	<td class="barra_tablas">Pagador</td>
				   	<td class="barra_tablas">Estado</td>
				   	<td class="barra_tablas">&nbsp;</td>
				   	<td class="barra_tablas">Fecha Operaci&oacute;n</td>
				   	<td class="barra_tablas">Pagado</td>
				   	<td class="barra_tablas">Fecha Contable</td>
				   	<td class="barra_tablas">Monto</td>
				   	<td class="barra_tablas">Monto Rendici&oacute;n</td>
				   	<td class="barra_tablas">&nbsp;</td>
				   </tr>
				   
				   <c:forEach var="i" items="${inconsistenciasForm.inconsistencias}" varStatus="status">
				   <c:set var="SIN_VALOR"><%=resources.getProperty(Constants.SIN_VALOR)%></c:set>
				   <c:set var="fchTransaccion" value="${i.fechaTransaccion}"></c:set>
				   <c:set var="fchContable" value="${i.fechaContable}"></c:set>
				   <c:if test="${empty fchTransaccion}">
				   		<c:set var="fchContable" value="${SIN_VALOR}"></c:set>
				   		<c:set var="fchTransaccion" value="${SIN_VALOR}"></c:set>
				   </c:if>				   
				   
				   <c:choose>
				   	<c:when test="${status.index % 2 == 0}">
				   		<c:set var="estiloFila" value="textos_formularios" />
				   	</c:when>
				   	<c:otherwise>
				   		<c:set var="estiloFila" value="textos-formcolor2" />
				   	</c:otherwise>
				   </c:choose>
				   	<tr>
				   		<td class="${estiloFila}" align="center"><a id="P${i.idPago}" href="#" class="pago" title="Ver Folios">[${i.idPago}]</a></td>
				   		<td class="${estiloFila}" align="center">${i.medio}</td>
				   		<td class="${estiloFila}" align="center">${i.sistema}</td>
				   		<td class="${estiloFila}">&nbsp;${i.pagador}</td>
				   		<td class="${estiloFila}" id="ESTADO_CELDA${i.idPago}" nowrap="nowrap">
				   			<span id="ESTADO_NOMBRE${i.idPago}">${i.estado.descripcion}</span>
				   		</td>
				   		<td class="${estiloFila}" >
				   			<c:if test="${i.estadoCambiable}">
				   				<a id="E${i.idPago}" href="#" class="modInconsistencia" title="Modificar Estado">
				   				<html:img page="/images/ico_editar.jpg" width="16" height="16" border="0"/>
				   				</a>
				   			</c:if>
				   			<c:if test="${not i.estadoCambiable}">
					   			&nbsp;
				   			</c:if>
				   		</td>
				   		<td class="${estiloFila}" align="center" nowrap="nowrap">&nbsp;
				   			<c:choose>
				   				<c:when test="${fchTransaccion ne SIN_VALOR}">
				   					<fmt:formatDate value="${fchTransaccion}" pattern="${datetimepattern}" />
				   				</c:when>
				   				<c:otherwise>${fchTransaccion}</c:otherwise>
				   			</c:choose>
				   		</td>
				   		<td class="${estiloFila}" align="center">
				   			<span id="PAGADO_DESC${i.idPago}">
				   				<c:set var="pagadoAux" scope="request">${i.pagado}</c:set>
					     		<%=resources.getProperty(Constants.PAGO_PAGADO_AUX + (String) request.getAttribute("pagadoAux"))%>
					     	</span>
				   		</td>
				   		<td class="${estiloFila}" align="center" nowrap="nowrap">&nbsp;
				   			<c:choose>
				   				<c:when test="${fchContable ne SIN_VALOR}">
				   					<fmt:formatDate value="${fchContable}" pattern="${datepattern}" />
				   				</c:when>
				   				<c:otherwise>${fchContable}</c:otherwise>
				   			</c:choose>
				   		</td>
				   		<td class="${estiloFila}" align="right"><fmt:formatNumber value="${i.monto}" /></td>
				   		<td class="${estiloFila}" align="right"><fmt:formatNumber value="${i.montoRendicion}" />&nbsp;</td>


				   		<td class="${estiloFila}" align="center" nowrap="nowrap">
						   	&nbsp;
					   		<c:if test="${i.cantidadCambios == 0}">
					   			<c:set var="visibilidadBitacora" value="none" />
					   		</c:if>
					   		<c:if test="${i.cantidadCambios > 0}">
					   			<c:set var="visibilidadBitacora" value="''" />
					   		</c:if>
						   	<a id="B${i.idPago}" href="#" class="bitacora" style="display: ${visibilidadBitacora}" title="Ver Bit&aacute;cora">
						   	<html:img page="/images/ico_bitacora.jpg" width="16" height="16" border="0"/>
						   	</a>
				   		</td>
				   	</tr>
				   	<tr><td colspan="12"><div id="D${i.idPago}"></div></td></tr>
				   </c:forEach>
				   <c:if test="${not empty inconsistenciasForm.buscar and empty inconsistenciasForm.inconsistencias}">
				   	<tr class="textos_formularios">
				   		<td colspan="12">No se encontraron registros que cumplan con el criterio</td>
				   	</tr>
				   </c:if>
				   <tr>
				   		<td colspan="12">
						   <jsp:include page="/WEB-INF/pages/_commons/paginacion.jsp" />
						</td>
					</tr>
		        </table>
		        </td>
		      </tr>
		      <tr><td><br/><br/></td></tr>
		      </table>


</c:if>
              
			</td>             
            </tr>        
          </table>

<%
HttpPageParameters params = (HttpPageParameters) request.getAttribute("com.bh.paginacion.HttpPageParameters");
%>
<% if(params != null){ %>
<script language="JavaScript" type="text/javascript">
function <%= params.getJavascriptFuncionName() %>(form,offset) {
	form.<%= params.getOffsetName() %>.value = offset;
	form.submit();
}
</script>
<%}%>



        </td>
      </tr>
    </table>
  </td>
</tr>
</html:form>
     
</table>
