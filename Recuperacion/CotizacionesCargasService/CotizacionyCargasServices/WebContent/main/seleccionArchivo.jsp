<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CESACIÓN TRABAJADORES MASIVA</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0}">
			mostrarInfo("Trabajadores desvinculados exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("Se han encontrado errores en el archivo. Corrija y vuelva a enviar <br>${errorMsg}", 4000);
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la desvinculación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}

function Desvincular(){
	var archivo= form1.file.value;
	pos= archivo.lastIndexOf(".");
	var extension= archivo.substr(pos+1);
	extension= extension.toLowerCase();
	document.getElementById("informacion").innerHTML = "";
	document.getElementById("mensajes").innerHTML = "";
	if(archivo!= "" && extension=="csv"){
		form1.accion.value="cesartrabajadores";
		form1.submit();
	}else{
		alertDialog("Ingrese un archivo válido");
		return false;
	}
}

function Volver(){	
	form2.accion.value="volver";
	form2.submit();
	
}

</script>
<script>
  $( function() {
    $( "#dialog" ).dialog({
      autoOpen: false,
      width: 500,
      height: 350,
      show: {
        effect: "blind",
        duration: 300
      },
      hide: {
        effect: "blind",
        duration: 150
      }
    });
 
    $( "#opener" ).on( "click", function() {
      $( "#dialog" ).dialog( "open" );
    });
  } );
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
	<p class="titulo">CESACIÓN TRABAJADORES MASIVA </p>
	<form action="cesacionfile.do" name="form1" method="post" enctype="multipart/form-data">
	<p>	
		<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
			<tr >
				<td class="certificadoRight" colspan="2" style="border: 0 px; border-color: #FFFFFF; height: 30px;" >Formato Archivo: &nbsp;<a id="opener" href="#"> <i class="fas fa-file-csv"></i></a></td>
			</tr>
		</table>
		<table align="center" class="tabla-creditos">
			<tr>
				<c:if test="${rol!='Ejecutivo_Cesacion' }">
					<th class="certificadoLeft">RUT Empresa</th>
					<td class="certificadoLeft">${rutEmpresa} &nbsp; ${razonSocial}</td>
				</c:if>
			</tr>
			<tr>
				<th class="certificadoLeft">Archivo CSV</th>
				<td class="certificadoLeft">
					<input  id="file" name="file" type="file" value="" /></td>
				</td>
			</tr>
		</table>
		<input type="hidden" name="accion" value="" />
	</p>
	<p>
		<div class="certificadoCenter">
			<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
				<tr>
					<td style="border: 0 px;border-color: #FFFFFF;" height="50">
					<input class="boton" id="desvincular" type="button" value="Enviar Archivo" onclick="Desvincular();"/>
				</td>
				</tr>
			</table>
			<input type="hidden" name="rutEmpresa" id="rutEmpresa" value="${rutEmpresa}">
		</div>
	</p>
	</form>
	<p><br>
	</p>
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>
	
	<form action="comprobanteMP.do" name="form2" method="post">					
	<p class="certificadoCenter" style="height: 50px">
	<div id="mensajes" class="certificadoCenter">
	<c:if test="${error==0}">
			<p>
			<div class="certificadoCenter">
					<c:if test="${rol!='Ejecutivo_Cesacion' }">
						<table align="center" class="tabla-creditos"
							style="border: 0 px; border-color: #FFFFFF;">
							<tr>

								<td style="border: 0 px; border-color: #FFFFFF;"><input
									class="boton" id="volver" type="button" value="Volver"
									onclick="Volver();" /></td>
								<td width="50" style="border: 0 px; border-color: #FFFFFF;">
									&nbsp;</td>
								<td style="border: 0 px; border-color: #FFFFFF;"><input
									class="boton" id="generar" type="submit" value="Descargar PDF" />
								</td>

							</tr>
						</table>
					</c:if>
					<input type="hidden" name="accion" id="accion" value="">
					<input type="hidden" name="tipo" value="M" />
			</div>
			</p>
	</c:if>
	<c:if test="${error==-1}">
		
			<p class="titulo">Informe de Errores encontrados en el Proceso</p>
			<br>
			<p>
				<table align="center" class="tabla-creditos">
					<tr>
						<th>LÍNEA</th>
						<th>RUT TRABAJADOR</th>
						<th>OBSERVACIÓN</th>
					</tr>
					<c:forEach var="error" varStatus="vs" items="${errores}">
							<tr>
								<td class="certificadoLeft">${error.numerolinea}</td>
								<td class="certificadoLeft">${error.rutTrabajador}</td>
								<td class="certificadoCenter">${error.obervacion}</td>
							</tr>
					</c:forEach>
				</table>
		
			</p>
	</c:if>
	
	</div>
	</p>
	</form>
	</div>
	<jsp:include page="formato.jsp" flush="true" />

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
					
					
					$("#rutEmpresa").Rut({
						format_on : 'keyup'
					}); 
					
					
				}); // fin del document ready
				
	</script>
</body>
</html>