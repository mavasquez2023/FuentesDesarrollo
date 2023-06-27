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
	font-size: 18px;
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
								<td class="smallheader" style="width: 30%;">
									La Araucana C.C.A.F<br /> Depto. Crédito
								</td>
								<td style="width: 40%;">
								</td>
								<td style="width: 30%; text-align: right;">
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
								<td class="header" style="width: 80%; text-align: center; vertical-align: top;">
									Certificado Saldo Deuda Capital	s&oacute;lo para Finiquito
									<!-- N&deg; <bean:write name="folioCertificado" scope="request" /> -->
								</td>
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
											<td style="width: 5mm;">:</td>
											<td class="textNormal" style="width: 50mm;">
												<bean:write name="afiliado" property="rut.formattedRut" scope="request" />-
												<bean:write name="afiliado" property="rut.dv" scope="request" />
											</td>
											<td style="width: 3mm;"></td>
											<td class="textNormal" style="width: 50mm;">
												<bean:write name="afiliado" property="apellidoPaterno" scope="request" />&nbsp;
												<bean:write name="afiliado" property="apellidoMaterno" scope="request" />&nbsp;
												<bean:write name="afiliado" property="nombre" scope="request" />
											</td>
										</tr>
										<tr>
											<td class="labelNormal">
												<bean:message key="global.text.empresa" />:
											</td>
											<td>:</td>
											<td class="textNormal">
												<bean:write name="empresa" property="rut.formattedRut" scope="request" />-
												<bean:write name="empresa" property="rut.dv" scope="request" />
											</td>
											<td></td>
											<td class="textNormal">
												<bean:write name="empresa" property="nombre" scope="request" />
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
												Fecha Finiquito:
											</td>
											<td style="width: 2mm;"></td>
											<td class="textNormal" style="width: 30mm;">
												<bean:write name="fechaFiniquito" property="fecha" scope="request" filter="true" formatKey="global.fecha" ignore="true" />
											</td>
											<td></td>
										</tr>		
										<tr>
											<td class="labelNormal" style="width: 40mm; text-align: left;">
												Fecha Solicitud:
											</td>
											<td style="width: 2mm;"></td>
											<td class="textNormal" style="width: 30mm;">
												<bean:write name="fechaActual" scope="request" filter="true" formatKey="global.date" ignore="true" />																	</td>
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
						<logic:notEmpty name="creditos" scope="request">										
							<table style="width : 90%;" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td>
											<table style="width : 100%; border-color : #cccccc;" border="1" cellpadding="0" cellspacing="0">
												<tbody>
												
													<tr style="background-color : #cccccc;">
														<td class="smalltableheader" style="width : 40%;">Folio</td>
														<td class="smalltableheader" style="width : 20%;">Cuotas</td>
														<td class="smalltableheader" style="width : 20%;">Saldo Capital</td>
														<td class="smalltableheader" style="width : 20%;">Grav&aacute;menes
														</td>
													</tr>
													
													<logic:iterate id="credito" name="creditos" scope="request">							
													<tr>
														<td class="smallcurrency" style="text-align: center;">
															<bean:write name="credito" property="codigoOficinaProceso" />-
															<bean:write name="credito" property="folio" />-
															<bean:write name="credito" property="digitoVerificador" />
														</td>
														<td class="smallprompt" style="text-align: center;">
															<bean:write name="credito" property="cuotaInicial" /> a la 
															<bean:write name="credito" property="cuotaFinal" />
														</td>
														<td class="smallcurrency">
															<span style="margin-right: 2mm;">
																<bean:write name="credito" property="saldoCapital" filter="true" formatKey="global.monto" ignore="true" />
															</span>
														</td>
														<td class="smallcurrency">
															<span style="margin-right: 2mm;">
																<bean:write name="credito" property="gravamenes" filter="true" formatKey="global.monto" ignore="true" />
															</span>
														</td>
													</tr>
													</logic:iterate>
			
													<tr>
														<td colspan="2"></td>
														<td class="labelNormal">
															Total a descontar
														</td>
														<td class="smallcurrency" style="font-size: 12px; font-weight: bold;">
															<span style="margin-right: 2mm;">
																<bean:write name="total" scope="request" filter="true" formatKey="global.monto" ignore="true" />
															</span>	
														</td>
													</tr>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>		
						</logic:notEmpty>					
					</td>
				</tr>

				<tr><td>&nbsp;</td></tr>								
				<tr><td>&nbsp;</td></tr>								

				<tr>
					<td>
						<table width="90%;">
							<tr>
								<td>
									<table style="width: 100%;" border="0">
										<tr>
											<td>
												Este certificado es v&aacute;lido s&oacute;lo si el finiquito tiene lugar 
												en la fecha indicada por la empresa.
											</td>
										</tr>		
									</table>	
								</td>
							</tr>
						</table>
					</td>
				</tr>	
				<tr>
					<td>
						<table width="90%;">
							<tr>
								<td>
									<table style="width: 100%;" border="0">
										<tr>
											<td style="width: 10px; vertical-align: top; font-family: verdana; font-size: 10px;">Nota:
											</td>
											<td style="font-family: verdana; font-size: 10px;">
												La cancelaci&oacute;n de los descuentos por finiquitos, no deben, ser
												incluidos en el pago normal de la n&oacute;mina de los cr&eacute;ditos, 
												se deben cancelar en forma directa, adjuntando a &eacute;ste documento, 
												copia del finiquito.
											</td>
										</tr>		
									</table>	
								</td>
							</tr>
						</table>
					</td>
				</tr>	

				<tr><td>&nbsp;</td></tr>								
				<tr><td>&nbsp;</td></tr>								

				<tr>
					<td>
						<table width="90%">
							<tr>
								<td style="text-align: center;">
									<html:img page="/img/firmaCredito.gif" />
								</td>
							</tr>
						</table>
					</td>
				</tr>				
				<tr><td></td></tr>				
			</table>
		</td>
		<td style="width: 2%;">&nbsp;</td>
	</tr>
</tbody>
</table>

</body>
</html>
