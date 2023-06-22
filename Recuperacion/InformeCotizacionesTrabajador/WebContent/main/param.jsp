<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>INFORME COTIZACIONES PREVISIONALES</title>
</head>
<body>
	<p class="titulo">INFORME COTIZACIONES PREVISIONALES</p>
	
		<form action="cotizaciones.do" method="POST">
				<table class="tabla-creditos">
				<tr>
				<c:if test="${rutEmpresa==null}">
					<td class="certificadoLeft">Ingrese RUT Empresa:</td>
					<td class="certificadoLeft"><input type="text" name="rutEmpresa" id="rutEmpresa" value=""> </td>
					<td class="certificadoLeft">&nbsp;&nbsp;</td>
				</c:if>
				<c:if test="${rutEmpresa!=null}">
					<td class="certificadoLeft">RUT Empresa:</td>
					<td class="certificadoLeft"><input type="hidden" name="rutEmpresa" id="rutEmpresa" value="${rutEmpresa }">${rutEmpresa}</td>
					<td class="certificadoLeft">&nbsp;&nbsp;</td>
				</c:if>
				</tr>
				<tr>
					<td class="certificadoLeft">Ingrese RUT Trabajador:</td>
					<td class="certificadoLeft"><input type="text" name="rutTrabajador" id="rutTrabajador" value=""> </td>
					<td class="certificadoLeft">&nbsp;&nbsp;<input class="boton" id="generar" type="submit" value="Buscar Cotizaciones"  /></td>
				</tr>
				</table>
			</form>
		



	<script type="text/javascript">
		$(document).ready(
				function() {
					
					$("#rutTrabajador").Rut({
						format_on : 'keyup',
						on_error: function(){ alert('El RUT Trabajador ingresado es incorrecto'); }
					}); 
					$("#rutEmpresa").Rut({
						format_on : 'keyup',
						on_error: function(){ alert('El RUT Empresa ingresado es incorrecto'); }
					});

					

				}); // fin del document ready
				
	</script>
</body>
</html>