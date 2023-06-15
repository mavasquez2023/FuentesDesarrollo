<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true" />
<link rel="stylesheet" href="../../css/prepago.css">
<meta http-equiv="Content-Type" content="text/html; charset=utf8">
<title>Certificado Prepago</title>
</head>
<body onbeforeunload="showLoading();">
	<div>
		<p class="titulo texto-centrado">Certificado de Prepago de Crédito (Ley 20.130)</p>
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