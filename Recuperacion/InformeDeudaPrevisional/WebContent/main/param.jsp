<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME DEUDA PREVISIONAL VIGENTE</title>
</head>
<body>
	<p class="titulo">INFORME DEUDA PREVISIONAL VIGENTE</p>
	
		<form action="seleccion.do" method="POST">
				<table class="tabla-creditos">
				<tr>
					<td class="certificadoLeft">Ingrese&nbsp;RUT&nbsp;Empresa:</td>
					<td class="certificadoLeft"><input type="text" name="rutEmpresa" id="rutEmpresa" value=""> </td>
					<td class="certificadoLeft">&nbsp;&nbsp;<input class="boton" id="generar" type="submit" value="Buscar Deuda"  />&nbsp;</td>

				</tr>
				</table>
			</form>

	<script type="text/javascript">
		$(document).ready(
				function() {
					
					$("#rutEmpresa").Rut({
						format_on : 'keyup',
						on_error: function(){ alert('El RUT Empresa ingresado es incorrecto'); }
					});

					

				}); // fin del document ready
				
	</script>
</body>
</html>