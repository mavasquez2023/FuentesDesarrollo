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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; Convenio</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <html:errors/>
          <html:form action="/opcionesConvenio">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td>
              <logic:notEmpty name="convenio.opciones">
	              <html:select property="opcion" styleClass="menuDespl">
	              	<html:options name="convenio.opciones.valores" labelName="convenio.opciones"/>
	              </html:select> 
	              <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/>
	  		  </logic:notEmpty>
			</td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="346" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td><p><strong>Convenio <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p><html:text property="nombre" styleClass="txtHomeSmall" size="50"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td width="133"><p><strong>C&oacute;digo:</strong></p>
                    </td>
                    <td width="199"><p><bean:write name="convenio" property="codigo" />
                        </p>                      </td>
                  </tr>
                  <tr>
                    <td><p><strong>Concepto:</strong></p>
                    </td>
                    <td><p>
	                    <html:select property="concepto" styleClass="menuDespl">
			      			<logic:iterate id="concept" name="lista.opciones.conceptos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Concepto">
								<html:option value="<%=String.valueOf(concept.getCodigo())%>"><bean:write name="concept" property="descripcion"/></html:option>
							</logic:iterate>
						</html:select>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Estado:</strong></p>
                    </td>
                    <td><p><bean:write name="convenio" property="estado" /></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Rut <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p><html:text property="rut" styleClass="txtHomeSmall" size="10" maxlength="8"/>-<html:text property="dv" styleClass="txtHomeSmall" size="2" maxlength="1"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>N&deg; Cuotas Externas <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p><html:text property="numCuotasExternas" styleClass="txtHomeSmall" size="2"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de Inicio :</strong></p>
                    </td>
                    <td><p>
                    <logic:notEmpty name="convenio" property="fecInicio">
                    <bean:write name="convenio" property="fecInicio" formatKey="format.date"/>
					</logic:notEmpty>
					&nbsp;
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de T&eacute;rmino :</strong></p>
                    </td>
                    <td><p>
					<logic:notEmpty name="convenio" property="fecFin">
                    <bean:write name="convenio" property="fecFin" formatKey="format.date"/>
					</logic:notEmpty>
					&nbsp;
                    </p>
                    </td>
                  </tr>
                </table>
              </td>
              <td width="27%" align="right" valign="top">
              <logic:notEmpty name="convenio.botonera">
                <table width="172" border="0" cellspacing="3" cellpadding="1">
 <% if (userinformation.hasAccess("convCasos")) { %>
                    <tr>
                      <td width="184" align="right" class="opciones"><html:link page="/getListaCasosConvenio.do" target="_top"><html:img page="/images/botones/convenios/mis_casos.gif" alt="Mis Casos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("convTalonarios")) { %>
                    <tr>
                      <td align="right" class="opciones"><html:link page="/getListaTalonarios.do" target="_top"><html:img page="/images/botones/convenios/mis_talonarios.gif" alt="Mis Talonarios" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("convProducto")) { %>
                    <tr>
                      <td align="right" class="opciones"><html:link page="/getListaCoberturasConvenio.do?source=convenio" target="_top"><html:img page="/images/botones/convenios/mis_coberturas.gif" alt="Mis Coberturas" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("convResumenMovimientos")) { %>
                    <tr>
                      <td align="right" class="opciones"><html:link page="/getMovimientosConvenio.do" target="_top"><html:img page="/images/botones/convenios/resumen_movimientos.gif" alt="Resumen Movimientos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("convVales")) { %>
                  <tr>
                      <td align="right" class="opciones"><html:link page="/getListaMisVales.do" target="_top"><html:img page="/images/botones/convenios/mis_vales.gif" alt="Mis Vales" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
                    <tr>
                      <td align="right" class="opciones">&nbsp;</td>
                  </tr>
                  </table>
                  </logic:notEmpty>
              </td></tr>
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
