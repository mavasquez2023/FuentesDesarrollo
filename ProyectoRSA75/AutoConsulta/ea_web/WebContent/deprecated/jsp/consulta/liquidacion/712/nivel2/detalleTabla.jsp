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
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
		
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.712.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>
			</tr>
			
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 10%;"></td>
								<td class="smalltableheader" style="width : 70%;"><bean:message key="liquidacion.712.nivel2.table.header.detalle" /></td>
								<td class="smalltableheader" style="width : 30%;"><bean:message key="liquidacion.712.nivel2.table.header.monto" /></td>
							</tr>
							
							<tr>
								<td class="smallprompt">
									1
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.1" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoAproteCotizacion" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									2
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.2" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoAsignacionFamiliarCompensada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									3
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.3" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="saldoFavorEmpresa" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">									
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.descuentos" />
								</td>
								<td class="smallcurrency">
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									4
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.4" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoNominaCredito" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									5
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.5" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoNominaLeasing" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									6
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.6" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoOtros" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									7
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.7" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoTotalDescuento" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
				
								</td>
								<td class="smallprompt">
									<bean:message key="liquidacion.712.nivel2.table.label.8" />
								</td>
								<td class="smallcurrency">
									<bean:write name="cotizacion" property="montoTotalCompensado" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>


							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
							</tr>							
							
							<tr style="background-color : #cccccc;">
								<td class="smallprompt" colspan="2">
									<bean:message key="liquidacion.712.nivel2.table.header.abonosAnteriores" />
								</td>
								<td class="smallcurrency">
									<bean:message key="liquidacion.712.nivel2.table.header.monto" />
								</td>
							</tr>
							<logic:iterate id="detalle" name="detalles" scope="request">							
							<tr>
								<td class="smallprompt" colspan="2">
									<bean:define id="dtoFecha" name="detalle" property="fecha" />
									<bean:write name="dtoFecha" property="fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
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
									<bean:message key="liquidacion.712.nivel2.table.label.totalAbonosAnteriores" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="total" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
							<tr>
								<td class="smallprompt" colspan="2">
									<bean:message key="liquidacion.712.nivel2.table.label.saldoPendiente" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="saldoPendiente" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>

							<tr>
								<td colspan="3" style="height : 1px; background-color : #006666;"></td>
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
</body>
</html>
