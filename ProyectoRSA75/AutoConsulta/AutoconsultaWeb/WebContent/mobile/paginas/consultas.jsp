<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String estaOpcion="";
String sOpcionMnu="";

if ((String)request.getParameter("md2_opcion")!=null) {
  estaOpcion = (String)request.getParameter("md2_opcion");
  
} else if (estaOpcion.equals("")) {
    estaOpcion = "" + (String)session.getAttribute("md2_opcion");
    sOpcionMnu = "1";
    
} else if (estaOpcion.equals("")) {
     response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp");
}

sOpcionMnu = "" + (String)session.getAttribute("md2_opcionMnu");
if (sOpcionMnu==null || sOpcionMnu=="") {
  response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp");
}

String sTitulo = "";
String sUriGo = "";
String sUriJsp = "";
if (sOpcionMnu.equals("1") || sOpcionMnu.equals("0")) {
  sTitulo = "Estado Licencias";
  sUriGo = "getEstadoLicenciasMedica.do";
  sUriJsp = "certificadoAfiliacion";
} else if (sOpcionMnu.equals("2")) {
	  sTitulo = "Cartola Ahorro";
	  sUriGo = "getCartolaAhorro.do";
} else if (sOpcionMnu.equals("3")) {
	  sTitulo = "Cr&eacute;ditos vigente";
	  sUriGo = "getCreditosVigente.do";
} else if (sOpcionMnu.equals("4")) {
	  sTitulo = "Liquidaci&oacute;n de reembolsos";
	  sUriGo = "getListaLiquidacionesReembolso.do";
} else {
  response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp");
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<title>laaraucana.cl</title> 
<meta content='width=device-width; initial-scale=1.0' name='viewport'>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="http://www.laaraucana.cl/favicon.ico">
</head>
<body>
<div class="header">
  <div class="logo"><a href="http://www.laaraucana.cl"><img src="img/logo_araucana_ss.gif" alt="laaraucana.cl" width="300" height="50"></a></div>
  <h1>Auto Consulta</h1>
  <div class="user"><bean:write name="usuario.nombre"/> | <a href="logout.do">Cerrar sesión</a></div>
</div>

<logic:present name="pideEmpleado"> 
   <iframe src="pideEmpleado.do" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0"></iframe> 
</logic:present>
<logic:notPresent name="pideEmpleado"> 
<div class="content">
<iframe src="<%=sUriGo %>" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0"></iframe> 
</div>
<div>
</logic:notPresent>

  <form id="fPdf" name="fPdf" action="getPdf.do" method="POST" target="_blank">
     <input type="hidden" name="fileHtml" value="<%=sUriJsp %>"/>
     <!--  input type="submit" name="enviar" value="obtener pdf"/-->
  </form>

  <br/>
  <br/>
  <form id="f1" name="f1" action="m.menu.jsp" method="POST"><input type="submit" name="volver" value="volver"/></form>
</div>
<div class="footer"><a href="http://www.laaraucana.cl">La Araucana.cl</a></div>
</body>
</html>



