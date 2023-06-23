<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${contextRoot}/scripts/jscalendar/calendario.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/calendar.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/calendario.js"></script>

<script language="javascript">
var rol= "${rolUsuario}";
	function muestraCalendar(id1, id2, showMore)
	{
		var f = new Date();
		
		var formato = "%d-%m-%Y";
		var mes = f.getMonth() +1;
		var anno = f.getFullYear();
		showCalendar(id1, id2, formato, showMore, mes, anno);
			
	}
	function limpiaFecha(idCajaFecha)
	{
		document.getElementById(idCajaFecha).value = "";
	}
	
	function buscarComprobantes() {
		var oficina = $('#oficina').val();
		var oficinas = $('#_oficinas').val();	
		var fechaDesde = $('#fechaDesde').val();
		var fechaDesdeArray = fechaDesde.split("-");
		var now = new Date();
		var fd = new Date();
		if(oficina=="" && rol=="EJECUTIVO" ){
			alert("Debe ingresar una Oficina para la búsqueda");
			return false;
		}
	    fd.setFullYear(parseInt(fechaDesdeArray[2])); //año
	    fd.setMonth(parseInt(fechaDesdeArray[1])); // mes
	    fd.setDate(parseInt(fechaDesdeArray[0])); // dia
	    var diff = now.getTime() - fd.getTime();
	    var days = diff / 1000 / 60 / 60 / 24;
	    if(days > 30) {
	    	alert("Busquedas sobre 30 dias podrian tomar una cantidad de tiempo considerable en entregar resultados");
	    }
    
    		if(oficinas!=null){
				var oficinasArray = oficinas.split(',');
				jQuery.each(oficinasArray, function( i, val ) {
					$("#reporte_seleccion_" + val).attr('checked', false);				
				});
			}
			jQuery('#oficinasSeleccionadas').remove();
		return true;
	}
	
	function descargarReportes(){		
		var oficinas = $('#_oficinas').val();	
		var oficinasArray = oficinas.split(',');
		var oficinasSeleccionadas = '';
		jQuery.each(oficinasArray, function( i, val ) {
			if($("#reporte_seleccion_" + val).is(':checked')){
				//alert("reporte_seleccion_" + val + " checked");
				oficinasSeleccionadas += val + ',';
			}
		});
				
		if(oficinasSeleccionadas.length == 0) {
			alert("Debe seleccionar una o más oficinas para descargar reporte Excel");
			return false;
		}
		
		$('<input>').attr({
		    type: 'hidden',
		    id: 'oficinasSeleccionadas',
		    name: 'oficinasSeleccionadas',
		    value: oficinasSeleccionadas
		}).appendTo('form');
		
		return true;
	}
	
	//permite key presses de solo números.
	function keyNum() {
		if (!isKeyNum())
			event.returnValue = false;
	}
	//valida si tecla presionada es número
	function isKeyNum() {
		if (event.keyCode >= 48 && event.keyCode <= 57)
			return true;
		else
			return false;
	}
</script>

<!-- script type="text/javascript">
$(function(){
	var date = new Date();
			var day = date.getDate();
			var monthIndex = date.getMonth() + 1;
			var year = date.getFullYear();

			if (day < 10)
				day = "0" + day;
			if (monthIndex < 10)
				monthIndex = "0"+monthIndex;
			
			$('#fechaHasta').val(String(day)+"-"+String(monthIndex)+"-"+String(year));
});
</script-->

<script type="text/javascript">
   $(document).ready(function() {
     $('#download').click(function() {
    	var e = $(this);
    	var url = "/ReportesTesoreriaWeb/informes/exportarExcel.do?_cmd=excel&oficina=" + e.attr('name');
   		$.get(url, function( data ) {
		  $( ".result" ).html( data );
		  //	alert( "get was performed." );
		});
     });    
   });    
</script>

<html:form action="/informes/buscarComprobanteIngreso">
	<html:hidden property="_flagValidar" value="true" />
	<html:hidden property="_cmd" value="resultado" />

	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="title.menu.posicion1.pagina"></bean:message></th>			
		</tr>
		<tr>
			<td><bean:message key="label.oficina"></bean:message></td>
			<td><input type="text" name="oficina" id="oficina" maxlength="4" onkeypress="keyNum()"  />
				
			</td>
		</tr>
		<tr>	
			<td><bean:message key="label.fechaDesde"></bean:message></td>
			<td><html:text property="fechaDesde" styleId="fechaDesde" readonly="true" size="15"></html:text>
				
				<a href="#" id="idFechaDesde">
				<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
				     onClick="limpiaFecha('fechaDesde');muestraCalendar('fechaDesde', 'idFechaDesde', true);return false;"/>
				</a>
					 </a>
						<a href="#" title="Limpia Fecha">
					<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaDesde')" title="Limpia Fecha"/>
				</a>
			</td>
			<td><bean:message key="label.fechaHasta"></bean:message></td>
			<td><html:text property="fechaHasta" styleId="fechaHasta" readonly="true" size="15"></html:text>
				<a href="#" id="idFechaHaste">
					<img src="${contextRoot}/images/ico_calendario.gif" width="11" height="10" border="0"
					     onClick="limpiaFecha('fechaHasta');muestraCalendar('fechaHasta', 'idFechaHaste', true);return false;"/>
				</a>
					<a href="#" title="Limpia Fecha">
				<img src="${contextRoot}/images/icono_basurero.gif"  width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaHasta')" title="Limpia Fecha"/>
			</a>
			</td>
		</tr>
		
		
		
		<tr>
			<td colspan="4" align="right"><html:submit property="btnBuscar" styleClass="button" onclick="return buscarComprobantes()"><bean:message key="button.buscar"/></html:submit></td>
		</tr>
	</table>
	
<logic:notEmpty name="ReporteComprobantes">
		
	    <input type="hidden" id="_oficinas" name="_oficinas" value="<bean:write name="oficinas"/>"/>
			
		<div id=scrolltable>
			<table  width="100%">
				<tr>
					<td align="left" colspan="2"> <bean:message key="label.oficina"/>: ${oficinaDesdeSession} </td>
				</tr>
				<tr>
					<td align="left"> <bean:message key="label.fechaDesde"/>: <bean:write name="fechaDesdeSession"/> </td>
					<td align="left"> <bean:message key="label.fechaHasta"/>: <bean:write name="fechaHastaSession"/></td>
				</tr>

			</table>
			<table width="100%">
				<tr>				
					<th>Código Oficina</th>
					<th>Nombre Oficina</th>
					<th>Comprobantes</th>
					<th>Monto</th>
					<th>Selección</th>
					<th>Descarga</th>
				</tr>
				<%int cont = 0;%>
				<logic:iterate id="reporte" indexId="i" name="ReporteComprobantes">
					<tr	<%if(cont == 0){cont = 1;%>class="tr1"<%} else {cont = 0;%>valign=top<%}%>>
						<td nowrap="nowrap" align="center"><bean:write name="reporte" property="oficinaCodigo" /></td>
						<td nowrap="nowrap" align="center"><bean:write name="reporte" property="oficinaNombre" /></td>
						<td nowrap="nowrap" align="center"><bean:write name="reporte" property="comprobantesNumero" /></td>					
						<td nowrap="nowrap" align="right"><fmt:formatNumber currencySymbol="$" value="${reporte.comprobantesMonto}" maxFractionDigits="0" type="currency" /></td>
						<td nowrap="nowrap" align="center"><input type="checkbox" name="reporte_seleccion_${reporte.oficinaCodigo}" value="false" id="reporte_seleccion_${reporte.oficinaCodigo}"/></td>
						<td nowrap="nowrap" align="center"> 
						<a href="/ReportesTesoreriaWeb/informes/exportarExcel.do?_cmd=excel&oficina=${reporte.oficinaCodigo}" id="download_${reporte.oficinaCodigo}" name="download_${reporte.oficinaCodigo}"/>
							<img src="${contextRoot}/images/xls_icon.png" width="24" height="24" /></td>
						</a>
					</tr>
				</logic:iterate>
			</table>
		</div>
		<table width="100%">
		<tr>
			<td align="right">
			
			<html:submit property="btnDescargarTodos" styleClass="button" onclick="return descargarReportes()"><bean:message key="button.descargar"/></html:submit>

 			</td>	
		</tr>
	</table>
</logic:notEmpty>
</html:form>
