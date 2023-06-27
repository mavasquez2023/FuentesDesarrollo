<%@ include file="/html/comun/taglibs.jsp" %>
<script language="javascript" type="text/javascript" src="js/jquery-1.3.2.js"></script>
<html:form action="/EditarComprobante" styleId="formulario">
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="procesos" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="comprobanteEditar" />
<html:hidden styleId="codigoBarra" property="codigoBarra" />
<bean:define id="bloqueoPagoLinea"><%=request.getAttribute("bloqueoPagoLinea")%></bean:define>
<bean:define id="bloqueoEdicionNom"><%=request.getAttribute("bloqueoEdicionNom")%></bean:define>
<bean:define id="bloqueoPagoCaja"><%=request.getAttribute("bloqueoPagoCaja")%></bean:define>


<script type="text/javascript">
$(document).ready(function(){
	    
	$(".checkBox").click(function(){
   		$("#botonPagar").attr('disabled','disabled');   		
	});
		
	$(".radioButton").click(function(){
   		$("#botonPagar").attr('disabled','disabled');
	});
	
});
</script>
<table width="590" border="0" cellspacing="0" cellpadding="0">
  	<tr> 
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
	        	<tr> 
		          	<td width="15%"><strong>RUT Independiente:</strong></td>
		          	<td width="20%">
						<html:select property="rut" styleClass="campos" styleId="comboEmpresas" onchange="javascript:cargaConvenios('${ConveniosActionForm.convenio}');">
							<html:optionsCollection property="empresas"/>
						</html:select>
					</td>
		            <td><strong>Nombre :</strong></td>
		          	<td>
		          		<div id="nombreEmp">${ConveniosActionForm.nombreEmpresa}</div>
						<logic:iterate id="emp" property="objEmpresas" name="ConveniosActionForm">
							<input type="hidden" value="${emp.razonSocial}" id="nomEmp#${emp.idEmpresa}" />
							<logic:iterate id="conv" property="convenios" name="emp" indexId="countConv">
								<input type="hidden" value="${conv.idConvenio}" id="idConvEmp#${emp.idEmpresa}#${countConv}" />
								<input type="hidden" value="${conv.descripcion}" id="descConvEmp#${emp.idEmpresa}#${countConv}" />
							</logic:iterate>
						</logic:iterate>
		          	</td>
		       	</tr>
		      	<tr style="display:none">
		         	<td><strong>Convenio:</strong></td>
		         	<td>
		         	<select name="convenio" class="campos">
						<option value="1" selected="selected">1</option>
					</select>
						<html:select property="convenio" styleClass="campos" styleId="comboConvenios" />
					</td>
		         	<td><strong>Tipo de N&oacute;mina:</strong></td>
		         	<td>
		         		<select name="tipoNomina" class="campos">
							<option value="R" selected="selected">REMUNERACION</option>
						</select>
						<!--<html:select name="ConveniosActionForm" property="tipoNomina" styleClass="campos">
							<html:optionsCollection name="ConveniosActionForm" property="tiposNomina" />
						</html:select> -->
		           	</td>
		      	</tr>
	          	<tr>
	          		<td colspan="3">&nbsp;</td>
	          		<td align="center"><html:submit property="operacion" value="${filtro}" styleClass="btn4"/></td>
	          	</tr>
	          	<tr> 
	            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
	         	</tr>
	        </table>
        </td>
	</tr>
	<tr>
		<td valign="top">
			<br />
			<html:errors />
		</td>
	</tr>
	<tr>
		<td>
			<br />
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
		<td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
			<logic:notPresent name="ConveniosActionForm" property="consulta">
				<tr><td>&nbsp;</td></tr>
	          	<tr align="center">
		        	<td height="30" bgcolor="#FFFFFF" class="titulo"><div align="center"><strong>No hay comprobante en estado POR PAGAR para este convenio y Tipo de N&oacute;mina</strong></div></td>
		        </tr>
			</logic:notPresent>
			<logic:present name="ConveniosActionForm" property="consulta">
	          	<tr align="center"> 
	            	<td height="19" valign="top">
		            	<table width="100%" border="0" cellpadding="0" cellspacing="1">
		                	<tr valign="bottom"> 
		                  		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Edici&oacute;n de Comprobante</strong></td>
		                	</tr>
		              	</table>
	              	</td>
				</tr>
			</logic:present>
			<logic:present name="ConveniosActionForm" property="consulta">
				<tr>
					<td>
		              	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
		                 	<tr> 
		                      	<td width="5%" align="center" class="barra_tablas">&nbsp;</td>
		                      	<td width="32%" align="center" nowrap="nowrap" class="barra_tablas">&nbsp;</td>
		                      	<td width="15%" align="center" nowrap="nowrap" class="barra_tablas">Total</td>
		                      	<td width="12%" align="center" nowrap="nowrap" class="barra_tablas">Pago</td>
		                      	<td width="15%" align="center" nowrap="nowrap" class="barra_tablas">Declaro y No Pago</td>
		                      	<td width="12%" align="center" nowrap="nowrap" class="barra_tablas">No Pago</td>
		                   	</tr>
		                   	<nested:iterate id="filaSeccion" name="ConveniosActionForm" property="consulta" indexId="nFila" type="cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO">
								<c:choose>
						   		    <c:when test="${nFila % 2 == 0}"><c:set var="estilo" value="textos_formularios"/>
						   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
						   		    </c:when>
			   						<c:otherwise>
			   							<c:set var="estilo" value="textos-formcolor2"/>
			   							<c:set var="estilo2" value="tablaOscuroBordes"/>
			   						</c:otherwise>
								</c:choose>
								<bean:define id="idLineaSec">
									<bean:write name="filaSeccion" property="hash" />
								</bean:define>
								<nested:hidden property="idCodigoBarra" />
								<nested:hidden property="idTipoSeccion" />
		                   		<tr >
			                  		<td class="${estilo}" width="37%" align="center" colspan="2"><a href="#SEC${idLineaSec}" onclick="swapAll('SEC${idLineaSec}', 'imgSEC${idLineaSec}');"><img id="imgSEC${idLineaSec}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>&nbsp;<strong>
				                  		<bean:write name="filaSeccion" property="glosa" /></strong>
				                  	</td>
			                  		<td class="${estilo}" width="15%" align="right" align="right">
			                  			<div align="right">
			                  				<bean:write format="$ ###,###,###" name="filaSeccion" property="total" />
										</div>
			                  		</td>
			                  		<%-- Si es pagable individual se muestran chekboxes, sino radios --%>
									<logic:equal name="filaSeccion" property="pagableIndividual" value="true">
		                          		<logic:equal name="filaSeccion" property="dnp" value="true">
					                  		<td class="${estilo}" width="12%"><div align="center">
					                  			<nested:checkbox title="Selecciona todos como Pago"  property="chkP" value="1" onclick="javaScript:cambiaRadios(this)" styleClass="checkBox"/>
					                  		</div></td>
					                  		<td class="${estilo}" width="19%"><div align="center">
					                  			<nested:checkbox  title="Selecciona todos como Declaro y No Pago" property="chkDNP" value="2" onclick="javaScript:cambiaRadios(this)" styleClass="checkBox"/>
					                  		</div></td>
					                  		<td class="${estilo}" width="12%">
					                  			&nbsp;
					                  		</td>
				                  		</logic:equal>
		                          		<logic:notEqual name="filaSeccion" property="dnp" value="true">
					                  		<td class="${estilo}" width="12%" ><div align="center">
					                  			<nested:checkbox title="Selecciona todos como Pago" property="chkP" value="1" onclick="javaScript:cambiaRadios(this)" styleClass="checkBox"/>
					                  		</div></td>
					                  		<td class="${estilo}" width="19%" ><div align="center">
					                  			&nbsp;
					                  		</div></td>
					                  		<td class="${estilo}" width="12%" ><div align="center">
					                  			<nested:checkbox title="Selecciona todos como No Pago" property="chkNP" value="3" onclick="javaScript:cambiaRadios(this)" styleClass="checkBox"/>
					                  		</div></td>
				                  		</logic:notEqual>
									</logic:equal>
									<logic:notEqual name="filaSeccion" property="pagableIndividual" value="true">
										<logic:equal name="filaSeccion" property="mostrar3" value="true">
						                  		<td class="${estilo}" width="12%"><div align="center">
						                  			<nested:radio property="tipoPago" value="1" styleClass="radioButton"/>
						                  		</div></td>
						                  		<td class="${estilo}" width="19%"><div align="center">
						                  			<nested:radio property="tipoPago"  value="2"  styleClass="radioButton"/>
					    	              		</div></td>
					            	      		<td class="${estilo}" width="12%" ><div align="center">
					                	  			<nested:radio property="tipoPago" value="3"  styleClass="radioButton"/>
						                  		</div></td>
										</logic:equal>
										<logic:notEqual name="filaSeccion" property="mostrar3" value="true">
			                          		<logic:equal name="filaSeccion" property="dnp" value="true">
						                  		<td class="${estilo}" width="12%"><div align="center">
						                  			<nested:radio property="tipoPago" value="1" onclick="javaScript:cambiaRadios(this)" styleClass="radioButton"/>
						                  		</div></td>
						                  		<td class="${estilo}" width="19%"><div align="center">
						                  			<nested:radio property="tipoPago" value="2" onclick="javaScript:cambiaRadios(this)" styleClass="radioButton"/>
					    	              		</div></td>
					        	          		<td class="${estilo}" width="12%">
					            	      			&nbsp;
					                	  		</td>
					                  		</logic:equal>
			                          		<logic:notEqual name="filaSeccion" property="dnp" value="true">
						                  		<td class="${estilo}" width="12%" ><div align="center">
						                  			<nested:radio property="tipoPago" value="1" onclick="javaScript:cambiaRadios(this)"  styleClass="radioButton"/>
						                  		</div></td>
						                  		<td class="${estilo}" width="19%" ><div align="center">
					    	              			&nbsp;
					        	          		</div></td>
					            	      		<td class="${estilo}" width="12%" ><div align="center">
					                	  			<nested:radio property="tipoPago" value="3" onclick="javaScript:cambiaRadios(this)"  styleClass="radioButton"/>
						                  		</div></td>
					                  		</logic:notEqual>
				                  		</logic:notEqual>
			                  		</logic:notEqual>
		                   		</tr>
			                	<tr style="display:none;" valign="top" id="SEC${idLineaSec}">
			                  		<td class="${estilo}" width="5%" align="center" >&nbsp;</td>
			                  		<td class="${estilo}" width="95%" colspan="5">
			                  			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
					                   		<nested:iterate id="filaDetalle" property="detalle" indexId="nFilaDet" type="cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO">
												<c:choose>
										   		    <c:when test="${nFilaDet % 2 == 0}"><c:set var="estilo" value="textos_formularios"/>
										   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
										   		    </c:when>
							   						<c:otherwise>
							   							<c:set var="estilo" value="textos-formcolor2"/>
							   							<c:set var="estilo2" value="tablaOscuroBordes"/>
							   						</c:otherwise>
												</c:choose>
												<bean:define id="idLineaDet"><bean:write name="filaDetalle" property="hash" /></bean:define>
												<nested:hidden property="idCodigoBarra" />
												<nested:hidden property="idTipoSeccion" />
												<nested:hidden property="idTipoDetalle" />
				                  				<tr>
				                  					<td class="${estilo}" width="37%" ><a href="#DET${filaSeccion.idTipoSeccion}${idLineaDet}" onclick="swapAll('DET${filaSeccion.idTipoSeccion}${idLineaDet}', 'imgDET${filaSeccion.idTipoSeccion}${idLineaDet}');"><img id="imgDET${filaSeccion.idTipoSeccion}${idLineaDet}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>
					                  					<bean:write name="filaDetalle" property="glosa" />
						                  			</td>
					                          		<td class="${estilo}" width="15%" align="right" >
					                          			<div align="right">
						                          			<bean:write name="filaDetalle" format="$ ###,###,###" property="total" />
					                          			</div>
					                          		</td>
													<logic:equal name="filaSeccion" property="pagableIndividual" value="true">
						                          		<logic:equal name="filaSeccion" property="dnp" value="true">
									                  		<td class="${estilo}" width="12%"><div align="center">
									                  			<nested:radio property="tipoPago" value="1" onclick="javascript:validarTodosChkBox(this);"  styleClass="radioButton"/>
									                  		</div></td>
									                  		<td class="${estilo}" width="22%"><div align="center">
									                  			<nested:radio property="tipoPago" value="2" onclick="javascript:validarTodosChkBox(this);"  styleClass="radioButton"/>
									                  		</div></td>
									                  		<td class="${estilo}" width="13%">
									                  			&nbsp;
									                  		</td>
								                  		</logic:equal>
						                          		<logic:notEqual name="filaSeccion" property="dnp" value="true">
									                  		<td class="${estilo}" width="12%"><div align="center">
									                  			<nested:radio property="tipoPago" value="1" onclick="javascript:validarTodosChkBox(this);"  styleClass="radioButton"/>
									                  		</div></td>
									                  		<td class="${estilo}" width="22%"><div align="center">
									                  			&nbsp;
									                  		</div></td>
									                  		<td class="${estilo}" width="13%"><div align="center">
									                  			<nested:radio property="tipoPago" value="3" onclick="javascript:validarTodosChkBox(this);"  styleClass="radioButton"/>
									                  		</div></td>
								                  		</logic:notEqual>
							                  		</logic:equal>
							                  		<logic:notEqual name="filaSeccion" property="pagableIndividual" value="true">
								                  		<td class="${estilo}" width="12%">&nbsp;</td>
								                  		<td class="${estilo}" width="22%">&nbsp;</td>
								                  		<td class="${estilo}" width="13%">&nbsp;</td>
							                  		</logic:notEqual>
				            	      			</tr>
					                  			<tr id="DET${filaSeccion.idTipoSeccion}${idLineaDet}">
		            			                   	<td class="${estilo}" colspan="5">
			                        			       	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				                                   			<tr> 
		    		                                 			<td height="19" colspan="2" align="left" valign="middle" class="barra_tablas">Detalle <bean:write name="filaDetalle" property="glosa" /></td>
						                                   	</tr>
						                                   	<!-- name="filaDetalle"  -->
															<nested:iterate id="filaDetDetalle" property="detalle" indexId="nFilaDetDet" type="cl.araucana.cp.presentation.struts.javaBeans.LineaComprobanteProvisorioVO">
																<c:choose>
														   		    <c:when test="${nFilaDetDet % 2 == 0}"><c:set var="estilo" value="textos_formularios"/>
														   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
														   		    </c:when>
											   						<c:otherwise>
											   							<c:set var="estilo" value="textos-formcolor2"/>
											   							<c:set var="estilo2" value="tablaOscuroBordes"/>
											   						</c:otherwise>
																</c:choose>
																<tr> 
																	<td class="tablaClaroBordes" width="42%" height="19" align="left" valign="middle">
																	    <bean:define id="nombreDetalle" name="filaDetDetalle" property="glosa"></bean:define>
										                            	<% if (((String)nombreDetalle).trim().equals("0,6%")) { %>
										                            	   Aporte CCAF
										                            	<% } else{  %>
																			<bean:write name="filaDetDetalle" property="glosa" />
										                            	<% } %>																	    
																	</td>
		        	        		                    		  	<td class="tablaClaroBordes" align="right" valign="middle">
		        	        		                    		  		<div align="right">
			        	        		                      				<bean:write name="filaDetDetalle" format="$ ###,###,###" property="total" />
		        	        		                      				</div>
				        	        		                      	</td>
		        		    	            		           	</tr>
															</nested:iterate>
														</table>
													</td>
												</tr>
											</nested:iterate>
										</table>
									</td>
								</tr>
							</nested:iterate>
		                	<tr valign="top"> 
		                  		<td width="45%" class="${estilo}" colspan="2"><strong>TOTALES</strong></td>
		                  		<td width="12%" class="${estilo}">&nbsp;</td>
		                  		<td width="12%" class="${estilo}"><div align="center"><strong><bean:write format="$ ###,###,###" name="ConveniosActionForm" property="totalP" /></strong></div><html:hidden styleId="totalP" name="ConveniosActionForm" property="totalP" /></td>
		                  		<td width="19%" class="${estilo}"><div align="center"><strong><bean:write format="$ ###,###,###" name="ConveniosActionForm" property="totalDNP" /></strong></div></td>
		                  		<td width="12%" class="${estilo}"><div align="center"><strong><bean:write format="$ ###,###,###" name="ConveniosActionForm" property="totalNP" /></strong></div></td>
		                	</tr>            	
		                </table>
	              	</td>
	          	</tr>
	          	<tr align="center"> 
	            	<td height="33" valign="top">
	            	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="txtLista">
	                	<tr> 
		                  	<td align="center" valign="middle">
	                  			<div class="mensaje_alerta">
	                  				<strong>Advertencia!</strong> Cualquier modificaci&oacute;n invalida comprobantes generados y no pagados
	                  			</div>
	                  			<br />
                      			<br />

	                      		<nested:equal property="puedeGuardarOPagar" value="true">
		                      		<c:if test="${bloqueoEdicionNom == 0}" >
			                      		<html:submit property="operacion" value="${guardar}" styleClass="btn4"/>
		                      		</c:if>
		                      		<c:if test="${bloqueoEdicionNom == 1}" >
			                      		<html:button property="operacion" onclick="alert('\n El plazo válido para editar Nominas ha finalizado')" alt="Fuera de plazo para enviar nómina" title="Fuera de plazo para enviar nómina"  value="${guardar}" styleClass="btn4"/>
		                      		</c:if>
		                      		
		                      		<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 0}" >
			                      		<html:submit property="operacion" value="${pagar}" styleClass="btn4" styleId="botonPagar"/>
			                      	</c:if>
			                      	<c:if test="${bloqueoPagoCaja == 0 && bloqueoPagoLinea == 1}" >
			                      		<html:submit property="operacion" value="${pagar}" styleClass="btn4" onclick="alert('\n El plazo válido para pagar Comprobantes en Línea ha finalizado, sólo podrá pagar por Caja')" styleId="botonPagar"/>
		                      		</c:if>
			                      	<c:if test="${bloqueoPagoCaja == 1 && bloqueoPagoLinea == 0}" >
			                      		<html:submit property="operacion" value="${pagar}" styleClass="btn4" onclick="alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado, sólo podrá pagar en Línea')" styleId="botonPagar"/>
		                      		</c:if>
			                      	<c:if test="${bloqueoPagoCaja == 1 && bloqueoPagoLinea == 1}" >
				                      	<html:button property="operacion" onclick="alert('\n El plazo válido para pagar Comprobantes ha finalizado')" alt="Fuera de plazo para pagar Comprobantes" title="Fuera de plazo para pago Comprobantes"  value="${pagar}" styleClass="btn4" styleId="botonPagar"/>
		                      		</c:if>		                      	
								</nested:equal>	                      			

	                      		<html:cancel property="operacion" value="${cancelar}" styleClass="btn4"/>
	                      		<br />
	                      		<br />
		                    </td>
	                	</tr>
	              	</table>
	              	</td>
	          	</tr>
			</logic:present>
        </table>
        </td>
  	</tr>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
cargaConvenios('${ConveniosActionForm.convenio}');

//Para cerrar todas las secciones
var idPrimeraFila = "";
var filas = document.getElementsByTagName("tr");
for (var i = 0; i < filas.length; i++) {
	if ((filas[i].id != null) &&
		((filas[i].id.indexOf("SEC") == 0) ||
		(filas[i].id.indexOf("DET") == 0))) {
		if (idPrimeraFila == "")
			idPrimeraFila = filas[i].id;
		cierra(filas[i].id, "img" + filas[i].id);
	}
}
if (idPrimeraFila != "")
{
	//Activa el primero
	//swapAll(idPrimeraFila, "img" + idPrimeraFila);
	
	//Valida todos los chkBoxes
	inputs = document.forms[0].getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].type == "checkbox") {
			validarChkBox(inputs[i]);
		}
	}
}
//Setea el valor de verdad del chkBox a "todos los detalles correspondientes a este chkBox están en verdadero"
function validarChkBox(chkBox) {
    var tipoPago = chkBox.value;
    var inputs = document.getElementsByTagName("input");
    var prefijo = chkBox.name.split(".")[0];
    var todosIgual = true;
    for (var i = 0; i < inputs.length; i++) {
        if ((inputs[i].type != "radio") || (inputs[i].name.indexOf(prefijo) != 0)
            || (inputs[i].value != tipoPago))
            continue;
		if (!inputs[i].checked) {
			todosIgual = false;
			break;
		}
    }
	chkBox.checked = todosIgual;
}

//Valida todos los chkBox de la misma sección que este radio
function validarTodosChkBox(radioBtn) {
    var inputs = document.getElementsByTagName("input");
    var prefijo = radioBtn.name.split(".")[0];
    for (var i = 0; i < inputs.length; i++) {
        if ((inputs[i].type != "checkbox") || (inputs[i].name.indexOf(prefijo) != 0))
        	continue;
		validarChkBox(inputs[i]);
    }
}

function cierra(id, imgId)  {
	obj = document.getElementById(id);
	img = document.getElementById(imgId);

	obj.style.display='none';
	img.src = '<c:url value="/img/ico_mas.gif" />';
}

function swapAll(id, imgId) {

	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if (obj.style.display=='') {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
	} else {
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
	}
}

//Cambia el estado de los radio button de la misma seccion y columna que el checkBox chkBtn
function cambiaRadios(chkBtn) {
	var tipoPago = chkBtn.value;
	var prefijo = chkBtn.name.split(".")[0];
	var inputs = document.forms[0].getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
        if ((inputs[i].type == undefined) || (inputs[i].type != "radio")
        	|| (inputs[i].name.indexOf(prefijo) != 0) || (inputs[i].value != tipoPago))
        	continue;
        inputs[i].checked = true;
	}

	for (var i = 0; i < inputs.length; i++) {
		if ((inputs[i].type == undefined) || (inputs[i].type != "checkbox")
        	|| (inputs[i].name.indexOf(prefijo) != 0))
        	continue;
        inputs[i].checked = false;
	}
	chkBtn.checked = true;
}


// End --> 
</script>