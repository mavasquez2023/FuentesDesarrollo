<%@ page import = "java.io.*" %>
  
<%@ include file = "/modulo/includes/env.jsp"%> 

<% showVolverMenu = false; %>
<% showInfoUser = false; %> 
<% showExit = false; %>
<% isTimming = (request.getParameter("data")==null ? false : true); %>
<% String user = request.getParameter("j_username"); %>
<%@ include file = "/modulo/includes/header.jsp"%>
<%@ include file = "/modulo/includes/top.jsp"%>

<form name="login" method="post" action="j_security_check">

<input type="hidden" name="j_username" value='<%=user %>' />

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td class="textobold" align='center' colspan='2'>
			<font size='6'><bean:message key="label.araucana"/></font> <br>
		</td>
    </tr>
    <tr>
		<td class="texto" align='center'>

			<font size='4'><bean:message key="login.password"/>:</font><br>
			<input name="j_password" type="password" id="j_password">	
						
		</td>
		<td class="texto" align='center'>
			<br>
			<%	String form = "login"; %>
			<%	String field = "j_password"; %>
			<%	String fieldUser = "j_username"; %>
			<%@ include file = "/modulo/includes/botoneraClave.jsp"%>
		</td>
    </tr>




</table>
</form>

<%@ include file = "/modulo/includes/footer.jsp"%>



