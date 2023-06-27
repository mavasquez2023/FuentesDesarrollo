<%@ include file="/html/comun/taglibs.jsp" %>

<%@page import="cl.araucana.cp.distribuidor.base.Constants"%>
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
<html:hidden styleId="mostrar" property="mostrar" />
<html:hidden styleId="operacion" property="operacion" />
<html:hidden styleId="periodo" property="periodo" />
<html:hidden styleId="tipoPrevision" property="tipoPrevision" />
<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" id="estadoRutVol" value="0" />
<input type="hidden" id="estadoRutDep" value="0" />

<c:set var="tipoTrabajador" scope="page">
	<%=request.getAttribute("tipoTrabajador")%>
</c:set>

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
		            	<td width="72%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Detalle de Afiliado Voluntario</strong></td>
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
		              <td align="left" nowrap="nowrap" class="textos_formularios">${CotizacionActionForm.cotizante.rut}&nbsp;</td>              	
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.nombre}&nbsp;</td>
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.apellidos}&nbsp;</td>
		            </tr>
		        </table>
		        <br />		        
		        <table width="100%" border="0" cellpadding="0" cellspacing="1">
		        	<tr valign="bottom"> 
		            	<td width="72%" height="30" bgcolor="#FFFFFF" class="titulo"><strong>Trabajador Dependiente</strong></td>
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
		              <td align="left" nowrap="nowrap" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvRutDpndiente}&nbsp;</td>              	
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvNombreDpndiente}&nbsp;</td>
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvAplldioPatDpndiente} ${CotizacionActionForm.cotizacion.afpvAplldioMatDpndiente}&nbsp;</td>
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
					<TD class=titulo bgColor=#ffffff height=30><STRONG>Ficha Trabajador Dependiente</STRONG></TD>
				</TR>
			</TABLE>
   		</c:if>
   		
        <logic:present name="CotizacionActionForm" property="cotizacion">
		<input type="hidden" id="cotizacion.tasaTrabPesado" name="cotizacion.tasaTrabPesado" value="-1"/>
		<html:hidden styleId="afpVoluntario" property="cotizante.afpVoluntario" />
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
              <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otros', this);" id="otrosTd">MVTO. PERSONAL</td>
            </tr>
            <tr>
              	<td colspan="8">
    <input type="hidden" id="topeImpAFPPesos" value="<c:out value='${topeImpAFPPesos}'/>" />              	
              	
<!-- ANTECEDENTES --><div id="antecedentes" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.rut" maxlength="12" size="15" styleClass="campos" styleId="newRutTrabajador" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
				                	<%--
									<img id="buscarTrabVoluntario" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Buscar Trabajador" style="cursor: pointer;">
									<img id="limpiarTrabVoluntario" src="<c:url value="/img/borrar.jpg"/>" width="16" height="16" border="0" title="Limpiar RUT" style="cursor: pointer;">
									--%>
				                </td>
				               	<td align="left" class="textos_formularios">
				               		&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rut") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rut")%>
				               		<span id="errorRutVol" style="color: #FF0000; font-weight: bold"></span>
			               		</td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.nombre" maxlength="30" size="45" styleClass="campos" styleId="newNombre" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombres") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombres")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.apellidoPat" maxlength="20" size="35" styleClass="campos" styleId="apellidoPat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr>
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.apellidoMat" maxlength="20" size="35" styleClass="campos" styleId="apellidoMat" onblur="javascript:soloNombre(this, '');" onkeyup="javascript:soloNombre(this, '');"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr>
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">G&eacute;nero</td>
				               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               		<html:select property="cotizante.idGeneroReal" styleClass="campos" styleId="idGenero">				               		
			                     		<option value="-1">Seleccione</option>
										<html:optionsCollection property="generos" label="nombre" value="id" />
									</html:select>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
				           </tr>
				           <tr id="rentaTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">$ <html:text property="cotizacion.rentaImp" maxlength="8" size="15" styleClass="campos" styleId="rentaImp" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);" onkeyup="javascript:soloMonto(this, '');"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr id="diasTrabajadosTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Dias Trabajados</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizante.numDiasTrabajados" maxlength="2" size="5" styleClass="campos" styleId="numDiasTrabajados" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("diasTrabajados") == null ? "" : ((java.util.HashMap)mensajesErrores).get("diasTrabajados")%></td>
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
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES TRABAJADOR DEPENDIENTE</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<html:text property="cotizacion.afpvRutDpndiente" maxlength="12" size="15" styleClass="campos" styleId="newRutTrabajadorDep" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/>
				                	<img id="buscarTrabVoluntarioDep" src="<c:url value="/img/refresh.png"/>" width="16" height="16" border="0" title="Buscar Trabajador Dependiente" style="cursor: pointer;">
				                	<img id="limpiarTrabVoluntarioDep" src="<c:url value="/img/borrar.jpg"/>" width="16" height="16" border="0" title="Limpiar RUT" style="cursor: pointer;">
				                </td>
				               	<td align="left" class="textos_formularios">
				               		&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutVoluntario")%>
				               		<span id="errorRutDep" style="color: #FF0000; font-weight: bold"></span>
				               	</td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizacion.afpvNombreDpndiente" maxlength="30" size="45" styleClass="campos" styleId="newNombreDep" onblur="javascript:soloTexto(this, '');" onkeyup="javascript:soloTexto(this, '');" readonly="true"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombresVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombresVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizacion.afpvAplldioPatDpndiente" maxlength="20" size="35" styleClass="campos" styleId="apellidoPatDep" onblur="javascript:soloTexto(this, '');" onkeyup="javascript:soloTexto(this, '');" readonly="true"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidosVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidosVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios"><html:text property="cotizacion.afpvAplldioMatDpndiente" maxlength="20" size="35" styleClass="campos" styleId="apellidoMatDep" onblur="javascript:soloTexto(this, '');" onkeyup="javascript:soloTexto(this, '');" readonly="true"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
				           </tr>
				    	</table>
	              	</div>
<!-- prevision -->  <div id="prevision" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
<!--afiliacion voluntaria--><table width="100%" border="0" cellpadding="0" cellspacing="1" id="afilVolunTable">
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">COTIZACI&Oacute;N VOLUNTARIA</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Sistema Previsional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntidadAFPVReal" styleClass="campos" styleId="idEntPensionCotVol">
			                     			<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol")%></td>
		                 	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.prevObligatorioAFP" maxlength="8" size="12" styleId="prevObligatorioAFPVol" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
		                 	<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <html:text property="cotizacion.previsionAhorro" value="0" maxlength="8" size="12" styleId="afpAhorroVol" styleClass="campos" onfocus="javascript:conFocoCampoNumerico(this);" onblur="javascript:soloMonto(this, '');sinFocoCampoNumerico(this);recalculaAFP();" onkeyup="javascript:soloMonto(this, '');"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionAhorro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionAhorro")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalAfpVolDiv">$0</div>
		                   			<html:hidden property="cotizacion.previsionTotal" styleId="totalAfp"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalPrevision") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalPrevision")%></td>
		                 	</tr>
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ADMINISTRADORA DE FONDOS DE PENSIONES TRABAJADOR DEPENDIENTE</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Sistema Previsional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
			                     		<html:select property="cotizante.idEntPensionReal" styleClass="campos" styleId="idEntPensionReal">
			                     		<option value="-1">Seleccione</option>
											<html:optionsCollection property="entidadesPension" label="nombre" value="id" />
										</html:select>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
				    	</table>
				    </div>		     		
<!-- movto pers --> <div id="otros" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">	
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
			                   		<input type="text" readonly="readonly" disabled size="12" class="campos" id="inicioMP${countMP}" name="inicioMP${countMP}" value="${m.inicio}"/>
			                   		<a href="#" id="fIMP${countMP}">
			                   			<img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0"
			                   				onClick="limpiaFecha('inicioMP${countMP}');muestraCalendar('inicioMP${countMP}', 'fIMP${countMP}', false, document.getElementById('periodo').value);return false;"/>
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
			                   		<input type="text" readonly="readonly" disabled size="12" class="campos" id="terminoMP${countMP}" name="terminoMP${countMP}" value="${m.termino}"/>
									<a href="#" id="fTMP${countMP}">
										<img src="<c:url value="/img/ico_calendario.gif" />" width="11" height="10" border="0"
											onClick="limpiaFecha('terminoMP${countMP}');muestraCalendar('terminoMP${countMP}', 'fTMP${countMP}', false, document.getElementById('periodo').value);return false;"/>
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
				    	</table></nested:iterate>
				    	<br /><br />
				    	<div align="right"><input type="button" class="btnAjustable" value="Agregar Movimiento (M&aacute;ximo ${CotizacionActionForm.numMaxMovimientos})" onclick="javascript:showMP();"/>&nbsp;<input type="button" class="btn2" value="Eliminar Movimiento" onclick="javascript:hideMP();"/></div>&nbsp;
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
    		<%--<input type="button" value="${guardar}" onclick="javascript:enviar('${guardar}')" class="btn3" />--%>
	    	<input type="button" class="btn3" value="${guardar}" id="btnGuardar"/>
    		<input type="button" class="btn3" value="${cancelar}" onclick="javascript:enviar('${cancelar}')"/>
        </td>
    </tr>
    </logic:present>
</table>
</html:form>

<script language="javaScript">
var listaDivs = new Array('antecedentes', 'prevision', 'otros');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";

var numMaxMovimientos = ${CotizacionActionForm.numMaxMovimientos};
var tipoMovtoEntidadSIL = ${CotizacionActionForm.tipoMovtoEntidadSIL};

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{
	var rutTmp = document.getElementById("newRutTrabajador").value;
	if (rutTmp.length > 2)
		document.getElementById("newRutTrabajador").value = rutTmp.replace(' ','-');

	if (document.getElementById(tabDefault) != null)
		cambiaDiv(tabDefault);

	for (i = 1; i < numMaxMovimientos; i++)
		if (document.getElementById("idTipoMovReal" + i).value == '-1' || document.getElementById("inicioMP" + i).value == '-1' || document.getElementById("terminoMP" + i).value == '-1')
			document.getElementById("mpTable" + i).style.display = 'none';
		else
			document.getElementById("mpTable" + i).style.display = 'block';
	recalculaAFP();
}

function enviar(op)
{
	document.getElementById("operacion").value = op;
	if (op == '${guardar}')
	{
	document.getElementById("afpVoluntario").value = "true";
		if (validaFormAFPV())
			document.forms[0].submit();
	} else
		document.forms[0].submit();
}

function validaFormAFPV()
{
	if (document.getElementById("botonGuardar").value == "1") //para que el boton se apriete solo una vez
		return false;
	
	//ids
		//idEntPensionCotVol
		//idEntPensionReal
		//idTipoMovRealX
		//idEntSil
	//montos
		//prevObligatorioAFPVol
		//afpAhorroVol
	//fechas
		//inicioMPX
		//terminoMPX
		
		//numDiasTrabajados
		//rentaImp
	var msg = validaDataBasicaFormAV();
	if (!validaNumero(document.getElementById('rentaImp').value, ''))
		msg += " - Monto Renta Imponible inválido.\n";
		
	var rentaImpValidar = document.getElementById('rentaImp').value;
	var topeImpAFPPesos = document.getElementById('topeImpAFPPesos').value;
	
	if (parseInt(rentaImpValidar.split(".").join("")) > parseInt(topeImpAFPPesos)) {
		msg += " - Renta Imponible es mayor que el tope legal. $" + formatNumero(topeImpAFPPesos) + "\n";
		document.getElementById('rentaImp').value = formatNumero(topeImpAFPPesos);
	}		
		
	if (!validaNumero(document.getElementById('numDiasTrabajados').value, ''))
		msg += " - Número de días trabajados inválido.\n";

	var prevObligatorioAFPVol = new creaNumero(document.getElementById('prevObligatorioAFPVol').value);
	if (!validaNumero(document.getElementById('prevObligatorioAFPVol').value, '') || prevObligatorioAFPVol < 0)
		msg += " - Monto Cotización Obligatoria Previsión inválido.\n";
	var afpAhorroVol = new creaNumero(document.getElementById('afpAhorroVol').value);
	if (!validaNumero(document.getElementById('afpAhorroVol').value, '') || afpAhorroVol < 0)
		msg += " - Monto Ahorro Voluntario Previsión Inválido.\n";

	if (document.getElementById('idEntPensionReal').value == -1)
		msg += " - Debe seleccionar Entidad Previsional para cotización voluntaria.\n";
	if (document.getElementById('idEntPensionCotVol').value == -1)
		msg += " - Debe seleccionar Entidad Previsional para trabajador dependiente.\n";

	document.getElementById('tipoPrevision').value = 1;

//MOVIMIENTO PERSONAL
	//1=Obligatorio
	//0=Omitible si se indica fecha de inicio o termino
	var opcionMvtoInicio = new Array(${CotizacionActionForm.tipoMovimientoInicio});
	var opcionMvtoTermino = new Array(${CotizacionActionForm.tipoMovimientoTermino});
	var ordenIdMovimiento = new Array(${CotizacionActionForm.ordenIdMovimiento});

	for (kk = 0; kk < numMaxMovimientos; kk++)
	{
		var fechaIni = document.getElementById("inicioMP" + kk).value;
		var fechaFin = document.getElementById("terminoMP" + kk).value;
		var flag = 0;

		if (fechaIni != '' && !validarFecha(fechaIni))
		{
			msg += " - Fecha inicio inválida para el Movimiento de Personal " + (kk + 1) + "\n";
			flag = 1;
		}
		if (fechaFin != '' && !validarFecha(fechaFin))
		{
			flag = 1;
			msg += " - Fecha término inválida para el Movimiento de Personal " + (kk + 1) + "\n";
		}

		if (flag == 0)
		{
			document.getElementById("inicioMP" + kk + "Hidd").value = fechaIni;
			document.getElementById("terminoMP" + kk + "Hidd").value = fechaFin;
						
			var opI = document.getElementById("idTipoMovReal" + kk).value;	
			var op = 0;
			for(a=0; a < ordenIdMovimiento.length; a++)
			{
				if(opI == ordenIdMovimiento[a])
					op = a;
			}	
			if(opcionMvtoInicio[op] == 1 && opcionMvtoTermino[op] == 0)
			{
				if (fechaIni == '' && document.getElementById("idTipoMovReal" + kk).value != '-1')
					msg += " - Verifique la fecha de inicio ingresada para el Movimiento de Personal " + (kk + 1) + "\n";			
			}
			if(opcionMvtoTermino[op] == 1 && opcionMvtoInicio[op] == 0)
			{
				if (fechaFin == '' && document.getElementById("idTipoMovReal" + kk).value != '-1')
					msg += " - Verifique la fecha de término ingresada para el Movimiento de Personal " + (kk + 1) + "\n";			
			}
			else
			{
				if ((fechaIni == '' || fechaFin == '') && document.getElementById("idTipoMovReal" + kk).value != '-1')
					msg += " - Verifique las fechas ingresadas para el Movimiento de Personal " + (kk + 1) + "\n";			
			}		
			if (fechaIni != '' && fechaFin != '')
			{	
				var fechaIArray = fechaIni.split("-");
				var fechaFArray = fechaFin.split("-");
				alert("aaa: " + fechaIArray[2]);
				alert("fech: " + creaNumero(fechaIArray[2]+fechaIArray[1]+fechaIArray[0]));

				if(creaNumero(fechaIArray[2]+fechaIArray[1]+fechaIArray[0]) > creaNumero(fechaFArray[2]+fechaFArray[1]+fechaFArray[0]))
					msg += " - Fechas de Movimiento de Personal " + (kk + 1) + ": Fecha Inicio debe ser menor a Fecha Término\n";
			}
			if (document.getElementById("idTipoMovReal" + kk).value == tipoMovtoEntidadSIL && document.getElementById("idEntidadSil" + kk).value == '-1')
				msg += " - Debe seleccionar una Entidad Pagadora de Subsidio para Movimiento de Personal " + (kk + 1) + "\n";
		}
	}
	
	if (document.getElementById("estadoRutVol").value == 1) {
		msg += " - El RUT ingresado para el Trabajador Voluntario corresponde a uno Dependiente.\n";
	}
	
	if (document.getElementById("estadoRutDep").value != 1) {
		switch (document.getElementById("estadoRutDep").value) {
			case "0":
				msg += " - El RUT del Trabajador Dependiente no existe.\n";
				break;
			case "2":
				msg += " - El RUT ingresado para el Trabajador Dependiente corresponde a uno Voluntario.\n";
				break;
		}
	}

	if (msg != '')
	{
		alert(msg);
		return false;
	}
	document.getElementById("botonGuardar").value = "1";
	return true;
}

function showMP()
{
	for (i = 0; i < numMaxMovimientos; i++)
		if (document.getElementById("idTipoMovReal" + i).value == '-1')
		{
			document.getElementById("mpTable" + i).style.display = 'block';
			break;
		}
}

function hideMP()
{
	for (i = numMaxMovimientos - 1; i >= 0; i--)
	{
		if (document.getElementById("mpTable" + i).style.display == 'block' || document.getElementById("mpTable" + i).style.display == '')
		{
			document.getElementById("idTipoMovReal" + i).value = -1;
			document.getElementById("inicioMP" + i).value = "";
			document.getElementById("terminoMP" + i).value = "";
			document.getElementById("idEntidadSil" + i).value = -1;
			if (i >= 1)
				document.getElementById("mpTable" + i).style.display = 'none';
			break;
		}
	}
}

function recalculaAFP()
{/*	prevObligatorioAFPVol
	afpAhorroVol
	totalAfp
	totalAfpVolDiv*/
		if(document.getElementById('idEntPensionReal').value == '<%=Constants.ID_AFP_NINGUNA %>')
	{
		document.getElementById('prevObligatorioAFP').value = 0;
		document.getElementById('afpAhorroVol').value = 0;				
	}
	var totalAfpVolDiv = new Number(0);
	var prevObligatorioAFPVol = new Number(limpiaNumero(document.getElementById('prevObligatorioAFPVol').value, ''));
	var afpAhorroVol = new Number(limpiaNumero(document.getElementById('afpAhorroVol').value, ''));

	if (prevObligatorioAFPVol > 0)
		totalAfpVolDiv += prevObligatorioAFPVol;
	if (afpAhorroVol > 0)
		totalAfpVolDiv += afpAhorroVol;

	document.getElementById("totalAfp").value = totalAfpVolDiv;
	document.getElementById("totalAfpVolDiv").innerHTML = '$ ' + formatNumero(totalAfpVolDiv);
}

function showErrorRUT(tipo, estado){

	//Voluntario
	if (tipo == "V") {
		var errorV = document.getElementById("errorRutVol");

		//RUT NO ENCONTRADO
		if (estado == 0){
			errorV.innerHTML = "RUT NO ENCONTRADO";
		//RUT ENCONTRADO
		} else if (estado == 1) {
			errorV.innerHTML = "RUT ES DEPENDIENTE"
		} else if (estado == 2) {
			errorV.innerHTML = "";
		}
	}

	//Dependiente
	if (tipo == "D") {
		var errorD = document.getElementById("errorRutDep");

		//RUT NO ENCONTRADO
		if (estado == 0) {
			errorD.innerHTML = "RUT NO ENCONTRADO";
		//RUT ENCONTRADO
		} else if (estado == 2) {
			errorD.innerHTML = "RUT ES VOLUNTARIO";
		} else if (estado == 1){
			errorD.innerHTML = "";
		}
	}
}

if (document.getElementById("operacion").value == "mod") {
	document.getElementById("newRutTrabajador").readOnly = true;
	document.getElementById("newNombre").readOnly		 = true;
	document.getElementById("apellidoPat").readOnly		 = true;
	document.getElementById("apellidoMat").readOnly		 = true;

	document.getElementById("newRutTrabajadorDep").readOnly = true;
	document.getElementById("newNombreDep").readOnly		= true;
	document.getElementById("apellidoPatDep").readOnly		= true;
	document.getElementById("apellidoMatDep").readOnly		= true;
	document.getElementById("buscarTrabVoluntarioDep").style.display  = "none";
	document.getElementById("limpiarTrabVoluntarioDep").style.display = "none";

	//Omite la validación de los RUT
	document.getElementById("estadoRutVol").value = "2";
	document.getElementById("estadoRutDep").value = "1";
}

$(document).ready(function(){

	if ($("#operacion").val() == "mod") {
		//Elimina las otras opciones para evitar la modificación del campo
		valor = $("#idGenero").val();
		$("#idGenero").find("option[value!="+valor+"]").remove();

		valor = $("#idEntPensionReal").val();
		$("#idEntPensionReal").find("option[value!="+valor+"]").remove();
	}

	$("#buscarTrabVoluntarioDep").click(function(){
		var rutTra = $("#newRutTrabajadorDep").attr("value");
		var rutEmp = $("#rutEmpresa").attr("value");
		var idConv = $("#convenio").attr("value");
		var idNom  = $("#tipoProceso").attr("value");

		if (rutTra == '') {
			alert("Debe ingresar RUT para realizar búsqueda.");
			$("#newRutTrabajadorDep").focus();
			return false;
		} else if (!validaRut(rutTra) || !validaDV(rutTra)) {
			alert("El RUT ingresado es inválido.");
			$("#newRutTrabajadorDep").focus();
			return false;
		}

		jQuery.ajax({
			type: "POST",
			beforeSend: function() {
				$.blockUI({
					message: '<img src="<c:url value="/img/loading.gif"/>" /><br>Buscando...',
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
			},
			complete: function(){$.unblockUI();},
			url: '<c:url value="/BuscarCotizanteAjax.do" />',
			data: "rut="       + rutTra + 
			      "&idEmp="    + rutEmp +
			      "&convenio=" + idConv +
			      "&nomina="   + idNom,
			dataType: ($.browser.msie) ? "text" : "xml",
			error : function(XMLHttpRequest, textStatus, errorThrown){alert('Error: ' + textStatus + ", " + errorThrown);},
			success: function(data) {
				var xml;
				if (typeof data == "string") {
					xml = new ActiveXObject("Microsoft.XMLDOM");
					xml.async = false;
					xml.loadXML(data);
				} else {
					xml = data;
				}

				var nom    = $(xml).find("nombre").text();
				var pat    = $(xml).find("paterno").text();
				var mat    = $(xml).find("materno").text();
				var afp    = $(xml).find("afp").text();
				var estado = $(xml).find("estado").text();
				/* estado
					0 RUT no encontrado
					1 RUT es Dependiente
					2 RUT es Voluntario
				*/

				$("#estadoRutDep").val(estado);

				if (estado == 1) {
					$("#newRutTrabajadorDep").attr("readonly",true);
					$("#newNombreDep").attr("readonly",true);
					$("#apellidoPatDep").attr("readonly",true);
					$("#apellidoMatDep").attr("readonly",true);
					
					$("#newNombreDep").val(nom);
					$("#apellidoPatDep").val(pat);
					$("#apellidoMatDep").val(mat);
					$("#idEntPensionReal").val(afp);

					// Se eliminan el resto de las opciones para evitar su modificación					
					$("#idEntPensionReal").find("option[value!="+afp+"]").remove();

					//Revisar: En caso de que el Trab. Dependiente no tenga AFP se están eliminando todas las opciones.
				}

				showErrorRUT("D", estado);
			}
		});
	});
	
	$("#limpiarTrabVoluntarioDep").click(function(){
		$("#errorRutDep").html("");

		$("#newRutTrabajadorDep").removeAttr("readonly");

		$("#newRutTrabajadorDep").val("");
		$("#newNombreDep").val("");
		$("#apellidoPatDep").val("");
		$("#apellidoMatDep").val("");

		$("#idEntPensionReal").empty();

		$("#idEntPensionCotVol").find("option").each(function(index, domEle){
			$("#idEntPensionReal").append("<option value='" + $(domEle).val() + "'>" + $(domEle).text() + "</option>");
		});

		$("#idEntPensionReal").val("-1");
	});

	$("#btnGuardar").click(function(){
		var rutTra = $("#newRutTrabajador").attr("value");
		var rutEmp = $("#rutEmpresa").attr("value");
		var idConv = $("#convenio").attr("value");
		var idNom  = $("#tipoProceso").attr("value");

		jQuery.ajax({
			type: "POST",
			url: '<c:url value="/BuscarCotizanteAjax.do" />',
			data: "rut="       + rutTra +
			      "&idEmp="    + rutEmp +
			      "&convenio=" + idConv +
			      "&nomina="   + idNom,
			dataType: ($.browser.msie) ? "text" : "xml",
			error : function(XMLHttpRequest, textStatus, errorThrown){alert('Error: ' + textStatus + ", " + errorThrown);},
			success: function(data) {
				var xml;
				if (typeof data == "string") {
					xml = new ActiveXObject("Microsoft.XMLDOM");
					xml.async = false;
					xml.loadXML(data);
				} else {
					xml = data;
				}

				var estado = $(xml).find("estado").text();
				/* estado
					0 RUT no encontrado
					1 RUT es Dependiente
					2 RUT es Voluntario
				*/
				$("#estadoRutVol").val(estado);
			}
		});
		enviar('${guardar}');
	});

});
</script>