<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- Le styles -->
<link href="dist/css/bootstrap.css" rel="stylesheet">
<link href="css/estilo.css" rel="stylesheet">

<link href="css/redmond/jquery-ui-1.10.4.custom.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/jquery.corner.js"></script>
<script type="text/javascript" src="js/ie.js"></script>
<script type="text/javascript" src="js/comun.js"></script>
<script type="text/javascript" src="js/cargar_datos.js"></script>
<script type="text/javascript" src="js/ldap.js"></script>
<script type="text/javascript" src="js/corev2.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<style>

label {
	display: inline-block;
	width: 5em;
}

fieldset div {
	margin-bottom: 2em;
}

fieldset .help {
	display: inline-block;
}

.ui-tooltip {
	width: 210px;
}
</style>
</head>
<body>
	<script>
	var path= "<%=request.getContextPath()%>";
		$(document).ready(function() {

			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
			estadoCronta('');
			//consultarRoles();
			rol_usuario= $("#rol_usuario").val();
			if(rol_usuario=="Gerente"){
				consultarApps();
				$("#pendientes").css("visibility", "visible");
				cargarValores('pendientes');
				verPendientes(true);
			}else if(rol_usuario=="Administrador"){
				cargarValores('consulta_pac');
			}else{
				cargarValores('consulta');
			}
			if(rol_usuario!="Gerente"){
				$("#consulta_filtroon").css("visibility", "hidden");
				$("#consulta_empresa_filtroon").css("visibility", "hidden");
			}
		 });
		
		function Salir(destino){
			window.location=path + "/Salir?destino=" + destino;
		}
		
		function editarOficinasAnexo(rutafiliado, rutempresa, nombre, alloffice){
	var dominio= "<%=request.getServerName()%>";
	var path= "<%=request.getContextPath()%>";
	var url = dominio + path + "/VentanaServlet?action=CONOFISUC&rutempresa="
		+ rutempresa
		+ "&rutafiliado=" + rutafiliado
		+ "&alloffice=" + alloffice
		+ "&nombre=" + nombre
		+ "&rand=" + Math.random();
;
openWindow(url, "_blank", 600, 600);


}

	</script>
	<div class="container">
		<form action="" id="frmh" name="frmh" >
		<div class="grid_12">
			<img src="images/cabecera_claves.jpg" /> <br/>
			
			<div id="salir" align="right"><span class="salir">${rol}:&nbsp;${user}&nbsp;</span><a href="#" onclick="Salir('logout.jsp');"><img src="images/ico_cerrar.gif" title="Cerrar Sesión"></a></div>
			<div id="informacion" class="informacion"></div>
			<div id="informacion_error" class="informacion"></div>
			<div id="informacion_warning" class="informacion"></div>
			<input type="hidden" name="rol_usuario" id="rol_usuario" value="${rol}">
		</div>
		</form>
		<div id="div_ingreso">
			<table style="margin-left: 30px;">
				<tr>
					<c:if test='${rol=="Gerente"}'>
					<td width="160px" class="mi_tab" id="tab_pendientes"
						onclick="cargarValores('pendientes');verPendientes(true);">Pendientes DB2
					</td>
					</c:if>
					<td width="220px" class="mi_tab" id="tab_consulta"
						onclick="cargarValores('consulta')">Clave Personas</td>
					<c:if test='${rol!="Administrador"}'>
					<td width="220px" class="mi_tab" id="tab_consulta_empresa"
						onclick="cargarValores('consulta_empresa')">Encargados Empresa</td>
					<td width="220px" class="mi_tab" id="tab_consulta_anexo"
						onclick="cargarValores('consulta_anexo')">Encargados Anexos</td>
					</c:if>
					<c:if test='${rol=="Administrador"}'>
					<td width="220px" class="mi_tab" id="tab_consulta_pac"
						onclick="cargarValores('consulta_pac')">Usuarios PAC</td>
					<td width="220px" class="mi_tab" id="tab_auditoria"
						onclick="cargarValores('auditoria')">Auditoria</td>
					</c:if>
					<c:if test='${rol=="Gerente"}'>
					<td width="220px" class="mi_tab" id="tab_consulta_roles"
						onclick="cargarValores('consulta_roles')">Usuarios APP</td>
					<td width="220px" class="mi_tab" id="tab_util_ldap"
						onclick="cargarValores('util_ldap')">Util LDAP</td>
					</c:if>
				</tr>
			</table>

			<div id="my-tab-content" class="tab-content"
				style="padding-left: 30px;">
				<div class="tab-pane active" id="pendientes" style="visibility: hidden">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td>&nbsp;</td>
							<td class="titulo_campo" align="center">Usuarios</td>
							<td class="titulo_campo" align="center">Empresas</td>
							<td class="titulo_campo" align="center">Roles Empresa</td>
							<td class="titulo_campo" align="center">Aplicaciones</td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Nuevos</td>
							<td class="data_campo" width="200px"><div id="nuevos1000">0</div></td>
							<td class="data_campo" width="200px"><div id="nuevos2000">0</div></td>
							<td class="data_campo" width="200px"><div id="nuevos2500">0</div></td>
							<td class="data_campo" width="200px"><div id="nuevos3500">0</div></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Modificar</td>
							<td class="data_campo"><div id="modificar1000">0</div></td>
							<td class="data_campo"><div id="modificar2000">0</div></td>
							<td class="data_campo"><div id="modificar2500">0</div></td>
							<td class="data_campo"><div id="modificar3500">0</div></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Eliminar</td>
							<td class="data_campo"><div id="eliminar1000">0</div></td>
							<td class="data_campo"><div id="eliminar2000">0</div></td>
							<td class="data_campo"><div id="eliminar2500">0</div></td>
							<td class="data_campo"><div id="eliminar3500">0</div></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Erroneos</td>
							<td class="data_campo"><div id="errores1000">0</div></td>
							<td class="data_campo"><div id="errores2000">0</div></td>
							<td class="data_campo"><div id="errores2500">0</div></td>
							<td class="data_campo"><div id="errores3500">0</div></td>
						</tr>
						<tr class="tr_formulario_resultado">
							<td class="titulo_campo">Resultado</td>
							<td class="data_campo_left"><div id="resultado1000" style="color: #238E23;font-family: Arial,Verdana;font-size: 10"></div></td>
							<td class="data_campo_left"><div id="resultado2000" style="color: #238E23;font-family: Arial,Verdana;font-size: 10"></div></td>
							<td class="data_campo_left"><div id="resultado2500" style="color: #238E23;font-family: Arial,Verdana;font-size: 10"></div></td>
							<td class="data_campo_left"><div id="resultado3500" style="color: #238E23;font-family: Arial,Verdana;font-size: 10"></div></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Última ejecución</td>
							<td class="data_campo_left" colspan="1"><div id="ultima_ejecucion"></div></td>
							<td>&nbsp;<a href="#" onclick="verPendientes(true);"></a></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Estado Cronta</td>
							<td class="data_campo_left">On&nbsp;<input type="radio" id="on"
								name="estadocronta" value="on" >&nbsp;&nbsp;Off&nbsp;<input type="radio" id="off"
								name="estadocronta" value="off" ></td>
							<td>&nbsp;
								<button type="button" class="btn btn-default btn-xs" onclick="estadoCronta('save');">
									<span class="glyphicon glyphicon-floppy-disk"></span>&nbsp;Save
								</button>
							</td>
							<td></td>
							<td align="right">&nbsp;
								<button type="button" class="btn btn-default btn-xs" onclick="verEstadisticas();">
									<span class="glyphicon glyphicon-stats"></span>&nbsp;Estadísticas
								</button>
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr>
							<td align="center" colspan="3">
								<button type="button" class="btn btn-default btn"
									onclick="procesarRegistros();">
									Procesar Pendientes
								</button>
							</td>
							<td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="reinyectarErroneos();">
									Reinyectar Erroneos
								</button>
							</td>
						</tr>
					</table>
				</div>
				<c:if test='${rol=="Gerente"}'>
				<div class="tab-pane" id="filtroAppRol">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Aplicación :</td>
							<td class="data_campo"><select id="apps" name="apps" onchange="consultarAppRoles(this.value);">
									<option value="" selected="selected">Seleccione</option>
								</select> 
							</td>
							<td class="titulo_campo">Ingrese Rol :</td>
							<td class="data_campo">
								<select id="roles" name="roles" onchange="consultarOpcion(this.value);">
									<option value="" selected="selected">Seleccione</option>
								</select>
							</td>
						</tr>
					</table>	
				</div>
				</c:if>
				<c:if test='${rol=="Administrador"}'>
				<div class="tab-pane" id="filtroAppRol">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Rol :</td>
							<td class="data_campo">
								<select id="roles" name="roles" onchange="consultarOpcion(this.value);">
									<option value="" selected="selected">Seleccione</option>
								</select>
							</td>
							<td >&nbsp;</td>
							<td >&nbsp;<input type="hidden" name="apps" id="apps" value="IntegradorLDAP"/> 
							</td>
						</tr>
					</table>	
				</div>
				</c:if>
				<c:if test='${rol!="Gerente"}'>
					<input type="hidden" name="apps" id="apps" value=""/>
					<input type="hidden" name="roles" id="roles" value=""/>
				</c:if>
				
				<div class="tab-pane" id="consulta">
				<form action="" id="frmu" name="frmu" >
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td class="titulo_campo">Rut&nbsp;* </td>
							<td class="data_campo">
							<input type="text" id="username" name="username" maxlength="13" 
									onBlur	="validarRutIngresado(this.id,this.value);"  
									onKeyPress="keyRut();" 
									onKeyUp="formateaRut(this);" />
							</td>
							<td>
								&nbsp;<button type="button" class="btn btn-default btn"
									onclick="limpiaCampos('consulta');">
									Limpiar
								</button>
							</td>
							<td align="left">
								<div id="consulta_filtroon"><a href="#" onclick="cargarFiltro('filtroAppRol');">++ Filtro Avanzado</a></div>
								<div id="consulta_filtrooff"><a href="#" onclick="ocultarFiltro('filtroAppRol');">-- Filtro Avanzado</a></div>
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Nombre&nbsp;*</td>
							<td class="data_campo" >
							<input type="text" id="nombres" name="nombres" maxlength="20" >
							</td>
						
							<td></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Apellido Paterno &nbsp;*</td>
							<td class="data_campo">
							<input type="text" id="apellido_paterno" name="apellido_paterno" maxlength="20">
							</td>	
							<td class="titulo_campo">Apellido Materno</td>
							<td class="data_campo">
							<input type="text" id="apellido_materno" name="apellido_materno" maxlength="20">
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Celular &nbsp;**</td>
							<td class="data_campo"><input type="text" id="telefono"
								name="telefono" maxlength="12" onKeyPress="keyFono();">
								<input type="hidden" id="telefono_old" name="telefono_old">
								</td>
							<td class="titulo_campo">Email &nbsp;**</td>
							<td class="data_campo"><input type="text" id="email" onKeyPress="keyEmail();" 
								name="email" maxlength="50">
								<input type="hidden" id="email_old" name="email_old">
								</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td colspan="4" class="texto_footer"><BR>(*) Campo obligatorio.<BR>(**) Debe ingresar al menos uno de los dos campos.</td>
						</tr>
						<!-- tr class="tr_formulario">
							<td class="titulo_campo">Sexo</td>
							<td class="data_campo"><input type="text"
								id="sexo" name="sexo" maxlength="1"></td>
							<td class="titulo_campo">Blocked</td>
							<td class="data_campo">Si&nbsp;<input type="radio" id="blocked_1"
								name="blocked" value="1" >&nbsp;&nbsp;No&nbsp;<input type="radio" id="blocked_0"
								name="blocked" value="0" ></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Pregunta Clave</td>
							<td class="data_campo"><input type="text" id="question"
								name="question" maxlength="50"></td>
							<td class="titulo_campo">Respuesta</td>
							<td class="data_campo"><input type="text" id="answer"
								name="answer" maxlength="20"></td>
						</tr-->
						<tr class="tr_separador">
							<td>&nbsp;<input type="hidden" id="nuevo" name="nuevo" value=""></td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<div id="consultar_usuario" style="visibility:visible;display:inline">
								<button type="button" class="btn btn-primary btn"
									onclick="consultarUsuario();">
									Consultar
								</button>
								<c:if test='${rol=="Administrador" or rol=="Gerente"}'>
								&nbsp;&nbsp;
								<button type="button" class="btn btn-default btn"
									onclick="subirListaUsuarios();">
									Subir lista
								</button>
								</c:if>
								</div>
								<div id="guardar_usuario" style="visibility:hidden;display:inline">
									<button type="button" class="btn btn-primary btn" onclick="guardarUsuario();">
										Guardar
									</button>
								</div>
							</td>
						</tr>
						
						<tr class="tr_separador">
							<td></td>
						</tr>
						</table>
						<div class="tab-pane" id="password">
						<table class="tabla_formulario" cellspacing="2">
							<tr class="tr_formulario">
								<td class="titulo_campo">Clave</td>
								<td class="data_campo">
									<c:choose>
										<c:when test='${rol=="Gerente"}'>
											<input type="text" id="clave" name="clave" maxlength="4" />
										</c:when>
										<c:otherwise>
											<input type="hidden" id="clave" name="clave" />
										</c:otherwise>
									</c:choose>	
									
										<button type="button" class="btn btn-default btn"
										onclick="setPassword();">
										Cambiar Clave
									</button></td>
								<td align="center" colspan="2">&nbsp;</td>
							</tr>
						</table>
						</div>
				</form>
				</div>
							<div class="tab-pane" id="consulta_empresa">
				<form action="" id="frme">
					<br>
					<table class="tabla_formulario" cellspacing="2" border=0>
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Rut Empresa :</td>
							<td class="data_campo"><input type="text" id="rutempresa"
								name="rutempresa" maxlength="13"
								onBlur="validarRutIngresado(this.id,this.value);"
								onKeyPress="keyRut();" 
								onKeyUp="formateaRut(this)" />
							</td>
							<td width="200px">
								&nbsp;<button type="button" class="btn btn-default btn"
									onclick="limpiaCampos('consulta_empresa');">
									Limpiar
								</button>
								<c:if test='${rol=="Agente"}'>
								&nbsp;&nbsp;
								<div id="consultar_encargados" style="visibility:visible;display:inline">
								
								<button type="button" class="btn btn-info btn"
									onclick="consultarEncargados();">
									Ver Encargados
								</button>
								</div>
								</c:if>
							</td>
							<td align="left">
							&nbsp;
								<div id="consulta_empresa_filtroon"><a href="#" onclick="cargarFiltro('filtroAppRol');">++ Filtro Avanzado</a></div>
								<div id="consulta_empresa_filtrooff"><a href="#" onclick="ocultarFiltro('filtroAppRol');">-- Filtro Avanzado</a></div>
							</td>
							<!-- td colspan=2><div id="observaciones" style="color: #ff0000;font-family: Arial,Verdana;font-size: 10"></div></td-->
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Razón Social</td>
							<td class="data_campo" colspan="2" align="left"><input type="text" id="razonSocialEmpresa"
								name="razonSocialEmpresa" maxlength="50" size="74"></td>
							<td width="150">&nbsp;</td>
						</tr>
	

						<tr class="tr_separador">
							<td><td>&nbsp;<input type="hidden" id="nuevoe" name="nuevoe" value=""></td></td>
						</tr>
						<tr class="tr_formulario">
						<td colspan=3>
						<div id="agregar_encargado" style="visibility:hidden;display:inline">
							<table>
							<tr>
							<td class="titulo_campo">Ingrese RUT Encargado :</td>
							<td class="data_campo" align="left"><input type="text" id="rut_encargado"
								name="rut_encargado" maxlength="13"
								onBlur="validarRutIngresado(this.id,this.value);"
								onKeyPress="keyRut();" 
								onKeyUp="formateaRut(this)"></td>
							<td width="200">&nbsp;<button type="button" class="btn btn-primary btn"
									onclick="addUsuarioRolEmp($('#rut_encargado').val(), 'N');">Crear</button>&nbsp;&nbsp;
									<button type="button" class="btn btn-default btn"
									onclick="addUsuarioRolEmp($('#rut_encargado').val(), 'E');">Eliminar</button>
							</td>
							</tr>
							</table>
						</div>
						</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr>
							<td align="center" colspan="4">
							<div id="consultar_empresa" style="visibility:visible;display:inline">
								<button type="button" class="btn btn-primary btn"
									onclick="consultarEmpresa();"> 
									Consultar
								</button>
							</div>							
							<div id="guardar_empresa" style="visibility:hidden;display:inline">
								<button type="button" class="btn btn-default btn"
									onclick="guardarEmpresa();">
									Guardar
								</button>
							</div>
							</td>
						</tr>
					</table>
				</form>
				</div>
				<div class="tab-pane" id="consulta_anexo">
				<form action="" id="frme">
					<br>
					<table class="tabla_formulario" cellspacing="2" border=0>
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Rut Empresa :</td>
							<td class="data_campo"><input type="text" id="rutanexo"
								name="rutanexo" maxlength="13"
								onBlur="validarRutIngresado(this.id,this.value);"
								onKeyPress="keyRut();" 
								onKeyUp="formateaRut(this)" />
							</td>
							<td>
								&nbsp;<button type="button" class="btn btn-default btn"
									onclick="limpiaCampos('consulta_anexo');">
									Limpiar
								</button>
								&nbsp;
								<div id="consultar_encargados_anexo" style="visibility:hidden;display:inline">
								<button type="button" class="btn btn-primary btn"
									onclick="consultarEncargadosAnexo();"> 
									Ver Encargados
								</button>
							</div>
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Razón Social</td>
							<td class="data_campo" colspan="2" align="left"><input type="text" id="razonSocialAnexo"
								name="razonSocialAnexo" maxlength="50" size="74"></td>
							<td width="150">&nbsp;</td>
						</tr>
	

						<tr class="tr_separador">
							<td><td>&nbsp;<input type="hidden" id="nuevoanx" name="nuevoanx" value=""></td></td>
						</tr>
						
						<tr class="tr_formulario">
						<td colspan=3>
						<div id="agregar_encargado_anexo" style="visibility:hidden;display:inline">
							<table>
							<tr>
							<td class="titulo_campo">Ingrese RUT Encargado :</td>
							<td class="data_campo" align="left"><input type="text" id="rut_encargado_anexo"
								name="rut_encargado_anexo" maxlength="13"
								onBlur="validarRutIngresado(this.id,this.value);"
								onKeyPress="keyRut();" 
								onKeyUp="formateaRut(this)"></td>
							<td width="200">&nbsp;<button type="button" class="btn btn-primary btn"
									onclick="addUsuarioAnexo($('#rut_encargado_anexo').val(), 'N');">Crear</button>&nbsp;&nbsp;
									<button type="button" class="btn btn-default btn"
									onclick="addUsuarioAnexo($('#rut_encargado_anexo').val(), 'E');">Eliminar</button>
							</td>
							</tr>
							</table>
						</div>
						</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
							<div id="consultar_anexo" style="visibility:visible;display:inline">
								<button type="button" class="btn btn-primary btn"
									onclick="consultarAnexo();"> 
									Consultar
								</button>
							</div>
							<div id="guardar_anexo" style="visibility:hidden;display:inline">
								<button type="button" class="btn btn-default btn"
									onclick="guardarAnexo();">
									Guardar
								</button>
							</div>
							</td>
						</tr>
					</table>
				</form>
				</div>
				<c:if test='${rol=="Administrador"}'>
				<div class="tab-pane" id="consulta_pac">
				<form id="fupload_rol" action="UploadServlet" name="fupload_rol" method="post" enctype="multipart/form-data">
					<br>
					<table border=0 class="tabla_formulario" cellspacing="2">
						
						<tr class="tr_separador">
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="consultarUsuAppRoles();">
									Consultar
								</button>
							</td>
						</tr>
						<tr class="tr_formulario">
							<td>
								<div id="usuarios_approl" class="container"></div>
								<input type="hidden" name="appid_rol" id="appid_rol" value="" />
								<input type="hidden" name="rolid_rol" id="rolid_rol" value="" />
							</td>
						
						</tr>
					</table>
				</form>
				</div>
				<div class="tab-pane" id="auditoria">
				<form id="auditoria" action="" name="auditoria" method="post">
					<br>
					<table border=0 class="tabla_formulario" cellspacing="2">
						
						<tr class="tr_formulario">
							<td class="titulo_campo" width="200">Ingrese Rango :</td>
							<td class="data_campo">
								<select id="diasAuditoria" name="diasAuditoria">
									<option value="0" selected="selected">Durante el día</option>
									<option value="7" >Última semana</option>
									<option value="30" >Último mes</option>
								</select>
							</td>
							<td >&nbsp;</td>
						</tr>
						<tr class="tr_separador"><td></td></tr>
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="descargarAuditoria();">
									Descargar
								</button>
							</td>
						</tr>
					</table>
				</form>
				</div>
				</c:if>
				<c:if test='${rol=="Gerente"}'>
				<div class="tab-pane" id="consulta_roles">
				<form id="fupload_rol" action="UploadServlet" name="fupload_rol" method="post" enctype="multipart/form-data">
					<br>
					<table border=0 class="tabla_formulario" cellspacing="2">
						
						<tr class="tr_separador">
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="consultarUsuAppRoles();">
									Consultar
								</button>
							</td>
						</tr>
						<tr class="tr_formulario">
							<td>
								<div id="usuarios_approl" class="container"></div>
								<input type="hidden" name="appid_rol" id="appid_rol" value="" />
								<input type="hidden" name="rolid_rol" id="rolid_rol" value="" />
							</td>
						
						</tr>
					</table>
				</form>
				</div>
				</c:if>
				<div class="tab-pane" id="util_ldap">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Username</td>
							<td class="data_campo"><input type="text" id="usuario"
								name="usuario" maxlength="12"></td>
							<td class="titulo_campo">Password</td>
							<td class="data_campo"><input type="text" id="contra"
								name="contra" maxlength="50"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr>
							<td class="titulo_campo">Codificación</td>
							<td class="data_campo">Base 64&nbsp;<input type="radio" id="base_64"
								name="base" value="64" checked="checked">&nbsp;&nbsp;Base 128&nbsp;<input type="radio" id="base_128"
								name="base" value="128" ></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="encode();">
									Encode
								</button>
							</td>
							<td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="decode();">
									Decode
								</button>
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Credential</td>
							<td class="data_campo" colspan="3"><input type="text"  id="credential"
								name="credential" size="110"></td>
							

						</tr>
					</table>
				</div>
			</div>
		</div>


		<script type="text/javascript">
			jQuery(document).ready(function($) {
				// $('#tabs').tab();
			});
		</script>

		<div id="footer_old" class="texto_footer">
			La Araucana CCAF @2021
			
		</div>
	</div>
	<!-- container -->


	<script type="text/javascript" src="dist/js/bootstrap.js"></script>
	<div id="cargando">
		<div id="imagen">
			<img src="img/cargando_big.gif" width="100px"></img>
		</div>
		<div id="texto_cargando"></div>

	</div>
	<div id="detalleconsulta" title="Detalle Consulta" style="display:none;">
	<form  id="fupload" action="UploadServlet" name="fupload" method="post" enctype="multipart/form-data">
		<div id="detalle"></div>
		<input type="hidden" name="appid" id="appid" value="" />
		<input type="hidden" name="rolid" id="rolid" value="" />
		<input type="hidden" name="userid" id="userid" value="" />
		<input type="hidden" name="rutemp" id="rutemp" value="" />
		<input type="hidden" name="estado" id="estado" value="" />
	</form>
	<script>
	$(document).ready(function()
	{
 		$('#fupload').submit(function(e) {
 			e.preventDefault();
 			uploadFile('fupload');
    	});
    	$('#fupload_rol').submit(function(e) {
 			e.preventDefault();
 			uploadFile('fupload_rol');
    	});
    	
 
	});
	</script>
	</div>
	<div id="estadisticas" title="Estadísticas Integrador" style="display:none;">
		<div id="tabla_stats"></div>
	</div>	
</body>
</html>
