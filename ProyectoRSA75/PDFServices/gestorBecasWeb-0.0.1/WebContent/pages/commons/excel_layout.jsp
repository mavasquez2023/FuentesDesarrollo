<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="application/vnd.ms-excel;charset=UTF-8"%>
<% SimpleDateFormat formatt = new java.text.SimpleDateFormat("dd-MM-yyyy", new Locale("es", "ES")); %>
<% String nombreArchivo = String.valueOf(session.getAttribute("nombreInfo")); %>
<% response.setHeader ("Content-Disposition", "attachment; filename="+nombreArchivo + formatt.format(new Date()) +".xls"); %>

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<%@page import="java.util.Date"%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="title.application"/></title>	
		<style type="text/css">		
			<jsp:include flush="true" page="/styles/excel.css"/>
		</style>
	</head>
	
	<body>
		<table border="0">
			<tr><td colspan="5">
					&nbsp;</td></tr>
			
			<logic:present name="page.title">	
				<tr><td colspan="5">
					<div class="titulo"><bean:message key='<%= (String)session.getAttribute("page.title") %>'/></div>
				</td></tr>
				<tr><td colspan="5">&nbsp;</td></tr>
			</logic:present>	
		</table>

		<div class="body_zone" align="center">
			<tiles:insert attribute="body"/>
		</div>

	</body>
	
</html>