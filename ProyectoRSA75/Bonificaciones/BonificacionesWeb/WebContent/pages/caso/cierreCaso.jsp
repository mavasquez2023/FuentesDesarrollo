<%@ page language="java"%>


<%@ include file = "/includes/env.jsp" %>

<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<script language="javascript">
var deudaSocio=<bean:write name="caso" property="aporteSocio"/>;
function calcula(x,y){
	var valor=x.value;
	if(valor=="") x.value="0";
	if(isNaN(valor)){
		alert("Ingrese un número válido");
		x.value="0";
		calcula(x,y);
	}
	else{
		y.value=deudaSocio-valor;
	}
}

function validaNumPrestamo(x,y){
	var valor=x.value;
	var prestamo=y.value;
	if(isNaN(valor)){
		alert("Ingrese un número válido en Número de Préstamo");
		return false;
	}
	if(prestamo>0){
		if(valor<1) {
			alert("Debe ingresar un Número de Préstamo válido");
			return false;
		}
	}
	return true;
}

function validaTodo(x,y){
	var ret=true;
	ret=validaNumPrestamo(x,y);
	if(document.forms[1].observacion.value==""){
		alert("Debe ingresar una observación");
		ret=false;
	}
	return ret;
}
</script>
</head>  

 
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
        <html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; 
        <html:link page="/setFichaCaso.do" target="_top">Caso</html:link> &gt; Cierre Caso
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf"%>
          </td>
          <td valign="top">
          <%@ include file="/includes/headerCaso.jsp" %>
          <br>
          <html:form action="/cierraCaso" onsubmit="return validaTodo(numeroPrestamo,montoPrestamo);">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td><html:select property="opcion" styleClass="menuDespl">
              <html:option value="1">Cerrar Caso</html:option>
              </html:select> <html:image page="/images/botones/boton_ir.gif" alt="Confirmar Opci&oacute;n" border="0"/> </td>
            </tr>
          </table>
          <table width="97%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="515" border="0" cellspacing="2" cellpadding="2">
                                  <tr>
                    <td width="206"><p><strong><bean:message key="label.caso.cierre.deudaSocio"/>:</strong></p>
                    </td>
                    <td width="295"><p><bean:write name="caso" property="aporteSocio" formatKey="format.int"/>  </p>                      </td>
                    </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.cierre.montoAbono"/>:</strong></p></td>
                    <td><p><html:text property="montoAbono" styleClass="txtHomeSmall"  onblur="calcula(this,montoPrestamo)"/></p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.cierre.montoPrestamo"/>:</strong></p></td>
                    <td><p><html:text property="montoPrestamo" styleClass="txtHomeSmall" onblur="calcula(this,montoAbono)"/></p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.cierre.numeroprestamo"/>:</strong></p></td>
                    <td><p><html:text property="numeroPrestamo" styleClass="txtHomeSmall"/></p></td>
                  </tr>
                  <tr>
                    <td><p><strong><bean:message key="label.caso.cierre.observacion"/> :</strong></p>
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
<script language="javascript">
calcula(document.forms[1].montoAbono,document.forms[1].montoPrestamo);
</script>
</body>
</html>
