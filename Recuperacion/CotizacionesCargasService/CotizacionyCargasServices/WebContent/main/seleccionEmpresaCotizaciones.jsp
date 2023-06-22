<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>TRABAJADORES NO COTIZADOS</title>
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
	<p class="titulo">TRABAJADORES NO COTIZADOS</p>
	
	<p class="subtitulo">Período Remuneraciones:&nbsp;${periodoProceso}</p>

<form action="cotizaciones.do" name="form1" method="post">
	<table align="center" class="tabla-creditos" style="border:0px; border-color: #FFFFFF;">
		<tr>
			<th class="certificadoLeft" style="background-color: #0e60b2; color: white;width: 180px">Período a Descargar:</th>

			<td class="certificadoLeft" style="text-decoration: none;border-color: white">
				<select id="periodo" name="periodo">					
				<c:forEach var="entry" varStatus="vs" items="${listaPeriodos}">
					<option value="${entry }" 
					<c:if test="${entry==periodo }">
						selected='selected'
					</c:if>
					>${entry }</option>
				</c:forEach>
				</select>
			</td>

		</tr>
	</table>

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
				<input type="hidden" name="numeroMeses" id="numeroMeses" value="${numeroMeses}">
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
</body>
</html>