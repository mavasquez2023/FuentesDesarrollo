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
          	<html:errors/>
          
          	<html:form action="/registrarEgreso">
          		<logic:present name="opciones.valores">
	 	        	<html:select property="opcion" styleClass="menuDespl">
	            	  	<html:options name="opciones.valores" labelName="opciones"/>
		        	</html:select>
		        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
		        </logic:present>
          	
          	<table  border="0" cellspacing="2" cellpadding="2">
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
          	        <td>
		          		&nbsp;
		          	<td>
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
	                <td bgcolor="#F0F0F0">$<bean:write name="register" property="montoEgresoTesoreria" formatKey="format.int"/></td>
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
		        
				<tr>
					<td>
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
				
				<logic:present name="preguntarParaQuienEsCheque">
					<tr>
						<td>
							<p><strong>
								Generar Cheque para:
							</strong></p>
						</td>
						<td>
						<html:select property="pagarA" styleClass="menuDespl">
							<html:option value="CONVENIO">Convenio</html:option>
							<html:option value="PROFESIONALES">Profesionales</html:option>
						</html:select>
						</td>
					</tr>
				</logic:present>

          		<tr>
          	        <td>
		          		&nbsp;
		          	<td>
		        </tr>
				
				<logic:notPresent name="preguntarParaQuienEsCheque">
				<html:hidden property="pagarA"/>
					<tr>
						<td class="celdaColor1" colspan="6">
							<p class="vinculosUp">
								El egreso se generará a nombre de:
							</p>
						</td>
					</tr>
					<tr class="lookup01">
						<td bgcolor="#F0F0F0">					
							Nombre:
						</td>
						<td bgcolor="#F0F0F0" colspan="5">
							<bean:write name="datosMovimientoTesoreriaVO" property="fullNombre"/>
						</td>
					</tr>					
					<tr class="lookup01">
						<td bgcolor="#F0F0F0">
							Rut:
						</td>
						<td bgcolor="#F0F0F0" colspan="5">
							<bean:write name="datosMovimientoTesoreriaVO" property="fullRut"/>
						</td>
					</tr>
				</logic:notPresent>
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
