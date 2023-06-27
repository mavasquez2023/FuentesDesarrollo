<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>

<logic:notEmpty name="lista" scope="request">										
<table style="width : 300px; border : 0px;" cellpadding=0 cellspacing=0 >
	<tbody>
	
		<tr style="height : 18px;">
			<td class=smallfontreverse >
				<b>
					<bean:message key="liquidacion.707.nivel1.table.header" />&nbsp;
					<bean:message key="global.text.unidadMonetaria" />
				</b>
			</td>
		</tr>
		
		<tr>
			<td>
				<table style="width : 300px; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
					<tbody>
					
						<tr style="background-color : #cccccc;">
							<td class="smalltableheader" style="width : 150px; text-align: center;">
								<bean:message key="liquidacion.707.nivel1.table.header.periodo" />
							</td>
							<td class="smalltableheader" style="width : 150px; text-align: center;">
								<bean:message key="liquidacion.707.nivel1.table.header.monto" />
							</td>
						</tr>
						
						<bean:define id="cod" name="concepto" property="codigo" scope="request" />
						<logic:iterate id="detalle" name="lista" scope="request">							
						<tr>
							<td class="smallprompt" style="text-align: center;">
								<bean:define id="parametros" name="detalle" property="params" />
								<bean:define id="dtoPeriodo" name="detalle" property="periodo" />
									<logic:equal name="detalle" property="tieneSiguienteNivel" scope="page" value="true">
										<html:link action='<%=(new java.lang.String("liquidacion/")) + cod.toString() + (new java.lang.String("N2"))%>' name="parametros">
											<bean:write name="dtoPeriodo" property="formattedPeriodo" scope="page" />
										</html:link>	
									</logic:equal>													
									<logic:equal name="detalle" property="tieneSiguienteNivel" scope="page" value="false">
										<bean:write name="dtoPeriodo" property="formattedPeriodo" scope="page" />
									</logic:equal>													
							</td>
							<td class="smallcurrency">
								<span style="margin-right: 10px;">
									<bean:write name="detalle" property="montoAPagar" filter="true" formatKey="global.monto" ignore="true" />
								</span>
							</td>
						</tr>
						</logic:iterate>

						<tr>
							<td colspan="2" style="height : 1px; background-color : #006666;"></td>
						</tr>							

						<tr>
							<td class="smallprompt">
								<bean:message key="liquidacion.707.nivel1.table.label.total" />
							</td>
							<td class="smallcurrency">
								<span style="margin-right: 10px;">
									<bean:write name="total" filter="true" formatKey="global.monto" ignore="true" />
								</span>
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
<p />
</logic:notEmpty>
	
<logic:equal name="concepto" property="tieneSubsistema" value="false" scope="request">
	<table border="0" cellspacing="1" cellpadding="0">
		<tbody>
			<tr style="height : 20px;">
				<td>
					<html:img alt="" height="1" width="4" page="/img/c.gif"/>
				</td>
			</tr>
			
			<tr>
				<td class="nota" width="430px">
					<bean:write name="concepto" property="mensaje" scope="request" />
				</td>
			</tr>
			<tr><td style="height : 14px;"><html:img alt="" height="1" width="4" page="/img/c.gif"/></td></tr>
			<tr>
				<td>
				</td>
			</tr>
		</tbody>
	</table>
</logic:equal>