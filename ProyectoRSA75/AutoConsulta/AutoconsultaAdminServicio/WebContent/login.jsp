<%@ include file = "/WEB-INF/includes/headerEnv.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML>
<HEAD>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>Login La Araucana administración</TITLE>
<link href="css/lar_services.css" rel="stylesheet" type="text/css" />
<link href="css/grid.css" rel="stylesheet" type="text/css" />
</HEAD>

<body>

<div id="container" class="container_12">
  <div id="header" class="grid_12">
    <div class="grid_2 alpha borde_sep">&nbsp;</div>
    <div class="grid_7">
      <h1>Administración acceso servicios</h1>
    </div>
    <div class="grid_3 omega"><img src="images/logo_araucana.jpg" width="216" height="74" alt="UGM" /></div>
  </div>
  <div class="clear"></div>
  <div class="suffix_3 prefix_3">
    <h2>Datos de Acceso</h2>
    <%@ include file = "/WEB-INF/includes/mensaje.jsp"%>
<logic:present name="application.message">
<bean:write name="application.message" />
</logic:present>
<logic:present name="message">
<bean:write name="message" />
</logic:present>

    <form method="post" action="j_security_check">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="login">
      <tr>
        <td><strong>Usuario</strong></td>
        <td><input type="text" name="j_username" id="j_username" /></td>
      </tr>
      <tr>
        <td><strong>Clave</strong></td>
        <td><input type="password" name="j_password" id="j_password" /></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input type="submit" name="Ingresar" id="Ingresar" value="Ingresar" class="btn"/></td>
      </tr>

    </table>
    </form>
    <br />
  </div>
  <div class="clear"></div>
</div>
<p class="txtHomeVersion">&nbsp;</p>
</body>
</html>
