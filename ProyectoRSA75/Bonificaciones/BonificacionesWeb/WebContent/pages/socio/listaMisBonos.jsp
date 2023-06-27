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
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt;
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; Mis Bonos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
			<%@ include file="/includes/headerSocio.jsp" %>
          <br>
          <table width="541" border="0" cellpadding="2" cellspacing="2">
            <tr class="celdaColor1">
              <td width="84"><p><a href="#" class="vinculosUp">Fecha Compra</a></p>
              </td>
              <td width="92"><p> <a href="#" class="vinculosUp">Monto del Bono</a></p>
              </td>
              <td width="82"><p><a href="#" class="vinculosUp">Aporte
                    Isapre</a></p>
              </td>
              <td width="98"><a href="#" class="vinculosUp">Aporte
                  Bienestar</a></td>
              <td width="87"><p><a href="#" class="vinculosUp">Aporte Socio</a></p>
              </td>
              <td width="60"><p><a href="#" class="vinculosUp">Caso ID</a></p></td>
            </tr>

		<logic:iterate id="register" name="lista.casos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Caso">

            <tr>
              <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaDeOcurrencia" formatKey="format.date"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="monto" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteIsapre" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0" class="lookup01"><p>$ <bean:write name="register" property="aporteBienestar" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p>$ <bean:write name="register" property="aporteSocio" formatKey="format.int"/></p>
              </td>
              <td bgcolor="#F0F0F0"><p><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID"><bean:write name="register" property="casoID"/></html:link></p></td>
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
