<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validaReli.js" />"></script>

<html:form action="/EditarCotizacion">
<input type="hidden" id="botonGuardar" value="0" />
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="trabajadores"/>
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa" property="rutEmpresa" />
<html:hidden styleId="rutTrabajador" property="rutTrabOrigin" />
<html:hidden styleId="convenio" property="idConvenio" />
<html:hidden styleId="idMutual" property="idMutual" />
<html:hidden styleId="aporteINPFON" property="aporteINPFON" />
<html:hidden styleId="aporteCCAFFON" property="aporteCCAFFON" />
<html:hidden styleId="idCotizPendiente" property="idCotizPendiente" />
<html:hidden styleId="topeAFP" property="topeAFP" />
<html:hidden styleId="topeINP" property="topeINP" />
<html:hidden styleId="topeCesantia" property="topeCesantia" />
<html:hidden styleId="apEmpIndSegCes" property="apEmpIndSegCes" />
<html:hidden styleId="apEmpPFSegCes" property="apEmpPFSegCes" />
<html:hidden styleId="apTrabIndSegCes" property="apTrabIndSegCes" />
<html:hidden styleId="apTrabPFSegCes" property="apTrabPFSegCes" />
<html:hidden styleId="tipoPrevision" property="tipoPrevision" />
<html:hidden styleId="mostrar" property="mostrar" />
<html:hidden styleId="operacion" property="operacion" />
<html:hidden styleId="periodo" property="periodo" />
<html:hidden styleId="periodoReli_old" property="periodoReli_old" />
<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" value="" name="opExCaja" id="opExCaja" />
<input type="hidden" name="idCaja" id="idCaja" value="${CotizacionActionForm.caja.id}"/>
<html:hidden styleId="respaldoOperacion" property="respaldoOperacion" />
<html:hidden styleId="primerCotizante"			property="primerCotizante" />

<html:hidden styleId="flgTrabNomina" property="flgTrabNomina"/>

<input type="hidden" id="valorINPPrevio" />
<input type="hidden" id="valorAFPPrevio" />
<input type="hidden" id="valorIsaprePrevio" />

<c:set var="idCCAF" scope="page"><bean:write name='idCCAF'/></c:set>

<c:set var="cmpPrepagado" scope="page">
	<%=request.getAttribute("comprobantePrepagado")%>
</c:set>

<c:set var="nomPrepagada" scope="page">
	<%=request.getAttribute("nominaPrepagada")%>
</c:set>

<c:set var="exigirValidacion" scope="page">
	<bean:write name='exigirValidacion'/>
</c:set>

<script type="text/javascript">
$(document).ready(function(){
	    
   	if(document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP){
   		document.getElementById("idEntAFCReal").value = document.getElementById('idEntPensionReal').value;
   		document.getElementById("idEntAFCReal").disabled = true; 	
   	}else{
	   	document.getElementById("idEntAFCReal").disabled = false; 	   	
   	}
   	
   	var idSalud = "<bean:write name ='CotizacionActionForm' property='cotizante.idEntSaludReal'/>";
	if(idSalud == -1 && document.getElementById("saludObligFONASA").value > 0){
   		$("#idEntSaludReal").val(sinEntidadSalud);
   		$("input[name=fonasaCheck]").attr('checked',true);
	} else if (idSalud == idFONASA) {
   		$("#idEntSaludReal").val(sinEntidadSalud);
   		$("input[name=fonasaCheck]").attr('checked',true);
   	} else if (idSalud == sinEntidadSalud) {
   		$("#idEntSaludReal").val(sinEntidadSalud);
   		$("input[name=fonasaCheck]").attr('checked',false);
   	} else {
   		$("input[name=fonasaCheck]").attr('checked',false);
   	}
   	
   	$("#fonasaCheck").click(function(){
   		if ($("#fonasaCheck").is(":checked")) {
			if ($("#saludObligISAPRE").val() != 0) {
				if (!confirm("Al seleccionar Fonasa se borrarán todos los datos que haya ingresado para la Isapre ¿Desea continuar?")) {
					$("#fonasaCheck").removeAttr("checked");
					return false;
				}
			}

			$("#saludObligFONASA").attr("readOnly", "");
			$("#aporteCaja").attr("readOnly", "");
			$("#idEntSaludReal").val("-1");
			$("#saludAdicional").attr("value", "0");
			if($("#remImpPension").val() == "0" || $("#remImpPension").val() == "")
				$("#remImpPension").val($("#montoReli").val());
			recalculaSalud();
		} else {
			$("#saludObligFONASA").attr("readOnly", "true");
			$("#aporteCaja").attr("readOnly", "true");
			$("#saludObligFONASA").attr("value", "0");
			$("#aporteCaja").attr("value", "0");
		}
		recalculaTotalINP();
   	});	
   	
   	$("#idEntSaludReal").change(function(){
   		if ($("#idEntSaludReal").val() != "-1") {
   			$("input[name=fonasaCheck]").attr('checked', false);
   			$("#saludObligFONASA").attr("readOnly", "true");
			$("#aporteCaja").attr("readOnly", "true");
   		}
   	});
});

function avisoCambioIsapre() {
	if(document.getElementById('fonasaCheck').checked) { //if(document.getElementById("saludObligFONASA").value > 0) {
		if(confirm("Al seleccionar una Isapre se borrará el monto que haya ingresado para Fonasa ¿Desea continuar?")){
			recalculaSalud();
			//recalculaTotalINP();
		} else {
			document.getElementById("idEntSaludReal").value = document.getElementById("valorIsaprePrevio").value;
		}
	} else {
		recalculaSalud();
		//recalculaTotalINP();
	}
}

function guardaValorIsapre(valor) {
	document.getElementById("valorIsaprePrevio").value = valor;
}

function limpiaINP() {
	if(!document.getElementById('fonasaCheck').checked) {
		document.getElementById('remImpPension').value = 0;
	}
	document.getElementById('prevObligatorioINP').value = 0;
	recalculaTotalINP();
}

function guardaValorINP(valor) {
	document.getElementById("valorINPPrevio").value = valor;
}

function guardaValorAFP(valor) {
	document.getElementById("valorAFPPrevio").value = valor;
}

function avisoINPaAFP(){
	var flgAviso = 0;
	if (document.getElementById("prevObligatorioINP").value != 0) {
		flgAviso = 1;
	}
	
	if(document.getElementById("idEntPensionReal").value != "-1" && flgAviso == 1) {
		if(!confirm("Al seleccionar una AFP se borrarán todos los datos que haya ingresado para la INP ¿Desea continuar?")) {
			document.getElementById("idEntPensionReal").value = document.getElementById("valorAFPPrevio").value;
			return false;
		}
	}

	limpiaINP();
	recalculaPensionAFP();
}

</script>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	    <logic:equal name="CotizacionActionForm" property="mostrar" value="new">
	    	<html:hidden styleId="tipoProceso" property="tipoProceso" />
	        <table class="textos-formularios3">
	       		<tr> 
		        	<td colspan="4">&nbsp;</td>
		     	</tr>
		       	<tr> 
		          	<td width="15%"><strong>RUT:</strong></td>
		          	<td width="20%">${CotizacionActionForm.rutEmpresaFormat}</td>
		            <td><strong>Empresa:</strong></td>
		          	<td>${CotizacionActionForm.razonSocial}</td>
		       	</tr>
		      	<tr> 
		         	<td><strong>Convenio:</strong></td>
		         	<td>${CotizacionActionForm.convenio}</td>
		         	<td><strong>Tipo de N&oacute;mina:</strong></td>
		         	<td>
		         		<logic:iterate id="tp" name="CotizacionActionForm" property="tiposProcesos">
		         			<logic:equal value="${CotizacionActionForm.tipoProceso}" name="tp" property="idTipoNomina">
		         				${tp.descripcion}
		         				<html:hidden styleId="tipoProceso" name="tp" property="idTipoNomina"/>
		         			</logic:equal>
		         		</logic:iterate>
		           	</td>
		      	</tr>
		      	<tr> 
		        	<td colspan="4">&nbsp;</td>
		     	</tr>
		    </table>
	    </logic:equal>
	    <logic:notEqual name="CotizacionActionForm" property="mostrar" value="new">
	    	<logic:equal name="CotizacionActionForm" property="mostrar" value="pen">
		    	<html:hidden styleId="tipoProceso" property="tipoProceso" />
		        <table class="textos-formularios3">
		        	<tr> 
			        	<td colspan="4">&nbsp;</td>
			     	</tr>
			       	<tr> 
			          	<td width="15%"><strong>RUT:</strong></td>
			          	<td width="20%">${CotizacionActionForm.rutEmpresaFormat}</td>
			            <td><strong>Empresa:</strong></td>
			          	<td>${CotizacionActionForm.razonSocial}</td>
			       	</tr>
			      	<tr> 
			         	<td><strong>Convenio:</strong></td>
			         	<td>${CotizacionActionForm.convenio}</td>
			         	<td><strong>Tipo de N&oacute;mina:</strong></td>
			         	<td>
			         		<logic:iterate id="tp" name="CotizacionActionForm" property="tiposProcesos">
			         			<logic:equal value="${CotizacionActionForm.tipoProceso}" name="tp" property="idTipoNomina">
			         				${tp.descripcion}
			         				<html:hidden styleId="tipoProceso" name="tp" property="idTipoNomina"/>
			         			</logic:equal>
			         		</logic:iterate>
			           	</td>
			      	</tr>
			      	<tr> 
			        	<td colspan="4">&nbsp;</td>
			     	</tr>
			    </table>
	    	</logic:equal>
	    	<logic:equal name="CotizacionActionForm" property="mostrar" value="ap">	    	
		        <table border="0" cellpadding="0" cellspacing="0" class="textos-formularios3">
			        <tr>
			        	<td colspan="4">&nbsp;</td>
			        </tr>
			       	<tr> 
			          	<td width="15%"><strong>RUT:</strong></td>
			          	<td width="20%">${CotizacionActionForm.rutEmpresaFormat}</td>
			            <td><strong>Empresa:</strong></td>
			          	<td>${CotizacionActionForm.razonSocial}</td>
			       	</tr>
			      	<tr> 
			         	<td><strong>Convenio:</strong></td>
			         	<td>${CotizacionActionForm.convenio}</td>
			         	<td><strong>Tipo de N&oacute;mina:</strong></td>
			         	<td>
							<html:select property="tipoProceso" styleClass="campos" styleId="tipoProceso">
								<html:optionsCollection property="tiposProcesos" label="descripcion" value="idTipoNomina" />
							</html:select>
			           	</td>
			      	</tr>
			      	<tr>
			      		<td colspan="3">&nbsp;</td>
			      		<td align="center"><input type="button" value="${aplicar}" class="btn-destacado" onclick="javascript:enviarRemu('${aplicar}')"/></td>
			      	</tr>
			      	<tr> 
		        	<td colspan="4">&nbsp;</td>
			     	</tr>
			    </table>
	    	</logic:equal>
		    <logic:present name="CotizacionActionForm" property="cotizacion">
		        <table width="100%" border="0" cellpadding="0" cellspacing="1">
		        	<tr valign="bottom"> 
		            	<td width="72%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Ficha Trabajador</strong></td>
		                <td width="28%" align="right" bgcolor="#FFFFFF" class="tit-13">&nbsp;</td>
		            </tr>
		        </table>
		        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		            <tr class="subtitulos_tablas">
		              <td align="center" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
		              <td align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		              <td align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Apellidos</td>
		            </tr>
		            <tr>
		              <td align="left" nowrap="nowrap" class="textos_formularios">
		              	${CotizacionActionForm.rutTrabFormat}&nbsp;
		              </td>              	
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.nombre}&nbsp;</td>
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.apellidos}&nbsp;</td>
		            </tr>
		        </table>
		    </logic:present>
	        <br />
	    </logic:notEqual>
	    <br />
		
	    <html:errors />
	    <html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>

		<logic:notEmpty name="CotizacionActionForm" property="errores">
			<span class="mensaje_error">ERRORES<br />
				<logic:iterate id="error" name="CotizacionActionForm" property="errores">
					&nbsp;&nbsp;&nbsp;-&nbsp;<bean:write name="error" /><br />
				</logic:iterate>
			</span>
		</logic:notEmpty>
		<logic:notEmpty name="CotizacionActionForm" property="avisos">
			<span class="mensaje_aviso">AVISOS<br />
				<logic:iterate id="aviso" name="CotizacionActionForm" property="avisos">
					&nbsp;&nbsp;&nbsp;-&nbsp;<bean:write name="aviso" /><br />
				</logic:iterate>
			</span>
		</logic:notEmpty>
		<bean:define id="erroresCD" name="CotizacionActionForm" property="erroresCD" type="java.util.HashMap" toScope="page"/>
		<bean:define id="mensajesErrores" name="CotizacionActionForm" property="mensajesErrores" type="java.util.HashMap" toScope="page"/>

		<br />
        <logic:present name="CotizacionActionForm" property="cotizacion">
		<html:hidden styleId="fechaIni" property="cotizacion.inicio" />
		<html:hidden styleId="fechaFin" property="cotizacion.termino" />
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
        	<tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('detallesNomina', this);" id="detallesNominaTd">DETALLES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('salud', this);" id="saludTd">ISAPRE</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('inpTab', this);" id="inpTabTd">INP/FONASA</td>
              <logic:notEmpty name="CotizacionActionForm" property="caja.nombre">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('caja', this);" id="cajaTd">CAJA</td>
              </logic:notEmpty>
              <logic:notEmpty name="CotizacionActionForm" property="nomMutual">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('mutual', this);" id="mutualTd">MUTUAL</td>
              </logic:notEmpty>
            </tr>
            <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otrospagos', this);" id="otrospagosTd" colspan="2">OTROS PAGOS</td>                        
				<td align="center" class="barra_tablas" colspan="5">&nbsp;</td>
            </tr>
            <tr>
              	<td colspan="7">
<!-- ANTECEDENTES --><div id="antecedentes" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizante.rut" maxlength="12" size="15" styleClass="campos" styleId="newRutTrabajador" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rut") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rut")%></td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizante.nombre" maxlength="30" size="45" styleClass="campos" styleId="newNombre" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombres") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombres")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				               		<html:text property="cotizante.apellidoPat" maxlength="20" size="35" styleClass="campos" styleId="apellidoPat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizante.apellidoMat" maxlength="20" size="35" styleClass="campos" styleId="apellidoMat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">G&eacute;nero</td>
				               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               		<html:select property="cotizante.idGeneroReal" styleClass="campos" styleId="idGeneroReal">				               		
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="generos" label="nombre" value="id" />
									</html:select>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Sucursal</td>
				               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"> 
				               		<html:select property="cotizante.idSucursal" styleClass="campos" styleId="idSucursal">
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="sucursales" label="nombre" value="idSucursal" />
									</html:select>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("sucursales") == null ? "" : ((java.util.HashMap)mensajesErrores).get("sucursales")%></td>
				           </tr>
				    	</table>
	              	</div>
	              	
    <input type="hidden" id="topeImpAFPPesos" value="<c:out value='${topeImpAFPPesos}'/>" />
    <input type="hidden" id="topeImpINPPesos" value="<c:out value='${topeImpINPPesos}'/>" />
	              	
<!--DETALLES  --> 	<div id="detallesNomina" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				     	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DETALLES DE COTIZACION</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           <tr> 
    							<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Monto de Reliquidaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               	$ <html:text property="cotizacion.reliquidacion" maxlength="8" size="12" styleId="montoReli" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);rentaImponible(this);" onkeyup="javascript:soloMonto(this, '');" onchange="javascript: recalculaINPRenta(); recalculaMutual();"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("reliquidacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("reliquidacion")%></td>
				           </tr>
				           <!-- tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Inicio</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<input type="text" readonly="readonly" disabled size="12" class="campos" id="fechaIniReli" name="fechaIniReli"/>
			                   		<a href="#" id="fIReli"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaIniReli', 'fIReli', true, document.getElementById('periodo').value);return false;"/></a>								
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaIniReli')" title="Limpia Fecha"/></a>
			                   	</td>
			                   	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniReli") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniReli")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Termino</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                <input type="text" readonly="readonly" disabled size="12" class="campos" id="fechaFinReli" name="fechaFinReli"/>
			                   		<a href="#" id="fFReli"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaFinReli', 'fFReli', true, document.getElementById('periodo').value);return false;"/></a>								
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaFinReli')" title="Limpia Fecha"/></a>
			                    </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaFinReli") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaFinReli")%></td>
				           </tr  -->
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Periodo Reliquida (AAAAMM)</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                <html:text property="periodoReli" maxlength="6" size="12" styleId="periodoReli" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" onchange="javascript: validaPeriodo(this.value);"/>
			                   		<a href="#" title="Limpia Periodo"></a>
			                    </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniReli") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniReli")%></td>
				           </tr>
				    	</table>
	              	</div>
<!-- SALUD -->		<div id="salud" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
                   				<td colspan="2" align="left" valign="middle" class="barra_tablas">INSTITUCION DE SALUD PREVISIONAL</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
		                 	</tr>
		                 	<tr> 
			                    <td width="30%" align="left" valign="middle" class="textos_formcolor">Isapre</td>
			                    <td align="left" valign="middle" class="textos_formularios">
			                      	<html:select property="cotizante.idEntSaludReal" styleClass="campos" styleId="idEntSaludReal" onfocus="javascript:guardaValorIsapre(this.value);" onchange="javascript: avisoCambioIsapre();">
										<html:optionsCollection property="entidadesSalud" label="nombre" value="id" />
									</html:select>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadSalud")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.saludObligISAPRE" maxlength="8" size="12" styleId="saludObligISAPRE" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludOblig")%></td>
		                 	</tr>
				    	</table>
		     		</div>
<!-- prevision -->  <div id="prevision" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
	<input type="hidden" value="<bean:write name='exigirValidacion'/>" id="exigirValidacion">
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ADMINISTRADORA DE FONDOS DE PENSIONES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Sistema Previsional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntPensionReal" styleClass="campos" styleId="idEntPensionReal" onclick="javascript: recalculaTotalAFP();" onchange="javascript:avisoINPaAFP();">
			                     		<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
<logic:notEqual name="CotizacionActionForm" property="entidadesPension" value="D">
			                   			<logic:iterate id="ent" name="CotizacionActionForm" property="entidadesPension">
				                   			<html:hidden property="id" value="${ent.sis}" styleId="porcentSis-${ent.id}"/>
										</logic:iterate>
		                   			</logic:notEqual>
		                   			<input type="hidden" id="tipoProcesoEdicion" value="<bean:write name='CotizacionActionForm' property='tipoProceso'/>">                   			
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.prevObligatorioAFP" maxlength="8" size="12" styleId="prevObligatorioAFP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>

							<%-- La lógica para "exigirValidacion" en S y O eran idénticas, de hecho para N también lo es, solo que en dicho caso los tag TR están ocultos --%>
							<c:set var="visibilidadSIS">
								<c:choose>
									<c:when test="${exigirValidacion == 'N'}">none</c:when>
									<c:otherwise>block</c:otherwise>
								</c:choose>
							</c:set>
		                 	<tr style="display: ${visibilidadSIS};">
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaPrevisionSIS(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
		                 	</tr>
		                 	<tr style="display: ${visibilidadSIS};">
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
		                 	</tr>

				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">SEGURO DE CESANTIA</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad AFC</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntAFCReal" styleClass="campos" styleId="idEntAFCReal">
			                     			<option value="${CotizacionActionForm.idSinAFP}">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para Seguro de Cesant&iacute;a</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.segCesRemImp" maxlength="8" size="12" styleId="segCesRemImp" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.segCesTrab" maxlength="8" size="12" styleId="segCesTrabajador" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		$ <html:text property="cotizacion.segCesEmpl" maxlength="8" size="12" styleId="segCesEmpresa" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteEmpresa") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteEmpresa")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Seguro Cesant&iacute;a</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalAfcDiv">$0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalSegCes")%></td>
		                 	</tr>
						</table>
						<table width="100%" border="0" cellpadding="0" cellspacing="1" id="trabajoPesadoTable">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">TRABAJO PESADO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tasa Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   		<html:select property="cotizacion.tasaTrabPesado" styleClass="campos" styleId="idTasaTraPesa" onchange="javaScript:recalculaTrabajo('montoReli');">
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="porcentajeTrabPesado" label="label" value="value" />
									</html:select>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Nombre Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<html:text property="cotizacion.tipoTrabPesado" maxlength="40" size="45" styleId="nombreTrabPesado" styleClass="campos" onkeyup="javascript:soloNomTrab(this);" onblur="javascript:soloNomTrab(this);recalculaTotalAFP();"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("idTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("idTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizacion.trabPesado" maxlength="8" size="12" styleId="montoTrabPesado" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotTrabPesado")%></td>
				        	</tr>
						</table><br>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
				           	<tr>
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>TOTAL A PAGAR AFP</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalPagarAfpDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalPrevision") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalPrevision")%></td>
				        	</tr>
				    	</table>
				    	<input type="hidden" id="exigirValidacion" value="<bean:write name='exigirValidacion'/>"/>
				    </div>
		     		<div id="inpTab" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
<!-- INP -->		<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">PARAMETROS INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad Ex-Caja</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntExCaja" styleClass="campos" styleId="idEntExCaja" onchange="javaScript:avisoCambioPension();" onfocus="javascript:guardaValorINP(this.value);">
			                     			<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesExCaja" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadExCaja") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadExCaja")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">C&oacute;digo R&eacute;gimen Impositivo</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
										<logic:present name="CotizacionActionForm" property="codRegImp">
				                     		<html:select property="cotizante.idRegimenImp" styleClass="campos" styleId="idRegimenImp" onchange="javaScript:cambiaCondINP();">
				                     			<option value="-1">Seleccione</option>
												<html:optionsCollection property="codRegImp" label="descripcion" value="idRegImpositivo" />
											</html:select>
											<logic:iterate id="codReg" name="CotizacionActionForm" property="codRegImp">
				                   				<input type="hidden" value="${codReg.tasaPension}" id="codReg-${codReg.idRegImpositivo}" name="codReg-${codReg.idRegImpositivo}"/>
				                   			</logic:iterate>
			                   			</logic:present>
			                   			<logic:notPresent name="CotizacionActionForm" property="codRegImp">
			                     		<html:select property="cotizante.idRegimenImp" styleClass="campos" styleId="idRegimenImp" onchange="javaScript:cambiaCondINP();">
			                     			<option value="-1">Seleccione</option>
										</html:select>
			                   			</logic:notPresent>
		                     		</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadRegImpositivo") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadRegImpositivo")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tasa de Cotizaci&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="tasaCotINP">0.0%</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
				        	</tr>
				        	<tr>
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fonasa</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
		                     		<input type="checkbox" name="fonasaCheck" id="fonasaCheck"/>
			                   		<c:choose>
			                   			<c:when test="${idCCAF != 0}">6.4%</c:when>
			                   			<c:otherwise>7.0%</c:otherwise>
			                   		</c:choose>		                     		
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
				        	</tr>
				        	<tr>
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">COTIZACIONES INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para INP</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.remImpPension" maxlength="8" size="12" styleId="remImpPension" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);cambiaCondINP();" onkeyup="javascript:soloMonto(this, '');" onchange="javascript: recalculaINPINP();" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponiblePension") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponiblePension")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pensi&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.prevObligatorioINP" maxlength="8" size="12" styleId="prevObligatorioINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("pensionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("pensionOblig")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n de Salud FONASA</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.saludObligFONASA" maxlength="8" size="12" styleId="saludObligFONASA" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fonasaOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fonasaOblig")%></td>
				        	</tr>
				           	<tr id="accTrabINPTR"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Accidentes del Trabajo</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.accTrabajoINP" maxlength="8" size="12" styleId="accTrabajoINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaciones</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalCotizINPDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalCotInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalCotInp")%></td>
				        	</tr>
				        </table>
				        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="rebajasINPTable">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">REBAJAS INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				        	<tr> 
				            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">ASIGNACION FAMILIAR</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           </tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tramo de Asignaci&oacute;n Familiar</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<span style="padding-bottom: 3px;"> 
			                    		<html:select property="cotizante.idTramoRealINP" styleClass="campos" styleId="idTramoINP" disabled="true">
			                     			<option value="-1">Seleccione</option>
											<html:optionsCollection property="tramosAsigFam" label="nombre" value="id" />
										</html:select>
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizante.numCargaSimpleINP" styleId="cargasSimplesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" readonly="true"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaternaINP" styleId="cargasMaternalesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" readonly="true"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidezINP" styleId="cargasInvalidezINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" readonly="true"/> Invalidez<br />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
				        </table><br>
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Saldo Final</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalPagarINPDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
				        	</tr>
			            </table>
			        </div>
<!-- CAJA -->		<div id="caja" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
		     		<input type="hidden" name="caja-porcentajeFonasa" id="caja-porcentajeFonasa" value="${CotizacionActionForm.caja.porcentajeFonasa}"/>
		     		<input type="hidden" name="caja-asigFam" id="caja-asigFam" value="${CotizacionActionForm.caja.asigFam}"/>
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">CCAF ADHERIDA ${CotizacionActionForm.caja.nombre}</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte CCAF ${CotizacionActionForm.aporteCCAFFON}%</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.aporteCaja" maxlength="8" size="12" styleId="aporteCaja" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteCcaf") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteCcaf")%></td>
				        	</tr>
				           <tr> 
				            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">ASIGNACION FAMILIAR</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           </tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tramo de Asignaci&oacute;n Familiar</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<span style="padding-bottom: 3px;"> 
			                    		<html:select property="cotizante.idTramoReal" styleClass="campos" styleId="idTramo">
			                     			<option value="-1">Seleccione</option>
											<html:optionsCollection property="tramosAsigFam" label="nombre" value="id" />
										</html:select>
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizante.numCargaSimple" styleId="cargasSimples" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaterna" styleId="cargasMaternales" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidez" styleId="cargasInvalidez" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
						</table>
		     		</div>
<!-- MUTUAL -->		<div id="mutual" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">INSTITUCION ACCIDENTES DEL TRABAJO: ${CotizacionActionForm.nomMutual}</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">(%) Tasa Cotizaci&oacute;n</td>
		                   		<td align="left" valign="middle" class="textos_formularios">${CotizacionActionForm.tasaMutual}</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.mutualImp" maxlength="8" size="12" styleId="rentaImpMutual" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImpMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImpMutual")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.accTrabajoMutual" maxlength="8" size="12" styleId="cotizacionMutual" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotizacionMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotizacionMutual")%></td>
				        	</tr>
		            	</table>
					</div>
<!--OTROS PAGOS     --><div id="otrospagos" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS OTROS PAGOS</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="barra_tablas">Monto AFBR</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.otrosAFBR" maxlength="8" size="12" styleId="otrosAFBR" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("otrosAFBR") == null ? "" : ((java.util.HashMap)mensajesErrores).get("otrosAFBR")%></td>
		                 	</tr>
		                 </table>
					</div>	
			  	</td>
            </tr>
        </table>
        </logic:present>
        <br />
		</td>
	</tr>
    <logic:present name="CotizacionActionForm" property="cotizacion">
	<tr align="center"> 
    	<td height="33" valign="top"><br />
    		<input type="button" value="${guardar}" onclick="javascript:enviar('${guardar}')" class="btn_grande" />
    		<input type="button" value="${cancelar}"  onclick="javascript:enviar('${cancelar}')" class="btn_grande" />
        </td>
    </tr>
    </logic:present>
</table>
</html:form>


	
<script language="javaScript"> 

var listaDivs = new Array('antecedentes', 'detallesNomina', 'salud', 'prevision', 'inpTab', 'caja', 'mutual', 'otrospagos');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";

var sinCaja = false, sinMutual = false;
var idAFPNinguna = ${CotizacionActionForm.idAFPNinguna};
var idSinAFP = ${CotizacionActionForm.idSinAFP};
var idFONASA = ${CotizacionActionForm.idFONASA};
var tramoASigFamNinguno = ${CotizacionActionForm.tramoASigFamNinguno};
var sinEntidadSalud = ${CotizacionActionForm.idSaludNinguna};

if ('${CotizacionActionForm.nomMutual}' == '')
	sinMutual = true;
if ('${CotizacionActionForm.caja.nombre}' == '')
	sinCaja = true;

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{//esto se ejecuta solo al cargar la pagina, no al cambiar tab
	if (document.getElementById("newRutTrabajador").value != ""){
		if ((document.getElementById("newRutTrabajador").value).length >2){
			document.getElementById("newRutTrabajador").value = (document.getElementById("newRutTrabajador").value).replace(' ','-');
		}
	}
	if (sinMutual)
	{
		listaDivs.splice(6, 1);
		document.getElementById("accTrabINPTR").style.display = 'block';
	} else //con mutual
		document.getElementById("accTrabINPTR").style.display = 'none';
	if (sinCaja)//sin caja
	{
		listaDivs.splice(5, 1);
		document.getElementById("rebajasINPTable").style.display = 'block';
	} else //con caja
		document.getElementById("rebajasINPTable").style.display = 'none';
	if (document.getElementById(tabDefault) != null)
		cambiaDiv(tabDefault);
	//document.getElementById('fechaIniReli').value = document.getElementById('fechaIni').value;
	//document.getElementById('fechaFinReli').value = document.getElementById('fechaFin').value;

	if (document.getElementById('idRegimenImp').value != -1)
		document.getElementById('tasaCotINP').innerHTML = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value + ' %';	
	
	if (document.getElementById("cargasSimples").value == "")
		document.getElementById("cargasSimples").value = 0;
	if (document.getElementById("cargasSimplesINP").value == "")
		document.getElementById("cargasSimplesINP").value = 0;
	if (document.getElementById("cargasMaternales").value == "")
		document.getElementById("cargasMaternales").value = 0;
	if (document.getElementById("cargasMaternalesINP").value == "")
		document.getElementById("cargasMaternalesINP").value = 0;
	if (document.getElementById("cargasInvalidez").value == "")
		document.getElementById("cargasInvalidez").value = 0;
	if (document.getElementById("cargasInvalidezINP").value == "")
		document.getElementById("cargasInvalidezINP").value = 0;		
	if (document.getElementById("saludObligISAPRE").value == "")
		document.getElementById("saludObligISAPRE").value = 0;
	if (document.getElementById("saludObligFONASA").value == "")
		document.getElementById("saludObligFONASA").value = 0;
	if (document.getElementById("prevObligatorioAFP").value == "")
		document.getElementById("prevObligatorioAFP").value = 0;
	if (document.getElementById("prevObligatorioINP").value == "")
		document.getElementById("prevObligatorioINP").value = 0;
	if (document.getElementById("montoReli").value == "")
		document.getElementById("montoReli").value = 0;
	var montoReli = document.getElementById("montoReli").value;
if (document.getElementById("rentaImponibleSIS").value == "")
		document.getElementById("rentaImponibleSIS").value = 0;
if (document.getElementById("previsionSIS").value == "")
		document.getElementById("previsionSIS").value = 0;				

	if (document.getElementById("segCesRemImp").value == "" || document.getElementById("segCesRemImp").value == 0)
		document.getElementById("segCesRemImp").value = montoReli;
	if (document.getElementById("segCesTrabajador").value == "")
		document.getElementById("segCesTrabajador").value = 0;
	if (document.getElementById("segCesEmpresa").value == "")
		document.getElementById("segCesEmpresa").value = 0;
	if (document.getElementById("montoTrabPesado").value == "")
		document.getElementById("montoTrabPesado").value = 0;
	if (document.getElementById("cotizacionMutual").value == "")
		document.getElementById("cotizacionMutual").value = 0;
	if (document.getElementById("accTrabajoINP").value == "")
		document.getElementById("accTrabajoINP").value = 0;
	if (document.getElementById("rentaImpMutual").value == "" || document.getElementById("rentaImpMutual").value == 0)
		document.getElementById("rentaImpMutual").value = montoReli;
	if (document.getElementById("aporteCaja").value == "")
		document.getElementById("aporteCaja").value = 0;
	if (document.getElementById("remImpPension").value == "" || document.getElementById("remImpPension").value == 0)
		document.getElementById("remImpPension").value = montoReli;
	recalculaTotalAFP();
	recalculaTotalINP();
	
	//clillo 27/1/15 AFBR	
	if (document.getElementById("otrosAFBR").value == "")
		document.getElementById("otrosAFBR").value = 0;
}

function enviar(op)
{
	if (op == '${guardar}')
	{
		var exigirValidacionSis = document.getElementById("exigirValidacion").value;
	
		if (exigirValidacionSis == 'O')
		{
			var idEntPensionReal  = document.getElementById("idEntPensionReal").value;
			var rentaImponibleSIS = document.getElementById("rentaImponibleSIS").value;
			var previsionSIS      = document.getElementById("previsionSIS").value;
			var periodoReli		  = document.getElementById("periodoReli").value;
			
			if(periodoReli == "" || periodoReli == "0" || isNaN(periodoReli) || periodoReli.length != 6){
				alert("Ingrese período de Reliquidación en formato AAAAMM");
				return false;
			}
			
			if ((idEntPensionReal  != -100 && idEntPensionReal != -1) &&
				(rentaImponibleSIS ==    0 || previsionSIS     ==  0))
			{
				if (!confirm("El trabajador no cuenta con cotización en Previsión SIS, ¿Esta seguro que desea continuar?"))
				{
					return false;
				}
			}
		}
	}

	if (op == '${guardar}')
	{
		var cmpPrepagado = ${cmpPrepagado};
		var nomPrepagada = ${nomPrepagada};
		if (cmpPrepagado == 1 || nomPrepagada == 1) {
			alert("Esta nómina tiene un comprobante en estado que no permite modificación, por lo tanto no se pueden realizar modificaciones.");
			return false;
		}

		if (validaFormReli()) {
			document.getElementById("respaldoOperacion").value = document.getElementById("operacion").value;
			document.getElementById("operacion").value = op;
			$.blockUI({
				message: '<img src="<c:url value="/img/loading.gif"/>" /><br>Guardando...',
				css: {
					width:           '180px',
					border:          '3px solid #85B4BE',
					backgroundColor: '#E1EBED'
				},
				overlayCSS: {
	      			backgroundColor: '#FFFFFF',
					opacity:         0.5
				}
			});
			document.forms[0].submit();
		}
	} else {
		document.getElementById("respaldoOperacion").value = document.getElementById("operacion").value;
		document.getElementById("operacion").value = op;	
		document.forms[0].submit();
	}
}

function validaFormReli()
{
	if(document.getElementById('primerCotizante').value == 'true'){
		if (!confirm("Si guarda el trabajador no va a poder editar la Mutual o Caja de Compensación de la Empresa, ¿Está seguro que desea continuar?"))
		{
			return false;
		}
	}
	
	if (document.getElementById("botonGuardar").value == "1") //para que el botón se apriete sólo una vez
		return false;

	//document.getElementById('fechaIni').value = document.getElementById('fechaIniReli').value;
	//document.getElementById('fechaFin').value = document.getElementById('fechaFinReli').value;
//campos formulario:
	//basicos
		//newRutTrabajador
		//newNombre
		//apellidoPat
		//apellidoMat
		//idGenero
		//idSucursal
	//texto
		//nombreTrabPesado		--
	//lista
		//idEntPensionReal		--
		//idEntSaludReal		--
		//idEntExCaja			--
		//idRegimenImp			--
		//idTramo				--
		//idTramoINP			--
		//idTasaTraPesa			--
	//numeros
		//cargasSimples			--
		//cargasSimplesINP		--
		//cargasMaternales		--
		//cargasMaternalesINP	--
		//cargasInvalidez		--
		//cargasInvalidezINP	--
	//montos
		//saludObligISAPRE		--
		//saludObligFONASA		--
		//prevObligatorioAFP	--
		//prevObligatorioINP	--
		//segCesRemImp			--
		//segCesTrabajador		--
		//segCesEmpresa			--
		//montoTrabPesado		--
		//cotizacionMutual		--
		//accTrabajoINP			--
		//rentaImpMutual		--
		//aporteCaja			--
		//montoReli			--
	//fechas (ya estan restringidas, no se validan)
		//fechaIniReli			--
		//fechaFinReli			--
	var msg = validaDataBasicaForm();
	var alertaPrevision = "";

//DETALLES
	if (!validaNumero(document.getElementById('montoReli').value, ''))
		msg += " - Monto para Reliquidación inválido.\n";
	var montoReli = new Number(limpiaNumero(document.getElementById('montoReli').value, ''));
	
	if (document.getElementById('idEntExCaja').value != -1)	
	{
		
		var topeImpINPPesos = document.getElementById('topeImpINPPesos').value

		if(montoReli>topeImpINPPesos) {
			msg += " - Monto de Reliquidación es mayor que el tope legal. $" + formatNumero(topeImpINPPesos) + "\n";
			document.getElementById('montoReli').value = formatNumero(topeImpINPPesos);
			recalculaINPRenta();
			recalculaMutual();
		}
	}else{
		var topeImpAFPPesos = document.getElementById('topeImpAFPPesos').value
	
		if(montoReli>topeImpAFPPesos){
			msg += " - Monto de Reliquidación es mayor que el tope legal. $" + formatNumero(topeImpAFPPesos) + "\n";
			document.getElementById('montoReli').value = formatNumero(topeImpAFPPesos);
			recalculaINPRenta();
			recalculaMutual();
		}
	}	
/*
	if (document.getElementById("fechaIniReli").value == '')
		msg += " - Debe seleccionar una Fecha de Inicio para la Reliquidación. \n";
	else if (!validarFecha(document.getElementById("fechaIniReli").value))
		msg += " - Fecha inicio inválida. \n";
	if (document.getElementById("fechaFinReli").value == '')
		msg += " - Debe seleccionar una Fecha de término para la Reliquidación. \n";
	else if (!validarFecha(document.getElementById("fechaFinReli").value))
		msg += " - Fecha término inválida. \n";
*/
	if (document.getElementById("periodoReli").value == '')
		msg += " - Debe ingresar un período para la Reliquidación. \n";
//SALUD
	if (!validaNumero(document.getElementById('saludObligFONASA').value, ''))
		msg += " - Monto para FONASA inválido.\n";
	if (!validaNumero(document.getElementById('saludObligISAPRE').value, ''))
		msg += " - Monto para ISAPRE inválido.\n";
	var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));
	var saludObligISAPRE = new Number(limpiaNumero(document.getElementById('saludObligISAPRE').value, ''));
	if (saludObligFONASA > 0 && saludObligISAPRE > 0)
		msg += " - Salud posee valores tanto para ISAPRE como para FONASA. Sólo se permite uno de los dos.\n";
//	else if (saludObligFONASA <= 0 && saludObligISAPRE <= 0  && montoReli > 0)
	// marco elimina el mensaje 
	// 	msg += " - Salud debe poseer valor para ISAPRE ó FONASA.\n";
	else
	{
		if (saludObligISAPRE > 0)
		{
			if (document.getElementById('idEntSaludReal').value == -1)
				msg += " - Debe seleccionar una Entidad para ISAPRE. \n";
		}
	}

//PREVISION
	if (!validaNumero(document.getElementById('prevObligatorioAFP').value, ''))
		msg += " - Monto Previsión AFP inválido.\n";
	if (!validaNumero(document.getElementById('prevObligatorioINP').value, ''))
		msg += " - Monto Previsión INP inválido.\n";
	var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
	var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
	var tipoPrevision = "ninguna";
	if (prevObligatorioAFP > 0 && prevObligatorioINP > 0)
		msg += " - Monto Previsión posee valores tanto para AFP como para INP. Sólo se permite uno de los dos.\n";
	else if (prevObligatorioAFP < 0 && prevObligatorioINP < 0)
		msg += " - Monto Previsión debe poseer valor para AFP ó INP.\n";
	else
	{
		if (prevObligatorioAFP == 0 && prevObligatorioINP == 0)
			alertaPrevision = " - Trabajador no cuenta con Cotización en Monto Previsión, ¿Esta seguro que desea continuar?\n";
		if (prevObligatorioAFP > 0)//AFP
		{
			document.getElementById('tipoPrevision').value = 1;
			document.getElementById('idEntExCaja').value = -1;
			document.getElementById('idRegimenImp').value = -1;
			if (document.getElementById('idEntPensionReal').value == -1)
				msg += " - Debe seleccionar una Entidad para Sistema Previsional. \n";
			tipoPrevision = "AFP";
		} else
		{
			if ( (document.getElementById('idEntPensionReal').value == idSinAFP || document.getElementById('idEntPensionReal').value == idAFPNinguna ) && ( document.getElementById('segCesRemImp').value > 0 || document.getElementById('segCesTrabajador').value > 0 || document.getElementById('segCesEmpresa').value > 0  )) {
				msg += " - Debe seleccionar una Entidad para Seguro de Cesantía. \n";
			}
			if (document.getElementById('idEntPensionReal').value == idSinAFP && document.getElementById('segCesRemImp').value == 0 && document.getElementById('segCesTrabajador').value == 0 && document.getElementById('segCesEmpresa').value == 0  ) {
				document.getElementById('idEntPensionReal').value = idAFPNinguna;
			}
			if (prevObligatorioINP > 0) //INP
			{
				document.getElementById('tipoPrevision').value = 2;
				if (document.getElementById('idEntExCaja').value == -1)
					msg += " - Debe seleccionar una Entidad Ex Caja para Sistema Previsional INP. \n";
				if (document.getElementById('idRegimenImp').value == -1)
					msg += " - Debe seleccionar un Código de Regimen Impositivo para Sistema Previsional INP. \n";
				tipoPrevision = "INP";
			}
		}
	}

	if (!validaNumero(document.getElementById('segCesRemImp').value, ''))
		msg += " - Monto Remuneración Imponible Seguro de Cesantía inválido.\n";
	if (!validaNumero(document.getElementById('segCesTrabajador').value, ''))
		msg += " - Monto Aporte Trabajador Seguro de Cesantía inválido.\n";
	if (!validaNumero(document.getElementById('segCesEmpresa').value, ''))
		msg += " - Monto Aporte Empleador Seguro de Cesantía inválido.\n";
	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var _segCesRemTope = Math.round(document.getElementById('topeCesantia').value);
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var segCesRemTope = new Number(limpiaNumero(document.getElementById('topeCesantia').value, ''));
	if (segCesRemImp < 0)
		msg += " - Debe ingresar un monto para Remuneración Imponible Seguro de Cesantía. \n";
	if (segCesRemImp > _segCesRemTope)
		msg += " - Monto para Remuneración Imponible Seguro de Cesantía mayor al tope (" + _segCesRemTope + "). \n";
	if (segCesTrabajador < 0)
		msg += " - Debe ingresar un monto para Aporte Trabajador Seguro de Cesantía. \n";
	if (segCesEmpresa < 0)
		msg += " - Debe ingresar un monto para Aporte Empleador Seguro de Cesantía. \n";

	if (!validaNumero(document.getElementById('montoTrabPesado').value, ''))
		msg += " - Monto Trabajo Pesado inválido.\n";
	var montoTrabPesado = creaNumero(document.getElementById('montoTrabPesado').value);
	var tieneTP = 0;
	if (montoTrabPesado > 0 || document.getElementById('idTasaTraPesa').value != -1 || document.getElementById('nombreTrabPesado').value != '')
		tieneTP = 1;
	if (tipoPrevision == "AFP")
	{
		if (tieneTP == 1)
		{
			if (document.getElementById('idTasaTraPesa').value == -1)
				msg += " - Debe seleccionar una Tasa para Trabajo Pesado. \n";
			if (document.getElementById('nombreTrabPesado').value == '')
				msg += " - Debe ingresar un nombre para Trabajo Pesado. \n";
			if(montoTrabPesado >0 && document.getElementById('idTasaTraPesa').value != -1 && segCesRemImp > 0){
				if(Math.round(montoReli * document.getElementById('idTasaTraPesa').value / 100) != montoTrabPesado)
					msg += " - Monto Trabajo Pesado debe ser " + Math.round(montoReli * document.getElementById('idTasaTraPesa').value / 100) + ". \n";
			}
		}
	} else if (tipoPrevision == "INP" && tieneTP == 1)
		msg += " - Cotizante es afiliado a INP, por lo que no debe cotizar Trabajo Pesado. \n";

//CAJA
	if (!sinCaja)//con caja
	{
		if (document.getElementById('idTramo').value != -1)
/*			msg += " - Debe seleccionar un Tramo de Asignación Familiar. \n";
		else  */
		{
			var cargasSimples = creaNumero(document.getElementById('cargasSimples').value);
			var cargasMaternales = creaNumero(document.getElementById('cargasMaternales').value);
			var cargasInvalidez = creaNumero(document.getElementById('cargasInvalidez').value);
			var tieneCargas = (cargasSimples > 0 || cargasMaternales > 0 || cargasInvalidez > 0  ? true : false);
			if (!validaNumero(document.getElementById('cargasSimples').value) || cargasSimples < 0)
				msg += " - Número de cargas Simples inválido. \n";
			if (!validaNumero(document.getElementById('cargasMaternales').value) || cargasMaternales < 0)
				msg += " - Número de cargas Maternales inválido. \n";
			if (!validaNumero(document.getElementById('cargasInvalidez').value) || cargasInvalidez < 0)
				msg += " - Número de cargas de Invalidez inválido. \n";
			if (document.getElementById('idTramo').value == tramoASigFamNinguno && tieneCargas)
				msg += " - No corresponde número de cargas familiares, si no selecciona un Tramo de Asignación Familiar. \n";
		}
		
		if ( document.getElementById('fonasaCheck').checked && creaNumero(document.getElementById('aporteCaja').value) < 0)
			msg += " - Monto Aporte CCAF incorrecto (" + monto + "). \n";
		else if (!document.getElementById('fonasaCheck').checked && creaNumero(document.getElementById('aporteCaja').value) > 0)
			msg += " - Monto Aporte CCAF no debe aparecer si no es afiliado a FONASA. \n";
	}
//MUTUAL
	if (!sinMutual) //con mutual
	{
		if (!validaNumero(document.getElementById('rentaImpMutual').value, ''))
			msg += " - Monto Renta Imponible MUTUAL inválido.\n";
		var rentaImpMutual = new Number(limpiaNumero(document.getElementById('rentaImpMutual').value));
		if (new Number(limpiaNumero(document.getElementById('rentaImpMutual').value, '')) < 0)
			msg += " - Monto Renta Imponible MUTUAL debe ser mayor o igual a cero. \n";
		if (!validaNumero(document.getElementById('cotizacionMutual').value, ''))
			msg += " - Monto Cotización MUTUAL inválido.\n";
		 else
		 {
		 	monto = calcula(rentaImpMutual / 100, parseFloat(${CotizacionActionForm.tasaMutual}));
			if (monto != new Number(limpiaNumero(document.getElementById('cotizacionMutual').value)))
		 		msg += " - Monto Cotización MUTUAL incorrecto ( valor calculado: " + monto + "). \n";
		}
	}

//INP
	if (sinMutual)
	{
		if (!validaNumero(document.getElementById('accTrabajoINP').value, ''))
			msg += " - Monto Accidentes del Trabajo inválido.\n";
		else if (new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, '')) < 0)
			msg += " - Monto Accidentes del Trabajo debe ser mayor o igual a cero. \n";
	}
	if (sinCaja)
	{
/*
		if (document.getElementById('idTramoINP').value == -1)
			msg += " - Debe seleccionar un Tramo de Asignación Familiar. \n";
*/
		if (!validaNumero(document.getElementById('cargasSimplesINP').value))
			msg += " - Número de cargas Simples inválido. \n";
		else if (creaNumero(document.getElementById('cargasSimplesINP').value) < 0)
			msg += " - Número de cargas Simples inválido. \n";
		if (!validaNumero(document.getElementById('cargasMaternalesINP').value))
			msg += " - Número de cargas Maternales inválido. \n";
		else if (creaNumero(document.getElementById('cargasMaternalesINP').value) < 0)
			msg += " - Número de cargas Maternales inválido. \n";
		if (!validaNumero(document.getElementById('cargasInvalidezINP').value))
			msg += " - Número de cargas de Invalidez inválido. \n";
		else if (creaNumero(document.getElementById('cargasInvalidezINP').value) < 0)
			msg += " - Número de cargas de Invalidez inválido. \n";
	}

	if (msg != '')
	{
		alert(msg);
		return false;
	} else if (alertaPrevision != '')
	{
		if (confirm(alertaPrevision))
		{
			document.getElementById('tipoPrevision').value = 0;
			document.getElementById("botonGuardar").value = "1";
			return true;
		} else 
			return false;
	}
	document.getElementById("botonGuardar").value = "1";
	return true;
}

function rentaImponible(){
	var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
	
	if (rentaImponibleSIS == 0){
		document.getElementById('rentaImponibleSIS').value = new Number(limpiaNumero(document.getElementById('rentaImp').value, ''));
	}

}

function recalculaPrevisionSIS()
{
	if(document.getElementById('tipoProcesoEdicion').value != 'D')
	{
		if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		{
			var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
			var previsionSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value) / 100;
			document.getElementById('previsionSIS').value = formatNumero(Math.round(rentaImponibleSIS * previsionSIS));	
		}
	}
}

function recalculaTotalAFP()
{
	if(document.getElementById('idEntPensionReal').value == idAFPNinguna || document.getElementById('idEntPensionReal').value == idSinAFP)
	{
		document.getElementById('prevObligatorioAFP').value = 0;	
		document.getElementById('segCesRemImp').value = 0;
		document.getElementById('segCesTrabajador').value = 0;
		document.getElementById('segCesEmpresa').value = 0;
		document.getElementById('idTasaTraPesa').value = -1;
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;
	}else{
		document.getElementById('idEntExCaja').value = -1;
		document.getElementById('idRegimenImp').value = -1;
     	document.getElementById('tasaCotINP').innerHTML = '0.0 %';
     	
     	document.getElementById("idEntAFCReal").value = document.getElementById('idEntPensionReal').value;
		document.getElementById("idEntAFCReal").disabled = true;
	}
	if(document.getElementById("idEntPensionReal").value == -1)
	{
		document.getElementById('prevObligatorioAFP').value = 0;	
		document.getElementById('segCesRemImp').value = 0;
		document.getElementById('segCesTrabajador').value = 0;
		document.getElementById('segCesEmpresa').value = 0;
		document.getElementById('idTasaTraPesa').value = -1;
		document.getElementById('nombreTrabPesado').value = '';
		document.getElementById('montoTrabPesado').value = 0;
	}
	
	var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	//csanchez. No se estaba agregando el SIS al "TOTAL A PAGAR AFP"
	var previsionSIS = new Number(limpiaNumero(document.getElementById('previsionSIS').value, ''));
	var total = new Number(0);
	var totalAfcDiv = new Number(0);

	if (prevObligatorioAFP > 0)		
		total += prevObligatorioAFP;

	if (segCesTrabajador > 0)
		totalAfcDiv += segCesTrabajador;
	if (segCesEmpresa > 0)
		totalAfcDiv += segCesEmpresa;
	document.getElementById('totalAfcDiv').innerHTML = "$ " + formatNumero(totalAfcDiv);
	total += totalAfcDiv;

	if (montoTrabPesado > 0)
		total += montoTrabPesado;

	//csanchez. Idem, SIS
	total += previsionSIS;
	document.getElementById("totalPagarAfpDiv").innerHTML ='$ ' + formatNumero(total);				
	
	if(document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP){
		recalculaPrevisionSIS();
	}
}

function recalculaMutual()
{
	document.getElementById('rentaImpMutual').value = document.getElementById("montoReli").value;
	monto = creaNumero(document.getElementById('rentaImpMutual').value);
	document.getElementById('cotizacionMutual').value = Math.round(monto / 100 * parseFloat(${CotizacionActionForm.tasaMutual}));
}

function recalculaPrevisionSIS()
{
	if(document.getElementById('tipoProcesoEdicion').value != 'D')
	{
		if (document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP)
		{
			var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
			var previsionSIS = new Number(document.getElementById('porcentSis-' + document.getElementById('idEntPensionReal').value).value) / 100;
			document.getElementById('previsionSIS').value = Math.round(rentaImponibleSIS * previsionSIS);	
		}
	}
}

function rentaImponible(){
	var rentaImponibleSIS = new Number(limpiaNumero(document.getElementById('rentaImponibleSIS').value, ''));
	
	if (rentaImponibleSIS == 0){
		document.getElementById('rentaImponibleSIS').value = new Number(limpiaNumero(document.getElementById('montoReli').value, ''));
	}

}

function recalculaINPRenta()
{
	document.getElementById('remImpPension').value = document.getElementById("montoReli").value;
	recalculaTotalINP();
}

function recalculaINPINP()
{
	if(document.getElementById('idEntSaludReal').value == '-1')//si es FONASA
	{
		var monto = creaNumero(document.getElementById('remImpPension').value);
		var aporteCaja = creaDecimal(document.getElementById('aporteCCAFFON').value);
		var tasaFONASA = creaDecimal(document.getElementById('aporteINPFON').value);
		if (sinCaja)
			document.getElementById('saludObligFONASA').value = Math.round(monto * tasaFONASA);
		else
		{
			document.getElementById('saludObligFONASA').value = Math.round(monto * (tasaFONASA - (aporteCaja / 100)));
			document.getElementById('aporteCaja').value = calcula(monto / 100, aporteCaja);
		}
	}
}

function limpiaAFP()
{
	document.getElementById('idEntPensionReal').value = idAFPNinguna;
	document.getElementById('prevObligatorioAFP').value = 0;
	document.getElementById('segCesRemImp').value = 0;
	document.getElementById('segCesTrabajador').value = 0;
	document.getElementById('segCesEmpresa').value = 0;
	document.getElementById('idTasaTraPesa').value = '-1';
	document.getElementById('nombreTrabPesado').value = '';
	document.getElementById('montoTrabPesado').value = 0;
	document.getElementById('rentaImponibleSIS').value = 0;
	document.getElementById('previsionSIS').value = 0;
	document.getElementById('totalAfcDiv').innerHTML = '$ 0';
	document.getElementById("idEntAFCReal").disabled = false;	
}

function recalculaTotalINP()
{
	var total = new Number(0);

	var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
	var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));

	/*if (prevObligatorioINP <= 0 && document.getElementById("idRegimenImp").value > 0)
	{
		var id = document.getElementById("idRegimenImp").value;
		var prevObligatorioINPTmp = Math.round(document.getElementById("codReg-" + id).value * document.getElementById("remImpPension").value.replace(/\./gi,"") / 100);
		document.getElementById('prevObligatorioINP').value = formatNumero(prevObligatorioINPTmp);
	}*/
	total += prevObligatorioINP;

	if (saludObligFONASA > 0)
		total += saludObligFONASA;
	if (sinMutual)
	{
		var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
		if (accTrabajoINP > 0)
			total += accTrabajoINP;
	}
	document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(total);
	document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(total);
}

if (document.getElementById("operacion").value == "mod") {
	document.getElementById("newRutTrabajador").readOnly = true;
	document.getElementById("newNombre").readOnly		 = true;
	document.getElementById("apellidoPat").readOnly		 = true;
	document.getElementById("apellidoMat").readOnly		 = true;
	//clillo 15-1-15 por Reliquidación
	if(document.getElementById("periodoReli").value.length==6){
		document.getElementById("periodoReli").readOnly		 = true;
	}
}

function limpiaAFPnoAFC() {
	var aux_segCesRemImp     = document.getElementById("segCesRemImp").value;
	var aux_segCesTrabajador = document.getElementById("segCesTrabajador").value;
	var aux_segCesEmpresa    = document.getElementById("segCesEmpresa").value;
	limpiaAFP();
	document.getElementById("segCesRemImp").value     = aux_segCesRemImp;
	document.getElementById("segCesTrabajador").value = aux_segCesTrabajador;
	document.getElementById("segCesEmpresa").value    = aux_segCesEmpresa;
}

function guardaValorINP(valor) {
	document.getElementById("valorINPPrevio").value = valor;
}

function avisoCambioPension() {

	if(document.getElementById("idEntExCaja").value != "-1")
		if(!confirm("Al seleccionar una INP se borrarán todos los datos que haya ingresado para la AFP ¿Desea continuar?")) {
			document.getElementById("idEntExCaja").value = document.getElementById("valorINPPrevio").value;
			return false;
		}

	document.getElementById("respaldoOperacion").value = document.getElementById("operacion").value;		
	limpiaAFPnoAFC();
	cambiaExCaja();
}
function validaPeriodo(periodo){
	if(periodo.length<6){
		alert("Ingrese periodo en formato AAAAMM, ejemplo: 201401");
		return false;
	}
	if(periodo.length==6){
		anio= periodo.substring(0, 4);
		mes= periodo.substring(4);
		periodoactual= document.getElementById("periodo").value;
		anioactual= periodoactual.substring(0, 4);
		if(Number(anio)<2000 || Number(anio)>Number(anioactual)){
			alert("Año incorrecto. Ingrese periodo en formato AAAAMM, ejemplo: 201401");
			return false;
		}
		if(Number(mes)<1 || Number(mes)>Number(12)){
			alert("Mes incorrecto. Ingrese periodo en formato AAAAMM, ejemplo: 201401");
			return false;
		}
	}
}
// End --> 
</script>

<script type="text/javascript">
/*
$(document).ready(function(){

	if ($("#operacion").val() == "mod") {
		//Elimina las otras opciones para evitar la modificación del campo
		valor = $("#idGenero").val();
		$("#idGenero").find("option[value!="+valor+"]").remove();
	}

	var idSalud = "<bean:write name ='CotizacionActionForm' property='cotizante.idEntSaludReal'/>";
	       
   	if(idSalud == idFONASA){
   		alert("1");
   		document.getElementById("idEntSaludReal").value = sinEntidadSalud;   
   		//document.getElementById("fonasaCheck").disabled = true;
   		$("input[name=fonasaCheck]").attr('checked',true);
   	}if(idSalud == sinEntidadSalud){
   		alert("1");
   		document.getElementById("idEntSaludReal").value = sinEntidadSalud;   
   		//document.getElementById("fonasaCheck").disabled = true;
   		$("input[name=fonasaCheck]").attr('checked',false);
   	}if (idSalud != sinEntidadSalud && idSalud != idFONASA){
   		alert("3");
   		$("input[name=fonasaCheck]").attr('checked',false);
   	}  
   	alert("fin");		
});*/
</script>