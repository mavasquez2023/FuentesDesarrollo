<%@ include file = "/web/includes/env.jsp"%>
<%@ include file = "/web/includes/header.jsp"%>
<%@ include file = "/web/includes/top.jsp"%>

<SCRIPT LANGUAGE="JavaScript" SRC="<%=sUriMedia %>/js/ar-paging.js"></SCRIPT>
<link href="<%=sUriMedia %>/css/ar-paginacion.css" rel="stylesheet" type="text/css">

<table border="0" cellpadding="0" cellspacing="0" width="100%" height='100%'  class="sello_agua">
<tr>
<td width='160' valign='top'><%@ include file = "/web/includes/opciones.jsp"%></td> <!-- menu de opciones -->
<td width='1%'>&nbsp;</td>
<td valign='top'>

<!-- Begin de la pagina particular -->
<%
String folio = "" + (String)session.getAttribute("credito.folio");
%>

<%@ include file = "/web/pages/creditoVigente/detalleCreditoCabecera.jsp"%>
<br/>
<% 
String vistaDespliegue;
try {vistaDespliegue = "" + (String) session.getAttribute("vistaDespliegue");} catch (Exception ex) {vistaDespliegue="Ver cuotas";}

if ( vistaDespliegue == null || vistaDespliegue.equals("Ver cuotas") || vistaDespliegue.equals("") ) {
%>
<form method="POST" />
<html:hidden property="folio" value="<%=folio %>"/>
<input  class="boton" type="button" name="dummyProperty" value="Volver" onclick="document.fVolver.submit();" onMouseOut="this.className='boton'" >			
&nbsp; &nbsp;
<input  class="boton" type="submit" name="vistaDespliegue" value="Ver pagos" onMouseOut="this.className='boton'" >			
</form>
<%@ include file = "/web/pages/creditoVigente/detalleCuotas.jsp"%>

<%} else { %>

<form method="POST" />
<html:hidden property="folio" value="<%=folio %>"/>
<input  class="boton" type="button" name="dummyProperty" value="Volver" onclick="document.fVolver.submit();" onMouseOut="this.className='boton'" >			
&nbsp; &nbsp;
<input  class="boton" type="submit" name="vistaDespliegue" value="Ver cuotas" onMouseOut="this.className='boton'" >			
</form>
<%@ include file = "/web/pages/creditoVigente/detallePagos.jsp"%>

<%} %>
<form action="/AutoconsultaWeb/web/getCreditos.do" method="POST" name="fVolver">
</form>


<!-- End de la pagina particular -->

</td>
</tr>
</table>

<%@ include file = "/web/includes/footer.jsp"%>