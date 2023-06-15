<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>Carta Pagos en Exceso</title>
<link rel="stylesheet" href="../css/certificado.css">
<script language="JavaScript" type="text/javascript">
	function aceptar(rutEmpresa){
		window.location="<%=request.getContextPath() %>/main/crm.do?rut=" + rutEmpresa;
	}
</script>
</head>
<body>
	<p class="titulo">Carta Pagos en Exceso Cuotas de Crédito</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>

				<p class="certificadoLeft">
					Seleccione una empresa de la lista: <br>
					<br>
				</p>
				<p>
					<table align="center" width="70%">
					<tr>
						<th>RUT EMPRESA</th>
						<th>RAZON SOCIAL</th>
						<th>OFICINA</th>
						<th>SUCURSAL</th>
					</tr>
					<logic:iterate id="lista" name="empresas"> 
					<tr>
						<td><a href="#" onclick="aceptar('${lista.rutEmpresa}-${lista.dvRutEmpresa}');">${lista.rutEmpresa}-${lista.dvRutEmpresa}</a></td>
						<td class="certificadoLeft">${lista.razonSocial}</td>
						<td class="certificadoLeft">${lista.nombreOficina}</td>
						<td class="certificadoLeft">${lista.sucursal}</td>
					</tr>
				</logic:iterate>
				</table>
				</p>
				
				<br>
				<hr>
				<br>
			<div class="certificadoRight">

			
			<!--  form action="<%=request.getContextPath() %>/certificados/afiliacion/cargaCertificado.do" target="_blank"-->
			</form>
			</div>
			</blockquote>
		</logic:equal>
		<logic:equal name="error" value="-1">
		
			No se han encontrado datos para la consulta realizada.
			
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