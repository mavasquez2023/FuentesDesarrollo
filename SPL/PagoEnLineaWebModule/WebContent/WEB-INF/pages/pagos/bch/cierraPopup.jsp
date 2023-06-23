<%
StringBuffer url = new StringBuffer("");
url.append(request.getScheme() + "://");
url.append(request.getServerName() + ":" + request.getServerPort());
url.append(request.getContextPath() + "/pagobch/TerminoPago.do");
%>
<html>
	<head>
		<script language="javascript" type="text/javascript">
		<!--
			function redirigirCerrar() {
				window.opener.location.href = "<%= url.toString() %>";
				window.close();
			}
		//-->
		</script>	
	</head>
	<body onLoad="redirigirCerrar()">
	</body>
</html>