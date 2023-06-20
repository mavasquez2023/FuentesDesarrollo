<html>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="Author" content="J-Factory Solutions" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script>

function cerrarSesion(){
	document.salir.submit();
}
</script>
</HEAD>
<BODY onload="cerrarSesion();">
<form method="post" action="ibm_security_logout" name="salir" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/index.jsp">
</form>
</BODY>
</html>