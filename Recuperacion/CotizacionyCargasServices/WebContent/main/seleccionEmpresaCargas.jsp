<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CARGAS FAMILIARES NO CANCELADAS</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==-1}">
			mostrarWarning("No se han encontrado datos para la empresa ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible descargar archivo en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}
function generarArchivo(){	
	nroemp= form1.rut.length;
	var lista="";
	var numempchecked=0;
	if (!isNaN(nroemp)){
		for (var i = 0; i < nroemp; i++) {
			if (form1.rut[i].checked==true){
				lista+=form1.rut[i].value + ";";
				numempchecked++;
			}
		}
	}else{
		lista= form1.rut.value+";";
		form1.rut.checked=true;
		numempchecked++;
	}

	if(numempchecked==0 ){
		alertDialog('Seleccione una Empresa a descargar archivo.');
		return;
	}
	form1.rutEmpresa.value= lista.substr(0, lista.length -1);
	document.getElementById("informacion").innerHTML = "";
	mostrarInfo("Archivo se está generando, espere un momento.");
	form1.submit();
	
}

function validaSeleccion(campo){
	nroemp= form1.rut.length;
	if (!isNaN(nroemp)){
		for (var i = 0; i < nroemp; i++) {
			if(campo.value!= form1.rut[i].value){
				form1.rut[i].checked=false;
			}
		}
	}else{
		form1.rut.checked=true;
	}
}

</script>
</head>
<body onLoad="init();">
<script>
	var path= "<%=request.getContextPath()%>";
		$(document).ready(function() {
			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
		 });

	</script>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
<div style="position: relative; left: 100px; width: 1024px;">
	<p class="titulo">DIFERENCIAS PAGO CARGAS FAMILIARES</p>
	<!-- 
	<c:if test="${numeroMeses<=2 }">
		<p class="subtitulo">Proceso de Remuneraciones ${periodo}</p>
	</c:if>
	 -->
	 <form action="cargas.do" name="form1" method="post">
	<p class="subtitulo">
	<table align="center" border="0" style="width: 50%; border:0px">
			<tr>
				<td class="certificadoCenter" style="border:0px">&nbsp;</td>
				<td class="certificadoCenter" style="border:0px">Desde</td>
				<td class="certificadoCenter" style="border:0px">Hasta</td>
			</tr>
			<tr>
				<td class="certificadoCenter" style="border:0px">Proceso de Remuneraciones</td>
				<td class="certificadoCenter" style="border:0px">
					<select name="periodo1" id="periodo1" >
						<c:forEach var="periodo" varStatus="vs" items="${listaPeriodos}">
							<option value="${periodo}">${ periodo}</option>
						</c:forEach>
					</select>
				</td>
				<td class="certificadoCenter" style="border:0px">
					<select name="periodo2" id="periodo2">
						<c:forEach var="periodo" varStatus="vs" items="${listaPeriodosHasta}">
							<option value="${periodo}">${ periodo}</option>
						</c:forEach>
					</select></td>
			</tr>
			
	</table>
	</p>
	<br>
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>

	
				<table align="center" class="tabla-creditos">
					<tr>
						<th>RUT EMPRESA</th>
						<th>NOMBRE EMPRESA</th>
						<th>&nbsp;<!-- input class="boton" id="sel" type="checkbox" value="Todas" /--></th>
					</tr>
					<c:set var="total" value="0"></c:set>
					<c:forEach var="entry" varStatus="vs" items="${empresas}">
							<tr>
								<td class="certificadoLeft">${entry.key}</td>
								<td class="certificadoLeft">${entry.value}</td>
								<td class="certificadoCenter"><input  id="rut" name="rut" type="checkbox" value="${entry.key}" onclick="validaSeleccion(this)" /></td>
							</tr>
					</c:forEach>
				</table>
				<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF; height:50px">
					<tr>
						<td colspan="3" style="border: 0 px;border-color: #FFFFFF;"><input class="boton" id="generar" type="button" value="Descargar"  onclick="generarArchivo();"/></td>
				</tr>
				</table>	
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="">
	</form>
	</div>

	<script>
	$(document).ready(function(){
		$("#accordian h3").click(function(){
			//slide up all the link lists
			$("#accordian ul ul").slideUp();
			//slide down the link list below the h3 clicked - only if its closed
			if(!$(this).next().is(":visible"))
			{
				$(this).next().slideDown();
			}
		})
	})
	</script>
	<script type="text/javascript">
	
		
		$('#periodo1').change(function() {
					 var hasta = $("#periodo2");
        			 var desde = $("#periodo1");

					hasta.prop('disabled', true);
					desde.prop('disabled', true);
					
					$.getJSON('hasta.do', {
						id : $(this).val(),
						ajax : 'true'
					}, function(data) {

						if (data.periodos != null) {
							 // Limpiamos el select hasta
                    		hasta.find('option').remove();
							$.each(data.periodos, 
								function(indice, per){
									var option = $(document.createElement('option'));
									option.text(per);
                        			option.val(per);
									$("#periodo2").append(option);
							});
							desde.prop('disabled', false);
							hasta.prop('disabled', false);
						}
					});
				});
				
	</script>
</body>
</html>