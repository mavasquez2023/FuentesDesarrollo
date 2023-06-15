
var espere = "Por Favor Espere.";
var texto_cargando_informacion = "Cargando Informaci&oacute;n. " + espere;
var texto_procesando_registros = "Procesando Registros. " + espere;
var formulario_cargado = "pendientes";
function cargarValores(formulario) {
rol_usuario= $("#rol_usuario").val();
	if(formulario=="consulta_empresa" && rol_usuario=="Ejecutivo"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	$(".mi_tab_selected").attr("class", "mi_tab");
	$("#tab_" + formulario).attr("class", "mi_tab mi_tab_selected");
	continuarCarga(formulario);
	if(formulario!="consulta_roles"){
		ocultarFiltro('filtroAppRol');
		limpiaRoles();		
		$("#apps").get(0).selectedIndex = 0;
		$("#roles").get(0).selectedIndex = 0;
	}
	ocultarPassword();
	limpiaCampos(formulario);
	if(formulario=="consulta_pac"){
		cargarFiltro('filtroAppRol');
		$("#apps").val("IntegradorLDAP");
		consultarAppRoles("IntegradorLDAP");
	}
	if(formulario=="consulta_roles"){
		cargarFiltro('filtroAppRol');
	}
}

function continuarCarga(formulario) {
	$("#" + formulario_cargado).hide();
	$("#" + formulario).fadeIn(1000);
	$(".active").attr("class", "");
	$("#li_" + formulario).attr("class", "active");
	formulario_cargado = formulario;
	//mostrarCargando(texto_cargando_informacion);
	//ocultarCargando();
}

function cargarFiltro(formulario) {
	$("#" + formulario).fadeIn(1000);
	$("#" + formulario_cargado + "_filtroon").hide();
	$("#" + formulario_cargado + "_filtrooff").show();
}
function ocultarFiltro(formulario) {
	$("#" + formulario).fadeOut(300);
	$("#" + formulario_cargado + "_filtrooff").hide();
	$("#" + formulario_cargado + "_filtroon").fadeIn(300);
	limpiaRoles();
	$("#apps").get(0).selectedIndex = 0;
}

function cargarPassword() {
	$("#password").fadeIn(1000);
}
function ocultarPassword() {
	$("#password").hide();
}

function limpiaRoles(){
	$("#roles").html("");
	var option = $(document.createElement('option'));
	option.text("Seleccione");
    option.val("");
	$("#roles").append(option);
}

function mostrarCargando(texto) {

	$("#texto_cargando").html(texto);
	if (!$('#texto_cargando').is(':visible')) {
		$("#cargando").show();
	}
}

function ocultarCargando() {
	$("#cargando").hide();
}

function errorCargando() {
	$("#cargando").hide();
	$("#texto_cargando").html("Servidor No Responde, intente nuevamente!");
}

function limpiaCampos(formulario){
if(formulario=="consulta"){
		$('#frmu input[type="text"]').each(function(){
      		$(this).val("");  
  		});
  		
  		$('#frmu input[type="radio"]').each(function(){
      		$(this).attr('checked', false);
  		});
		ocultarPassword();
		$("#consultar_usuario").css("visibility", "visible");
		$("#guardar_usuario").css("visibility", "hidden");
		$("#nuevo").val("");
		$("#nombres").val("");
		$("#apellido_paterno").val("");
		$("#apellido_materno").val("");
		$("#username").removeAttr("disabled");
		$("#nombres").removeAttr("disabled");
		$("#apellido_paterno").removeAttr("disabled");
		$("#apellido_materno").removeAttr("disabled");
		$("#username").focus();
}else if(formulario=="pendientes"){
		$("#nuevos1000").html("0");
		$("#modificar1000").html("0");
		$("#errores1000").html("0");
		$("#eliminar1000").html("0");
		$("#nuevos2000").html("0");
		$("#modificar2000").html("0");
		$("#errores2000").html("0");
		$("#eliminar2000").html("-");
		$("#nuevos2500").html("0");
		$("#modificar2500").html("-");
		$("#errores2500").html("0");
		$("#eliminar2500").html("0");
		$("#nuevos3500").html("0");
		$("#modificar3500").html("-");
		$("#errores3500").html("0");
		$("#eliminar3500").html("0");
		$("#ultima_ejecucion").html("");
}else if(formulario=="consulta_empresa"){
	if($("#razonSocialEmpresa").val()!="" || $("#observaciones").html()!=""){
		$('#frme input[type="text"]').each(function(){
      		$(this).val("");  
  		});
  		$('#frme input[type="radio"]').each(function(){
      		$(this).attr('checked', false);
  		});
  		$("#consultar_empresa").css("visibility", "visible");
  		$("#consultar_encargados").css("visibility", "visible");
		$("#guardar_empresa").css("visibility", "hidden");
		$("#nuevoe").val("");
		$("#apps").val("");
		$("#roles").val("");
		$("#rutempresa").removeAttr("disabled");
		$("#razonSocialEmpresa").removeAttr("disabled");
		$("#rutempresa").focus();
	}
}else if(formulario=="consulta_roles"){
		limpiaRoles();
		$("#apps").get(0).selectedIndex = 0;
		newAPPROL();
}else if(formulario=="consulta_pac"){
		limpiaRoles();
		$("#apps").get(0).selectedIndex = 0;
		//newAPPROL();
}else if(formulario=="consulta_pac_simple"){
		$("#newuser").val("");
		consultarUsuAppRoles();
}else if(formulario=="util_ldap"){
		$("#usuario").val("");
		$("#clave").val("");
		$("#credential").val("");
}
	return;
}

function mostrarInfo(texto, tiempo) {
			$("#informacion").hide()
			$("#informacion_error").hide()
			$("#informacion_warning").hide()
			var fade_out = 2500;
			if (tiempo != null)
				fade_out = tiempo
			$("#informacion").html(texto);
			$("#informacion").fadeIn(500);
			$("#informacion").fadeOut(fade_out);
}

function mostrarInfoErrorLogin(texto, tiempo) {
		$("#informacion").hide()
		$("#informacion_error_login").hide()
		var fade_out = 2500;
		if (tiempo != null)
			fade_out = tiempo
		$("#informacion_error_login").html(texto);
		$("#informacion_error_login").fadeIn(500);
		$("#informacion_error_login").fadeOut(fade_out);
}

function mostrarInfoError(texto, tiempo) {
		$("#informacion").hide()
		$("#informacion_error").hide()
		var fade_out = 2500;
		if (tiempo != null)
			fade_out = tiempo
		$("#informacion_error").html(texto);
		$("#informacion_error").fadeIn(500);
		$("#informacion_error").fadeOut(fade_out);
}

function mostrarInfoWarning(texto, tiempo) {
			$("#informacion").hide()
			$("#informacion_error").hide()
			$("#informacion_warning").hide()
			var fade_out = 2500;
			if (tiempo != null)
				fade_out = tiempo
			$("#informacion_warning").html(texto);
			$("#informacion_warning").fadeIn(500);
			$("#informacion_warning").fadeOut(fade_out);
}
	
function marcarCampoError(id) {
		$("#" + id).attr("class", "hint");
		//$("#" + id).val("");
		$("#" + id).click(function() {
			$(this).attr("class", "");
		});
}

function marcarCampoError(id, campo) {
		$("#" + id).attr("class", "hint");
		//$("#" + id).val("");
		$("#" + id).click(function() {
			$(this).attr("class", "");
		});
		mostrarInfoWarning("Ingrese un " + campo + " válido", 3000);
}


function abrir_detalle(ancho, alto) {
        	$("#detalleconsulta").dialog({ <!--  ------> muestra la ventana  -->
			width: ancho,  <!-- -------------> ancho de la ventana -->
			height: alto,<!--  -------------> altura de la ventana -->
			show: "scale", <!-- -----------> animación de la ventana al aparecer -->
			hide: "scale", <!-- -----------> animación al cerrar la ventana -->
			resizable: "true", <!-- ------> fija o redimensionable si ponemos este valor a "true" -->
			position: "center",<!--  ------> posicion de la ventana en la pantalla (left, top, right...) -->
			modal: "true" <!-- ------------> si esta en true bloquea el contenido de la web mientras la ventana esta activa  -->
			});
        	
}
function abrir_stats(ancho, alto) {
        	$("#estadisticas").dialog({ <!--  ------> muestra la ventana  -->
			width: ancho,  <!-- -------------> ancho de la ventana -->
			height: alto,<!--  -------------> altura de la ventana -->
			show: "scale", <!-- -----------> animación de la ventana al aparecer -->
			hide: "scale", <!-- -----------> animación al cerrar la ventana -->
			resizable: "true", <!-- ------> fija o redimensionable si ponemos este valor a "true" -->
			position: "center",<!--  ------> posicion de la ventana en la pantalla (left, top, right...) -->
			modal: "true" <!-- ------------> si esta en true bloquea el contenido de la web mientras la ventana esta activa  -->
			});
        	
}
function validaEnter(){
	if(window.event.keyCode==13) {
		window.event.keyCode = 0;
		return false;
	}
}
function consultarOpcion(value){
	if(formulario_cargado=="consulta_roles"){
		if(value!=""){
			newROL_LIST();
		}else{
			newROL();
		}
	}
}