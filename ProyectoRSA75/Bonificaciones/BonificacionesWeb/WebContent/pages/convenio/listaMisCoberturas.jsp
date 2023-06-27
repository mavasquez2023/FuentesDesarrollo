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
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaConvenios.do" target="_top">Lista de Convenios</html:link> &gt; 
        <html:link page="/setFichaConvenio.do" target="_top">Convenio</html:link> &gt; Lista Mis Coberturas</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
           
           <%@ include file="/includes/headerConvenio2.jsp" %>

            <br>
            <html:errors/>
            <table width="520%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td><p>
                <html:form action="/getListaCoberturasLookUp.do?source=lookup">
	                <html:select property="opcion" styleClass="menuDespl">
	            	  	<html:options name="opciones.valores" labelName="opciones"/>	
        	        </html:select>
		        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
        	    </html:form>
                </td>
              </tr>
            </table>
            <table border="0" cellspacing="2" cellpadding="2">
            <html:form action="/getListaCoberturasConvenio?source=convenio">
              <tr>
                <td width="56" class="celdaColor1"><a href="#" class="vinculosUp">C&oacute;digo
                    Producto</a></td>
                <td width="108" class="celdaColor1"><p><a href="#" class="vinculosUp">Producto</a></p></td>
                <td width="81" class="celdaColor1"><p> <a href="#" class="vinculosUp">Concepto</a></p>
                </td>
                <td width="63" class="celdaColor1"><a href="#" class="vinculosUp">Tipo
                    de Tope</a></td>
                <td class="celdaColor1" width="80"><p><a href="#" class="vinculosUp">Valor
                      Tope</a></p></td>
                <td class="celdaColor1" width="42"><p><a href="#" class="vinculosUp">%
                  Desc.</a></p></td>
                <td width="68" class="celdaColor1"><p><a href="#" class="vinculosUp">Valor Ref.</a></p></td>
              </tr>

       		<logic:iterate id="lista" name="lista.coberturas" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Producto">
			  <bean:define id="register" name="lista" property="cobertura"/>
              <tr class="lookup01">
                <td bgcolor="#F0F0F0"><html:link page="/setFichaProducto.do" paramId="codigo" paramName="i" target="_top"><bean:write name="register" property="codigo"/></html:link></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="descripcion"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="conceptoDescripcion"/></td>
                <td bgcolor="#F0F0F0"><bean:write name="register" property="tipoTope"/></td>
                <td bgcolor="#F0F0F0" width="80"><p>$<bean:write name="register" property="tope" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0" width="42"><p><bean:write name="lista" property="descuento" formatKey="format.int"/></p>
                </td>
                <td bgcolor="#F0F0F0"><p>$<bean:write name="register" property="valorReferencial" formatKey="format.int"/></p>
                </td>
              </tr>

			</logic:iterate>

              <tr class="lookup01">
                <td bgcolor="#F0F0F0"><html:text property="codigo" styleClass="txtHomeSmall" size="10"/>
                </td>
                <td bgcolor="#F0F0F0"><html:text property="descripcion" styleClass="txtHomeSmall" size="18"/>
                </td>
                <td bgcolor="#F0F0F0"><html:select property="codigoConcepto" styleClass="menuDespl">
                   	<html:option value="">Todos</html:option>
		      		<logic:iterate id="concept" name="lista.opciones.conceptos" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.Concepto">
						<html:option value="<%=String.valueOf(concept.getCodigo())%>"><bean:write name="concept" property="descripcion"/></html:option>
					</logic:iterate>
               </html:select>
                </td>
                <td bgcolor="#F0F0F0">&nbsp;</td>
                <td bgcolor="#F0F0F0" width="80"><p>&nbsp;</p>
                </td>
                <td bgcolor="#F0F0F0" width="42"><p>&nbsp;</p>
                </td>
                <td bgcolor="#F0F0F0"><p>&nbsp;</p>
                </td>
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
