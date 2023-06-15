<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true"></jsp:include>
<title>Info Deudor</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
<p class="titulo">Certificados Empresa</p>
<div id="datosAfiliado">
			<span><b>RUT : </b>${rutEmpresa}</span>
</div>
</body>
</html>