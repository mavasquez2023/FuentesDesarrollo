<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body class="corp">


<div id="content" class="container_12">
  <div class="grid_12">
  <img src="img/logo.png" /> <br/><br/>
   <!--  <div class="mensaje bg_gris">Por favor ingrese su <strong>Rut</strong> y <strong>Clave</strong> de acceso</div>-->
    <div> 
      Nuestros sistemas no pudieron validar sus credenciales.<br />
      Por favor, intente ingresar su <b>RUT</b> y <b>CONTRASE&Ntilde;A</b> nuevamente.
    </div><br/>
    <div class="grid_4 alpha borde4" style="height:293px;">
      <div class="pad_caja"><br />
      <form action="j_security_check" method="post" name="frmlogin">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <th valign="top">Rut</th>
            <td valign="top"><input type="text" name="j_username" />
              <br />
            (incluir gui&oacute;n y d&iacute;gito verificador)</td>
          </tr>
          <tr>
            <th valign="top">Clave</th>
            <td valign="top"><input type="password" name="j_password"  id="pswd"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td><input  onmouseover="this.className='botonOver'" onmouseout="this.className='boton'" 
                        class="boton_intranet" id="filtro"  
                        type="submit" value="Ingresar" name="filtro" onclick="this.form.submit()"/></td>
          </tr>
        </table>
        </form>
<div class="hr"></div>
    </div></div>
    <div class="grid_8 omega"> <img src="img/imagen_login2.jpeg" width="650" height="295"   /></a></div>
</div>
  <div style="clear:left;"></div>
</div>


</div>

<script>
	document.frmlogin.j_username.focus();
</script>

</body>
</html>
