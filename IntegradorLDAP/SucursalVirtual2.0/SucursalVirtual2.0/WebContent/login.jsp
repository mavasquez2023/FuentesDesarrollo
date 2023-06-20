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
<link href="css/new_estilos.css" rel="stylesheet" type="text/css" />
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
        <table border="0" cellspacing="0" cellpadding="0">
        <tr><td colspan="2" style="padding-bottom: 0px"><b style="font-size: 12px ">Solicitar Clave</b>:</td><td colspan="2" align="center" style="padding-bottom: 0px"><b style="font-size: 12px ">Recuperar Clave</b>:</td></tr>
        <tr><td style="padding-top: 0px"><a href="https://www.laaraucana.cl/clave-persona/" target="_top">Personas</a></td>
        	 <td style="padding-top: 0px"><a href="https://www.laaraucana.cl/clave-empresa/" target="_top">Empresas</a></td>
        	 <td colspan="2" align="center" style="padding-top: 0px"><a href="https://www.laaraucana.cl/recuperacion-clave/" target="_top">Recuperar</a></td>
        </tr>
        
        </table>
        
<div class="hr"></div>
<p style="font-family: Arial;color:#145D93;">
<font style="font-size: 18px">Información y consultas </font></br>
<font style="font-size: 10px">Desde teléfonos fijos y celulares</font></br>
<font style="font-size: 26px">600 4228 100</font></p>
</div>
    </div>
    <div class="grid_8 omega"><img src="images/foto_login.jpg" width="646" height="295" /></div>
</div>
  <div style="clear:left;">&nbsp;</div>
</div>
<jsp:include page="new_footer.jsp" />


<script>
	document.frmlogin.j_username.focus();
</script>

</body>
</html>
