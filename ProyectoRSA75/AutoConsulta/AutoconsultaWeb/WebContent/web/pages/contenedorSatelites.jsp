<%@ page import="cl.araucana.autoconsulta.vo.UsuarioVO"%>

<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header_new.jsp "%>
<%@ include file = "/web/includes/top.jsp" %>

<table border="0" cellpadding="0" cellspacing="0"   class="sello_agua" width="1104px">
<tr> 
<td width='160' valign='top'>
<div class="menu">
<%@ include file = "/web/includes/opciones.jsp"%>
</div>
</td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>
	<!--Contenido de la pagina -->
	<div style="margin-top: 15px;"></div>
	<iframe
		src="<%=request.getAttribute("urlDestino")%>" frameborder="0"
		style="height: 500px;width: 900px;"
		name="mainIframe" id="mainIframe">
	</iframe>

	<!-- Fin contenido de la pagina  -->
</td>
</tr>
</table>
<%@ include file = "/web/includes/footer_new.jsp"%>
