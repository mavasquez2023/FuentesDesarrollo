<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/Consulta" styleId="formuConsulta"> 
<input type="hidden" name="accion"    id="accion"    value="reportes" />
<input type="hidden" name="subAccion" id="subAccion" value="excel" />

<script type="text/javascript">
	function enviar()
	{				
		document.getElementById("accion").value = "reportes";
		document.forms[0].submit();
	}
</script>
<table width="590" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td>
		<html:errors />
	</td>
</tr>
<tr>
	<td valign="top">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr> 
          <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
              <tr valign="bottom"> 
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Generar Archivo de Consultas</strong>
                
                </td>
              </tr>
            </table>
            <table width="100%" border="0" cellpadding="1" id="tipoProcesoTable" cellspacing="1" class="tabla-datos" style="display:none">
              <tr> 
	            <td><strong>Tipo de N&oacute;mina:</strong></td>
	            <td>
		            <select name="tipoProceso" id="tipoProceso" Class="campos" onchange="enviar();">
						<option value="R" selected="selected">R</option>
					</select>
					
	            	<!-- <html:select property="tipoProceso" styleClass="campos" styleId="tipoProceso" onchange="enviar();">
						<html:optionsCollection property="tiposProcesos" label="descripcion" value="idTipoNomina" />
					</html:select> -->
               	</td>
          	</tr>
          	<tr> 
            	<td height="4" colspan="2" bgcolor="#85b4be"></td>
         	</tr>
            </table>
            <br />
            <logic:notEmpty name="ConsultaActionForm" property="consulta">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
		               <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		               <tr class="subtitulos_tablas" align="center" valign="middle">
		                 <td colspan="2" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><img src="<c:url value="/img/flecha2.gif" />" width="12" height="12" border="0" />&nbsp;RUT</td>
		                 <td nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Nombre</td>
		                 <td width="15%" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" style="display:none">Convenio</td>
		                 <td width="21%" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Descargar</td>
		               </tr>
		               <logic:iterate id="empresa" name="ConsultaActionForm" property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
							<bean:size id="numConvenios" name="empresa" property="convenios"/>
							<tr valign="middle">
	                       		<c:choose>
					   		    	<c:when test="${numConvenios == 1}">
					   		    		<td class="${estilo}">&nbsp;</td>
					   		    	</c:when>
					   				<c:otherwise>
										<td width="2%" align="center" valign="middle" nowrap="nowrap" class="${estilo}"><a href="#" onclick="swapAll('${nFila}', 'img${nFila}');"><img id="img${nFila}" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a></td>
	                        		</c:otherwise>
					   		  	</c:choose>
                        		<td align="left" nowrap="nowrap" class="${estilo}"><div align="right">${empresa.idEmpresaFmt}</div></td>
                        		<td class="${estilo}" width="50%">${empresa.razonSocial}</td>
                        		<c:choose>
					   		    	<c:when test="${numConvenios == 1}">
					   		    		<logic:iterate id="convenio" name="empresa" property="convenios">
						   		    		<td valign="middle" class="${estilo}" style="display:none"><div align="right">${convenio.idConvenio}</div></td>
						                    <td valign="middle" class="${estilo}"><div align="center"><input type="button" class="btn-1" value="Descargar" onclick="javascript:excel('<c:url value="/Consulta.do" />', '${empresa.idEmpresa}', '${convenio.idConvenio}');" /></div></td>
						                    </tr>
					   		    		</logic:iterate>
					   		    	</c:when>
					   				<c:otherwise>
					   					<td class="${estilo}" colspan="2">&nbsp;</td>
					   					</tr>
					   					<tr valign="middle" id="${nFila}">
					   						<td colspan="5">
					   						<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
							   					<logic:iterate id="convenio" name="empresa" property="convenios">
							   						<tr>
							   							<td class="${estilo}" colspan="3" width="64%">&nbsp;</td>
								   		    			<td align="right" valign="middle" class="${estilo}" style="display:none"><div align="right">${convenio.idConvenio}</div></td>
								                    	<td width="21%" align="center" valign="middle" class="${estilo}"><div align="center"><input type="button" class="btn-1" value="Descargar" onclick="javascript:excel('<c:url value="/Consulta.do" />', '${empresa.idEmpresa}', '${convenio.idConvenio}');" /></div></td>						                    
								                    </tr>
							   		    		</logic:iterate>
							   		    	</table>
							   		    	</td>
					   		    		</tr>
<script language="javaScript">
document.getElementById("${nFila}").style.display='none';
</script>
					   				</c:otherwise>
					   		  	</c:choose>
						</logic:iterate>
					</table>
				</td>
			</tr>
			<bean:size id="nPags" name="ConsultaActionForm" property="numeroFilas"/>
			<c:if test="${nPags > 1}">
				<tr> 
					<td align="center" valign="middle" class="numeracion">
						<logic:iterate id="paginacion" name="ConsultaActionForm" property="numeroFilas">
							${paginacion}
						</logic:iterate>
					</td>
				</tr>
			</c:if>
		</table>
	</logic:notEmpty>
	<logic:empty name="ConsultaActionForm" property="consulta">
	<br />
	<div align="center" class="titulo">No hay archivos para mostrar.</div>
	</logic:empty>
    </td>
</tr>
</table>
</td>
	</tr>
</table>
<script language="javaScript"> 
<!-- 
function swapAll(id, imgId) 
{
	obj = document.getElementById(id);
	img = document.getElementById(imgId);
    if ( obj.style.display=='' )
    {
		obj.style.display='none';
		img.src = '<c:url value="/img/ico_mas.gif" />';
	} else		
	{
		obj.style.display='';
		img.src = '<c:url value="/img/ico_menos.gif" />';
	}
}

function retornaEnlace(paginacion)
{
	formu = document.getElementById("formuConsulta");
	formu.action = "Consulta.do?accion=consulta&tipoProceso=${ConsultaActionForm.tipoProceso}&paginaNumero=" + paginacion;
	formu.submit();
}
// End --> 
</script>
</html:form>
