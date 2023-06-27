<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="css/Interna_Araucana.css" rel="stylesheet"
	type="text/css">
<TITLE>error500.jsp</TITLE>
</HEAD>
<BODY>

<%@ include file="header.jspf" %>
		<center>
		<br>
		<br>
		<br>
		
		<H3>Error 500</H3>
	
		<br>
	
		<table border="0" width="45%" class="textos-formcolor2">
		  <tr>
		    <td width="15%" rowspan="2">&nbsp;
		    	<img src="<%= cPath %>/images/exclaim.gif">
		    </td>
		    
		    <td width="85%">
				<p>
				   Nuestros sistemas han reportado la siguiente informaci&oacute;n:
				</p>
		    </td>
		  </tr>
		  <tr>
		    <td width="85%" >
				<p>
					<table class="textos-formcolor2">
						<tr>
							<td>
								Servidor
							</td>
							<td>
								<%= request.getServerName() %>:<%= request.getLocalPort()%>
							</td>
						</tr>
						<tr>
							<td>
								URL
							</td>
							<td>
								<%= request.getRequestURI()%>
							</td>
						</tr>
					</table>
				</p>
				 <p>
				 	<b>Contacte al administrador del sistema para mas detalles.</b>
				</p>
		    </td>
		  </tr>
		</table>
	</center>
</BODY>
</HTML>
