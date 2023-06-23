<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<%@ page import="cl.araucana.spl.util.ResourceHelper"%>

<%
ResourceHelper resources = ResourceHelper.getInstance(); %>

<table width="60%" border="0" cellpadding="0" cellspacing="0">
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr>
              <td height="25" align="left" bgcolor="#FFFFFF" class="tit-13"><strong><strong>Bitacora de Modificaciones</strong> </strong></td>             
            </tr>
            <tr valign="top"> 
              <td height="30" align="left" bgcolor="#FFFFFF"><span class="titulos_formularios">Pago ${pagoForm.pago}</span> </td>             
            </tr>
          </table></td>
      </tr>
    </table>
  </td>
</tr>
<tr bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
  <td width="100%" height="14" bordercolor="#FFFFFF" bgcolor="#FFFFFF"> 
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr> 
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1">
            <tr> 
              <td align="left" class="textos-formularios" bgcolor="#FFFFFF">
              

		        <table width="100%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
				   <tr class="subtitulos_tablas" valign="middle" nowrap="nowrap" align="center">
				     <td width="15%" class="barra_tablas" >Fecha</td>
				     <td width="15%" class="barra_tablas" >Usuario</td>
				     <td width="15%" class="barra_tablas" >Estado Anterior</td>
           		     <td width="15%" class="barra_tablas" >Pagado Anterior</td>
				     <td width="40%" class="barra_tablas" >Observaci&oacute;n</td>          		     
				   </tr>
				   <c:forEach var="ev" items="${pagoForm.eventos}" varStatus="status">
				   <c:choose>
				   	<c:when test="${status.index % 2 == 0}">
				   		<c:set var="estiloFila" value="textos_formularios" />
				   	</c:when>
				   	<c:otherwise>
				   		<c:set var="estiloFila" value="textos-formcolor2" />
				   	</c:otherwise>
				   </c:choose>
				   <tr>
				     <td valign="middle" nowrap="nowrap" class="${estiloFila}" align="center"><fmt:formatDate value="${ev.fechaIngreso}" pattern="${datetimepattern}" /></td>
				     <td valign="middle" nowrap="nowrap" class="${estiloFila}" >${ev.usuario}</td>
				     <td valign="middle" nowrap="nowrap" class="${estiloFila}">${ev.estadoAnterior.descripcion}</td>
				     <td valign="middle" nowrap="nowrap" class="${estiloFila}">
				     	<c:set var="pagadoAux" scope="request">${ev.pagadoAnterior}</c:set>
				     	<%=resources.getProperty(Constants.PAGO_PAGADO_AUX + (String) request.getAttribute("pagadoAux"))%> 				     	
				     </td>
				     <td valign="middle" nowrap="nowrap" class="${estiloFila}">&nbsp;${ev.observacion}</td>
				   </tr>
				   </c:forEach>
				   <c:if test="${empty pagoForm.eventos}">
				   	<tr>
				   		<td colspan="4" valign="middle" nowrap="nowrap" class="textos_formularios">No se han registrado correcciones en el pago</td>
				   	</tr>
				   </c:if>
		        </table>
				<br />
				<div align="center">
					<strong>
						<input type="button" class="btn2" id="Cerrar" value="Cerrar" onclick="window.close();" />
					</strong>
				</div>

              </td>             
            </tr>        
          </table>
        </td>
      </tr>
    </table>
  </td>
</tr>
     
</table>