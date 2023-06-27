<html>
<HEAD>
<script>
function redirect(){
	window.location= "/portal/menu.jsp";
}
function cerrarSesion(){
	document.logout.submit();
}
</script>
</HEAD>
<BODY onload="redirect();">
<form method="post" action="ibm_security_logout" name="logout" id="logoutForm">
	<input type="hidden" name="logout" value="Logout">
	<input type="hidden" name="logoutExitPage" value="/index.jsp">
</form>
</BODY>
</html>