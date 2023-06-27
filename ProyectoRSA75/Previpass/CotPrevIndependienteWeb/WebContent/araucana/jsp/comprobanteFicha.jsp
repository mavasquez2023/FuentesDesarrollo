<%@ include file="/html/comun/taglibs.jsp" %>
<script language="Javascript">
function abrePopup(valor)
{
	var accion = '<%=request.getParameter("accion")%>';
	var subAccion = '<%=request.getParameter("subAccion")%>';
	var cod = '<%=request.getParameter("idCodigoBarras")%>';
 	var ruta = '<%=request.getContextPath()%>';
	ruta = ruta+'/PagarComprobantes.do?accion='+accion+'&subAccion='+subAccion+'&codigoBarra='+cod+'&operacion='+valor;
	window.open(ruta,'window','width=770, height=500');
}
function envia(valor)
{
	var accion = '<%=request.getParameter("accion")%>';
	var subAccion = '<%=request.getParameter("subAccion")%>';
	var cod = '<%=request.getParameter("idCodigoBarras")%>';
 	var ruta = '<%=request.getContextPath()%>';
	ruta = ruta+'/PagarComprobantes.do?accion='+accion+'&subAccion='+subAccion+'&codigoBarra='+cod+'&operacion='+valor;
	location.replace(ruta);
}
</script>

<html:form action="/DetalleComprobante" target="_blank" styleId="formulario">
<html:hidden styleId="codigos" property="idCodigoBarras"/>
<html:hidden styleId="estadoImpresion" property="estadoImpresion"/>
<html:hidden styleId="detallesMostrar" property="detallesMostrar"/>
<html:hidden styleId="periodo" property="periodo"/>

<html:hidden styleId="accion" property="accion" name="accion" value="inicio" />
<html:hidden styleId="subAccion" property="subAccion" name="subAccion" value="procesos" />
<html:hidden styleId="subSubAccion" property="subSubAccion" name="subSubAccion" value="comprobanteFicha" />
<bean:define id="bloqueoPagoLinea"><%=request.getAttribute("bloqueoPagoLinea")%></bean:define>
<bean:define id="bloqueoPagoCaja"><%=request.getAttribute("bloqueoPagoCaja")%></bean:define>
<input type="hidden" name="codigos" id="codigos" value="${DetalleActionForm.idCodigoBarras}" />
<c:set var="estilo" value="textos-formcolor2"/>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>		
		<table width="100%" border="0" cellpadding="1" cellspacing="5" class="tabla-datos">
			<tr> 
	          	<td width="15%"><strong>RUT Independiente:</strong></td>
	          	<td width="20%">
					<html:select property="rutEmpresa" styleClass="campos" styleId="comboEmpresas" onchange="javascript:cargaConvenios('${DetalleActionForm.convenio}');">
						<html:optionsCollection property="empresas"/>
					</html:select>
				</td>
	            <td><strong>Nombre:</strong></td>
	          	<td>
	          		<div id="nombreEmp">${DetalleActionForm.razonSocial}</div>
					<logic:iterate id="emp" property="objEmpresas" name="DetalleActionForm">
						<input type="hidden" value="${emp.razonSocial}" id="nomEmp#${emp.idEmpresa}" />
						<logic:iterate id="conv" property="convenios" name="emp" indexId="countConv">
							<input type="hidden" value="${conv.idConvenio}" id="idConvEmp#${emp.idEmpresa}#${countConv}" />
							<input type="hidden" value="${conv.descripcion}" id="descConvEmp#${emp.idEmpresa}#${countConv}" />
						</logic:iterate>
					</logic:iterate>
	          	</td>
	       	</tr>
			<tr style="display:none">
			
	         	<td ><strong>Convenio:</strong></td>
	         	<td>
					<html:select property="convenio" styleClass="campos" styleId="comboConvenios" />
					<select name="convenio" class="campos">
						<option value="1" selected="selected">1</option>
					</select>
				</td>
	         	<td><strong>Tipo de N&oacute;mina:</strong></td>
	         	<td>
		         	<select name="tipoProceso" class="campos">
						<option value="R" selected="selected">REMUNERACION</option>
					</select>
					<!-- <html:select property="tipoProceso" styleClass="campos">
						<html:optionsCollection property="tiposProcesos" label="descripcion" value="idTipoNomina" />
					</html:select> -->
	           	</td>
	      	</tr>
          	<tr>
          		<td colspan="3">&nbsp;</td>
          		<td align="center"><html:submit property="operacion"  value="${filtro}" onclick="javascript:limpiar();" styleClass="btn4"/></td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="4" bgcolor="#85b4be"></td>
         	</tr>
      	</table>
      	<html:messages id="msg" message="true">
      		<br />
			<div class="msgExito">${msg}</div>
		</html:messages>
      	<logic:notPresent name="msg">
			<table width="100%" border="0" cellpadding="0" cellspacing="1">
	         	<tr valign="bottom"> 
	           		<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Comprobante de Pago de Cotizaciones</strong></td>
	          	</tr>
	         </table>
	         <table width="100%" border="0" cellpadding="0" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#FFFFFF">
	         <logic:iterate id="seccion" name="DetalleActionForm" property="secciones" indexId="count"><c:choose>
   		    	<c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/></c:when>
	   				<c:otherwise><c:set var="estilo" value="textos-formcolor2"/></c:otherwise>
	   		 	</c:choose>
	         	<tr valign="top"> 
					<td width="18%" align="center" class="${estilo}"><strong>${seccion.nombre}</strong></td>
	           		<td width="82%" align="left" bordercolor="#CCCCCC" class="${estilo}"> 
	            	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="txt2">
					<logic:iterate id="detalle" name="seccion" property="detalles">
	            		<tr> 
							<td width="79%" align="left" valign="middle" class="textos_formularios">
								<a href="#${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}" onclick="swapAll('${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}', 'img-${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}');">
									<img id="img-${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" />
								</a>
								${detalle.nombre}
							</td>
							<td width="5%" align="right" valign="middle" class="textos_formularios"><div align="right">$</div></td>
	                   		<td width="13%" align="right" valign="middle" class="textos_formularios"><div align="right">${detalle.total}</div></td>
	                   		<td width="3%" class="textos_formularios">&nbsp;</td>
						</tr>
	               		<tr id="${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}"> 
	              			<td colspan="4" align="center" class="textos_formcolor">
	                  		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
	                  			<tr> 
	                      			<td height="19" colspan="3" align="left" valign="middle" class="barra_tablas">Detalle ${detalle.nombre}</td>
	                      		</tr>
	                            <logic:iterate id="detalleDetalle" name="detalle" property="listaDetalles">
		                            <bean:define id="nombreDetalle" name="detalleDetalle" property="label"></bean:define>
		                            <tr> 
		                            	<td width="60%" height="19" align="left" valign="middle" class="textos-formularios2">
		                            
		                            	<% if (((String)nombreDetalle).trim().equals("0,6%")) { %>
		                            		<c:if test="${detalleDetalle.value != '0'}"> 		                            		
		                            	   		<a href="#" onclick="mostrarOcultarTablas('seccionSeccionAporte','img-Aporte');">
												<img id="img-Aporte" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>
	                            			</c:if>	                            			
		                            	   Aporte CCAF
		                            	<% }else if (((String)nombreDetalle).trim().equals("Creditos")) { %>
		                            		<c:if test="${detalleDetalle.value != '0'}">
			                               	    <a href="#" onclick="mostrarOcultarTablas('seccionSeccionCreditos','img-Creditos');">
												<img id="img-Creditos" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>
	                            			</c:if>
	                            			${detalleDetalle.label}
		                            	<% }else if (((String)nombreDetalle).trim().equals("Leasing")) { %>
		                            		<c:if test="${detalleDetalle.value != '0'}"> 		                            		 		                            		
			                            		 <a href="#" onclick="mostrarOcultarTablas('seccionSeccionLeasing','img-Leasing');">
												<img id="img-Leasing" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>
	                            			</c:if>	                            			
		                            		${detalleDetalle.label}
		                            	<% } else{  %>
  		                            	   	${detalleDetalle.label}
		                            	<% } %>
		                            	</td>
		                                <td align="right" width="40%" valign="middle" class="textos-formularios2">$ ${detalleDetalle.value}</td>
		                            </tr>
			                            <% if (((String)nombreDetalle).trim().equals("0,6%")){ %>
				                               <tr id="seccionSeccionAporte" style="display:none">
							                            <td colspan="2">
								                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								                            	<tr>
								                            		<td align="center" nowrap="nowrap" width="40%" valign="middle" class="barra_tablas">Fecha Vencimiento</td>
								                            		<td align="center" nowrap="nowrap" width="40%" valign="middle" class="barra_tablas">Monto</td>
								                            		<td align="center" nowrap="nowrap" width="40%" valign="middle" class="barra_tablas">Interes y reajustes</td>
								                            		<td align="center" nowrap="nowrap" width="40%" valign="middle" class="barra_tablas">Renta Promedio</td>
								                            	</tr>
								                            	<logic:notEmpty name="DetalleActionForm" property="aporteDetalleFormVO">
								                            		<logic:iterate id="aporteDetalleFormVO" name="DetalleActionForm" property="aporteDetalleFormVO" type="cl.araucana.cp.presentation.struts.forms.AporteDetalleFormVO">
					  					                            	<tr>
		   								                            		<td align="right" nowrap="nowrap" width="40%" valign="middle" class="textos-formularios2"><bean:write name="aporteDetalleFormVO" property="fechaVencimiento" format="dd-MM-yyyy"/></td>
										                            		<td align="right"" nowrap="nowrap" width="40%" valign="middle" class="textos-formularios2"><bean:write name="aporteDetalleFormVO" property="monto" format="$ ###,###,###"/></td>
										                            		<td align="right" nowrap="nowrap" width="40%" valign="middle" class="textos-formularios2"><bean:write name="aporteDetalleFormVO" property="interesesReajuste" format="$ ###,###,###"/></td>
										                            		<td align="right" nowrap="nowrap" width="40%" valign="middle" class="textos-formularios2"><bean:write name="aporteDetalleFormVO" property="rentaPromedio" format="$ ###,###,###"/></td>
										                            	</tr>
										                            </logic:iterate>
							                            		</logic:notEmpty>
								                            </table>
							                            </td>
						                          </tr>
				                         <%}%>
				                           <% if (((String)nombreDetalle).trim().equals("Creditos")){ %>
				                               <tr id="seccionSeccionCreditos" style="display:none">
							                            <td colspan="2">
								                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								                            	<tr>
								                            		<td align="center" width="10%" nowrap="nowrap" valign="middle" class="barra_tablas">Folio</td>
								                            		<td align="center" width="5%"  valign="middle" class="barra_tablas">Cuota</td>
								                            		<td align="center" width="10%" nowrap="nowrap" valign="middle" class="barra_tablas">Monto</td>
								                            		<td align="center" width="10%" valign="middle" class="barra_tablas">Fecha Vencimiento</td>
								                            		<td align="center" width="10%" nowrap="nowrap" valign="middle" class="barra_tablas">Capital</td>
								                            		<td align="center" width="10%" nowrap="nowrap" valign="middle" class="barra_tablas">Seguros</td>
								                            		<td align="center" width="10%" valign="middle" class="barra_tablas">Reajuste e Intereses</td>
								                            	</tr>
							                            	<logic:present name="DetalleActionForm" property="creditoDetalleFormVO">
								                            	<logic:iterate id="creditoDetalleFormVO" name="DetalleActionForm" property="creditoDetalleFormVO" type="cl.araucana.cp.presentation.struts.forms.CreditoDetalleFormVO">
				  					                            	<tr>
					  					                            	<td align="right" width="10%" nowrap="nowrap" ="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" property="folioCredito"/></td>
									                            		<td align="center" width="5%"  nowrap="nowrap" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" property="numCuota"/></td>
									                            		<td align="right" width="10%" nowrap="nowrap" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" format="$ ###,###,###" property="valorCouta"/></td>
									                            		<td align="right" width="10%" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" property="fechaVencimiento" format="dd-MM-yyyy"/></td>
									                            		<td align="right" width="10%" nowrap="nowrap" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" format="$ ###,###,###" property="capital"/></td>
									                            		<td align="right" width="10%" nowrap="nowrap" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" format="$ ###,###,###" property="seguros"/></td>
									                            		<td align="right" width="10%" valign="middle" class="textos-formularios2"><bean:write name="creditoDetalleFormVO" format="$ ###,###,###" property="intereses"/></td>									                            											                            											                            		
									                            	</tr>
								                            	</logic:iterate>
							                            	</logic:present>
							                            </table>
						                            </td>
					                          </tr>
				                         <%}%>
				                           <% if (((String)nombreDetalle).trim().equals("Leasing")){ %>
				                               <tr id="seccionSeccionLeasing" style="display:none">
							                            <td colspan="2">
								                            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
								                            	<tr>
								                            		<td align="center" width="30%" valign="middle" class="barra_tablas">Cuenta Corriente</td>
								                            		<td align="center" width="20%" valign="middle" class="barra_tablas">Fecha de Vencimiento</td>
								                            		<td align="center" width="40%" valign="middle" class="barra_tablas">Monto en $</td>
								                            		<td align="center" width="30%" valign="middle" class="barra_tablas">Monto en UF</td>
								                            	</tr>
							                            	<logic:notEmpty name="DetalleActionForm" property="leasingDetalleFormVO">
								                            	<logic:iterate id="leasingDetalleFormVO" name="DetalleActionForm" property="leasingDetalleFormVO" type="cl.araucana.cp.presentation.struts.forms.LeasingDetalleFormVO">
				  					                            	<tr>
									                            		<td align="center" width="30%" valign="middle" class="textos-formularios2"><bean:write name="leasingDetalleFormVO" property="numCuenta"/></td>
									                            		<td align="center" width="20%" valign="middle" class="textos-formularios2"><bean:write name="leasingDetalleFormVO" format="dd-MM-yyyy" property="fechaVencimiento"/></td>
									                            		<td align="right" width="40%" valign="middle" class="textos-formularios2"><bean:write name="leasingDetalleFormVO" format="$ ###,###,###" property="monto"/></td>
									                            		<td align="right" width="30%" valign="middle" class="textos-formularios2"><bean:write name="leasingDetalleFormVO" property="montoUF"/></td>
									                            	</tr>
									                            	</logic:iterate>
							                            	</logic:notEmpty>
								                            </table>
							                            </td>
						                          </tr>
				                         <%}%>
		                           </logic:iterate>
							</table>
							<script language="javaScript">
								document.getElementById("${seccion.idTipoSeccion}-${detalle.idDetalleSeccion}").style.display='none';
							</script> 
							</td>
						</tr>
					</logic:iterate>
		                <tr class="textos_formcolor"> 
		                  	<td align="left" valign="middle" class="Totales_Numeros"><strong>TOTAL ${seccion.nombre}</strong></td>
		                  	<td colspan="3" align="right" valign="middle" class="Totales_Numeros">$${seccion.total}</td>
		                </tr>
		            </table>
		            </td>
				</tr>
			</logic:iterate>
	            <tr valign="top"> 
	              <td align="center" class="textos_formcolor"><span class="Totales_Numeros">MONTO TOTAL</span></td>
	              <td align="center" class="textos_formcolor"><div align="right"><p class="Totales_Numeros">$${DetalleActionForm.totalCmp}</p></div></td>
	            </tr>
				<tr> 
					<td>&nbsp;</td>
				</tr>
			</table>
		</logic:notPresent>
  		</td>
	</tr>
    <logic:notPresent name="msg">
	    <tr> 
			<td height="33" valign="top" align="center">
		  		<input name="operacion" type="button" class="btn2" value="${imprimirResumen}" onclick="javascript:imprimirResumen();"/>
		  		<logic:equal name="DetalleActionForm" property="puedePagar" value="1">
			  		<c:if test="${bloqueoPagoCaja == 0}" >
				  		<input name="operacion" type="submit" class="btn4" value="${pagoCaja}" onclick="javascript:pagarEnCaja();"/>
				  	</c:if>
				  	<c:if test="${bloqueoPagoCaja == 1}" >
	            		<html:button property="operacion" onclick="alert('\n El plazo válido para pagar Comprobantes por Caja ha finalizado'); limpiar();" alt="Fuera de plazo para pago de Comprobantes por Caja" title="Fuera de plazo para pago Comprobantes por Caja"  value="${pagoCaja}" styleClass="btn4"/>
	           		</c:if>	
	           		<c:if test="${bloqueoPagoLinea == 0}" >
			  			<input name="operacion" type="submit" class="btn4" onclick="validaPagoCero(); return false;" value="${pagoLinea}"/>
			  		</c:if>
			  		<c:if test="${bloqueoPagoLinea == 1}" >
	            		<html:button property="operacion" onclick="alert('\n El plazo válido para pagar Comprobantes en Línea  ha finalizado'); limpiar();" alt="Fuera de plazo para pago de Comprobantes en Línea" title="Fuera de plazo para pago de Comprobantes en Línea"  value="${pagoLinea}" styleClass="btn4"/>
	           		</c:if>
           		</logic:equal>
			</td>
	    </tr>
	</logic:notPresent>
	<tr><td>&nbsp;</td></tr>
</table>
</html:form>
<script language="javaScript"> 
<!-- 
cargaConvenios('${DetalleActionForm.convenio}');

var divExpandidos="";
function swapAll(id, imgId) 
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
		var existentes=divExpandidos.split("@");
		divExpandidos="";
		for (i=0;i< existentes.length;i++)
		{
			if (existentes[i]!=id)
			{
				if (divExpandidos=="")
					divExpandidos+=existentes[i];
				else
					divExpandidos+="@"+existentes[i];
			}
		}
	} else		
	{
		if (divExpandidos=="")
			divExpandidos+=id;
		else
			divExpandidos+="@"+id;
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
	}
	
}

	function imprimirResumen()
	{
		alert('Informe No Valido para Pago');
		document.getElementById('detallesMostrar').value = divExpandidos;
		document.getElementById('estadoImpresion').value = "OK";
		document.getElementById('periodo').value = '${periodo}';
		document.forms[0].target="ImprimirComprobanteFicha";
		Abrir_ventana();
		document.forms[0].submit();
	}
  
	function Abrir_ventana() 
	{
		var opciones="height=600,width=650,toolbar=no,directories=no,scrollbars=yes,status=no,linemenubar=no,resizable=yes,modal=yes";
		window.open("","ImprimirComprobanteFicha",opciones);
	}

	function limpiar()
	{
		document.getElementById('estadoImpresion').value = "";
		document.forms[0].target="";
	}

function validaPagoCero()
{
	if (${DetalleActionForm.totalLong} == 0)
		envia('${pagoLinea}');
	else
		abrePopup('${pagoLinea}');
}
function pagarEnCaja(){
	formu = document.getElementById("formulario");
	formu.target = "popup";
	return true;

}
function mostrarOcultarTablas(id,imgId){
	elem = document.getElementById(id);
	img = document.getElementById(imgId);
	
	if (elem.style.display != "none") {
    	elem.style.display = "none"; //ocultar fila 
    	img.src = '<c:url value="/img/ico_mas.gif" />';
	  } else {
	    img.src = '<c:url value="/img/ico_menos.gif" />';
    	elem.style.display = ""; //mostrar fila 
	  }
	
}
// End --> 
</script>
