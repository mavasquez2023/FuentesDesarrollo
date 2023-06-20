<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page 
    language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="javax.servlet.*, javax.servlet.http.*, cl.araucana.sv.ServicesFilter"
%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
	/*
	 *  Determines local HTTP redirector.
	 */

	String localRedirector = application.getInitParameter("localRedirector");
			
	if (localRedirector == null || localRedirector.equals("")) {
		String URL = request.getRequestURL().toString();
		String URI = request.getRequestURI();
		int index = URL.indexOf(URI);

		if (index > 0) {
			localRedirector =
					URL.substring(0, index) + request.getContextPath();
		}
	}
%>
    
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Pragma" content="no-cache">    
    
    <jsp:include page="/WEB-INF/meta_application.jsp" />
    
    <link href="/sv/theme/Master.css" rel="stylesheet" type="text/css" />
	
	<title>La Araucana C.C.A.F. - Servicios CP ...</title>

	<script language="JavaScript1.2">

		var localRedirector = "<%= localRedirector %>"

		function redirect(service) {
			url = localRedirector + "/router.do?service=" + service;

			window.location = url;
		}
	</script>
</head>

<body>
	<center>
	<img src="images/CP.jpg">
	</center>
	
	<%
		// Determines which services can be used by the user.
		ServicesFilter servicesFilter = new ServicesFilter(request);
		
		if (!servicesFilter.isOK()) {
			session.invalidate();
			
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("/notservice.jsp");
					
			dispatcher.forward(request, response);
			
			return;
		}
	%>

	<table border="0" cellpadding="0" cellspacing="0" width="100%" align="right">
		  <tr>
		    <td width="10%">&nbsp;</td>
		  
		    <td width="80%" align="right" valign="bottom">
		        <table border="0" cellpadding="0" cellspacing="0" width="100%">
		        	<tr>
		        		<td width="50%" align="left" height="50" valign="middle">
					  	    <form>
					  	        <input class="boton" type="button" value="Cambiar Clave" onclick="location.href='changePassword.jsp'; return false;"/>
					  	    </form>
					  	</td>

		        		<td width="50%" align="right" height="50" valign="middle">
					  	    <form>
					  	        <input class="boton" type="button" value="Cerrar Sesi&oacute;n" onclick="location.href='logout.do'; return false;"/>
					  	    </form>
					  	</td>
					</tr>
				</table>
		  	</td>
		  	
		  	<td width="10%">&nbsp;</td>
		  </tr>
	</table>

	<br>
	<br>
	<br>
	
		<table border="0" cellpadding="0" cellspacing="0" width="80%" align="center">
			  <tr>
			      <td width="10%"></td>
			  
			      <td width="80%">
				    <p align="center"><font size="+1">
				    	<a href="javascript:redirect('AC')">Aplicación CP #1 (CP)</a>
				    </font></p>
				    
				    <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Usted podr&aacute; ...
				    </p>
				    
				    <br>
				</td>
				
			      <td width="10%"></td>
			 </tr>

			  <tr>
			      <td width="10%"></td>
			  
			      <td width="80%">
				    <p align="center"><font size="+1">
				    	<a href="javascript:redirect('AC')">Aplicación CP #2 (CTCP)</a>
				    </font></p>
				    
				    <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Usted podr&aacute; ...
				    </p>
				    
				    <br>
				</td>
				
			      <td width="10%"></td>
			 </tr>

			  <tr>
			      <td width="10%"></td>
			  
			      <td width="80%">
				    <p align="center"><font size="+1">
				    	<a href="javascript:redirect('AC')">Aplicación CP #3 (Trabajador Independiente)</a>
				    </font></p>
				    
				    <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Usted podr&aacute; ...
				    </p>
				    
				    <br>
				</td>
				
			      <td width="10%"></td>
			 </tr>
		</table>
	<center>
		<p>
			<hr width="80%">
			
		    <font size="-1"> Cualquier consulta acerca de estos servicios por favor dirigirla a
		        <a href="mailto:serviciosweb@laaraucana.cl">serviciosweb@laaraucana.cl</a>   </font>
	   </p>
    </center>
</body>
</html>
