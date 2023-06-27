<%@ page import = "java.io.*" %>
<%@ include file = "/tipoMedio.jsp"%>
<%@ include file = "/modulo/includes/env.jsp"%>
<% 
showVolverMenu = false; 
showInfoUser = false; 
showExit = false; 
session.setAttribute("subApp", "modulo");
%>
<% isTimming = (request.getParameter("data")==null ? false : true);%>
<%@ include file = "/modulo/includes/header.jsp"%>
<%@ include file = "/modulo/includes/top.jsp"%>  

<form name="login" action="/AutoconsultaWeb/modulo/pages/passwordLogin.jsp">

<table border="0" cellpadding="0" cellspacing="0" width="80%">
	<tr>
		<td class="textobold" align='center' colspan='2'>
			<font size='6'><bean:message key="label.araucana"/></font> <br>
		</td>
    </tr>	
    <tr>
		<td class="texto" align='center'>
			<font size='4'><bean:message key="login.user.just"/></font><br>  
			<input type="text" name="j_username" readonly="true" class="bigfield" size='15' maxlength='9'/>

		</td>
		<td class="texto" align='center'>
			<br>
			<%	String form = "login"; %>
			<%	String field = "j_username"; %>
			<%@ include file = "/modulo/includes/botoneraRut.jsp"%>
		</td>
    </tr>
</table>
</form>



<%@ include file = "/modulo/includes/footer.jsp"%>

<script>
	<%
	String msg="";
	if("user".equals(request.getParameter("error")))
		msg="El Usuario y/o Contraseña no son válidos";
	if("app".equals(request.getParameter("error")))
		msg="Usted no está autorizado para ingresar al Sistema";
	if("sess".equals(request.getParameter("error")))
		msg="Su sesión ha expirado o usted no está Autentificado";
	if (!msg.equals("")) {
	out.print("alert('"+msg+"');");
	}
	
	%>
</script>