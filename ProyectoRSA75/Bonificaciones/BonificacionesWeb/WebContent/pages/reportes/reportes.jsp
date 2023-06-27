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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Reportes </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <table width="481" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td valign="top"><p>&nbsp;</p>
              </td>
              <td valign="top"><p>&nbsp;</p>
              </td>
              <td align="right" valign="top"><p>&nbsp;</p>
              </td>
            </tr>
            <tr>
              <td width="17%" valign="top">&nbsp;</td>
              <td width="71%" valign="top">
                <table width="221" border="0" cellspacing="2" cellpadding="2">
<% if (userinformation.hasAccess("repSituacionSocio")) { %>
                  <tr>
                    <td><html:link page="/getReporteSituacionSocio.do" target="_top"><html:img page="/images/botones/informes/situacion_socio.gif" alt="Situaci&oacute;n Socio" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repReembolsos")) { %>
                  <tr>
                    <td><html:link page="/getListaReembolsosTotales.do" target="_top"><html:img page="/images/botones/operacion/reembolsos.gif" alt="Reembolsos Totales" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repDescuentos")) { %>
                  <tr>
                    <td><html:link page="/getListaDescuentosTotales.do" target="_top"><html:img page="/images/botones/informes/descuentos_socios.gif" alt="Descuentos Totales" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repPagoConvenios")) { %>
                  <tr>
                    <td><html:link page="/getListaPagosTotales.do" target="_top"><html:img page="/images/botones/informes/pago_convenios.gif" alt="Pagos Totales" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repDsctoConvenios")) { %>
                  <tr>
                    <td><html:link page="/getListaDescuentosConvenios.do" target="_top"><html:img page="/images/botones/informes/descuentos_convenios.gif" alt="Descuentos Convenios" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repAportesBienestar")) { %>
                  <tr>
                    <td><html:link page="/getListaAportesBienestar.do" target="_top"><html:img page="/images/botones/informes/resumen_movimientos.gif" alt="Aportes Bienestar" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("repDsctoSocio") || grupoUsuario == 5) { %>
                  <tr>
                    <td><html:link page="/getReporteDsctoSocio.do" target="_top"><html:img page="/images/botones/socio/mis_descuentos.gif" alt="Descuentos Socio" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<%}%>
<% if (userinformation.hasAccess("socReembolsos") && (grupoUsuario == 5 || grupoUsuario == 6)) {%>                
                  <tr>
                    <td><html:link page="/getListaCasosBeneficiario.do?tipo=reembolso" target="_top"><html:img page="/images/botones/socio/mis_reembolsos.gif" alt="Reembolsos Socio" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>

                </table>
              </td>
              <td width="12%" align="right" valign="top">&nbsp; </td>
            </tr>
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
