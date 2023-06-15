<%@page import="java.util.ArrayList"%>
<%@page import="cl.jfactory.planillas.service.helper.ConfiguradorHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%
	HashMap parameters = new HashMap();	
	HashMap data = new HashMap();
	((HashMap)parameters).put("uri_reporte", ((HashMap)parameters).get("NOM_1234_test_acento"));
	((HashMap)parameters).put("id_atributo", "mapeo_detalle");
	ArrayList arrayDisponiblesHeader =  new ConfiguradorHelper().obtenerColumnasReporte(data, parameters);
	System.out.println(arrayDisponiblesHeader);
%>
