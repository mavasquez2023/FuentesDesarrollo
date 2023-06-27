<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery-1.3.2.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>

<html:form action="/EditarCotizacion">
<input type="hidden" id="botonGuardar" value="0" />
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="trabajadores"/>
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa" property="rutEmpresa" />
<html:hidden styleId="rutTrabajador" property="rutTrabOrigin" />
<html:hidden styleId="convenio" property="idConvenio" />
<html:hidden styleId="idCotizPendiente" property="idCotizPendiente" />
<html:hidden styleId="topeIndemn" property="topeIndemn" />
<html:hidden styleId="minTasaIndemn" property="minTasaIndemn" />
<html:hidden styleId="maxTasaIndemn" property="maxTasaIndemn" />
<html:hidden styleId="mostrar" property="mostrar" />
<html:hidden styleId="operacion" property="operacion" />
<html:hidden styleId="periodo" property="periodo" />
<html:hidden styleId="respaldoOperacion" property="respaldoOperacion" />

<html:hidden styleId="flgTrabNomina" property="flgTrabNomina"/>

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
			      		<td align="center"><input type="submit" value="${aplicar}" class="btn4" onclick="javascript:enviar('${aplicar}')"/></td>
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
		<html:hidden styleId="fechaIni" property="cotizacion.indemInicio" />
		<html:hidden styleId="fechaFin" property="cotizacion.indemTermino" />
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
			<tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('deposito', this);" id="depositoTd">DEPOSITO CONVENIDO</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('indemnizacion', this);" id="indemnizacionTd">INDEMNIZACIONES</td>
            </tr>
            <tr>
              	<td colspan="3">
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
<!--DEP CONV     --><div id="deposito" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">DEPOSITO CONVENIDO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;">
			                     		<html:select name="CotizacionActionForm" property="cotizacion.idEntDep" styleClass="campos" styleId="idEntDep">
			                     		<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesApvs" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Dep&oacute;sito Convenido</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.depositoConvenido" maxlength="8" size="12" styleId="depositoConvenido" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("depositoConvenido") == null ? "" : ((java.util.HashMap)mensajesErrores).get("depositoConvenido")%></td>
		                 	</tr>
		                 </table>
					</div>
<!-- INDEMN -->		<div id="indemnizacion" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">APORTE DE INDEMNIZACIONES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Tipo R&eacute;gimen Previsional</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:radio property="cotizacion.tipoRegimenPrev" styleId="RegPrevDepINP" value="1"/>INP 
				                	<html:radio property="cotizacion.tipoRegimenPrev" styleId="RegPrevDepAFP" value="2"/>AFP
	                     		</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tipoRegimenPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tipoRegimenPrevisional")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	$ <html:text property="cotizacion.rentaImp" maxlength="8" size="12" styleId="rentaImp" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaIndemAporte();" onkeyup="javascript:soloMonto(this, '');"/>
				                </td>
				                <td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Tasa Pactada Indemnizaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizacion.tasaPactada" maxlength="6" size="9" styleId="tasaPactada" styleClass="campos" onkeyup="javascript:soloReal(this, ',');validaContenido(this);" onblur="javascript:soloReal(this, ',');validaContenido(this);recalculaIndemAporte();"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaPactadaIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaPactadaIndemnizacion")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Monto Aporte Indemnizaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	$ <html:text property="cotizacion.indemAporte" readonly="true" maxlength="8" size="12" styleId="indemAporte" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteIndemnizacion")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Per&iacute;odos Indemnizaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizacion.numPeriodos" maxlength="2" size="5" styleId="numPeriodos" styleClass="campos" onblur="javascript:soloMonto(this, '');" onkeyup="javascript:soloMonto(this, '');"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("periodoIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("periodoIndemnizacion")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha Inicio Indemnizaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                <input type="text" readonly="readonly" disabled size="12" class="campos" id="fechaIniDep" name="fechaIniDep"/>
			                   		<a href="#" id="fIDepo"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaIniDep', 'fIDepo', true, document.getElementById('periodo').value);return false;"/></a>								
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaIniDep')" title="Limpia Fecha"/></a>
			                   	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniDep") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniDep")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha T&eacute;rmino Indemnizaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                <input type="text" readonly="readonly" disabled size="12" class="campos" id="fechaFinDep" name="fechaFinDep"/>
					             	<a href="#" id="fFDepo"><img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0" onClick="muestraCalendar('fechaFinDep', 'fFDepo', true, document.getElementById('periodo').value);return false;"/></a>								
			                   		<a href="#" title="Limpia Fecha"><img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0" onClick="javascript:limpiaFecha('fechaFinDep')" title="Limpia Fecha"/></a>
			                   	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaFinDep") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaFinDep")%></td>
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
    		<input type="button" value="${guardar}"  onclick="javascript:enviar('${guardar}')"  class="btn3" />
    		<input type="button" value="${cancelar}" onclick="javascript:enviar('${cancelar}')" class="btn3" />
        </td>
    </tr>
    </logic:present>
</table>
</html:form>

<script language="javaScript">
function validaContenido(element) 
{
	var value1 = element.value;
	var value2 = element.value.replace(/\,/gi, "");
	if (value1.length - value2.length > 1)
		element.value = element.value.substring(0,element.value.length-1);
}
function recalculaIndemAporte() 
{
	var rentaImp = document.getElementById("rentaImp").value.replace(/\./gi, "");
	var tasaPactada = document.getElementById("tasaPactada").value.replace(/\,/gi, ".");
	if (document.getElementById("tasaPactada").value.length - document.getElementById("tasaPactada").value.replace(/\,/gi, "").length > 1) 
	{
		document.getElementById("tasaPactada").value = 0;
		tasaPactada=0;
	}
	var indemAporte = formatNumero(limpiaNumero( Math.round((rentaImp*tasaPactada)/100) , ''));
	document.getElementById("indemAporte").value = indemAporte;
}

var listaDivs = new Array('antecedentes', 'deposito', 'indemnizacion');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";
document.getElementById('tasaPactada').value = document.getElementById('tasaPactada').value.replace('.', ',');

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{//esto se ejecuta solo al cargar la pagina, no al cambiar tab
	if (document.getElementById("newRutTrabajador").value != "" && document.getElementById("newRutTrabajador").value.length > 2)
		document.getElementById("newRutTrabajador").value = (document.getElementById("newRutTrabajador").value).replace(' ','-');

	document.getElementById('fechaIniDep').value = document.getElementById('fechaIni').value;
	document.getElementById('fechaFinDep').value = document.getElementById('fechaFin').value;
	cambiaDiv(tabDefault);

	if (document.getElementById("depositoConvenido").value == "")
		document.getElementById("depositoConvenido").value = 0;
	if (document.getElementById("tasaPactada").value == "")
		document.getElementById("tasaPactada").value = 0;
	if (document.getElementById("rentaImp").value == "")
		document.getElementById("rentaImp").value = 0;
	if (document.getElementById("indemAporte").value == "")
		document.getElementById("indemAporte").value = 0;
	if (document.getElementById("numPeriodos").value == "")
		document.getElementById("numPeriodos").value = 0;
}

function enviar(op)
{
	if (op == '${guardar}')
	{
		if (validaFormDep()) {
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

function validaFormDep()
{
	if (document.getElementById("botonGuardar").value == "1") //para que el boton se apriete solo una vez
		return false;

	document.getElementById('fechaIni').value = document.getElementById('fechaIniDep').value;
	document.getElementById('fechaFin').value = document.getElementById('fechaFinDep').value;
//campos formulario:
	//basicos
		//newRutTrabajador
		//newNombre
		//apellidoPat
		//apellidoMat
		//idGenero
		//idSucursal
	//lista
		//idEntDep
	//radio
		//RegPrevDepINP: 1
		//RegPrevDepAFP: 2
	//montos
		//depositoConvenido
		//indemAporte
		//rentaImp
		//numPeriodos
	//porcentajes
		//tasaPactada
	//fechas (ya estan restringidas, no se validan)
		//fechaIniDep
		//fechaFinDep
	var msg = validaDataBasicaForm();
	var flgDepConvenido    = 0;
	var flgIndemnizaciones = 0;

	if (document.getElementById("RegPrevDepINP").checked || document.getElementById("RegPrevDepAFP").checked) {
		flgIndemnizaciones = 1;
	}
	
	if (document.getElementById('idEntDep').value != -1 || (document.getElementById("depositoConvenido").value != "" && document.getElementById("depositoConvenido").value != 0)) {
		flgDepConvenido = 1;
	}
	
	if (flgDepConvenido == 1 && flgIndemnizaciones == 0) {
		msg += validaDepConvenido();
	} else if (flgDepConvenido == 0 && flgIndemnizaciones == 1) {
		msg += validaIndemnizaciones();
	} else if (flgDepConvenido == 1 && flgIndemnizaciones == 1) {
		msg += validaDepConvenido();
		msg += validaIndemnizaciones();
	} else {
		msg += " - Debe ingresar un monto para Depósito Convenido y/o seleccionar un valor para Tipo Régimen Previsional";
	}

	if (msg != '')
	{
		alert(msg);
		return false;
	}
	document.getElementById("botonGuardar").value = "1";		
	return true;
}

function conFocoCampoNumerico(objCampo)
{
	soloNumero(objCampo);
}

function sinFocoCampoNumerico(objCampo)
{
	objCampo.value = formatNumero(objCampo.value);
}

function foco()
{
	if ( document.getElementById('newRutTrabajador') && document.getElementById('newRutTrabajador')!=null )
		document.getElementById('newRutTrabajador').focus();
}
foco();

if (document.getElementById("operacion").value == "mod") {
	document.getElementById("newRutTrabajador").readOnly = true;
	/*document.getElementById("newNombre").readOnly		 = true;
	document.getElementById("apellidoPat").readOnly		 = true;
	document.getElementById("apellidoMat").readOnly		 = true;*/
}

function validaDepConvenido() {
	var msg = '';

	if (document.getElementById('idEntDep').value == -1) {
		msg += " - Debe seleccionar una Entidad. \n";
	} else {
		if (document.getElementById("depositoConvenido").value == "" || document.getElementById("depositoConvenido").value == 0)
			msg += " - Debe ingresar un monto para Depósito Convenido. \n";
		else if (!validaNumero(document.getElementById("depositoConvenido").value))
			msg += " - Monto para Depósito Convenido debe corresponder a un número. \n";
	}

	/*if (document.getElementById("depositoConvenido").value != "" && document.getElementById("depositoConvenido").value != 0 && document.getElementById('idEntDep').value == -1)
		msg += " - Debe seleccionar una Entidad. \n";*/
	
	return msg;
}

function validaIndemnizaciones() {
	var msg = '';
	
	if (!document.getElementById("RegPrevDepINP").checked && !document.getElementById("RegPrevDepAFP").checked)
		msg += " - Debe seleccionar un valor para Tipo Régimen Previsional. \n";

	var topeIndemn    = new Number(limpiaNumero(document.getElementById('topeIndemn').value, ''));
	var tasaPactada   = parseFloat(document.getElementById('tasaPactada').value.replace(/,/,'.'));
	var minTasaIndemn = new Number(document.getElementById('minTasaIndemn').value);
	var maxTasaIndemn = new Number(document.getElementById('maxTasaIndemn').value);

	if (tasaPactada > 0) {
		if (tasaPactada > maxTasaIndemn)
			msg += " - Valor para Tasa Pactada es superior al máximo permitido (" + maxTasaIndemn + "). \n";
		if (tasaPactada < minTasaIndemn)
			msg += " - Valor para Tasa Pactada es inferior al mínimo permitido (" + minTasaIndemn + "). \n";
	}

	if (document.getElementById("rentaImp").value == "" || document.getElementById("rentaImp").value == 0)
		msg += " - Debe ingresar un monto para Remuneración Imponible. \n";

	if (document.getElementById("indemAporte").value == "" || document.getElementById("indemAporte").value == 0)
		msg += " - Debe ingresar un monto para Aporte Indemnización. \n";

	if (new Number(limpiaNumero(document.getElementById('indemAporte').value, '')) > topeIndemn)
		msg += " - Monto para Aporte Indemnización supera al tope permitido (" + topeIndemn + "). \n";

	if (document.getElementById("numPeriodos").value == "" || document.getElementById("numPeriodos").value == 0)
		msg += " - Debe ingresar un valor para Períodos Indemnización. \n";

	if (document.getElementById("fechaIniDep").value == '')
		msg += " - Debe seleccionar una Fecha de Inicio. \n";
	else if (!validarFecha(document.getElementById("fechaIniDep").value))
		msg += " - Fecha inicio inválida. \n";

	if (document.getElementById("fechaFinDep").value == '')
		msg += " - Debe seleccionar una Fecha de Término. \n";
	else if (!validarFecha(document.getElementById("fechaFinDep").value))
		msg += " - Fecha término inválida. \n";

	return msg;
}

$(document).ready(function(){

	if ($("#operacion").val() == "mod") {
		//Elimina las otras opciones para evitar la modificación del campo
		valor = $("#idGenero").val();
		$("#idGenero").find("option[value!="+valor+"]").remove();
	}
});
// End --> 
</script>