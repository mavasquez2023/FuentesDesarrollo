
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">

<%
String msg=" ";
if("user".equals(request.getParameter("error")))
	msg="El Usuario y/o Contraseña no son válidos";
if("app".equals(request.getParameter("error")))
	msg="Usted no está autorizado para ingresar al Sistema";
if("sess".equals(request.getParameter("error")))
	msg="Su sesión ha expirado o usted no está Autentificado";	
%>

<title>Login a Intranet La Araucana</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="araucanaLogin.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>

<body>
<form name="login" method="post" action="j_security_check">
<table width="400" border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#000033">
  <tr>
    <td><table width="400" border="0" cellpadding="3" cellspacing="1">
        <tr>
          <td height="167" align="center" bgcolor="#000066"><img src="images/logo_entrada.gif" width="189" height="124">
              <table width="274" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td colspan="3"><img src="images/spacer.gif" width="8" height="4"></td>
                </tr>
                <tr>
                  <td bgcolor="#0099CC"><img src="images/spacer.gif" width="2" height="8"></td>
                  <td width="270" align="center" class="txtHomeVersion">Intranet
                    La Araucana<br> </td>
                  <td bgcolor="#0099CC"><img src="images/spacer.gif" width="2" height="8"></td>
                </tr>
                <tr>
                  <td colspan="3"><img src="images/spacer.gif" width="8" height="4"></td>
                </tr>
              </table>
          </td>
        </tr>
        <tr>
          <td align="center" bgcolor="#666666" class="txtHomeVersion3">Ingrese
            sus Datos</td>
        </tr>
        <tr>
          <td align="center" bgcolor="#CCCCCC"><table width="353" border="0" cellspacing="2" cellpadding="2">


            <tr>
              <td width="247" align="right"><span class="txtHomeVersion4">Usuario:</span>                <input name="j_username" type="text" id="j_username"></td>
              <td width="92" rowspan="2" align="center" valign="middle">
<input type=submit value="Ingresar">
              </td>
            </tr>
            <tr>
              <td align="right"><span class="txtHomeVersion4">Contrase&ntilde;a:</span>                <input name="j_password" type="password" id="j_password"></td>
              </tr>
          </table>
            </td>
        </tr>
        <tr>
          <td align="center" bgcolor="#FFFFFF" class="txtHomeVersion2"><font color='red'><%= msg %></font>&nbsp;</td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>

<p class="txtHomeVersion">&nbsp;</p>
</body>
</html>
