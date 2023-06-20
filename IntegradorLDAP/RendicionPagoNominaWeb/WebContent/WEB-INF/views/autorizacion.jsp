<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="cl.laaraucana.rendicionpagonomina.utils.DescripcionCodigo"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:include page="comun/header.jsp" flush="true" />
<html>
<head>

<title>Autorización Usuarios</title>
<script language="JavaScript 1.2" type="text/javascript">
function init(){
	<c:if test="${error==0 && resultado=='add'}">
			mostrarInfo("Se ha agregado Usuario a Convenio exitosamente. ");
	</c:if>
	<c:if test="${error==-1}">
			mostrarWarning("No se ha encontrado convenio ${mensaje} ");
	</c:if>
	<c:if test="${error==-2}">
			mostrarWarning("No se ha podido eliminar usuario de convenio ${mensaje} ");
	</c:if>
	<c:if test="${error==1}">
			mostrarError("No es posible realizar la operación en este momento. Intente mas tarde <br>${errorMsg}");
	</c:if>
	$('#guardar').prop('disabled', true);
}


function CambiarConvenio(){	
	form1.accion.value="editaUsuario";
	form1.submit();
}


function Agregar(){
var idUsuario= form1.idUsuario.value;
	if(idUsuario!=""){
		/*if(!validaRut(correo)){
			alertDialog("Ingrese un rut válido");
			return false;
		}*/
			form1.accion.value="agregarUsuario";
			form1.submit();
	}
}

function confirmDialog(message, id) {
    $('<div></div>').appendTo('body')
    .html('<div><h5>'+message+'?</h5></div>')
    .dialog({
        modal: true, title: 'Eliminar Usuario', zIndex: 10000, autoOpen: true,
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

function eliminarUsuario(id){	
	confirmDialog("Confirma que desea eliminar Usuario ?", id);
}

function eliminarId(id){
		form1.accion.value="eliminarUsuario";
		form1.id.value=id;
		form1.submit();
}

function agregarFila(){
   var htmlTags = '<tr>'+
        '<td class="certificadoLeft" ><input id="idUsuario" name="idUsuario" type="text" size="30" maxlength="50" value=""/></td>'+
        '<td><input class="boton" id="agregar" type="button" value="Agregar" onclick="Agregar();"/></td>'+
      '</tr>';
      
   $('#usuariosProceso').append(htmlTags);
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
	<br />

	<table align="center" class="tabla-creditos"
		style="border: 0 px; border-color: #FFFFFF; margin-left: 330px">
		<tr>
			<td class="bienvenida"
				style="border: 0 px; font-size: 20px; border-color: #FFFFFF; text-align: left;">
				Autorización Usuarios
				</td>

		</tr>
	</table>
	<form action="autorizar.do" name="form1" method="post">
	
	<p>	
					
	<table class="tabla-creditos" style="border: 0 px;border-color: #FFFFFF; margin-left: 330px">
	<tr>
		<td style="border: 0 px;border-color: #FFFFFF;">
			Seleccione Convenio: 
			<select name="convenio" id="convenio">
					<c:forEach items="${conveniosAutorizacion}" var="item">
							<option value="${item.codigoConvenio}" 
							<c:if test='${item.codigoConvenio== convenio_actual}'>selected="selected"</c:if>
							>${item.codigoConvenio}-${item.descripcionConvenio}</option>

						</c:forEach>
			</select>
			<input class="boton" id="aceptar" type="button" value="Aceptar" onclick="CambiarConvenio();"/>
		</td>
		<td width="50" style="border: 0 px;border-color: #FFFFFF;">
			&nbsp;
		</td>

		
	</tr>
	</table>

	<br><br>
		<div id="tabla_correos" class="certificadoCenter">
				<table id="usuariosProceso" align="center" class="tabla-creditos" style="margin-left: 330px">
					
					<tr>
						<th class="certificadoLeft">Usuarios de Convenio</th>
						<th class="certificadoRight" width="200px"><input class="boton" id="nuevo"
						type="button" value="Nuevo Usuario" onclick="agregarFila();">
					</th>
					</tr>
					<c:forEach var="entry" varStatus="vs" items="${usuariosConvenio}">
							<tr>
								<td class="certificadoLeft">${entry}</td>
								<td class="certificadoCenter"><input class="boton-gris" id="eliminar" type="button" value="Eliminar"  onclick="eliminarUsuario('${entry }');"/></td>
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
	<div class="preloader-general" id="preloader-general" data-tipo="screen" style="display:none"></div>
	<script src="assets/js/automatizar.js"></script>
	<script src="assets/js/fln.js"></script>
	<script src="resources/js/corev2.js"></script>
	<script src="resources/js/jquery-ui-1.9.2.custom.js"></script>
	
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
