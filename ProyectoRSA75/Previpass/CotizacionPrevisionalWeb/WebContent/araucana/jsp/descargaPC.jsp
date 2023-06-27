<%@ include file="/html/comun/taglibs.jsp" %>
<html:form action="/DescargaPC" styleId="formuConsulta"> 
<input type="hidden" name="accion"    id="accion"    value="reportes" />
<input type="hidden" name="subAccion" id="subAccion" value="excel" />

<script type="text/javascript">
	function enviar()
	{				
		document.getElementById("accion").value = "reportes";
		document.forms[0].submit();
	}

	//Dado un checkbox "Seleccionar Todos" permite seleccionar o deseleccionar 
	//todos los checkbox de una lista que tiene el mismo nombre que parámetro 'campo'
	function selectAll(campo, estado){
	if(campo== null) return;
	var nroseleccionadas= campo.length;
		if (isNaN(nroseleccionadas)){
			campo.checked= estado;
		}
		else{
			for (var i = 0; i < nroseleccionadas; i++) {
				campo[i].checked= estado;
			}
		}
	}

	//Si un elemento de la lista de checkbox es deseleccionada también lo hace 
	//el checkbox principal "Seleccionar Todos".
	function unSelect(campoTodas, estado){
		if (!estado){
			campoTodas.checked= false;
		}
	}

	//Retorna el número de checkbox seleccionados de una lista
	function numSelected(campo){
		var sels=0;
		nrosel= campo.length;
		for (var i = 0; i < nrosel; i++) {
			if (campo[i].checked==true)
				sels= sels + 1;
		}
		return sels;
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
                <td height="30" bgcolor="#FFFFFF" class="titulo"><strong>Generar Archivos Productos Caja</strong>
                
                </td>
              </tr>
            </table>
            <table id="tipoProcesoTable" class="textos-formularios3">
              <tr> 
            	<td colspan="2">&nbsp;</td>
         	</tr>
              <tr> 
	            <td>Tipo de N&oacute;mina:</td>
	            <td>
	            	<html:select property="tipoProceso" styleClass="campos" styleId="tipoProceso" onchange="enviar();">
						<html:optionsCollection property="tiposProcesos" label="descripcion" value="idTipoNomina" />
					</html:select>
               	</td>
          	</tr>
          	<tr> 
            	<td colspan="2">&nbsp;</td>
         	</tr>
            </table>
            <br />
            <logic:notEmpty name="DescargaPCActionForm" property="consulta">
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
            	<tr> 
                    <td align="center" bgcolor="#FFFFFF">
		               <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		               <tr class="subtitulos_tablas" align="center" valign="middle">
		               	 <td nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><input type="checkbox" name="todas" value="todas" onclick="selectAll(formuConsulta.idCheck, this.checked);"></td>
		                 <td nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">RUT</td>
		                 <td nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Raz&oacute;n Social</td>
		                 <td width="15%" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Convenio</td>
		                 <td colspan="2" width="21%" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Opciones: <input type="checkbox" name="opcionC" id="opcionC" value="C" checked="checked"/>Cr&eacute;dito &nbsp;<input type="checkbox" name="opcionL" id="opcionL" value="L" checked="checked"/>Leasing</td>
		               </tr>
		               <logic:iterate id="empresa" name="DescargaPCActionForm" property="consulta" indexId="nFila">
							<c:choose>
					   		    <c:when test="${nFila % 2 == 0}">
					   		    	<c:set var="estilo" value="textos_formularios"/>
					   		    </c:when>
		   						<c:otherwise>
		   							<c:set var="estilo" value="textos-formcolor2"/>
		   						</c:otherwise>
							</c:choose>
							<bean:size id="numConvenios" name="empresa" property="convenios"/>
							<logic:iterate id="convenio" name="empresa" property="convenios">
								<tr valign="middle">
	                       			<td class="${estilo}"><input type="checkbox" name="idCheck" id="idCheck" value="${empresa.idEmpresa}_${convenio.idConvenio}" onclick="unSelect(formuConsulta.todas, this.checked);"/></td>
                        			<td align="left" nowrap="nowrap" class="${estilo}"><div align="right">${empresa.idEmpresaFmt}</div></td>
                        			<td class="${estilo}">${empresa.razonSocial}</td>
						   			<td valign="middle" class="${estilo}"><div align="center">${convenio.idConvenio}</div></td>
						        	<td valign="middle" class="${estilo}"><div align="center"><input type="button" class="btn-1" value="Descargar" onclick="javascript:excelPC('<c:url value="/DescargaPC.do" />', '${empresa.idEmpresa}', '${convenio.idConvenio}');" /></div></td>
						        	<td valign="middle" class="${estilo}"><div align="center"><input type="button" class="btn-1" value="Procesar" onclick="javascript:procesaCSV('<c:url value="/DescargaPC.do" />', '${empresa.idEmpresa}', '${convenio.idConvenio}');" /></div></td>
						        </tr>
					   		 </logic:iterate>
						</logic:iterate>
						<tr valign="middle">
							<td colspan="5" height="50" align="center" valign="middle"><div align="center"><input type="button" class="btn3" value="Descargar Seleccionadas" onclick="javascript:excelFull('<c:url value="/DescargaPC.do" />', formuConsulta.idCheck);" /></div></td>
						</tr>
					</table>
				</td>
			</tr>
			<bean:size id="nPags" name="DescargaPCActionForm" property="numeroFilas"/>
			<c:if test="${nPags > 1}">
				<tr> 
					<td align="center" valign="middle" class="numeracion">
						<logic:iterate id="paginacion" name="DescargaPCActionForm" property="numeroFilas">
							${paginacion}
						</logic:iterate>
					</td>
				</tr>
			</c:if>
		</table>
	</logic:notEmpty>
	<logic:empty name="DescargaPCActionForm" property="consulta">
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
function retornaEnlace(paginacion)
{
	formu = document.getElementById("formuConsulta");
	formu.action = "DescargaPC.do?accion=consulta&tipoProceso=${DescargaPCActionForm.tipoProceso}&paginaNumero=" + paginacion;
	formu.submit();
}
// End --> 
</script>
</html:form>
