var inputs = new Array();

function input(formulario, id, nombre, textoDefault, class_hint, class_default,
		data, mostrarItemSeleccione) {
	inputs[inputs.length] = new Array();
	inputs[inputs.length - 1].formulario = formulario;
	inputs[inputs.length - 1].id = id;
	inputs[inputs.length - 1].nombre = nombre;
	// inputs[inputs.length -1].textoDefault = textoDefault;
	$("#" + id).attr("placeHolder", textoDefault);
	inputs[inputs.length - 1].textoDefault = "";
	inputs[inputs.length - 1].class_hint = class_hint;
	$("#" + id).attr("class", class_default);
	inputs[inputs.length - 1].class_default = class_default;
	inputs[inputs.length - 1].data = data;
	inputs[inputs.length - 1].mostrarItemSeleccione = mostrarItemSeleccione;
}
function inputWithEvents(formulario, id, nombre, textoDefault, class_hint,
		class_default, _function_onBlur, _function_onKeyPress) {
	inputs[inputs.length] = new Array();
	inputs[inputs.length - 1].formulario = formulario;
	inputs[inputs.length - 1].id = id;
	inputs[inputs.length - 1].nombre = nombre;
	// inputs[inputs.length -1].textoDefault = textoDefault;
	inputs[inputs.length - 1].textoDefault = "";
	$("#" + id).attr("placeHolder", textoDefault);
	$("#" + id).attr("class", class_default);
	inputs[inputs.length - 1].class_hint = class_hint;
	inputs[inputs.length - 1].class_default = class_default;
	inputs[inputs.length - 1]._function_onBlur = _function_onBlur;
	inputs[inputs.length - 1]._function_onKeyPress = _function_onKeyPress;
}
function inputWithDataAndEvents(formulario, id, nombre, textoDefault,
		class_hint, class_default, data, _function_onBlur, _function_onKeyPress) {
	inputs[inputs.length] = new Array();
	inputs[inputs.length - 1].formulario = formulario;
	inputs[inputs.length - 1].id = id;
	inputs[inputs.length - 1].nombre = nombre;
	// inputs[inputs.length -1].textoDefault = textoDefault;
	$("#" + id).attr("placeHolder", textoDefault);
	$("#" + id).attr("class", class_default);
	inputs[inputs.length - 1].textoDefault = "";
	inputs[inputs.length - 1].class_hint = class_hint;
	inputs[inputs.length - 1].class_default = class_default;
	inputs[inputs.length - 1]._function_onBlur = _function_onBlur;
	inputs[inputs.length - 1]._function_onKeyPress = _function_onKeyPress;
	inputs[inputs.length - 1].data = data;
}

function obtenerItemInput(id) {
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].id == id)
			return inputs[i];
	}
	return null;
}

function cargarInputs(formulario) {
	
	estadoValidacion[formulario] = true;
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].formulario == formulario) {
			var validacion = cargarDataInput(inputs[i].id,
					inputs[i].textoDefault, inputs[i].class_hint,
					inputs[i].class_default, dataFormulario[inputs[i].id
							.toUpperCase()], inputs[i].data,
					inputs[i]._function_onBlur, inputs[i]._function_onKeyPress);
			var obj_validacion = obtenerItemValidacion(inputs[i].id);
			if (obj_validacion != null && obj_validacion.obligatorio) {
				estadoValidacion[formulario] = estadoValidacion[formulario]
						&& validacion;
			}
			if (validacion)
				dataFormulario[inputs[i].id.toUpperCase()].value = inputs[i].value;
		}
	}

}

function validarInputs(formulario) {
	var resultado = true;
	for ( var i = 0; i < inputs.length; i++) {
		if (inputs[i].formulario == formulario) {
			var validacion = validarDataInput(inputs[i].id,
					inputs[i].textoDefault, inputs[i].class_hint,
					inputs[i].class_default, $("#" + inputs[i].id).val(),
					inputs[i].data);
			if (!validacion)
				resultado = false;
			var valor = $("#" + inputs[i].id).val();
			var type = $("#" + inputs[i].id).prop("type");
			if (type == "checkbox") {
				valor = getValueCheckbox(inputs[i].id);
				sysout("tag2 valor: " + valor)
			}
			if (valor == inputs[i].textoDefault)
				valor = "";
			dataFormulario[inputs[i].id.toUpperCase()] = valor;
		}
	}
	guardarDataTemporal(formulario);
	return resultado;
}

function cargarAllInputs() {
	inputs = new Array();

	// Admin
	/*input("admin", "rut_admin", "Rut ", "Ej: 12312312-3", "hint", "");
	input("admin", "nombre_admin", "Nombre ", "Ej: Juan", "hint", "");
	input("admin", "apellido_paterno_admin", "Apellido Paterno", "Ej: Soto",
			"hint", "");
	input("admin", "apellido_materno_admin", "Apellido Materno", "Ej: Soto",
			"hint", "");
	input("admin", "telefono_fijo_admin", "Tel&eacute;fono Fijo", "22334455",
			"hint", "");
	input("admin", "codigo_telefono_fijo_admin",
			"C&oacute;digo Telef&oacute;nico", "Ej: 2", "hint", "");
	input("admin", "codigo_fax_admin", "C&oacute;digo Fax", "Ej: 2", "hint",
			"", dataFormulario["codigo_fax_admin".toUpperCase()]);
	input("admin", "fax_admin", "C&oacute;digo Telef&oacute;nico",
			"Ej: 22334455", "hint", "", dataFormulario["fax_admin"
					.toUpperCase()]);
	input("admin", "celular_admin", "N&uacute;mero Celular", "Ej: 81828384",
			"hint", "");
	input("admin", "email_admin", "Correo Electr&oacute;nico",
			"Ej: j.perez@empresa.cl", "hint", "");
	input("admin", "direccion_admin", "Direcci&oacute;n", "Ej: Av. Matta",
			"hint", "");
	input("admin", "direccion_numero_admin", "N&uacute;mero direcci&oacute;n",
			"Ej: 125", "hint", "");
	input("admin", "departamento_numero_admin", "N&uacute;mero departamento",
			"Ej: 101", "hint", "");
	input("admin", "region_admin", "Regi&oacute;n", "", "hint", "", regiones);
	input("admin", "ciudad_admin", "Ciudad", "", "hint", "",
			obtenerCiudadesPorRegion(dataFormulario["region_admin"
					.toUpperCase()]));
	input(
			"admin",
			"comuna_admin",
			"Comuna",
			"",
			"hint",
			"",
			obtenerComunasPorCiudad(dataFormulario["ciudad_admin".toUpperCase()]));
*/
	// Empresa
	inputWithEvents("empresa", "rut_empresa", "Rut Empresa",
			"Ingrese Rut Empresa", "hint", "", formatear_rut,
			valida_texto_numerico);
	input("empresa", "email_empresa", "Correo Electr&oacute;nico",
			"Ingrese Email", "hint", "");
	input("empresa", "razon_social_empresa", "Raz&oacute;n social",
			"Ej: Mi Empresa", "hint", "");
	input("empresa", "tipo_empresa", "Tipo Empresa", "", "hint", "",
			tiposEmpresa);
	input("empresa", "numero_trabajadores", "Cantidad de Trabajadores", "",
			"hint", "", numeroTrabajadores);
	input("empresa", "nombre_holding_empresa", "Nombre holding",
			"Ej: Empresas X", "hint", "");
	input("empresa", "rut_representante_legal_empresa",
			"Rut Representante Legal", "Ej: 12312312-3", "hint", "");
	input("empresa", "nombre_representante_legal_empresa",
			"Nombre Representante Legal", "Ej: Juan", "hint", "");
	input("empresa", "apellido_paterno_representante_legal_empresa",
			"Apellido Paterno Representante Legal", "Ej: Soto", "hint", "");
	input("empresa", "apellido_materno_representante_legal_empresa",
			"Apellido Materno Representante Legal", "Ej: Soto", "hint", "");
	input("empresa", "vigencia_representante_legal_empresa",
			"Vigencia Representante Legal", "Ej: 31/12/2020", "hint", "");

	inputWithEvents("empresa", "codigo_acividad_economica_empresa",
			"C&oacute;digo Actividad Econ&oacute;mica", "Ej: 12345", "hint",
			"", function() {

				// seleccionarValorSelect("nombre_acividad_economica_empresa",$("#codigo_acividad_economica_empresa").val());
				$("#nombre_acividad_economica_empresa").val(
						$("#codigo_acividad_economica_empresa").val());
				procesarResultadoValidacionCampo(
						"nombre_acividad_economica_empresa", "");
			}, function() {
				return true;
			});

	inputWithDataAndEvents(
			"empresa",
			"nombre_acividad_economica_empresa",
			"Actividad Econ&oacute;mica",
			"",
			"hint",
			"",
			actividadesEconomicas,
			function() {
				// seleccionarValorSelect("codigo_acividad_economica_empresa",$("#nombre_acividad_economica_empresa").val());

				$("#codigo_acividad_economica_empresa").val(
						$("#nombre_acividad_economica_empresa").val());
				procesarResultadoValidacionCampoObligatorio("nombre_acividad_economica_empresa");
				validaCampo("codigo_acividad_economica_empresa", $(
						"#codigo_acividad_economica_empresa").val());
				procesarResultadoValidacionCampoObligatorio("codigo_acividad_economica_empresa");

			}, function() {
				return true;
			});

	// Casa Matriz
	input("casa_matriz", "codigo_casa_matriz", "C&oacute", "Ej: CAS_MAT",
			"hint", "");
	input("casa_matriz", "nombre_casa_matriz", "Nombre", "Ej: Casa Matriz",
			"hint", "");
	input("casa_matriz", "direccion_casa_matriz", "Direcci&oacute;n",
			"Ej: Av. Matta", "hint", "");
	input("casa_matriz", "direccion_numero_casa_matriz",
			"N&uacute;mero Direcci&oacute;n", "Ej: 125", "hint", "");
	input("casa_matriz", "depto_numero_casa_matriz",
			"N&uacute;mero Departamento", "Ej: 101", "hint", "");
	input("casa_matriz", "region_casa_matriz", "Regi&oacute;n", "", "hint", "",
			regiones);
	input("casa_matriz", "ciudad_casa_matriz", "Ciudad", "", "hint", "",
			obtenerCiudadesPorRegion(dataFormulario["region_casa_matriz"
					.toUpperCase()]));
	input("casa_matriz", "comuna_casa_matriz", "Comuna", "", "hint", "",
			obtenerComunasPorCiudad(dataFormulario["ciudad_casa_matriz"
					.toUpperCase()]));
	input("casa_matriz", "codigo_telefono_casa_matriz",
			"C&oacute;digo Telef&oacute;nico", "Ej: 2", "hint", "");
	input("casa_matriz", "telefono_fijo_casa_matriz",
			"N&uacute;mero Telef&oacute;nico", "Ej: 22334455", "hint", "");
	input("casa_matriz", "celular_casa_matriz", "Celular", "Ej: 81828384",
			"hint", "");
	input("casa_matriz", "codigo_fax_casa_matriz", "C&oacute;digo Fax",
			"Ej: 2", "hint", "");
	input("casa_matriz", "fax_casa_matriz", "Fax", "Ej: 22334455", "hint", "");
	input("casa_matriz", "email_casa_matriz", "Correo Electr&oacute;nico",
			"Ej: j.perez@empresa.cl", "hint", "");

	// Entidades Previsionales
	/*input("previsionales", "nombre_mutual", "Mutual", "", "hint", "", mutuales);
	input("previsionales", "numero_adherentes_mutual",
			"N&uacute;mero Adherentes", "Ej: 10", "hint", "");
	input("previsionales", "calculo_individual_mutual",
			"C&aacute;culo Individual", "", "hint", "", estadosSiNo, false);
	input("previsionales", "tasa_adicional_mutual", "Tasa Adici&oacute;nal",
			"Ej: 2.5", "hint", "");

	input("previsionales", "caja_compensacion", "Caja de Compensaci&oacute;n",
			"", "hint", "", cajas);

	// Formato
	input("formato_nomina", "generar_planilla_inp_sucursal", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_monto_total_ccaf", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "generar_planilla_mutual_sucursal", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_monto_total_salud", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "generar_planilla_ccaf_sucursal", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_monto_total_prevision", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_monto_total_fonasa", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "imprimir_planillas", "", "", "hint_pequeno",
			"select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_monto_total_mutual", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "calcular_movimiento_personal", "", "",
			"hint_pequeno", "select_pequeno", estadosSiNo, false);
	input("formato_nomina", "formato_nomina_sel", "", "", "hint_pequeno",
			"select_pequeno");
*/
}