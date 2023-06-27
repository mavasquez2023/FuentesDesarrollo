<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>
<%@ taglib uri="/tags/araucana-gestorBecas" prefix="gesBecas" %>
	
<html:html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
	<table  width="100%">
				<tr>
					<th colspan="2"><bean:message key="label.informe.becasByFecha.titulo"/></th>
				</tr>
				<tr>
					<td> <bean:message key="label.informe.becasByFecha.fechaDesde"/>:  <bean:write name="fechaDesdeSession"/> </td>
					<td> <bean:message key="label.informe.becasByFecha.fechaHasta"/>: <bean:write name="fechaHastaSession"/></td>
				</tr>
				<tr>
					<td colspan="2"><bean:message key="label.informe.becasByFecha.beca"/>: <bean:write name="becaSession"/></td>
				</tr>
				<tr>
					<td colspan="2"><bean:message key="label.informe.becasByFecha.oficina"/>: <bean:write name="oficinaSession"/></td>
				</tr>
	</table>
   	<table width="100%" border="1">  
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
</body>
</html:html>