<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME COTIZACIONES PAGADAS</title>
<script language="JavaScript" type="text/javascript">
	function aceptar(id, periodo, rutemp, oficina, sucursal){
		window.location="<%=request.getContextPath() %>/deuda.do?id=" + id + "&periodo=" + periodo + "&rutEmpresa=" + rutemp + "&oficina=" + oficina + "&sucursal=" + sucursal;
	}
</script>
</head>
<body>
	<p class="titulo">INFORME COTIZACIONES PAGADAS</p>
	<div class="certificadoAfiliacion">
		<logic:equal name="error" value="0">
			<blockquote>

				<p class="certificadoLeft">
					Seleccione una oficina/sucursal de la lista: <br>
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
						<td class="certificadoLeft">${lista.rutEmpresa}-${lista.dvEmpresa}</td>
						<td class="certificadoLeft">${lista.razonSocial}</td>
						<td class="certificadoLeft"><a href="#" onclick="aceptar('${lista.id}', '${lista.periodo}', '${lista.rutEmpresa}', '${lista.oficina}', '${lista.sucursal}');">${lista.nombreOficina}</a></td>
						<td class="certificadoLeft"><a href="#" onclick="aceptar('${lista.id}', '${lista.periodo}', '${lista.rutEmpresa}', '${lista.oficina}', '${lista.sucursal}');">${lista.nombreSucursal}</a></td>
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


	<script type="text/javascript">

	$("tr").not(':first').hover(
  		function () {
    		$(this).css("background","#F2F3F4");
  		}, 
  		function () {
    		$(this).css("background","");
  		}
	);

	</script>
</body>
</html>