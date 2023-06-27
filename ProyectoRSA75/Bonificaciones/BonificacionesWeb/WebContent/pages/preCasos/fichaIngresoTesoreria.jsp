<%@ page language="java"%>
<%@ page import = "cl.araucana.bienestar.bonificaciones.common.Constants" %>

<%@ include file = "/includes/env.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<SCRIPT language="Javascript">
function validaNumero(x,y){
	var valor=x.value;
	var maximo=y.value;
	if(isNaN(valor)){
		alert("Ingrese un número válido");
	}
	
	if(valor>maximo){
		alert("El monto ingresado supera el máximo permitido");
	}
}
</SCRIPT>

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
        <html:link page="/prepareListaPreCasos.do" target="_top">Lista de Pre-Casos</html:link> &gt; Generar Ingreso</td>
      </tr>
	</table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
          <td width="23%" valign="top"><%@ include file="/includes/menu.jspf" %>
          </td>
          <td width="77%" valign="top">
          	<html:errors/>
          
          	<html:form action="/registrarIngreso">
 	        	<html:select property="opcion" styleClass="menuDespl">
            	  	<html:options name="opciones.valores" labelName="opciones"/>
	        	</html:select>
	        	<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />
          	
          	<table  border="0" cellspacing="2" cellpadding="2">
          		<tr>
          	        <td>
		          		&nbsp;
		          	<td>
		        </tr>
          		<tr>
          			<td nowrap>
						  <h4 class="txtHomeSmall">Ficha Ingreso Tesoreria</h4>
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
	            
	            <bean:define id="datosMovimientoTesoreriaVO" name="datosMovimientoTesoreriaVO" type="cl.araucana.bienestar.bonificaciones.vo.DatosMovimientoTesoreriaVO" />
	      		<logic:iterate id="register" name="datosMovimientoTesoreriaVO" property="listaCasos" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.CasoVO">	      		
				<tr class="lookup01">
				  <td bgcolor="#F0F0F0"><html:link page="/setFichaCaso.do" paramId="codigo" paramName="register" paramProperty="casoID" target="_top"><bean:write name="register" property="casoID"/></html:link></td>
				  <td bgcolor="#F0F0F0"><html:link page="/setFichaSocio.do?source=socio" paramId="rut" paramName="register" paramProperty="rutSocio" target="_top"><bean:write name="register" property="rutSocio"/></html:link></td>
                  <td bgcolor="#F0F0F0"><bean:write name="register" property="nombreConvenio"/></td>
                  
                  	<%if(datosMovimientoTesoreriaVO.getTipoMovimiento().equals(Constants.MOVI_INGRESO_ISAPRE)) { %>
		                  <td bgcolor="#F0F0F0">$<bean:write name="register" property="montoIngresoIsapreTesoreria" formatKey="format.int"/></td>
					<%} else { %>
		                  <td bgcolor="#F0F0F0">$<bean:write name="register" property="montoIngresoOtrosTesoreria" formatKey="format.int"/></td>
					<%} %>

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
				
				<tr>
					<td>
						<p><strong>
							Monto del Ingreso:
						</strong></p>
					</td>				
                    <td><p>
                    	<% String maximo = String.valueOf((int)datosMovimientoTesoreriaVO.getMonto()); %>
                      <html:text property="montoIngreso" styleClass="txtHomeSmall" size="18" onblur='<%="validaNumero(this,"+maximo+")"%>'/>
                    </p>
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
