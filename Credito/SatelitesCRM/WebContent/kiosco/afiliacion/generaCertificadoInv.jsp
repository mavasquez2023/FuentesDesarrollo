<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comunKiosco/header.jsp" flush="true" />
<title>Certificado Afiliacion</title>
<link rel="stylesheet" href="../../cssKiosco/certificado.css">
</head>
<body>
<div id="content">
	<p class="titulo">Certificado de Afiliaci&oacute;n</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">
					Caja de compensación La Araucana certifica que ${nombreAfiliado}, RUT N° ${rut} se mantiene inactivo. 
					Se extiende el presente certificado a petición del interesado, para los fines que estimen convenientes.
					<br>
					<br>
				</p>
				<p class="certificadoLeft">
					Santiago, ${fechaEmision}.
				</p>
				<br>
				<hr>
				<br>
			<div class="certificadoRight">
			<form id="genCertForm" name="genCertForm" action="<%=request.getContextPath() %>/kiosco/afiliacion/cargaCertificadoInv.do"  method="post" target="iframeLoad">
				<input type="hidden" name="origen" value="kiosco">
			</form>
			</div>
			</blockquote>
		</logic:equal>
		<logic:equal name="error" value="1">
		
			No es posible generar certificado en este momento.
		
		</logic:equal>

		<logic:equal name="error" value="2">
		
			Primero debe seleccionar un rut de la lista.
			<br>
			<br>
			<input type="button" class="button" value="volver" onclick="history.back()"/>
		</logic:equal>
	</div>
</div>
<!-- Fin Contenido -->
<jsp:include page="../../comunKiosco/botoneraSemi.jsp" flush="true" />
<script>
$(document).ready(function(){
	configureScroll("iframeLoad", "content", "imprimeOff", "cargando");
});
</script>

</body>
</html>