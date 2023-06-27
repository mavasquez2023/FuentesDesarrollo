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
	ventana=window.open('<%= contextRoot+"/imprimeReporteUpLoadFile.do"%>',"print",prop);
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
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Carga de Archivo &gt; Resultado</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
                    </td>
          <td width="77%" valign="top">
</logic:empty>
       <p><strong>BIENESTAR LA ARAUCANA<br>
 RESULTADO DE LA CARGA DE ARCHIVO</strong></p>
          
            <table width="530" border="0" cellspacing="2" cellpadding="2">
              <tr>
            	<td>
	            	<p class="textoHeader1">
                	<strong><bean:message key="label.carga.archivo.convenio"/>:</strong>
                	</p>
				</td>
            	<td>
	            	<p class="textoHeader1">
                	<bean:write name="result" property="convenio"/>
                	</p>
				</td>				
              </tr>                            
              <tr>
            	<td>
	            	<p class="textoHeader1">
                	<strong><bean:message key="label.carga.archivo.producto"/>:</strong>
                	</p>
				</td>
            	<td>
	            	<p class="textoHeader1">
                	<bean:write name="result" property="producto"/>
                	</p>
				</td>				
              </tr>   
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.carga.archivo.archivo"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="result" property="fileName"/>
                	</p>
				</td>				
              </tr>                                                                   
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.carga.archivo.fechaProceso"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="result" property="fechaProceso" formatKey="format.date"/>
                	</p>
				</td>				
              </tr>
              <tr>
                <td>
                	<p class="textoHeader1">
                	<strong><bean:message key="label.carga.archivo.usuario"/>:</strong>
                	</p>
				</td>
                <td>
                	<p class="textoHeader1">
                	<bean:write name="result" property="usuario"/>
                	</p>
				</td>				
              </tr>            
            </table>
            <br>
            <table>
            	<tr>
	                <td colspan= "6" class="celdaColor1">
    	            	<p class="vinculosUp">
         			    <strong><bean:message key="label.carga.archivo.detalleResultado"/></strong>
         			    </p>
            		</td>
            	</tr>
            	<tr>
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.fila"/>
            			</p>
            		</td>
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.rut"/>
            			</p>
            		</td>     
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.boleta"/>
            			</p>
            		</td>            	
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.casoId"/>
            			</p>
            		</td>      
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.resultado"/>
            			</p>
            		</td>      
            		 <td class="celdaColor2">
	            		<p class="vinculosUp">
            			<bean:message key="label.carga.archivo.detalleResultado.mensaje"/>
            			</p>
            		</td>            		      		      			       		
            	</tr>
            	<tr>
            		<td>
            			
            		</td>
            	</tr>
				<logic:iterate id="register" name="result" property="resultado" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.ResultLineVO">
				<tr>
					<td>
						<p>
							<bean:write name="register" property="fila"/>
						</p>
					</td>									
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
							<bean:write name="register" property="boleta"/>
						</p>
					</td>					
					<td>
						<p>					
						<logic:empty name="print">
							<%if(register.getCasoId()>0) {%>
							<html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoId">
								<bean:write name="register" property="casoId"/>
							</html:link>
							<%} else {%>
								<bean:write name="register" property="casoId"/>
							<%}%>
						</logic:empty>
						<logic:notEmpty name="print">
							<bean:write name="register" property="casoId"/>
						</logic:notEmpty>
						</p>					
					</td>					
					<td>
						<p>
						<bean:message key='<%="label.carga.archivo.resul."+register.getResultado()%>'/>						
						</p>
					</td>										
					<td>
						<p>
							<bean:write name="register" property="mensaje"/>
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
