<%@ include file="/WEB-INF/pages/_commons/taglibs.jsp" %>
<html>
<head>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>

	<link href="<c:url value="/css/Interna_Araucana.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/web_publica.css" />" rel="stylesheet" type="text/css">
	<link href="<c:url value="/css/calendar-system.css" />" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<c:url value="/js/comunes.js" />"></script>
	<title>La Araucana</title>
</head>
<body>
<table width="759" height="259" border="0" align="left" cellpadding="0" cellspacing="0">
<tr align="left" valign="top"> 
	<td width="759">
		<tiles:insert name="header" />
	</td>
</tr>
<tr align="center" valign="top"> 
	<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td width="20%" height="0" valign="top"><tiles:insert name="menu" /></td>
			<td width="80%">
				<tiles:insert name="message" />
				<tiles:insert name="main" />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<tiles:insert name="tail" />
			</td>
		</tr>		
		</table>
	</td>
</tr>
</table>
</body>
</html>
