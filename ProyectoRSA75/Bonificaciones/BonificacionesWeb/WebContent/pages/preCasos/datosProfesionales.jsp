<%@ page language="java"%>

<%@ include file = "/includes/env.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
        <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link> &gt; Generar Egreso</td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
          
          	<html:form action="/validarEgresoProfesionales">
 	        	<logic:present name="opciones.valores">
	 	        	<html:select property="opcion" styleClass="menuDespl">
	            	  	<html:options name="opciones.valores" labelName="opciones"/>
		        	</html:select>
		        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
		        </logic:present>
          	
          	<table  border="0" cellspacing="2" cellpadding="2">
				<html:hidden property="pagarA"/>
          		<tr>
          	        <td>
		          		&nbsp;
		          	<td>
		        </tr>
          		<tr>
          			<td nowrap>
						  <h4 class="txtHomeSmall">Ficha Egreso Tesoreria</h4>
		          	</td>
		        </tr>
		        <tr>
	                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.caso.id"/></p></td>
	                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.socio.rut"/></p></td>
	                <td class="celdaColor1"><p class="vinculosUp">Convenio</p></td>
	                <td class="celdaColor1"><p class="vinculosUp"><bean:message key="label.monto"/></p></td>
	                <td class="celdaColor1"><p class="vinculosUp">Ingreso Caso</p></td>
	                <td class="celdaColor1"><p class="vinculosUp">Concepto</p></td>
	            </tr>
                		        
	      		<logic:iterate id="register" name="datosMovimientoTesoreriaVO" property="listaCasos" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.CasoVO">
	              <tr class="lookup01">
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
	                <td bgcolor="#F0F0F0"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio" target="_top"><bean:write name="register" property="rutSocio"/></html:link></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="nombreConvenio"/></td>
	                <td bgcolor="#F0F0F0">$<bean:write name="register" property="monto" formatKey="format.int"/></td>
	                <td bgcolor="#F0F0F0"><bean:write name="register" property="fechaIngreso" formatKey="format.date"/></td>
					<td bgcolor="#F0F0F0"><bean:write name="register" property="descripcionConcepto"/></td>                
	              </tr>
	            </logic:iterate>
		            
		        <tr  class="lookup01">
			        <td class="celdaColor1" colspan="3" align="right">
			        	<p class="vinculosUp">
		        			Total
		        		</p>
		        	</td>
					<td bgcolor="#F0F0F0">
						$<bean:write name="datosMovimientoTesoreriaVO" property="monto" formatKey="format.int"/>
		        	</td>
		        </tr>
		        
          		<tr>
          	        <td>
		          		&nbsp;
		          	<td>
		        </tr>
			</table>
     	  </html:form>
     	  

	  <h4 class="txtHomeSmall">Ingrese los datos solicitados para generar los Egresos</h4>
     	  

	    <html:form action="/administrarDatosProfesionales">
    		<html:select property="opcion" styleClass="menuDespl">
       	  		<html:options name="opciones.valores.profesionales" labelName="opciones.datos.profesionales"/>
        	</html:select>
    		<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />        	
     	  <table>
          	<tr>
                  <td colspan="6">
		       		&nbsp;
   		           	<html:errors/>
		       	<td>
		    </tr>
		    
	        <logic:present name="validation.message">
	      		<tr>
	      	        <td colspan="6">
		          		<div class="txtHomeSmall">
		          			<font color=red>			
			          				Errores de Validacion:
		          			</font>
		          		</div>
		          	<td>
		        </tr>
	      		<logic:iterate id="register" name="validation.message">	        
		      		<tr>
		      	        <td colspan="6">
			          		<div class="txtHomeSmall">
			          			<font color=red>
				          				* <bean:write name="register"/>
			          			</font>
			          		</div>
			          	<td>
			        </tr>			        
				</logic:iterate>
	         	<tr>
	                <td>
			       		&nbsp;
			       	<td>
			    </tr>				
	        </logic:present>		    
		        		    
		    <tr>
		      	<td>
		       		<p><strong>
		       			Nombre <bean:message key="label.obligatorio"/>:
		       		</strong></p>
		       	</td>
                <td>
                  	<p>
	                   <html:text property="nombre" styleClass="txtHomeSmall" maxlength="70" size="63" readonly="true"/>
    	            </p>
                </td>
	                <td>
	                   <html:link page="/getListaProfesionales.do">
	                   	<html:img page="/images/botones/boton_look_up.gif" alt="Profesional" width="14" height="14" border="0"/>
	                   </html:link>
			       	<td>                
		    </tr>		        
		    <tr>
		        <td>
		        	<p><strong>
		          		Rut <bean:message key="label.obligatorio"/>:
		          	</strong></p>
		        </td>
                <td>
                   	<p>
	                   <html:text property="rut" styleClass="txtHomeSmall" maxlength="8" size="12" readonly="true"/>  
	                   <html:text property="dv" styleClass="txtHomeSmall" maxlength="1" size="2" readonly="true"/>
    	             </p>
                </td>
		    </tr>
	        <tr>
	          	<td>
	          		<p><strong>
	          			Monto <bean:message key="label.obligatorio"/>:
	          		</strong></p>
	          	</td>
                <td>
                	<p>
                      <html:text property="monto" styleClass="txtHomeSmall" maxlength="10" size="12"/>
	                </p>
                </td>
	        </tr>
			<tr>
				<td nowrap>
					<p><strong>
						Forma de Pago:
					</strong></p>
				</td>
				<td>
				<html:select property="formaPago" styleClass="menuDespl">
					<html:option value="CHEQUE">Cheque</html:option>
					<html:option value="EFECTIVO">Efectivo</html:option>
				</html:select>
				</td>
			</tr>	        
      		<tr>
      	        <td>
	          		&nbsp;
	          	<td>
	        </tr>
			<tr>
				<td class="celdaColor1" colspan="6">
					<p class="vinculosUp">
						Los egresos que se generarán son:
					</p>
				</td>
			</tr>
		
	        <tr>
	        	<td class="celdaColor1"><p class="vinculosUp">&nbsp;</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Nombre</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Rut</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Monto</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Forma de Pago</p></td>
	            <td class="celdaColor1"><p class="vinculosUp">Sel. Detalles</p></td>
	        </tr>
	        		        
	  		<logic:iterate id="datosProfesional" name="datosMovimientoTesoreriaVO" property="listaProfesionales" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.DatosProfesionalesVO">
	          <tr class="lookup01">
				<td bgcolor="#F0F0F0"><html:radio property="indice"
					value='<%=i+""%>' />
				</td>
	            <td bgcolor="#F0F0F0"><bean:write name="datosProfesional" property="nombre"/></td>
	            <td bgcolor="#F0F0F0" nowrap><bean:write name="datosProfesional" property="fullRut"/></td>
	            <td bgcolor="#F0F0F0" nowrap>$<bean:write name="datosProfesional" property="monto" formatKey="format.int"/></td>
	            <td bgcolor="#F0F0F0" nowrap><bean:write name="datosProfesional" property="tipoPago" /></td>
	            <td bgcolor="#F0F0F0" align="center">
                   <html:link page='<%="/prepareListaDetallesCasos.do?indexProf="+i%>'>
                   	<html:img page="/images/botones/boton_look_up.gif" alt="Agregar Detalle" width="14" height="14" border="0"/>
                   </html:link>
	            </td>
	          </tr>
	          <logic:notEmpty name="datosProfesional" property="detalles">
					<logic:iterate id="detalleProfesional" name="datosProfesional" property="detalles" indexId="j" type="cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO">
					<tr class="lookup01">
						<td align="right" bgcolor="#F0F0F0">
							<bean:write name="detalleProfesional" property="casoId"/>
						</td>
						<td align="left" colspan="2" bgcolor="#F0F0F0">
							<bean:write name="detalleProfesional" property="descripcion"/>
						</td>
						<td align="right" bgcolor="#F0F0F0">
							$<bean:write name="detalleProfesional" property="monto" formatKey="format.int"/>
						</td>
						<td bgcolor="#F0F0F0">
							&nbsp;
						</td>
						<td bgcolor="#F0F0F0">
							<html:link page='<%="/quitarDetalleSeleccionado.do?indexDetalle="+j+
							"&indexProf="+i%>'>
								<html:img page="/images/botones/goma.gif" alt="Eliminar Detalle" width="14" height="14" border="0"/>
							</html:link>
						</td>
					<tr>
					</logic:iterate>
		      </logic:notEmpty>
	        </logic:iterate>
	        
	        <tr  class="lookup01">
		        <td class="celdaColor1" colspan="3" align="right">
		        	<p class="vinculosUp">
	        			Total
	        		</p>
	        	</td>
				<td bgcolor="#F0F0F0" nowrap>
					$<bean:write name="datosMovimientoTesoreriaVO" property="montoTotalProfesionales" formatKey="format.int"/>
	        	</td>
	        </tr>
	        
     	  	</table>
       	  </html:form>
          </td>          
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
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
