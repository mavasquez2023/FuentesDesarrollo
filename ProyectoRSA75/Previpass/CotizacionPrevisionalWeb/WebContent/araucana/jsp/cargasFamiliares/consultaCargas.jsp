<%@ include file="/html/comun/taglibs.jsp" %>
      
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>

<html:form action="/ConsultaCargas" styleId="formulario" onsubmit="return onFormSubmit(this)">
	<html:hidden styleId="accion"       property="accion"       name="accion"       value="cargas" />
	<html:hidden styleId="subAccion"    property="subAccion"    name="subAccion"    value="cfConsulta" />

	<html:hidden property="rutOculto"       styleId="rutOculto"/>
	<html:hidden property="nombreOculto"    styleId="nombreOculto"/>

	<input type="hidden" name="operacion" id="operacion" value=""/>

	<table width="590" border="0" cellspacing="0" cellpadding="0">
		<tr valign="bottom">
			<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
				<strong>Consulta por Trabajador</strong>
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" class="textos_formularios">Permite la b&uacute;squeda de uno o m&aacute;s trabajadores dado su RUT y/o nombre.</td>
		</tr>
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
					<tr>
						<td><strong>RUT:</strong></td>
						<td><input type="text" name="rut" id="rut" maxlength="12" size="25" class="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
						<td><strong>Nombre y/o Apellido:</strong></td>
						<td><input type="text" name="nombre" id="nombre" maxlength="30" size="45" class="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<%--html:submit property="operacion" styleClass="btn2" value="${buscarTrabajador}"/--%>
							<input type="button" name="operacion" class="btn2" value="${buscarTrabajador}" onclick="validar('${buscarTrabajador}');"/>
						</td>
					</tr>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="4" bgcolor="#85b4be"></td>
		</tr>
		<tr valign="bottom">
			<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
				<strong>Consulta por Empresa</strong>
			</td>
		</tr>
		<tr>
			<td bgcolor="#FFFFFF" class="textos_formularios">Permite la b&uacute;squeda de una o m&aacute;s empresa dado su RUT y/o raz&oacute;n social.</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
					<tr>
						<td><strong>RUT Empresa:</strong></td>
						<td><input type="text" name="rutEmpresa" id="rutEmpresa" maxlength="12" size="25" class="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
						<td><strong>Raz&oacute;n Social: </strong></td>
						<td><input type="text" name="razonSocial" id="razonSocial" size="25" class="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
					</tr>
					<tr>
						<td colspan="4" align="right">
							<%--html:submit property="operacion" styleClass="btn2" value="${buscarEmpresa}"/--%>
							<input type="button" name="operacion" class="btn2" value="${buscarEmpresa}" onclick="validar('${buscarEmpresa}');"/>
						</td>
					</tr>
					<tr>
						<td height="4" colspan="5" bgcolor="#85b4be"></td>
					</tr>

				</table>
				<br />

				<html:errors/>
				<html:messages id="msg" message="true">
					<div class="msgExito">${msg}</div>
				</html:messages>

				<logic:equal name="ConsultaCargasForm" property="mostrarLista" value="SI">

					<%-- EMPRESAS --%>
					<logic:present name="ConsultaCargasForm" property="empresas">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
								<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">&nbsp;</td>
							</tr>
							<tr align="center">
								<td height="33" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr align="center">
										<td height="33" align="left" valign="top">
											<logic:notEmpty name="ConsultaCargasForm" property="empresas">
												<table width="100%" border="0" cellpadding="0" cellspacing="1">
													<tr valign="bottom">
														<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Selecci&oacute;n Empresa</strong></td>
													</tr>
												</table>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
													<tr class="subtitulos_tablas">
														<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT Empresa</td>
														<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
														<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Caja</td>
														<td width="25%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
													</tr>
													<logic:iterate id="empresa" name="ConsultaCargasForm" property="empresas">
														<c:set var="countEmp" value="1"/>
														<c:choose>
															<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
															<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
														</c:choose>
														<c:set var="count" value="${count + 1}"/>
														<tr>
															<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
															<td class="${estilo}">${empresa.razonSocial}</td>
															<td class="${estilo}">${empresa.nombreCaja}</td>
															<td class="${estilo}" STYLE="text-align:center">
																<html:link href="ConsultaCargas.do?accion=cargas&subAccion=cfConsulta&rutEmpresa=${empresa.idEmpresa}&idCaja=${empresa.idCaja}&operacion=buscarTrabajadores">
																	<img align="middle" alt="Listar Trabajadores" title="Listar Trabajadores" border="0" src="<c:url value="/img/ico_pop.gif"/>" />
																</html:link>
																<html:link href="ConsultaCargasPDF.do?idCaja=${empresa.idCaja}&rutEmpresa=${empresa.idEmpresa}&operacion=buscarCargasPorEmp&periodo=${periodo}">
																	<img id="imgPDF" src="<c:url value="/img/ico_pdf.gif"/>" width="12" height="14" border="0" title="Imprimir PDF">
																</html:link>
															</td>
														</tr>
													</logic:iterate>
												</table>
											</logic:notEmpty>
										</td>
									</tr>
									</table>
								</td>
							</tr>
						</table>
					</logic:present>

					<%-- TRABAJADORES --%>
					<logic:present name="ConsultaCargasForm" property="trabajadores">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
							<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">&nbsp;</td>
						</tr>
						<tr align="center">
							<td height="33" valign="top">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr align="center">
									<td height="33" align="left" valign="top">
										<logic:notEmpty name="ConsultaCargasForm" property="trabajadores">
											<c:set var="estilo" value="textos-formcolor2"/>
											<c:set var="count" value="0"/>
											<table width="100%" border="0" cellpadding="0" cellspacing="1">
												<tr valign="bottom">
													<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Selecci&oacute;n Trabajador</strong></td>
												</tr>
											</table>
											<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
												<tr class="subtitulos_tablas">
													<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT</td>
													<td width="27%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
													<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT Empresa</td>
													<td width="26%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
													<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
												</tr>
												<logic:iterate id="trabajador" name="ConsultaCargasForm" property="trabajadores">
													<c:set var="countEmp" value="1"/>
													<logic:iterate id="empresa" name="trabajador" property="empresas">
														<c:choose>
															<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
															<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
														</c:choose>
														<c:set var="count" value="${count + 1}"/>
														<tr>
															<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">${trabajador.rut}</td>
															<td class="${estilo}">${trabajador.nombre}</td>
															<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${empresa.idEmpresaFmt}</td>
															<td class="${estilo}">${empresa.razonSocial}</td>
															<td class="${estilo}" STYLE="text-align:center">
																<html:link href="ConsultaCargas.do?idCaja=${empresa.idCaja}&accion=cargas&subAccion=cfConsulta&idTrabajador=${trabajador.idCotizante}&idEmpresa=${empresa.idEmpresa}&operacion=buscarCargas">
																	<img align="middle" alt="Listar Cargas Familiares" title="Listar Cargas Familiares" border="0" src="<c:url value="/img/ico_pop.gif"/>" />
																</html:link>
																<html:link href="ConsultaCargasPDF.do?idCaja=${empresa.idCaja}&idTrabajador=${trabajador.idCotizante}&rutEmpresa=${empresa.idEmpresa}&operacion=buscarCargasPorTrab&periodo=${periodo}">
																	<img id="imgPDF" src="<c:url value="/img/ico_pdf.gif"/>" width="12" height="14" border="0" title="Imprimir PDF">
																</html:link>
															</td>
														</tr>
													</logic:iterate>
												</logic:iterate>
											</table>
										</logic:notEmpty>
									</td>
								</tr>
								</table>
							</td>
						</tr>
					</table>
					</logic:present>

					<%-- CARGAS FAMILIARES --%>
					<logic:present name="ConsultaCargasForm" property="cargas">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF">
								<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">&nbsp;</td>
							</tr>
							<tr align="center">
								<td height="33" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr align="center">
										<td height="33" align="left" valign="top">
											<logic:notEmpty name="ConsultaCargasForm" property="cargas">
												<table width="100%" border="0" cellpadding="0" cellspacing="1">
													<tr valign="bottom">
														<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Listado de Cargas Familiares</strong></td>
													</tr>
												</table>
												<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
													<tr class="subtitulos_tablas">
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT Trab</td>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Trab</td>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">RUT Carga</td>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre Carga</td>
														<%--td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fec. Nac.</td--%>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fec. Inicio</td>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Fec. Fin</td>
														<td width="16%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Parentesco</td>
														<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Tipo Carga</td>
													</tr>
													<logic:iterate id="cargas" name="ConsultaCargasForm" property="cargas">
														<c:set var="countEmp" value="1"/>
														<c:choose>
															<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
															<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
														</c:choose>
														<c:set var="count" value="${count + 1}"/>
														<tr>
															<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${cargas.rutTrabajadorFmt}</td>
															<td class="${estilo}">${cargas.nombreTrabajador}</td>
															<td class="${estilo}">${cargas.rutCargaFmt}</td>
															<td class="${estilo}">${cargas.nombreCarga}</td>
															<%--td class="${estilo}"><bean:write format="dd/MM/yyyy" name="cargas" property="fecNacCarga"/></td--%>
															<td class="${estilo}"><bean:write format="dd/MM/yyyy" name="cargas" property="fecIniVigencia"/></td>
															<td class="${estilo}"><bean:write format="dd/MM/yyyy" name="cargas" property="fecFinVigencia"/></td>
															<%--td class="${estilo}">${cargas.strFecIniVigencia}</td>
															<td class="${estilo}">${cargas.strFecFinVigencia}</td--%>
															<td class="${estilo}">${cargas.parentesco}</td>
															<td class="${estilo}">${cargas.tipo}</td>
														</tr>
													</logic:iterate>
												</table>
											</logic:notEmpty>
										</td>
									</tr>
									</table>
								</td>
							</tr>
						</table>
						<logic:iterate id="carga" name="ConsultaCargasForm" property="cargas" length="1">
							<table>
								<tr>
									<td height="14">
										<%--html:link href="ConsultaCargasPDF.do?idTrabajador=${trabajador.idCotizante}&rutEmpresa=${empresa.idEmpresa}&operacion=buscarCargasPorTrab&periodo=${periodo}">
											<img id="imgPDF" src="<c:url value="/img/ico_pdf.gif"/>" width="12" height="14" border="0" title="Imprimir PDF">
										</html:link--%>
										<button class="btn3" onclick="javascript:abrirPDF(${carga.rutTrabajador}, ${carga.rutEmpresa});">Descargar PDF</button>
									</td>
								</tr>
							</table>
						</logic:iterate>
					</logic:present>
				</logic:equal>
			</td>
		</tr>
	</table>
</html:form>
<script language="javaScript">
<!--

	function validar(tipo) {

		document.getElementById("operacion").value = tipo;
		
		//Valida caracteres validos en caja de búsqueda
		var sMsje = "";
		
		if (tipo == '${buscarTrabajador}') {
			if (validaReq(document.getElementById("rut")) &&
				!validaRut(document.getElementById("rut").value))
				sMsje += "* Formato de rut inválido para el trabajador a buscar\n";
			if (validaReq(document.getElementById("nombre")) && !validaChrs(document.getElementById("nombre").value))
				sMsje += "* Caracteres inválidos en el nombre del trabajador a buscar\n";
			else if (document.getElementById("nombre").value.length > 0 && document.getElementById("nombre").value.length < 3)
				sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del trabajador a buscar\n";
	
			if (sMsje == "") {
				if (validaReq(document.getElementById("rut")) &&
					!validaDV(document.getElementById("rut").value))
					sMsje += "* Dígito verificador inválido para el rut del trabajador a buscar\n";
			}
		}

		if (sMsje != "") {
			alert("Errores de validación:\n\n" + sMsje);
		} else {
			document.getElementById("rutOculto").value=document.getElementById("rut").value;
			document.getElementById("nombreOculto").value=document.getElementById("nombre").value;

			formu = document.getElementById("formulario");
			formu.action = "ConsultaCargas.do";
			formu.submit();
		}
	}

	function validaFormulario(f)
	{
		//Valida caracteres validos en caja de búsqueda
		var sMsje = "";
		if (validaReq(document.getElementById("rut")) &&
			!validaRut(document.getElementById("rut").value))
			sMsje += "* Formato de rut inválido para el trabajador a buscar\n";
		if (validaReq(document.getElementById("nombre")) && !validaChrs(document.getElementById("nombre").value))
			sMsje += "* Caracteres inválidos en el nombre del trabajador a buscar\n";
		else if (document.getElementById("nombre").value.length > 0 && document.getElementById("nombre").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del trabajador a buscar\n";

		if (sMsje == "") {
			if (validaReq(document.getElementById("rut")) &&
				!validaDV(document.getElementById("rut").value))
				sMsje += "* Dígito verificador inválido para el rut del trabajador a buscar\n";
		}

		if (sMsje != "")
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		document.getElementById("rutOculto").value=document.getElementById("rut").value;
		document.getElementById("nombreOculto").value=document.getElementById("nombre").value;

		return true;
	}

	function verificarCampos()
	{
	 	rut_a = document.getElementById("rutOculto");
		rut_n = document.getElementById("rut");
		if(rut_a!=rut_n)
	 		rut_n.value=rut_a.value;

		nom_a = document.getElementById("nombreOculto");
		nom_n = document.getElementById("nombre");
		if(nom_a!=nom_n)
	 		nom_n.value=nom_a.value;
	}

	function foco()
	{
		document.getElementById('rut').focus();
	}

	function abrirPDF(idTrab, idEmp) {
		formu = document.getElementById("formulario");
		formu.action = "ConsultaCargasPDF.do?idTrabajador=" + idTrab + "&rutEmpresa=" + idEmp + "&operacion=buscarCargasPorTrab&periodo=${periodo}";
		formu.submit();
	}

	foco();
// End -->
</script>