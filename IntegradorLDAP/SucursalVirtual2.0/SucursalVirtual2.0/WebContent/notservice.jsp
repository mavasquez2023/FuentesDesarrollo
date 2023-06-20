<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*"
%>

<%
	String homePage = application.getInitParameter("homePage");
	String loginURL = homePage;
	
	if (homePage == null) {	
		String URL = request.getRequestURL().toString();
		String URI = request.getRequestURI();
		int index = URL.indexOf(URI);
				
		if (index > 0) {
			loginURL =
				  URL.substring(0, index)
				+ request.getContextPath() + "/login.jsp";
		}
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body class="corp">
<jsp:include page="new_header.jsp" />
<div id="content" class="container_12">
  <div class="grid_12">
    <h1>Servicios en Línea</h1>
    <div class="mensaje bg_gris"> Nuestros sistemas no pueden atenderlo en este momento.</div>
    <div class="grid_4 alpha borde4" style="height:293px;">
      <div class="pad_caja"><br />
    <!--   <form action="j_security_check" method="post">-->
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
           
            <td valign="top"> <p>Por favor, intente ingresar su <b>RUT</b> y <b>CONTRASE&Ntilde;A</b>
				   nuevamente haciendo click <a href="<%= loginURL %>">aqu&iacute;</a>.</p>
              <p>Muchas gracias por su gentileza.</p></td>
          </tr>
        </table>
       <!-- </form>-->
        <p><strong>Solicitar Clave</strong>:<br />
        <a href="#">Personas</a>&nbsp;&nbsp;&nbsp;<a href="#">Empresas</a></p></div>
      <div class="hr"></div>
      <div class="pad_caja acenter"><img src="images/fono.gif" width="174" height="40" /></div>
    </div>
    <div class="grid_8 omega"><img src="images/foto_login_pyme.jpg" width="646" height="295" /></div>
</div>
  <div style="clear:left;"></div>
</div>
<div class="container_12">
  <div class="grid_12" id="footer">
    <p><img src="images/logito.gif" width="153" height="35" /></p>
  </div>
</div>

<script>
	document.forms[0].j_username.focus();
</script>

</body>
</html>
