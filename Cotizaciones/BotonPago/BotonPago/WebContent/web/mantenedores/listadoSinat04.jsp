<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="/comun/tld.jsp"%>
<html>
<head>
<title>LA ARAUCANA C.C.A.F.</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include flush="true" page="/comun/inports.jsp"></jsp:include>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui-1.9.2.custom.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/css/mantenedor.css" rel="stylesheet" />
<link type="text/css" href="<%=request.getContextPath()%>/css/themes/araucana/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" />
</head>
<body>
	<jsp:include flush="true" page="/comun/header.jsp"></jsp:include>
	<div class="contenedor">
		<jsp:include flush="true" page="/comun/top.jsp"></jsp:include>
		<div class="menu">
			<jsp:include flush="true" page="/comun/menu.jsp"></jsp:include>
		</div>
		<div class="opcionmenu">
			<div id="contenedorMantenedor">
				<p id="titulo" class="titulo">Administraci&oacute;n de porcentaje Condonación de gastos de Cobranzas</p>
				<div id="buscador">
					<!-- <form action="" method="post" id="formBuscar">
						<input type="text" name="tipcod" id="entrada" />
						<input type="hidden" name="op" value="buscarSinat04" />
						<button id="btnBuscar">Buscar</button>
					</form> -->
				</div>
				<div id="ingreso">
					<button id="btnIngresar">Ingresar</button>
				</div>
				
				<div class="contenedorNormal">
					<div class="ui-widget" id="mensajeErrorAjax" style="display:none;">
						<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;text-align:center;">
							<p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
							Ocurrió un error al realizar la solicitud.</p>
						</div>
					</div>
					<div class="ui-widget" id="mensajeExitoAjax" style="display:none;">
						<div class="ui-state-highlight ui-corner-all" style="padding: 0 .7em;text-align:center;">
							<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: .3em;"></span>
							La solicitud fue ejecutada correctamente.</p>
						</div>
					</div>
				</div>
				
				<div id="contenedor-tabla">
					<table class="tabla-principal">
						<tr>
							<th>Año tabla</th>
							<th>Código producto</th>
							<th>Cuota desde</th>
							<th>Cuota hasta</th>
							<th>% Condonación</th>
							<th>fecha cambio</th>
							<th>Hora cambio</th>
							<th>Usuario</th>
							<th>Acci&oacute;n</th>
						</tr>
						<c:forEach items="${sinat04List}" var="sinat04">
							<tr>
								<td class="anopro">${sinat04.anopro}</td>
								<td class="codpro">${sinat04.codpro}</td>
								<td class="nrodes">${sinat04.nrodes}</td>
								<td class="nrohas">${sinat04.nrohas}</td>
								<td>${sinat04.porcen}</td>
								<td>${sinat04.fecsis}</td>
								<td>${sinat04.horsis}</td>
								<td>${sinat04.iduser}</td>
								<td>
									<input type="image" class="editar" src="<%=request.getContextPath() %>/img/file_edit.png" title="editar condonación de gastos de cobranza"/>
									<input type="image" class="eliminar" src="<%=request.getContextPath() %>/img/file_delete.png" title="eliminar condonación de gastos de cobranza"/>
									<!-- <a href="javascript:void(0)" >Eliminar</a> / 
									<a href="javascript:void(0)" class="editar">Modificar</a>  -->
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				
				<!-- AGREGAR sinat04 -->
				
				<div id="dialog-form-add" title="Añadir porcentaje Condonación de gastos de Cobranzas">
					  <!-- <p class="validateTips">Todos los campos son requeridos.</p> -->
					  <h2>Ingresar porcentaje Condonación de gastos de Cobranzas</h2>
					  <form action="" method="post" name="formAddSinat04" id="formAddSinat04" class="form">
			            <fieldset class="form-fieldset fieldset-med">
			                <div class="padding-m">
			                    <div class="field li">
			                        <label class="span2-8">Año tabla</label>
			                        <input type="text" name="anopro" class="text anopro" />
			                    </div>
			                    <!-- <div class="field li">
			                        <label class="span2-8">Código producto</label>
			                        <input type="text" name="codpro" class="text codpro" />
			                    </div> -->
			                    <div class="field li">
			                        <label class="span2-8">Cuota desde</label>
			                        <input type="text" name="nrodes" class="text nrodes" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Cuota hasta</label>
			                        <input type="text" name="nrohas" class="text nrohas" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">% Condonación</label>
			                        <input type="text" name="porcen" class="text porcen" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">fecha cambio</label>
			                        <input type="text" name="fecsis"  class="text fecsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Hora cambio</label>
			                        <input type="text" name="horsis"  class="text horsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Usuario</label>
			                        <input type="text" name="iduser"  class="text iduser" maxlength="10"/>
			                    </div>
			                    <input type="hidden" name="op" value="agregarSinat04">
			                    <!-- <div class="right">
			                        <input type="submit" value="Calcular cuota" class="boton" />
			                    </div> -->
			                </div>
			                <br>
			            </fieldset>
			        </form>
				</div>
				
				<!-- FIN  AGREGAR sinat04 -->
				
				<!-- EDITAR sinat04 -->
				
				<div id="dialog-form-edit" title="Editar porcentaje Condonación de gastos de Cobranzas">
					  <!-- <p class="validateTips">Todos los campos son requeridos.</p> -->
					  <h2>Editar porcentaje Condonación de gastos de Cobranzas</h2>
					  <form action="" method="post" name="formEditSinat04" id="formEditSinat04" class="form">
			            <fieldset class="form-fieldset fieldset-med">
			                <div class="padding-m">
			                    <div class="field li">
			                        <label class="span2-8">Año tabla</label>
			                        <input type="text" name="anopro" class="text anopro" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Código producto</label>
			                        <input type="text" name="codpro" class="text codpro" disabled="disabled"/>
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Cuota desde</label>
			                        <input type="text" name="nrodes" class="text nrodes"/>
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Cuota hasta</label>
			                        <input type="text" name="nrohas" class="text nrohas" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">% Condonación</label>
			                        <input type="text" name="porcen" class="text porcen" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">fecha cambio</label>
			                        <input type="text" name="fecsis"class="text fecsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Hora cambio</label>
			                        <input type="text" name="horsis" class="text horsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Usuario</label>
			                        <input type="text" name="iduser" id="iduserEdit" class="text iduser" maxlength="10"/>
			                    </div>
			                    <input type="hidden" name="op" value="editarSinat04">
			                </div>
			                <br>
			            </fieldset>
			        </form>
				</div>
				
				<div style="display:none;">
					<form action="" method="post" name="formBuscaEditSinat04" id="formBuscaEditSinat04" class="form">
						<input type="hidden" name="anopro" id="anoproEdit">
						<input type="hidden" name="codpro" id="codproEdit">
						<input type="hidden" name="nrodes" id="nrodesEdit">
						<input type="hidden" name="nrohas" id="nrohasEdit">
						<input type="hidden" name="op" value="buscarEditarSinat04">
					</form>
				</div>
				<!-- FIN EDITAR sinat04 -->
				
				<!-- Elimina SINAT 04 -->
				
				<div id="dialog-confirm" title="Eliminar porcentaje Condonación de gastos de Cobranzas">
				  <p>
				  <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
				  ¿Está seguro de eliminar la fila seleccionada?</p>
				</div>
				
				<div style="display:none;">
					<form action="" method="post" name="formDeleteSinat04" id="formDeleteSinat04" class="form">
						<input type="hidden" name="anopro" id="anoproDel">
						<input type="hidden" name="codpro" id="codproDel">
						<input type="hidden" name="nrodes" id="nrodesDel">
						<input type="hidden" name="nrohas" id="nrohasDel">
						<input type="hidden" name="op" value="eliminarSinat04">
					</form>
				</div>
				<!-- FIN eliminar sinat04 -->
				<div id="loading" style="position:fixed; top:55%; left:55%; display:none; z-index: auto" >
					<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>

<script type="text/javascript">

/** **************************AGREGAR sinat04************************************ */
//boton agregar (abre el dialogo)
$("#btnIngresar").button().click(function() {
	$("#dialog-form-add").dialog("open");
}); // termina el boton que abre el dialogo
//dialogo para agregar
$("#dialog-form-add").dialog({
	autoOpen : false,
	height : 500,
	width : 550,
	modal : true,
	buttons : {
		"Agregar" : function() {
			// se deberia validar y realizar el ajax
			$("#formAddSinat04").submit();
		},
		Cancelar : function() {
			$(this).dialog("close");
		}
	}
}); // fin del dialogo para agregar

//valida formulario de usuario al agregar
$("#formAddSinat04").validate({
	rules : {
		anopro : {required : true},
		codpro : {required : true},
		nrodes : {required : true},
		nrohas : {required : true},
		porcen : {required : true},
		fecsis : {required : true},
		horsis : {required : true},
		iduser : {required : true}
	},
	submitHandler : function(form) {
		$("#loading").show();
		$("#dialog-form-add").dialog("close");
		$.ajax({
			type : "POST",
			url : "mantenedorSinat04.do",
			data : $(form).serialize(),
			success : function(data) {
				$("#contenedor-tabla").empty();
				$("#contenedor-tabla").append(data);
				$("#mensajeErrorAjax").hide();
				$("#mensajeExitoAjax").show();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				// mostrar mensaje de error
				$("#mensajeExitoAjax").hide();
				$("#mensajeErrorAjax").show();
			},
			complete: function(){
				$("#loading").hide();
			}
		});

		//$("#loading").hide();
		return false; // required to block normal submit since you used ajax
	}
}); // fin validate

/** ***********FIN AGREGAR sinat04*********************** */

/** ***********EDITAR sinat04*********************** */



//boton agregar (abre el dialogo)

//dialogo para agregar
$("#dialog-form-edit").dialog({
	autoOpen : false,
	height : 500,
	width : 550,
	modal : true,
	buttons : {
		"Editar" : function() {
			// se deberia validar y realizar el ajax
			$("#formEditSinat04").submit();
		},
		Cancelar : function() {
			$(this).dialog("close");
		}
	}
}); // fin del dialogo para editar

$(document).on('click', '.editar', function() {
	
	var tr = $(this).closest("tr");
	$("#anoproEdit").val(tr.find(".anopro").text());
	$("#codproEdit").val(tr.find(".codpro").text());
	$("#nrodesEdit").val(tr.find(".nrodes").text());
	$("#nrohasEdit").val(tr.find(".nrohas").text());
	//$("#formBuscaEditSinat04").submit();
	// ejecutar ajax que llena los datos del formulario
		$("#loading").show();
		$.ajax({
			type : "POST",
			url : "mantenedorSinat04.do",
			data : $("#formBuscaEditSinat04").serialize(),
			success : function(data) {
				$("#formEditSinat04").empty();
				$("#formEditSinat04").append(data);
				$('.anopro').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '9999'});
				$('.codpro').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
				$('.nrodes').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
				$('.nrohas').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
				$('.porcen').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '100'});
				$('.fecsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '99999999'});
				$('.horsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999999'});
				$("#dialog-form-edit").dialog("open");
				//$("#mensajeErrorAjax").hide();
				//$("#mensajeExitoAjax").show();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				// mostrar mensaje de error
				$("#mensajeExitoAjax").hide();
				$("#mensajeErrorAjax").show();
			},
			complete: function(){
				$("#loading").hide();
			}
		});
		//$("#loading").hide();
		//return false; // required to block normal submit since you used ajax
}); // termina el click buscar editar
	


//valida formulario de usuario al editar
$(document).ready(function() {

	$("#formEditSinat04").validate({
		rules : {
			anopro : {required : true},
			codpro : {required : true},
			nrodes : {required : true},
			nrohas : {required : true},
			porcen : {required : true},
			fecsis : {required : true},
			horsis : {required : true},
			iduser : {required : true, maxlength : 10}
		},
		submitHandler : function(form) {
			$("#dialog-form-edit").dialog("close");
			$("#loading").show();
			$.ajax({
				type : "POST",
				url : "mantenedorSinat04.do",
				data : $(form).serialize(),
				success : function(data) {
					$("#contenedor-tabla").empty();
					$("#contenedor-tabla").append(data);
					$("#mensajeErrorAjax").hide();
					$("#mensajeExitoAjax").show();
				},
				error : function(xhr, ajaxOptions, thrownError) {
					// mostrar mensaje de error
					$("#mensajeExitoAjax").hide();
					$("#mensajeErrorAjax").show();
				},
				complete: function(){
					$("#loading").hide();
				}
			});
			//$("#loading").hide();
			return false; // required to block normal submit since you used
			// ajax
		}
	}); // fin validate
}); // fin doc ready

/** ***********FIN EDITAR sinat04***************** */

/** ***********ELIMINAR sinat04***************** */

$(document).on('click','.eliminar', function() {
	var tr = $(this).closest("tr");
	$("#anoproDel").val(tr.find(".anopro").text());
	$("#codproDel").val(tr.find(".codpro").text());
	$("#nrodesDel").val(tr.find(".nrodes").text());
	$("#nrohasDel").val(tr.find(".nrohas").text());
	$("#dialog-confirm").dialog("open");
});


$(function() {
	$("#dialog-confirm").dialog({
		autoOpen : false,
		resizable : true,
		height : 200,
		width : 350,
		modal : false,
		open : function() {
			$('.ui-dialog button').removeClass('ui-state-focus');
		},
		buttons : {
			Aceptar : function() {
				// ajax de elimnar usuario formDeleteSinat04
				$(this).dialog("close");
				$("#loading").show();
				$.ajax({
					type : "POST",
					url : "mantenedorSinat04.do",
					data : $("#formDeleteSinat04").serialize(),
					success : function(data) {
						$("#contenedor-tabla").empty();
						$("#contenedor-tabla").append(data);
						$("#mensajeErrorAjax").hide();
						$("#mensajeExitoAjax").show();
					},
					error : function(xhr, ajaxOptions, thrownError) {
						// mostrar mensaje de error
						$("#mensajeExitoAjax").hide();
						$("#mensajeErrorAjax").show();
					},
					complete: function(){
						$("#loading").hide();
					}
				});
				//$("#loading").hide();
				return false; // required to block normal submit since you
				// used ajax
			},
			Cancelar : function() {
				$(this).dialog("close");
			}
		}
	});
});


/** ***********BUSCAR sinat04***************** */


/** ***********FIN BUSCAR sinat04***************** */
 $("#btnBuscar").click(function(){
	 $("#formBuscar").submit();
 });
 
 $("#formBuscar").submit(function() {
		$("#loading").show();
		$.ajax({
			type : "POST",
			url : "mantenedorSinat04.do",
			data : $(this).serialize(),
			success : function(data) {
				$("#contenedor-tabla").empty();
				$("#contenedor-tabla").append(data);
				$("#mensajeErrorAjax").hide();
				$("#mensajeExitoAjax").show();
			},
			error : function(xhr, ajaxOptions, thrownError) {
				// mostrar mensaje de error
				$("#mensajeExitoAjax").hide();
				$("#mensajeErrorAjax").show();
			},
			complete: function(){
				$("#loading").hide();
			}
		});
		//$("#loading").hide();
		$("#entrada").focus();
		return false; // required to block normal submit since you used ajax
	});
 
 /** ***********FIN ELIMINAR sinat04***************** */


/** ***********GENERICO sinat04***************** */

$(document).ready(function() {
	

	$("#btnBuscar").button({
		icons : {
			primary : "ui-icon-circle-zoomout"
		}
	});

	$("#btnIngresar").button({
		icons : {
			primary : "ui-icon-circle-plus"
		}
	});

	$('.anopro').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '9999'});
	$('.codpro').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
	$('.nrodes').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
	$('.nrohas').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
	$('.porcen').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '100'});
	$('.fecsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '99999999'});
	$('.horsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999999'});
	

}); // fin document ready

/** ***********FIN GENERICO sinat04***************** */

</script>	
</body>
</html>