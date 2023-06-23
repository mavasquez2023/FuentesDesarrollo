<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<jsp:useBean id="render" class="cl.araucana.spl.util.Renderer" scope="page"/>

<script type="text/javascript" language="JavaScript">
	
	function cancelar() {
		frm = document.getElementById('formu-inicio');
		frm.submit();
	}

</script>

<html:form action="admin/importarRendicion/ins">
<html:hidden property="medioPago.codigo"/>
<table width="97%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong>Importar Rendici&oacute;n</strong></td>             
            </tr>            
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
<tr align="center" valign="top"> 
    <td class="Titulos">
	<table width="95%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td bgcolor="#CCCCCC">
          	<table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr> 
                <td align="center" bgcolor="#FFFFFF">
                <table width="100%" border="0" cellpadding="0" cellspacing="0">
                    <tr> 
                      <td align="left" width="30%" class="barratablas"><span class="Verde">*</span>Banco</td>                      
                      <td align="left" width="70%" bordercolor="#FFFFFF" class="textos-formularios1a">
                      	<c:out value="${importarRendicionForm.medioPago.descripcion}"></c:out>
                      </td>                     
                    </tr>
                    <tr> 
                      <td align="left" class="barratablas"><span class="Verde">*</span>Archivo</td>                      
                      <td align="left" bordercolor="#FFFFFF" class="textos-formularios1a">
                      	<c:out value="${importarRendicionForm.rendicion.fileName}"></c:out>
                      </td>                     
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

<c:choose>
	<c:when test="${importarRendicionForm.flagErrorCabeceraControl == '1'}">
		<tr align="center" valign="top"> 
		    <td>&nbsp;</td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td align="center">
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="73%" height="30" align="left" valign="top"><span class="titulos_formularios">Detalle Errores</span></td>
		        </tr>
		      </table>
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="80%" height="42" bordercolor="#FFFFFF" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		              <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		                <td width="100%" height="14"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr> 
		                      <td bgcolor="#CCCCCC"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                          <tr> 
		                            <td align="center" bgcolor="#FFFFFF">
		                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                                <tr valign="middle"> 
		                                  <td width="20%" height="20" align="center" valign="middle" nowrap="nowrap" class="barra_tablas">
		                                  	<div align="center"><span class="subtitulos_tablas"><strong>Ubicaci&oacute;n</strong></span><strong><br />
		                                      </strong></div>
		                                  </td>
		                                  <td width="80%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Problema</strong></span><strong><br />
		                                    </strong>
		                                  </td>
		                                </tr>
		                                <logic:present name="importarRendicionForm" property="listaNoImportados">                                
		                                <logic:iterate id="mensaje" type="cl.araucana.spl.beans.Mensaje" name="importarRendicionForm" property="listaNoImportados">
		                                    <tr> 
			                                  <td height="20" align="left" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos_formularios">
			                                  	<c:out value="${mensaje.tipo}"></c:out>
			                                  </td>
			                                  <td align="left" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos_formularios">
	                                			<c:out value="${mensaje.descripcion}" escapeXml="false"></c:out><br>                                		
			                                  </td>
			                                </tr>
		                                </logic:iterate>
		                                </logic:present>
                               
		                            </table>
		                            </td>
		                          </tr>
		                        </table></td>
		                    </tr>
		                  </table></td>
		              </tr>
		
		            </table></td>
		        </tr>
		      </table>
		    </td>
		</tr>	
		
	</c:when>
	<c:otherwise>

		<tr align="center" valign="top"> 
		    <td>&nbsp;</td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td align="center">
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="73%" height="30" align="left" valign="top"><span class="titulos_formularios">Resumen de Transacciones</span></td>
		        </tr>
		      </table>
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="80%" height="42" bordercolor="#FFFFFF" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		              <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		                <td width="100%" height="14"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr> 
		                      <td bgcolor="#CCCCCC"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                          <tr> 
		                            <td align="center" bgcolor="#FFFFFF">
		                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                                <tr valign="middle"> 
		                                  <td width="80%" height="20" align="left" valign="middle" nowrap="nowrap" class="barra_tablas">
		                                  	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="subtitulos_tablas"><strong>Descripci&oacute;n</strong></span><br/>
		                                  </td>
		                                  <td width="20%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Cantidad</strong></span><strong><br />
		                                    </strong>
		                                  </td>
		                                </tr>
		                                <tr> 
		                                  <td height="20" align="left" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos_formularios">
		                                  	Rendiciones importadas
		                                  </td>
		                                  <td align="right" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="textos_formularios">
		                                  	<c:out value="${importarRendicionForm.importados}"></c:out>&nbsp;&nbsp;
		                                  </td>
		                                </tr>
		                                <tr> 
		                                  <td height="18" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">
		                                  	Rendiciones no importadas
		                                  </td>
		                                  <td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
		                                  	<c:out value="${importarRendicionForm.noImportados}"></c:out>&nbsp;&nbsp;
		                                  </td>
		                                </tr>
		                                <tr> 
		                                  <td height="18" align="left" valign="middle" nowrap="nowrap" class="textos_formularios">
		                                  	&nbsp;&nbsp;&nbsp;&nbsp;<b>Total de rendiciones archivo</b>
		                                  </td>
		                                  <td align="right" valign="middle" nowrap="nowrap" class="textos_formularios">
		                                  	<b><c:out value="${importarRendicionForm.totalDetallesRendicion}"></c:out></b>&nbsp;&nbsp;
		                                  </td>
		                                </tr>
		                                <tr> 
		                                  <td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                                  	Pagos consistentes
		                                  </td>
		                                  <td align="right" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                                  	<c:out value="${importarRendicionForm.consistentes}"></c:out>&nbsp;&nbsp;
		                                  </td>
		                                </tr>
		                                <tr> 
		                                  <td height="20" align="left" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                                  	Pagos inconsistentes
		                                  </td>
		                                  <td align="right" valign="middle" nowrap="nowrap" class="textos-formcolor2">
		                                  	<c:out value="${importarRendicionForm.inconsistentes}"></c:out>&nbsp;&nbsp;
		                                  </td>
		                                </tr>
		                            </table>
		                            </td>
		                          </tr>
		                        </table></td>
		                    </tr>
		                  </table></td>
		              </tr>
		              
		            </table></td>
		        </tr>
		      </table>
		    </td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td>&nbsp;</td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td align="center">
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="73%" height="30" align="left" valign="top"><span class="titulos_formularios">Detalle de Transacciones No Importadas</span></td>
		        </tr>
		      </table>
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="80%" height="42" bordercolor="#FFFFFF" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		              <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		                <td width="100%" height="14"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr> 
		                      <td bgcolor="#CCCCCC"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                          <tr> 
		                            <td align="center" bgcolor="#FFFFFF">
		                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                                <tr valign="middle"> 
		                                  <td width="15%" height="20" align="center" valign="middle" nowrap="nowrap" class="barra_tablas">
		                                  	<div align="center"><span class="subtitulos_tablas"><strong>Nro.Pago</strong></span><strong><br />
		                                      </strong></div>
		                                  </td>
		                                  <td width="30%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Detalle</strong></span><strong><br />
		                                    </strong>
		                                  </td>
		                                  <td width="55%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Problema</strong></span><strong><br />
		                                    </strong>
		                                  </td>
		                                </tr>
		                                <logic:present name="importarRendicionForm" property="listaNoImportados">                                
		                                <logic:iterate id="data" type="cl.araucana.spl.beans.DetalleRendicion" name="importarRendicionForm" property="listaNoImportados" indexId="i">
		                                    <tr> 
			                                  <td height="20" align="right" valign="top" bordercolor="#CCCCCC" class="textos_formularios">
			                                  	<c:out value="${data.idPago}"></c:out>&nbsp;
			                                  </td>
			                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos_formularios">
												<c:out value="${data.detalle}"></c:out>&nbsp;			                                  	
			                                  </td>
			                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos_formularios">
			               	                   	<logic:iterate id="mensaje" name="data" property="listErrorImportacion" type="cl.araucana.spl.beans.Mensaje">
			                                			<c:out value="${mensaje.descripcion}" escapeXml="false"></c:out><br>                                		
			                                	</logic:iterate>
			                                  </td>
			                                </tr>
		                                </logic:iterate>
		                                </logic:present>
		                                
		                                <logic:empty name="importarRendicionForm" property="listaNoImportados">
		                                <tr> 
		                                  <td height="20" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formcolor2">&nbsp;</td>
		                                  <td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formcolor2">Sin registros No Importados</td>
		                                </tr>
		                                </logic:empty>                                
		                            </table>
		                            </td>
		                          </tr>
		                        </table></td>
		                    </tr>
		                  </table></td>
		              </tr>
		
		            </table></td>
		        </tr>
		      </table>
		    </td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td>&nbsp;</td>
		</tr>
		
		<tr align="center" valign="top"> 
		    <td align="center">
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="73%" height="30" align="left" valign="top"><span class="titulos_formularios">Detalle de Transacciones Inconsistentes</span></td>
		        </tr>
		      </table>
		      <table width="97%" border="0" cellpadding="0" cellspacing="0">
		        <tr> 
		          <td width="80%" height="42" bordercolor="#FFFFFF" bgcolor="#FFFFFF"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		              <tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
		                <td width="100%" height="14"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                    <tr> 
		                      <td bgcolor="#CCCCCC"><table width="100%" border="0" cellpadding="0" cellspacing="0">
		                          <tr> 
		                            <td align="center" bgcolor="#FFFFFF">
		                            <table width="100%" border="0" cellpadding="0" cellspacing="0">
		                                <tr valign="middle"> 
		                                  <td width="15%" height="20" align="center" valign="middle" nowrap="nowrap" class="barra_tablas">
		                                  	<div align="center"><span class="subtitulos_tablas"><strong>Nro.Pago</strong></span><strong><br />
		                                      </strong></div>
		                                  </td>
		                                  <td width="30%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Detalle</strong></span><strong><br />
		                                    </strong>
		                                  </td>
		                                  <td width="55%" align="center" nowrap="nowrap" class="barra_tablas">
		                                  	<span class="subtitulos_tablas"><strong>Problema</strong></span><strong><br />
		                                    </strong>
		                                  </td>                                    
		                                </tr>
		                                <logic:present name="importarRendicionForm" property="listaInconsistentes">
			                                <logic:iterate id="data" type="cl.araucana.spl.beans.DetalleRendicion" name="importarRendicionForm" property="listaInconsistentes">
				                                <tr> 
				                                  <td height="20" align="right" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
				                                  	<c:out value="${data.idPago}"></c:out>&nbsp;	                                  
				                                  </td>
				                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
													<c:out value="${data.detalle}"></c:out>&nbsp;	                                  
				                                  </td>
				                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
				               	                   	<logic:iterate id="mensaje" name="data" property="listErrorInconsistente" type="cl.araucana.spl.beans.Mensaje">
				                                			<c:out value="${mensaje.descripcion}" escapeXml="false"></c:out><br>                                		
				                                	</logic:iterate>
				                                  </td>                                
				                                </tr>
			                                </logic:iterate>
		                                </logic:present>
		                                <logic:present name="importarRendicionForm" property="listaInconsistentesPagos">
			                                <logic:iterate id="data" type="cl.araucana.spl.beans.Pago" name="importarRendicionForm" property="listaInconsistentesPagos">
				                                <tr> 
				                                  <td height="20" align="right" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
				                                  	<c:out value="${data.id}"></c:out>&nbsp;	                                  
				                                  </td>
				                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
													<c:out value="${data.pagador}"></c:out>&nbsp;	                                  
				                                  </td>
				                                  <td align="left" valign="top" bordercolor="#CCCCCC" class="textos-formcolor2">
				                                	<c:out value="${data.mensaje.descripcion}" escapeXml="false"></c:out><br>                                		
				                                  </td>                                
				                                </tr>
			                                </logic:iterate>
		                                </logic:present>		                                
		                                <logic:empty name="importarRendicionForm" property="listaInconsistentes">
		                                	<logic:empty name="importarRendicionForm" property="listaInconsistentesPagos">
			                                <tr> 
			                                  <td height="20" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formcolor2">&nbsp;</td>
			                                  <td colspan="2" align="left" valign="middle" bordercolor="#CCCCCC" class="textos-formcolor2">Sin registros Inconsistentes</td>
			                                </tr>
			                                </logic:empty>
		                                </logic:empty>
		                                
		                            </table>
		                            </td>
		                          </tr>
		                        </table></td>
		                    </tr>
		                  </table></td>
		              </tr>
		
		            </table></td>
		        </tr>
		      </table>
		    </td>
		</tr>
	
	
	
	</c:otherwise>
</c:choose>


<tr align="center" valign="top"> 
    <td>&nbsp;</td>
</tr>
<tr align="center" valign="top"> 
    <td>&nbsp;</td>
</tr>

<tr align="center" valign="top"> 
    <td align="center">
         <table width="100%" border="0" cellpadding="0" cellspacing="0">
         <tr> 
           <td>&nbsp;</td>                      
           <td align="right" bordercolor="#FFFFFF" colspan="2">           
			<input name="Button2" type="button" class="btn2" value="Cancelar importaci&oacute;n" onclick="javascript:cancelar();" />&nbsp;&nbsp;&nbsp;
			<c:choose>
				<c:when test="${importarRendicionForm.flagErrorCabeceraControl != '1'}">
					<c:if test="${importarRendicionForm.importados > 0}">
		           		<input name="Button2" type="submit" class="btn2" value="Aceptar importaci&oacute;n" />
		           	</c:if>
				</c:when>
			</c:choose>
           </td>                     
         </tr>                                
         </table>
    </td>
</tr>
    
</table>
</html:form>

<html:form action="admin/importarRendicion/frm" styleId="formu-inicio">
</html:form>


