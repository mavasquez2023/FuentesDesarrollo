<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/html/comun/taglibs.jsp" %>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<title>Sistema de Cotizaci&oacute;n Previsional :: cp.cl</title>
	<script type="text/javascript" src="<c:url value="/araucana/login/loginNew.js"/>" charset="iso-8859-1"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value="/css/web_publica.css"/>" />

<script type="text/javascript">
	var http;
	if (window.XMLHttpRequest)
		http = new XMLHttpRequest();
	else if (window.ActiveXObject)
	{
		try 
		{
			http = new ActiveXObject("MSXML2.XMLHTTP");
		} catch (e) 
		{
			try 
			{
				http = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {}
		}
	}
	if (!http)
	{
		alert("No ha sido posible crear una instancia de XMLHttpRequest");
	}

	validaAdmin = function()
	{
		var url = "/AdminCotPrevWEB/j_security_check";

		var form = document.getElementById('logForm');
		var params = 'j_username='+form.j_username.value+'&j_password='+form.j_password.value;
		http.open("POST", url, true);

		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		http.setRequestHeader("Content-length", params.length);
		http.setRequestHeader("Connection", "close");
		http.onreadystatechange = function() {}
		http.send(params);

		return true;
	}

	function envio(form)
	{
		if(validaFormulario(form))
		{
			validaAdmin();
			return true;
		}
		return false;
	}
</script>
</head>
<body onload="document.forms[0]['j_username'].focus()">
<form action="/CotPrevIndependienteWeb/j_security_check" method="post" id="logForm" onsubmit="return envio(this);">

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
    <td align="center" valign="top" class="titulo_login">---------------------------------------------------------</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td height="49" align="center" valign="top">
    	<table width="193" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td align="left" valign="top" background="<c:url value="/img/bg_login.jpg" />" scope="col">
          <table width="188" border="0" align="left" cellpadding="0" cellspacing="0">
              <tr> 
                <td height="30" colspan="4" align="center" class="titulo_login" scope="col">USUARIO REGISTRADO</td>
              </tr>
              <tr> 
                <th width="14" height="27" scope="row">&nbsp;</th>
                <td width="35" align="left" class="btn_caja" scope="row">RUT</td>
                <td colspan="2" class="btn_caja">
                <input name="j_username" type="text" class="texto" id="j_username" size="15" maxlength="10" value="" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
              </tr>
              <tr> 
                <th height="23" scope="row">&nbsp;</th>
                <td scope="row"><span class="btn_caja">Clave</span></td>
                <td align="left" class="btn_caja">
                	<input name="j_password" type="password" class="texto" id="j_password" size="15" maxlength="6" value=""/>
                </td>
                <td align="right" class="btn_caja">&nbsp;</td>
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
