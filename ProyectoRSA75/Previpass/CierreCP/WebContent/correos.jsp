<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="cl.araucana.cierrecpe.business.Parametros;"%>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="GENERATOR" content="IBM WebSphere Studio" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<LINK href="theme/Interna_Araucana.css" rel="stylesheet" type="text/css">
<title>Correo Usuarios</title>
<script language="JavaScript 1.2" type="text/javascript">
function Aceptar(){
if (form1.emails.value==""){
		alert("Ingrese correo del Encargado");
		form1.emails.focus();
		return false;
}
form1.submit();
return true;	
}
</script>
</head>
<body topmargin="0" leftmargin="0">
<form action= "ActualizarCorreoEncargados.do" name="form1">
<table border="0" cellspacing="0" cellpadding="0">
<tr><td class="text-11">&nbsp;</td></tr>
<tr><td class="titulos_formularios" align="center" colspan="2">Configuración Correo Encargados</td></tr>
<tr><td class="text-11" height="50">&nbsp;</td></tr>
<tr><td class="textos-formularios1" colspan="2">Ingrese uno o mas correos para informar resultado de los procesos de cierre.</td></tr>
<tr><td class="text-11">&nbsp;</td></tr>
<tr valign="top"><td width="220" class="text-11">Correo Encargado(s):</td><td width="460">
<input name="emails" value="<%=Parametros.getInstance().getParam().getEmailUsuario()%>" size=60 maxlength=100 ></td></tr>
<tr><td class="text-11" height="50">&nbsp;</td></tr>
<tr><td class="text-11" colspan="2" align="center">
		<table  border="0" cellspacing="0" cellpadding="0">
			<tr><td class="text-11" width="150" align="center"><INPUT type="button" name="aceptar" class="salir" value="Aceptar" onclick="Aceptar();"></td><td class="text-11" width="150" align="center"><INPUT type="button" name="cancelar" class="salir" value="Cancelar" onclick="window.close();"></td></tr>
		</table>
</td></tr>
</table>
</form>
</body>
</html>