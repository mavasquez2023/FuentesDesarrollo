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
<style>
.header {
	font-family: Verdana, Arial, sans-serif;
	font-size: large;
	font-weight: bold;
	font-stretch: expanded;
	word-spacing: 5px;
	text-decoration: underline;
}

.smallheader {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
}

.labelNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	text-decoration: none;
}

.textNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
}

.normalBold {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
}

.normalSmall {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 10px;
}

.numberNormal {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
	text-align: right;
	text-decoration: none;
}

.numberBold {
	color: #000033;
	font-family: Verdana, Arial, sans-serif;
	font-size: 12px;
	font-weight: bold;
	text-align: right;
	text-decoration: none;
}

.buttonRed {
	background-color: red; 
	color: white; 
	border-color: yellow; 
	border-left-style: groove; 
	font-family: verdana; 
	font-size: 8px; 
	font-weight: bold;
}
</style>
<link href="<%=request.getContextPath()%>/theme/Master.css" rel="stylesheet" type="text/css" />
<title>Empresas Adherente/Crédito</title>
</head>
<body>

<table style="width: 100%;" border="0">
<tbody>
	<tr>
		<td style="width: 2%;">&nbsp;</td>
		<td>

			
<table border="0" width="100%;">
	<tr>
		<td style="width: 100%;">
			<table border="0" width="100%;">
				<tr>
					<td class="smallheader" style="width: 30%;">La Araucana C.C.A.F<br /> Depto. Crédito</td>
					<td style="width: 40%;">&nbsp;</td>
					<td style="width: 30%; text-align: center;">
						<html:image property="logo"	page="/img/logoCertificado.jpg" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td></td></tr>
	<tr>
		<td style="width: 100%;">
			<table border="0" width="100%;">
				<tr>
					<td style="width: 10%;">&nbsp;</td>
					<td class="header" style="width: 80%; text-align: center; vertical-align: top;">Informe de Crédito (Monto $)</td>
					<td style="width: 10%;">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr><td></td></tr>
	<tr>
		<td style="width: 100%;">
			<table border="0" width="100%;">
				<tr>
					<td>
						<table border="0" width="100%">
							<tr>
								<td class="labelNormal" style="width: 20mm;">
									<bean:message key="global.text.afiliado" />:
								</td>
								<td style="width: 3mm;"></td>
								<td class="textNormal" style="width: 50mm;">
									<bean:write name="afiliado" property="apellidoPaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="apellidoMaterno" scope="request" />&nbsp;
									<bean:write name="afiliado" property="nombre" scope="request" />
								</td>
								<td style="width: 5mm;"></td>
								<td class="labelNormal" style="width: 10mm;">
									<bean:message key="global.text.rut" />:
								</td>
								<td style="width: 3mm;"></td>
								<td class="textNormal" style="width: 50mm;">
									<bean:write name="afiliado" property="rut.formattedRut" scope="request" />-
									<bean:write name="afiliado" property="rut.dv" scope="request" />
								</td>
								<td></td>
							</tr>
							<tr>
								<td class="labelNormal">
									<bean:message key="global.text.empresa" />:
								</td>
								<td></td>
								<td class="textNormal">
									<bean:write name="empresa" property="nombre" scope="request" />
								</td>
								<td></td>
								<td class="labelNormal">
									<bean:message key="global.text.rut" />:
								</td>
								<td></td>
								<td class="textNormal">
									<bean:write name="empresa" property="rut.formattedRut" scope="request" />-
									<bean:write name="empresa" property="rut.dv" scope="request" />
								</td>
								<td></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr><td></td></tr>
	<tr>
		<td style="width: 100%;">
			<table border="0" width="100%;">
				<tr>
					<td>
						<table style="width: 100%;" border="0">
						<tr>
							<td class="labelNormal" style="width: 40mm; text-align: left;">
								<bean:message key="tablaDesarrollo.label.fechaOtorgamiento" />:
							</td>
							<td style="width: 2mm;"></td>
							<td class="textNormal" style="width: 30mm;">
								<bean:write name="resumen" property="fechaOtorgamiento.fecha" scope="request" filter="true" formatKey="global.fecha" ignore="true" />
							</td>
							<td></td>
						</tr>		
						<tr>
							<td class="labelNormal">
								<bean:message key="deudaCreditoN2.label.folio" />:
							</td>
							<td></td>
							<td class="textNormal">
								<bean:write name="tablaDesarrolloCreditoForm" property="codigoOficinaProceso" ignore="true" />- 
								<bean:write name="tablaDesarrolloCreditoForm" property="folio" ignore="true" /> 
							</td>
							<td></td>
						</tr>	
						</table>	
					</td>
				</tr>
			</table>
		</td>
	</tr>	
	<tr><td></td></tr>
	<tr>
		<td style="width: 100%;">
			<table border="0" width="100%;">
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="labelNormal" style="width: 50mm;">Valor Cuota:</td>
								<td style="width: 2mm;"></td>
								<td style="width: 2mm;"></td>
								<td class="numberNormal" style="width: 20mm;">
									<bean:write name="resumen" property="valorCuota" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td></td>
							</tr>
							<tr>
								<td colspan="5">&nbsp;</td>
							</tr>
							<tr>
								<td class="labelNormal" style="width: 50mm;">Monto Solicitado</td>
								<td style="width: 2mm;"></td>
								<td style="width: 2mm;"></td>
								<td class="numberNormal" style="width: 20mm;">
									<bean:write name="resumen" property="montoSolicitado" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td></td>
							</tr>		
							<tr>
								<td class="normalSmall" colspan="5" style="color: blue; width: 5mm;">(+)</td>
								<td style="height: 2mm;"></td>							
							</tr>	
							<tr>
								<td class="labelNormal" style="text-align: right;">
									<bean:message key="tablaDesarrollo.label.seguroDesgravamen" />:
								</td>
								<td></td>
								<td></td>
								<td class="numberNormal">
									<logic:equal name="resumen" property="montoSeguroDesgravamen" scope="request" value="0">
										No
									</logic:equal>
									<logic:greaterThan name="resumen" property="montoSeguroDesgravamen" scope="request" value="0">
										S&iacute;
									</logic:greaterThan>
								</td>
								<td></td>
							</tr>		
							<tr>
								<td class="labelNormal" style="text-align: right;">
									<bean:message key="tablaDesarrollo.label.seguroCesantia" />:
								</td>
								<td></td>
								<td></td>
								<td class="numberNormal">
									<logic:equal name="resumen" property="montoSeguroCesantia" scope="request" value="0">
										No
									</logic:equal>
									<logic:greaterThan name="resumen" property="montoSeguroCesantia" scope="request" value="0">
										S&iacute;
									</logic:greaterThan>
								</td>
								<td></td>
							</tr>		
							<tr>
								<td class="labelNormal" style="text-align: right;">
									<bean:message key="tablaDesarrollo.label.seguroVida" />:
								</td>
								<td></td>
								<td></td>
								<td class="numberNormal">
									<logic:equal name="resumen" property="montoSeguroVida" scope="request" value="0">
										No
									</logic:equal>
									<logic:greaterThan name="resumen" property="montoSeguroVida" scope="request" value="0">
										S&iacute;
									</logic:greaterThan>
								</td>
								<td></td>
							</tr>		
							<tr>
								<td class="labelNormal" style="text-align: right;">
									<bean:message key="tablaDesarrollo.label.impuestoLeyTimbre" />:
								</td>
								<td></td>
								<td></td>
								<td class="numberNormal">
									<logic:equal name="resumen" property="impuesto" scope="request" value="0">
										No
									</logic:equal>
									<logic:greaterThan name="resumen" property="impuesto" scope="request" value="0">
										S&iacute;
									</logic:greaterThan>
								</td>
								<td></td>
							</tr>		
							<tr>
								<td colspan="4" style="height : 4px;"><html:img page="/img/box_top.gif" style="width: 100%; height : 4px; text-align : center; border : 0px;" />
								</td>
								<td></td>							
							</tr>		
							<tr>
								<td class="labelNormal">
									<bean:message key="tablaDesarrollo.label.totalCredito" />:
								</td>
								<td></td>
								<td></td>
								<td class="numberBold">
									<bean:write name="resumen" property="montoTotal" scope="request" filter="true" formatKey="global.monto" ignore="true" />
								</td>
								<td></td>
							</tr>		
						</table>			
					</td>
				</tr>
			</table>
		</td>
	</tr>	

	<tr><td></td></tr>
	
	<tr>
		<td>
			<logic:notEmpty name="detalles" scope="request">										
				<table style="width : 90%;" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td>
								<table style="width : 100%; border-color : #cccccc;" border="1" cellpadding="0" cellspacing="0">
									<tbody>
									
										<tr style="background-color : #cccccc;">
											<td class="smalltableheader" style="width : 10%;">
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
											<td class="smalltableheader" style="width : 20%;">
												<bean:message key="tablaDesarrollo.table.header.estado" />
											</td>
										</tr>
										
										<logic:iterate id="detalle" name="detalles" scope="request">							
										<tr>
											<td class="smallcurrency" style="text-align: center;">
												<span style="margin-right: 45%;">
													<bean:write name="detalle" property="numeroCuota" />&nbsp;
												</span>
											</td>
											<td class="smallprompt" style="text-align: center;">
												<bean:define id="dtoFecha" name="detalle" property="fechaVencimiento" />
												<bean:write name="dtoFecha" property="fecha" scope="page" filter="true"  formatKey="global.fecha" ignore="true" />
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 2mm;">
													<bean:write name="detalle" property="capital" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 2mm;">
													<bean:write name="detalle" property="interes" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 2mm;">
													<bean:write name="detalle" property="saldo" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallprompt" style="text-align: center;">
												<bean:write name="detalle" property="estado" />&nbsp;
											</td>
										</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>		
			</logic:notEmpty>			

		</td>
	</tr>

</table>


		</td>
		<td style="width: 2%;">&nbsp;</td>
	</tr>
</tbody>
</table>

</body>
</html>
