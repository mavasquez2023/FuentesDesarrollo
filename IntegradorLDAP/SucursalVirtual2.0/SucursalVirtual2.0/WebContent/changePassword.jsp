<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ page 
    language="java"
    contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"
    import="javax.servlet.*, javax.servlet.http.*, cl.araucana.sv.ServicesFilter"
%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
	String changeMode = (String) request.getAttribute("changeMode");
	boolean initialChange = changeMode != null && changeMode.equals("initial");
	String nextPage = (String) request.getAttribute("nextPage");
%>

<jsp:include page="/WEB-INF/meta_application.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>   <% if (initialChange) { %>
		Cambio de Clave Inicial
	<% } else { %>
		Cambio de Clave
	<% } %>
    </title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />

	<script language="JavaScript1.2">
		function checkForm() {
			currentPassword = document.frmlogin.currentPassword.value;
			confirmNewPassword = document.frmlogin.confirmNewPassword.value;
			newPassword = document.frmlogin.newPassword.value;
			
			if (currentPassword == "") {
				alert("Advertencia: Debe ingresar la clave actual!");
				
				document.frmlogin.currentPassword.focus();
				
				return false;
			}

			if (currentPassword == newPassword) {
				alert("Advertencia: La nueva clave debe ser diferente de la actual!");
				
				document.frmlogin.newPassword.value = "";
				document.frmlogin.confirmNewPassword.value = "";
				document.frmlogin.newPassword.focus();
				
				return false;
			}

			if (newPassword != confirmNewPassword) {
				alert("Advertencia: No coincide la confirmaci�n de la nueva clave!");
				
				document.frmlogin.confirmNewPassword.value = "";
				document.frmlogin.confirmNewPassword.focus();
				
				return false;
			}
			
			if (!isNumber(currentPassword) || currentPassword.length != 4) {
				alert("Advertencia: Clave actual inv�lida. Recuerde que debe ser num�rica!");
				
				document.frmlogin.currentPassword.value = "";
				document.frmlogin.currentPassword.focus();
				
				return false;
			}

			if (!isNumber(newPassword) || newPassword.length != 4) {
				alert("Advertencia: La nueva clave debe ser num�rica!");
				
				document.frmlogin.newPassword.value = "";
				document.frmlogin.confirmNewPassword.value = "";
								
				document.frmlogin.newPassword.focus();
				
				return false;
			}
			
			return true;
		}
		
		function isNumber(s) {
			nmatches = 0;
			
		    for (i = 0; i < s.length; i++) {   
		        ch = s.charAt(i);
		        
				if ('0' <= ch && ch <= '9') {
					nmatches++;
				} else {
					return false;
				}
		    }
		    
		    return nmatches > 0;
		}		
	</script>

</head>
<body class="corp sm"><div id="segmentos" class="container_12"></div>
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
    <h1>Servicios en L&iacute;nea</h1>
    <div class="mensaje bg_gris">   <% if (initialChange) { %>
		Estimado usuario, Usted ha ingresado al sistema por primera vez. Por razones de seguridad es obligatorio que usted cambie su clave actual por una nueva clave de 4 d&iacute;gitos.
	<% } else { %>
		Se&ntilde;or usuario: si desea cambiar su clave, proceda seg&uacute;n lo siguiente
	<% } %></div>
    <div class="grid_9 alpha borde9fondo">
    <div class="grid_5 alpha">
    <div class="pad_caja">
    <form action="changePassword.do"  name="frmlogin" method="post">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" style="border-right: solid 1px #015E8A">
          <tr>
            <th valign="top">Clave Actual</th>
            <td valign="top"><input type="password" name="currentPassword" /></td>
          </tr>
          <tr>
            <th valign="top">Nueva Clave</th>
            <td valign="top"><input  type="password" name="newPassword" /></td>
          </tr>
          <tr>
            <th valign="top">Confirmar Nueva Clave</th>
            <td valign="top"><input type="password" name="confirmNewPassword"  /></td>
          </tr>
          <tr>
            <td colspan="2" class="acenter">
<input onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="Aceptar" onclick="return checkForm();" type="submit" value="Aceptar" name="Aceptar" />&nbsp;

<input onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class="boton" id="cancelar" 
		 <% if (initialChange) { %>
		onclick="location.href='logout.do'; return false;"
		<% } else { %>
		onclick="window.history.back(); return false;"
		 <% } %>              
type="button" value="Cancelar" name="cancelar" /></td>
            </tr>
        </table>
        </form></div></div>
      <div class="grid_4 omega">
        <div class="pad_caja">Ingrese su clave actual y nueva clave de 4 digitos.</div>
      </div>
      <div style="clear:left;"></div>
    </div>

    <div class="grid_3 borde3 omega">
        <div class="pad_caja"><img src="images/sobre.gif" width="19" height="15" class="aleft" /><a href="http://www.laaraucana.cl/irj/go/km/docs/documents/corpsite/personas/inicio/LinksCabecera/Contactenos">Consultas<br />
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
