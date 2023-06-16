<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties"%>
<%@page import="cl.araucana.ctasfam.resources.util.Utils"%>

 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<% 
	Properties Config = new Properties();
	Config.load(getClass().getClassLoader().getResourceAsStream("configuracion.properties"));
%>
 

 
<div class="menu"> 

	
	<table cellspacing="0" cellpadding="0" width="100%"  border="0" style="height: 80px;">
		<tbody>
<tr>
<ul>
<%
Utils util = new Utils();

if(util.compareCurrentDay(Config.getProperty("PROCESO_CERRADO")) >= 2)
{ 
%>

<li>
<a href="#" onclick="window.open('<%=request.getContextPath()%>/edocs/LaAraucana_ActTramos_ManualOperacion1.pdf', '_blank','toolbar=0,menubar=0,resizable=1,width=800,height=600');"><span style="font-size: 14px">Ayuda</span></a>
</li>
<%
}
%>
<li>
<html:link page="/logout"><span style="font-size: 14px">Cerrar Sesi&oacute;n</span></html:link>

</li>						 
</ul>						 	 

 
 
</tr>			
		</tbody>
	</table>
	
	</div>