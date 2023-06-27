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
</head>
<body>
<html:form action="/VerCotizacion">
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
		              <td align="left" nowrap="nowrap" class="textos_formularios">
							${CotizacionActionForm.cotizante.rut}&nbsp;
		              </td>              	
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
		              <td align="left" nowrap="nowrap" class="textos_formularios">
		           <a href="#" onclick="javascript:enviar('Cancelar');" >${CotizacionActionForm.rutTrabFormat}&nbsp;</a>
		              </td>              	
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvNombreDpndiente}&nbsp;</td>
		              <td align="left" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvAplldioPatDpndiente} ${CotizacionActionForm.cotizacion.afpvAplldioMatDpndiente}&nbsp;</td>
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
<!-- 		               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)erroresCD).get(new Integer(103))==null ? "" : ((java.util.HashMap)erroresCD).get(new Integer(103))%><%=((java.util.HashMap)erroresCD).get(new Integer(233)) == null ? "" : ((java.util.HashMap)erroresCD).get(new Integer(233))%><%=((java.util.HashMap)erroresCD).get(new Integer(323)) == null ? "" : ((java.util.HashMap)erroresCD).get(new Integer(323))%></td>-->
		
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
				           <tr id="rentaTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Renta Imponible</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">$ ${CotizacionActionForm.cotizacion.rentaImp}
				                	<html:hidden property="cotizacion.rentaImp" styleId="rentaImp"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rentaImponible") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rentaImponible")%></td>
				           </tr>
				           <tr id="diasTrabajadosTr"> 
								<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Dias Trabajados</td>
				                <td height="14" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">${CotizacionActionForm.cotizante.numDiasTrabajados}
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
				               		</c:forEach>				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("sucursales") == null ? "" : ((java.util.HashMap)mensajesErrores).get("sucursales")%></td>
				           </tr>
				        	<tr> 				        	
				           		<td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas">DATOS PERSONALES TRABAJADOR DEPENDIENTE</td>
				           		<td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="barra_tablas" width="20%">Errores</td>
				            </tr>
				            <tr> 
				             	<td width="30%" height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">RUT</td>
				                <td height="22" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvRutDpndiente}
					                <html:hidden property="cotizacion.afpvRutDpndiente" styleId="newRutTrabajadorDep"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("rutVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("rutVoluntario")%></td>
							</tr>
				           <tr> 
				           		<td width="30%" align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formcolor">Nombres</td>
				                <td align="left" valign="middle" bordercolor="#CCCCCC" bgcolor="#FFFFFF" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvNombreDpndiente}
					                <html:hidden property="cotizacion.afpvNombreDpndiente" styleId="newNombreDep"/>
				                </td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("nombresVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("nombresVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Paterno</td>
				               	<td align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">${CotizacionActionForm.cotizacion.afpvAplldioPatDpndiente}
				               		<html:hidden property="cotizacion.afpvAplldioPatDpndiente" styleId="apellidoPatDep"/>
				               	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("apellidosVoluntario") == null ? "" : ((java.util.HashMap)mensajesErrores).get("apellidosVoluntario")%></td>
				           </tr>
				           <tr> 
				           		<td width="30%" height="22" align="left" valign="middle" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formcolor">Apellido Materno</td>
				                <td height="22" align="left" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="textos_formularios">
				                	${CotizacionActionForm.cotizacion.afpvAplldioMatDpndiente}
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
			                   			<c:forEach var="entidadesPension" items="${CotizacionActionForm.entidadesPension}">
					               			<c:choose>
					               				<c:when test="${entidadesPension.id eq CotizacionActionForm.cotizante.idEntidadAFPVReal}">
					               					<c:out value="${entidadesPension.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>
					               		<html:hidden property="cotizante.idEntidadAFPVReal" styleId="idEntPensionCotVol"/>
		                     		</span>
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol") == null ? "" : ((java.util.HashMap)mensajesErrores).get("entidadPrevisionalVol")%></td>
		                 	</tr>
				           	<tr> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Obligatoria</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.prevObligatorioAFP}
		                   			<html:hidden property="cotizacion.prevObligatorioAFP" styleId="prevObligatorioAFPVol" />
		                     	</td>
				               	<td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("previsionOblig") == null ? "" : ((java.util.HashMap)mensajesErrores).get("previsionOblig")%></td>
		                 	</tr>
		                 	<tr id="afpAhorroTr"> 
		                   		<td width="30%" align="left" valign="middle" class="textos_formcolor">Cotizaci&oacute;n Ahorro</td>
		                   		<td align="left" valign="middle" class="textos_formularios">
		                   			$ ${CotizacionActionForm.cotizacion.previsionAhorro}
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
										<c:forEach var="entidadPension" items="${CotizacionActionForm.entidadesPension}">
					               			<c:choose>
					               				<c:when test="${entidadPension.id eq CotizacionActionForm.cotizante.idEntPensionReal}">
					               					<c:out value="${entidadPension.nombre}"></c:out>
					               				</c:when>
					               			</c:choose>
					               		</c:forEach>		                     					                     		
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
			                   		<c:out value="${m.inicio}"></c:out>
			                   	
		                    	</td>
				               <td align="left" class="textos_formularios">&nbsp;<%=((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP) == null ? "" : ((java.util.HashMap)mensajesErrores).get("inicioMovPersonal"+countMP)%></td>
				        	</tr>
				           	<tr> 
			                   	<td width="30%" align="left" valign="middle" class="textos_formcolor">Fecha de Fin</td>
			                   	<td align="left" valign="middle" class="textos_formularios"> 
			                   		<nested:hidden property="termino" styleId="terminoMP${countMP}Hidd" />
			                   		<c:out value="${m.termino}"></c:out>
									
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
	if(a.selectedIndex == undefined){
		tipo=document.getElementById('nomproceso').value;
	}else{
		tipo=a.options[a.selectedIndex].text;
	}
	frm = document.forms['CotizacionActionForm'];
	frm.action = 'NominasTrabajadoresVer.do?idConv=' + idConv + '&rutEmp=' + rutEmp + '&tipoNom=' + tipoNom+'&tipo='+tipo;
	frm.submit();	
}
 
var listaDivs = new Array('antecedentes', 'prevision', 'otros');
var tabDefault = "${tabActual}";
var tabActual = "${tabActual}";

if ('${CotizacionActionForm.cotizacion}' != '')//hay cotizante
{
	if (document.getElementById(tabDefault) != null)
		cambiaDiv(tabDefault);
	recalculaAFP();
}

function showMP()
{
	for (i = 0; i < 4; i++)
		if (document.getElementById("idTipoMovReal" + i).value == '-1')
		{
			document.getElementById("mpTable" + i).style.display = 'block';
			break;
		}
}

function hideMP()
{
	for (i = 3; i >= 1; i--)
		if (document.getElementById("mpTable" + i).style.display == 'block')
		{
			document.getElementById("idTipoMovReal" + i).value = -1;
			document.getElementById("mpTable" + i).style.display = 'none';
			break;
		}
}

function recalculaAFP()
{/*	prevObligatorioAFPVol
	afpAhorroVol
	totalAfp
	totalAfpVolDiv*/
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
// End --> 
</script>
</html>