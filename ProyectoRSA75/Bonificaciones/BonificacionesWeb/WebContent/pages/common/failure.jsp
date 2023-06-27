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
<%@ include file="/includes/arriba.jsp"%>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Operaci&oacute;n no realizada</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top"><h1>&nbsp;</h1>
            <h1 class="centrado">La Operación no ha podido ser realizada<br>

<br>

<font size='2' color='red'>

<%   String code=(String)request.getAttribute("error.code"); 
     if (code!=null) { %>
     
     <bean:message key='<%= code %>'/>
     
<%   } else {
        String umsg=(String)request.getAttribute("error.usermessage");
        if (umsg!=null) { %>

     <bean:write name="error.usermessage"/>

<%      } else {  %>

     <bean:message key='errors.system'/>

<%      }
     }  %>

</font>

<br>
<% String info=(String)request.getAttribute("error.info"); %>
<font size='1' color='black'><%= info!=null ? info : "" %></font>

</h1>

			</td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
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
