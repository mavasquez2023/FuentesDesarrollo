<%@ include file="../comun/headerJsp.jsp"%>
<html>
<head>
<jsp:include page="../comun/header.jsp" flush="true"></jsp:include>
<title>Info Deudor</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/certificado.css">
</head>
<body>
<p class="titulo">Certificados Trabajador</p>
<div id="datosAfiliado">
			<span><b>RUT : </b>${rut}</span>&nbsp;&nbsp;<span><b>Nombre : </b>${nombre}</span>
			<div><br></div>
			<div><span><b>${mensaje }</b></span></div>
			
</div>
</body>
</html>