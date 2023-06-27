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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Reembolsos Totales </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
			<html:form action="/setFichaConcepto">
 	        	<html:select property="opcion" styleClass="menuDespl">
            	  	<html:options name="concepto.opciones.valores" labelName="concepto.opciones"/>
	        	</html:select>
	        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
			</html:form>
             <table width="532" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="81" class="celdaColor1"><p><a href="#" class="vinculosUp">C&oacute;digo</a></p>
                </td>
                <td width="252" class="celdaColor1"><p>
                    <a href="#" class="vinculosUp">Descripci&oacute;n</a></p>
                </td>
                <td width="157" class="celdaColor1"><p><a href="#" class="vinculosUp">Fecha
                      de Creaci&oacute;n</a></p></td>
              </tr>
 
       		<logic:iterate id="register" name="lista.conceptos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Concepto">
 
              <tr>
                <td bgcolor="#F0F0F0"><p>
                <html:link page="/setFichaConcepto.do" paramId="codigo" paramName="register" paramProperty="codigo" target="_top"><bean:write name="register" property="codigo"/></html:link>
                </p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="descripcion"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaCreacion" formatKey="format.date"/></p></td>
              </tr>
              
             </logic:iterate>
             
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
