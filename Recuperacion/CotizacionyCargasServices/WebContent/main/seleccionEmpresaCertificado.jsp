<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CERTIFICADO CESACIÓN TRABAJADORS</title>
<script language="JavaScript 1.2" type="text/javascript">
function Buscar(){	
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
		alertDialog('Seleccione una Empresa a buscar Trabajador.');
		return;
	}
	form1.rutEmpresa.value= lista.substr(0, lista.length -1);
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
<body>
<jsp:include page="banner.jsp" flush="true" />
<jsp:include page="menuServices.jsp" flush="true" />
<div style="position: relative; left: 100px; width: 1024px;">
	<p class="titulo">CERTIFICADO CESACIÓN TRABAJADOR</p>
	
	<form action="certificado.do" name="form1" method="post">
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
								<td class="certificadoCenter"><input id="rut" name="rut" type="checkbox" value="${entry.key}" onclick="validaSeleccion(this)" /></td>
							</tr>
					</c:forEach>
				</table>
				<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF; height:50px">
					<tr>
						<td colspan="3" style="border: 0 px;border-color: #FFFFFF;"><input class="boton" id="generar" type="button" value="Buscar"  onclick="Buscar();"/></td>
				</tr>
				</table>	
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="">
				<input type="hidden" name="accion" id="accion" value="ingresarTrabajador">
	</form>
	</div>
	<p class="certificadoCenter" style="height: 50px">
	<div id="mensajes" class="certificadoCenter">
	<c:if test="${error==-1}">
		
			No se han encontrado datos para la empresa ${mensaje}
			
	</c:if>
	<c:if test="${error==1}">
		
			No es posible generar certificado en este momento. Intente mas tarde.
			<br>
			${errorMsg}
	</c:if>
	</div>
	</p>
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