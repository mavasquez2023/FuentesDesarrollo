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
				<p id="titulo" class="titulo">Administraci&oacute;n de porcentaje Condonación de Gravámenes</p>
				<div id="buscador">
					<!-- <form action="" method="post" id="formBuscar">
						<input type="text" name="tipcod" id="entrada" />
						<input type="hidden" name="op" value="buscarSinat03" />
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
							<th>Código tabla</th>
							<th>% Condonación</th>
							<th>Fecha cambio</th>
							<th>Hora cambio</th>
							<th>Usuario</th>
							<th>Acci&oacute;n</th>
						</tr>
						<c:forEach items="${sinat03List}" var="sinat03">
							<tr>
								<td class="elCodigo">${sinat03.tipcod}</td>
								<td>${sinat03.porcen}</td>
								<td>${sinat03.fecsis}</td>
								<td>${sinat03.horsis}</td>
								<td>${sinat03.iduser}</td>
								<td>
									<input type="image" class="editar" src="<%=request.getContextPath() %>/img/file_edit.png" title="editar condonación gravamenes"/>
									<input type="image" class="eliminar" src="<%=request.getContextPath() %>/img/file_delete.png" title="eliminar condonación gravamenes"/>
									<!-- <a href="javascript:void(0)" >Eliminar</a> / 
									<a href="javascript:void(0)" class="editar">Modificar</a>  -->
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- AGREGAR SINAT03 -->
				<div id="dialog-form-add" title="Añadir porcentaje Condonación de Gravámenes">
					  <!-- <p class="validateTips">Todos los campos son requeridos.</p> -->
					  <h2>Ingresar porcentaje Condonación de Gravámenes</h2>
					  <form action="" method="post" name="formAddSinat03" id="formAddSinat03" class="form">
			            <fieldset class="form-fieldset fieldset-med">
			                <div class="padding-m">
			                    <div class="field li">
			                        <label class="span2-8">Código tabla</label>
			                        <input type="text" name="tipcod" id="tipcodAdd" class="text tipcod" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">% Condonación</label>
			                        <input type="text" name="porcen" id="porcenAdd" class="text porcen" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Fecha cambio</label>
			                        <input type="text" name="fecsis" id="fecsisAdd" class="text fecsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Hora cambio</label>
			                        <input type="text" name="horsis" id="horsisAdd" class="text horsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Usuario</label>
			                        <input type="text" name="iduser" id="iduserAdd" class="text iduser" maxlength="10"/>
			                    </div>
			                    <input type="hidden" name="op" value="agregarSinat03">
			                    <!-- <div class="right">
			                        <input type="submit" value="Calcular cuota" class="boton" />
			                    </div> -->
			                </div>
			                <br>
			            </fieldset>
			        </form>
				</div>
				
				<!-- FIN  AGREGAR SINAT03 -->
				
				<!-- EDITAR SINAT03 -->
				
				<div id="dialog-form-edit" title="Editar porcentaje Condonación de Gravámenes">
					  <!-- <p class="validateTips">Todos los campos son requeridos.</p> -->
					  <h2>Editar porcentaje Condonación de Gravámenes</h2>
					  <form action="" method="post" name="formEditSinat03" id="formEditSinat03" class="form">
			            <fieldset class="form-fieldset fieldset-med">
			                <div class="padding-m">
			                    <div class="field li">
			                        <label class="span2-8">Código tabla</label>
			                        <input type="text" name="tipcod" id="tipcodEdit" class="text tipcod" disabled="disabled"/>
			                        <label class="span2-8"></label>
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">% Condonación</label>
			                        <input type="password" name="porcen" id="porcenEdit" class="text porcen" />
			                    </div>
			                   
			                    <div class="field li">
			                        <label class="span2-8">Fecha cambio</label>
			                        <input type="text" name="fecsis" id="fecsisEdit" class="text fecsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Hora cambio</label>
			                        <input type="text" name="horsis" id="horsisEdit" class="text horsis" />
			                    </div>
			                    <div class="field li">
			                        <label class="span2-8">Usuario</label>
			                        <input type="text" name="iduser" id="iduserEdit" class="text iduser" maxlength="10"/>
			                    </div>
			                    <input type="hidden" name="op" value="editarSinat03">
			                    <!-- <div class="right">
			                        <input type="submit" value="Calcular cuota" class="boton" />
			                    </div> -->
			                </div>
			                <br>
			            </fieldset>
			        </form>
				</div>
				
				<!-- FIN EDITAR SINAT03 -->
				
				
				<!-- Elimina SINAT 03 -->
				
				<div id="dialog-confirm" title="Eliminar porcentaje Condonación de Gravámenes">
				  <p>
				  <span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
				  ¿Está seguro de eliminar la fila seleccionada?</p>
				</div>
				
				<div style="display:none;">
					<form action="" method="post" name="formDeleteSinat03" id="formDeleteSinat03" class="form">
						<input type="hidden" name="tipcod" id="elCodigoDel">
						<input type="hidden" name="op" value="eliminarSinat03">
					</form>
				</div>
				<!-- FIN eliminar SINAT03 -->
				<div id="loading" style="position:fixed; top:55%; left:55%; display:none; z-index: auto" >
					<img src="<%=request.getContextPath() %>/img/3d-loader.gif">
				</div>
			</div>
		</div>
	</div>
	<jsp:include flush="true" page="/comun/footer.jsp"></jsp:include>

<script type="text/javascript">

/** **************************AGREGAR SINAT03************************************ */
//boton agregar (abre el dialogo)
$("#btnIngresar").button().click(function() {
	$("#dialog-form-add").dialog("open");
}); // termina el boton que abre el dialogo
//dialogo para agregar
$("#dialog-form-add").dialog({
	autoOpen : false,
	height : 400,
	width : 500,
	modal : true,
	buttons : {
		"Agregar" : function() {
			// se deberia validar y realizar el ajax
			$("#formAddSinat03").submit();
		},
		Cancelar : function() {
			$(this).dialog("close");
		}
	}
}); // fin del dialogo para agregar

//valida formulario de usuario al agregar
$("#formAddSinat03").validate({
	rules : {
		tipcod : {
			required : true
		},
		porcen : {
			required : true
		},
		fecsis : {
			required : true
		},
		horsis : {
			required : true
		},
		iduser : {
			required : true
		}
	},
	submitHandler : function(form) {
		$("#loading").show();
		$("#dialog-form-add").dialog("close");
		$.ajax({
			type : "POST",
			url : "mantenedorSinat03.do",
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

/** ***********FIN AGREGAR SINAT03*********************** */

/** ***********EDITAR SINAT03*********************** */

//boton agregar (abre el dialogo)
$(document).on('click', '.editar', function() {
	$("#loading").show();
	var tr = $(this).closest("tr");
	var elCodigo = tr.find(".elCodigo").text();
	// ejecutar ajax que llena los datos del formulario
	$.ajax({
		type : "POST",
		url : "mantenedorSinat03.do",
		data : {
			tipcod : elCodigo,
			op: "buscarEditarSinat03"
		},
		success : function(data) {
			$("#formEditSinat03").empty();
			$("#formEditSinat03").append(data);
			$('.tipcod').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999'});
			$('.porcen').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '100'});
			$('.fecsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '99999999'});
			$('.horsis').autoNumeric('init', {aSep : '',aDec : ',',mDec : '0',vMax : '999999'});
			$("#dialog-form-edit").dialog("open");
			//$("#mensajeErrorAjax").hide();
			//$("#mensajeExitoAjax").show();
			$("#loading").hide();
		},
		error : function(xhr, ajaxOptions, thrownError) {
			// mostrar mensaje de error
			$("#mensajeExitoAjax").hide();
			$("#mensajeErrorAjax").show();
			$("#loading").hide();
		},
		complete: function(){
			$("#loading").hide();
		}
	});
	
	// return false; // required to block normal submit since you used ajax
	
}); // termina el boton que abre el dialogo

//dialogo para agregar
$("#dialog-form-edit").dialog({
	autoOpen : false,
	height : 400,
	width : 500,
	modal : true,
	buttons : {
		"Editar" : function() {
			// se deberia validar y realizar el ajax
			$("#formEditSinat03").submit();
		},
		Cancelar : function() {
			$(this).dialog("close");
		}
	}
}); // fin del dialogo para editar

//valida formulario de usuario al editar
$(document).ready(function() {

	$("#formEditSinat03").validate({
		rules : {
			tipcod : {
				required : true
			},
			porcen : {
				required : true
			},
			fecsis : {
				required : true
			},
			horsis : {
				required : true
			},
			iduser : {
				required : true, maxlength : 10
			}
		},
		submitHandler : function(form) {
			$("#dialog-form-edit").dialog("close");
			$("#loading").show();
			$.ajax({
				type : "POST",
				url : "mantenedorSinat03.do",
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
			
			return false; // required to block normal submit since you used
			// ajax
		}
	}); // fin validate
}); // fin doc ready

/** ***********FIN EDITAR SINAT03***************** */

/** ***********ELIMINAR SINAT03***************** */

$(document).on('click','.eliminar', function() {
	$("#dialog-confirm").dialog("open");
	var tr = $(this).closest("tr");
	var elCodigo = tr.find(".elCodigo").text();
	$("#elCodigoDel").val(elCodigo);
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
				// ajax de elimnar usuario formDeleteSinat03
				$(this).dialog("close");
				$("#loading").show();
				$.ajax({
					type : "POST",
					url : "mantenedorSinat03.do",
					data : $("#formDeleteSinat03").serialize(),
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


/** ***********BUSCAR SINAT03***************** */



/** ***********FIN BUSCAR SINAT03***************** */
 $("#btnBuscar").click(function(){
	 $("#formBuscar").submit();
 });
 
 $("#formBuscar").submit(function() {
		$("#loading").show();
		$.ajax({
			type : "POST",
			url : "mantenedorSinat03.do",
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
 
 /** ***********FIN ELIMINAR SINAT03***************** */


/** ***********GENERICO SINAT03***************** */

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

	$('.tipcod').autoNumeric('init', {
		aSep : '',
		aDec : ',',
		mDec : '0',
		vMax : '999'
	});
	$('.porcen').autoNumeric('init', {
		aSep : '',
		aDec : ',',
		mDec : '0',
		vMax : '100'
	});
	$('.fecsis').autoNumeric('init', {
		aSep : '',
		aDec : ',',
		mDec : '0',
		vMax : '99999999'
	});
	$('.horsis').autoNumeric('init', {
		aSep : '',
		aDec : ',',
		mDec : '0',
		vMax : '999999'
	});

}); // fin document ready

/** ***********FIN GENERICO SINAT03***************** */

</script>	
</body>
</html>