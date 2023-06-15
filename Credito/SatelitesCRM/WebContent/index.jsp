<%@page import="cl.laaraucana.satelites.webservices.utils.ServiciosConst"%>
<%@ include file="/comun/headerJsp.jsp"%>
<html>
<head>
</head>
<body >
<script type="text/javascript">
	<!-- Redirecciona la pagina contenedora del iframe (Autoconsulta) -->
		window.parent.location ='<%=ServiciosConst.URL_ACHOMEPAGE %>';
	</script>
</body>
</html>