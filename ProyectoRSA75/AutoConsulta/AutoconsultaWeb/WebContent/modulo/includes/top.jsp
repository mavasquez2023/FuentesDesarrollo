<script>
  
function logoff(){

		document.logout.submit();
		
}

</script> 


<table border="0" cellpadding="0" cellspacing="3" width="700">
  <tr valign="middle">
   <td width="20%"><html:img src="/AutoconsultaWeb/modulo/images/LogoAraucanaGreen.jpg" border="0"/></td>
   <td width="60%" class="textobold" align='center'>
   <% if (title==null) { %>
   		<html:img src="/AutoconsultaWeb/modulo/images/Bannerpaisaje.gif" border="0" width="600" height="80"/>
   <% } else { %>
        <font size='4'><%= title %></font>
   <% } %>   		
   </td>
   <td width="20%">
   <% if (title!=null) { %>
   		<html:img src="/AutoconsultaWeb/modulo/images/green.jpg" border="0"/>
   <% } %>
  </td>
  </tr>

   <% if (title!=null) { %>

	<tr valign="middle">
	<td colspan=3 height="2" bgcolor="black"></td>
	</tr>

	<tr valign="middle">
	<td align="left" width="20%">
	<% if ((isSessionActive && showExit) || forceExit ) { %>

	<form method="post" action="<%=request.getContextPath().toString()%>/ibm_security_logout" name="logout">
	
		<input type="hidden" name="logout" value="Logout" />	
		<input type="hidden" name="logoutExitPage" value="/modulo.jsp" />	
		<html:link styleClass="subopcion" href="javascript:logoff()">		
		<html:img src="/AutoconsultaWeb/modulo/images/salir.gif" border="0"/>
		</html:link>
		
	</form>	
	
	<% } %>
	</td>
	<td align="center" class="texto" width="60%">
	<% if (isSessionActive && showInfoUser) { %>
	<logic:present name='datosUsuario'>
	<font size='2'><bean:write name='datosUsuario' property='nombre'/></font> 
	</logic:present>
	<% } %>
	</td>
	<td align="right" width="20%">
	<% if (isSessionActive && showVolverMenu) { %>
	<html:link styleClass="subopcion" page="/Menu.do">
	<html:img src="/AutoconsultaWeb/modulo/images/volver.gif" border="0"/>
	</html:link>   
	<% } %>
	</td>



	</tr>
   <% } %>   		
    


</table>




 