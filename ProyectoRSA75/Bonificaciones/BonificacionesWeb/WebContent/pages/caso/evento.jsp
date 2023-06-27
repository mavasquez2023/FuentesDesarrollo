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
        <html:link page="/setFichaCaso.do" target="_top">Caso</html:link> &gt; 
        <html:link page="/getListaEventos.do" target="_top">Mis Eventos</html:link> &gt; Evento</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerCaso.jsp" %>
          <br>
          <html:form action="/registraEvento">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td><html:select property="opcion" styleClass="menuDespl">
              	<html:options name="evento.opciones.valores" labelName="evento.opciones"/>
              </html:select> <html:image page="/images/botones/boton_ir.gif" alt="Confirmar Opci&oacute;n" border="0"/> </td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="515" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td><p><strong><bean:message key="label.evento.anotacion"/> <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                        <html:textarea property="comentario" styleClass="txtHomeSmall" cols='50' rows='5'></html:textarea>
                    </p>
                    </td>
                    </tr>
                  <tr>
                    <td width="206"><p><strong>Estado del Caso:</strong></p>
                    </td>
                    <td width="295"><p><bean:write name="evento" property="tipo"/>  </p>                      </td>
                    </tr>
                  <tr>
                    <td><p><strong>Fecha de Ingreso de la Anotaci&oacute;n:</strong></p></td>
                    <td><p><bean:write name="evento" property="fechaIngreso" formatKey="format.date"/></p></td>
                    </tr>
                  <!-- <tr>
                    <td><p><strong>Usuario que ingres&oacute; la Anotaci&oacute;n:</strong></p></td>
                    <td><p><bean:write name="evento" property="usuario"/></p></td>
                    </tr> -->
                    
                </table>
              </td>
              </tr>
          </table>
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
