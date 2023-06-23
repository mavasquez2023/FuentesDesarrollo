<%@ page import="cl.araucana.spl.base.Constants" %>
<% response.setContentType("text/html; charset=" + Constants.CHARSET); %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>

<fmt:setBundle basename="cl.araucana.spl.resources.ApplicationResources" scope="application" />
<%	pageContext.setAttribute("datepattern", Constants.DEFAULT_DATE_PATTERN); %>
<%	pageContext.setAttribute("datetimepattern", Constants.DEFAULT_DATETIME_PATTERN); %>
<%	pageContext.setAttribute("jscalendarpattern", Constants.JSCALENDAR_PATTERN); %>
<%  pageContext.setAttribute("dateNow", new java.util.Date(System.currentTimeMillis())); %>

<fmt:setLocale scope="session" value="es_CL" />





