<html>
<head>
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/js/editor.js"></script>
<script>
		$(document).ready(function() {
		$("#txtEditor").Editor();
		 
  	$("#ParamSimuladorCreditoSocialForm").submit(function(e){
           e.preventDefault();
   	 });
    $("#guardarDatos").click(function(e){
   		var limit = $("#editorText").text().length;
		if(limit < 4000) { 
			var e = document.getElementById("parametrosSelect");
			var value = e.options[e.selectedIndex].value;
            $.ajax({
                type: "POST",
                url: "/FormParametrosSimulacion/GrabarParametros",
                data: {parametro: $("#editorText").html(), opcion: value},
                dataType : "html",
				error: function( data, textStatus, jqXHR) {
                      //alert("Ha ocurrido un error al procesar la solicitud, por favor reintente, si el problema persiste contacte al área de soporte");
                },
			
				complete: function( data, textStatus, jqXHR) {
					//alert("Los parámetros han sido grabados.");
					location.reload();
                }
            });    
       } else {
		alert("El número máximo de caracteres permitido es 4000");
		}
    });
		
	});
</script>
<script> 
		function cargarParametros(){
		var e = document.getElementById("parametrosSelect");
		var value = e.options[e.selectedIndex].value;
		if(value == 'parametrosIngreso') {
			$("#editorText").html($("#parametrosIngreso").val());
		} else if(value == 'parametrosResultado')  {
			$("#editorText").html($("#parametrosResultado").val());
		} 
	}
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css">
<link href="<%=request.getContextPath()%>/css/editor.css" type="text/css" rel="stylesheet" />

</head>
<br>
<body onload="cargarParametros();">
    <form id="ParamSimuladorCreditoSocialForm">
		<div class="container-fluid">
		<h3>Parametros Simulación Crédito Social</h3>
			<div style="height: 500px; width: 666px;">
			<br>
			<select id="parametrosSelect" onChange="cargarParametros(this.selectedIndex);">
				<option value="parametrosResultado">Notas Resultado Simulación</option>
			</select>
				<div>
					<div >
						<div>
							<textarea id="txtEditor"></textarea>
						</div>
					</div>
				</div>
				<br>
				 <input id="guardarDatos" type="button" value="Guardar" />
				 <input type="hidden" id="parametrosIngreso" value="${parametrosIngreso}"/>
				 <input type="hidden" id="parametrosResultado" value="${parametrosResultado}"/>
			</div>
				 
		</div>
	</form>
</body>
</html>