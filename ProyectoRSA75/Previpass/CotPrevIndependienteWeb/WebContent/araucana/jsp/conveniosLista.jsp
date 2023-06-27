<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/ListarConvenios" styleId="formulario">
<html:hidden property="accion" name="accion" styleId="accion" value="admin" />
<html:hidden property="subAccion" name="subAccion" styleId="subAccion" value="empresas" />
<html:hidden property="subSubAccion" name="subSubAccion" styleId="subSubAccion" value="conveniosCrear" />
<html:hidden property="rutEmpresa"><nested:write property="rutEmpresa" /></html:hidden>
<table width="590" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<html:errors />
		</td>
	</tr>
	<tr>
		<td>
		    <html:messages id="msg" message="true">
				<div class="msgExito">${msg}</div>
			</html:messages>
		</td>
	</tr>
	<tr>
		<td valign="top">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="tabla-datos">
		    	<tr> 
		        	<td width="15%"><strong>RUT:</strong></td>
		           	<td width="20%"><nested:write property="rutEmpresaFmt" /></td>
		            <td><strong>Nombre:</strong></td>
		           	<td><nested:write property="nombreEmpresa" /></td>
		        </tr>
		        <tr> 
		          	<td height="4" colspan="4" bgcolor="#85b4be"></td>
		       	</tr>
		    </table>
	    </td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="1">
							<tr valign="bottom"> 
								<td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Lista de Convenios</strong></td>
								<td align="right" bgcolor="#FFFFFF">
									<c:set var="url"><c:url value='/ListarConvenios.do'/>?rutEmpresa=<nested:write property="rutEmpresa"/>&imprimir=&accion=admin&subAccion=imprimir</c:set>
								    <html:button property="operacion" styleClass="btn3" value="${imprimir}" onclick="javascript:abrirDocImpresion('${url}');"/>
									<nested:equal property="puedeCrear" value="true">
										&nbsp;&nbsp;&nbsp;<html:submit property="operacion" styleClass="btn3" value="${crearConvenio}"/>
									</nested:equal>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
			            <table width="100%" border="0" cellpadding="0" cellspacing="1">
			            	<tr> 
			                    <td align="center" bgcolor="#FFFFFF">
				               		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
										<tr>
						                 	<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;Convenio</td>
						                 	<td width="60%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descripi&oacute;n</td>
						                 	<td width="15%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Estado</td>
						                 	<td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#FFFFFF" class="barra_tablas" colspan="2">Acci&oacute;n</td>
					               		</tr>
					               		<nested:notEmpty property="consulta">
						               		<nested:iterate id="convenio" property="consulta" indexId="nFila">
												<c:choose>
										   		    <c:when test="${nFila % 2 == 0}"><c:set var="estilo" value="textos_formularios"/>
										   		    	<c:set var="estilo2" value="tablaClaroBordes"/>
										   		    </c:when>
							   						<c:otherwise>
							   							<c:set var="estilo" value="textos-formcolor2"/>
							   							<c:set var="estilo2" value="tablaOscuroBordes"/>
							   						</c:otherwise>
												</c:choose>
						               			<tr> 
								                 	<td align="center" valign="middle" nowrap="nowrap" class="${estilo}">
								                 		<div align="right">
								                 			<nested:write format="00" property="idConvenio" />
								                 		</div>
								                 	</td>
								                 	<td nowrap="nowrap" align="left" class="${estilo}">
								                 		<nested:write property="descripcion" />
								                 	</td>
								                 	<td nowrap="nowrap" align="left" class="${estilo}">
								                 		<nested:notEqual property="habilitado" value="0">
								                 			Habilitado
								                 		</nested:notEqual>
								                 		<nested:equal property="habilitado" value="0">
								                 			Deshabilitado
								                 		</nested:equal>
								                 	</td>
						                   			<td align="center" nowrap="nowrap" class="${estilo}">
								                 		<a href="<c:url value="/DetalleConvenio.do?accion=admin&subAccion=empresas&subSubAccion=conveniosFicha&rutEmpresa=${convenio.idEmpresa}&idConvenio=${convenio.idConvenio}" />" title="Ver detalle" class="links">
					                   						<img src="<c:url value="/img/lupa.gif" />" width="14" height="13" border="0"/>
					                   					</a>
						                   			</td>
						                   			<td align="center" nowrap="nowrap" class="${estilo}">
						                   				<nested:equal property="editable" value="true">
							                   				<div align="center">
							                   					<a href="<c:url value="/EditarConvenio.do?accion=admin&subAccion=empresas&subSubAccion=conveniosEditar&rutEmpresa=${convenio.idEmpresa}&idConvenio=${convenio.idConvenio}" />">
							                   						<img src="<c:url value="/img/ico_hojap.gif" />" width="14" height="13" border="0" alt="Editar" title="Editar"/>
							                   					</a>
							                   				</div>
						                   				</nested:equal>
						                   				<nested:notEqual property="editable" value="true">
						                   					&nbsp;
						                   				</nested:notEqual>
						                   			</td>
						               			</tr>
					               			</nested:iterate>
				               			</nested:notEmpty>
					               		<nested:empty property="consulta">
					               			<tr>
							                 	<td nowrap="nowrap" align="left" class="tablaClaroBordes" colspan="5">
							                 		No existen convenios para esta empresa
							                 	</td>
							                 </tr>
					               		</nested:empty>
									</table>
								</td>
							</tr>
			            </table>
		            </td>
		        </tr>
		        
			</table>
		</td>
	</tr>
	<!-- Paginacion -->
	<bean:size id="nPags" name="ListaConveniosActionForm" property="numeroFilas"/>
	<c:if test="${nPags > 1}">
		<tr align="center">
			<td>
			<table border="0" cellpadding="0" cellspacing="0">
				<tr> 
					<td align="center" valign="middle" class="numeracion">
						<logic:iterate id="paginacion" name="ListaConveniosActionForm" property="numeroFilas">
							${paginacion}
						</logic:iterate>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</c:if>
</table>
</html:form>
<script language="javaScript">
<!--
	function retornaEnlace(paginacion)
	{
		formu = document.getElementById("formulario");
		formu.action = "ListarConvenios.do?accion=admin&subAccion=empresas&subSubAccion=conveniosLista&rutEmpresa="+${ListaConveniosActionForm.rutEmpresa}+"&paginaNumero="+paginacion;
		formu.submit();
	}
// -->
</script>

<!--
<html:link styleClass="links" action="ListarConvenios.do?accion=admin&subAccion=empresas&subSubAccion=conveniosLista&rutEmpresa=${ListaConveniosActionForm.rutEmpresa}&paginaNumero=${paginacion}">${paginacion}</html:link> 
  -->