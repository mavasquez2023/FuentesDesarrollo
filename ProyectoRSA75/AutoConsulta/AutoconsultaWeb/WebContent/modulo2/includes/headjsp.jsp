<%-- 
    Document   : headjsp
    Created on : 10-04-2012, 01:39:29 PM
    Author     : desajee
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
String float5FormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/float5");

String float3FormatPatt = com.schema.util.FileSettings.getValue(cl.araucana.common.env.AppConfig.getInstance().settingsFileName,
          "/application-settings/autoconsulta/format/float3");

%>
<%@ include file = "/tipoMedio.jsp"%>