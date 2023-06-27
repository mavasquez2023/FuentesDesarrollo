<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<table>
	<tbody>
		<tr>
			<td style="text-align: center;">
				<span style="font-family: Verdana; font-size:16px;; color: #006666; font-weight:bold; text-align: center; vertical-align:middle;">
					<bean:message key="ctaCte.402.nivel2.banner" />&nbsp;al&nbsp;
				</span>
				<span style="font-family: Verdana; font-size:14px;; color: #006666; font-weight:bold; text-align: center; vertical-align:middle;">
					<bean:write name="fechaActual" scope="request" filter="true" formatKey="global.date" ignore="true" />
				</span>
			</td>
		</tr>
	</tbody>
</table>