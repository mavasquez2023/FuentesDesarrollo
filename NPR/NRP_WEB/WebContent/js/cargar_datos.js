var dataFormulario = new Array();
var carga_inicial = true;

function estadoCronta(estado) {
rol_usuario= $("#rol_usuario").val();
	if(estado!="" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if(estado=='save'){
		estado= $('input:radio[name=estadocronta]:checked').val();
	}
	var url = "ActionServlet?action=ECLDAP"
			+ "&cronta=" + estado;
			+ "&rand=" + Math.random();
	;
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado_cronta=="on"){
								$("#on").attr('checked', 'checked');
							}else if(data.estado_cronta=="off"){
								$("#off").attr('checked', 'checked');
							}else{
								mostrarInfoError("Cronta no salvado!");
							}
							if(data.save=="OK"){
								mostrarInfo("Cronta salvado exitosamente!");
							}
						}
					});
}
function consultarApps() {
	var url = "ActionServlet?action=CALDAP" + "&rand=" + Math.random();
	;
	//mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						
						if (data.apps != null) {
							$.each(data.apps, 
								function(indice, app){
									var option = $(document.createElement('option'));
									option.text(app);
                        			option.val(app);
									$("#apps").append(option);
							});
						}
					//ocultarCargando();
					});
}

function consultarAppRoles(app) {
	rol_usuario= $("#rol_usuario").val();
	var url = "ActionServlet?action=CARLDAP" + "&appid=" + app + "&rand=" + Math.random();
	mostrarCargando(texto_cargando_informacion);
	$("#approl").html("");
	limpiaRoles();
	if($("#apps").get(0).selectedIndex==0 && rol_usuario=="Gerente"){
		newAPPROL();
		ocultarCargando();
	}else{
		count=0;
		$
			.getJSON(
					url,
					function(data) {
						if (data.approles != null) {
							$.each(data.approles, 
								function(indice, approl){
									option = $(document.createElement('option'));
									option.text(approl);
                        			option.val(approl);
									$("#roles").append(option);
									count++;
							});
						}
					newROL(count);
					ocultarCargando();
					});
 	}
}

function consultarUsuAppRoles() {
	count=0;
	rol_usuario= $("#rol_usuario").val();
	if(($("#apps").val()=="" || $("#roles").get(0).selectedIndex==0)){
		mostrarInfoError("Seleccione una Aplicación y un Rol");
		//marcarCampoError("roles");
		return;
	}
	if(rol_usuario=="Administrador" && $("#roles").val()=="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios para cambiar este Rol");
		$("#usuarios_approl").html("");
		//marcarCampoError("roles");
		return;
	}
	
	var url = "ActionServlet?action=CUARLDAP" 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + $("#roles").val()
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						
						if (data.usuapproles != null) {
							$("#usuarios_approl").html("");
								$("#usuarios_approl").append("<table border=1 align=\"center\"  cellspacing=\"2\">");
								$("#usuarios_approl").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Usuarios Registrados</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
								$.each(data.usuapproles, 
									function(indice, usu){
									linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\">" + usu.RUT + " " + usu.NOMBRE + "</td><td class=\"data_campo_check\"><a href=\"#\" title=\"Eliminar RUT\" onclick=\"addUsuarioRol('" + usu.RUT + "','E');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></td></tr>";
									$("#usuarios_approl").append(linea);
									count++;
								});
								linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newuser\" id=\"newuser\" maxlength=\"13\" onKeyPress=\"keyRut();\" onKeyUp=\"formateaRut(this)\" /></td>"+
										"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar RUT\" onclick=\"addUsuarioRol('', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>"+
																		"&nbsp;&nbsp;<a href=\"#\" title=\"Subir Lista\" onclick=\"cargarArchivoAR();\"><span class=\"glyphicon glyphicon-list-alt\"></span></a>";
																		
								if(count==0){
									linea+= "&nbsp;&nbsp;<a href=\"#\" title=\"Eliminar ROL\" onclick=\"addRolAPP('R','E');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></a>"
								}
								linea+= "</td></tr>";
								$("#usuarios_approl").append(linea);
								linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><div id=\"upfile_a\" style=\"visibility: hidden\"><input type=\"file\" name=\"listusu_a\" id=\"listusu_a\" value=\"\" />&nbsp;<a href=\"#\" onclick=\"ayuda();\"><img src=\"images/help.jpg\" title=\"Formato archivo\"></a></div></td>"+
								"<td class=\"data_campo_check\"><div id=\"upfile_a2\" style=\"visibility: hidden\"><input type=\"submit\" name=\"Subir\" value=\"Subir\" onclick=\"submitFileAR();\" /></div></td></tr>"
								$("#usuarios_approl").append(linea);
								$("#usuarios_approl").append("</table>");
						}else{
							$("#usuarios_approl").html("No existen Usuarios registrados al Rol");
						}
					ocultarCargando();
					});
}

function newAPPROL(){
	$("#usuarios_approl").html("");
	$("#usuarios_approl").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
	$("#usuarios_approl").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Nueva Aplicación</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
	linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newapp\" id=\"newapp\" /></td>"+
			"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar APP\" onclick=\"addRolAPP('A', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a></td></tr>";
	$("#usuarios_approl").append(linea);
	$("#usuarios_approl").append("</table>");
}

function newROL(count){
	$("#usuarios_approl").html("");
	if(rol_usuario=="Gerente"){
	$("#usuarios_approl").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
	$("#usuarios_approl").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Nuevo ROL</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
	linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newrol\" id=\"newrol\" /></td>"+
			"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar ROL\" onclick=\"addRolAPP('R', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>";
	if(count==0){
		linea+= "&nbsp;&nbsp;<a href=\"#\" title=\"Eliminar APP\" onclick=\"addRolAPP('A', 'E');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></a>"
	}
	linea+= "</td></tr>";
	$("#usuarios_approl").append(linea);
	$("#usuarios_approl").append("</table>");
	}
}

function newROL_LIST(){
	$("#usuarios_approl").html("");
	$("#usuarios_approl").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
	$("#usuarios_approl").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Nuevo ROL</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
	linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newrol\" id=\"newrol\" /></td>"+
			"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar ROL\" onclick=\"addRolAPP('R', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>";
	linea+= "</td></tr>";
	$("#usuarios_approl").append(linea);
	$("#usuarios_approl").append("</table>");
}

function submitFileAR(){
	if($("#listusu_a").val()==""){
		marcarCampoError("listusu_a");
		return;
	}
	document.fupload_rol.appid_rol.value= $("#apps").val();
	document.fupload_rol.rolid_rol.value= $("#roles").val();
}

function cargarArchivoAR(){
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Administrador" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	$("#upfile_a").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
    $("#upfile_a2").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
}

function addRolAPP(tipo, estado){
rol_usuario= $("#rol_usuario").val();
if(rol_usuario!="Administrador" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
}
rol=$("#roles").val();
app=$("#apps").val();
if(tipo=="R" && estado=="N"){
		rol= $("#newrol").val();
		if(rol==""){
			marcarCampoError("newrol");
			return;
		}
}
if(tipo=="A" && estado=="N"){
		app= $("#newapp").val();
		if(app==""){
			marcarCampoError("newapp");
			return;
		}
}
var url = "ActionServlet?action=GUARLDAP" 
			+ "&appid=" + app
			+ "&rolid=" + rol
			+ "&userid=" + ""
			+ "&estado=" + estado
			+ "&rand=" + Math.random();
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Operación realizada exitosamente!");
							}else{
								mostrarInfoError("ERROR, operación no realizada, consulte log.");
							}
						}
					ocultarCargando();
					limpiaRoles();
					$("#apps").get(0).selectedIndex = 0;
					$("#usuarios_approl").html("");
					});
}

function addUsuarioRol(user, estado) {
rol_usuario= $("#rol_usuario").val();
if(rol_usuario!="Administrador" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
}
	if(estado=="N"){
		user= $("#newuser").val();
		if(!validarRutIngresado("newuser", user)){
			return;
		}
	}
	
	if(user==""){
			marcarCampoError("newuser");
			return;
	}
	
	var url = "ActionServlet?action=GUARLDAP" 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + $("#roles").val()
			+ "&userid=" + user
			+ "&estado=" + estado
			+ "&rand=" + Math.random();
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Operación realizada exitosamente!");
							}else{
								mostrarInfoError("ERROR, operación no realizada, consulte log.");
							}
						}
					limpiaCampos('consulta_pac_simple');
					ocultarCargando();
					});
}

function consultarUsuario() {
	rol_usuario= $("#rol_usuario").val();
	if($("#username").val()==""){
		marcarCampoError("username", "RUT");
		return;
	}
	if(!validarRutIngresado("username", $("#username").val())){
		return;
	}
	var url = "ActionServlet?action=CULDAP&username="
			+ $("#username").val() 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + $("#roles").val()
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							$("#detalle").html("");
							$("#nombres").val(data.usuario.nombres);
							$("#apellido_paterno").val(data.usuario.apellidoPaterno);
							$("#apellido_materno").val(data.usuario.apellidoMaterno);
							$("#email").val(data.usuario.email);
							$("#telefono").val(data.usuario.telefono);
							$("#email_old").val(data.usuario.email);
							$("#telefono_old").val(data.usuario.telefono);
							$("#nuevo").val(data.usuario.nuevo);
							/*if(data.usuario.blocked){
								$("#blocked_1").attr('checked', 'checked');
							}else{
								$("#blocked_0").attr('checked', 'checked');
							}
							$("#question").val(data.usuario.question);
							$("#answer").val(data.usuario.answer);
							*/
							if(data.usuario.observaciones!=null && data.usuario.observaciones!=""){
								mostrarInfoError(data.usuario.observaciones);
							}else{
								cargarPassword();
							}
							if (data.empresas_autorizadas != null) {
								$("#detalle").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
								$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\" colspan=\"2\">Empresas Autorizadas</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
								$.each(data.empresas_autorizadas, 
										function(indice, empresa){
										linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\">" + empresa.ID + "</td><td class=\"data_campo\">" + empresa.NOMBRE + "</td><td class=\"data_campo_check\"><a href=\"#\" title=\"Eliminar Empresa\" onclick=\"addEmpresaRolUsu('" + empresa.ID + "','E');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></td></tr>"
										$("#detalle").append(linea);
								});
								linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newempuser\" id=\"newempuser\" maxlength=\"13\" onKeyPress=\"keyRut();\" onKeyUp=\"formateaRut(this)\" /></td><td class=\"data_campo\">&nbsp;</td>"+
								"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar Empresa\" onclick=\"addEmpresaRolUsu('', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>"+
																"&nbsp;&nbsp;<a href=\"#\" title=\"Subir Lista\" onclick=\"cargarArchivoU();\"><span class=\"glyphicon glyphicon-list-alt\"></span></a></td></tr>";
								$("#detalle").append(linea);
								linea= "<tr class=\"tr_formulario\"><td colspan=\"2\" class=\"data_campo\"><div id=\"upfile_u\" style=\"visibility: hidden\"><input type=\"file\" name=\"listemp_u\" id=\"listemp_u\" value=\"\" />&nbsp;<a href=\"#\" onclick=\"ayuda();\"><img src=\"images/help.jpg\" title=\"Formato archivo\"></a></div></td>"+
								"<td class=\"data_campo_check\"><div id=\"upfile_u2\" style=\"visibility: hidden\"><input type=\"submit\" name=\"Subir\" value=\"Subir\" onclick=\"submitFileU();\" /></div></td></tr>"
								$("#detalle").append(linea);
								$("#detalle").append("</table>");
								abrir_detalle(500, 350);
							}
							
						}
					ocultarCargando();
					if($("#nuevo").val()=="true"){
						$("#guardar_usuario").css("visibility", "visible");
					}else if(rol_usuario=="Ejecutivo"){
						$("#nombres").attr('disabled', 'disabled');
						$("#apellido_paterno").attr('disabled', 'disabled');
						$("#apellido_materno").attr('disabled', 'disabled');
					}
					if(rol_usuario=="Gerente"){
						$("#guardar_usuario").css("visibility", "visible");
					}
					$("#consultar_usuario").css("visibility", "hidden");
					$("#username").attr('disabled', 'disabled');
					});
}

function submitFileU(){
	if($("#listemp_u").val()==""){
		marcarCampoError("listemp_u");
		return;
	}
	if(rol_usuario=="Agente"){
		$("#apps").val("PorEmpAdhe");
		$("#roles").val("PorEmpAdheEnc");
	}
	document.fupload.appid.value= $("#apps").val();
	document.fupload.rolid.value= $("#roles").val();
	document.fupload.userid.value= $("#username").val();
	document.fupload.rutemp.value= $("#rutempresa").val();
	//document.fupload.submit();
}

function cargarArchivoU(){
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Agente" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	$("#upfile_u").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
    $("#upfile_u2").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
}

function uploadFile(formulario){
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario=="Ejecutivo"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	var formData = new FormData(document.getElementById(formulario));
		//formData.append("dato", "valor");
  	// Enviamos el formulario usando AJAX
        $.ajax({
            type: $("#fupload").attr('method'),
            url: $("#fupload").attr('action'),
            dataType: "json",
    		data: formData,
    		cache: false,
    		contentType: false,
    		processData: false,
            beforeSend:function(data) {
            	if(formulario=="fupload"){
            		$("#detalleconsulta").dialog('close');
            	}else{
            		cargarArchivoAR();
            	}
            	mostrarCargando(texto_cargando_informacion);
            },
            //error: mostrarInfoError('ERROR, operación no realizada, consulte log.'),
            // Mostramos un mensaje con la respuesta de Servlet
            success: function(data) {
            	ocultarCargando();
            	mostrarInfo('Operación realizada exitosamente!');
            	resultadoUpload(data);
            }
        })        
        return false;
}

function resultadoUpload(data){
	if (data != null) {
			$("#detalle").html("");
			$("#detalle").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\" colspan=\"2\">Resumen Archivo</td></tr>");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"data_campo\">Total Registros</td><td class=\"data_campo\">" + data.total + "</td></tr>");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"data_campo\" align=\"left\">RUT Agregados</td><td class=\"data_campo\">" + data.ok + "</td></tr>");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"data_campo\" align=\"left\">RUT Eliminados</td><td class=\"data_campo\">" + data.del + "</td></tr>");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"data_campo\" align=\"left\">RUT Inválidos</td><td class=\"data_campo\">" + data.invalido + "</td></tr>");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"data_campo\" align=\"left\">RUT No Afiliado</td><td class=\"data_campo\">" + data.error+ "</td></tr>");
			$("#detalle").append("</table>");
			abrir_detalle(500, 350);
	}
}

function addEmpresaRolUsu(empresa, estado) {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Agente" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if(estado=="N"){
		empresa= $("#newempuser").val();
		if(!validarRutIngresado("newempuser", empresa)){
			return;
		}
	}
	if(empresa==""){
		marcarCampoError("newempuser");
		return;
	}
	
	var url = "ActionServlet?action=GURELDAP" 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + $("#roles").val()
			+ "&rutEmpresa=" + empresa 
			+ "&userid=" + $("#username").val()
			+ "&estado=" + estado
			+ "&rand=" + Math.random();
	$("#detalleconsulta").dialog('close');
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Operación realizada exitosamente!");
							}else{
								mostrarInfoError("ERROR, operación no realizada, consulte log.");
							}
						}
					ocultarCargando();
					
					});
}

function consultarEncargados(){
	$("#apps").val("PorEmpAdhe");
	$("#roles").val("PorEmpAdheEnc");
	
	consultarEmpresa();
}

function consultarEmpresa() {
rol_usuario= $("#rol_usuario").val();
if(rol_usuario=="Agente"){
	roles= "PorEmpAdheEnc";
}else{
	roles=$("#roles").val();
}
	if($("#rutempresa").val()==""){
		marcarCampoError("rutempresa", "RUT Empresa");
		return;
	}
	if(!validarRutIngresado("rutempresa", $("#rutempresa").val())){
		return;
	}
	$("#rutempresa").attr('disabled', 'disabled');
	
	var url = "ActionServlet?action=CELDAP&rutempresa="
			+ $("#rutempresa").val() 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + roles
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						
						if (data != null) {
							$("#detalle").html("");
							$("#razonSocialEmpresa").val(data.empresa.razonSocialEmpresa);
							$("#nuevoe").val(data.empresa.nuevoe);
							/*if(data.empresa.afiliada=="1"){
								$("#afiliada_1").attr('checked', 'checked');
							}else{
								$("#afiliada_0").attr('checked', 'checked');
							}
							$("#holding").val(data.empresa.holding);
							$("#codigoActividadEconomica").val(data.empresa.codigoActividadEconomica);
							$("#actividadEconomica").val(data.empresa.actividadEconomica);
							$("#direccion").val(data.empresa.direccion);
							$("#comuna").val(data.empresa.comuna);
							$("#ciudad").val(data.empresa.ciudad);
							$("#region").val(data.empresa.region);
							$("#rutRepLegal").val(data.empresa.rutRepLegal);
							$("#nombreRepLegal").val(data.empresa.nombreRepLegal);
							$("#apellidoPaternoRepLegal").val(data.empresa.apellidoPaternoRepLegal);
							$("#apellidoMaternoRepLegal").val(data.empresa.apellidoMaternoRepLegal);
							$("#fono").val(data.empresa.fono);
							$("#email").val(data.empresa.email);
							$("#tipo").val(data.empresa.tipo);
							*/
							$("#estado").val(data.empresa.estado);
							if(data.empresa.observaciones!=null && data.empresa.observaciones!=""){
								mostrarInfoError(data.empresa.observaciones);
								$("#consultar_encargados").css("visibility", "hidden");
							}
							if (data.usuarios_registrados != null) {
								$("#detalle").html("");
								$("#detalle").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
								$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Encargados Empresa</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
								$.each(data.usuarios_registrados, 
									function(indice, usuario){
									linea= "<tr class=\"tr_formulario\"><td class=\"data_campo_ancho\">" + usuario.RUT + " " + usuario.NOMBRE + "</td><td class=\"data_campo_check\"><a href=\"#\" title=\"Eliminar RUT\" onclick=\"addUsuarioRolEmp('" + usuario.RUT + "','E');\"><span class=\"glyphicon glyphicon-remove-circle\"></span></td></tr>"
									$("#detalle").append(linea);
								});
								linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><input type=\"text\" name=\"newusere\" id=\"newusere\" maxlength=\"13\" onBlur=\"validarRutIngresado(this.id,this.value);\" onKeyPress=\"keyRut();\" onKeyUp=\"formateaRut(this)\"/></td>"+
									"<td class=\"data_campo_check\"><a href=\"#\" title=\"Agregar RUT\" onclick=\"addUsuarioRolEmp('', 'N');\"><span class=\"glyphicon glyphicon-ok-circle\"></span></a>"+
																	"&nbsp;&nbsp;<a href=\"#\" title=\"Subir Lista\" onclick=\"cargarArchivoE('N');\"><span class=\"glyphicon glyphicon-list-alt\"></span></a></td></tr>";
								$("#detalle").append(linea);
								linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><div id=\"upfile_e\" style=\"visibility: hidden\"><input type=\"file\" name=\"listusu_e\" id=\"listusu_e\" value=\"\" />&nbsp;<a href=\"#\" onclick=\"ayuda();\"><img src=\"images/help.jpg\" title=\"Formato archivo\"></a></div></td>"+
								"<td class=\"data_campo_check\"><div id=\"upfile_e2\" style=\"visibility: hidden\"><input type=\"submit\" name=\"Subir\" value=\"Subir\" onclick=\"submitFileE();\" /></div></td></tr>"
								$("#detalle").append(linea);
								$("#detalle").append("</table");
								abrir_detalle(400, 350);
							}else{
								$("#detalle").html("No existen Encargados de Empresa");
							}
						}
					ocultarCargando();
					if($("#nuevoe").val()=="true"){
						$("#guardar_empresa").css("visibility", "visible");
					}
					if(rol_usuario=="Gerente"){
						$("#guardar_empresa").css("visibility", "visible");
					}
					if(rol_usuario=="Agente"){
						$("#razonSocialEmpresa").attr('disabled', 'disabled');
					}
					$("#consultar_empresa").css("visibility", "hidden");
					//$("#apps").val("");
					//$("#roles").val("");
				});
}

function subirListaUsuarios(){
			$("#detalle").html("");
			$("#detalle").append("<table align=\"center\" class=\"tabla_formulario\" cellspacing=\"2\">");
			$("#detalle").append("<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Subir Lista Usuarios</td><td class=\"titulo_campo_check\" align=\"center\">Acción</td></tr>");
			linea= "<tr class=\"tr_formulario\"><td class=\"data_campo\"><div id=\"upfile_lu\"><input type=\"file\" name=\"listusu_u\" id=\"listusu_u\" value=\"\" />&nbsp;<a href=\"#\" onclick=\"ayuda();\"><img src=\"images/help.jpg\" title=\"Formato archivo\"></a></div></td>"+
					"<td class=\"data_campo_check\"><div id=\"upfile_lu2\"><input type=\"submit\" name=\"Subir\" value=\"Subir\" onclick=\"submitFileLU();\" /></div></td></tr>"
			$("#detalle").append(linea);
			$("#detalle").append("</table");
			abrir_detalle(400, 350);
}

function submitFileLU(){
	if($("#listusu_u").val()==""){
		marcarCampoError("listusu_u");
		return;
	}
	
	document.fupload.appid.value= "";
	document.fupload.rolid.value= "";
	document.fupload.userid.value= "";
	document.fupload.rutemp.value= "";
}

function cargarArchivoLU(){
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Administrador" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	$("#upfile_lu").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
    $("#upfile_lu2").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
}

function submitFileE(){
	if($("#listusu_e").val()==""){
		marcarCampoError("listusu_e");
		return;
	}
	if(rol_usuario=="Agente"){
		$("#apps").val("PorEmpAdhe");
		$("#roles").val("PorEmpAdheEnc");
	}
	document.fupload.appid.value= $("#apps").val();
	document.fupload.rolid.value= $("#roles").val();
	document.fupload.userid.value= $("#username").val();
	document.fupload.rutemp.value= $("#rutempresa").val();
}

function cargarArchivoE(){
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Agente" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	$("#upfile_e").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
    $("#upfile_e2").css('visibility', function(i, visibility) {
        return (visibility == 'visible') ? 'hidden' : 'visible';
    });
}

function addUsuarioRolEmp(user, estado) {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Agente" && rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if(rol_usuario=="Agente"){
		$("#apps").val("PorEmpAdhe");
		$("#roles").val("PorEmpAdheEnc");
	}
	if(estado=="N" && user==""){
		user= $("#newusere").val();
		if(!validarRutIngresado("newusere", user)){
			return;
		}
	}
	if(user==""){
		marcarCampoError("newusere");
		return;
	}

	var url = "ActionServlet?action=GURELDAP" 
			+ "&appid=" + $("#apps").val()
			+ "&rolid=" + $("#roles").val()
			+ "&rutEmpresa=" + $("#rutempresa").val() 
			+ "&userid=" + user
			+ "&estado=" + estado
			+ "&rand=" + Math.random();
	$("#detalleconsulta").dialog('close');
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Operación realizada exitosamente!");
							}else if(data.estado=="0"){
								mostrarInfoError("ERROR, operación no realizada, consulte log.");
							} else if(data.estado=="-1"){
								mostrarInfoWarning("Usuario debe ser creado previamente!", 3000);
								cargarValores('consulta');
								$("#username").val(data.usuario.username);
								$("#nombres").val(data.usuario.nombres);
								$("#apellido_paterno").val(data.usuario.apellidoPaterno);
								$("#apellido_materno").val(data.usuario.apellidoMaterno);
								$("#apps").val(data.appid);
								$("#roles").val(data.rolID);
								$("#nuevo").val(data.usuario.nuevo);
								$("#guardar_usuario").css("visibility", "visible");
								$("#consultar_usuario").css("visibility", "hidden");
							}else if(data.estado=="-2"){
								mostrarInfoError("ERROR, operación no realizada, usuario NO afiliado");
							}
							
						}
					ocultarCargando();
					
					});
}

function verPendientes(limpia_resultado) {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Gerente"){
		//mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	mostrarCargando(texto_cargando_informacion);
	limpiaCampos("pendientes");
	if(limpia_resultado== true){
		$("#resultado1000").html("");
		$("#resultado2000").html("");
		$("#resultado2500").html("");
		$("#resultado3500").html("");
	}
	var url = "ActionServlet?action=CPLDAP&rand=" + Math.random();
	$.getJSON(url, function(data) {
		if (data.pendientes!=null) {
			limpiaCampos('pendientes');
			$.each(data.pendientes, 
				function(indice, tipo){
					var tabla= tipo.TABLA;
					var estado= tipo.ESTADO;
					if(estado=="N"){
						if(tabla=="USUARIOS"){
							$("#nuevos1000").html(tipo.CANTIDAD);
						}else if(tabla=="EMPRESAS"){
							$("#nuevos2000").html(tipo.CANTIDAD);
						}else if(tabla=="ROLES"){
							$("#nuevos2500").html(tipo.CANTIDAD);
						}else if(tabla=="APPS"){
							$("#nuevos3500").html(tipo.CANTIDAD);
						}
					}
					else if(estado=="M"){
						if(tabla=="USUARIOS"){
							$("#modificar1000").html(tipo.CANTIDAD);
						}else if(tabla=="EMPRESAS"){
							$("#modificar2000").html(tipo.CANTIDAD);
						}else if(tabla=="ROLES"){
							$("#modificar2500").html("N/A");
						}else if(tabla=="APPS"){
							$("#modificar3500").html("N/A");
						}
					}
					else if(estado=="E"){
						if(tabla=="USUARIOS"){
							$("#eliminar1000").html(tipo.CANTIDAD);
						}else if(tabla=="EMPRESAS"){
							$("#eliminar2000").html(tipo.CANTIDAD);
						}else if(tabla=="ROLES"){
							$("#eliminar2500").html(tipo.CANTIDAD);
						}else if(tabla=="APPS"){
							$("#eliminar3500").html(tipo.CANTIDAD);
						}
					}
					else if(estado=="X"){
						if(tabla=="USUARIOS"){
							$("#errores1000").html(tipo.CANTIDAD);
						}else if(tabla=="EMPRESAS"){
							$("#errores2000").html(tipo.CANTIDAD);
						}else if(tabla=="ROLES"){
							$("#errores2500").html(tipo.CANTIDAD);
						}else if(tabla=="APPS"){
							$("#errores3500").html(tipo.CANTIDAD);
						}
					}
			});
			$("#ultima_ejecucion").html(data.ultimo.FECHA + ", " + data.ultimo.HORA);
			ocultarCargando();
		}else{
			errorCargando();
		}

	});
	
}

function reinyectarErroneos() {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	var url = "ActionServlet?action=RELDAP&rand=" + Math.random();
	mostrarCargando(texto_procesando_registros);
	$.getJSON(url, function(data) {
	ocultarCargando();
	verPendientes(true);
	});
}

function procesarRegistros() {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario!="Gerente"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	var url = "ActionServlet?action=PPLDAP&rand=" + Math.random();
	mostrarCargando(texto_procesando_registros);
	$.getJSON(url, function(data) {
		$("#resultado1000").html(data.procesoU.RESULTADO);
		$("#resultado2000").html(data.procesoE.RESULTADO);
		$("#resultado2500").html(data.procesoR.RESULTADO);
		$("#resultado3500").html(data.procesoA.RESULTADO);
		ocultarCargando();
		verPendientes(false);
	});
}

function verEstadisticas() {
	var url = "ActionServlet?action=EELDAP" + "&rand=" + Math.random();
	;
	//mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data.meses != null) {
							var stats_meses="";
							var stats_usuario="";
							var stats_empresa="";
							var stats_rolese="";
							var stats_approles="";
							var tabla="<table class=\"tabla_formulario\" cellspacing=\"2\">";
							tabla+= "<tr class=\"tr_formulario\"><td class=\"data_campo\">&nbsp;</td><td class=\"titulo_campo\" align=\"center\" colspan=\"13\">Períodos</td></tr>"
							$.each(data.meses, 
								function(pos, mes){
								var indicemes= 12-pos;
								if(pos==0){
									linea="<tr class=\"tr_formulario\"><td class=\"data_campo\" align=\"center\">&nbsp;</td>";
									stats_meses+= linea;
								}
								mesval= eval("mes.MES" + indicemes);
								linea="<td class=\"titulo_campo\" align=\"center\">" + mesval + "</td>";
								stats_meses+=linea;
											
								cantidad="0";
								$.each(data.estadisticas_usuarios, 
									function(indice, e_usuario){
										if(pos==0 && indice==0){
											linea="<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Usuarios</td>";
											stats_usuario+=linea;
										}
										if(e_usuario.PERIODO==mesval){
											cantidad= e_usuario.CANTIDAD;
										}
								});
								linea="<td class=\"data_campo\">" + cantidad + "</td>";
								stats_usuario+=linea;
								
								cantidad="0";
								$.each(data.estadisticas_empresas, 
									function(indice, e_empresa){
										if(pos==0 && indice==0){
											linea="<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Empresas</td>";
											stats_empresa+=linea;
										}
										if(e_empresa.PERIODO==mesval){
											cantidad= e_empresa.CANTIDAD;
										}
								});
								linea="<td class=\"data_campo\">" + cantidad + "</td>";
								stats_empresa+=linea;
								
								cantidad="0";
								$.each(data.estadisticas_roles_empresas, 
									function(indice, e_rolese){
										if(pos==0 && indice==0){
											linea="<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">Roles-Empresa</td>";
											stats_rolese+=linea;
										}
										if(e_rolese.PERIODO==mesval){
											cantidad= e_rolese.CANTIDAD;
										}
								});
								linea="<td class=\"data_campo\">" + cantidad + "</td>";
								stats_rolese+=linea;
								
								cantidad="0";		
								$.each(data.estadisticas_approles, 
									function(indice, e_approles){
										if(pos==0 && indice==0){
											linea="<tr class=\"tr_formulario\"><td class=\"titulo_campo\" align=\"center\">App-Roles</td>";
											stats_approles+=linea;
										}
										if(e_approles.PERIODO==mesval){
											cantidad= e_approles.CANTIDAD;
										}
								});
								linea="<td class=\"data_campo\">" + cantidad + "</td>";
								stats_approles+=linea;			
							});
							stats_usuario+="</tr>";
							stats_empresa+="</tr>";
							stats_rolese+="</tr>";
							stats_approles+="</tr>";
							stats_meses+="</tr>";
							tabla+= stats_meses;
							tabla+= stats_usuario;
							tabla+= stats_empresa;
							tabla+= stats_rolese;
							tabla+= stats_approles;
							tabla+="</table>";
							$("#tabla_stats").html(tabla);
						}
					abrir_stats(800, 250);
					});
}

function encode() {
	if($("#usuario").val()==""){
		marcarCampoError("usuario");
		return;
	}
	if($("#contra").val()==""){
		marcarCampoError("contra");
		return;
	}
	var base="";
	if($("#base_64").is(':checked')){
		base= $("#base_64").val()
	}else{
		base= $("#base_128").val()
	}
	$("#credential").val("");
	
	var url = "ActionServlet?action=ENCODE&username="
			+ $("#usuario").val() 
			+ "&password=" + $("#contra").val()
			+ "&base=" + base
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						
						if (data != null) {
							$("#credential").val(data.credential);
						}
					ocultarCargando();
					
					});
}

function decode() {
	if($("#credential").val()==""){
		marcarCampoError("credential");
		return;
	}
	var base="";
	if($("#base_64").is(':checked')){
		base= $("#base_64").val()
	}else{
		base= $("#base_128").val()
	}
	$("#usuario").val("");
	$("#contra").val("");
	
	var url = "ActionServlet?action=DECODE&credential="
			+ $("#credential").val()
			+ "&base=" + base
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						
						if (data != null) {
							$("#usuario").val(data.credential.name);
							$("#contra").val(data.credential.password);
						}
					ocultarCargando();
					
					});
}

function setPassword() {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario=="Usuario"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if($("#username").val()==""){
		marcarCampoError("username");
		return;
	}
	if($("#telefono").val()=="" && $("#email").val()==""){
		marcarCampoError("telefono", "Celular o Email");
		return;
	}
	if($("#email").val()!="" && !validaEmail($("#email").val())){
		marcarCampoError("email", "Email");
		return;
	}
	
	var url = "ActionServlet?action=PASS&username="
			+ $("#username").val() 
			+ "&email=" + $("#email").val()
			+ "&telefono=" + $("#telefono").val()
			+ "&clave=" + $("#clave").val()
			+ "&nombres=" + $("#nombres").val()
			+ "&apellido_paterno=" + $("#apellido_paterno").val()
			+ "&apellido_materno=" + $("#apellido_materno").val()
			+ "&email_old=" + $("#email_old").val()
			+ "&telefono_old=" + $("#telefono_old").val()
			
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.save=="1"){
								mostrarInfo("Cambio Password Exitoso!");
								//if($("#email").val() != $("#email_old").val() || $("#telefono").val() != $("#telefono_old").val()){
								//	setTimeout("guardarUsuario()", 3000);
								//}else{
								//	limpiaCampos('consulta');
								//}
								limpiaCampos('consulta');
							}else{
								mostrarInfoError("Error, Password No Cambiada!");
							}
						}
					ocultarCargando();
					
					});
}

function guardarEmpresa() {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario=="Usuario"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if($("#rutempresa").val()==""){
		marcarCampoError("rutempresa");
		return;
	}
	if($("#razonSocialEmpresa").val()==""){
		marcarCampoError("razonSocialEmpresa", "Razón Social");
		return;
	}

	var url = "ActionServlet?action=GELDAP&rutempresa="
			+ $("#rutempresa").val() 
			+ "&razonSocialEmpresa=" + $("#razonSocialEmpresa").val()
			+ "&nuevoe=" + $("#nuevoe").val()
		/*	+ "&afiliada=" + afiliado
			+ "&holding=" + $("#holding").val()
			+ "&tipo=" + $("#tipo").val()
			+ "&codigoActividadEconomica=" + $("#codigoActividadEconomica").val()
			+ "&direccion=" + $("#direccion").val()
			+ "&comuna=" + $("#comuna").val()
			+ "&ciudad=" + $("#ciudad").val()
			+ "&region=" + $("#region").val()
			+ "&rutRepLegal=" + $("#rutRepLegal").val()
			+ "&nombreRepLegal=" + $("#nombreRepLegal").val()
			+ "&apellidoPaternoRepLegal=" + $("#apellidoPaternoRepLegal").val()
			+ "&apellidoMaternoRepLegal=" + $("#apellidoMaternoRepLegal").val()
			+ "&fono=" + $("#fono").val()
			+ "&email=" + $("#email").val()
			*/
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Empresa creada o modificada exitosamente!");
							}else{
								mostrarInfoError("ERROR, no se ha grabado la empresa, consulte log.");
							}
						}
					ocultarCargando();
					limpiaCampos('consulta_empresa');
					});
}

function guardarUsuario() {
rol_usuario= $("#rol_usuario").val();
	if(rol_usuario=="Usuario"){
		mostrarInfoError("Usuario no tiene los privilegios necesarios.");
		return;
	}
	if($("#username").val()==""){
		marcarCampoError("username");
		return;
	}
	if($("#nombres").val()==""){
		marcarCampoError("nombres", "Nombre");
		return;
	}
	if($("#apellido_paterno").val()==""){
		marcarCampoError("apellido_paterno", "Apellido Paterno");
		return;
	}
	if($("#telefono").val()=="" && $("#email").val()==""){
		marcarCampoError("telefono", "Celular o Email");
		return;
	}
	if($("#email").val()!="" && !validaEmail($("#email").val())){
		marcarCampoError("email", "Email");
		return;
	}
	var url = "ActionServlet?action=GULDAP&username="
			+ $("#username").val() 
			+ "&nombres=" + $("#nombres").val()
			+ "&apellido_paterno=" + $("#apellido_paterno").val()
			+ "&apellido_materno=" + $("#apellido_materno").val()
			+ "&telefono=" + $("#telefono").val()
			+ "&email=" + $("#email").val()
			+ "&nuevo=" + $("#nuevo").val()
			+ "&rand=" + Math.random();
	;
	mostrarCargando(texto_cargando_informacion);
	$
			.getJSON(
					url,
					function(data) {
						if (data != null) {
							if(data.estado=="1"){
								mostrarInfo("Usuario creado o modificado exitosamente!");
								if(data.rutempresa!=""){
									cargarValores('consulta_empresa');
									$("#rutempresa").val(data.rutempresa); 
									addUsuarioRolEmp(data.username, 'N');
								}
							}else{
								mostrarInfoError("ERROR, no se ha grabado el usuario, consulte log.");
							}
						}
					ocultarCargando();
					limpiaCampos('consulta');
					$("#apps").val("");
					$("#roles").val("");
					});
}

function ayuda(){
	alert("Formato registros: \n"+ 
	"<RUT-DV>;<ACCION>[;<CELULAR>;<EMAIL>;<NOMBRE>;<APELLIDO>]\n\n" +
	"ACCION: N o E (Nuevo o Eliminar)\n"+
	"CELULAR, EMAIL, NOMBRE y APELLIDO obligatorio para Nuevos\n\n" + 
	"Ejemplos: \n 1-9;E\n" + 
	"2-8;N;987654321;nn@gmail.com;NN;AA"
	);
}