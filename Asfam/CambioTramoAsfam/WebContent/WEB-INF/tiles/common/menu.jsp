<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Properties"%>
<%@page import="cl.araucana.ctasfam.resources.util.Utils"%>
<%@page import= "cl.araucana.ctasfam.resources.util.Parametros"%>
 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<div class="menu"> 

	
	<table cellspacing="0" cellpadding="0" width="100%"  border="0" style="height: 80px;">
		<tbody>
<tr>
<ul>


	<li>
	<html:link action="HomePage.do" ><span style="font-size: 14px">Inicio</span></html:link>
	</li>
	<li>
	<html:link action="/DescargaPropuestas.do" ><span style="font-size: 14px">
		<c:if test="${parametros.tipoDescarga=='PROPUESTA' }">
				Descargar Propuesta
		</c:if>
		<c:if test="${parametros.tipoDescarga=='INFORME' }">
				Descargar Informe
		</c:if>
		</span></html:link>
	</li>
<%
Utils util = new Utils();
//System.out.println("PROCESO_CERRADO=" + Parametros.getInstance().getParam().getFechaEnvio());
//System.out.println("COMPARA=" + util.compareCurrentDay(Parametros.getInstance().getParam().getFechaEnvio()));
if(util.compareCurrentDay(Parametros.getInstance().getParam().getFechaEnvio()) >1)
{ 
%>
	 <li>
	<html:link action="/BeginPropuesta.do"><span style="font-size: 14px">Enviar Declaración de Renta</span></html:link>
	</li>
	<li>
	<html:link action="/estadoprocesamiento.do"><span style="font-size: 14px">Estado de Procesamiento</span></html:link>
	</li>
<%
}
%>
	
	<li>
	<a href="#" onclick="window.open('<%=request.getContextPath()%>/edocs/LaAraucana_ActTramos_ManualOperacion1.pdf', '_blank','toolbar=0,menubar=0,resizable=1,width=800,height=600');"><span style="font-size: 14px">Ayuda</span></a>
	</li>


<li>
<html:link page="/logout"><span style="font-size: 14px;color:blue">Cerrar Sesi&oacute;n</span></html:link>

</li>						 
</ul>						 	 

 
 
</tr>			
		</tbody>
	</table>
	
	</div>