<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<script language="javascript">
	$(document).ready(function() {
			//reponeStatus();
		}
	);
</script>
<table width="100%">
<tr> 
	<td width="25%"><strong><bean:message key="label.caja"/>:</strong></td>
	<td colspan="3">
		<html:select property="cajas">
			<html:option value=""><bean:message key="label.common.seleccione.text"/></html:option>
			<html:options collection="comboCajas" property="codigo" labelProperty="nombre"/>
		</html:select>			
	</td>
</tr>
</table>		