<%@ include file="/html/comun/taglibs.jsp" %>
<tiles:insert  page="/araucana/jsp/buscarInforme.jsp"/><br/>

<c:set var="estilo" value="textos-formcolor2"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
 <tr> 
  <td align="center" bgcolor="#FFFFFF">
   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
    <tr class="subtitulos_tablas" align="center" valign="middle"> 
     <td bordercolor="#FFFFFF" class="barra_tablas">Rut</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Nombre</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Estado</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
    </tr>
    <logic:iterate id="record" name="listadoEmpresas" indexId="count">
    	<c:choose>
   		    <c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/><c:set var="estilo2" value="tablaClaroBordes"/></c:when>
   			<c:otherwise><c:set var="estilo" value="textos-formcolor2"/><c:set var="estilo2" value="tablaOscuroBordes"/></c:otherwise>
   		</c:choose>
	    <tr valign="middle">
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="rutFormateado"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="razonSocial"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap">
	    		<logic:equal name="record" property="habilitada" value="1">Habilitada</logic:equal>
	    		<logic:notEqual name="record" property="habilitada" value="1">Desabilitada</logic:notEqual>
	    	</td>
	    	<td class="${estilo}" align="left" nowrap="nowrap">
		    	<logic:equal name="record" property="tieneAviso" value="true">
		    		<%--a target="_blank" href="ListarAvisos.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1"--%>
		    		<img align="middle" alt="Listado Avisos" title="Listado Avisos" border="0" src="<c:url value="/img/alert.png" />" onclick="javascript:openPopUp('ListarAvisos.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1&origenTablaAviso=${record.origenTablaAviso}');" style="CURSOR: hand;"/>
		    	</logic:equal>
		    	<logic:equal name="record" property="tieneError" value="true">
		    		<%--a target="_blank" href="ListarErrores.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1"--%>
	    			<img align="middle" alt="Listado Errores" title="Listado Errores" border="0" src="<c:url value="/img/alert-error.png" />" onclick="javascript:openPopUp('ListarErrores.do?idConvenio=${record.idConvenio}&idEmpresa=${record.rutEmpresa}&tipoNomina=${tipoNomina}&aviso=1');" style="CURSOR: hand;"/>
		    	</logic:equal>
	    	</td>
    	</tr>
    </logic:iterate>
   </table>
  </td>
 </tr>
</table>