<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
	function print(indice) {
		$(document).ready(function() {
			$("#offset").prepend=indice;
			$("#printForm").submit();
			hideLoading();
		});
	}
	
</script>


<!--  INICIO DEL FORMULARIO  -->
<html:form action="${webPrePath}/solicitudBecasIncentivos/ImprimirBeneficiosCreados" styleId="printForm" target="print">
	<html:hidden property="offset" styleId="offset" />
</html:form>


<html:form
	action="${webPrePath}/solicitudBecasIncentivos/inicioBusquedaPersona"
	styleId="dataForm">
	<html:hidden property="_cmd" value="inicio" />

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
	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.beneficio" /></th>
		</tr>
		<tr>
			<td><strong><bean:message key="label.administracion.becas.nombre" /></strong></td>
			<td><strong><bean:message key="label.administracion.becas.beneficio"/></strong></td>
			<td><strong><bean:message key="label.informacion"/></strong></td>
			<td>&nbsp;</td>
		</tr>
		<logic:iterate id="beca" indexId="i" name="alternativaVO" property="becas">
			<tr>
				<td><bean:write name="beca" property="nombre" /> </td>
				<td><bean:write name="beca" property="premio.premio" /> </td>
				<td><bean:write name="beca" property="glosaEntregaResultados"/> </td>
				<td>
					<html:link page="/solicitudBecasIncentivos/cartaGanadorBecaPDF.do?idBeneficiario=${beca.idBeneficiario}" styleClass="links" onclick="javascript:setTimeout('hideLoading();',4000);"><img src="../images/ico_pdf.gif"/></html:link>
				</td>
			</tr>
		</logic:iterate>
	</table>


	<table width="100%">
		<tr>
			<td align="right"><html:submit property="botonAceptar" styleClass="button">
					<bean:message key="button.aceptar" />
				</html:submit>
			</td>
		</tr>

	</table>

</html:form>
