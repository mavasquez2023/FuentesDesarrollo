<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>

<script>
function mensaje(s1, s2) {
	var monto = String(s1);
	var fecha = String(s2);
	
	monto = monto.replace("-", "");
	alert("Se ha efectuado un abono de $" + monto + " en la fecha " + s2);
}
</script>


<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
	<tbody>
		<tr style="height : 18px;">
			<td class=smallfontreverse >
				<b>
					<bean:message key="ctaCteN3.table.header" />
				</b>
			</td>
		</tr>
		<tr>
			<td>
				<table style="border : 0px; border-color : #cccccc;" cellspacing=0 cellpadding=2   width=100%>
					<tbody>
						<tr style="background-color : #cccccc;">
							<td class="smalltableheader" style="width : 20%;">
								<bean:message key="ctaCteN3.table.header.descripcion" />
							</td>
							<td class="smalltableheader" style="width : 20%;">
								<logic:equal name="concepto" property="tipo" scope="request" value="I">
									<bean:message key="ctaCteN3.table.header.monto" />
								</logic:equal>
								<logic:equal name="concepto" property="tipo" scope="request" value="E">
									<bean:message key="ctaCteN3.table.header.haber" />
								</logic:equal>
							</td>
						</tr>
						
						<logic:iterate id="detalle" name="detalles" scope="request">
						<tr>
							<td class="smallprompt" >
								<logic:greaterThan name="detalle" property="monto" value="0">
									<bean:define id="baseURL" name="detalle" property="baseURL" />
										<logic:equal name="concepto" property="tieneSubsistema" value="true" scope="request">
											<html:link action='<%=(String)baseURL%>' name="detalle" property="params" scope="page"> 
												<bean:write name="detalle" property="descripcion" filter="true" ignore="true" />
											</html:link>
										</logic:equal>
										<logic:equal name="concepto" property="tieneSubsistema" value="false" scope="request">
											<bean:write name="detalle" property="descripcion" filter="true" ignore="true" />
										</logic:equal>										
								</logic:greaterThan>
								<logic:lessEqual name="detalle" property="monto" value="0">
										<a onclick="mensaje('<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />', '<bean:write name="detalle" property="fecha.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />'); return false;" href="">
											<bean:write name="detalle" property="descripcion" filter="true" ignore="true" />
										</a>
								</logic:lessEqual>
							</td>
							<td class=smallcurrency style="vertical-align : top;">
								<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
							</td>																				
						</tr>
						</logic:iterate>
						
						<tr>
							<td colspan="2" style="height : 1px; background-color : #006666;"></td>
						</tr>


						<!-- Subtotal, Grávamenes -->
						<tr>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.subtotal" />
							</td>
							<td class=smallboldcurrency>
								<bean:write name="resumen" property="monto" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
						</tr>
						<tr>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.gravamenes" />
							</td>
							<td class=smallboldcurrency>
								<logic:notEqual name="resumen" property="gravamenes" scope="request" value="0">
									<bean:write name="resumen" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
								</logic:notEqual>
							</td>
						</tr>

						<tr>
							<td colspan="2" style="height : 1px; background-color : #006666;"></td>
						</tr>																				

						<tr>
							<td class=smallprompt style="text-align : right; font-weight : bold; width : 50%;">
								<bean:message key="ctaCteN3.table.label.total" />
							</td>
							<td class=smallboldcurrency>
								<bean:write name="resumen" property="total" scope="request" filter="true" formatKey="global.monto" ignore="true" />
							</td>
						</tr>																				
					</tbody>
				</table>
			</td>
		</tr>
	</tbody>
</table>

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