<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/headerPrint.jsp"%>

<table border="0" cellpadding="0" cellspacing="0" width=600" height='100%' >
	<tr>
		<td valign='top'>
			<% session.removeAttribute("comercial"); %>
			<%@ include file = "/web/pages/creditoVigente/cuerpoCreditoVigente.jsp"%>
		</td>
	</tr>
</table>

     