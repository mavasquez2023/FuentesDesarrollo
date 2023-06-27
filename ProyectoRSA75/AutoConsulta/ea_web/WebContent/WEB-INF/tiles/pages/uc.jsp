<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table border="0" cellspacing="1" cellpadding="0">
	<tbody>
		<tr style="height : 20px;">
			<td>
				<html:img alt="" height="1" width="4" page="/img/c.gif"/>
			</td>
		</tr>
		
		<tr>
			<td class="nota">
				<bean:write name="mensaje" scope="request" ignore="true" />
			</td>
		</tr>
		<tr><td style="height : 14px;"><html:img alt="" height="1" width="4" page="/img/c.gif"/></td></tr>
		<tr>
			<td>
				<input class="buttonbold" type="button" value="Volver a la página anterior" onclick="history.go(-1); return false;"/>
			</td>
		</tr>
	</tbody>
</table>