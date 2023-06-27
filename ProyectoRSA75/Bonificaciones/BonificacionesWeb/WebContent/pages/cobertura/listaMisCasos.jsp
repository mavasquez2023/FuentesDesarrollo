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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/getListaCoberturas.do" target="_top">Lista
          de Coberturas</html:link> &gt; <html:link page="/setFichaCobertura.do" target="_top">Cobertura
          </html:link> &gt; Mis
          Casos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
			<%@ include file="/includes/headerCobertura.jsp" %>
            <br>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr class="celdaColor1">
                <td width="50"><p><a href="#" class="vinculosUp">Caso ID</a></p>
                </td>
                <td width="50"><p><a href="#" class="vinculosUp">Nombre Socio</a></p>
                </td>
                <td width="50"><p><a href="#" class="vinculosUp">RUT Socio</a></p>
                </td>
                <td width="50"><p>
                    <a href="#" class="vinculosUp">Tipo de Caso</a></p>
                </td>
                <td width="50"><p><a href="#" class="vinculosUp">Fecha Ingreso</a></p>
                </td>
                <td width="50"><a href="#" class="vinculosUp">Monto Sevicio</a></td>
                </tr>
		<logic:iterate id="register" name="lista.casos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Caso">

              <tr>
                <td bgcolor="#F0F0F0"><p><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="nombreSocio"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio"><bean:write name="register" property="rutSocio"/>-<bean:write name="register" property="dvRutSocio"/></html:link></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="tipoCaso"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></p></td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="monto" formatKey="format.int"/></p></td>
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
