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
		eval("page" + id + " = window.open('https://extranet.araucana.cl/Cotiza/bonificaext.nsf/antecedentes?OpenForm&usuario=<%=edocs_encargado.getFullName()%>&rutUsuario=<%=edocs_encargado.getFullRut()%>', '" + id + "', 'width=800,height=600,left = 112,top = 84');");
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
    	<td width="30%" valign="middle" height="80">
	    	<P align="center">&nbsp;<FONT class="blackTextP6"><b>Bono Extraordinario Ley N� 20.262</b></FONT></P>
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
    <td width="30%" bgcolor="#CCCCCC" valign="top" align="left" height="350">
      <table border="0" cellpadding="0" cellspacing="0" width="100%">
        <tr>
          <td width="100%">
              <br>
              <p align="center"><font class="optionBlueText">Selecci�n de la Informaci�n</font></p>
              &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" name="TODOS_BONO" value="ON"><font class="optionText">Todas las empresas</font><br>
              <br>
              <p align="center">
                  <input type="button" class="button" value="Marcar" name="Marcar" onclick="marcarTodos(true)">
                  <input type="button" class="button" value="Desmarcar" name="Desmarcar" onclick="marcarTodos(false)">                  
              </p>
              <hr>
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
         	  <FONT color="#ff8000">Env�o Bono Extraordinario Ley N� 20.262</FONT>
              	<p><font class="blackText14">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              	Para enviarnos su archivo, por favor haga click </font><font class="blackText16"><a href="javascript:subirArchivo()">AQU&Iacute;</a>
              	</font>
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
         	  <FONT color="#ff8000">Descarga propuesta Bono Extraordinario Ley N� 20.262</FONT>
              <p><font class="blackText">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              A trav�s de este servicio usted puede obtener la propuesta correspondiente al bono extraordinario que beneficia
              a los sectores de menores recursos.
              </FONT>
              <p><font class="blueText"  color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              <b>NOTA:</b> En caso que su empresa no tenga posibles beneficiarios de acuerdo a nuestra informaci�n, aparecer� la glosa <b>"SIN PROPUESTA"</b>.
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
         
            <table border="0" cellpadding="0" cellspacing="0" width="100%">            

              <tr>
                <td width="2%">&nbsp;</td>
                <td width="16%" bgcolor="#000080" align="left"><font class="text">Rut <br> Empresa</font></td>
                <td width="42%" bgcolor="#000080" align="left"><font class="text">Nombre <br> Empresa</font></td>
                <td width="38%" bgcolor="#000080" align="center"><font class="text">Bonos</font></td>
                <td width="2%">&nbsp;</td>
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
	                <td width="16%" bgcolor="#CCFFFF"><font class="blueText">&nbsp;<jsp:getProperty name="empresa" property="formattedRut" /></font></td>
	                <td width="42%" bgcolor="#CCFFFF"><font class="blueText"><jsp:getProperty name="empresa" property="name" /></font></td>
	                <td width="38%" bgcolor="#CCFFFF">
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
