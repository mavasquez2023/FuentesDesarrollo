<%@include file="/html/comun/taglibs.jsp" %>
<%@page import="java.util.Properties"; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Sistema de Cotizaci&oacute;n Previsional :: cp.cl</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/web_publica.css"/>" />
</head>
<body>
<form method="post" action="ibm_security_logout">
<html:hidden property="logoutExitPage" value="/Tiles.do"/>
<table width="768" border="0" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" scope="col"><img src="<c:url value="/img/logo.jpg"/>" width="770" height="95" /></td>
  </tr>
  <tr> 
    <td colspan="3" align="center" valign="middle" scope="row"><img src="<c:url value="/img/sombra.jpg"/>" width="739" height="7" /></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center" valign="middle" class="titulos_tabla">ERROR</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center" valign="top" class="titulo_login">---------------------------------------------------------</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="49" align="center" valign="top">
    <table width="193" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr > 
          <td align="left" valign="top" background="<c:url value="/img/bg_login.jpg" />" scope="col">
          <table width="188" height="134" border="0" align="left" cellpadding="0" cellspacing="0">
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="center" class="titulo_login" scope="col">
                <% 	
					if(request.getParameter("error") != null)
					{						
						Properties prop = new Properties();
						prop.load(getClass().getResourceAsStream("/files/mensajesLogin.properties"));
                %>
                <%= ((String)prop.get(request.getParameter("error"))).toUpperCase() %>
                <% } else
                { %>
                	USTED NO SE ENCUENTRA REGISTRADO EN EL SISTEMA, POR FAVOR CONTACTESE CON UN ADMINISTRADOR DE LA ARAUCANA
                <%} %>
                </td>
              </tr>
              <tr>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <td align="center" class="btn_caja">
                	<html:submit property="cancela" styleClass="boton_login" value="Cancelar"/>
                </td>
              </tr>
              <tr>
                <td >&nbsp;</td>
              </tr>
            </table></td>
        </tr>
      </table>
      <br /></td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
<table width="768" border="0" cellspacing="0" cellpadding="0">
	<tr>
    	<td>
    		<div align="center">
      			<script language="JavaScript" src="<c:url value="/js/piepagina.js"/>" type="text/javascript"></script>
			</div>
		</td>
	</tr>
</table>
</body>
</html>