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
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=detalleDescuentosOficina"%>',"print",prop);
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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Informe Descuentos</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
          <br>
          <div align="center">
          	            <html:link href="javascript:printInforme();"><html:img page="/images/printer.gif" border="0" /></html:link>
          <div>
	      <br>
</logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 DETALLE DE DESCUENTOS CODIGO <bean:write name="reporte" property="codigoDescuento"/></strong></p>
          
            <table width="100%" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td class="celdaColor1"><p class="vinculosUp">
                <strong>RESUMEN DE DESCUENTOS OFICINA <bean:write name="reporte" property="codigoOficina"/> - <bean:write name="reporte" property="oficina"/></strong>
                </p>
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" cellspacing="1">
              <tr>
                <td width="50%"><p class="textoHeader1"><strong>Número de Descuentos:</strong></p>
                </td>
                <td width="50%"><p class="textoHeader1">
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
            
            <logic:iterate id="register" name="reporte" property="detalleSocios" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosSocioVO">
            
           <H3></H3> <!-- corte -->
           
           <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td class="celdaColor1">
                <p class="vinculosUp"><strong>BIENESTAR LA ARAUCANA</strong></p>
                </td>
                <td class="celdaColor1"><p class="vinculosUp">
                </td>                
              </tr>
              <tr>
                <td class="celdaColor1" colspan="2">
                	<p class="vinculosUp"><strong>
                		Oficina <bean:write name="reporte" property="codigoOficina"/> - <bean:write name="reporte" property="oficina"/>
                	</strong></p>
                </td>
             </tr>
              <tr>
                <td class="celdaColor1" colspan="2">
                	<p class="vinculosUp"><strong>
                		Departamento: <bean:write name="register" property="departamento"/>
                	</strong></p>
                </td>
              </tr>             
             <tr>
                <td class="celdaColor1">
                	<p class="vinculosUp"><strong>
                		Socio: <bean:write name="register" property="fullNombre"/>
                	</strong></p>
                </td>
                <td class="celdaColor1">
                	<p class="vinculosUp"><strong>
                		RUT: <bean:write name="register" property="fullRut"/>
                	</strong></p>
                </td>
			  </tr>
			  <tr>
                <td class="celdaColor1"><p class="vinculosUp"><strong>Descuento: <bean:write name="register" property="fecha" formatKey="format.date.periodo"/></strong></p>
                </td>
                <td class="celdaColor1"><p class="vinculosUp">
                </td>
              </tr>
            </table>

			<logic:empty name="print">
            	<table width="100%" border="0" cellspacing="2" cellpadding="2">
            </logic:empty>
			<logic:notEmpty name="print">
            	<table width="100%" bgcolor="white" border="0" cellspacing="0" cellpadding="0">
            </logic:notEmpty>
              <tr>
        	        <td class="celdaColor2">
        	        	<p class="vinculosUp"><strong>
        	        		Descuento(s)
    	            	</strong></p>
    	            </td>
	                <td class="celdaColor2">
	                	<p class="vinculosUp"><strong>
                			Monto
	                	</strong></p>
	                </td>
                </tr>

              <logic:iterate id="detalle" name="register" property="casosDescontados" indexId="t" type="cl.araucana.bienestar.bonificaciones.vo.CasosDescontadosVO">
				<tr>
					<td><p>
					<logic:empty name="print">
						<html:link page="/setFichaCaso.do" paramId="codigo" paramName="detalle" paramProperty="casoId">Caso <bean:write name="detalle" property="casoId"/></html:link>
					</logic:empty>
						</p>
					</td>
					<td>
						<p>
						</p>
					</td>
				</tr>
				<logic:iterate id="cobertura" name="detalle" property="detalle">
				<%if(detalle.getNumeroCuotas()== 0) { %>
					<tr>
						<td>
							<p>
								<bean:write name="detalle" property="convenio"/>
							</p>
						</td>
						<td>
							<p>
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
				<% } else { %>
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
					<tr>
						<td>
							<p>
								 * <bean:write name="cobertura" property="detalleDescripcion"/>
							</p>
						</td>
						<td>
							<p>
							</p>
						</td>
					</tr>				
				<% } %>
				</logic:iterate>
			</logic:iterate>

              <logic:iterate id="detalle" name="register" property="cuotasPrestamos" indexId="t" >
					<tr>
							<td>
								<p>
									Cuotas Préstamos
								</p>
							<td>
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
                		<p><strong>Total Descontado: </strong></p>
                	</td> 
                	<td>
						<P><strong>$<bean:write name="register" property="montoDescuentoTotal" formatKey="format.int"/></strong></p>
                	</td> 
                </tr>

            </table>
      </logic:iterate>
<logic:empty name="print">
<br>

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