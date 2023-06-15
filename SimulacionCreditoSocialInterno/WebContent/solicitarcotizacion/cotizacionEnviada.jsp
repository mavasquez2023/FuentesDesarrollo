<%@ include file="../layout/headerJsp.jsp"%>
<html>
<head>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<link type="text/css" href="<%=request.getContextPath()%>/css/simulacion.css" rel="stylesheet" />
	<title>Cotización enviada exitosamente</title>
</head>
<body>
	<div>
		<p class="titulo">
			Contacto
		</p>
		<fieldset class="form-fieldset">
			<h2>Solicitud enviada exitosamente</h2>
			<div class="hr"></div>
			<p>
				Estimado Cliente: su solicitud quedó registrada con el folio ${folio} y dentro de las próximas 24 horas hábiles será contactado.
			</p>
		</fieldset>
	</div>
</body>
</html>