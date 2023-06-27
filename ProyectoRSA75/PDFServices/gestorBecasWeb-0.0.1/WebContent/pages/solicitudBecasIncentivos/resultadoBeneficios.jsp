<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
	function alerta() {
		if (confirm("¿Está seguro de crear este beneficio?")) {
			document.BeneficiosCreadosForm.submit(); 
		}else{
		}
	}
	function actualizaTextos(idBeneficio, paso){
		getTexto('documento', idBeneficio, paso);
		getTexto('premio', idBeneficio, paso);
	}
</script>

<!--  INICIO DEL FORMULARIO  -->
<html:form
	action="${webPrePath}/solicitudBecasIncentivos/BeneficiosCreados"
	styleId="dataForm">

	<!-- Datos Postulación -->
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.postulacion" /></th>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreSolicitante" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullNombre" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutSolicitante" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullRut" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.email" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="email" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.telefono" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="telefono" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.celular" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="celular" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.categoria" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="segmentoDVO.segmento" />
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreBeneficiario" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="beneficiarioSeleccionado.fullNombre" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutBeneficiario" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="beneficiarioSeleccionado.fullRut" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nivelEducacional" />
			</strong></td>
			<td><bean:write name="solicitudBO"
					property="nivelEducacional.nivelEducacional" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.curso" />
			</strong></td>
			<td><bean:write name="solicitudBO" property="curso.curso" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.calificacion" />
			</strong></td>
			<td><bean:write name="solicitudBO" property="calificacion" />
			</td>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.puntajeLenguaje" />
			</strong></td>
			<td><bean:write name="solicitudBO" property="puntajeLenguaje" />
			</td>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.puntajeMatematica" />
			</strong></td>
			<td><bean:write name="solicitudBO" property="puntajeMatematica" />
			</td>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.puntajePromedio" />
			</strong></td>
			<td><bean:write name="solicitudBO" property="puntajePromedio" />
			</td>
		</tr>
	</table>
	
	<c:if test="${!empty solicitudBO.alternativas}">
		<html:hidden property="_cmd" value="grabar" />
	
	<!-- Datos de benficio -->

	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.beneficiosPostular" />
			</th>
		</tr>
		<logic:iterate id="alternativa" indexId="i" name="solicitudBO"
			property="alternativas">
			<tr>
				<td width="5%"><html:radio property="indice" value="${i}" onclick="javascript:actualizaTextos(this.value, '')"/>
				</td>
				<td><bean:write name="alternativa"
						property="descripcionAlternativa" />
				</td>
			</tr>
		</logic:iterate>
	</table>

	<!-- Documentación Requerida -->
	<table width="100%">
		<tr>
			<th><bean:message key="label.documentacionRequerida" />
			</th>
		</tr>
		<tr>
			<td><div id="documento"/></td>
		</tr>
	</table>

	<!-- Premio -->
	<table width="100%">
		<tr>
			<th><bean:message key="label.premios" />
			</th>
		</tr>
		<tr>
			<td><div id="premio"/></td>
		</tr>
	</table>

	<table width="100%">
		<tr>
			<td align="right"><html:button property="botonAceptar" styleClass="button" onclick="javascript:alerta();">
					<bean:message key="button.aceptar" />
				</html:button>
			</td>
		</tr>

	</table>
	</c:if>
	<c:if test="${empty solicitudBO.alternativas}">
			<html:hidden property="_cmd" value="volver" />
		
		<table width="100%">
			<tr bgcolor="#FFFFE5;">
				<td align="center">
					<strong><bean:message key="message.sinBeneficios"/></strong>
				</td>
			</tr>
		
		<tr>
			<td align="right"><html:submit property="botonVolver" styleClass="button">
					<bean:message key="button.volver" />
				</html:submit>
			</td>
		</tr>

	</table>
	
	</c:if>
	
	
	
</html:form>
