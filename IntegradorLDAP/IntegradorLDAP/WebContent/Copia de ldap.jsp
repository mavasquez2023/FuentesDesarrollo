<!DOCTYPE html>

<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
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
			$("td").corner("8px");
			$("input").corner("4px");
			$(".borde_redondeado").corner("6px");
			$(".informacion").corner("6px");
			$(".informacion_error").corner("6px");
			$(".informacion_warning").corner("6px");
			consultarApps();
			estadoCronta('');
			//consultarRoles();
			cargarValores('pendientes');
			verPendientes(true);
		});
		
		function Salir(destino){
			window.location=path + "/Salir?destino=" + destino;
		}

	</script>

	<div class="container">

		<div id="header">
			<div id="logo"></div>
			<div id="bottom_header"></div>
			<div id="salir" align="right"><span class="salir">${user}&nbsp;</span><a href="#" onclick="Salir('logout.jsp');"><span class="glyphicon glyphicon-off"></span></a></div>
			<div id="informacion" class="informacion"></div>
			<div id="informacion_error" class="informacion"></div>
			<div id="informacion_warning" class="informacion"></div>
		</div>

		<div id="div_ingreso">
			<table style="margin-left: 30px;">
				<tr>
					<td width="160px" class="mi_tab" id="tab_pendientes"
						onclick="cargarValores('pendientes');verPendientes(true);">Pendientes DB2 <span class="glyphicon glyphicon-time"></span>
					</td>
					<td width="220px" class="mi_tab" id="tab_consulta"
						onclick="cargarValores('consulta')">Consulta Usuario LDAP <span class="glyphicon glyphicon-search"></span></td>
					<td width="220px" class="mi_tab" id="tab_consulta_empresa"
						onclick="cargarValores('consulta_empresa')">Consulta Empresa LDAP <span class="glyphicon glyphicon-search"></span></td>
					<td width="220px" class="mi_tab" id="tab_consulta_roles"
						onclick="cargarFiltro('filtroAppRol');cargarValores('consulta_roles')">Consulta App-Roles LDAP <span class="glyphicon glyphicon-search"></span></td>
				</tr>
			</table>

			<div id="my-tab-content" class="tab-content"
				style="padding-left: 30px;">
				<div class="tab-pane active" id="pendientes">
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
							<td>&nbsp;<a href="#" onclick="verPendientes(true);"><span class="glyphicon glyphicon-refresh"></span></a></td>
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
									<span class="glyphicon glyphicon-step-forward"></span> 
									Procesar Pendientes
								</button>
							</td>
							<td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="reinyectarErroneos();">
									<span class="glyphicon glyphicon-repeat"></span>
									Reinyectar Erroneos
								</button>
							</td>
						</tr>
					</table>
				</div>
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
								<select id="roles" name="roles">
									<option value="" selected="selected">Seleccione</option>
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div class="tab-pane" id="consulta">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Rut :</td>
							<td class="data_campo"><input type="text" id="username"
								name="username" maxlength="12"
								onchange="validarRutIngresado(this.id,this.value);"
								onfocus="limpiaCampos('consulta');">
							</td>
							<td colspan=2 align="left">
								<div id="consulta_filtroon"><a href="#" onclick="cargarFiltro('filtroAppRol');"><span class="glyphicon glyphicon-plus"> Filtro Avanzado</span></a></div>
								<div id="consulta_filtrooff"><a href="#" onclick="ocultarFiltro('filtroAppRol');"><span class="glyphicon glyphicon-minus"> Filtro Avanzado</span></a></div>
							</td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Nombres</td>
							<td class="data_campo"><input type="text" id="nombres"
								name="nombres" maxlength="30"></td>
							<td></td>
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Apellido Paterno</td>
							<td class="data_campo"><input type="text"
								id="apellido_paterno" name="apellido_paterno"
								maxlength="30"></td>
							<td class="titulo_campo">Apellido Materno</td>
							<td class="data_campo"><input type="text"
								id="apellido_materno" name="apellido_materno"
								maxlength="30"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Tel&eacute;fono</td>
							<td class="data_campo"><input type="text" id="telefono"
								name="telefono" maxlength="12"></td>
							<td class="titulo_campo">Email</td>
							<td class="data_campo"><input type="text" id="email"
								name="email" maxlength="50"></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Sexo</td>
							<td class="data_campo"><input type="text"
								id="sexo" name="sexo" maxlength="1"></td>
							<td class="titulo_campo">Estado</td>
							<td class="data_campo"><input type="text" id="estado"
								name="estado" maxlength="50"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="consultarUsuario();">
									<span class="glyphicon glyphicon-search"></span>
									Consultar
								</button>
							</td>
							<!--  td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="guardarCambiosFormulario('consulta');cargarValores('formato_nomina')">
									<span class="glyphicon glyphicon-step-forward"></span> Guardar
								</button>
							</td-->
						</tr>
					</table>

				</div>
				<div class="tab-pane" id="consulta_empresa">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						<tr class="tr_formulario">
							<td class="titulo_campo">Ingrese Rut Empresa :</td>
							<td class="data_campo"><input type="text" id="rutempresa"
								name="rutempresa" maxlength="12"
								onchange="validarRutIngresado(this.id,this.value);"
								onfocus="limpiaCampos('consulta_empresa');">
							</td>
							<td colspan=2 align="left">
								<div id="consulta_empresa_filtroon"><a href="#" onclick="cargarFiltro('filtroAppRol');"><span class="glyphicon glyphicon-plus"> Filtro Avanzado</span></a></div>
								<div id="consulta_empresa_filtrooff"><a href="#" onclick="ocultarFiltro('filtroAppRol');"><span class="glyphicon glyphicon-minus"> Filtro Avanzado</span></a></div>
							</td>
							<!-- td colspan=2><div id="observaciones" style="color: #ff0000;font-family: Arial,Verdana;font-size: 10"></div></td-->
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Razon Social</td>
							<td class="data_campo"><input type="text" id="razonSocialEmpresa"
								name="razonSocialEmpresa" maxlength="30"></td>
							<td class="titulo_campo">Afiliada</td>
							<td class="data_campo">Si&nbsp;<input type="radio" id="afiliada_1"
								name="afiliada" value="1" >&nbsp;&nbsp;No&nbsp;<input type="radio" id="afiliada_0"
								name="afiliada" value="0" ></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Holding (dn)</td>
							<td class="data_campo"><input type="text" id="holding"
								name="holding" maxlength="30"></td>
							<td class="titulo_campo">Tipo</td>
							<td class="data_campo"><input type="text"
								id="tipo" name="tipo" maxlength="1"></td>
							<!-- >td class="titulo_campo">Rut Empresa Líder</td>
							<td class="data_campo"><input type="text" id="rutEmpresaLider"
								name="rutEmpresaLider" maxlength="30"></td-->
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Código Act. Económica</td>
							<td class="data_campo"><input type="text"
								id="codigoActividadEconomica" name="codigoActividadEconomica"
								maxlength="30"></td>
							<td class="titulo_campo">Actividad Económica</td>
							<td class="data_campo"><input type="text"
								id="actividadEconomica" name="actividadEconomica"
								maxlength="30"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Dirección</td>
							<td class="data_campo"><input type="text" id="direccion"
								name="direccion" maxlength="12"></td>
							<td class="titulo_campo">Comuna</td>
							<td class="data_campo"><input type="text" id="comuna"
								name="comuna" maxlength="50"></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Ciudad</td>
							<td class="data_campo"><input type="text"
								id="ciudad" name="ciudad" maxlength="1"></td>
							<td class="titulo_campo">Region</td>
							<td class="data_campo"><input type="text" id="region"
								name="region" maxlength="50"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Rut Representante Legal</td>
							<td class="data_campo"><input type="text" id="rutRepLegal"
								name="rutRepLegal" maxlength="12"></td>
							<td class="titulo_campo">Nombre Rep. Legal</td>
							<td class="data_campo"><input type="text" id="nombreRepLegal"
								name="nombreRepLegal" maxlength="50"></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Ap. Paterno Rep. Legal</td>
							<td class="data_campo"><input type="text"
								id="apellidoPaternoRepLegal" name="apellidoPaternoRepLegal" maxlength="1"></td>
							<td class="titulo_campo">Ap. Materno Rep. Legal</td>
							<td class="data_campo"><input type="text" id="apellidoMaternoRepLegal"
								name="apellidoMaternoRepLegal" maxlength="50"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr class="tr_formulario">
							<td class="titulo_campo">Teléfono</td>
							<td class="data_campo"><input type="text" id="fono"
								name="fono" maxlength="12"></td>
							<td class="titulo_campo">Email</td>
							<td class="data_campo"><input type="text" id="email"
								name="email" maxlength="50"></td>
						</tr>
						<tr class="tr_separador">
							<td></td>
						</tr>
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="consultarEmpresa();">
									<span class="glyphicon glyphicon-search"></span>
									Consultar
								</button>
							</td>
							<!--  td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="guardarCambiosFormulario('consulta');cargarValores('formato_nomina')">
									<span class="glyphicon glyphicon-step-forward"></span> Guardar
								</button>
							</td-->
						</tr>
					</table>
				</div>
				
				<div class="tab-pane" id="consulta_roles">
					<br>
					<table class="tabla_formulario" cellspacing="2">
						
						<tr class="tr_separador">
							<td></td>
						</tr>
						
						<tr>
							<td align="center" colspan="4">
								<button type="button" class="btn btn-default btn"
									onclick="consultarUsuAppRoles();">
									<span class="glyphicon glyphicon-search"></span>
									Consultar
								</button>
							</td>
						</tr>
						<tr>
							<td><div id="usuarios_approl"></div></td>
							<!--  td align="center" colspan="2">
								<button type="button" class="btn btn-default btn"
									onclick="guardarCambiosFormulario('consulta');cargarValores('formato_nomina')">
									<span class="glyphicon glyphicon-step-forward"></span> Guardar
								</button>
							</td-->
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

		<div id="footer" class="texto_footer">
			Una innovaci&oacute;n tecnol&oacute;gica de Caja De
			Compensaci&oacute;n
			<div id="logo_araucana"></div>
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
		<div id="detalle"></div>
	</div>
	<div id="estadisticas" title="Estadísticas Integrador" style="display:none;">
		<div id="tabla_stats"></div>
	</div>	
</body>
</html>
