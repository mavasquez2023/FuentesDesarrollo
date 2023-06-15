
	<%@page import="cl.jfactory.planillas.web.SessionUsuario"%>
<% 
	
	
	
	SessionUsuario miSessionAux =  ((SessionUsuario) request.getSession().getAttribute("usuario"));
	
	if ( miSessionAux!= null  ){ %>

		<!DOCTYPE html>
		<html lang="en">

		<head>
			<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
			<script src="bootstrap-3.3.7-dist/js/jquery.min.js"></script>
			<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
		</head>
		<body>

		<nav class="navbar navbar-default">
		  <div class="container-fluid">

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			  
			  <ul class="nav navbar-nav navbar-right">
				<li><a href="LogoutServlet"><span class="glyphicon glyphicon-off"></span> Cerrar Sesi&oacute;n</a></li>
			  </ul>
			  <ul class="nav navbar-nav navbar-right" style="font-size:11px;">
				<% if( miSessionAux!= null && miSessionAux.tieneElPerfil("administrador") || miSessionAux.tieneElPerfil("supervisor") ) {%>
				<li id="menu_proceso"><a href="procesos.jsp"> <span class="glyphicon glyphicon-triangle-right"></span> Workflow Procesos </a></li>
				<% }%>
				<% if( miSessionAux!= null && miSessionAux.tieneElPerfil("administrador") ) {%>
				<li id="menu_configurador"><a href="configuracionEntidad.jsp"> <span class="glyphicon glyphicon-edit"></span> Configurador de NRP </a></li>
				<% }%>
				<li id="menu_ver_nominas" ><a href="verNominasPorEntidad.jsp"  > <span class="glyphicon glyphicon-folder-open""></span>&nbsp; N&oacute;minas Por Entidades </a></li>
		
				<% if( miSessionAux!= null && miSessionAux.tieneElPerfil("administrador") ) {%>
				<li id="menu_opciones"><a href="opciones.jsp"> <span class="glyphicon glyphicon-cog"></span> Utilidad </a></li>
				<% }%>

				<% if( miSessionAux!= null && miSessionAux.tieneElPerfil("administrador") || miSessionAux.tieneElPerfil("supervisor") ) {%>
				<li id="menu_pdf"><a href="http://146.83.1.129:9080/PDFServiceWeb/" target="_blank"> <span class="glyphicon glyphicon-globe"></span> PDF Services </a></li>
				<% }%>
				
				<% if( miSessionAux!= null && miSessionAux.tieneElPerfil("administrador") || miSessionAux.tieneElPerfil("supervisor") ) {%>
				<li id="menu_pdf"><a href="http://serv-nrp:9080/ResultadoNRP/" target="_blank"> <span class="glyphicon glyphicon-stats"></span> Resultado Procesos </a></li>
				<% }%>
			  </ul>
			</div>
		  </div>
		</nav>
		  <div style="float:right">Bienvenid@, <b><%=miSessionAux.getNombreUsuario()%> &nbsp;</b></div>

		</body>
		</html>


	<% } %>
