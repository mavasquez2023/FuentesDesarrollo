<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

<html:form action="${webPrePath}/adminBecasIncentivos/listaTipoPremioDocReque"
	styleId="dataForm">
	<html:hidden property="_cmd" styleId="_cmd" value="${_cmd}"/>
	
	<logic:notEmpty name="tipoPremios">
		<table width="100%">
			<tr>
				<th colspan="2"><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio" /></th>
			</tr>
			<tr>
				<th width="10%"><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio.codigo"/></th>
				<th><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio.descripcion"/></th>
			</tr>
			
			<logic:iterate id="tipoPremio" name="tipoPremios">
				<tr>
					<td  width="10%" align="center"><a href="${contextRoot}/adminBecasIncentivos/inicioTipoPremioDocReque.do?_cmd=tipoPremio&opc=actualiza&id=<bean:write name="tipoPremio" property="idTipoPremio"/>" class="links"><bean:write name="tipoPremio" property="idTipoPremio"/></a></td>
					<td><bean:write name="tipoPremio" property="tipoPremio"/></td>
				</tr>
			</logic:iterate>
			<tr>
				<td colspan="2"  align="right"><html:button property="agregar" onclick="this.form.action='${contextRoot}/adminBecasIncentivos/inicioTipoPremioDocReque.do?_cmd=tipoPremio&opc=inicio';this.form.submit();" styleClass="button"><bean:message key="button.agregar"/></html:button> </td>
			</tr>
			
		</table>
	 </logic:notEmpty>

	<logic:notEmpty name="docRequeridos">
		<table width="100%">
			<tr>
				<th colspan="2"><bean:message key="label.administracion.tipoPremioDocReque.docRequerido" /></th>
				
			</tr>
			<tr>
				<th width="10%"><bean:message key="label.administracion.mantenedor.docRequerido.codigo"/></th>
				<th><bean:message key="label.administracion.mantenedor.docRequerido.descripcion"/></th>
			</tr>
			
			<logic:iterate id="docRequerido" name="docRequeridos">
			<tr>
				<td  width="10%" align="center"><a href="${contextRoot}/adminBecasIncentivos/inicioTipoPremioDocReque.do?_cmd=docRequerido&opc=actualiza&id=<bean:write name="docRequerido" property="idDocumentacionRequerida"/>" class="links"><bean:write name="docRequerido" property="idDocumentacionRequerida"/></a></td>
				<td><bean:write name="docRequerido" property="documentacionRequerida"/></td>
			</tr>
			</logic:iterate>
			<tr>
				<td colspan="2" align="right"><html:button property="agregar" onclick="this.form.action='${contextRoot}/adminBecasIncentivos/inicioTipoPremioDocReque.do?_cmd=docRequerido&opc=inicio';this.form.submit();" styleClass="button"><bean:message key="button.agregar"/></html:button> </td>
			</tr>
		</table>
	</logic:notEmpty>
	

</html:form>