<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html; charset=ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>La Araucana C.C.A.F. - Servicio Nóminas y Anexos</title>

<link rel="stylesheet" type="text/css" href="/ea/edocs/css/edocs.css">

<script language="JavaScript1.2">
<!--
	
	function initForm() {
	}

    function closeForm() {
    	window.location =
    			  "http://"
    			+ "<%=request.getServerName()%>"
    			+ ":"
    			+ "<%=request.getServerPort()%>"
    			+ "/ea/logout.do";
    
        return false;
    }
    	
-->
</script>

</head>

<body topmargin="10" leftmargin="10" marginheight="10" marginwidth="10" onload="initForm();">

<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.Encargado" scope="session" >
</jsp:useBean>

<table border="0" style="width : 100%;" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td align="right">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td rowspan="2" valign="top">
							<html:img page="/img/EA.jpg"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>
	    
<form action="/ea/proxy.do" method="GET">

<table border="0" cellpadding="0" cellspacing="0" width="100%" height="411">
  
  <tr>
    <td width="25%" valign="middle" align="center" height="21">
		<p align="center"><font class="blackTextP6"><b>Servicio Nóminas y Anexos</b></font></p>
    </td>

    <td width="75%" colspan="4" valign="top" align="left" height="21">&nbsp;
      <table border="0" cellpadding="0" cellspacing="0" width="100%" height="21">
        <tr>
          <td width="5%" height="21" valign="top"></td>
          <td width="55%" height="21" align="left" valign="top"><font class="blackText">Encargado de Empresa:</font><font class="blueText"> <jsp:getProperty name="edocs_encargado" property="fullName" /></font></td>
          <td width="35%" height="21" align="right" valign="top"><font class="blackText">Rut Encargado:</font><font class="blueText"> <jsp:getProperty name="edocs_encargado" property="formattedRut" /></font></td>
          <td width="5%" height="21" valign="top"></td>
        </tr>
      </table>
    </td>
  </tr>
    
  <tr>
    <td width="25%" bgcolor="#FFFFCC" valign="top" align="left" height="270">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
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
        <br>
        <font class="redTextP4">
            Su requerimiento no pudo ser atendido.
            Por favor, reinténtelo más tarde.
        </font>
        <br>
        <br>
        <input type="submit" name="aceptar" class="button" value="Aceptar" onclick="closeForm();">
    </td>
  </tr>
</table>
</form>

</body>
</html>
