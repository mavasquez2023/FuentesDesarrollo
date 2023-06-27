<%@ include file="/html/comun/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
	<!-- HTTP 1.1 -->
	<meta http-equiv="Cache-Control" content="no-store"/>
	<!-- HTTP 1.0 -->
	<meta http-equiv="Pragma" content="no-cache"/>
	<!-- Prevents caching at the Proxy Server -->
	<meta http-equiv="Expires" content="-1"/>
	<meta name="author" content="Builderhouse Ingenieros (http://www.builderhouse.cl)"/>
	<TITLE>Administraci&oacute;n Cotizaci&oacute;n Previsional</TITLE>
	<link href="<c:url value="/frameset/adminAraucana.css" />" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		function doLogout() 
		{
			document.getElementById("logoutForm").submit();
			parent.BODY.location.reload();
			parent.titulo.location.reload();
			parent.menu.location.reload();
			parent.user.location.reload();
		}
	</script>
</HEAD>
<body>
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/frameset/titulo.jsp">
</form>
<table width="770" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td height="95" valign="top"><img src="<c:url value="/frameset/logo.jpg" />" width="770" height="95" /></td>
	</tr>
   	<tr>
    	<td class="menuSuperior" valign="top" height="20">
			<img src="<c:url value="/frameset/ico_cerrar.gif" />" width="9" height="9" />&nbsp;<a href="<c:url value="/Logout.do"/>" target="_top" class="menuSuperior">Cerrar Sesi&oacute;n</a>
    	</td>
   	</tr>
	<tr>
		<td colspan="2" height="20" valign="top" align="center"><img src="<c:url value="/frameset/sombra.jpg" />" width="739" height="7" /></td>
	</tr>
</table>
</body>
</HTML>