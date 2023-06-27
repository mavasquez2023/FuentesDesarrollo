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
        <html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; Caso</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerCaso.jsp" %>
 
          <br>
          <table width="538" border="0" cellspacing="2" cellpadding="2">
            <tr>
              <td width="69" class="celdaColor1">
              	<a href="#" class="vinculosUp">Detalle</a>
              </td>
              <td width="74" class="celdaColor1">
              	<p><a href="#" class="vinculosUp">Valor Prestaci&oacute;n</a></p>
              </td>
              <td width="63" class="celdaColor1">
              	<p><a href="#" class="vinculosUp">Descuento</a></p>
              </td>
              <td width="65" class="celdaColor1">
              	<a href="#" class="vinculosUp">Total</a>
              </td>
              <td width="65" class="celdaColor1">
              	<p><a href="#" class="vinculosUp">Aporte Isapre</a></p>
              </td>
              <td width="71" class="celdaColor1">
              	<p><a href="#" class="vinculosUp">Aporte Bienestar</a></p>
              </td>
              <td width="64" class="celdaColor1">
              	<a href="#" class="vinculosUp">Aporte Socio</a>
              </td>
              <td width="64" class="celdaColor1">
              	<a href="#" class="vinculosUp">Aporte Convenio</a>
              </td>              
            </tr>
            
			<logic:iterate id="register" name="caso" property="detalleCaso" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.DetalleCaso">
				<bean:define name="register" property="producto" id="prod"/>      
				<bean:define name="prod" property="cobertura" id="cob"  type="cl.araucana.bienestar.bonificaciones.model.Cobertura"/>      
            <tr>
              <td bgcolor="#F0F0F0">
              	<p>
              		<bean:write name="cob" property="descripcion"/>
					<%if(cob.getTieneOcurrencias().equals("SI")) {%>
						 (<bean:write name="register" property="cantidadOcurencias"/>
						 <bean:write name="cob" property="etiquetaOcurrencia"/>
						)
					<%}%>              		
              	</p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="monto" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="montoDescuento" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="total" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteIsapre" formatKey="format.int"/></p></td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteBienestar" formatKey="format.int"/></p></td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteSocio" formatKey="format.int"/></p></td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteConvenio" formatKey="format.int"/></p></td>
            </tr>
            </logic:iterate>
            <tr>
              <td bgcolor="#CCCCCC"><p><strong>Total</strong></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="monto" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="montoDescuento" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="total" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="aporteIsapre" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="aporteBienestar" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="aporteSocio" formatKey="format.int"/></p></td>
              <td bgcolor="#CCCCCC"><p>$ <bean:write name="caso" property="aporteConvenio" formatKey="format.int"/></p></td>              
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
