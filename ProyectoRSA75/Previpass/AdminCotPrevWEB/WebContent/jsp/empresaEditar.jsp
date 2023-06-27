<%@ include file="/html/comun/taglibs.jsp" %>
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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="foco();">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/DetalleEmpresa" styleId="formulario" onsubmit="return onFormSubmit(this);" >
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<logic:equal parameter="subSubAccion" value="empresaEditar">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaEditar" />
</logic:equal>
<logic:equal parameter="subSubAccion" value="empresaCrear">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaCrear" />
</logic:equal>
<c:set var="opcioneditcrea" value="c"/>
<input type="hidden" name="comboSucu" id="comboSucu" value="false" />
<table width="580" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors/>
		</td>
	</tr>
	<tr>
		<td>
			<html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
  	<tr> 
    	<td align="left" valign="top">
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
          		<tr valign="bottom"> 
            		<td height="30" bgcolor="#FFFFFF" class="titulo">
						<logic:equal parameter="subSubAccion" value="empresaEditar">
            				<strong>Edici&oacute;n de Empresa</strong>
							<c:set var="opcioneditcrea" value="e"/>
            			</logic:equal>
						<logic:equal parameter="subSubAccion" value="empresaCrear">
            				<strong>Creaci&oacute;n de Empresa</strong>
            			</logic:equal>
            		</td>
          		</tr>
	        </table>
	    </td>
	</tr>
	<tr>
		<td>
	        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
				<tr> 
            		<td height="4" colspan="4" bgcolor="#85b4be">
            		</td>
				</tr>
				<tr> 
					<td width="19%" height="17" class="barratablas">RUT *</td>
					<td height="17" class="textos_formularios">
						<logic:equal parameter="subSubAccion" value="empresaEditar">
							<bean:write name="DetalleEmpresaActionForm" property="rutEmpresaEd"/>
							<html:hidden property="rutEmpresaEd" styleId="rutEmpresaEd"/>
						</logic:equal>
						<logic:equal parameter="subSubAccion" value="empresaCrear">
							<html:text property="rutEmpresaEd" styleId="rutEmpresaEd" styleClass="campos" readonly="false" maxlength="13" size="17" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
						</logic:equal>
					</td>
					<td height="17" class="barratablas"><strong>Raz&oacute;n Social *</strong></td>
					<td height="17" class="textos_formularios">
						<html:textarea property="razonSocial" styleId="razonSocial" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 100);soloRazonSocial(this);" onkeyup="javascript:cuentaTxtArea(this, 100);soloRazonSocial(this);" rows="2" cols="30"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barratablas"><strong>Tipo *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="tipoEmpresa" styleClass="campos">
							<html:option value="0">Privada</html:option>
							<html:option value="1">P&uacute;blica</html:option>
						</html:select>
					</td>
					<td height="17" class="barratablas"><strong>Estado *</strong></td>
					<td height="17" class="textos_formularios">
						<nested:select property="estadoEmpresa" styleId="estadoEmpresa" styleClass="campos">
                    		<html:option value="1">Habilitado</html:option>
                    		<html:option value="0">Deshabilitado</html:option>
						</nested:select>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barratablas" rowspan="3">RUT Rep. Legal *</td>
					<td height="17" class="textos_formularios" rowspan="3">
						<html:text property="idRepLegal" styleClass="campos" styleId="idRepLegal" maxlength="13" size="17" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
					</td>
					<td height="17" class="barratablas"><strong>Nombres *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="nombreRepLegal" styleClass="campos" styleId="nombreRepLegal" maxlength="30" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
				<tr> 
					<td height="17" class="barratablas"><strong>Apellido paterno *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="apPatRepLegal" styleClass="campos" styleId="apPatRepLegal" maxlength="20" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
				<tr> 
					<td height="17" class="barratablas"><strong>Apellido materno *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="apMatRepLegal" styleClass="campos" styleId="apMatRepLegal" maxlength="20" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas">Vigencia Rep. Legal *</td>
					<td height="17" class="textos_formularios">
						<html:text property="diaVigRepLegal" styleClass="campos" styleId="diaVigRepLegal" size="2" maxlength="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						- <html:text property="mesVigRepLegal" styleClass="campos" styleId="mesVigRepLegal" size="2" maxlength="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						- <html:text property="yearVigRepLegal" styleClass="campos" styleId="yearVigRepLegal" size="4" maxlength="4" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<logic:equal parameter="subSubAccion" value="empresaCrear">
		                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n *</strong></td>
		                <td height="17" class="textos_formularios">
							<nested:select property="opcCaja" styleId="opcCaja" styleClass="campos">
								<html:option value="0">Sin caja</html:option>
								<nested:optionsCollection property="cajas" />
							</nested:select>
		              	</td>
					</logic:equal>
					<logic:notEqual parameter="subSubAccion" value="empresaCrear">
		                <td height="17" class="barratablas">&nbsp;</td>
		                <td height="17" class="textos_formularios">&nbsp;</td>
		            </logic:notEqual>
          		</tr>
				<tr> 
            		<td height="17" class="barratablas" rowspan="3">RUT Administrador *</td>
            		<td height="17" class="textos_formularios" rowspan="3">
						<html:text property="idAdmin" styleClass="campos" styleId="idAdmin" maxlength="13" size="17" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
            		</td>
            		<td height="17" class="barratablas"><strong>Nombres *</strong></td>
            		<td height="17" class="textos_formularios">
						<html:text property="nombreAdmin" styleClass="campos" styleId="nombreAdmin" maxlength="30" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
              		</td>
          		</tr>
          		<tr> 
            		<td height="17" class="barratablas"><strong>Apellido paterno *</strong></td>
            		<td height="17" class="textos_formularios">
						<html:text property="apPatAdmin" styleClass="campos" styleId="apPatAdmin" maxlength="20" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
              		</td>
          		</tr>
          		<tr> 
            		<td height="17" class="barratablas"><strong>Apellido materno *</strong></td>
            		<td height="17" class="textos_formularios">
						<html:text property="apMatAdmin" styleClass="campos" styleId="apMatAdmin" maxlength="20" size="38" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
              		</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas"><strong>C&aacute;lculo de movimiento personal *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCalculoMovPersonal" styleClass="campos">
							<html:option value="1">S&iacute;</html:option>
							<html:option value="0">No</html:option>
						</html:select>
					</td>
					<td height="17" class="barratablas">&nbsp;</td>
					<td height="17" class="textos_formularios">&nbsp;</td>
				</tr>
				<logic:equal parameter="subSubAccion" value="empresaCrear">
					<tr> 
	            		<td height="4" colspan="4" bgcolor="#85b4be"></td>
					</tr>
	          		<tr>
	          			<td height="17" class="barratablas" colspan="4">
	          				<strong>Actividad Econ&oacute;mica *</strong>
	          			</td>
	          		</tr>
					<tr> 
						<td height="17" class="barratablas"><strong>C&oacute;digo</strong></td>
						<td height="17" class="textos_formularios">
							<nested:text property="opcActividadEconomicaMostrar" styleId="opcActividadEconomicaMostrar" maxlength="6" size="9" styleClass="campos" onchange="javascript:txtRubrosOnChange(this);"/>
						</td>
						<td height="17" class="barratablas"><strong>Nombre *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:select property="opcActividadEconomica" styleClass="campos" styleId="opcActividadEconomica" onchange="javascript:rubrosOnChange(this);">
								<html:option value="">-Seleccionar-</html:option>
								<nested:optionsCollection property="actividadesEconomicas"/>
							</nested:select>
						</td>
					</tr>
				</logic:equal>
				<tr> 
            		<td height="4" colspan="4" bgcolor="#85b4be"></td>
				</tr>
          		<tr>
          			<td height="17" class="barratablas" colspan="4">
          				<strong>Instituci&oacute;n Accidente del Trabajo</strong>
          			</td>
          		<tr>
          		<tr>
					<td height="17" class="barratablas"><strong>Nombre</strong></td>
					<td height="17" class="textos_formularios" colspan="3">
						<html:select property="opcMutual" styleId="opcMutual" styleClass="campos"  onchange="javascript:desbloquearValores(this);">
							<html:option value="0">Sin mutual</html:option>
							<html:optionsCollection property="mutuales" />
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas"><div id="divNumAd" style="font-weight: bold">N&ordm; Adherentes</div></td>
					<td height="17" class="textos_formularios">
						<html:text property="numAdherentesMutual" styleClass="dercampos" styleId="numAdherentesMutual" maxlength="9" size="14"  disabled="true" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<td height="17" class="barratablas"><div id="divCalcInd" style="font-weight: bold">C&aacute;lculo Individual</div></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCalculoIndividual" styleClass="campos"  styleId="opcCalculoIndividual">
							<html:option value="true">Si</html:option>
							<html:option value="false">No</html:option>
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas"><div id="divTasaAd" style="font-weight: bold">Tasa Adicional</div></td>
					<td height="17" class="textos_formularios">
			<!-- habilita opcion de proceso  -->	
						<html:text property="tasaAdicionalMutual" disabled="false" styleClass="dercampos" styleId="tasaAdicionalMutual" maxlength="6" size="11" onblur="javascript:soloDecimales(this);" onkeyup="javascript:soloDecimales(this);"/>
					</td>
					<td height="17" class="barratablas">&nbsp;</td>
					<td height="17" class="textos_formularios">&nbsp;</td>
          		</tr>
				<logic:equal parameter="subSubAccion" value="empresaCrear">			
					<tr> 
	            		<td height="4" colspan="4" bgcolor="#85b4be">
	            		</td>
					</tr>
	          		<tr>
	          			<td height="17" class="barratablas" colspan="4">
	          				<strong>Configuraci&oacute;n de Convenio</strong>
	          			</td>
	          		<tr>
	          		<tr>
						<td height="17" class="barratablas"><strong>Grupo de Convenios</strong></td>
						<td height="17" class="textos_formularios">
							<html:text property="opcGrupoConvenio" styleClass="dercampos" styleId="opcGrupoConvenio" maxlength="9" size="17" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
						</td>
						<td height="17" class="barratablas">&nbsp;</td>
						<td height="17" class="textos_formularios">&nbsp;</td>
	          		</tr>
					<tr> 
	            		<td height="4" colspan="4" bgcolor="#85b4be">
	            		</td>
					</tr>
	          		<tr>
	          			<td height="17" class="barratablas" colspan="4">
	          				<strong>Opciones de proceso</strong>
	          			</td>
	          		<tr>
					<tr>
						<td colspan="4">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
				          		<tr>
									<td width="35%" height="17" class="barratablas">
										Generar planillas INP por sucursal?:
									</td>
									<td width="15%" height="17" class="textos_formularios">
										<nested:radio property="genINPPorSucursal" value="1"/>S&iacute;
										<nested:radio property="genINPPorSucursal" value="0"/>No
									</td>
									<td width="35%" height="17" class="barratablas">
										Calcular monto total CCAF?:		
									</td>
									<td width="15%" height="17" class="textos_formularios">
										<nested:radio property="calcularMontoCCAF" value="1"/>S&iacute;
										<nested:radio property="calcularMontoCCAF" value="0"/>No
									</td>
				          		</tr>
				          		<tr>
									<td height="17" class="barratablas">
										Generar planillas Mutual por sucursal?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="genMutualPorSucursal" value="1"/>S&iacute;
										<nested:radio property="genMutualPorSucursal" value="0"/>No
									</td>
									<td height="17" class="barratablas">
										Calcular monto total Salud?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="calcularMontoTotalSalud" value="1"/>S&iacute;
										<nested:radio property="calcularMontoTotalSalud" value="0"/>No
									</td>
				          		</tr>
				          		<tr>
									<td height="17" class="barratablas">
										Generar planillas CCAF por sucursal?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="genCCAFPorSucursal" value="1"/>S&iacute;
										<nested:radio property="genCCAFPorSucursal" value="0"/>No
									</td>
									<td height="17" class="barratablas">
										Calcular monto total Previsi&oacute;n?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="calcularMontoTotalPrev" value="1"/>S&iacute;
										<nested:radio property="calcularMontoTotalPrev" value="0"/>No
									</td>
				          		</tr>
				          		<tr>
									<td height="17" class="barratablas">
										Calcular monto FONASA?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="calcularMontoINP" value="1"/>S&iacute;
										<nested:radio property="calcularMontoINP" value="0"/>No
									</td>
									<td height="17" class="barratablas">
										Imprimir planillas?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="imprimirPlanillas" value="1"/>S&iacute;
										<nested:radio property="imprimirPlanillas" value="0"/>No
									</td>
				          		</tr>
				          		<tr>
									<td height="17" class="barratablas">
										Calcular monto total Mutual?:
									</td>
									<td height="17" class="textos_formularios">
										<nested:radio property="calcularMontoMutual" value="1"/>S&iacute;
										<nested:radio property="calcularMontoMutual" value="0"/>No
									</td>
									<td height="17" class="barratablas">
										&nbsp;
									</td>
									<td height="17" class="textos_formularios">
										&nbsp;
									</td>
				          		</tr>
							</table>
						</td>
					</tr>
				</logic:equal>
			</table>
		</td>
	</tr>
	<tr>
		<td>
        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom"> 
					<td height="30" bgcolor="#FFFFFF" class="titulo">
						<strong>Casa Matriz</strong>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
        	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
  				<tr> 
                	<td height="4" colspan="4" bgcolor="#85b4be"></td>
            	</tr>
				<tr> 
					<td width="12%" height="17" class="barratablas">C&oacute;digo *</td>
					<td height="17" class="textos_formularios">
						<logic:equal parameter="subSubAccion" value="empresaEditar">
							<nested:select property="opcSucursalCasaMatriz" styleId="codigoCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
								<nested:optionsCollection property="listaSucursales"/>
							</nested:select>
						</logic:equal>
						<logic:equal parameter="subSubAccion" value="empresaCrear">
							<nested:text property="codigoCasaMatriz" styleId="codigoCasaMatriz" styleClass="campos" size="10" maxlength="6" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
						</logic:equal>
					</td>
					<td height="17" class="barratablas"><strong>Nombre *</strong></td>
					<td height="17" class="textos_formularios">
						<nested:text property="nombreCasaMatriz" styleId="nombreCasaMatriz" styleClass="campos" size="45" maxlength="30" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barratablas">Direcci&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:textarea property="direccionCasaMatriz" styleId="direccionCasaMatriz" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 80);soloDescripcion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);soloDescripcion(this);" rows="3" cols="25"></html:textarea>
					</td>
					<td height="17" class="barratablas"><strong>N&ordm; *</strong></td>
					<td height="17" class="textos_formularios">
						<nested:text property="noCasaMatriz" styleId="noCasaMatriz" styleClass="campos" size="10" maxlength="6" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
					</td>
				</tr>
				<tr> 
					<td width="9%" height="17" class="barratablas">Departamento</td>
					<td width="18%" class="textos_formularios">
						<nested:text property="dptoCasaMatriz" styleId="dptoCasaMatriz" styleClass="campos" size="10" maxlength="6" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
					</td>
					<td height="17" class="barratablas">Regi&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:select property="opcRegionCasaMatriz" styleId="opcRegionCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="regiones"/>
						</html:select>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barratablas"><strong>Ciudad *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCiudadCasaMatriz" styleId="opcCiudadCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="ciudades"/>
						</html:select>
					</td>
	                <td height="17" class="barratablas">Comuna *</td>
    	             <td height="17" class="textos_formularios">
						<html:select property="opcComunaCasaMatriz" styleId="opcComunaCasaMatriz" styleClass="campos">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="comunas"/>
						</html:select>
	                 </td>
    	       </tr>
               <tr> 
                 <td height="17" class="barratablas">Tel&eacute;fono *</td>
                 <td height="17" class="textos_formularios">
					<nested:text property="codigoAreaCasaMatriz" styleId="codigoAreaCasaMatriz" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					<nested:text property="fonoCasaMatriz" styleId="fonoCasaMatriz" maxlength="9" size="12" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
                 <td height="17" class="barratablas"><strong>Celular</strong></td>
                 <td height="17" class="textos_formularios">
					<nested:text property="celCasaMatriz" styleId="celCasaMatriz" maxlength="9" size="13" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
               </tr>
               <tr> 
                 <td height="17" class="barratablas">Fax:</td>
                 <td height="17" class="textos_formularios">
					<nested:text property="codigoAreaFaxCasaMatriz" styleId="codigoAreaFaxCasaMatriz" styleClass="campos" maxlength="4" size="6" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					<nested:text property="faxCasaMatriz" styleId="faxCasaMatriz" styleClass="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
                 <td height="17" class="barratablas">e-mail:</td>
                 <td width="27%" height="17" class="textos_formularios">
					<nested:text property="emailCasaMatriz" styleId="emailCasaMatriz" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloEmail(this);" onkeyup="javascript:soloEmail(this);"/>
                 </td>
               </tr>
             </table>
             </td>
         </tr>
		<tr>
			<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
		</tr>
	<tr align="center">
		<td valign="top"><br />
			<html:submit property="operacion" value="Guardar" styleClass="btn3" />
			<html:cancel property="operacion" value="Cancelar" styleClass="btn3" />
		</td>
	</tr>
</table>
</html:form>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<script language="javaScript"> 
<!--
	function combosChange(combo) 
	{
		if (combo.id == "opcRegionCasaMatriz") 
		{
			document.getElementById("opcCiudadCasaMatriz").value = "-1";
			document.getElementById("opcComunaCasaMatriz").value = "-1";

			bCancel = true;
			document.getElementById("formulario").submit();
		} else if (combo.id == "opcCiudadCasaMatriz") 
		{
			document.getElementById("opcComunaCasaMatriz").value = "-1";

			bCancel = true;			
			document.getElementById("formulario").submit();
		} else if (combo.id == "codigoCasaMatriz") 
		{
			bCancel = true;
			document.getElementById("comboSucu").value = "true";
			document.getElementById("formulario").submit();
		}
	}

	function rubrosOnChange(obj) 
	{
		document.getElementById("opcActividadEconomicaMostrar").value = obj.value;
	}
	
	function txtRubrosOnChange(obj) 
	{		
		var cmbRubro = document.getElementById("opcActividadEconomica");
		var opcs = cmbRubro.options;
		var bEncontrado = false
		for (var i = 0; i < opcs.length; i++) 
		{
			if (opcs[i].value == "")
				continue;
			if (opcs[i].value == obj.value) 
			{
				bEncontrado = true;
				break;
			}
		}
		if (bEncontrado) 
		{
			cmbRubro.selectedIndex = i;
		} else 
		{
			alert("Código de actividad económica no existe!");
			cmbRubro.selectedIndex = 0;
			cmbRubro.focus();
		}
	}
	
	function validaFormulario(f) 
	{
		//Valida prescencia de campos
		var sMsje = "";
		//Empresa
		if (!validaReq(document.getElementById("rutEmpresaEd")))
			sMsje += "* Debe ingresar el RUT de la empresa\n";
		if (!validaRut(document.getElementById("rutEmpresaEd").value))
			sMsje += "* Formato de rut inválido para la empresa\n";
		if (!validaReq(document.getElementById("razonSocial")))
			sMsje += "* Debe ingresar la razón social\n";
		if (!validaRazonSocial(document.getElementById("razonSocial").value))
			sMsje += "* Caracteres inválidos en razón social.\n";
		//Actividad economica
		if (document.getElementById("subSubAccion").value == "empresaCrear") 
			if (document.getElementById("opcActividadEconomica").value == "")
				sMsje += "* Debe seleccionar la Actividad Económica\n";
		//Rep. legal
		if (!validaReq(document.getElementById("idRepLegal")))
			sMsje += "* Debe ingresar el RUT del representante legal\n";
		if (!validaRut(document.getElementById("idRepLegal").value))
			sMsje += "* Formato de rut inválido en representante legal\n";
		if (!validaReq(document.getElementById("nombreRepLegal")))
			sMsje += "* Debe ingresar el nombre del representante legal\n";
		if (!validaReq(document.getElementById("apPatRepLegal")))
			sMsje += "* Debe ingresar el apellido paterno del representante legal\n";
		if (!validaReq(document.getElementById("apMatRepLegal")))
			sMsje += "* Debe ingresar el apellido materno del representante legal\n";
		if (!validaReq(document.getElementById("diaVigRepLegal")))
			sMsje += "* Debe ingresar el día de inicio de la vigencia del representante legal\n";
		if (!validaReq(document.getElementById("mesVigRepLegal")))
			sMsje += "* Debe ingresar el mes de inicio de la vigencia del representante legal\n";
		if (!validaReq(document.getElementById("yearVigRepLegal")))
			sMsje += "* Debe ingresar el año de inicio de la vigencia del representante legal\n";
		var sFecha = document.getElementById("diaVigRepLegal").value + "/" + document.getElementById("mesVigRepLegal").value
			+ "/" + document.getElementById("yearVigRepLegal").value;
		if (!validaFecha(sFecha))
			sMsje += "* Fecha inválida para el inicio de la vigencia del representante legal\n";
			
		//Admin
		if (!validaReq(document.getElementById("idAdmin")))
			sMsje += "* Debe ingresar el RUT del administrador\n";
		if (!validaRut(document.getElementById("idAdmin").value))
			sMsje += "* Formato de rut inválido en administrador\n";
		if (!validaReq(document.getElementById("nombreAdmin")))
			sMsje += "* Debe ingresar el nombre del administrador\n";
		if (!validaReq(document.getElementById("apPatAdmin")))
			sMsje += "* Debe ingresar el apellido paterno del administrador\n";
		if (!validaReq(document.getElementById("apMatAdmin")))
			sMsje += "* Debe ingresar el apellido materno del administrador\n";

		//Solo si se ha seleccionado mutual
		if (document.getElementById("opcMutual").value != 0) 
		{
			if (!validaReq(document.getElementById("numAdherentesMutual")))
				sMsje += "* Debe ingresar el número de adherentes de la mutual\n";
			if (!validaNumero(document.getElementById("numAdherentesMutual").value))
				sMsje += "* No es un número válido el número de adherentes de la mutual\n";
			if (!validaReq(document.getElementById("tasaAdicionalMutual")))
				sMsje += "* Debe ingresar la tasa adicional de la mutual\n";
			//Flotantes				
			var tasaStr = new String(document.getElementById("tasaAdicionalMutual").value).replace(',', '.');
			if (parseFloat(tasaStr) == NaN)
				sMsje += "* Tasa Adicional inválida\n";
			else
			{
				var tasa = document.getElementById("tasaAdicionalMutual").value.split(',');
				if (tasa[0].length > 2)
					sMsje += "* Tasa Adicional de mutual debe ser menor a 100\n";
				else
				{
					//document.getElementById("tasaAdicionalMutual").value = parseFloat(tasaStr).toFixed(4);
					
					vlrPrueba = parseFloat(tasa).toFixed(4);						
					if (vlrPrueba == 'NaN')
					{						
						document.getElementById("tasaAdicionalMutual").value = "";
						sMsje += "* Tasa Adicional de mutual inválida\n";
					} else if (!validaReal(vlrPrueba))
						sMsje += "* Tasa Adicional inválida\n";
				}
			}
		}
		
		//Casa matriz
		if (document.getElementById("subSubAccion").value == "empresaCrear") 
		{
			if (!validaReq(document.getElementById("codigoCasaMatriz")))
				sMsje += "* Debe ingresar el código de la casa matriz\n";
			if (!validaNombreSuc(document.getElementById("codigoCasaMatriz").value))
				sMsje += "* Caracteres inválidos en el código de la casa matriz\n";
		}
		if (!validaReq(document.getElementById("nombreCasaMatriz")))
			sMsje += "* Debe ingresar el nombre de la casa matriz\n";

		if (!validaReq(document.getElementById("noCasaMatriz")))
			sMsje += "* Debe ingresar el número de la casa matriz\n";
		if (!validaReq(document.getElementById("direccionCasaMatriz")))
			sMsje += "* Debe ingresar la dirección de la casa matriz\n";

		if (document.getElementById("opcRegionCasaMatriz").value == "-1")
			sMsje += "* Debe seleccionar la región de la casa matriz\n";
		if (document.getElementById("opcCiudadCasaMatriz").value == "-1")
			sMsje += "* Debe seleccionar la ciudad de la casa matriz\n";
		if (document.getElementById("opcComunaCasaMatriz").value == "-1")
			sMsje += "* Debe seleccionar la comuna de la casa matriz\n";

		if (!validaReq(document.getElementById("fonoCasaMatriz")))
			sMsje += "* Debe ingresar el teléfono de la Casa Matriz.\n";
		else if (document.getElementById("fonoCasaMatriz").value.length < 6)
			sMsje += "* No es un número válido el telefono de la Casa Matriz.\n";
		if (document.getElementById("codigoAreaCasaMatriz").value.length < 1)
			sMsje += "* Debe ingresar un código de area para el telefono de la Casa Matriz.\n";
		
		if (validaReq(document.getElementById("celCasaMatriz")) &&
			 !validaFormatoCelular(document.getElementById("celCasaMatriz").value))
			sMsje += "* No es un número válido el celular de la casa matriz\n";
		if ((document.getElementById("codigoAreaFaxCasaMatriz").value.length<1)&&
		(document.getElementById("faxCasaMatriz").value.length>0))
			sMsje += "* Debe ingresar un código de area para el Fax\n";
		if ((document.getElementById("codigoAreaFaxCasaMatriz").value.length>0)&&
		(document.getElementById("faxCasaMatriz").value.length<6))
		 	sMsje += "* No es un número válido el fax de la casa matriz.\n";
		if (validaReq(document.getElementById("emailCasaMatriz")) &&
		    !validaMail(document.getElementById("emailCasaMatriz").value))
			sMsje += "* Formato de e-mail inválido en e-mail de la casa matriz.\n";

		if (sMsje == "") 
		{
			if (!validaDV(document.getElementById("rutEmpresaEd").value))
				sMsje += "* Dígito verificador inválido para el rut de la empresa\n";
			if (!validaDV(document.getElementById("idRepLegal").value))
				sMsje += "* Dígito verificador inválido para el rut del representante legal\n";
			if (!validaDV(document.getElementById("idAdmin").value))
				sMsje += "* Dígito verificador inválido para el rut del administrador\n";
		}
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function desbloquearValores(obj)
	{	
		if (obj.value != '0')
		{
			 document.getElementById("numAdherentesMutual").disabled = false;
			 document.getElementById("opcCalculoIndividual").disabled = false;
			 document.getElementById("tasaAdicionalMutual").disabled = false;
			 document.getElementById("divNumAd").innerHTML = "N° Adherentes *";
			 document.getElementById("divCalcInd").innerHTML = "Cálculo Individual *";
			 document.getElementById("divTasaAd").innerHTML = "Tasa Adicional *";
		} else
		{
		// marco Se habilita campos  para soportar cotización de independientes
			 document.getElementById("numAdherentesMutual").disabled = true;
//			 document.getElementById("opcCalculoIndividual").disabled = true;
//			 document.getElementById("tasaAdicionalMutual").disabled = true;	
			 document.getElementById("numAdherentesMutual").value = "";
//			 document.getElementById("tasaAdicionalMutual").value = "";
			 document.getElementById("divNumAd").innerHTML = "N° Adherentes";
			 document.getElementById("divCalcInd").innerHTML = "Cálculo Individual";
			 document.getElementById("divTasaAd").innerHTML = "Tasa Adicional";
		}
	}

	if(document.getElementById("opcMutual").value != 0)
	{
		desbloquearValores(document.getElementById("opcMutual"));
	}

	function foco()
	{	
		foco =  document.getElementById('opcionfoco').value;

		if(foco == 'c')
		 	document.getElementById('rutEmpresaEd').focus();
		else
		 document.getElementById('razonSocial').focus();
	}

// -->
</script>
</body>
</html:html>
