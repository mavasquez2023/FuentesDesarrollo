<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<table>
	<tbody>
		<tr>
			<td style="text-align: center;">
				<span style="font-family: Verdana; font-size:16px;; color: #006666; font-weight:bold; text-align: center; vertical-align:middle;">
					<bean:message key="ctaCteN3.banner" />&nbsp;al&nbsp;
				</span>
				<span style="font-family: Verdana; font-size:14px;; color: #006666; font-weight:bold; text-align: center; vertical-align:middle;">
					<bean:write name="fechaActual" scope="request" filter="true" formatKey="global.date" ignore="true" />
				</span>
			</td>
		</tr>
	</tbody>
</table>