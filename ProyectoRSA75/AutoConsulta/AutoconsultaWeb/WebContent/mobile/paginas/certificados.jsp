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
    sOpcionMnu = "5";

} else if (estaOpcion.equals("")) {
     response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp?p=1");
}

sOpcionMnu = "" + (String)session.getAttribute("md2_opcionMnu");
if (sOpcionMnu==null || sOpcionMnu=="") {
  response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp?p=2");
}

String sTitulo = "";
String sUriGo = "";
String sUriJsp = "";
if (sOpcionMnu.equals("5") || sOpcionMnu.equals("0")) {
  sTitulo = "Asignacion familiar";
  sUriGo = "getAsignacionFamiliar.do";
  sUriJsp = "mobile/certificados/asignacionFamiliar.jsp";
} else if (sOpcionMnu.equals("6")) {
	  sTitulo = "Licencias medicas";
	  sUriGo = "getCertificadoLicenciasMedicas.do";
	  sUriJsp = "mobile/certificados/licenciasMedicas.jsp";
	  
} else if (sOpcionMnu.equals("7")) {
	  sTitulo = "Deuda vigente";
	  sUriGo = "getDeudaVigente.do";
	  sUriJsp = "mobile/certificados/deudaVigente.jsp";
	  
} else if (sOpcionMnu.equals("8")) {
	  sTitulo = "Afiliaci&oacute;n";
	  sUriGo = "getAfiliacion.do";
	  sUriJsp = "mobile/certificados/afiliacion.jsp";
} else {
  response.sendRedirect("/AutoconsultaWeb/mobile/m.menu.jsp?p=3");
}

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//ES" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es">
<head>
<title>laaraucana.cl</title> 
<meta content='width=device-width; initial-scale=1.0' name='viewport'>
<link href="css/estilo.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="http://www.laaraucana.cl/favicon.ico">
<script type='text/javascript'>
function setIframeHeight( iframeId ) 
{
 var ifDoc, ifRef = document.getElementById( iframeId );

 try {   
  ifDoc = ifRef.contentWindow.document.documentElement;  
 } catch( e ) { 
  try {
   ifDoc = ifRef.contentDocument.documentElement;  
  } catch(ee) {   
  }  
 } 
 if( ifDoc ) {
   ifRef.height = 1;  
   ifRef.height = ifDoc.scrollHeight;  
 }
}
</script>
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
<iframe src="<%=sUriGo %>" width="100%" height="480" frameborder="0" allowtransparency="true" scrolling="no" name="iData" id="iData" onload="this.contentWindow.document.documentElement.scrollTop=0; setIframeHeight( this.id );" ></iframe> 
</div>
<div>
</logic:notPresent>
  <form id="fPdf" name="fPdf" action="getPdf.do" method="POST" target="_blank">
     <input type="hidden" name="fileHtml" value="<%=sUriJsp %>"/>
     <input type="submit" name="enviar" value="obtener pdf"/>
  </form>
  <br/>

  <br/>
  <form id="f1" name="f1" action="m.menu.jsp" method="POST"><input type="submit" name="volver" value="volver"/></form>
</div>
<div class="footer"><a href="http://www.laaraucana.cl">La Araucana.cl</a></div>
</body>
</html>
