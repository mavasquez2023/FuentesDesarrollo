<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />

<title>CONSULTA BITACORA</title>

</head>
<body>
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
	<p class="titulo">CONSULTA BITACORA</p>
	<form action="encargados.do" name="form1" method="post">
	
	<p>	
	
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>


				<table align="center" class="tabla-creditos">
					
					<tr>
						<th class="certificadoLeft">TOTAL VAL.</th>
						<th class="certificadoLeft">NO 72</th>
						<th class="certificadoLeft">TIMEOUT</th>
						<th class="certificadoLeft">OBS.</th>
						<th class="certificadoLeft">USUARIO</th>
						<th class="certificadoLeft">ARCHIVO</th>
						<th class="certificadoLeft">FECHA</th>
						<th class="certificadoLeft">HORA INI</th>
						<th class="certificadoLeft">HORA FIN</th>
					</tr>
					<c:forEach var="entry" varStatus="vs" items="${bitacora}">
							<tr>
								<td class="certificadoLeft">${entry.totalValidados}</td>
								<td class="certificadoLeft">${entry.estadono72}</td>
								<td class="certificadoLeft">${entry.timeout}</td>
								<td class="certificadoLeft">${entry.observacion}</td>
								<td class="certificadoLeft">${entry.usuario}</td>
								<td class="certificadoLeft">${entry.nombreArchivo}</td>
								<td class="certificadoLeft">${entry.fecha}</td>
								<td class="certificadoLeft">${entry.horaInicio}</td>
								<td class="certificadoLeft">${entry.horaTermino}</td>
							</tr>
					</c:forEach>
					
				</table>

				<input type="hidden" name="accion" value="" />
	</p>
	
	</form>
	
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
					

					
				}); // fin del document ready
				
	</script>
</body>
</html>
