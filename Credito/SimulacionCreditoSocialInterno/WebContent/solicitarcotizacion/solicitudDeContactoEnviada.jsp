<%@ include file="../layout/headerJsp.jsp"%>
<html>
<head>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<link type="text/css" href="<%=request.getContextPath()%>/css/simulacion.css" rel="stylesheet" />
	<title>Solicitud de contacto enviada exitosamente</title>
</head>
<body>
	<div>
		<p class="titulo">
			Contacto
		</p>
		<fieldset class="form-fieldset">
			<h2>Solicitud enviada exitosamente</h2>
			<hr>
			<p>
				Estimado Cliente: su solicitud qued� registrada y ser� pr�ximamente contactado.
			</p>
		</fieldset>
	</div>
</body>
</html>