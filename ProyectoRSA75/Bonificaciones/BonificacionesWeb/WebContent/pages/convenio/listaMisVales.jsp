<%@ page language="java"%>


<%@ include file = "/includes/env.jsp" %>
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css"%>' rel="stylesheet" type="text/css">
</head>
 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
     <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; 
        <html:link page="/setFichaConvenio.do" target="_top">Convenio</html:link> &gt; Lista Mis Vales </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
			<%@ include file="/includes/headerConvenio.jsp"%>
            <br>
            <table width="534" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="65" class="celdaColor1"><p class="vinculosUp">Vale</p></td>
                <td width="92" class="celdaColor1"><p> <a href="#" class="vinculosUp">Fecha
                      Entrega</a></p>
                </td>
                <td width="87" class="celdaColor1"><a href="#" class="vinculosUp">Fecha
                    Recibo</a></td>
                <td width="66" class="celdaColor1"><p><a href="#" class="vinculosUp">Monto</a></p></td>
                <td width="71" class="celdaColor1"><p class="vinculosUp">Caso ID</p></td>
                <td width="71" class="celdaColor1"><p class="vinculosUp">RUT Socio</p></td>
              </tr>

			<logic:iterate id="register" name="lista.vales" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Vale">
			    <tr class="lookup01">
                <td bgcolor="#F0F0F0"><p><html:link page="/setFichaVale.do" paramId="codigoVale" paramName="register" paramProperty="codigoVale"><bean:write name="register" property="codigoVale"/></html:link></p></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaEntrega" formatKey="format.date"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaRecepcion" formatKey="format.date"/></td>
                <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="monto" formatKey="format.int"/></p></td>
                <td bgcolor="#F0F0F0"><p>
                <logic:greaterThan value="0" name="register" property="caso_id">
                	<html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="caso_id"><bean:write name="register" property="caso_id"/></html:link>
                </logic:greaterThan>
                </p></td>
                <td bgcolor="#F0F0F0"><p><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio"><bean:write name="register" property="fullRut"/></html:link></p></td>
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
