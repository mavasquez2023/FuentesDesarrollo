<%@ include file="/comun/header.jsp"%>
<html>
<head>
</head>
<body >
<script type="text/javascript">
	<!-- Redirecciona la pagina contenedora del iframe (Autoconsulta) -->
		window.parent.location ='<%=application.getInitParameter("HomePage") %>';
	</script>
</body>
</html>