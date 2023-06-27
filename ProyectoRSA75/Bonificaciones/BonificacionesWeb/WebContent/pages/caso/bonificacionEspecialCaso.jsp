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

<bean:define id="caso" name="caso" type="cl.araucana.bienestar.bonificaciones.model.Caso" />
<bean:define id="listaCoberturasEspeciales" name="lista.coberturas.especiales" type="java.util.ArrayList" />

<script type="text/javascript">
function analizarDatos(){
	var deudaSocio =0;
	<%if(caso.getIndicadorBonificacion().equals("SI")) { %>
		deudaSocio = <%= caso.getAporteSocio() %>;
	<%} else { %>
		deudaSocio = <%= caso.getTotal() %>;
	<%} %>
	var cobertura = document.forms[1].codigoCobertura.value;

	var size = <%= listaCoberturasEspeciales.size() %>;
	var porcentaje = 0;
	
	while (size > 0){
	<%
		for (int i = 0; i < listaCoberturasEspeciales.size(); i++) {
			cl.araucana.bienestar.bonificaciones.vo.CoberturaEspecialVO cobertura = 
				(cl.araucana.bienestar.bonificaciones.vo.CoberturaEspecialVO)listaCoberturasEspeciales.get(i);			
	%>
		if (cobertura == <%= cobertura.getCodigo() %>){
			porcentaje = <%= cobertura.getPorcentajeCobertura() %>;
			break;
		}
	<%
		}
	%>	
		size--;
	}

	var aporteEspecial = redondeo(((porcentaje / 100) * deudaSocio), 0);
	document.forms[1].montoAporte.value = aporteEspecial;
	
	var coPago = deudaSocio - aporteEspecial;
	document.forms[1].coPagoSocio.value = coPago;
	
}

function redondeo(numero, decimales) {

	var numeroRedondeado = Math.round(numero * Math.pow(10, decimales)) / Math.pow(10, decimales);
	return numeroRedondeado;
	
}

function actualizarCopago(){
	
	var deudaSocio =0;
	<%if(caso.getIndicadorBonificacion().equals("SI")) { %>
		deudaSocio = <%= caso.getAporteSocio() %>;
	<%} else { %>
		deudaSocio = <%= caso.getTotal() %>;
	<%} %>

	var aporteEspecial = document.forms[1].montoAporte.value;
	
	var coPago = deudaSocio - aporteEspecial;
	document.forms[1].coPagoSocio.value = coPago;	
}
function revisarDatos(){
	var observacion = document.forms[1].observacion.value;

	// quitar blancos extras (trim)
	observacion = observacion.replace( /^\s+/g, "" );// strip leading
	observacion.replace( /\s+$/g, "" );// strip trailing

	if (observacion == ""){
		alert('Debe escribir una observación');
		return false;
	}
	return true;
}
</script>

<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; 
        <html:link page="/setFichaCaso.do" target="_top">Caso</html:link> &gt; Bonificación Especial
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerCaso.jsp" %>
          <br>
          <html:form action="/bonificacionEspecial" onsubmit="return revisarDatos();">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td><html:select property="opcion" styleClass="menuDespl">
              <html:option value="1">Bonificación Especial</html:option>
              </html:select> <html:image page="/images/botones/boton_ir.gif" alt="Confirmar Opci&oacute;n" border="0" /> </td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="515" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="206"><p><strong><bean:message key="label.caso.aporteEspecial.deudaSocio"/>:</strong></p>
                    </td>
					<td width="295">
					<p>
                    	<%if(caso.getIndicadorBonificacion().equals("SI")) { %>
	                    	<bean:write name="caso" property="aporteSocio" formatKey="format.int"/>
	                	<%} else { %>
		                	<bean:write name="caso" property="total" formatKey="format.int"/>
	                	<%} %>
	                </p>
					</td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.aporteEspecial.cobertura"/></strong></p></td>
                    <td><p>
                      <html:select property="codigoCobertura" styleClass="menuDespl" onchange="javascript:analizarDatos()">
	              	  <html:options collection="lista.coberturas.especiales" property="codigo" labelProperty="descripcion"/>
	                  </html:select>
	                </p></td>
                  </tr>                                    
                  <tr>
                    <td><p><strong><bean:message key="label.caso.aporteEspecial.montoAporte"/>:</strong></p></td>
                    <td><p><html:text property="montoAporte" styleClass="txtHomeSmall" onchange="javascript:actualizarCopago()"/>
                    	<a href="#" onClick="javascript:analizarDatos()" >Calcular Aporte Máximo</a>
                    </p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.aporteEspecial.coPagoSocio"/>:</strong></p></td>
                    <td><p><html:text property="coPagoSocio" styleClass="txtHomeSmall" readonly="true"/>
					</p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.aporteEspecial.observacion"/> :</strong></p>
                    </td>
                    <td><p><html:textarea property="observacion" styleClass="txtHomeSmall" cols="25" rows="6"></html:textarea></p>
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
