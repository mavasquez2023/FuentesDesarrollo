<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!--%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %-->

<html>
<head>
<title>La Araucana - Intranet</title> 
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file = "/includes/arriba.jsp" %>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td>
	    <table width="97%" border="0" cellspacing="0" cellpadding="0">
	      <tr valign="top">
	        <td width="80%">
	        <table width="100%" border="0" cellspacing="2" cellpadding="2">
	          <tr>
	            <td class="caminito">Usted est&aacute; en <html:link page="/prepareOption.do" target="_top">Home
	                Page </html:link>&gt; Sistema
	              de Bonificaciones de Bienestar</td>
	          </tr>
       		 </table>          
          <h1>Sistema de Bonificaciones de Bienestar</h1>
          <p>Escoja una opci&oacute;n de las siguientes:</p>       
          <table width="694" border="0" cellspacing="0" cellpadding="2">
          <!--DWLayoutTable-->
          
          <tr align="center">
	        	<td nowrap="nowrap">
		            <% if (userinformation.hasAccess("opSocio")) { %>
		            <html:link page="/prepareOption.do?destino=socios" target="_top">
						<html:img page="/images/botones_home/socio.gif" alt="Socio" width="52" height="72" border="0"/>
		           	</html:link>
		           	<html:link page="/prepareOption.do?destino=sociosInactivos" target="_top">
						<html:img page="/images/botones_home/socioInactivos.gif" alt="Socio" width="52" height="72" border="0"/>
		           	</html:link>
		            <% } %>
		            <% if (userinformation.hasAccess("opCaso")) { %>
		            <html:link page="/prepareOption.do?destino=casos" target="_top">
						<html:img page="/images/botones_home/caso.gif" alt="Caso" width="53" height="73" border="0"/>
		            	</html:link>
		            <% } %>
		            <% if (userinformation.hasAccess("prcPreCaso")) { %>
		            <html:link page="/prepareOption.do?destino=listaPreCasos" target="_top">
						<html:img page="/images/botones_home/preCaso.gif" alt="Pre-Caso" width="79" height="73" border="0"/>
		            	</html:link>
		            <% } %>		            
		            <% if (userinformation.hasAccess("opConvenio")) { %>
		            <html:link page="/prepareOption.do?destino=convenios" target="_top">
						<html:img page="/images/botones_home/convenio.gif" alt="Lista Convenio" width="79" height="73" border="0"/>
		            	</html:link>
		            <% } %>
		            <% if (userinformation.hasAccess("opCobertura")) { %>
		            <html:link page="/prepareOption.do?destino=coberturas" target="_top">
						<html:img page="/images/botones_home/coberturas.gif" alt="Lista Coberturas" width="87" height="73" border="0"/>
		            	</html:link>
		            <% } %>
		            <% if (userinformation.hasAccess("opOperacion")) { %>
		            <html:link page="/prepareOption.do?destino=operaciones" target="_top">
						<html:img page="/images/botones_home/operacion.gif" alt="Operaci&oacute;n" width="85" height="74" border="0"/>
		            	</html:link>
		            <% } %>
		            <% if (userinformation.hasAccess("opReporte")) { %>
		            <html:link page="/prepareOption.do?destino=reportes" target="_top">
						<html:img page="/images/botones_home/reportes.gif" alt="Reportes" width="73" height="72" border="0"/>
		            	</html:link>
		            <% } %>
		            <% if (false && userinformation.hasAccess("opConfiguracion")) { %>
		            <html:link page="/prepareOption.do?destino=configuracion" target="_top">
						<html:img page="/images/botones_home/configuracion.gif" alt="Configuraci&oacute;n" width="102" height="73" border="0"/>
		            	</html:link>
		            <% } %>
				</td>
            </tr>
        </table>
 		<% if (userinformation.hasAccess("opSocio")) { %>
 		<br/>
 		<br/>
 		<br/>
          <!--c:if test="${sociosInactivos != null && (sociosInactivosSize != null && sociosInactivosSize > 0)}"-->  
          <logic:notEqual name="sociosInactivosSize" scope="request" value="0">
	          <table width="694" border="0" cellspacing="2" cellpadding="0">
	          	<tr class="lookup01">
		        	<td class="celdaColor1">
		        		<p class="vinculosUpCenter">Resumen socios Inactivos con casos abiertos</p>
		        	</td>
	        	</tr>
	        	<!--
	        	<tr>
		        	<td bgcolor="#F0F0F0">
		        	<p>Existen <--c:out value="${sociosInactivosSize}" /> socios inactivos con casos activos</p>
		        	</td>
				</tr> 
				-->
	        	<tr align="center">
		        	<td>
		        	<table width="694" border="0" cellspacing="2" cellpadding="0">
			        	<!-- c:forEach items="${sociosInactivos}" var="socioInactivo"-->
			        	<logic:iterate id="socioInactivo" name="sociosInactivos" indexId="i">
					       		<tr align="center">
			        				<td bgcolor="#F0F0F0" width="70%">
						        		<p>
						        		<!--c:out value="${socioInactivo.nombre}"/ --> <!-- c:out value="${socioInactivo.apellidoPaterno}"/--> <!-- c:out value="${socioInactivo.apellidoMaterno}"/-->
						        		<bean:write name="socioInactivo" property="nombre"/> <bean:write name="socioInactivo" property="apellidoPaterno"/> <bean:write name="socioInactivo" property="apellidoMaterno"/>
						        		</p>
						        	</td>
						        	<td bgcolor="#F0F0F0" width="30%">	
						        		<p><!--  c:out value="${socioInactivo.contadorCasosAbiertos}"/-->
						        		 <bean:write name="socioInactivo" property="contadorCasosAbiertos"/>
						        		 Casos Abiertos <html:link page="/CasosSocioInactivo.do"  paramId="socio" paramName="socioInactivo" paramProperty="rut" target="_top">ver</html:link><br/>
						        		</p>
					        		</td>
			        		</tr>
				         <!--/c:forEach-->
				         </logic:iterate>
	          		</table>
	          		</td>
	          	</tr>
	          </table>
	          </logic:notEqual>
		  <!--/c:if-->
		  <br/>   
		  <%} %>          
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
