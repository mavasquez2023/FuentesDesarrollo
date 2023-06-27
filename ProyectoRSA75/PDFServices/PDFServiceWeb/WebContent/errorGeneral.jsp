
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<link href="css/Interna_Araucana.css" rel="stylesheet" type="text/css" />
<TITLE>La Araucana C.C.A.F. - Informaci&oacute;n</TITLE>
</HEAD>

<body>
	<center>
		<%@ include file="header.jspf" %>
	
		<br>
		<br>
		<br>
		
		<H3>Estimado Usuario</H3>
	
		<br>
	
		<table border="0" width="45%" class="textos-formcolor2">
		  <tr>
		    <td width="15%" rowspan="2">&nbsp;
		    	<img src="<%= cPath %>/images/exclaim.gif">
		    </td>
		    
		    <td width="85%" align="center">
				<p>
				   Nuestros sistemas han reportado la siguiente informaci&oacute;n:
				</p>
		    </td>
		  </tr>
		  <tr>
		    <td width="85%" align="center">
				<p align="center">
					<b>
						<br/>
						<html:errors /><br/><br/>
					</b>
				</p>
				<p align="center">
					<h4>${nscript}</h4>
				</p>
				<p>
					Para volver al inicio presione <a href="${urlResponse}">AQUI</a>
				</p>
				 <p>
				 	Contacte al administrador del sistema para mas detalles.
				</p>
		    </td>
		  </tr>
		</table>
	</center>
</body>

</html:html>
