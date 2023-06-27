<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">var jsContextRoot = '<c:out value="${contextRoot}"/>';</script>

<script language="javascript">
$(document).ready(function(){
	  $('#limpiar').click(function(){
		  $('#nombre').val("");
		});
});
</script>
<html:form action="${webPrePath}/adminBecasIncentivos/guardaTipoPremioDocReque"
	styleId="dataForm">
	<html:hidden property="_cmd" styleId="_cmd" value="${_cmd}"/>
	
	<logic:equal name="_cmd" value="tipoPremio">
		<table width="100%">
			<tr>
				<th colspan="3"><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio.mantenedor" /></th>
			</tr>
			<logic:equal name="muestraCodigo" value="true">
			<tr>
				<td><strong><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio.codigo"/></strong></td>
				<td colspan="2"><html:text property="codigo" styleId="codigo" size="10" readonly="true"></html:text></td>
			</tr>
			</logic:equal>
			<tr>
				<td><strong><bean:message key="label.administracion.tipoPremioDocReque.tipoPremio.descripcion"/></strong></td>
				<td colspan="2"><html:text property="nombre" styleId="nombre" size="50"></html:text></td>
			</tr>
			<tr>
				<td></td>
				<td align="right" width="60%"> 
					<html:submit property="opc" styleId="opc" styleClass="button" ><bean:message key="button.guardar" /></html:submit>					
				</td>
				<td align="right">
					<html:button property="limpiar" styleId="limpiar" styleClass="button"><bean:message key="button.limpiar" /></html:button>
				</td>
			</tr>
			
		</table>
	</logic:equal>
	
	<logic:equal name="_cmd" value="docRequerido">
		<table width="100%">
			<tr>
				<th colspan="3"><bean:message key="label.administracion.mantenedor.docRequerido.mantenedor" /></th>
			</tr>
			<logic:equal name="muestraCodigo" value="true">
			<tr>
				<td><strong><bean:message key="label.administracion.mantenedor.docRequerido.codigo"/></strong></td>
				<td colspan="2"><html:text property="codigo" styleId="codigo" size="10" readonly="true"></html:text></td>
			</tr>
			</logic:equal>
			<tr>
				<td><strong><bean:message key="label.administracion.mantenedor.docRequerido.descripcion"/></strong></td>
				<td colspan="2"><html:text property="nombre" styleId="nombre" size="50"></html:text></td>
			</tr>
			<tr>
				<td></td>
				<td align="right" width="60%"> 
					<html:submit property="opc" styleId="opc" styleClass="button"><bean:message key="button.guardar" /></html:submit>					
				</td>
				<td align="right">
					<html:button property="limpiar" styleId="limpiar" styleClass="button"><bean:message key="button.limpiar" /></html:button>
				</td>
			</tr>
			
		</table>
	</logic:equal>

</html:form>