<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<c:set var="menus" value="${SESSION_KEY_MENUS}" />
<c:set var="lista" value="${SESSION_PATH_NAVEGACION}" />
<c:set var="usuario" value="${currentUser}" />
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<title>Cotizaci&oacute;n Previsional Electr&oacute;nica :: cp.cl</title>
	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	<script src="<c:url value='/js/contenidoCajas.js'/>"></script>
</head>
<body>
<table width="770" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="2" height="95" valign="top"><img src="<c:url value="/img/logo.jpg" />" width="770" height="95" /></td>
	</tr>
	<tr>
		<td colspan="2" height="95" valign="top">&nbsp;</td>
	</tr>
	<tr>
	  <td>
	   <tiles:insert attribute="body"/>
	  </td>
	</tr>
</table>
</body>
</html>
