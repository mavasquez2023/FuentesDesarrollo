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
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=detalleDescuentosSocio"%>',"print",prop);
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
p{	
	font-family: courier-new, monospace;
	font-size: 90%;
}
p.textoHeader1{
	font-family: courier-new, monospace;
	font-size: 90%;
	background-color: white;
	color: black;
	font: normal;
}

p.vinculosUp{
	font-family: courier-new, monospace;
	font-size: 90%;
	background-color: white;
	color: black;
	font: normal;
	border: none;
}

.celdaColor1{
	background-color: white;
	color: black;
}

.celdaColor2{
	background-color: white;
	color: black;
}

H3 { page-break-before: always }

table {
	border: 1px solid black;
	border-collapse: collapse;
	border-spacing: 0px;
}

table.inner {

	border-top-width: 0px;
	border-left-width: 0px;
	border-right-width: 0px;
	border-collapse: collapse;
	border-spacing: 0px;
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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Detalle Descuentos Socio</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
</logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 DETALLE DE DESCUENTOS DE: <bean:write name="reporte" property="fecha" formatKey="format.date.periodo"/></strong></p>
          
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="celdaColor1" colspan="2">
                	<p class="vinculosUp"><strong>
                		Oficina: <bean:write name="reporte" property="codigoOficina"/> - <bean:write name="reporte" property="oficina"/>
                	</strong></p>
                </td>
              </tr>
              <tr>
                <td class="celdaColor1" colspan="2">
                	<p class="vinculosUp"><strong>
                		Departamento: <bean:write name="reporte" property="departamento"/>
                	</strong></p>
                </td>
              </tr>
              <tr>
                <td class="celdaColor1">
	                <p class="vinculosUp"><strong>
	                	Socio: <bean:write name="reporte" property="fullNombre"/>
	                </strong></p>
                </td>
                <td class="celdaColor1">
                	<p class="vinculosUp"><strong>
                		RUT: <bean:write name="reporte" property="fullRut"/>
                	</strong></p>
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td width="300" class="celdaColor2"><p class="vinculosUp">
                	<strong>
                	Descuento(s)
	                </strong>
                </td>
                <td width="115" class="celdaColor2"><p class="vinculosUp">
                	<strong>
	                	Monto
                	</strong>
                </td>
                </tr>

              <logic:iterate id="detalle" name="reporte" property="casosDescontados" indexId="t" type="cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosVO">
				<tr>
					<td><p>
					<logic:empty name="print">
						<html:link page="/setFichaCaso.do" paramId="codigo" paramName="detalle" paramProperty="casoId">Caso <bean:write name="detalle" property="casoId"/></html:link>
					</logic:empty>
						</p>
					</td>
					<td>
					</td>
				</tr>
				<tr>
				<logic:iterate id="cobertura" indexId="i" name="detalle" property="detalle" type="cl.araucana.bienestar.bonificaciones.vo.DetalleCasoDescontadoVO">						
				<%if(detalle.getNumeroCuotas()== 0 && cobertura.getDetalleAporteSocio()>0) { %>
					<tr>
						<td>
							<p>
								<bean:write name="detalle" property="convenio"/>
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								 * <bean:write name="cobertura" property="detalleDescripcion"/>
								  <bean:write name="detalle" property="fullCuota"/>
							</p>
						</td>					
						<td>
							<p>
								$<bean:write name="cobertura" property="detalleAporteSocio" formatKey="format.int"/>
							</p>
						</td>
					</tr>
				<% } else if(detalle.getMontoDescuento()>0) { %>
					<% if(i.intValue()==0) { %>
						<tr>
							<td>
								<p>
									<bean:write name="detalle" property="convenio"/>
									 <bean:write name="detalle" property="fullCuota"/>
								</p>
							</td>
							<td>
								<p>
									$<bean:write name="detalle" property="montoDescuento" formatKey="format.int"/>
								</p>
							</td>							
						</tr>
					<% } %>
					<tr>
						<td>
							<p>
								 * <bean:write name="cobertura" property="detalleDescripcion"/>
							</p>
						</td>
					</tr>				
				<% } %>
				</logic:iterate>
				</tr>
			</logic:iterate>

              <logic:iterate id="detalle" name="reporte" property="cuotasPrestamos" indexId="t" >
					<tr>
							<td>
								<p>
									Cuotas Préstamos
								</p>
							</td>
					</tr>                            
				<tr>
					<td><p>
						* <bean:write name="detalle" property="descripcionPrestamo"/>
						  <bean:write name="detalle" property="fullCuota"/>
						</p>
					</td>
					<td><p>
						$<bean:write name="detalle" property="monto" formatKey="format.int"/>
						</p>
					</td>
				</tr>
			</logic:iterate>

                <tr>
                	<td>
                		<p><strong>Total de Descuentos: </strong></p>
                	</td> 
                	<td>
						<P><strong>$<bean:write name="reporte" property="montoDescuentoTotal" formatKey="format.int"/></strong></p>
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
