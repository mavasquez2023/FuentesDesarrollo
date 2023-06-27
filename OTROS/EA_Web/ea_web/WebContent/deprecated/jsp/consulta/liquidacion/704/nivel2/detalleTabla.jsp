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

	<logic:notEmpty name="resumenCotizacion" scope="request">
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr style="height : 18px;">
				<td class=smallfontreverse >
					<b>
						<bean:message key="liquidacion.706.nivel2.table.header" />&nbsp;
						<bean:message key="global.text.unidadMonetaria" />
					</b>
				</td>														
			</tr>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc;" cellspacing=0 cellpadding=2>
						<tbody>
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width : 50%; text-align : left;">&nbsp;
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.empresa" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.caja" />
								</td>
								<td class="smalltableheader" style="width : 25%;">
									<bean:message key="liquidacion.706.nivel2.table.header.diferencia" />
								</td>
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.a" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="cotizacionInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="cotizacionDiferencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
					
							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.b" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="asignacionFamiliarInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="asignacionFamiliarDiferencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.c" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="resumenCotizacion" property="gravamenes" value="0">
										<bean:write name="resumenCotizacion" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
																									
							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.d" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
								</td>
							</tr>
							<tr>
								<td class="smallprompt" >
									<span style="margin-left: 10px;">
										<bean:message key="liquidacion.706.nivel2.table.label.saldoDeber" />
									</span>
								</td>
								<td class="smallcurrency" >
								<logic:notEqual name="resumenCotizacion" property="saldoCajaPlanilla" value="0">
										<bean:write name="resumenCotizacion" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual></td>
								<td class="smallcurrency" >
								<logic:notEqual name="resumenCotizacion" property="saldoCajaCalculado" value="0">
										<bean:write name="resumenCotizacion" property="saldoCajaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								<!--
									<logic:notEqual name="resumenCotizacion" property="saldoDiferenciaCaja" value="0">
										<bean:write name="resumenCotizacion" property="saldoDiferenciaCaja" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								-->
								</td>
							</tr>
							<tr>
								<td class="smallprompt" >
									<span style="margin-left: 10px;">
										<bean:message key="liquidacion.706.nivel2.table.label.saldoHaber" />
									</span>
								</td>
								<td class="smallcurrency" >
								<logic:notEqual name="resumenCotizacion" property="saldoEmpresaPlanilla" value="0">
										<bean:write name="resumenCotizacion" property="saldoEmpresaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual></td>
								<td class="smallcurrency" >
								<logic:notEqual name="resumenCotizacion" property="saldoEmpresaCalculado" value="0">
										<bean:write name="resumenCotizacion" property="saldoEmpresaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
								<!--
									<logic:notEqual name="resumenCotizacion" property="saldoDiferenciaEmpresa" value="0">
										<bean:write name="resumenCotizacion" property="saldoDiferenciaEmpresa" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								-->
								</td>
							</tr>
							<tr>
								<td colspan="4" style="height : 1px; background-color : #006666;"></td>
							</tr>

							<tr>
								<td class="smalltableheader" style="text-align : left;">
									<bean:message key="liquidacion.706.nivel2.table.label.saldoFinal" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="saldoDeber" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="saldoHaber" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="resumenCotizacion" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</logic:notEmpty>
	
	<p />
	
	<logic:notEmpty name="abonos" scope="request">										
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
						
							<tr style="background-color : #cccccc;">
								<td class="smalltableheader" style="width: 70%"><bean:message key="liquidacion.704.nivel2.table.header.abonosAnteriores" /></td>
								<td class="smalltableheader"><bean:message key="liquidacion.704.nivel2.table.header.monto" /></td>
							</tr>

							<logic:iterate id="detalle" name="abonos" scope="request">							
							<tr>
								<td class="smallprompt">
									<bean:write name="detalle" property="fecha.fecha" scope="page" filter="true" formatKey="global.fecha" ignore="true" />
								</td>
								<td class="smallcurrency">
									<bean:write name="detalle" property="monto" filter="true" formatKey="global.monto" ignore="true" />
								</td>
							</tr>
							</logic:iterate>

							<tr>
								<td colspan="2" style="height : 1px; background-color : #006666;"></td>
							</tr>							

						</tbody>
					</table>
				</td>
			</tr>

		</tbody>
	</table>
	</logic:notEmpty>	

	<p />
	<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
		<tbody>
			<tr>
				<td>
					<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
						<tbody>
							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.704.nivel2.table.label.total" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="total" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
							<tr>
								<td class="smallprompt">
									<bean:message key="liquidacion.704.nivel2.table.label.saldoPendiente" />
								</td>
								<td class="smallcurrency">
									<bean:write name="resumen" property="saldoPendiente" filter="true" formatKey="global.monto" ignore="true" />									
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

		</tbody>
	</table>			
</body>
</html>
