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
	f.fechaPagoDesde.value = now;
	f.fechaPagoHasta.value = now;

	// setValueSelect(f.estado, '<%= Constants.ESTADO_PAGO_INCONSISTENTE %>');
	
	f.banco.selectedIndex = 0;
}
function buscarPagos(f) {
	if (!checkField(f['numeroPago'], isNumber, true, 'Verifique número de transacción')) {
		return false;
	}
	if (!validarRangoFechas(f['fechaPagoDesde'], f['fechaPagoHasta'])) {
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

function abrir(url) { 
	open(url,'popup','top=100,left=100,width=500,height=250') ; 
} 

function pagarPago(idPago) {
	document.forms[0].action='pagarPagos.do?idPago='+idPago;
	document.forms[0].target = "popup";
	document.forms[0].submit();	
				
}
$(document).ready(function() {

	setupCalendar('idFechaPagoDesde', '${jscalendarpattern}', 'idImgFechaPagoDesde');
	setupCalendar('idFechaPagoHasta', '${jscalendarpattern}', 'idImgFechaPagoHasta');
	
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
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Concluir Pagos</strong> </strong></td>             
            </tr>
          </table></td>
      </tr>
    </table>
  </td>
</tr>

<html:form action="/admin/listarPagos" styleId="idFormConcluirPago">
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
                  <td width="107" height="30" class="campo">Id Pago</td>
                  <td width="129">
                  	<html:text property="numeroPago" size="10" />
                  </td>

                  <td class="campo"></td>
                  <td colspan="3"></td>
                </tr>
                <tr> 
                  <td height="22" class="campo">Fecha Pago</td>
                  <td colspan="3" class="campo">                  	
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
                <tr> 
                  <td height="4">&nbsp;</td>
                  <td colspan="3">&nbsp;</td>
                </tr>
                <tr> 
                  <td height="27" class="campo">Banco</td>
                  <td>
                  	<html:select property="banco" size="1" styleClass="textonegrointerior" styleId="banco">
                  		<html:optionsCollection property="bancos" label="descripcion" value="id"  />
                  	</html:select>
                   </td>
                  <td class="campo"></td>
                  <td>
                  	
                   </td>
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


<c:if test="${not empty concluirPagoForm.buscar}">

		<table width="700"  align="center" cellpadding="0" cellspacing="0">
		   	  <tr>
		   	    <td class="titulos_formularios">Pagos</td>
		      </tr>
		      <tr>
		        <td>
		        <table width="100%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" border="0">
				   <tr class="subtitulos_tablas" valign="middle" nowrap="nowrap" align="center">
				   	<td class="barra_tablas" width="10%">Id Pago</td>
				   	<td class="barra_tablas" width="10%">Pagador</td>
				   	<td class="barra_tablas" width="10%">Id Contrato</td>
				   	<td class="barra_tablas" width="10%">Id Transacci&oacute;n</td>
				   	<td class="barra_tablas" width="10%">Total</td>
				   	<td class="barra_tablas" width="10%">Numeros de Pagos</td>
				   	<td class="barra_tablas" width="10%">Fecha Inicio</td>
				   	<td class="barra_tablas" width="20%">Glosa</td>
				   	<td class="barra_tablas" width="20%">&nbsp;</td>
				   </tr>
				   
				   <c:forEach var="i" items="${concluirPagoForm.concluirPago}" varStatus="status">
				   	 <c:choose>
				   	<c:when test="${status.index % 2 == 0}">
				   		<c:set var="estiloFila" value="textos_formularios" />
				   	</c:when>
				   	<c:otherwise>
				   		<c:set var="estiloFila" value="textos-formcolor2" />
				   	</c:otherwise>
				   </c:choose>
				   	
				   	<tr>
				   		<td class="${estiloFila}">${i.idPago}</td>
				   		<td class="${estiloFila}">${i.pagador}</td>
				   		<td class="${estiloFila}">
				   			<c:if test="${not empty i.id_Contrato}">
				   				${i.id_Contrato}
				   			</c:if>
				   			<c:if test="${empty i.id_Contrato}">
				   				-
				   			</c:if>
				   		</td>
				   		<td class="${estiloFila}">
				   			<c:if test="${not empty i.cod_idtransaccion}">
				   				${i.cod_idtransaccion}
				   			</c:if>
				   			<c:if test="${empty i.cod_idtransaccion}">
				   				-
					   		</c:if>
				   		</td>
						<td class="${estiloFila}">${i.total}</td>
						<td class="${estiloFila}">
							<c:if test="${not empty i.nro_pagos}">
				   				${i.nro_pagos}
				   			</c:if>
				   			<c:if test="${empty i.nro_pagos}">
				   				-
				   			</c:if>
						</td>
						<td class="${estiloFila}" width="5px"><fmt:formatDate value="${i.fch_inicio}" pattern="${datetimepattern}" /></td>
						<td class="${estiloFila}">${i.glosa}</td>
						<td class="${estiloFila}">
						<input type="button" name="concluirPago" onclick="javascript:location.href='../admin/pagarPagos.do?idPago=${i.idPago}'" value="Concluir Pago">
				   	</tr>
				   </c:forEach>
				   <c:if test="${not empty concluirPagoForm.buscar and empty concluirPagoForm.concluirPago}">
				   	<tr class="textos_formularios">
				   		<td colspan="12">No se encontraron registros que cumplan con el criterio</td>
				   	</tr>
				   </c:if>
		        </table>
		        </td>
		      </tr>
		      <tr><td><br/><br/></td></tr>
		      </table>
</c:if>
			</td>             
            </tr>        
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
</html:form>
</table>
