<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>

<html:form action="/EditarCotizacion">
<input type="hidden" id="botonGuardar" value="0" />
<html:hidden styleId="accion"			property="accion"		name="accion"		value="inicio" />
<html:hidden styleId="subAccion"		property="subAccion"	name="subAccion"	value="trabajadores"/>
<html:hidden styleId="subSubAccion"		property="subSubAccion"	name="subSubAccion"	value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa"		property="rutEmpresa" />
<html:hidden styleId="rutTrabajador"	property="rutTrabOrigin" />
<html:hidden styleId="convenio"			property="idConvenio" />
<html:hidden styleId="idMutual"			property="idMutual" />
<html:hidden styleId="aporteINPFON"		property="aporteINPFON" />
<html:hidden styleId="aporteCCAFFON"	property="aporteCCAFFON" />
<html:hidden styleId="idCotizPendiente"	property="idCotizPendiente" />
<html:hidden styleId="topeAFP" 			property="topeAFP" />
<html:hidden styleId="topeINP"			property="topeINP" />
<html:hidden styleId="topeCesantia"		property="topeCesantia" />
<html:hidden styleId="apEmpIndSegCes"	property="apEmpIndSegCes" />
<html:hidden styleId="apEmpPFSegCes"	property="apEmpPFSegCes" />
<html:hidden styleId="apTrabIndSegCes"	property="apTrabIndSegCes" />
<html:hidden styleId="apTrabPFSegCes"	property="apTrabPFSegCes" />
<html:hidden styleId="tipoPrevision"	property="tipoPrevision" />
<html:hidden styleId="mostrar"			property="mostrar" />
<html:hidden styleId="operacion"		property="operacion" />
<html:hidden styleId="respaldoOperacion" property="respaldoOperacion" />
<html:hidden styleId="periodo"			property="periodo" />

<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" id="opExCaja" name="opExCaja" value=""/>
<input type="hidden" id="idCaja"   name="idCaja"   value="${CotizacionActionForm.caja.id}"/>

<html:hidden styleId="flgTrabNomina" property="flgTrabNomina"/>

<input type="hidden" id="valorINPPrevio" />
<input type="hidden" id="datosSis" value="<bean:write name='datosSis'/>" />

<c:set var="idCCAF" scope="page"><bean:write name='idCCAF'/></c:set>

<script type="text/javascript">
$(document).ready(function(){
	    
   	if(document.getElementById('idEntPensionReal').value != idAFPNinguna && document.getElementById('idEntPensionReal').value != idSinAFP){
   		document.getElementById("idEntAFCReal").value = document.getElementById('idEntPensionReal').value;   
   		document.getElementById("idEntAFCReal").disabled = true;   	
   	}		
});
</script>

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	    <logic:equal name="CotizacionActionForm" property="mostrar" value="new">
	    	<html:hidden styleId="tipoProceso" property="tipoProceso" />
	        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
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
		        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
		     	</tr>
		    </table>
	    </logic:equal>
	    <logic:notEqual name="CotizacionActionForm" property="mostrar" value="new">
	    	<logic:equal name="CotizacionActionForm" property="mostrar" value="pen">
		    	<html:hidden styleId="tipoProceso" property="tipoProceso" />
		        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
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
			        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
			     	</tr>
			    </table>
	    	</logic:equal>
	    	<logic:equal name="CotizacionActionForm" property="mostrar" value="ap">	    	
		        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
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
			      		<td align="center"><input type="submit" value="${aplicar}" class="btn4" onclick="javascript:enviarGrati('${aplicar}')"/></td>
			      	</tr>
			      	<tr> 
			        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
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
<!--DETALLES  --> 	<div id="detallesNomina" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="gratiTable">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DETALLES DE COTIZACION</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           <tr> 
    							<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Monto de Gratificaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				 			    $ <html:text property="cotizacion.gratificacion" maxlength="8" size="12" styleId="montoGrati" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);rentaImponible(this);" onkeyup="javascript:soloMonto(this, '');" onchange="javascript:cambiaGratificacion();"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("gratificacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("gratificacion")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Inicio</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<input type="text" size="12" class="campos" id="fechaIniGrat" name="fechaIniGrat" readonly="true"/>
			                   		<a href="#" id="fIGrati"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaIniGrat', 'fIGrati', true, document.getElementById('periodo').value); return false;"/></a>
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaIniGrat')" title="Limpia Fecha"/></a>
			                   	</td>
			                   	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniGrati") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniGrati")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Termino</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                <input type="text" size="12" class="campos" id="fechaFinGrati" name="fechaFinGrati" readonly="true"/>
			                   		<a href="#" id="fFGrati"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaFinGrati', 'fFGrati', true, document.getElementById('periodo').value);return false;"/></a>
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaFinGrati')" title="Limpia Fecha"/></a>
			                    </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaFinGrati") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaFinGrati")%></td>
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
			                      	<html:select property="cotizante.idEntSaludReal" styleClass="campos" styleId="idEntSaludReal" onchange="javascript:recalculaSalud();javascript:recalculaINP();">
			               				<html:optionsCollection property="entidadesSalud" label="nombre" value="id" />
									</html:select>
									<logic:iterate id="porcentajeSalud" name="CotizacionActionForm" property="entidadesSalud">
				                   		<html:hidden property="tasaSalud_${porcentajeSalud.id}" value="${porcentajeSalud.tasaSalud}"/>
		                   			</logic:iterate>
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
			                     		<html:select property="cotizante.idEntPensionReal" styleClass="campos" styleId="idEntPensionReal" onchange="javascript:recalculaPensionAFP();">
			                     			<option value="${CotizacionActionForm.idSinAFP}">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
									<logic:iterate id="entAFP" name="CotizacionActionForm" property="entidadesPension">
		                   				<logic:equal name="exigirValidacion" value="S">
											<html:hidden property="id" value="${entAFP.tasaAFPSinSIS}" styleId="entAFP-${entAFP.id}"/>
										</logic:equal>
										<logic:equal name="exigirValidacion" value="N">
											<html:hidden property="id" value="${entAFP.tasaAFPConSIS}" styleId="entAFP-${entAFP.id}"/>
										</logic:equal>
										<logic:equal name="exigirValidacion" value="O">
											<html:hidden property="id" value="${entAFP.tasaAFPSinSIS}" styleId="entAFP-${entAFP.id}"/>
										</logic:equal>
		                   			</logic:iterate>
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
		                   			$ <html:text property="cotizacion.prevObligatorioAFP" maxlength="8" size="12" styleId="prevObligatorioAFP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);calculaTotales('AFP');" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
<logic:equal name="exigirValidacion" value="S">
			                 	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaPrevisionSIS(this);" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
		                 	<logic:equal name="exigirValidacion" value="O">
			                 	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaPrevisionSIS(this);" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
		                 	<logic:equal name="exigirValidacion" value="N">
			                 	<tr style="display: none;"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaPrevisionSIS(this);" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr style="display: none;"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
			                     	<html:hidden property="cotizacion.previsionTotal" styleId="totalAfp"/>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
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
		                   			$ <html:text property="cotizacion.segCesRemImp" maxlength="8" size="12" styleId="segCesRemImp" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.segCesTrab" maxlength="8" size="12" styleId="segCesTrabajador" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);calculaTotales('AFP');" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		$ <html:text property="cotizacion.segCesEmpl" maxlength="8" size="12" styleId="segCesEmpresa" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);calculaTotales('AFP');" onkeyup="javascript:soloMonto(this, '');"/>
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
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">TRABAJO PESADO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tasa Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   		<html:select property="cotizacion.tasaTrabPesado" styleClass="campos" styleId="idTasaTraPesa" onchange="javaScript:recalculaTrabajo('montoGrati');calculaTotales('AFP');">
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="porcentajeTrabPesado" label="label" value="value" />
									</html:select>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Nombre Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<html:text property="cotizacion.tipoTrabPesado" maxlength="40" size="45" styleId="nombreTrabPesado" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("idTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("idTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizacion.trabPesado" maxlength="8" size="12" styleId="montoTrabPesado" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);calculaTotales('AFP');" onkeyup="javascript:soloMonto(this, '');"/>
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
		                     		<input type="checkbox" name="fonasaCheck" id="fonasaCheck" />
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
		                     		$ <html:text property="cotizacion.remImpPension" maxlength="8" size="12" styleId="remImpPension" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');" onchange="javascript:recalculaINP();"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponiblePension") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponiblePension")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pensi&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.prevObligatorioINP" maxlength="8" size="12" styleId="prevObligatorioINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaINP();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("pensionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("pensionOblig")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n de Salud FONASA</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.saludObligFONASA" maxlength="8" size="12" styleId="saludObligFONASA" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaINP();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fonasaOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fonasaOblig")%></td>
				        	</tr>
				           	<tr id="accTrabINPTR"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Accidentes del Trabajo</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.accTrabajoINP" maxlength="8" size="12" styleId="accTrabajoINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaINP();" onkeyup="javascript:soloMonto(this, '');"/>
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
			                    		<html:select property="cotizante.idTramoRealINP" styleClass="campos" styleId="idTramoINP">
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
		                     		<html:text property="cotizante.numCargaSimpleINP"    styleId="cargasSimplesINP"    maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaternaINP"   styleId="cargasMaternalesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidezINP" styleId="cargasInvalidezINP"  maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
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
		                     		<html:text property="cotizante.numCargaSimple"    styleId="cargasSimples"    maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaterna"   styleId="cargasMaternales" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidez" styleId="cargasInvalidez"  maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
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
		                     		$ <html:text property="cotizacion.mutualImp" maxlength="8" size="12" styleId="rentaImpMutual" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaMutual();" onkeyup="javascript:soloMonto(this, '');"/>
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
			  	</td>
            </tr>
        </table>
        </logic:present>
        <br />
		</td>
	</tr>
    <logic:present name="CotizacionActionForm" property="cotizacion">
	<tr align="center"> 
    	<td height="33" valign="top">
    		<br />
    		<input type="button" value="${guardar}"  onclick="javascript:enviarGrati('${guardar}')"  class="btn3" />
    		<input type="button" value="${cancelar}" onclick="javascript:enviarGrati('${cancelar}')" class="btn3" />
        </td>
    </tr>
    </logic:present>
</table>
</html:form>


	
<script language="javaScript"> 

var listaDivs = new Array('antecedentes', 'detallesNomina', 'salud', 'prevision', 'inpTab', 'caja', 'mutual');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";

var sinCaja = false, sinMutual = false;
var idAFPNinguna = ${CotizacionActionForm.idAFPNinguna};
var idSinAFP = ${CotizacionActionForm.idSinAFP};
var idFONASA = ${CotizacionActionForm.idFONASA};
var tasaMutual = ${CotizacionActionForm.tasaMutual};
var tramoASigFamNinguno = ${CotizacionActionForm.tramoASigFamNinguno};
var sinEntidadSalud = ${CotizacionActionForm.idSaludNinguna};

if ('${CotizacionActionForm.nomMutual}' == '')
	sinMutual = true;
if ('${CotizacionActionForm.caja.nombre}' == '')
	sinCaja = true;

</script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validaGrati.js" />"></script>
<script language="javaScript"> 
if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{//esto se ejecuta solo al cargar la pagina, no al cambiar tab
	var rutTmp = document.getElementById("newRutTrabajador").value;
	if (rutTmp.length >2)
		document.getElementById("newRutTrabajador").value = rutTmp.replace(' ','-');

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
	document.getElementById('fechaIniGrat').value  = document.getElementById('fechaIni').value;
	document.getElementById('fechaFinGrati').value = document.getElementById('fechaFin').value;

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
	if (document.getElementById("cargasInvalidezINP").value == "")
		document.getElementById("cargasInvalidezINP").value = 0;		
	if (document.getElementById("cargasInvalidez").value == "")
		document.getElementById("cargasInvalidez").value = 0;
	if (document.getElementById("saludObligISAPRE").value == "")
		document.getElementById("saludObligISAPRE").value = 0;
	if (document.getElementById("saludObligFONASA").value == "")
		document.getElementById("saludObligFONASA").value = 0;
	if (document.getElementById("prevObligatorioAFP").value == "")
		document.getElementById("prevObligatorioAFP").value = 0;
	if (document.getElementById("prevObligatorioINP").value == "")
		document.getElementById("prevObligatorioINP").value = 0;
	if (document.getElementById("montoGrati").value == "")
		document.getElementById("montoGrati").value = 0;
	var montoGrati = document.getElementById("montoGrati").value;
	//jlandero. Solicitado en mantención 25/08/11
	/*if (document.getElementById("segCesRemImp").value == "" || document.getElementById("segCesRemImp").value == 0)
		document.getElementById("segCesRemImp").value = montoGrati;*/
	if (document.getElementById("rentaImponibleSIS").value == "")
		document.getElementById("rentaImponibleSIS").value = 0;
	if (document.getElementById("previsionSIS").value == "")
		document.getElementById("previsionSIS").value = 0;	
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
		document.getElementById("rentaImpMutual").value = montoGrati;
	if (document.getElementById("aporteCaja").value == "")
		document.getElementById("aporteCaja").value = 0;
	if (document.getElementById("remImpPension").value == "" || document.getElementById("remImpPension").value == 0) {
		if (document.getElementById("idEntExCaja").value != "-1") {
			document.getElementById("remImpPension").value = montoGrati;
		}
	}

	recalculaTipoPrevision();
	calculaTotales('');//INP y AFP
}

function enviarGrati(op)
{
	if (op == '${guardar}')
	{
		var exigirValidacionSis = document.getElementById("exigirValidacion").value;

		//if (exigirValidacionSis == 'O')
		//{
		//	var idEntPensionReal  = document.getElementById("idEntPensionReal").value;
		//	var rentaImponibleSIS = document.getElementById("rentaImponibleSIS").value;
		//	var previsionSIS      = document.getElementById("previsionSIS").value;

		//	if ((idEntPensionReal  != -100 && idEntPensionReal != -1) &&
		//		(rentaImponibleSIS ==    0 || previsionSIS     ==  0))
		//	{
		//		if (!confirm("El trabajador no cuenta con cotización en Previsión SIS, ¿Está seguro que desea continuar?"))
		//		{
		//			return false;
		//		}
		//	}
		//}
	}
	/*document.getElementById("respaldoOperacion").value = document.getElementById("operacion").value;
	document.getElementById("operacion").value = op;*/
	if (op == '${guardar}')
	{
		if (validaFormGrati()) {
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

if (document.getElementById("operacion").value == "mod") {
	document.getElementById("newRutTrabajador").readOnly = true;
	/*document.getElementById("newNombre").readOnly		 = true;
	document.getElementById("apellidoPat").readOnly		 = true;
	document.getElementById("apellidoMat").readOnly		 = true;*/
}

function asignaFechas() {
	document.getElementById('fechaIni').value = document.getElementById('fechaIniGrat').value;
	document.getElementById('fechaFin').value = document.getElementById('fechaFinGrati').value;
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
	asignaFechas();
	cambiaExCaja();
}

if (document.getElementById('datosSis').value == 'false') {
	document.getElementById('rentaImponibleSIS').readOnly = true;
	document.getElementById('previsionSIS').readOnly = true;
}
</script>

<script type="text/javascript">
$(document).ready(function(){

   	if ($("#operacion").val() == "mod") {
		//Elimina las otras opciones para evitar la modificación del campo
		valor = $("#idGenero").val();
		$("#idGenero").find("option[value!="+valor+"]").remove();
	}
	 
	var idSalud = "<bean:write name ='CotizacionActionForm' property='cotizante.idEntSaludReal'/>";
	       
   	if (idSalud == idFONASA) {
   		document.getElementById("idEntSaludReal").value = sinEntidadSalud;   
   		//document.getElementById("fonasaCheck").disabled = true;
   		$("input[name=fonasaCheck]").attr('checked',true);
   	}
   	if (idSalud == sinEntidadSalud) {
   		document.getElementById("idEntSaludReal").value = sinEntidadSalud;   
   		//document.getElementById("fonasaCheck").disabled = true;
   		$("input[name=fonasaCheck]").attr('checked',false);
   	}
   	if (idSalud != sinEntidadSalud && idSalud != idFONASA) {
   		$("input[name=fonasaCheck]").attr('checked',false);
   	}

   	$("#fonasaCheck").click(function(){
		if ($("#fonasaCheck").attr("checked")) {
			$("#idEntSaludReal").val("-1");
			recalculaSalud();
			recalculaTotalINP();
		} else {
			$("#saludObligFONASA").attr("value", "0");
			$("#aporteCaja").attr("value", "0");
			recalculaTotalINP();
		}
   	});

	$("#idEntSaludReal").change(function(){
   		if ($("#idEntSaludReal").val() != "-1") {
   			$("input[name=fonasaCheck]").attr('checked', false);
   		}
   	});
});
</script>