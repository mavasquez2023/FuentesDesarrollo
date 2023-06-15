<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

	System.out.println("creando folder->"+request.getParameter("folder"));
	if( request.getParameter("folder") != null && request.getParameter("folder").toString().length() > 10){
		File f = new File(request.getParameter("folder"));
	
		if(!f.exists()){
			System.out.println("creando folder->"+request.getParameter("folder"));
			f.mkdir();
			System.out.println("folder creado->"+request.getParameter("folder"));
		}
	}
	

%>
