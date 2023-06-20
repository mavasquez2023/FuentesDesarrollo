<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
    <div class="mensaje bg_gris">Nuestros sistemas no pudieron validar sus credenciales.<br />
    Por favor, intente ingresar su <b>RUT</b> y <b>CONTRASE&Ntilde;A</b>
				   nuevamente.
		  <tr>
		    <td width="85%">
	<p>&nbsp;</p></div>
    <div class="grid_4 alpha borde4" style="height:293px;">
      <div class="pad_caja"><br />
      <form action="j_security_check" method="post" name="frmlogin">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th valign="top">Rut</th>
            <td valign="top"><input type="text" name="j_username" id="name" />
              <br />
            (incluir guión y dígito verificador)</td>
          </tr>
          <tr>
            <th valign="top">Clave</th>
            <td valign="top"><input type="password" name="j_password"  id="pswd">
</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" class=boton id=filtro  type="submit" value="Aceptar" name=filtro /></td>
          </tr>
        </table>
        </form>
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
	document.frmlogin.j_username.focus();
</script>

</body>
</html>
