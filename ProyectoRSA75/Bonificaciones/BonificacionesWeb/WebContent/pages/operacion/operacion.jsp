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
<% String bandera = request.getParameter("bandera");
if(bandera.equals("true")){
%>
<script>
alert("Debe calcular las bonificaciones de todos los descuentos \n antes de poder ejecutar esta acción");
</script>
<%}%>
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Operaciones</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <table width="481" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td valign="top"><p>&nbsp;</p></td>
              <td valign="top"><p>&nbsp;</p></td>
              <td align="right" valign="top"><p>&nbsp;</p></td>
            </tr>
            <tr>
              <td width="17%" valign="top">&nbsp;</td>
              <td width="71%" valign="top">
                <table width="221" border="0" cellspacing="2" cellpadding="2">
<% if (userinformation.hasAccess("opeConcepto")) { %>
                  <tr>
                    <td><html:link page="/getListaConceptos.do" target="_top"><html:img page="/images/botones/operacion/conceptos.gif" alt="Conceptos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opeUpLoadFile")) { %>
                  <tr>
                    <td><html:link page="/prepareUpLoadFile.do" target="_top"><html:img page="/images/botones/operacion/upLoadFile.gif" alt="Cargar Archivos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opeBonifica")) { %>
                  <tr>
                    <td><html:link page="/getListaCasosAbiertos.do" target="_top"><html:img page="/images/botones/operacion/calcular_bonificacion.gif" alt="Calcular Bonificaci&oacute;n" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opeReembolsa")) { %>
                  <tr>
                    <td><html:link page="/getListaCasosPorReembolsar.do" target="_top"><html:img page="/images/botones/operacion/reembolsos.gif" alt="Reembolsos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opeDescuenta")) { %>
                  <tr>
                    <td><html:link page="/getListaCasosPorDescontar.do" target="_top"><html:img page="/images/botones/operacion/descuentos.gif" alt="Descuentos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opeContabiliza")) { %>
                  <tr>
                    <td><html:link page="/setFichaAsientosContables.do" target="_top"><html:img page="/images/botones/operacion/contabilidad.gif" alt="Contabilidad" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("opePagaConvenio")) { %>
                  <tr>
                    <td><html:link page="/setFichaPagoConvenios.do" target="_top"><html:img page="/images/botones/operacion/pagar_convenios.gif" alt="Pagar Convenios" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
                </table>                
              </td>
              <td width="12%" align="right" valign="top">&nbsp;                </td>
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
