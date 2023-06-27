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
	<td width="25%" class="subtabla"><strong><bean:message key="label.caja"/></strong></td>
	<td colspan="2">
		<select name="caja" id="caja">
			<option value="1">La Araucana</option>
			<option value="2">Gabriela Mistral</option>
			<option value="3">Otra</option>
		</select>			
	</td>
</tr>
</table>		