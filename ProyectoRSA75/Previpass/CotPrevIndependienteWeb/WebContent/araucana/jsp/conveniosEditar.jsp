<%@ include file="/html/comun/taglibs.jsp" %>
<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
<html:form action="/EditarConvenio" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosEditar" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
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
      	<td valign="top">
      	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			<tr> 
				<td width="15%"><strong>RUT:</strong></td>
				<td width="20%">
					<nested:hidden property="rutEmpresaFmt" write="true"/>
				</td>
				<td><strong>Nombre:</strong></td>
				<td>
					<nested:hidden property="nombreEmpresa" write="true"/>
				</td>
			</tr>
          	<tr> 
	            <td><strong>Convenio:</strong></td>
	            <td>
	            	<nested:select property="opcConvenio" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="convenios"/>
	            	</nested:select>
				</td>
	            <td colspan="2">&nbsp;</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
         	</tr>
        </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Edici&oacute;n de Convenio</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:hidden property="codigoConvenio" write="true"/>
                </td>
                <td width="25%" height="17" class="barratablas"><strong>Descripci&oacute;n *</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:text property="nombreConvenio" styleId="nombreConvenio" styleClass="campos" size="45" maxlength="30" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
                </td>
              </tr>
              <tr>
                <td height="17" class="barratablas"><strong>Estado *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:select property="opcHabilitado" styleClass="campos">
                		<html:option value="0">Deshabilitado</html:option>
                		<html:option value="1">Habilitado</html:option>
                	</nested:select>
              	</td>
                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCaja" styleClass="campos">
						<html:option value="0">INP-CAJA</html:option>
						<nested:optionsCollection property="cajas" />
					</nested:select>
              	</td>
              </tr>
              <tr> 
                <td height="17" class="barratablas" colspan="4"><strong>Grupo de Convenios *</strong></td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&oacute;digo:</strong></td>
                <td height="17" class="textos_formularios">                	
                	<nested:text property="codigoGrupoConvenio" styleId="codigoGrupoConvenio" styleClass="campos" size="14" maxlength="9" onkeyup="javascript:soloNumero(this);" onblur="javascript:soloNumero(this);return cambiaCodigoGC(this);"/>
		       	</td>
                <td height="17" class="barratablas">Nombre:</td>
                <td height="17" class="textos_formularios">
                	<nested:select property="codigoGrupoConvenio" styleClass="campos" onclick="javascript:document.getElementById('codigoGrupoConvenio').value=this.value" styleId="comboGrupoConvenio">
                		<html:option value="0">Sin asignar</html:option>
                		<nested:optionsCollection property="grupos" label="nombre" value="idGrupoConvenio" />
                	</nested:select>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&aacute;lculo Mov. Personal *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCalculoMovPersonal" styleClass="campos">
						<html:option value="1">S&iacute;</html:option>
						<html:option value="0">No</html:option>
					</nested:select>
              	</td>
                <td height="17" class="barratablas">&nbsp;</td>
                <td height="17" class="textos_formularios">&nbsp;</td>
              </tr>
              <tr> 
                <td height="17" class="barratablas" colspan="4"><strong>Actividad Econ&oacute;mica *</strong></td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&oacute;digo:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:text property="opcActividadEconomicaMostrar" styleId="opcActividadEconomicaMostrar" maxlength="6" size="9" styleClass="campos" onkeyup="javascript:soloNumero(this);" onblur="javascript:txtRubrosOnChange(this);"/>
                </td>
                <td height="17" class="barratablas"><strong>Nombre:</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:select property="opcActividadEconomica" styleClass="campos" styleId="opcActividadEconomica" onchange="javascript:rubrosOnChange(this);">
                		<html:option disabled="true" value="">-Seleccionar-</html:option>
                		<nested:optionsCollection property="actividadesEconomicas"/>
                	</nested:select>
                </td>
              </tr>
              <tr> 
                <td height="4" colspan="4" bgcolor="#85b4be"></td>
              </tr>
            </table>
            </td>
        </tr>
      </table></td>
  </tr>
	<tr>
		<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
	</tr>
  <tr>
    <td align="center" valign="top"><br />
    	<html:submit property="operacion" styleClass="btn3" value="${guardar}"/>
    	<html:cancel property="operacion" styleClass="btn3" value="${cancelar}"/>
      </td>
  </tr>
</table>
</html:form>
<script language="javaScript">
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
			cmbRubro.selectedIndex = i;
		else 
		{
			alert("C�digo de actividad econ�mica no existe!");
			cmbRubro.selectedIndex = 0;
		}
	}

	function validaFormulario(f) 
	{
		var sMsje = "";
		if (!validaReq(document.getElementById("nombreConvenio")))
			sMsje += "* Debe ingresar la descripci�n del convenio\n";
		if (!validaReq(document.getElementById("codigoGrupoConvenio")))
			sMsje += "* Debe ingresar el c�digo del grupo de convenios\n";
		opcActEc = document.getElementById("opcActividadEconomica");
		if (opcActEc.options[opcActEc.selectedIndex].value == "")
			sMsje += "* Debe seleccionar la actividad econ�mica\n";
			
		if (sMsje == "")
		{
			if (!validaDescripcion(document.getElementById("nombreConvenio").value))
				sMsje += "* Caracteres inv�lidos en la descripci�n del convenio\n";
			if (!validaNumero(document.getElementById("codigoGrupoConvenio").value))
				sMsje += "* C�digo del grupo de convenios, no es un n�mero v�lido\n";
		}
		
		if (sMsje != "") 
		{
			alert("Errores de validaci�n:\n\n" + sMsje);
			return false;
		}
		return true;
	}

	function cambiaCodigoGC(textInput) 
	{
		var oldValue = textInput.form.comboGrupoConvenio.value;
		textInput.form.comboGrupoConvenio.value = textInput.value;
		if (textInput.form.comboGrupoConvenio.value != textInput.value) 
		{
			alert('C�digo de grupo de convenios no existe!');
			textInput.form.comboGrupoConvenio.value = oldValue;
			textInput.value = oldValue;
		}
		return true;
	}

	function foco()
	{
		document.getElementById('nombreConvenio').focus();
	}
	foco();
</script>
