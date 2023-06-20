<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">



<head>
<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*, java.util.*, java.io.File, java.security.Principal"
%>

<jsp:include page="/WEB-INF/services/epc_announce_checker.jsp" />

<%
	/*
	 *  Determines local HTTP redirector.
	 */

	String localRedirector = application.getInitParameter("localRedirector");
			
	if (localRedirector == null || localRedirector.equals("")) {
		String URL = request.getRequestURL().toString();
		String URI = request.getRequestURI();
		int index = URL.indexOf(URI);

		if (index > 0) {
			localRedirector =
					URL.substring(0, index) + request.getContextPath();
		}
	}
%>

  <jsp:include page="/WEB-INF/meta_application.jsp" />
    <script language="JavaScript1.2" src="/sv/js/common.js" type="text/javascript">
	</script>
	
	<script language="JavaScript1.2">

		url = window.location + "";
		index = url.indexOf("/router.do");
		
		var publicDocURL = url.substring(0, index) + "/public";
		
		var localRedirector = "<%= localRedirector %>"

<%
	Principal principal = (Principal) session.getAttribute("userPrincipal");
	
	if (principal == null) {	// Unexpected case.
		return;
	}
	
	String userName = principal.getName();

	if (userName == null) {		// Unexpected case.
		return;
	}
	
	Map epcCPAnnouncements = (Map) application.getAttribute("epc.cp.announcements");
	
	String auxUserName = (String) epcCPAnnouncements.get(userName);
	
	if (auxUserName == null) {
	    epcCPAnnouncements.put(userName, userName);
%>	
        var CPAnnouncementEnabled = true;
<%
	} else {	
%>
		var CPAnnouncementEnabled = false;
<%
    }
%>
		function redirect(service) {
			url = localRedirector + "/router.do?service=" + service;
			window.location = url;
		}
	
		function followLink(name) {
			if (name == "PLANILLAS") {
				url = publicDocURL + "/manual_emision_planillas.pdf";
			} else if (name == "NOMINAS_ANEXOS") {
				url = publicDocURL + "/manual_entrega_archivos_nominas_anexos.pdf";
			}
			
			openFullNewWindow(url, name);
		}
		
		function showNotification() {
		    if (CPAnnouncementEnabled) {
				url = publicDocURL + "/NuevosControlesCP.pdf";
		
				openFullNewWindow(url, "Controles_CP");
			}
		}
		
		function showFechas() {
		  var x = document.getElementById("fechas");
		  if (x.style.display == "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
		}
	</script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/new_estilos.css" rel="stylesheet" type="text/css" />

</head>
<body class="corp">
<jsp:include page="../../new_header.jsp" />
<div id="segmentos" class="container_12"></div>
<div id="content" class="container_12">
    <h1>Emisión de Planillas de Cotizaciones</h1>
<div id="botones_superiores">
         <div id="botones_superiores">
      <table width="100%" border="0">
        <tr>
          <td width="14%" align="left"><input name="aceptar2" type="button" class="botongris" onclick="location.href='changePassword.jsp'; return false;" onmouseover="this.className='botongrisOver'" onmouseout="this.className='botongris'" value="Cambio de Clave" /></td>
          <td align="right">&nbsp;</td>
          <td width="12%" align="right"><input name="aceptar" type="button" class="botongris" onClick="location.href='logout.do'; return false;" onMouseOver="this.className='botongrisOver';" onMouseOut="this.className='botongris'" value="Cerrar Sesión"></td>
        </tr>
      </table>
    </div>
    </div>
<div class="grid_12" style="margin-top:10px; margin-bottom:10px">
  <p>Mediante este servicio, podrás <strong>Imprimir Documentos y Descargar Archivos de Datos</strong>, sin tener que concurrir a las oficinas de La Araucana C.C.A.F., optimizando así el tiempo laboral de tu empresa. 

Dichos documentos y archivos contienen la información necesaria para que puedas cumplir con la obligación de cotizar y pagar tus compromisos con La Araucana C.C.A.F. </p>
       
  </div>
  <div class="grid_12">

    <div class="grid_4 alpha borde4">
      <p class="titulo_caja">Impresión de Documentos</p>
      <div class="hr"></div>
      <div class="pad_caja">
       <img src="images/icono_imprimir.jpg" width="43" height="43" /><br />
 <p>Los documentos disponibles para 
        imprimir son:</p>
        <ul>
          <li> Nóminas de Crédito </li>
          <li> Nóminas de Ahorro</li>
          <li> Declaración y Pago de Cotizaciones</li>
          <li>Previsionales </li>
          <li>Anexos de Trabajadores</li>
          <li>Modificaciones de Asignación Familiar</li>
          <li>Autorizaciones de Asignación Familiar</li>
        </ul>

    <form method="get" action="javascript:redirect('PUBNOM')">
		        <input type="submit" name="action" onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class=boton
		               value='Imprimir Documentos'>
		    </form>
        <strong>
Nota:</strong> Los documentos a imprimir están 
        en <strong>formato PDF</strong>.</div>
    </div>
    <div class="grid_4 borde4">
      <p class="titulo_caja">Descarga de Archivos</p>
      <div class="hr"></div>
      <div class="pad_caja">
        <img src="images/icono_descarga.jpg" width="43" height="43" /><br />
        <p>Los archivos disponibles para 
        descargar son:</p>
        <ul>
          <li>Nóminas de Crédito</li>
          <li>Nóminas de Ahorro </li>
          <li>Anexos de Trabajadores 
            
            y Cargas Familiares </li>
        </ul>
        
         <form method="get" action="javascript:redirect('EDOCS')">
		        <input type="submit" name="action" onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class=boton
		               value='Descargar Archivos de Datos'>
		    </form>
        <strong> <strong><br />
        </strong> 
        Nota: </strong>Los archivos a descargar están 
        en <strong>formato TXT, CSV y XLS</strong>. <br />
Para aprender cómo utilizar este 
      servicio, consulte su <a href="javascript:followLink('NOMINAS_ANEXOS')">manual de operaci&oacute;n</a>.<br />
      <br />
      <br />
      </div>
    </div>
    <div class="grid_4 omega borde4">
      <p class="titulo_caja">Resultado Asignación Familiar</p>
      <div class="hr"></div>
      <div class="pad_caja">
     <img src="images/procesos.png" width="297" height="127" alt="Resultado ASFAM y Cotizaciones" /> <br />
         <p> Mediante este servicio, podrás descargar el resultado de la Compensación de Asignación Familiar,
             generada mensualmente, sin la necesidad de concurrir a las oficinas de La Araucana.<br />
  	<form method="get" action="javascript:redirect('RESASFAM')">
		<input type="submit" name="action" onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class=boton
			value='Ingresa Aquí'>
	</form>
        </p>
        
        
      </div>
    </div>
	<div class="grid_4 omega borde4" style="margin-left: -1px;">
          <p class="titulo_caja">Fechas de Publicación</p>
          <div class="hr"></div>

         <img src="images/icono_calendario.jpg" style="margin-left: 10px;" width="43" height="43" onmouseover="showFechas();" onmouseout="showFechas();"/> <br />
         	<div id="fechas" style="display:none;margin-left: 10px;">
                <p> Las fechas de publicación en esta página Web son:<br />
      <strong>Al 5to día hábil de cada mes: </strong><br />
            </p>
            <ul>
              <li>Nóminas de Crédito </li>
              <li>Modificaciones de Asignación Familiar </li>
              <li>Autorizaciones de Asignación Familiar </li>
    		  <li>Declaración y Pago de Cotizaciones 
                Previsionales </li>
              <li>Anexos de Trabajadores </li>
            </ul>
            <strong>Al 6to día hábil de cada mes: </strong>
            <ul>
              <li>Nóminas de Ahorro</li>
            </ul>
            </div>
	</div>
    <div style="clear:left;"></div>
  </div>
  
  <div style="clear:left;"></div>
  <div class="grid_12" style="margin-top:10px; margin-bottom:10px">
      <p>
    <strong>  Aviso Importante</strong> <br/>
  Informamos, que en el afán de dar mayor fluidez al otorgamiento del beneficio del crédito social a nuestros trabajadores afiliados, tenemos el agrado de comunicar el nuevo procedimiento que rige desde el <strong>22 de Agosto de 2016.</strong>
  <br>Para los trabajadores interesados en solicitar un crédito en La Araucana, se elimina la firma del empleador en la <strong>solicitud de crédito.</strong>
   <strong>
   <br><br>
  <a href="doc/Comunicado_Firma_Solicitud_Credito.pdf" target="_blank">Más información haz click aquí</a>
  </strong>   
  </div>
</div>
<div class="container_12">
  <div class="grid_12" id="footer">
    <p><img src="images/logito.gif" width="153" height="35" /></p>
  </div>
</div>
</body>
</html>
