<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />

<title>ADMINITRACION</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0 && resultado=='del'}">
			mostrarInfo("Se ha elminado Empresa a Encargado exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("No se ha encontrado encargado ${mensaje} ");
	</c:if>
	<c:if test="${error==-2}">
			mostrarWarning("No se ha podido eliminar empresa a encargado ${mensaje} ");
	</c:if>
	<c:if test="${error==-3}">
			mostrarWarning("Usted no posee permisos sobre empresas asociadas al encargado ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la operación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}
function buscarEncargado(){
	if(validaRut(form1.rutEncargado.value)){
		document.getElementById("informacion").innerHTML = "";
		form1.accion.value="buscarEncargado";
		form1.submit();
	}
	
}

function Volver(){	
	form1.accion.value="volver";
	form1.submit();
	
}

function eliminarEncargado(ruttrabajador, rutempresa){	
	if(confirm("Confirma que desea eliminar encargado " + ruttrabajador + " de empresa "  + rutempresa)){
		form1.accion.value="modificaEncargado";
		form1.subaccion.value="del";
		form1.rutEncargado.value=ruttrabajador;
		form1.rutEmpresa.value=rutempresa;
		form1.submit();
	}
}

function agregarEncargado(ruttrabajador, rutempresa){	
		form1.accion.value="nuevoEncargado";
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
	<p class="titulo">ADMINISTRACIÓN ENCARGADOS</p>
	<form action="encargados.do" name="form1" method="post">
	
	<p>	
				<table align="center" class="tabla-creditos">
					
					<tr>
						<th class="certificadoLeft">RUT Encargado&nbsp;:</th>
						<td class="certificadoLeft">
						<input id="rutEncargado" name="rutEncargado" type="text" value="" />&nbsp;<input class="boton" id="buscar" type="button" value="Buscar"  onclick="buscarEncargado();"/></td>

					</tr>
				</table>
				<p>
					<div align="right" id="add" style="width: 75%">
						<input class="boton" id="agregar" type="button" value="Nuevo Encargado"  onclick="agregarEncargado();"/>
					</div>
				</p>
				
				<br>


	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>

		<c:if test="${error==0}">
				<table align="center" class="tabla-creditos">
					
					<tr>
						<th class="certificadoLeft">RUT</th>
						<th class="certificadoLeft">Nombre</th>
						<th class="certificadoLeft">Empresas Autorizadas</th>
						<th class="certificadoLeft">&nbsp;</th>
					</tr>
					<c:forEach var="entry" varStatus="vs" items="${empresasLDAP}">
							<tr><c:if test="${vs.index==0}">
									<td class="certificadoLeft" rowspan="${countEmpresasLDAP}">${encargado.rutTrabajador }</td>
									<td class="certificadoLeft" rowspan="${countEmpresasLDAP}">${encargado.nombre}</td>
								</c:if>

								<td class="certificadoLeft">${entry.rutEmpresa} &nbsp;  ${entry.nombreEmpresa}</td>
								<td><input class="boton-gris" id="eliminar" type="button" value="Eliminar"  onclick="eliminarEncargado('${encargado.rutTrabajador }', '${entry.rutEmpresa}');"/></td>
							</tr>
					</c:forEach>
					
				</table>
		</c:if>
				<input type="hidden" name="accion" value="" />
				<input type="hidden" name="subaccion" value="" />
				<input type="hidden" name="rutEmpresa" value="" />
	</p>
	
	</form>
	</div>
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
