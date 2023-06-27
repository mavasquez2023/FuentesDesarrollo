<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<html:form action="${webPrePath}/adminBecasIncentivos/ListaBecasIncentivos"
	styleId="dataForm">
	<html:hidden property="_cmd" value="resultado" />
	<table width="100%">
		<tr>
			<th><bean:message key="label.becaIncentivo" /></th>
		</tr>
		<tr>
			<td align="left"><strong><bean:write name="nombreBeca" /></strong></td>
		</tr>
		<tr>
			<td><bean:message key="label.totalBeneficiarios"/><bean:size id="total" name="beneficiariosDVO"/><c:out value="${total}"></c:out>			
			</td>
		</tr>

	</table>
	<table width="100%">
		<tr>
			<th colspan="5"><bean:message key="label.benefBecasIncentivos" /></th>
		</tr>
		<tr>
			<td width="10%"><strong><bean:message key="label.carta.beca.pdf.numeroInscripcion"/></strong></td>
			<td><strong><bean:message key="label.tipopersona.afiliado"/></strong></td>
			<td><strong><bean:message key="label.administracion.becas.beneficiario"/></strong></td>
			<td><strong><bean:message key="label.administracion.becas.beneficio"/></strong></td>
			<td><strong><bean:message key="label.administracion.becas.entregado"/></strong></td>
		</tr>
		<logic:iterate id="beneficiario" indexId="i" name="beneficiariosDVO">
			<tr>
				<td width="10%"><bean:write name="beneficiario" property="idBeneficiario" /></td>
				<td><bean:write name="beneficiario" property="fullNombreSolicitante" /></td>
				<td><html:link page="/consultaBecasIncentivos/detalleBeneficiario.do?_cmd=inicio&idBeneficiario=${beneficiario.idBeneficiario}" styleClass="links" target="_blank"><bean:write name="beneficiario" property="fullNombreBeneficiario" /></html:link></td>
				<td><bean:write name="beneficiario" property="premio"/></td>
				<td>
					<c:if test="${beneficiario.premioEntregado}"><bean:message key="label.boolean.true"/></c:if>
					<c:if test="${!beneficiario.premioEntregado}"><bean:message key="label.boolean.false"/></c:if>
				</td>
			</tr>
		</logic:iterate>
		<tr>
			<td colspan="5" align="right"><html:submit styleClass="button"><bean:message key="button.aceptar"/> </html:submit>  </td>
		</tr>
	</table>
		

</html:form>