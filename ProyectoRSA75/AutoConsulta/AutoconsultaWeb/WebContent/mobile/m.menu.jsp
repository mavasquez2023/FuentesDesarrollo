<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<logic:notPresent name="usuario.nombre"> 
<%
  response.sendRedirect("/AutoconsultaWeb/mobile/m.ingreso.jsp");
%>  
</logic:notPresent>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<title>laaraucana.cl</title>
<meta content='width=device-width; initial-scale=1.0' name='viewport'>
<meta http-equiv="content-type" content="text/html;charset=ISO-8859-1" />
<link rel="shortcut icon" href="http://www.laaraucana.cl/favicon.ico">
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<script>
function doAccion(opc, subop) {
  document.fOpc.md2_opcion.value = opc;
  document.fOpc.md2_opcionMnu.value = subop;
  if (opc=="cns") {
     document.fOpc.action = "consultas.do";
  } else {
    document.fOpc.action = "certificados.do";
  }
  document.fOpc.submit();
}
</script>
</head>
<body>
<form name="fOpc" id="fOpc" method="post" action="">
  <input type="hidden" name="md2_opcion" id="md2_opcion" value=""/>
  <input type="hidden" name="md2_opcionMnu" id="md2_opcionMnu" />
</form>
<div class="header">
  <div class="logo"><a href="http://www.laaraucana.cl"><img src="img/logo_araucana_ss.gif" alt="laaraucana.cl" width="300" height="50"></a></div>
  <h1>Auto Consulta</h1>
  <div class="user"><bean:write name="usuario.nombre"/> | <a href="logout.do">Cerrar sesión</a></div>
</div>
<div class="content">
  <h2>Consultas</h2>
  <ul class="menu">
    <li><a href="#" onclick="doAccion('cns',1)">Estado licencia m&eacute;dica</a></li>
    <li><a href="#" onclick="doAccion('cns',2)">Cartola de Ahorro</a></li>
    <li><a href="#" onclick="doAccion('cns',3)">Cr&eacute;ditos Vigentes</a></li>
    <li><a href="#" onclick="doAccion('cns',4)">Liquidaci&oacute;n de reembolsos</a></li>
  </ul>
  <h2>Certificados</h2>
  <ul class="menu">
    <li><a href="#" onclick="doAccion('cert',5)">Asignaci&oacute;n familiar</a></li>
    <li><a href="#" onclick="doAccion('cert',6)">Licencia M&eacute;dica</a></li>
    <li><a href="#" onclick="doAccion('cert',7)">Deuda Vigente de Cr&eacute;dito</a></li>
    <li><a href="#" onclick="doAccion('cert',8)">Afiliaci&oacute;n</a></li>
  </ul>
</div>
<div class="footer"><a href="http://www.laaraucana.cl">La Araucana.cl</a></div>
</body>
</html>