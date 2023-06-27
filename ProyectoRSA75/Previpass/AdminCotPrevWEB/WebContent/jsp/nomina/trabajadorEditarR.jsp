<%@ include file="/html/comun/taglibs.jsp" %>
<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
<link href="<c:url value="/js/jscalendar/calendario.css" />" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/calendar.js" />"></script>
<script type="text/javascript" charset="utf-8" src="<c:url value="/js/jscalendar/lang/calendar-es.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/calendario.js" />"></script>
<script type="text/javascript" src="<c:url value="/js/funciones.js" />"></script>
<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head><body>
<html:form action="/VerCotizacion"><!--  onsubmit="return validaFormulario()" -->
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="trabajadores"/>
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa" property="rutEmpresa" />
<html:hidden styleId="rutTrabajador" property="rutTrabOrigin" />
<html:hidden styleId="convenio" property="idConvenio" />
<html:hidden styleId="idMutual" property="idMutual" />
<html:hidden styleId="diasTopeAF" property="diasTopeAF" />
<html:hidden styleId="aporteCCAFFON" property="aporteCCAFFON" />
<html:hidden styleId="idCotizPendiente" property="idCotizPendiente" />
<html:hidden styleId="topeAFP" property="topeAFP" />
<html:hidden styleId="topeINP" property="topeINP" />
<html:hidden styleId="topeIndemn" property="topeIndemn" />
<html:hidden styleId="topeCesantia" property="topeCesantia" />
<html:hidden styleId="apEmpIndSegCes" property="apEmpIndSegCes" />
<html:hidden styleId="apEmpPFSegCes" property="apEmpPFSegCes" />
<html:hidden styleId="apTrabIndSegCes" property="apTrabIndSegCes" />
<html:hidden styleId="apTrabPFSegCes" property="apTrabPFSegCes" />
<html:hidden styleId="mostrar" property="mostrar" />
<html:hidden styleId="diasXMes" property="diasXMes" />
<html:hidden styleId="operacion" property="operacion" />
<html:hidden styleId="periodo" property="periodo" />

<!-- Depósito Convenido  -->
<html:hidden styleId="minTasaIndemn"	property="minTasaIndemn" />
<html:hidden styleId="maxTasaIndemn"	property="maxTasaIndemn" />

<input type="hidden" id="tipoProcesoActual" value="" />
<input type="hidden" id="segCesantiaEmpleadorTotal" value="3" />
<input type="hidden" id="segCesantiaEmpleador" value="2.4" />
<input type="hidden" id="segCesantiaTrabajador" value="0.6" />

<input type="hidden" value="" name="opExCaja" id="opExCaja" />
<input type="hidden" name="idCaja" id="idCaja" value="${CotizacionActionForm.caja.id}"/>

<html:hidden styleId="tipoPrevision" property="tipoPrevision" />
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
		         				<input type="hidden" id='nomproceso' name='nomproceso' value="${tp.descripcion}"/>
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
<input type="hidden" id='nomproceso' name='nomproceso' value="${tp.descripcion}"/>
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
							<logic:iterate id="tp" name="CotizacionActionForm" property="tiposProcesos">
		         			<logic:equal value="${CotizacionActionForm.tipoProceso}" name="tp" property="idTipoNomina">
		         				${tp.descripcion}
		         				<html:hidden styleId="tipoProceso" name="tp" property="idTipoNomina"/>
		         				<input type="hidden" id='nomproceso' name='nomproceso' value="${tp.descripcion}"/>
		         			</logic:equal>
		         		</logic:iterate>
							
			           	</td>
			      	</tr>
			      	<tr>
			      		<td colspan="3">&nbsp;</td>
			      		<td align="center">&nbsp;</td>
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
	        <logic:notPresent name="CotizacionActionForm" property="cotizacion">
	        	<div class="msgSuperiorTabla">
	  				<strong>No hay cotizaci&oacute;n para este trabajador, asociada a esta empresa, convenio y tipo de proceso</strong>
	  			</div>
	        </logic:notPresent>
	    </logic:notEqual>
	        <br />
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
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
			<tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:recalculaSalud();javascript:cambiaDiv('salud', this);" id="saludTd">ISAPRE</td>
              <td align="center" class="barra_tablas" onclick="javascript:recalculaAFP();javascript:cambiaDiv('prevision', this);" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:recalculaINP();javascript:cambiaDiv('inpTab', this);" id="inpTabTd">INP/FONASA</td>
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
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('deposito', this);" id="depositoTd" colspan="4">DEP&Oacute;SITO CONVENIDO</td>
				<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('indemnizacion', this);" id="indemnizacionTd" colspan="4">INDEMNIZACIONES</td>            
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
									${CotizacionActionForm.cotizante.rut}
				             		<html:hidden property="cotizante.rut" styleId="newRutTrabajador"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rut") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rut")%></td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	${CotizacionActionForm.cotizante.nombre}
				                	<html:hidden property="cotizante.nombre" styleId="newNombre"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombres") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombres")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				               		${CotizacionActionForm.cotizante.apellidoPat}
				               		<html:hidden property="cotizante.apellidoPat" styleId="apellidoPat"/>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				                	${CotizacionActionForm.cotizante.apellidoMat}
				                	<html:hidden property="cotizante.apellidoMat" styleId="apellidoMat"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidos") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidos")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">G&eacute;nero</td>
				               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				               		<c:forEach var="genero" items="${CotizacionActionForm.generos}">
				               			<c:choose>
				               				<c:when test="${genero.id eq CotizacionActionForm.cotizante.idGeneroReal}">
				               					<c:out value="${genero.nombre}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
				               		<html:hidden property="cotizante.idGeneroReal" styleId="idGenero"/>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("genero") == null ? "" : ((java.util.HashMap)mensajesErrores).get("genero")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	$ ${CotizacionActionForm.cotizacion.rentaImp} 
				                	<html:hidden property="cotizacion.rentaImp" styleId="rentaImp"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Dias Trabajados</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	${CotizacionActionForm.cotizante.numDiasTrabajados} 
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
				               					<c:out value="${sucursal.nombre}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
				               		<html:hidden property="cotizante.idSucursal" styleId="idSucursal"/>
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
			                   		<c:forEach var="entidadesSalud" items="${CotizacionActionForm.entidadesSalud}">
				               			<c:choose>
				               				<c:when test="${entidadesSalud.id eq CotizacionActionForm.cotizante.idEntSaludReal}">
				               					<c:out value="${entidadesSalud.nombre}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
			                      	<html:hidden property="cotizante.idEntSaludReal" styleId="idEntSaludReal"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadSalud") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadSalud")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.saludObligISAPRE}
		                   			<html:hidden property="cotizacion.saludObligISAPRE" styleId="saludObligISAPRE"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludOblig")%></td>
		                 	</tr>
		                 	<tr id="saludAdicionalTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Adicional</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.saludAdicional}
		                   			<html:hidden property="cotizacion.saludAdicional" styleId="saludAdicional"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludAdicional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludAdicional")%></td>
		                 	</tr>
		                 	<tr id="saludPactadaTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pactada</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalSaludDiv">$ 0</div>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludPactada") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludPactada")%></td>
		                 	</tr>
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
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisional") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisional")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.prevObligatorioAFP}
		                   			<html:hidden property="cotizacion.prevObligatorioAFP" styleId="prevObligatorioAFP"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
							<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.previsionAhorro} 
		                   			<html:hidden property="cotizacion.previsionAhorro" styleId="afpAhorro"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionAhorro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionAhorro")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor"><b>Total Cotizaci&oacute;n</b></td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="totalAfpDiv">$ 0</div>
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
		                   			$ ${CotizacionActionForm.cotizacion.segCesRemImp}
		                   			<html:hidden property="cotizacion.segCesRemImp" styleId="segCesRemImp"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.segCesTrab}
		                   			<html:hidden property="cotizacion.segCesTrab" styleId="segCesTrabajador"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		$ ${CotizacionActionForm.cotizacion.segCesEmpl}
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
			                   		<c:forEach var="tasaTrabPesado" items="${CotizacionActionForm.porcentajeTrabPesado}">
				               			<c:choose>
				               				<c:when test="${tasaTrabPesado.value eq CotizacionActionForm.cotizacion.tasaTrabPesado}">
				               					<c:out value="${tasaTrabPesado.label}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
			                   		<html:hidden property="cotizacion.tasaTrabPesado" styleId="idTasaTraPesa"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tasaTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tasaTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Nombre Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		${CotizacionActionForm.cotizacion.tipoTrabPesado}
			                   		<html:hidden property="cotizacion.tipoTrabPesado" styleId="nombreTrabPesado"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("idTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("idTrabPesado")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Trabajo Pesado</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		${CotizacionActionForm.cotizacion.trabPesado}
		                     		<html:hidden property="cotizacion.trabPesado" styleId="montoTrabPesado"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotTrabPesado") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotTrabPesado")%></td>
				        	</tr>
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
			                   	<td align="left" valign="middle" class="textos_formularios">
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
			                   			</logic:present>
			                   			<logic:notPresent name="CotizacionActionForm" property="codRegImp">
				                     		<html:select property="cotizante.idRegimenImp" styleClass="campos" styleId="idRegimenImp">
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
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">COTIZACIONES INP</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para INP</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="remImpPensionDiv">$ 0</div>
		                     		<html:hidden property="cotizacion.remImpPension" styleId="remImpPension"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponiblePension") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponiblePension")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Pensi&oacute;n</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.prevObligatorioINP}
		                     		<html:hidden property="cotizacion.prevObligatorioINP" styleId="prevObligatorioINP"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("pensionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("pensionOblig")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n de Salud FONASA</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.saludObligFONASA}
		                     		<html:hidden property="cotizacion.saludObligFONASA" styleId="saludObligFONASA"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fonasaOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fonasaOblig")%></td>
				        	</tr>
				           	<tr id="accTrabINPTR"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Accidentes del Trabajo</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.accTrabajoINP}
		                     		<html:hidden property="cotizacion.accTrabajoINP" styleId="accTrabajoINP"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp") == null ? "" : ((java.util.HashMap)mensajesErrores).get("accidenteTrabajoInp")%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Desahucio</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.inpDesahucio}
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
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
						        	</tr>
						         	<tr> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
					                   	<td align="left" valign="middle" class="textos_formularios">
					                   		${CotizacionActionForm.cotizante.numCargaSimpleINP} Simples <br />
				                     		<html:hidden property="cotizante.numCargaSimpleINP" styleId="cargasSimplesINP" />
				                     		${CotizacionActionForm.cotizante.numCargaMaternaINP} Maternales <br />
				                     		<html:hidden property="cotizante.numCargaMaternaINP" styleId="cargasMaternalesINP"/>
				                     		${CotizacionActionForm.cotizante.numCargaInvalidezINP} Invalidez<br />
				                    		<html:hidden property="cotizante.numCargaInvalidezINP" styleId="cargasInvalidezINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
						        	</tr>
						           <tr id="asigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ ${CotizacionActionForm.cotizacion.asigFamiliarINP}
				                     		<html:hidden property="cotizacion.asigFamiliarINP" styleId="asigFamiliarINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
						        	</tr>
						           <tr id="asigFamRetroTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ ${CotizacionActionForm.cotizacion.asigFamRetroINP}
				                     		<html:hidden property="cotizacion.asigFamRetroINP" styleId="asigFamRetroINP"/>
				                    	</td>
						               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
						        	</tr>
						           <tr id="reintegroAsigFamTr"> 
					                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
					                   	<td align="left" valign="middle" class="textos_formularios"> 
				                     		$ ${CotizacionActionForm.cotizacion.asigFamReintINP}
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
		                     		$ ${CotizacionActionForm.cotizacion.inpBonificacion}
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
		                     		<span style="padding-bottom: 3px;">
										<c:forEach var="entidadApvs" items="${CotizacionActionForm.entidadesApvs}">
					               			<c:choose>
					               				<c:when test="${entidadApvs.id eq CotizacionActionForm.cotizante.idEntidadAPVCReal}">
					               					<c:out value="${entidadApvs.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>		                     		
		                     			<html:hidden property="cotizante.idEntidadAPVCReal" styleId="idAPVC"/>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadApvc")%></td>
		                 	</tr>
		                	<tr>
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero Contrato</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			${CotizacionActionForm.cotizacion.apvcNumContr}
		                   			<html:hidden property="cotizacion.apvcNumContr" styleId="numContratoAPVC"/>
		                   		</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("codContratoApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("codContratoApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ ${CotizacionActionForm.cotizacion.apvcAporteTrab}
		                   			<html:hidden property="cotizacion.apvcAporteTrab" styleId="aporteTraAPVC"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabApvc") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabApvc")%></td>
		                 	</tr>
		                	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios"> 
		                   			$ ${CotizacionActionForm.cotizacion.apvcAporteEmpl}
		                   			<html:hidden property="cotizacion.apvcAporteEmpl" styleId="aporteEmpAPVC"/>
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
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">ENTIDADES Y MONTOS AHORRO PREVISIONAL VOLUNTARIO INDIVIDUAL</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
				        </table>
			     		<nested:iterate id="apv" name="CotizacionActionForm" property="apvs" indexId="countAPV" type="cl.araucana.cp.distribuidor.hibernate.beans.ApvVO">
			     			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="apvTable${countAPV}">
			                	<tr> 
			                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Entidad APV ${countAPV + 1}</td>
			                   		<td align="left" valign="middle" class="textos_formularios">
			                     		<span style="padding-bottom: 3px;">
			                    			<c:forEach var="entidadApvs" items="${CotizacionActionForm.entidadesApvs}">
			                    				<c:if test="${entidadApvs.id eq apv.idEntidadApv}">
			                    					<c:out value="${entidadApvs.nombre}"></c:out>&nbsp;			                    					
			                    				</c:if>
						               		</c:forEach>			                     		
			                     		</span>
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
							</table>
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
		                     		$ ${CotizacionActionForm.cotizacion.aporteCaja}
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
			                   		<logic:iterate id="trAsigFam" name="CotizacionActionForm" property="tramosAsigFam">
			                   			<html:hidden property="id" value="${trAsigFam.valorCarga}" styleId="${trAsigFam.id}-trAsigFam"/>
			                   		</logic:iterate>
			                   		<span style="padding-bottom: 3px;">			                   		
										<c:forEach var="tramosAsigFam" items="${CotizacionActionForm.tramosAsigFam}">
					               			<c:choose>
					               				<c:when test="${tramosAsigFam.id eq CotizacionActionForm.cotizante.idTramoReal}">
					               					<c:out value="${tramosAsigFam.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<html:text property="cotizante.numCargaSimple" disabled="true" styleId="cargasSimples" maxlength="2" size="5" styleClass="campos"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaterna" disabled="true" styleId="cargasMaternales" maxlength="2" size="5" styleClass="campos"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidez" disabled="true" styleId="cargasInvalidez" maxlength="2" size="5" styleClass="campos"/> Invalidez<br />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
				           <tr id="asigFamTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.asigFamiliar}
		                     		<html:hidden property="cotizacion.asigFamiliar" styleId="asigFam"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliar") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliar")%></td>
				        	</tr>
				           <tr id="asigFamRetroTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Asignaci&oacute;n Familiar Retroactiva (+)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.asigFamRetro}
		                     		<html:hidden property="cotizacion.asigFamRetro" styleId="asigFamRetro"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro") == null ? "" : ((java.util.HashMap)mensajesErrores).get("asigFamiliarRetro")%></td>
				        	</tr>
				           <tr id="reintegroAsigFamTr"> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Monto Reintegro Asignaci&oacute;n Familiar (-)</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.asigFamReint}
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
		                     		$ ${CotizacionActionForm.cotizacion.ccafCredito}
		                     		<html:hidden property="cotizacion.ccafCredito" styleId="creditoPersonal"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("creditoPersonal") == null ? "" : ((java.util.HashMap)mensajesErrores).get("creditoPersonal")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Leasing C.C.A.F.</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.ccafLeasing}
		                     		<html:hidden property="cotizacion.ccafLeasing" styleId="leasing"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("leasing") == null ? "" : ((java.util.HashMap)mensajesErrores).get("leasing")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Convenio Dental</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.ccafDental}
		                     		<html:hidden property="cotizacion.ccafDental" styleId="convenioDental"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("convenioDental") == null ? "" : ((java.util.HashMap)mensajesErrores).get("convenioDental")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Seguro de Vida</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.ccafSeguro}
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
		                   		<td align="left" valign="middle" class="textos_formularios">${CotizacionActionForm.tasaMutual}</td>
				               	<td align="left" class="textos_formularios">&nbsp;</td>
		                	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Renta Imponible Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.mutualImp}
		                     		<html:hidden property="cotizacion.mutualImp" styleId="rentaImpMutual"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImpMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImpMutual")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		$ ${CotizacionActionForm.cotizacion.accTrabajoMutual}
		                     		<html:hidden property="cotizacion.accTrabajoMutual" styleId="cotizacionMutual"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("cotizacionMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("cotizacionMutual")%></td>
				        	</tr>
		            	</table>
					</div>
<!-- MOVTO PERSO --><div id="otros" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">	
			        	<nested:iterate id="m" name="CotizacionActionForm" property="movtosPersonal" indexId="countMP" type="cl.araucana.cp.distribuidor.presentation.beans.MovtoPersonal">
			        	<c:if test="${m.idTipoMovReal ne -1}">
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
					               					<c:out value="${tiposMovPersonal.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("tiposMovPersonal"+countMP)%></td>
					        </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Inicio</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<nested:hidden property="inicio" styleId="inicioMP${countMP}Hidd" />
			                   		<c:out value="${m.inicio}"></c:out>&nbsp;
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP)%></td>
					        </tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Fin</td>
			                   	<td align="left" valign="middle" class="textos_formularios">
			                   		<nested:hidden property="termino" styleId="terminoMP${countMP}Hidd" />
			                   		<c:out value="${m.termino}"></c:out>&nbsp;
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
					               					<c:out value="${entidadSil.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>			                   		
			                     	</span>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutEntidadSil"+countMP)%></td>
					        	</tr>
				    	</table>
				    	</c:if>
				    	</nested:iterate>
				    	<br /><br />
				    	&nbsp;
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
    		<input type="button" value="Volver" onclick="javascript:enviar('Cancelar')" class="btn3" />
        </td>
    </tr>
    </logic:present>
</table>
</html:form>
</body>
<script language="javaScript"> 

function enviar(op)
{
	var idConv = document.getElementById('convenio').value;
	var rutEmp = document.getElementById('rutEmpresa').value;
	var tipoNom = document.getElementById('tipoProceso').value;
	var a = document.getElementById('tipoProceso');
	var tipo;
	if(a.selectedIndex == undefined)
		tipo=document.getElementById('nomproceso').value;
	else
		tipo=a.options[a.selectedIndex].text;

	frm = document.forms['CotizacionActionForm'];
	frm.action = 'NominasTrabajadoresVer.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom+'&tipo='+tipo;
	frm.submit();
}

var listaDivs = new Array('antecedentes', 'salud', 'prevision', 'inpTab', 'apv', 'caja', 'mutual', 'otros', 'deposito', 'indemnizacion');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";
var sinCaja = false, sinMutual = false;

if ('${CotizacionActionForm.nomMutual}' == '')
	sinMutual = true;
if ('${CotizacionActionForm.caja.nombre}' == '')
	sinCaja = true;

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

		document.getElementById("remImpPensionDiv").innerHTML = "$ " + document.getElementById("rentaImp").value;
		document.getElementById("remImpPension").value = document.getElementById("rentaImp").value;
		document.getElementById('totalAPVC').innerHTML = "$ " + (<c:if test="${empty CotizacionActionForm.cotizacion.apvcAporteTrab}">0</c:if>${CotizacionActionForm.cotizacion.apvcAporteTrab} + <c:if test="${empty CotizacionActionForm.cotizacion.apvcAporteEmpl}">0</c:if>${CotizacionActionForm.cotizacion.apvcAporteEmpl});
		if (document.getElementById('idRegimenImp').value != -1)
			document.getElementById('tasaCotINP').innerHTML = document.getElementById('codReg-' + document.getElementById('idRegimenImp').value).value + ' %';	

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

	if (saludObligISAPRE > 0) 
	{
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
{/*	idEntPensionReal
	prevObligatorioAFP
	afpAhorro
		totalAfpDiv
		totalAfp
	segCesRemImp
	segCesTrabajador
	segCesEmpresa
		totalAfcDiv
	idTasaTraPesa
	nombreTrabPesado
	montoTrabPesado
		totalPagarAfpDiv*/
	var totalAfpDiv = new Number(0);
	var totalAfcDiv = new Number(0);
	var prevObligatorioAFP = new Number("");
	var afpAhorro = new Number(limpiaNumero(document.getElementById('afpAhorro').value,''));
	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value,''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value,''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value,''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value,''));

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
/*	idEntExCaja
	idRegimenImp
		tasaCotINP
	remImpPension
	prevObligatorioINP
	saludObligFONASA
	accTrabajoINP
	inpDesahucio
		totalCotizINPDiv
	idTramoINP
	cargasSimplesINP
	cargasMaternalesINP
	cargasInvalidezINP
	asigFamiliarINP
	asigFamRetroINP
	reintegroAsigFamINP
		totalAsignFamDivINP
		totASigFamHiddINP
	inpBonificacion
		totalRebajasINPDiv
		totalPagarINPDiv
*/
	var totalCotizINPDiv = new Number(0);
	var totalAsignFamDivINP = new Number(0);
	var remImpPension = new Number(limpiaNumero(document.getElementById('remImpPension').value,''));
	var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value,''));
	var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value,''));
	var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value,''));
	var inpDesahucio = new Number(limpiaNumero(document.getElementById('inpDesahucio').value,''));
	var asigFamiliarINP = new Number(limpiaNumero(document.getElementById('asigFamiliarINP').value,''));
	var asigFamRetroINP = new Number(limpiaNumero(document.getElementById('asigFamRetroINP').value,''));
	var reintegroAsigFamINP = new Number(limpiaNumero(document.getElementById('reintegroAsigFamINP').value,''));
	var inpBonificacion = new Number(limpiaNumero(document.getElementById('inpBonificacion').value,''));

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
	document.getElementById("totalRebajasINPDiv").innerHTML = '$ ' + formatNumero(totalAsignFamDivINP - inpBonificacion);	
	document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(totalCotizINPDiv - totalAsignFamDivINP - inpBonificacion);
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
			AsignFamTmp += cargasMaternalesINP;

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

	if (document.getElementById("idTramo").value != "-1")
	{
		var valorTramo = document.getElementById(document.getElementById("idTramo").value + "-trAsigFam").value;
		if (cargasSimples > 0)
			AsignFamTmp += cargasSimples;

		if (cargasMaternales > 0)
			AsignFamTmp += cargasMaternales;

		if (cargasInvalidez > 0)
			AsignFamTmp += cargasInvalidez * 2;

		var numDiasTrabajados = new Number(limpiaNumero(document.getElementById('numDiasTrabajados').value, ''));
		var asigFam = valorTramo * AsignFamTmp;
		if (numDiasTrabajados < new Number(limpiaNumero(document.getElementById('diasTopeAF').value, '')))
			asigFam = Math.round((asigFam * numDiasTrabajados) / 30);

		document.getElementById('asigFam').value = asigFam;
	} else
		document.getElementById('asigFam').value = 0;
	recalculaCaja();
}

function recalculaCaja()
{/*
	aporteCaja
	idTramo
	cargasSimples
	cargasMaternales
	cargasInvalidez
	asigFam
	asigFamRetro
	reintegroAsigFam
		totalAsignFamDiv
	creditoPersonal
	leasing
	convenioDental
	seguro*/
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
// End --> 
</script></html>
