<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Genera
          Asientos Contables</td>
      </tr> 
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">          
          <html:form action="/generarAsientosContables">
 	        <html:select property="opcion" styleClass="menuDespl">
              	<html:options name="opciones.valores" labelName="opciones"/>
	        </html:select>
	        <html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
			<br>
			<br>

              <table width="200" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td></td>
                <td width="150" class="celdaColor1"><p class="vinculosUp">Periodo</p>
                </td>
              </tr>
 
       		<logic:iterate id="register" name="lista" indexId="i">
 
              <tr>
              	<td><html:radio property="codigo" value='<%=String.valueOf(i)%>'/> </td>
                <td><p>
                <bean:write name="register" property="fechaDescuento" formatKey="format.date.periodo"/>
                </p>
                </td>
              </tr>
              
             </logic:iterate>
             
            </table>
            </html:form>
            </td>
            
        </tr>
      </table>      
    </td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
