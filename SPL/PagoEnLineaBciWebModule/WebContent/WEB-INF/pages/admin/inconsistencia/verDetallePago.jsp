<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>

<table width="90%" align="center" cellpadding="0" cellspacing="0">
<tr>
    <td width="100%" align="center" valign="top" bgcolor="#FFFFFF">
   	  <table width="100%"  align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td>
        <table width="100%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
		   <tr class="subtitulos_tablas">
		     <td width="10%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Nro. Folio</td>
		     <td width="70%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Descripci&oacute;n</td>
		     <td width="20%" valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Monto</td>          		     
		   </tr>
		   <c:forEach var="f" items="${pagoForm.folios}" varStatus="status">
		   <c:choose>
		   	<c:when test="${status.index % 2 == 0}">
		   		<c:set var="estiloFila" value="textos_formularios" />
		   	</c:when>
		   	<c:otherwise>
		   		<c:set var="estiloFila" value="textos-formcolor2" />
		   	</c:otherwise>
		   </c:choose>
		   <tr>
		     <td valign="middle" nowrap="nowrap" class="${estiloFila}" align="center">${f.folio}</td>
		     <td valign="middle" nowrap="nowrap" class="${estiloFila}" align="left">${f.descripcion}</td>
		     <td valign="middle" nowrap="nowrap" class="${estiloFila}" align="right"><fmt:formatNumber value="${f.monto}" /></td>
		   </tr>
		   </c:forEach>
        </table>
        </td>
      </tr>
      </table>
    </td> 
</tr>    
</table> 
