
<%@page import="cl.liv.export.comun.util.Funciones"%>
<%@page import="cl.liv.export.comun.util.SessionUtil"%>
<%@page import="java.util.HashMap"%>
<%@page import="cl.liv.export.txt.util.xml.LectorTXTConfig"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.export.txt.util.PropertiesTXTUtil"%>
<%

String folder = PropertiesTXTUtil.getProperty("export.path.resources.txt")+"/txts/";
File directorio = new File(folder);
System.out.println("directorio: "+ directorio.exists());
File[] archivos = new File(folder).listFiles();
System.out.println("archivos: "+ archivos);
ArrayList formatos = new ArrayList();
for(int i=0; i< archivos.length; i++){
	String txt = archivos[i].getName();
	
	LectorTXTConfig.getDataXML(txt);

	HashMap conf = null;
	conf = (HashMap)SessionUtil.txts.get(txt);

if (conf == null) {
	System.out.println("ERROR AL OBTENER CONFIGURACION DEL REPORTE. ["+txt+"]");
	
}
String prefijo = "exportlivtxt";
if(! conf.get("key").toString().equals( Funciones.getMD5(prefijo+txt))){
	System.out.println("key invalida ["+txt+"]");

	%>

	key invalida [<%=txt%>]<br>

	<%
	
}

}



%>
