<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table width="370" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="15"><html:img page="/img/left_emp.gif" width="15" height="50" /></td>
		<td width="368" height="50" background="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/img/center_emp.gif">
			<table width="366">
				<tr>
					<td style="width : 10px;"></td>
					<td style="height : 4px; font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #006666; vertical-align : top;">
						<bean:message key="global.text.empresa" />
					</td>												
					<td>
						<table width="100%">
							<tbody>
								<tr>
									<td style="font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #000000">
										<bean:write name="empresa" property="rut.formattedRut" scope="request" />-
										<bean:write name="empresa" property="rut.dv" scope="request"/>
									</td>												
								</tr>
								<tr>											
									<td style="font-family: Verdana, Arial, sans-serif; font-size: 11px; font-weight: bold; color: #000000">
										<bean:write name="empresa" property="nombre" scope="request"/>
									</td>												
								</tr>																														
							</tbody>
						</table>
					</td>
				</tr>
			</table>
		</td>
		<td width="13"><html:img page="/img/right_emp.gif" width="13" height="50" /></td>
	</tr>
</table>
