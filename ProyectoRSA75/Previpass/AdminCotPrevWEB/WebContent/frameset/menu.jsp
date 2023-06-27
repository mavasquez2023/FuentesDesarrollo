<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/frameset/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function doLogout() 
		{
			document.getElementById("logoutForm").submit();
			parent.BODY.location.reload();
			parent.titulo.location.reload();
			parent.menu.location.reload();
			parent.user.location.reload();
		}
		
var listaMenu = new Array();
var listaSubMenu = new Array();
var listaLinks = new Array();

	listaMenu.push("admin");
		listaSubMenu.push("subadmin");
			listaLinks.push("encargado");
			listaLinks.push("admine");
			listaLinks.push("transPerm");
			listaLinks.push("empresas");
			listaLinks.push("grupos");
			listaLinks.push("nominas");
			listaLinks.push("estCl");
			listaLinks.push("conProc");
			listaLinks.push("conOper");
			listaLinks.push("comPago");
			listaLinks.push("comSPL");
			listaLinks.push("repoMens");
			listaLinks.push("archConti");
			listaLinks.push("cargArchi");
			listaLinks.push("notSPL");
			listaLinks.push("correoSISE");

	listaMenu.push("config");
		listaSubMenu.push("subconfig");
			listaLinks.push("estadisticas");
			listaLinks.push("entidades");
			listaLinks.push("estruc");
			listaLinks.push("nodos");
			listaLinks.push("periodo");
			listaLinks.push("bancos");
			listaLinks.push("errores");
			listaLinks.push("parametros");
			listaLinks.push("mapeoTes");
			listaLinks.push("concepTes");
			listaLinks.push("aviso");
			listaLinks.push("calendario");

function initMenu(accion, subAccion)
{
	despliega("sub" + accion, subAccion);
	resalta(accion)
}

function despliega(target, subAccion)
{
    for (i = 0; i < listaSubMenu.length; i++)
    {
		if (target == listaSubMenu[i])
	  			document.getElementById(target).style.display = "block";
    	else
	    	document.getElementById(listaSubMenu[i]).style.display = "none";
   	}
   	if (subAccion != "")
    	resalta2(subAccion);
}

function resalta(target)
{
    for (i = 0; i < listaMenu.length; i++)
    {
		if (target == listaMenu[i])
	      	    document.getElementById(target).style.color = "#ff8a00";
		else
		    document.getElementById(listaMenu[i]).style.color = "#FFFFFF";
    }
}

function resalta2(target)
{
    for (i = 0; i < listaLinks.length; i++)
    {
		if (target == listaLinks[i])
		    document.getElementById(target).style.color = "#ff8a00";
		else if (listaLinks[i] != "")
	    	document.getElementById(listaLinks[i]).style.color = "#000000";
    }
}
</script>
</HEAD>
<body onload="initMenu('admin', 'encargado');">
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/frameset/menu.jsp">
</form>
<table width="180" border="0" cellspacing="0" cellpadding="0">
<!--   ALDO -->
	<tr> 
		<td width="170" class="fondomenu" height="20">
   			<a href="/AdminCotPrevWEB/ListarUsuarios.do?limpiaPath=" onclick="initMenu('admin','encargado');" class="textoMenu" id="admin" target="BODY">
   				Administraci&oacute;n
   			</a>
		</td>
	</tr>
<!-- ADMINISTRACION -->
	<tr id="subadmin" style="display: none;">
		<td>
			<table width="153" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>	
								<td>
									<a href="/AdminCotPrevWEB/ListarUsuarios.do?limpiaPath=" target="BODY" class="submenu" id="encargado" onclick="initMenu('admin','encargado');">
										Encargado
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListarAdministrador.do?limpiaPath=" target="BODY"  class="submenu" id="admine" onclick="initMenu('admin','admine');">
										Administradores
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menuR">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/TransferirPermisos.do?limpiaPath=" target="BODY"  class="submenu" id="transPerm" onclick="initMenu('admin','transPerm');">
										Transferir Permisos
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="EMPR">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListarEmpresas.do?limpiaPath=" target="BODY"  class="submenu" id="empresas" onclick="initMenu('admin','empresas');">
										Empresas
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="GRUP">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListarGruposConvenio.do?limpiaPath=" target="BODY"  class="submenu" id="grupos" onclick="initMenu('admin','grupos');">
										Grupos de Convenios
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="NOMI">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/NominaXestadoBuscar.do?accion=listar&limpiaPath=" target="BODY"  class="submenu" id="nominas" onclick="initMenu('admin','nominas');">
										N&oacute;minas
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="DESP">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListarEstrCliente.do?limpiaPath=" target="BODY"  class="submenu" id="estCl" onclick="initMenu('admin','estCl');">
										Estructura Clientes
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="INCP">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ControlProcesosListar.do?limpiaPath=" target="BODY"  class="submenu" id="conProc" onclick="initMenu('admin','conProc');">
										Control de Procesos
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="INCO">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ControlOperacionListar.do?limpiaPath=" target="BODY"  class="submenu" id="conOper" onclick="initMenu('admin','conOper');">
										Control de Operaciones
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ComprobPagoListar.do?limpiaPath=" target="BODY"  class="submenu" id="comPago" onclick="initMenu('admin','comPago');">
										Comprobantes Pago
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ComprobSplListar.do?limpiaPath=" target="BODY"  class="submenu" id="comSPL" onclick="initMenu('admin','comSPL');">
										Comprobantes SPL
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/InitReporteMensual.do?limpiaPath=" target="BODY"  class="submenu" id="repoMens" onclick="initMenu('admin','repoMens');">
										Reporte AFP Mensual
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/InitArchivoContigencia.do?limpiaPath=" target="BODY"  class="submenu" id="archConti" onclick="initMenu('admin','archConti');">
										Contingencia AFP
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/InitCargaArchivo.do?limpiaPath=" target="BODY"  class="submenu" id="cargArchi" onclick="initMenu('admin','cargArchi');">
										Carga AFP Contingencia
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/notificacionSPL.do?limpiaPath=" target="BODY"  class="submenu" id="notSPL" onclick="initMenu('admin','cargArchi');">
										Notificación SPL
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/correoSISEmpresa.do?limpiaPath=" target="BODY"  class="submenu" id="correoSISE" onclick="initMenu('admin','correoSISE');">
										Correo SIS Empresa
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr> 
					<td><img src="<c:url value="/frameset/vde_bas.gif" />" width="153" height="7" border="0"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr> 
   		<td height="7"><img src="<c:url value="/img/space.gif" />" width="7" height="7"></td>
	</tr>
<!-- CONFIGURACION -->
	<tr>
		<td width="170" class="fondomenu" height="20">
   			<a href="/AdminCotPrevWEB/ListaEntidadesFicha.do?limpiaPath=" onclick="initMenu('config','entidades');" class="textoMenu" id="config" target="BODY">
   				Configuraci&oacute;n
   			</a>
		</td>
	</tr>			
	<tr id="subconfig" style="display: none;">
		<td>
			<table width="153" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top" class="vde-menu" id="ENTI">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListaEntidadesFicha.do?limpiaPath=" target="BODY"  class="submenu" id="entidades" onclick="initMenu('config','entidades');">
										Entidades
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
 				<tr>
					<td valign="top" class="vde-menu" id="ESTR">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/MovPersonalListar.do?limpiaPath=" target="BODY"  class="submenu" id="estruc" onclick="initMenu('config','estruc');">
										Estructuras
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<!-- 
				<tr>
					<td valign="top" class="vde-menu" id="PARA">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListarParametros.do" target="BODY"  class="submenu" id="param" onclick="initMenu('config','param');">
										Par&aacute;metros
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				 -->
				<tr>
					<td valign="top" class="vde-menu" id="NODO">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/listarNodo.do?limpiaPath=" target="BODY"  class="submenu" id="nodos" onclick="initMenu('config','nodos');">
										Nodos
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="ESTA">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/estadisticas.do?limpiaPath=" target="BODY"  class="submenu" id="estadisticas" onclick="initMenu('config','estadisticas');">
										Estad&iacute;sticas
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="ICPE">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/Periodo.do?limpiaPath=" target="BODY"  class="submenu" id="periodo" onclick="initMenu('config','periodo');">
										Cierre/Inicio Per&iacute;odo
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="BANC">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListaBanco.do?limpiaPath=" target="BODY"  class="submenu" id="bancos" onclick="initMenu('config','bancos');">
										Bancos
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="CERR">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ErroresListar.do?limpiaPath=" target="BODY"  class="submenu" id="errores" onclick="initMenu('config','errores');">
										Clasificaci&oacute;n de Errores
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="PRMT">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ListaParametros.do?limpiaPath=" target="BODY"  class="submenu" id="parametros" onclick="initMenu('config','parametros');">
										Par&aacute;metros
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="MAPT">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/MapeoTesoreriaListar.do?limpiaPath=" target="BODY"  class="submenu" id="mapeoTes" onclick="initMenu('config','mapeoTes');">
										Mapeo Tesorer&iacute;a
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top" class="vde-menu" id="CONT">
						<table width="100%" border="0" cellspacing="3" cellpadding="0">
							<tr>
								<td>
									<a href="/AdminCotPrevWEB/ConceptoTesoListar.do?limpiaPath=" target="BODY"  class="submenu" id="concepTes" onclick="initMenu('config','concepTes');">
										Concepto Tesorer&iacute;a
									</a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
                    <td valign="top" class="vde-menu" id="AVIS">
                        <table width="100%" border="0" cellspacing="3" cellpadding="0">
                            <tr>
                                <td>
									<a href="/AdminCotPrevWEB/ListarAvisos.do?limpiaPath=" target="BODY"  class="submenu" id="aviso" onclick="initMenu('config','aviso');">
										Avisos de Usuario
									</a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td valign="top" class="vde-menu" id="CALE">
                        <table width="100%" border="0" cellspacing="3" cellpadding="0">
                            <tr>
                                <td>
									<a href="/AdminCotPrevWEB/EditarCalendario.do?limpiaPath=" target="BODY"  class="submenu" id="calendario" onclick="initMenu('config','calendario');">
										Calendario
									</a>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
				<tr> 
					<td><img src="<c:url value="/frameset/vde_bas.gif" />" width="153" height="7" border="0"/></td>
				</tr>
			</table>
		</td>
	</tr>
	<!-- FIN ALDO -->
</table>

<script type="text/javascript">
	var MENU_ACTIVO = "USUA";

	function setMenuActivo(obj) 
	{	
		MENU_ACTIVO = obj.id.substr(0, obj.id.length - 4);
	}
</script>
</body>
</HTML>