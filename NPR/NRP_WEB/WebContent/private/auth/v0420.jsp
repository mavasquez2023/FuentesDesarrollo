<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

	File f = new File(request.getParameter("ruta"));

	if(f.isDirectory())
		ArchivosUtiles.eliminarDirectorioRecursivo(request.getParameter("ruta"));
	else{
		f.delete();
	}

%>
