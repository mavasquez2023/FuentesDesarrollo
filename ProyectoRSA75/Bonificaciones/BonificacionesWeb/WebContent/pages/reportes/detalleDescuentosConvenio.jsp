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
	ventana=window.open('<%= contextRoot+"/imprimeReporte.do?destino=detalleDescuentosConvenio"%>',"print",prop);
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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Informe Descuentos Convenios</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
</logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 INFORME DE DESCUENTOS A CONVENIOS</strong></p>
          
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.informe.descuento.convenios.periodo"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="reporte" property="fechaDescuento" formatKey="format.date.periodo"/>
                	</p>
				</td>				
              </tr>
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.informe.descuento.convenios.convenio"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="reporte" property="convenio"/>
                	</p>
				</td>				
              </tr>
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.informe.descuento.convenios.rut"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="reporte" property="fullRut"/>
                	</p>
				</td>				
              </tr>              
              <tr>
            	<td>
	            	<p class="textoHeader1">
                	<strong><bean:message key="label.informe.descuento.convenios.montoTotal"/>:</strong>
                	</p>
				</td>
            	<td>
	            	<p class="textoHeader1">
                	$<bean:write name="reporte" property="montoTotal" formatKey="format.int"/>
                	</p>
				</td>				
              </tr>                            
            </table>
            <br>
            <table>
            	<tr>
	                <td colspan= "6" class="celdaColor1">
    	            	<p class="vinculosUp">
         			    <strong><bean:message key="label.informe.descuento.convenios.detalleCobro"/></strong></p>
            		</td>
            	</tr>
            	<tr>
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.rut"/>
            			</p>
            		</td>
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.nombre"/>
            			</p>
            		</td>     
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.fechaOcurrencia"/>
            			</p>
            		</td>            	
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.idCaso"/>
            			</p>
            		</td>      
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.detalle"/>
            			</p>
            		</td>      
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.informe.descuento.convenios.aporteConvenio"/>
            			</p>
            		</td>            		      		      			       		
            	</tr>
            	<tr>
            		<td>
            			
            		</td>
            	</tr>
				<logic:iterate id="register" name="reporte" property="detalleCasos" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.DetalleCasosDescontadosConvenioVO">
				<tr>
					<td nowrap>
						<p>
						
						<logic:empty name="print">
							<html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rut" target="_top">
							<bean:write name="register" property="fullRut"/>
							</html:link>
						</logic:empty>
						<logic:notEmpty name="print">
							<bean:write name="register" property="fullRut"/>
						</logic:notEmpty>						
						</p>
					</td>
					<td>
						<p>
							<bean:write name="register" property="fullNombre"/>
						</p>
					</td>					
					<td>
						<p>
							<bean:write name="register" property="fechaOcurrencia"  formatKey="format.date"/>
						</p>
					</td>										
					<td>
						<p>					
						<logic:empty name="print">
							<html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID">
								<bean:write name="register" property="casoID"/>
							</html:link>
						</logic:empty>
						<logic:notEmpty name="print">
							<bean:write name="register" property="casoID"/>
						</logic:notEmpty>
						</p>					
					</td>
					<td>
						<p>
							<bean:write name="register" property="detalleCobertura"/>
						</p>
					</td>
					<td>
						<p>
							$<bean:write name="register" property="aporteConvenio" formatKey="format.int"/>
						</p>
					</td>					
				</tr>
				</logic:iterate>
            </table>

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
