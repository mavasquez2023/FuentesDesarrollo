
<%@page import="cl.jfactory.planillas.service.util.Utiles"%>
<%@page import="cl.liv.comun.utiles.PropertiesUtil"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="cl.liv.archivos.comun.txt.ManejoArchivoTXT"%>
<%@page import="cl.liv.export.comun.util.Funciones"%>
<%@page import="cl.liv.export.comun.util.SessionUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="cl.liv.export.txt.util.xml.LectorTXTConfig"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.export.txt.util.PropertiesTXTUtil"%>
<%


String path = PropertiesUtil.propertiesNominas.getString("config.output.nomina.path")+"/"+Utiles.getPeriodoActual()+"/";

File[] entidades = new File(path).listFiles();
for(int i=0; i< entidades.length; i++){
	File[] archivos = new File(entidades[i].getAbsolutePath()).listFiles();
	if(archivos.length == 0){
		%>
			entidad sin nóminas:<%=entidades[i].getName() %><br/>
		<%
	}
}

%>
