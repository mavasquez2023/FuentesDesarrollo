<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<table width="100%">
	<tbody>
		<tr>
			<td style="width : 5%">
				<html:img alt="" height="1" width="4" page="/img/c.gif" />
			</td>

			<td style="width : 90%;">
				<html:form action="/proxy" method="psot" scope="request">
				<table border="0" cellspacing="1" cellpadding="0">
					<tbody>
						<logic:iterate id="element" name="opciones" scope="session">
							<tr>
								<td>
									<html:radio property="empresaElegida" idName="element" value="idValue"></html:radio>
								</td>
								<td>
									<bean:write name="element" property="displayValue" scope="page" filter="true" ignore="true" />
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				<html:submit value="Aceptar"></html:submit>
				<html:hidden property="status" value="c" />
				</html:form>
			</td>
			<td style="width : 5%">
				<img alt="" height="1" width="4" src="./img/c.gif"/>
			</td>
		</tr>
	</tbody>
</table>
