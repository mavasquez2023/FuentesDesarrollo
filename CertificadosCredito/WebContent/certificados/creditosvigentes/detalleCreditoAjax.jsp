<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true"></jsp:include>
<title>Certificado Cuotas Creditos Vigentes</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
	<logic:equal value="0" name="codError">
		<p class="titulo">Certificado Cuotas Creditos Vigentes</p>
		<div id="datosAfiliado">
			<span><b>RUT: </b>${rut}</span><span><b>Nombre: </b>${nombre}</span>
			<span><b>Número de cuotas pendientes:</b>${cuotasPendientes}</span>			
		</div>
		<table>
			<tr>
				<th>Nc</th>
				<th>Vcto.</th>
				<th>Cuota</th>
				<th>Fec. Pago</th>
				<th>Ofi.</th>
				<th>Doc. Pago</th>
				<th>Monto</th>
				<th>Est. al Pago</th>
			</tr>

		</table>
		<div class="botones">
			<form target="blank" action="detalleCredito.do" method="POST">
				<input type="hidden" name="accion" value="imprimirReporte">
				<input id="volver" type="button" value="Volver" onClick="history.back()" / class="boton"> 
				<input type="submit" value="Generar Certificado" id="imprimir" class="boton" />
			</form>
		</div>
				
		<div id="loading" style="position:fixed; top:25%; left:47%; display:none; z-index: auto" >
			<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
		</div>
	</logic:equal>
	<script>
				
		$(document).ready(function(){

		jQuery.ajax({
	        type: "POST",
	        url: '../../getDetalleContrato.do',
	        data:{folio:"${folio_contrato}"},
	        success: function(data)
	        {
	        $("#loading").remove();
	        $("table").append(data);
	        }
		});
		
		$("#loading").show();

		});
		
	</script>
</body>
</html>