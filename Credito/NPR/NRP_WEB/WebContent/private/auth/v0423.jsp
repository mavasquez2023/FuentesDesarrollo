
<%@page import="cl.liv.comun.utiles.PropertiesUtil"%>
<%@page import="java.io.File"%>
<%

String ruta = request.getParameter("f");
System.out.println("archivo -> "+ ruta);

if(
		new File(ruta).delete()
){

%>OK <%
}
else{
	%>NO OK <%
	}

%>
