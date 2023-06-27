<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page import="cl.araucana.spl.util.ResourceHelper"%>

<%
ResourceHelper resources = ResourceHelper.getInstance();
String pagado = (String) request.getAttribute("_PAGADO");
%>

<script type="text/javascript" language="JavaScript">
<!--
	function modificar(frm) {
		if (validate(frm)) {
			frm.submit();
		}
	}
	
	function validate(frm) {
		if (frm.modificable.value=='true') {
			var estadoValor = frm.estadoId.options[frm.estadoId.selectedIndex].value;
			var pagadoValor = frm.pagado.options[frm.pagado.selectedIndex].value;
			
			if (estadoValor == '' && pagadoValor == '') {
				alert('Debe modificar estado o pagado.');
				return false;
			} else {
				if (frm.observacion.value=='') {
					alert('Debe ingresar una observación.');
					frm.observacion.focus();
					return false;
				}
			}
		}
		return true;
	}	
//-->
</script>



<html:form action="admin/modificarInconsistencia/mod">
<html:hidden property="modificable" name="modificarInconsistenciaForm"/>
<html:hidden property="idPago" name="modificarInconsistenciaForm"/>

<table border="0" align="left" cellpadding="0" cellspacing="0" width="100%">
<tr align="center" valign="top"> 
	<td height="32">
		<table width="97%" border="0" cellpadding="0" cellspacing="0">
		<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		    <table width="100%" border="0" cellpadding="0" cellspacing="0">
		      <tr> 
		        <td><table width="100%" border="0" cellpadding="0" cellspacing="1">
		            <tr>
		              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Modificar Inconsistencia</strong> </strong></td>             
		            </tr>
		          </table></td>
		      </tr>
		    </table>
		  </td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td class="Titulos">
		      <table width="100%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td bgcolor="#CCCCCC">
		          	<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
		              <tr> 
		                <td align="center" bgcolor="#FFFFFF">
		                	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr> 
		                      <td width="28%" align="left" valign="middle" nowrap="nowrap" class="barratablas">Nro. pago</td>
		                      <td width="2%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1">:</td>
		                      <td width="65%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1"> 
		                      	<c:out value="${ modificarInconsistenciaForm.idPago }"></c:out>
		                      </td>
		                      <td width="5%" align="right" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos-formularios1a"> 
		                        &nbsp;</td>
		                    </tr>
		                    <tr> 
		                      <td width="28%" align="left" valign="middle" nowrap="nowrap" class="barratablas">Estado actual</td>
		                      <td width="2%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1">:</td>
		                      <td width="65%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1"> 
		                      	<html:hidden property="estadoIdActual" name="modificarInconsistenciaForm"/>
		                      	<c:out value="${ modificarInconsistenciaForm.estado.descripcion }"></c:out>&nbsp;
		                      </td>
		                      <td width="5%" align="right" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos-formularios1a"> 
		                        &nbsp;</td>
		                    </tr>
		                    <tr> 
		                      <td width="28%" align="left" valign="middle" nowrap="nowrap" class="barratablas">Pagado</td>
		                      <td width="2%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1">:</td>
		                      <td width="65%" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formularios1">
		                      	<html:hidden property="pagadoActual" name="modificarInconsistenciaForm" value="<%=pagado%>"/>
		                      	<%=resources.getProperty(Constants.PAGO_PAGADO_AUX + pagado)%> 
		                      </td>
		                      <td width="5%" align="right" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos-formularios1a"> 
		                        &nbsp;</td>
		                    </tr>			                    	                    
		                  	</table>
		                 </td>
		              </tr>
		              
					  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
				          <td width="100%">&nbsp;</td>
				      </tr>

					  <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
				          <td width="100%">
				          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
				              <tr> 
				                <td bgcolor="#CCCCCC"><table width="100%" border="0" cellpadding="0" cellspacing="1">
				                    <tr> 
				                      <td align="center" valign="top" bgcolor="#F5FAF9">
				                        <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				                          <tr bgcolor="#F5FAF9"> 
				                            <td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC"> 
				                              <table width="100%" align="left" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				                                <tr> 
				                                  <td colspan="3" bordercolor="#FFFFFF">&nbsp;</td>
				                                </tr>
				                                <tr> 
				                                  <td width="28%" bordercolor="#FFFFFF" class="text-11">&nbsp;&nbsp;Estado</td>
				                                  <td width="2%" bordercolor="#FFFFFF" class="text-11">:</td>
				                                  <td width="70%" bordercolor="#FFFFFF">
										              	<html:select property="estadoId" name="modificarInconsistenciaForm">
										              		<html:option value="">Seleccione</html:option>
										              		<html:optionsCollection property="estados" value="id" label="descripcion"/>
										              	</html:select>	                                  
				                                  </td>
				                                </tr>
				                                
				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" colspan="3">&nbsp;</td>
				                                </tr>
				                                
				                                <tr> 
				                                  <td width="28%" bordercolor="#FFFFFF" class="text-11">&nbsp;&nbsp;Pagado</td>
				                                  <td width="2%" bordercolor="#FFFFFF" class="text-11">:</td>
				                                  <td width="70%" bordercolor="#FFFFFF">
										              	<html:select property="pagado" name="modificarInconsistenciaForm">
										              		<html:option value="">Seleccione</html:option>
										              		<html:optionsCollection property="pagoPagados" value="id" label="descripcion"/>
										              	</html:select>	                                  
				                                  </td>
				                                </tr>
				                                
				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" colspan="3">&nbsp;</td>
				                                </tr>					                                				                                

				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" valign="top">&nbsp;&nbsp;Observaci&oacute;n</td>
				                                  <td bordercolor="#FFFFFF" class="text-11" valign="top">:</td>
				                                  <td bordercolor="#FFFFFF">
										              	<html:textarea property="observacion" name="modificarInconsistenciaForm" cols="30" rows="6" onkeydown="characterCount(this.form.observacion, 200);" onkeyup="characterCount(this.form.observacion, 200);">
										              	</html:textarea>&nbsp;
				                                  </td>
				                                </tr>

				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" colspan="3">&nbsp;</td>
				                                </tr>	
				                                				                                
				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" colspan="2">&nbsp;</td>
				                                  <td bordercolor="#FFFFFF" align="center" nowrap="nowrap">
					                            	<strong><input type="button" class="btn2" id="Cerrar" value="Cancelar" onclick="window.close();" /></strong>&nbsp;&nbsp;&nbsp;
				                                  	<c:choose>
				                                  		<c:when test="${ !modificarInconsistenciaForm.modificable }">
				                                  			&nbsp;
											            </c:when>	                                  
				                                  		<c:otherwise>
							                            	<strong><input type="button" class="btn2" id="Modificar" value="Aceptar" onclick="modificar(document.forms['modificarInconsistenciaForm']);" /></strong>
				                                  		</c:otherwise>
				                                  	</c:choose>
				                                  </td>
				                                </tr>	
				                                
				                                <tr> 
				                                  <td bordercolor="#FFFFFF" class="text-11" colspan="3">&nbsp;</td>
				                                </tr>				                                			                                
				                                
				                              </table>
				                            </td>
				                          </tr>
				                        </table>
				                      </td>
				                    </tr>
				                  </table></td>
				              </tr>
				            </table>
				          
				          </td>
				      </tr>
				      	              
		            </table>
		          </td>
		        </tr>
		      </table>
		    </td>
		</tr>	
		  
		<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
          <td width="100%">&nbsp;</td>
        </tr>
		

		</table>
	</td>
</tr>
</table>
</html:form>