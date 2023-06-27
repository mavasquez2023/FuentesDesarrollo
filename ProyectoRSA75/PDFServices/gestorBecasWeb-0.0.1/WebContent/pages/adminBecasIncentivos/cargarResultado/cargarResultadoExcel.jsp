<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>
	
<html:html>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<body>
	<table  width="100%">
				<tr>
					<td align="left" colspan="2"><bean:message key="label.administracion.carga.resultado.beca"/>: <bean:write name="becaSession"/></td>
				</tr>
				<tr>
					<td align="left" colspan="2"></td>
				</tr>
	</table>
   	<table width="100%" border="1">
				<tr>
					<th><bean:message key="label.informe.carga.resultado.folioInscripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.rutAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nombreAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.apellidoAfiliado"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.rutPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nombresPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.apellidosPostulante"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.codigoBeca"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.descripcionBeca"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.segmento"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nivelEducacional"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.nota"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.fechaInscripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.oficvinaIncripcion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.EjecutivoAtencion"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.tienePremio"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.folio"></bean:message></th>
					<th><bean:message key="label.informe.carga.resultado.premio"></bean:message></th>
				</tr>
				<%int cont = 0;%>
				<logic:iterate id="beca" indexId="i" name="cargaResultadoVOs">
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
						<td nowrap="nowrap"><bean:write name="beca" property="segmento" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nivelEstudio" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="nota" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="fechaIncripcion" formatKey="format.date"/></td>
						<td nowrap="nowrap"><bean:write name="beca" property="oficinaInscripcion" /></td>
						<td nowrap="nowrap"><bean:write name="beca" property="ejecutivoAtencion" /></td>
						<td nowrap="nowrap">&nbsp;</td>
						<td nowrap="nowrap">&nbsp;</td>
						<td nowrap="nowrap">&nbsp;</td>
					</tr>
				</logic:iterate>
			</table>
</body>
</html:html>