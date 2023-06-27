<%  
   session.setAttribute("subApp", "web");
   // request.getRequestDispatcher( "/web/Welcome.do" ).forward( request, response );
  
%>

<script language="Javascript">
	this.location.href="/AutoconsultaWeb/web/cotizacionesPagadas.do";
</script>