<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">

	function alerta() {
		if (confirm("Esta seguro de eliminar el Beneficiario?")) {
			document.EliminarBeneficiarioForm.submit(); 
		}else{
			
		}
	}
</script>

<html:form
	action="${webPrePath}/adminBecasIncentivos/EliminaBeneficiario"
	styleId="dataForm">
	<!-- Datos Postulación -->
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.postulacion" /></th>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.carta.beca.pdf.numeroInscripcion" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="beneficiarioDVO.idBeneficiario" />
			<html:hidden name="detalleBeneficiarioVO" property="beneficiarioDVO.idBeneficiario" styleId="idBeneficiario" />		
					</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreSolicitante" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="beneficiarioDVO.fullNombreSolicitante" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutSolicitante" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="beneficiarioDVO.fullRutSolicitante" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.categoria" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="segmentoDVO.segmento" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreBeneficiario" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="beneficiarioDVO.fullNombreBeneficiario" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutBeneficiario" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="beneficiarioDVO.fullRutBeneficiario" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nivelEducacional" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO"
					property="nivelEducacionalDVO.nivelEducacional" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.curso" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="cursoDVO.curso" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.fechaPostulacion" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.creacionFecha" formatKey="format.date" /></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.usuarioPostulacion" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.creacionUsuario"/></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.nombreUsuarioPostulacion" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.fullNombreUserCrea"/></td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.nomina.oficina" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.fullOficina"/></td>
		</tr>	
		<tr>
			<td width="20%"><strong><bean:message key="label.tienePremio" />
			</strong></td>
			<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.tienePremio" /></td>
		</tr>
		<c:set var="tienePremio" value="${detalleBeneficiarioVO.beneficiarioDVO.tienePremio}"/>
		<c:if test="${'SI'== tienePremio}">
			<tr>
				<td width="20%"><strong><bean:message key="label.becaIncentivo" />
				</strong></td>
				<td><bean:write name="detalleBeneficiarioVO" property="becaDVO.nombre" /></td>
			</tr>
			<tr>
				<td width="20%"><strong><bean:message key="label.beneficio" />
				</strong></td>
				<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.premio" /></td>
			</tr>
			<c:if test="${detalleBeneficiarioVO.beneficiarioDVO.premioEntregado}">
				<tr>
					<td width="20%"><strong><bean:message key="label.administracion.becas.fechaEntrega" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.fechaEntrega" formatKey="format.date" /></td>
				</tr>
				<tr>
					<td width="20%"><strong><bean:message key="label.usuarioEntrega" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.usuarioEntrega"/></td>
				</tr>
				<tr>
					<td width="20%"><strong><bean:message key="label.nomUsuarioEntrega" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.fullNombreUserEntrega"/></td>
				</tr>				
			</c:if>
			<c:set var="folioTeso" value="${detalleBeneficiarioVO.beneficiarioDVO.folioTesoreria}"/>
			<c:if test="${folioTeso > 0}">
				<tr>
					<td width="20%"><strong><bean:message key="label.folio" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.folioTesoreria" /></td>
				</tr>
				<tr>
					<td width="20%"><strong><bean:message key="label.estado.folio" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.estadoFolioTesoreria" /></td>
				</tr>
			</c:if>
		</c:if>
		<logic:equal name="detalleBeneficiarioVO" property="beneficiarioDVO.eliminable" value="true">
				<logic:empty name="bloquear">
						<tr>
							<td width="20%"><strong><bean:message key="label.texto" /></strong></td>
							<td><html:text property="texto" size="60" maxlength="249"></html:text></td>
						</tr>
						<tr>
							<td colspan="2" align="right"><html:button property="" onclick="javascript:alerta();" styleClass="button"><bean:message key="button.eliminar"/></html:button></td>
						</tr>
				</logic:empty>
				<logic:notEmpty name="bloquear">
						<tr>
							<td width="20%"><strong><bean:message key="label.texto" /></strong></td>
							<td width="80%"><bean:write name="comentario" /></td>
						</tr>
						<tr>
							<td colspan="2" align="right"></td>
						</tr>
						
				</logic:notEmpty>
		</logic:equal>
		<logic:equal name="detalleBeneficiarioVO" property="beneficiarioDVO.eliminable" value="false">
			<c:set var="folioTesoreria" value="${detalleBeneficiarioVO.beneficiarioDVO.folioTesoreria}"/>
			<c:if test="${folioTesoreria > 0}">
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td align="center" bgcolor="Khaki" colspan="2"><strong><bean:message key="label.no.es.eliminable.beca" /></strong></td>
				</tr>
			</c:if>
			<c:if test="${folioTesoreria <= 0}">
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="20%" align="center" bgcolor="Khaki" colspan="2"><strong><bean:message key="label.no.es.eliminable.incentivo" /></strong></td>
				</tr>
			</c:if>
		</logic:equal>
	</table>
</html:form>