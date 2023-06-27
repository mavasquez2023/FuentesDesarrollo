<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<table>
	<tbody>
		<tr>
			<td>
				<table>
					<tbody>
						<tr>
							<td style="vertical-align: top;"><html:img page="/img/vwicn069.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link onclick="history.go(-2); return false;" href="" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									<bean:message key="global.text.paginaInicioLiquidacionCuentaCorriente" />
								</html:link>
							</td>
						</tr>
						<tr>
							<td><html:img page="/img/prevview.gif" style="border : 0px; width : 10px; height : 10px;" /></td>
							<td style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
								<html:link onclick="history.go(-1); return false;" href="" style="text-decoration: none; color: #0000ff; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: bold;">
									<bean:message key="global.text.paginaAnterior" />
								</html:link>
							</td>
						</tr>			
					</tbody>
				</table>
			</td>
		</tr>
		
		<tr>
			<td><html:img page="/img/horiz_gray.gif" style="border : 0; width : 142px; height : 6px;" alt=""/></td>										
		</tr>

		<tr>
			<td>
				<table>
					<tbody>
						<tr>										
							<td style="text-decoration: none; color: #000000; font-family: Verdana, Arial, sans-serif; font-size: 10px; font-weight: normal;">
							</td>
						</tr>
						<tr>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>