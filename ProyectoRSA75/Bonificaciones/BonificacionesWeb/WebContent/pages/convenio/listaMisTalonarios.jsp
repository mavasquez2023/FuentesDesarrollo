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
        <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; 
        <html:link page="/setFichaConvenio.do" target="_top">Convenio</html:link> &gt; Lista Mis Talonarios</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">

			<!--Header de Convenio -->
			<%@ include file="/includes/headerConvenio.jsp" %>
			
            <br>
            <html:errors/>
            <table width="520%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><p>
                <html:form action="/setFichaTalonario">
	                <html:select property="opcion" styleClass="menuDespl">
	            	  	<html:options name="opciones.valores" labelName="opciones"/>	
        	        </html:select>
		        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
        	    </html:form>
                </td>
              </tr>
            </table>
            <table width="520" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="67%" valign="top"><table width="351" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="91" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.talonario.inicioSecuencia"/></p>
                    </td>
                    <td width="83" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.talonario.finSecuencia"/></p></td>
                    <td width="131" class="celdaColor1"><p class="vinculosUp">Vale Disponible</p>
                    </td>
                  </tr>
                  <html:form action="/getListaTalonarios">
            <logic:iterate id="register" name="lista.talonarios" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Talonario">
                  
                  <tr>
                    <td bgcolor="#F0F0F0" class="lookup01"><html:link page="/setFichaTalonario.do" paramId="codigo" paramName="i" target="_top"><bean:write name="register" property="inicioSecuencia"/></html:link></td>
                    <td bgcolor="#F0F0F0" class="lookup01"><bean:write name="register" property="finSecuencia"/></td>
                    <td bgcolor="#F0F0F0" class="lookup01"><p><bean:write name="register" property="valeDisponible"/></p>
                  </tr>
			</logic:iterate>
                  <tr>
                    <td bgcolor="#F0F0F0" class="lookup01"><html:text property="inicioSecuencia" styleClass="txtHomeSmall" size="8"/>
                    </td>
                    <td bgcolor="#F0F0F0" class="lookup01"><html:text property="finSecuencia" styleClass="txtHomeSmall" size="8"/>
                    </td>
                    <td bgcolor="#F0F0F0" class="lookup01">&nbsp;</td>
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
                  </table></td>
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
