<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	Cookie[] cookies = request.getCookies();
	if (cookies == null)
		cookies = new Cookie[0];
	for (int i = 0; i < cookies.length; i++) {
		cookies[i].setMaxAge(0);
		cookies[i].setValue(null);
		response.addCookie(cookies[i]);
		//out.println(cookies[i].getName() + ":\t" + cookies[i].getValue() + "<BR>");
	}
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/WEB-INF/meta_application.jsp" />
<title>La Araucana</title>
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/estilos.css" rel="stylesheet" type="text/css" />
</head>
<body class="corp">

<jsp:include page="new_header.jsp" />
<div id="content" class="container_12">
  <div class="grid_12">
    <h1>Servicios en L&iacute;nea</h1>
    <div class="mensaje bg_gris">Por favor ingrese su <strong>Rut</strong> y <strong>Clave</strong> de acceso</div>
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
                        class="boton" id="filtro"  
                        type="submit" value="Ingresar" name="filtro" onclick="this.form.submit()"/></td>
          </tr>
        </table>
        </form>
        <p><strong>Solicitar Clave</strong>:<br />
        <a href="http://www.laaraucana.cl/irj/portal/anonymous/solicitudclave.personas" target="_top">Personas</a>         &nbsp;&nbsp;&nbsp;
        <a href="http://www.laaraucana.cl/irj/portal/anonymous/solicitudclave.empresas" target="_top">Empresas</a>
         </p>
<div class="hr"></div>
<img src="images/fono.gif" width="172" height="78" style="padding-top:10px;" /></div>
    </div>
    <div class="grid_8 omega"><img src="images/foto_login_pyme.jpg" width="646" height="295" /></div>
</div>
  <div style="clear:left;"></div>
</div>

<div class="container_12">
	<div style="background-image:url(images/bg_lineas_footer.gif); background-repeat:no-repeat; background-position:0px 10px;" id="footer" class="grid_12 bg_gris">
  <div class="grid_3 alpha acenter">
    <p><img width="153" height="35" alt="" src="images/logo_footer.gif"></p>
  </div>
  <div class="grid_5"> Casa Matriz: Santa Luc&iacute;a Nro 302, Santiago de Chile<a href="#"></a><br>
    Informaciones y Consultas: 600 4228 100
    <ul>
      <li><a href="http://www.laaraucana.cl/irj/portal/anonymous/contactenos">Cont&aacute;ctenos</a></li>
      <li><a href="http://www.laaraucana.cl/irj/portal/anonymous/sucursales">Red de Sucursales</a></li>
      <li><a href="http://www.laaraucana.cl/irj/portal/anonymous/MapaSitio">Mapa del Sitio</a></li>
    </ul>
    Sitio desarrollado para resoluci&oacute;n  de 1024 x 768 ples.<br>
    &copy; La Araucana. Todos los Derechos Reservados. </div>
  <div class="grid_2">
    <ul>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><strong>Personas</strong></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://0b656e3a7bda02e939db72b63045268c&amp;InitialNodeFirstLevel=true">Empresas</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://b010a0a45bda8e59748f93fcc323a3ec&amp;InitialNodeFirstLevel=true">Club Tiempo Pleno</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://149e93b6654bbd8413b3a096842a7e1e&amp;InitialNodeFirstLevel=true">Informaci&oacute;on Corporativa</a></li>
    </ul>
  </div>
  <div class="grid_2 omega">
    <ul>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://aea3d27f72a727ceb6190650da0b6ca5&amp;InitialNodeFirstLevel=true">Inicio</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://4ceb1c2767db51b888c058d241cd327c&amp;NavPathUpdate=false">Cr&eacute;ditos</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://3400a89cd8acadaab9122ee181d65787&amp;NavPathUpdate=false">Beneficios</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://e9d8223ad8782de4decffb2a33013685&amp;NavPathUpdate=false">Salud</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://fdc5f85ce54dadf73b8af7fb71803f77&amp;NavPathUpdate=false">Educaci&oacute;n</li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://20470945293d4754f19c30abe4d297db&amp;NavPathUpdate=false">Vivienda</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://52499b5bfe976585b0143e5ccdc52a94&amp;NavPathUpdate=false">Recreaci&oacute;n Turismo</a></li>
      <li style="background-image:url(../images/flechita_corp.gif);background-repeat: no-repeat;background-position: 0px 3px;padding-left: 10px;"><a href="http://www.laaraucana.cl/irj/portal/anonymous?NavigationTarget=navurl://4e1a41b9ce210ca3f38a9cf57fd234a6&amp;NavPathUpdate=false">Culturas</a></li>
    </ul>
  </div>
  <div class="bg_gris" style="clear:left;"></div>
</div>

</div>

<script>
	document.frmlogin.j_username.focus();
</script>

</body>
</html>
