<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/cssKiosco/certificado.css">
<title>Certificado Créditos Cancelados</title>
</head>
<body>
<div id="content">
	<p class="titulo">Certificado Créditos Cancelados</p>
	<div id="datosAfiliado">
		<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>
	</div>
	<table>
		<tr>
			<th></th>
			<th>Folio</th>
			<th>Monto Solicitado</th>
			<th>Fecha Otorgamiento</th>
			<th>Fecha Cancelación</th>
			<th>Monto Cuota</th>
			<th>Plazo</th>
		</tr>
		
	</table>
	<form id="genCertForm" name="genCertForm" target="iframeLoad" action="listadoCreditos.do" method="POST">
	<!-- <form id="genCertForm" name="genCertForm" target="_blank" action="listadoCreditos.do" method="POST"> -->
		<input type="hidden" name="rut" value="${rut}"> 
		<input type="hidden" name="accion" value="imprimirReporte">
		<input type="hidden" name="origen" value="kiosco">
	</form>
</div>
<jsp:include page="../../comunKiosco/botoneraSemi.jsp" flush="true" />	
	<script>
		$(document).ready(function(){
		$("#btnImprimir").css("display", "none");
		$.get("getCreditos.do",{rut:"${rut}"},function(data){
			$("table tbody").append(data);
			$('#loading').remove();
			$("#btnImprimir").css("display", "");
		});
		
		$('#loading').show();
		
		configureScroll("iframeLoad", "content", "imprimeOff", "cargando");
		
		});
	</script>
</body>
</html>