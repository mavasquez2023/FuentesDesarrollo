var ciudades = new Array();
var comunas = new Array();
var regiones = new Array();
var tiposEmpresa = new Array();
tiposEmpresa[tiposEmpresa.length] = new Array("1", "Publica");
tiposEmpresa[tiposEmpresa.length] = new Array("2", "Privada");

var estados = new Array();
estados[estados.length] = new Array("HA", "Habilitado");
estados[estados.length] = new Array("DE", "deshabilitado");

var mutuales = new Array();
var estadosSiNo = new Array();
estadosSiNo[estadosSiNo.length] = new Array("SI", "SI");
estadosSiNo[estadosSiNo.length] = new Array("NO", "NO");

var cajas = new Array();
var actividadesEconomicas = new Array();

var numeroTrabajadores = new Array();
numeroTrabajadores = new Array();
numeroTrabajadores[numeroTrabajadores.length] = new Array("1", "1 - 10");
numeroTrabajadores[numeroTrabajadores.length] = new Array("11", "11 - 100");
numeroTrabajadores[numeroTrabajadores.length] = new Array("101", "101 - 500");
numeroTrabajadores[numeroTrabajadores.length] = new Array("501", "501 - 2000");
numeroTrabajadores[numeroTrabajadores.length] = new Array("2001", "2001 o mas");

var espere = "Por Favor Espere.";
var texto_autenticando = "Autenticando. " + espere;
var texto_generando_clave = "Generando Clave. " + espere;
var texto_cargando_informacion = "Cargando Informaci&oacute;n. " + espere;
var texto_guardando_informacion = "Guardando Informaci&oacute;n. " + espere;
var texto_creacion_empresa = "Creando Empresa. " + espere;

var texto_autenticacion_error_captcha = "Ingreso Código Imagen Erroneo.";
var texto_autenticacion_error_datos_invalidos = "Los Datos Ingresados No Son V&aacute;lidos.";
var texto_autenticacion_error_empresa_existe = "La Empresa ya existe, no es posible acceder al sistema";
var texto_autenticacion_error_empresa_en_proceso = "La Empresa ya tiene una solicitud de registro en proceso.";
var texto_autenticacion_exitosa = "Autenticaci&oacute;n Exitosa";

var formatos = new Array();

function obtenerCiudadesPorRegion(region) {
	var ciudadesTMP = new Array();

	for ( var i = 0; i < ciudades.length; i++) {
		if (ciudades[i][2] == region)
			ciudadesTMP[ciudadesTMP.length] = ciudades[i];
	}
	return ciudadesTMP;
}

function obtenerComunasPorCiudad(ciudad) {
	var comunasTMP = new Array();

	for ( var i = 0; i < comunas.length; i++) {
		if (comunas[i][2] == ciudad)
			comunasTMP[comunasTMP.length] = comunas[i];
	}
	return comunasTMP;
}

function cargarDataInput(id, textoDefault, class_hint, class_default, value,
		data, _function_onBlur, _function_onKeyPress) {

	sysout("cargando Input: " + id);

	var resultado = false;

	var espacio = 1;
	var type = $("#" + id).prop("type");

	if (type == "text") {
		$("#" + id).val(value);
	} else if (type == "select-one") {
		cargarDataCampoSelect(data, id, value);
		espacio = 1;
	} else if (type == "checkbox") {
		cargarDataCampoCheckbox(id, value);
		class_hint = "";
		espacio = -50;

	} else if (type == "textarea") {
		$("#" + id).val(value);
	}

	var visible = $("#" + id).is(":visible");
	var resultado_validacion = validaCampo(id, value, textoDefault);

	if (resultado_validacion == "nulo")
		return;
	if (!visible)
		return resultado_validacion;

	$("#info_" + id).remove();

	var obj_validacion = obtenerItemValidacion(id);
	var es_obligatorio = true;
	try {
		es_obligatorio = obj_validacion.obligatorio;
	} catch (e) {
		es_obligatorio = false;
	}

	cargarImagenValidacion(id, icon_validacion_campo_ok);

	if (!resultado_validacion) {
		try {
			if (es_obligatorio) {
				$("#" + id).attr("class", class_hint);
				$("#info_" + id).attr("src", icon_validacion_campo_error);
				$("#info_" + id).attr("title", obtenerItemValidacion(id).error);
				if (type != "checkbox")
					$("#" + id).val(textoDefault);

				$("#info_" + id).show();
			}
		} catch (e) {
		}
	} else {
		if (es_obligatorio) {
			$("#info_" + id).attr("src", icon_validacion_campo_ok);
			$("#info_" + id).show();
		}
		resultado = true;
	}

	agregarEventosInput(id, textoDefault, class_hint, class_default, value,
			data, _function_onBlur, _function_onKeyPress);

	return resultado;
}

function cargarImagenValidacion(id, icon) {
	var espacio = 0;
	var espacio_top = 0;
	var type = $("#" + id).prop("type");
	if (type == "select-one") {
		espacio = 1;
		if (isIE) {
			espacio_top = -3;
		} else {
			espacio_top = -3;
		}
	}
	if (type == "text") {
		espacio = 1;
		if (isIE) {
			espacio_top = -3;
		} else {
			espacio_top = -3;
		}
	} else if (type == "checkbox") {
		if (isIE) {
			espacio = 0;
		} else {
			espacio = 120;
		}
		espacio_top = -30;
	}

	var width = $("#" + id).css("width");
	if (width != null && width != "") {
		width = parseInt(width.split("px")[0]) + espacio;
	} else {
		width = 180 + espacio;
	}
	var top = $("#" + id).offset().top + 5 + espacio_top;
	var left = $("#" + id).offset().left + width;

	$("body")
			.append(
					"<img id='info_"
							+ id
							+ "' src='"
							+ icon
							+ "' class='icono_resultado img_info' style='position:absolute;left:"
							+ left
							+ "px;top:"
							+ (top - 2)
							+ "px;display:none;' title='Dato Obligatorio'></img>");
}

function procesarResultadoValidacionCampoObligatorio(id) {
	if (obtenerItemValidacion("nombre_acividad_economica_empresa").estado_validacion) {
		$("#info_" + id).attr("src", icon_validacion_campo_ok);
		$("#" + id).attr("class", "");
	} else {
		var type = $("#" + id).prop("type");
		if (type != "checkbox"){
			$("#" + id).val("");
		}
		$("#" + id).attr("class", "hint");
		$("#info_" + id).attr("src", icon_validacion_campo_error);
		$("#info_" + id).attr("title", obtenerItemValidacion(id).error);
		sysout("seteo value 3");
		mostrarInformacionValidacion(id);
	}
}

function agregarEventosInput(id, textoDefault, class_hint, class_default,
		value, data, _function_onBlur, _function_onKeyPress, top, left) {
	$("#" + id)
			.focus(
					function() {
						if (obtenerItemValidacion(id) != null) {
							if ($("#" + id).val() == textoDefault) {
								$("#" + id).val("");
								$("#" + id).attr("class", class_default);
							}

							if (obtenerItemInput(id).eventos_cargados != true) {
								$("#" + id)
										.change(
												function() {
													sysout("onchange cambio texto : "
															+ id
															+ ", "
															+ $("#" + id).val())
													var relacionado = obtenerRelacionado(id);
													validaCampo(id, $("#" + id)
															.val(),
															textoDefault);
													if (obtenerItemValidacion(id).estado_validacion != true) {
														sysout("Seteo value 1");
														try {
															var type = $("#" + id).prop("type");
															sysout("type: "+ type)
															if (type != "checkbox"){
																$("#" + id)
																		.val(
																				textoDefault);
															}
															$("#" + id).attr(
																	"class",
																	class_hint);
															$("#info_" + id)
																	.attr(
																			"src",
																			icon_validacion_campo_error);
															$("#info_" + id)
																	.attr(
																			"title",
																			obtenerItemValidacion(id).error);
															mostrarInformacionValidacion(id);
															if (relacionado != null
																	&& relacionado.length > 0) {
																if ($("#" + id)
																		.val().length == 0
																		&& $(
																				"#"
																						+ relacionado)
																				.val().length == 0) {
																	obtenerItemValidacion(id).obligatorio = false;
																	$("#" + id)
																			.attr(
																					"class",
																					class_default);
																	$(
																			"#info_"
																					+ id)
																			.hide();
																	if (relacionado != null
																			&& relacionado.length > 0) {
																		obtenerItemValidacion(relacionado).obligatorio = false;
																		$(
																				"#"
																						+ relacionado)
																				.attr(
																						"class",
																						class_default);
																		$(
																				"#info_"
																						+ relacionado)
																				.hide();
																	}

																}
															}
														} catch (e) {
														}
													} else {
														$("#" + id).attr(
																"class",
																class_default);
														$("#info_" + id)
																.attr("src",
																		icon_validacion_campo_ok);
														if (relacionado != null
																&& relacionado.length > 0) {
															obtenerItemValidacion(id).obligatorio = true;
															obtenerItemValidacion(relacionado).obligatorio = true;
															validaCampo(id, $(
																	"#" + id)
																	.val(),
																	textoDefault);
															if (obtenerItemValidacion(id).estado_validacion == true)
																$("#info_" + id)
																		.attr(
																				"src",
																				icon_validacion_campo_ok);
															else
																$("#info_" + id)
																		.attr(
																				"src",
																				icon_validacion_campo_error);

															$("#info_" + id)
																	.show();

															if (relacionado != null
																	&& relacionado.length > 0) {
																validaCampo(
																		relacionado,
																		$(
																				"#"
																						+ relacionado)
																				.val(),
																		"");
																if (obtenerItemValidacion(relacionado).estado_validacion == true) {
																	$(
																			"#info_"
																					+ relacionado)
																			.attr(
																					"src",
																					icon_validacion_campo_ok);
																} else {
																	$(
																			"#info_"
																					+ relacionado)
																			.attr(
																					"src",
																					icon_validacion_campo_error);
																}
																$(
																		"#info_"
																				+ relacionado)
																		.show();
															}

														}

													}
												});
								$("#" + id)
										.blur(
												function() {

													sysout("onblur cambio texto : "
															+ id
															+ ", "
															+ $("#" + id).val())
													if (_function_onBlur != null) {
														_function_onBlur($(
																"#" + id).val());
													} else {
														validaCampo(id,
																$("#" + id)
																		.val(),
																textoDefault);
														if (obtenerItemValidacion(id).estado_validacion != true) {
															sysout("seteo value 2")
															var type = $("#" + id).prop("type");
															if (type != "checkbox"){
																$("#" + id)
																		.val(
																				textoDefault);
															}
															$("#" + id).attr(
																	"class",
																	class_hint);
															$("#info_" + id)
																	.attr(
																			"src",
																			icon_validacion_campo_error);
															$("#info_" + id)
																	.attr(
																			"title",
																			obtenerItemValidacion(id).error);
															mostrarInformacionValidacion(id);
														} else {
															$("#info_" + id)
																	.attr(
																			"src",
																			icon_validacion_campo_ok);
															$("#" + id)
																	.attr(
																			"class",
																			class_default);
														}
													}
													obtenerItemInput(id).eventos_cargados = true;

												});
								if (_function_onKeyPress != null) {
									$("#" + id).keypress(function(event) {
										return _function_onKeyPress(event);
									});
								}

								var type = $("#" + id).prop("type");
								if (type == "checkbox") {
									$(':checkbox')
											.change(
													function() {

														validaCampo(
																id,
																getValueCheckbox(id),
																textoDefault);

														if (obtenerItemValidacion(id).estado_validacion == true)
															$("#info_" + id)
																	.attr(
																			"src",
																			icon_validacion_campo_ok);
														else
															$("#info_" + id)
																	.attr(
																			"src",
																			icon_validacion_campo_error);
													});

								}

							}

						}

					});

}

function procesarResultadoValidacionCampo(id, textoDefault) {
	if (!validaCampo(id, $("#" + id).val(), textoDefault)) {
		$("#" + id).val(textoDefault);
		$("#" + id).attr("class", "hint");
		$("#info_" + id).attr("src", icon_validacion_campo_error);
		$("#info_" + id).attr("title", obtenerItemValidacion(id).error);
	} else {
		$("#info_" + id).attr("src", icon_validacion_campo_ok);
		$("#" + id).attr("class", "");
	}

}

function validarDataInput(id, textoDefault, class_hint, class_default, value,
		data) {

	var resultado = false;
	var resultado_validacion = validaCampo(id, $("#" + id).val(), textoDefault);

	if (!resultado_validacion) {
		resultado = false;
	} else {
		resultado = true;
	}

	return resultado;
}

function cargarCiudadesPorRegion(id_region, id_ciudad, id_comuna, valor) {

	var items = obtenerCiudadesPorRegion($("#" + id_region).val())
	cargarDataCampoSelect(items, id_ciudad, "");
	var items_2 = obtenerComunasPorCiudad(valor)
	cargarDataCampoSelect(items_2, id_comuna, "");

}
function cargarComunasPorCiudad(id_ciudad, id_comuna) {
	var items = obtenerComunasPorCiudad($("#" + id_ciudad).val())
	cargarDataCampoSelect(items, id_comuna, "");

}

function cargarDataCampoSelect(data, id, value) {
	if(id=="caja_compensacion"){
		value="3";
	}
	$("#" + id).html("");
	if (obtenerItemInput(id).mostrarItemSeleccione != false) {
		if (value == "") {
			$("#" + id).append("<option value='' selected>Seleccione</option>");
		} else {
			$("#" + id).append("<option value='' >Seleccione</option>");
		}
	}
	for ( var i = 0; i < data.length; i++) {
		var selected = "";
		if (value == data[i][0]){
			selected = " selected ";
		}
		$("#" + id).append(
				"<option value='" + data[i][0] + "' " + selected + ">"
						+ data[i][1] + "</option>");
	}

}

function seleccionarValorSelect(id, value) {
	$("#" + id).val(value);
	var encontrado = false;
	$("#" + id + " option").each(function() {
		if ($(this).text() == value) {
			encontrado = true;
		}
	});

	if (!encontrado) {
		$("#" + id).val("");
	}

}

function cargarDataCampoCheckbox(id, value) {
	sysout("tag1: cargando Checkbox");
	var checks = document.getElementsByName(id);
	var values = value.split("::");

	limpiarValoresCheck(id);
	sysout("tag1: values #" + values.length);
	if (values.length > 0) {
		for ( var i = 0; i < values.length; i++) {
			if (values[i].length > 0) {

				for ( var ii = 0; ii < checks.length; ii++) {
					if (checks[ii].value.toUpperCase() == values[i]
							.toUpperCase()) {
						checks[ii].checked = true;
						ii = checks.length;
					}

				}
			}

		}
	}

}

function limpiarValoresCheck(id) {
	sysout("tag1: Limpiando check")
	var checks = document.getElementsByName(id);
	for ( var i = 0; i < checks.length; i++) {
		checks[i].checked = false;
	}
}

function getValueCheckbox(id) {
	var checks = document.getElementsByName(id);
	var values = "";
	for ( var i = 0; i < checks.length; i++) {
		if (checks[i].checked) {
			values += checks[i].value + "::";
		}

	}
	return values;
}

var formulario_cargado = "empresa";
function cargarValores(formulario) {
	$(".mi_tab_selected").attr("class", "mi_tab");
	$("#tab_" + formulario).attr("class", "mi_tab mi_tab_selected");
	if (!carga_inicial && hayCambios(formulario_cargado)) {
		procesarConfirmacionCambio(formulario);
		ocultarCargando();
		return false;
	} else {
		continuarCarga(formulario);
		carga_inicial = false;
	}
	ocultarCargando();
	setTimeout(function() {
		ocultarCargando();
	}, 5000);

}

function continuarCarga(formulario) {
	$("#" + formulario_cargado).hide();
	$("#" + formulario).fadeIn(1000);
	$(".active").attr("class", "");
	$("#li_" + formulario).attr("class", "active");
	formulario_cargado = formulario;
	mostrarCargando(texto_cargando_informacion);
	cargarAllInputs();
	eliminarIconos();
	if (formulario == "guardar") {
		setTimeout(function() {
			if (obtenerEstadoValidacion()) {
				$("#texto_datos_faltantes").hide();
				$("#boton_grabar").show();
			} else {
				$("#texto_datos_faltantes").show();
				$("#boton_grabar").hide();
			}
			ocultarCargando();
		}, 1000);
	} else {
		if (formulario == "formato_nomina") {

			for ( var i = 0; i < formatos.length; i++) {
				$("#descripcion_formato_" + formatos[i].CODIGO).html(
						formatos[i].DESCRIPCION);
			}

		}
		validarDatosIngresados();

		setTimeout(
				function() {
					if (!estadoValidacion[formulario]) {
						mostrarInfoWarning("Ingrese los datos faltantes en el formulario ");
					}
				}, 2000);

	}

	ocultarCargando();
}

function eliminarIconos() {
	$(".icono_resultado").each(function() {
		$(this).hide();
	});
	$(".hint").each(function() {
		$(this).attr("class", "");
	});
}

function validarDatosIngresados() {
	var resultado = true;
	var form = "";

	setTimeout(function() {
		cargarInputsFormulario("admin");
		cargarInputsFormulario("empresa");
		cargarInputsFormulario("casa_matriz");
		cargarInputsFormulario("previsionales");
		cargarInputsFormulario("formato_nomina");
		ocultarCargando();
	}, 1000);

	validacion_campos = resultado;
}

function obtenerEstadoValidacion() {
	return  estadoValidacion["empresa"]
			&& estadoValidacion["casa_matriz"]
			&& estadoValidacion["previsionales"];
	/* return estadoValidacion["admin"] && estadoValidacion["empresa"]
			&& estadoValidacion["casa_matriz"]
			&& estadoValidacion["previsionales"]
			&& estadoValidacion["formato_nomina"];
		*/
}

function guardarCambiosFormulario(form) {
	mostrarCargando(texto_guardando_informacion);
	var resultado = validarInputs(form);
	continuarCarga(form);

}

function guardarCambiosFormularioSalir(form) {
	mostrarCargando(texto_guardando_informacion);
	var resultado = validarInputs(form);
	continuarCarga(form);
	$("#confirmacion_salir_app").dialog({
		resizable : false,
		height : 250,
		width : 550,
		modal : true,
		buttons : {
			"Cancelar" : function() {
				$(this).dialog("close");

			},
			"Salir" : function() {
				$(this).dialog("close");
				setTimeout(function() {
					location.href = "index.jsp"
				}, 500);
			}
		}
	});

}

function cargarInputsFormulario(form) {
	cargarInputs(form);
	if (estadoValidacion[form]) {
		$("#img_datos_" + form).attr("src", icon_validacion_pestana_ok);
		return true;
	} else {
		$("#img_datos_" + form).attr("src", icon_validacion_pestana_error);
		return false;
	}
}

function validarCamposFormulario(form) {
	var resultado = validarInputs(form);
	if (resultado)
		$("#img_datos_" + form).attr("src", icon_validacion_pestana_ok);
	else
		$("#img_datos_" + form).attr("src", icon_validacion_pestana_error);

	return resultado;
}

function procesarConfirmacionCambio(formulario) {

	var nombre_pestana = "";

	if (formulario_cargado == "empresa") {
		nombre_pestana = "Empresa";
	} else if (formulario_cargado == "casa_matriz") {
		nombre_pestana = "Casa Matriz";
	} else if (formulario_cargado == "previsionales") {
		nombre_pestana = "Entidades Previsionales";
	} else if (formulario_cargado == "admin") {
		nombre_pestana = "Administrador";
	} else if (formulario_cargado == "formato_nomina") {
		nombre_pestana = "Formato N&oacute;mina";
	}

	var html_conf = '<br>'
			+ '&#191; Desea guardar los cambios realizados en la pesta&ntilde;a '
			+ nombre_pestana + ' ?' + '<br><br>' + '</div>';

	$("#confirmacion_cambio_tab").html(html_conf)
	$("#confirmacion_cambio_tab").dialog({
		resizable : false,
		height : 200,
		width : 550,
		modal : true,
		buttons : {
			"No Guardar" : function() {
				$(this).dialog("close");
				continuarCarga(formulario);

			},
			"Guardar" : function() {
				$(this).dialog("close");
				guardarCambiosFormulario(formulario_cargado)
				cargarValores(formulario);
			}
		}
	});
	return false;
}

function mostrarCargando(texto) {
	$("#texto_cargando").html(texto);
	$(".info_validacion").remove();
	if (!$('#texto_cargando').is(':visible')) {

		$("#cargando").fadeIn("slow");
	}

}
function ocultarCargando() {
	$("#cargando").fadeOut("slow");
}