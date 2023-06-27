<%@ include file = "/modulo/includes/env.jsp"%> 
<%@ include file = "/modulo/includes/header.jsp"%>
<% title = "Operación Realizada"; %>
<%@ include file = "/modulo/includes/top.jsp"%>

<br>
<br> 
<div class="texto" align='center'><font color=red size='3'><bean:message key="message.success"/></font></div>

<logic:present name="message.success">
<div class="texto"><font size='3'><bean:write name="message.success"/></font></div>
</logic:present>
<%@ include file = "/modulo/includes/footer.jsp"%>