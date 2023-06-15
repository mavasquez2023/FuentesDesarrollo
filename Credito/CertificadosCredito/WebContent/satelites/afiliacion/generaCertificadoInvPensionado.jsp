<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true" />
<title>Certificado Afiliacion</title>
<link rel="stylesheet" href="../../css/certificado.css">
</head>
<body>
	<p class="titulo">Certificado de Afiliaci&oacute;n</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">
					Caja de compensación La Araucana certifica que ${nombreAfiliado}, RUT N° ${rut} estuvo vigente como ${rol } hasta el ${fechaTerminoRol}.  
					<br>
					<br>
				</p>
				<p class="certificadoLeft">
					Santiago, ${fechaEmision}.
				</p>
				<br>
				<hr>
				<br>
			<!-- div class="certificadoRight">
			<form action="<%=request.getContextPath() %>/certificados/afiliacion/cargaCertificadoInv.do" target="_blank">
				<input class="boton" id="generar" type="submit"
				value="Generar certificado">
			</form>
			</div-->
			</blockquote>
		</logic:equal>
		
		<logic:equal name="error" value="1">
			No es posible generar certificado en este momento.
		</logic:equal>

	</div>


	<!-- 	<script>
		$(document).ready(function() {
			$("#generar").click(function() {
				$("#generar").attr("disabled", "disabled");

			});
		});
	</script> -->
</body>
</html>