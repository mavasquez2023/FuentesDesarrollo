<%@ include file="/html/comun/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<!-- HTTP 1.1 -->
<meta http-equiv="Cache-Control" content="no-store" />
<!-- HTTP 1.0 -->
<meta http-equiv="Pragma" content="no-cache" />
<!-- Prevents caching at the Proxy Server -->
<meta http-equiv="Expires" content="-1" />
<meta name="author"
	content="Builderhouse Ingenieros (http://www.builderhouse.cl)" />
<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet"
	type="text/css">
<script src="<c:url value='/js/comun.js'/>"></script>
<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
 <script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body onLoad="foco();">

<html:form action="/NominasTrabajadoresVer" styleId="formulario">
	<html:hidden property="convenioId" />
	<html:hidden property="rutEmpresa" />
	<html:hidden property="tipoNominaId" styleId="tipoNominaId" />
	<html:hidden styleId="b_nombre" property="b_nombre" />
	<html:hidden styleId="b_apellido" property="b_apellido" />
	<html:hidden styleId="b_rut" property="b_rut" />
	<html:hidden property="rutEmpresaBuscar" styleId="rutEmpresaBuscar"/>
	<html:hidden property="grupoConvenioBuscar" styleId="grupoConvenioBuscar"/>
	<html:hidden property="tipoNominaIdBuscar" styleId="tipoNominaIdBuscar"/>
	<html:hidden property="estadoIdBuscar" styleId="estadoIdBuscar"/>	

	<c:set var="estilo" value="textos-formcolor2" />
	<c:set var="count" value="0" />
	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				<html:errors/>
			</td>
		</tr>
		<tr>
			<td>
				<html:messages id="msg" message="true">
					<div class="msgExito">${msg}</div>
				</html:messages>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="tabla-datos">
				<tr>
					<td width="15%"><strong>RUT:</strong></td>
					<td width="20%" align="right"><c:out value="${NominaForm.empresaRut}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><strong>Empresa:</strong></td>
					<td><c:out value="${NominaForm.empresaNombre}"></c:out></td>
				</tr>
				<tr>
					<td width="15%"><strong>Id Convenio</strong></td>
					<td width="20%" align="right"><c:out value="${NominaForm.convenioId}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td><strong>Tipo Proceso: </strong></td>
					<td><c:out value="${NominaForm.nombreProceso}"></c:out></td>
				</tr>
				<tr>
					<td height="4" colspan="4" bgcolor="#85b4be"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td valign="top">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="1">
						<tr valign="bottom">
							<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Ver
							trabajadores</strong></td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="tabla-datos">
						<tr align="left">
							<td width="20%"><strong>RUT trabajador:</strong></td>
							<td><html:text property="trabajadorRut" styleId="trabajadorRut" size="15" maxlength="15" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);" styleClass="campos" />
							</td>
							<td width="40%" colspan="2">&nbsp;</td>
						</tr>
						<tr  align="left">
							<td width="20%"><strong>Nombre:</strong></td>
							<td><html:text property="trabajadorNombre" size="32" maxlength="21" onblur="javascript:soloNombre(this);" onkeyup="javascript:soloNombre(this);" styleId="trabajadorNombre"
								styleClass="campos" /></td>
							<td width="20%"><strong>Apellidos:</strong></td>
							<td><html:text property="trabajadorApPaterno" styleId="trabajadorApPaterno"
								styleClass="campos" size="32" maxlength="21" onblur="javascript:soloNombre(this);" onkeyup="javascript:soloNombre(this);" /></td>
						</tr>
						<tr>
							<td colspan="4" align="right"><html:button
								property="operacion" styleClass="btn3" value="Buscar"
								onclick="javascript:listar(document.forms['NominaForm']);" ></html:button></td>
						</tr>
						<tr>
							<td height="4" colspan="4" bgcolor="#85b4be"></td>
						</tr>
					</table>

					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table>
				<tr valign="bottom">
					<td width="100%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Listado</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<logic:present name="NominaForm" property="trabajadores">
			<tr align="center">
				<td valign="top">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="1" bordercolor="#CCCCCC">
					<tr class="subtitulos_tablas" align="center" valign="middle">
						<td width="86" bordercolor="#CCCCCC" class="barra_tablas"><%@ include file="/html/comun/flecha.jspf"%> RUT</td>
						<td width="110" bordercolor="#FFFFFF" class="barra_tablas">Nombres</td>
						<td width="110" bordercolor="#FFFFFF" class="barra_tablas">Apellidos</td>
						<td width="110" bordercolor="#FFFFFF" class="barra_tablas">Sucursal</td>
						<td colspan="3" bordercolor="#FFFFFF" class="barra_tablas"
							style="text-align:center">Acci&oacute;n</td>
					</tr>
					<logic:present name="NominaForm" property="trabPendientes">
						<logic:iterate id="cotizante" name="NominaForm" property="trabPendientes">
							<c:choose>
								<c:when test="${count % 2 == 0}">
									<c:set var="estilo" value="textos_formularios" />
								</c:when>
								<c:otherwise>
									<c:set var="estilo" value="textos-formcolor2" />
								</c:otherwise>
							</c:choose>
							<c:set var="count" value="${count + 1}" />
							<tr valign="top" align="center">
								<td height="20" style="text-align:right" nowrap="nowrap" class="${estilo}">${cotizante.rut}</td>
								<td align="left" class="${estilo}">${cotizante.nombre}&nbsp;</td>
								<td align="left" class="${estilo}">${cotizante.apellidos}&nbsp;</td>
								<td class="${estilo}">&nbsp;${cotizante.sucursal}&nbsp;</td>
								<td class="${estilo}" valign="middle">
						       		<div class="textos_resaltados" align="center" title="Trabajador con errores en su Cotizaci&oacute;n">
						       			<c:choose>
							   		    	<c:when test="${cotizante.nivelError == 0}"><div class="mensaje_error">Error</div></c:when>
							   		    	<c:when test="${cotizante.nivelError == 1}"><div class="mensaje_aviso">Aviso</div></c:when>
							   				<c:otherwise>&nbsp;</c:otherwise>
							   		  	</c:choose>
						       		</div>
								</td>
								<td class="${estilo}" valign="middle">
								<div align="center"><html:link action="/VerCotizacion.do">
								<html:param name="rutEmpresa" value="${cotizante.rutEmpresa}"></html:param>								
								<html:param name="idConvenio" value="${cotizante.idConvenio}"></html:param>
								<html:param name="tipoProceso" value="${cotizante.tipoProceso}"></html:param>
								<c:choose>
								<c:when test="${cotizante.idCotizPendiente == 0}">
								<html:param name="idCotizante" value="${cotizante.idCotizante}"></html:param>
								</c:when>
								<c:when test="${cotizante.idCotizPendiente != 0}">
								<html:param name="idCotizPend" value="${cotizante.idCotizPendiente}"></html:param>
								</c:when>
								</c:choose>
								<html:param name="operacion" value="mod"></html:param>
								<html:param name="accion" value="inicio"></html:param>
								<html:param name="subAccion" value="trabajadores"></html:param>
								<html:param name="subSubAccion" value="trabajadorEditar"></html:param>
	
									<img src="<c:url value="/img/iconos/lupa.gif" />" alt="Ver detalle"
										width="14" height="13" border="0" title="Ver detalle" />
								</html:link></div>
								</td>
								<td class="${estilo}" valign="middle">
								<div align="center">&nbsp;</div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:iterate id="cotizanteAviso" name="NominaForm"
						property="trabAvisos">
						<c:choose>
							<c:when test="${count % 2 == 0}">
								<c:set var="estilo" value="textos_formularios" />
							</c:when>
							<c:otherwise>
								<c:set var="estilo" value="textos-formcolor2" />
							</c:otherwise>
						</c:choose>
						<c:set var="count" value="${count + 1}" />
						<tr valign="top" align="center">
							<td height="20" style="text-align:right" nowrap="nowrap"
								class="${estilo}">${cotizanteAviso.rut}</td>
							<td align="left" class="${estilo}">${cotizanteAviso.nombre}&nbsp;</td>
							<td align="left" class="${estilo}">${cotizanteAviso.apellidos}&nbsp;</td>
							<td class="${estilo}">&nbsp;${cotizanteAviso.sucursal}</td>
							<td class="${estilo}" valign="middle">
						       		<div class="textos_resaltados" align="center" title="Trabajador con errores en su Cotizaci&oacute;n">
						       			<c:choose>
							   		    	<c:when test="${cotizanteAviso.nivelError == 0}"><div class="mensaje_error">Error</div></c:when>
							   		    	<c:when test="${cotizanteAviso.nivelError == 1}"><div class="mensaje_aviso">Aviso</div></c:when>
							   				<c:otherwise>&nbsp;</c:otherwise>
							   		  	</c:choose>
						       		</div>
								</td>
							<td class="${estilo}" valign="middle">
							<div align="center"><html:link action="/VerCotizacion.do">
								<html:param name="rutEmpresa" value="${cotizanteAviso.rutEmpresa}"></html:param>
								<html:param name="idConvenio" value="${cotizanteAviso.idConvenio}"></html:param>
								<html:param name="tipoProceso" value="${cotizanteAviso.tipoProceso}"></html:param>
								<c:choose>
								<c:when test="${cotizanteAviso.idCotizPendiente == 0}">
								<html:param name="idCotizante" value="${cotizanteAviso.idCotizante}"></html:param>
								</c:when>
								<c:when test="${cotizanteAviso.idCotizPendiente != 0}">
								<html:param name="idCotizPend" value="${cotizanteAviso.idCotizPendiente}"></html:param>
								</c:when>
								</c:choose>
								<html:param name="operacion" value="mod"></html:param>
								<html:param name="accion" value="inicio"></html:param>
								<html:param name="subAccion" value="trabajadores"></html:param>
								<html:param name="subSubAccion" value="trabajadorEditar"></html:param>
								<img src="<c:url value="/img/iconos/lupa.gif" />" alt="Ver detalle"
									width="14" height="13" border="0" title="Ver detalle" />
							</html:link></div>
							</td>
							<td class="${estilo}" valign="middle">
							<div align="center">&nbsp;</div>
							</td>
						</tr>
					</logic:iterate>
					<logic:iterate id="cotizante" name="NominaForm"
						property="trabajadores">
						<c:choose>
							<c:when test="${count % 2 == 0}">
								<c:set var="estilo" value="textos_formularios" />
							</c:when>
							<c:otherwise>
								<c:set var="estilo" value="textos-formcolor2" />
							</c:otherwise>
						</c:choose>
						<c:set var="count" value="${count + 1}" />
						<tr valign="top" align="center">
							<td height="20" style="text-align:right" nowrap="nowrap"
								class="${estilo}">${cotizante.rut}</td>
							<td align="left" class="${estilo}">${cotizante.nombre}&nbsp;</td>
							<td align="left" class="${estilo}">${cotizante.apellidos}&nbsp;</td>
							<td class="${estilo}">&nbsp;${cotizante.sucursal}</td>
							<td class="${estilo}">&nbsp;</td>
							<td class="${estilo}" valign="middle">
							<div align="center"><html:link action="/VerCotizacion.do">
								<html:param name="rutEmpresa" value="${cotizante.rutEmpresa}"></html:param>
								<html:param name="idConvenio" value="${cotizante.idConvenio}"></html:param>
								<html:param name="tipoProceso" value="${cotizante.tipoProceso}"></html:param>
								<c:choose>
								<c:when test="${cotizante.idCotizPendiente == 0}">
								<html:param name="idCotizante" value="${cotizante.idCotizante}"></html:param>
								</c:when>
								<c:when test="${cotizante.idCotizPendiente != 0}">
								<html:param name="idCotizPend" value="${cotizante.idCotizPendiente}"></html:param>
								</c:when>
								</c:choose>
								<html:param name="operacion" value="mod"></html:param>
								<html:param name="accion" value="inicio"></html:param>
								<html:param name="subAccion" value="trabajadores"></html:param>
								<html:param name="subSubAccion" value="trabajadorEditar"></html:param>
								<img src="<c:url value="/img/iconos/lupa.gif" />" alt="Ver detalle"
									width="14" height="13" border="0" title="Ver detalle" />
							</html:link></div>
							</td>
							<td class="${estilo}" valign="middle">
							<div align="center">&nbsp;</div>
							</td>
						</tr>
					</logic:iterate>
					
					<logic:empty property="trabajadores" name="NominaForm">
					<logic:empty property="trabAvisos" name="NominaForm">
					<logic:empty property="trabPendientes" name="NominaForm">
             			<tr>
             				<td class="textos_formularios" height="20" colspan="7">
             					No existen n&oacute;minas que cumplan con los criterios especificados
             				</td>
             			</tr>
             			</logic:empty>
             			</logic:empty>
             		</logic:empty>
				</table>
				</td>
			</tr>
			<bean:size id="nPags" name="NominaForm" property="numeroFilas"/>
			<c:if test="${nPags > 1}">
				<tr> 
					<td align="center" valign="middle" class="numeracion">
						<logic:iterate id="paginacion" name="NominaForm" property="numeroFilas">
							${paginacion}
						</logic:iterate>
					</td>
				</tr>
			</c:if>
		</logic:present>
		<tr align="center">
			<td valign="top"><br /><br />
				<input type="button" name="operacion" value="Cancelar" onclick="javascript:volver(document.forms['NominaForm']);" class="btn4"><br />
			</td>
		</tr>
	</table>
</html:form>
<script language="javaScript"> 
	var a;
	var i;

	function listar(frm) 
	{
		formu=document.getElementById('formulario');
		if(document.getElementById("trabajadorRut").value.length > 0)
		{
			if(valida_Rut(document.getElementById("trabajadorRut")))
				enviar(formu);
			else
			{
				alert("Rut inválido");
				return false;
			}
		} else
			enviar(formu);
	}
	
	function enviar(frm)
	{
		var b_r=document.getElementById("b_rut");
		var b_n=document.getElementById("b_nombre");
		var b_a=document.getElementById("b_apellido");
		var n=document.getElementById("trabajadorNombre");
		var a=document.getElementById("trabajadorApPaterno");
		var r=document.getElementById("trabajadorRut");
		b_r.value=r.value;
		b_n.value=n.value;
		b_a.value=a.value;
		var t= '<%=request.getParameter("tipo")%>';
		frm.action = frm.action + '?accion=buscar&tipo='+t;
		frm.submit();
	}
	
	function volver(frm) 
	{
			//Se envian valores de los filtros originales
			rutEmpBus = document.getElementById("rutEmpresaBuscar").value;
			convenio = document.getElementById("grupoConvenioBuscar").value;
			tipo = document.getElementById("tipoNominaIdBuscar").value;
			estado = document.getElementById("estadoIdBuscar").value;
	
			//NominaXestadoBuscar.do?accion=listar
			frm.rutEmpresa.value = "";
			//estadoId
			a = frm.action; 
			i = a.lastIndexOf('/'); 
			a = a.substr(0,i) + '/NominaXestadoBuscar.do?accion=listar&rutEmpresaBuscar='+rutEmpBus+'&grupoConvenioBuscar='+convenio+'&tipoNominaIdBuscar='+tipo+'&estadoIdBuscar='+estado;
			frm.action = a; 			
			frm.submit();
	}
	
	function foco()
	{
		document.getElementById('trabajadorRut').focus();
	}

function retornaEnlace(paginacion)
{
	formu = document.getElementById("formulario");
	formu.action = "NominasTrabajadoresVer.do?accion=buscar&idConv=${NominaForm.convenioId}&rutEmp=${NominaForm.rutEmpresa}&tipoNom=${NominaForm.tipoNominaId}&subAccion=trabajadores&subSubAccion=nominaEditar&nom=${NominaForm.b_nombre}&rut=${NominaForm.b_rut}&ap=${NominaForm.b_apellido}&tipo=${NominaForm.nombreProceso}&paginaNumero=" + paginacion;
	formu.submit();
}
</script>

</body>
</html>
