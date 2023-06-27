<%@ include file="/html/comun/taglibs.jsp" %>
<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
<html:form action="/EditarConvenio" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosCrear" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<html:hidden property="eliminaNominas" name="eliminaNominas" styleId="eliminaNominas" value="NO" />
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
      	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="textos-formularios3">
			<tr> 
				<td width="15%"><strong>RUT:</strong></td>
				<td width="20%">
					<a href="<c:url value="/ListarEmpresas.do?accion=admin&subAccion=empresas&subSubAccion=empresaLista" />" class="links">
						<nested:hidden property="rutEmpresaFmt" write="true"/>
					</a>
				</td>
				<td><strong>Empresa:</strong></td>
				<td>
					<nested:hidden property="nombreEmpresa" write="true"/>
					<%--nested:write property="nombreEmpresa" /--%>
				</td>
			</tr>
          	<%--><tr> 
	            <td><strong>Convenio:</strong></td>
	            <td>
	            	<nested:select property="opcConvenio" styleId="opcConvenio" styleClass="campos" onchange="javascript:bCancel=true;submit();">
	            		<nested:optionsCollection property="convenios"/>
	            	</nested:select>
				</td>
	            <td colspan="2">&nbsp;</td>
          	</tr><--%>
        </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="1">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Creaci&oacute;n de Convenio</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo *</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:text property="codigoConvenio" styleId="codigoConvenio" styleClass="campos" size="6" maxlength="2" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
                </td>
                <td width="25%" height="17" class="barratablas"><strong>Descripci&oacute;n *</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:text property="nombreConvenio" styleId="nombreConvenio" styleClass="campos" size="45" maxlength="30" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
                </td>
              </tr>
              <tr>
                <td height="17" class="barratablas"><strong>Estado *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:select property="opcHabilitado" styleId="opcHabilitado" styleClass="campos">
                		<html:option value="0">Deshabilitado</html:option>
                		<html:option value="1">Habilitado</html:option>
                	</nested:select>
              	</td>
                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCaja" styleId="opcCaja" styleClass="campos">
						<html:option value="0">Sin caja</html:option>
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
              <!--  <tr>
                <td height="17" class="barratablas"><strong>Grupo de Convenios *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:text property="codigoGrupoConvenio" styleId="codigoGrupoConvenio" styleClass="campos" size="14" maxlength="9" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
		     	</td>
                <td height="17" class="barratablas"><strong>C&aacute;lculo de movimiento personal *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCalculoMovPersonal" styleId="opcCalculoMovPersonal" styleClass="campos">
						<html:option value="1">S&iacute;</html:option>
						<html:option value="0">No</html:option>
					</nested:select>
                </td>
              </tr>-->
              <tr> 
                <td height="17" class="barratablas" colspan="4"><strong>Actividad Econ&oacute;mica</strong></td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&oacute;digo *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:text property="opcActividadEconomicaMostrar" styleId="opcActividadEconomicaMostrar" size="9" maxlength="6" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);" styleClass="campos" onchange="javascript:txtRubrosOnChange(this);"/>
                </td>
                <td height="17" class="barratablas"><strong>Nombre *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:select property="opcActividadEconomica" styleClass="campos" styleId="opcActividadEconomica" onchange="javascript:rubrosOnChange(this);">
                		<html:option disabled="true" value="">-Seleccionar-</html:option>
                		<nested:optionsCollection property="actividadesEconomicas"/>
                	</nested:select>
                </td>
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
    	<html:submit property="operacion" styleClass="btn3" value="Guardar"/>
    	<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
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
			alert("Código de actividad económica no existe!");
			cmbRubro.selectedIndex = 0;
		}
	}

	function validaFormulario(f) 
	{
		var sMsje = "";
		if (!validaReq(document.getElementById("codigoConvenio")))
			sMsje += "* Debe ingresar el código del convenio\n";
		else
		{
			var codigo = parseInt(limpiaNumero(document.getElementById('codigoConvenio').value, ''));
			if (codigo < 1)
			sMsje += "* Código del convenio inválido\n";
		}
		if (!validaReq(document.getElementById("nombreConvenio")))
			sMsje += "* Debe ingresar la descripción del convenio\n";
		if (!validaReq(document.getElementById("codigoGrupoConvenio")))
			sMsje += "* Debe ingresar el código del grupo de convenios\n";
		var opcActEc = document.getElementById("opcActividadEconomica");
		if (opcActEc.options[opcActEc.selectedIndex].value == "")
			sMsje += "* Debe seleccionar la actividad económica\n";
			
		if (sMsje == "") 
		{
			//CHAR
			if (!validaDescripcion(document.getElementById("nombreConvenio").value))
				sMsje += "* Caracteres inválidos en la descripción del convenio\n";
				
			//NUM
			if (!validaNumero(document.getElementById("codigoConvenio").value))
				sMsje += "* No es un número válido el id del convenio\n";
			if (!validaNumero(document.getElementById("codigoGrupoConvenio").value))
				sMsje += "* No es un número válido el código del grupo de convenios\n";
		}
		
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
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
			alert('Código de grupo de convenios no existe!');
			textInput.form.comboGrupoConvenio.value = oldValue;
			textInput.value = oldValue;
		}
		return true;
	}
	
	function foco()
	{
		document.getElementById('codigoConvenio').focus();
	}
	foco();
</script>
