<%-- 
    Document   : headjsp
    Created on : 10-04-2012, 01:39:29 PM
    Author     : desajee
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
session.setAttribute("subApp", "mobile");
response.sendRedirect("/AutoconsultaWeb/mobile/Welcome.do");
%>

