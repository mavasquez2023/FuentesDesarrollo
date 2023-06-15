<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true" />

<title>RESULTADO NRP</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0 && resultado=='add'}">
			mostrarInfo("Se ha agregado Correo a Empresa exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("No se ha encontrado empresa ${mensaje} ");
	</c:if>
	<c:if test="${error==-2}">
			mostrarWarning("No se ha podido eliminar correo a empresa ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la operación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
	$('#guardar').prop('disabled', true);
}


function CambiarProceso(){	
	form1.accion.value="editaCorreo";
	form1.submit();
}


function Agregar(){
var correo= form1.correo.value;
	if(correo!=""){
		if(!validaEmail(correo)){
			alertDialog("Ingrese un correo válido");
			return false;
		}
			form1.accion.value="agregaCorreo";
			form1.submit();
	}
}

function confirmDialog(message, id) {
    $('<div></div>').appendTo('body')
    .html('<div><h5>'+message+'?</h5></div>')
    .dialog({
        modal: true, title: 'Eliminar Correo', zIndex: 10000, autoOpen: true,
        width: 'auto', resizable: false,
        buttons: {
            Si: function () {
            	eliminarId(id);
                $(this).dialog("close");
            },
            No: function () {                                                               
                $(this).dialog("close");
            }
        }
    });
};

function eliminarCorreo(id){	
	confirmDialog("Confirma que desea eliminar correo ?", id);
}

function eliminarId(id){
		form1.accion.value="eliminaCorreo";
		form1.id.value=id;
		form1.submit();
}

function agregarFila(){
   var htmlTags = '<tr>'+
        '<td class="certificadoLeft" ><input id="correo" name="correo" type="text" size="40" maxlength="50" value=""/></td>'+
        '<td class="certificadoLeft" ><input id="usuario" name="usuario" type="text" size="30" maxlength="50" value=""/></td>'+
        '<td><input class="boton" id="agregar" type="button" value="Agregar" onclick="Agregar();"/></td>'+
      '</tr>';
      
   $('#correosProceso').append(htmlTags);
   $('#nuevo').prop('disabled', true);
   $('#recibir').prop('checked', false);
   $('#guardar').prop('disabled', false);
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
	<p class="titulo">ADMINISTRACIÓN CORREOS</p>
	<form action="correos.do" name="form1" method="post">
	
	<p>	
					
	<table align="center" class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF;">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			Seleccione Proceso: 
			<select name="proceso" id="proceso">
					<option value="CON" <c:if test='${proceso=="CON"}'>selected="selected"</c:if>>Consolidación</option>
					<option value="GEN" <c:if test='${proceso=="GEN"}'>selected="selected"</c:if>>Generación</option>
					<option value="DIS" <c:if test='${proceso=="DIS"}'>selected="selected"</c:if>>Disponibilización</option>
			</select>
			<input class="boton" id="aceptar" type="button" value="Aceptar" onclick="CambiarProceso();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>

		
	</tr>
	</table>

	<br><br>
		<div id="tabla_correos" class="certificadoCenter">
				<table id="correosProceso" align="center" class="tabla-creditos">
					
					<tr>
						<th class="certificadoLeft">Correo</th>
						<th class="certificadoLeft">Usuario</th>
						<th class="certificadoRight" width="200px"><input class="boton" id="nuevo"
						type="button" value="Nuevo Correo" onclick="agregarFila();">
					</th>
					</tr>
					<c:forEach var="entry" varStatus="vs" items="${correos}">
							<tr>
								<td class="certificadoLeft">${entry.correo}</td>
								<td class="certificadoLeft">${entry.usuario}</td>
								<td class="certificadoCenter"><input class="boton-gris" id="eliminar" type="button" value="Eliminar"  onclick="eliminarCorreo('${entry.id }');"/></td>
							</tr>
					</c:forEach>
					
				</table>
		</div>
	</p>
	<p>
		<br>
		<div id="informacion" class="informacion"></div>
		<div id="informacion_warning" class="informacion"></div>
		<div id="informacion_error" class="informacion"></div>

		<input type="hidden" name="accion" value="" />
		<input type="hidden" name="id" value="" />
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
