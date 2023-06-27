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
          Eventos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"> <%@ include file="/includes/menu.jspf"%>
</td>
          <td width="77%" valign="top">

 			<%@ include file="/includes/headerCaso.jsp" %> 
	  	  <logic:notEmpty name="socio">
            <%@ include file="/includes/headerSocio2.jsp" %>
		  </logic:notEmpty>	
            <br>
            <html:form action="/setFichaEvento">
                <html:select property="opcion" styleClass="menuDespl">
            	  	<html:options name="opciones.valores" labelName="opciones"/>	
    	        </html:select>
	        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
    	    </html:form>
        
            <table width="528" border="0" cellpadding="2" cellspacing="2">
              <tr>
                <td width="179" class="celdaColor1"><p class="vinculosUp">Anotaci&oacute;n</p>
                </td>
                <td width="46" class="celdaColor1"><p class="vinculosUp">
                    Tipo</p>
                </td>
                <td width="99" class="celdaColor1"><p class="vinculosUp">Fecha</p></td>
                <td width="155" class="celdaColor1"><p class="vinculosUp">Usuario</p></td>
              </tr>

			<logic:iterate id="register" name="caso" property="evento" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Evento">
              <tr>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="comentario"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="tipo"/></p></td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="usuario"/></p></td>
              </tr>
			</logic:iterate>

            </table>
            <p>&nbsp;</p>
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
