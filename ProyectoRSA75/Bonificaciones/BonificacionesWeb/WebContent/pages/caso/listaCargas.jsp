<%@ page language="java"%>
 
<%@ include file = "/includes/env.jsp" %>

<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<!-- <META name="links-collection-enabled" content="false"> -->
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file = "/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito">
        
        <%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt; 
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; Cargas</td>
      
    </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file = "/includes/menu.jspf"%>
          </td>
          <td width="77%" valign="top"><html:form action="/getListaCargas?source=caso">
             <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
              <tr>
                <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                      <td width="59%"><p class="derecha">Filtrar </p>
                      </td>
                      <td width="41%"><div align="center"><html:image page="/images/botones/boton_ir.gif" border="0"/></div>
                      </td>
                    </tr>
                  </table> 
                </td>
              </tr>
            </table>
            <table width="538" border="0" cellpadding="2" cellspacing="2">
              <tr>
                <td width="82" class="celdaColor1"><p class="vinculosUp">Rut
                    Socio</p>
                </td>
                <td width="133" class="celdaColor1"><p class="vinculosUp">Nombres
                    Socio</p>
                </td>
                <td width="80" class="celdaColor1"><p class="vinculosUp"> Rut Carga</p>
                </td>
                <td width="127" class="celdaColor1"><p class="vinculosUp">Nombres Carga</p>
                </td>
                <td width="64" class="celdaColor1"><p class="vinculosUp">Tipo de Carga</p></td>
              </tr>
             <tr>
                <td bgcolor="#F0F0F0" class="lookup01"><html:text property="rutSocio" styleClass="txtHomeSmall" size="13"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:text property="nombreSocio" styleClass="txtHomeSmall" size="24"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:text property="rutCarga" styleClass="txtHomeSmall" size="13"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:text property="nombreCarga" styleClass="txtHomeSmall" size="24"/></td>
                <td bgcolor="#F0F0F0" class="lookup01">
                <html:select property="tipoCarga" styleClass="menuDespl">
                	<html:option value="">Todos</html:option>
                	<html:option value="C">Conyuge</html:option>
                	<html:option value="H">Hijo</html:option>
                	<html:option value="N">Nieto</html:option>
                	<html:option value="B">Menor bajo medida de protección</html:option>
                	<html:option value="A">Ascendente</html:option>
                	<html:option value="M">Maternal</html:option>
                	<html:option value="P">Provisoria</html:option>
                </html:select>
                </td>
             </tr>
			<logic:iterate id="register" name="lista.cargas" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Carga">
              <tr>
                <td bgcolor="#F0F0F0" class="lookup01"><html:link page='<%="/setFichaSocio.do?source=socio&rut="+register.getFullRutSocio() %>'><bean:write name="register" property="fullRutSocio"/></html:link></td>
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="nombreSocio"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:link page='<%="/setFichaCaso.do?carga="+register.getRutCarga()%>' paramName="register" paramId="socio" paramProperty="rutSocio"><bean:write name="register" property="rutCarga"/>-<bean:write name="register" property="dvCarga"/></html:link></td>
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="nombreCarga"/> <bean:write name="register" property="apePatCarga"/> <bean:write name="register" property="apeMatCarga"/></td>
                <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="tipoCarga"/></td>
              </tr>
			
			</logic:iterate>
              <tr>
                <td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
                <td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
                <td bgcolor="#F0F0F0" class="lookup01"><html:link page="/setFichaCaso.do?carga=" >Sin Carga</html:link></td>
                <td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
                <td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
              </tr>
            </table>

            </html:form></td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
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
