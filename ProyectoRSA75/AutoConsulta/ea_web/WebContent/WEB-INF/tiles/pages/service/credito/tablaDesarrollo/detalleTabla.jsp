<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacionN1.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 5%;">
									<bean:message key="tablaDesarrollo.table.header.cuotaNro" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="tablaDesarrollo.table.header.fechaVencimiento" />
								</td>
								<td class="smalltableheader" style="width : 15%;">
									<bean:message key="tablaDesarrollo.table.header.capital" />
								</td>
								<td class="smalltableheader" style="width : 15%;">
									<bean:message key="tablaDesarrollo.table.header.interes" />
								</td>
								<td class="smalltableheader" style="width : 15%;">
									<bean:message key="tablaDesarrollo.table.header.saldo" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="tablaDesarrollo.table.header.estado" />
								</td>
							</tr>
							
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class=smallprompt>
									<bean:write name="detalle" property="numeroCuota" />&nbsp;
								</td>
								<td class=smallprompt>
									<bean:define id="dtoFecha" name="detalle" property="fechaVencimiento" />
									<bean:write name="dtoFecha" property="fecha" scope="page" filter="true"  formatKey="global.fecha" ignore="true" />
								</td>
								<td class=smallcurrency>
									<bean:write name="detalle" property="capital" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency>
									<bean:write name="detalle" property="interes" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallcurrency>
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class=smallprompt>
									<bean:write name="detalle" property="estado" />&nbsp;
								</td>
							</tr>
							</logic:iterate>
							<tr>
								<td colspan="6" style="height : 1px; background-color : #006666;"></td>
							</tr>
							<tr>
								<td colspan="6">
									<bean:write name="pageBar" scope="request" filter="false" />
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