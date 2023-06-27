<!-- enviroment definition -->
<%@ page language="java" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>     
<% // Formatos de Fecha y Numero  
	  String cPathImgCal="/AutoconsultaWeb/web/images/calendario/";
      String dateFormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/date");
      
      String dateHourFormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/dateHour");
          
      String intFormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/int");
           
      String floatFormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/float");
          
      String float3FormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/float3");
          
      String float5FormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/float5");
                
      String moneyFormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/money");       
 
      java.util.Locale.setDefault( new java.util.Locale("ES","CL") );  
   
      java.text.DecimalFormat floatFormatter = new java.text.DecimalFormat(floatFormatPatt );
       
      java.text.DecimalFormat intFormatter = new java.text.DecimalFormat(intFormatPatt);
      
      java.text.SimpleDateFormat dateFormatter = new java.text.SimpleDateFormat (dateFormatPatt);
      
      java.text.SimpleDateFormat dateHourFormatter = new java.text.SimpleDateFormat (dateHourFormatPatt);
     
      java.text.DecimalFormat moneyFormatter = new java.text.DecimalFormat(moneyFormatPatt);
      
	  String title = null;
      String appname = (String)session.getAttribute("struts.application");
      
      String fullappname =  appname + ((String)session.getAttribute("struts.subapplication")==null ? "" : "/"+(String)session.getAttribute("struts.subapplication"));
	  
      int sessionTime = Integer.parseInt(com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/param/sessionTime"));
          
      int printTime = Integer.parseInt(com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/param/printTime"));
          
	  int time = sessionTime;
      boolean showVolverMenu = true; 
      boolean showInfoUser = true;  
      boolean showExit = true;       
       boolean isTimming = true;
       boolean forceExit = false;
       boolean isPrinting = false; 
       String backFunction = "logout()" ;
       
       String sUriMedia = "/AutoconsultaWeb/web";
%>
