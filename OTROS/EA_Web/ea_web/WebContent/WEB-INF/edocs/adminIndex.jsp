<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page contentType="text/html; charset=ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>La Araucana C.C.A.F. - Administración del Servicio Nóminas y Anexos</title>

<link rel="stylesheet" type="text/css" href="css/edocs.css">

<script language="JavaScript1.2">
<!--
	
	function initForm() {
       document.forms[0].index.disabled = true;
	}

    function goProxy() {
        if (window.history.length > 0) {
            window.history.back();
            window.history.back();
        }
        
        return false;
    }	

-->
</script>

</head>

<body topmargin="10" leftmargin="10" marginheight="10" marginwidth="10" onload="initForm();">

<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.Encargado" scope="session" >
</jsp:useBean>

<jsp:useBean id="edocs_admin" class="cl.araucana.ea.edocs.AdminBean" scope="application">
</jsp:useBean>
	    
<form action="admin.do" method="POST">

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
    <td width="25%" bgcolor="#FFFFCC" valign="top" align="left" height="270">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
              <br>
              <p align="center"><font class="optionBlueText">Opciones de Administración</font></p>
              
              <p align="center">
                  <input type="submit" class="button" value="Indexar Documentos" name="index">
              </p>

              <p align="center">
                  <input type="submit" class="button" value="Cancelar Indexación" name="cancelIndex">
              </p>
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
    <td width="75%" valign="top" align="left" height="270">
    <table border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td width="100%" colspan="5">
            <br>
                <font class="blueText">&nbsp;&nbsp;&nbsp;&nbsp;Indexando Documentos ...</font>
            <br>
            <br>
                <font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;Fecha/Hora Inicio:</font>
                <font class="blueText"><jsp:getProperty name="edocs_admin" property="beginTime" /></font>
            <hr width="98%" align="center">
        </td>
      </tr>

      <tr>
        <td width="2%">&nbsp;</td>
        <td width="20%"><font class="blackText">Tipo de Documento</font></td>
        <td width="53%"><font class="blackText">Porcentaje</font></td>
        
        <td width="14%"><font class="blackText">&nbsp;&nbsp;Avance</font></td>
   
        <td width="11%"><font class="blackText">Tiempo</font></td>
      </tr>

      <tr>
	    <jsp:useBean id="edocs_ncIndexer" class="cl.araucana.ea.edocs.IndexBean" scope="application">
	    </jsp:useBean>        
	    
        <td width="2%">&nbsp;</td>
        <td width="20%"><font class="blueText">Nóminas de Crédito</font></td>
        <td width="53%">
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td width='<jsp:getProperty name="edocs_ncIndexer" property="workPercentDone" />%' bgcolor="#FFCC99">
                  <logic:greaterEqual name="edocs_ncIndexer" property="workPercentDone" value="5">
                       <p><font class="blackText"><jsp:getProperty name="edocs_ncIndexer" property="workPercentDone" />%</font></p>
                  </logic:greaterEqual>
              </td>
              <td width='<jsp:getProperty name="edocs_ncIndexer" property="workPercentPending" />%'>&nbsp;</td>
            </tr>
          </table>
        </td>
        <td width="14%">
            <font class="blueText">
            &nbsp;
            <jsp:getProperty name="edocs_ncIndexer" property="workUnitsDone" />
            /
            <jsp:getProperty name="edocs_ncIndexer" property="workUnits" />
            </font>
        </td>
   
        <td width="11%">
            <font class="blueText"><jsp:getProperty name="edocs_ncIndexer" property="elapsedTime" /></font>
        </td>
      </tr>

      <tr>
	    <jsp:useBean id="edocs_nlIndexer" class="cl.araucana.ea.edocs.IndexBean" scope="application">
	    </jsp:useBean>        

        <td width="2%">&nbsp;</td>
        <td width="20%"><font class="blueText">Nóminas de Ahorro</font></td>
        <td width="53%">
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td width='<jsp:getProperty name="edocs_nlIndexer" property="workPercentDone" />%' bgcolor="#99CC00">
                  <logic:greaterEqual name="edocs_nlIndexer" property="workPercentDone" value="5">
                       <p><font class="blackText"><jsp:getProperty name="edocs_nlIndexer" property="workPercentDone" />%</font></p>
                  </logic:greaterEqual>
              </td>
              <td width='<jsp:getProperty name="edocs_nlIndexer" property="workPercentPending" />%'>&nbsp;</td>
            </tr>
          </table>
        </td>
        <td width="14%">
            <font class="blueText">
            &nbsp;
            <jsp:getProperty name="edocs_nlIndexer" property="workUnitsDone" />
            /
            <jsp:getProperty name="edocs_nlIndexer" property="workUnits" />
            </font>
        </td>
   
        <td width="11%">
            <font class="blueText"><jsp:getProperty name="edocs_nlIndexer" property="elapsedTime" /></font>
        </td>
      </tr>

      <tr>
	    <jsp:useBean id="edocs_atIndexer" class="cl.araucana.ea.edocs.IndexBean" scope="application">
	    </jsp:useBean>        

        <td width="2%">&nbsp;</td>
        <td width="20%"><font class="blueText">Anexo de Trabajadores</font></td>
        <td width="53%">
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td width='<jsp:getProperty name="edocs_atIndexer" property="workPercentDone" />%' bgcolor="#99CCFF">
                  <logic:greaterEqual name="edocs_atIndexer" property="workPercentDone" value="5">
                       <p><font class="blackText"><jsp:getProperty name="edocs_atIndexer" property="workPercentDone" />%</font></p>
                  </logic:greaterEqual>
              </td>
              <td width='<jsp:getProperty name="edocs_atIndexer" property="workPercentPending" />%'>&nbsp;</td>
            </tr>
          </table>
        </td>
        <td width="14%">
            <font class="blueText">
            &nbsp;
            <jsp:getProperty name="edocs_atIndexer" property="workUnitsDone" />
            /
            <jsp:getProperty name="edocs_atIndexer" property="workUnits" />
            </font>
        </td>
   
        <td width="11%">
            <font class="blueText"><jsp:getProperty name="edocs_atIndexer" property="elapsedTime" /></font>
        </td>
      </tr>
      
      <tr>
	    <jsp:useBean id="edocs_cfIndexer" class="cl.araucana.ea.edocs.IndexBean" scope="application">
	    </jsp:useBean>        
	    
        <td width="2%">&nbsp;</td>
        <td width="20%"><font class="blueText">Cargas Familiares</font></td>
        <td width="53%">
          <table border="0" cellpadding="0" cellspacing="0" width="100%">
            <tr>
              <td width='<jsp:getProperty name="edocs_cfIndexer" property="workPercentDone" />%' bgcolor="#00CC99">
                  <logic:greaterEqual name="edocs_cfIndexer" property="workPercentDone" value="5">
                       <p><font class="blackText"><jsp:getProperty name="edocs_cfIndexer" property="workPercentDone" />%</font></p>
                  </logic:greaterEqual>
              </td>
              <td width='<jsp:getProperty name="edocs_cfIndexer" property="workPercentPending" />%'>&nbsp;</td>
            </tr>
          </table>
        </td>
        <td width="14%">
            <font class="blueText">
            &nbsp;
            <jsp:getProperty name="edocs_cfIndexer" property="workUnitsDone" />
            /
            <jsp:getProperty name="edocs_cfIndexer" property="workUnits" />
            </font>
        </td>
   
        <td width="11%">
            <font class="blueText"><jsp:getProperty name="edocs_cfIndexer" property="elapsedTime" /></font>
        </td>
      </tr>
      
      <tr>
          <td width="100%" colspan="5" valign="middle" align="center" height="140">
              <form>
                  <input type="button" name="retornar" class="button" value="Retornar" onclick="goProxy();">
              </form>
          </td>
      </tr>
    </table>
    </td>
  </tr>
</table>
</form>

</body>
</html>
