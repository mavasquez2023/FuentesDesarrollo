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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; 
        <html:link page="/setFichaCaso.do" target="_top">Caso</html:link> &gt; Mis
          Descuentos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"> <%@ include file="/includes/menu.jspf" %>
</td>
          <td width="77%" valign="top">
 
 			<%@ include file="/includes/headerCaso.jsp" %> 
 			<logic:notEmpty name="socio">
            	<%@ include file="/includes/headerSocio2.jsp" %>
			</logic:notEmpty>
            <br>
            <table width="538" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="127" class="celdaColor1"><a href="#" class="vinculosUp">Cuota</a></td>
                <td width="127" class="celdaColor1"><p><a href="#" class="vinculosUp">Monto</a></p>
                </td>
                <td width="179" class="celdaColor1"><p>
                    <a href="#" class="vinculosUp">Fecha Descuento</a></p>
                </td>
                <td width="103" class="celdaColor1"><a href="#" class="vinculosUp">Estado</a></td>
                </tr>

			<logic:iterate id="register" name="caso" property="cuota" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Cuota">

              <tr>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="cuotaNumero"/></p></td>
                <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="valorCuota" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaVencimiento" formatKey="format.date"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="cuotaEstado"/></p>
                </td>
                </tr>
			</logic:iterate>
 

           </table>

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
