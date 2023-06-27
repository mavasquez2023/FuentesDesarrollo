<%@ page contentType="text/html; charset=ISO-8859-1" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

<!--jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />-->

<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.be.Encargado" scope="session" >
</jsp:useBean>

<title>La Araucana C.C.A.F. - Bono Extraordinario</title>
<link rel="stylesheet" type="text/css" href="/ea/edocs/css/edocs.css">
<style>
UNKNOWN {
	
}
body {
	padding-top: 0px;
	margin-top: 0px;
	background-image: url(fondopag.jpg);
	font-size: 13px;
	font-family: Arial, Helvetica;
}
#mensaje {
	padding-top: 5px;
	margin: 0px;
	padding-right: 5px;
	padding-bottom: 5px;
	padding-left: 5px;
	height: auto;
	background-color: #f1f1f1;
	font-family: Arial, Helvetica;
	font-size: 13px;
}

.text {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.button {
	FONT-SIZE: 13px;
	COLOR: #FFFFFF;
	FONT-STYLE: normal;
	FONT-FAMILY: arial;
	background-image: url(fondobot.jpg);
	margin-right: 5px;
	margin-left: 5px;
	margin-bottom: 15px;
	border-top-color: 03557b;
	border-right-color: 03557b;
	border-bottom-color: 007bb7;
	border-left-color: 007bb7;
	font-weight: bold;
	height: 25px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
}
.menu {
	border: 1px solid #ABABAB;
	width: 238px;
}

h1 {
	font-family: Arial, Helvetica;
	font-size: 25px;
	font-weight: bold;
	color: #999999;
	margin-left: 10px;
}
#tabla {
	border: 1px solid #ABABAB;
	width: 730px;
}


.blackText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
#tabla th {
	background-image: url(fondo_top_tabla.jpg);
	height: 24px;
	padding-top: 3px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
}

.blackText14 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackText16 {
	FONT-WEIGHT: bold;
	FONT-SIZE: 16px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP6 {
	FONT-SIZE: 16px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blackTextP2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: Arial, Helvetica
}
.blueTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.blueText5 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.redTextP4 {
	FONT-SIZE: 14px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.redBackGris {
	FONT-STYLE: normal;
	padding-top: 10px;
	padding-right: 10px;
	padding-bottom: 10px;
	padding-left: 5px;
}
.optionText {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: arial
}
.optionBlueText {
	FONT-SIZE: 16px;
	COLOR: #005d8b;
	FONT-STYLE: normal;
	FONT-FAMILY: arial;
	margin: 5px;
	text-align: left;
	font-weight: bold;
	padding: 5px;
	display: block;
}
.optionBlueTextP2 {
	FONT-SIZE: 13px;
	COLOR: #333;
	FONT-STYLE: normal;
	FONT-FAMILY: Arial, Helvetica
}
#tabla td {
	height: 25px;
	padding-top: 5px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
	font-family: Arial, Helvetica;
}
a:link {
	font-family: Arial, Helvetica;
	font-size: 13px;
	color: #333;
}
#tabla th {
	height: 25px;
	padding-top: 5px;
	padding-right: 3px;
	padding-bottom: 0px;
	padding-left: 3px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: none;
	border-left-style: none;
	font-family: Arial, Helvetica;
}
.hr  {
	clear: both;
	height: 2px;
	background-repeat: repeat-x;
	background-position: 0px 0px;
	border:none !important;
	padding: 0px;
	margin-top: 5px;
	margin-right: 0px;
	margin-bottom: 5px;
	margin-left: 0px;
	background-image: url(corp_linea_puntos.gif);
}

</style>
<script language="JavaScript1.2">
<!--
	
    function marcarTodos(value) {
        if (document.forms[0].TODOS_BONO.checked) {
            marcarTodosBonos(value);
        }

        document.forms[0].TODOS_BONO.checked = false;
    }

	function marcarTodosBonos(value) {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('BONO') == 0) {
			     marcarItem(document.forms[0].elements[j], value);
          }
	   }	
	}
	
	function marcarItem(item, value) {
       item.checked = value;
	   item.disabled = false;			         
	   item.value = "ALL";
	}

	function checkInput() {
		for (j = 0; j < document.forms[0].elements.length; j++) {
		    if (document.forms[0].elements[j].name.indexOf('BONO') == 0 && document.forms[0].elements[j].checked) {
			    return true;
			}
        }
	
		alert("Por favor, seleccione los bonos requeridos por usted.");
	
		return false;
	}
	
	function subirArchivo() {
		day = new Date();
		id = day.getTime();
		eval("page" + id + " = window.open('https://domdesa/Cotiza/bonificaext.nsf/antecedentes?OpenForm&usuario=<%=edocs_encargado.getFullName()%>&rutUsuario=<%=edocs_encargado.getFullRut()%>', '" + id + "', 'width=800,height=600,left = 112,top = 84');");
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

<body topmargin="10" leftmargin="10" marginheight="10" marginwidth="10">

<form action="Zipper" method="POST">

<input type="hidden" name="serviceName" value="bono">

<table border="0" style="width : 100%; height: 60px;" cellpadding="0" cellspacing="0">
	<tbody>
		<tr>
			<td align="right">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td rowspan="2" valign="top">
							<img src='/ea/img/BONO.JPG'>
						</td>
					</tr>
				</table>
			</td>
		</tr>
</table>

<table border="0" cellpadding="0" cellspacing="0" width="100%">
  
  <tr>
    	<td width="30%" valign="middle" height="80"><P align="center">&nbsp;<FONT class="blackTextP6"><b>Bono Extraordinario Ley N° 20.262</b></FONT></P>
    	</td>

		<td width="70%" colspan="6" valign="top" align="left" height="80">&nbsp;
		<TABLE border="0" cellpadding="0" cellspacing="0" width="100%">
			<TBODY>
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
						property="formattedRut" /></FONT>
						<%
							String fullName = edocs_encargado.getFullName();
							String rut = edocs_encargado.getFullRut(); 
						%>
					</TD>
					<TD width="5%" height="18"></TD>
				</TR>
				<TR>
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="80%" height="21"></TD>
					<TD width="10%" height="21"></TD>
					<TD width="5%" height="21"></TD>
				</TR>
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="65%" height="21"></TD>
					<TD width="35%" height="21" >
						<FONT class="blackText"><a href="http://download.winzip.com/winzip90.exe">Download Winzip</a></FONT>
					</TD>
				</TR>
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="80%" height="21"></TD>
					<TD width="10%" height="21"></TD>
					<TD width="5%" height="21"></TD>
				</TR>
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="35%" colspan=2 align=right height="21" >
						<FONT class="blackText">Si desea presentar los datos de forma <b>Manual</b></FONT>
					</TD>
				</TR>	
				<TR>
					<TD width="5%" height="21"></TD>
					<TD width="35%" colspan=2 align=right height="21" >
						<FONT class="blackText">DESCARGUE FORMULARIO <a href="/ea/edocs/BONO_Ley_20262.pdf">AQUI</a></FONT>
					</TD>
				</TR>			
			</TBODY>
		</TABLE>
		</td>
	</tr>
     
  <tr>
    <td width="30%"  valign="top" align="left" height="350">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
              <br>
              <p align="center"><font class="optionBlueText">Selección de la Información</font></p>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="TODOS_BONO" value="ON"><font class="optionText">Todas las empresas</font><br>
              <br>
              <p align="center">
                  <input type="button" class="button" value="Marcar" name="Marcar" onClick="marcarTodos(true)">
                  <input type="button" class="button" value="Desmarcar" name="Desmarcar" onClick="marcarTodos(false)">                  
              </p>
              <div class="hr"></div>
          </td>
        </tr>
                <tr>
          <td width="100%">
              
            
             <img height="120" border="0" width="120" src="/ea/edocs/images/logo_gobierno.jpg">
          </td>
        </tr>
      </table>
    </td>
    <td width="70%" valign="top" align="left" height="350">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="5%">
              <p><font class="blackText">&nbsp;</font></p>
          </td>
          <td width="90%">
         	  <FONT >Envío Bono Extraordinario Ley N° 20.262</FONT>
              	<p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	Para enviarnos su archivo, por favor haga click <a href="javascript:subirArchivo()">AQU&Iacute;</a>
              </FONT>
              <br>
              <br>
          </td>
          <td width="5%">
          </td>
        </tr>
        <tr>
          <td width="5%">
              <p><font class="blackText">&nbsp;</font></p>
          </td>
          <td width="90%">
         	  <FONT >Descarga propuesta Bono Extraordinario Ley N° 20.262</FONT>
              <p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              A través de este servicio usted puede obtener la propuesta correspondiente al bono extraordinario que beneficia
              a los sectores de menores recursos.
              </FONT>
              <p><font class="blueText"  color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <b>NOTA:</b> En caso que su empresa no tenga posibles beneficiarios de acuerdo a nuestra información, aparecerá la glosa <b>"SIN PROPUESTA"</b>.
              </font>
          </td>
          <td width="5%">
          </td>
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
        
        <tr>
          <td width="100%" colspan="3">
          <div id="tabla">
            <table border="0" cellpadding="0" cellspacing="0" width="100%">            

              <tr>
                <th width="2%">&nbsp;</th>
                <th width="16%" align="left"><font class="text">Rut <br> Empresa</font></th>
                <th width="42%"  align="left"><font class="text">Nombre <br> Empresa</font></th>
                <th width="38%" align="center"><font class="text">Bonos</font></th>
                <th width="2%">&nbsp;</th>
              </tr>

          <logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index"> 

			<bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />

              <% if (((index.intValue() + 1) % 2) == 1) {%>
	              <tr>
	                <td width="2%">&nbsp;</td>
	                <td width="16%"><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="42%"><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="38%">
	                   <logic:greaterThan name="empresa" property="flag" value="0">
	                        <p align="center"><input type="checkbox" name="BONO<%=index.intValue() + 1%>" value="ALL"></p>
	                   </logic:greaterThan>
	                   <logic:equal name="empresa" property="flag" value="0">
	                        <p align="center"><font class="blueText">SIN PROPUESTA</font></p>
	                   </logic:equal>
	                </td>
	                <td width="2%"></td>
	              </tr>
	          <% } else {%>
	              <tr>
	                <td width="2%">&nbsp;</td>
	                <td width="16%" ><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="42%" ><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="38%">
						<logic:greaterThan name="empresa" property="flag" value="0">
	                        <p align="center"><input type="checkbox" name="BONO<%=index.intValue() + 1%>" value="ALL"></p>
	     		        </logic:greaterThan>
	                   <logic:equal name="empresa" property="flag" value="0">
	                        <p align="center"><font class="blueText">SIN PROPUESTA</font></p>
	                   </logic:equal>
	                </td>
	                <td width="2%"></td>
	              </tr>
	          <% } %>
          </logic:iterate>

            </table>
            </div>
          </td>
        </tr>
        <tr>
          <td width="100%" colspan="3">
              <br>
              <p align="center">
                  <input type="submit" name="aceptar" class="button" value="Aceptar" onClick="return checkInput();">
                  <input type="button" name="cancelar" class="button" value="Cancelar" onClick="closeForm();">
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
