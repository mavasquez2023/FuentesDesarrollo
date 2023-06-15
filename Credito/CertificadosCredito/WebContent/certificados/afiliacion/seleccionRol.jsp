
<%@ include file="../../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../../comun/header.jsp" flush="true" />
<title>Certificado Afiliacion</title>
<link rel="stylesheet" href="../../css/certificado.css">
<script type="text/javascript">
	function setRol(rol){
		window.location= "<c:url value='/certificados/afiliacion/generaCertificado.do?rol='/>" + rol;
	}
	

</script>


</head>
<body>
<p class="titulo">Certificado de Afiliaci&oacute;n</p>
		<strong>Nombre Afiliado:</strong> ${nombreAfiliado}
		
		<br><br>
		Seleccione el rol con el cual requiere generar el certificado:
		<br><br>
		<ul>
			<c:forEach items="${rolesList}" var="rol" varStatus="loop">
			<input type="radio" name="rol" id="rol" value="${rol}" onclick="setRol(this.value);"/>${rol}<br><br>
			</c:forEach>
		</ul>
	
</body>
</html>