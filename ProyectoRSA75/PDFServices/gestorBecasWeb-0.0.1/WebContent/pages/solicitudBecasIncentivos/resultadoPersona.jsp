<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="/tags/struts-logic" prefix="logic"%>
<%@ taglib uri="/tags/struts-bean" prefix="bean"%>
<%@ taglib uri="/tags/struts-html" prefix="html"%>
<%@ taglib uri="/tags/struts-tiles" prefix="tiles"%>
<%@ taglib uri="/tags/c" prefix="c"%>

<script language="javascript">

	function actualizaNivelEducacional(idPostulante, paso) {
		getComboList('nivelEducacional', idPostulante, paso);

	}
	
	function actualizaComboCurso(idNivelEducacional, paso) {
		getComboList('curso', idNivelEducacional, paso);
		getComboList('tipoCalificacion', idNivelEducacional, paso);
		if(idNivelEducacional == 6) {
			$("#puntaje_lenguaje_tdiv").show();
			$("#puntaje_lenguaje_ldiv").show();
			$("#puntaje_matematica_tdiv").show();
			$("#puntaje_matematica_ldiv").show();
			$("#puntaje_promedio_tdiv").show();
			$("#puntaje_promedio_ldiv").show();
			$("#calificacion_tdiv").hide();
			$("#calificacion_ldiv").hide();
			document.getElementsByName("calificacion")[0].value = "";		
		} else {
			$("#puntaje_lenguaje_tdiv").hide();
			$("#puntaje_lenguaje_ldiv").hide();
			$("#puntaje_matematica_tdiv").hide();
			$("#puntaje_matematica_ldiv").hide();
			$("#puntaje_promedio_tdiv").hide();
			$("#puntaje_promedio_ldiv").hide();
			$("#calificacion_tdiv").show();
			$("#calificacion_ldiv").show();
			document.getElementsByName("puntajeLenguaje")[0].value = "";
			document.getElementsByName("puntajeMatematica")[0].value = "";
			document.getElementsByName("puntajePromedio")[0].value = "";
			document.getElementsByName("puntajePromedioHidden")[0].value = "";
		}
	}

	function swapAll(id, imgId)
	{
		obj = document.getElementById(id);
		img = document.getElementById(imgId);
	    if ( obj.style.display == '' )
	    {
			obj.style.display='none';
			img.src   = '<c:url value="/images/ico_mas.gif" />';
			img.alt   = "Expandir";
			img.title = "Expandir";
		} else
		{
			obj.style.display = '';
			img.src   = '<c:url value="/images/ico_menos.gif" />';
			img.alt   = "Contraer";
			img.title = "Contraer";
		}
	}
	
	function ocultarBotonConfirmar(valor)
	{
		
		var obj = document.getElementById("divBotonContinuar");
		
		if(valor == true){
			obj.style.display = '';
		}
	}

	
</script>
<!--  INICIO DEL FORMULARIO  -->
<html:form
	action="${webPrePath}/solicitudBecasIncentivos/ResultadoPersona"
	styleId="dataForm">
	<!-- titular -->
	<input type="hidden" name="puntajePromedioHidden" id="puntajePromedioHidden" value="">
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.solicitante" />
			</th>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nombreSolicitante" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullNombre" />
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.rutSolicitante" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="solicitante.fullRut" />
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.categoria" /> </strong>
			</td>
			<td><bean:write name="solicitudBO"
					property="segmentoDVO.segmento" />
			</td>
		</tr>
	</table>

	<!-- beneficiarios -->

	<table width="100%">
		<tr>
			<th colspan="4"><bean:message key="label.seleccioneBeneficiario" />
			</th>
		</tr>
		<logic:iterate id="beneficiario" name="solicitudBO"
			property="posiblesBeneficiarios" indexId="i">
			<tr>
				<td width="5%">
					<c:if test="${!beneficiario.seleccionable}">
						<html:radio property="index" value="${i}" disabled="true" />
					</c:if> 
					<c:if test="${beneficiario.seleccionable}">
						<html:radio property="index" value="${i}" onclick="javascript:actualizaNivelEducacional(this.value, '');ocultarBotonConfirmar(true);" />
					</c:if> 
				</td>
				<td width="55%" <c:if test="${!beneficiario.seleccionable}"> bgcolor="Khaki" </c:if>>
					<bean:write name="beneficiario" property="fullNombre" />
				</td>
				<td width="45%">
					<c:if test="${beneficiario.tieneBeca}">
						<a href="#" onclick="swapAll('masBeca${i}', 'img${i}');">
							<img id="img${i}" src="<c:url value="/images/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							<bean:message key="label.tieneBeca"/>
						</a>
						<div id="masBeca${i}" style="display:none">
							<logic:iterate id="becas" name="beneficiario" property="listaBecas">
								<html:link page="/consultaBecasIncentivos/detalleBeneficiario.do?_cmd=inicio&idBeneficiario=${becas.idBeneficiario}" styleClass="links" target="_blank"><bean:write name="becas" property="nombreBeca" /></html:link>
								<br/>
							</logic:iterate>
						</div>
					</c:if>
					<c:if test="${!beneficiario.tieneBeca}">
						<td colspan="2">&nbsp;</td>
					</c:if>
				</td>
			</tr>
		</logic:iterate>
	</table>
	<html:hidden property="_cmd" value="resultado" />
	<html:hidden property="_flagValidar" value="true" />
	
	<!-- Información Beneficiario -->
	<table width="100%">
		<tr>
			<th colspan="2"><bean:message key="label.informacionAdicional" />
			</th>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.email" />:</strong></td>
			<td><html:text property="email" onblur="javascript:soloEmail(this);" onkeyup="javascript:soloEmail(this);" maxlength="250"/>
			</td>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.telefono" />:</strong></td>
			<td><html:text property="telefono" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);" maxlength="9"/>
			</td>
		</tr>
			<tr>
			<td width="20%"><strong><bean:message
						key="label.celular" />:</strong></td>
			<td><html:text property="celular" onblur="javascript:soloNumero(this);" onkeyup="javascript:soloNumero(this);" maxlength="9"/>
			</td>
		</tr>
		
		
		<tr>
			<td width="20%"><strong><bean:message
						key="label.nivelEducacional" />:</strong></td>
			<td><html:select property="nivelEducacional"
					onchange="javascript:actualizaComboCurso(this.value, '');" styleId="nivelEducacional">
					<html:option value="">
						<bean:message key="label.common.seleccione.text" />
					</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message key="label.curso" />:</strong>
			</td>
			<td><html:select property="curso" styleId="curso">
					<html:option value="">
						<bean:message key="label.common.seleccione.text" />
					</html:option>
				</html:select>
			</td>
		</tr>
		<tr>
			<td width="20%"><strong><bean:message
						key="label.tipoCalificacion" />:</strong></td>
			<td><html:select property="tipoCalificacion"
					styleId="tipoCalificacion">
					<html:option value="">
						<bean:message key="label.common.seleccione.text" />
					</html:option>
				</html:select>
			</td>
		</tr>		
		<tr>
			<td width="20%"><div id="calificacion_ldiv"><strong><bean:message key="label.calificacion" />:</strong></div></td>
			<td><div id="calificacion_tdiv"><html:text property="calificacion" styleId="calificacionIN" maxlength="3" onkeyup="javascript:soloDecimales2(this);"/> 
			</div></td>
		</tr>			
		<tr>
			<td width="20%"><div id="puntaje_lenguaje_ldiv" style="display:none"><strong><bean:message key="label.puntajeLenguaje" />:</strong></div></td>
			<td><div id="puntaje_lenguaje_tdiv" style="display:none"><html:text property="puntajeLenguaje" onkeyup="javascript:soloPuntajePSU(this);" maxlength="3"/>
			</div></td>
		</tr>		
		<tr>
			<td width="20%"><div id="puntaje_matematica_ldiv" style="display:none"><strong><bean:message
						key="label.puntajeMatematica" />:</strong></div></td>
			<td><div id="puntaje_matematica_tdiv" style="display:none"><html:text property="puntajeMatematica" onkeyup="javascript:soloPuntajePSU(this);" maxlength="3"/>
			</div></td>
		</tr>
		</div>		
		<tr>
			<td width="20%"><div id="puntaje_promedio_ldiv" style="display:none"><strong><bean:message
						key="label.puntajePromedio" />:</strong></div></td>
			<td><div id="puntaje_promedio_tdiv" style="display:none"><html:text property="puntajePromedio" disabled="true"/>
			</div></td>
		</tr>
		</div>
	</table>
	<table width="100%">
		<tr>
			<td align="right">
				<div id="divBotonContinuar" style="display:none">
					<html:submit property="continuar" styleId="continuar" styleClass="button"><bean:message key="button.continuar" />
					</html:submit>
				</div>
			</td>
		</tr>
		<tr>
			<td bgcolor="Khaki" align="left">
				<bean:message key="label.leyenda.beca"/>			
			</td>
			
		</tr>
	</table>
</html:form>
