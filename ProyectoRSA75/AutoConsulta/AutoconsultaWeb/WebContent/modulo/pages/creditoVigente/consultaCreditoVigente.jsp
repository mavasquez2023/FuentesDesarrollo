<%@ include file = "/modulo/includes/env.jsp"%>
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Créditos Vigentes"; %>
<%@ include file = "/modulo/includes/top.jsp"%>
 
<br>
<br>

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td align="left">
<%@ include file = "/modulo/pages/creditoVigente/cuerpoCreditoVigente.jsp"%>
		</td>
	</tr>
</table>

<br>
<br>
<div align='center'>
	<html:link page='/showCreditoVigente.do'>
	<html:img page="/images/imprimir.gif" border="0" alt="Imprimir este resultado"/>
	</html:link>
</div>

<%@ include file = "/modulo/includes/footer.jsp"%>

