<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<logic:notEmpty name="detalles" scope="request">
	
	<!-- Concepto Por Cobrar-->
	<logic:equal name="concepto" property="tipo" scope="request" value="I">										
		<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
			<tbody>
				<tr style="height : 18px;">
					<td class=smallfontreverse >
						<b>
							<bean:message key="ctaCteN2.table.header" />
						</b>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
							<tbody>
								<tr style="background-color : #cccccc;">
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.periodo" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.monto" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.gravamenes" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.total" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.acumulado" /></td>
								</tr>
								<logic:iterate id="detalle" name="detalles" scope="request">						
								<tr>
									<td class="smallprompt">
										<bean:define id="parametros" name="detalle" property="params"></bean:define>
											<html:link page="/ctaCteN3.do" name="parametros">
												<bean:define id="periodo" name="detalle" property="periodo" />
												<bean:write name="periodo" property="formattedPeriodo" />
											</html:link>																
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align: top;">
										<logic:notEqual name="detalle" property="gravamenes" value="0">
											<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
										</logic:notEqual>
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="subtotal" filter="true" formatKey="global.monto" ignore="true" />
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="acumulado" filter="true" formatKey="global.monto" ignore="true" />
									</td>
								</tr>
								</logic:iterate>
								
								<tr>
									<td colspan="5" style="height : 1px; background-color : #006666;"></td>
								</tr>


							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</logic:equal>	

	<!-- Concepto Por Pagar -->
	<logic:equal name="concepto" property="tipo" scope="request" value="E">
		<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
			<tbody>
				<tr style="height : 18px;">
					<td class=smallfontreverse >
						<b>
							<bean:message key="ctaCteN2.table.header" />
						</b>
					</td>																				</tr>
				<tr>
					<td>
						<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
							<tbody>
								<tr style="background-color : #cccccc;">
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.periodo" /></td>
									<td class="smalltableheader" style="width : 20%;"><bean:message key="ctaCteN2.table.header.haber" /></td>
								</tr>
								<logic:iterate id="detalle" name="detalles" scope="request">						
								<tr>
									<td class="smallprompt" >
										<bean:define id="parametros" name="detalle" property="params"></bean:define>
											<html:link page="/ctaCteN3.do" name="parametros">
												<bean:define id="periodo" name="detalle" property="periodo" />
												<bean:write name="periodo" property="formattedPeriodo" />
											</html:link>																
									</td>
									<td class=smallcurrency style="vertical-align : top;">
										<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
									</td>
								</tr>
								</logic:iterate>
								
								<tr>
									<td colspan="5" style="height : 1px; background-color : #006666;"></td>
								</tr>

							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</logic:equal>															
</logic:notEmpty>