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
							<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle Independiente</strong></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="tabla-datos">
						<tr>
							<td width="19%" height="17" class="barratablas" style="display: none;">RUT</td>
							<td height="17" class="textos_formularios" style="display: none;"><nested:hidden property="rutEmpresaEd" /> <nested:write property="rutEmpresaEd" /></td>
							<td height="17" class="barratablas" style="display: none;"><strong>Nombre</strong></td>
							<td height="17" class="textos_formularios" style="display: none;"><nested:write property="razonSocial" /></td>
						</tr>
						<%--><tr>
							<td height="17" class="barratablas">C&oacute;digo:</td>
							<td height="17" class="textos_formularios"><nested:write
								property="codigoRubro" /></td>
							<td height="17" class="barratablas"><strong>Rubro:</strong></td>
							<td height="17" class="textos_formularios"><nested:write
								property="nombreRubro" /></td>
						</tr><--%>
						<tr>
							<td height="17" class="barratablas">RUT</td>
							<td height="17" class="textos_formularios"><nested:write
								property="idRepLegal" /></td>
							<td height="17" class="barratablas"><strong>Nombre</strong></td>
							<td height="17" class="textos_formularios"><nested:write
								property="nombreCompletoRepLegal" /></td>
						</tr>
						<tr style="display: none;">
							<td height="17" class="barratablas">Vigencia Rep. Legal</td>
							<td width="19%" height="17" class="textos_formularios"><nested:write
								property="vigenciaRepLegal" /></td>
							<td width="16%" height="17" class="barratablas">Tipo</td>
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
						
		                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n</strong></td>

						<td height="17" class="textos_formularios"><nested:write property="caja" /></td>
						
						<td height="17" class="barratablas"><strong>Tipo de Pago</strong></td>

						<td height="17" class="textos_formularios">
						<c:if test="${DetalleEmpresaActionForm.tipoPagoObligatorio != null}"><nested:write property="tipoPagoObligatorio"/></c:if>
						<c:if test="${DetalleEmpresaActionForm.tipoPagoVolountarias != null}"><nested:write property="tipoPagoVolountarias"/></c:if>						
						</td>
						

					</tr>
	          		<tr>
	          			<td height="17" class="barratablas" colspan="4"><strong>Actividad Econ&oacute;mica</strong></td>
	          		</tr>
					<tr> 
						<td height="17" class="barratablas"><strong>C&oacute;digo</strong></td>
						
						<td height="17" class="textos_formularios"><nested:write property="opcActEconomica" /></td>
						
						<td height="17" class="barratablas"><strong>Nombre</strong></td>
						
						<td height="17" class="textos_formularios"><nested:write property="nomAcEconomina" /></td>
					</tr>

					
						
						
						
						
						<tr>
							<td height="17" class="barratablas" colspan="4"><strong>Instituci&oacute;n
							Accidente del Trabajo</strong></td>
						<tr>
						<tr>
							<td height="17" class="barratablas">Nombre</td>
							<td height="17" colspan="3" class="textos_formularios"><nested:write property="nombreMutual" /></td>
						</tr>
						<tr style="display: none;">
							<td height="17" class="barratablas">N&ordm; Adherentes</td>
							<td height="17" class="textos_formularios">
								<div align="right">
									<nested:write property="numAdherentesMutual" />&nbsp;
								</div>
							</td>
							<td height="17" class="barratablas">
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
							<td height="17" class="barratablas">Tasa Adicional</td>
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
					<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Direcci&oacute;n</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
				<tr>
					<td width="12%" height="17" class="barratablas">Tipo de Direcci&oacute;n</td>
					<td height="17" class="textos_formularios"><nested:write property="codigoCasaMatriz" /></td>
					<td width="12%" height="17" class="barratablas"><strong>Descripci&oacute;n</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCasaMatriz" /></td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Direcci&oacute;n</td>
					<td height="17" class="textos_formularios"><nested:write property="direccionCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas"><strong>Nº</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="noCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td width="9%" height="17" class="barratablas">Departamento</td>
					<td width="18%" class="textos_formularios"><nested:write property="dptoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">Regi&oacute;n</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreRegionCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barratablas"><strong>Ciudad</strong></td>
					<td height="17" class="textos_formularios"><nested:write property="nombreCiudadCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">Comuna</td>
					<td height="17" class="textos_formularios"><nested:write property="nombreComunaCasaMatriz" />&nbsp;</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Tel&eacute;fono</td>
					<td height="17" class="textos_formularios">
					(<nested:write
						property="codigoAreaCasaMatriz" />)
					<nested:write
						property="fonoCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas"><strong>Celular</strong>
					</td>
					<td height="17" class="textos_formularios">
						<nested:notEqual property="celCasaMatriz" value="0">
							<nested:write property="celCasaMatriz" />
						</nested:notEqual>&nbsp;
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Fax</td>
					<td height="17" class="textos_formularios">
					<nested:present property="codigoAreaFaxCasaMatriz">
					(<nested:write
						property="codigoAreaFaxCasaMatriz" />)
					</nested:present>
					<nested:write property="faxCasaMatriz" />&nbsp;</td>
					<td height="17" class="barratablas">e-mail</td>
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
				<html:submit property="operacion" value="${editar}" styleClass="btn3" />
			</nested:equal>
			<html:submit property="operacion" value="${verDirecciones}" styleClass="btn3" />
			<!-- <html:submit property="operacion" value="${verConvenios}" styleClass="btn3" /> --></td>
		</tr>
	</table>
</html:form>
