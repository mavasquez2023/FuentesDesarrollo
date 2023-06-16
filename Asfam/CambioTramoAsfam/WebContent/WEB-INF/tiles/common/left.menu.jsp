<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<div class="menu">
	<table cellspacing="0" cellpadding="0" width="100%" border="0">
		<tbody>
			 
				 
<tr>						 
<ul>
<li>
<html:link action="HomePage.do"><span style="font-size: 14px"> Inicio</span></html:link>
</li>
<li>
<html:link action="/DescargaPropuestas.do" ><span style="font-size: 14px">1. Descargar Propuesta</span></html:link>
</li>
<li>
<html:link action="/BeginPropuesta.do"><span style="font-size: 14px">2. Aceptar Propuesta</span></html:link>
</li>
<li>	 
<html:link action="archivoempresa.do"><span style="font-size: 14px">3. Enviar Propuesta Modificada</span></html:link>
</li>
<li>
<html:link action="ValidaTerminosPage.do?step=startValidacionTerminos"><span style="font-size: 14px">4. Aceptar Declaraci&oacute;n Jurada
</span></html:link>
</li>
<li>
<a href="#"><span style="font-size: 14px">Ayuda</span></a>
</li>
<li>
<html:link action="logout.do"><span style="font-size: 14px">Cerrar Sesi&oacute;n</span></html:link>
</li>						 
						 
</ul>				 
				
			</tr>
		</tbody>
	</table>
</div>