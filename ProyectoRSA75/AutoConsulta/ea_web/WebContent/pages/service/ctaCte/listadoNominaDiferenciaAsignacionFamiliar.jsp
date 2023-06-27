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

<link href="/ea/edocs/css/ar_edocs.css" rel="stylesheet" type="text/css">
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
								<td class="smallheader" style="width: 30%;">La Araucana C.C.A.F<br /> Depto. Beneficios</td>
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
								<td class="header" style="width: 80%; text-align: center; vertical-align: top;">Detalle de Mayor Compensaci&oacute;n por Afiliado</td>
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
											<td class="labelNormal" style="width: 25%;">
												<bean:message key="global.text.empresa" />:
											</td>
											<td></td>
											<td class="textNormal">
												<bean:write name="empresa" property="nombre" scope="request" />
											</td>
										</tr>
										<tr>
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
										<tr>
											<td class="labelNormal">
												Sucursal
											</td>
											<td></td>
											<td class="textNormal">
												<bean:write name="sucursal" property="codigo" scope="request" />-
												<bean:write name="sucursal" property="nombre" scope="request" />
											</td>
											<td></td>
										</tr>
										<tr>
											<td class="labelNormal">
												Oficina de Adhesi&oacute;n
											</td>
											<td></td>
											<td class="textNormal">
												<bean:write name="oficina" property="codigo" scope="request" />-
												<bean:write name="oficina" property="nombre" scope="request" />
											</td>
											<td></td>
										</tr>										
										<tr>
											<td class="labelNormal">
												Per&iacute;odo
											</td>
											<td></td>
											<td class="textNormal">
												<bean:write name="periodoRemuneracion" property="formattedPeriodo" scope="request" />
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
											<td class="labelNormal" style="width: 25%; text-align: left;">
												Fecha Emisi&oacute;n
											</td>
											<td style="width: 2mm;"></td>
											<td class="textNormal" style="width: 30mm;">
												<bean:write name="fechaActual" scope="request" filter="true" formatKey="global.fecha" ignore="true" />
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
				<tr><td>
				<logic:notEmpty name="detalles" scope="request">										
				<table style="width : 100%; border : 0px;" cellpadding=0 cellspacing=0 >
					<tbody>
					
						<tr style="height : 18px;">
							<td class=smallfontreverse >
								<b>
									<bean:message key="ctaCte.402.nivel2.table.header" />&nbsp;
									<bean:message key="global.text.unidadMonetaria" />
								</b>
							</td>
						</tr>
						
						<tr>
							<td>
								<table style="width : 100%; border : 0px; border-color : #cccccc; padding-left: 2px; padding-right: 2px; border-spacing: 1px">
									<tbody>
									
										<tr style="background-color : #cccccc;">
											<td class="smalltableheader" style="width : 61%;" colspan="2"><bean:message key="ctaCte.402.nivel2.table.header.afiliado" /></td>
											<td class="smalltableheader" style="width : 13%;"><bean:message key="ctaCte.402.nivel2.table.header.montoCompensado" /></td>
											<td class="smalltableheader" style="width : 13%;"><bean:message key="ctaCte.402.nivel2.table.header.montoAutorizado" /></td>
											<td class="smalltableheader" style="width : 13;"><bean:message key="ctaCte.402.nivel2.table.header.montoDiferencia" /></td>
										</tr>
										
										<logic:iterate id="detalle" name="detalles" scope="request">							
										<tr>
											<td class="smallprompt" style="width : 20%; text-align: right; vertical-align: top;">
												<span style="margin-right: 5mm;">
													<bean:write name="detalle" property="afiliado.rut.formattedRut" scope="page" />-
													<bean:write name="detalle" property="afiliado.rut.dv" scope="page" />
												</span>
											</td>
											<td class="smallprompt" style="">
												<span style="margin-left: 5mm;">
													<bean:write name="detalle" property="afiliado.apellidoPaterno" scope="page" />&nbsp;
													<bean:write name="detalle" property="afiliado.apellidoMaterno" scope="page" />&nbsp;
													<bean:write name="detalle" property="afiliado.nombre" scope="page" />
												</span>
											</td>
											<td class="smallcurrency" style="vertical-align: top;">
												<span style="margin-right: 5mm;">
													<bean:write name="detalle" property="montoCompensado" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallcurrency" style="vertical-align: top;">
												<span style="margin-right: 5mm;">
													<bean:write name="detalle" property="montoAutorizado" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallcurrency" style="vertical-align: top;">
												<span style="margin-right: 5mm;">
													<bean:write name="detalle" property="montoDiferencia" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
										</tr>
										</logic:iterate>
				
										<tr>
											<td colspan="5" style="height : 1px; background-color : #006666;"></td>
										</tr>							
				
										<tr>
											<td class="smallprompt" colspan="2">
												<bean:message key="ctaCte.402.nivel2.table.label.totales" />
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 5mm;">
													<bean:write name="resumen" property="totalMontoCompensado" filter="true" formatKey="global.monto" ignore="true" />
												</span>
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 5mm;">
													<bean:write name="resumen" property="totalMontoAutorizado" filter="true" formatKey="global.monto" ignore="true" />									
												</span>
											</td>
											<td class="smallcurrency">
												<span style="margin-right: 5mm;">
													<bean:write name="resumen" property="totalMontoDiferencia" filter="true" formatKey="global.monto" ignore="true" />									
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
				</logic:notEmpty>
				</td></tr>
				
			</table>
		</td>
		<td style="width: 2%;">&nbsp;</td>
	</tr>
</tbody>
</table>

</body>
</html>
