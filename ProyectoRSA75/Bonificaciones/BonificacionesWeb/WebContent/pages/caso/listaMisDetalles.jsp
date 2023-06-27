<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>  
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<SCRIPT language="Javascript">
var pje=0;
<logic:notEmpty name="producto">
	pje=<bean:write name="producto" property="descuento"/>;
</logic:notEmpty>
function porcentaje(x,y){
	var valor=x.value;
	if(isNaN(valor)){
		alert("Ingrese un número válido");
	}
	else{
		y.value=valor*pje/100;
	}
}
</SCRIPT>

</head>
  
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <logic:equal name="caso" property="estado" value="PRECASO">
                <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link> &gt; Caso</td>
        </logic:equal>
        <logic:notEqual name="caso" property="estado" value="PRECASO">        
        	<html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; Caso</td>
		</logic:notEqual>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerCaso.jsp" %>
	  	  <logic:notEmpty name="socio">
          <%@ include file="/includes/headerSocio2.jsp" %>
          </logic:notEmpty>
 		  <logic:notEmpty name="convenio">
          <%@ include file="/includes/headerConvenio2.jsp" %>
          </logic:notEmpty>
 		  <html:errors/>
          <html:form action="/opcionesDetallesCaso">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td>
              <html:select property="opcion" styleClass="menuDespl">
              	<html:options name="opciones.valores" labelName="opciones"/>
              </html:select> 
              <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/> 
              </td>
            </tr>
          </table>
                    <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="359" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="195"><p><strong>Código Cobertura <bean:message key="label.obligatorio"/>:
                      </strong></p>
                    </td>
                    <td width="150"><p>
                    <logic:notEmpty name="cobertura">
	                    <bean:write name="cobertura" property="codigo"/>
					</logic:notEmpty>
					<html:hidden property="cobertura"/>
					</p>
                    </td>
                  </tr>
                  <tr>
                    <td width="195"><p><strong>Cobertura <bean:message key="label.obligatorio"/>:
                      </strong></p>
                    </td>
                    <td width="150">
                    <p>
                    <logic:notEmpty name="cobertura">
	                    <bean:write name="cobertura" property="descripcion"/>
					</logic:notEmpty>
                    <html:link page="/getListaCoberturasCaso.do?source=caso"><html:img page="/images/botones/boton_look_up.gif" alt="Socio" width="14" height="14" border="0"/></html:link>
					</p>
                    </td>
                  </tr>
 				
 				  <logic:present name="permiteOcurencias">
 				  	<tr>
                    	<td><p><strong>
	                    	<bean:message key="label.cobertura.cantidad.de"/>
                    		 <bean:write name="cobertura" property="etiquetaOcurrencia"/>
                    		  <bean:message key="label.obligatorio"/>:</strong></p>
                	    </td>
            	        <td><p>
        	              <html:text property="cantidadOcurrencias" styleClass="txtHomeSmall" size="18"/>
    	                </p>
	                    </td> 				  	
 				  	</tr>
 				  </logic:present>
 
                  <tr>
                    <td><p><strong>Valor Prestación <bean:message key="label.obligatorio"/>:
                    </strong></p>
                    </td>
                    <td><p>
                      <html:text property="valorPrestacion" styleClass="txtHomeSmall" size="18" onblur="porcentaje(this,descuento)"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Descuento <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="descuento" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Total Aporte Isapre <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="aporteIsapre" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Número Documento <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="documento" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  </table>
              </td>
              </tr> 
          </table>
          <br>
					<table width="552" border="0" cellspacing="2" cellpadding="2">
						<tr>
							<td width="56" class="celdaColor1">
								&nbsp;
							</td>
							<td width="69" class="celdaColor1">
								<a href="#" class="vinculosUp">Detalle</a>
							</td>
							<td width="69" class="celdaColor1">
								<a href="#" class="vinculosUp">Nº Doc</a>
							</td>
							<td class="celdaColor1" width="72">
								<p><a href="#" class="vinculosUp">Valor Prestaci&oacute;n</a></p>
							</td>
							<td class="celdaColor1" width="73">
								<p><a href="#" class="vinculosUp">Descto</a></p>
							</td>
							<td class="celdaColor1" width="71">
								<a href="#" class="vinculosUp">Total</a>
							</td>
							<td class="celdaColor1" width="70">
								<p><a href="#" class="vinculosUp">Aporte Isapre</a></p>
							</td>
							<td class="celdaColor1" width="68">
								<p><a href="#" class="vinculosUp">Aporte Bienestar</a></p>
							</td>
							<td class="celdaColor1" width="68">
								<p><a href="#" class="vinculosUp">Aporte Socio</a></p>
							</td>
							<td class="celdaColor1" width="68">
								<p><a href="#" class="vinculosUp">Aporte Convenio</a></p>
							</td>							
						</tr>

						<logic:iterate id="register" name="caso" property="detalleCaso" indexId="i" type="cl.araucana.bienestar.bonificaciones.model.DetalleCaso">
							<bean:define name="register" property="producto" id="prod" />
							<bean:define name="prod" property="cobertura" id="cob" type="cl.araucana.bienestar.bonificaciones.model.Cobertura"/>
							<tr>
								<td bgcolor="#F0F0F0"><html:radio property="indice"
									value='<%=i+""%>' /></td>
								<td bgcolor="#F0F0F0">
									<p>
									<bean:write name="cob" property="descripcion"/>
									<%if(cob.getTieneOcurrencias().equals("SI")) {%>
										 (<bean:write name="register" property="cantidadOcurencias"/>
										 <bean:write name="cob" property="etiquetaOcurrencia"/>
										)
									<%}%>
									</p>
								</td>
								<td bgcolor="#F0F0F0">
									<p><bean:write name="register" property="documento" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="72">
									<p>$<bean:write name="register" property="monto"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="73">
									<p>$<bean:write name="register" property="montoDescuento"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="71">
									<p>$<bean:write name="register" property="totalMenosIsapreYDescuento"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="70">
									<p>$<bean:write name="register" property="aporteIsapre"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="68">
									<p>$<bean:write name="register" property="aporteBienestar"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="68">
									<p>$<bean:write name="register" property="aporteSocio"
									formatKey="format.int" /></p>
								</td>
								<td bgcolor="#F0F0F0" width="68">
									<p>$<bean:write name="register" property="aporteConvenio"
									formatKey="format.int" /></p>
								</td>								
							</tr>
						</logic:iterate>
						<tr>
							<td bgcolor="#CCCCCC">&nbsp;</td>
							<td bgcolor="#CCCCCC">&nbsp;</td>
							<td bgcolor="#CCCCCC">
								<p><strong>Total</strong></p>
							</td>
							<td bgcolor="#CCCCCC" width="72">
								<p>$ <bean:write name="caso" property="monto"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="73">
								<p>$ <bean:write name="caso" property="montoDescuento"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="73">
								<p>$ <bean:write name="caso" property="total"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="70">
								<p>$ <bean:write name="caso" property="aporteIsapre"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="68">
								<p>$ <bean:write name="caso" property="aporteBienestar"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="68">
								<p>$ <bean:write name="caso" property="aporteSocio"
								formatKey="format.int" /></p>
							</td>
							<td bgcolor="#CCCCCC" width="68">
								<p>$ <bean:write name="caso" property="aporteConvenio"
								formatKey="format.int" /></p>
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
