
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
String folder = PropertiesTXTUtil.getProperty("export.path.resources.txt")+"/txts/";
File directorio = new File(folder);
System.out.println("directorio: "+ directorio.exists());
File[] archivos = new File(folder).listFiles();
System.out.println("archivos: "+ archivos);
boolean formatosInvalidos = false;
ArrayList formatos = new ArrayList();
for(int i=0; i< archivos.length; i++){
	System.out.println(archivos[i].getName());
	String txt = archivos[i].getName();
	
	LectorTXTConfig.getDataXML(txt);

	HashMap conf = null;
	conf = (HashMap)SessionUtil.txts.get(txt);

if (conf == null) {
	System.out.println("ERROR AL OBTENER CONFIGURACION DEL REPORTE. ["+txt+"]");
	
}
String prefijo = "exportlivtxt";
System.out.println("->"+Funciones.getMD5(prefijo+txt));
if(! conf.get("key").toString().equals( Funciones.getMD5(prefijo+txt))){
	formatosInvalidos = true;
	formatos.add(archivos[i]);
	System.out.println("key invalida");
	String archivo = ManejoArchivoTXT.getFileContentAsString(archivos[i].getAbsolutePath()+"/conf.xml");
	archivo = archivo.replaceAll(conf.get("key").toString(), Funciones.getMD5(prefijo+txt));
	PrintWriter pw = ManejoArchivoTXT.getOpenedFileToWrite(archivos[i].getAbsolutePath()+"/conf.xml");
	ManejoArchivoTXT.putLineFromFileOpened( pw , archivo);
	ManejoArchivoTXT.closeFileToWrite(pw);
}
else{
	System.out.println("key_ok");
}

System.out.println("\n\n");

}


if(formatosInvalidos){
	
	%>

	hay formatos invalidos<br>

	<%
	for(int i=0; i < formatos.size(); i++){
		System.out.println("formato->"+ ((File) formatos.get(i) ).getName() );


		%>

		formato <%=((File) formatos.get(i) ).getName()%><br>

		<%
	}
}
else{
	%>

	No hay formatos invalidos<br>

	<%
}

%>
