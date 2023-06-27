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
<html:hidden styleId="mostrar" property="mostrar" />
<html:hidden styleId="operacion" property="operacion" value="volver"/>
<html:hidden styleId="periodo" property="periodo" />
<input type="hidden" id="f_periodo" value=""/>
<html:hidden styleId="tipoPrevision" property="tipoPrevision" />
<input type="hidden" id="tipoProcesoActual" value="" />
<html:hidden styleId="idCotizante" property="rutTrabOrigin" />

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td valign="top" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="textos-formularios3">
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
		    </table>
	    <logic:notEqual name="CotizacionActionForm" property="mostrar" value="new">
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
	    </logic:notEqual>
	    <br />
	    
		<html:messages id="msg" message="true">
			<div class="msgExito">${msg}</div>
		</html:messages>
		
   		<bean:define id="errores" name="CotizacionActionForm" property="errores" type="java.util.ArrayList" toScope="page"/>
   				<bean:define id="mensajesErrores" name="CotizacionActionForm" property="mensajesErrores" type="java.util.HashMap" toScope="page"/>
   		
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
   		
   		<br />
        <logic:present name="CotizacionActionForm" property="cotizacion">
		<input type="hidden" id="cotizacion.tasaTrabPesado" name="cotizacion.tasaTrabPesado" value="-1"/>
		<html:hidden styleId="afpVoluntario" property="cotizante.afpVoluntario" />
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#CCCCCC">
              <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otros', this);" id="otrosTd">MVTO. PERSONAL</td>
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
				           <tr id="rentaTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	$ <c:out value="${ CotizacionActionForm.cotizacion.rentaImp }"></c:out>
				                	<html:hidden property="cotizacion.rentaImp" styleId="rentaImp"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr id="diasTrabajadosTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Dias Trabajados</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<c:out value="${ CotizacionActionForm.cotizante.numDiasTrabajados }"></c:out>&nbsp;
				                	<html:hidden property="cotizante.numDiasTrabajados" styleId="numDiasTrabajados"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("diasTrabajados") == null ? "" : ((java.util.HashMap)mensajesErrores).get("diasTrabajados")%></td>
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
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES TRABAJADOR DEPENDIENTE</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<c:out value="${ CotizacionActionForm.cotizacion.afpvRutDpndiente }"></c:out>&nbsp;
					                <html:hidden property="cotizacion.afpvRutDpndiente" styleId="newRutTrabajadorDep"/>
					            </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutVoluntario")%></td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<c:out value="${CotizacionActionForm.cotizacion.afpvNombreDpndiente}"></c:out>&nbsp;
					                <html:hidden property="cotizacion.afpvNombreDpndiente" styleId="newNombreDep"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombresVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombresVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				                	<c:out value="${CotizacionActionForm.cotizacion.afpvAplldioPatDpndiente}"></c:out>&nbsp;
					                <html:hidden property="cotizacion.afpvAplldioPatDpndiente" styleId="apellidoPatDep"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidosVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidosVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				                	<c:out value="${CotizacionActionForm.cotizacion.afpvAplldioMatDpndiente}"></c:out>&nbsp;
					                <html:hidden property="cotizacion.afpvAplldioMatDpndiente" styleId="apellidoMatDep"/>
				                </td>
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
										<c:forEach var="entidadPension" items="${CotizacionActionForm.entidadesPension}">
					               			<c:choose>
					               				<c:when test="${entidadPension.id eq CotizacionActionForm.cotizante.idEntPensionReal}">
					               					<c:out value="${entidadPension.nombre}"></c:out>&nbsp;
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>		                     		
			                     		<html:hidden property="cotizante.idEntidadAFPVReal" styleId="idEntPensionCotVol"/>&nbsp;
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.prevObligatorioAFP }"></c:out>
		                   			<html:hidden property="cotizacion.prevObligatorioAFP" styleId="prevObligatorioAFPVol"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
		                 	<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.previsionAhorro }"></c:out>
		                   			<html:hidden property="cotizacion.previsionAhorro" styleId="afpAhorroVol"/>
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
			                    		<c:forEach var="entidadesPension" items="${CotizacionActionForm.entidadesPension}">
					               			<c:choose>
					               				<c:when test="${entidadesPension.id eq CotizacionActionForm.cotizante.idEntidadAFPVReal}">
					               					<c:out value="${entidadesPension.nombre}"></c:out>&nbsp;
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
				               			<html:hidden property="cotizante.idEntPensionReal" styleId="idEntPensionReal"/>&nbsp;
					               	</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
				    	</table>
				    </div>		     		
<!-- movto pers --> <div id="otros" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">	
			        	<nested:iterate id="m" name="CotizacionActionForm" property="movtosPersonal" indexId="countMP" type="cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal">
			        	<c:if test="${m.idTipoMovReal ne -1 or countMP eq 0}">
			        		<table width="100%" border="0" cellpadding="0" cellspacing="1" id="mpTable${countMP}">
				            	<tr> 				        	
					           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">MOVIMIENTO DE PERSONAL: ${countMP + 1}</td>
					           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
					            </tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tipo de Movimiento</td>
				                   	<td width="50%" align="left" valign="middle" class="textos_formularios">
				                   		<span style="padding-bottom: 3px;">
											<c:forEach var="tiposMovPersonal" items="${CotizacionActionForm.tiposMovPersonal}">
												<nested:hidden  property="idTipoMovReal" styleId="idTipoMovReal${countMP}" />
						               			<c:choose>
						               				<c:when test="${tiposMovPersonal.id eq m.idTipoMovReal}">
						               					<c:out value="${tiposMovPersonal.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
				                     	</span>&nbsp;
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Inicio</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
				                   		<nested:hidden property="inicio" styleId="inicioMP${countMP}Hidd" />
				                   		<c:out value="${ m.inicio }"></c:out>&nbsp;
				                   	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Fin</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
				                   		<nested:hidden property="termino" styleId="terminoMP${countMP}Hidd" />
				                   		<c:out value="${ m.termino }"></c:out>&nbsp;
				                   	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("terminoMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("terminoMovPersonal"+countMP)%></td>
					        	</tr>
					           	<tr> 
				                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad Pagadora de Subsidio</td>
				                   	<td align="left" valign="middle" class="textos_formularios">
										<span style="padding-bottom: 3px;">
											<c:forEach var="entidadSil" items="${CotizacionActionForm.entidadesSIL}">
						               			<c:choose>
						               				<c:when test="${entidadSil.id eq m.idEntidadSil}">
						               					<c:out value="${entidadSil.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
						               	</span>&nbsp;
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP)%></td>
					        	</tr>
					    	</table>
			        	</c:if>					        
					    </nested:iterate>
				    	<br /><br />&nbsp;
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
    		<input type="button" value="Volver" onclick="javascript:volver()" class="btn3" />
        </td>
    </tr>
    </logic:present>
</table>
</html:form>

<script language="javaScript">
	function volver() 
	{
		document.forms[0].submit();
	}

	function recargar() 
	{
		formu = document.getElementById("frm");
		document.getElementById("operacion").value = "recargar";
		formu.submit();
	}

	var listaDivs = new Array('antecedentes', 'prevision', 'otros');
	var tabDefault = "${tabActual}";
	var tabActual = "${tabActual}";
	//var numMaxMovimientos = ${CotizacionActionForm.numMaxMovimientos};
	
	if ('${CotizacionActionForm.cotizacion}' != '') 
	{ //hay cotizante
		if (document.getElementById(tabDefault) != null)
			cambiaDiv(tabDefault);

		recalculaAFP();
	}

	function recalculaAFP()
	{
		var totalAfpVolDiv = new Number(0);
		var prevObligatorioAFPVol = new Number(limpiaNumero(document.getElementById('prevObligatorioAFPVol').value, ''));
		var afpAhorroVol = new Number(limpiaNumero(document.getElementById('afpAhorroVol').value, ''));
	
		if (prevObligatorioAFPVol > 0)
			totalAfpVolDiv += prevObligatorioAFPVol;
		else
			document.getElementById('prevObligatorioAFPVol').value = 0;
		if (afpAhorroVol > 0)
			totalAfpVolDiv += afpAhorroVol;
		else
			document.getElementById('afpAhorroVol').value = 0;
		document.getElementById("totalAfp").value = totalAfpVolDiv;
		document.getElementById("totalAfpVolDiv").innerHTML = '$ ' + formatNumero(totalAfpVolDiv);
	}
</script>
