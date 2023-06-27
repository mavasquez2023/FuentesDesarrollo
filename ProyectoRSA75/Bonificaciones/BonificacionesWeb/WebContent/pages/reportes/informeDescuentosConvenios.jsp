<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head> 
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
<!-- <META name="links-collection-enabled" content="false"> -->
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td>
    	<table width="97%" border="0" cellspacing="2" cellpadding="2">
	      <tr>
    	    <td class="caminito"><%@ include file="/includes/camino.jsp"%> <html:link page="/prepareOption.do?=destino=reportes" target="_top">Reportes</html:link> &gt; Informe Descuentos Convenios</td>
	      </tr>
    	</table>
        <table width="97%" border="0" cellspacing="2" cellpadding="2">
        	<tr>
          		<td width="23%" valign="top">
          			<%@ include file="/includes/menu.jspf" %>
                </td>
	            <td width="77%" valign="top">
      				 <p><strong>BIENESTAR LA ARAUCANA<br>
					 INFORME DE DESCUENTOS A LOS CONVENIOS CÓDIGO <bean:write name="reporte" property="codigoDescuento"/></strong></p>
          
		             <table width="530" border="0" cellspacing="2" cellpadding="2">
        		    	<tr>
                			<td class="celdaColor1"><p class="vinculosUp">
			                	<strong>RESUMEN DE DESCUENTOS CÓDIGO <bean:write name="reporte" property="codigoDescuento"/> DEL <bean:write name="reporte" property="fechaDescuento" formatKey="format.date"/></strong></p>
				            </td>
    	       		 	</tr>
		             </table>
		             <table width="100%" border="0" cellpadding="1" cellspacing="1">
         			     <tr>
			                <td width="210"><p class="textoHeader1">
            			    	<strong><bean:message key="label.descuentos.convenios.cantidad"/>:</strong></p>
			                </td>
            			    <td width="200"><p class="textoHeader1">
				                <bean:write name="reporte" property="filas" formatKey="format.int"/></p>
            			    </td>
			              </tr>
			              <tr>
            			    <td>
			                	<p class="textoHeader1"><strong><bean:message key="label.descuentos.convenios.montoTotal"/>:</strong></p>
            			    </td>
			                <td>
			                	<p class="textoHeader1">$<bean:write name="reporte" property="montoTotal" formatKey="format.int"/></p>
			                </td>
            			  </tr>
			         </table>
 
			         
			         
			         
			  		 <bean:define name="reporte" id="informe" type="cl.araucana.bienestar.bonificaciones.vo.InformeDescuentosConveniosVO"/>
		             <table width="450" border="0" cellspacing="2" cellpadding="2">
        			      <tr>
			                <td width="150" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.descuentos.convenios.codigoConvenio"/></p>
            			    </td>
			                <td width="150" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.descuentos.convenios.convenio"/></p>
            			    </td>
			                <td width="150" class="celdaColor1"><p class="vinculosUp"><bean:message key="label.descuentos.convenios.monto"/></p>
            			    </td>
			              </tr>			  		 
					 	  <logic:iterate id="register" name="reporte" property="detalleConvenios" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.DetalleDescuentosConveniosVO">
	             		  <br>
              			  <tr>
              			   	<td>
              			   		<p>
				                	<html:link page='<%="/getDetalleDescuentosConvenio.do?codigo="+informe.getCodigoDescuento()%>' paramId="codigoConvenio" paramName="register" paramProperty="codigoConvenio">
				                	<bean:write name="register" property="codigoConvenio"/>
				                	</html:link>
                				</p>
                		   	</td>              			  
			                <td><p><bean:write name="register" property="convenio"/></p>
            			    </td>
			                <td><p>$<bean:write name="register" property="montoTotal" formatKey="format.int"/></p>
            			    </td>			                    
             		      </tr>
		             </table>
		 			 </logic:iterate>
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
</body>
</html>
