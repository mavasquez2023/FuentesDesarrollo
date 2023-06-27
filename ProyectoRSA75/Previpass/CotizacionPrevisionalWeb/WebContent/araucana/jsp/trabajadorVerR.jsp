<%@ include file="/html/comun/taglibs.jsp" %>

<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>

<html:form action="/EditarCotizacion" styleId="frm">
<html:hidden property="accion" name="accion" value="inicio" />
<html:hidden property="subAccion" name="subAccion" value="trabajadores"/>
<html:hidden property="subSubAccion" name="subSubAccion" value="nominaVer" />
<html:hidden property="rutEmpresa" />
<html:hidden property="rutTrabOrigin" />
<html:hidden property="idConvenio" />
<html:hidden property="idMutual" />
<html:hidden property="diasTopeAF" />
<html:hidden property="aporteCCAFFON" />
<html:hidden property="idCotizPendiente" />
<html:hidden property="topeAFP" />
<html:hidden property="topeINP" />
<html:hidden property="topeIndemn" />
<html:hidden property="topeCesantia" />
<html:hidden property="apEmpIndSegCes" />
<html:hidden property="apEmpPFSegCes" />
<html:hidden property="apTrabIndSegCes" />
<html:hidden property="apTrabPFSegCes" />
<html:hidden property="mostrar" />
<html:hidden property="diasXMes" />
<html:hidden property="operacion" value="volver"/>
<html:hidden property="periodo" />
<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" id="segCesantiaEmpleadorTotal" value="3" />
<input type="hidden" id="segCesantiaEmpleador" value="2.4" />
<input type="hidden" id="segCesantiaTrabajador" value="0.6" />
<input type="hidden" value="" name="opExCaja" id="opExCaja" />
<input type="hidden" name="idCaja" id="idCaja" value="${CotizacionActionForm.caja.id}"/>
<html:hidden styleId="tipoPrevision" property="tipoPrevision" />
<html:hidden styleId="idCotizante" property="rutTrabOrigin" />

<html:hidden styleId="minTasaIndemn" property="minTasaIndemn" />
<html:hidden styleId="maxTasaIndemn" property="maxTasaIndemn" />

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
		      		<td align="center"><input type="button" value="Aplicar" class="btn3" onclick="recargar();"/></td>
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
		              <td align="center" valign="middle" bordercolor="#CCCCCAC" class="barra_tablas">RUT</td>
		              <td align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
		              <td align="center" valign="middle" bordercolor="#FFFFFF" class="barra_tablas">Apellidos</td>
		            </tr>
		            <tr>
		              <td align="left" nowrap="nowrap" class="textos_formularios">${CotizacionActionForm.rutTrabFormat}&nbsp;</td>              	
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.nombre}&nbsp;</td>
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizante.apellidos}&nbsp;</td>
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
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="1" bordercolor="#CCCCCC">
			<tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <nested:equal property="prodCaja" value="true">
              <td align="center" class="barra_tablas" onclick="javascript:recalculaSalud();javascript:cambiaDiv('salud', this);" id="saludTd">ISAPREX</td>
              <td align="center" class="barra_tablas" onclick="javascript:recalculaAFP();javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:recalculaINP();javascript:cambiaDiv('inpTab', this);" id="inpTabTd">INP/FONASA</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('apv', this);" id="apvTd">APV</td>
              </nested:equal>
              <logic:notEmpty name="CotizacionActionForm" property="caja.nombre">
              		<td align="center" class="barra_tablas" onclick="javascipt:recalculaCaja();javascript:cambiaDiv('caja', this);" id="cajaTd">CAJA</td>
              </logic:notEmpty>
              <logic:notEmpty name="CotizacionActionForm" property="nomMutual">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('mutual', this);" id="mutualTd">MUTUAL</td>
              </logic:notEmpty>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otros', this);" id="otrosTd">MVTO. PERSONAL</td>
            </tr>
            <tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('deposito', this);" id="depositoTd" colspan="2">DEP&Oacute;SITO CONVENIDO</td>
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('indemnizacion', this);" id="indemnizacionTd" colspan="2">INDEMNIZACIONES</td>            
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('otrospagos', this);" id="otrospagosTd" colspan="4">OTROS PAGOS</td>                        
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
									<logic:present name="CotizacionActionForm" property="generos">
										<c:forEach var="genero" items="${CotizacionActionForm.generos}">
					               			<c:choose>
					               				<c:when test="${genero.id eq CotizacionActionForm.cotizante.idGeneroReal}">
					               					<c:out value="${genero.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
					               		<html:hidden property="cotizante.idGeneroReal" styleId="idGenero"/>
					               	</logic:present>&nbsp;
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               		$ <c:out value="${ CotizacionActionForm.cotizacion.rentaImp }"></c:out>&nbsp;
				                	<html:hidden property="cotizacion.rentaImp" styleId="rentaImp"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr> 
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
									<logic:present name="CotizacionActionForm" property="sucursales">
										<c:forEach var="sucursal" items="${CotizacionActionForm.sucursales}">
					               			<c:choose>
					               				<c:when test="${sucursal.idSucursal eq CotizacionActionForm.cotizante.idSucursal}">
					               					<c:out value="${sucursal.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
					               		<html:hidden property="cotizante.idSucursal" styleId="idSucursal"/>
					               	</logic:present>&nbsp;
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
			                    	<logic:present name="CotizacionActionForm" property="entidadesSalud">
			                    		<c:forEach var="entidadesSalud" items="${CotizacionActionForm.entidadesSalud}">
					               			<c:choose>
					               				<c:when test="${entidadesSalud.id eq CotizacionActionForm.cotizante.idEntSaludReal}">
					               					<c:out value="${entidadesSalud.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
				                      	<html:hidden property="cotizante.idEntSaludReal" styleId="idEntSaludReal"/>
									</logic:present>&nbsp;
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadSalud")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
				               		$ <c:out value="${ CotizacionActionForm.cotizacion.saludObligISAPRE }"></c:out>
		                   			<html:hidden property="cotizacion.saludObligISAPRE" styleId="saludObligISAPRE"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludOblig")%></td>
		                 	</tr>
		                 	<tr id="saludAdicionalTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Adicional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.saludAdicional }"></c:out>
		                   			<html:hidden property="cotizacion.saludAdicional" styleId="saludAdicional"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludAdicional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludAdicional")%></td>
		                 	</tr>
		                 	<tr id="saludPactadaTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pactada</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalSaludDiv">$0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludPactada") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludPactada")%></td>
		                 	</tr>
		                 </table><br>
		                 <table width="100%" border="0" cellpadding="0" cellspacing="1">
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalCotiSaludDiv">$0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalSalud")%></td>
		                 	</tr>
				    	</table>
		     		</div>
<!-- prevision -->  <div id="prevision" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
		     			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ADMINISTRADORA DE FONDOS DE PENSIONES</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Sistema Previsional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                    	<logic:present name="CotizacionActionForm" property="entidadesPension">
			                    		<span style="padding-bottom: 3px;">
				                    		<c:forEach var="entidadesPension" items="${CotizacionActionForm.entidadesPension}">
						               			<c:choose>
						               				<c:when test="${entidadesPension.id eq CotizacionActionForm.cotizante.idEntPensionReal}">
						               					<c:out value="${entidadesPension.nombre}"></c:out>
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
					               			<html:hidden property="cotizante.idEntPensionReal" styleId="idEntPensionReal"/>
						               	</span>
									</logic:present>&nbsp;
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.prevObligatorioAFP }"></c:out>
		                   			<html:hidden property="cotizacion.prevObligatorioAFP" styleId="prevObligatorioAFP"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
							<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.previsionAhorro }"></c:out>
		                   			<html:hidden property="cotizacion.previsionAhorro" styleId="afpAhorro"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionAhorro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionAhorro")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible SIS</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.rentaImponibleSIS }"></c:out>
		                   			<html:hidden property="cotizacion.rentaImponibleSIS" styleId="rentaImponibleSIS"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSIS")%></td>
		                 	</tr>
		                 	<tr>
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Prevision SIS</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.previsionSIS }"></c:out>
		                   			<html:hidden property="cotizacion.previsionSIS" styleId="previsionSIS"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalAfpDiv">$0</div>
		                   			<html:hidden property="cotizacion.previsionTotal" styleId="totalAfp"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("totalPrevision") == null ? "" : ((java.util.HashMap)mensajesErrores).get("totalPrevision")%></td>
		                 	</tr>
		                 </table>
		                 <table width="100%" border="0" cellpadding="0" cellspacing="1" id="tableSegCes">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">SEGURO DE CESANTIA</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para Seguro de Cesant&iacute;a</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.segCesRemImp }"></c:out>
		                   			<html:hidden property="cotizacion.segCesRemImp" styleId="segCesRemImp"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.segCesTrab }"></c:out>
		                   			<html:hidden property="cotizacion.segCesTrab" styleId="segCesTrabajador"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		$ <c:out value="${ CotizacionActionForm.cotizacion.segCesEmpl }"></c:out>
			                   		<html:hidden property="cotizacion.segCesEmpl" styleId="segCesEmpresa"/>
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
			                    	<logic:present name="CotizacionActionForm" property="porcentajeTrabPesado">
			                    		<c:forEach var="tasaTrabPesado" items="${CotizacionActionForm.porcentajeTrabPesado}">
					               			<c:choose>
					               				<c:when test="${tasaTrabPesado.value eq CotizacionActionForm.cotizacion.tasaTrabPesado}">
					               					<c:out value="${tasaTrabPesado.label}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
				                   		<html:hidden property="cotizacion.tasaTrabPesado" styleId="idTasaTraPesa"/>
				                   	</logic:present>
				                   	<logic:notPresent name="CotizacionActionForm" property="porcentajeTrabPesado">
				                   		<html:hidden property="cotizacion.tasaTrabPesado" styleId="idTasaTraPesa"/>
				                   	</logic:notPresent>&nbsp;
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Nombre Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<c:out value="${ CotizacionActionForm.cotizacion.tipoTrabPesado }"></c:out>&nbsp;
			                   		<html:hidden property="cotizacion.tipoTrabPesado" styleId="nombreTrabPesado"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("idTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("idTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<c:out value="${ CotizacionActionForm.cotizacion.trabPesado }"></c:out>&nbsp;
		                     		<html:hidden property="cotizacion.trabPesado" styleId="montoTrabPesado"/>
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
				    </div>
		     		<div id="inpTab" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
<!-- INP -->		<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">PARAMETROS INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad Ex-Caja</td>
			                   	<td align="left" valign="middle" class="textos_formularios">&nbsp;
									<logic:present name="CotizacionActionForm" property="entidadesExCaja">
										<span style="padding-bottom: 3px;">
				                    		<c:forEach var="entExCaja" items="${CotizacionActionForm.entidadesExCaja}">
						               			<c:choose>
						               				<c:when test="${entExCaja.id eq CotizacionActionForm.cotizante.idEntExCaja}">
						               					<c:out value="${entExCaja.nombre}"></c:out>
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
			                     			<html:hidden property="cotizante.idEntExCaja" styleId="idEntExCaja"/>
						               	</span>
									</logic:present>
				                   	<logic:notPresent name="CotizacionActionForm" property="entidadesExCaja">
				                   		<html:hidden property="cotizante.idEntExCaja" styleId="idEntExCaja"/>
				                   	</logic:notPresent>&nbsp;
					            </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadExCaja") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadExCaja")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">C&oacute;digo R&eacute;gimen Impositivo</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
		                     		<span style="padding-bottom: 3px;"> 
										<logic:present name="CotizacionActionForm" property="codRegImp">
											<c:forEach var="regImp" items="${CotizacionActionForm.codRegImp}">
						               			<c:choose>
						               				<c:when test="${regImp.idRegImpositivo eq CotizacionActionForm.cotizante.idRegimenImp}">
						               					<c:out value="${regImp.descripcion}"></c:out>
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>										
				                     		<html:hidden property="cotizante.idRegimenImp" styleId="idRegimenImp"/>
											<logic:iterate id="codReg" name="CotizacionActionForm" property="codRegImp">
				                   				<html:hidden property="id" value="${codReg.tasaPension}" styleId="codReg-${codReg.idRegImpositivo}"/>
				                   			</logic:iterate>
			                   			</logic:present>&nbsp;
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
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">COTIZACIONES INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para INP</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="remImpPens"></div>
		                     		<html:hidden property="cotizacion.remImpPension" styleId="remImpPension"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponiblePension") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponiblePension")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pensi&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.prevObligatorioINP }"></c:out>
		                     		<html:hidden property="cotizacion.prevObligatorioINP" styleId="prevObligatorioINP"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("pensionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("pensionOblig")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n de Salud FONASA</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.saludObligFONASA }"></c:out>
		                     		<html:hidden property="cotizacion.saludObligFONASA" styleId="saludObligFONASA"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fonasaOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fonasaOblig")%></td>
				        	</tr>
				           	<tr id="accTrabINPTR"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Accidentes del Trabajo</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.accTrabajoINP }"></c:out>
		                     		<html:hidden property="cotizacion.accTrabajoINP" styleId="accTrabajoINP"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Desahucio</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.inpDesahucio }"></c:out>
		                     		<html:hidden property="cotizacion.inpDesahucio" styleId="inpDesahucio" />
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
				        		<table width="100%" border="0">
						        	<tr> 
						            	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">ASIGNACION FAMILIAR</td>
						           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
						           </tr>
						           <tr> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Tramo de Asignaci&oacute;n Familiar</td>
					                   	<td align="left" valign="middle" class="textos_formularios">
											<logic:present name="CotizacionActionForm" property="tramosAsigFam">
												<span style="padding-bottom: 3px;">
													<c:forEach var="tramoAsigFam" items="${CotizacionActionForm.tramosAsigFam}">
								               			<c:choose>
								               				<c:when test="${tramoAsigFam.id eq CotizacionActionForm.cotizante.idTramoRealINP}">
								               					<c:out value="${tramoAsigFam.nombre}"></c:out>
								               				</c:when>
								               			</c:choose>
								               		</c:forEach>
						                    		<html:hidden property="cotizante.idTramoRealINP" styleId="idTramoINP"/>
								               	</span>
											</logic:present>
											<logic:notPresent name="CotizacionActionForm" property="tramosAsigFam">
												<html:hidden property="cotizante.idTramoRealINP" styleId="idTramoINP"/>
											</logic:notPresent>&nbsp;
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
						        	</tr>
						         	<tr> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
					                   	<td align="left" valign="middle" class="textos_formularios">
				                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaSimpleINP }"></c:out> Simples<br>
				                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaMaternaINP }"></c:out> Maternales<br>
				                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaInvalidezINP }"></c:out> Invalidez<br>
				                     		<html:hidden property="cotizante.numCargaSimpleINP" styleId="cargasSimplesINP" />
				                     		<html:hidden property="cotizante.numCargaMaternaINP" styleId="cargasMaternalesINP"/>
				                    		<html:hidden property="cotizante.numCargaInvalidezINP" styleId="cargasInvalidezINP"/>
		                    			</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
						        	</tr>
						           <tr id="asigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamiliarINP }"></c:out>
				                     		<html:hidden property="cotizacion.asigFamiliarINP" styleId="asigFamiliarINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
						        	</tr>
						           <tr id="asigFamRetroTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamRetroINP }"></c:out>
				                     		<html:hidden property="cotizacion.asigFamRetroINP" styleId="asigFamRetroINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
						        	</tr>
						           <tr id="reintegroAsigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamReintINP }"></c:out>
				                     		<html:hidden property="cotizacion.asigFamReintINP" styleId="reintegroAsigFamINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarReint") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarReint")%></td>
						        	</tr>
						           <tr id="reintegroAsigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Asignaci&oacute;n Familiar</b></td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		<div id="totalAsignFamDivINP">$0</div>
				                     		<input type="hidden" name="totASigFamHiddINP" id="totASigFamHiddINP" value="0">
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;</td>
						        	</tr>
						        </table>
						        </td>
					        </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Bonificaci&oacute;n Art. 19 Ley 15.386</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.inpBonificacion }"></c:out>
		                     		<html:hidden property="cotizacion.inpBonificacion" styleId="inpBonificacion"/>
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
		     		<div id="apv" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
<!-- APV -->   			<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS AHORRO PREVISIONAL VOLUNTARIO COLECTIVO</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad APV</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                     	<logic:present name="CotizacionActionForm" property="entidadesApvs">
			                     		<span style="padding-bottom: 3px;">
											<c:forEach var="entidadApvs" items="${CotizacionActionForm.entidadesApvs}">
						               			<c:choose>
						               				<c:when test="${entidadApvs.id eq CotizacionActionForm.cotizante.idEntidadAPVCReal}">
						               					<c:out value="${entidadApvs.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
			                     			<html:hidden property="cotizante.idEntidadAPVCReal" styleId="idAPVC"/>
			                     		</span>
			                     	</logic:present>
									<logic:notPresent name="CotizacionActionForm" property="entidadesApvs">
										<html:hidden property="cotizante.idEntidadAPVCReal" styleId="idAPVC"/>
									</logic:notPresent>&nbsp;
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadApvc")%></td>
		                 	</tr>
		                	<tr>
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero Contrato</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<c:out value="${CotizacionActionForm.cotizacion.apvcNumContr}"></c:out>&nbsp;
		                   			<html:hidden property="cotizacion.apvcNumContr" styleId="numContratoAPVC"/>
		                   		</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("codContratoApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("codContratoApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ <c:out value="${CotizacionActionForm.cotizacion.apvcAporteTrab}"></c:out>
		                   			<html:hidden property="cotizacion.apvcAporteTrab" styleId="aporteTraAPVC"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ <c:out value="${CotizacionActionForm.cotizacion.apvcAporteEmpl}"></c:out>
		                   			<html:hidden property="cotizacion.apvcAporteEmpl" styleId="aporteEmpAPVC"/>
		                   	 	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteEmpApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteEmpApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Total APV Colectivo</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			<div id="totalAPVC" style="font-weight: bold">$0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                 	</tr>
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				        </table>
			     		<nested:iterate id="apv" name="CotizacionActionForm" property="apvs" indexId="countAPV" type="cl.araucana.cp.distribuidor.hibernate.beans.ApvVO">
				     		<c:forEach var="ap" items="apv">
			     				<table width="100%" border="0" cellpadding="0" cellspacing="0" id="apvTable${countAPV}">
				                	<tr> 
				                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad APV ${countAPV + 1}</td>
				                   		<td align="left" valign="middle" class="textos_formularios">
				                   			<nested:hidden property="id"/>
				                    		<span style="padding-bottom: 3px;">
				                    			<c:forEach var="entidadApvs" items="${CotizacionActionForm.entidadesApvs}">
				                    				<c:if test="${entidadApvs.id eq apv.idEntidadApv}">
				                    					<c:out value="${entidadApvs.nombre}"></c:out>&nbsp;
				                    				</c:if>
							               		</c:forEach>
				                     		</span>&nbsp;
				                     	</td>
						               	<td align="left" class="textos_formularios" width="20%">&nbsp;
											<%=((java.util.HashMap)mensajesErrores).get("entidadApv"+countAPV) == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadApv"+countAPV)%>
										</td>
				                 	</tr>
				                	<tr> 
				                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto APV ${countAPV + 1}</td>
				                   		<td align="left" valign="middle" class="textos_formularios"> 
				                   			$ <c:out value="${apv.montoFormat}"></c:out>
				                     	</td>
							               	<td align="left" class="textos_formularios" width="20%">&nbsp;
												<%=((java.util.HashMap)mensajesErrores).get("montoApv"+countAPV) == null ? "" : ((java.util.HashMap)mensajesErrores).get("montoApv"+countAPV)%>
											</td>
				                 	</tr>
				                 	<tr> 
				                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Régimen APV ${countAPV + 1}</td>
				                   		<td align="left" valign="middle" class="textos_formularios"> 
				                   			&nbsp;&nbsp;<c:out value="${apv.regimen}"></c:out>
				                     	</td>
							               	<td align="left" class="textos_formularios" width="20%">&nbsp;</td>
				                 	</tr>
								</table>
				     		</c:forEach>
			     		</nested:iterate>
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
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte CCAF ${CotizacionActionForm.aporteCCAFFON}%</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.aporteCaja }"></c:out>
		                     		<html:hidden property="cotizacion.aporteCaja" styleId="aporteCaja"/>
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
				                   	<logic:present name="CotizacionActionForm" property="tramosAsigFam">
				                   		<logic:iterate id="trAsigFam" name="CotizacionActionForm" property="tramosAsigFam">
				                   			<html:hidden property="id" value="${trAsigFam.valorCarga}" styleId="${trAsigFam.id}-trAsigFam"/>
				                   		</logic:iterate>
				                   		<span style="padding-bottom: 3px;">
											<c:forEach var="tramosAsigFam" items="${CotizacionActionForm.tramosAsigFam}">
						               			<c:choose>
						               				<c:when test="${tramosAsigFam.id eq CotizacionActionForm.cotizante.idTramoReal}">
						               					<c:out value="${tramosAsigFam.nombre}"></c:out>&nbsp;
						               				</c:when>
						               			</c:choose>
						               		</c:forEach>
				                     	</span>
				                     </logic:present>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
		                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaSimple }"></c:out> Simples<br>
		                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaMaterna }"></c:out> Maternales<br>
		                     		<c:out value="${ CotizacionActionForm.cotizante.numCargaInvalidez }"></c:out> Invalidez<br>
		                     		<html:hidden property="cotizante.numCargaSimple" styleId="cargasSimples" />
		                     		<html:hidden property="cotizante.numCargaMaterna" styleId="cargasMaternales"/>
		                    		<html:hidden property="cotizante.numCargaInvalidez" styleId="cargasInvalidez"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
				           <tr id="asigFamTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamiliar }"></c:out>
		                     		<html:hidden property="cotizacion.asigFamiliar" styleId="asigFam"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
				        	</tr>
				           <tr id="asigFamRetroTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamRetro }"></c:out>
		                     		<html:hidden property="cotizacion.asigFamRetro" styleId="asigFamRetro"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
				        	</tr>
				           <tr id="reintegroAsigFamTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.asigFamReint }"></c:out>
		                     		<html:hidden property="cotizacion.asigFamReint" styleId="reintegroAsigFam"/> 
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarReint") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarReint")%></td>
				        	</tr>
				           <tr id="reintegroAsigFamTr"> 
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
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.ccafCredito }"></c:out>
		                     		<html:hidden property="cotizacion.ccafCredito" styleId="creditoPersonal"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("creditoPersonal") == null ? "" : ((java.util.HashMap)mensajesErrores).get("creditoPersonal")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Leasing C.C.A.F.</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.ccafLeasing }"></c:out>
		                     		<html:hidden property="cotizacion.ccafLeasing" styleId="leasing"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("leasing") == null ? "" : ((java.util.HashMap)mensajesErrores).get("leasing")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Convenio Dental</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.ccafDental }"></c:out>
		                     		<html:hidden property="cotizacion.ccafDental" styleId="convenioDental"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("convenioDental") == null ? "" : ((java.util.HashMap)mensajesErrores).get("convenioDental")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Seguro de Vida</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.ccafSeguro }"></c:out>
		                     		<html:hidden property="cotizacion.ccafSeguro" styleId="seguro"/>
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
		                   			<input type="hidden" id="tasaMutual" value="${CotizacionActionForm.tasaMutual}"/>
		                   		</td>
		                   		<td align="left" class="textos_formularios">&nbsp;</td>
		                	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.mutualImp }"></c:out>
		                     		<html:hidden property="cotizacion.mutualImp" styleId="rentaImpMutual"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImpMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImpMutual")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="cotizacionMutualDiv"></div>
		                     		$ <c:out value="${ CotizacionActionForm.cotizacion.accTrabajoMutual}"></c:out>
		                     		<html:hidden property="cotizacion.accTrabajoMutual" styleId="cotizacionMutual"/>&nbsp;
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
										<logic:present name="CotizacionActionForm" property="tiposMovPersonal">
					                   		<span style="padding-bottom: 3px;">
												<c:forEach var="tiposMovPersonal" items="${CotizacionActionForm.tiposMovPersonal}">
													<nested:hidden  property="idTipoMovReal" styleId="idTipoMovReal${countMP}" />
							               			<c:choose>
							               				<c:when test="${tiposMovPersonal.id eq m.idTipoMovReal}">
							               					<c:out value="${tiposMovPersonal.nombre}"></c:out>&nbsp;
							               				</c:when>
							               			</c:choose>
							               		</c:forEach>
					                     	</span>
					                     </logic:present>&nbsp;
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
										<logic:present name="CotizacionActionForm" property="entidadesSIL">
											<span style="padding-bottom: 3px;">
												<c:forEach var="entidadSil" items="${CotizacionActionForm.entidadesSIL}">
							               			<c:choose>
							               				<c:when test="${entidadSil.id eq m.idEntidadSil}">
							               					<c:out value="${entidadSil.nombre}"></c:out>&nbsp;
							               				</c:when>
							               			</c:choose>
							               		</c:forEach>
							               	</span>
										</logic:present>&nbsp;
			                    	</td>
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP)%></td>
					        	</tr>
					    	</table>
						</nested:iterate>
				    	<br /><br />
					</div>
     <!--DEP CONV--><div id="deposito" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
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
					               				<c:when test="${entidadApvs.id eq CotizacionActionForm.cotizacion.idEntDep}">
					               					<c:out value="${entidadApvs.nombre}"></c:out>&nbsp;
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
		                     			<html:hidden property="cotizacion.idEntDep" styleId="idEntDep"/>&nbsp;
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
     <!-- INDEMN --><div id="indemnizacion" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
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
<!--OTROS PAGOS     --><div id="otrospagos" STYLE="position:relative; top:0px; left:0px; width:100%; display:none;">
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS OTROS PAGOS</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="barra_tablas">Monto AFBR</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ <c:out value="${ CotizacionActionForm.cotizacion.otrosAFBR }"></c:out>&nbsp;
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
	
	function calculaCotizacionMutual() 
	{
		var tasaMutual = document.getElementById("tasaMutual").value;
		var rentaImpMutual = document.getElementById("rentaImpMutual").value.replace(/\./gi, "");
		var cotizacionMutual = Math.round( (tasaMutual * rentaImpMutual)/100 );
		document.getElementById("cotizacionMutualDiv").innerHTML = "$ " + formatNumero(limpiaNumero(cotizacionMutual, ''));
	}
	var listaDivs = new Array('antecedentes', 'salud', 'prevision', 'inpTab', 'apv', 'caja', 'mutual', 'otros', 'deposito', 'indemnizacion', 'otrospagos');
	var tabDefault = "${tabActual}";
	var tabActual = "${tabActual}";
	var sinCaja = false, sinMutual = false;

	var numMaxMovimientos = ${CotizacionActionForm.numMaxMovimientos};
	var numMaxAPVs = ${CotizacionActionForm.numMaxAPVs};
	
	if ('${CotizacionActionForm.nomMutual}' == '')
		sinMutual = true;
	if ('${CotizacionActionForm.caja.nombre}' == '')
		sinCaja = true;
	
	if ('${CotizacionActionForm.cotizacion}' != '') 
	{ //hay cotizante
		if (document.getElementById('idRegimenImp').value != -1)
			if ( document.getElementById('idRegimenImp') && document.getElementById('idRegimenImp')!=null )
				if ( document.getElementById('codReg-' + document.getElementById('idRegimenImp').value)
						&& document.getElementById('codReg-' + document.getElementById('idRegimenImp').value)!=null )
					document.getElementById('tasaCotINP').innerHTML = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value + ' %';
		document.getElementById("remImpPens").innerHTML = "$ " + document.getElementById("rentaImp").value;
		document.getElementById('totalAPVC').innerHTML = "$ " + (<c:if test="${empty CotizacionActionForm.cotizacion.apvcAporteTrab}">0</c:if>${CotizacionActionForm.cotizacion.apvcAporteTrab} + <c:if test="${empty CotizacionActionForm.cotizacion.apvcAporteEmpl}">0</c:if>${CotizacionActionForm.cotizacion.apvcAporteEmpl});
	}
	
	if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
	{
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
			if (document.getElementById('idTipoMovReal'+i).value == '-1' ||
				document.getElementById('inicioMP' + i + 'Hidd').value == '-1' ||
				document.getElementById('terminoMP' + i + 'Hidd').value == '-1'
				)
				document.getElementById("mpTable" + i).style.display = 'none';
			else
				document.getElementById("mpTable" + i).style.display = 'block';
		}

		//Depósito Convenido		
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
	
	document.getElementById('tipoProcesoActual').value = document.getElementById("tipoProceso").value;
	
	function recalculaSalud() 
	{
		var total = new Number(0);
		var saludObligISAPRE = new Number(limpiaNumero(document.getElementById('saludObligISAPRE').value, ''));
		var saludAdicional = new Number(limpiaNumero(document.getElementById('saludAdicional').value, ''));
	
		if (saludObligISAPRE > 0) {
			document.getElementById('saludObligFONASA').value = 0;
			total += saludObligISAPRE;
		}
		else
			document.getElementById('saludObligISAPRE').value = 0;
		
		if (saludAdicional > 0)
			total += saludAdicional;
		else
			document.getElementById('saludAdicional').value = 0;
	
		document.getElementById("totalSaludDiv").innerHTML = '$ ' + formatNumero(total);
		document.getElementById("totalCotiSaludDiv").innerHTML = '$ ' + formatNumero(total);
	}

	function recalculaAFP() 
	{
		var totalAfpDiv = new Number(0);
		var totalAfcDiv = new Number(0);
		var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
		var afpAhorro = new Number(limpiaNumero(document.getElementById('afpAhorro').value, ''));
		var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
		var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
		var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
		var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));
	
		if (prevObligatorioAFP > 0)
		{
			document.getElementById('prevObligatorioINP').value = 0;
			document.getElementById('inpDesahucio').value = 0;
			document.getElementById('inpBonificacion').value = 0;
			document.getElementById('idEntExCaja').value = -1;
			document.getElementById('idRegimenImp').value = -1;
			totalAfpDiv += prevObligatorioAFP;
		} else
			document.getElementById('prevObligatorioAFP').value = 0;
		if (afpAhorro > 0)
			totalAfpDiv += afpAhorro;
		else
			document.getElementById('afpAhorro').value = 0;
		document.getElementById("totalAfp").value = totalAfpDiv;
		document.getElementById("totalAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv);
	
		if (segCesTrabajador > 0)
			totalAfcDiv += segCesTrabajador;
		else
			document.getElementById('segCesTrabajador').value = 0;
		if (segCesEmpresa > 0)
			totalAfcDiv += segCesEmpresa;
		else
			document.getElementById('segCesEmpresa').value = 0;
		if (segCesRemImp < 0)
			document.getElementById('segCesRemImp').value = 0;
		document.getElementById("totalAfcDiv").innerHTML = '$ ' + formatNumero(totalAfcDiv);
	
		if (montoTrabPesado < 0)
		{
			document.getElementById('montoTrabPesado').value = 0;
			montoTrabPesado = 0;
		}
	
		document.getElementById("totalPagarAfpDiv").innerHTML = '$ ' + formatNumero(totalAfpDiv + totalAfcDiv + montoTrabPesado);
	}
	
	function recalculaINP() 
	{
		var totalCotizINPDiv = new Number(0);
		var totalAsignFamDivINP = new Number(0);
		var remImpPension = new Number(limpiaNumero(document.getElementById('remImpPension').value, ''));
		var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
		var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));
		var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
		var inpDesahucio = new Number(limpiaNumero(document.getElementById('inpDesahucio').value, ''));
		var asigFamiliarINP = new Number(limpiaNumero(document.getElementById('asigFamiliarINP').value, ''));
		var asigFamRetroINP = new Number(limpiaNumero(document.getElementById('asigFamRetroINP').value, ''));
		var reintegroAsigFamINP = new Number(limpiaNumero(document.getElementById('reintegroAsigFamINP').value, ''));
		var inpBonificacion = new Number(limpiaNumero(document.getElementById('inpBonificacion').value, ''));
	
		if (prevObligatorioINP > 0)
		{	
			document.getElementById('prevObligatorioAFP').value = 0;
			document.getElementById('afpAhorro').value = 0;
			document.getElementById('idTasaTraPesa').value = -1;
			document.getElementById('nombreTrabPesado').value = "";
			document.getElementById('montoTrabPesado').value = 0;
	
			totalCotizINPDiv += prevObligatorioINP;
		} else
			document.getElementById('prevObligatorioINP').value = 0;
		if (saludObligFONASA > 0)
		{
			document.getElementById('saludObligISAPRE').value = 0;
			document.getElementById('saludAdicional').value = 0;
			totalCotizINPDiv += saludObligFONASA;
		} else
			document.getElementById('saludObligFONASA').value = 0;
		if (accTrabajoINP > 0)
			totalCotizINPDiv += accTrabajoINP;
		else
			document.getElementById('accTrabajoINP').value = 0;
		if (inpDesahucio > 0)
			totalCotizINPDiv += inpDesahucio;
		else
			document.getElementById('inpDesahucio').value = 0;
		if (remImpPension < 0)
			document.getElementById('remImpPension').value = 0;
		document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv);
	
		if (sinCaja)//sin caja
		{
			if (asigFamiliarINP > 0)
				totalAsignFamDivINP += asigFamiliarINP;
			else
				document.getElementById('asigFamiliarINP').value = 0;
			if (asigFamRetroINP > 0)
				totalAsignFamDivINP += asigFamRetroINP;
			else
				document.getElementById('asigFamRetroINP').value = 0;
			if (reintegroAsigFamINP > 0)
				totalAsignFamDivINP -= reintegroAsigFamINP;
			else
				document.getElementById('reintegroAsigFamINP').value = 0;
			document.getElementById("totalAsignFamDivINP").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP);
			document.getElementById('totASigFamHiddINP').value = totalAsignFamDivINP;
		}
	
		if (inpBonificacion < 0)
		{
			document.getElementById('inpBonificacion').value = 0;
			inpBonificacion = 0;
		}
		document.getElementById("totalRebajasINPDiv").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP + inpBonificacion);	
		document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv - (totalAsignFamDivINP + inpBonificacion));
	}
	
	function recalculaAsigFamINP() 
	{
		var cargasSimplesINP = new Number(limpiaNumero(document.getElementById('cargasSimplesINP').value, ''));
		var cargasMaternalesINP = new Number(limpiaNumero(document.getElementById('cargasMaternalesINP').value, ''));
		var cargasInvalidezINP = new Number(limpiaNumero(document.getElementById('cargasInvalidezINP').value, ''));
		var AsignFamTmp = new Number(0);
		var totalAsignFamDivINP = new Number(0);
	
		if (document.getElementById("idTramoINP").value != "-1")
		{
			var valorTramo = document.getElementById(document.getElementById("idTramoINP").value + "-trAsigFam").value;
			if (cargasSimplesINP > 0)
				AsignFamTmp += cargasSimplesINP;
			if (cargasMaternalesINP > 0)
				AsignFamTmp += cargasMaternalesINP * 2;
			if (cargasInvalidezINP > 0)
				AsignFamTmp += cargasInvalidezINP * 2;
		
			var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));			
			var asigFamiliarINP = valorTramo * AsignFamTmp;
			if (numDiasTrabajados < new Number(limpiaNumero(document.getElementById('diasTopeAF').value, '')))
				asigFamiliarINP = Math.round((asigFamiliarINP * numDiasTrabajados) / 30);
		
			document.getElementById('asigFamiliarINP').value = asigFamiliarINP;
		} else
			document.getElementById('asigFamiliarINP').value = 0;
		recalculaINP();
	}
	
	function recalculaAsigFam() 
	{	
		var cargasSimples = new Number(limpiaNumero(document.getElementById('cargasSimples').value, ''));
		var cargasMaternales = new Number(limpiaNumero(document.getElementById('cargasMaternales').value, ''));
		var cargasInvalidez = new Number(limpiaNumero(document.getElementById('cargasInvalidez').value, ''));
		var AsignFamTmp = new Number(0);
		var totalAsignFamDiv = new Number(0);
	
		if (document.getElementById("idTramo").value != "-1") {
			var valorTramo = document.getElementById(document.getElementById("idTramo").value + "-trAsigFam").value;
			if (cargasSimples > 0)
				AsignFamTmp += cargasSimples;
			if (cargasMaternales > 0)
				AsignFamTmp += cargasMaternales * 2;
			if (cargasInvalidez > 0)
				AsignFamTmp += cargasInvalidez * 2;
		
			var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));
			var asigFam = valorTramo * AsignFamTmp;
			if (numDiasTrabajados < new Number(limpiaNumero(document.getElementById('diasTopeAF').value, '')))
				asigFam = Math.round((asigFam * numDiasTrabajados) / 30);

			document.getElementById('asigFam').value = asigFam;
		}
		else
			document.getElementById('asigFam').value = 0;
		recalculaCaja();
	}
	
	function recalculaCaja() 
	{
		var totalAsignFamDiv = new Number(0);
		var asigFam = new Number(limpiaNumero(document.getElementById('asigFam').value, ''));
		var asigFamRetro = new Number(limpiaNumero(document.getElementById('asigFamRetro').value, ''));
		var reintegroAsigFam = new Number(limpiaNumero(document.getElementById('reintegroAsigFam').value, ''));
	
		if (asigFam > 0)
			totalAsignFamDiv += asigFam;
		else
			document.getElementById('asigFam').value = 0;
		if (asigFamRetro > 0)
			totalAsignFamDiv += asigFamRetro;
		else
			document.getElementById('asigFamRetro').value = 0;
		if (reintegroAsigFam > 0)
			totalAsignFamDiv -= reintegroAsigFam;
		else
			document.getElementById('reintegroAsigFam').value = 0;
		document.getElementById("totalAsignFamDiv").innerHTML = '$ ' + formatNumero(totalAsignFamDiv);
	}
</script>