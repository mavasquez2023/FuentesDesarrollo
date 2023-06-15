<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
<title>Certificado Créditos Cancelados</title>
</head>
<body>
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
		<form target="blank" action="listadoCreditos.do" method="POST">
			<input type="hidden" name="rut" value="${rut}"> 
			<input type="hidden" name="accion" value="imprimirReporte">
			<input type="hidden" name="uc" value="${uc}">
			<div class="botones">
				<input type="submit" value="Generar Certificado"  id="imprimir"class="boton" />
			</div>
		</form>
	<div id="loading" style="position:fixed; top:25%; left:47%; display:none; z-index: auto" >
		<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
	</div>
	
	<script>

		$(document).ready(function(){
		
		$.get("getCreditos.do",{rut:"${rut}",uc:"${uc}"},function(data){
			$("table tbody").append(data);
			$('#loading').remove();
		});
		
		$('#loading').show();
		
		});
		
	</script>
</body>
</html>