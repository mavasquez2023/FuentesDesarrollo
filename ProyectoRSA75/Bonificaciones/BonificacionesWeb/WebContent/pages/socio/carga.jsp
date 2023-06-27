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

<%@ include file="/includes/arriba.jsp"%>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> &gt; 
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt;
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; 
        <html:link page="/getListaCargas.do" target="_top">Lista de Cargas</html:link> &gt; Carga</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top">
          <%@ include file = "/includes/menu.jspf" %>
                    <p>&nbsp;</p></td>
          <td width="77%" valign="top">
			<%@ include file="/includes/headerSocio.jsp"%>
            <br>
            <table width="97%" border="0" cellpadding="2" cellspacing="2">
              <tr>
                <td width="65%" height="216" valign="top"><table width="340" border="0" cellspacing="2" cellpadding="2">
                <tr>
                	<!-- 
                	<html:form action="/actualizarCarga.do">
                    <html:select property="opcion" styleClass="menuDespl">
   			          	<html:options name="carga.opciones.valores" labelName="carga.opciones"/>
            	    </html:select> 
            	    <html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
  					</html:form>-->
                </tr>
                  <tr>
                    <td width="182"><p><strong>Rut:</strong></p>
                    </td>
                    <td width="144" bgcolor="#FFFFFF"><p><bean:write name="carga" property="fullRutCarga"/><html:link page="/getListaCargas.do" target="_top"><html:img page="/images/botones/boton_look_up.gif" alt="Cargas" width="14" height="14" border="0"/></html:link></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Estado:</strong></p> 
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="estadoCarga"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Tipo de Carga:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="tipoCarga"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Sexo:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="sexoCarga"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Carga N&deg;</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="numCarga"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Nombres:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="nombreCarga"/> <bean:write name="carga" property="apePatCarga"/> <bean:write name="carga" property="apeMatCarga"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Fecha Nacimiento:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="fecNacCarga" format="dd/MM/yyyy"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Fecha Activaci&oacute;n de la Carga:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="fecIngCarga" format="dd/MM/yyyy"/></p>
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Fecha Vencimiento Carga:</strong></p>
                    </td>
                    <td bgcolor="#FFFFFF"><p><bean:write name="carga" property="fecEgrCarga" format="dd/MM/yyyy"/></p>
                    </td>
                    </tr>
                </table>
                  </td>
                <td width="35%" align="right" valign="top"><table width="172" border="0" cellspacing="3" cellpadding="1">
<% if (userinformation.hasAccess("socCargaCasos")) { %>
                    <tr>
                      <td width="184" align="right" class="opciones"><html:link page="/getListaCasosBeneficiario.do?tipo=carga" target="_top"><html:img page="/images/botones/socio/mis_casos.gif" alt="Mis Casos" width="160" height="21" border="0"/></html:link></td>
                    </tr>
<% } %>
<% if (userinformation.hasAccess("socCargaResMovi")) { %>
                    <tr>
                      <td height="23" align="right" class="opciones"><html:link page="/getResumenMovimientoBeneficiario.do?source=carga" target="_top"><html:img page="/images/botones/socio/res_movimientos.gif" alt="Resumen Movimientos" width="160" height="21" border="0"/></html:link></td>
                    </tr>
<% } %>
                  </table>
                </td>
              </tr>
            </table>
            </td>
        </tr>
      </table>      
    </td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp"%>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
