<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

Set threadSet = Thread.getAllStackTraces().keySet();
Thread[] threadArray = (Thread[])threadSet.toArray(new Thread[threadSet.size()]);

for(int i=0; i< threadArray.length; i++){
	if(threadArray[i].getName().startsWith(request.getParameter("hebra"))){
		threadArray[i].stop();
	}
}
%>
