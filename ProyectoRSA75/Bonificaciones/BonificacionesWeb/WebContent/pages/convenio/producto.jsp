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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; 
        <html:link page="/setFichaConvenio.do" target="_top">Convenio</html:link> &gt; 
        <html:link page="/getListaCoberturas.do?source=convenio" target="_top">Lista de Productos</html:link> &gt; Producto </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerConvenio2.jsp" %>
          <html:errors/>
          <html:form action="/opcionesProducto">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td><html:select property="opcion" styleClass="menuDespl">
              	<html:options name="producto.opciones.valores" labelName="producto.opciones"/>
              </html:select> 
              <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/> </td>
            </tr>
          </table>
         <bean:define id="cobertura" name="producto" property="cobertura"/>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="346" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td><p><strong>Producto:</strong></p>
                    </td>
                    <td><p>
                      <bean:write name="cobertura" property="descripcion"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td width="197"><p><strong>C&oacute;digo:
                      </strong></p>
                    </td>
                    <td width="135"><p><bean:write name="cobertura" property="codigo"/>
                    <logic:notEmpty name="producto.lookup"><html:link page="/getListaCoberturasLookUp.do?source=lookup"><html:img page="/images/botones/boton_look_up.gif" alt="Actual" width="14" height="14" border="0"/></html:link>
                    </logic:notEmpty>
                        </p>                      </td>
                  </tr>
                  <tr>
                    <td><p><strong>Concepto:</strong></p>
                    </td>
                    <td><p>
                      	<bean:write name="cobertura" property="conceptoDescripcion"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Tipo de Tope:</strong></p>
                    </td>
                    <td><p><bean:write name="cobertura" property="tipoTope"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Valor Tope:</strong></p>
                    </td>
                    <td><p>
                      <bean:write name="cobertura" property="tope" formatKey="format.int"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Valor Referencial:</strong></p>
                    </td>
                    <td><p>
                      <bean:write name="cobertura" property="valorReferencial" formatKey="format.int"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.producto.descuento"/> <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="descuento" styleClass="txtHomeSmall" size="4"/>%
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de Ingreso:</strong></p>
                    </td>
                    <td><p>
                      <bean:write name="producto" property="fechaIngreso" formatKey="format.date"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Periodo de Carencia (en meses):</strong></p>
                    </td>
                    <td><p>
                      <bean:write name="cobertura" property="periodoCarencia"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Cuenta de Gasto:</strong></p>
                    </td>
                    <td><p>
                    <logic:greaterThan name="producto" property="cuentaGasto" value="0">
                      <bean:write name="producto" property="cuentaGasto"/>
					</logic:greaterThan>
                    </p>
                    </td>
                  </tr>
                  <logic:greaterThan name="producto" property="cuentaGasto" value="0">
                  	<tr>
                  		<td>
                  			<p><strong>
                  				<bean:message key="label.producto.bonificacion.especial"/>:
	                  		</strong></p>
    	              	</td>
        	            <td>
            	        	<p>
	            	          <html:text property="porcentajeAporteConvenio" styleClass="txtHomeSmall" size="4"/>%
    	            	    </p>
	                    </td>
    	              </tr>
					</logic:greaterThan>    	              
                </table>
              </td>
              <td width="27%" align="right" valign="top">
                <table width="172" border="0" cellspacing="3" cellpadding="1">
				<logic:notEmpty name="producto.botonera">
<% if (userinformation.hasAccess("convProRango")) { %>
                    <tr>
                      <td align="right" class="opciones"><html:link page="/getListaRangos.do?source=convenio" target="_top"><html:img page="/images/botones/cobertura/mis_rangos.gif" alt="Mis Rangos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
                </logic:notEmpty>
                <tr>
                      <td align="right" class="opciones">&nbsp;</td>
                  </tr>
                    <tr>
                      <td align="right" class="opciones">&nbsp;</td>
                  </tr>
                  </table>
              </td></tr>
          </table>
          </html:form></td>
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
