<%@page import="cl.liv.export.comun.util.SessionUtil"%>
<%@page import="cl.liv.comun.utiles.PropertiesUtil"%>
<%@page import="cl.liv.comun.utiles.UtilesComunes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cl.jfactory.planillas.service.helper.ConfiguradorHelper"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Set"%>
<%@page import="java.io.File"%>
<%@page import="cl.liv.archivos.comun.ArchivosUtiles"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%


SessionUtil.txts.put(request.getParameter("formato").toString(), null);


%>
