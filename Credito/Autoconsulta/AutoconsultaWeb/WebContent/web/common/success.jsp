<%@ include file = "/web/includes/env.jsp"%> 
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>
    
<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'>
<tr>
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td> 
<td valign='top'>

<!-- Begin de la pagina particular -->

<div class="titulo"><bean:message key="message.success.title"/></div><br>
<div class="texto"><font color=red><bean:message key="message.success"/></font></div>

<logic:present name="message.success">
<div class="texto"><bean:write name="message.success"/></div>
</logic:present>


<!-- End de la pagina particular -->
 
</td> 
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>