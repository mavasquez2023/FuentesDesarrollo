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
	<TITLE>Entidades</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
	<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body onLoad="foco();">
<html:form action="/MovPersonalEditar" styleId="formulario">
	<input type="hidden" id="accionInterna" name="accionInterna" />
	<input type="hidden" id="id" name="id" value="<nested:write property="id"/>"/>
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
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr valign="bottom"> 
						<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Tipo movimiento Personal</strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
					<tr>
						<td width="120" class="barratablas">
							<strong>Descripci&oacute;n:</strong>
						</td>	
						<td width="290" class="textos_formularios">
							<input type="text" id="nombre" name="nombre" value="<nested:write property="nombre" />" onblur="javascript:soloDescripcion(this);" onkeyup="javascript:soloDescripcion(this);" class="campos" maxlength="45" size="63" />						
						</td>
					</tr>
					<tr>
						<td width="120" class="barratablas">
							<strong>Fecha de Inicio:</strong>
						</td>	
						<td width="290" class="textos_formularios">
							<nested:equal property="fechaInicoObligatoria" value="1">
								<input type="radio" id="fechaInicoObligatoria" name="fechaInicoObligatoria" value="1" checked/>S&iacute;
								<input type="radio" id="fechaInicoObligatoria" name=fechaInicoObligatoria value="0" />No
							</nested:equal>
							<nested:equal property="fechaInicoObligatoria" value="0">
								<input type="radio" id="fechaInicoObligatoria" name="fechaInicoObligatoria" value="1" />S&iacute;
								<input type="radio" id="fechaInicoObligatoria" name="fechaInicoObligatoria" value="0" checked/>No
							</nested:equal>
						</td>
					</tr>
					<tr>
						<td width="120" class="barratablas">
							<strong>Fecha de T&eacute;rmino:</strong>
						</td>	
						<td width="290" class="textos_formularios">
							<nested:equal property="fechaTerminoObligatoria" value="1">
								<input type="radio" id="fechaTerminoObligatoria" name="fechaTerminoObligatoria" value="1" checked/>S&iacute;
								<input type="radio" id="fechaTerminoObligatoria" name="fechaTerminoObligatoria" value="0" />No
							</nested:equal>
							<nested:equal property="fechaTerminoObligatoria" value="0">
								<input type="radio" id="fechaTerminoObligatoria" name="fechaTerminoObligatoria" value="1" />S&iacute;
								<input type="radio" id="fechaTerminoObligatoria" name="fechaTerminoObligatoria" value="0" checked/>No
							</nested:equal>
						</td>
					</tr>
					<tr>
						<td height="4" bgcolor="#85b4be" colspan="2"/>
					</tr>
					<tr>
						<td  colspan="2"> &nbsp;</td>
					</tr>
				
					<tr>
						<td width="410" colspan="2" align="center" >
							<html:button property="operacion" styleClass="btn3" value="Aceptar" onclick="javascript:guardarTipo();"/>
							<html:button property="operacion" styleClass="btn3" value="Cancelar" onclick="javascript:cancelarTipo();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
</body>
<script>
<!--
	function guardarTipo()
	{
		var formu=document.getElementById('formulario');
		formu.accionInterna.value = 'GUARDAR';
		
		if (validar(formu))
			formu.submit();
	}
	
	function cancelarTipo()
	{
		var formu=document.getElementById('formulario');
		formu.accionInterna.value = 'CANCELAR';
		formu.submit();
	}
	
	function validar(frm) 
	{
		var sMsje = "";
		
		if(!validaReq(document.getElementById("nombre")))
			sMsje += "* Debe ingresar la descripción\n";
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;		

	}
	function foco(){
	document.getElementById('nombre').focus();
}
// -->
</script>
</html:html>
