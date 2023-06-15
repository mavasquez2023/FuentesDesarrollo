<%  
   session.setAttribute("subApp", "web");
   // request.getRequestDispatcher( "/web/Welcome.do" ).forward( request, response );
  
%>

<script language="Javascript">
	this.location.href="/AutoconsultaWeb/web/satelitesRedir.do?navurl=sat.creditosvigentes";
</script>