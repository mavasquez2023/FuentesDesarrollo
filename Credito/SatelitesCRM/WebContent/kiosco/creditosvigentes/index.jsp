<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<%@ include file="../../comunKiosco/header.jsp"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/cssKiosco/certificado.css">
<title>Certificado Créditos Vigentes</title>
<!-- <style>
.cert{display:none;}
@media print{
	#content{width:612px; height:792px;}
		
	#datosAfiliado, .container-toolbar, .link_cert{display:none;}
	.cert{display:block;}
	
	.print_logo{text-align: right;}
	.print_logo img{width:200px; height:50px;}
	
	.texto_head{margin:20px 0;}
	
	.print_footer{margin:20px 0;}
	#fecha_firma{text-align:right; margin:10px 0px;}
	#fecha_firma * {display:inline; vertical-align:middle;}
	#fecha_cert{margin-right: 140px;}
	#firma_cert img{width:220px; height:85px;}
	
	#validacion_cert{position:fixed; bottom:0px;}
	
}
</style> -->
</head>
<body>
<div id="content">
		<div class="cert print_logo"></div>
		<p class="titulo">Certificado Créditos Vigentes</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>
		</div>
<!-- 		<div class="cert texto_head">
		Se certifica que <strong id="nom_cert"></strong> , Rut <strong id="rut_cert"></strong> registra los siguientes créditos vigentes.
		</div> -->
		<table>
			<tr>
				<th class="link_cert"></th>
				<th>Folio</th>
				<th>Monto Solicitado</th>
				<th>Fecha Otorgamiento</th>
				<th>Cuota a Descuento</th>
				<th>Plazo</th>
				<th>Gastos de Cobranza</th>
				<th>Rol asociado</th>
			</tr>
		</table>
		<form id="genCertForm" name="genCertForm" target="iframeLoad" action="<%=request.getContextPath() %>/kiosco/creditosvigentes/listadoCreditos.do" method="POST">
			<input type="hidden" name="rut" value="${rut}"> 
			<input type="hidden" name="origen" value="kiosco">
			<input type="hidden" name="accion" value="imprimirReporte">
		</form>
<!-- 		<div class="cert print_footer">
			<div id="text_footer">
				Se extiende el presente certificado a petición del interesado para los fines que estime convenientes, sin ulterior responsabilidad de La Araucana C.C.A.F.
			</div>
			<div id="fecha_firma">
				<div id="fecha_cert"></div>
				<div id="firma_cert"></div>
			</div>
		</div>
		<div id="validacion_cert" class="cert">
			Código validación: <span></span>, ingrese a www.laaraucana.cl
		</div> -->
</div>	
<jsp:include page="../../comunKiosco/botoneraSemi.jsp" flush="true" />	
	<script>
				
		$(document).ready(function(){
		$("#btnImprimir").css("display", "none");
		$.get("getContratos.do",{rut:"${rut}"},function(data){
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
