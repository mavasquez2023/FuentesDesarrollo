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
        <html:link page="/getListaSocios.do" target="_top">Lista de Socios</html:link> &gt;
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; <html:link page="/getListaValesSocio.do" target="_top">Mis Vales</html:link> &gt; Vale</td>
      </tr>
    </table> 
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="97%" valign="top">

			<logic:notEmpty name="vale.lista.opciones">
            	<%@ include file="/includes/headerSocio.jsp" %>
            </logic:notEmpty>
                <br>
				<table width="97%" border="0" cellspacing="1" cellpadding="1">
                  <tr>
                    <td>
                    <logic:notEmpty name="vale.lista.opciones">
                    <html:form action="/opcionesVale">
                    <html:select property="opcion" styleClass="menuDespl">
        		      	<html:options name="vale.opciones.valores" labelName="vale.opciones"/>
                    </html:select>
                      <html:image page="/images/botones/boton_ir.gif" alt="Efectura Opci&oacute;n" border="0"/> 
                     </html:form>
                     </logic:notEmpty>
                    </td>
						
                  </tr>
                </table>
                <table width="516" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="148"><p><strong>Vale:</strong></p>
                    </td>
                    <td width="354"><p><bean:write name="vale" property="codigoVale"/><html:link page="/getTalonariosDisponibles.do"><html:img page="/images/botones/boton_look_up.gif" alt="Lista Talonarios" width="14" height="14" border="0"/></html:link> </p>                      </td>
                  </tr>
                  <tr>
                    <td><p><strong>Convenio:</strong></p>
                     </td>
                    <td><p><bean:write name="vale" property="nombreConvenio"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>C&oacute;digo del Convenio:</strong></p>
                    </td>
                    <td><p><bean:write name="vale" property="codigoConvenio"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de entrega:</strong></p>
                    </td>
                    <td><p>
                     <bean:write name="vale" property="fechaEntrega" formatKey="format.date"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de Recepci&oacute;n:</strong></p>
                    </td>
                    <td><p><bean:write name="vale" property="fechaRecepcion" formatKey="format.date"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Monto:</strong></p>
                    </td>
                    <td><p>$ <bean:write name="vale" property="monto" formatKey="format.int"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Caso ID:</strong></p>
                    </td>
                    <td><p>
                    <logic:greaterThan value="0" name="vale" property="caso_id">
                    	<html:link page="/setFichaCaso.do" paramId="codigo" paramName="vale" paramProperty="caso_id" target="_top"><bean:write name="vale" property="caso_id"/></html:link></p>
					</logic:greaterThan>
                    </td>
                  </tr>
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
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
