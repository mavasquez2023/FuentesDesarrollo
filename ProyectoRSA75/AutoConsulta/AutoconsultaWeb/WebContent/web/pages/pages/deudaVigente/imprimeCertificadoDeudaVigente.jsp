<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
          
<table border="0" cellpadding="0" cellspacing="0" width=600" height='100%'>
	<tr>
		<td valign='top'>
			<% session.removeAttribute("comercial"); %>
			<%@ include file = "/web/pages/deudaVigente/cuerpoDeudaVigente.jsp"%>
		</td>
	</tr>
</table>

     