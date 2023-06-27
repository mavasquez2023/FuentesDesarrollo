<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html:html>
<HEAD> 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<LINK href="css/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<title>La Araucana C.C.A.F. - Mensaje (PDFServiceWeb)</title>
</HEAD>

<BODY>

	<%@ include file="header.jspf" %>
	
<CENTER>

<table width="767" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
					<%@ include file="contextUser.jspf" %>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td class="contextUser" width="5%">
							Contexto:
						</td>
							<td class="contextNav" align="left">
							<a href="<%= cPath %>/LoadConfUser.do" >Sistemas</a> 
							<%@ include file="p.jspf" %>
							<a href="<%= cPath %>/LoadScripts.do?sysID=${sistema.identificador}" >Scripts</a>
							<%@ include file="p.jspf" %>
							<a href="ViewTasks.do">Tareas</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table> 
	<br/>
	<br/>
	<br/>
	<table class="tdcuerpotabla" width="500">
		<tr>
			<td align="center">
				<logic:present name="messageNoStart" scope="request">
					<br/>
					Estimado Usuario(a):<br/>
					Existe una instancia del script <br/>
					<b>${idscript}</b> <br/>
					en ejecución <br/>
					y este no permite múltiples ejecuciones. 
					<br/>
					<br/>
					Favor espere a que termine su ejecución e intételo nuevamente.
					<br/>
				</logic:present>
				<logic:present name="messageStart" scope="request">
					El script <br/>
					<b>${idscript}</b> <br/>
					Se ha iniciado con el id: ${SID}
				</logic:present>
				
				
			</td>
		</tr>
	</table>
	<br/>
	<table  width="500" align="center">
		<tr>
			<td align="center">
		  		<input name="Submit4" type="submit"
						class="btn2" value="Aceptar"
						onclick="document.location.href='ViewTasks.do'" />
		  	</td>
		</tr>
	</table>
</CENTER>
</BODY>
</html:html>
