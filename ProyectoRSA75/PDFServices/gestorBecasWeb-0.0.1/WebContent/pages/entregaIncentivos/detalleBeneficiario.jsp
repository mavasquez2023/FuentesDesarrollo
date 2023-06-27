<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">

	function alerta() {
		if (confirm("¿Está Seguro de Entregar el Premio?")) {
			document.EntregarIncentivoFrom.submit(); 
		}else{
			
		}
	}
</script>

<html:form
	action="${webPrePath}entregaIncentivos/entregarIncentivo"
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
				<html:hidden name="detalleBeneficiarioVO" property="beneficiarioDVO.idBeneficiario"  styleId="idBeneficiario"/>
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
					<td width="20%"><strong><bean:message key="label.administracion.becas.entregado" />
					</strong></td>
					<td><bean:message key="label.administracion.becas.si" /></td>
				</tr>
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
			<c:if test="${!detalleBeneficiarioVO.beneficiarioDVO.premioEntregado}">
				<tr>
					<td width="20%"><strong><bean:message key="label.administracion.becas.entregado" />
					</strong></td>
					<td><bean:message key="label.administracion.becas.no" /></td>
				</tr>
				<c:if test="${isFueraDePlazo == 'true'}">
					<tr>
						<td colspan="2" bgcolor="Khaki" align="center"><strong><bean:message key="label.fueraDePlazo"/>
						<bean:write name="detalleBeneficiarioVO" property="becaDVO.fechaInicioEntrega" formatKey="format.date"/> al <bean:write name="detalleBeneficiarioVO" property="becaDVO.fechaFinEntrega" formatKey="format.date" /> 
						</strong>
						</td>
					</tr>
				</c:if>
			</c:if>
			<c:set var="folioTeso" value="${detalleBeneficiarioVO.beneficiarioDVO.folioTesoreria}"/>
			<c:if test="${folioTeso > 0}">
				<tr>
					<td width="20%"><strong><bean:message key="label.folio" />
					</strong></td>
					<td><bean:write name="detalleBeneficiarioVO" property="beneficiarioDVO.folioTesoreria" /></td>
				</tr>
			</c:if>
		</c:if>
		<c:if test="${'NO' == tienePremio}">
				<tr>
					<td colspan="2" bgcolor="Khaki" align="center"><strong><bean:message key="label.noTienPremio"/></strong>
					</td>
				</tr>
		</c:if>
		<c:if test="${'PO'== tienePremio}">
				<tr>
					<td colspan="2" bgcolor="Khaki" align="center"><strong><bean:message key="label.suSolicitudEstaEnEvaluacion"/></strong>
					</td>
				</tr>
		</c:if>
		<c:if test="${!detalleBeneficiarioVO.beneficiarioDVO.premioEntregado}">
			<c:if test="${('SI'== tienePremio  || 'PO'== tienePremio) && isFueraDePlazo == 'false'}">
				<tr>
					<td colspan="2" align="right"><html:button property="" onclick="javascript:alerta();" styleClass="button"><bean:message key="button.entregar"/></html:button></td>
				</tr>
			</c:if>
		</c:if>
	</table>
</html:form>