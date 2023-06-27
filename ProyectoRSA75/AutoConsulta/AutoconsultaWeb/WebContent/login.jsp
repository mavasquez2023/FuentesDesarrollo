  
<% 
	String error = request.getParameter("error");
	String subApp = (String)session.getAttribute("subApp");
	if (error!=null)
		error = "?error="+error;
	else
		error="";
%>

<script language="Javascript">
<% if ("web".equals(subApp)) { %>
	this.location.href = '/AutoconsultaWeb/web/pages/login.jsp<%= error %>';
<% } else { %>
	this.location.href = '/AutoconsultaWeb/web/pages/login.jsp<%= error %>';
<% } %>
</script>

