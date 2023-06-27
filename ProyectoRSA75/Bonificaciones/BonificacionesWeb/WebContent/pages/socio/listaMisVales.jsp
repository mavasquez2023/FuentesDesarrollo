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
        <html:link page="/setFichaSocio.do" target="_top">Socio</html:link> &gt; Mis Vales</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <table width="99%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="97%" valign="top">

               <%@ include file="/includes/headerSocio.jsp"%>

                <br>
		       	<html:form action="/getTalonariosDisponibles">
		        	<html:select property="opcion" styleClass="menuDespl">
		           	  	<html:options name="opciones.valores" labelName="opciones"/>
		        	</html:select>
			       	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
				</html:form>
				<bean:define id="lista" name="socio" property="vale"/>
                <table width="533" border="0" cellspacing="2" cellpadding="2">
                  <tr class="celdaColor1">
                    <td width="72"><p class="vinculosUp">Vale</p>
                    </td>
                    <td width="176"><p class="vinculosUp">Nombre Convenio</p>
                    </td>
                    <td width="158"><p class="vinculosUp">Fecha Entrega</p></td>
                    <td width="100"><p class="vinculosUp">Caso ID</p>
                    </td>
                  </tr>
                  
    <!-- despliegue de Vales -->                
		<logic:iterate id="register" name="lista" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Vale">
                  <tr>
                    <td bgcolor="#F0F0F0"><p><html:link page="/setFichaVale.do" paramId="codigo" paramName="i" target="_top"><bean:write name="register" property="codigoVale"/></html:link></p>
                    </td>
                    <td bgcolor="#F0F0F0"><p><bean:write name="register" property="nombreConvenio"/></p>
                    </td>
                    <td bgcolor="#F0F0F0"><p><bean:write name="register" property="fechaEntrega" formatKey="format.date"/></p></td>
                    <td bgcolor="#F0F0F0"><p>
                    <logic:greaterThan value="0" name="register" property="caso_id">
                    <html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="caso_id"><bean:write name="register" property="caso_id"/></html:link>
                    </logic:greaterThan></p>
                    </td>
                  </tr>
		</logic:iterate>			 	

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
