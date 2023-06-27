<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<script language="javascript">
<!--
function irByMedioPago(url){

       /*Oculta panel*/
       document.getElementById('p1').style.position= "absolute";
       document.getElementById('p1').style.visibility="hidden";            
       document.getElementById('p2').style.position= "absolute";
       document.getElementById('p2').style.visibility="hidden";            
       /*fin Oculta panel*/


	var wname = 'WINDOW_MEDIO_PAGO';
	window.open('about:blank', wname, 'width=720,height=690,scrollbars=1');
	var form = document.forms['inicio'];
	form.action = url;
	form.target = wname;
	form.submit();
}
//-->



</script>
<br>

<table width="759" border="0" cellpadding="0" cellspacing="0">
<form name="inicio" method="post">
	<input type="hidden" name="sistema" value="${mediosPagoForm.codSistema}">
	<input type="hidden" name="xml" value="${mediosPagoForm.xml}">
	<input type="hidden" name="vector" value="${mediosPagoForm.vector}">
</form>
<tr align="left" valign="top">
 <td width="759" align="left">
<table width="97%" border="0" cellpadding="0" cellspacing="0">
<!-- tr>
 <td align="right" valign="middle" class="tit-13"><a href="javascript:history.go(-2);"><img src="<c:url value="/images/back.jpg" />" border=0 valign="middle" />&nbsp;Volver</a>
 </td>
</tr -->
<tr>
<td height="25" align="left" class="tit-13"><strong>Pagar en Línea</strong></td>
</tr>
<tr valign="top"> 
<td height="30" align="left"><span class="titulos_formularios">${mediosPagoForm.glosa}</span></td>
</tr>
</table>
 </td>
</tr>
<tr align="center" valign="top">
 <td width="759">
  <table width="97%" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
   <tr class="subtitulos_tablas">
     <td valign="middle" nowrap="nowrap" class="barra_tablas" align="center">N° Folio</td>
     <td valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Descripci&oacute;n</td>
     <td valign="middle" nowrap="nowrap" class="barra_tablas" align="center">Monto</td>     
   </tr>   
	<c:forEach var="folio" items="${mediosPagoForm.folios}" varStatus="status">
	<c:choose>
		<c:when test="${status.index % 2 == 0}">
			<c:set var="clase" value="textos_formularios" />
		</c:when>
		<c:otherwise>
			<c:set var="clase" value="textos-formcolor2" />
		</c:otherwise>
	</c:choose>
	<tr>
		<td class="${clase}" width="100" align="center">${folio.numero}</td>
		<td class="${clase}" width="441" align="left">${folio.detalle}</td>
		<td class="${clase}" width="200" align="right"><fmt:formatNumber value="${folio.monto}" /></td>
	</tr>

   </c:forEach>
     <tr>
     <td class="${clase}" width="100" align="left"><strong>Monto Total</strong></td>
     <td colspan="2" class="${clase}" align="right"><fmt:formatNumber value="${mediosPagoForm.montoTotal}" /></td>
     </tr>
  </table>
 </td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr>
  <td>
     <div id="p1" style="position:static;visibility:visible"> 
	<table width="97%" align="center" cellpadding="0" cellspacing="0">
		<tr>
		 <td class="${clase}" >
			Seleccione la institución con la cual desea pagar sus comprobantes:
		 </td>
		</tr>
	</table>
     </div>	
 </td>
</tr>
	
<tr>
  <td width="759">
   <div id="p2" style="position:static;visibility:visible"> 
    <table width="97%" cellpadding="0" cellspacing="0">
    <tr>
      <td colspan="${fn:length(mediosPagoForm.medios)}">&nbsp;<br></td>
    </tr>
     <tr align="center">
     <c:forEach var="medio" items="${mediosPagoForm.medios}">
     	<td>
     		<a href="javaScript:irByMedioPago('${medio.urlIniPago}');">
     			<img src="<c:url value="${medio.imagen}" />" border="0" width="90" height="50" />
     		</a>
     	</td>
     </c:forEach>
     </tr>
    </table>
   </div>

  </td>
</tr>
</table>
