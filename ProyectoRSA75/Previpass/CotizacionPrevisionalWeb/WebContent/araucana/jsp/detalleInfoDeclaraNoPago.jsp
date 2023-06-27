<%@ include file="/html/comun/taglibs.jsp" %>
<tiles:insert  page="/araucana/jsp/buscarInformeDNP.jsp"/><br/>

<c:set var="estilo" value="textos-formcolor2"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tabla-datos">
 <tr> 
  <td align="center" bgcolor="#FFFFFF">
   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
    <tr class="subtitulos_tablas" align="center" valign="middle"> 
     <td bordercolor="#FFFFFF" class="barra_tablas">Rut</td>
	 <td bordercolor="#FFFFFF" class="barra_tablas">Convenio</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Raz&oacute;n Social</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Período</td>
     <td bordercolor="#FFFFFF" class="barra_tablas">Acci&oacute;n</td>
    </tr>
    <logic:iterate id="record" name="listadoEmpresas" indexId="count">
    	<c:choose>
   		    <c:when test="${count % 2 == 0}"><c:set var="estilo" value="textos_formularios"/><c:set var="estilo2" value="tablaClaroBordes"/></c:when>
   			<c:otherwise><c:set var="estilo" value="textos-formcolor2"/><c:set var="estilo2" value="tablaOscuroBordes"/></c:otherwise>
   		</c:choose>
	    <tr valign="middle">
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="rutFormateado"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="idConvenio"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="razonSocial"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap"><bean:write name="record" property="periodo"/></td>
	    	<td class="${estilo}" align="left" nowrap="nowrap">
	    		<a href="#" onclick="verComprobante('${record.codigoBarra}');" title="Ver comprobante" class="links">
					<img align="middle" title="Ver comprobante" border="0" src="<c:url value="/img/ico_pdf.gif" />" />
				</a>
		    </td>
    	</tr>
    </logic:iterate>
   </table>
  </td>
 </tr>
</table>

<script language="javaScript">

function verComprobante(barra){
	url='GenerarComprobPDF.do?accion=pdf&codigo='+barra;
	window.open(url,"nueva","toolbar=no,location=no,directories=<no,status=no,menubar=no,scrollbar=no,resizable=si,width=900,height=600");
}
</script>