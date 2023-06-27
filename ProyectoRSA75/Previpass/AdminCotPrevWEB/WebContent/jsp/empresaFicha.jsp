<%@ include file="/html/comun/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
</head>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleEmpresa" styleId="formulario">
	<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
	<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaDetalle" />
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr valign="bottom">
							<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle
							Empresa</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="tabla-datos">
						<tr>
							<td width="19%" height="17" class="barratablas">RUT:</td>
							<td height="17" class="textos_formularios"><nested:hidden property="rutEmpresaEd" /> <nested:write property="rutEmpresaEd" /></td>
							<td height="17" class="barratablas"><strong>Raz&oacute;n Social:</strong></td>
							<td height="17" class="textos_formularios"><nested:write property="razonSocial" /></td>
						</tr>
						<tr>
							<td width="19%" height="17" class="barratablas">&nbsp;</td>
							<td height="17" class="textos_formularios">&nbsp;</td>
							<td height="17" class="barratablas">
								<strong>Estado:</strong>
							</td>
							<td height="17" class="textos_formularios">
								<nested:hidden property="estadoEmpresa"/>
								<nested:notEqual property="estadoEmpresa" value="0">
									Habilitada
								</nested:notEqual>
								<nested:equal property="estadoEmpresa" value="0">
									Deshabilitada
								</nested:equal>
							</td>
						</tr>
						<tr>
							<td height="17" class="barratablas">RUT Rep. Legal:</td>
							<td height="17" class="textos_formularios"><nested:write
								property="idRepLegal" /></td>
							<td height="17" class="barratablas"><strong>Nombre:</strong></td>
							<td height="17" class="textos_formularios"><nested:write
								property="nombreCompletoRepLegal" /></td>
						</tr>
						<tr>
							<td height="17" class="barratablas">Vigencia Rep. Legal:</td>
							<td width="19%" height="17" class="textos_formularios"><nested:write
								property="vigenciaRepLegal" /></td>
							<td width="16%" height="17" class="barratablas">Tipo:</td>
							<td height="17" class="textos_formularios"><nested:equal
								property="tipoEmpresa" value="0">
											Privada
										</nested:equal> <nested:equal property="tipoEmpresa" value="1">
											P&uacute;blica
										</nested:equal></td>
						</tr>
						<tr>
							<td height="17" class="barratablas">RUT Administrador:</td>
							<td height="17" class="textos_formularios">
								<nested:write property="idAdmin"/>
							</td>
							<td height="17" class="barratablas"><strong>Nombre:</strong></td>
							<td height="17" class="textos_formularios">
								<nested:write property="nombreCompletoAdmin" />
							</td>
						</tr>
						<tr>
							<td height="17" class="barratablas"><strong>C&aacute;lculo de movimiento personal:</strong></td>
							<td height="17" class="textos_formularios">
								<nested:equal property="opcCalculoMovPersonal" value="1">
									S&iacute;
								</nested:equal>
								<nested:equal property="opcCalculoMovPersonal" value="0">
									No
								</nested:equal>&nbsp;
							</td>
							<td height="17" class="barratablas">&nbsp;</td>
							<td height="17" class="textos_formularios">&nbsp;</td>
						</tr>
						<tr>
							<td height="4" colspan="4" bgcolor="#85b4be"></td>
						</tr>
						<tr>
							<td height="17" class="barratablas" colspan="4"><strong>Instituci&oacute;n Accidente del Trabajo</strong></td>
						<tr>
						<tr>
							<td height="17" class="barratablas">Nombre:</td>
							<td height="17" colspan="3" class="textos_formularios"><nested:write
								property="nombreMutual" /></td>
						</tr>
						<tr>
							<td height="17" class="barratablas">N&ordm; Adherentes:</td>
							<td height="17" class="textos_formularios">
								<div align="right">
									<nested:write property="numAdherentesMutual" />&nbsp;
								</div>
							</td>
							<td height="17" class="barratablas"><strong>C&aacute;lculo Individual:</strong>&nbsp;</td>
							<td height="17" class="textos_formularios">
								<nested:equal property="opcCalculoIndividual" value="true">Si </nested:equal>
								<nested:equal property="opcCalculoIndividual" value="false">No</nested:equal>&nbsp;
							</td>
						</tr>
						<tr>
							<td height="17" class="barratablas">Tasa Adicional:</td>
							<td height="17" class="textos_formularios">
								<div align="right">
									<nested:write format="#0.0##" property="tasaAdicionalMutual" />&nbsp;
								</div>
							</td>
							<td height="17" class="barratablas">&nbsp;</td>
							<td height="17" class="textos_formularios">&nbsp;</td>
						</tr>					
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom">
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Casa Matriz</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="tabla-datos">
				<tr>
					<td width="12%" height="17" class="barratablas">C&oacute;digo:</td>
					<td height="17" class="textos_formularios"><nested:write property="codigoCasaMatriz" /></td>
					<td width="12%" height="17" class="barratablas"><strong>Nombre:</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Direcci&oacute;n:</td>
					<td height="17" class="textos_formularios"><nested:write property="direccionCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas"><strong>Nº:</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="noCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td width="9%" height="17" class="barratablas">Departamento:</td>
					<td width="18%" class="textos_formularios"><nested:write property="dptoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">Regi&oacute;n:</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreRegionCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barratablas"><strong>Ciudad:</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCiudadCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">Comuna:</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreComunaCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Tel&eacute;fono:</td>
					<td height="17" class="textos_formularios">(<nested:write property="codigoAreaCasaMatriz" />)<nested:write property="fonoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas"><strong>Celular:</strong></td>
					<td height="17" class="textos_formularios">
						<nested:notEqual property="celCasaMatriz" value="0">
							<nested:write property="celCasaMatriz" />
						</nested:notEqual>&nbsp;
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Fax:</td>
					<td height="17" class="textos_formularios">
					<nested:present property="codigoAreaFaxCasaMatriz">
						(<nested:write property="codigoAreaFaxCasaMatriz" />)
					</nested:present>
					<nested:write property="faxCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">e-mail:</td>
					<td width="27%" height="17" class="textos_formularios"><nested:write property="emailCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="4" colspan="6" bgcolor="#85b4be"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr align="center">
			<td valign="top"><br />
			<html:submit property="operacion" value="Editar" styleClass="btn3" />
			<html:submit property="operacion" value="Ver Sucursales" styleClass="btn3" /> <html:submit property="operacion" value="Ver Convenios" styleClass="btn3" /></td>
		</tr>
	</table>
</html:form>
</body>
</html:html>
