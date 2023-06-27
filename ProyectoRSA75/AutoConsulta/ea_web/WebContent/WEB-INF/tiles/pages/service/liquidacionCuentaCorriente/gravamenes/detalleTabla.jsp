<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


	<logic:notEmpty name="lista" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.gravamenes.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 50%; text-align: center;">
									<bean:message key="liquidacion.gravamenes.table.header.gravamenes" />
								</td>
								<td class="smalltableheader" style="width : 50%; text-align: center">
									<bean:message key="liquidacion.gravamenes.table.header.monto" />
								</td>
							</tr>
							
							<logic:iterate id="detalle" name="lista" scope="request">							
							<tr>
								<td class="smallprompt">
									<bean:write name="detalle" property="concepto.glosa" scope="page" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="2" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.gravamenes.table.label.total" />
								</td>
								<td class="smallcurrency">
									<bean:write name="total" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			<tr>
				<td>&nbsp;</td>
			</tr>

		</tbody>
	</table>
	</logic:notEmpty>