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
	String paramChangeOK = (String) request.getAttribute("changeOK");
	boolean changeOK = paramChangeOK != null && paramChangeOK.equals("true");
	String initialChange = (String) request.getAttribute("initialChange");
	String nextPage = (String) request.getAttribute("nextPage");	
	
	boolean initialPasswordChange =
			initialChange != null && initialChange.equals("true");
%>

<jsp:include page="/WEB-INF/meta_application.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>   <% if (initialChange == null) { %>
		Cambio de Clave Inicial
	<% } else { %>
		Cambio de Clave
	<% } %>
    </title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body class="corp sm">
<div id="segmentos" class="container_12"></div>
<div id="header" class="container_12">
  <div class="grid_6">
    <div id="Logo"><a href="Pages/Personas/index.html"><span>La Araucana</span></a></div>
  </div>
  <div id="" class="grid_6 "></div>
</div>
<div id="Breadcrumb" class="container_12">
  <div class="grid_10"></div><div class="grid_2" id="iconos"></div>
</div>
<div id="content" class="container_12">
  <div class="grid_12">
    <h1>Servicios en Línea</h1>
    <div class="mensaje bg_gris">  Estimado usuario:</div>
    <div class="grid_9 alpha borde9fondo">
    <div class="grid_5 alpha">
    <div class="pad_caja"> <% if (changeOK) { %>
			    Su clave ha sido cambiada exitosamente.
			    <br>
			    <br>
			    Puede seguir operando con nuestros Servicios en L&iacute;nea. Muchas gracias.
			 <% } else { %>
			    <b>Su clave no pudo ser cambiada. Aseg&uacute;rese de ingresar correctamente
			    su clave actual.</b>
			    <br>
			    <br>
			    Por favor reint&eacute;ntelo nuevamente. Muchas gracias.
			 <% } %>
             <br /><% if (changeOK) {
			if (initialPasswordChange) { %>
            <input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro" onclick="window.location='<%= nextPage %>'; return false;" type=button value="Continuar" name=filtro />
            
		
		 <%     } else { %>
                  <input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro" onclick="window.history.back(); window.history.back();" type=button value="Continuar" name=filtro />
         
					
		 <%     } 
		    } else { %>
            
                         <input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="filtro" onclick="window.history.back(); window.history.back();" type=button value="Reintentar" name=filtro />
                         
			   
		 <% } %>
        </div></div>
    <div style="clear:left;"></div>
    </div>
    <div class="grid_3 borde3 omega" >
      <div class="pad_caja"> <img src="images/sobre.gif" width="19" height="15" class="aleft" /><a href="#">Consultas<br />
        sugerencias o reclamos</a><br /><br />
          <div class="hr"></div>
      <div><img src="images/fono.gif" width="174" height="40" /></div>
	  </div>	
    </div>
    <div style="clear:left;"></div>
  </div>
  <div style="clear:left;"></div>
</div>
<div class="container_12">
  <div class="grid_12" id="footer">
    <p><img src="images/logito.gif" width="153" height="35" /></p>
  </div>
</div>
</body>
</html>
