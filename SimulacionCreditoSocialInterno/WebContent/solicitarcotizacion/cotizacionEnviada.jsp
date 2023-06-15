<%@ include file="../layout/headerJsp.jsp"%>
<html>
<head>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<link type="text/css" href="<%=request.getContextPath()%>/css/simulacion.css" rel="stylesheet" />
	<title>Cotizaci�n enviada exitosamente</title>
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
				Estimado Cliente: su solicitud qued� registrada con el folio ${folio} y dentro de las pr�ximas 24 horas h�biles ser� contactado.
			</p>
		</fieldset>
	</div>
</body>
</html>