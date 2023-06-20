<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*, cl.araucana.sv.ServicesFilter"
%>
 

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

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
<%
		// Determines which services can be used by the user.
		ServicesFilter servicesFilter = new ServicesFilter(request);

		if (!servicesFilter.isOK()) {
			session.invalidate();

			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/notservice.jsp");

			dispatcher.forward(request, response);

			return;
		}
	%>
	<script language="JavaScript1.2">

		var localRedirector = "<%= localRedirector %>";
          
		function redirect(service) {
			url = localRedirector  + "/router.do?service=" + service;
			
			window.location = url;
		}
	</script>

<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
<link href="css/new_estilos.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.min.js"></script>		
<script type="text/javascript" src="js/jquery.fancybox-1.3.4.js"></script>
<link rel="stylesheet" type="text/css" href="css/jquery.fancybox-1.3.4.css" media="screen" />

</head>
<body class="corp">
<jsp:include page="../../new_header.jsp" />
<div id="segmentos" class="container_12"></div>
<div id="content" class="container_12">
    <h1>Servicios en Línea</h1>



    <div id="botones_superiores">
      <table width="100%" border="0">
        <tr>
          <td width="14%" align="left"><input name="aceptar2" type="button" class="botongris" onclick="location.href='changePassword.jsp';return false;" onmouseover="this.className='botongrisOver'" onmouseout="this.className='botongris'" value="Cambio de Clave" /></td>
          <td align="right">&nbsp;</td>
          <td width="12%" align="right"><input name="aceptar" type="button" class="botongris" onClick="location.href='logout.do'; return false;" onMouseOver="this.className='botongrisOver'" onMouseOut="this.className='botongris';" value="Cerrar Sesión"></td>
        </tr>
      </table>
    </div>



    


<% if (servicesFilter.isEnabled("AC")) { %>
<div class="grid_12">
<div class="grid_6 alpha borde6">
      <p class="titulo_caja">Certificados y Consultas</p>
      <div class="hr"></div>
      <div class="pad_caja">
        <img src="images/foto0.jpg" alt="Emitir Certificados" width="456" height="226" border="0" />
        <p style="height:85px">Aquí podrá emitir certificados y conocer el estado de las cargas familiares, de licencias médicas, cuenta de ahorro y más.
        <br/>
          <input name="aceptar"  type="button" class="botonsolicitar" onclick="javascript:redirect('AC');" onmouseover="this.className='botonsolicitarOver'" onmouseout="this.className='botonsolicitar'" value="Ingrese Aquí" src="images/aceptar.gif" />
      </p>
      </div>
    </div>
<div class="grid_6 borde6" style="margin-left: 12px">
      <p class="titulo_caja">Pago de cuotas de Crédito</p>
      <div class="hr"></div>
      <div class="pad_caja">
        <img src="images/foto0_1.jpg" alt="Emitir Certificados" width="456" height="226" border="0" />
        <p style="height:85px">Aquí podrá realizar el pago de sus cuotas vigentes o morosas, consultar su cartola y descargar el certificado de su crédito social.
        <br/>
          <input name="aceptar"  type="button" class="botonsolicitar" onclick="javascript:redirect('BP');" onmouseover="this.className='botonsolicitarOver'" onmouseout="this.className='botonsolicitar'" value="Ingrese Aquí" src="images/aceptar.gif" />
        </p>
      </div>
    </div>

</div>
   <% }   %>
   
  <% if (servicesFilter.isEnabled("EPC")) { %>


  <div class="grid_12">

    <div class="grid_4 alpha borde4">
      <p class="titulo_caja">Emisión de Planillas de Cotización</p>
      <div class="hr"></div>
      <div class="pad_caja">
        <img src="images/foto1.jpg" alt="Imagen Certificados" width="298" height="126" border="0" />
        <p style="height:85px">Mediante este servicio, usted podrá <strong>imprimir documentos y descargar archivos</strong>, sin tener que concurrir a las oficinas de La Araucana.</p>
          <input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro2" onclick="javascript:redirect('EPC');" type="button" value="Ingresar Aqu&iacute;" name="filtro2" />
      
      </div>
    </div>


   <% } %>

	<% if (servicesFilter.isEnabled("EPC")) { %>
    <div class="grid_4 borde4">
      <p class="titulo_caja">Subir Asignacion Familiar</p>
      <div class="hr"></div>
      <div class="pad_caja"> <img src="images/envio_asfam.jpg" width="298" height="127" alt="Subir Formularios Asfam" />
        <p style="height:85px">
        
        Mediante este servicio podrás subir el formulario y certificado de Asignaciòn Familiar, para su tramitaciòn ante la Caja	
	</p>
	<input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro2" onclick="javascript:redirect('ENVASFAM');" type="button" value="Ingresar Aqu&iacute;" name="filtro2" />

        </div>
    </div>

  <% } %>


<% if (servicesFilter.isEnabled("EPC")) { %>
    <div class="grid_4 omega borde4">
      <p class="titulo_caja">Subir Licencia Médica</p>
      <div class="hr"></div>
      <div class="pad_caja"> <img src="images/foto3.png" width="297" height="127" alt="Licencias Médicas" />
        <p style="height:85px"> 
          

         Mediante este servicio podrás  subir las imágenes de las Licencias Médicas de tus trabajadores, para su tramitación ante la Caja y Compin
         </p>
         <input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro2" onclick="javascript:redirect('LICEMP');" type="button" value="Ingresar Aqu&iacute;" name="filtro2" />
	
      </div>
    </div>


  <% } %>

<% if (servicesFilter.isEnabled("EPC")) { %>
    <div class="grid_4 alpha borde4">
      <p class="titulo_caja">Proceso de Cambio de Tramo</p>
      <div class="hr"></div>
      <div class="pad_caja"> <img src="images/foto2.jpg" width="298" height="127" alt="Imagen Certificados" />
        <p style="height:85px">
        
        Para mayor información sobre el proceso de actualización de tramos <a href="https://www.laaraucana.cl/empresas/servicios/asignacion-familiar/"  target="_blank">HAZ CLICK AQUÍ</a>
<br>
<br>
Para descargar o cargar propuesta de ingresos promedio <a onclick="javascript:redirect('RENTAS');" target="_blank">HAZ CLICK AQUÍ</a>

        </p>
        
        </div>
    </div>

  <% } %>
  

    <div style="clear:left;"></div>
  </div>
  
  <div style="clear:left;"></div>
</div>
<div class="container_12">
<div class="grid_12" id="footer">
  <div style="text-align:left;  float:right; padding-top:10px;">
    <img width="153" height="35" src="images/fono.gif">
  </div>
<div style="clear:left;"></div>
  <div style="text-align:right; float:left; padding-top:10px;">
    <img width="153" height="35" src="images/logito.gif" style="text-align:right;">
  </div>
</div>
</div>



</body>
</html>
