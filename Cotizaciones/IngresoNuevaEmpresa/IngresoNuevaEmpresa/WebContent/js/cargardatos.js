var dataFormulario = new Array();
var carga_inicial = true;
function cargarDataFromServer() {

	var url = "ActionServlet?action=obtener_data_tmp_empresa&rut_empresa="
			+ $("#_rut_empresa").val() + "&email_empresa="
			+ $("#_email_empresa").val() + "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {

						if (data != null) {
							dataFormulario = data.data;
							if($("#seleccionTipo").val()=="I"){
							dataFormulario["rut_representante_legal_empresa"
									.toUpperCase()] = $("#_rut_empresa").val();
							}else{
								dataFormulario["rut_representante_legal_empresa"
									.toUpperCase()] = data.data["rut_rep_leg_emp"
									.toUpperCase()];
							}
							dataFormulario["nombre_representante_legal_empresa"
									.toUpperCase()] = data.data["nombre_rep_leg_emp"
									.toUpperCase()];

							dataFormulario["apellido_paterno_representante_legal_empresa"
									.toUpperCase()] = data.data["ape_pat_rep_leg_emp"
									.toUpperCase()];
							dataFormulario["apellido_materno_representante_legal_empresa"
									.toUpperCase()] = data.data["ape_mat_rep_leg_emp"
									.toUpperCase()];
							dataFormulario["codigo_acividad_economica_empresa"
									.toUpperCase()] = data.data["cod_act_eco_emp"
									.toUpperCase()];
							dataFormulario["nombre_acividad_economica_empresa"
									.toUpperCase()] = data.data["nom_act_eco_emp"
									.toUpperCase()];
							dataFormulario["vigencia_representante_legal_empresa"
									.toUpperCase()] = data.data["vig_rep_leg_emp"
									.toUpperCase()];
							dataFormulario["generar_planilla_inp_sucursal"
									.toUpperCase()] = data.data["gen_pla_inp_sucursal"
									.toUpperCase()];
							dataFormulario["calcular_monto_total_ccaf"
									.toUpperCase()] = data.data["cal_monto_total_ccaf"
									.toUpperCase()];
							dataFormulario["generar_planilla_mutual_sucursal"
									.toUpperCase()] = data.data["gen_pla_mutual_sucursal"
									.toUpperCase()];
							dataFormulario["calcular_monto_total_salud"
									.toUpperCase()] = data.data["cal_monto_total_salud"
									.toUpperCase()];
							dataFormulario["generar_planilla_ccaf_sucursal"
									.toUpperCase()] = data.data["gen_pla_ccaf_sucursal"
									.toUpperCase()];
							dataFormulario["calcular_monto_total_prevision"
									.toUpperCase()] = data.data["cal_monto_total_prevision"
									.toUpperCase()];
							dataFormulario["calcular_monto_total_fonasa"
									.toUpperCase()] = data.data["cal_monto_total_fonasa"
									.toUpperCase()];
							dataFormulario["calcular_monto_total_mutual"
									.toUpperCase()] = data.data["cal_monto_total_mutual"
									.toUpperCase()];
							dataFormulario["calcular_movimiento_personal"
									.toUpperCase()] = data.data["cal_movimiento_personal"
									.toUpperCase()];
							if (carga_inicial) {
								cargarValores("empresa");
							}

						} else {
							dataFormulario = new Array();
						}
					});
}

function guardarDataTemporal(formulario) {

	var data = "&formulario=" + formulario + "&rut_empresa="
			+ $("#_rut_empresa").val() + "&email_empresa="
			+ $("#_email_empresa").val() + "&data=";
	var parametros = "";
	if (formulario == "admin") {

		parametros += "rut_admin:=" + dataFormulario["rut_admin".toUpperCase()]
				+ ";;" + "nombre_admin:="
				+ dataFormulario["nombre_admin".toUpperCase()] + ";;"
				+ "apellido_paterno_admin:="
				+ dataFormulario["apellido_paterno_admin".toUpperCase()] + ";;"
				+ "apellido_materno_admin:="
				+ dataFormulario["apellido_materno_admin".toUpperCase()] + ";;"
				+ "codigo_telefono_fijo_admin:="
				+ dataFormulario["codigo_telefono_fijo_admin".toUpperCase()]
				+ ";;" + "telefono_fijo_admin:="
				+ dataFormulario["telefono_fijo_admin".toUpperCase()] + ";;"
				+ "codigo_fax_admin:="
				+ dataFormulario["codigo_fax_admin".toUpperCase()] + ";;"
				+ "fax_admin:=" + dataFormulario["fax_admin".toUpperCase()]
				+ ";;" + "celular_admin:="
				+ dataFormulario["celular_admin".toUpperCase()] + ";;"
				+ "email_admin:=" + dataFormulario["email_admin".toUpperCase()]
				+ ";;" + "direccion_admin:="
				+ dataFormulario["direccion_admin".toUpperCase()] + ";;"
				+ "direccion_numero_admin:="
				+ dataFormulario["direccion_numero_admin".toUpperCase()] + ";;"
				+ "departamento_numero_admin:="
				+ dataFormulario["departamento_numero_admin".toUpperCase()]
				+ ";;" + "region_admin:="
				+ dataFormulario["region_admin".toUpperCase()] + ";;"
				+ "ciudad_admin:="
				+ dataFormulario["ciudad_admin".toUpperCase()] + ";;"
				+ "comuna_admin:="
				+ dataFormulario["comuna_admin".toUpperCase()] + ";;";

	} else if (formulario == "empresa") {
		parametros += "razon_social_empresa:="
				+ dataFormulario["razon_social_empresa".toUpperCase()]
				+ ";;"
				+ "tipo_empresa:="
				+ dataFormulario["tipo_empresa".toUpperCase()]
				+ ";;"
				+ "numero_trabajadores:="
				+ dataFormulario["numero_trabajadores".toUpperCase()]
				+ ";;"
				+ "nombre_holding_empresa:="
				+ dataFormulario["nombre_holding_empresa".toUpperCase()]
				+ ";;"
				+ "rut_representante_legal_empresa:="
				+ dataFormulario["rut_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "nombre_representante_legal_empresa:="
				+ dataFormulario["nombre_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "apellido_paterno_representante_legal_empresa:="
				+ dataFormulario["apellido_paterno_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "apellido_materno_representante_legal_empresa:="
				+ dataFormulario["apellido_materno_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "estado_representante_legal_empresa:="
				+ dataFormulario["estado_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "vigencia_representante_legal_empresa:="
				+ dataFormulario["vigencia_representante_legal_empresa"
						.toUpperCase()]
				+ ";;"
				+ "codigo_acividad_economica_empresa:="
				+ dataFormulario["codigo_acividad_economica_empresa"
						.toUpperCase()]
				+ ";;"
				+ "nombre_acividad_economica_empresa:="
				+ dataFormulario["nombre_acividad_economica_empresa"
						.toUpperCase()] + ";;";

	} else if (formulario == "casa_matriz") {

		parametros += "codigo_casa_matriz:="
				+ dataFormulario["codigo_casa_matriz".toUpperCase()] + ";;"
				+ "nombre_casa_matriz:="
				+ dataFormulario["nombre_casa_matriz".toUpperCase()] + ";;"
				+ "direccion_casa_matriz:="
				+ dataFormulario["direccion_casa_matriz".toUpperCase()] + ";;"
				+ "direccion_numero_casa_matriz:="
				+ dataFormulario["direccion_numero_casa_matriz".toUpperCase()]
				+ ";;" + "depto_numero_casa_matriz:="
				+ dataFormulario["depto_numero_casa_matriz".toUpperCase()]
				+ ";;" + "region_casa_matriz:="
				+ dataFormulario["region_casa_matriz".toUpperCase()] + ";;"
				+ "ciudad_casa_matriz:="
				+ dataFormulario["ciudad_casa_matriz".toUpperCase()] + ";;"
				+ "comuna_casa_matriz:="
				+ dataFormulario["comuna_casa_matriz".toUpperCase()] + ";;"
				+ "codigo_telefono_casa_matriz:="
				+ dataFormulario["codigo_telefono_casa_matriz".toUpperCase()]
				+ ";;" + "telefono_fijo_casa_matriz:="
				+ dataFormulario["telefono_fijo_casa_matriz".toUpperCase()]
				+ ";;" + "celular_casa_matriz:="
				+ dataFormulario["celular_casa_matriz".toUpperCase()] + ";;"
				+ "codigo_fax_casa_matriz:="
				+ dataFormulario["codigo_fax_casa_matriz".toUpperCase()] + ";;"
				+ "fax_casa_matriz:="
				+ dataFormulario["fax_casa_matriz".toUpperCase()] + ";;"
				+ "email_casa_matriz:="
				+ dataFormulario["email_casa_matriz".toUpperCase()] + ";;";

	} else if (formulario == "previsionales") {
		parametros += "nombre_mutual:="
				+ dataFormulario["nombre_mutual".toUpperCase()] + ";;"
				+ "numero_adherentes_mutual:="
				+ dataFormulario["numero_adherentes_mutual".toUpperCase()]
				+ ";;" + "calculo_individual_mutual:="
				+ dataFormulario["calculo_individual_mutual".toUpperCase()]
				+ ";;" + "tasa_adicional_mutual:="
				+ dataFormulario["tasa_adicional_mutual".toUpperCase()] + ";;"
				+ "caja_compensacion:="
				+ dataFormulario["caja_compensacion".toUpperCase()] + ";;";
	}

	else if (formulario == "formato_nomina") {

		parametros += "generar_planilla_inp_sucursal:="
				+ dataFormulario["generar_planilla_inp_sucursal".toUpperCase()]
				+ ";;"
				+ "calcular_monto_total_ccaf:="
				+ dataFormulario["calcular_monto_total_ccaf".toUpperCase()]
				+ ";;"
				+ "generar_planilla_mutual_sucursal:="
				+ dataFormulario["generar_planilla_mutual_sucursal"
						.toUpperCase()]
				+ ";;"
				+ "calcular_monto_total_salud:="
				+ dataFormulario["calcular_monto_total_salud".toUpperCase()]
				+ ";;"
				+ "generar_planilla_ccaf_sucursal:="
				+ dataFormulario["generar_planilla_ccaf_sucursal".toUpperCase()]
				+ ";;"
				+ "calcular_monto_total_prevision:="
				+ dataFormulario["calcular_monto_total_prevision".toUpperCase()]
				+ ";;" + "calcular_monto_total_fonasa:="
				+ dataFormulario["calcular_monto_total_fonasa".toUpperCase()]
				+ ";;" + "imprimir_planillas:="
				+ dataFormulario["imprimir_planillas".toUpperCase()] + ";;"
				+ "calcular_monto_total_mutual:="
				+ dataFormulario["calcular_monto_total_mutual".toUpperCase()]
				+ ";;" + "calcular_movimiento_personal:="
				+ dataFormulario["calcular_movimiento_personal".toUpperCase()]
				+ ";;" + "formato_nomina_sel:="
				+ dataFormulario["formato_nomina_sel".toUpperCase()] + ";;";

	}
	parametros = replaceAll(parametros, "&", "amp;");
	parametros = replaceEne(parametros);
	var url = "ActionServlet?action=actualizar_data" + data + parametros
			+ "&rand=" + Math.random();
	$.getJSON(url, function(data) {
		if (data.data) {
			cargarDataFromServer();

		}

	});

}

function replaceEne(parametros) {
	var data = "";
	for ( var i = 0; i < parametros.length; i++) {

		if (parametros.charAt(i).charCodeAt(0) == 241) {
			data += "ene;";
		} else if (parametros.charAt(i).charCodeAt(0) == 209) {
			data += "eNe;";
		} else {
			data += parametros.charAt(i);
		}
	}
	return data;
}
function obtenerRecursos() {
	var url = "ActionServlet?action=obtener_recursos_pantalla&rand="
			+ Math.random();
	// mostrarCargando(texto_guardando_informacion);

	$.getJSON(url, function(data) {
		actividadesEconomicas = new Array();
		for ( var i = 0; i < data.actividades_economicas.length; i++) {
			actividadesEconomicas[i] = new Array(
					data.actividades_economicas[i].ID_ACTIVIDAD,
					data.actividades_economicas[i].NOMBRE);
		}
/*		cajas = new Array();
		for ( var i = 0; i < data.cajas.length; i++) {
			cajas[i] = new Array(data.cajas[i].ID_CCAF, data.cajas[i].NOMBRE);
		}
		mutuales = new Array();
		for ( var i = 0; i < data.mutuales.length; i++) {
			mutuales[i] = new Array(data.mutuales[i].ID_MUTUAL,
					data.mutuales[i].NOMBRE);
		}
*/
		regiones = new Array();
		for ( var i = 0; i < data.regiones.length; i++) {
			regiones[i] = new Array(data.regiones[i].ID_REGION,
					data.regiones[i].NOMBRE);
		}

		ciudades = new Array();
		for ( var i = 0; i < data.ciudades.length; i++) {
			ciudades[i] = new Array(data.ciudades[i].ID_CIUDAD,
					data.ciudades[i].NOMBRE, data.ciudades[i].ID_REGION);
		}

		comunas = new Array();
		for ( var i = 0; i < data.comunas.length; i++) {
			comunas[i] = new Array(data.comunas[i].ID_COMUNA,
					data.comunas[i].NOMBRE, data.comunas[i].ID_CIUDAD);
		}

		formatos = data.formatos;

		cargarDataFromServer();
	});

}

function autenticar(){
	
	var url = "ActionServlet?action=autenticar&rut_empresa="
			+ $("#_rut_empresa").val() + "&email_empresa="
			+ $("#_email_empresa").val() + "&jcaptcha="
			+ $("#jcaptcha").val() + "&rand=" + Math.random();

	mostrarCargando(texto_autenticando);
	var rut_empresa = $("#_rut_empresa").val();
	var email_empresa = $("#_email_empresa").val();

	$.getJSON(url, function(data) {
		if (data.data.ESTADO_AUTENTICACION == "true") {
			$("#div_login").hide();
			$("#div_ingreso").show();
			obtenerRecursos();

		} else {
			if(data.data.ERROR == "CAPTCHA_NO_COINCIDE"){
				mostrarInfoError(texto_autenticacion_error_captcha);
				refreshImg();
			}else if (data.data.ERROR == "DATOS_INVALIDOS") {
				mostrarInfoError(texto_autenticacion_error_datos_invalidos);
			} else if (data.data.ERROR == "EMPRESA_EXISTE") {
				mostrarInfoError(texto_autenticacion_error_empresa_existe);
			} else if (data.data.ERROR == "EMPRESA_REGISTRO_EN_PROCESO") {
				mostrarInfoError(texto_autenticacion_error_empresa_en_proceso);
			} else {
				mostrarInfoError(texto_autenticacion_error_datos_invalidos);
			}
		}

		ocultarCargando();

	});
}

function crearEmpresa() {

	var url = "ActionServlet?action=crear_empresa&rut_empresa="
			+ $("#_rut_empresa").val() + "&email_empresa="
			+ $("#_email_empresa").val() + "&tipo_seleccion="
			+ $("#seleccionTipo").val() + "&rand=" + Math.random();
	mostrarCargando(texto_creacion_empresa);
	$
			.getJSON(
					url,
					function(data) {
						if (data.data.ESTADO) {
							mostrarInfo("Creaci&oacute;n satisfactoria.", 2000);
							setTimeout(
									function() {
										mostrarInfo(
												"Se ha enviado un mail al Administrador para Acceder Al Sistema.",
												15000);
									}, 2000);
							setTimeout(function() {
								location.href = "index.jsp";
							}, 5000);

						} else {
							mostrarInfoError(
									"Ha ocurrido un error al momento de ingresar la empresa: "
											+ data.data.ERROR, 15000);
						}

						$("#div_ingreso").fadeOut(1000);
						$("#div_login").fadeIn(1000);
						setTimeout(function() {
							ocultarCargando();
						}, 500);
					});

}
function generarClave() {
	var url = "ActionServlet?action=generar_clave&rut_empresa="
			+ $("#_rut_empresa").val() + "&email_empresa="
			+ $("#_email_empresa").val() + "&rand=" + Math.random();
	mostrarCargando(texto_generando_clave);
	$.getJSON(url + "&" + Math.random(), function(data) {
		if (data.data.ESTADO_GENERACION == "true") {
			mostrarInfo("La clave ha sido generada, por favor revise su mail");
		} else if (data.data.ERROR == "EMPRESA_EXISTE") {
			mostrarInfoError(texto_autenticacion_error_empresa_existe);
		} else if (data.data.ERROR == "EMPRESA_REGISTRO_EN_PROCESO") {
			mostrarInfoError(texto_autenticacion_error_empresa_en_proceso);
		}

		ocultarCargando();
	});
}

function verDetalleIngreso() {

	$("#rut_empresa").html("");
	$("#email_empresa").html("");
	$("#estado_ingreso").html("");
	$("#nombre_administrado").html("");
	$("#email_administrado").html("");
	$("#telefono_administrado").html("");

	var miUrl = location.href;
	var rut_empresa = miUrl.split("rut_empresa=")[1].split("&")[0];
	var email_empresa = miUrl.split("email_empresa=")[1].split("&")[0];

	var url = "ActionServlet?action=ver_detalle&rut_empresa=" + rut_empresa
			+ "&email_empresa=" + email_empresa + "&rand=" + Math.random();
	$.getJSON(url, function(data) {
		if (data.data) {
			$("#rut_empresa").html(data.data.rut_empresa);
			$("#email_empresa").html(data.data.email_empresa);
			var str_estado = "NUEVO, Solo ha solicitado clave.";
			if (data.data.estado_ingreso == "1") {
				str_estado = "EN PROCESO, Se han actualizado algunos datos.";
			}
			if (data.data.estado_ingreso == "2") {
				str_estado = "GUARDADA, La Empresa ya ha sido guardada.";
			}
			$("#estado_ingreso").html(str_estado);
			$("#nombre_administrado").html(
					data.data.nombre_admin + " "
							+ data.data.apellido_paterno_admin + " "
							+ data.data.apellido_materno_admin);
			$("#email_administrado").html(data.data.email_admin);
			if (data.data.telefono_fijo_admin != null)
				$("#telefono_administrado").html(
						data.data.codigo_telefono_fijo_admin + " - "
								+ data.data.telefono_fijo_admin);

		}

	});
}

function hayCambios(formulario) {

	var dataTMP = "";
	var dataValues = "";
	sysout("info : " + formulario);
	if (formulario == "admin") {

		dataTMP = dataFormulario["rut_admin".toUpperCase()] + ";"
				+ dataFormulario["nombre_admin".toUpperCase()] + ";"
				+ dataFormulario["apellido_paterno_admin".toUpperCase()] + ";"
				+ dataFormulario["apellido_materno_admin".toUpperCase()] + ";"
				+ dataFormulario["codigo_telefono_fijo_admin".toUpperCase()]
				+ ";" + dataFormulario["telefono_fijo_admin".toUpperCase()]
				+ ";" + dataFormulario["codigo_fax_admin".toUpperCase()] + ";"
				+ dataFormulario["fax_admin".toUpperCase()] + ";"
				+ dataFormulario["celular_admin".toUpperCase()] + ";"
				+ dataFormulario["email_admin".toUpperCase()] + ";"
				+ dataFormulario["direccion_admin".toUpperCase()] + ";"
				+ dataFormulario["direccion_numero_admin".toUpperCase()] + ";"
				+ dataFormulario["departamento_numero_admin".toUpperCase()]
				+ ";" + dataFormulario["region_admin".toUpperCase()] + ";"
				+ dataFormulario["ciudad_admin".toUpperCase()] + ";"
				+ dataFormulario["comuna_admin".toUpperCase()];

		dataValues = $("#rut_admin").val() + ";" + $("#nombre_admin").val()
				+ ";" + $("#apellido_paterno_admin").val() + ";"
				+ $("#apellido_materno_admin").val() + ";"
				+ $("#codigo_telefono_fijo_admin").val() + ";"
				+ $("#telefono_fijo_admin").val() + ";"
				+ $("#codigo_fax_admin").val() + ";" + $("#fax_admin").val()
				+ ";" + $("#celular_admin").val() + ";"
				+ $("#email_admin").val() + ";" + $("#direccion_admin").val()
				+ ";" + $("#direccion_numero_admin").val() + ";"
				+ $("#departamento_numero_admin").val() + ";"
				+ $("#region_admin").val() + ";" + $("#ciudad_admin").val()
				+ ";" + $("#comuna_admin").val();

	}
	if (formulario == "formato_nomina") {
		dataTMP = dataFormulario["generar_planilla_inp_sucursal".toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_monto_total_ccaf".toUpperCase()]
				+ ";"
				+ dataFormulario["generar_planilla_mutual_sucursal"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_monto_total_salud".toUpperCase()]
				+ ";"
				+ dataFormulario["generar_planilla_ccaf_sucursal".toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_monto_total_prevision".toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_monto_total_fonasa".toUpperCase()]
				+ ";" + dataFormulario["imprimir_planillas".toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_monto_total_mutual".toUpperCase()]
				+ ";"
				+ dataFormulario["calcular_movimiento_personal".toUpperCase()]
				+ ";" + dataFormulario["formato_nomina_sel".toUpperCase()];
		
		dataValues = $("#generar_planilla_inp_sucursal").val() + ";"
				+ $("#calcular_monto_total_ccaf").val() + ";"
				+ $("#generar_planilla_mutual_sucursal").val() + ";"
				+ $("#calcular_monto_total_salud").val() + ";"
				+ $("#generar_planilla_ccaf_sucursal").val() + ";"
				+ $("#calcular_monto_total_prevision").val() + ";"
				+ $("#calcular_monto_total_fonasa").val() + ";"
				+ $("#imprimir_planillas").val() + ";"
				+ $("#calcular_monto_total_mutual").val() + ";"
				+ $("#calcular_movimiento_personal").val() + ";"
				+ getValueCheckbox("formato_nomina_sel");

	}

	if (formulario == "previsionales") {

	/*	dataTMP = dataFormulario["nombre_mutual".toUpperCase()] + ";"
				+ dataFormulario["numero_adherentes_mutual".toUpperCase()]
				+ ";"
				+ dataFormulario["calculo_individual_mutual".toUpperCase()]
				+ ";" + dataFormulario["tasa_adicional_mutual".toUpperCase()]
				+ ";" + dataFormulario["caja_compensacion".toUpperCase()];
	*/			
		dataTMP = dataFormulario["caja_compensacion".toUpperCase()];
				
	/*	dataValues = $("#nombre_mutual").val() + ";"
				+ $("#numero_adherentes_mutual").val() + ";"
				+ $("#calculo_individual_mutual").val() + ";"
				+ $("#tasa_adicional_mutual").val() + ";"
				+ $("#caja_compensacion").val();
	*/
		
		dataValues = $("#caja_compensacion").val();
	}

	if (formulario == "empresa") {

		dataTMP = dataFormulario["razon_social_empresa".toUpperCase()]
				+ ";"
				+ dataFormulario["tipo_empresa".toUpperCase()]
				+ ";"
				+ dataFormulario["nombre_holding_empresa".toUpperCase()]
				+ ";"
				+ dataFormulario["rut_representante_legal_empresa"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["nombre_representante_legal_empresa"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["apellido_paterno_representante_legal_empresa"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["apellido_materno_representante_legal_empresa"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["estado_representante_legal_empresa"
						.toUpperCase()]
				+ ";"
				//+ dataFormulario["vigencia_representante_legal_empresa"
				//		.toUpperCase()]
				//+ ";"
				+ dataFormulario["codigo_acividad_economica_empresa"
						.toUpperCase()]
				+ ";"
				+ dataFormulario["nombre_acividad_economica_empresa"
						.toUpperCase()];

		dataValues = $("#razon_social_empresa").val() + ";"
				+ $("#tipo_empresa").val() + ";"
				+ $("#nombre_holding_empresa").val() + ";"
				+ $("#rut_representante_legal_empresa").val() + ";"
				+ $("#nombre_representante_legal_empresa").val() + ";"
				+ $("#apellido_paterno_representante_legal_empresa").val()
				+ ";"
				+ $("#apellido_materno_representante_legal_empresa").val()
				+ ";" + $("#estado_representante_legal_empresa").val() + ";"
				//+ $("#vigencia_representante_legal_empresa").val() + ";"
				+ $("#codigo_acividad_economica_empresa").val() + ";"
				+ $("#nombre_acividad_economica_empresa").val();

	}

	if (formulario == "casa_matriz") {

		dataTMP = dataFormulario["codigo_casa_matriz".toUpperCase()] + ";"
				+ dataFormulario["nombre_casa_matriz".toUpperCase()] + ";"
				+ dataFormulario["direccion_casa_matriz".toUpperCase()] + ";"
				+ dataFormulario["direccion_numero_casa_matriz".toUpperCase()]
				+ ";"
				+ dataFormulario["depto_numero_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["region_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["ciudad_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["comuna_casa_matriz".toUpperCase()]
				+ ";"
				+ dataFormulario["codigo_telefono_casa_matriz".toUpperCase()]
				+ ";"
				+ dataFormulario["telefono_fijo_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["celular_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["codigo_fax_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["fax_casa_matriz".toUpperCase()]
				+ ";" + dataFormulario["email_casa_matriz".toUpperCase()];
		
		dataValues = $("#codigo_casa_matriz").val() + ";"
				+ $("#nombre_casa_matriz").val() + ";"
				+ $("#direccion_casa_matriz").val() + ";"
				+ $("#direccion_numero_casa_matriz").val() + ";"
				+ $("#depto_numero_casa_matriz").val() + ";"
				+ $("#region_casa_matriz").val() + ";"
				+ $("#ciudad_casa_matriz").val() + ";"
				+ $("#comuna_casa_matriz").val() + ";"
				+ $("#codigo_telefono_casa_matriz").val() + ";"
				+ $("#telefono_fijo_casa_matriz").val() + ";"
				+ $("#celular_casa_matriz").val() + ";"
				+ $("#codigo_fax_casa_matriz").val() + ";"
				+ $("#fax_casa_matriz").val() + ";"
				+ $("#email_casa_matriz").val();
	}

	if (dataTMP.toLowerCase() == dataValues.toLowerCase()) {
		return false;
	} else {
		return true;
	}
}