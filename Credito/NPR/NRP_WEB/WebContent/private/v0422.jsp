<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%

Set threadSet = Thread.getAllStackTraces().keySet();
Thread[] threadArray = (Thread[])threadSet.toArray(new Thread[threadSet.size()]);
%>
<table border="1"><tr><th>Name</th><th>Status</th></tr>
<%
for(int i=0; i< threadArray.length; i++){
		%><tr><td><%=threadArray[i].getName()%></td><td><%=threadArray[i].getState()%></td></tr><%
	
}

%>
</table>