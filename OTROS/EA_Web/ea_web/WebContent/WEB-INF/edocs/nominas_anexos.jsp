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

<link rel="stylesheet" type="text/css" href="/ea/edocs/css/edocs.css">

<script language="JavaScript1.2">

	// Se agrega Nómina Crédito Educación Superior
	//Claudio Lillo A.
	//12-04-2011
	// Se elimina Nómina Crédito Educación Superior
	//Claudio Lillo A.
	//02-07-2011

<!--
	var path= "http://<%=request.getServerName()%>:<%=request.getServerPort()%>";
	function initForm() {
        document.forms[0].SEPARADA_POR_NC.disabled = true;
        document.forms[0].SEPARADA_POR_NL.disabled = true;
        document.forms[0].SEPARADA_POR_AT.disabled = true;
        OpenAviso();	
	}
	
	function OpenAviso(){
		var opciones="toolbar=no,location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, width=400, height=160, top=350, left=400";
		pagina = path + "/ea/edocs/AvisoNominas.html";
		window.open(pagina,"",opciones);
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

</head>

<body topmargin="0" leftmargin="10" marginheight="10" marginwidth="10" onload="initForm();">

<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.Encargado" scope="session" >
</jsp:useBean>

<jsp:useBean id="publishedPeriods" class="cl.araucana.ea.edocs.PublishedPeriodsBean" scope="request" >
</jsp:useBean>




<form action="/ea/edocs/zipper.do" method="POST">

<input type="hidden" name="serviceName" value="NC_NL_AT">
<input type="hidden" name="period" value='<jsp:getProperty name="publishedPeriods" property="selectedPeriod" />'>

<table border="0" style="width : 100%; height: 60px;" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td align="left">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td rowspan="2" valign="top" align="left">
							<img src='/ea/img/EPC.jpg'>
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  
  <tr>
    	<td width="30%" valign="middle" height="80">
    		<P align="center">&nbsp;<FONT class="blackTextP6"><b>Servicio N&oacute;minas y Anexos</b></FONT></P>
    	</td>

		<td width="70%" colspan="6" valign="top" align="left" height="80">&nbsp;
		<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
			<TBODY>
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="80%" height="21">
					<TD width="10%" height="21" align="left"><A
						href="javascript:fPopupHelp(
				                   '<%=request.getServerName()%>',
				                   '<%=request.getServerPort()%>')">
					<IMG src="/ea/edocs/images/ayuda.gif" border="0"> </A></TD>
					<TD width="5%" height="21"></TD>
				</TR>

				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="80%" height="21"></TD>
					<TD width="10%" height="21"></TD>
					<TD width="5%" height="21"></TD>
				</TR>

				<TR>
					<TD width="5%" height="18"></TD>
					<TD width="20%" align="left" height="18"><FONT class="blackText">Encargado
					de Empresa:</FONT><BR>
					<FONT class="blueText"><jsp:getProperty name="edocs_encargado"
						property="fullName" /></FONT></TD>
					<TD width="70%" align="left" height="18"><FONT class="blackText">Rut
					Encargado:</FONT><BR>
					<FONT class="blueText"> <jsp:getProperty name="edocs_encargado"
						property="formattedRut" /></FONT></TD>
					<TD width="5%" height="18"></TD>
				</TR>
			</TBODY>
		</TABLE>
		</td>
	</tr>
     
  <tr>
    <td width="30%" bgcolor="#5599AA" valign="top" align="left" height="1761">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
              <br>
              <p align="center"><font class="blackTextP2">Selección de la Información</font></p>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="TODAS_NC" value="ON"><font class="optionText">Todas las Nóminas de Crédito</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="TODAS_NL" value="ON"><font class="optionText">Todas las Nóminas de Ahorro</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="TODOS_AT" value="ON"><font class="optionText">Todos los Anexos de Trabajadores</font>
              <br>
              <p align="center">
                  <input type="button" class="button" value="Marcar" name="Marcar" onclick="marcarTodos(true)">
                  <input type="button" class="button" value="Desmarcar" name="Desmarcar" onclick="marcarTodos(false)">                  
              </p>
              <hr>
          </td>
        </tr>
        
        <tr>
          <td width="100%">
              <p align="center"><font class="blackTextP2">Organización de la Información</font></p>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="SEPARADA_POR_EMPRESA" value="ON" onclick="checkSeparadaPorEmpresa()"><font class="optionText">Separada por Empresa</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="SEPARADA_POR_NC" value="ON"><font class="optionText">Separada por Nómina de Crédito</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="SEPARADA_POR_NL" value="ON"><font class="optionText">Separada por Nómina de Ahorro</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="SEPARADA_POR_AT" value="ON"><font class="optionText">Separada por Anexo de Trabajadores</font>
              <br>
              <br>
              <hr>
          </td>
        </tr>

        <tr>
          <td width="100%">
              <p align="center"><font class="blackTextP2">Formato de la Información</font></p>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="FORMATO" value="txt" checked><font class="optionText">Texto Plano</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="FORMATO" value="csv"><font class="optionText">Texto CSV</font><br>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="FORMATO" value="xls"><font class="optionText">Planilla Excel XLS</font><br>              
              <br>
              <p></p>
          </td>
        </tr>        
      </table>
    </td>
    <td width="70%" valign="top" align="left" height="1761">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="5%">
              <p><font class="blackText">&nbsp;</font></p>
          </td>
          <td width="90%">
              <br>
              <br>
              <p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              Esta página le permitirá obtener las <u>Nóminas de Crédito</u>, <u>Nóminas de Ahorro</u>
              y <u>Anexos de Trabajadores</u>, correspondientes al
              <font class="blueText">
              período de cotización
              <jsp:getProperty name="publishedPeriods" property="selectedPeriodToString" />
              </font>
              y que pertenecen a aquellas empresas que están bajo su administración. Las
              opciones disponibles a su izquierda, le permitirán seleccionar y especificar
              la organización y formato de la información requerida por usted.</font></p>
             
              <logic:greaterThan name="publishedPeriods" property="publishedPeriodsCount" value="1">
                  <logic:equal name="publishedPeriods" property="selectedPeriodIndex" value="0">
                      <p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      También se encuentran disponibles las nóminas y anexos del período anterior.
                      Si desea obtener esta información, por favor haga click en 
                      <a href='edocs/main.do?period=<jsp:getProperty name="publishedPeriods" property="secondPeriod" />'>
                      <font class="blueText">
                      período de cotización
                      <jsp:getProperty name="publishedPeriods" property="secondPeriodToString" /></font></a>.
                      </font></p>
                  </logic:equal>
              </logic:greaterThan>
              
          </td>
          <td width="5%">
          </td>
        </tr>

        <tr>
          <td width="5%">
              <p><font class="blackText">&nbsp;</font></p>
          </td>
          <td width="90%">
			&nbsp;
          </td>
          <td width="5%">
          </td>
        </tr>
        
        <logic:greaterThan name="edocs_encargado" property="empresasCount" value="12">
	        <tr>
	          <td width="100%" colspan="3">
	              <br>
	              <p align="center">
	                  <input type="submit" name="aceptar" class="button" value="Aceptar" onclick="return checkInput();">
	                  <input type="button" name="cancelar" class="button" value="Cancelar" onclick="closeForm();">
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
        
        <tr>
          <td width="100%" colspan="3">
         
            <table border="0" cellpadding="1" cellspacing="1" width="100%">            

              <tr>
                <td width="2%">&nbsp;</td>
                <td width="14%" bgcolor="#558899" align="left"><font class="text">Rut <br> Empresa</font></td>
                <td width="44%" bgcolor="#558899" align="left"><font class="text">Nombre <br> Empresa</font></td>
                <td width="12%" bgcolor="#558899" align="center"><font class="text">N&oacute;minas <br> Cr&eacute;dito</font></td>
                <td width="12%" bgcolor="#558899" align="center"><font class="text">N&oacute;minas <br> Ahorro</font></td>
                <td width="14%" bgcolor="#558899" align="center"><font class="text">Anexos <br> Trabajadores</font></td>
                <td width="2%">&nbsp;</td>
              </tr>

          <logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 

			  <bean:define id="counters" type="java.util.Map" name="empresa" property="counters" />

              <% if (((index.intValue() + 1) % 2) == 1) {%>
	              <tr>
	                <td width="2%">&nbsp;</td>
	                <td width="14%"><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="44%"><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
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
	                <td width="2%"></td>
	              </tr>
	          <% } else {%>
	              <tr>
	                <td width="2%">&nbsp;</td>
	                <td width="14%" bgcolor="#CCFFFF"><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="44%" bgcolor="#CCFFFF"><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="12%" bgcolor="#CCFFFF">
	                    <logic:greaterThan name="counters" property="NC" value="0">
	                        <p align="center"><input type="checkbox" name="NC<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="12%" bgcolor="#CCFFFF">
	                    <logic:greaterThan name="counters" property="NL" value="0">
	                        <p align="center"><input type="checkbox" name="NL<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="14%" bgcolor="#CCFFFF">
	                    <logic:greaterThan name="counters" property="AT" value="0">
	                        <p align="center"><input type="checkbox" name="AT<%=index.intValue() + 1%>" value="ALL"></p>
	                    </logic:greaterThan>
	                </td>
	                <td width="2%"></td>
	              </tr>
	          <% } %>
          </logic:iterate>

            </table>
          </td>
        </tr>
        <tr>
          <td width="100%" colspan="3">
              <br>
              <p align="center">
                  <input type="submit" name="aceptar" class="button" value="Aceptar" onclick="return checkInput();">
                  <input type="button" name="cancelar" class="button" value="Cancelar" onclick="closeForm();">
              </p>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</form>

</body>
</html>
