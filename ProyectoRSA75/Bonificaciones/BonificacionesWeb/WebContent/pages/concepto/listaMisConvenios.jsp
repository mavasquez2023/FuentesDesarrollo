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
        <html:link page="/getListaConceptos.do" target="_top">Lista de Conceptos
        </html:link> &gt; <html:link page="/setFichaConcepto.do" target="_top">Concepto</html:link> &gt; Mis Convenios </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr> 
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
          
          
          <!-- Header de Concepto -->
          <%@ include file="/includes/headerConcepto.jsp" %>
          
          <html:errors/>
          <html:form action="/getListaConveniosConcepto">
          <html:hidden property="concepto"/>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="95" class="celdaColor1"><a href="#" class="vinculosUp">C&oacute;digo</a></td>
                <td width="128" class="celdaColor1"><p> <a href="#" class="vinculosUp">Convenio</a></p>
                </td>
                <td width="115" class="celdaColor1"><a href="#" class="vinculosUp">Concepto</a></td>
                <td width="54" class="celdaColor1"><a href="#" class="vinculosUp">Estado</a></td>
                <td width="82" class="celdaColor1"><a href="#" class="vinculosUp">Rut</a></td>
              </tr>

       		<logic:iterate id="register" name="lista.convenios" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Convenio">

              <tr class="lookup01">
                <td bgcolor="#F0F0F0"><html:link page="/setFichaConvenio.do" paramId="convenio" paramName="register" paramProperty="codigo" target="_top"><bean:write name="register" property="codigo"/></html:link></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="nombre"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="descripcionConcepto"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="estado"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="rut"/>-<bean:write name="register" property="dvRut"/></td>
           </tr>
			</logic:iterate> 
              <tr class="lookup01">
                <td bgcolor="#F0F0F0"><html:text property="codigo" styleClass="txtHomeSmall" size="12"/></td>
                <td bgcolor="#F0F0F0"><html:text property="nombre" styleClass="txtHomeSmall" size="24"/></td>
                <td bgcolor="#F0F0F0">
				</td>
                <td bgcolor="#F0F0F0">
                <html:select property="estado" styleClass="menuDespl" >
                   	<html:option value="">Todos</html:option>
					<html:option value="BORRADOR">BORRADOR</html:option>
					<html:option value="ACTIVO">ACTIVO</html:option>
					<html:option value="ELIMINADO">ELIMINADO</html:option>					
                </html:select>
                </td>
                <td bgcolor="#F0F0F0"><html:text property="rut" styleClass="txtHomeSmall" size="12"/></td>
              </tr>
            </table>
 
            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
              <tr>
                <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                    <tr>
                      <td width="59%"><p class="derecha">Filtrar </p>
                      </td>
                      <td width="41%"><div align="center"><html:image page="/images/botones/boton_ir.gif" alt="Filtrar" border="0"/></div>
                      </td>
                    </tr>
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
