<!-- enviroment definition -->
<%@ page language="java" %><%@ 
taglib uri="/tags/struts-html" prefix="html" %><%@ 
taglib uri="/tags/struts-bean" prefix="bean" %><%@ 
taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ page import="cl.araucana.autoconsulta.vo.LicenciaMedicaVO" %>
	<%
	
	LicenciaMedicaVO licencia = (LicenciaMedicaVO) request.getAttribute("licencia");
	
	 %>
	<logic:empty name="error"> 
		<table  align="center"> 
			<tr>
				<th>Estado</th>
				<th>Fecha</th>
			</tr>
			<tr>
				<td class="left"><strong><bean:message key="label.licencia.fecha.ingreso"/><strong></td>
				<td><bean:write name="licencia" property="fechaRecepcion" /></td>
			</tr>
			<tr>
				<td class="left"><strong><bean:message key="label.licencia.fecha.envio.compin"/><strong></td>
				<td><bean:write name="licencia" property="fechaEnvioCompin" /></td>
			</tr>
			<tr>
				<td class="left"><strong><bean:message key="label.licencia.fecha.resolucion"/><strong></td>
				<td><bean:write name="licencia" property="fechaRecepcionCompin" /></td>
			</tr>
			<tr>
				<td class="left"><strong>Estado Actual<strong></td>
					<logic:equal name="licencia" property="visada" value="2">
						<td id="estadoTxt">
							<bean:message key='<%="traductor.estado.licenciaMedicaCompinOcc."+licencia.getCodigoEstadoLicencia()%>'/>
						</td>
					</logic:equal>
					<logic:notEqual name="licencia" property="visada" value="2">
						<td id="estadoTxt">
							<bean:message key='<%="traductor.estado.licenciaMedica."+licencia.getCodigoEstadoLicencia()%>'/>
						</td>
					</logic:notEqual>
			</tr>
			<tr>
				<td class="left"><strong>Disponible Para Pago<strong></td>
				<td id="fechaPago"><bean:write name="licencia" property="fechaDePago" /></td>
			</tr>
		</table>
	</logic:empty>
	<logic:notEmpty name="error">
		<div align="center">
			<bean:write name="error"/>
		</div>
	</logic:notEmpty>
	<script type="text/javascript">
		$(document).ready(function () {
			if($.trim($("#estadoTxt").text())=='Recepcionada'){
				$("#fechaPago").text("En curso");
			}else if($.trim($("#estadoTxt").text())!='Autorizada'){
				$("#fechaPago").text("00/00/0000");
			}
		});
	</script>