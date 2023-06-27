<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

<html:form action="${webPrePath}/adminBecasIncentivos/ListaBecasIncentivos"
	styleId="dataForm">
	<html:hidden property="_cmd" value="resultado" />
	
	<table width="100%">
		<tr>
			<th colspan="5"><bean:message key="label.admBecasIncentivos" /></th>
		</tr>
		<tr>
			<td colspan="2"><strong><bean:message key="label.administracion.becas.nombre"/></strong></td>
			<td><strong><bean:message key="label.administracion.becas.estado"/></strong></td>
			<td><strong><bean:message key="label.administracion.becas.beneficiarios"/></strong></td>
		</tr>
		<logic:iterate id="beca" indexId="i" name="becaDVO">
			<tr>
				<td><a class="links" href="${contextRoot}/adminBecasIncentivos/configuracionBecasIncentivos.do?_cmd=modificar&beca=${beca.idBeca}">${beca.idBeca}</a></td>
				<td><bean:write name="beca" property="nombre" /></td>
				<td>
					<c:if test="${beca.activa}"><bean:message key="label.administracion.becas.vigente"/></c:if>
					<c:if test="${!beca.activa}"><bean:message key="label.administracion.becas.noVigente"/></c:if>
				</td>
				<td><html:link page="/adminBecasIncentivos/ListaBeneficiariosBecasIncentivos.do?_cmd=inicio&beca=${beca.idBeca}&indiceBeca=${i}" styleClass="links"><bean:message key="label.administracion.becas.ver" /></html:link>   </td>
			</tr>
		</logic:iterate>
		<tr>
			<td colspan="4" align="right">
			<html:button property="boton" onclick="this.form.action='${contextRoot}/adminBecasIncentivos/configuracionBecasIncentivos.do?_cmd=inicio';this.form.submit();" styleClass="button"><bean:message key="button.agregar" /></html:button></td>
		</tr>
		
	</table>
		

</html:form>