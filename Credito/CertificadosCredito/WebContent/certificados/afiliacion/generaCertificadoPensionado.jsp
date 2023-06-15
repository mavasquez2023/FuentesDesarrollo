<%@ include file="../../comun/headerJsp.jsp"%>
<html>

<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<title>Certificado Afiliacion</title>
<link rel="stylesheet" href="../../css/certificado.css">
</head>
<body>
	<p class="titulo">Certificado de Afiliaci&oacute;n</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>
				<p class="certificadoLeft">
					Caja de compensación La Araucana certifica que ${nombreAfiliado} ,
					RUT N° ${rut} se encuentra vigente como ${rol} desde el ${fechaRol}.<br>
					<br>Se extiende el presente certificado a petición
					del interesado, para los fines que estime convenientes. <br>
					<br>
				</p>
				<p class="certificadoLeft">
					Santiago, ${fechaEmision}.
				</p>
				<br>
				<hr>
				<br>
			<div class="certificadoRight">
			<form action="<%=request.getContextPath() %>/satelites/afiliacion/cargaCertificado.do" target="_blank">
				<input class="boton" id="generar" type="submit"
				value="Generar certificado">
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


	<!-- 	<script>
		$(document).ready(function() {
			$("#generar").click(function() {
				$("#generar").attr("disabled", "disabled");

			});
		});
	</script> -->
</body>
</html>