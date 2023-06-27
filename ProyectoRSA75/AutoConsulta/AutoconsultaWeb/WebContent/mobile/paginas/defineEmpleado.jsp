<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
String estaOpcion="";
String sOpcionMnu="";
String sGoMenu = "consultas.do";

if ((String)request.getParameter("md2_opcion")!=null) {
  estaOpcion = (String)request.getParameter("md2_opcion");
  if (!estaOpcion.equals("cns")) {
     sGoMenu = "certificados.do";
  }
  
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

} else if (sOpcionMnu.equals("5")) {
  sTitulo = "Asignacion familiar";
  sUriGo = "getAsignacionFamiliar.do";
  sUriJsp = "mobile/certificados/asignacionFamiliar.jsp";
  sGoMenu = "certificados.do";

} else if (sOpcionMnu.equals("6")) {
	sTitulo = "Licencias medicas";
	sUriGo = "getCertificadoLicenciasMedicas.do";
	sUriJsp = "mobile/certificados/licenciasMedicas.jsp";
    sGoMenu = "certificados.do";
	  
} else if (sOpcionMnu.equals("7")) {
	  sTitulo = "Deuda vigente";
	  sUriGo = "getDeudaVigente.do";
	  sUriJsp = "mobile/certificados/deudaVigente.jsp";
    sGoMenu = "certificados.do";
	  
} else if (sOpcionMnu.equals("8")) {
	  sTitulo = "Afiliaci&oacute;n";
	  sUriGo = "getAfiliacion.do";
	  sUriJsp = "mobile/certificados/afiliacion.jsp";
    sGoMenu = "certificados.do";

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
<div class="content">

<h2><%= sTitulo%></h2>
<br/>
<logic:notEmpty name="validation.message"> 
<div style="border:1px solid red; ">
<bean:message key='<%=(String)session.getAttribute("validation.message") %>'/>
<logic:notEmpty name="info"> <bean:message key='<%=(String)session.getAttribute("validation.info") %>'/></logic:notEmpty>
</div>
<br/>
</logic:notEmpty>

<form name="fEncargado" action="<%=sGoMenu %>" method="post" target="_top">
<input type="hidden" name="md2_opcion"  value='<bean:write name="md2_opcion"/>'/>
<input type="hidden" name="md2_opcionMnu" value='<bean:write name="md2_opcionMnu"/>'/>

Ingrese rut del Afiliado a consultar:<br/><br/>
Rut Afiliado <input name="rutAfiliado" type="text" class="caja" id="rutAfiliado" value='<bean:write name="afiliado.fullRut"/>' maxlength="12" />
<input type="submit" name="enviar" id="enviar" value="consultar"/>
</form>
</div>
<div>
  <br/>
  <br/>
  <form id="f1" name="f1" action="m.menu.jsp" method="POST" target="_top"><input type="submit" name="volver" value="volver"/></form>
</div>
<div class="footer"><a href="http://www.laaraucana.cl">La Araucana.cl</a></div>
</body>
</html>
</logic:notPresent>


