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
<script type="text/javascript" src="<c:url value="/js/contenidoCajas.js" />"></script>
<script >
	if(top==self)
		top.location.replace("<c:url value='/' />");
	</script>
</head>
<body>
<html:form action="/VerCotizacion">
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="trabajadores"/>
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="trabajadorEditar" />
<html:hidden styleId="rutEmpresa" property="rutEmpresa" />
<html:hidden styleId="rutTrabajador" property="rutTrabOrigin" />
<html:hidden styleId="convenio" property="idConvenio" />
<html:hidden styleId="idMutual" property="idMutual" />
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

<input type="hidden" id="tipoProcesoActual" value="" />

<input type="hidden" value="" name="opExCaja" id="opExCaja" />
<input type="hidden" name="idCaja" id="idCaja" value="${CotizacionActionForm.caja.id}"/>

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
		         				<input type="hidden" id='nomproceso' name="nomproceso" value="${tp.descripcion}"/>
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
			         				<input type="hidden" id='nomproceso' name="nomproceso" value="${tp.descripcion}"/>
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
		         				<input type="hidden" id='nomproceso' name="nomproceso" value="${tp.descripcion}"/>
		         			</logic:equal>
		         		</logic:iterate>
						
			           	</td>
			      	</tr>
			      	<tr>
			      		<td colspan="3">&nbsp;</td>
			      		<!-- <td align="center"><input type="submit" value="Buscar" class="btn4" onclick="javascript:enviar('${aplicar}')"/>
			      		</td>
			      		-->
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
		              <a href="#" onclick="javascript:enviar('Cancelar');" >${CotizacionActionForm.rutTrabFormat}&nbsp;</a>
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
		<html:hidden styleId="fechaIni" property="cotizacion.inicio" />
		<html:hidden styleId="fechaFin" property="cotizacion.termino" />
        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
        	<tr class="subtitulos_tablas" onmouseover="javascript:this.style.cursor='pointer';">
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('antecedentes', this);" id="antecedentesTd">ANTECEDENTES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('detallesNomina', this); javascript:verDetalle();" id="detallesNominaTd">DETALLES</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('salud', this); javascript:verSalud();" id="saludTd">ISAPRE</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('prevision', this);javascript:;recalculaAFP();" id="previsionTd">AFP</td>
              <td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('inpTab', this);" id="inpTabTd">INP/FONASA</td>
              <logic:notEmpty name="CotizacionActionForm" property="caja.nombre">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('caja', this);" id="cajaTd">CAJA</td>
              </logic:notEmpty>
              <logic:notEmpty name="CotizacionActionForm" property="nomMutual">
              		<td align="center" class="barra_tablas" onclick="javascript:cambiaDiv('mutual', this); javascript:veMutual();" id="mutualTd">MUTUAL</td>
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
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Sucursal</td>
				               	<td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios"> 
				               		<c:forEach var="sucursal" items="${CotizacionActionForm.sucursales}">
				               			<c:choose>
				               				<c:when test="${sucursal.idSucursal eq CotizacionActionForm.cotizante.idSucursal}">
				               					<c:out value="${sucursal.nombre}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("sucursales") == null ? "" : ((java.util.HashMap)mensajesErrores).get("sucursales")%></td>
				           </tr>
				    	</table>
	              	</div>
<!--DETALLES  --> 	<div id="detallesNomina" STYLE="position:relative; width:590; display:none; z-index:1; top:0px; left:0px; ">
				        <table width="100%" border="0" cellpadding="0" cellspacing="1">
				     	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DETALLES DE COTIZACION</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				           <tr> 
    							<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Monto de Reliquidaci&oacute;n</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	<div id="monto_Reli"> </div>
				                	<html:hidden property="cotizacion.reliquidacion" styleId="montoReli"/></td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("reliquidacion") == null ? "" : ((java.util.HashMap)mensajesErrores).get("reliquidacion")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Inicio</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
				                	 	<div id="fechaIniReli"></div>
			                   	</td>
			                   	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaIniReli") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaIniReli")%></td>
				           </tr>
				           <tr> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Fecha de Termino</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">
					                 <div id="fechaFinReli"></div>
					                
			                    </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("fechaFinReli") == null ? "" : ((java.util.HashMap)mensajesErrores).get("fechaFinReli")%></td>
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
			                    <td width="30%" align="left" valign="middle" class="textos_formcolor">Sistema Salud </td>
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
		                   			<div id="saludOblig_ISAPRE"></div>
		                   			<html:hidden property="cotizacion.saludObligISAPRE" styleId="saludObligISAPRE" />
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("saludOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("saludOblig")%></td>
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
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Isapre</td>
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
		                   			<div id="prevObligatorio_AFP"></div>
		                   			<html:hidden property="cotizacion.prevObligatorioAFP" styleId="prevObligatorioAFP" />
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
		                 	<logic:equal name="datosSis" value="true">
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
		                 	<logic:equal name="datosSis" value="false">
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
					               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionSIS") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionSIS")%></td>
			                 	</tr>
		                 	</logic:equal>
				        	<tr> 
				             	<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="barra_tablas">SEGURO DE CESANTIA</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				           	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Remuneraci&oacute;n Imponible para Seguro de Cesant&iacute;a</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="segCesRem_Imp"></div>
		                   			<html:hidden property="cotizacion.segCesRemImp" styleId="segCesRemImp" />
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponibleSegCes")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Trabajador</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			<div id="segCes_Trabajador"> </div> 
		                   			<html:hidden property="cotizacion.segCesTrab" styleId="segCesTrabajador"/>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("aporteTrabajador") == null ? "" : ((java.util.HashMap)mensajesErrores).get("aporteTrabajador")%></td>
		                 	</tr>
		                 	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Aporte Empresa</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
			                   		<div id="segCes_Empresa"></div>
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
		                     	<div id="montoTrab_Pesado"></div>
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
		                     		$ ${CotizacionActionForm.cotizacion.remImpPension}
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
		                     		<div id="saludOblig_FONASA"></div>
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
		                     		<html:text property="cotizante.numCargaSimpleINP" disabled="true" styleId="cargasSimplesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Simples <br />
		                     		<html:text property="cotizante.numCargaMaternaINP" disabled="true" styleId="cargasMaternalesINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Maternales <br />
		                    		<html:text property="cotizante.numCargaInvalidezINP" disabled="true" styleId="cargasInvalidezINP" maxlength="2" size="5" styleClass="campos" onblur="javascript:soloNumero(this, '');" onkeyup="javascript:soloNumero(this, '');"/> Invalidez<br />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("numCargas") == null ? "" : ((java.util.HashMap)mensajesErrores).get("numCargas")%></td>
				        	</tr>
				        </table>
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
		                     		$ ${CotizacionActionForm.cotizacion.aporteCaja}
		                     		<html:hidden property="cotizacion.aporteCaja" styleId="aporteCaja" />
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
				               		<c:forEach var="tramo" items="${CotizacionActionForm.tramosAsigFam}">
				               			<c:choose>
				               				<c:when test="${tramo.id eq CotizacionActionForm.cotizante.idTramoReal}">
				               					<c:out value="${tramo.nombre}"></c:out>
				               				</c:when>
				               			</c:choose>
				               		</c:forEach>
			                     	<html:hidden property="cotizante.idTramoReal" styleId="idTramo"/>
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("tramoAsigFam") == null ? "" : ((java.util.HashMap)mensajesErrores).get("tramoAsigFam")%></td>
				        	</tr>
				         	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">N&uacute;mero de Cargas</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		${CotizacionActionForm.cotizante.numCargaSimple} Simples <br />
		                     		${CotizacionActionForm.cotizante.numCargaMaterna} Maternales <br />
		                    		${CotizacionActionForm.cotizante.numCargaInvalidez} Invalidez<br />
		                    		
		                     		<html:hidden property="cotizante.numCargaSimple" styleId="cargasSimples" />
		                     		<html:hidden property="cotizante.numCargaMaterna" styleId="cargasMaternales" />
		                    		<html:hidden property="cotizante.numCargaInvalidez" styleId="cargasInvalidez" />		                    		
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
		                     		<div id="rentaImp_Mutual"></div>
		                     		<html:hidden property="cotizacion.mutualImp" styleId="rentaImpMutual" />
		                    	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImpMutual") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImpMutual")%></td>
				        	</tr>
				           <tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Mutual</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
		                     		<div id="cotizacion_Mutual"></div>
		                     		<html:hidden property="cotizacion.accTrabajoMutual" styleId="cotizacionMutual" />
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
    	<td height="33" valign="top"><br />
    		 
    		<input type="button" value="Volver"  onclick="javascript:enviar('Cancelar')" class="btn3" />
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
	var a=document.getElementById('tipoProceso');
	var tipo;
	if(undefined == a.selectedIndex){
		tipo=document.getElementById('nomproceso').value;
	}else{
		tipo=a.options[a.selectedIndex].text;
	}
	frm = document.forms['CotizacionActionForm'];
		frm.action = 'NominasTrabajadoresVer.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom+'&tipo='+tipo;
		frm.submit();

	 
}



var listaDivs = new Array('antecedentes', 'detallesNomina', 'salud', 'prevision', 'inpTab', 'caja', 'mutual');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";

var sinCaja = false, sinMutual = false;

if ('${CotizacionActionForm.nomMutual}' == '')
	sinMutual = true;
if ('${CotizacionActionForm.caja.nombre}' == '')
	sinCaja = true;

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{//esto se ejecuta solo al cargar la pagina, no al cambiar tab
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
	 
	document.getElementById("fechaIniReli").innerHTML = document.getElementById('fechaIni').value;
	document.getElementById("fechaFinReli").innerHTML = document.getElementById('fechaFin').value;

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
	if (document.getElementById("saludObligISAPRE").value == "")
		document.getElementById("saludObligISAPRE").value = 0;
	if (document.getElementById("saludObligFONASA").value == "")
		document.getElementById("saludObligFONASA").value = 0;
	if (document.getElementById("prevObligatorioAFP").value == "")
		document.getElementById("prevObligatorioAFP").value = 0;
if (document.getElementById("rentaImponibleSIS").value == "")
		document.getElementById("rentaImponibleSIS").value = 0;
if (document.getElementById("previsionSIS").value == "")
		document.getElementById("previsionSIS").value = 0;	
	if (document.getElementById("prevObligatorioINP").value == "")
		document.getElementById("prevObligatorioINP").value = 0;
	if (document.getElementById("montoReli").value == "")
		document.getElementById("montoReli").value = 0;
	if (document.getElementById("segCesRemImp").value == "")
		document.getElementById("segCesRemImp").value = document.getElementById("montoReli").value;
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
	if (document.getElementById("rentaImpMutual").value == "")
		document.getElementById("rentaImpMutual").value = document.getElementById("montoReli").value;
	if (document.getElementById("aporteCaja").value == "")
		document.getElementById("aporteCaja").value = 0;
	if (document.getElementById("remImpPension").value == "")
		document.getElementById("remImpPension").value = document.getElementById("montoReli").value;
	 
}

function recalculaAFP()
{
	
	var prevObligatorioAFP = new Number(limpiaNumero(document.getElementById('prevObligatorioAFP').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	var segCesEmpresa = new Number(limpiaNumero(document.getElementById('segCesEmpresa').value, ''));
	var montoTrabPesado = new Number(limpiaNumero(document.getElementById('montoTrabPesado').value, ''));


	var segCesRemImp = new Number(limpiaNumero(document.getElementById('segCesRemImp').value, ''));
	var segCesTrabajador = new Number(limpiaNumero(document.getElementById('segCesTrabajador').value, ''));
	

	var total = new Number(0);
	var totalAfcDiv = new Number(0);
	 
	if (prevObligatorioAFP > 0)
		total += prevObligatorioAFP;
	else
		document.getElementById('prevObligatorioAFP').value = 0;

	if (segCesTrabajador > 0)
		totalAfcDiv += segCesTrabajador;
	else
		document.getElementById('segCesTrabajador').value = 0;
	if (segCesEmpresa > 0)
		totalAfcDiv += segCesEmpresa;
	else
		document.getElementById('segCesEmpresa').value = 0;
	document.getElementById('totalAfcDiv').innerHTML = "$ " + formatNumero(totalAfcDiv);
	total += totalAfcDiv;

	if (montoTrabPesado > 0)
		total += montoTrabPesado;
	else
		document.getElementById('montoTrabPesado').value = 0;
	
	
	document.getElementById("segCes_Empresa").innerHTML = '$ ' + formatNumero(segCesEmpresa);
	document.getElementById("segCesRem_Imp").innerHTML = '$ ' + formatNumero(segCesRemImp);
	document.getElementById("segCes_Trabajador").innerHTML = '$ ' + formatNumero(segCesTrabajador);
	document.getElementById("montoTrab_Pesado").innerHTML = '$ ' + formatNumero(montoTrabPesado);
	
	document.getElementById("prevObligatorio_AFP").innerHTML ='$ ' + formatNumero(prevObligatorioAFP);
	document.getElementById("totalPagarAfpDiv").innerHTML ='$ ' + formatNumero(total);
}


function recalculaINP()
{
	var total = new Number(0);

	var prevObligatorioINP = new Number(limpiaNumero(document.getElementById('prevObligatorioINP').value, ''));
	var saludObligFONASA = new Number(limpiaNumero(document.getElementById('saludObligFONASA').value, ''));

	 


	if (prevObligatorioINP > 0)
		total += prevObligatorioINP;
	else
		document.getElementById('prevObligatorioAFP').value = 0;
	if (saludObligFONASA > 0)
		total += saludObligFONASA;
	else
		document.getElementById('saludObligFONASA').value = 0;
	if (sinMutual)
	{
		var accTrabajoINP = new Number(limpiaNumero(document.getElementById('accTrabajoINP').value, ''));
		if (accTrabajoINP > 0)
			total += accTrabajoINP;
		else
			document.getElementById('accTrabajoINP').value = 0;
	}
	//if (sinCaja)
	//{
	//}
	
	document.getElementById("saludOblig_FONASA").innerHTML = '$ ' + formatNumero(saludObligFONASA);
	
	document.getElementById("totalCotizINPDiv").innerHTML = '$ ' + formatNumero(total);
	document.getElementById("totalPagarINPDiv").innerHTML = '$ ' + formatNumero(total);
}
function verDetalle(){
var montoReli = new Number(limpiaNumero(document.getElementById('montoReli').value, ''));
	document.getElementById("monto_Reli").innerHTML ='$ ' + formatNumero(montoReli);

}
function verSalud(){
var saludObligISAPRE = new Number(limpiaNumero(document.getElementById('saludObligISAPRE').value, ''));
	document.getElementById("saludOblig_ISAPRE").innerHTML ='$ ' + formatNumero(saludObligISAPRE);

}
function veMutual(){
var rentaImpMutual = new Number(limpiaNumero(document.getElementById('rentaImpMutual').value, ''));
	document.getElementById("rentaImp_Mutual").innerHTML ='$ ' + formatNumero(rentaImpMutual);
var cotizacionMutual = new Number(limpiaNumero(document.getElementById('cotizacionMutual').value, ''));
	document.getElementById("cotizacion_Mutual").innerHTML ='$ ' + formatNumero(cotizacionMutual);

}


// End --> 
</script></html>
