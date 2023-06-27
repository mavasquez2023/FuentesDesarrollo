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
        <html:link page="/setFichaCaso.do" target="_top">Caso</html:link> &gt; Impuesto</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"> <%@ include file="/includes/menu.jspf" %>
		</td>
          <td width="77%" valign="top">
			<%@ include file="/includes/headerSocio.jsp" %>
            <br>
            <html:errors/>
          <html:form action="/calculaImpuesto">
            <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <tr>
                <td>
                <html:select property="opcion" styleClass="menuDespl">
                   	<html:options name="opciones.valores" labelName="opciones"/>
                </html:select>
                  <html:image page="/images/botones/boton_ir.gif" border="0"/></td>
              </tr>
            </table>
            <table width="346" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="140"><p><strong>Cr&eacute;dito Girado:</strong></p>
                </td>
                <td width="192"><p>
                  <html:text property="creditoGirado" styleClass="txtHomeSmall" size="8"/>
                  </p>
                </td>
              </tr>
              <tr>
                <td><p><strong>N&uacute;mero de Cuotas:</strong></p>
                </td>
                <td><p>
                  <html:text property="numeroCuotas" styleClass="txtHomeSmall" size="3"/>
                  </p>
                </td>
              </tr>
              
              <tr>
                <td><p><strong>N&uacute;mero de Cuotas LT:</strong></p>
                </td>
                <td>
                	<logic:notEmpty name="impuesto">
                	<p><bean:write name="impuesto" property="cuotasLT"/></p>
					</logic:notEmpty>
                </td>
              </tr>
              <tr>
                <td><p><strong>Impuesto:</strong></p></td>
                <td><p>
                	<logic:notEmpty name="impuesto">
                $ <bean:write name="impuesto" property="impuesto"/>
					</logic:notEmpty>
					</p></td>
              </tr>
              <tr>
                <td><p><strong>Capital Cr&eacute;dito:</strong></p></td>
                <td><p>
                	<logic:notEmpty name="impuesto">
                $ <bean:write name="impuesto" property="capitalCredito"/>
					</logic:notEmpty>
					</p></td>
              </tr>
            </table>
            <p>&nbsp;</p>
          </html:form></td>
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
