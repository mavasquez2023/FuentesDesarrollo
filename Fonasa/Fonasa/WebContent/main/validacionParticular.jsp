<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />
<title>CESACIÓN TRABAJADORES MASIVA</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	
	<c:if test="${error==-1}">
			mostrarWarning("Se han encontrado errores en la validación. Corrija y vuelva a enviar <br>${errorMsg}", 4000);
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la validación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
}

function Validar(){
	var lista= form1.folios.value;
	document.getElementById("informacion").innerHTML = "";
	document.getElementById("mensajes").innerHTML = "";
	if(lista!= ""){
		form1.submit();
	}else{
		alertDialog("Ingrese una o mas licencias separadas por coma");
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
	<p class="titulo">VALIDACIÓN LICENCIAS FONASA </p>
	<form action="fonasa.do" name="form1" method="post">
	<p>	
		<table align="center" class="tabla-creditos" style="border: 0 px; border-color: #FFFFFF;">
			<tr >
				<td class="certificadoLeft" colspan="2" style="border: 0 px; border-color: #FFFFFF; height: 30px;" >Ingrese el (los) número de formulario(s), separados por coma, para validar el último estado de las licencias:</td>
			</tr>
		</table>
		<table align="center" class="tabla-creditos">
			<tr>
				<th class="certificadoLeft">Tipo Formulario:</th>
				<td class="certificadoLeft">
					&nbsp; 1<input  id="tipo" name=tipo type="radio" value="1" checked="checked"/>&nbsp;2<input  id="tipo" name=tipo type="radio" value="2" /></td>
				</td>
			</tr>
			<tr>
				<th class="certificadoLeft">Número Formulario(s):</th>
				<td class="certificadoLeft">
					<input  size="60px" id="folios" name=folios type="text" value="" /></td>
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
					<input class="boton" id="validar" type="button" value="Validar" onclick="Validar();"/>
				</td>
				</tr>
			</table>
	
		</div>
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
		
			<p class="titulo">Validaciones Estado Distinto de 72</p>
			<br>
			<p>
				<table align="center" class="tabla-creditos">
					<tr>
						<th>NÚMERO FORMULARIO</th>
						<th>TIPO</th>
						<th>ESTADO</th>
						<th>GLOSA ESTADO</th>
						<th>COD.ESTADO</th>
						<th>COMENTARIO</th>
					</tr>
					<c:forEach var="entry" varStatus="vs" items="${lista_erroneos}">
							<tr>
								<td class="certificadoLeft">${entry.numeroLicencia}</td>
								<td class="certificadoLeft">${entry.tipoFormulario}</td>
								<td class="certificadoCenter">${entry.estado}</td>
								<td class="certificadoLeft">${entry.glosaEstado}</td>
								<td class="certificadoCenter">${entry.codEstado}</td>
								<td class="certificadoCenter">${entry.comentario}</td>
							</tr>
					</c:forEach>
				</table>
			</p>
	</c:if>
	<c:if test="${error==9}">
		
			<p>Licencias Validadas exitosamente, no se han encontrado estados distinto de 72.</p>
			<br>

	</c:if>
	<c:if test="${error==-1}">
		
			<p>Se han encontrado errores en la validación. Corrija y vuelva a enviar <br>${errorMsg}</p>
			<br>

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