<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table style="vertical-align: top;" border="0" cellspacing="0" cellpadding="0">					
	<tbody>
		<tr>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_1.gif" style="text-align : center; border : 0px;" height="4px" /></td>
			<td style="width: 340px; height : 4px;"><html:img page="/img/box_top.gif" style="width: 340px; height : 4px; text-align : center; border : 0px;" height="4px" /></td>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_2.gif" style="text-align : center; border : 0px;" height="4px" /></td>
		</tr>
		
		<tr>
			<td style="width : 4px;"><html:img page="/img/box_left.gif" style="width : 4px; height : 38px;"/></td>
			<td style="width : 340px; background-color: #f8f8f8;">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tbody>								
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
					</tbody>
				</table>											
			</td>
			<td style="width : 4px;"><html:img page="/img/box_right.gif" style="width : 4px; height : 38px;"/></td>
		</tr>
		
		<tr>
			<td style="width : 4px; height : 4px"><html:img page="/img/box_3.gif" style="text-align : center; border : 0px;" /></td>
			<td style="width: 340px; height : 4px;"><html:img page="/img/box_bottom.gif" style="width: 340px; height : 4px; text-align : center; border : 0px;" /></td>
			<td style="width : 4px; height : 4px;"><html:img page="/img/box_4.gif" style="text-align : center; border : 0px;" /></td>
		</tr>
	</tbody>
</table>
</html>
