<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0030)http://www.misplanillas.cl/ea/ -->
<HTML>
<HEAD>
<TITLE>La Araucana C.C.A.F. - Ingresos de Trabajadores</TITLE>
<META http-equiv=Content-Type content="text/html; charset=iso-8859-1">
<!--jsp:useBean id="software" class="cl.araucana.core.util.ApplicationBean" scope="application" />-->
<LINK 
href="/ea/edocs/css/Rentas.css" 
type=text/css rel=stylesheet>
<jsp:useBean id="edocs_encargado" class="cl.araucana.ea.edocs.re.Encargado" scope="session" >
</jsp:useBean>
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
		eval("page" + id + " = window.open('http://extranet.araucana.cl/circular2511.nsf/attach?OpenForm&usuario=<%=edocs_encargado.getFullName()%>&rutUsuario=<%=edocs_encargado.getFullRut()%>', '" + id + "', 'width=800,height=600,left = 112,top = 84');");
	}
	
		
    function closeForm() {
    	window.location = "http://rasw.laaraucana.cl/sv/router.do?service=DEFAULT"

  /*  			  "http://"
    			+ "<%=request.getServerName()%>"
    			+ ":"
    			+ "<%=request.getServerPort()%>"
    			+ "/ea/logout.do";
    
    */
        return false;
    }	

-->
</script>

<!-- clillo, 15/06/10 se cambian todas las referencias en duro de los años por:

-->
</HEAD>
<BODY topMargin=10 marginwidth="10" marginheight="10">
<FORM action="ZipperRentas" method="post">
  <INPUT type=hidden value=bono name=serviceName>
  <TABLE style="WIDTH: 100%; HEIGHT: 60px" cellSpacing=0 cellPadding=0 border=0>
    <TBODY>
      <TR>
        <TD align=left><TABLE cellSpacing=0 cellPadding=0>
            <TBODY>
              <TR>
                <TD vAlign=top rowSpan=2><IMG 
            src="/ea/edocs/images/BONO.jpg"></TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <TABLE cellSpacing=0 cellPadding=0 width="974" border=0>
    <TBODY>
      <TR>
        <TD vAlign=top><DIV class="menu">
            <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
              <TBODY>
                <TR>
                  <TD width="100%"><FONT class=optionBlueText>Selección de la 
                    Información</FONT>    
                    <INPUT type=checkbox 
            value=ON name=TODOS_BONO>
                    <FONT class=optionText>Todas las 
                    empresas</FONT><BR>
                    <BR>
                    <P align=center>
                      <INPUT onclick=marcarTodos(true) type=button value=Marcar name=Marcar class="boton" onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'">
                      <INPUT class=boton onclick=marcarTodos(false) type=button value=Desmarcar name=Desmarcar onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >
                      <br>
                      <br>
                    </P></TD>
                </TR>
              </TBODY>
            </TABLE>
          </DIV></TD>
        <TD vAlign=top align=left colSpan=6 height=80><h1>Proceso Actualización de Tramos período 2011 a 2012</h1>
          <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
            <TBODY>
              <TR>
                <TD width="5%" height=21></TD>
                <TD width="80%" height=21></TD>
                <TD width="10%" height=21></TD>
                <TD width="5%" height=21></TD>
              </TR>
              <TR>
                <TD width="5%" height=18></TD>
                <TD align=left width="20%" height=18><FONT class=blackText>Encargado 
                  de Empresa:</FONT><BR>
                  <FONT class=blueText>
                  <jsp:getProperty name="edocs_encargado"
						property="fullName" />
                  </FONT></TD>
                <TD align=left width="70%" height=18><FONT class=blackText>Rut 
                  Encargado:</FONT><BR>
                  <FONT class=blueText>
                  <jsp:getProperty name="edocs_encargado"
						property="formattedRut" />
                  </FONT>
                  <%
							String fullName = edocs_encargado.getFullName();
							String rut = edocs_encargado.getFullRut(); 
						%></TD>
                <TD width="5%" height=18></TD>
              </TR>
              <TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD width="80%" height=21></TD>
                <TD width="10%" height=21></TD>
                <TD width="5%" height=21></TD>
              </TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD width="65%" height=21></TD>
                <TD width="35%" height=21><FONT class=blackText><A 
            href="http://download.winzip.com/winzip90.exe">Download 
                  Winzip</A></FONT></TD>
              </TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD width="80%" height=21></TD>
                <TD width="10%" height=21></TD>
                <TD width="5%" height=21></TD>
              </TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD align=right width="35%" colSpan=2 height=21><FONT 
            class=blackText>Si desea presentar los datos de foarm <B>Manual</B></FONT></TD>
              </TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD align=right width="35%" colSpan=2 height=21><FONT 
            class=blackText>DESCARGUE FORMULARIO <A 
            onclick="openWindow('', 'PDF',  700, 500);return false;" 
            href="http://rasz.laaraucana.cl:9080/ea/edocs/INFORME_Ingresos.pdf">AQUI</A></FONT></TD>
              </TR>
              <TR>
                <TD width="5%" height=21></TD>
                <TD align=right width="35%" colSpan=2 height=21><FONT 
            class=blackText>DESCARGUE FORMATO <A 
            onclick="openWindow('', 'PDF',  700, 500);return false;" 
            href="http://rasz.laaraucana.cl:9080/ea/edocs/formato2511.xls">AQUI</A></FONT></TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
      <TR>
        <TD vAlign=top align=left  height=350></TD>
        <TD vAlign=top align=left height=350><TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
            <TBODY>
              <TR>
                <TD width="90%"><FONT class=blueText5>Descarga de propuesta para el 
                  proceso de actualización de tramos período agosto 2010 a junio 
                  2011</FONT> <!--p><font class="blackText14">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<BR>
              	Para enviarnos su archivo, por favor haga click </font><font class="blackText16"><a href="javascript:subirArchivo();">AQU&Iacute;</a>
              	</font></p--></TD>
              </TR>
              <TR>
  <logic:greaterThan name="edocs_encargado" property="empresasCount" value="12">
              <tr>
                <td width="100%" colspan="3"><p align="center">
                    <input type="submit" name="aceptar" class="button" value="Aceptar" onClick="return checkInput();">
                    <input type="button" name="cancelar" class="button" value="Cancelar" onClick="closeForm();">
                  </p></td>
              </tr>
                </logic:greaterThan>
              
              <logic:lessEqual name="edocs_encargado" property="empresasCount" value="12">
                <tr>
                  <td width="100%" colspan="3"><br>
                    <br></td>
                </tr>
              </logic:lessEqual>
              <TR>
                <TD width="100%"><div id="tabla">
                    <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
                      <TBODY>
                        <TR>
                          <th width="16%" align=left bgcolor="#050BD1"><FONT 
                  class=text>Rut
                            Empresa</FONT></th>
                          <TH align=left width="42%"><FONT 
                  class=text>Nombre Empresa</FONT></TH>
                          <tH align=middle width="38%"><FONT 
                  class=text>Rentas</FONT></TH>
                        </TR>
                        <logic:iterate name="edocs_encargado" property="empresas" id="empresa" indexId="index">
                          <bean:define id="flag" type="java.lang.Integer" name="empresa" property="flag" />
                          <% if (((index.intValue() + 1) % 2) == 1) {%>
                          <tr>
                            
                            <td width="16%"><font class="blueText">&nbsp;
                              <jsp:getProperty name="empresa" property="formattedRut" />
                              </font></td>
                            <td width="42%"><font class="blueText">
                              <jsp:getProperty name="empresa" property="name" />
                              </font></td>
                            <td width="38%"><logic:greaterThan name="empresa" property="flag" value="0">
                                <p align="center">
                                  <input type="checkbox" name="BONO<%=index.intValue() + 1%>" value="ALL">
                                </p>
                              </logic:greaterThan>
                              <logic:equal name="empresa" property="flag" value="0">
                                <p align="center"><font class="blueText">SIN PROPUESTA</font></p>
                              </logic:equal></td>
                            
                          </tr>
                          <% } else {%>
                          <tr>
                         
                            <td width="16%" bgcolor=""><font class="blueText">&nbsp;
                              <jsp:getProperty name="empresa" property="formattedRut" />
                              </font></td>
                            <td width="42%" bgcolor=""><font class="blueText">
                              <jsp:getProperty name="empresa" property="name" />
                              </font></td>
                            <td width="38%" bgcolor=""><logic:greaterThan name="empresa" property="flag" value="0">
                                <p align="center">
                                  <input type="checkbox" name="BONO<%=index.intValue() + 1%>" value="ALL">
                                </p>
                              </logic:greaterThan>
                              <logic:equal name="empresa" property="flag" value="0">
                                <p align="center"><font class="blueText">SIN PROPUESTA</font></p>
                              </logic:equal></td>
                        
                          </tr>
                          <% } %>
                        </logic:iterate>
                        <!--
                        <TR>
                          <TD width="16%"><FONT class=blueText>&nbsp;70.016.160-9</FONT></TD>
                          <TD width="42%"><FONT class=blueText>LA ARAUCANA   C.C.A.F.</FONT></TD>
                          <TD width="38%"><P align=center>
                              <INPUT type=checkbox value=ALL 
                name=BONO1>
                            </P></TD>
                        </TR>
                        
                        -->
                      </TBODY>
                    </TABLE>
                  </div></TD>
              </TR>
              <TR>
                <TD width="100%"><BR>
                  <P align=center>
                    <INPUT class=boton onClick="return checkInput();" type=submit value=Aceptar name=aceptar onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >
                    <INPUT class=boton onclick=closeForm(); type=button value=Cancelar name=cancelar onMouseOver="this.className='botonOver'" onMouseOut="this.className='boton'" >
                  </P></TD>
              </TR>
              <TR>
                <TD width="90%"><!--
         	  <FONT color="#ff8000">Envío Informe de Ingresos de Trabajadores para Actualización de Tramos período julio 2010 a junio de 2011</FONT>
         	-->
                  
                  <P>&nbsp;</P>
                  <div id="mensaje">
                    <P><FONT class=redBackGris>&nbsp;&nbsp;&nbsp; A contar del 1° y 
                      hasta el 10 de Agosto puede enviarnos su archivo, para ello, por 
                      favor haga click </FONT>&nbsp;<A 
            href="javascript:subirArchivo()"><B>AQUÍ</B></A> </P>
                  </div>
                  <P>
                  <FONT class=blueText2 color=blue>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  <LI>En caso que su empresa tenga trabajadores que hayan efectuado 
                    reconocimiento de causantes por primera vez durante el mes de junio 
                    o julio 2010, debe agregar en la nómina a estos trabajadores.
                  
                  <LI>Si la empresa envía la información en formato Excel, debe ser 
                    versión 2003. </FONT>
                    <P></P>
                  </LI></TD>
              </TR>
            </TBODY>
          </TABLE></TD>
      </TR>
    </TBODY>
  </TABLE>
  <img src="/ea/edocs/images/footer.jpg" width="974" height="44">
</FORM>
</BODY>
</HTML>
