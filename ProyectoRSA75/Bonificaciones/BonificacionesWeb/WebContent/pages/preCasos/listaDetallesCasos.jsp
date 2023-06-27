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

<%@ include file = "/includes/arriba.jsp" %>

<table width="756" border="0" align="center" cellpadding="8" cellspacing="8" bgcolor="#FFFFFF">
  <tr>
    <td><table width="97%" border="0" cellspacing="2" cellpadding="2">
      <tr>
        <td class="caminito"><%@ include file="/includes/camino.jsp"%> Lista de Detalles </td>
      </tr>
    </table>
      <table width="97%" border="0" cellspacing="2" cellpadding="2">
        <tr>
			<td width="23%" valign="top">
				<%@ include file = "/includes/menu.jspf"%>
			</td>
		<td width="77%" valign="top">
		<br>
            <table width="50%" border="0" align="center" cellpadding="1" cellspacing="2">
			  <tr>
				<td colspan="4">
					<h4 class="txtHomeSmall">Ingrese el monto y seleccione el detalle</h4>
				</td>
			  </tr>
		    <html:form action="/setearDetalleSeleccionado">
				<tr>
					<td colspan="4">
			    		<html:select property="opcion" styleClass="menuDespl">
			       	  		<html:options name="opciones.valores.detalles" labelName="opciones.datos.detalles"/>
			        	</html:select>
			    		<html:image property="_filtar" page="/images/botones/boton_ir.gif" border="0" />        	
			    	</td>
				</tr>
	          	<tr>
	                  <td colspan="4">
			       		&nbsp;
	   		           	<html:errors/>
			       	<td>
			    </tr>
			    
		        <logic:present name="validation.message">
		      		<tr>
		      	        <td colspan="4">
			          		<div class="txtHomeSmall">
			          			<font color=red>			
				          				Errores de Validacion:
			          			</font>
			          		</div>
			          	<td>
			        </tr>
		      		<logic:iterate id="register" name="validation.message">	        
			      		<tr>
			      	        <td colspan="4">
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
		          	<td nowrap="nowrap">
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
	                <td>
			       		&nbsp;
			       	<td>
			    </tr>
		      
              <tr>

						<td class="celdaColor1" colspan="4">
							<p class="vinculosUp">
								Los detalles por distrubuir son:
							</p>
						</td>
					</tr>                
	                <tr>
						<td nowrap="nowrap" class="celdaColor1">
							<p class="vinculosUp">
								&nbsp;
							</p>
						</td>	                
						<td nowrap="nowrap" class="celdaColor1">
							<p class="vinculosUp">
								Caso
							</p>
						</td>	                
						<td nowrap="nowrap" class="celdaColor1">
							<p class="vinculosUp">
								Cobertura
							</p>
						</td>
						<td nowrap="nowrap" class="celdaColor1">
							<p class="vinculosUp">
								Monto m&aacute;x por Distribuir
							</p>
						</td>					
	                </tr>
					<logic:iterate id="detalle" name="listaDetalles" indexId="i" type="cl.araucana.bienestar.bonificaciones.vo.DetalleMovimientoPreCasoVO">
						<logic:notEqual name="detalle" property="monto" value="0">
		                <tr bgcolor="#F0F0F0" class="lookup01">
							<td bgcolor="#F0F0F0">
								<html:radio property="indice" value='<%=i+""%>' />
							</td>
							<td bgcolor="#F0F0F0" nowrap="nowrap">
								<bean:write name="detalle" property="casoId"/>
							</td>
			                <td bgcolor="#F0F0F0" nowrap="nowrap">
								<bean:write name="detalle" property="descripcion"/>			                
			                </td>
			                <td bgcolor="#F0F0F0" nowrap="nowrap" align="right">
			                	$<bean:write name="detalle" property="monto" formatKey="format.int"/>
			                </td>
		                </tr>
		                </logic:notEqual>
		        	</logic:iterate>
            </table>
           </html:form>
		</td>
        </tr>
      </table>      
      <p class="lookup01">&nbsp;</p></td>
  </tr>
</table>

<%@ include file = "/includes/pie.jsp" %>

<map name="Map">
  <area shape="rect" coords="475,1,540,16" href="#" alt="Buscar">
  <area shape="rect" coords="390,1,455,16" href="#" alt="Mapa">
  <area shape="rect" coords="314,1,379,16" href="#" alt="Ayuda">
<area shape="rect" coords="232,1,297,16" href="#" alt="Contacto">
</map>
</body>
</html>

