<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CERTIFICADO CESACIÓN TRABAJADOR</title>
<script language="JavaScript 1.2" type="text/javascript">


function Limpiar(){
	form1.reset();
	document.getElementById("mensajes").innerHTML = "";
}

function Generar(){	
	if(validaRut(form1.rutTrabajador.value)){
		document.getElementById("mensajes").innerHTML = "";
		form1.accion.value="generarCertificado";
		form1.target="blank";
		form1.submit();
	}else{
		alertDialog("Ingrese un Rut válido");
	}
}

</script>
</head>
<body>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
	<p class="titulo">CERTIFICADO CESACIÓN TRABAJADOR</p>
	
	<p>	
	<form action="certificado.do" name="form1" method="post">
				<table align="center" class="tabla-creditos">
					<tr>
						<th class="certificadoLeft">RUT Empresa</th>
						<td class="certificadoLeft">${rutEmpresa} &nbsp; ${razonSocial}</td>
					</tr>
					<tr>
						<th class="certificadoLeft">RUT Trabajador</th>
						<td class="certificadoLeft">
						<input id="rutTrabajador" name="rutTrabajador" type="text" value="" /></td>
						</td>
					</tr>
					
				</table>
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="${rutEmpresa}">
				<input type="hidden" name="accion" id="accion" value="">
	</form>
	</p>
	
	<p>
	
	<div class="certificadoCenter">
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton" id="volver" type="button" value="Limpiar" onclick="Limpiar();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton" id="generar" type="submit" value="Generar" onclick="Generar();"/>
		</td>
		
	</tr>
	</table>
	</div>
	</p>
	<p class="certificadoCenter" style="height: 50px">
	<div id="mensajes" class="certificadoCenter">
	<c:if test="${error==0}">
			Certificado generado para Trabajador
			
	</c:if>
	<c:if test="${error==-1}">
		
			No se han encontrado datos para el trabajador ${mensaje}
			
	</c:if>
	<c:if test="${error==1}">
		
			No es posible generar el certificado en este momento. Intente mas tarde.
			<br>
			${errorMsg}
	</c:if>
	</div>
	</p>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$("#accordian h3").click(function(){
						//slide up all the link lists
						$("#accordian ul ul").slideUp();
						//slide down the link list below the h3 clicked - only if its closed
						if(!$(this).next().is(":visible")){
							$(this).next().slideDown();
						}
					});
					
					$("#rutTrabajador").Rut({
						format_on : 'keyup'
					}); 			
					
				}); // fin del document ready
				
	</script>
</body>
</html>