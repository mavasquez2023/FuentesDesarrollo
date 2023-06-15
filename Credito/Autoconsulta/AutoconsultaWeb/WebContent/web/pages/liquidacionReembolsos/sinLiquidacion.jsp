<%@ include file = "/web/includes/env.jsp"%>    
<%@ include file = "/web/includes/header.jsp"%> 
<%@ include file = "/web/includes/top.jsp"%>
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%' class="sello_agua">
<tr>  
<td width='160' valign='top' >

<%@ include file = "/web/includes/opciones.jsp"%>

</td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>      
 
<!-- Begin de la pagina particular -->    

	<table border='0' cellspacing='0' width='100%'>
	<tr>
		<td nowrap class="certificado">
			<div align='center'><h1><bean:message key="label.liquidacion.reembolsos.titulo"/></h1></div>
		</td>
	</tr>
	</table>     
<br>

<font class="certificado">
<%  // Captura el Mensaje de Error  
    String message=(String)session.getAttribute("sin.datos");
%>
<% if (message!=null) { %>
<bean:message key='<%= message %>'/>
<% } %>
</font>
 
<!-- End de la pagina particular -->      
 
<br>  
<br>   
<div align='center'>
<input type="submit"  class="boton" name="dummyProperty" value="Cerrar"        onclick="window.location='Menu.do'" 
       onMouseOver="this.className='botonOver'" 
       onMouseOut="this.className='boton'" />
</div> 
<br> 
</td>
</tr>

</table>

<%@ include file = "/web/includes/footer.jsp"%>