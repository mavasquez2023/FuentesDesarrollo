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
	var prop="resizable=yes,scrollbars=yes";
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=informePagos"%>',"print",prop);
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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Informe Pago Convenios</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
</logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 INFORME DE PAGO DE CONVENIOS C�DIGO <bean:write name="reporte" property="codigoPago"/></strong></p>
          
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp">
                <strong>RESUMEN DE PAGOS C�DIGO <bean:write name="reporte" property="codigoPago"/> DEL <bean:write name="reporte" property="fechaPago" formatKey="format.date"/></strong></p></td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td width="210"><p class="textoHeader1"><strong>N�mero de Pagos:</strong></p>
                </td>
                <td width="200"><p class="textoHeader1">
                <bean:write name="reporte" property="filas" formatKey="format.int"/></p>
                </td>
              </tr>
              <tr>
                <td><p class="textoHeader1"><strong>Monto Total:</strong></p>
                </td>
                <td><p class="textoHeader1">            
                $<bean:write name="reporte" property="montoTotal" formatKey="format.int"/></p>
                </td>
              </tr>
          </table>
            
             
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="90" class="celdaColor2"><p class="vinculosUp">C�digo Convenio</p>
                </td>
                <td width="165" class="celdaColor2"><p class="vinculosUp">Nombre Convenio</p>
                </td>
                <td width="90" class="celdaColor2"><p class="vinculosUp">RUT Convenio</p>
                </td>
                <td width="115" class="celdaColor2"><p class="vinculosUp">Folio Tesorer�a</p>
                </td>
                <td width="115" class="celdaColor2"><p class="vinculosUp">Monto</p>
                </td>
                </tr>
              <logic:iterate id="detalle" name="reporte" property="detalleConvenios" indexId="t">
				<tr>
					<td><p>
						<bean:write name="detalle" property="codigoConvenio"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="nombreConvenio"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="fullRut"/>
						</p>
					</td>
					<td><p>
						<bean:write name="detalle" property="folioTesoreria"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="detalle" property="monto" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>
                <tr>
                	<td colspan="2">
                		<p><strong>Total de Pagos</strong></p>
                	</td>
                	<td>
                		<p><strong>Cantidad <bean:write name="reporte" property="filas"/></strong></p>
                	</td>
					<td align="right"></td>                	
                	<td>
                		<p>$<strong><bean:write name="reporte" property="montoTotal" formatKey="format.int"/></strong></p>
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
