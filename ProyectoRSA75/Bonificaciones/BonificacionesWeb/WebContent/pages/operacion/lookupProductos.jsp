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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <bean:message key="label.operacion.lookup.producto.lista"/> </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top">
 
 <%@ include file = "/includes/menu.jspf"%>
 
          </td>
          <td width="77%" valign="top">
  <br>

	<!-- FOrmulario de FIltro -->
	<html:form action="/prepareLookUpUpLoadFile">
	<html:hidden property="destino" value="productos"/>
            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
              <tr>
                <td align="center"><table width="25%" border="0" cellspacing="1" cellpadding="0">
                  <tr>
                    <td width="59%"><p class="derecha">Filtrar </p>
                    </td>
                    <td width="41%"><div align="center">
                    <html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
                    
                    </div></td>
                    
                  </tr>
                </table>                  
                </td>
              </tr>
            </table>
            <table width="529" border="0" cellspacing="2" cellpadding="2">
              <tr bgcolor="#999999">
                <td width="226"><p><a href="#" class="vinculosUp"><bean:message key="label.operacion.lookup.producto.codigo"/></a></p>
                </td>
                <td><p>
                  <a href="#" class="vinculosUp"><bean:message key="label.operacion.lookup.producto.nombre"/></a></p></td>
                <td bgcolor="#ffffff"><p>&nbsp;</p></td>
                </tr>
              <tr bgcolor="#F0F0F0" class="lookup01">
                <td>
                <html:text property="codigoProducto" styleClass="txtHomeSmall" size="10"/>
                </td>
                <td width="289">
	            <html:text property="nombreProducto" styleClass="txtHomeSmall" size="10"/>
				</td>

                </tr>

				<bean:define id="productos" name="convenio" property="productos"/>
				<!-- despliegue de los Convenios -->                
				<logic:iterate id="register" name="productos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Producto">
                
              <tr bgcolor="#F0F0F0" class="lookup01">
                <td height="19">
     	           <html:link page='<%="/prepareUpLoadFile.do?codigoProducto="+register.getCobertura().getCodigo() %>' paramId="codigoConvenio" paramName="convenio" paramProperty="codigo" target="_top">
	    	            <bean:write name="register" property="cobertura.codigo"/>
            	    </html:link>
                </td>
                <td>
                	<bean:write name="register" property="cobertura.descripcion"/> 
                </td>
                <td bgcolor="#ffffff"><p>&nbsp;</p></td>
                </tr>

				</logic:iterate>

            </table>

</html:form>

</td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
  </tr>
</table>

<%@ include file = "/includes/pie.jsp" %>

<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
