<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<body>

	<form name="mandato" id="mandato" action="<%=cl.laaraucana.copagocredito.util.Configuraciones.getConfig("url.ingreso.mandato")%>" method="post">

		<input type="hidden" name="rutAfiliado" id="rutAfiliado" value="${rutEncode}">
		<input type="hidden" name="urlRetorno" id="urlRetorno" value="${urlRetorno}">
	</form>
	
	<script
		src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&amp;render=explicit"></script>
	<script src="assets/js/jquery-3.3.1.js"></script>
	<script src="assets/js/jquery-ui.js"></script>	
	
	<script type="text/javascript">
		$('#mandato').submit();
		
	</script>
</body>
</html>