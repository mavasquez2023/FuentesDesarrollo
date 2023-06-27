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
        <html:link page="/getListaCoberturas.do?source=convenio" target="_top">Lista de Productos</html:link> &gt; 
        <html:link page="/setFichaProducto.do" target="_top">Producto</html:link> &gt; Mis
            Rangos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top"><html:form action="/opcionesRangosProducto">

			<!-- Header Convenio -->
			<%@ include file="/includes/headerConvenio2.jsp" %>
			<!-- Header Cobertura -->
			<%@ include file="/includes/headerProducto.jsp" %>

          <br>
          <html:errors/>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="359" border="0" cellspacing="2" cellpadding="2">
	            <tr><td colspan="2">&nbsp;</td></tr>
                <tr>
					<td width="195"><p><strong><bean:message key="label.rango.cuentaGasto"/>:
                      </strong></p>
                    </td> 
	              <td>
		<bean:define id="cobertura2" name="producto" property="cobertura" type="cl.araucana.bienestar.bonificaciones.model.Cobertura"/>
	              	<logic:iterate id="cuenta" name="rangos.cuenta.gasto" type="cl.araucana.bienestar.bonificaciones.model.Parametro">
					<% if (Long.parseLong(cuenta.getCodigo()) == cobertura2.getCuentaGasto()) { %>
						<p><bean:write name="cuenta" property="descripcion" /></p>
					<% } %>
	              </logic:iterate>
	              </td>
	            </tr>
	            <tr><td colspan="2"><hr></td></tr> 
                </table>
              </td>
              </tr>
          </table>
          <table width="529" border="0" cellspacing="2" cellpadding="2">
             <tr>
              <td width="170" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.inicio"/></p></td>
              <td width="161" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.fin"/></p>
              </td>
              <td width="94" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.rango.porcentajeCobertura"/></p>
              </td>
              <td width="16">&nbsp;</td>
            </tr>
            <logic:iterate id="register" name="lista.rangos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Rango">
            <tr>
              <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="rangoInicio" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="rangoFin" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p><bean:write name="register" property="rangoPorcentajeCobertura" formatKey="format.int"/>%</p>
              </td>
            </tr>
            </logic:iterate>
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
