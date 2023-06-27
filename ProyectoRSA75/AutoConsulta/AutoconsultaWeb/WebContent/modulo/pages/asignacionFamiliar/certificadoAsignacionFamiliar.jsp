<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Asignación Familiar"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
 
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td align="center">
<%@ include file = "/modulo/pages/asignacionFamiliar/cuerpoCertificadoAsignacionFamiliar.jsp"%>
		</td>
	</tr>
</table>

<br>
<br>
<div align='left'>
	<html:link page='/showCertificadoASFAM.do'>
	<html:img page="/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
	</html:link>
</div>

<%@ include file = "/modulo/includes/footer.jsp"%>

