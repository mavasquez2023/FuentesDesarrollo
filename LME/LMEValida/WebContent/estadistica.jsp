<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page errorPage="error.jsp" %>
<html>
<HEAD>
<%@ page language="java" contentType="text/html;"	pageEncoding="UTF-8"%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM Software Development Platform">
<META http-equiv="Content-Style-Type" content="text/css">
<LINK href="<%=request.getContextPath() %>/css/Interna_Araucana.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/web_publica.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/XMLDisplay.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/menu.css" rel="stylesheet" type="text/css">
<LINK href="<%=request.getContextPath() %>/css/screen.css" rel="stylesheet" type="text/css">
<TITLE>Licencia M&#233;dica Electr&#243;nica</TITLE>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/utils.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/XMLDisplay.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/menu.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_setup_new.js"></SCRIPT>
<SCRIPT language="JavaScript" src="<%=request.getContextPath() %>/js/calendar_lang.js"></SCRIPT>

</HEAD>
<BODY onload="schedulerStatus()" bgcolor="white">
<table align="center"><tr><td>
<IMG border="0" src="<%=request.getContextPath() %>/img/[Top frame] La Araucana.jpg"
	width="767" height="81">
 
<!--<IMG border="0" src="<%=request.getContextPath() %>/img/[Banner] Proyectyos_.jpg" width="767" height="81">-->
<form name="form1" method="post" action="">
<!-- Menu bar. -->
<INPUT type="hidden" name="admin" id="admin" value="${role}"/>

<div class="menuBar" style="width:767;">
<c:if test='${role == "admin" }'>
<a class="menuButton" href="" onclick="return buttonClick(event, 'fileMenu');" onmouseover="buttonMouseover(event, 'fileMenu');">Proceso</a>
</c:if>

<!--<a class="menuButton" href="" onclick="return buttonClick(event, 'optionsMenu');" onmouseover="buttonMouseover(event, 'optionsMenu');">Options</a>-->
<!--<a class="menuButton" href="" onclick="return buttonClick(event, 'helpMenu');" onmouseover="buttonMouseover(event, 'helpMenu');">Help</a>-->
</div>
<!-- Main menus. -->
<div id="fileMenu" class="menu" onmouseover="menuMouseover(event)">
	<a class="menuItem" title="Start" onclick="javascript:start();" href="#">Iniciar D&V</a><!-- exec('start') -->
	<a class="menuItem" title="Stop" onclick="javascript:stop();" href="#">Detener D&V</a><!-- exec('end') -->
	<a class="menuItem" title="Start" onclick="javascript:startLiq();" href="#">Iniciar Liq.</a><!-- exec('start') -->
	<a class="menuItem" title="Stop" onclick="javascript:stopLiq();" href="#">Detener Liq.</a><!-- exec('end') -->
	</div>

<!--	<p>Iniciar LME</p>-->

	<div id="divStatus" class="texto"></div>
	  <label>
	  <INPUT type="hidden" name="event"/>
	  </label>	 
	</form>
<!--<hr>-->

</td></tr>
<tr>
	<th colspan="7"><h1>Estadísticas Casos Devolución y Validación LME</h1></th> 
</tr>
</table>
<form name="frm" id="frm">
<TABLE border="0" align="center" cellpadding="0" cellspacing="0">
<tr>	
		<c:if test='${time == "false" }'>
			<td>Ingrese Periodo (AAAAMM):
			<input type="text" name="periodo" id="periodo" value="${periodo}" maxlength="8" size="20"/></td>
			<td><INPUT type="button" class="btn_mini" value="Aceptar" title="Ver Estadísticas" onclick="javascript:abrirEstadistic(document.getElementById('periodo').value);">
			</td>
		</c:if>
		<c:if test='${time == "true" }'>
			<td>Eventos del día:</td>
			<td><INPUT type="button" class="btn_mini" value="Volver" title="Volver" onclick="javascript:history.back();">
			</td>
		</c:if>
		
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td colspan="2">
<TABLE border="1" align="center" cellpadding="0" cellspacing="0">
	
	<tr>
		<td class="textos_formcolor" align="center" valign="middle" width="80">Fecha Evento</td>
		<c:if test='${time == "true" }'>
		<td class="textos_formcolor" align="center" valign="middle">Hora Evento</td>
		</c:if>
		<td class="textos_formcolor" align="center" valign="middle">Código Operador</td>
		<td class="textos_formcolor" align="center" valign="middle">Total Casos</td>
		<td class="textos_formcolor" align="center" valign="middle">Devolución</td>
		<td class="textos_formcolor" align="center" valign="middle">Validación</td>
		<td class="textos_formcolor" align="center" valign="middle">ZonaC</td>				
	</tr>
	<c:forEach var="evento" items="${estadistica}">

	<tr>
		<c:if test='${time == "false" }'>
		<td class="text-11" align="center"><a href="#" onclick="javascript:abrirEstadistic('${evento.dateEvento}');">${evento.dateEvento}</a></td>
		</c:if>
		<c:if test='${time == "true" }'>
		<td class="text-11" align="center">${evento.dateEvento}</td>
		<td class="text-11" align="center">${evento.timeEvento}</td>
		</c:if>
		<td class="text-11" align="center">${evento.codOperador}</td>
		<td class="text-11" align="center">${evento.total}</td>
		<td class="text-11" align="center">${evento.devolucion}</td>
		<td class="text-11" align="center">${evento.validacion}</td>
		<td class="text-11" align="center">${evento.zonaC}</td>
	</tr>
	<c:set var="totalCasos" value="${totalCasos + evento.total}"/>
	<c:set var="totalDevolucion" value="${totalDevolucion + evento.devolucion}"/>
	<c:set var="totalValidacion" value="${totalValidacion + evento.validacion}"/>
	<c:set var="totalZonaC" value="${totalZonaC + evento.zonaC}"/>
	</c:forEach>
	<tr>
	<c:if test='${time == "false" }'>
	<td align="right" colspan="2" class="textos_formcolor">Totales:</td>
	</c:if>
	<c:if test='${time == "true" }'>
	<td align="right" colspan="3" class="textos_formcolor">Totales:</td>
	</c:if>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><B><fmt:formatNumber><c:out value="${totalCasos}"/></fmt:formatNumber></B></td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><B><fmt:formatNumber><c:out value="${totalDevolucion}"/></fmt:formatNumber></B><BR>(<fmt:formatNumber maxFractionDigits="1" > <c:out value="${totalDevolucion/totalCasos*100}" /></fmt:formatNumber>%)</td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><B><fmt:formatNumber><c:out value="${totalValidacion}"/></fmt:formatNumber></B><BR>(<fmt:formatNumber maxFractionDigits="1" > <c:out value="${totalValidacion/totalCasos*100}" /></fmt:formatNumber>%)</td>
				<td align="center" class="textos_formcolor"><fmt:setLocale value="ES" /><B><fmt:formatNumber><c:out value="${totalZonaC}"/></fmt:formatNumber></B><BR>(<fmt:formatNumber maxFractionDigits="1" > <c:out value="${totalZonaC/totalCasos*100}" /></fmt:formatNumber>%)</td>
	</tr>
</table>
</td>
</tr>
</table>
</form>
</body>
</html>
