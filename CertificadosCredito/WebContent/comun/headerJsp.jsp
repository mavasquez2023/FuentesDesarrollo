<!DOCTYPE html><%@page
	language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><% response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
%><%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%><%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%><%@ taglib uri="/WEB-INF/tld/c-rt.tld" prefix="c"%><%@ taglib uri="/WEB-INF/tld/c.tld" prefix="core"%><%@ taglib uri="/WEB-INF/tld/fmt-rt.tld" prefix="fmt"%><%@ taglib uri="/WEB-INF/tld/fmt.tld" prefix="f"%><f:setLocale value="es_CL"/><fmt:setLocale value="es_CL"/>