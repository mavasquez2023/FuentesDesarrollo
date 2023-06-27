<%@ include file="/html/comun/taglibs.jsp" %>
<script type="text/javascript" src="<c:url value="/js/validaciones/validacionesComun.js" />"></script>
<html:form action="/TrabCotizantesAll" styleId="formulario" onsubmit="return onFormSubmit(this)">
<html:hidden styleId="accion"       property="accion"       name="accion"       value="nominas" />
<html:hidden styleId="subAccion"    property="subAccion"    name="subAccion"    value="trabajadores" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="trabListaAll" />
<bean:define id="bloqueoEdicionNom"><%=request.getAttribute("bloqueoEdicionNom")%></bean:define>
<html:hidden property="rutOculto"       styleId="rutOculto"/>
<html:hidden property="nombreOculto"    styleId="nombreOculto"/>
<html:hidden property="apellidosOculto" styleId="apellidosOculto"/>

<bean:define id="procesoOculto"><%=request.getAttribute("procesoOculto")%></bean:define>
<bean:define id="convenioOculto"><%=request.getAttribute("convenioOculto")%></bean:define>
<c:set var="FLG_Busqueda" scope="page"><%=request.getAttribute("FLG_Busqueda")%></c:set>
<html:hidden property="FLG_Paginacion" styleId="FLG_Paginacion" value="0" />

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr valign="bottom">
    	<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
			<strong>B&uacute;squeda de Trabajador en N&oacute;minas</strong>
		</td>
	</tr>
  	<tr>
     	<td valign="top">
	       	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
	         	<tr>
	           		<td><strong>RUT:</strong></td>
	           		<td><html:text property="rut" styleId="rut" maxlength="12" size="25" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
	           		<td><strong>Nombre:</strong></td>
	           		<td><html:text property="nombre" styleId="nombre" maxlength="30" size="45" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
	         	</tr>
	         	<tr>
	           		<td><strong>Apellidos:</strong></td>
	           		<td colspan="3"><html:text property="apellidos" styleId="apellidos" maxlength="40" size="40" styleClass="campos" onblur="javascript:soloNomTrab(this);" onkeyup="javascript:soloNomTrab(this);"/></td>
	         	</tr>
	         	<tr>
	           		<td colspan="4" align="right"><html:submit property="operacion" styleClass="btn3" value="${buscar}"/></td>
	         	</tr>
	         	<tr>
	           		<td colspan="4">&nbsp;</td>
	         	</tr>
	       	</table>
		</td>
	</tr>
	<tr>
		<td height="4" bgcolor="#85b4be"></td>
	</tr>
</table>

<br>
<br>

<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
				<tr>
					<td colspan="4" class="titulos_formularios">
						<a href="javascript:;" onclick="swapAll('filtros', 'img');">
							<img id="img" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" alt="Expandir" title="Expandir" />
							B&uacute;squeda Avanzada
						</a>
					</td>
				</tr>
				<tr id="filtros" style="display:none">
					<td colspan="4">
						<table border="0" cellpadding="0" cellspacing="0" width="100%" class="titulos_formularios">
							<tr>
								<td><strong>RUT Independiente:</strong></td>
				   				<td><html:text property="rutEmpresa" styleId="rutEmpresa" maxlength="12" size="25" styleClass="campos" onblur="javascript:soloRut(this);" onkeyup="javascript:soloRut(this);"/></td>
				    			<td><strong>Raz&oacute;n Social: </strong></td>
				    			<td><html:text property="razonSocial" styleId="razonSocial" styleClass="campos"/></td>
							</tr>
							<tr>
						    	<td><strong>Grupo de Convenios:</strong></td>
							    <td>
									<html:select property="opcGrupoConvenio" styleClass="campos">
										<option value="0">...</option>
										<html:optionsCollection property="gruposConvenio"/>
									</html:select>
								</td>
								<td><strong>Tipo Proceso:</strong></td>
								<td>
							    	<html:select property="tipoProceso" styleId="tipoProceso" size="1" styleClass="campos">
										<option value="0">...</option>
								       	<logic:iterate id="tipoNomina" name="tiposDeNominas">
								        	<bean:define id="nomina" name="tipoNomina" type="cl.araucana.cp.distribuidor.hibernate.beans.TipoNominaVO"/>
							       			<option value="<bean:write name='nomina' property='idTipoNomina' />">
						    	   				<bean:write name="nomina" property="descripcion"/>
							    			</option>
			        					</logic:iterate>
									</html:select>
								</td>
							</tr>
							<%--tr>
						    	<td colspan="4" align="right">
						    		<html:submit property="operacion" styleClass="btn3" value="${buscar}"/>
						    	</td>
							</tr--%>
						</table>
					</td>
				</tr>
			   	<tr> 
	           		<td height="4" colspan="4" bgcolor="#85b4be"></td>
	         	</tr>
			</table>
			<br />
	    
		    <html:errors/>
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>

		   	<%--<logic_present name="msg"><div class="titulo" style="text-align: center; font-weight: bold;">${msg}</div></logic_present>--%>
			<logic:equal name="TrabListarAllActionForm" property="mostrarLista" value="SI">
		   		<logic:present name="TrabListarAllActionForm" property="trabajadores">
		       	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		         	<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		           		<td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF">&nbsp;</td>
		         	</tr>
		         	<tr align="center"> 
			           	<td height="33" valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
			            	<tr align="center"> 
			                	<td height="33" align="left" valign="top">
			                    <table width="100%" border="0" cellpadding="0" cellspacing="1">
			                    	<tr valign="bottom"> 
			                        	<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Selecci&oacute;n Trabajador</strong></td>
			                        </tr>
			                    </table>
			                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		                       		<tr class="subtitulos_tablas">
		                         		<td width="12%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT</td>
		                         		<td width="27%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
					                 	<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" colspan="2">RUT Independiente</td>
					                 	<td width="26%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
					                 	<td width="20%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
		                       		</tr>
									<c:set var="estilo" value="textos-formcolor2"/>
									<c:set var="count" value="0"/>
									<logic:notEmpty name="TrabListarAllActionForm" property="pendientes">
			                       		<logic:iterate id="trabajador" name="TrabListarAllActionForm" property="pendientes">
			                       			<c:set var="countEmp" value="1"/>
				                         	<logic:iterate id="empresa" name="trabajador" property="empresas">
			                       				<c:choose>
									   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
									   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
									   		  	</c:choose>
									   		  	<c:set var="count" value="${count + 1}"/>
								   		  		<bean:size id="numConvenios" name="empresa" property="convenios"/>
					                       		<tr>
						                       		<c:choose>
										   		    	<c:when test="${countEmp == 1}">
							                         		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">&nbsp;<div align="right">${trabajador.rut}</div></td>
							                         		<td class="${estilo}">${trabajador.nombre}</td>
										   		    	</c:when>
										   				<c:otherwise>
					                         				<td colspan="2" class="${estilo}">&nbsp;</td>
										   				</c:otherwise>
										   		  	</c:choose>
									   		  		<c:set var="countEmp" value="${countEmp + 1}"/>
					                         		<c:choose>
										   		    	<c:when test="${numConvenios == 1}">
										   		    		<td class="${estilo}">&nbsp;</td>
										   		    	</c:when>
										   		    	<c:otherwise>
										   		    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}">
										   		    			<a href="#tr-${trabajador.idCotizante}-${empresa.idEmpresa}"
										   		    				onclick="swapAll('tr-${trabajador.idCotizante}-${empresa.idEmpresa}', 'img${trabajador.idCotizante}-${empresa.idEmpresa}');">
										   		    				<img id="img${trabajador.idCotizante}-${empresa.idEmpresa}" src="<c:url value="/img/ico_mas.gif" />"
										   		    					width="11" height="11" border="0" />
										   		    			</a>
										   		    		</td>
										   		    	</c:otherwise>
										   		 	</c:choose>
										   		 	<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
					                         		<td class="${estilo}">${empresa.razonSocial}</td>
					                         		<c:choose>
										   		    	<c:when test="${numConvenios == 1}">
										   		    		<c:if test="${bloqueoEdicionNom == 0}" >
											   		    		<td class="${estilo}">
											   		    			<table cellpadding="0" cellspacing="0" border="0" width="100%">
					                         							<tr>							                         									
													   		    			<logic:iterate id="convenio" name="empresa" property="convenios">
											                       				<logic:iterate id="item" name="convenio">
											                       					<logic:empty name="item" property="label">
												                         				<td class="textos-tabla" width="25">
												                         					${item.value}
												                         				</td>
												                         			</logic:empty>
												                         			<logic:notEmpty name="item" property="label">
												                         				<logic:equal name="item" property="label" value="flagEA">
																							<c:choose>
																								<c:when test="${ item.value==0 }"><td><div class="mensaje_error">Error</div></td></c:when>
																								<c:when test="${ item.value==1 }"><td><div class="mensaje_aviso">Aviso</div></td></c:when>
																								<c:otherwise><td>&nbsp;</td></c:otherwise>
																							</c:choose>
												                         				</logic:equal>
												                         				<logic:notEqual name="item" property="label" value="flagEA">
													                         				<c:if test="${bloqueoEdicionNom == 0}" >
												                         						<td><html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link></td>
													                         				</c:if>
										                         							<c:if test="${bloqueoEdicionNom == 1}" >
										                         								<td><html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link></td>
									                         								</c:if>
												                         				</logic:notEqual>
								                         							</logic:notEmpty>
												                         		</logic:iterate>
												                         	</logic:iterate>
					                         							</tr>
					                         						</table>									                         			
											   		    		</td>
										   		    		</c:if>
										   		    	</c:when>
										   		    	<c:otherwise>
										   		    		<td class="${estilo}">&nbsp;</td>
										   		    	</c:otherwise>
										   		 	</c:choose>
						                         </tr>
						                         <c:choose>
									   		    	<c:when test="${numConvenios > 1}">
							                         	<tr id="tr-${trabajador.idCotizante}-${empresa.idEmpresa}">
							                         		<td colspan="6">
								                         		<table width="100%" cellpadding="0" cellspacing="0" border="0">
									                         		<logic:iterate id="convenio" name="empresa" property="convenios">
											                         	<c:choose>
															   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
															   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
															   		  	</c:choose>
															   		  	<c:set var="count" value="${count + 1}"/>
									                         			<tr>
									                         				<td class="${estilo}">&nbsp;</td>
									                         				<td width="19%" class="${estilo}">
												                         		<table cellpadding="0" cellspacing="0" border="0" width="100%">
								                         							<tr>
							                         									<logic:iterate id="item" name="convenio">
													                       					<logic:empty name="item" property="label">
														                         				<td class="textos-tabla" width="25">
														                         					${item.value}
														                         				</td>
														                         			</logic:empty>
														                         			<logic:notEmpty name="item" property="label">
														                         				<logic:equal name="item" property="label" value="flagEA">
																									<c:choose>
																										<c:when test="${ item.value==0 }"><td><div class="mensaje_error">Error</div></td></c:when>
																										<c:when test="${ item.value==1 }"><td><div class="mensaje_aviso">Aviso</div></td></c:when>
																										<c:otherwise><td>&nbsp;</td></c:otherwise>
																									</c:choose>
														                         				</logic:equal>
														                         				<logic:notEqual name="item" property="label" value="flagEA">
															                         				<c:if test="${bloqueoEdicionNom == 0}" >
													                         							<td><html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link></td>
															                         				</c:if>
												                         							<c:if test="${bloqueoEdicionNom == 1}" >
												                         								<td><html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link></td>
											                         								</c:if>
														                         				</logic:notEqual>
										                         							</logic:notEmpty>
														                         		</logic:iterate>
															                         </tr>
															                	</table>
																			</td>
									                         			</tr>
									                         		</logic:iterate>
								                         		</table>
							                         		</td>
							                         	</tr>				                         	
														<script language="javaScript">
															document.getElementById("tr-${trabajador.idCotizante}-${empresa.idEmpresa}").style.display='none';
														</script>
									   		    	</c:when>
										   		</c:choose>
			                         		</logic:iterate>
			                       		</logic:iterate>
		                       		</logic:notEmpty>
		                       		<logic:notEmpty name="TrabListarAllActionForm" property="avisos">
			                       		<logic:iterate id="trabajador" name="TrabListarAllActionForm" property="avisos">
									   		<c:set var="countEmp" value="1"/>
				                         	<logic:iterate id="empresa" name="trabajador" property="empresas">
					                         	<c:choose>
									   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
									   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
									   		  	</c:choose>
									   		  	<c:set var="count" value="${count + 1}"/>
								   		  		<bean:size id="numConvenios" name="empresa" property="convenios"/>
					                       		<tr>
						                       		<c:choose>
										   		    	<c:when test="${countEmp == 1}">
							                         		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">${trabajador.rut}&nbsp;</td>
							                         		<td class="${estilo}">${trabajador.nombre}</td>
										   		    	</c:when>
										   				<c:otherwise>
					                         				<td colspan="2" class="${estilo}">&nbsp;</td>
										   				</c:otherwise>
										   		  	</c:choose>
									   		  		<c:set var="countEmp" value="${countEmp + 1}"/>
					                         		<c:choose>
										   		    	<c:when test="${numConvenios == 1}">
										   		    		<td class="${estilo}">&nbsp;</td>
										   		    	</c:when>
										   		    	<c:otherwise>
										   		    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}"><a href="#tr-${trabajador.idCotizante}-${empresa.idEmpresa}av" onclick="swapAll('tr-${trabajador.idCotizante}-${empresa.idEmpresa}av', 'img${trabajador.idCotizante}-${empresa.idEmpresa}av');"><img id="img${trabajador.idCotizante}-${empresa.idEmpresa}av" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" title="Ver todas las cotizaciones del Trabajador" /></a></td>
										   		    	</c:otherwise>
										   		 	</c:choose>
										   		 	<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
					                         		<td class="${estilo}">${empresa.razonSocial}</td>
						                         		<c:choose>
											   		    	<c:when test="${numConvenios == 1}">
											   		    		<c:if test="${bloqueoEdicionNom == 0}" >
												   		    		<td class="${estilo}" width="19%">
												   		    			<logic:iterate id="convenio" name="empresa" property="convenios">
												   		    				<table cellpadding="0" cellspacing="0" border="0">
								                         						<tr>
													                         		<logic:iterate id="item" name="convenio">
												                         				<td class="textos-tabla" width="25">
											                         						<logic:empty name="item" property="label">${item.value}</logic:empty>
											                         					</td>
											                         					<td align="center">
											                         						<logic:empty name="item" property="label"><div class="mensaje_aviso">Aviso</div></logic:empty>
											                         					</td>
											                         					<td align="center">
											                         						<logic:notEmpty name="item" property="label">									                         				
											                         						<html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">
											                         							${item.value}
												                         					</html:link>
												                         					</logic:notEmpty>
												                         				</td>
													                         		</logic:iterate>
													                       		</tr>
												                         	</table>
											                         	</logic:iterate>
												   		    		</td>										   		    		
											   		    		</c:if>
											   		    		<c:if test="${bloqueoEdicionNom == 1}" >
											   		    			<td class="${estilo}">
												   		    			<logic:iterate id="convenio" name="empresa" property="convenios">
											                         		<logic:iterate id="item" name="convenio">
											                         			<logic:empty name="item" property="label">${item.value}</logic:empty>
											                         			<logic:notEmpty name="item" property="label">
											                         				<html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link>
											                         			</logic:notEmpty>
											                         			&nbsp;
											                         		</logic:iterate>
											                         	</logic:iterate>
												   		    		</td>
											   		    		</c:if>
											   		    	</c:when>
											   		    	<c:otherwise>
											   		    		<td class="${estilo}">&nbsp;</td>
											   		    	</c:otherwise>
											   		 	</c:choose>
					                         	</tr>
					                         	<c:choose>
									   		    	<c:when test="${numConvenios > 1}">
							                         	<tr id="tr-${trabajador.idCotizante}-${empresa.idEmpresa}av">
							                         		<td colspan="6">
							                         		<table cellpadding="0" cellspacing="0" border="0" width="100%">
							                         		<logic:iterate id="convenio" name="empresa" property="convenios">
									                         	<c:choose>
													   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
													   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
													   		  	</c:choose>
													   		  	<c:set var="count" value="${count + 1}"/>
							                         			<tr>
							                         				<td class="${estilo}">&nbsp;</td>
							                         				<td width="19%" class="${estilo}" valign="middle">
							                         					<table width="100%" cellpadding="0" cellspacing="0" border="0">
							                         						<tr>
							                         							<logic:iterate id="item" name="convenio">
							                         							<td class="textos-tabla" width="25">
												                         			<logic:empty name="item" property="label">${item.value}</logic:empty>
												                         		</td>
												                         		<td align="center">
													                         		<logic:empty name="item" property="label"><div class="mensaje_aviso">Aviso</div></logic:empty>
													                         	</td>
													                         	<td align="center">
												                         			<logic:notEmpty name="item" property="label">
												                         				<c:if test="${bloqueoEdicionNom == 0}" >
											                       						<html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">
									                         								${item.value}
									                         							</html:link>&nbsp;
											                         					</c:if>
										    	                     					<c:if test="${bloqueoEdicionNom == 1}" >
										        	                	 					<html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link>
										            	        	     				</c:if>
										                		         			</logic:notEmpty>
										                		         		</td>
									                         				    </logic:iterate>
										                         			</tr>
										                         		</table>
																	</td>
							                         			</tr>
							                         		</logic:iterate>
							                         		</table>
							                         		</td>
							                         	</tr>				                         	
														<script language="javaScript">
															document.getElementById("tr-${trabajador.idCotizante}-${empresa.idEmpresa}av").style.display='none';
														</script>
									   		    	</c:when>
										   		</c:choose>
			                         		</logic:iterate>
			                       		</logic:iterate>
		                       		</logic:notEmpty>
		                       		<logic:notEmpty name="TrabListarAllActionForm" property="trabajadores">
			                       		<logic:iterate id="trabajador" name="TrabListarAllActionForm" property="trabajadores">
									   		<c:set var="countEmp" value="1"/>
				                         	<logic:iterate id="empresa" name="trabajador" property="empresas">
					                         	<c:choose>
									   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
									   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
									   		  	</c:choose>
									   		  	<c:set var="count" value="${count + 1}"/>
								   		  		<bean:size id="numConvenios" name="empresa" property="convenios"/>
					                       		<tr>
						                       		<c:choose>
										   		    	<c:when test="${countEmp == 1}">
							                         		<td align="right" valign="middle" nowrap="nowrap" class="${estilo}">${trabajador.rut}&nbsp;</td>
							                         		<td class="${estilo}">${trabajador.nombre}</td>
										   		    	</c:when>
										   				<c:otherwise>
					                         				<td colspan="2" class="${estilo}">&nbsp;</td>
										   				</c:otherwise>
										   		  	</c:choose>
									   		  		<c:set var="countEmp" value="${countEmp + 1}"/>
					                         		<c:choose>
										   		    	<c:when test="${numConvenios == 1}">
										   		    		<td class="${estilo}">&nbsp;</td>
										   		    	</c:when>
										   		    	<c:otherwise>
										   		    		<td align="center" valign="middle" nowrap="nowrap" class="${estilo}"><a href="#tr-${trabajador.idCotizante}-${empresa.idEmpresa}" onclick="swapAll('tr-${trabajador.idCotizante}-${empresa.idEmpresa}', 'img${trabajador.idCotizante}-${empresa.idEmpresa}');"><img id="img${trabajador.idCotizante}-${empresa.idEmpresa}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" title="Ver todas las cotizaciones del Trabajador" /></a></td>
										   		    	</c:otherwise>
										   		 	</c:choose>
										   		 	<td align="left" valign="middle" nowrap="nowrap" class="${estilo}">${empresa.rut}</td>
					                         		<td class="${estilo}">${empresa.razonSocial}</td>
						                         		<c:choose>
											   		    	<c:when test="${numConvenios == 1}">
											   		    		<c:if test="${bloqueoEdicionNom == 0}" >
												   		    		<td class="${estilo}">
												   		    			<logic:iterate id="convenio" name="empresa" property="convenios">
											                         		<logic:iterate id="item" name="convenio">
											                         			<logic:empty name="item" property="label">${item.value}</logic:empty>
											                         			<logic:notEmpty name="item" property="label">									                         				
											                         				<html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">
											                         					${item.value}
											                         				</html:link>
											                         			</logic:notEmpty>
											                         			&nbsp;
											                         		</logic:iterate>
											                         	</logic:iterate>
												   		    		</td>
											   		    		</c:if>
											   		    		<c:if test="${bloqueoEdicionNom == 1}" >
											   		    			<td class="${estilo}">
												   		    			<logic:iterate id="convenio" name="empresa" property="convenios">
											                         		<logic:iterate id="item" name="convenio">
											                         			<logic:empty name="item" property="label">${item.value}</logic:empty>
											                         			<logic:notEmpty name="item" property="label">
											                         				<html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link>
											                         			</logic:notEmpty>
											                         			&nbsp;
											                         		</logic:iterate>
											                         	</logic:iterate>
												   		    		</td>
											   		    		</c:if>
											   		    	</c:when>
											   		    	<c:otherwise>
											   		    		<td class="${estilo}">&nbsp;</td>
											   		    	</c:otherwise>
											   		 	</c:choose>
					                         	</tr>
					                         	<c:choose>
									   		    	<c:when test="${numConvenios > 1}">
							                         	<tr id="tr-${trabajador.idCotizante}-${empresa.idEmpresa}">
							                         		<td colspan="6">
							                         		<table width="100%" cellpadding="0" cellspacing="0" border="0">
							                         		<logic:iterate id="convenio" name="empresa" property="convenios">
									                         	<c:choose>
													   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
													   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
													   		  	</c:choose>
													   		  	<c:set var="count" value="${count + 1}"/>
							                         			<tr>
							                         				<td class="${estilo}">&nbsp;</td>
							                         				<td width="19%" class="${estilo}">
										                         		<logic:iterate id="item" name="convenio">
										                         			<logic:empty name="item" property="label">${item.value}</logic:empty>
										                         			<logic:notEmpty name="item" property="label">
										                         				<c:if test="${bloqueoEdicionNom == 0}" >
									                         						<html:link styleClass="links" title="Ver Detalle del Trabajador para n&oacute;mina ${item.value}" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">
									                         							${item.value}
									                         						</html:link>
										                         				</c:if>
										                         				<c:if test="${bloqueoEdicionNom == 1}" >
										                         					<html:link styleClass="links" title="Fuera de plazo para editar nómina" onclick="alert('\n El plazo válido para editar nóminas ha finalizado')" action="/EditarCotizacion?${item.label}&operacion=mod&accion=nominas&subAccion=trabajadores">${item.value}</html:link>
										                         				</c:if>
										                         			</logic:notEmpty>
										                         			&nbsp;
										                         		</logic:iterate>
																	</td>
							                         			</tr>
							                         		</logic:iterate>
							                         		</table>
							                         		</td>
							                         	</tr>				                         	
														<script language="javaScript">
															document.getElementById("tr-${trabajador.idCotizante}-${empresa.idEmpresa}").style.display='none';
														</script>
									   		    	</c:when>
										   		</c:choose>
			                         		</logic:iterate>
			                       		</logic:iterate>
		                       		</logic:notEmpty>
		                      	</table>
		                      	</td>
		                  	</tr>
		                	</table>                	
						</td>
					</tr>
				</table>
				</logic:present>
			</logic:equal>
		</td>
   	</tr>
   	<logic:present name="TrabListarAllActionForm" property="trabajadores">
		<bean:size id="nPags" name="TrabListarAllActionForm" property="numeroFilas"/>
		<c:if test="${nPags > 1}">
			<tr> 
				<td align="center" valign="middle" class="numeracion">
					<logic:iterate id="paginacion" name="TrabListarAllActionForm" property="numeroFilas">
						${paginacion}
					</logic:iterate>
				</td>
			</tr>
		</c:if>
   	</logic:present>
</table>


<script language="javaScript"> 
<!-- 
	function swapAll(id, imgId) 
	{
		obj = document.getElementById(id);
		img = document.getElementById(imgId);
	    if ( obj.style.display=='' )
	    {
			obj.style.display='none';
			img.src = '<c:url value="/img/ico_mas.gif" />';
		} else		
		{
			obj.style.display='';
			img.src = '<c:url value="/img/ico_menos.gif" />';
		}
	}

	function validaFormulario(f) 
	{
		//Valida caracteres validos en caja de búsqueda
		var sMsje = "";
		if (validaReq(document.getElementById("rut")) &&
			!validaRut(document.getElementById("rut").value))
			sMsje += "* Formato de rut inválido para el trabajador a buscar\n";
		if (validaReq(document.getElementById("nombre")) && !validaChrs(document.getElementById("nombre").value))
			sMsje += "* Caracteres inválidos en el nombre del trabajador a buscar\n";
		else if (document.getElementById("nombre").value.length > 0 && document.getElementById("nombre").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en el nombre del trabajador a buscar\n";
		if (validaReq(document.getElementById("apellidos")) &&
			!validaChrs(document.getElementById("apellidos").value))
			sMsje += "* Caracteres inválidos en los apellidos del trabajador a buscar\n";
		else if (document.getElementById("apellidos").value.length > 0 && document.getElementById("apellidos").value.length < 3)
			sMsje += "* Debe ingresar al menos 3 caracteres en los apellidos del trabajador a buscar\n";

		if (sMsje == "") {
			if (validaReq(document.getElementById("rut")) &&
				!validaDV(document.getElementById("rut").value))
				sMsje += "* Dígito verificador inválido para el rut del trabajador a buscar\n";
		}

		if (sMsje != "") 
		{
			alert("Errores de validación:\n\n" + sMsje);
			return false;
		}
		document.getElementById("rutOculto").value=document.getElementById("rut").value;
		document.getElementById("nombreOculto").value=document.getElementById("nombre").value;
		document.getElementById("apellidosOculto").value=document.getElementById("apellidos").value;
		return true;
	}

	function verificarCampos()
	{
	 	rut_a = document.getElementById("rutOculto");
		rut_n = document.getElementById("rut");
		if(rut_a!=rut_n)
	 		rut_n.value=rut_a.value;

		nom_a = document.getElementById("nombreOculto");
		nom_n = document.getElementById("nombre");
		if(nom_a!=nom_n)
	 		nom_n.value=nom_a.value;

   	    ap_a = document.getElementById("apellidosOculto");
		ap_n = document.getElementById("apellidos");
		if(ap_a!=ap_n)
	 		ap_n.value=ap_a.value;
	}

	function retornaEnlace(paginacion)
	{
		document.getElementById("FLG_Paginacion").value = "1"; //Indica que la paginación está presente
		formu = document.getElementById("formulario");
		verificarCampos();
		formu.action = "TrabCotizantesAll.do?accion=nominas&subAccion=trabajadores&subSubAccion=trabListaAll&paginaNumero="+paginacion;
		formu.submit();
	}
	
	function foco()
	{
		document.getElementById('rut').focus();
	}

	function seleccionCombos() {
		var comboProceso  = document.getElementById('tipoProceso');
		var comboConvenio = document.getElementById('opcGrupoConvenio');
		var proceso       = '${procesoOculto}';
		var convenio      = '${convenioOculto}';
		var i = 0;

		if (proceso != 'null'){
			for (i = 0; i < comboProceso.length; i++) {
				if (comboProceso[i].value   == proceso) {
         			comboProceso[i].selected = true;
         			break;
         		}
			}
		}

		if (convenio != 'null'){
			for (i = 0; i < comboConvenio.length; i++) {
				if (comboConvenio[i].value   == convenio){
         			comboConvenio[i].selected = true;
         			break;
         		}
			}
		}
	}

	seleccionCombos();

	foco();
// End --> 
</script>
</html:form>