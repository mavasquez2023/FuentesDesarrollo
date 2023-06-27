<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<style>
.nota {color: #333366; text-align: justify; vertical-align: top; font:8.5pt tahoma;}
</style>

<script>
// copia los parametros a los campos ocultos y submite este formulario
function perform(key) {
	var f = document.forms[0];
	f.folioMovimiento.value = key;
	f.idChanged.value = "S";
	f.submit();
	return true;
} 
</script>
<!-- hay mas de un cheque -->

<html:form action="/liquidacion/751N2" scope="request" method="post">
	<logic:notEmpty name="listaCheques" scope="request">										
	<table style="width : 100%; border : 1px solid #333366;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<!--<bean:message key="liquidacion.751.nivel2.table.header" />-->
						Cheques Reemplazados&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.751.nivel2.table.header.numeroCheque" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.751.nivel2.table.header.banco" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.751.nivel2.table.header.fechaEmision" /></td>
								<td class="smalltableheader" style="width : 25%;"><bean:message key="liquidacion.751.nivel2.table.header.monto" /></td>
							</tr>
							
							<logic:iterate id="detalle" name="listaCheques" scope="request">							
							<tr>
								<td class="smallprompt">
									<span style="margin-left: 3px;">
										<a href="" onclick="perform('<bean:write name="detalle" property="folioComprobante" scope="page" filter="true" ignore="true" />'); 
															return;"> 
											<bean:write name="detalle" property="numeroCheque" scope="page" filter="true" ignore="true" />
										</a>									
									</span>
								</td>
								<td class="smallprompt">
									<span style="margin-left: 3px;">
										<bean:write name="detalle" property="banco" scope="page" filter="true" ignore="true" />
									</span>
								</td>
								<td class="smallprompt" style="text-align: center;">
									<bean:write name="detalle" property="fechaEmision.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
								</td>
								<td class="smallcurrency">
									<span style="margin-right: 5px;">
										<bean:write name="detalle" property="montoEndosado" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>
							</logic:iterate>

							<!-- No va esta seccion 
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.751.nivel2.table.label.totales" />
								</td>
								<td></td>
								<td></td>
								<td class="smallcurrency">
									<bean:write name="total" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							-->
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</logic:notEmpty>
	<html:hidden property="codigoOficina" />
	<html:hidden property="codigoSucursal" />
	<html:hidden property="codigoConcepto" />
	<html:hidden property="periodoLiquidacion" />
	<html:hidden property="fechaLiquidacion" />
	<html:hidden property="periodoRemuneracion" />
	<html:hidden property="idChanged" />
	<html:hidden property="folioMovimiento" />
</html:form>

<!-- hay solo un cheque reemplazado -->	
	<logic:notEmpty name="cheque" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						Cheque reemplazado&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 1px solid #cccc99; border-color : #cccccc; padding-left: 0px; padding-right: 0px; border-spacing: 1px">
						<tbody>
							<tr>
								<td class="smallprompt" style="width : 25%;">
									<bean:message key="documentoPago.table.label.banco" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:write name="cheque" property="banco" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:message key="documentoPago.table.label.cuentaCorriente" />
								</td>
								<td class="smallprompt" style="width : 25%;">
									<bean:write name="cheque" property="numeroCuentaCorriente" scope="request" filter="true" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.fechaEmision" />
								</td>
								<td class="smallprompt">
									<bean:write name="cheque" property="fechaEmision.fecha" scope="request" filter="true" formatKey="global.date" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.folio" />
								</td>
								<td class="smallprompt">
									<bean:write name="cheque" property="folioComprobante" scope="request" filter="true" ignore="true" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.numeroCheque" />
								</td>
								<td class="smallprompt">
									<bean:write name="cheque" property="numeroCheque" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.monto" />
								</td>
								<td class="smallcurrency">
									<span style="margin-right: 20px;">
										<bean:write name="cheque" property="montoEndosado" scope="request" filter="true" formatKey="global.monto" ignore="true" />
									</span>
								</td>
							</tr>

							<tr>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.estadoCheque" />
								</td>
								<td class="smallprompt">
									<bean:write name="cheque" property="estadoCheque" scope="request" filter="true" ignore="true" />
								</td>
								<td class="smallprompt">
									<bean:message key="documentoPago.table.label.fechaCobro" />
								</td>
								<td class="smallprompt">
									<logic:empty name="cheque" property="fechaCobro" scope="request">
										<bean:write name="cheque" property="fechaCobro" scope="request" filter="true" formatKey="global.date" ignore="true" />
									</logic:empty>
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
	
<logic:equal name="hayDetalle" value="false" scope="request">
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
