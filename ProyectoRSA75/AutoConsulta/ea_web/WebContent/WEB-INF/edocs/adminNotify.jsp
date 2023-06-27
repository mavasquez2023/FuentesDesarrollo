<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html; charset=ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>La Araucana C.C.A.F. - Administración del Servicio Nóminas y Anexos</title>

<link rel="stylesheet" type="text/css" href="/ea/edocs/css/edocs.css">

<script language="JavaScript1.2">
<!--
	
	function initForm() {
       document.forms[0].cancelIndex.disabled = true;
	}

-->
</script>

</head>

<body topmargin="10" leftmargin="10" marginheight="10" marginwidth="10" onLoad="initForm();">
<img src='/ea/edocs/images/EPC.jpg'>
<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.Encargado" scope="session" >
</jsp:useBean>

<jsp:useBean id="edocs_admin" class="cl.araucana.ea.edocs.AdminBean" scope="application">
</jsp:useBean>

<table border="0" cellpadding="0" cellspacing="0" width="100%" height="411">
  
  <tr>
    <td width="25%" valign="middle" align="center" height="141">
    	 <br>
    	 <img border="0" src="images/logo_araucana-8%25.JPG" width="125" height="82">
    	 <br>
    	 <br>
    </td>

    <td width="75%" colspan="6" valign="top" align="left" height="141">&nbsp;
      <table border="0" cellpadding="0" cellspacing="0" width="100%" height="97">
        <tr>
          <td width="100%" colspan="3" height="21">
            <p align="center">&nbsp;<font class="blackTextP6">Empresas Adherentes</font></p>
          </td>
        </tr>
        
        <tr>
          <td width="100%" colspan="3" height="18">
              <p align="center"><font class="blackTextP2">Administración del Servicio Nóminas y Anexos</font></p>
          </td>
        </tr>
        
        <tr>
          <td width="100%" colspan="3" height="37">
              <p align="center"></p>
          </td>
        </tr>

        <tr>
          <td width="40%" height="21"><font class="blackText">Administrador:</font><font class="blueText"> <jsp:getProperty name="edocs_encargado" property="fullName" /></font></td>
          <td width="20%" height="21">&nbsp;<font class="blueText">&nbsp;</font></td>
          <td width="40%" height="21"></td>
        </tr>
      </table>
    </td>
  </tr>
    
  <tr>
    <td width="25%"  valign="top" align="left" height="270">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
              <br>
              <p align="center"><font class="optionBlueText">Opciones de Administración</font></p>

			  <form action="admin.do" method="POST">              
	              &nbsp;&nbsp;<input type="submit" class="button" value="Indexar Documentos" name="index">
	              <br>
	              <br>
	              &nbsp;&nbsp;<input type="submit" class="button" value="Cancelar Indexación" name="cancelIndex">
	              <br>&nbsp;
	              <br>
			  </form>
          </td>
        </tr>
        
        <tr>
          <td width="100%">
          </td>
        </tr>

        <tr>
          <td width="100%">
          </td>
        </tr>        
      </table>
    </td>
    
    <td width="75%" valign="top" align="center" height="270">
        <br>
        <br>

        <font class="blueTextP4"><jsp:getProperty name="edocs_admin" property="message" /></font>

        <br>
        <br>

	    <form action="/ea/proxy.do" method="GET">
	       <input type="submit" name="aceptar" class="button" value="Aceptar">
	    </form>
    </td>
  </tr>
</table>

</body>
</html>
