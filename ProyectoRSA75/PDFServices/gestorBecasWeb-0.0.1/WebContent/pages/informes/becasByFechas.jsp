<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>
<%@ taglib uri="/tags/araucana-gestorBecas" prefix="gesBecas" %>

<link href="${contextRoot}/scripts/jscalendar/calendario.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/calendar.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/jscalendar/lang/calendar-es.js"></script>
<script type="text/javascript" src="${contextRoot}/scripts/calendario.js"></script>

<script language="javascript">



	function becasByFecha() {
		getComboListInforme('becas', $('#fechaDesde').val(),$('#fechaHasta').val(),'opcBeca','0');
	}
	function oficinasByFecha() {
		getComboListInforme('oficina', $('#fechaDesde').val(),$('#fechaHasta').val(),'opcOficina',$('#opcBeca').val());
	}
	
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
	function excelView(){		
		window.location= "${contextRoot}/informes/exportarExcel.do?_cmd=excel";
	}
</script>

<html:form action="/informes/buscarbecasByFecha">
	<html:hidden property="_flagValidar" value="true" />
	<html:hidden property="_cmd" value="resultado" />
	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.informe.becasByFecha.titulo"></bean:message></th>			
		</tr>
		<tr>
			<td><bean:message key="label.informe.becasByFecha.fechaDesde"></bean:message></td>
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
			<td><bean:message key="label.informe.becasByFecha.fechaHasta"></bean:message></td>
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
			<td><bean:message key="label.informe.becasByFecha.beca"></bean:message></td>
			<td colspan="3" align="left"><html:select property="opcBeca" styleId="opcBeca" onchange="oficinasByFecha();">
					<html:option value="">
						<bean:message key="label.common.seleccione.text" />
					</html:option>
				 </html:select>
			 </td>
		</tr>
		<tr>
			<td><bean:message key="label.informe.becasByFecha.oficina"></bean:message></td>
			<td colspan="3" align="left"><html:select property="opcOficina" styleId="opcOficina">
					<html:option value="">
						<bean:message key="label.common.seleccione.text" />
					</html:option>
				 </html:select>
			 </td>
		</tr>
		<gesBecas:tieneRol roles="administradorRole">
		<tr>
			<td><bean:message key="label.informe.becasByFecha.conEstadoFolio"></bean:message></td>
			<td>
				<a class="addToolTip" title='<bean:message key="help.becasByFecha.descripcion"/>'>
					<input type="radio" name="isEstadoFolio" value="1"><bean:message key="label.informe.becasByFecha.conEstadoFolio.si"></bean:message></input>
				</a>
				<input type="radio" name="isEstadoFolio" value="0" checked="checked"><bean:message key="label.informe.becasByFecha.conEstadoFolio.no"></bean:message></input>
			</td>
		</tr>
		</gesBecas:tieneRol>
		<gesBecas:tieneRol roles="ejecutivoRole">
		<tr>
			<td colspan="2"><html:hidden name="isEstadoFolio" property="isEstadoFolio" value="0"></html:hidden></td>
		</tr>
		</gesBecas:tieneRol>
		<tr>
			<td colspan="4" align="right"><html:submit property="btnBuscar" styleClass="button"><bean:message key="button.buscar"/></html:submit></td>
		</tr>
	</table>
<logic:notEmpty name="BecasByFechaVO">
		<div id=scrolltable style="overflow:auto;padding-right: 0px; padding-top: 0px; padding-left: 0px; padding-bottom: 0px;scrollbar-arrow-color : #A4B9CA; scrollbar-face-color : #F3F7F8;
			scrollbar-track-color :#EDF2F5 ;height:208px; left: 126px; top: 179; width: 590px"; align=center>
			<table  width="100%">
				<tr>
					<td align="left"> <bean:message key="label.informe.becasByFecha.fechaDesde"/>:  <bean:write name="fechaDesdeSession"/> </td>
					<td align="left"> <bean:message key="label.informe.becasByFecha.fechaHasta"/>: <bean:write name="fechaHastaSession"/></td>
				</tr>
				<tr>
					<td align="left" colspan="2"><bean:message key="label.informe.becasByFecha.beca"/>: <bean:write name="becaSession"/></td>
				</tr>
				<tr>
					<td align="left" colspan="2"><bean:message key="label.informe.becasByFecha.oficina"/>: <bean:write name="oficinaSession"/></td>
				</tr>
			</table>
			<table width="100%">
				<tr>
					<th><bean:message key="label.informe.becasByFecha.folioInscripcion"></bean:message></th>
					
					<th><bean:message key="label.informe.becasByFecha.rutAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.nombreAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.apellidoAfiliado"></bean:message></th>
					
					<th><bean:message key="label.informe.becasByFecha.rutPostulante"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.nombresPostulante"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.apellidosPostulante"></bean:message></th>
					
					<th><bean:message key="label.informe.becasByFecha.codigoBeca"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.descripcionBeca"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.nivelEducacional"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.nota"></bean:message></th>
					
					<th><bean:message key="label.informe.becasByFecha.idCurso"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.tipoCalificacion"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.montoBeca"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.premioEntregado"></bean:message></th>	
					<th><bean:message key="label.informe.becasByFecha.tipoPremio"></bean:message></th>					
					<th><bean:message key="label.informe.becasByFecha.tienePremio"></bean:message> </th>	
								
					<th><bean:message key="label.informe.becasByFecha.folioTesoreria"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.folioTesoreriaFinal"></bean:message></th>										
					<th><bean:message key="label.informe.becasByFecha.estadoOperacion"></bean:message></th>					
					
					<th><bean:message key="label.informe.becasByFecha.fechaInscripcion"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.rutUsuarioCrea"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.EjecutivoAtencion"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.creacionOfCodigo"></bean:message> </th>					
					<th><bean:message key="label.informe.becasByFecha.oficvinaIncripcion"></bean:message> </th>
					
					<th><bean:message key="label.informe.becasByFecha.fechaEntrega"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.usuarioEntrega"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.nomUsuEntrega"></bean:message> </th>
					
					
					<th><bean:message key="label.informe.becasByFecha.codOficinaEntrega"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.oficinaEntrega"></bean:message> </th>					
					
					
					<th><bean:message key="label.informe.becasByFecha.rutEmpresa"></bean:message> </th>
					<th><bean:message key="label.informe.becasByFecha.razonSocial"></bean:message> </th>
										
					<th><bean:message key="label.informe.becasByFecha.segmento"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.area"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.concepto"></bean:message></th>
					<th><bean:message key="label.informe.becasByFecha.antiguedad"></bean:message></th>
						
				</tr>
				<%int cont = 0;%>
				<logic:iterate id="beca" indexId="i" name="BecasByFechaVO">
					<tr	<%if(cont == 0){cont = 1;%>class="tr1"<%} else {cont = 0;%>valign=top<%}%>>
				
						<td nowrap="nowrap"><bean:write name="beca" property="folioInscripcion" /></td>
					
						<td nowrap="nowrap"><bean:write name="beca" property="rutAfiliado" />-<bean:write name="beca" property="dvAfiliado" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nombresAfiliado" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="apellidosAfiliado" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="rutPostulante" />-<bean:write name="beca" property="dvPostulante" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nombresPostulante" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="apellidosPostulantes" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="codigoBeca" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="descripcionBeca" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nivelEstudio" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nota" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="idCurso" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="tipoCalificacion" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="premio" /></td>
						<td nowrap="nowrap">
							<logic:equal name="beca" property="premioEntregado" value="true">
								<bean:message key="label.informe.becasByFecha.premioEntregado.si"></bean:message>
							</logic:equal>
							<logic:equal name="beca" property="premioEntregado" value="false">
								<bean:message key="label.informe.becasByFecha.premioEntregado.no"></bean:message>
							</logic:equal>
						</td>
						<td nowrap="nowrap"><bean:write name="beca" property="tipoPremio" /></td>						
						<td nowrap="nowrap"><bean:write name="beca" property="tienePremio" /></td>
												
						<td nowrap="nowrap"><bean:write name="beca" property="folioTesoreria" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="folioTesoreriaFinal" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="estadoFolioTesoreria" /></td>
						
						
						<td nowrap="nowrap"><bean:write name="beca" property="fechaIncripcion" formatKey="format.date"/></td>
						<td nowrap="nowrap"><bean:write name="beca" property="rutUsuarioCrea" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="ejecutivoAtencion" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="codOficina" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="oficinaInscripcion" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="fechaEntrega" formatKey="format.date"/></td>
						<td nowrap="nowrap"><bean:write name="beca" property="usuarioEntrega" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nombreUsuarioEntrega" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="codOficinaEntrega" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="oficinaEntrega" /></td>						
						
						
						<td nowrap="nowrap"><bean:write name="beca" property="rutEmpresa" />-<bean:write name="beca" property="dvRutEmpresa" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="razonSocial" /></td>
						
						<td nowrap="nowrap"><bean:write name="beca" property="segmento" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="area" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="concepto" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="antiguedad" /></td>
						
					</tr>
				</logic:iterate>
			</table>
		</div>
		<table width="100%">
		<tr>
			<td align="center">
  					 <a onclick="javascript:excelView();setTimeout('hideLoading();',3000);" href="#">
  							 <img src="${contextRoot}/images/excel.jpg" />
  					 </a>
 			</td>	
		</tr>
	</table>
</logic:notEmpty>
</html:form>