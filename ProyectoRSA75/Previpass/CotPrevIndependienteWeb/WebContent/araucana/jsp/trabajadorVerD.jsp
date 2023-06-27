<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>

<html:form action="/EditarCotizacion" styleId="frm">
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
<html:hidden styleId="operacion" property="operacion" value="volver"/>
<html:hidden styleId="periodo" property="periodo" />
<html:hidden styleId="idCotizante" property="rutTrabOrigin" />

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
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
		      		<td align="center"><input type="button" value="Aplicar" class="btn4" onclick="recargar();"/></td>
		      	</tr>
		      	<tr> 
		        	<td height="4" colspan="4" bgcolor="#85b4be"></td>
		     	</tr>
		    </table>
		    
		    <logic:notEqual name="CotizacionActionForm" property="mostrar" value="new">
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
			              <td align="left" nowrap class="textos_formularios">${CotizacionActionForm.rutTrabFormat}&nbsp;</td>
			              <td align="left" nowrap class="textos_formularios">${CotizacionActionForm.cotizante.nombre}&nbsp;</td>
			              <td align="left" nowrap class="textos_formularios">${CotizacionActionForm.cotizante.apellidos}&nbsp;</td>
			            </tr>
			        </table>
			    </logic:present>
		    </logic:notEqual>
		    <br />
			
			<html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
	   		
	   		<bean:define id="errores" name="CotizacionActionForm" property="errores" type="java.util.ArrayList" toScope="page"/>
	   		
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
						                	<c:out value="${ CotizacionActionForm.cotizante.rut }"></c:out>&nbsp;
											<html:hidden property="cotizante.rut" styleId="newRutTrabajador"/>
						                </td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rut") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rut")%></td>
									</tr>
						           <tr> 
						           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
						                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						                	<c:out value="${ CotizacionActionForm.cotizante.nombre }"></c:out>&nbsp;
						                	<html:hidden property="cotizante.nombre" styleId="newNombre"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombres") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombres")%></td>
						           </tr>
						           <tr> 
						           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
						               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
						               		<c:out value="${ CotizacionActionForm.cotizante.apellidoPat }"></c:out>&nbsp;
						                	<html:hidden property="cotizante.apellidoPat" styleId="newRutTrabajador"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
						           </tr>
						           <tr> 
						           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
						                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
						                	<c:out value="${ CotizacionActionForm.cotizante.apellidoMat }"></c:out>&nbsp;
						                	<html:hidden property="cotizante.apellidoMat" styleId="newRutTrabajador"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
						           </tr>
						           <tr> 
						           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">G&eacute;nero</td>
						               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						               		<c:forEach var="genero" items="${CotizacionActionForm.generos}">
						               			<c:choose>
						               				<c:when test="${genero.id eq CotizacionActionForm.cotizante.idGeneroReal}">
						               					<c:out value="${genero.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
						               		<html:hidden property="cotizante.idGeneroReal" styleId="idGenero"/>&nbsp;
						               	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
						           </tr>
						           <tr> 
						           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Sucursal</td>
						               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						               		<c:forEach var="sucursal" items="${CotizacionActionForm.sucursales}">
						               			<c:choose>
						               				<c:when test="${sucursal.idSucursal eq CotizacionActionForm.cotizante.idSucursal}">
						               					<c:out value="${sucursal.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
						               		<html:hidden property="cotizante.idSucursal" styleId="idSucursal"/>&nbsp;
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
												<c:forEach var="entidadApvs" items="${CotizacionActionForm.entidadesApvs}">
							               			<c:choose>
							               				<c:when test="${entidadApvs.id eq CotizacionActionForm.cotizante.idEntidadAPVCReal}">
							               					<c:out value="${entidadApvs.nombre}"></c:out>&nbsp;
							               				</c:when>
							               			</c:choose>
							               		</c:forEach>
				                     			<html:hidden property="cotizante.idEntidadAPVCReal" styleId="idAPVC"/>&nbsp;
				                     		</span>
				                     	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
				                 	</tr>
				                 	<tr> 
				                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Dep&oacute;sito Convenido</td>
				                   		<td align="left" valign="middle" class="textos_formularios">
				                   			$ <c:out value="${CotizacionActionForm.cotizacion.depositoConvenido}"></c:out>
				                   			<html:hidden property="cotizacion.depositoConvenido" styleId="depositoConvenido"/>
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
						                	<c:choose>
						                		<c:when test="${CotizacionActionForm.cotizacion.tipoRegimenPrev eq 1}">INP</c:when>
						                		<c:when test="${CotizacionActionForm.cotizacion.tipoRegimenPrev eq 2}">AFP</c:when>
						                		<c:otherwise>&nbsp;</c:otherwise>
						                	</c:choose>
						                </td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tipoRegimenPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tipoRegimenPrevisional")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						               		$ <c:out value="${ CotizacionActionForm.cotizacion.rentaImp }"></c:out>
						                	<html:hidden property="cotizacion.rentaImp" styleId="rentaImp"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Tasa Pactada Indemnizaci&oacute;n</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                   			$ <c:out value="${CotizacionActionForm.cotizacion.tasaPactada}"></c:out>
				                   			<html:hidden property="cotizacion.tasaPactada" styleId="tasaPactada"/>
				                     	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaPactadaIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaPactadaIndemnizacion")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Monto Aporte Indemnizaci&oacute;n</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                   			$ <c:out value="${CotizacionActionForm.cotizacion.indemAporte}"></c:out>
				                   			<html:hidden property="cotizacion.indemAporte" styleId="indemAporte"/>
				                     	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteIndemnizacion")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Per&iacute;odos Indemnizaci&oacute;n</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						                	$ <c:out value="${CotizacionActionForm.cotizacion.numPeriodos}"></c:out>
				                   			<html:hidden property="cotizacion.numPeriodos" styleId="numPeriodos"/>
				                     	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("periodoIndemnizacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("periodoIndemnizacion")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha Inicio Indemnizaci&oacute;n</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						                	<c:out value="${ CotizacionActionForm.cotizacion.inicio }"></c:out>&nbsp;
							            </td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniDep") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniDep")%></td>
						           </tr>
						           <tr> 
										<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha T&eacute;rmino Indemnizaci&oacute;n</td>
						                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
						                	<c:out value="${ CotizacionActionForm.cotizacion.termino }"></c:out>&nbsp;
							            </td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaFinDep") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaFinDep")%></td>
						           </tr>
						    	</table>
			              	</div>
					  	</td>
		            </tr>
		        </table>
			</logic:present>
		</td>
	</tr>
    <logic:present name="CotizacionActionForm" property="cotizacion">
		<tr align="center"> 
	    	<td height="33" valign="top"><br />
	    		<input type="button" value="Volver" onclick="javascript:volver()" class="btn3" />
	        </td>
	    </tr>
    </logic:present>
</table>
</html:form>

<script language="javaScript">
	
	function volver() {
		document.forms[0].submit();
	}
	function recargar() {
		formu = document.getElementById("frm");
		document.getElementById("operacion").value = "recargar";
		formu.submit();
	}
	
	var listaDivs = new Array('antecedentes', 'deposito', 'indemnizacion');
	var tabDefault = "${tabActual}";
	var tabActual = "${tabActual}";
	
	if ('${CotizacionActionForm.cotizacion}' != '') { //hay cotizante
		//esto se ejecuta solo al cargar la pagina, no al cambiar tab
		if (document.getElementById("newRutTrabajador").value != ""){
			if ((document.getElementById("newRutTrabajador").value).length >2){
				document.getElementById("newRutTrabajador").value = (document.getElementById("newRutTrabajador").value).replace(' ','-');
			}
		}
		//document.getElementById('fechaIniDep').value = document.getElementById('fechaIni').value;
		//document.getElementById('fechaFinDep').value = document.getElementById('fechaFin').value;
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
</script>