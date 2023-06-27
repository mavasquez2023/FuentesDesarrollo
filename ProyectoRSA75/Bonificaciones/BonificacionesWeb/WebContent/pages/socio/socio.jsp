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

<%@ include file = "/includes/arriba.jsp" %>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> 
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt; Socio</td>
      </tr>
    </table> 
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top">
          	<%@ include file = "/includes/menu.jspf"%>
          </td>
          <td valign="top">
          
 		<html:errors/>
         
         
          <%@ include file = "/includes/touch.jsp" %>

          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="346" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="133"><p><strong>Rut:</strong></p>
                    </td>
                    <td width="199"><p><logic:notEqual value="?" name="socio" property="digito"><bean:write name="socio" property="fullRut"/></logic:notEqual>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Sexo:</strong></p>
                    </td>
                    <td><p><logic:notEqual value="?" name="socio" property="sexo"><bean:write name="socio" property="sexo"/></logic:notEqual></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Nombre:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="nombre"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Apellido Paterno:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="apePat"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Apellido Materno:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="apeMat"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha Nacimiento:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="fecNac" formatKey="format.date"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Domicilio Particular:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="domicilioParticular"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Comuna:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="codComuna"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Ciudad:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="codCiudad"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fono Particular:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="fonoParticular"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fono Emergencia:</strong></p>
                    </td>
                    <td><p><bean:write name="socio" property="fonoEmergencia"/></p>
                    </td>
                  </tr>
                 
                  <tr>
                    <td><p><strong>Oficina</strong></p></td>
                    <td><p><bean:write name="socio" property="oficina"/></p></td>
                  </tr>
                  <tr>
                    <td><p><strong>Estado:</strong></p>
                    </td>
                    <td><p><logic:notEqual value="?" name="socio" property="digito"><bean:write name="socio" property="estado"/></logic:notEqual></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de Ingreso:</strong></p>
                    </td>
                    <td><p>
                    <logic:notEmpty name="textoFecha">	
                    	<html:text property="fechaIngreso" styleClass="txtHomeSmall"/>
					</logic:notEmpty>
                    <logic:empty name="textoFecha">	
                    	<bean:write name="socio" property="fecIng" formatKey="format.date"/>
					</logic:empty>				
                    </p>                    
                    </td>
                  </tr>
                </table>
              </td>
              <td width="27%" align="right" valign="top"><table width="172" border="0" cellspacing="3" cellpadding="1">
              <logic:notEmpty name="socio.botonera">
<% if (userinformation.hasAccess("socCargaFamiliar")) { %>
                  <tr>
                    <td width="184" align="right" class="opciones"><html:link page="/getListaCargasSocio.do" target="_top"><html:img page="/images/botones/socio/mis_cargas.gif" alt="Mis Cargas" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socVales")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaValesSocio.do" target="_top"><html:img page="/images/botones/socio/mis_vales.gif" alt="Mis Vales" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socBonos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaCasosBeneficiario.do?tipo=bono" target="_top"><html:img page="/images/botones/socio/mis_bonos.gif" alt="Mis Bonos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socPrestamos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaPrestamosSocio.do" target="_top"><html:img page="/images/botones/socio/mis_prestamos.gif" alt="Mis Pr&eacute;stamos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socDescuentos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaDescuentosSocio.do" target="_top"><html:img page="/images/botones/socio/mis_descuentos.gif" alt="Mis Descuentos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socReembolsos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaCasosBeneficiario.do?tipo=reembolso" target="_top"><html:img page="/images/botones/socio/mis_reembolsos.gif" alt="Mis Reembolsos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socCasos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getListaCasosBeneficiario.do?tipo=caso" target="_top"><html:img page="/images/botones/socio/mis_casos.gif" alt="Mis Casos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
<% if (userinformation.hasAccess("socResumenMovimientos")) { %>
                  <tr>
                    <td align="right" class="opciones"><html:link page="/getResumenMovimientoBeneficiario.do?source=socio" target="_top"><html:img page="/images/botones/socio/res_movimientos.gif" alt="Resumen Movimientos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
<% } %>
				</logic:notEmpty>
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
