<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">
	/** Objeto Form */
	function getDataForm() {
		return document.getElementById('dataForm');
	}

	function swapAll(id, imgId)
	{
		obj = document.getElementById(id);
		img = document.getElementById(imgId);
	    if ( obj.style.display == '' )
	    {
			obj.style.display='none';
			img.src   = '<c:url value="/images/ico_mas.gif" />';
			img.alt   = "Expandir";
			img.title = "Expandir";
		} else
		{
			obj.style.display = '';
			img.src   = '<c:url value="/images/ico_menos.gif" />';
			img.alt   = "Contraer";
			img.title = "Contraer";
		}
	}
</script>

<!--  INICIO DEL FORMULARIO  -->
<html:form
	action="${webPrePath}/consultaBecasIncentivos/inicioConsultaBecasIncentivosByPersona"
	styleId="dataForm">
	<!-- titular -->
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.solicitante" />
			</th>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreSolicitante" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullNombre" />
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutSolicitante" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullRut" />
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.categoria" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="segmentoDVO.segmento" />
			</td>
		</tr>
	</table>

	<!-- beneficiarios -->

	<table width="100%">
		<tr>
			<th colspan="3"><bean:message key="label.benefBecasIncentivos" />
			</th>
		</tr>
		<tr>
			<td></td>
			<td><strong><bean:message key="label.nombreBeneficiario"/></strong></td>
			<td><strong><bean:message key="label.becaIncentivo"/></strong></td>
			
		</tr>
		<logic:iterate id="beneficiario" name="solicitudBO"
			property="posiblesBeneficiarios" indexId="i">
			<tr>
				<td width="5%"></td>
				<td width="50%"><bean:write name="beneficiario" property="fullNombre" /></td>
				<td width="45%">
					<c:if test="${beneficiario.tieneBeca}">
							<a href="#" onclick="swapAll('masBeca${i}', 'img${i}');">
								<img id="img${i}" src="<c:url value="/images/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
								<bean:message key="label.tieneBeca"/>
							</a>
							<div id="masBeca${i}" style="display:none">
								<logic:iterate id="becas" name="beneficiario" property="listaBecas">
								<c:if test="${becas.premioEntregado}">
									<bean:write name="becas" property="nombreBeca" />
									<html:link page="/entregaIncentivos/detalleBeneficiario.do?_cmd=inicio&idBeneficiario=${becas.idBeneficiario}" styleClass="links">(<bean:message key="label.entregado"/>)</html:link>
									<br/>
								</c:if>
								<c:if test="${!becas.premioEntregado}">
									<bean:write name="becas" property="nombreBeca" />
									<html:link page="/entregaIncentivos/detalleBeneficiario.do?_cmd=inicio&idBeneficiario=${becas.idBeneficiario}" styleClass="links">(<bean:message key="label.entregar"/>)</html:link>
									<br/>
								</c:if>
								</logic:iterate>
							</div>
					</c:if>
					<c:if test="${!beneficiario.tieneBeca}">
							<bean:message key="label.noTieneBeca"/> 
					</c:if>
				</td>
			</tr>
		</logic:iterate>
	</table>
	<c:if test="${!solicitudBO.todosYaTienenBeca}">
	<html:hidden property="_cmd" value="resultado" />
	<html:hidden property="_flagValidar" value="true" />
	</c:if>
</html:form>
