<%@ include file="comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at tde Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="autdor" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/css/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/comun.js'/>"></script>
</HEAD>
<body>
<script type="text/javascript">
<!--
	var bCancel = false;
	
	if (top == self)
		top.location.replace("<c:url value='/'/>");
//-->
</script>
<form action="<c:url value="estadisticas.do" />" name="formulario">
<input type="hidden" name="accion" id="accion" value="estadisticas" />
<input type="hidden" name="subAccion" id="subAccion" value="refresh" />
	<table width="590" border="0" cellpadding="0" cellspacing="0">
        <tr> 
			<td colspan="4">
				<table width="100%" border="0" cellpadding="0" cellspacing="1">
	            	<tr valign="bottom"> 
	              		<td width="38%" height="30" bgcolor="#FFFFFF" class="titulo">
	              			<strong>Estad&iacute;sticas de Nodos</strong>
	              		</td>
        			</tr>
		         </table>
			</td>
		</tr> 
	</table>
	<table width="590" border="0" cellpadding="0" cellspacing="0"> 
        <tr> 
          <td width="3%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas"><input type="checkbox" name="checkbox" value="todo" /></td>
          <td width="39%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Nodo</td>
          <td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">N Con Disp</td>
          <td width="48%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" colspan="5">Mensaje</td>
        </tr>
        <tr> 
          <td width="3%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">&nbsp;</td>
          <td width="49%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas" colspan="2">Nombre Cache</td>
          <td width="9%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Hits</td>
          <td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Miss</td>
          <td width="9%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Hits Mem</td>
          <td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Hits disco</td>
          <td width="10%" align="center" valign="middle" nowrap="nowrap" bordercolor="#CCCCCC" class="barra_tablas">Num Elemen</td>
        </tr>
      </table>
      <table width="590" border="0" cellpadding="0" cellspacing="0">
        <logic:iterate id="report" name="reportes" scope="request">
	        <tr>
	        	<td align="center"  valign="middle" bordercolor="#CCCCCC" class="textos_formularios"><input type="checkbox" name="checkbox" value="check<c:out value="${report.idNodo}" />" /></td>
	        	<td align="left" valign="middle" bordercolor="#CCCCCC" class="textos_formularios"><a href="#" onclick="swapAll('nodo<c:out value="${report.idNodo}" />', 'imgNodo<c:out value="${report.idNodo}" />');"><img id="imgNodo<c:out value="${report.idNodo}" />" src="<c:url value="/img/ico_mas.gif" />" width="11" height="11" border="0" /></a>&nbsp;<strong><c:out value="${report.nombreNodo}" /></strong></td>
		        <td align="left" valign="middle" bordercolor="#CCCCCC" class="textos_formularios"><c:out value="${report.numConnDisponibles}" /></td>
		        <td colspan="5" align="left" valign="middle" bordercolor="#CCCCCC" class="textos_formularios"><c:out value="${report.msg}" />&nbsp;</td>
	        </tr>
	        <tr id="nodo<c:out value="${report.idNodo}" />">
	        	<td colspan="8">
	        	<logic:iterate id="estadistica" name="report" property="estadisticas">
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr>
		        			<td width="3%" align="center" valign="middle" class="textos_formularios"><input type="checkbox" name="checkbox" value="<c:out value="${report.idNodo}" />_<c:out value="${estadistica.region}" />" /></td>
		        			<td width="49%" align="center" nowrap="nowrap" class="textos_formularios"><c:out value="${estadistica.region}" /></td>
		        			<td width="9%" align="center" nowrap="nowrap" class="textos_formularios"><c:out value="${estadistica.cacheHits}" /></td>
		        			<td width="10%" align="center" nowrap="nowrap" class="textos_formularios"><c:out value="${estadistica.cacheMisses}" /></td>
		        			<td width="9%" align="center" nowrap="nowrap" class="textos_formularios"><c:out value="${estadistica.inMemoryHits}" /></td>
		        			<td width="10%" align="center" nowrap="nowrap" class="textos_formularios" ><c:out value="${estadistica.onDiskHits}" /></td>
		        			<td width="10%" align="center" nowrap="nowrap" class="textos_formularios"><c:out value="${estadistica.objectCount}" /></td>
	        		</tr>
	        	</table>
	        	</logic:iterate>
	        	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	        		<tr>
	        			<td width="52%" align="center" nowrap="nowrap" colspan="2" class="textos_formcolor"><strong>TOTALES:</strong></td>
	        			<td width="9%" align="center" nowrap="nowrap"   class="textos_formcolor"><strong><c:out value="${report.totales.cacheHits}" /></strong></td>
	        			<td width="10%" align="center" nowrap="nowrap"  class="textos_formcolor"><strong><c:out value="${report.totales.cacheMisses}" /></strong></td>
	        			<td width="9%" align="center" nowrap="nowrap"   class="textos_formcolor"><strong><c:out value="${report.totales.inMemoryHits}" /></strong></td>
	        			<td width="10%" align="center" nowrap="nowrap"  class="textos_formcolor"><strong><c:out value="${report.totales.onDiskHits}" /></strong></td>
	        			<td width="10%" align="center" nowrap="nowrap"  class="textos_formcolor"><strong><c:out value="${report.totales.objectCount}" /></strong></td>
	        		</tr>
	        	</table>
	        	</td>
	        </tr>
			<script language="javaScript"> 
				document.getElementById("nodo<c:out value="${report.idNodo}" />").style.display='none';
			</script>
        </logic:iterate>
        <tr>
        <td colspan="8" align="center">
			<br />
       		<html:button property="actualizar" styleClass="btn3" value="Actualizar" onclick="javascript:enviar1('estadisticas', 'refresh');"></html:button>
       		<html:button property="LimpiarCache" styleClass="btn3" value="Limpiar Cache" onclick="javascript:enviar1('estadisticas', 'clean');"></html:button>
        </td>
        </tr>
      </table>
	<br />
</form>
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
function cambiaRadio(tipo, valor, n)
{
	for (i = 1; i <= n; i++)
		document.getElementById('r' + tipo + i + valor).checked = 'true';
}
function enviar1(accion, subAccion)
{
	document.getElementById("accion").value = accion;
	document.getElementById("subAccion").value = subAccion;
	document.formulario.submit();
}	
// End -->
</script>
</body>
</HTML>