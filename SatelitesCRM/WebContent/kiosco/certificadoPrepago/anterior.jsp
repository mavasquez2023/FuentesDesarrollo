<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="../../cssKiosco/prepago.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado Prepago</title>
</head>
<body onbeforeunload="showLoading();">
	<div>
		<p class="titulo texto-centrado">Certificado Prepago</p>
	</div>
	<div id="loading" style="position:fixed; top:25%; left:47%; display:none; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
	</div>
	<form action="listado.do" id="formulario">
		
	</form>
	<script type="text/javascript">
	
		$(document).ready(function() {
			$("#formulario").submit();
		});
		
		function showLoading() {
			$('#loading').show();
		}
	</script>
</body>