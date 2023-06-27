<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles" %>
<%@ taglib uri="/tags/c" prefix="c" %>

<script language="javascript">

	// pone al día el Formulario 
	$(document).ready(function() {
			reponeStatus();
			// ve si debe colocar defaults por caso anonimo
			if ( ${fichaProyeccion.anonimo} ) {
				// hide("dataPersonalZone");
				var form = getDataForm();
				form.ingresoFamiliar.value = 0;
				form.sueldo.value = 0;
				form.comuna.value = 605; // usamos Santiago
			}

		}
	);
	
	/** Objeto Form */
	function getDataForm() {
		return document.getElementById('dataForm');
	}
	
	function actualizaComboInstituciones( idTipo, idInstitucion, plazo, agnos) {
		getComboList('instituciones', idTipo, idInstitucion );
		// actualizaComboCarreras( idInstitucion, idCarrera );
		actualizaComboPlazos( idTipo, plazo );
		tieneAgnosGraciaTipoInstitucion(idTipo);	
		getDataForm().agnoGracia.value=false;	
	}

	/** function actualizaComboCarreras( idInstitucion, idCarrera ) {
		getComboList ('carreras', idInstitucion, idCarrera );
		getDataForm().carrera.value = idCarrera;
	} */

	function actualizaComboPlazos( idTipo, plazo ) {
		getComboList ('plazos', idTipo, plazo );
		getDataForm().plazo.value = plazo;
	}	

	/* para recuperar a posterior los combos dinámicos */
	function preservaCombos() {
		var form = getDataForm();
		form._institucion.value = form.institucion.value;
		form._plazo.value = form.plazo.value;
		form._agnoGracia.value = form.agnoGracia.value;
	}

	/* para ajustar control de Carrera */
	function ajustaCarrera() {
		var form = getDataForm();
		var carrera =  form.carrera.value;
		var otraCarrera =  form.otraCarrera.value;
		
		if (carrera != null && carrera != '') {
			form._ctr_carrera.value = carrera;
			form.otraCarrera.value='';
		} else {
			if (otraCarrera != null && otraCarrera != '') {
				form._ctr_carrera.value = otraCarrera;
				form.carrera.value='';
			} else
				form._ctr_carrera.value = '';
		}
	}
	
	/* para reponer los valores seleccionados en los combos dinámicos y los efectos de página */
	function reponeStatus() {
		/* combos & valores */
		var form = getDataForm();
		if ( form.tipoInstitucion.value != '' )
			actualizaComboInstituciones( form.tipoInstitucion.value, form._institucion.value, form._plazo.value ,form._agnoGracia.value);
		/* efectos */
		toggleValorCuotaFija();
		toggleEstudiante();
		toggleOtraCarrera();
		
	}

	/** prende o apaga el campo de valor cuota fija */
	function toggleValorCuotaFija() {
		var tipoCredito = getDataForm().tipoCredito.value;
		if ( tipoCredito == 1) {
			show ( 'label_cuota_fija' );
			show ( 'valor_cuota_fija' );
		} else {
			hide ( 'label_cuota_fija' );
			hide ( 'valor_cuota_fija' );
		}
	}

	/** prende o apaga estudiante */
	function toggleEstudiante() {
		var noflag = getDataForm()._flagEstudiante[0].checked;
		if ( noflag ) {
			hide ( 'estudianteZone' );
		} else { 
			show ( 'estudianteZone' );
		}
	}

	/** prende o apaga otraCarrera */
	function toggleOtraCarrera() {
		var carrera = getDataForm().carrera.value;
		if ( carrera != '' ) {
			hide ( 'otraCarreraZone' );
		} else { 
			show ( 'otraCarreraZone' );
		}
	}
		
	/** util de carga familiar */
	function buscarCargaFamiliar() {
		showLoading();
	
		$(document).ready(function() {
			var data = "rutCarga=" + getDataForm().rutEstudiante.value+"&rutCliente="+ getDataForm().rutSolicitante.value;
	        $.ajax({
	           type: "post",
	           url: jsContextRoot + "/commons/GetCargaFamiliarAjax.do?",
	           data: data,
	           success: function (xml) {
					var error = $(xml).find("return").find("error").text();
	       			if (error == 'true')  
	       				despliegaErroresAjax (xml);
	       			else {
	       			
						// actualiza Campos
						getDataForm().nombreEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("nombre").text());
						getDataForm().apellidoPaternoEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("apellidoPaterno").text() );
						getDataForm().apellidoMaternoEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("apellidoMaterno").text() );
						getDataForm().sexoEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("sexo").text() );
						getDataForm().parentescoEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("parentesco").text() );
						getDataForm().fechaNacimientoEstudiante.value = cleanNull( $(xml).find("return").find("carga").find("CargaFamiliarDVO").find("fechaNacimiento").text() );
						
						// manda señal para destacar campos
						flash('nombreEstudiante');
						flash('apellidoPaternoEstudiante');
						flash('apellidoMaternoEstudiante');
						flash('sexoEstudiante');
						flash('parentescoEstudiante');
						flash('fechaNacimientoEstudiante');

						// todo ok ...
						hide ("ajaxErrorBox");
						hideLoading();
						
	       			}
	       	   }
			});			
		 });
	 }


</script>

<c:set var="styleDataPersonal"></c:set>
<c:if test="${fichaProyeccion.anonimo}">
	<c:set var="styleDataPersonal">display: none;</c:set>
</c:if>					

<!--  INICIO DEL FORMULARIO  -->
<html:form action="${webPrePath}/simulacion/ValidaPrecondiciones" styleId="dataForm" onsubmit="javascript:cleanNumbers();preservaCombos();ajustaCarrera();">
	<html:hidden property="_cmd"/>
	<html:hidden property="_institucion"/>
	<html:hidden property="_plazo"/>
	<html:hidden property="_ctr_carrera"/>
	<html:hidden property="_agnoGracia"/>
	<html:hidden property="rutSolicitante"/>
	
	<div id="dataPersonalZone" style="${styleDataPersonal}">
		<!-- solicitante -->
		<c:set var="solicitante" scope="request" value="${fichaProyeccion.solicitante}"></c:set>
		<tiles:insert page="/pages/commons/headers/solicitante.jsp"/>
		<table width="100%">
			<tr> 
				<td width="25%"><strong><bean:message key="label.direccion"/>:</strong></td>
				<td colspan="3">
					<html:text property="direccion" maxlength="45" size="45"/>
					<html:select property="comuna">
						<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
						<html:options collection="comboComunas" property="codigo" labelProperty="nombre"/>
					</html:select>			
				</td>
			</tr>
			<tr> 
				<td width="25%"><strong><bean:message key="label.sueldo"/>:</strong></td>
				<td>
					<html:text property="sueldo" maxlength="15" size="15" styleClass="integer" styleId="sueldo"/>
				</td>
				<td width="25%"><strong><bean:message key="label.ingresoFamiliar"/>:</strong></td>
				<td>
					<html:text property="ingresoFamiliar" maxlength="15" size="15" styleClass="integer" styleId="ingresoFamiliar"/>
				</td>
			</tr>	
			<tr> 
				<td width="25%"><strong><bean:message key="label.fechaContrato"/>:</strong></td>
				<td><html:text property="fechaContrato" maxlength="10" size="15" styleClass="date"/></td>
				<td width="25%"><strong><bean:message key="label.oficinaCentro"/>:</strong></td>
				<td>
					<html:select property="oficina">
						<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
						<html:options collection="comboCentros" property="oficina" labelProperty="nombreCentro"/>
					</html:select>			
				</td>
			</tr>	
		</table>		
	
		<tiles:insert page="/pages/commons/headers/empresa.jsp"/>
	
		<!-- estudiante -->
	    <table width="100%">
	    	<tr>
				<th colspan="4" nowrap="nowrap"><bean:message key="seccion.simulacion.datosEstudiante"/></th>
			</tr>
			<tr> 
				<td colspan="2"><strong><bean:message key="label.flagEstudiante"/>:</strong></td>
				<td  colspan="2" align="left">
					<html:radio property="_flagEstudiante" value="false" onclick="javascript:toggleEstudiante();"><bean:message key="label.boolean.false"/></html:radio>
					<html:radio property="_flagEstudiante" value="true" onclick="javascript:toggleEstudiante();"><bean:message key="label.boolean.true"/></html:radio>
				</td>
			</tr>
		</table>
	</div>
		
	<div id="estudianteZone" style="display: none">
	    <table width="100%">
			<tr> 
				<td colspan="4"><bean:message key="message.datosCarga"/><br/></td>
			</tr>		
			<tr> 
				<td width="25%"><strong><bean:message key="label.rutEstudiante"/>:</strong></td>
				<td width="25%">
					<html:text property="rutEstudiante" maxlength="13" size="15"/>
					<a class="addToolTip" title='<bean:message key="help.rutEstudiante.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
						<html:img page="/images${internetPath}/lupa.gif" onclick="javascript:buscarCargaFamiliar();"></html:img>
				</td>
				<td width="25%"><strong><bean:message key="label.nombreEstudiante"/>:</strong></td>
				<td width="25%">
					<div id="nombreEstudiante"><html:text property="nombreEstudiante" maxlength="25" size="25"/></div>
				</td>
			</tr>
			<tr>
				<td width="25%"><strong><bean:message key="label.apellidoPaternoEstudiante"/>:</strong></td>
				<td width="25%">
					<div id="apellidoPaternoEstudiante"><html:text property="apellidoPaternoEstudiante" maxlength="25" size="25"/></div>
				</td>
				<td width="25%"><strong><bean:message key="label.apellidoMaternoEstudiante"/>:</strong></td>
				<td width="25%">
					<div id="apellidoMaternoEstudiante"><html:text property="apellidoMaternoEstudiante" maxlength="25" size="25"/></div>
				</td>
			</tr>
			<tr> 
				<td width="25%"><strong><bean:message key="label.sexoEstudiante"/>:</strong></td>
				<td colspan="3">
					<div id="sexoEstudiante">
						<html:select property="sexoEstudiante">
							<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
							<html:option value="M"><bean:message key="label.sexo.M"/></html:option>
							<html:option value="F"><bean:message key="label.sexo.F"/></html:option>
							<html:option value="I"><bean:message key="label.sexo.I"/></html:option>
						</html:select>
					</div>
				</td>
			</tr>		
			<tr> 
 				<td width="25%"><strong><bean:message key="label.parentescoEstudiante"/>:</strong></td>
				<td width="25%">
					<div id="parentescoEstudiante">
						<html:select property="parentescoEstudiante">
							<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
							<html:option value="C"><bean:message key="label.parentesco.C"/></html:option>
							<html:option value="H"><bean:message key="label.parentesco.H"/></html:option>
						</html:select>					
					</div>
				</td>
 				<td width="25%"><strong><bean:message key="label.fechaNacimientoEstudiante"/>:</strong></td>
				<td width="25%">
					<div id="fechaNacimientoEstudiante"><html:text property="fechaNacimientoEstudiante" maxlength="10" size="25" styleClass="date"/></div>
				</td>
			</tr>		
			<tr> 
				<td width="25%"><strong><bean:message key="label.direccionEstudiante"/>:</strong></td>
				<td colspan="3">
					<html:text property="direccionEstudiante" maxlength="45" size="45"/>
					<html:select property="comunaEstudiante">
						<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
						<html:options collection="comboComunas" property="codigo" labelProperty="nombre"/>
					</html:select>			
				</td>
			</tr>	
		</table>
	</div>	
	
	<!-- carrera -->
    <table width="100%">
    	<tr>
			<th colspan="4" nowrap="nowrap"><bean:message key="seccion.simulacion.carrera"/></th>
		</tr>
		<tr> 
			<td width="25%"><strong><bean:message key="label.tipoInstitucion"/>:</strong></td>
			<td colspan="3">
				<html:select property="tipoInstitucion" onchange="javascript:actualizaComboInstituciones(this.value, '', '', '');">
					<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
					<html:options collection="comboTiposInstitucion" property="id" labelProperty="nombre"/>
				</html:select>
			</td>
		</tr>
		<tr> 
			<td width="25%"><strong><bean:message key="label.institucion"/>:</strong></td>
			<td colspan="3">
				<html:select property="institucion" styleId="instituciones" onchange="javascript:actualizaComboCarreras(this.value, '');">
					<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
				</html:select>
			</td>
		</tr>
		<tr> 
			<td width="25%"><strong><bean:message key="label.carrera"/>:</strong></td>
			<td colspan="3">
				<html:select property="carrera" styleId="carreras" onchange="javascript:toggleOtraCarrera();">
					<html:option value=""><bean:message key="label.common.otraCarrera.opcion"/></html:option>
					<html:options collection="listaCarreras" property="id" labelProperty="nombreCorto"/>					
				</html:select>
				<div id="otraCarreraZone">
				<br/><bean:message key="label.otraCarrera"/>:<br/><html:text property="otraCarrera" maxlength="80" size="80"/><br/><br/>
				</div>
				
			</td>
		</tr>
		<tr> 
			<td width="25%"><strong><bean:message key="label.valorAnualPeso"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:text property="valorAnual" maxlength="15" size="15" styleClass="integer" styleId="valorAnual"/>
				<a class="addToolTip" title='<bean:message key="help.valorAnual.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
			</td>
			<td width="25%"><strong><bean:message key="label.valorExtrasPeso"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:text property="valorExtras" maxlength="15" size="15" styleClass="integer" styleId="valorExtras"/>
				<a class="addToolTip" title='<bean:message key="help.valorExtras.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
			</td>
		</tr>
		
		<tr> 
			<td width="25%"><strong><bean:message key="label.totalSemestresCarrera"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:text property="totalSemestresCarrera" maxlength="5" size="8" styleClass="integer" styleId="totalSemestresCarrera"/>
				<a class="addToolTip" title='<bean:message key="help.totalSemestresCarrera.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
			</td>
			<td width="25%"><strong><bean:message key="label.semestreCurse"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:text property="semestreCurse" maxlength="5" size="8" styleClass="integer" styleId="semestreCurse"/>
				<a class="addToolTip" title='<bean:message key="help.semestreCurse.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
			</td>
		</tr>		
		<tr> 
			<td width="25%"><strong><bean:message key="label.agnoCurse"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:select property="agnoCurse">
					<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
					<html:options name="comboAgnosEstudio"/>
				</html:select>
			</td>
			<td width="25%"><strong><bean:message key="label.medioAgno"/>:</strong></td>
			<td width="25%" nowrap="nowrap">
				<html:radio property="medioAgno" value="false"><bean:message key="label.medioAgno.agno"/></html:radio><br/>
				<html:radio property="medioAgno" value="true"><bean:message key="label.medioAgno.medio"/></html:radio>
			</td>
		</tr>
	</table>
	
	<!-- credito -->
    <table width="100%">
    	<tr>
			<th colspan="4" nowrap="nowrap"><bean:message key="seccion.simulacion.credito"/></th>
		</tr>
		<tr> 
			<td width="25%"><strong><bean:message key="label.tipoCredito"/>:</strong></td>
			<td width="25%">
				<html:select property="tipoCredito" onchange="javascript:toggleValorCuotaFija();">
					<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
					<html:option value="1"><bean:message key="label.tipoCredito.1"/></html:option>
					<html:option value="2"><bean:message key="label.tipoCredito.2"/></html:option>
				</html:select>
			</td>
			<td width="25%"><strong><bean:message key="label.plazo"/>:</strong></td>
			<td width="25%">
				<html:select property="plazo" styleId="plazos">
					<html:option value=""><bean:message key="label.common.seleccione"/></html:option>
				</html:select>
			</td>
		</tr>
		<tr> 
			<td width="25%">
				<div id="label_agno_gracia">
				<strong><bean:message key="label.agnoGracia"/>:</strong>
				</div>
			</td>
			<td width="25%"  nowrap="nowrap">
				<div id="valor_agno_gracia">
					<html:radio property="agnoGracia" value="true"><bean:message key="label.boolean.true"/></html:radio>
					<html:radio property="agnoGracia" value="false"><bean:message key="label.boolean.false"/></html:radio>
					<a class="addToolTip" title='<bean:message key="help.agnoGracia.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a>
				</div>	
			</td>
			<td width="25%">
				<div id='label_cuota_fija'><strong><bean:message key="label.cuotaFija"/>:</strong></div>&nbsp;
			</td>
			<td width="25%" nowrap="nowrap">
				<div id='valor_cuota_fija'>
					<html:text property="cuotaFija" maxlength="10" size="10" styleClass="decimal" styleId="cuotaFija"/>
					<span id="ayudaCuotaFija"><a class="addToolTip" title='<bean:message key="help.cuotaFija.descripcion"/>'><html:img page='/images${internetPath}/help.jpg' /></a></span>				
				</div>&nbsp;
			</td>
		</tr>
		
		<logic:notEqual name="fichaProyeccion" property="maxMesesGracia" value="0">
			<tr>
				<td width="25%"><strong><bean:message key="label.mesesGracia"/>:</strong></td>
				<td width="25%">
					<html:text property="mesesGracia"  maxlength="5" size="5" styleClass="integer" styleId="mesesGracia"/>
				</td>
				<td width="25%"><strong><bean:message key="label.agnosFinancia"/>:</strong></td>
				<td width="25%">
					<html:text property="agnosFinancia"  maxlength="5" size="5" styleClass="integer" styleId="agnosFinancia"/>
				</td>
			</tr>
		</logic:notEqual>
	
		<tr> 
			<td width="30%"><strong><bean:message key="label.ufref"/> (<bean:write name="fichaProyeccion" property="fecha" formatKey="format.date"/>)</strong></td>
			<td width="20%"><bean:write name="fichaProyeccion" property="ufReferencial" formatKey="format.moneydec.fmt"/></td>
			<td width="25%"><strong><bean:message key="label.seguroCesantia"/>:</strong>
			</td>
			<td width="25%"  nowrap="nowrap">
					<html:radio property="seguroCesantia" value="true"><bean:message key="label.boolean.true"/></html:radio>
					<html:radio property="seguroCesantia" value="false"><bean:message key="label.boolean.false"/></html:radio>
			</td>
		</tr>
		
		
		<!-- solo muestra meses de gracia si el maximo ses 0 -->
		<logic:equal name="fichaProyeccion" property="maxMesesGracia" value="0">
			<html:hidden property="mesesGracia" value="0"/>
		</logic:equal>	

	</table>	
	
    <table width="100%">
		<tr> 
			<td colspan="4" class="botonera">
				<html:submit property="" styleClass="button"><bean:message key="button.simular"/></html:submit>
				<tiles:insert page="/pages/commons/otros/cancelar.jsp"/>
			</td>
		</tr>
	</table>	
</html:form>
