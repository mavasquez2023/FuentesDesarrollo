<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/DetalleEmpresa" styleId="formulario" onsubmit="return onFormSubmit(this)" >
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />

<html:hidden property="mutualInicio" styleId="mutualInicio" value="${DetalleEmpresaActionForm.opcMutual}"/>
<html:hidden property="mutualInicioTasa" styleId="mutualInicioTasa" value="${DetalleEmpresaActionForm.tasaAdicionalMutual}"/>

<logic:equal parameter="subSubAccion" value="empresaEditar">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaEditar" />
</logic:equal>
<logic:equal parameter="subSubAccion" value="empresaCrear">
	<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="empresaCrear" />
</logic:equal>
<input type="hidden" name="comboSucu" id="comboSucu" value="false" />

<html:hidden property="eliminaNominas" name="eliminaNominas" styleId="eliminaNominas" value="NO" />
	
<table width="580" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors />
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
	        <table border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
					<td width="20%" height="17" class="barra_tablas">RUT:</td>
					<td width="30%" height="17" class="textos_formularios">
						<logic:equal parameter="subSubAccion" value="empresaEditar">
							<bean:write name="DetalleEmpresaActionForm" property="rutEmpresaEd"/>
							<html:hidden property="rutEmpresaEd" styleId="rutEmpresaEd"/>
						</logic:equal>
						<logic:equal parameter="subSubAccion" value="empresaCrear">
							<html:text property="rutEmpresaEd" styleClass="campos" styleId="rutEmpresaEd" maxlength="13" size="17" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
						</logic:equal>
					</td>
					<td width="20%" height="17" class="barra_tablas"><strong>Raz&oacute;n Social *</strong></td>
					<td width="30%" height="17" class="textos_formularios">
						<html:textarea property="razonSocial" styleId="razonSocial" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 100);javascript:soloRazonSocial(this);" onkeyup="javascript:cuentaTxtArea(this, 100);javascript:soloRazonSocial(this);" rows="2" cols="30"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barra_tablas" rowspan="3">RUT Rep. Legal *</td>
					<td height="17" class="textos_formularios" rowspan="3">
						<html:text property="idRepLegal" styleClass="campos" styleId="idRepLegal" maxlength="13" size="17" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
					</td>
					<td height="17" class="barra_tablas"><strong>Nombres *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="nombreRepLegal" styleClass="campos" styleId="nombreRepLegal" maxlength="30" size="40" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
				<tr> 
					<td height="17" class="barra_tablas"><strong>Apellido paterno *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="apPatRepLegal" styleClass="campos" styleId="apPatRepLegal" maxlength="20" size="30" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
				<tr> 
					<td height="17" class="barra_tablas"><strong>Apellido materno *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="apMatRepLegal" styleClass="campos" styleId="apMatRepLegal" maxlength="20" size="30" onblur="javascript:soloNombres(this);" onkeyup="javascript:soloNombres(this);"/>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barra_tablas">Vigencia Rep. Legal *</td>
					<td height="17" class="textos_formularios">
						<html:text property="diaVigRepLegal" styleClass="campos" styleId="diaVigRepLegal" size="2" maxlength="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						-
						<html:text property="mesVigRepLegal" styleClass="campos" styleId="mesVigRepLegal" size="2" maxlength="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						-
						<html:text property="yearVigRepLegal" styleClass="campos" styleId="yearVigRepLegal" size="4" maxlength="4" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<td height="17" class="barra_tablas"><strong>Tipo *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="tipoEmpresa" styleClass="campos">
							<html:option value="0">Privada</html:option>
							<html:option value="1">P&uacute;blica</html:option>
						</html:select>
					</td>
          		</tr>
				<logic:equal parameter="subSubAccion" value="empresaCrear">
	              	<tr> 
						<td height="17" class="barra_tablas">
							<logic:equal parameter="subSubAccion" value="empresaEditar">&nbsp;</logic:equal>
							<logic:equal parameter="subSubAccion" value="empresaCrear">Grupo de Convenios *</logic:equal>
						</td>
						<td height="17" class="textos_formularios">
							<logic:equal parameter="subSubAccion" value="empresaEditar">&nbsp;</logic:equal>
							<logic:equal parameter="subSubAccion" value="empresaCrear">
								<nested:select property="opcGrupoConvenio" styleId="idGrupoConvenio" styleClass="campos">
									<html:optionsCollection property="listaGrupos" value="idGrupoConvenio" label="nombre"/>
								</nested:select>
							</logic:equal>
						</td>
		                <td height="17" class="barra_tablas"><strong>Caja de Compensaci&oacute;n *</strong></td>
		                <td height="17" class="textos_formularios">
							<nested:select property="opcCaja" styleId="opcCaja" styleClass="campos">
								<html:option value="0">Sin caja</html:option>
								<nested:optionsCollection property="cajas" />
							</nested:select>
		              	</td>
					</tr>
					<tr> 
	          			<td height="17" class="barra_tablas" colspan="4">
	          				<strong>Actividad Econ&oacute;mica *</strong>
	          			</td>
	          		</tr>
					<tr> 
						<td height="17" class="barra_tablas"><strong>C&oacute;digo</strong></td>
						<td height="17" class="textos_formularios">
							<nested:text property="opcActEconomMostrar" styleId="opcActEconomMostrar" maxlength="6" size="9" styleClass="campos" onchange="javascript:txtRubrosOnChange(this);"/>
						</td>
						<td height="17" class="barra_tablas"><strong>Nombre *</strong></td>
						<td height="17" class="textos_formularios">
							<nested:select property="opcActEconomica" styleClass="campos" styleId="opcActEconomica" onchange="javascript:rubrosOnChange(this);">
								<html:option value="">-Seleccionar-</html:option>
								<nested:optionsCollection property="actividadesEconomicas"/>
							</nested:select>
						</td>
					</tr>
				</logic:equal>
				<tr> 
          			<td height="17" class="barra_tablas" colspan="4">
          				<strong>Instituci&oacute;n Accidente del Trabajo</strong>
            		</td>
				</tr>
          		<tr>
					<td height="17" class="barra_tablas"><strong>Nombre</strong></td>
					<td height="17" class="textos_formularios" colspan="3">
						<html:select property="opcMutual" styleId="opcMutual" styleClass="campos"  onchange="javascript:desbloquearValores(this);">
							<html:option value="0">Sin mutual</html:option>
							<html:optionsCollection property="mutuales" />
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barra_tablas"><div id="divNumAd" style="font-weight: bold">N&ordm; Adherentes</div></td>
					<td height="17" class="textos_formularios">
						<html:text property="numAdherentesMutual" disabled="true" styleClass="dercampos" styleId="numAdherentesMutual" maxlength="9" size="14" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<td height="17" class="barra_tablas"><div id="divCalcInd" style="font-weight: bold">C&aacute;lculo Individual</div></td>
					<td height="17" class="textos_formularios">
	<!-- habilita opcion de proceso  -->			
						<html:select property="opcCalculoIndividual" disabled="false" styleId="opcCalculoIndividual" styleClass="campos">
							<html:option value="true">Si</html:option>
							<html:option value="false">No</html:option>
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barra_tablas"><div id="divTasaAd" style="font-weight: bold">Tasa Adicional</div></td>
					<td height="17" class="textos_formularios">
	<!-- habilita ingreseo tasa adicional -->		
					<html:text property="tasaAdicionalMutual" styleClass="dercampos" styleId="tasaAdicionalMutual" maxlength="7" size="11" disabled="false" onblur="javascript:soloReal(this);" onkeyup="javascript:soloReal(this);"/>
					</td>
					<td height="17" class="barra_tablas">&nbsp;</td>
					<td height="17" class="textos_formularios">&nbsp;</td>
          		</tr>
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
        	<table border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
  				<tr> 
					<td width="12%" height="17" class="barra_tablas">C&oacute;digo *</td>
					<td height="17" class="textos_formularios">
						<logic:equal parameter="subSubAccion" value="empresaEditar">							
							<nested:select property="opcSucursalCasaMatriz" styleId="codigoCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
								<nested:optionsCollection property="listaSucursales"/>
							</nested:select>
						</logic:equal>
						<logic:equal parameter="subSubAccion" value="empresaCrear">
							<html:text property="opcSucursalCasaMatriz" styleClass="campos" styleId="codigoCasaMatriz" size="10" maxlength="6" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
						</logic:equal>
					</td>
					<td height="17" class="barra_tablas"><strong>Nombre *</strong></td>
					<td height="17" class="textos_formularios">
						<nested:text property="nombreCasaMatriz" styleId="nombreCasaMatriz" styleClass="campos" size="45" maxlength="30" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barra_tablas">Direcci&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:textarea property="direccionCasaMatriz" styleId="direccionCasaMatriz" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" rows="5" cols="25"></html:textarea>
						<!-- <nested:text property="direccionCasaMatriz" styleId="direccionCasaMatriz" styleClass="campos" maxlength="80"/> -->
					</td>
					<td height="17" class="barra_tablas"><strong>N&ordm; *</strong></td>
					<td height="17" class="textos_formularios">
						<nested:text property="noCasaMatriz" styleId="noCasaMatriz" styleClass="campos" size="10" maxlength="6" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
					</td>
				</tr>
				<tr> 
					<td width="9%" height="17" class="barra_tablas">Departamento</td>
					<td width="18%" class="textos_formularios">
						<nested:text property="dptoCasaMatriz" styleId="dptoCasaMatriz" styleClass="campos" size="10" maxlength="6" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);" />
					</td>
					<td height="17" class="barra_tablas">Regi&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:select property="opcRegionCasaMatriz" styleId="opcRegionCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="regiones"/>
						</html:select>
					</td>
				</tr>
				<tr> 
					<td height="17" class="barra_tablas"><strong>Ciudad *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCiudadCasaMatriz" styleId="opcCiudadCasaMatriz" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="ciudades"/>
						</html:select>
					</td>
	                <td height="17" class="barra_tablas">Comuna *</td>
    	             <td height="17" class="textos_formularios">
						<html:select property="opcComunaCasaMatriz" styleId="opcComunaCasaMatriz" styleClass="campos">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="comunas"/>
						</html:select>
	                 </td>
    	       </tr>
               <tr> 
                 <td height="17" class="barra_tablas">Tel&eacute;fono *</td>
                 <td height="17" class="textos_formularios">
					<nested:text property="codigoAreaCasaMatriz" styleId="codigoAreaCasaMatriz" styleClass="campos" maxlength="4" size="6" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					<nested:text property="fonoCasaMatriz" styleId="fonoCasaMatriz" maxlength="9" size="12" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
                 <td height="17" class="barra_tablas"><strong>Celular</strong></td>
                 <td height="17" class="textos_formularios">
					<nested:text property="celCasaMatriz" styleId="celCasaMatriz" maxlength="9" size="13" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
               </tr>
               <tr> 
                 <td height="17" class="barra_tablas">Fax:</td>
                 <td height="17" class="textos_formularios">
					<nested:text property="codigoAreaFaxCasaMatriz" styleId="codigoAreaFaxCasaMatriz" styleClass="campos" maxlength="4" size="6" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					<nested:text property="faxCasaMatriz" styleId="faxCasaMatriz" styleClass="campos" maxlength="9" size="12" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                 </td>
                 <td height="17" class="barra_tablas">e-mail:</td>
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
			<html:submit property="operacion" value="${guardar}" styleClass="btn_grande" />
			<html:cancel property="operacion" value="${cancelar}" styleClass="btn_grande" />
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 

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
		document.getElementById("opcActEconomMostrar").value = obj.value;
	}
	
	function txtRubrosOnChange(obj) 
	{		
		var cmbRubro = document.getElementById("opcActEconomica");
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
	
	if(document.getElementById('mutualInicio').value != document.getElementById('opcMutual').value){
		if (!confirm("Si modifica la Mutual se eliminara todas las nóminas de esta Empresa, ¿Está seguro que desea continuar?"))
		{
			return false;
		}else{
			document.getElementById("eliminaNominas").value = 'SI';
		}
	}
	if(document.getElementById('mutualInicioTasa').value != document.getElementById('tasaAdicionalMutual').value){
		if (!confirm("Si modifica la Tasa de la Mutual se eliminara todas las nóminas de esta Empresa, ¿Está seguro que desea continuar?"))
		{
			return false;
		}else{
			document.getElementById("eliminaNominas").value = 'SI';
		}
	}
	
		var creacion = false;
			creacion = true;
		//Valida presencia de campos
		var sMsje = "";
		//Empresa
		if (!validaReq(document.getElementById("rutEmpresaEd")))
			sMsje += "* Debe ingresar el RUT de la empresa\n";
		else if (!validaRut(document.getElementById("rutEmpresaEd").value))
			sMsje += "* Formato de RUT inválido para la empresa\n";
		else if (!validaDV(document.getElementById("rutEmpresaEd").value))
			sMsje += "* Dígito verificador inválido para el rut de la empresa\n";

		if (!validaReq(document.getElementById("razonSocial")))
			sMsje += "* Debe ingresar la razón social\n";
		else if (!validaRazonSocial(document.getElementById("rutEmpresaEd").value))
			sMsje += "* Razón social inválida\n";
		//Representante Legal
		if (!validaReq(document.getElementById("idRepLegal")))
			sMsje += "* Debe ingresar el RUT del representante legal\n";
		else if (!validaRut(document.getElementById("idRepLegal").value))
			sMsje += "* Formato de RUT inválido para el representante legal\n";
		else if (!validaDV(document.getElementById("idRepLegal").value))
			sMsje += "* Dígito verificador inválido para el rut del representante legal\n";

		if (!validaReq(document.getElementById("nombreRepLegal")))
			sMsje += "* Debe ingresar el nombre del representante legal\n";
		else if (!validaNombres(document.getElementById("nombreRepLegal").value))
			sMsje += "* Nombre del representante legal inválido\n";

		if (!validaReq(document.getElementById("apPatRepLegal")))
			sMsje += "* Debe ingresar el Apellido Paterno del representante legal\n";
		else if (!validaNombres(document.getElementById("apPatRepLegal").value))
			sMsje += "* Apellido Paterno del representante legal inválido\n";

		if (!validaReq(document.getElementById("apMatRepLegal")))
			sMsje += "* Debe ingresar el Apellido Materno del representante legal\n";
		else if (!validaNombres(document.getElementById("apMatRepLegal").value))
			sMsje += "* Apellido Materno del representante legal inválido\n";

		if (!validaReq(document.getElementById("diaVigRepLegal")))
			sMsje += "* Debe ingresar día de vigencia Rep. Legal\n";
		else if (!validaNumero(document.getElementById("diaVigRepLegal").value))
			sMsje += "* Día de vigencia Rep. Legal inválido\n";

		if (!validaReq(document.getElementById("mesVigRepLegal")))
			sMsje += "* Debe ingresar mes de vigencia Rep. Legal\n";
		else if (!validaNumero(document.getElementById("mesVigRepLegal").value))
			sMsje += "* Mes de vigencia Rep. Legal inválido\n";

		if (!validaReq(document.getElementById("yearVigRepLegal")))
			sMsje += "* Debe ingresar año de vigencia Rep. Legal\n";
		else if (!validaNumero(document.getElementById("yearVigRepLegal").value))
			sMsje += "* Año de vigencia Rep. Legal inválido\n";
		var sFecha = document.getElementById("diaVigRepLegal").value + "/" + document.getElementById("mesVigRepLegal").value
				+ "/" + document.getElementById("yearVigRepLegal").value;
		if (!validaFecha(sFecha))
			sMsje += "* Fecha inválida para el inicio de la vigencia del representante legal\n";

		//MUTUAL
		if (document.getElementById("opcMutual").value != 0) 
		{
			if (!validaReq(document.getElementById("numAdherentesMutual")))
				sMsje += "* Debe ingresar el número de adherentes de mutual\n";
			else if (!validaNumero(document.getElementById("numAdherentesMutual").value))
				sMsje += "* Número de adherentes inválido\n";
			if (!validaReq(document.getElementById("tasaAdicionalMutual")))
				sMsje += "* Debe ingresar la Tasa Adicional de mutual\n";
			else
			{
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
		}

		//Casa matriz
		if (creacion)
		{
			if (!validaReq(document.getElementById("codigoCasaMatriz")))
				sMsje += "* Debe ingresar un Código para la Casa Matriz\n";
			else if (!validaNombreSuc(document.getElementById("codigoCasaMatriz").value))
				sMsje += "* Código Casa Matriz inválido\n";
		}
		if (validaReq(document.getElementById("celCasaMatriz")) &&
			!validaFormatoCelular(document.getElementById("celCasaMatriz").value))
			sMsje += "* No es un número válido el celular de la casa matriz\n";
		if (!validaReq(document.getElementById("nombreCasaMatriz")))
			sMsje += "* Debe un nombre para la Casa Matriz\n";
		else if (!validaNombreEntidad(document.getElementById("nombreCasaMatriz").value))
			sMsje += "* Nombre Casa Matriz inválido\n";

		if (!validaReq(document.getElementById("fonoCasaMatriz")))
			sMsje += "* Debe ingresar el teléfono de la Casa Matriz.\n";
		else if (document.getElementById("fonoCasaMatriz").value.length < 6)
			sMsje += "* No es un número válido el telefono de la Casa Matriz.\n";
		if (document.getElementById("codigoAreaCasaMatriz").value.length < 1)
			sMsje += "* Debe ingresar un código de area para el telefono de la Casa Matriz.\n";

		if (!document.getElementById("faxCasaMatriz").value == "" || !document.getElementById("codigoAreaFaxCasaMatriz").value=="")
		{
			if (document.getElementById("codigoAreaFaxCasaMatriz").value.length < 1)
				sMsje += "* No es un código válido para el fax de la Casa Matriz.\n";			 
		 	if (document.getElementById("faxCasaMatriz").value.length < 6)
				sMsje += "* No es un número válido el fax de la Casa Matriz.\n";
		}
		if (!validaReq(document.getElementById("noCasaMatriz")))
			sMsje += "* Debe ingresar número de Dirección para la Casa Matriz\n";
		else if (!validaDescripcion(document.getElementById("noCasaMatriz").value))
			sMsje += "* Número de Dirección para la Casa Matriz inválido\n";
		if (!validaReq(document.getElementById("direccionCasaMatriz")))
			sMsje += "* Debe ingresar Dirección para la Casa Matriz\n";
		else if (!validaDireccion(document.getElementById("direccionCasaMatriz").value))
			sMsje += "* Dirección para la Casa Matriz inválida\n";

		if (document.getElementById("opcRegionCasaMatriz").value == "-1")
			sMsje += "* Debe seleccionar la región de la Casa Matriz\n";
		if (document.getElementById("opcCiudadCasaMatriz").value == "-1")
			sMsje += "* Debe seleccionar la ciudad de la Casa Matriz\n";
		if (document.getElementById("opcComunaCasaMatriz").value <= 0)
			sMsje += "* Debe seleccionar Comuna para la Casa Matriz\n";

		//no obligatorios
		if (document.getElementById("celCasaMatriz").value != "" &&
			!validaNumero(document.getElementById("celCasaMatriz").value)  && document.getElementById("celCasaMatriz").value.length < 8)
			sMsje += "* No es un número válido el celular de la Casa Matriz\n";
		if (document.getElementById("emailCasaMatriz").value != "" && !validaMail(document.getElementById("emailCasaMatriz").value))
			sMsje += "* E-Mail Casa Matriz inválido\n";

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
//			 document.getElementById("numAdherentesMutual").value = "";
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

function onfocusEmp(id)
{
	x = document.getElementById(id);
	tip = x.type
	if(tip  == 'text')
	 	document.getElementById(id).focus();
	else
	 	document.getElementById('razonSocial').focus();
}
onfocusEmp('rutEmpresaEd');
</script>