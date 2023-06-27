<?xml version="1.0" encoding="ISO-8859-1" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<link href="../../../../../theme/Master.css" rel="stylesheet" type="text/css" />
<title>detalleTabla.jsp</title>
</head>
<body>

	<logic:notEmpty name="detalles" scope="request">										
	<table style="width : 460; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.402.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 10%;"><bean:message key="liquidacion.402.nivel2.table.header.item" /></td>
								<td class="smalltableheader" style="width : 70%;"><bean:message key="liquidacion.402.nivel2.table.header.concepto" /></td>
								<td class="smalltableheader" style="width : 30%;"><bean:message key="liquidacion.402.nivel2.table.header.monto" /></td>
							</tr>

							<tr>
								<td class="smallcurrency">1</td>
								<td class="smallprompt">
									Asignaci&oacute;n Familiar Emitida para el mes por la Empresa
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoEmitido" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">2</td>
								<td class="smallprompt">
									Asignaci&oacute;n Familiar Compensada por la Empresa en Declaraci&oacute;n y Pago
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoCompensado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">3</td>
								<td class="smallprompt">
									Asignaci&oacute;n Familiar Aceptada en la Cotizaci&oacute;n a la Empresa
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoAceptado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">4</td>
								<td class="smallprompt">
									Asignaci&oacute;n Familiar Informada por la Empresa en Anexo de Trabajadores
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoInformado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">5</td>
								<td class="smallprompt">
									Asignaci&oacute;n Familiar no Pagada a la Empresa (2-3)
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoNoPagado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">6</td>
								<td class="smallprompt">
									Total Diferencia por Inconsistencia de Informaci&oacute;n Entregada (4-2)
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoDiferenciaInconsistencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">7</td>
								<td class="smallprompt">
									Total Asignaci&oacute;n Familiar Reconocida y Autorizada en Definitiva por la Caja (suma en asignaciones individuales reconocidas por la Caja seg&uacute;n anexos entregadis por la empresa)								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoAutorizado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">8</td>
								<td class="smallprompt">
									Mayor Valor Compensado y No Autorizado por la Caja (7-2)
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoMayorValor" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallcurrency">9</td>
								<td class="smallprompt">
									Diferencia por Cobrar (8+5)
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="montoDiferenciaCobrar" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>

							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
							</tr>							
							<!--
							<tr style="background-color : #cccccc;">
								<td class="smallprompt" colspan="2">
									<bean:message key="liquidacion.402.nivel2.table.header.abonosAnteriores" />
								</td>
								<td class="smallcurrency">
									<bean:message key="liquidacion.402.nivel2.table.header.monto" />
								</td>
							</tr>
							<logic:iterate id="detalle" name="abonos" scope="request">							
							<tr>
								<td class="smallprompt" colspan="2">
									<bean:write name="detalle" property="fecha.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
							</tr>							

							<tr>
								<td class="smallprompt" colspan="2">
									<bean:message key="liquidacion.402.nivel2.table.label.totalAbonosAnteriores" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="total" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
							<tr>
								<td class="smallprompt" colspan="2">
									<bean:message key="liquidacion.402.nivel2.table.label.saldoPendienteLiquidacion" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="saldoPendiente" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>

							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
							</tr>							
							-->
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
</body>
</html>
