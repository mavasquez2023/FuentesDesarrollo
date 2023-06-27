<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<script language="JavaScript">
function printInforme(){
	var ventana;
	var prop="resizable=yes,scrollbars=yes,location=yes";
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=informeReembolsos"%>',"print",prop);
	ventana.print();
}
</script>
<logic:notEmpty name="print">
<STYLE>
body {
	background-attachment: scroll;
	background-color: #FFFFFF;
	background-image:  none;
	background-repeat: repeat-x;

}
</STYLE>
</logic:notEmpty> 
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<logic:empty name="print">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Informe Reembolsos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
 </logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 NOMINA DE CARGA DE REEMBOLSOS EN TESORERIA ENVIO Nro <bean:write name="reporte" property="codigo"/></strong></p>
          
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp">
                <strong>RESUMEN DE REEMBOLSOS TRASPASADOS ENVIO <bean:write name="reporte" property="codigo"/> DEL <bean:write name="reporte" property="fecha" formatKey="format.date"/></strong></p></td>
              </tr>
            </table>
            <table width="530" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td width="210"><p class="textoHeader1"><strong>Total de Comprobantes Traspasados:</strong></p>
                </td>
                <td width="200"><p class="textoHeader1">
                <bean:write name="reporte" property="totalFilas" formatKey="format.int"/></p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Monto Total de los Comprobantes Traspasados:</strong></p>
                </td>
                <td><p class="textoHeader1">            
                $<bean:write name="reporte" property="totalReembolso" formatKey="format.int"/></p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Comprobante de Ingreso Tesoreria Bienestar:</strong></p>
                </td>
                <td><p class="textoHeader1">
  					<bean:write name="reporte" property="folioIngresoBienestar" formatKey="format.int"/>
  				</p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Comprobante de Egreso Tesoreria Bienestar:</strong></p>
                </td>
                <td><p class="textoHeader1">
  				<bean:write name="reporte" property="folioEgresoBienestar" formatKey="format.int"/>
  				</p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Comprobante de Ingreso Tesoreria Araucana:</strong></p>
                </td>
                <td><p class="textoHeader1">
                <bean:write name="reporte" property="folioIngresoAraucana" formatKey="format.int"/>
                </p>
                </td>
              </tr>
            </table>
            
            <logic:iterate id="register" name="reporte" property="detalleInforme" indexId="i">
            
            <br>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Oficina: <bean:write name="register" property="codigoOficina"/> - <bean:write name="register" property="oficina"/></strong></p>
                </td>
              </tr>
            </table>
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="181" class="celdaColor2"><p class="vinculosUp">Socio</p>
                </td>
                <td width="112" class="celdaColor2"><p class="vinculosUp">RUT</p>
                </td>
                <td width="112" class="celdaColor2"><p class="vinculosUp">Caso(s)</p>
                </td>
                <td width="112" class="celdaColor2"><p class="vinculosUp">Comprobante de Bienestar</p>
                </td>
                <td width="112" class="celdaColor2"><p class="vinculosUp">Comprobante
de La Araucana
</p>
                </td>
                <td width="112" class="celdaColor2"><p class="vinculosUp">Monto</p>
                </td>
                </tr>
             <logic:iterate id="detalle" name="register" property="reembolsos" indexId="t">
				<tr>
					<td><p>
						<bean:write name="detalle" property="fullNombre"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="fullRut"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="casos"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="folioTesoreriaBienestar"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="folioTesoreriaAraucana"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="detalle" property="montoReembolso" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>
                <tr>
                	<td colspan="3">
                		<p><strong>Total de Reembolsos por Oficina</strong></p>
                	</td>
                	<td colspan="2">
                		<p><strong>Cantidad <bean:write name="register" property="cantidadFilas"/></strong></p>
                	</td>
                	<td>
                		<p>$<strong><bean:write name="register" property="montoTotalOficina" formatKey="format.int"/></strong></p>
                	</td>
                </tr>

            </table>

			</logic:iterate>
	<logic:empty name="print">
				<br>
			            <html:link href="javascript:printInforme();"><html:img page="/images/printer.gif" border="0" /></html:link>
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
</logic:empty>
</body>
</html>
