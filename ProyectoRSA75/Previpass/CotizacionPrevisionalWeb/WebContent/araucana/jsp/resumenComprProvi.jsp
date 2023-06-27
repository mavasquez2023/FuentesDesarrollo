<%@ include file="/html/comun/taglibs.jsp" %>
<script language="Javascript">
function abrePopup(valor)
{
	var accion = '<%=request.getParameter("accion")%>';
	var subAccion = '<%=request.getParameter("subAccion")%>';
	var cod = '<%=request.getParameter("codigoBarra")%>';
 	var ruta = '<%=request.getContextPath()%>';
	ruta = ruta+'/PagarComprobantes.do?accion='+accion+'&subAccion='+subAccion+'&codigoBarra='+cod+'&operacion='+valor;
	window.open(ruta,'window','width=770, height=500');
}
function envia(valor)
{
	var accion = '<%=request.getParameter("accion")%>';
	var subAccion = '<%=request.getParameter("subAccion")%>';
	var cod = '<%=request.getParameter("codigoBarra")%>';
 	var ruta = '<%=request.getContextPath()%>';
	ruta = ruta+'/PagarComprobantes.do?accion='+accion+'&subAccion='+subAccion+'&codigoBarra='+cod+'&operacion='+valor;
	location.replace(ruta);
}
function openPopUp(url) {
		target = "_blank";
		//window.open(url, target, "height=600,width=650,toolbar=no,directories=no,scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
		window.open(url, target, "toolbar=no,directories=no,scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes");
}
</script>

<html:form action="/PagarComprobantes" styleId="formulario">
<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="procesos" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="pagarNomina" />
<html:hidden styleId="codigoBarra" property="codigoBarra" />
<bean:define id="bloqueoPagoLinea"><%=request.getAttribute("bloqueoPagoLinea")%></bean:define>
<bean:define id="bloqueoPagoCaja"><%=request.getAttribute("bloqueoPagoCaja")%></bean:define>
<input type="hidden" name="codigos" id="codigos" value="${PagarActionForm.codigoBarra}" />
<table width="590" border="0" cellspacing="0" cellpadding="0">
  	<tr> 
    	<td align="left" valign="top">
	        <table width="100%" border="0" cellpadding="1" cellspacing="5" class="textos-formularios3">
	        	<tr > 
		          	<td width="15%"><strong>RUT Empresa:</strong></td>
		          	<td  width="20%">
						<html:select property="rut" styleClass="campos" styleId="comboEmpresas" onchange="javascript:cargaConvenios('${PagarActionForm.convenio}');">
							<html:optionsCollection property="empresas"/>
						</html:select>
					</td>
		            <td><strong>Raz&oacute;n Social:</strong></td>
		          	<td>
		          		<div id="nombreEmp">${PagarActionForm.nombreEmpresa}</div>
						<logic:iterate id="emp" property="objEmpresas" name="PagarActionForm">
							<input type="hidden" value="${emp.razonSocial}" id="nomEmp#${emp.idEmpresa}" />
							<logic:iterate id="conv" property="convenios" name="emp" indexId="countConv">
								<input type="hidden" value="${conv.idConvenio}" id="idConvEmp#${emp.idEmpresa}#${countConv}" />
								<input type="hidden" value="${conv.descripcion}" id="descConvEmp#${emp.idEmpresa}#${countConv}" />
							</logic:iterate>
						</logic:iterate>
		          	</td>
		       	</tr>
		      	<tr> 
		         	<td><strong>Convenio:</strong></td>
		         	<td>
						<html:select property="convenio" styleClass="campos" styleId="comboConvenios" />
					</td>
		         	<td><strong>Tipo de N&oacute;mina:</strong></td>
		         	<td>
						<html:select property="tipoNomina" styleClass="campos">
							<html:optionsCollection property="tiposNomina" />
						</html:select>
		           	</td>
		      	</tr>
	          	<tr>
	          		<td colspan="3">&nbsp;</td>
	          		<td align="center"><html:submit property="operacion"  value="${filtro}" styleClass="btn-destacado"/></td>
	         	</tr>
	        </table>
		</td>
	</tr>
	<tr>
		<td>
	        <table width="100%" border="0" cellpadding="0" cellspacing="0">
				<html:messages id="msg" message="true">
					<br><div class="msgExito">${msg}</div><br>
				</html:messages>
				<logic:notPresent name="msg">
		          	<logic:present name="PagarActionForm" property="consulta" >
		          		<tr align="center"> 
		    	        	<td height="19" valign="top">
			    	        	<table width="100%" border="0" cellpadding="0" cellspacing="1">
			        	        	<tr valign="bottom"> 
			            	      		<td height="30" bgcolor="#FFFFFF" class="titulo">
			            	      			<strong>Resumen de Comprobante Provisorio</strong>
			            	      		</td>
			                		</tr>
				              	</table>
			              	</td>
						</tr>
						<tr>
							<td>
				              	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				                 	<tr> 
				                      	<td width="40%" align="center" nowrap="nowrap" class="barra_tablas">&nbsp;</td>
				                      	<td width="60%" align="center" nowrap="nowrap" class="barra_tablas">Total</td>
				                   	</tr>
				                   	<logic:iterate id="filaSeccion" name="PagarActionForm" property="consulta" indexId="nFila" type="cl.araucana.cp.presentation.struts.javaBeans.LineaResumenComprobanteProvisorio">
										<c:choose>
								   		    <c:when test="${nFila % 2 == 0}">
								   		    	<c:set var="estilo" value="textos_formularios"/>
							   		    		<c:set var="estilo2" value="tablaClaroBordes"/>
							   		    	</c:when>
					   						<c:otherwise>
					   							<c:set var="estilo" value="textos-formcolor2"/>
					   							<c:set var="estilo2" value="tablaOscuroBordes"/>
					   						</c:otherwise>
										</c:choose>
										<tr>
				                  			<td class="${estilo}" width="40%" align="center">
						                  		&nbsp;<strong><bean:write name="filaSeccion" property="glosa" /></strong>
						                  	</td>
					                  		<td class="${estilo}" width="60%" align="right">
					                  			<div align="right">
					                  				<bean:write format="$ ###,###,###,###" name="filaSeccion" property="total" />
												</div>
					                  		</td>
					                  	</tr>
									</logic:iterate>
									<c:choose>
							   		    <c:when test="${nFila % 2 == 0}">
				   							<c:set var="estilo" value="textos-formcolor2"/>
				   							<c:set var="estilo2" value="tablaOscuroBordes"/>
						   		    	</c:when>
				   						<c:otherwise>
							   		    	<c:set var="estilo" value="textos_formularios"/>
						   		    		<c:set var="estilo2" value="tablaClaroBordes"/>
				   						</c:otherwise>
									</c:choose>
									<tr valign="top"> 
			                  			<td width="40%" class="${estilo}">
				                  			<div align="right">
				                  				<strong>TOTAL</strong>
				                  			</div>
				                  		</td>
				                  		<td width="60%" class="${estilo}">
				                  			<div align="right">
				                  				<strong>
				                  					<bean:write format="$ ###,###,###,###" name="PagarActionForm" property="total"/>
				                  				</strong>
				                  			</div>
				                  		</td>
				                	</tr>
								</table>
							</td>
						</tr>
			          	<tr align="center"> 
			            	<td height="33" valign="top">
				            	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="txtLista">
				                	<tr> 
					                  	<td align="center" valign="middle">
					                  		<br> 
				                      		<input name="operacion" type="submit" class="btn4" value="${verDetalle}" /> 
	
				                      		<c:if test="${bloqueoPagoCaja == 0}" >
										  		<%--input name="operacion" type="submit" class="btn4" value="${pagoCaja}"/--%> 	
										  		<input name="operacion" type="submit" class="btn4" value="${pagoCaja}" onclick="abrePopup('${pagoCaja}'); return false;" />
											</c:if>
											<c:if test="${bloqueoPagoCaja == 1}" >
					                      		<input name="operacion" type="submit" class="btn_grande" value="Impresión Comprobante" onclick="javascript:limpiar();"/>
				                      		</c:if>
				                      		<c:if test="${bloqueoPagoLinea == 0}" >							  		
										  		<input name="operacion" type="submit" class="btn4" onclick="validaPagoCero(); return false;" value="${pagoLinea}"/>
										  	</c:if>
										  	<c:if test="${bloqueoPagoLinea == 1}" >
					                      		<html:button property="operacion" onclick="alert('\n El plazo válido para pago de Comprobantes en Línea ha finalizado')" alt="El plazo válido para pago de Comprobantes en Línea ha finalizado" title="El plazo válido para pago de Comprobantes en Línea ha finalizado"  value="${pagoLinea}" styleClass="btn4"/>
				                      		</c:if>
					                  		<br><br>
					                    </td>
				                	</tr>
				              	</table>
			              	</td>
			          	</tr>
		          	</logic:present>
				</logic:notPresent>
			</table>
		</td>
	</tr>
</table>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<logic:present name="msnAvisos">
		<tr>
				<td colspan="2" class="textos_formularios" align="left">En la nomina procesada se han detectados avisos relacionados con las cotizaciones informadas.</td>
		</tr>
		<tr>
			<td width="40%" class="textos_formularios" align="left">
				Para revisar informe de Avisio
			</td>
			<logic:iterate id="record" name="msnAvisos" indexId="count">
			    <td class="textos_formularios" align="right">
				   	<logic:equal name="record" property="tieneAviso" value="true">
				 		<img align="middle" alt="Listado Avisos" title="Listado Avisos" border="0" src="<c:url value="/img/alert.png" />" onclick="javascript:openPopUp('ListarAvisos.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1&origenTablaAviso=${record.origenTablaAviso}');" style="CURSOR: hand;"/>
				   	</logic:equal>
				   	<logic:equal name="record" property="tieneError" value="true">
						<img align="middle" alt="Listado Errores" title="Listado Errores" border="0" src="<c:url value="/img/alert-error.png" />" onclick="javascript:openPopUp('ListarErrores.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1');" style="CURSOR: hand;"/>
				   	</logic:equal>
				</td>
		    </logic:iterate>
	 	</tr>
	</logic:present>
</table>
<script language="javaScript"> 
<!-- 
cargaConvenios('${PagarActionForm.convenio}');

function validaPagoCero()
{
	if (${PagarActionForm.total} == 0)
		envia('${pagoLinea}');
	else
		abrePopup('${pagoLinea}');
}
// End --> 
</script>
</html:form>