<%@ page contentType="text/html; charset=ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />

<meta name="APPLICATION_ORGANIZATION_NAME" content="<%= software.getOrganizationName() %>" />
<meta name="APPLICATION_NAME" content="<%= software.getName() %>" />
<meta name="APPLICATION_TITLE" content="<%= software.getTitle() %>" />
<meta name="APPLICATION_VERSION" content="<%= software.getVersion() %>" />
<meta name="APPLICATION_RELEASE_DATE" content="<%= software.getReleaseDate() %>" />
<meta name="APPLICATION_COPYRIGHT" content="<%= software.getCopyright() %>" />
<title>La Araucana C.C.A.F. - Nóminas y Anexos</title>

<link rel="stylesheet" type="text/css" href="/ea/edocs/css/ar_edocs.css">

<script language="JavaScript1.2">
<!--
	
	function initForm() {
        document.forms[0].SEPARADA_POR_NC.disabled = true;
        document.forms[0].SEPARADA_POR_NL.disabled = true;
        document.forms[0].SEPARADA_POR_AT.disabled = true;	
	}
	
    function marcarTodos(value) {
        if (document.forms[0].TODAS_NC.checked) {
            marcarTodasNC(value);
        }

        if (document.forms[0].TODAS_NL.checked) {
            marcarTodasNL(value);
        }

        if (document.forms[0].TODOS_AT.checked) {
            marcarTodosAT(value);
        }
       
        document.forms[0].TODAS_NC.checked = false;
        document.forms[0].TODAS_NL.checked = false;
        document.forms[0].TODOS_AT.checked = false;
    }

	function marcarTodasNC(value) {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('NC') == 0) {
			     marcarItem(document.forms[0].elements[j], value);
          }
	   }	
	}

	function marcarTodasNL(value) {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('NL') == 0) {
			     marcarItem(document.forms[0].elements[j], value);
          }
	   }	
	}

	function marcarTodosAT(value) {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('AT') == 0) {
			     marcarItem(document.forms[0].elements[j], value);
          }
	   }	
	}
	
	function marcarItem(item, value) {
       item.checked = value;
	   item.disabled = false;			         
	   item.value = "ALL";
	}
	
	function checkSeparadaPorEmpresa() {
	    if (document.forms[0].SEPARADA_POR_EMPRESA.checked) {
	        document.forms[0].SEPARADA_POR_NC.disabled = false;
	        document.forms[0].SEPARADA_POR_NL.disabled = false;
	        document.forms[0].SEPARADA_POR_AT.disabled = false;
	    } else {
	        document.forms[0].SEPARADA_POR_NC.checked = false;
	        document.forms[0].SEPARADA_POR_NL.checked = false;
	        document.forms[0].SEPARADA_POR_AT.checked = false;
	        
	        document.forms[0].SEPARADA_POR_NC.disabled = true;
	        document.forms[0].SEPARADA_POR_NL.disabled = true;
	        document.forms[0].SEPARADA_POR_AT.disabled = true;
	    }
	}

	function checkInput() {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('NC') == 0 && document.forms[0].elements[j].checked) {
			    return true;
			}

		    if (document.forms[0].elements[j].name.indexOf('NL') == 0 && document.forms[0].elements[j].checked) {
			    return true;
			}

		    if (document.forms[0].elements[j].name.indexOf('AT') == 0 && document.forms[0].elements[j].checked) {
			    return true;
			}
        }
	
		alert("Por favor, seleccione las nóminas y/o anexos requeridos por usted.");
	
		return false;
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

	function fPopupHelp(address, port) {
       window.open(
           "http://" + address + ":" + port + "/ea/edocs/instructivo.mht",
           "ayuda",
           "resizable=yes,scrollbars=yes,width=800,height=600,status=no");
	}
-->
</script>


<link href="/ea/edocs/css/ar_edocs.css" rel="stylesheet" type="text/css">
</head>

<body topmargin="10" leftmargin="10" marginheight="10" marginwidth="10" onLoad="initForm();">
<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.Encargado" scope="session" >
</jsp:useBean>

<jsp:useBean id="publishedPeriods" class="cl.araucana.ea.edocs.PublishedPeriodsBean" scope="request" >
</jsp:useBean>

<img src='/ea/edocs/images/EPC.jpg'>

<form action="/ea/edocs/zipper.do" method="POST">

<input type="hidden" name="serviceName" value="NC_NL_AT">
<input type="hidden" name="period" value='<jsp:getProperty name="publishedPeriods" property="selectedPeriod" />'>


<table border="0" cellpadding="0" cellspacing="0" width="974">
  
  <tr>
    	<td width="30%" height="80" valign="middle"><div class="menu">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%"><p align="center"><font class="optionBlueText">Selección de la Información</font></p>
              <input type="checkbox" name="TODAS_NC" value="ON"><font class="optionText">Todas las Nóminas de Crédito</font><br>
              <input type="checkbox" name="TODAS_NL" value="ON"><font class="optionText">Todas las Nóminas de Ahorro</font><br>
              <input type="checkbox" name="TODOS_AT" value="ON"><font class="optionText">Todos los Anexos de Trabajadores</font>
              <br>
              <p align="center">
                  <input type="button" class="button" value="Marcar" name="Marcar" onClick="marcarTodos(true)">
                  <input type="button" class="button" value="Desmarcar" name="Desmarcar" onClick="marcarTodos(false)">                  
              </p><div class="hr"></div>              </td>
        </tr>
        
        <tr>
          <td width="100%"><font class="optionBlueText">Organización de la Información</font>
            <input type="checkbox" name="SEPARADA_POR_EMPRESA" value="ON" onClick="checkSeparadaPorEmpresa()"><font class="optionText">Separada por Empresa</font><br>
              <input type="checkbox" name="SEPARADA_POR_NC" value="ON"><font class="optionText">Separada por Nómina de Crédito</font><br>
              <input type="checkbox" name="SEPARADA_POR_NL" value="ON"><font class="optionText">Separada por Nómina de Ahorro</font><br>
              <input type="checkbox" name="SEPARADA_POR_AT" value="ON"><font class="optionText">Separada por Anexo de Trabajadores</font>
              <br>
              <br></td>
        </tr>

        <tr>
          <td width="100%"><div class="hr"></div>
             <font class="optionBlueText">Formato de la Información</font>
             <input type="radio" name="FORMATO" value="txt" checked><font class="optionText">Texto Plano</font><br>
              <input type="radio" name="FORMATO" value="csv"><font class="optionText">Texto CSV</font><br>
              <input type="radio" name="FORMATO" value="xls"><font class="optionText">Planilla Excel XLS</font><br>              
              <br>
              <p></p>          </td>
        </tr>        
      </table></div>
    		<P align="center">&nbsp;</P>   	  </td>

	  <td width="70%" colspan="6" align="left" valign="top">
		<h1><strong>Servicio N&oacute;minas y Anexos</strong></h1>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tbody>
            <tr>
              <td width="80%" height="21">        
              <td width="10%" height="21" align="right"><A
						href="javascript:fPopupHelp(
				                   '<%=request.getServerName()%>',
				                   '<%=request.getServerPort()%>')"> <img src="/ea/edocs/images/ayuda.gif" border="0"> </a></td>
            </tr>
            <tr>
              <td width="80%" height="21"></td>
              <td width="10%" height="21"></td>
            </tr>
            <tr>
              <td width="20%" align="left" height="18"><font class="blackText">Encargado
                de Empresa:</font><br>
                            <font class="blueText"><jsp:getProperty name="edocs_encargado"
						property="fullName" /></font></td>
              <td width="70%" align="right" height="18"><font class="blackText">Rut
                Encargado:</font><br>
                            <font class="blueText">  <jsp:getProperty name="edocs_encargado"
						property="formattedRut" /></font></td>
            </tr>
          </tbody>
        </table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%">
          <tr>
            <td width="90%"><br>
                <br>
                <p><font class="blackText">&nbsp;&nbsp;&nbsp;
                  Esta p&aacute;gina le permitir&aacute; obtener las <u>N&oacute;minas de Cr&eacute;dito</u>, <u>N&oacute;minas de Ahorro</u> y <u>Anexos de Trabajadores</u>, correspondientes al <font class="blueText"> per&iacute;odo de cotizaci&oacute;n
                     <jsp:getProperty name="publishedPeriods" property="selectedPeriodToString" /> </font> y que pertenecen a aquellas empresas que est&aacute;n bajo su administraci&oacute;n. Las
                  opciones disponibles a su izquierda, le permitir&aacute;n seleccionar y especificar
                  la organizaci&oacute;n y formato de la informaci&oacute;n requerida por usted.</font></p>
             
              <logic:greaterThan name="publishedPeriods" property="publishedPeriodsCount" value="1">
                  <logic:equal name="publishedPeriods" property="selectedPeriodIndex" value="0">
                      <p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      También se encuentran disponibles las nóminas y anexos del período anterior.
                      Si desea obtener esta información, por favor haga click en 
                      <a href='main.do?period=<jsp:getProperty name="publishedPeriods" property="secondPeriod" />'>
                      <font class="blueText">
                      período de cotización
                      <jsp:getProperty name="publishedPeriods" property="secondPeriodToString" /></font></a>.
                      </font></p>
                  </logic:equal>
              </logic:greaterThan>
                  
             </td>
          </tr>
          <tr>
            <td width="90%"> <div id="mensaje"><br>
                <p><font color="#333333"> <b>AVISO IMPORTANTE:</b> Se comunica que a partir del per&iacute;odo <b>Noviembre/2007</b>, las n&oacute;minas de cr&eacute;dito se publicar&aacute;n
                  con un nuevo formato. Para m&aacute;s detalle, por favor haga click <a href="/ea/edocs/formato_nc.html">aqu&iacute;</a>. </font> <br>
                      </p></div></td>
          </tr>
          
          
        <logic:greaterThan name="edocs_encargado" property="empresasCount" value="12">
	        <tr>
	          <td width="100%" colspan="3">
	              <br>
	              <p align="center">
	                  <input type="submit" name="aceptar" class="button" value="Aceptar" onClick="return checkInput();">
	                  <input type="button" name="cancelar" class="button" value="Cancelar" onClick="closeForm();">
	              </p>
	              <p>&nbsp;</p>
	          </td>
	        </tr>
        </logic:greaterThan>
        <logic:lessEqual name="edocs_encargado" property="empresasCount" value="12">
	        <tr>
	          <td width="100%" colspan="3">
	              <br>
	              <br>
	          </td>
	        </tr>
        </logic:lessEqual>
        
          
          <!--
          <tr>
            <td width="100%"><br>
                <br></td>
          </tr>
          
          
          -->
          <tr>
            <td width="100%"><div id="tabla">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                  <tr>
                    <th align="left"><font class="text">Rut  Empresa</font></th>
                    <th align="left"><font class="text">Nombre  Empresa</font></th>
                    <th align="center"><font class="text">N&oacute;minas Cr&eacute;dito</font></th>
                    <th align="center"><font class="text">N&oacute;minas  Ahorro</font></th>
                    <th align="center"><font class="text">Anexos  Trabajadores</font></th>
                  </tr>
                  
                  
          <logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 

			  <bean:define id="counters" type="java.util.Map" name="empresa" property="counters" />

              <% if (((index.intValue() + 1) % 2) == 1) {%>
	              <tr>
	                
	                <td width="16%"><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="42%"><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="12%">
	                    <logic:greaterThan name="counters" property="NC" value="0">
	                        <p align="center"><input type="checkbox" name="NC<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="12%">
	                    <logic:greaterThan name="counters" property="NL" value="0">
	                        <p align="center"><input type="checkbox" name="NL<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="14%">
	                    <logic:greaterThan name="counters" property="AT" value="0">
	                        <p align="center"><input type="checkbox" name="AT<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                
	              </tr>
	          <% } else {%>
	              <tr>
	               
	                <td width="16%" ><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="39%" ><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="13%">
	                    <logic:greaterThan name="counters" property="NC" value="0">
	                        <p align="center"><input type="checkbox" name="NC<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="13%" >
	                    <logic:greaterThan name="counters" property="NL" value="0">
	                        <p align="center"><input type="checkbox" name="NL<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="15%" >
	                    <logic:greaterThan name="counters" property="AT" value="0">
	                        <p align="center"><input type="checkbox" name="AT<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	               
	              </tr>
	          <% } %>
          </logic:iterate>
                  
                  <!--
                  <tr>
                    <td width="16%"><font class="blueText">&nbsp;70.016.160-9</font></td>
                    <td><font class="blueText">LA ARAUCANA C.C.A.F.</font></td>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  -->
                </table>
            </div></td>
          </tr>
          <tr>
            <td width="100%"><br>
                <p align="center">
                  <input type="submit" name="aceptar" class="button" value="Aceptar" onClick="return checkInput();">
                  <input type="button" name="cancelar" class="button" value="Cancelar" onClick="closeForm();">
              </p></td>
          </tr>
      </table></td>
	</tr>
</table>
</form>

</body>
</html>
