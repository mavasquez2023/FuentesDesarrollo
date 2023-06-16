var validaciones = new Array();
var errors = new Array();

var estadoValidacion = new Array();

var validacion_campos = false;
function validacion(campo, validaNoNulo, validaLargo, largoMinimo, largoMaximo,
		validaEstructura, validaSoloNumeros, estructura, ejemplo) {
	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = validaNulo;
	validaciones[validaciones.length - 1].validaLargo = validaLargo;
	validaciones[validaciones.length - 1].validaEstructura = validaEstructura;
	validaciones[validaciones.length - 1].largoMaximo = largoMaximo;
	validaciones[validaciones.length - 1].largoMinimo = largoMinimo;
	validaciones[validaciones.length - 1].estructura = estrutura;
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].validaSoloNumeros = validaSoloNumeros;
	validaciones[validaciones.length - 1].obligatorio = true;
}

function validacionNoNulo(campo, ejemplo) {
	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = true;
	validaciones[validaciones.length - 1].validaLargo = false;
	validaciones[validaciones.length - 1].validaEstructura = false;
	validaciones[validaciones.length - 1].largoMaximo = 0;
	validaciones[validaciones.length - 1].largoMinimo = 0;
	validaciones[validaciones.length - 1].estructura = "";
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].validaSoloNumeros = false;
	validaciones[validaciones.length - 1].obligatorio = true;
	sysout("Validacion No Nulo: "
			+ validacionToString(validaciones[validaciones.length - 1]))
}

function validacionLargo(campo, largoMinimo, largoMaximo, ejemplo) {

	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = false;
	validaciones[validaciones.length - 1].validaLargo = true;
	validaciones[validaciones.length - 1].validaEstructura = false;
	validaciones[validaciones.length - 1].largoMaximo = largoMaximo;
	validaciones[validaciones.length - 1].largoMinimo = largoMinimo;
	validaciones[validaciones.length - 1].estructura = "";
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].validaSoloNumeros = false;
	validaciones[validaciones.length - 1].obligatorio = true;
	sysout("Validacion Largo: "
			+ validacionToString(validaciones[validaciones.length - 1]))
}

function validacionLargoFijo(campo, largo, ejemplo) {

	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = false;
	validaciones[validaciones.length - 1].validaLargo = false;
	validaciones[validaciones.length - 1].validaLargoFijo = true;
	validaciones[validaciones.length - 1].validaEstructura = false;
	validaciones[validaciones.length - 1].estructura = "";
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].validaSoloNumeros = false;
	validaciones[validaciones.length - 1].obligatorio = true;
	validaciones[validaciones.length - 1].largo = largo;
	sysout("Validacion Largo: "
			+ validacionToString(validaciones[validaciones.length - 1]))
}

function validacionEstructura(campo, estructura, ejemplo) {
	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = false;
	validaciones[validaciones.length - 1].validaLargo = false;
	validaciones[validaciones.length - 1].validaEstructura = true;
	validaciones[validaciones.length - 1].largoMaximo = 0;
	validaciones[validaciones.length - 1].largoMinimo = 0;
	validaciones[validaciones.length - 1].estructura = estructura;
	validaciones[validaciones.length - 1].validaSoloNumeros = false;
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].obligatorio = true;
	sysout("Validacion Estructura: "
			+ validacionToString(validaciones[validaciones.length - 1]))
}

function validacionSoloNumero(campo, ejemplo) {
	validaciones[validaciones.length] = new Array();
	validaciones[validaciones.length - 1].campo = campo;
	validaciones[validaciones.length - 1].validaNoNulo = false;
	validaciones[validaciones.length - 1].validaLargo = false;
	validaciones[validaciones.length - 1].validaEstructura = false;
	validaciones[validaciones.length - 1].largoMaximo = 0;
	validaciones[validaciones.length - 1].largoMinimo = 0;
	validaciones[validaciones.length - 1].estructura = "";
	validaciones[validaciones.length - 1].validaSoloNumeros = true;
	validaciones[validaciones.length - 1].ejemplo = ejemplo;
	validaciones[validaciones.length - 1].obligatorio = true;
	sysout("Validacion Solo Numero: "
			+ validacionToString(validaciones[validaciones.length - 1]))
}

function validaCampo(campo, valor, textoDefault) {
	
	//sysout("valor: '" + valor + "', largo:"+valor.length +" : " + textoDefault + "tipo ! checkbox ? "+(type != "checkbox"));
	//sysout("valor F1->"+ document.getElementsByName("formato_nomina_sel")[0].value)
	if(valor==null){
		return false;
	}
	var type = $("#" + campo).prop("type");

	
	if (valor == textoDefault && type != "checkbox") {
		obtenerItemValidacion(campo).estado_validacion = false;
		return false;
	}

	var itemValidacion = obtenerItemValidacion(campo);
	if(type == "checkbox" && valor ==""){
		agregarError(campo,
				"Debe Seleccionar al menos una opci&oacute;n");
		obtenerItemValidacion(campo).estado_validacion = false;
		sysout("saliendo por validacion inicial...valor F1->"+ document.getElementsByName("formato_nomina_sel")[0].value);
		return false;
	}
	
	
	sysout("Validando campo: " + validacionToString(itemValidacion))
	if (itemValidacion != null && itemValidacion.campo != null) {
		if (itemValidacion.validaNoNulo) {

			if (type == "checkbox") {
				var itemsSeleccionados = getValueCheckbox(campo).split("::");
				sysout("tag itemsSeleccionados.length : "
						+ itemsSeleccionados.length);
				
				
				if (itemsSeleccionados.length == 1) {
					agregarError(campo,
							"Debe Seleccionar al menos una opci&oacute;n");
					obtenerItemValidacion(campo).estado_validacion = false;
					return false;
				}

			} else if (valor != null && valor.length == 0) {

				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " no puede ser vac&iacute;o");
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaLargo) {
			if (valor != null && valor.length < itemValidacion.largoMinimo
					|| valor.length > itemValidacion.largoMaximo) {
				agregarError(
						campo,
						"El valor del campo "
								+ obtenerItemInput(campo).nombre
								+ " no cumple con el largo establecido, largo m&iacute;nimo: "
								+ itemValidacion.largoMinimo
								+ ", largo M&aacute;ximo: "
								+ itemValidacion.largoMaximo);
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaLargoFijo) {
			if (valor != null && valor.length < itemValidacion.largo
					|| valor.length > itemValidacion.largo) {
				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " no cumple con el largo establecido, largo : "
						+ itemValidacion.largo + " caracteres ");
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaSoloNumeros) {
			if (valor != null && isNaN(valor)) {
				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " debe ser un n&uacute;mero");
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaDecimal) {
			var partes = valor.split(".");

			if (valor != null && (isNaN(partes[0]) || isNaN(partes[0]))) {
				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " no cumple con el formato establecido Ej: 3.5");
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaSoloTexto) {
			var partes = valor.split(".");

			if (valor != null && (isNaN(partes[0]) || isNaN(partes[0]))) {
				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " no cumple con el formato establecido Ej: 3.5");
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}
		if (itemValidacion.validaEstructura) {
			if (valor != null && valor.match(itemValidacion.estructura) == null) {
				agregarError(campo, "El valor del campo "
						+ obtenerItemInput(campo).nombre
						+ " no cumple con la estructura definida Ej: "
						+ itemValidacion.ejemplo);
				obtenerItemValidacion(campo).estado_validacion = false;
				return false;
			}
		}

	} else
		return "nulo"
	obtenerItemValidacion(campo).estado_validacion = true;
	return true;
}
function soloLetras(e, obj) {

	key = e.keyCode || e.which;
	tecla = String.fromCharCode(key).toLowerCase();
	letras = " áéíóúabcdefghijklmnñopqrstuvwxyz-";
	especiales = [ 8, 46, 209, 241, 39 ];

	tecla_especial = false
	for ( var i in especiales) {
		if (key == especiales[i]) {
			tecla_especial = true;
			break;
		}
	}

	if (letras.indexOf(tecla) == -1 && !tecla_especial) {
		return false;
	}
	$("#" + obj.id).blur(function() {
		$("#" + obj.id).val($("#" + obj.id).val().toUpperCase());
	});
}

function soloLetrasYNumeros(e, obj) {

	key = e.keyCode || e.which;
	tecla = String.fromCharCode(key).toLowerCase();
	letras = " áéíóúabcdefghijklmnñopqrstuvwxyz-";
	especiales = [ 8, 46, 209, 241, 39, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57 ];

	tecla_especial = false
	for ( var i in especiales) {
		if (key == especiales[i]) {
			tecla_especial = true;
			break;
		}
	}

	if (letras.indexOf(tecla) == -1 && !tecla_especial) {
		return false;
	}
	$("#" + obj.id).blur(function() {
		$("#" + obj.id).val($("#" + obj.id).val().toUpperCase());
	});
}
function soloNumeros(e, obj) {

	key = e.keyCode || e.which;
	tecla = String.fromCharCode(key).toLowerCase();
	letras = "1234567890";
	especiales = [ 8, 46, 209, 241, 39 ];

	tecla_especial = false
	for ( var i in especiales) {
		if (key == especiales[i]) {
			tecla_especial = true;
			break;
		}
	}

	if (letras.indexOf(tecla) == -1 && !tecla_especial) {
		return false;
	}
	$("#" + obj.id).blur(function() {
		$("#" + obj.id).val($("#" + obj.id).val().toUpperCase());
	});
}

function obtenerItemValidacion(campo) {

	for ( var i = 0; i < validaciones.length; i++) {
		if (validaciones[i].campo == campo)
			return validaciones[i];
	}
	return null;
}
function agregarError(campo, error) {
	for ( var i = 0; i < validaciones.length; i++) {
		if (validaciones[i].campo == campo) {
			validaciones[i].error = error;
			return;
		}
	}
	return null;
}
function agregarCampoNoObligatorio(campo) {

	for ( var i = 0; i < validaciones.length; i++) {
		if (validaciones[i].campo == campo) {
			validaciones[i].obligatorio = false;
			return;
		}
	}
}

function validacionToString(item) {
	try {
		return "[campo:" + item.campo + "]," + "[validaNoNulo:"
				+ item.validaNoNulo + "]," + "[validaLargo:" + item.validaLargo
				+ "]," + "[validaEstructura:" + item.validaEstructura + "],"
				+ "[largoMaximo:" + item.largoMaximo + "]," + "[largoMinimo:"
				+ item.largoMinimo + "]," + "[estructura:" + item.estructura
				+ "]," + "[ejemplo:" + item.ejemplo + "]";
	} catch (e) {
		return "Nulo";
	}
}

function mostrarValidaciones() {
	for ( var i = 0; i < validaciones.length; i++) {
		sysout("item [" + i + "] : " + validacionToString(validaciones[i]))
	}
}

function mostrarInformacionValidacion(id) {

	if (obtenerItemValidacion(id) != null
			&& obtenerItemValidacion(id).error != null) {
		var width = $("#" + id).css("width");
		if (width != null && width != "") {
			width = parseInt(width.split("px")[0]);
		} else {
			width = 180;
		}
		var top = $("#" + id).offset().top + 5;
		var left = $("#" + id).offset().left + width;
		var type = $("#" + id).prop("type");
		if (type == "checkbox") {
			top = top - 50;
			left = left + 150;
		}
		var div = "<div id='info_validacion_" + id
				+ "' class='info_validacion'></div> ";
		$("body").append(div);
		$("#info_validacion_" + id).css("left", (left - 150) + "px");
		$("#info_validacion_" + id).css("top", (top + 15) + "px");
		$("#info_validacion_" + id).html(obtenerItemValidacion(id).error);
		$("#info_validacion_" + id).fadeIn(500);
		$("#info_validacion_" + id).fadeOut(8000);
		setTimeout(function() {
			$("#info_validacion_" + id).remove();
			if (!obtenerItemValidacion(id).obligatorio)
				$("#" + id).attr("class", "");
		}, 10000);
	}
}

function addValidacionEstrutura(campo, estructura, ejemplo) {

	obtenerItemValidacion(campo).validaEstructura = true;
	obtenerItemValidacion(campo).estructura = estructura;
	obtenerItemValidacion(campo).ejemplo = ejemplo;
}

function addValidacionSoloTexto(campo, ejemplo) {
	obtenerItemValidacion(campo).validaSoloTexto = true;
	obtenerItemValidacion(campo).ejemplo = ejemplo;
}

// Admin
validacionEstructura("rut_admin", /^0*(\d{1,3}(\.?\d{3})*)\-([\dkK])$/,
		"12345678-9");
validacionLargo("nombre_admin", 2, 30, "");

validacionLargo("apellido_paterno_admin", 2, 20, "");
validacionLargo("apellido_materno_admin", 2, 20, "");
validacionLargo("telefono_fijo_admin", 6, 8, "");
obtenerItemValidacion("telefono_fijo_admin").validaSoloNumeros = true;
validacionSoloNumero("codigo_telefono_fijo_admin", "");
validacionSoloNumero("codigo_fax_admin", "");
validacionLargo("fax_admin", 6, 8, "");
obtenerItemValidacion("fax_admin").validaSoloNumeros = true;
validacionLargoFijo("celular_admin", 8);
obtenerItemValidacion("celular_admin").validaSoloNumeros = true;
validacionEstructura("email_admin",
		/^[a-zA-Z.0-9_\-]+@[a-zA-Z._\-]+?\.[a-zA-Z]{2,3}$/,
		"jperez@laaraucana.cl")
validacionLargo("direccion_admin", 5, 80);
validacionLargo("direccion_numero_admin", 1, 6);
validacionLargo("departamento_numero_admin", 1, 6);
validacionNoNulo("region_admin", "");
validacionNoNulo("ciudad_admin", "");
validacionNoNulo("comuna_admin", "");

// Empresa

validacionEstructura("rut_empresa", /^0*(\d{1,3}(\.?\d{3})*)\-([\dkK])$/,
		"12345678-9");
validacionEstructura("email_empresa",
		/^[a-zA-Z.0-9_\-]+@[a-zA-Z._\-]+?\.[a-zA-Z]{2,3}$/,
		"jperez@laaraucana.cl")
validacionLargo("razon_social_empresa", 3, 100, "");
validacionNoNulo("tipo_empresa", "");
validacionNoNulo("numero_trabajadores", "");
validacionLargo("nombre_holding_empresa", 3, 30, "");
validacionEstructura("rut_representante_legal_empresa",
		/^0*(\d{1,3}(\.?\d{3})*)\-([\dkK])$/, "12345678-9");
validacionLargo("nombre_representante_legal_empresa", 3, 30);
validacionLargo("apellido_paterno_representante_legal_empresa", 2, 20);
validacionLargo("apellido_materno_representante_legal_empresa", 2, 20);
validacionLargo("vigencia_representante_legal_empresa", 1, 20, "");
// validacionLargoFijo("vigencia_representante_legal_empresa",10);
validacionNoNulo("estado_representante_legal_empresa", "");
validacionSoloNumero("codigo_acividad_economica_empresa", "");
validacionNoNulo("nombre_acividad_economica_empresa", "");

// Casa Matriz
validacionLargo("codigo_casa_matriz", 2, 6);
validacionLargo("nombre_casa_matriz", 2, 30);
validacionLargo("direccion_casa_matriz", 2, 80);
validacionLargo("direccion_numero_casa_matriz", 1, 6);
validacionLargo("depto_numero_casa_matriz", 1, 6);
validacionNoNulo("region_casa_matriz");
validacionNoNulo("ciudad_casa_matriz");
validacionNoNulo("comuna_casa_matriz");

validacionLargo("codigo_telefono_casa_matriz", 1, 2, "");
validacionSoloNumero("codigo_telefono_casa_matriz");
validacionSoloNumero("telefono_fijo_casa_matriz");

validacionLargoFijo("celular_casa_matriz", 8);
obtenerItemValidacion("celular_casa_matriz").validaSoloNumeros = true;

validacionSoloNumero("codigo_fax_casa_matriz");
validacionSoloNumero("fax_casa_matriz");
validacionLargo("fax_casa_matriz", 6, 8, "");

obtenerItemValidacion("fax_casa_matriz").validaSoloNumeros = true;

validacionEstructura("email_casa_matriz",
		/^[a-zA-Z.0-9_\-]+@[a-zA-Z._\-]+?\.[a-zA-Z]{2,3}$/,
		"jperez@laaraucana.cl")

// Previsionales
validacionNoNulo("nombre_mutual");
validacionLargo("numero_adherentes_mutual", 0, 6, "");
obtenerItemValidacion("numero_adherentes_mutual").validaSoloNumeros = true;
validacionNoNulo("calculo_individual_mutual");
validacionLargo("tasa_adicional_mutual", 0, 5, "");
obtenerItemValidacion("tasa_adicional_mutual").validaDecimal = true;
validacionNoNulo("caja_compensacion");

// formato nomina
validacionNoNulo("generar_planilla_inp_sucursal");
validacionNoNulo("calcular_monto_total_ccaf");
validacionNoNulo("generar_planilla_mutual_sucursal");
validacionNoNulo("calcular_monto_total_salud");
validacionNoNulo("generar_planilla_ccaf_sucursal");
validacionNoNulo("calcular_monto_total_prevision");
validacionNoNulo("calcular_monto_total_fonasa");
validacionNoNulo("imprimir_planillas");
validacionNoNulo("calcular_monto_total_mutual");
validacionNoNulo("calcular_movimiento_personal");
validacionNoNulo("formato_nomina_sel");

agregarCampoNoObligatorio("nombre_holding_empresa");

agregarCampoNoObligatorio("celular_casa_matriz");
agregarCampoNoObligatorio("codigo_fax_casa_matriz");
agregarCampoNoObligatorio("fax_casa_matriz");
//agregarCampoNoObligatorio("email_casa_matriz");
agregarCampoNoObligatorio("depto_numero_casa_matriz", "");

// agregarCampoNoObligatorio("nombre_mutual");
agregarCampoNoObligatorio("numero_adherentes_mutual");
agregarCampoNoObligatorio("calculo_individual_mutual");
agregarCampoNoObligatorio("tasa_adicional_mutual");

agregarCampoNoObligatorio("celular_admin", "");
agregarCampoNoObligatorio("departamento_numero_admin", "");
agregarCampoNoObligatorio("codigo_fax_admin", "");
agregarCampoNoObligatorio("fax_admin", "");

var relacionados = new Array();
relacionados[relacionados.length] = new Array("codigo_fax_casa_matriz",
		"fax_casa_matriz");
relacionados[relacionados.length] = new Array("codigo_fax_admin", "fax_admin");

function obtenerRelacionado(campo) {
	for ( var i = 0; i < relacionados.length; i++) {
		if (relacionados[i][0] == campo)
			return relacionados[i][1];
		if (relacionados[i][1] == campo)
			return relacionados[i][0];
	}

	return null;
}