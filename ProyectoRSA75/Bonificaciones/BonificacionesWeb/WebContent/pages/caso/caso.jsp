<%@ page language="java"%>
 
<%@ include file = "/includes/env.jsp" %>
<%@ page import="cl.araucana.bienestar.bonificaciones.model.Caso" %>
<jsp:include page="/includes/calendario.js" flush="true"/>
<script language="javascript" src="<html:rewrite page="/includes/jquery-1.3.2.js" />"></script>
<script language="javascript" src="<html:rewrite page="/includes/jquery.blockUI.js" />"></script>
<!-- This Document is Produced or Designed by www.graphictronics.com -->
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<SCRIPT LANGUAGE="JavaScript">
var cal1 = new CalendarPopup();
cal1.showYearNavigation();
cal1.showYearNavigationInput();
cal1.setMonthNames("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
cal1.setDayHeaders("D","L","M","M","J","V","S");
</SCRIPT> 

<bean:define id="currentDate" name="currentDate" type="java.lang.String" />
	<script language="JavaScript">
	cal1.addDisabledDates("<%= currentDate%>",null);
</script>
<script type="text/javascript">
			
			$(document).ready(function(){			
			
			$("#validateCaso").click(function(){
			
				

					var rutSocio 	= $("#rutSocio").attr("value");
					var casoId 		= $("#casoID").attr("value");
					var numDoc 		= $("#numeroDocumento").attr("value");
					var tipoDoc 	= $("#tipoDocumento").attr("value");
					var opcion 		= $("#opcion").attr("value");
					
					if (opcion == '1'){
					
						if (rutSocio == '' || numDoc == ''){
							alert('Debe completar los campos Rut Socio y Número de Documento para validar el Número de Documento previamente');
							return false;
						}
					
						jQuery.ajax({
		                type: "POST",
				beforeSend: function() {
						   $.blockUI({ message: '<html:image page="/images/ajax-loader.gif"/>  <br><br><b>Verificando Número de Documento... </b>' });     
						    // $('div.examenes').block({ message: null }); 
						 }, complete: function(){
						    $.unblockUI();				    
				         },
				         url: "/BonificacionesWeb/validateCasoAjax.do",
		                 data: "rutSocio="+rutSocio+"&casoId="+casoId+"&numDoc="+numDoc+"&tipoDoc="+tipoDoc,
		                 dataType: ($.browser.msie) ? "text" : "xml",
		                 error : function(XMLHttpRequest, textStatus, errorThrown){alert('Error: ' + textStatus + ", " + errorThrown);},
		                 success: function(data) {
			                 var xml;
							     if (typeof data == "string") {
							        xml = new ActiveXObject("Microsoft.XMLDOM");
							       xml.async = false;
							       xml.loadXML(data);
							     } else {
							       xml = data;
							     }
			                 var respuesta = $(xml).find("respuesta").text();
			                 if (respuesta == "true"){
			                 	if(tipoDoc == 1) {
				                 	if (confirm ("La Boleta Número: " + numDoc + " ya fue ingresada.\n\nPresione Aceptar para continuar o Cancelar para modificar la información.")){
				                 		$("#opcionesCaso").submit();
				                 	}			                 	
			                 	}else if(tipoDoc == 2) {
									if (confirm ("La Factura Número: " + numDoc + " ya fue ingresada.\n\nPresione Aceptar para continuar o Cancelar para modificar la información.")){
				                 		$("#opcionesCaso").submit();
				                 	}			                 	
			                 	}if(tipoDoc == 3) {
				                 	if (confirm ("El Bono Número: " + numDoc + " ya fue ingresada.\n\nPresione Aceptar para continuar o Cancelar para modificar la información.")){
				                 		$("#opcionesCaso").submit();
				                 	}
			                 	}
			                 }else{
			                 	$("#opcionesCaso").submit();
			                 }
		                    
		                 }
		        	}); 
					}else{
						$("#opcionesCaso").submit();
					}													  
					 				
			});
		});
	</script>
 
<title>La Araucana - Intranet</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<%= contextRoot+"/araucana.css" %>' rel="stylesheet" type="text/css">
</head> 

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="/includes/arriba.jsp" %>
<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%>
	        <logic:equal name="caso" property="estado" value="PRECASO">
	                <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link> &gt; Caso
	        </logic:equal>
	        
	        <logic:notEqual name="caso" property="estado" value="PRECASO">        
	        	<html:link page="/getListaCasos.do" target="_top">Lista de Casos</html:link> &gt; Caso
			</logic:notEqual>
		</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="21%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td valign="top">
          <html:errors/>
          <html:form action="/opcionesCaso" styleId="opcionesCaso">
          <table width="97%" border="0" cellspacing="1" cellpadding="1">
            <tr>
              <td>
              <html:select property="opcion" styleClass="menuDespl" styleId="opcion">
              	<html:options name="caso.opciones.valores" labelName="caso.opciones"/>
              </html:select> 
              <a href="#" id="validateCaso"><img src="images/botones/boton_ir.gif" alt="Aplicar" border="0"/></a>
              </td>
            </tr>
          </table>
          <table width="99%" border="0" cellpadding="2" cellspacing="2">
            <tr>
              <td width="52%" valign="top">
                <table width="344" border="0" cellspacing="2" cellpadding="2">
                  <tr>
                    <td width="124"><p><strong>Caso ID:</strong></p>
                    </td>
                    <td width="206"><p>
                        <bean:write name="caso" property="casoID"/></p>
                        <input type="hidden" id="casoID" value="<bean:write name='caso' property='casoID'/>">
                    </td>
                    </tr>
                  <tr>
                    <td><p><strong>Rut Socio <bean:message key="label.obligatorio"/>:</strong></p></td>
                    <td><p>
                    <logic:notEmpty name="caso" property="rutSocio">                      
                      <bean:write name="caso" property="fullRutSocio"/>
                      <input type="hidden" id="rutSocio" value="<bean:write name='caso' property='fullRutSocio'/>">
                      </logic:notEmpty>
                      <logic:notEqual name="grupoUsuario" value="5">
                      <%//if(grupoUsuario != 5){%>
                      	<html:link page="/prepareLookUp.do?destino=socio"><html:img page="/images/botones/boton_look_up.gif" alt="Socio" width="14" height="14" border="0"/></html:link>
                      <%//}%>
                      </logic:notEqual>
                      </p></td>
                    </tr>
                  <tr>
                    <td><p><strong>Nombre Socio:</strong></p></td>
                    <td><p><bean:write name="caso" property="nombreSocio"/></p></td>
                    </tr>
                  <tr>
                    <td><p><strong>Rut Carga:</strong></p></td>
                    <td><p>
                    <logic:notEmpty name="caso" property="rutCarga">                      
                      <bean:write name="caso" property="fullRutCarga"/>
                      </logic:notEmpty>
                      <html:link page="/prepareLookUp.do?destino=carga"><html:img page="/images/botones/boton_look_up.gif" alt="Socio" width="14" height="14" border="0"/></html:link>
					</p></td>
                    </tr>
                  <tr>
                    <td><p><strong>Nombre Carga:</strong></p></td>
                    <td><p><bean:write name="caso" property="nombreCarga"/></p></td>
                    </tr>
                  <tr>
                    <td><p><strong>C&oacute;digo Convenio <bean:message key="label.obligatorio"/>:</strong></p></td>
                    <td><p><bean:write name="caso" property="codigoConvenio"/> <html:link page="/prepareLookUp.do?destino=convenio" target="_top"><html:img page="/images/botones/boton_look_up.gif" alt="Convenio" width="14" height="14" border="0"/></html:link></p></td>
                    </tr>
                  <tr>
                    <td><p><strong>Nombre Convenio:</strong></p></td>
                    <td><p><bean:write name="caso" property="nombreConvenio"/> </p></td>
                    </tr>
                <%//if(grupoUsuario != 5){%>
                <logic:notEqual name="grupoUsuario" value="5">
                  <tr>
                    <td><p><strong>Número Máximo de Cuotas: </strong></p></td>
                    <td><p><bean:write name="caso" property="numeroMaximoCuotas"/> </p></td>
                    </tr>
                 <%//}%>   
                 </logic:notEqual>
                 <%//if(grupoUsuario != 5){%>
                 <logic:notEqual name="grupoUsuario" value="5">
	                  <logic:notEmpty name="caso.muestraVale">
	                  	<tr>
		                    <td><p><strong>Vale:</strong></p></td>
		                    <td><p>
		                    <logic:notEmpty name="caso" property="vale">
		                  	<bean:define id="vale" name="caso" property="vale"/>
		                    <bean:write name="vale" property="codigoVale"/> 
		                    </logic:notEmpty>
		                    <html:link page="/getListaValesSocio.do" name="caso.parametros"><html:img page="/images/botones/boton_look_up.gif" alt="Socio" width="14" height="14" border="0"/></html:link>
		                     </p></td>
	                    </tr>
	                  </logic:notEmpty>
                  <%//}%>
                  </logic:notEqual>
                  <tr>
                    <td><p><strong>Tipo Caso:</strong></p></td>
                    <td>
                    <logic:notEmpty name="caso.botonera.descuento">
                    	<%//if(grupoUsuario == 5){%>  
		                <logic:equal name="grupoUsuario" value="5">
		                    <html:select property="tipoCaso" styleClass="menuDespl">
		 	                    <html:option value="DESCUENTO">Descuento</html:option>
		                    </html:select>
	              		<%//}else{%>
	              		</logic:equal>
	              		<logic:notEqual name="grupoUsuario" value="5">
		                    <html:select property="tipoCaso" styleClass="menuDespl">
		 	                    <html:option value="DESCUENTO">Descuento</html:option>
		        	            <html:option value="REEMBOLSO">Reembolso</html:option>	 
		        	         </html:select>                		
	              		<%//}%>
	              		</logic:notEqual>
	                 </logic:notEmpty>  
	                 <logic:empty name="caso.botonera.descuento">
                    	<%//if(grupoUsuario == 5){%>  
                    	<logic:equal name="grupoUsuario" value="5">
		                    <html:select property="tipoCaso" styleClass="menuDespl">
		        	            <html:option value="REEMBOLSO">Reembolso</html:option>
		                    </html:select>
	              		<%//}else{%>	        
	              		</logic:equal>
	              		<logic:notEqual name="grupoUsuario" value="5"> 
		                    <html:select property="tipoCaso" styleClass="menuDespl">
		 	                    <html:option value="DESCUENTO">Descuento</html:option>
		        	            <html:option value="REEMBOLSO">Reembolso</html:option>	
             		        </html:select>
             		    <%//}%>
             		    </logic:notEqual>
	                 </logic:empty>
                      </td>
                    </tr>
				<%//if(grupoUsuario != 5){%> 
				<logic:notEqual name="grupoUsuario" value="5">                    
                  <tr>
                    <td><p><strong>Compra Bono:</strong></p></td>
                    <td><p>
	                    <logic:equal name="caso" property="indicadorPreCaso" value="SI">
		                    Socio
		                    <html:radio property="compraBono" value="SOCIO"/>
		                    &nbsp;&nbsp;&nbsp;Convenio
		                    <html:radio property="compraBono" value="CONVENIO"/>
						    &nbsp;&nbsp;&nbsp;No
						    <html:radio property="compraBono" value="NO" disabled="true"/>
	                    </logic:equal>
	                    <logic:equal name="caso" property="indicadorPreCaso" value="NO">
		                    Socio
		                    <html:radio property="compraBono" value="SOCIO"/>
		                    &nbsp;&nbsp;&nbsp;Convenio
		                    <html:radio property="compraBono" value="CONVENIO"/>
						    &nbsp;&nbsp;&nbsp;No<html:radio property="compraBono" value="NO"/>
	                    </logic:equal>
                    </p></td>
                  </tr>
				<%//}%>
				</logic:notEqual>                                          
                  <tr>
				<%//if(grupoUsuario != 5){%>                  
                <logic:notEqual name="grupoUsuario" value="5"> 
                  <tr>
                    <td><p><strong>Fecha Ingreso:</strong></p></td>
                    <td><p><bean:write name="caso" property="fechaIngreso" formatKey="format.date"/></p></td>
                  </tr>
				<%//}%>                
				</logic:notEqual>
                  <tr>
                    <td><p><strong>Fecha de Ocurrencia <bean:message key="label.obligatorio"/></strong></p></td>
	                <td>
                   		<p>
                   			<html:text property="fechaDeOcurrencia" styleClass="txtHomeSmall" readonly="true" size="12"/>
                   			
							<A href="#" 
								onClick="cal1.select(document.forms[1].fechaDeOcurrencia,'anchor1','dd/MM/yyyy'); return false;" 
								TITLE="Fecha de Ocurrencia" 
								NAME="anchor1" ID="anchor1"> 
								<html:img page='/images/botones/boton_look_up.gif' border="0"/> 
							</A>   			
                   		</p>
                   	</td>
                  </tr>
				<%//if(grupoUsuario != 5){%>                  
                <logic:notEqual name="grupoUsuario" value="5"> 
                  <tr>
                    <td><p><strong>Estado:</strong></p></td>
                    <td><p><bean:write name="caso" property="estado"/></p>
                    </td>
                    </tr>                    
                  <tr>
                    <td><p><strong>Fecha Estado:</strong></p></td>
                    <td><p><bean:write name="caso" property="fechaEstado" formatKey="format.date"/></p>
					</td>
                  </tr>
				<%//}%>                  
                </logic:notEqual> 
                  <tr>
                    <td><p><strong>Tipo Documento:</strong></p></td>
                    <td>
                    <html:select property="tipoDocumento" styleClass="menuDespl" styleId="tipoDocumento">
 	                    <html:option value="1">Boleta</html:option>
        	            <html:option value="2">Factura</html:option>
        	            <html:option value="3">Bono</html:option>
                    </html:select>
                      </td>
                    </tr>
                  <tr>
                    <td><p><strong>Número de Documento: <bean:message key="label.obligatorio"/></strong></p></td>
                    <td><html:text property="numeroDocumento" styleClass="txtHomeSmall" size="7" styleId="numeroDocumento"/>
					</td>
                  </tr>
				<%//if(grupoUsuario != 5){%>
                <logic:notEqual name="grupoUsuario" value="5"> 
                  <tr>
                    <td><p><strong>Cuotas Internas <bean:message key="label.obligatorio"/>:</strong></p></td>
                    <td><html:text property="cuotasBienestar" styleClass="txtHomeSmall" size="7"/></td>
                    </tr>
                  <tr>
                    <td><p><strong>Cuotas Externas <bean:message key="label.obligatorio"/>:</strong></p></td>
                    <td><html:text property="cuotasConvenio" styleClass="txtHomeSmall" size="7"/></td>
                    </tr>
				<%//}%>
				</logic:notEqual>
                </table>
              </td>
              <td width="27%" align="right" valign="top"><table width="172" border="0" cellspacing="3" cellpadding="1">
 <% if (userinformation.hasAccess("casDetalle")) { %>
 
 	        <logic:notEqual name="caso" property="estado" value="PRECASO">
       	        <logic:equal name="caso" property="indicadorPreCaso" value="SI">
	                <tr>
	                  <td align="right" class="opciones">
	                  	<html:link page="/verDetalleTesoreria.do" paramId="casoId" paramName="caso" paramProperty="casoID" target="_top"><html:img page="/images/botones/caso/historiaPrecaso.gif" alt="Detalles" width="160" height="21" border="0"/></html:link>
	                  </td>
	                </tr>
		        </logic:equal>
			</logic:notEqual>
 <% } %>              
			<logic:notEmpty name="caso.botonera.descuento">
 <% if (userinformation.hasAccess("casImpuesto")) { %>
                <tr>
                  <td width="184" align="right" class="opciones"><html:link page="/setFichaImpuesto.do" target="_top"><html:img page="/images/botones/caso/prestamo.gif" alt="Pr&eacute;stamos" width="160" height="21" border="0"/></html:link></td>
                </tr>
 <% } %>
 <% if (userinformation.hasAccess("casDescuentos")) { %>
                <tr>
                  <td align="right" class="opciones"><html:link page="/getListaDescuentos.do" target="_top"><html:img page="/images/botones/caso/descuentos.gif" alt="Descuentos" width="160" height="21" border="0"/></html:link></td>
                </tr>
                
 				                  
 <% } %>
			</logic:notEmpty>
			<logic:notEmpty name="caso.botonera.comun">
 <% if (userinformation.hasAccess("casEvento")) { %>
                <tr> 
                  <td align="right" class="opciones"><html:link page="/getListaEventos.do" target="_top"><html:img page="/images/botones/caso/eventos.gif" alt="Eventos" width="160" height="21" border="0"/></html:link></td>
                </tr>
 <% } %>
                <logic:greaterThan value="0" name="caso" property="codigoConvenio">
 <% if (userinformation.hasAccess("casDetalle")) { %>
                <tr>
                  <td align="right" class="opciones"><html:link page="/getDetallesCaso.do" target="_top"><html:img page="/images/botones/caso/detalles.gif" alt="Detalles" width="160" height="21" border="0"/></html:link></td>
                </tr>
 <% } %>
                </logic:greaterThan>
			</logic:notEmpty>			
              </table>
              <br>
              <br>
 <% if (userinformation.hasAccess("casDetalle")) { %>              
              <table align="center">
              <tr>
              	<td class="celdaColor1"><p class="vinculosUp">Item</p></td>
              	<td class="celdaColor1"><p class="vinculosUp">Monto</p></td>
              </tr>
  	         	<tr>
              		<td>
              			<p>Total</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="monto" formatKey="format.int"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Descuento</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="montoDescuento" formatKey="format.int"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Aporte Isapre</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="aporteIsapre" formatKey="format.int"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Aporte Bienestar</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="aporteBienestar" formatKey="format.int"/></p>
              		</td>
              	</tr>
<bean:define id="cas" name="caso" type="cl.araucana.bienestar.bonificaciones.model.Caso"/>
<% 
if(cas.getAporteConvenio()>0){%>

  	         	<tr>
              		<td>
              			<p>Aporte Convenio</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="aporteConvenio" formatKey="format.int"/></p>
              		</td>
              	</tr>
<%} %>

<% 
if(cas.getPrestamo()>0 || cas.getAbono()>0){%>
				<tr><th colspan="2"><p>Aporte Socio</p></th>
				</tr>
  	         	<tr>
              		<td>
              			<p>Abono</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="abono" formatKey="format.int"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Préstamo</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="prestamo" formatKey="format.int"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Total</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="aporteSocio" formatKey="format.int"/></p>
              		</td>
              	</tr>
<%}
else{%>
  	         	<tr>
              		<td>
              			<p>Aporte Socio</p>
              		</td>
              		<td>
              			<p>$<bean:write name="caso" property="aporteSocio" formatKey="format.int"/></p>
              		</td>
              	</tr>

<%} %>
              </table>
          
              <br>
			<logic:notEmpty name="caso.botonera.comun">
              <table align="center">
              <tr>
              	<td class="celdaColor1"><p class="vinculosUp">Indicador</p></td>
              	<td class="celdaColor1"><p class="vinculosUp">Estado</p></td>
              </tr>
  	         	<tr>
              		<td>
              			<p>Bonificado</p>
              		</td>
              		<td>
              			<p><bean:write name="caso" property="indicadorBonificacion"/></p>
              		</td>
              	</tr>
              	<logic:equal name="caso" property="tipoCaso" value='<%=Caso.TIPO_DESCUENTO%>'>
  	         	<tr>
              		<td>
              			<p>Descontado</p>
              		</td>
              		<td>
              			<p><bean:write name="caso" property="indicadorDescontado"/></p>
              		</td>
              	</tr>
  	         	<tr>
              		<td>
              			<p>Pagado</p>
              		</td>
              		<td>
              			<p><bean:write name="caso" property="indicadorPago"/></p>
              		</td>
              	</tr>
				</logic:equal>
              	<logic:equal name="caso" property="tipoCaso" value='<%=Caso.TIPO_REEMBOLSO%>'>
  	         	<tr>
              		<td>
              			<p>Reembolso</p>
              		</td>
              		<td>
              			<p><bean:write name="caso" property="indicadorReembolso"/></p>
              		</td>
              	</tr>
				</logic:equal>
              </table>
			</logic:notEmpty>
<%} //fin (userinformation.hasAccess("casDetalle")%>   			
              </td>
            </tr>
          </table>
          
          <br>
          
         </html:form>
       
         </td>
        </tr>
      </table>      
    </td>
  </tr>
</table>
<%@ include file="/includes/pie.jsp" %>
<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>
