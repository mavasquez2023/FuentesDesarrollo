<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />

<title>ADMINITRACION</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0 && resultado=='add'}">
			mostrarInfo("Se ha agregado Empresa a Encargado exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("No se ha encontrado encargado ${mensaje} ");
	</c:if>
	<c:if test="${error==-2}">
			mostrarWarning("No se ha podido agregar empresa a encargado ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la operación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}


function Volver(){	
	form1.accion.value="volver";
	form1.submit();
	
}


function agregarEncargado(){	
		form1.accion.value="modificaEncargado";
		form1.subaccion.value="add";
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
	<p class="titulo">ADMINISTRACIÓN ENCARGADOS</p>
	<form action="encargados.do" name="form1" method="post">
	
	<p>	
				<table align="center" class="tabla-creditos">
					
					<tr>
						<td class="certificadoLeft">RUT Encargado&nbsp;:</td>
						<td class="certificadoLeft">
						<input id="rutEncargado" name="rutEncargado" type="text" value="" /></td>

					</tr>
					<tr>
						<td class="certificadoLeft">Empresa&nbsp;:</td>
						<td class="certificadoLeft">
						<select name="rutEmpresa">
						<c:forEach var="entry" varStatus="vs" items="${empresas}">
							<option value="${entry.key}">${entry.key}&nbsp;:&nbsp;${entry.value}</option>
						</c:forEach>
						</select>
					</tr>
				</table>
				
				<p>
	
	<div class="certificadoCenter">
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton-gris" id="volver" type="button" value="Volver" onclick="Volver();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>
		<td style="border: 0 px;border-color: #FFFFFF;">
			<input class="boton-gris" id="agregar" type="submit" value="Agregar" onclick="agregarEncargado();"/>
		</td>
		
	</tr>
	</table>
	</div>
	</p>
	<p>
		<br>
		<div id="informacion" class="informacion"></div>
		<div id="informacion_warning" class="informacion"></div>
		<div id="informacion_error" class="informacion"></div>

		<input type="hidden" name="accion" value="" />
		<input type="hidden" name="subaccion" value="" />
		<input type="hidden" name="rutEmpresa" value="" />
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
					
					
					$("#rutEncargado").Rut({
						format_on : 'keyup'
					}); 

					
				}); // fin del document ready
				
	</script>
</body>
</html>
