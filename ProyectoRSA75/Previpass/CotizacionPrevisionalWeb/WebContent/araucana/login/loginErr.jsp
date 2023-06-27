<%@include file="/html/comun/taglibs.jsp" %>
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
	<script type="text/javascript" src="<c:url value="/araucana/login/loginNew.js"/>" charset="iso-8859-1"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/web_publica.css"/>" />
</head>
<body onload="document.forms[0]['j_username'].focus()">
<script type="text/javascript">
	var bCancel = false;
</script>
<form method="post" action="j_security_check" onsubmit="return validaFormulario(this);">
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
    <td align="center" valign="middle" class="titulos_tabla">LOGIN</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center" valign="top" class="titulo_login">------------------------------------------------</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="49" align="center" valign="top"><table width="193" height="136" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td align="left" valign="top" background="<c:url value="/img/bg_login.jpg" />" scope="col"><table width="188" height="134" border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="30" colspan="4" align="center" class="titulo_login" scope="col">USUARIO REGISTRADO</td>
              </tr>
              <tr> 
    			<td>&nbsp;</td>
                <td width="35" align="left" class="btn_caja" scope="row">RUT</td>
                <td class="btn_caja">
                	<input name="j_username" type="text" class="texto" id="j_username" size="15" maxlength="10" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
                </td>
    			<td>&nbsp;</td>
              </tr>
              <tr> 
    			<td>&nbsp;</td>
                <td scope="row"><span class="btn_caja">Clave</span></td>
                <td align="left" class="btn_caja">
                	<input name="j_password" type="password" class="texto" id="j_password" size="15" maxlength="6" />
                </td>
    			<td>&nbsp;</td>
              </tr>
              <tr>
                <td scope="row" colspan="4" align="center"><span class="mensaje_login">RUT de usuario y clave no coinciden.</span></td>
              </tr>
			  <tr>
			    <td colspan="4">&nbsp;</td>
			  </tr>
              <tr>
                <td align="center" class="btn_caja" colspan="4">
                	<input name="button4" type="submit" class="boton_login" id="button4" value="Entrar"/>
                </td>
              </tr>
			  <tr>
			    <td colspan="4">&nbsp;</td>
			  </tr>
            </table></td>
        </tr>
      </table>
      <br />
    </td>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
<table width="768" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><div align="center">
      <script language="JavaScript" src="<c:url value="/js/piepagina.js"/>" type="text/javascript"> </script>
    </div></td>
  </tr>
</table>
</body>
</html>