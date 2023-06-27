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
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="foco()">
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<html:form action="/SucursalesEditar" styleId="formulario" onsubmit="return onFormSubmit(this)">
	<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
	<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
	<nested:equal property="tipoOper" value="crear">
		<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="sucursalesCrear" />
	</nested:equal>
	<nested:equal property="tipoOper" value="editar">
		<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="sucursalesEditar" />
	</nested:equal>
	<nested:equal property="tipoOper" value="ficha">
		<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="sucursalesFicha" />
	</nested:equal>
	<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa"/></html:hidden>
	<html:hidden property="rutEmpresaFmt"><nested:write property="rutEmpresaFmt"/></html:hidden>
	<html:hidden property="nombreEmpresa"><nested:write property="nombreEmpresa"/></html:hidden>
	<html:hidden property="combosLugaresClick" styleId="combosLugaresClick" value="false"/>
	<c:set var="opcioneditcrea" value="c"/>
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
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="tabla-datos">
				<tr>
					<td width="15%"><strong>RUT:</strong></td>
					<td width="20%">
						
							<nested:write property="rutEmpresaFmt" />
						
					<td><strong>Empresa:</strong></td>
					<td>
						<nested:write property="nombreEmpresa" />
					</td>
				</tr>
				<nested:notEqual property="tipoOper" value="crear">
					<tr>
						<td><strong>Sucursal:</strong></td>
						<td>
							<html:select property="opcSucursal" styleClass="campos" onchange="javascript:bCancel=true;submit();">
								<html:optionsCollection property="sucursales" />
							</html:select>
						</td>
						<td colspan="2">&nbsp;</td>
					</tr>
				</nested:notEqual>
				<tr>
					<td height="4" colspan="4" bgcolor="#85b4be"></td>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				<tr valign="bottom">
					<nested:notEqual property="tipoOper" value="crear">
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Edici&oacute;n de Sucursal</strong>
							<c:set var="opcioneditcrea" value="e"/>
						</td>
					</nested:notEqual>
					<nested:equal property="tipoOper" value="crear">
						<td height="30" bgcolor="#FFFFFF" class="titulo">
							<strong>Creaci&oacute;n de Sucursal</strong>
						</td>
					</nested:equal>
				</tr>
			</table>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
				<tr>
					<td height="4" colspan="4" bgcolor="#85b4be"></td>
				</tr>
				<tr>
					<td width="12%" height="17" class="barratablas">C&oacute;digo *</td>
					<td height="17" class="textos_formularios">
						<%-- Esta editando o mostrando, o creando? --%>
						<nested:equal property="tipoOper" value="crear">
							<nested:text property="codigo" styleId="codigo" size="10" maxlength="6" styleClass="campos" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
						</nested:equal>
						<nested:notEqual property="tipoOper" value="crear">
							<bean:write name="SucursalActionForm" property="codigo"/>
							<html:hidden property="codigo" styleId="codigo"/>
						</nested:notEqual>
					</td>
					<td height="17" class="barratablas"><strong>Nombre *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="nombre" styleId="nombre" size="45" maxlength="30" styleClass="campos" onblur="javascript:soloNombreSuc(this);" onkeyup="javascript:soloNombreSuc(this);"/>
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Direcci&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:textarea property="direccion" styleId="direccion" styleClass="campos" onblur="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" onkeyup="javascript:cuentaTxtArea(this, 80);javascript:soloDireccion(this);" rows="5" cols="25"></html:textarea>
					</td>
					<td height="17" class="barratablas"><strong>N&ordm; *</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="numero" styleId="numero" size="10" maxlength="6" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
					</td>
				</tr>
				<tr>
					<td width="9%" height="17" class="barratablas">Departamento</td>
					<td width="18%" class="textos_formularios">
						<html:text property="dpto" styleId="dpto" size="10" maxlength="6" styleClass="campos" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);"/>
					</td>
					<td height="17" class="barratablas">Regi&oacute;n *</td>
					<td height="17" class="textos_formularios">
						<html:select property="opcRegion" styleId="opcRegion" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="regiones" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas"><strong>Ciudad *</strong></td>
					<td height="17" class="textos_formularios">
						<html:select property="opcCiudad" styleId="opcCiudad" styleClass="campos" onchange="javascript:combosChange(this);">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="ciudades" />
						</html:select>
					</td>
					<td height="17" class="barratablas">Comuna *</td>
					<td height="17" class="textos_formularios">
						<html:select property="opcComuna" styleId="opcComuna" styleClass="campos">
							<html:option value="-1" >-Seleccione-</html:option>
							<html:optionsCollection property="comunas" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Tel&eacute;fono *</td>
					<td height="17" class="textos_formularios">
						<html:text property="codigoFono" styleId="codigoFono" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						<html:text property="fono" styleId="fono" maxlength="9" size="12" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<td height="17" class="barratablas"><strong>Celular</strong></td>
					<td height="17" class="textos_formularios">
						<html:text property="celular" styleId="celular" maxlength="9" size="13" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
				</tr>
				<tr>
					<td height="17" class="barratablas">Fax</td>
					<td height="17" class="textos_formularios">
						<html:text property="codigoFax" styleId="codigoFax" maxlength="4" size="6" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
						<html:text property="fax" styleId="fax" maxlength="9" size="12" styleClass="campos" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);"/>
					</td>
					<td height="17" class="barratablas">e-mail</td>
					<td width="27%" height="17" class="textos_formularios">
						<html:text property="email" styleId="email" maxlength="100" size="45" styleClass="campos" onblur="javascript:soloEmail(this);" onkeyup="javascript:soloEmail(this);"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="right" valign="top" class="leyenda">(*)Campos Obligatorios</td>
		</tr>
		<tr>
			<td align="center" valign="top">
				<br />
				<html:submit property="operacion" styleClass="btn3" value="Guardar"/>
				<html:cancel property="operacion" styleClass="btn3" value="Cancelar"/>
			</td>
		</tr>
	</table>
</html:form>
<input type="hidden" id="opcionfoco" value="${opcioneditcrea}"/>
<script language="javaScript">
<!--
	function combosChange(combo) 
	{
		if (combo.id == "opcRegion") 
		{
			document.getElementById("opcCiudad").value = "-1";
			document.getElementById("opcComuna").value = "-1";

			bCancel = true;
			document.getElementById("combosLugaresClick").value = "true";
			document.getElementById("formulario").submit();
		} else if (combo.id == "opcCiudad") 
		{
			document.getElementById("opcComuna").value = "-1";

			bCancel = true;			
			document.getElementById("combosLugaresClick").value = "true";
			document.getElementById("formulario").submit();
		}
	}
	
	function validaFormulario(f) 
	{
		//Valida prescencia de campos
		var sMsje = "";

		//sucursal
		if (document.getElementById("subSubAccion").value == "sucursalesCrear") 
		{
			if (!validaReq(document.getElementById("codigo")))
				sMsje += "* Debe ingresar el código de la sucursal\n";
			if (!validaNombreSuc(document.getElementById("codigo").value))
				sMsje += "* Caracteres inválidos en el código de la sucursal\n";
		}
		if (!validaReq(document.getElementById("nombre")))
			sMsje += "* Debe ingresar el nombre de la sucursal\n";
		if (!validaNombreSuc(document.getElementById("nombre").value))
			sMsje += "* Caracteres inválidos en el nombre de la sucursal\n";
		
		if (!validaReq(document.getElementById("direccion")))
			sMsje += "* Debe ingresar la dirección de la sucursal\n";
		if (validaReq(document.getElementById("direccion")) &&
			!validaDireccion(document.getElementById("direccion").value))
			sMsje += "* Caracteres inválidos en la dirección de la sucursal\n";
		if (!validaReq(document.getElementById("numero")))
			sMsje += "* Debe ingresar el número de la sucursal\n";
		if (validaReq(document.getElementById("numero")) &&
			!validaDescripcion(document.getElementById("numero").value))
			sMsje += "* Caracteres inválidos en el número de la sucursal\n";
		if (validaReq(document.getElementById("dpto")) &&
			!validaDescripcion(document.getElementById("dpto").value))
			sMsje += "* Caracteres inválidos en el departamento de la sucursal\n";

		if (document.getElementById("opcRegion").value == "-1")
			sMsje += "* Debe seleccionar la región de la sucursal\n";
		if (document.getElementById("opcCiudad").value == "-1")
			sMsje += "* Debe seleccionar la ciudad de la sucursal\n";
		if (document.getElementById("opcComuna").value == "-1")
			sMsje += "* Debe seleccionar la comuna de la sucursal\n";
			
		if (!validaReq(document.getElementById("fono")))
			sMsje += "* Debe ingresar el teléfono de la sucursal\n";
		else if (document.getElementById("fono").value.length < 6)
			sMsje += "* Número de teléfono de la sucursal inválido\n";
		if (document.getElementById("codigoFono").value.length < 1)
			sMsje += "* Debe ingresar un código de area para el telefono\n";

		if ((document.getElementById("codigoFax").value.length<1)&&
		(document.getElementById("fax").value.length>0))
			sMsje += "* No es un código válido para la sucursal.\n";
		 
		 if ((document.getElementById("codigoFax").value.length>0)&&
		(document.getElementById("fax").value.length<6))
				sMsje += "* No es un número válido el fax de la sucursal.\n";
		 
		if(document.getElementById("celular").value!='')
			if (!validaFormatoCelular(document.getElementById("celular").value))
				sMsje += "* No es un número válido el celular de la sucursal\n";

		if (validaReq(document.getElementById("email")) &&
			!validaMail(document.getElementById("email").value))
			sMsje += "* Formato de e-mail inválido en e-mail de la sucursal.\n";

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}
		function foco()
	{	
		foco =  document.getElementById('opcionfoco').value;		
		if(foco == 'c')
			 document.getElementById('codigo').focus();
		else 
			 document.getElementById('nombre').focus();
		
	}
	// -->
</script>
</body>
</html:html>
