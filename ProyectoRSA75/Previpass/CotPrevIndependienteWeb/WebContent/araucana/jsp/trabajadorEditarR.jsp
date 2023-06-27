<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js"/> "></script>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="<c:url value="/js/jquery.blockUI.js" />"></script>



<script type="text/javascript">
$(document).ready(function(){
   	if(document.getElementById('idEntPensionReal').value != idAFPNinguna &&
   	   document.getElementById('idEntPensionReal').value != idSinAFP) {
   		document.getElementById("idEntAFCReal").value     = document.getElementById('idEntPensionReal').value;   
   		document.getElementById("idEntAFCReal").disabled  = true;   	
   	}  
   	 calculaCotizacionMutual();	
});
</script>

<html:form action="/EditarCotizacion">
<html:hidden styleId="accion" 			property="accion"		name="accion"		value="inicio" />
<html:hidden styleId="subAccion" 		property="subAccion"	name="subAccion"	value="trabajadores"/>
<html:hidden styleId="subSubAccion" 	property="subSubAccion" name="subSubAccion" value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa"		property="rutEmpresa" />
<html:hidden styleId="rutTrabajador"	property="rutTrabOrigin" />
<html:hidden styleId="convenio"			property="idConvenio" />
<html:hidden styleId="idMutual"			property="idMutual" />
<html:hidden styleId="diasTopeAF"		property="diasTopeAF" />
<html:hidden styleId="aporteCCAFFON"	property="aporteCCAFFON" />
<html:hidden styleId="idCotizPendiente" property="idCotizPendiente" />
<html:hidden styleId="topeAFP" 			property="topeAFP" />
<html:hidden styleId="topeINP" 			property="topeINP" />
<html:hidden styleId="topeIndemn" 		property="topeIndemn" />
<html:hidden styleId="topeCesantia" 	property="topeCesantia" />
<html:hidden styleId="apEmpIndSegCes" 	property="apEmpIndSegCes" />
<html:hidden styleId="apEmpPFSegCes"	property="apEmpPFSegCes" />
<html:hidden styleId="apTrabIndSegCes"	property="apTrabIndSegCes" />
<html:hidden styleId="apTrabPFSegCes"	property="apTrabPFSegCes" />
<html:hidden styleId="mostrar"			property="mostrar" />
<html:hidden styleId="diasXMes"			property="diasXMes" />
<html:hidden styleId="operacion"		property="operacion" />
<html:hidden styleId="respaldoOperacion" property="respaldoOperacion" />
<html:hidden styleId="periodo"			property="periodo" />

<!-- Depósito Convenido  -->
<html:hidden styleId="minTasaIndemn"	property="minTasaIndemn" />
<html:hidden styleId="maxTasaIndemn"	property="maxTasaIndemn" />

<!-- Marco  -->
<html:hidden styleId="tolerancia" property="tolerancia" />

<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" id="botonGuardar" value="0" />
<input type="hidden" value="" name="opExCaja" id="opExCaja" />
<input type="hidden" name="idCaja" id="idCaja" value="${CotizacionActionForm.caja.id}"/>
<html:hidden styleId="tipoPrevision" property="tipoPrevision" />

<html:hidden styleId="flgTrabNomina" property="flgTrabNomina"/>

<input type="hidden" id="valorINPPrevio" />
<input type="hidden" id="valorAFPPrevio" />
<input type="hidden" id="valorIsaprePrevio" />

<c:set var="tipoTrabajador" scope="page">
	<%=request.getAttribute("tipoTrabajador")%>
</c:set>

<c:set var="idCCAF" scope="page">
	<bean:write name='idCCAF'/>
</c:set>

<input type="hidden" id="datosSis" value="<bean:write name='datosSis'/>" />

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	    <logic:equal name="CotizacionActionForm" property="mostrar" value="new">
	    	<html:hidden styleId="tipoProceso" property="tipoProceso" />
	        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos" style="display:none">
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
		        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos" style="display:none">
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
		        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos" style="display:none">
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
			      		<td align="center"><input type="button" value="${aplicar}" class="btn4" onclick="javascript:enviarRemu('${aplicar}')"/></td>
			      	</tr>
			      	<tr> 
			        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
			     	</tr>
			    </table>
	    	</logic:equal>
		    <logic:present name="CotizacionActionForm" property="cotizacion">
		        <table width="100%" border="0" cellpadding="0" cellspacing="1">
		        	<tr valign="bottom"> 
		            	<td width="72%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Ficha Trabajador Independiente</strong></td>
		                <td width="28%" align="right" bgcolor="#FFFFFF" class="tit-13">&nbsp;</td>
		            </tr>
		        </table>
		        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" style="display:none">
		            <tr class="subtitulos_tablas">
		              <td align="center" valign="middle" bordercolor="#CCCCCAC" class="barra_tablas">RUT</td>
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

		<c:if test="${tipoTrabajador == 'V'}">
			<TABLE cellSpacing=1 cellPadding=0 width="100%" border=0>
				<TR vAlign=bottom>
					<TD class=titulo bgColor=#ffffff height=30><STRONG>Ficha Trabajador Voluntario</STRONG></TD>
				</TR>
			</TABLE>
   		</c:if>
   		<c:if test="${tipoTrabajador == 'D'}">
			<TABLE cellSpacing=1 cellPadding=0 width="100%" border=0>
				<TR vAlign=bottom>
					<TD class=titulo bgColor=#ffffff height=30><STRONG>Ficha Trabajador Independiente</STRONG></TD>
				</TR>
			</TABLE>
   		</c:if>

   		<logic:present name="CotizacionActionForm" property="cotizacion">
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
              <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('salud', this);" id="saludTd">ISAPRE</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('inpTab', this);" id="inpTabTd">INP/FONASA</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('apv', this);" id="apvTd">APV</td>
              <logic:notEmpty name="CotizacionActionForm" property="caja.nombre">
              		<td align="center" class="barra_tablas" onclick="javascipt:recalculaCaja();javascript:cambiaDiv('caja', this);" id="cajaTd">CAJA</td>
              </logic:notEmpty>
              <logic:notEmpty name="CotizacionActionForm" property="nomMutual">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('mutual', this);" id="mutualTd">MUTUAL</td>
              </logic:notEmpty>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otros', this);" id="otrosTd">MVTO. PERSONAL</td>
            </tr>
            <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('deposito', this);" id="depositoTd" colspan="8">DEP&Oacute;SITO CONVENIDO</td>
				<td style="display:none" align="center" class="barra_tablas" onclick="javascript:cambiaDiv('indemnizacion', this);" id="indemnizacionTd" colspan="4">INDEMNIZACIONES</td>
            </tr>
            <tr>
              	<td colspan="8">
<!-- ANTECEDENTES --><div id="antecedentes" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
									
				               		<input type="text" name="cotizante.rut" id="newRutTrabajador" value="${CotizacionActionForm.rutEmpresaFormat}" maxlength="12" size="15" Class="campos" readonly="readonly" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
				               		<!-- <html:text property="cotizante.rut" maxlength="12" size="15" styleClass="campos" styleId="newRutTrabajador" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>  --> 
				               
				               
				               	<td align="left" class="textos_formularios">
				               		&nbsp;
				               		<c:set var="errorRut" scope="page">
					               		<%=((java.util.HashMap)mensajesErrores).get("rut") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rut")%>
				               		</c:set>
				               		${errorRut}
				               	</td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                
				                
				                <input type="text" name="cotizante.nombre" id="newNombre" value="${nombreToken}" maxlength="30" size="45" Class="campos" readonly="readonly" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/>

				                  <!-- <html:text property="cotizante.nombre" maxlength="30" size="45" styleClass="campos" styleId="newNombre" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/></td> -->
				               	
				               	
				               	<td align="left" class="textos_formularios">
				               		&nbsp;
					               	<c:set var="errorNombres" scope="page">
				               			<%=((java.util.HashMap)mensajesErrores).get("nombres") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombres")%>
				               		</c:set>
				               		${errorNombres}
				               	</td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">

				               <input type="text" name="cotizante.apellidoPat" id="apellidoPat" value="${apellidoPatToken}" maxlength="20" size="35" Class="campos" readonly="readonly" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/>

				                <!-- <html:text property="cotizante.apellidoPat" maxlength="20" size="35" styleClass="campos" styleId="apellidoPat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/></td> -->

				               	<td align="left" class="textos_formularios">
				               		&nbsp;
				               		<c:set var="errorApellidos" scope="page">
				               			<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%>
				               		</c:set>
				               		${errorApellidos}
				               	</td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">

								<input type="text" name="cotizante.apellidoMat" id="apellidoMat" value="${apellidoMatToken}" maxlength="20" size="35" readonly="readonly" class="campos" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');">				               	                                
				                <!--  <html:text property="cotizante.apellidoMat" maxlength="20" size="35" styleClass="campos" styleId="apellidoMat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');" readonly/></td>--> 
				               
				               
				               	<td align="left" class="textos_formularios">
				               		&nbsp;
				               		${errorApellidos}
								</td>
				           </tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">G&eacute;nero</td>
				               	<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               		<html:select property="cotizante.idGeneroReal" styleClass="campos" styleId="idGeneroReal">
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="generos" label="nombre" value="id" />
									</html:select>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">$ <html:text property="cotizacion.rentaImp" maxlength="8" size="15" styleClass="campos" styleId="rentaImp" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);rentaImponible(this);" onkeyup="javascript:soloMonto(this, '');" onchange="javascript:cambiaRentaImp();"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Dias Trabajados</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.numDiasTrabajados" maxlength="2" size="5" styleClass="campos" styleId="numDiasTrabajados" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');" onchange="javascript:recalculaAsigFam();"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("diasTrabajados") == null ? "" : ((java.util.HashMap)mensajesErrores).get("diasTrabajados")%></td>
				           </tr>
				           <tr style="display:none"> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Sucursal</td>
				               	<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"> 
				               		<html:select property="cotizante.idSucursal" styleClass="campos" styleId="idSucursal">
			                     		<!-- <option value="-1">Seleccione</option> -->
										<html:optionsCollection property="sucursales" label="nombre" value="idSucursal" />
									</html:select>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("sucursales") == null ? "" : ((java.util.HashMap)mensajesErrores).get("sucursales")%></td>
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
									<logic:iterate id="porcentajeSalud" name="CotizacionActionForm" property="entidadesSalud">
				                   		<html:hidden property="tasaSalud_${porcentajeSalud.id}" value="${porcentajeSalud.tasaSalud}"/>				                   		
		                   			</logic:iterate>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadSalud")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.saludObligISAPRE" maxlength="8" size="15" styleId="saludObligISAPRE" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalSalud();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludOblig")%></td>
		                 	</tr>
		                 	<tr id="saludAdicionalTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Adicional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.saludAdicional" maxlength="8" size="15" styleId="saludAdicional" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalSalud();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludAdicional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludAdicional")%></td>
		                 	</tr>
		                 	<tr id="saludPactadaTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pactada</td>
		                   		<td align="left" valign="middle" class="textos_formularios">		                   			
		                   			<div id="totalSaludDiv">$ ${CotizacionActionForm.cotizacion.saludPactado}&nbsp;</div>
		                   			<html:hidden property="cotizacion.saludPactado" styleId="saludPactado"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludPactada") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludPactada")%></td>
		                 	</tr>
		                 </table><br>
		                 <table width="100%" border="0" cellpadding="0" cellspacing="1">
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalCotiSaludDiv">$ ${CotizacionActionForm.cotizacion.saludTotal}&nbsp;</div>
		                   			<html:hidden property="cotizacion.saludTotal" styleId="saludTotal"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalSalud")%></td>
		                 	</tr>
				    	</table>
		     		</div>
<!-- AFP -->	    <div id="prevision" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
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
			                     		<html:select property="cotizante.idEntPensionReal" styleClass="campos" styleId="idEntPensionReal" onchange="javascript:seleccionaINPaAFP();" onfocus="javascript:guardaValorAFP(this.value);">
			                     			<option value="${CotizacionActionForm.idSinAFP}">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                     		<img id="imgActualizarAFP" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Actualizar AFP" style="cursor: pointer;" onclick="javascript:actualizaAFP()">
									<logic:iterate id="entAFP" name="CotizacionActionForm" property="entidadesPension">
										<logic:equal name="exigirValidacion" value="S">
											<html:hidden property="id" value="${entAFP.tasaAFPSinSIS}" styleId="entAFP-${entAFP.id}"/>
										</logic:equal>
										<logic:equal name="exigirValidacion" value="O">
											<html:hidden property="id" value="${entAFP.tasaAFPSinSIS}" styleId="entAFP-${entAFP.id}"/>
										</logic:equal>
										<logic:equal name="exigirValidacion" value="N">
											<html:hidden property="id" value="${entAFP.tasaAFPConSIS}" styleId="entAFP-${entAFP.id}"/>
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
		                   			$ <html:text property="cotizacion.prevObligatorioAFP" maxlength="8" size="15" styleId="prevObligatorioAFP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
							<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.previsionAhorro" maxlength="8" size="15" styleId="afpAhorro" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionAhorro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionAhorro")%></td>
		                 	</tr>
		                 	<tr>
		                 		<td width="30%" align="left" valign="middle" class="textos_formcolor">Numero de Periodo</td>
		                 		<td align="left" valign="middle" class="textos_formularios">
									 <html:select property="cotizante.numeroPeriodoAfp" styleClass="campos" styleId="numeroPeriodoAfp">
								        <html:optionsCollection property="numeroPeriodo" label="label" value="value" />
									 </html:select>
		                 		</td>
		                 		<td  align="left" class="textos_formularios"></td>		                 				                 		
		                 	</tr>
		                 	<logic:equal name="exigirValidacion" value="S">
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" value="0" />  <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" value="0" /> <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
		                 	<logic:equal name="exigirValidacion" value="O">
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" value="0" /> <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td> 
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" value="0" /> <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
		                 	<logic:equal name="exigirValidacion" value="N">
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.rentaImponibleSIS" maxlength="8" size="15" styleId="rentaImponibleSIS" styleClass="campos" value="0" />  <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
			                 	</tr>
			                 	<tr style="display:none"> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			$ <html:text property="cotizacion.previsionSIS" maxlength="8" size="15" styleId="previsionSIS" styleClass="campos" value="0"  /> <!--onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>-->
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalAfpDiv">$ 0</div>
		                   			<html:hidden property="cotizacion.previsionTotal" styleId="totalAfp"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalPrevision") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalPrevision")%></td>
		                 	</tr>
		                 </table>
		                 <table width="100%" border="0" cellpadding="0" cellspacing="1" id="tableSegCes" style="display:none">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">SEGURO DE CESANTIA</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad AFC</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntAFCReal" styleClass="campos" styleId="idEntAFCReal" onchange="copiaRemuneracion();">
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
		                   			$ <html:text property="cotizacion.segCesRemImp" maxlength="8" size="15" styleId="segCesRemImp" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.segCesTrab" maxlength="8" size="15" styleId="segCesTrabajador" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		$ <html:text property="cotizacion.segCesEmpl" maxlength="8" size="15" styleId="segCesEmpresa" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
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
						<table width="100%" border="0" cellpadding="0" cellspacing="1" id="trabajoPesadoTable" style="display:none">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">TRABAJO PESADO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tasa Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   		<html:select property="cotizacion.tasaTrabPesado" styleClass="campos" styleId="idTasaTraPesa" onchange="javaScript:recalculaTrabajo('rentaImp');">
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="porcentajeTrabPesado" label="label" value="value" />
									</html:select>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Nombre Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<html:text property="cotizacion.tipoTrabPesado" maxlength="40" size="60" styleId="nombreTrabPesado" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("idTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("idTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizacion.trabPesado" maxlength="8" size="15" styleId="montoTrabPesado" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td colspan="3">&nbsp;</td>
				        	</tr>
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
				                   				<html:hidden property="id" value="${codReg.tasaPension}" styleId="codReg-${codReg.idRegImpositivo}"/>
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
			                   		<input type="checkbox" name="fonasaCheck" id="fonasaCheck" checked="checked"/>
			                   		<!--<c:choose>   
			                   			<c:when test="${idCCAF != 0}">6.4%</c:when>
			                   			<c:otherwise>7.0%</c:otherwise>
			                   		</c:choose> -->
			                   		7.0%
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
		                     		$ <html:text property="cotizacion.remImpPension" maxlength="8" size="15" styleId="remImpPension" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);cambiaCondINP();" onkeyup="javascript:soloMonto(this, '');" onchange="javascript:recalculaINP();" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponiblePension") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponiblePension")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pensi&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.prevObligatorioINP" maxlength="8" size="15" styleId="prevObligatorioINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("pensionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("pensionOblig")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n de Salud FONASA</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.saludObligFONASA" maxlength="8" size="15" styleId="saludObligFONASA" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fonasaOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fonasaOblig")%></td>
				        	</tr>
				           	<tr id="accTrabINPTR"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Accidentes del Trabajo</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.accTrabajoINP" maxlength="8" size="15" styleId="accTrabajoINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Desahucio</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.inpDesahucio" maxlength="8" size="15" styleId="inpDesahucio" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotDeshaucio") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotDeshaucio")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaciones</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalCotizINPDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalCotInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalCotInp")%></td>
				        	</tr>
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">REBAJAS INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				        	<tr id="asigFamINPTR">
				        		<td colspan="3">
				        		<table width="100%" border="0" style="display:none;">
						        	<tr> 
						            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">ASIGNACION FAMILIAR</td>
						           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
						           </tr>
						           <tr> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tramo de Asignaci&oacute;n Familiar</td>
					                   	<td align="left" valign="middle" class="textos_formularios">
					                   		<span style="padding-bottom: 3px;"> 
					                    		<html:select property="cotizante.idTramoRealINP" styleClass="campos" styleId="idTramoINP" onchange="javascript:recalculaAsigFamINP();">
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
				                     		<html:text property="cotizante.numCargaSimpleINP"    styleId="cargasSimplesINP"    maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFamINP();" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
				                     		<html:text property="cotizante.numCargaMaternaINP"   styleId="cargasMaternalesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFamINP();" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
				                    		<html:text property="cotizante.numCargaInvalidezINP" styleId="cargasInvalidezINP"  maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFamINP();" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
						        	</tr>
						           <tr id="asigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <html:text property="cotizacion.asigFamiliarINP" maxlength="8" size="15" styleId="asigFamiliarINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
						        	</tr>
						           <tr id="asigFamRetroTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <html:text property="cotizacion.asigFamRetroINP" maxlength="8" size="15" styleId="asigFamRetroINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
						        	</tr>
						           <tr id="reintegroAsigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <html:text property="cotizacion.asigFamReintINP" maxlength="8" size="15" styleId="reintegroAsigFamINP" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarReint") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarReint")%></td>
						        	</tr>
						           <tr id="reintegroAsigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Asignaci&oacute;n Familiar</b></td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		<div id="totalAsignFamDivINP">$0</div><input type="hidden" name="totASigFamHiddINP" id="totASigFamHiddINP" value="0">
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;</td>
						        	</tr>
						        </table>
						        </td>
					        </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Bonificaci&oacute;n Art. 19 Ley 15.386</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.inpBonificacion" maxlength="8" size="15" styleId="inpBonificacion" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaTotalINP();" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("bonificacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("bonificacion")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Rebajas</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalRebajasINPDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
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
<!-- APV -->   	    <div id="apv" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" id="apvTable${countAPV}">
			     			<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<nested:iterate id="apv" name="CotizacionActionForm" property="apvs" indexId="countAPV" type="cl.araucana.cp.distribuidor.hibernate.beans.ApvVO">
			                	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad APV ${countAPV + 1}</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                   			<nested:hidden property="id" />
			                     		<span style="padding-bottom: 3px;">
			                     			<nested:select property="idEntidadApv" value="${apv.idEntidadApv}" styleClass="campos" styleId="idEntidadApv${countAPV}" onchange="javascript: limpiaApv('${countAPV}');">
				                     			<option value="-1">Seleccione</option>
												<html:optionsCollection property="entidadesApvs" label="nombre" value="id" />
											</nested:select>
			                     		</span>
			                     	</td>
					               	<td width="20%" align="left" class="textos_formularios">&nbsp;
										<%=((java.util.HashMap)mensajesErrores).get("entidadApv"+countAPV) == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadApv"+countAPV)%>
									</td>
			                 	</tr>
			                	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto APV ${countAPV + 1}</td>
			                   		<td align="left" valign="middle" class="textos_formularios"> 
			                   			$ <nested:text property="montoFormat" styleId="ahorro${countAPV}" maxlength="8" size="15" styleClass="campos" value='${apv.montoFormat}' onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;
										<%=((java.util.HashMap)mensajesErrores).get("montoApv"+countAPV) == null ? "" : ((java.util.HashMap)mensajesErrores).get("montoApv"+countAPV)%>
									</td>
			                 	</tr>
			                 	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Régimen APV ${countAPV + 1}</td>
			                   		<td align="left" valign="middle" class="textos_formularios"> 
			                   			<nested:select property="regimen" value="${apv.regimen}" styleClass="campos" styleId="regimen${countAPV}">
				                     			<option value="0">Seleccione</option>
												<html:optionsCollection property="regimenAPV" label="label" value="value" />
										</nested:select>
			                     	</td>
					               	<td align="left" class="textos_formularios">&nbsp;
										<%=((java.util.HashMap)mensajesErrores).get("tipoRegimenAPV"+countAPV) == null ? "" : ((java.util.HashMap)mensajesErrores).get("tipoRegimenAPV"+countAPV)%>
									</td>
			                 	</tr>
			                 	</nested:iterate>
						</table>		     		
						<table width="100%" border="0" cellpadding="0" cellspacing="1"  style="display:none">		     				
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS AHORRO PREVISIONAL VOLUNTARIO COLECTIVO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad APV</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;">
		                     			<html:select property="cotizante.idEntidadAPVCReal" styleClass="campos" styleId="idAPVC">
		                     				<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesApvs" label="nombre" value="id" />
		                     			</html:select>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadApvc")%></td>
		                 	</tr>
		                	<tr>
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero Contrato</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<html:text property="cotizacion.apvcNumContr" maxlength="10" size="15" styleId="numContratoAPVC" styleClass="campos" onblur="javascript:soloAlfanumerico(this, '');" onkeyup="javascript:soloAlfanumerico(this, '');"/>
		                   		</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("codContratoApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("codContratoApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ <html:text property="cotizacion.apvcAporteTrab" maxlength="8" size="15" styleId="aporteTraAPVC" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaAPVC();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ <html:text property="cotizacion.apvcAporteEmpl" maxlength="8" size="15" styleId="aporteEmpAPVC" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaAPVC();" onkeyup="javascript:soloMonto(this, '');"/>
		                   	 	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteEmpApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteEmpApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Total APV Colectivo</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			<div id="totalAPVC" style="font-weight: bold">$ 0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                 	</tr>
				        </table>
					</div>
<!-- CAJA -->       <div id="caja" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
			     		<input type="hidden" name="caja-porcentajeFonasa" id="caja-porcentajeFonasa" value="${CotizacionActionForm.caja.porcentajeFonasa}"/>
			     		<input type="hidden" name="caja-asigFam" id="caja-asigFam" value="${CotizacionActionForm.caja.asigFam}"/>
			     		<input type="hidden" name="caja-creditos" id="caja-creditos" value="${CotizacionActionForm.caja.creditos}"/>
			     		<input type="hidden" name="caja-leasing" id="caja-leasing" value="${CotizacionActionForm.caja.leasing}"/>
			     		<input type="hidden" name="caja-dental" id="caja-dental" value="${CotizacionActionForm.caja.dental}"/>
			     		<input type="hidden" name="caja-segurosVida" id="caja-segurosVida" value="${CotizacionActionForm.caja.segurosVida}"/>
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">CCAF ADHERIDA ${CotizacionActionForm.caja.nombre}</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           <tr> 
			                   	<!--  <td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte CCAF ${CotizacionActionForm.aporteCCAFFON}%</td>-->
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte CCAF</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<!--  $ <html:text property="cotizacion.aporteCaja" maxlength="8" size="15" styleId="aporteCaja" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaCaja();" onkeyup="javascript:soloMonto(this, '');"/>-->
		                    		<c:choose>
			                   			<c:when test="${idCCAF == 3}">
			                   				$ <html:text property="cotizacion.aporteCaja" maxlength="8" size="15" styleId="aporteCaja" styleClass="campos" readonly="true"/>
		                    	        </c:when>
			                   			<c:otherwise>
			                   					$ <html:text property="cotizacion.aporteCaja" maxlength="8" size="15" styleId="aporteCaja" styleClass="campos"/>
			                   			</c:otherwise>
			                   		</c:choose>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteCcaf") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteCcaf")%></td>
				        	</tr>
				           <tr style="display:none;">
				            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">ASIGNACION FAMILIAR</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           </tr>
				           <tr style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tramo de Asignaci&oacute;n Familiar</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<logic:iterate id="trAsigFam" name="CotizacionActionForm" property="tramosAsigFam">
			                   			<html:hidden property="id" value="${trAsigFam.valorCarga}" styleId="${trAsigFam.id}-trAsigFam"/>
			                   		</logic:iterate>
			                   		<span style="padding-bottom: 3px;"> 
			                    		<html:select property="cotizante.idTramoReal" styleClass="campos" styleId="idTramo" onchange="javascript:recalculaAsigFam();" >
			                     			<option value="-1">Seleccione</option>
											<html:optionsCollection property="tramosAsigFam" label="nombre" value="id" />
										</html:select>
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizante.numCargaSimple"    styleId="cargasSimples"    maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFam();" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaterna"   styleId="cargasMaternales" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFam();" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidez" styleId="cargasInvalidez"  maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');sinFocoCampoNumerico(this);recalculaAsigFam();" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
				           <tr id="asigFamTr" style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.asigFamiliar" maxlength="8" size="15" styleId="asigFam" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaCaja();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
				        	</tr>
				           <tr id="asigFamRetroTr" style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.asigFamRetro" maxlength="8" size="15" styleId="asigFamRetro" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaCaja();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
				        	</tr>
				           <tr id="reintegroAsigFamTr" style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.asigFamReint" maxlength="8" size="15" styleId="reintegroAsigFam" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaCaja();" onkeyup="javascript:soloMonto(this, '');"/> 
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarReint") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarReint")%></td>
				        	</tr>
				           <tr id="reintegroAsigFamTr" style="display:none;"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Asignaci&oacute;n Familiar</b></td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="totalAsignFamDiv">$0</div>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
				        	</tr>
						</table>
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				           <tr> 
				            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">OTROS DESCUENTOS</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           </tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cr&eacute;dito</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   		<c:choose>
										<c:when test="${idCCAF == 3}">
				                     		$ <html:text property="cotizacion.ccafCredito" maxlength="8" size="15" styleId="creditoPersonal" styleClass="campos" readonly="true"/>
										    </c:when>
										<c:otherwise>
					                     		$ <html:text property="cotizacion.ccafCredito" maxlength="8" size="15" styleId="creditoPersonal" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
										</c:otherwise>
									</c:choose>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("creditoPersonal") == null ? "" : ((java.util.HashMap)mensajesErrores).get("creditoPersonal")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Leasing C.C.A.F.</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   	<c:choose>
										<c:when test="${idCCAF == 3}">
				                     		$ <html:text property="cotizacion.ccafLeasing" maxlength="8" size="15" styleId="leasing" styleClass="campos" readonly="true"/>
				                    	</c:when>
									<c:otherwise>
					                     		$ <html:text property="cotizacion.ccafLeasing" maxlength="8" size="15" styleId="leasing" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
										</c:otherwise>
									</c:choose>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("leasing") == null ? "" : ((java.util.HashMap)mensajesErrores).get("leasing")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Convenio Dental</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.ccafDental" maxlength="8" size="15" styleId="convenioDental" readonly="true" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("convenioDental") == null ? "" : ((java.util.HashMap)mensajesErrores).get("convenioDental")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Seguro de Vida</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.ccafSeguro" maxlength="8" size="15" styleId="seguro" readonly="true" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("seguroVida") == null ? "" : ((java.util.HashMap)mensajesErrores).get("seguroVida")%></td>
				        	</tr>
						</table>
		     		</div>
<!-- MUTUAL -->    	<div id="mutual" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">INSTITUCION ACCIDENTES DEL TRABAJO: ${CotizacionActionForm.nomMutual}</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">(%) Tasa Cotizaci&oacute;n</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			${CotizacionActionForm.tasaMutual}
		                   			<input type="hidden" value="${CotizacionActionForm.tasaMutual}" id="tasaMutual"/>
		                   		</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.mutualImp" maxlength="8" size="15" styleId="rentaImpMutual" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);calculaCotizacionMutual();" onkeyup="javascript:soloMonto(this, '');"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImpMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImpMutual")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <html:text property="cotizacion.accTrabajoMutual" maxlength="8" size="15" styleId="cotizacionMutual" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');" readonly="true"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotizacionMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotizacionMutual")%></td>
				        	</tr>
		            	</table>
					</div>
<!-- MOVTO PERSO --><div id="otros" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">	
			        	<nested:iterate id="m" name="CotizacionActionForm" property="movtosPersonal" indexId="countMP" type="cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal">
					        <table width="100%" border="0" cellpadding="0" cellspacing="1" id="mpTable${countMP}">
				            	<tr> 				        	
					           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">MOVIMIENTO DE PERSONAL: ${countMP + 1}</td>
					           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
					            </tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tipo de Movimiento</td>
				                   	<td width="50%" align="left" valign="middle" class="textos_formularios">
				                   		<span style="padding-bottom: 3px;">
				                    		<nested:select property="idTipoMovReal" value="${m.idTipoMovReal}" styleClass="campos" styleId="idTipoMovReal${countMP}">
				                     			<option value="-1">Seleccione</option>
												<html:optionsCollection property="tiposMovPersonal" label="nombre" value="id" />
											</nested:select>
				                     	</span>
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Inicio</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
				                   		<nested:hidden property="inicio" styleId="inicioMP${countMP}Hidd" />
				                   		<input type="text" maxlength="10" readonly="readonly" disabled size="12" class="campos" id="inicioMP${countMP}" name="inicioMP${countMP}" value="${m.inicio}"/>
				                   		<a href="#" id="fIMP${countMP}">
				                   			<img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0"
				                   				onClick="limpiaFecha('inicioMP${countMP}');muestraCalendar('inicioMP${countMP}', 'fIMP${countMP}', true, document.getElementById('periodo').value);return false;"/>
				                   		</a>
				                   		<a href="#" title="Limpia Fecha">
				                   			<img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0"
				                   				onClick="javascript:limpiaFecha('inicioMP${countMP}')" title="Limpia Fecha"/>
				                   		</a>
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Fin</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
				                   		<nested:hidden property="termino" styleId="terminoMP${countMP}Hidd" />
				                   		<input type="text" maxlength="10" readonly="readonly" disabled size="12" class="campos" id="terminoMP${countMP}" name="terminoMP${countMP}" value="${m.termino}"/>
										<a href="#" id="fTMP${countMP}">
											<img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0"
												onClick="limpiaFecha('terminoMP${countMP}');muestraCalendar('terminoMP${countMP}', 'fTMP${countMP}', true, document.getElementById('periodo').value);return false;"/>
										</a>
				                   		<a href="#" title="Limpia Fecha">
				                   			<img src="<c:url value="/img/icono_basurero.gif" />" width="16" height="16" border="0"
				                   				onClick="javascript:limpiaFecha('terminoMP${countMP}')" title="Limpia Fecha"/>
				                   		</a>
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("terminoMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("terminoMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad Pagadora de Subsidio</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
				                   		<span style="padding-bottom: 3px;">
				                   			<nested:select property="idEntidadSil" value="${m.idEntidadSil}" styleClass="campos" styleId="idEntidadSil${countMP}">
				                     			<option value="-1">Seleccione</option>
												<html:optionsCollection property="entidadesSIL" label="nombre" value="id" />
											</nested:select>
				                     	</span>
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP)%></td>
					        	</tr>
					    	</table>
				    	</nested:iterate>
				    	<br /><br />
				    	<div align="right">
				    		<input type="button" class="btnAjustable" value="Agregar Movimiento (M&aacute;ximo ${CotizacionActionForm.numMaxMovimientos})" onclick="javascript:showMP();"/>
				    		&nbsp;
	 		    			<input type="button" class="btn2" value="Eliminar Movimiento" onclick="javascript:hideMP();"/>
				    	</div>&nbsp;
	              	</div>
<!--DEP CONV     --><div id="deposito" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
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
<!-- INDEMN -->		<div id="indemnizacion" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
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
				           <%--
				           DEJARLA COMO DISABLED Y QUE COPIE EL VALOR DESDE ANTECEDENTES
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	$ <html:text property="cotizacion.rentaImp" maxlength="8" size="12" styleId="rentaImp" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaIndemAporte();" onkeyup="javascript:soloMonto(this, '');"/>
				                </td>
				                <td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				            --%>
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
	<html:hidden styleId="fechaIni" property="cotizacion.indemInicio" />
	<html:hidden styleId="fechaFin" property="cotizacion.indemTermino" />
	<tr align="center"> 
    	<td height="33" valign="top"><br />
    		<input type="button" value="${guardarInd}"  onclick="javascript:enviarRemu('${guardarInd}')"  class="btn3" id="btnGuardar"/>
    		<input type="button" value="${cancelar}" onclick="javascript:enviarRemu('${cancelar}')" class="btn3" />
        </td>
    </tr>
    </logic:present>
    <input type="hidden" id="topeImpAFPPesos" value="<c:out value='${topeImpAFPPesos}'/>" />
    <input type="hidden" id="topeImpINPPesos" value="<c:out value='${topeImpINPPesos}'/>" />
</table>
</html:form>
<bean:define id="isAviso" name="CotizacionActionForm" property="isAviso" type="java.util.HashMap" toScope="page"/>
<script language="javaScript">
var listaDivs = new Array('antecedentes', 'salud', 'prevision', 'inpTab', 'apv', 'caja', 'mutual', 'otros', 'deposito', 'indemnizacion');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";
var sinCaja = false, sinMutual = false;
var idAFPNinguna = ${CotizacionActionForm.idAFPNinguna};
var idSinAFP = ${CotizacionActionForm.idSinAFP};
var idFONASA = ${CotizacionActionForm.idFONASA};
var tramoASigFamNinguno = ${CotizacionActionForm.tramoASigFamNinguno};
var sinEntidadSalud = ${CotizacionActionForm.idSaludNinguna};

var tasaMutual = ${CotizacionActionForm.tasaMutual};
var tipoMovimiento = new Array(${CotizacionActionForm.tipoMovimiento});
var ordenIdMovimiento = new Array(${CotizacionActionForm.ordenIdMovimiento});

var numMaxMovimientos = ${CotizacionActionForm.numMaxMovimientos};
var tipoMovtoEntidadSIL = ${CotizacionActionForm.tipoMovtoEntidadSIL};
var numMaxAPVs = ${CotizacionActionForm.numMaxAPVs};


</script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validaRemu.js" />"></script>
<script language="javaScript">
document.getElementById('tipoProcesoActual').value = document.getElementById("tipoProceso").value;

if ('${CotizacionActionForm.nomMutual}' == '')
	sinMutual = true;
if ('${CotizacionActionForm.caja.nombre}' == '')
	sinCaja = true;

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{
	var rutTmp = document.getElementById("newRutTrabajador").value;
	if (rutTmp.length > 2)
		document.getElementById("newRutTrabajador").value = rutTmp.replace(' ','-');

	if (sinMutual)
	{
		listaDivs.splice(6, 1);
	} else //con mutual
		document.getElementById("accTrabINPTR").style.display = 'none';
	if (sinCaja)//sin caja
	{
		listaDivs.splice(5, 1);
	} else //con caja
		document.getElementById("asigFamINPTR").style.display = 'none';
	if (tabDefault != '')
		cambiaDiv(tabDefault);

	for (i = 1; i < numMaxMovimientos; i++)
	{
		if (document.getElementById("idTipoMovReal" + i).value == '-1')
			document.getElementById("mpTable" + i).style.display = 'none';
	}

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
	if (document.getElementById("rentaImponibleSIS").value == "")
		document.getElementById("rentaImponibleSIS").value = 0;
	if (document.getElementById("previsionSIS").value == "")
		document.getElementById("previsionSIS").value = 0;				
	if (document.getElementById("rentaImp").value == "")
		document.getElementById("rentaImp").value = 0;
	var	rentaImp = document.getElementById("rentaImp").value;
/*
	Asepulveda quita esta regla de acuerdo a definición de La Araucana
	if (document.getElementById("segCesRemImp").value == "" || document.getElementById("segCesRemImp").value == 0)
		document.getElementById("segCesRemImp").value = rentaImp;
*/	
	if (document.getElementById("segCesRemImp").value == "")
		document.getElementById("segCesRemImp").value = 0;
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
		document.getElementById("rentaImpMutual").value = rentaImp;
	//if (document.getElementById("aporteCaja").value == "")
	//	document.getElementById("aporteCaja").value = 0;
	//jlandero Se exige que tenga FONASA o INP.
	if (document.getElementById("remImpPension").value == "" || document.getElementById("remImpPension").value == 0) {
		if (document.getElementById("idEntExCaja").value != "-1") {
			document.getElementById("remImpPension").value = rentaImp;
		}
	}

	if (document.getElementById("saludAdicional").value == "")
		document.getElementById("saludAdicional").value = 0;
	if (document.getElementById("afpAhorro").value == "")
		document.getElementById("afpAhorro").value = 0;
	if (document.getElementById("inpDesahucio").value == "")
		document.getElementById("inpDesahucio").value = 0;
	if (document.getElementById("inpDesahucio").value == "")
		document.getElementById("inpDesahucio").value = 0;
	if (document.getElementById("inpBonificacion").value == "")
		document.getElementById("inpBonificacion").value = 0;
	if (document.getElementById("segCesTrabajador").value == "")
		document.getElementById("segCesTrabajador").value = 0;
	if (document.getElementById("segCesEmpresa").value == "")
		document.getElementById("segCesEmpresa").value = 0;											
	if (document.getElementById("inpBonificacion").value == "")
		document.getElementById("inpBonificacion").value = 0;
	if (document.getElementById("asigFamiliarINP").value == "")
		document.getElementById("asigFamiliarINP").value = 0;
	if (document.getElementById("asigFamRetroINP").value == "")
		document.getElementById("asigFamRetroINP").value = 0;
	if (document.getElementById("reintegroAsigFamINP").value == "")
		document.getElementById("reintegroAsigFamINP").value = 0;
	if (document.getElementById("asigFam").value == "")
		document.getElementById("asigFam").value = 0;
	if (document.getElementById("asigFamRetro").value == "")
		document.getElementById("asigFamRetro").value = 0;
	if (document.getElementById("reintegroAsigFam").value == "")
		document.getElementById("reintegroAsigFam").value = 0;	
	if (document.getElementById("leasing").value == "")
		document.getElementById("leasing").value = 0;
	if (document.getElementById("convenioDental").value == "")
		document.getElementById("convenioDental").value = 0;
	if (document.getElementById("creditoPersonal").value == "")
		document.getElementById("creditoPersonal").value = 0;
	if (document.getElementById("seguro").value == "")
		document.getElementById("seguro").value = 0;

	document.getElementById("saludPactado").value = '${CotizacionActionForm.cotizacion.saludPactado}';		
	document.getElementById("saludTotal").value = '${CotizacionActionForm.cotizacion.saludTotal}';

	recalculaTipoPrevision();

	recalculaTotalAFP();
	recalculaTotalINP();
	recalculaCaja();
	recalculaAPVC();
	
	//Depósito Convenido
	document.getElementById('fechaIniDep').value = document.getElementById('fechaIni').value;
	document.getElementById('fechaFinDep').value = document.getElementById('fechaFin').value;

	if (document.getElementById("depositoConvenido").value == "")
		document.getElementById("depositoConvenido").value = 0;
	if (document.getElementById("tasaPactada").value == "")
		document.getElementById("tasaPactada").value = 0;
	if (document.getElementById("indemAporte").value == "")
		document.getElementById("indemAporte").value = 0;
	if (document.getElementById("numPeriodos").value == "")
		document.getElementById("numPeriodos").value = 0;
}

function enviarRemu(op)
{
	if (op == '${guardarInd}')
	{
		var exigirValidacionSis = document.getElementById("exigirValidacion").value;
	
		//if (exigirValidacionSis == 'O' && document.getElementById('datosSis').value == 'true')
		//{
		//	var idEntPensionReal  = document.getElementById("idEntPensionReal").value;
		//	var rentaImponibleSIS = document.getElementById("rentaImponibleSIS").value;
		//	var previsionSIS      = document.getElementById("previsionSIS").value;
		
		//	if ((idEntPensionReal  != -100 && idEntPensionReal != -1) &&
		//	    (rentaImponibleSIS ==    0 || previsionSIS     ==  0))
		//	{
		//		if (!confirm("El trabajador no cuenta con cotización en Previsión SIS, ¿Está seguro que desea continuar?"))
		//		{
		//			return false;
		//		}
		//	}
		//}
	}

	if (op == '${guardarInd}')
	{
		for (var i = 0; i < numMaxMovimientos; i++)
		{
			document.getElementById("inicioMP"  + i + "Hidd").value = document.getElementById("inicioMP"  + i).value;
			document.getElementById("terminoMP" + i + "Hidd").value = document.getElementById("terminoMP" + i).value;
		}

		//Depósito Convenido
		document.getElementById('fechaIni').value = document.getElementById('fechaIniDep').value;
		document.getElementById('fechaFin').value = document.getElementById('fechaFinDep').value;

		if (validaFormRemu()) {
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
	if('${errorRut}' == '') {
		document.getElementById("newRutTrabajador").readOnly = true;
	}
	
	/*if('${errorNombres}' == '') {
		document.getElementById("newNombre").readOnly = true;
	}

	if('${errorApellidos}' == '') {
		document.getElementById("apellidoPat").readOnly = true;
		document.getElementById("apellidoMat").readOnly	= true;
	}*/
}

function copiaRemuneracion() {
	var remImp = document.getElementById("segCesRemImp").value;

	if (document.getElementById("idEntExCaja").value != -1) {
		//copiar Remuneración Imponible -> Remuneración Imponible para Seguro de Cesantia
		remImp = document.getElementById("remImpPension").value;
	} else if (document.getElementById("idEntAFCReal").value != -1) {
		remImp = 0;
	}

	document.getElementById("segCesRemImp").value = remImp;
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

var auxSegCesRemImp = ('${CotizacionActionForm.cotizacion.segCesRemImp}' != '') ? '${CotizacionActionForm.cotizacion.segCesRemImp}' : '0';
var auxSegCesTrab   = ('${CotizacionActionForm.cotizacion.segCesTrab}'   != '') ? '${CotizacionActionForm.cotizacion.segCesTrab}'   : '0';
var auxSegCesEmpl   = ('${CotizacionActionForm.cotizacion.segCesEmpl}'   != '') ? '${CotizacionActionForm.cotizacion.segCesEmpl}'   : '0';

document.getElementById("segCesRemImp").value     = auxSegCesRemImp;
document.getElementById("segCesTrabajador").value = auxSegCesTrab;
document.getElementById("segCesEmpresa").value    = auxSegCesEmpl;

auxSegCesTrab = new Number(limpiaNumero(auxSegCesTrab, ''));
auxSegCesEmpl = new Number(limpiaNumero(auxSegCesEmpl, ''));

//jlandero
//El Total Seguro Cesantía corresponde a la suma del Aporte Trabajador y del Aporte Empresa.
//var totalAFC = parseInt(auxSegCesRemImp.replace('.', '')) + parseInt(auxSegCesTrab.replace('.', '')) + parseInt(auxSegCesEmpl.replace('.', ''));
var totalAFC = auxSegCesTrab + auxSegCesEmpl;

document.getElementById("totalAfcDiv").innerHTML  = '$ ' + formatNumero(totalAFC);

function guardaValorINP(valor) {
	document.getElementById("valorINPPrevio").value = valor;
}

function guardaValorAFP(valor) {
	document.getElementById("valorAFPPrevio").value = valor;

}

function guardaValorIsapre(valor) {
	document.getElementById("valorIsaprePrevio").value = valor;
}

function avisoCambioPension() {
	var flgAviso = 0;

	if (document.getElementById("prevObligatorioAFP").value != 0 ||
		document.getElementById("afpAhorro").value          != 0 ||
		//document.getElementById("rentaImponibleSIS").value  != 0 ||
		document.getElementById("previsionSIS").value       != 0) {
		flgAviso = 1;
	}

	//La confirmación de cambio de AFP a INP sólo ocurre cuando hay campos comprometidos.
	if(document.getElementById("idEntExCaja").value != "-1" && flgAviso == 1) {
		if(!confirm("Al seleccionar una INP se borrarán todos los datos que haya ingresado para la AFP ¿Desea continuar?")) {
			document.getElementById("idEntExCaja").value = document.getElementById("valorINPPrevio").value;
			return false;
		}
	}

	document.getElementById("respaldoOperacion").value = document.getElementById("operacion").value;
	limpiaAFPnoAFC();
	cambiaExCaja();
}

function avisoINPaAFP(){
	var flgAviso = 0;

	if (document.getElementById("prevObligatorioINP").value != 0 ||
		document.getElementById("inpDesahucio").value       != 0) {
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
	actualizaAFP();
}

function seleccionaINPaAFP(){
	var flgAviso = 0;

	if (document.getElementById("prevObligatorioINP").value != 0 ||
		document.getElementById("inpDesahucio").value       != 0) {
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
	seleccionaAFP();
}

function avisoCambioIsapre() {
	if(document.getElementById('fonasaCheck').checked) { //if(document.getElementById("saludObligFONASA").value > 0) {
		if(confirm("Al seleccionar una Isapre se borrará el monto que haya ingresado para Fonasa ¿Desea continuar?")){
			recalculaSalud();
			recalculaTotalINP();
		} else {
			document.getElementById("idEntSaludReal").value = document.getElementById("valorIsaprePrevio").value;
		}
	} else {
		recalculaSalud();
		recalculaTotalINP();
	}
}
$(document).ready(function(){

	/*$('#btnGuardar').click(function() { 
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
	}); */


   	if ($("#operacion").val() == "mod") {
		//Elimina las otras opciones para evitar la modificación del campo
		valor = $("#idGenero").val();
		$("#idGenero").find("option[value!="+valor+"]").remove();
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
					alert("Este es el texto del mensaje");
					$("#fonasaCheck").removeAttr("checked");
					return false;
				}
			}
			$("#idEntSaludReal").val("-1");
                        $("#saludAdicional").attr("value", "0");
			if($("#remImpPension").val() == "0" || $("#remImpPension").val() == "")
				$("#remImpPension").val($("#rentaImp").val());
			recalculaSalud();
		} else {
			$("#saludObligFONASA").attr("readOnly", "true");
			$("#aporteCaja").attr("readOnly", "true");
			$("#saludObligFONASA").attr("value", "0");
			//$("#aporteCaja").attr("value", "0");
		}
		recalculaTotalINP();
   	});

   	$("#idEntSaludReal").change(function(){
   		if ($("#idEntSaludReal").val() != "-1") {
   			$("input[name=fonasaCheck]").attr('checked', false);
   		}
   	});
   	
   	/*$("#idEntPensionReal").change(function(){
   		if ($("#idEntPensionReal").val() != "-1" && $("#idEntPensionReal").val() != "-100") {
	   		$("#inpDesahucio").attr("value", "0");
	   		recalculaTotalINP();
	   	}
   	});*/
});


//jlandero: El siguente código corresponde a validaciones propias de Depósito Convenido
function validaContenido(element) {
	var value1 = element.value;
	var value2 = element.value.replace(/\,/gi, "");
	if (value1.length - value2.length > 1)
		element.value = element.value.substring(0,element.value.length-1);
}

function recalculaIndemAporte() {
	var rentaImp = document.getElementById("rentaImp").value.replace(/\./gi, "");
	var tasaPactada = document.getElementById("tasaPactada").value.replace(/\,/gi, ".");
	if (document.getElementById("tasaPactada").value.length - document.getElementById("tasaPactada").value.replace(/\,/gi, "").length > 1) {
		document.getElementById("tasaPactada").value = 0;
		tasaPactada=0;
	}
	var indemAporte = formatNumero(limpiaNumero( Math.round((rentaImp*tasaPactada)/100) , ''));
	document.getElementById("indemAporte").value = indemAporte;
}

document.getElementById('tasaPactada').value = document.getElementById('tasaPactada').value.replace('.', ',');

function actualizaAFP(){

	recalculaPensionAFP();
	if(document.getElementById('idEntPensionReal').value != idSinAFP ) {
		rentaImponible();
	}
	cambiaRentaImp();
	if (document.getElementById('datosSis').value == 'true') {
		recalculaPrevisionSIS();
		
	var prevObligatorioAFP   = Math.round(new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, '')));
	var previsionSIS		 = Math.round(new Number(limpiaNumero(document.getElementById('previsionSIS').value, '')));

	document.getElementById('prevObligatorioAFP').value = formatNumero(prevObligatorioAFP + previsionSIS ); 
	
	document.getElementById('previsionSIS').value = 0;	
	
		
	}	
	recalculaTotalAFP();
}

//No vuelve a cero el campo (saludObligFONASA)
function seleccionaAFP(){

	recalculaPensionAFP();
	if(document.getElementById('idEntPensionReal').value != idSinAFP ) {
		rentaImponible();
	}
	cambiaRentaImpAfp();
	if (document.getElementById('datosSis').value == 'true') {
		recalculaPrevisionSIS();
		
	var prevObligatorioAFP   = Math.round(new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, '')));
	var previsionSIS		 = Math.round(new Number(limpiaNumero(document.getElementById('previsionSIS').value, '')));

	document.getElementById('prevObligatorioAFP').value = formatNumero(prevObligatorioAFP + previsionSIS ); 
	
	document.getElementById('previsionSIS').value = 0;	
	
		
	}	
	recalculaTotalAFP();
}

if (document.getElementById('datosSis').value == 'false') {
	document.getElementById('rentaImponibleSIS').readOnly = true;
	document.getElementById('previsionSIS').readOnly = true;
}
</script>