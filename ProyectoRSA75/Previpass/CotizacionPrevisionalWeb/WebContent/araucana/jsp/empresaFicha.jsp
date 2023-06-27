<%@ include file="/html/comun/taglibs.jsp"%>
<html:form action="/DetalleEmpresa" styleId="formulario" method="get">
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
							<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle Empresa</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="tabla-datos">
						<tr>
							<td width="19%" height="17" class="barra_tablas">RUT</td>
							<td height="17" class="textos_formularios"><nested:hidden property="rutEmpresaEd" /> <nested:write property="rutEmpresaEd" /></td>
							<td height="17" class="barra_tablas"><strong>Raz&oacute;n Social</strong></td>
							<td height="17" class="textos_formularios"><nested:write property="razonSocial" /></td>
						</tr>
						<%--><tr>
							<td height="17" class="barra_tablas">C&oacute;digo:</td>
							<td height="17" class="textos_formularios"><nested:write
								property="codigoRubro" /></td>
							<td height="17" class="barra_tablas"><strong>Rubro:</strong></td>
							<td height="17" class="textos_formularios"><nested:write
								property="nombreRubro" /></td>
						</tr><--%>
						<tr>
							<td height="17" class="barra_tablas">RUT Rep. Legal</td>
							<td height="17" class="textos_formularios"><nested:write
								property="idRepLegal" /></td>
							<td height="17" class="barra_tablas"><strong>Nombre</strong></td>
							<td height="17" class="textos_formularios"><nested:write
								property="nombreCompletoRepLegal" /></td>
						</tr>
						<tr>
							<td height="17" class="barra_tablas">Vigencia Rep. Legal</td>
							<td width="19%" height="17" class="textos_formularios"><nested:write
								property="vigenciaRepLegal" /></td>
							<td width="16%" height="17" class="barra_tablas">Tipo</td>
							<td height="17" class="textos_formularios"><nested:equal
								property="tipoEmpresa" value="0">
											Privada
										</nested:equal> <nested:equal property="tipoEmpresa" value="1">
											P&uacute;blica
										</nested:equal></td>
						</tr>
						<tr>
							<td height="4" colspan="4" bgcolor="#85b4be"></td>
						</tr>
						<tr>
							<td height="17" class="barra_tablas" colspan="4"><strong>Instituci&oacute;n
							Accidente del Trabajo</strong></td>
						<tr>
						<tr>
							<td height="17" class="barra_tablas">Nombre</td>
							<td height="17" colspan="3" class="textos_formularios"><nested:write
								property="nombreMutual" /></td>
						</tr>
						<tr>
							<td height="17" class="barra_tablas">N&ordm; Adherentes</td>
							<td height="17" class="textos_formularios">
								<div align="right">
									<nested:write property="numAdherentesMutual" />&nbsp;
								</div>
							</td>
							<td height="17" class="barra_tablas">
								<strong>C&aacute;lculo Individual</strong>
							</td>
							<td height="17" class="textos_formularios">
								<nested:equal property="opcCalculoIndividual" value="true">
									Si
								</nested:equal>
								<nested:equal property="opcCalculoIndividual" value="false">
									No
								</nested:equal>&nbsp;
							</td>
						</tr>
						<tr>
							<td height="17" class="barra_tablas">Tasa Adicional</td>
							<td height="17" class="textos_formularios">
								<div align="right">
									<nested:write format="#0.0##" property="tasaAdicionalMutual" />&nbsp;
								</div>
							</td>
							<td height="17" class="barra_tablas">&nbsp;</td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr>
					<td width="12%" height="17" class="barra_tablas">C&oacute;digo</td>
					<td height="17" class="textos_formularios"><nested:write property="codigoCasaMatriz" /></td>
					<td width="12%" height="17" class="barra_tablas"><strong>Nombre</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCasaMatriz" /></td>
				</tr>
				<tr>
					<td height="17" class="barra_tablas">Direcci&oacute;n</td>
					<td height="17" class="textos_formularios"><nested:write property="direccionCasaMatriz" />&nbsp;</td>
					<td height="17" class="barra_tablas"><strong>Nº</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="noCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td width="9%" height="17" class="barra_tablas">Departamento</td>
					<td width="18%" class="textos_formularios"><nested:write property="dptoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barra_tablas">Regi&oacute;n</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreRegionCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barra_tablas"><strong>Ciudad</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCiudadCasaMatriz" />&nbsp;</td>
					<td height="17" class="barra_tablas">Comuna</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreComunaCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barra_tablas">Tel&eacute;fono</td>
					<td height="17" class="textos_formularios">
					(<nested:write
						property="codigoAreaCasaMatriz" />)
					<nested:write
						property="fonoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barra_tablas"><strong>Celular</strong>
					</td>
					<td height="17" class="textos_formularios">
						<nested:notEqual property="celCasaMatriz" value="0">
							<nested:write property="celCasaMatriz" />
						</nested:notEqual>&nbsp;
					</td>
				</tr>
				<tr>
					<td height="17" class="barra_tablas">Fax</td>
					<td height="17" class="textos_formularios">
					<nested:present property="codigoAreaFaxCasaMatriz">
					(<nested:write
						property="codigoAreaFaxCasaMatriz" />)
					</nested:present>
					<nested:write property="faxCasaMatriz" />&nbsp;</td>
					<td height="17" class="barra_tablas">e-mail</td>
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
			<nested:equal property="editable" value="true">
				<html:submit property="operacion" value="${editar}" styleClass="btn_grande" />
			</nested:equal>
			<html:submit property="operacion" value="${verSucursales}" styleClass="btn_grande" />
			<html:submit property="operacion" value="${verConvenios}" styleClass="btn_grande" /></td>
		</tr>
	</table>
</html:form>
