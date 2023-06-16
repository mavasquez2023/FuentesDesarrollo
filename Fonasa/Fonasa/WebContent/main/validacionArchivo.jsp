<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CESACIÓN TRABAJADORES MASIVA</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==-1}">
			mostrarWarning("Se han encontrado errores en el archivo. Corrija y vuelva a enviar <br>${errorMsg}", 4000);
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la validación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}

function Validar(){
	var archivo= form1.file.value;
	pos= archivo.lastIndexOf(".");
	var extension= archivo.substr(pos+1);
	extension= extension.toLowerCase();
	document.getElementById("informacion").innerHTML = "";
	document.getElementById("mensajes").innerHTML = "";
	if(archivo!= "" && extension=="csv"){
		form1.submit();
	}else{
		alertDialog("Ingrese un archivo válido");
		return false;
	}
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
	<p class="titulo">VALIDACIÓN LICENCIAS FONASA MASIVA </p>
	<form action="fonasafile.do" name="form1" method="post" enctype="multipart/form-data">
	<p>	
		<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
			<tr >
				<td class="certificadoRight" colspan="2" style="border: 0 px; border-color: #FFFFFF; height: 30px;" >Formato Archivo: &nbsp;<a id="opener" href="#"> <i class="fas fa-file-csv"></i></a></td>
			</tr>
			<tr >
				<td class="certificadoLeft" colspan="2" style="border: 0 px; border-color: #FFFFFF; height: 30px;" >Ingrese el archivo, en formato csv, para validar el último estado de las licencias:</td>
			</tr>
		</table>
		<table align="center" class="tabla-creditos">
			
			<tr>
				<th class="certificadoLeft">Archivo CSV</th>
				<td class="certificadoLeft">
					<input  id="file" name="file" type="file" value="" /></td>
				</td>
			</tr>
			<tr>
				<th class="certificadoLeft">Ingrese Correo(s):</th>
				<td class="certificadoLeft">
					<input size="50px" id="correos" name=correos type="text" value="${correos}" /></td>
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
					<input class="boton" id="validar" type="button" value="Validar Archivo" onclick="Validar();"/>
				</td>
				</tr>
			</table>
	
		</div>
	</p>
	</form>
	<form action="fonasabd.do" name="form3" method="post">
	<p align="center">
	En caso de contingencias en proceso diario automático, ejecute  aquí <input class="boton" id="validarBD" type="submit" value="Validar BD"/>
	</p>
	</form>
	<p><br>
	</p>
	<div id="informacion" class="informacion"></div>
	<div id="informacion_warning" class="informacion"></div>
	<div id="informacion_error" class="informacion"></div>
	
	<form action="" name="form2" method="post">					
	<p class="certificadoCenter" style="height: 50px">
	<div id="mensajes" class="certificadoCenter">
	<c:if test="${error==0}">
			<p>
			<div class="certificadoCenter">
					
						<table align="center" class="tabla-creditos"
							style="border: 0 px; border-color: #FFFFFF;">
							<tr>
								<td style="border: 0 px; border-color: #FFFFFF;">Archivo validado Exitosamente, resultado será enviado por correo</td>
							</tr>
						</table>

					<input type="hidden" name="accion" id="accion" value="">
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
						<th>NÚMERO FORMULARIO</th>
						<th>OBSERVACIÓN</th>
					</tr>
					<c:forEach var="error" varStatus="vs" items="${errores}">
							<tr>
								<td class="certificadoLeft">${error.numerolinea}</td>
								<td class="certificadoLeft">${error.numeroFormulario}</td>
								<td class="certificadoCenter">${error.observacion}</td>
							</tr>
					</c:forEach>
				</table>
		
			</p>
	</c:if>
	
	</div>
	</p>
	</form>
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
									
					
				}); // fin del document ready
				
	</script>
</body>
</html>