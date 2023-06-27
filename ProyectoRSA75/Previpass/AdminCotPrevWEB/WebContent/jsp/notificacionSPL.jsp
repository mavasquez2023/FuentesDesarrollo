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
	<meta name="author" content="Schema (http://www.Schema.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body onLoad="poneFoco('idPagoSpl')">
<html:form action="/notificacionSPL" styleId="formulario" onsubmit="return onFormSubmit(this);">
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
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tabla-datos">
				<tr> 
	           		<td><strong>Id Pago:</strong></td>
	           		<td><html:text property="idPagoSpl" styleId="idPagoSpl" maxlength="13" size="18" styleClass="campos" onblur="javascript:soloNumero(this, '')" onkeyup="javascript:soloNumero(this, '')"/></td>
	           		<td align="right"><html:submit property="operacion" styleClass="btn3" value="Guardar"/>&nbsp;&nbsp;&nbsp;</td>
	           	</tr>	
	         	<tr> 
	           		<td height="4" colspan="4" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
<script language="javaScript"> 
	function validaFormulario(f) 
	{
		//Valida caracteres validos en caja de busqueda
		var sMsje = "";
		if (document.getElementById("idPagoSpl").value.length == 0)
			sMsje += "* Debe ingresar al menos 1 dígito\n";
		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		return true;
	}
</script>
</body>
</html:html>
