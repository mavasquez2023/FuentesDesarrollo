<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page isErrorPage="true" %>
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="theme/Master.css" rel="stylesheet" type="text/css">
<TITLE>error</TITLE>
</HEAD>
<BODY>
<b>Exception:</b><br> 
<%= exception.toString() %>
<input type="button" name="back" value="Volver" onclick="javascript:history.back()"/>
</BODY>
</HTML>
