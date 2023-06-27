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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Lista de Casos activos de socio inactivo</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
          <html:errors/>

          
          <html:form action="/getListaCasos">
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.caso.id"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.socio.rut"/></p></td>
                <td class="celdaColor1"><p class="vinculosUp">Tipo</p>
                </td>
	            <logic:notEqual name="grupoUsuario" value="5">
	                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.monto"/></p>
	                </td>
                </logic:notEqual>
                <td class="celdaColor1"><p class="vinculosUp">Ingreso Caso</p>
                </td>
                <td class="celdaColor1"><p class="vinculosUp">Concepto</p>
                </td>
                <td class="celdaColor1"><p class="vinculosUp">Fue Pre-Caso</p>
                </td>                
                <td width="19">&nbsp;</td>
              </tr>
            <!-- despliegue de los Casos -->
			<logic:notEmpty name="lista.casos"> 
      		<logic:iterate id="register" name="lista.casos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Caso">
              
              <tr class="lookup01">
                <td bgcolor="#F0F0F0"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
				                <td bgcolor="#F0F0F0"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio" target="_top"><bean:write name="register" property="rutSocio"/></html:link></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="tipoCaso"/></td>

                <logic:notEqual name="grupoUsuario" value="5">
                	<td bgcolor="#F0F0F0">$<bean:write name="register" property="monto" formatKey="format.int"/></td>
                </logic:notEqual>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></td>
				<td bgcolor="#F0F0F0"><bean:write name="register" property="descripcionConcepto"/></td>
				<td bgcolor="#F0F0F0"><bean:write name="register" property="indicadorPreCaso"/></td>				
              </tr>
            </logic:iterate>  
            </logic:notEmpty>
            <logic:empty name="lista.casos">
            <tr bgcolor="#ffffff" class="lookup01">
            <td height="19" colspan='6'>
            <h1 class="centrado">
            	<br><br>
            		<logic:present name="ejecutoConsulta">
	            		<bean:message key="label.noCoincidencias"/>
            		</logic:present>
					<logic:notPresent name="ejecutoConsulta">
		           		<bean:message key="label.noBusquedas"/>
					</logic:notPresent>
            </h1>
            </td>
            </tr>
			</logic:empty>
            </table>

          </html:form></td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
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
