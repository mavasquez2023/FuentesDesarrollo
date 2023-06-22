<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CONFIGURACION CORREOS ENVÍO NOMINAS</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==-1}">
			mostrarWarning("No se han encontrado datos para la empresa ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la consulta en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}


function Editar(campo){
	form1.rutEmpresa.value= campo;
	form1.accion.value= "editaCorreo";
	form1.submit();
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
	<p class="titulo">CONFIGURACION CORREOS ENVÍO NOMINAS</p>
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>

	<form action="correos.do" name="form1" method="post">
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
								<td class="certificadoCenter"><input class="boton" id="modificar" name="modificar" type="button" value="Modificar" onclick="Editar('${entry.key}')" /></td>
							</tr>
					</c:forEach>
				</table>
				
				<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="">
				<input type="hidden" name="accion" id="accion" value="">
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