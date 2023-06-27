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
        <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; 
        <html:link page="/setFichaConvenio.do" target="_top">Convenio</html:link> &gt; 
        <html:link page="/getListaTalonarios.do" target="_top">Lista Mis Talonarios</html:link> &gt; Talonario &gt;</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top"><html:form action="/opcionesTalonario">

			<!-- Header Convenio-->
			<%@ include file="/includes/headerConvenio.jsp" %>

            <br>
            <html:errors/>
            <table width="97%" border="0" cellspacing="1" cellpadding="1">
              <tr>
                <td><html:select property="opcion" styleClass="menuDespl">
	              	<html:options name="talonario.opciones.valores" labelName="talonario.opciones"/>
                </html:select>
                  <html:image page="/images/botones/boton_ir.gif" border="0"/></td>
              </tr>
            </table>
            <table width="344" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="129"><p><strong>Ingreso Talonario:</strong></p>
                </td>
                <td width="201"><p><bean:write name="talonario" property="fechaIngreso" formatKey="format.date"/></p>
                </td>
              </tr>
              <tr>
                <td><p><strong><bean:message key="label.talonario.inicioSecuencia"/> <bean:message key="label.obligatorio"/>:</strong></p>
                </td>
                <td><html:text property="inicioSecuencia" styleClass="txtHomeSmall" size="18"/>
</td>
              </tr>
              <tr>
                <td><p><strong><bean:message key="label.talonario.finSecuencia"/> <bean:message key="label.obligatorio"/>:</strong></p>
                </td>
                <td><p>
                  <html:text property="finSecuencia" styleClass="txtHomeSmall" size="18"/>
                </p>
                </td>
              </tr>
              <tr>
                <td><p><strong>Vale Disponible <bean:message key="label.obligatorio"/>:</strong></p>
                </td>
                <td><p><bean:write name="talonario" property="valeDisponible"/>
                </p></td>
              </tr>
            </table>
            <p>&nbsp;</p>
          </html:form></td>
          <td width="27%" align="right" valign="top">
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
