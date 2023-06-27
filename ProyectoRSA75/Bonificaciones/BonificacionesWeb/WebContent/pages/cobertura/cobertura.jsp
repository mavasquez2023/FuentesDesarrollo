<%@ page language="java"%>
 
<%@ include file = "/includes/env.jsp" %>

<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<STYLE>

.editable {
         font-size: 10px;
         color: #333333;
         background-color: #F0F0F0;
         visibility: visible;
}
.noEditable {
         font-size: 10px;
         color: #333333;
         background-color: #F0F0F0;
         visibility: hidden;
}

.txtHomeSmallVisible {
	display: inline;
}
.txtHomeSmallNoVisible {
	display: none;
}

</STYLE>
<script language="JavaScript">
function analizarTipoPrestacion() {
       var selected = document.forms[1].tipoTope.value;

       if (selected == "PORPRESTA"){
               document.getElementById("comboSiNo").disabled=false;
               document.getElementById("caja").disabled=false;
       }
       else {
               document.getElementById("comboSiNo").disabled=true;
               document.getElementById("caja").disabled=true;
       }
}
function analizarTieneOcurrencias() {
       var selected = document.forms[1].tieneOcurrencias.value;
       if (selected == "SI"){
               document.getElementById("caja").disabled=false;
       }
       else {
               document.getElementById("caja").disabled=true;
       }
}
</script>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp"%>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaCoberturas.do" target="_top">
            Lista de Coberturas</html:link> &gt; Cobertura</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <html:errors/>
           
          <html:form action="/opcionesCobertura">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td><html:select property="opcion" styleClass="menuDespl">
              	<html:options name="cobertura.opciones.valores" labelName="cobertura.opciones"/>
              </html:select> 
              <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/> </td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="346" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td><p><strong><bean:message key="label.cobertura.descripcion"/> <bean:message key="label.obligatorio"/>:</strong></p></td>
                    <td><p>
                      <html:text property="descripcion" styleClass="txtHomeSmall" size="50"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td width="197"><p><strong>C&oacute;digo:
                      </strong></p>
                    </td>
                    <td width="135"><p><bean:write name="cobertura" property="codigo"/></p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.cobertura.tipoCobertura"/> <bean:message key="label.obligatorio"/>:</strong></p></td>
		            <td>
		            <p>
		            <html:select property="tipoCobertura" styleClass="menuDespl">
		            <html:option value="ADICIONAL">ADICIONAL</html:option>
                   	<html:option value="DIRECTA">DIRECTA</html:option>
					<html:option value="ESPECIAL">ESPECIAL</html:option>
	                </html:select>
                    </p>
                    </td>
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
                    <td width="197"><p><strong>Estado:
                      </strong></p>
                    </td>
                    <td width="135"><p><bean:write name="cobertura" property="estado"/>
                        </p>                      </td>
                  </tr>
                  <tr>
                    <td><p><strong>Tipo de Tope:</strong></p>
                    </td>
                    <td><p><html:select property="tipoTope" styleClass="menuDespl" onchange="javascript:analizarTipoPrestacion()">
                    	<html:option value="ANUALBENEF">ANUALBENEF</html:option>
                    	<html:option value="ANUALFAMIL">ANUALFAMILIA</html:option>
                    	<html:option value="MENSUALFAM">MENSUALFAMILIA</html:option>
                    	<html:option value="PORPRESTA">PORPRESTACION</html:option>
	                 </html:select>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Valor Tope <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="valorTope" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Valor Referencial <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="valorReferencial" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Periodo de Carencia (en meses) <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="periodoCarencia" styleClass="txtHomeSmall" size="8"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Cuenta de Gasto:</strong></p>
                    </td>
                    <td><p>
                    <logic:greaterThan name="cobertura" property="cuentaGasto" value="0">
                      <bean:write name="cobertura" property="cuentaGasto"/>
					</logic:greaterThan>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.cobertura.adicional"/></strong></p></td>
                    <td><p>
                      <html:select property="codigoCoberturaAdicional" styleClass="menuDespl" >
	              	  <html:options collection="lista.coberturas.adicionales" property="codigo" labelProperty="descripcion"/>
	                  </html:select>
	                </p></td>
                 </tr>                  
                 <tr >
                    <td><p><strong>
                    	<bean:message key="label.cobertura.tieneOcurrencias"/>
                    	 <bean:message key="label.obligatorio"/>:
                    	</strong></p>
                    </td>
                    <td><p><html:select property="tieneOcurrencias" styleClass="menuDespl" styleId="comboSiNo" onchange="javascript:analizarTieneOcurrencias()">
                    	<html:option value="SI">SI</html:option>
                    	<html:option value="NO">NO</html:option>
	                 </html:select>
                    </p>
                    </td>                                  
                  </tr>
                  <tr >
                    <td><p><strong><bean:message key="label.cobertura.cantidad.de"/>
                     <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                      <html:text property="etiquetaOcurrencias" styleClass="txtHomeSmall" size="50" styleId="caja"/>
                    </p>
                    </td>                                    	
                  </tr>
                </table>
              </td>
              <td width="27%" align="right" valign="top">
                <table width="172" border="0" cellspacing="3" cellpadding="1">
                <logic:notEmpty name="cobertura.botonera">
				<% if (userinformation.hasAccess("cobConvenios")) { %>
                  <tr>
                      <td width="184" align="right" class="opciones"><html:link page="/getListaConveniosCobertura.do" paramId="cobertura" paramName="cobertura" paramProperty="codigo" target="_top"><html:img page="/images/botones/cobertura/mis_convenios.gif" alt="Mis Convenios" width="160" height="21" border="0"/></html:link></td>
                  </tr>
				<% } %>
				<% if (userinformation.hasAccess("cobCasos")) { %>
                  <tr>
                      <td align="right" class="opciones"><html:link page="/getListaCasosCobertura.do" target="_top"><html:img page="/images/botones/cobertura/mis_casos.gif" alt="Mis Casos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
				<% } %>
				<% if (userinformation.hasAccess("cobRango")) { %>
                  <tr>
                      <td align="right" class="opciones"><html:link page="/getListaRangos.do" target="_top"><html:img page="/images/botones/cobertura/mis_rangos.gif" alt="Mis Rangos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
				<% } %>
                  <tr>
                      <td align="right" class="opciones">&nbsp;</td>
                  </tr>
                    <tr>
                      <td align="right" class="opciones">&nbsp;</td>
                  </tr>
                 </logic:notEmpty>
                 <logic:present name="isCoberturaMaestra">
    	             <tr>
	                 	<td>
                 			<p><strong>
                 			<logic:present name="variasCoberturas">
	                 			Nota: Esta Cobertura Define y Consume los mismos aportes de Bienestar  que las coberturas:
	                 		</logic:present>
	                 		<logic:notPresent name="variasCoberturas">
	                 			Nota: Esta Cobertura Define y Consume los mismos aportes de Bienestar  que la cobertura:
	                 		</logic:notPresent>
							<logic:iterate name="listaAgrupacionCoberturas" id="cobertura">
								<bean:write name="cobertura" property="descripcion"/> (cod. <bean:write name="cobertura" property="codigo"/>)
							</logic:iterate>
        	         		</strong></p>
    	             	</td>
	                 </tr>                 
                 </logic:present>
                 <logic:notPresent name="isCoberturaMaestra">
					<logic:present name="coberturaMaestra">
		                 <tr>
    		             	<td>
        		         		<p><strong>					
									Nota: Esta cobertura Utiliza y Consume los aportes de Bienestar seg&uacute;n las definiciones que tiene la cobertura:
									<bean:write name="coberturaMaestra" property="descripcion"/> (cod. <bean:write name="coberturaMaestra" property="codigo"/>)									
	    	             		</strong></p>
    	    	         	</td>
        	    	     </tr>								
					</logic:present>					                 
					<logic:present name="listaAgrupacionCoberturas">
		                 <tr>
    		             	<td>
        		         		<p><strong>
	                 			<logic:present name="variasCoberturas">
	            		     		Nota: Esta Cobertura Consume los mismos aportes de Bienestar que las coberturas:
		                 		</logic:present>
		                 		<logic:notPresent name="variasCoberturas">
	            		     		Nota: Esta Cobertura Consume los mismos aportes de Bienestar que la cobertura:
		                 		</logic:notPresent>        		         		
								<logic:iterate name="listaAgrupacionCoberturas" id="cobertura">
									<bean:write name="cobertura" property="descripcion"/> (cod. <bean:write name="cobertura" property="codigo"/>)
								</logic:iterate>
	    	             		</strong></p>
    	    	         	</td>
        	    	     </tr>
					</logic:present>
            	 </logic:notPresent>
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
<%@ include file="/includes/pie.jsp"%>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
