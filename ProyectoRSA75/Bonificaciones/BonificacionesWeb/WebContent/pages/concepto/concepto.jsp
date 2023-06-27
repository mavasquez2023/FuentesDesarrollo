<%@ page language="java"%>
<%@ page import="cl.araucana.bienestar.bonificaciones.model.Parametro"%>
<%@ page import="java.util.ArrayList"%> 
<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html> 
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">

<%
    ArrayList ingresos  = (ArrayList) session.getAttribute("concepto.tesoreria.ingresos");    
    ArrayList egresos   = (ArrayList) session.getAttribute("concepto.tesoreria.egresos");    
    Parametro parametro = new Parametro();            
%>

<bean:define id="area" name="ConceptoForm" property="areaTesoreria" />
<bean:define id="conceptoIngreso" name="ConceptoForm" property="conceptoTesoreriaIngreso" />
<bean:define id="conceptoEgreso" name="ConceptoForm" property="conceptoTesoreriaEgreso" />

<script>
    var matrix = new Array();
    var matrixE = new Array();    
    var ingreso = <%=conceptoIngreso%>
    var egreso = <%=conceptoEgreso%>
    //var matrixPais = new Array();
    
    function listaValores() {            
        
        <%    
            int i;
            for(i=0; i < ingresos.size() ; i++) {%>
            <%                                                                                  
                parametro = (Parametro) ingresos.get(i); %>                        
                var matrixAux = new Array(3);
                matrixAux[0] = "<%= parametro.getCodigoPadre()%>";
                matrixAux[1] = "<%= parametro.getCodigo()%>";                
                matrixAux[2] = "<%= parametro.getDescripcion()%>";
                matrix[matrix.length] = matrixAux;                        
            <%}%>        
            
			<%
			int j;
            for(j=0; j < egresos.size() ; j++) {%>
            <%                                                                                  
                parametro = (Parametro) egresos.get(j); %>                        
                var matrixAux = new Array(3);
                matrixAux[0] = "<%= parametro.getCodigoPadre()%>";
                matrixAux[1] = "<%= parametro.getCodigo()%>";                
                matrixAux[2] = "<%= parametro.getDescripcion()%>";
                matrixE[matrixE.length] = matrixAux;                        
            <%}%>     
                			            
    }
    
    function selectioN(codigo) 
    {
        document.forms[1].conceptoTesoreriaIngreso.length=0;
        for(var i=0;i<matrix.length;i++) 
        {
            if(matrix[i][0]==codigo) 
            {                
                document.forms[1].conceptoTesoreriaIngreso.options[document.forms[1].conceptoTesoreriaIngreso.length] = 
                                    new Option(matrix[i][2], matrix[i][1]);                                
            }                                                        
        }      
                
        document.forms[1].conceptoTesoreriaEgreso.length=0;
        for(var i=0;i<matrixE.length;i++) 
        {
            if(matrixE[i][0]==codigo) 
            {                
                document.forms[1].conceptoTesoreriaEgreso.options[document.forms[1].conceptoTesoreriaEgreso.length] = 
                                    new Option(matrixE[i][2], matrixE[i][1]);                                
            }                                                        
        }                
    }
        
    function inicio(ingreso, egreso) {
    	for(var i=0;i<document.forms[1].conceptoTesoreriaIngreso.length;i++) {
    		if(ingreso==document.forms[1].conceptoTesoreriaIngreso.options[i].value) {
    			document.forms[1].conceptoTesoreriaIngreso.options[i].selected=true;
    			break;
    		}
    	}

    	for(var i=0;i<document.forms[1].conceptoTesoreriaEgreso.length;i++) {
    		if(egreso==document.forms[1].conceptoTesoreriaEgreso.options[i].value) {
    			document.forms[1].conceptoTesoreriaEgreso.options[i].selected=true;
    			break;
    		}
    	}
    	
    	
    }
    
</script>

</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="listaValores();selectioN(<%=area%>);inicio(<%=conceptoIngreso%>,<%=conceptoEgreso%>);">

<%@ include file = "/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/getListaConceptos.do" target="_top">Lista de Conceptos</html:link> &gt; Concepto</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file = "/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <html:errors/>
          <html:form action="/opcionesConceptos">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td>
              <html:select property="opcion" styleClass="menuDespl">
              	<html:options name="concepto.opciones.valores" labelName="concepto.opciones"/>
              </html:select> <html:image page="/images/botones/boton_ir.gif" alt="Aplicar" border="0"/></td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="346" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td><p><strong>Concepto <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p>
                    	<html:text property="descripcion" styleClass="txtHomeSmall" size="18"/>
                    </p>
                    </td>
                  </tr>
                  <tr>
                    <td width="191"><p><strong>C&oacute;digo:</strong></p>
                    </td>
                    <td width="141"><p>
                    <logic:greaterThan value="0" name="concepto" property="codigo">
                    	<bean:write name="concepto" property="codigo"/>
					</logic:greaterThan>
                        </p>                      </td>
                  </tr>
                  <tr>
                    <td><p><strong>Fecha de Creaci&oacute;n:</strong></p>
                    </td>
                    <td><p><bean:write name="concepto" property="fechaCreacion" formatKey="format.date"/></p>
                    </td>
                  </tr>
                  <tr>
                    <td><p><strong>Cuenta Acreedora <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td><p><html:select property="cuentaAcreedora" styleClass="menuDespl">
	              	<html:options collection="concepto.cuenta.acreedora" property="codigo" labelProperty="descripcion"/>
	              </html:select>
	              </p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    	<p><strong>Cuenta Deudora <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td>
                    	<p><html:select property="cuentaDeudora" styleClass="menuDespl">
	              		<html:options collection="concepto.cuenta.deudora" property="codigo" labelProperty="descripcion"/>
	              		</html:select></p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    	<p><strong>Area Tesoreria <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td>                    	
                    	<p><html:select property="areaTesoreria" styleClass="menuDespl" onchange="selectioN(document.forms[1].areaTesoreria.value);">
	              		<html:options collection="concepto.areas.tesoreria" property="codigo" labelProperty="descripcion"/>
	              		</html:select></p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    	<p><strong>Concepto Ingreso Tesoreria <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td>
                    	<p><html:select property="conceptoTesoreriaIngreso" styleClass="menuDespl">
	              		<!--<html:options collection="concepto.tesoreria.ingresos" property="codigo" labelProperty="descripcion"/>-->
	              		
	              		</html:select></p>
                    </td>
                  </tr>
                  <tr>
                    <td>
                    	<p><strong>Concepto Egreso Tesoreria <bean:message key="label.obligatorio"/>:</strong></p>
                    </td>
                    <td>
                    	<p><html:select property="conceptoTesoreriaEgreso" styleClass="menuDespl">
	              		<!--<html:options collection="concepto.tesoreria.egresos" property="codigo" labelProperty="descripcion"/>-->
	              		</html:select></p>
                    </td>
                  </tr>
                </table>
              </td>
              <td width="27%" align="right" valign="top">
                <table width="172" border="0" cellspacing="3" cellpadding="1">
				<logic:notEmpty name="concepto.botonera.convenio">
 <% if (userinformation.hasAccess("opeConceptoConvenios")) { %>
                    <tr>
                      <td width="184" align="right" class="opciones"><html:link page="/getListaConveniosConcepto.do" paramId="concepto" paramName="concepto" paramProperty="codigo" target="_top"><html:img page="/images/botones/concepto/mis_convenios.gif" alt="Mis Convenios" width="160" height="21" border="0"/></html:link></td>
                  </tr>
 <% } %>
                 </logic:notEmpty>
				<logic:notEmpty name="concepto.botonera.cobertura">
 <% if (userinformation.hasAccess("opeConceptoCoberturas")) { %>
                    <tr>
                      <td align="right" class="opciones"><html:link page="/getListaCoberturasConcepto.do" paramId="concepto" paramName="concepto" paramProperty="codigo" target="_top"><html:img page="/images/botones/concepto/mis_coberturas.gif" alt="Mis Casos" width="160" height="21" border="0"/></html:link></td>
                  </tr>
 <% } %>
                 </logic:notEmpty>
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
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
