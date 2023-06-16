<%@ include file="/comun/header.jsp"%>
<html>
<head>
</head>
<body >
<script type="text/javascript">
	<!-- Redirecciona la pagina contenedora del iframe (Autoconsulta) -->
		window.parent.location ='<%=request.getContextPath()%>/init.do';
	</script>
</body>
</html>