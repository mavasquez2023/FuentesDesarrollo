<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
</head>
<body >
<script type="text/javascript">
	<!-- Redirecciona la pagina contenedora del iframe (Autoconsulta) -->
		window.parent.location ='${destino}';
	</script>
</body>
</html>