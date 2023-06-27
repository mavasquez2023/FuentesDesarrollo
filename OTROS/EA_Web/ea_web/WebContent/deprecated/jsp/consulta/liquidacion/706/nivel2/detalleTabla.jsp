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

	<logic:notEmpty name="detalle" scope="request">
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
									<bean:write name="detalle" property="cotizacionInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="cotizacionCalculada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="cotizacionDiferencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
					
							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.b" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="asignacionFamiliarInformada" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td class="smallcurrency" >
									<bean:write name="detalle" property="asignacionFamiliarAceptada" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="asignacionFamiliarDiferencia" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>

							<tr>
								<td class="smallprompt" >
									<bean:message key="liquidacion.706.nivel2.table.label.c" />
								</td>
								<td class="smallcurrency" >
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="gravamenes" value="0">
										<bean:write name="detalle" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
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
									<logic:notEqual name="detalle" property="saldoCajaPlanilla" value="0">
									<!--
										<bean:write name="detalle" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									-->
									</logic:notEqual></td>
								<td class="smallcurrency" >
								<logic:notEqual name="detalle" property="saldoCajaCalculado" value="0">
										<bean:write name="detalle" property="saldoCajaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoDiferenciaCaja" value="0">
										<!--
										<bean:write name="detalle" property="saldoDiferenciaCaja" filter="true" formatKey="global.monto" ignore="true" />
										-->
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td class="smallprompt" >
									<span style="margin-left: 10px;">
										<bean:message key="liquidacion.706.nivel2.table.label.saldoHaber" />
									</span>
								</td>
								<td class="smallcurrency" >
								<logic:notEqual name="detalle" property="saldoEmpresaPlanilla" value="0">
										<bean:write name="detalle" property="saldoEmpresaPlanilla" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual></td>
								<td class="smallcurrency" >
								<logic:notEqual name="detalle" property="saldoEmpresaCalculado" value="0">
										<bean:write name="detalle" property="saldoEmpresaCalculado" filter="true" formatKey="global.monto" ignore="true" />
									</logic:notEqual>
								</td>
								<td class="smallcurrency" >
									<logic:notEqual name="detalle" property="saldoDiferenciaEmpresa" value="0">
										<!--
										<bean:write name="detalle" property="saldoDiferenciaEmpresa" filter="true" formatKey="global.monto" ignore="true" />
										-->
									</logic:notEqual>
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
									<bean:write name="detalle" property="saldoDeber" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="saldoHaber" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
								<td class="smallcurrency" >
									<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
								</td>																				
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</logic:notEmpty>
</body>
</html>
