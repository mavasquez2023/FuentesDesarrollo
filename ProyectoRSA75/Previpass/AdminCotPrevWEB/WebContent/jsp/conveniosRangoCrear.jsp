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
<body onLoad="poneFoco('codigoConvenioDesde')">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/CrearRangoConvenio" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosCrearRango" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
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
      	<td valign="top">
      	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
			<tr> 
				<td width="15%"><strong>RUT:</strong></td>
				<td width="20%">
				
						<nested:hidden property="rutEmpresaFmt" write="true"/>
				
				</td>
				<td><strong>Empresa:</strong></td>
				<td>
					<nested:hidden property="nombreEmpresa" write="true"/>
				</td>
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
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Creaci&oacute;n de Rango de Convenios</strong></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
              <tr> 
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo inicial *</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:text property="codigoConvenioDesde" styleId="codigoConvenioDesde" styleClass="campos" size="6" maxlength="3" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
                </td>
                <td width="25%" height="17" class="barratablas"><strong>C&oacute;digo final *</strong></td>
                <td width="25%" height="17" class="textos_formularios">
                	<nested:text property="codigoConvenioHasta" styleId="codigoConvenioHasta" styleClass="campos" size="6" maxlength="3" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
                </td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Prefijo descripci&oacute;n *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:text property="nombreConvenio" styleId="nombreConvenio" styleClass="campos" size="40" maxlength="30" onblur="javascript:soloAlfanumerico(this, '.');" onkeyup="javascript:soloAlfanumerico(this, '.');"/>
                </td>
                <td height="17" class="barratablas"><strong>Estado *</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:select property="opcHabilitado" styleId="opcHabilitado" styleClass="campos">
                		<html:option value="0">Deshabilitado</html:option>
                		<html:option value="1">Habilitado</html:option>
                	</nested:select>
              	</td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>Caja de Compensaci&oacute;n *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCaja" styleId="opcCaja" styleClass="campos">
						<html:option value="0">Sin caja</html:option>
						<nested:optionsCollection property="cajas" />
					</nested:select>
              	</td>
                <td height="17" class="barratablas"><strong>Grupo de Convenios *</strong></td>
                <td height="17" class="textos_formularios">
					<nested:text property="grupoConvenio" styleId="grupoConvenio" styleClass="campos" size="12" maxlength="9" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
              	</td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&aacute;lculo de movimiento personal:</strong></td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcCalculoMovPersonal" styleId="opcCalculoMovPersonal" styleClass="campos">
						<html:option value="1">S&iacute;</html:option>
						<html:option value="0">No</html:option>
					</nested:select>
                </td>
                <td height="17" class="barratablas">&nbsp;</td>
                <td height="17" class="textos_formularios">&nbsp;</td>
              </tr>
              <%--><tr> 
                <td height="17" class="barratablas">
                	<strong>Opci&oacute;n Proceso:</strong>
                </td>
                <td height="17" class="textos_formularios">
					<nested:select property="opcOpcionProceso" styleId="opcOpcionProceso" styleClass="campos">
						<nested:optionsCollection property="opcionesProcesos"/>
					</nested:select>
              	</td>
                <td height="17" class="barratablas">
                	&nbsp;
                </td>
                <td height="17" class="textos_formularios">
                	&nbsp;
                </td>
              </tr><--%>
              <tr> 
                <td height="17" class="barratablas" colspan="4"><strong>Actividad Econ&oacute;mica *</strong></td>
              </tr>
              <tr> 
                <td height="17" class="barratablas"><strong>C&oacute;digo</strong></td>
                <td height="17" class="textos_formularios">
                	<nested:text property="opcActividadEconomicaMostrar" styleId="opcActividadEconomicaMostrar" maxlength="6" size="9" styleClass="campos" onchange="javascript:txtRubrosOnChange(this);" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/>
                </td>
                <td height="17" class="barratablas"><strong>Nombre</strong></td>
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
         		<%--><tr>
          			<td height="17" class="barratablas" colspan="4">
          				<strong>Instituci&oacute;n Accidente del Trabajo</strong>
          			</td>
          		<tr>
          		<tr>
					<td height="17" class="barratablas"><strong>Nombre:</strong></td>
					<td height="17" class="textos_formularios" colspan="3">
						<html:select property="opcMutual" styleId="opcMutual" styleClass="campos">
							<html:option value="0">Sin mutual</html:option>
							<html:optionsCollection property="mutuales" />
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas"><strong>N&ordm; Adherentes:</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="numAdherentesMutual" styleClass="campos" styleId="numAdherentesMutual" maxlength="9" />
					</td>
					<td height="17" class="barratablas"><strong>C&aacute;lculo Individual:</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCalculoIndividual" styleClass="campos">
							<html:option value="true">Si</html:option>
							<html:option value="false">No</html:option>
						</html:select>
					</td>
          		</tr>
          		<tr>
					<td height="17" class="barratablas"><strong>Tasa Adicional:</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="tasaAdicionalMutual" styleClass="campos" styleId="tasaAdicionalMutual" maxlength="7" />
					</td>
					<td height="17" class="barratablas">&nbsp;</td>
					<td height="17" class="textos_formularios">&nbsp;</td>
          		</tr><--%>
            </table>
            <br />
            </td>
        </tr>
      </table></td>
  </tr>
<tr>
	<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
</tr>
  <tr align="center">
    <td valign="top"><br />
    	<html:submit property="operacion" styleClass="btn3" value="Generar"/>
    	<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
      </td>
  </tr>
</table>
</html:form>
<script language="javaScript">
<!--
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
		if (!validaReq(document.getElementById("codigoConvenioDesde")))
			sMsje += "* Debe ingresar el código del convenio inicial\n";
		if (!validaReq(document.getElementById("codigoConvenioHasta")))
			sMsje += "* Debe ingresar el código del convenio final\n";
		if (!validaReq(document.getElementById("nombreConvenio")))
			sMsje += "* Debe ingresar el prefijo de la descripción del convenio\n";
		if (!validaReq(document.getElementById("grupoConvenio")))
			sMsje += "* Debe ingresar el grupo de convenio\n";
		var opcActEc = document.getElementById("opcActividadEconomica");
		if (opcActEc.options[opcActEc.selectedIndex].value == "")
			sMsje += "* Debe seleccionar la actividad económica\n";
			
		if (sMsje == "") 
		{
			//CHAR
			if (!validaChrs(document.getElementById("nombreConvenio").value))
				sMsje += "* Caracteres inválidos en la descripción del convenio\n";
				
			//NUM
			if (!validaUInt(document.getElementById("codigoConvenioDesde").value))
				sMsje += "* No es un número válido el id del convenio inicial\n";
			if (!validaUInt(document.getElementById("codigoConvenioHasta").value))
				sMsje += "* No es un número válido el id del convenio final\n";
			if (!validaUInt(document.getElementById("grupoConvenio").value))
				sMsje += "* No es un número válido el id del grupo de convenios\n";
				
			//Convenio desde debe ser menor que convenio hasta
			if (parseInt(document.getElementById("codigoConvenioDesde").value) <= 0)
				sMsje += "* El código del convenio inicial debe ser mayor que 0\n";
			if (parseInt(document.getElementById("codigoConvenioDesde").value) >= parseInt(document.getElementById("codigoConvenioHasta").value))
				sMsje += "* El código del convenio final debe ser mayor al código del convenio inicial\n";
		}
		
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}
// -->
</script>
</body>
</html:html>
