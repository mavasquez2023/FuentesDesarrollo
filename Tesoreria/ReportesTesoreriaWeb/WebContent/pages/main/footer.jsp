<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<script language="javascript">

	function logout() {
		var form = document.getElementById('logout');
		form.submit();
	}
	
</script>

	
	<form method="post" action="ibm_security_logout" name="logout" id="logout">
		<input type="hidden" name="logoutExitPage" value="/index.jsp"/>
	</form>
	
	<table width="980" class="table_transparente">
    	<tr>
			<td colspan="2" class="td_line"></td>
		</tr>
		<tr>
			<td align="right">
				<i><font size="1"><bean:message key="title.company"/> - <bean:message key="title.version"/></font></i>
			</td>
		</tr>
	</table>
	